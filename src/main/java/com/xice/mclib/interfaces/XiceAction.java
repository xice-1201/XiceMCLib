package com.xice.mclib.interfaces;

import com.xice.mclib.event.XiceEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Xice 动作接口
 * <p>
 * 用于定义事件触发时执行的动作
 *
 * @author Xice玄冰
 * @since 1.1-alpha
 */
public interface XiceAction {
  /**
   * 执行动作
   * <p>
   * 当对应事件触发时调用此方法
   *
   * @param event 事件对象
   * @since 1.1-alpha
   */
  void action(@NotNull XiceEvent event);
}