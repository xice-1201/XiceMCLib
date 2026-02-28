package com.xice.mclib.enums;

import java.util.List;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

/**
 * 配置枚举
 * <p>
 * 表示 XiceMCLib 中的配置键和默认值
 * 注意：这是一个基础枚举，实际使用时需要添加具体的配置项
 *
 * @author Xice玄冰
 * @since 1.0-release
 */
public enum SettingsEnum {
    // 配置键枚举
    ;

    private final String key;
    private final Object defaultValue;
    private final List<String> comments;

    /**
     * 构造函数
     *
     * @param key 配置键
     * @param defaultValue 默认值
     * @param comments 注释
     * @since 1.0-release
     */
    @Internal
    SettingsEnum(String key, Object defaultValue, List<String> comments) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.comments = comments;
    }

    /**
     * 获取配置键
     * <p>
     *
     * @return 配置键字符串
     * @since 1.0-release
     */
    @Internal
    public @NotNull String getKey() {
        return key;
    }

    /**
     * 获取默认值
     * <p>
     *
     * @return 默认值对象
     * @since 1.0-release
     */
    @Internal
    public @NotNull Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * 获取注释
     * <p>
     *
     * @return 注释列表
     * @since 1.0-release
     */
    @Internal
    public @NotNull List<String> getComments() {
        return comments;
    }

    /**
     * 获取布尔类型的默认值
     * <p>
     * 如果默认值不是布尔类型，返回 false
     *
     * @return 布尔默认值
     * @since 1.0-release
     */
    public boolean getDefaultBoolean() {
        return defaultValue instanceof Boolean ? (Boolean) defaultValue : false;
    }

    /**
     * 获取字符串类型的默认值
     * <p>
     * 如果默认值不是字符串类型，返回空字符串
     *
     * @return 字符串默认值
     * @since 1.0-release
     */
    public @NotNull String getDefaultString() {
        return defaultValue instanceof String ? (String) defaultValue : "";
    }

    /**
     * 获取整数类型的默认值
     * <p>
     * 如果默认值不是整数类型，返回 0
     *
     * @return 整数默认值
     * @since 1.0-release
     */
    public int getDefaultInteger() {
        return defaultValue instanceof Integer ? (Integer) defaultValue : 0;
    }

    /**
     * 获取双精度浮点数类型的默认值
     * <p>
     * 如果默认值不是双精度浮点数类型，返回 0.0
     *
     * @return 双精度浮点数默认值
     * @since 1.0-release
     */
    public double getDefaultDouble() {
        return defaultValue instanceof Double ? (Double) defaultValue : 0.0;
    }
}