package com.xice.mclib.enums;

import org.bukkit.GameMode;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 游戏模式枚举
 * <p>
 * 表示 Minecraft 中的各种游戏模式
 *
 * @author Xice玄冰
 * @since 1.1-beta
 */
public enum GameModeEnum {
    /** 生存模式 */
    SURVIVAL(GameMode.SURVIVAL),
    /** 创造模式 */
    CREATIVE(GameMode.CREATIVE),
    /** 冒险模式 */
    ADVENTURE(GameMode.ADVENTURE),
    /** 旁观模式 */
    SPECTATOR(GameMode.SPECTATOR);

    private final GameMode gameMode;

    @Internal
    GameModeEnum(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * 获取对应的 Bukkit GameMode 对象
     * <p>
     *
     * @return Bukkit GameMode 对象
     * @since 1.1-beta
     */
    public @NotNull GameMode getGameMode() {
        return gameMode;
    }

    /**
     * 根据 Bukkit GameMode 获取对应的 GameModeEnum
     * <p>
     * 如果传入的 gameMode 为 null 或不匹配任何枚举值，返回 null
     *
     * @param gameMode Bukkit GameMode 对象
     * @return 对应的 GameModeEnum，如果不匹配则返回 null
     * @since 1.1-beta
     */
    public static @Nullable GameModeEnum getByGameMode(@Nullable GameMode gameMode) {
        if (gameMode == null) {
            return null;
        }
        for (GameModeEnum gameModeEnum : values()) {
            if (gameModeEnum.gameMode == gameMode) {
                return gameModeEnum;
            }
        }
        return null;
    }
}