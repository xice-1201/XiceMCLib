package com.xice.mclib.event;

import org.bukkit.event.player.PlayerQuitEvent;

public class XicePlayerQuitEvent extends XicePlayerEvent {
  public XicePlayerQuitEvent(PlayerQuitEvent event) {
    super(event);
  }
}