package com.xice.mclib.api;

import com.xice.mclib.configuration.file.XiceYamlConfiguration;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class XiceMCLibYAMLLoader {
  private final JavaPlugin plugin;

  public XiceMCLibYAMLLoader(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  /**
   * 通过 XiceMCLib 读取 yml 配置文件
   * <p>
   * 若对应文件不存在，返回 null
   *
   * @param yamlFileName 文件名称，将读取 plugins/xice/[yamlFileName].yml 文件
   * @return 配置参数
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  public XiceYamlConfiguration readYAMLSettings(String yamlFileName) {
    File configFile = new File(plugin.getDataFolder().getParentFile(), "xice/" + yamlFileName + ".yml");
    if (!configFile.exists()) {
      return null;
    }
    return new XiceYamlConfiguration(YamlConfiguration.loadConfiguration(configFile));
  }

  /**
   * 通过 XiceMCLib 写入 yml 配置文件
   * <p>
   * 若文件及其对应父文件夹不存在，会自动创建
   *
   * @param yamlFileName 文件名称，将写入 plugins/xice/[yamlFileName].yml 文件
   * @param settings     配置参数
   * @author Xice玄冰
   * @since 1.21.11-1.0-alpha
   */
  public void writeYAMLSettings(String yamlFileName, XiceYamlConfiguration settings) {
    File configFile = new File(plugin.getDataFolder().getParentFile(), "xice");
    if (!configFile.exists() && !configFile.mkdirs()) {
      return;
    }
    configFile = new File(configFile, yamlFileName + ".yml");
    try {
      if (!configFile.exists() && !configFile.createNewFile()) {
        return;
      }
      settings.save(configFile);
    } catch (IOException ignored) {
    }
  }
}