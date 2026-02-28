# XiceMCLib - Xice玄冰的 Minecraft 支持库

[![版本](https://img.shields.io/badge/版本-Folia_1.21.11--1.1--beta-orange)](https://github.com/xice-1201/XiceMCLib/releases)
[![许可证](https://img.shields.io/github/license/xice-1201/XiceMCLib?label=许可证&color=green)](LICENSE)
![Paper](https://img.shields.io/badge/PaperAPI-1.21.11--R0.1--SNAPSHOT-blue)
![Folia](https://img.shields.io/badge/Folia-支持-brightgreen)

XiceMCLib 是**Xice玄冰**系列插件的核心依赖库，旨在**隔离 PaperAPI 的版本变动**，为插件提供通用、稳定的工具集和事件总线。

## 特性
- **API 适配层**：对 Paper 1.21.11 及 Folia 环境下的 API 进行封装，隔离 PaperAPI 版本变动。
- **模块化架构**：采用模块化设计，包含命令执行、事件处理、日志管理、配置加载等独立模块。
- **线程安全设计**：为 Folia 多线程环境优化，使用 `volatile` 引用和 `ConcurrentHashMap`。
- **事件系统**：提供完整的事件总线，支持自定义事件发布/订阅机制。
- **配置管理**：双层配置系统，支持 YAML 配置文件和枚举键值设置。
- **工具集合**：提供 MiniMessage 解析、设置管理等实用工具。
- **兼容性保证**：升级 Paper 版本时，只需更新 XiceMCLib 而无需修改依赖插件。

## 架构概览

XiceMCLib 采用模块化架构，主要包含以下组件：

### 核心模块
- **XiceMCLib** - 主入口点，继承 `XicePlugin`
- **XicePlugin** - 基础插件类，为所有 Xice 插件提供通用功能

### API 模块
- **XiceMCLibCommandExecutor** - 命令路由和执行
- **XiceMCLibListener** - 事件处理
- **XiceMCLibLogger** - 日志工具
- **XiceMCLibYAMLLoader** - 配置加载

### 事件系统
- **XiceEvent** - 基础事件类
- **XicePlayerEvent** - 玩家相关事件基类
- **XicePlayerJoinEvent** - 玩家加入事件
- **XicePlayerQuitEvent** - 玩家退出事件
- **XicePlayerInteractEntityEvent** - 玩家与实体交互事件
- **XicePrepareItemCraftEvent** - 准备物品合成事件

### 工具与接口
- **SettingsUtil** - 设置管理工具
- **XiceMiniMessageParseUtil** - MiniMessage 解析工具
- **XiceCommandExecutor** - 命令执行器接口
- **XiceAction** - 通用动作接口

### 配置与枚举
- **XiceYamlConfiguration** - YAML 配置处理器
- **SettingsEnum** - 设置键枚举
- **MessageEnum** - 消息枚举
- **GameModeEnum** - 游戏模式枚举
- **SoundEnum** - 声音枚举

## 依赖
- **服务端**：[Paper 1.21.11](https://papermc.io) 或兼容的 Folia 1.21.11 服务端
- **Java**: 版本 21
- **构建工具**: Maven

## 安装
### 服务器安装
从 [Release 列表](https://github.com/xice-1201/XiceMCLib/releases) 下载最新版本，放入服务器的 `plugins` 目录。

### 开发者安装
作为 Maven 依赖添加到你的插件项目中：
```xml
<dependency>
  <groupId>com.xice</groupId>
  <artifactId>XiceMCLib</artifactId>
  <version>Folia 1.21.11-1.1-beta</version>
  <scope>provided</scope>
</dependency>
```


## 快速开始

### 创建基于 XiceMCLib 的插件
1. **创建新的 Maven 项目**
2. **添加 XiceMCLib 依赖**（如上文所示）
3. **继承 `XicePlugin` 类并实现插件逻辑**：
```java
public class MyPlugin extends XicePlugin {
    @Override
    public void onEnable() {
        // 注册命令执行器
        XiceMCLib.getXiceMCLibCommandExecutor().addExecutor("mycommand", new MyCommandExecutor());

        // 使用设置工具
        boolean enabled = SettingsUtil.getConfiguration().getBoolean(SettingsEnum.MY_FEATURE_ENABLED);

        // 使用日志工具
        XiceMCLib.getXiceMCLibLogger().info("我的插件已启用");
    }
}

// 命令执行器实现
public class MyCommandExecutor implements XiceCommandExecutor {
    @Override
    public boolean onCommand(@NotNull XiceCommandSender sender, @NotNull XiceCommand command,
                           @NotNull String label, @NotNull String[] args) {
        // 命令处理逻辑
        return true;
    }
}
```

## 构建与开发

### 构建
```bash
# 克隆仓库
git clone https://github.com/xice-1201/XiceMCLib.git
cd XiceMCLib

# 构建并打包
mvn clean package

# 构建并安装到本地 Maven 仓库（推荐）
mvn clean install
```

### 输出文件
构建成功后，JAR 文件位于：`target/XiceMCLib-Folia 1.21.11-1.1-beta.jar`

## 贡献指南

### 提交 Issue
欢迎提交 issue 报告问题或提出建议：
- 问题反馈：[GitHub Issues](https://github.com/xice-1201/XiceMCLib/issues)
- 报告 bug 时，请提供：
  - 服务器版本（Paper/Folia 版本）
  - XiceMCLib 版本
  - 相关插件的版本
  - 完整的错误日志和堆栈跟踪
  - 重现步骤

### 代码规范
- 使用中文注释和文档
- 公共 API 方法使用 `@SuppressWarnings("unused")`
- 内部 API 使用 `@Internal` 注解标记
- 使用 JetBrains 注解（`@NotNull`, `@Nullable`）

### 测试要求
- 当前版本需要通过 Minecraft 服务器部署进行手动测试
- 确保与 Folia 多线程环境的兼容性
- 验证跨模块依赖的兼容性（特别是 XiceSystemMessage）

## 发布与版本

### 版本格式
XiceMCLib 使用以下版本格式：`Folia 1.21.11-1.1-beta`
- `Folia 1.21.11`：支持的 Folia/Paper 版本
- `1.1`：库的主版本和次版本
- `beta`：发布状态（alpha/beta/stable）

### 版本兼容性
- API 版本锁定为 1.21（Paper 1.21.11）
- 保持向后兼容性，确保依赖插件无需修改
- 重大变更需要更新主版本号

## 许可证
本项目基于 [MIT License](LICENSE) 开源，在保留版权声明的前提下可以自由使用、修改和分发。