package com.xice.mclib.api;

import com.xice.mclib.enums.MessageEnum;
import com.xice.mclib.exceptions.XicePluginDisabledException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class XiceMCLibLogger {
  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private volatile Logger logger;

  public XiceMCLibLogger(@NotNull Logger logger) {
    this.logger = logger;
  }

  /**
   * 通过 XiceMCLib 输出信息
   * <p>
   * 使用该方法输出日志会自动格式化日志内容为 &lt;INFO&gt; [插件名称 时间戳] 日志内容
   *
   * @param pluginName 插件名称
   * @param message 日志内容
   * @since 1.21.11-1.0-release
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void writeInfo(@NotNull String pluginName, @NotNull String message) {
    Logger localLogger = logger;
    if (localLogger == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    localLogger.info("<INFO> [" + pluginName + " " + LocalDateTime.now().format(TIME_FORMATTER) + "] " + message);
  }

  /**
   * 通过 XiceMCLib 输出警告
   * <p>
   * 使用该方法输出日志会自动格式化日志内容为 &lt;WARNING&gt; [插件名称 时间戳] 日志内容
   *
   * @param pluginName 插件名称
   * @param message 日志内容
   * @since 1.21.11-1.1-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void writeWarning(@NotNull String pluginName, @NotNull String message) {
    Logger localLogger = logger;
    if (localLogger == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    localLogger.warning("<WARNING> [" + pluginName + " " + LocalDateTime.now().format(TIME_FORMATTER) + "] " + message);
  }

  /**
   * 通过 XiceMCLib 输出错误
   * <p>
   * 使用该方法输出日志会自动格式化日志内容为 &lt;ERROR&gt; [插件名称 时间戳] 日志内容
   *
   * @param pluginName 插件名称
   * @param message 日志内容
   * @since 1.21.11-1.1-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void writeError(@NotNull String pluginName, @NotNull String message) {
    Logger localLogger = logger;
    if (localLogger == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    localLogger.severe("<ERROR> [" + pluginName + " " + LocalDateTime.now().format(TIME_FORMATTER) + "] " + message);
  }

  /**
   * 关闭 XiceMCLibLogger
   * <p>
   * 使用该方法后，所有后续的日志操作均不会被调用，且不应继续尝试继续使用该日志工具
   *
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public void shutdown() {
    logger = null;
  }
}