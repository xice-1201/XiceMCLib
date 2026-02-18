package com.xice.mclib.event;

import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerJoinEvent;

public class XicePlayerJoinEvent extends XicePlayerEvent {
  public XicePlayerJoinEvent(PlayerJoinEvent event) {
    super(event);
  }

  public void setJoinMessage(String message) {
    if (message == null || message.isEmpty()) {
      ((PlayerJoinEvent) event).joinMessage(null);
    } else {
      ((PlayerJoinEvent) event).joinMessage(Component.text(message));
    }
  }
}