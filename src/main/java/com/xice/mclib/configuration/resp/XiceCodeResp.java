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
  public static final int FILE_NOT_FOUND = 2002; // 因指定文件不存在而无法继续执行指令
  public static final int FILE_IO_ERROR = 2003; // 因指定文件读写错误而无法继续执行指令
  public static final int EMPTY_FILE = 2004; // 写文件操作得到空文件
  private static final int INTERRUPTED_END = 2999;
  // 3 开头表示指令未能正常执行
  private static final int ERROR_START = 3000;
  private static final int ERROR_END = 3999;
  // 4 开头表示指令本身具有缺陷而无法完成
  private static final int CODE_WRONG_START = 4000;
  public static final int CODE_NOT_COMPLETE = 4001; // 代码未实现
  public static final int CODE_ARGS_ERROR = 4002; // 代码中存在硬编码的参数错误
  private static final int CODE_WRONG_END = 4999;

  /**
   * 代码是否执行成功
   * <p>
   *
   * @param code 代码执行状态
   * @return 是否执行成功
   * @since 1.1-alpha
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
   * @since 1.1-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public static boolean isInterrupted(int code) {
    return (code >= INTERRUPTED_START) && (code <= INTERRUPTED_END);
  }

  /**
   * 代码是否未能正常执行
   * <p>
   *
   * @param code 代码执行状态
   * @return 是否执行出错
   * @since 1.1-beta
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public static boolean isError(int code) {
    return (code >= ERROR_START) && (code <= ERROR_END);
  }

  /**
   * 代码是否本身具有问题
   * <p>
   *
   * @param code 代码执行状态
   * @return 代码是否具有问题
   * @since 1.1-beta
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public static boolean isCodeWrong(int code) {
    return (code >= CODE_WRONG_START) && (code <= CODE_WRONG_END);
  }
}