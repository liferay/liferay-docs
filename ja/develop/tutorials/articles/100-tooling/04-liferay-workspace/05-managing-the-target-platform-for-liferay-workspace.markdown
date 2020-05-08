---
header-id: managing-the-target-platform-for-liferay-workspace
---

# Liferay Workspaceのターゲットプラットフォームの管理

[TOC levels=1-4]

Liferay Workspaceは@product@の特定のリリースをターゲットにするのに役立ち、依存関係を適切に解決することができます。これにより、アップグレードが簡単になります。ターゲットプラットフォームを指定し、Workspaceが新しいバージョンを指定するからです。すべての依存関係は、ターゲットのリリースで提供される最新のものにアップデートされます。

| **注：**場合によっては、バージョンの範囲に基づいて依存関係を設定する方法が、| 正確なバージョンを追跡するよりも優れている場合もあります。詳細については、| [Semantic Versioning](/docs/7-1/tutorials/-/knowledge_base/t/semantic-versioning)| のチュートリアルを参照してください。

[Liferay @ide@ 3.2以降](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)を使うと、特定のバージョンをターゲットにすることがさらに効率的にできます。@ide@は、以下の目的に沿って、設定済みの@product@ソースコードにインデックスを付けます。

- 高度なJava検索の提供（オープンタイプおよびリファレンス検索）（[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/searching-product-source-in-liferay-ide)）
- @product@ソースのデバッグ（[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/debugging-product-source-in-liferay-ide)）

この機能を有効にするには、ワークスペースの`gradle.properties`ファイルで、以下のプロパティを設定します。

```properties
target.platform.index.sources=true
```

| **注：**ポータルソースのインデックス作成は、Gradleワークスペースのバージョン| 2.0.3以降 (Target Platformプラグインのバージョン 2.0.0以降)では無効になっています。

