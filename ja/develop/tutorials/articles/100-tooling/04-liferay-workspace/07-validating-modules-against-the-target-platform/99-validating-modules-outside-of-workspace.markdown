---
header-id: validating-modules-outside-of-workspace
---

# ワークスペースの外にあるモジュールの検証

[TOC levels=1-4]

Liferay Workspaceを使用せずにターゲットプラットフォームに対してモジュールを検証したい場合は、マルチモジュールのGradleビルドのルート`build.gradle`ファイルに[Target Platform](/docs/7-1/reference/-/knowledge_base/r/target-platform-gradle-plugin) Gradleプラグインを適用する必要があります。これを行うには、[Targeting a Platform Outside of Workspace](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace#targeting-a-platform-outside-of-workspace)のセクションに従ってください。

Target PlatformプラグインとそのBOM依存関係が設定できたら、`targetPlatformDistro`依存関係を設定する必要があります。プロジェクトのルート`build.gradle`ファイルを開き、これを依存関係のリストに追加します。すると、以下のようになります。

    dependencies {
    targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom", version: "7.1.0"
    targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom.compile.only", version: "7.1.0"
    targetPlatformDistro group: "com.liferay.portal", name "release.portal.distro", version: "7.1.0"
    }

Liferay DXPユーザーは、アーティファクト名とバージョンを以下のように置き換える必要があります。

- `release.portal.bom` &rarr; `release.dxp.bom`
- `release.portal.bom.compile.only` &rarr; `release.dxp.bom.compile.only`
- `release.portal.distro` &rarr; `release.dxp.distro`
- `7.1.0` &rarr; `7.1.10`

これで、ターゲットプラットフォームに対してモジュールの検証ができます。
