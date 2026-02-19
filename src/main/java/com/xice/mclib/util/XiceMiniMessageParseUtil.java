package com.xice.mclib.util;

import com.xice.mclib.entity.XicePlayer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class XiceMiniMessageParseUtil {
  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * 替换占位符
   * <p>
   * 该方法会将可基于 sourcePlayer 的占位符替换为对应值
   *
   * @param rawMessage 源字符串
   * @param sourcePlayer 触发事件或发起消息的玩家
   * @return 替换过的字符串
   * @since 1.21.11-1.0-release
   * @author Xice玄冰
   */
  public static String parseMessageSourcePlayer(String rawMessage, XicePlayer sourcePlayer) {
    return rawMessage
        .replace("%player%", sourcePlayer.getName())
        .replace("%currentTime%", LocalDateTime.now().format(TIME_FORMATTER));
  }
}