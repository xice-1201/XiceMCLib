package com.xice.mclib.interfaces;

import com.xice.mclib.command.XiceCommand;
import com.xice.mclib.command.XiceCommandSender;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Xice 命令执行器接口
 * <p>
 * 用于定义插件命令的执行逻辑
 *
 * @author Xice玄冰
 * @since 1.1-alpha
 */
public interface XiceCommandExecutor {
  /**
   * 执行命令
   * <p>
   * 当用户输入命令时调用此方法
   *
   * @param sender 命令发送者
   * @param command 命令对象
   * @param args 命令参数
   * @return 命令执行是否成功
   * @since 1.1-alpha
   */
  boolean onCommand(@NotNull XiceCommandSender sender, @NotNull XiceCommand command, @NotNull List<String> args);

  /**
   * 命令补全
   * <p>
   * 当用户按 Tab 键时调用此方法提供补全建议
   *
   * @param sender 命令发送者
   * @param command 命令对象
   * @param args 命令参数
   * @return 补全建议列表
   * @since 1.1-alpha
   */
  @NotNull List<String> onTabComplete(@NotNull XiceCommandSender sender, @NotNull XiceCommand command, @NotNull List<String> args);

  /**
   * 关闭命令执行器
   * <p>
   * 当插件卸载或需要停止命令处理时调用此方法
   *
   * @since 1.1-alpha
   */
  void shutdown();
}