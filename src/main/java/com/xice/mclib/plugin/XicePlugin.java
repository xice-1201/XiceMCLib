package com.xice.mclib.plugin;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus.Internal;

public abstract class XicePlugin extends JavaPlugin {
  @Override
  public void onEnable() {}

  @Override
  public void onDisable() {}

  @SuppressWarnings("unused")
  public void disableSelf(String message) {
    getLogger().severe(message);
    getServer().getPluginManager().disablePlugin(this);
  }

  @SuppressWarnings("unused")
  public boolean isXiceMCLibEnabled() {
    return getServer().getPluginManager().getPlugin("XiceMCLib") != null;
  }

  @Internal
  public File getPluginFile() {
    return getFile();
  }
}