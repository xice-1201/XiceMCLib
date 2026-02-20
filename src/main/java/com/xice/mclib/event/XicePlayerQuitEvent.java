package com.xice.mclib.event;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.player.PlayerQuitEvent;

public class XicePlayerQuitEvent extends XicePlayerEvent {
  public XicePlayerQuitEvent(PlayerQuitEvent event) {
    super(event);
  }

  /**
   * 设置触发退出事件玩家的系统退出消息
   * <p>
   *
   * @param message 文本内容
   * @author Xice玄冰
   * @since 1.21.11-1.1-alpha
   */
  @SuppressWarnings("unused")
  public void setQuitMessage(String message) {
    if (message == null || message.isEmpty()) {
      ((PlayerQuitEvent) event).quitMessage(null);
    } else {
      ((PlayerQuitEvent) event).quitMessage(MiniMessage.miniMessage().deserialize(message));
    }
  }
}