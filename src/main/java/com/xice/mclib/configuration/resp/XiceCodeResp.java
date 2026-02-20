package com.xice.mclib.configuration.resp;

public class XiceCodeResp {
  // 1 开头表示指令执行成功
  private static final int SUCCESS_START = 1000;
  public static final int SUCCESS = 1001; // 指令执行正常
  public static final int SUCCESS_WITHOUT_DOING_ANYTHING = 1002; // 指令未进行任何操作即达成要求
  public static final int SUCCESS_WITH_CONFLICT = 1003; // 指令正常执行，且中途将其余重复对象关停
  private static final int SUCCESS_END = 1999;
  // 2 开头表示指令执行正常，但因部分原因导致未能执行成功
  private static final int INTERRUPTED_START = 2000;
  public static final int PLUGIN_DISABLED = 2001; // 因插件被禁用而无法继续执行指令
  private static final int INTERRUPTED_END = 2999;

  /**
   * 代码是否执行成功
   * <p>
   *
   * @param code 代码执行状态
   * @return 是否执行成功
   * @since 1.21.11-1.1-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public static boolean isSuccess(int code) {
    return (code >= SUCCESS_START) && (code <= SUCCESS_END);
  }

  /**
   * 代码是否执行正常，但异常停止
   * <p>
   *
   * @param code 代码执行状态
   * @return 是否执行正常且异常停止
   * @since 1.21.11-1.1-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public static boolean isInterrupted(int code) {
    return (code >= INTERRUPTED_START) && (code <= INTERRUPTED_END);
  }
}