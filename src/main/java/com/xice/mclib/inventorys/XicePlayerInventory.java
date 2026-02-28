package com.xice.mclib.inventorys;

import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.ApiStatus.Internal;

public class XicePlayerInventory {
    private final PlayerInventory inventory;

    @Internal
    public XicePlayerInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    // 这里可以添加更多方法
    // 目前只提供基本的构造函数以支持编译
}