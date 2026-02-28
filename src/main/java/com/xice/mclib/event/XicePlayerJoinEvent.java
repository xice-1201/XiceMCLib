package com.xice.mclib.event;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Nullable;

public class XicePlayerJoinEvent extends XicePlayerEvent {
  @Internal
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
  public void setJoinMessage(@Nullable String message) {
    if (message == null || message.isEmpty()) {
      ((PlayerJoinEvent) event).joinMessage(null);
    } else {
      ((PlayerJoinEvent) event).joinMessage(MiniMessage.miniMessage().deserialize(message));
    }
  }
}