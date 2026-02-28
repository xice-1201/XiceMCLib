package com.xice.mclib.event;

import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.ApiStatus.Internal;

public class XicePlayerInteractEntityEvent extends XicePlayerEvent {
    @Internal
    public XicePlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        super(event);
    }

    // 可以添加特定于玩家与实体交互事件的方法
}