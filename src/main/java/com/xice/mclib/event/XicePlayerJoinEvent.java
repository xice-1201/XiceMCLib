package com.xice.mclib.event;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.player.PlayerJoinEvent;

public class XicePlayerJoinEvent extends XicePlayerEvent {
  public XicePlayerJoinEvent(PlayerJoinEvent event) {
    super(event);
  }

  /**
   * 设置触发登录事件玩家的系统加入消息
   * <p>
   *
   * @param message 文本内容
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  @SuppressWarnings("unused")
  public void setJoinMessage(String message) {
    if (message == null || message.isEmpty()) {
      ((PlayerJoinEvent) event).joinMessage(null);
    } else {
      ((PlayerJoinEvent) event).joinMessage(MiniMessage.miniMessage().deserialize(message));
    }
  }
}