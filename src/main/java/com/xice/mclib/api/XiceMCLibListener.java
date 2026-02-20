package com.xice.mclib.api;

import com.xice.mclib.enums.MessageEnum;
import com.xice.mclib.event.XicePlayerJoinEvent;
import com.xice.mclib.event.XicePlayerQuitEvent;
import com.xice.mclib.exceptions.XicePluginDisabledException;
import com.xice.mclib.interfaces.XiceAction;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class XiceMCLibListener implements Listener {
  private volatile List<XiceAction> userLoginActionList = new CopyOnWriteArrayList<>();
  private volatile List<XiceAction> userQuitActionList = new CopyOnWriteArrayList<>();

  /**
   * 向 XiceMCLib 监听用户登录事件
   * <p>
   * 当用户登录时，对应传入的 action.action(event) 会被调用
   *
   * @param action 执行代码，当用户登录时，该对象的 action(event) 方法将被调用
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  @SuppressWarnings("unused")
  public void doWhenUserLogin(@NotNull XiceAction action) {
    List<XiceAction> localUserLoginActionList = userLoginActionList;
    if (localUserLoginActionList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    localUserLoginActionList.add(action);
  }

  /**
   * 向 XiceMCLib 监听用户退出事件
   * <p>
   * 当用户退出时，对应传入的 action.action(event) 会被调用
   *
   * @param action 执行代码，当用户退出时，该对象的 action(event) 方法将被调用
   * @author Xice玄冰
   * @since 1.21.11-1.1-alpha
   */
  @SuppressWarnings("unused")
  public void doWhenUserQuit(@NotNull XiceAction action) {
    List<XiceAction> localUserQuitActionList = userQuitActionList;
    if (localUserQuitActionList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    localUserQuitActionList.add(action);
  }

  // 当有玩家登录时调用
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    List<XiceAction> localUserLoginActionList = userLoginActionList;
    if (localUserLoginActionList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    for (XiceAction action : localUserLoginActionList) {
      action.action(new XicePlayerJoinEvent(event));
    }
  }

  // 当有玩家退出时调用
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    List<XiceAction> localUserQuitActionList = userQuitActionList;
    if (localUserQuitActionList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    for (XiceAction action : localUserQuitActionList) {
      action.action(new XicePlayerQuitEvent(event));
    }
  }

  /**
   * 关闭 XiceMCLibListener
   * <p>
   * 使用该方法后，所有后续的事件均不会被捕获，且不应继续尝试监听该监听器
   *
   * @author Xice玄冰
   * @since 1.21.11-1.1-alpha
   */
  public void shutdown() {
    List<XiceAction> localUserLoginActionList = userLoginActionList;
    List<XiceAction> localUserQuitActionList = userQuitActionList;
    userLoginActionList = null;
    userQuitActionList = null;
    if (localUserLoginActionList != null) {
      localUserLoginActionList.clear();
    }
    if (localUserQuitActionList != null) {
      localUserQuitActionList.clear();
    }
  }
}