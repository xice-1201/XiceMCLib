package com.xice.mclib.api;

import com.xice.mclib.configuration.file.XiceYamlConfiguration;
import com.xice.mclib.enums.MessageEnum;
import com.xice.mclib.exceptions.XiceFileInteractionException;
import com.xice.mclib.exceptions.XicePluginDisabledException;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class XiceMCLibYAMLLoader {
  private JavaPlugin plugin;

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
   * @since 1.21.11-1.0-release
   */
  public XiceYamlConfiguration readYAMLSettings(String yamlFileName) {
    JavaPlugin localPlugin = plugin;
    if (localPlugin == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    File configFile = new File(localPlugin.getDataFolder().getParentFile(), "xice/" + yamlFileName + ".yml");
    if (!configFile.exists()) {
      return null;
    }
    return new XiceYamlConfiguration(YamlConfiguration.loadConfiguration(configFile));
  }

  /**
   * 通过 XiceMCLib 写入 yml 配置文件
   * <p>
   * 若文件及其对应父文件夹不存在，会自动创建；若对应文件存在，会覆盖设置
   *
   * @param yamlFileName 文件名称，将写入 plugins/xice/[yamlFileName].yml 文件
   * @param settings     配置参数
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public void writeYAMLSettings(String yamlFileName, XiceYamlConfiguration settings) {
    JavaPlugin localPlugin = plugin;
    if (localPlugin == null) {
      throw new XicePluginDisabledException(MessageEnum.MSG_PLUGIN_DISABLED.getContent());
    }
    File configFile = new File(localPlugin.getDataFolder().getParentFile(), "xice");
    if (!configFile.exists() && !configFile.mkdirs()) {
      throw new XiceFileInteractionException(MessageEnum.MSG_FILE_CREATE_ERROR.getContent());
    }
    configFile = new File(configFile, new File(yamlFileName).getName() + ".yml");
    try {
      if (!configFile.exists() && !configFile.createNewFile()) {
        throw new XiceFileInteractionException(MessageEnum.MSG_FILE_CREATE_ERROR.getContent());
      }
      settings.save(configFile);
    } catch (IOException ignored) {
      throw new XiceFileInteractionException(MessageEnum.MSG_FILE_IO_ERROR.getContent());
    }
  }

  /**
   * 关闭 XiceMCLibYAMLLoader
   * <p>
   * 使用该方法后，所有后续的配置管理操作均不会执行，且不应继续尝试使用该配置管理器
   *
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public void shutdown() {
    plugin = null;
  }
}