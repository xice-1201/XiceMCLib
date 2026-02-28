package com.xice.mclib.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.ApiStatus.Internal;

public class XiceCommandSender {
  private final CommandSender commandSender;

  @Internal
  public XiceCommandSender(CommandSender commandSender) {
    this.commandSender = commandSender;
  }

  /**
   * 发送消息
   * <p>
   * 向执行指令的实体发送消息
   *
   * @param message 消息内容
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  @SuppressWarnings("unused")
  public void sendMessage(String message) {
    commandSender.sendMessage(message);
  }

  /**
   * 检查权限
   * <p>
   * 检查执行指令的实体是否有对应指令的权限
   *
   * @param s 指令权限 key，于 plugin.yml 中定义
   * @return 是否有对应指令权限
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  @SuppressWarnings("unused")
  public boolean hasPermission(String s) {
    return commandSender.hasPermission(s);
  }
}