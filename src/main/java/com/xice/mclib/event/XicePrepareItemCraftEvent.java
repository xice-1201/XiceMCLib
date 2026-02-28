package com.xice.mclib.event;

import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.jetbrains.annotations.ApiStatus.Internal;

public class XicePrepareItemCraftEvent extends XiceEvent {
    @Internal
    public XicePrepareItemCraftEvent(PrepareItemCraftEvent event) {
        super(event);
    }

    // 可以添加特定于准备合成事件的方法
}
