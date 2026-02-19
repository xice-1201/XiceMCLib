package com.xice.mclib.entity;

import com.xice.mclib.XiceMCLib;
import com.xice.mclib.api.XiceMCLibLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class XicePlayer {

  private final Player player;

  public XicePlayer(Player player) {
    this.player = player;
  }

  public String getName() {
    return player.getName();
  }

  /**
   * 向该玩家发送消息
   * <p>
   * 发送的消息支持 MiniMessage 格式
   *
   * @param message 发送的消息（支持 MiniMessage 格式）
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  public void sendMessage(String message) {
    player.getScheduler().run(XiceMCLib.getInstance(), scheduledTask -> {
      player.sendMessage(MiniMessage.miniMessage().deserialize(message));
    }, null);
  }

  /**
   * 查询该玩家指定半径范围内的所有玩家
   * <p>
   * 返回的列表不包含该玩家自己
   *
   * @param range 查询半径（单位：格）
   * @author Xice玄冰
   * @since 1.21.11-1.0-beta
   */
  public @NotNull List<XicePlayer> getNearbyPlayers(double range) {
    // 半径不大于0时直接返回空列表
    if (range <= 0.0) {
      return new ArrayList<>();
    }
    // 预处理所需的参数
    XiceMCLib plugin = XiceMCLib.getInstance();
    XiceMCLibLogger logger = XiceMCLib.getXiceMCLibLogger();
    ConcurrentHashMap<UUID, XicePlayer> retMap = new ConcurrentHashMap<>();
    World world = player.getWorld();
    Location loc = player.getLocation();
    UUID uuid = player.getUniqueId();
    double rangeSquared = range * range;
    // 根据 range 计算需要查询的区块位置与范围
    int minChunkX = (int) Math.floor((loc.getX() - range) / 16.0),
        maxChunkX = (int) Math.floor((loc.getX() + range) / 16.0),
        minChunkZ = (int) Math.floor((loc.getZ() - range) / 16.0),
        maxChunkZ = (int) Math.floor((loc.getZ() + range) / 16.0),
        totalTask = (maxChunkX - minChunkX + 1) * (maxChunkZ - minChunkZ + 1);
    CountDownLatch latch = new CountDownLatch(totalTask);
    // 遍历每一个需要查询的区块
    for (int chunkX = minChunkX; chunkX <= maxChunkX; chunkX++) {
      for (int chunkZ = minChunkZ; chunkZ <= maxChunkZ; chunkZ++) {
        // 检查区块是否加载
        if (!world.isChunkLoaded(chunkX, chunkZ)) {
          latch.countDown();
          continue;
        }
        // 开启查询任务
        int finalChunkX = chunkX;
        int finalChunkZ = chunkZ;
        Bukkit.getRegionScheduler().run(plugin, world, chunkX, chunkZ, scheduledTask -> {
          try {
            Chunk chunk = world.getChunkAt(finalChunkX, finalChunkZ);
            for (Entity entity : chunk.getEntities()) {
              if (!(entity instanceof Player target)) {
                continue;
              }
              if (target.getUniqueId().equals(uuid)) {
                continue;
              }
              if (target.getLocation().distanceSquared(loc) <= rangeSquared) {
                retMap.putIfAbsent(target.getUniqueId(), new XicePlayer(target));
              }
            }
          } catch (Exception e) {
            logger.writeLog(plugin.getName(), e.getMessage());
          } finally {
            latch.countDown();
          }
        });
      }
    }
    try {
      latch.await();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return new ArrayList<>(retMap.values());
  }

  public List<XicePlayer> getWorldPlayers() {
    List<XicePlayer> ret = new ArrayList<>();
    for (Player target : player.getWorld().getPlayers()) {
      if (target.equals(player)) {
        continue;
      }
      ret.add(new XicePlayer(target));
    }
    return ret;
  }

  public List<XicePlayer> getOnlinePlayers() {
    List<XicePlayer> ret = new ArrayList<>();
    for (Player target : Bukkit.getOnlinePlayers()) {
      if (target.equals(player)) {
        continue;
      }
      ret.add(new XicePlayer(target));
    }
    return ret;
  }
}