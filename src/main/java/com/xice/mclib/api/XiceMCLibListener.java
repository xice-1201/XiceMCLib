package com.xice.mclib.api;

import com.xice.mclib.configuration.resp.XiceCodeResp;
import com.xice.mclib.enums.MessageEnum;
import com.xice.mclib.event.XicePlayerJoinEvent;
import com.xice.mclib.event.XicePlayerQuitEvent;
import com.xice.mclib.exceptions.XicePluginDisabledException;
import com.xice.mclib.interfaces.XiceAction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class XiceMCLibListener implements Listener {
  private volatile Map<String, XiceAction> userLoginActionList;
  private volatile Map<String, XiceAction> userQuitActionList;

  public XiceMCLibListener() {
    userLoginActionList = new ConcurrentHashMap<>();
    userQuitActionList = new ConcurrentHashMap<>();
  }

  /**
   * 向 XiceMCLib 监听用户登录事件
   * <p>
   * 当用户登录时，对应传入的 action.action(event) 会被调用
   *
   * @param moduleName 插件名称
   * @param action     执行代码，当用户登录时，该对象的 action(event) 方法将被调用
   * @author Xice玄冰
   * @since 1.1-alpha
   */
  @SuppressWarnings("unused")
  public int doWhenUserLogin(@NotNull String moduleName, @NotNull XiceAction action) {
    Map<String, XiceAction> localUserLoginActionList = userLoginActionList;
    if (localUserLoginActionList == null) {
      return XiceCodeResp.PLUGIN_DISABLED;
    }
    XiceAction oldAction = localUserLoginActionList.put(moduleName, action);
    if (oldAction != null) {
      return XiceCodeResp.SUCCESS_WITH_CONFLICT;
    }
    return XiceCodeResp.SUCCESS;
  }

  /**
   * 向 XiceMCLib 取消监听用户登录事件
   * <p>
   * 当用户登录时，对应传入的 action.action(event) 不再被调用
   *
   * @param moduleName 插件名称
   * @author Xice玄冰
   * @since 1.1-alpha
   */
  @SuppressWarnings("unused")
  public int undoWhenUserLogin(@NotNull String moduleName) {
    Map<String, XiceAction> localUserLoginActionList = userLoginActionList;
    if (localUserLoginActionList == null) {
      return XiceCodeResp.PLUGIN_DISABLED;
    }
    XiceAction oldAction = localUserLoginActionList.remove(moduleName);
    if (oldAction == null) {
      return XiceCodeResp.SUCCESS_WITHOUT_DOING_ANYTHING;
    }
    return XiceCodeResp.SUCCESS;
  }

  /**
   * 向 XiceMCLib 监听用户退出事件
   * <p>
   * 当用户退出时，对应传入的 action.action(event) 会被调用
   *
   * @param moduleName 插件名称
   * @param action     执行代码，当用户退出时，该对象的 action(event) 方法将被调用
   * @author Xice玄冰
   * @since 1.1-alpha
   */
  @SuppressWarnings("unused")
  public int doWhenUserQuit(@NotNull String moduleName, @NotNull XiceAction action) {
    Map<String, XiceAction> localUserQuitActionList = userQuitActionList;
    if (localUserQuitActionList == null) {
      return XiceCodeResp.PLUGIN_DISABLED;
    }
    XiceAction oldAction = localUserQuitActionList.put(moduleName, action);
    if (oldAction != null) {
      return XiceCodeResp.SUCCESS_WITH_CONFLICT;
    }
    return XiceCodeResp.SUCCESS;
  }

  /**
   * 向 XiceMCLib 取消监听用户退出事件
   * <p>
   * 当用户退出时，对应传入的 action.action(event) 不再被调用
   *
   * @param moduleName 插件名称
   * @author Xice玄冰
   * @since 1.1-alpha
   */
  @SuppressWarnings("unused")
  public int undoWhenUserQuit(@NotNull String moduleName) {
    Map<String, XiceAction> localUserQuitActionList = userQuitActionList;
    if (localUserQuitActionList == null) {
      return XiceCodeResp.PLUGIN_DISABLED;
    }
    XiceAction oldAction = localUserQuitActionList.remove(moduleName);
    if (oldAction == null) {
      return XiceCodeResp.SUCCESS_WITHOUT_DOING_ANYTHING;
    }
    return XiceCodeResp.SUCCESS;
  }

  // 当有玩家登录时调用
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Map<String, XiceAction> localUserLoginActionList = userLoginActionList;
    if (localUserLoginActionList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    for (XiceAction action : localUserLoginActionList.values()) {
      action.action(new XicePlayerJoinEvent(event));
    }
  }

  // 当有玩家退出时调用
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    Map<String, XiceAction> localUserQuitActionList = userQuitActionList;
    if (localUserQuitActionList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    for (XiceAction action : localUserQuitActionList.values()) {
      action.action(new XicePlayerQuitEvent(event));
    }
  }

  /**
   * 关闭 XiceMCLibListener
   * <p>
   * 使用该方法后，所有后续的事件均不会被捕获，且不应继续尝试监听该监听器
   *
   * @author Xice玄冰
   * @since 1.1-alpha
   */
  public void shutdown() {
    Map<String, XiceAction> localUserLoginActionList = userLoginActionList;
    Map<String, XiceAction> localUserQuitActionList = userQuitActionList;
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