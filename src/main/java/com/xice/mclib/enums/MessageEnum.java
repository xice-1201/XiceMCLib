package com.xice.mclib.enums;

public enum MessageEnum {
  MSG_FILE_CREATE_ERROR("创建文件失败！"),
  MSG_FILE_IO_ERROR("文件读写出现问题！"),
  MSG_MISSING_ARG("缺失必要参数！"),
  MSG_PLUGIN_DISABLED("插件已禁用！"),
  MSG_UNKNOWN_MODULE("未知的插件名称！");

  private final String content;

  MessageEnum(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}