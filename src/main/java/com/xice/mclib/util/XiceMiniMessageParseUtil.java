package com.xice.mclib.util;

import com.xice.mclib.entity.XicePlayer;

public class XiceMiniMessageParseUtil {
  public static String parseSourceMessage(String rawMessage, XicePlayer sourcePlayer) {
    return rawMessage
        .replace("%player%", sourcePlayer.getName());
  }
}