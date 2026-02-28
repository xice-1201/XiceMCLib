package com.xice.mclib.enums;

import org.bukkit.Sound;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

/**
 * 音效枚举
 * <p>
 * 表示 Minecraft 中的各种音效
 * 注意：这是一个基础枚举，实际使用时需要添加具体的音效枚举值
 *
 * @author Xice玄冰
 * @since 1.0-release
 */
public enum SoundEnum {
    ;

    private final Sound sound;

    /**
     * 构造函数
     *
     * @param sound Bukkit Sound 对象
     * @since 1.0-release
     */
    @Internal
    SoundEnum(Sound sound) {
        this.sound = sound;
    }

    /**
     * 获取对应的 Bukkit Sound 对象
     * <p>
     *
     * @return Bukkit Sound 对象
     * @since 1.0-release
     */
    @Internal
    public @NotNull Sound getInnerSound() {
        return sound;
    }
}