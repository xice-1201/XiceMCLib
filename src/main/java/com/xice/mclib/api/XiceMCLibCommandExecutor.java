package com.xice.mclib.api;

import com.xice.mclib.command.XiceCommand;
import com.xice.mclib.command.XiceCommandSender;
import com.xice.mclib.interfaces.XiceCommandExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class XiceMCLibCommandExecutor implements CommandExecutor, TabCompleter {
  private static XiceMCLibCommandExecutor instance;

  private final Map<String, XiceCommandExecutor> commandExecutorList = new HashMap<>();

  private XiceMCLibCommandExecutor() {
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    if (args.length < 1) {
      sender.sendMessage("缺失必要参数！");
      return true;
    }
    XiceCommandExecutor executor = commandExecutorList.get(args[0]);
    if (executor == null) {
      sender.sendMessage("未知的插件名称！");
      return true;
    }
    List<String> subArgs = new ArrayList<>(List.of(args));
    subArgs.remove(0);
    return executor.onCommand(new XiceCommandSender(sender), new XiceCommand(command), subArgs);
  }

  @Override
  public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    List<String> suggestions = null;
    if (args.length == 1) {
      suggestions = new ArrayList<>(commandExecutorList.keySet());
      Collections.sort(suggestions);
      suggestions.removeIf(s -> !s.startsWith(args[0]));
    } else if (args.length > 1) {
      XiceCommandExecutor executor = commandExecutorList.get(args[0]);
      if (executor != null) {
        List<String> subArgs = new ArrayList<>(List.of(args));
        subArgs.remove(0);
        return executor.onTabComplete(new XiceCommandSender(sender), new XiceCommand(command), subArgs);
      }
    }
    return suggestions;
  }

  /**
   * 向 XiceMCLib 注册指令
   * <p>
   * 使用该方法注册指令后，被注册的指令将可以被用户调用
   *
   * @param moduleName 插件名称，该名称对应了用户输入的 /xice [moduleName] [子指令] 指令
   * @param commandExecutor 指令执行器，其中定义了如何分析与执行 [子指令]
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public void addExecutor(@NotNull String moduleName, @NotNull XiceCommandExecutor commandExecutor) {
    commandExecutorList.put(moduleName, commandExecutor);
  }

  public static XiceMCLibCommandExecutor getInstance() {
    if (instance == null) {
      instance = new XiceMCLibCommandExecutor();
    }
    return instance;
  }
}