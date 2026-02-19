package com.xice.mclib;

import com.xice.mclib.api.XiceMCLibCommandExecutor;
import com.xice.mclib.api.XiceMCLibListener;
import com.xice.mclib.api.XiceMCLibLogger;
import com.xice.mclib.api.XiceMCLibYAMLLoader;
import java.net.http.WebSocket.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class XiceMCLib extends JavaPlugin implements Listener {
  private static XiceMCLib instance;
  private static XiceMCLibCommandExecutor commandExecutor;
  private static XiceMCLibListener listener;
  private static XiceMCLibLogger logger;
  private static XiceMCLibYAMLLoader yamlLoader;

  private XiceMCLib() {}

  // 当插件被启动时
  @Override
  public void onEnable() {
    instance = this;
    commandExecutor = XiceMCLibCommandExecutor.getInstance();
    getCommand("xice").setExecutor(commandExecutor);
    listener = XiceMCLibListener.getInstance();
    getServer().getPluginManager().registerEvents(listener, this);
    logger = new XiceMCLibLogger(getLogger());
    yamlLoader = new XiceMCLibYAMLLoader(this);
    getLogger().info("XiceMCLib 已成功加载！");
  }

  // 当插件被关闭时
  @Override
  public void onDisable() {
    commandExecutor = null;
    listener = null;
    logger = null;
    yamlLoader = null;
    getLogger().info("XiceMCLib 已完成卸载！");
  }

  /**
   * 获取指令执行器
   * <p>
   *
   * @return 指令执行器
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public static XiceMCLibCommandExecutor getXiceMCLibCommandExecutor() {
    return commandExecutor;
  }

  /**
   * 获取事件监听器
   * <p>
   *
   * @return 事件监听器
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public static XiceMCLibListener getXiceMCLibListener() {
    return listener;
  }

  /**
   * 获取日志工具
   * <p>
   *
   * @return 日志工具
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public static XiceMCLibLogger getXiceMCLibLogger() {
    return logger;
  }

  /**
   * 获取配置加载器
   * <p>
   *
   * @return 配置加载器
   * @since 1.21.11-1.0-alpha
   * @author Xice玄冰
   */
  public static XiceMCLibYAMLLoader getXiceMCLibYAMLLoader() {
    return yamlLoader;
  }

  public static XiceMCLib getInstance() {
    if (instance == null) {
      instance = new XiceMCLib();
    }
    return instance;
  }
}