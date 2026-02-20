package com.xice.mclib.event;

import com.xice.mclib.entity.XicePlayer;
import org.bukkit.event.player.PlayerEvent;

public class XicePlayerEvent extends XiceEvent {
  public XicePlayerEvent(PlayerEvent event) {
    super(event);
  }

  /**
   * 获取触发对应事件的玩家
   * <p>
   *
   * @return 玩家对象
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  @SuppressWarnings("unused")
  public XicePlayer getPlayer() {
    return new XicePlayer(((PlayerEvent) event).getPlayer());
  }
}