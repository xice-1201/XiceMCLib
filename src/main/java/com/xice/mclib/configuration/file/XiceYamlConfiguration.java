package com.xice.mclib.configuration.file;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public class XiceYamlConfiguration {
  private final YamlConfiguration yamlConfiguration;

  public XiceYamlConfiguration() {
    yamlConfiguration = new YamlConfiguration();
  }

  public XiceYamlConfiguration(YamlConfiguration yamlConfiguration) {
    this.yamlConfiguration = yamlConfiguration;
  }

  public void save(File file) {
    try {
      yamlConfiguration.save(file);
    } catch (IOException ignored) {}
  }

  public void set(String key, Object value) {
    yamlConfiguration.set(key, value);
  }

  public double getDouble(String key) {
    return getDouble(key, 0.0);
  }

  public double getDouble(String key, double defaultValue) {
    return yamlConfiguration.getDouble(key, defaultValue);
  }

  public String getString(String key) {
    return getString(key, null);
  }

  public String getString(String key, String defaultValue) {
    return yamlConfiguration.getString(key, defaultValue);
  }
}