package com.xice.mclib.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class XicePlugin extends JavaPlugin {
  @Override
  public void onEnable() {}

  @Override
  public void onDisable() {}

  public void disableSelf(String message) {
    getLogger().severe(message);
    getServer().getPluginManager().disablePlugin(this);
  }

  public boolean isXiceMCLibEnabled() {
    return getServer().getPluginManager().getPlugin("XiceMCLib") != null;
  }
}