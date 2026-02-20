package com.xice.mclib.api;

import com.xice.mclib.command.XiceCommand;
import com.xice.mclib.command.XiceCommandSender;
import com.xice.mclib.enums.MessageEnum;
import com.xice.mclib.exceptions.XicePluginDisabledException;
import com.xice.mclib.interfaces.XiceCommandExecutor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public class XiceMCLibCommandExecutor implements CommandExecutor, TabCompleter {
  private volatile Map<String, XiceCommandExecutor> commandExecutorList;

  public XiceMCLibCommandExecutor() {
    commandExecutorList = new ConcurrentHashMap<>();
  }

  // 执行指令时调用
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    Map<String, XiceCommandExecutor> localCommandExecutorList = commandExecutorList;
    if (localCommandExecutorList == null) {
      sender.sendMessage(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
      return true;
    }
    if (args.length < 1) {
      sender.sendMessage(MessageEnum.MSG_MISSING_ARG.getContent());
      return true;
    }
    XiceCommandExecutor executor = localCommandExecutorList.get(args[0]);
    if (executor == null) {
      sender.sendMessage(MessageEnum.MSG_UNKNOWN_MODULE.getContent());
      return true;
    }
    List<String> subArgs = new ArrayList<>(Arrays.asList(args));
    subArgs.removeFirst();
    return executor.onCommand(new XiceCommandSender(sender), new XiceCommand(command), subArgs);
  }

  // Tab 补全指令时调用
  @Override
  public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
    Map<String, XiceCommandExecutor> localCommandExecutorList = commandExecutorList;
    if (localCommandExecutorList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    // 无额外参数时，提供全部启用的功能插件列表
    if (args.length < 1) {
      return new ArrayList<>(localCommandExecutorList.keySet());
    // 根据已输入内容提供符合条件的插件名
    } else if (args.length == 1) {
      List<String> suggestions = new ArrayList<>(localCommandExecutorList.keySet());
      suggestions.removeIf(s -> !s.startsWith(args[0]));
      Collections.sort(suggestions);
      return suggestions;
    // 调用对应插件的 onTabComplete 获取对应的补全指令
    } else {
      XiceCommandExecutor executor = localCommandExecutorList.get(args[0]);
      if (executor != null) {
        List<String> subArgs = new ArrayList<>(Arrays.asList(args));
        subArgs.removeFirst();
        return executor.onTabComplete(new XiceCommandSender(sender), new XiceCommand(command), subArgs);
      } else {
        return new ArrayList<>();
      }
    }
  }

  /**
   * 向 XiceMCLib 注册指令
   * <p>
   * 使用该方法注册指令后，被注册的指令将可以被用户调用
   * 若因各种原因注册失败，返回 false
   *
   * @param moduleName      插件名称，该名称对应了用户输入的 /xice [moduleName] [子指令] 指令
   * @param commandExecutor 指令执行器，其中定义了如何分析与执行 [子指令]
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  @SuppressWarnings("unused")
  public void addExecutor(@NotNull String moduleName, @NotNull XiceCommandExecutor commandExecutor) {
    Map<String, XiceCommandExecutor> localCommandExecutorList = commandExecutorList;
    if (localCommandExecutorList == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    XiceCommandExecutor oldExecutor = localCommandExecutorList.put(moduleName, commandExecutor);
    if (oldExecutor != null) {
      oldExecutor.shutdown();
    }
  }

  /**
   * 关闭 XiceMCLibCommandExecutor
   * <p>
   * 使用该方法后，所有后续进行的指令请求会被阻止，同时也不应继续使用该模块
   *
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public void shutdown() {
    if (commandExecutorList == null) {
      return;
    }
    Map<String, XiceCommandExecutor> localCommandExecutorList = commandExecutorList;
    commandExecutorList = null;
    for (XiceCommandExecutor executor : localCommandExecutorList.values()) {
      executor.shutdown();
    }
  }
}