@ide@のこれらのオプションは、Liferay Workspaceで開発する場合、または特定の設定がされているマルチモジュールのGradleプロジェクトに[Target Platform](/docs/7-1/reference/-/knowledge_base/r/target-platform-gradle-plugin) Gradleプラグインを適用している場合にのみ、使用することができます。Target Platform Gradleプラグインの適用の詳細については、[Targeting a Platform Outside of Workspace](#targeting-a-platform-outside-of-workspace)のセクションを参照してください。

次は、この仕組みについて説明します。

## BOMを使用した依存関係の管理

定義済みの部品表（BOM）をインポートすることで、バージョンをターゲットにできます。これは、ワークスペースの`gradle.properties`ファイルのプロパティを指定するだけでできます。この方法については、後ほど紹介します。

各@product@バージョンには、ワークスペースが参照するように指定できる、定義済みのBOMがあります。各BOMは、特定のリリースで使用されるアーティファクトとそのバージョンを定義します。BOMはすべての依存関係を管理形式でリストするため、プロジェクトに依存関係を**追加**しません。ビルドツール（例: GradleまたはMaven）に、プロジェクトで定義されたアーティファクトに必要なバージョンのみを**提供**します。つまり、依存関係のバージョンを指定する必要はありません。BOMは自動的に、BOMに基づいて適切なアーティファクトバージョンを定義します。

プロジェクトの`build.gradle`で異なるバージョンを指定することにより、BOMの定義済みのアーティファクトバージョンのオーバーライドができます。プロジェクトのビルドファイルで定義されたアーティファクトバージョンは、定義済みのBOMで指定されたものをオーバーライドします。BOMのオーバーライドは危険な場合があることに注意し、新しいバージョンがターゲットプラットフォームと互換性があることを確認してください。

BOMの詳細については 、Mavenの公式ドキュメンテーションの[Importing Dependencies](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism#Importing_Dependencies)のセクションを参照してください。

 次に、設定例について説明します。

## Target Platformの設定

開発するバージョンを設定するには、以下の2つのステップが必要です。

1. ワークスペースの`gradle.properties`ファイルを開き、ターゲットにするバージョンに`liferay.workspace.target.platform.version`プロパティを設定します。例えば、

       liferay.workspace.target.platform.version=7.1.1
   
   Liferay DXPを使用している場合は、以下のようにプロパティを設定できます。

       liferay.workspace.target.platform.version=7.1.10
   
   DXPのGA1リリースに続くバージョンは、フィックスパックバージョン（例: `7.1.10.fp1`、`7.1.10.fp2`など）に従います。

2. ターゲットプラットフォームを設定したら、Gradleビルドファイルの依存関係にバージョンが指定されていないことを確認してください。これで、設定されたターゲットプラットフォームのBOMからバージョンがインポートされます。たとえば、シンプルなMVCポートレットの`build.gradle`は以下のようになります。

       dependencies {
       compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
       compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
       compileOnly group: "javax.portlet", name: "portlet-api"
       compileOnly group: "javax.servlet", name: "javax.servlet-api"
       compileOnly group: "jstl", name: "jstl"
       compileOnly group: "org.osgi", name: "osgi.cmpn"
       }
   
| **注**：`liferay.workspace.target.platform.version`プロパティも| ビルド中にプロジェクトを検証するために使用できる、ディストリビューションJAR | を設定します。詳細については、| [Validating Modules Against the Target Platform](/docs/7-1/tutorials/-/knowledge_base/t/validating-modules-against-the-target-platform)| のチュートリアルを参照してください。

ターゲットプラットフォームの機能は、Liferay Workspaceのバージョン1.9.0以降で使用できます。古いバージョンを使用している場合は、プラットフォームターゲティングを活用するためにアップデートする必要があります。これを行うには、[Updating Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/updating-liferay-workspace)のチュートリアルを参照してください。

これで、ワークスペースでターゲットプラットフォームを設定する方法と、Gradleのビルドファイルにバージョンのない依存関係がどのように表示されるかがわかるようになりました。

## ワークスペースの外部のプラットフォームをターゲットとする

Liferay Workspaceを使用しないでプラットフォームを対象としたい場合は、カスタムされたマルチモジュールGradleビルドの`build.gradle`ルートファイルに[Target Platform](/docs/7-1/reference/-/knowledge_base/r/target-platform-gradle-plugin) Gradleプラグインを適用する必要があります。

これを行うには、`build.gradle`ファイルは以下のようになります。

    buildscript {
    dependencies {
    classpath group: "com.liferay", name: "com.liferay.gradle.plugins.target.platform", version: "1.1.6"
    }
    repositories {
    maven {
    url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
    }
    }
    
    apply plugin: "com.liferay.target.platform"
    
    dependencies {
    targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom", version: "7.1.0"
    targetPlatformBoms group: "com.liferay.portal", name: "release.portal.bom.compile.only", version: "7.1.0"
    }

Liferay DXPユーザーは、アーティファクト名とバージョンを以下のように置き換える必要があります。

- `release.portal.bom` &rarr; `release.dxp.bom`
- `release.portal.bom.compile.only` &rarr; `release.dxp.bom.compile.only`
- `7.1.0` &rarr; `7.1.10`

このGradleコードは、

- LiferayのTarget Platform Gradleプラグインを適用し、
- プロジェクトのビルドに必要なアーティファクトを提供するリポジトリを設定し、
- Target Platformプラグインの依存関係を設定します。
   - `com.liferay.ce.portal.bom`：@product@に含まれる、すべてのアーティファクトを提供します。
   - `com.liferay.ce.portal.compile.only`：@product@には含まれなくても、ビルド中に参照する必要があるアーティファクトを提供します（例: `org.osgi.core`）。

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)を使用した[高度な検索](/docs/7-1/tutorials/-/knowledge_base/t/searching-product-source-in-liferay-ide)や@product@ソースの[デバッグ](/docs/7-1/tutorials/-/knowledge_base/t/debugging-product-source-in-liferay-ide)に関心がある場合は、以下次の設定も適用する必要があります。

    targetPlatformIDE {
    includeGroups "com.liferay", "com.liferay.portal"
    }

これにより、ターゲットプラットフォームのソースコードにインデックスが付けられ、@ide@で利用できるようになります。

これで、ターゲットプラットフォームを定義できるようになりました。
