package com.xice.mclib.entity;

import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class XicePlayer {
  private final Player player;

  public XicePlayer(Player player) {
    this.player = player;
  }

  public String getName() {
    return player.getName();
  }

  public void sendMessage(String message) {
    player.sendMessage(MiniMessage.miniMessage().deserialize(message));
  }

  public List<XicePlayer> getNearbyPlayers(double range) {
    List<XicePlayer> ret = new ArrayList<>();
    if (range > 0.0) {
      World world = player.getWorld();
      Location center = player.getLocation();
      double rangeSquared = range * range;
      for (Player target : world.getPlayers()) {
        if (target.equals(player)) {
          continue;
        }
        if (target.getLocation().distanceSquared(center) <= rangeSquared) {
          ret.add(new XicePlayer(target));
        }
      }
    }
    return ret;
  }
}