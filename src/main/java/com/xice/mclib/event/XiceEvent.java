package com.xice.mclib.event;

import org.bukkit.event.Event;
import org.jetbrains.annotations.ApiStatus.Internal;

public class XiceEvent {
  protected Event event;

  @Internal
  public XiceEvent(Event event) {
    this.event = event;
  }
}