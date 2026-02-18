package com.xice.mclib.event;

import com.xice.mclib.entity.XicePlayer;
import org.bukkit.event.player.PlayerEvent;

public class XicePlayerEvent extends XiceEvent {
  public XicePlayerEvent(PlayerEvent event) {
    super(event);
  }

  public XicePlayer getPlayer() {
    return new XicePlayer(((PlayerEvent) event).getPlayer());
  }
}