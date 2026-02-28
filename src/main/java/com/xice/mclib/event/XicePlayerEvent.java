package com.xice.mclib.event;

import com.xice.mclib.entity.XicePlayer;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

public class XicePlayerEvent extends XiceEvent {
  @Internal
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
  public @NotNull XicePlayer getPlayer() {
    return new XicePlayer(((PlayerEvent) event).getPlayer());
  }
}