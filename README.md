# XiceMCLib - Xice玄冰的 Minecraft 支持库

[![版本](https://img.shields.io/badge/版本-Folia_1.21.11--1.0--release-orange)](https://github.com/xice-1201/XiceMCLib/releases)
[![许可证](https://img.shields.io/github/license/xice-1201/XiceMCLib?label=许可证&color=green)](LICENSE)
![Paper](https://img.shields.io/badge/PaperAPI-1.21.11--R0.1--SNAPSHOT-blue)
![Folia](https://img.shields.io/badge/Folia-支持-brightgreen)

XiceMCLib 是**Xice玄冰**系列插件的核心依赖库，旨在**隔离 PaperAPI 的版本变动**，为插件提供通用、稳定的工具集和事件总线。

## 特性
- **API 适配层**：对 Paper 1.21.11 及 Folia 环境下的 API 进行封装，便于 Paper 方法更新时统一管理。
- **通用工具集**：提供通用的日志管理、消息格式化工具。
- **事件总线**：统一管理事件的发布/订阅，方便插件内部通信。
- **兼容性保证**：升级至新的 Paper 版本时，只需更新 XiceMCLib 而无需变动其它依赖该库的插件。

## 依赖
- **服务端**：[Paper 1.21.11](https://papermc.io) 或兼容的 Folia 1.21.11 服务端

## 安装
- **服务器安装**：[Release 列表](https://github.com/xice-1201/XiceMCLib/releases)
- **开发者安装**：暂无


## 构建与贡献
### 构建
```bash
# 从源码构建
git clone https://github.com/xice-1201/XiceMCLib.git
cd XiceMCLib
mvn clean package
```

### 贡献
欢迎提交 issue！
- 问题反馈：[GitHub Issues](https://github.com/xice-1201/XiceMCLib/issues)
- 报告 bug 时，提供服务器版本、插件版本与完整日志。

## 许可证
本项目基于 [MIT License](LICENSE) 开源，在保留版权声明的前提下可以自由使用、修改和分发。