package com.xice.mclib.command;

import org.bukkit.command.Command;
import org.jetbrains.annotations.ApiStatus.Internal;

public class XiceCommand {
  private final Command command;

  @Internal
  public XiceCommand(Command command) {
    this.command = command;
  }
}