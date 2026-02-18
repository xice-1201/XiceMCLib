package com.xice.mclib.interfaces;

import com.xice.mclib.command.XiceCommand;
import com.xice.mclib.command.XiceCommandSender;
import java.util.List;

public interface XiceCommandExecutor {
  boolean onCommand(XiceCommandSender sender, XiceCommand command, List<String> args);

  List<String> onTabComplete(XiceCommandSender sender, XiceCommand command, List<String> args);
}