package com.xice.mclib.api;

import com.xice.mclib.event.XicePlayerJoinEvent;
import com.xice.mclib.interfaces.XiceAction;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class XiceMCLibListener implements Listener {
  private static XiceMCLibListener instance;

  private final List<XiceAction> userLoginActionList = new ArrayList<>();

  private XiceMCLibListener() {}

  /**
   * 向 XiceMCLib 监听用户登录事件
   * <p>
   * 当用户登录时，对应传入的 action.action(event) 会被调用
   *
   * @param action 执行代码，当用户登录时，该对象的 action(event) 方法将被调用
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public void doWhenUserLogin(XiceAction action) {
    userLoginActionList.add(action);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    for (XiceAction action : userLoginActionList) {
      action.action(new XicePlayerJoinEvent(event));
    }
  }

  public static XiceMCLibListener getInstance() {
    if (instance == null) {
      instance = new XiceMCLibListener();
    }
    return instance;
  }
}