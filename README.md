# OriginCore

Core library for OriginCraft Minecraft plugins and shared plugin utilities.

## Repository Status

- Visibility: public
- Recommendation: maintain
- Publish assessment: 可以公开，适合作为 Origin 体系入口之一。
- Reviewed: 2026-07-05

## Value

OriginCraft 旧生态核心库，是多个插件的依赖中心。

## Future Value

中；仍有体系价值，但需要和 Magic/新 core 关系理顺。

## Maintenance Notes

保留；补更完整文档并标明依赖仓库。

## Project Shape

- Main language: Java
- File count: 87
- Detected manifests: README.md, pom.xml
- Top-level entries: .gitignore, libs, pom.xml, README.md, src

## Build / Run Notes

- Java/Maven project: run mvn package after checking the target server/API version.

## Previous Notes

# OriginCore
核心库
### Maven
#### 请使用Maven 3.6.3
```
<repository>
    <id>origin-craft</id>
    <url>http://address.originmc.cn:8081/repository/maven-releases/</url>
</repository>

<dependency>
    <groupId>cn.originmc.plugins</groupId>
    <artifactId>origin-core</artifactId>
    <version>2.31</version>
    <scope>provided</scope>
</dependency>
```
