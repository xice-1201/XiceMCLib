package com.xice.mclib.enums;

import org.jetbrains.annotations.NotNull;

/**
 * 消息枚举
 * <p>
 * 包含 XiceMCLib 中使用的各种系统消息
 *
 * @author Xice玄冰
 * @since 1.0-release
 */
public enum MessageEnum {
  /** 创建文件失败消息 */
  MSG_FILE_CREATE_ERROR("创建文件失败！"),
  /** 文件读写错误消息 */
  MSG_FILE_IO_ERROR("文件读写出现问题！"),
  /** HTTP 服务器启动错误消息 */
  MSG_HTTP_ERROR("启动 HTTP 服务器出错！"),
  /** 缺失必要参数消息 */
  MSG_MISSING_ARG("缺失必要参数！"),
  /** 插件已禁用消息 */
  MSG_PLUGIN_DISABLED("插件已禁用！"),
  /** 未知插件名称消息 */
  MSG_UNKNOWN_MODULE("未知的插件名称！");

  private final String content;

  @SuppressWarnings("unused")
  MessageEnum(String content) {
    this.content = content;
  }

  /**
   * 获取消息内容
   * <p>
   *
   * @return 消息内容字符串
   * @since 1.0-release
   */
  @SuppressWarnings("unused")
  public @NotNull String getContent() {
    return content;
  }
}