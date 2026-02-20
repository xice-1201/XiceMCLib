package com.xice.mclib.configuration.file;

import com.xice.mclib.enums.MessageEnum;
import com.xice.mclib.exceptions.XiceFileInteractionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.YamlConfiguration;

public class XiceYamlConfiguration {
  private final YamlConfiguration yamlConfiguration;

  @SuppressWarnings("unused")
  public XiceYamlConfiguration() {
    yamlConfiguration = new YamlConfiguration();
  }

  public XiceYamlConfiguration(YamlConfiguration yamlConfiguration) {
    this.yamlConfiguration = yamlConfiguration;
  }

  /**
   * 将 YAML 配置保存到文件
   * <p>
   * 使用该方法后，在该 XiceYamlConfiguration 定义的配置项会被写入指定的文件中
   * 若文件不存在，会自动创建
   *
   * @param file 文件路径
   * @since 1.21.11-1.0-release
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void save(File file) {
    try {
      yamlConfiguration.save(file);
    } catch (IOException ignored) {
      throw new XiceFileInteractionException(MessageEnum.MSG_FILE_IO_ERROR.getContent());
    }
  }

  /**
   * 写入 YAML 配置
   * <p>
   * 使用该方法后，在该 XiceYamlConfiguration 定义一项配置内容
   * 若 key 存在，对应的 value 会被覆盖
   *
   * @param key 键
   * @param value 值
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void set(String key, Object value) {
    yamlConfiguration.set(key, value);
  }

  /**
   * 写入 YAML 注释
   * <p>
   * 使用该方法后，在该 XiceYamlConfiguration 定义的配置项添加注释
   * 该方法不会将配置的注释内容保存在文件中
   *
   * @param key 键
   * @param comments 注释
   * @since 1.21.11-1.0-beta
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void setComments(String key, String comments) {
    List<String> commentsList = new ArrayList<>();
    commentsList.add(comments);
    yamlConfiguration.setComments(key, commentsList);
  }


  /**
   * 写入 YAML 注释
   * <p>
   * 使用该方法后，在该 XiceYamlConfiguration 定义的配置项添加多行注释
   * 该方法不会将配置的注释内容保存在文件中
   *
   * @param key 键
   * @param comments 注释
   * @since 1.21.11-1.0-beta
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public void setComments(String key, List<String> comments) {
    yamlConfiguration.setComments(key, comments);
  }

  /**
   * 获取 YAML 配置
   * <p>
   * 使用该方法获取 XiceYamlConfiguration 中对应 key 的值
   * 默认值为 false
   *
   * @param key 键
   * @return  对应的值（boolean）
   * @since 1.21.11-1.0-beta
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public boolean getBoolean(String key) {
    return getBoolean(key, false);
  }

  /**
   * 获取 YAML 配置
   * <p>
   * 使用该方法获取 XiceYamlConfiguration 中对应 key 的值
   * 默认值为 defaultValue
   *
   * @param key 键
   * @param defaultValue 默认值
   * @return  对应的值（boolean）
   * @since 1.21.11-1.0-beta
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public boolean getBoolean(String key, boolean defaultValue) {
    return yamlConfiguration.getBoolean(key, defaultValue);
  }

  /**
   * 获取 YAML 配置
   * <p>
   * 使用该方法获取 XiceYamlConfiguration 中对应 key 的值
   * 默认值为 0.0
   *
   * @param key 键
   * @return  对应的值（double）
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public double getDouble(String key) {
    return getDouble(key, 0.0);
  }

  /**
   * 获取 YAML 配置
   * <p>
   * 使用该方法获取 XiceYamlConfiguration 中对应 key 的值
   * 默认值为 defaultValue
   *
   * @param key 键
   * @param defaultValue 默认值
   * @return  对应的值（double）
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public double getDouble(String key, double defaultValue) {
    return yamlConfiguration.getDouble(key, defaultValue);
  }

  /**
   * 获取 YAML 配置
   * <p>
   * 使用该方法获取 XiceYamlConfiguration 中对应 key 的值
   * 默认值为 null
   *
   * @param key 键
   * @return  对应的值（String）
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public String getString(String key) {
    return getString(key, null);
  }

  /**
   * 获取 YAML 配置
   * <p>
   * 使用该方法获取 XiceYamlConfiguration 中对应 key 的值
   * 默认值为 defaultValue
   *
   * @param key 键
   * @param defaultValue 默认值
   * @return  对应的值（String）
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  @SuppressWarnings("unused")
  public String getString(String key, String defaultValue) {
    return yamlConfiguration.getString(key, defaultValue);
  }
}