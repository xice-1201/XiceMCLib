package com.xice.mclib;

import com.xice.mclib.api.XiceMCLibCommandExecutor;
import com.xice.mclib.api.XiceMCLibListener;
import com.xice.mclib.api.XiceMCLibLogger;
import com.xice.mclib.api.XiceMCLibYAMLLoader;
import java.util.concurrent.atomic.AtomicReference;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public class XiceMCLib extends JavaPlugin {
  private static final AtomicReference<XiceMCLib> instance = new AtomicReference<>();
  private static volatile XiceMCLibCommandExecutor commandExecutor;
  private static volatile XiceMCLibListener listener;
  private static volatile XiceMCLibLogger logger;
  private static volatile XiceMCLibYAMLLoader yamlLoader;

  public XiceMCLib() {}

  // 当插件被启动时
  @Override
  public void onEnable() {
    try{
      if (!instance.compareAndSet(null, this)) {
        getLogger().severe("XiceMCLib 重复加载！");
        getServer().getPluginManager().disablePlugin(this);
        return;
      }
      commandExecutor = new XiceMCLibCommandExecutor();
      listener = new XiceMCLibListener();
      logger = new XiceMCLibLogger(getLogger());
      yamlLoader = new XiceMCLibYAMLLoader(this);
      PluginCommand xiceCommand = getCommand("xice");
      if (xiceCommand == null) {
        getLogger().severe("命令 xice 不存在！");
        getServer().getPluginManager().disablePlugin(this);
        return;
      }
      xiceCommand.setExecutor(commandExecutor);
      getServer().getPluginManager().registerEvents(listener, this);
      getLogger().info("XiceMCLib 已成功加载！");
    } catch (Exception e) {
      getLogger().severe("启动 XiceMCLib 时发现问题：" + e.getMessage());
      instance.set(null);
      getServer().getPluginManager().disablePlugin(this);
    }
  }

  // 当插件被关闭时
  @Override
  public void onDisable() {
    if (this != instance.get()) {
      return;
    }
    instance.set(null);
    PluginCommand xiceCommand = getCommand("xice");
    if (xiceCommand != null) {
      xiceCommand.setExecutor(null);
    }
    if (commandExecutor != null) {
      XiceMCLibCommandExecutor commandExecutor = XiceMCLib.commandExecutor;
      XiceMCLib.commandExecutor = null;
      commandExecutor.shutdown();
    }
    if (listener != null) {
      XiceMCLibListener listener = XiceMCLib.listener;
      XiceMCLib.listener = null;
      listener.shutdown();
    }
    if (logger != null) {
      XiceMCLibLogger logger = XiceMCLib.logger;
      XiceMCLib.logger = null;
      logger.shutdown();
    }
    if (yamlLoader != null) {
      XiceMCLibYAMLLoader yamlLoader = XiceMCLib.yamlLoader;
      XiceMCLib.yamlLoader = null;
      yamlLoader.shutdown();
    }
    getLogger().info("XiceMCLib 已完成卸载！");
  }

  /**
   * 获取指令执行器
   * <p>
   * 若插件被禁用，该方法返回 null
   *
   * @return 指令执行器
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public static @Nullable XiceMCLibCommandExecutor getXiceMCLibCommandExecutor() {
    return commandExecutor;
  }

  /**
   * 获取事件监听器
   * <p>
   * 若插件被禁用，该方法返回 null
   *
   * @return 事件监听器
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public static @Nullable XiceMCLibListener getXiceMCLibListener() {
    return listener;
  }

  /**
   * 获取日志工具
   * <p>
   * 若插件被禁用，该方法返回 null
   *
   * @return 日志工具
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public static @Nullable XiceMCLibLogger getXiceMCLibLogger() {
    return logger;
  }

  /**
   * 获取配置加载器
   * <p>
   * 若插件被禁用，该方法返回 null
   *
   * @return 配置加载器
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public static @Nullable XiceMCLibYAMLLoader getXiceMCLibYAMLLoader() {
    return yamlLoader;
  }

  /**
   * 获取 XiceMCLib 自身
   * <p>
   * 若插件被禁用，该方法返回 null
   *
   * @return XiceMCLib 自身
   * @author Xice玄冰
   * @since 1.21.11-1.0-release
   */
  public static @Nullable XiceMCLib getInstance() {
    return instance.get();
  }
}