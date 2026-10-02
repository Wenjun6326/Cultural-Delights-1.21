# Cultural Delights — Minecraft 26.1 / Fabric

[![Build](https://github.com/Wenjun6326/Cultural-Delights-1.21/actions/workflows/build.yml/badge.svg?branch=26.1-fabric)](https://github.com/Wenjun6326/Cultural-Delights-1.21/actions/workflows/build.yml)

> ## ⚠️ 非官方移植版 / UNOFFICIAL PORT ⚠️
>
> 这是由社区玩家**自行移植**的版本，**不是**原作者发布的，也**没有得到**原作者的支持或认可。
> This is a **community-made, unofficial port**. It is **not** released by, affiliated with, endorsed by,
> or supported by the original authors.
>
> - 请**不要**把本移植版的 bug 报告给原作者（Baisylia / NCP Bails）。
>   Do **not** report bugs from this port to the original authors.
> - 玩法机制与贴图**与原版保持一致**，本移植只做版本适配。
>   Gameplay mechanics and textures are **identical to the original**; this port only adapts the mod to a new Minecraft version.
> - 部分语言的翻译**可能存在错误**（详见下方"已知问题"）。
>   Some translations may contain errors (see *Known issues* below).

---

## 分支说明 / Branches

| 分支 Branch | 内容 Content |
| --- | --- |
| `main` | 上游 [Baisylia/Cultural-Delights-1.21](https://github.com/Baisylia/Cultural-Delights-1.21) 的 NeoForge 1.21.1 源码，**未做任何改动**，方便继续与上游同步。<br>The unmodified upstream NeoForge 1.21.1 source, kept in sync with upstream. |
| `26.1-fabric` | **本移植版**：Minecraft 26.1.2 + Fabric。发布标签 `culturaldelight+26.1fabric`。<br>**This port**: Minecraft 26.1.2 + Fabric. Released under the tag `culturaldelight+26.1fabric`. |

---

# 中文说明

## 这是什么

[Cultural Delights（多元乐事）](https://github.com/Baisylia/Cultural-Delights-1.21) 是 Farmer's Delight（农夫乐事）的附属模组，加入了世界各地的食物体系：玉米、黄瓜、茄子、牛油果，以及围绕它们展开的切割、烹饪、发酵、装箱与村民交易内容。

本项目把 Cultural Delights `0.17.8`（Minecraft 1.21.1）移植到了 **Minecraft 26.1 + Fabric**。
移植基准是 1.21.1 的 Fabric 版本行为与资源，玩法、数值、配方、贴图全部照搬，没有做任何平衡性改动。

## 环境要求

| 项目 | 版本 |
| --- | --- |
| Minecraft | 26.1.x（`~26.1`）|
| Fabric Loader | **≥ 0.19.1**（开发与测试使用 0.19.5）|
| Fabric API | 0.155.2+26.1.2（或兼容版本）|
| Java | **25** 或更高 |
| Farmer's Delight Refabricated | **必需**，`26.1-3.6.25`（或兼容版本）|

**可选**（装了就会自动联动，不装也能正常玩）：

- **Brewin' & Chewin'** — 泡菜（Pickle）的发酵配方。该配方带有 `fabric:registry_contains` 加载条件，没装这个模组时会静默跳过，不会报错。
- **Tough as Nails** — 食物的保暖/水解渴标签。
- **Serene Seasons** — 季节作物标签。

## 安装

1. 安装 Fabric Loader 0.19.1 或更高版本。
2. 把 [Fabric API](https://modrinth.com/mod/fabric-api) 和 [Farmer's Delight Refabricated](https://modrinth.com/mod/farmers-delight-refabricated) 放进 `mods/` 文件夹。
3. 把本移植版的 jar 放进 `mods/` 文件夹。
4. 启动游戏。**这是一个同时适用于客户端和服务端的模组**，两边都要装。

## 从源码构建

```bash
./gradlew build
```

产物在 `build/libs/` 下（`culturaldelights-0.17.8+26.1.jar` 和 `-sources.jar`）。
需要 JDK 25。依赖从 Modrinth Maven 与 Fabric Maven 拉取，首次构建需要联网。

## 这次移植改了什么

只做了"让 1.21.1 的代码能在 26.1 上跑起来"所必需的适配：

- **加载器**：NeoForge 专属代码（`neoforge.mods.toml`、datagen provider、`neoforge:biome_modifier` 等）改写为 Fabric 等价实现（`fabric.mod.json`、`BiomeModifications.addFeature`）。
- **生物群系注入**：野生作物与牛油果树改用 Fabric 的 `BiomeModifications` 在初始化阶段注入，12 个生物群系挂载点全部保留。
- **村民交易**：改为 26.1 的数据驱动 JSON 格式（`data/<namespace>/villager_trade/...`），交易内容与等级不变。
- **种子物品命名**：26.1 的物品翻译键在构造时就固定了，`BlockItem` 会走方块前缀。这里改用 `useItemDescriptionPrefix()`，所以**玉米粒显示"玉米粒"、黄瓜种子显示"黄瓜种子"**，不会显示成方块名。
- **渲染层**：删除了 1.21 时代的客户端入口点与 `BlockRenderLayerMap`。26.1 会根据贴图透明度自动选择 `cutout`/`solid`，无需再手动注册。
- **战利品表**：目录结构改为 26.1 的 `loot_table/`，并修正了原本文件名与方块 ID 不匹配、导致挖掉不掉落的 `exotic_roll_medley`（热带寿司拼盘）战利品表。
- **兼容性标签**：补齐了 `c:crops/cucumbers`（复数，1.21.1 及其它模组使用的名字）与 `toughasnails:heating_consumed_items` 等惯例标签。

## 已验证的行为

移植完成后用真实开服的集成测试逐项验证过，不是"看代码猜的"：

| 项目 | 结果 |
| --- | --- |
| 自然生成 | 丛林扫描到野生茄子与牛油果树（含结果树叶）、平原扫描到野生玉米、沼泽扫描到野生黄瓜 |
| 生长 | 黄瓜/茄子正常随机刻生长到成熟；玉米会长高并在顶部结出玉米棒，上部秆继续生长到成熟 |
| 掉落 | 成熟黄瓜 → 黄瓜 + 种子；成熟茄子 → 茄子 + 种子；成熟玉米 → 玉米粒；成熟玉米上部 → 玉米棒；未成熟只掉种子；各种箱子/原木/拼盘掉落自身 |
| 配方 | 开服加载 1903 条配方，0 条解析错误；配方、战利品表、标签、村民交易的引用静态校验 0 处无法解析 |
| 服务端日志 | 启动无 ERROR |

## 已知问题

- **翻译**：上游的非英文翻译是用 AI 补全的（见上游提交 `9a19736 "Using AI to complete translations of the other languages. Someone should definitely check if it did an ok job."`），本移植原样继承，**可能存在问题**。另外 `fr_fr`、`pt_br`、`de_de`、`ko_kr`、`uk_ua`、`zh_tw` 的键本来就不完整，缺键会**自动回退到英文**（不会显示成 `item.xxx.yyy` 这种原始 ID）。欢迎提交翻译修正的 PR。
- 语言文件里存在 `item.culturaldelights.ginger` 这个键，但模组并没有注册姜这个物品——**上游遗留**，无副作用。
- 玻璃瓶/发酵等联动内容依赖对应的模组，未安装时相关配方不会加载（这是刻意设计，不是 bug）。
- 本移植版**没有**得到原作者的技术支持。遇到问题请在本仓库的 [Issues](https://github.com/Wenjun6326/Cultural-Delights-1.21/issues) 反馈，并注明是 26.1 移植版。

## 鸣谢与授权

- 原模组 **Cultural Delights** 由 **NCP Bails (Baisylia)** 制作，美术由 **NCP Bails、Raspmary、Bagels、Nive** 提供。
- 1.21.1 的 Fabric 版本由 **BlueSapphy** 维护。
- 本仓库的全部玩法代码、数值、贴图、模型与翻译均来自上述原作者，**版权归原作者所有**（`All Rights Reserved`）。本移植版仅做版本适配，不含任何原创美术资源。
- 如果你是原作者，不希望这个移植版存在，请开 issue 联系，我会立刻删除。

---

# English

## What is this?

[Cultural Delights](https://github.com/Baisylia/Cultural-Delights-1.21) is an addon for Farmer's Delight that adds food systems from around the world: corn, cucumbers, eggplants and avocados, together with the cutting, cooking, fermenting, crating and villager-trading content built around them.

This repository hosts an **unofficial port of Cultural Delights `0.17.8` (Minecraft 1.21.1) to Minecraft 26.1 + Fabric**.
It is based on the behaviour and assets of the 1.21.1 Fabric edition. Gameplay, numbers, recipes and textures are carried over unchanged — no rebalancing was done.

## Requirements

| Component | Version |
| --- | --- |
| Minecraft | 26.1.x (`~26.1`) |
| Fabric Loader | **≥ 0.19.1** (developed and tested with 0.19.5) |
| Fabric API | 0.155.2+26.1.2 (or compatible) |
| Java | **25** or newer |
| Farmer's Delight Refabricated | **required**, `26.1-3.6.25` (or compatible) |

**Optional** integrations (detected automatically, the mod works fine without them):

- **Brewin' & Chewin'** — the Pickle fermenting recipe. It is guarded by a `fabric:registry_contains` load condition, so it is skipped silently when the mod is absent.
- **Tough as Nails** — heating/hydration item tags.
- **Serene Seasons** — seasonal crop tags.

## Installation

1. Install Fabric Loader 0.19.1 or newer.
2. Put [Fabric API](https://modrinth.com/mod/fabric-api) and [Farmer's Delight Refabricated](https://modrinth.com/mod/farmers-delight-refabricated) into your `mods/` folder.
3. Put this port's jar into your `mods/` folder.
4. Launch the game. This is a **client-and-server** mod — install it on both sides.

## Building from source

```bash
./gradlew build
```

Artifacts land in `build/libs/` (`culturaldelights-0.17.8+26.1.jar` and `-sources.jar`).
JDK 25 is required. Dependencies come from the Modrinth and Fabric Maven repositories, so the first build needs network access.

## What the port changed

Only what was necessary to make the 1.21.1 code run on 26.1:

- **Loader**: NeoForge-specific pieces (`neoforge.mods.toml`, datagen providers, `neoforge:biome_modifier`, data maps) were rewritten as their Fabric equivalents (`fabric.mod.json`, `BiomeModifications.addFeature`, registry events).
- **World generation**: wild crops and avocado trees are now injected through Fabric's `BiomeModifications` during initialisation. All 12 biome attachments are preserved.
- **Villager trades**: converted to the 26.1 data-driven JSON layout (`data/<namespace>/villager_trade/...`). Trade contents and levels are unchanged.
- **Seed item names**: in 26.1 an item's translation key is fixed at construction time, and `BlockItem` derives it from the block. The port uses `useItemDescriptionPrefix()` instead, so **Corn Kernels read "Corn Kernels" and Cucumber Seeds read "Cucumber Seeds"** rather than being named after the crop block.
- **Render layers**: the 1.21-era client entrypoint and `BlockRenderLayerMap` registration were removed. 26.1 picks `cutout`/`solid` automatically from the texture's transparency, so nothing needs registering.
- **Loot tables**: moved to the 26.1 `loot_table/` layout, and the `exotic_roll_medley` loot table — whose file name did not match its block id upstream, so the block dropped nothing — was fixed.
- **Compatibility tags**: restored `c:crops/cucumbers` (plural, the name used by 1.21.1 and by other mods) and `toughasnails:heating_consumed_items`, among others.

## Verified behaviour

The port was checked with a real dedicated server, not by reading code:

| Area | Result |
| --- | --- |
| Natural generation | Jungle scans found wild eggplants and avocado trees (including fruiting leaves); plains scans found wild corn; swamp scans found wild cucumbers |
| Growth | Cucumbers and eggplants random-tick to maturity; corn grows tall, sets a cob on the upper block and that upper block keeps growing to maturity |
| Drops | Ripe cucumber → cucumber + seeds; ripe eggplant → eggplant + seeds; ripe corn → corn kernels; ripe corn top → corn cob; immature crops drop seeds only; crates, logs and feasts drop themselves |
| Recipes | 1903 recipes loaded at startup with zero parse errors; a static reference check over recipes, loot tables, tags and villager trades found zero unresolvable references |
| Server log | Starts with no ERROR lines |

## Known issues

- **Translations**: upstream completed its non-English languages with AI (see upstream commit `9a19736 "Using AI to complete translations of the other languages. Someone should definitely check if it did an ok job."`). This port inherits them as-is, so **they may contain errors**. `fr_fr`, `pt_br`, `de_de`, `ko_kr`, `uk_ua` and `zh_tw` are also incomplete upstream; missing keys **fall back to English** (you will never see a raw id such as `item.xxx.yyy`). Fixes via PR are very welcome.
- `item.culturaldelights.ginger` exists in the language files but no ginger item is registered — an **upstream leftover**, harmless.
- Optional integration recipes only load when the corresponding mod is installed. That is intentional.
- This port has **no support** from the original authors. Please report problems to this repository's [Issues](https://github.com/Wenjun6326/Cultural-Delights-1.21/issues) and mention that you are on the 26.1 port.

## Credits & license

- The original **Cultural Delights** mod is by **NCP Bails (Baisylia)**, with art by **NCP Bails, Raspmary, Bagels and Nive**.
- The 1.21.1 Fabric edition was maintained by **BlueSapphy**.
- All gameplay code, values, textures, models and translations in this repository belong to those authors and remain **All Rights Reserved**. This port only adapts them to a newer Minecraft version and contributes no original art.
- If you are an original author and would prefer this port not to exist, please open an issue and it will be taken down.
