package com.xice.mclib.api;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class XiceMCLibLogger {
  private final Logger logger;

  public XiceMCLibLogger(Logger logger) {
    this.logger = logger;
  }

  /**
   * 通过 XiceMCLib 输出日志
   * <p>
   * 使用该方法输出日志会自动格式化日志内容为 [插件名称 时间戳] 日志内容
   *
   * @param pluginName 插件名称
   * @param message 日志内容
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public void writeLog(String pluginName, String message) {
    logger.info("[" + pluginName + " " + LocalDateTime.now() + "] " + message);
  }
}