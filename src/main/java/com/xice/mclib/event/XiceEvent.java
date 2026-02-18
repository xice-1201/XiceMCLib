package com.xice.mclib.event;

import org.bukkit.event.Event;

public class XiceEvent {
  protected Event event;

  public XiceEvent(Event event) {
    this.event = event;
  }
}