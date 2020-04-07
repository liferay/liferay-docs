---
header-id: upgrading-build-dependencies
---

# ビルドの依存関係のアップグレード

[TOC levels=1-4]

プロジェクトがワークスペースですぐに利用可能なフィックスパックになったら、次にプロジェクトビルドの依存関係がアップグレードされていることを確認する必要があります。 ワークスペースは、次の3つの変更を必要とすることのみにより、ビルドの依存関係のアップグレードプロセスを効率化します。

  - [リポジトリURLのアップデート](#updating-the-repository-url)（Gradleのみ）
  - [ワークスペースプラグインのバージョンのアップデート](#updating-the-workspace-plugin-version)
  - [プロジェクトのビルドの依存関係バージョンを削除](#removing-your-projects-build-dependency-versions)（Gradleのみ）

最近作成したワークスペースをアップグレードする場合、これらのタスクのサブセットのみが必要となる場合があります。

それではリポジトリURLのアップデートから始めます。

## リポジトリURLのアップデート

Upgrade Plannerでこの手順を開始すると、ワークスペース向けのアーティファクトのダウンロードに使用されるリポジトリURLがアップデートされます。

Gradleベースのワークスペースを使用している場合、リポジトリURLは最新のLiferay CDNリポジトリを指示するようにアップデートされます。 これは、`buildscript`ブロック内にあるワークスペースの`settings.gradle`ファイルで、次のように設定されます。

``` groovy
repositories {
    maven {
        url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
}
```

リポジトリURLが適切なCDNリポジトリに設定されると、Liferayの独自に管理されたリポジトリから、ビルドの依存関係がダウンロードされます。

Mavenベースのワークスペースの場合、Maven Centralがデフォルトのリポジトリであるため、操作は不要となります。

## Workspaceプラグインバージョンのアップデート

最良のアップグレードエクスペリエンスを得るには、最新のLiferay Workspaceバージョンを活用して、すべての最新機能を利用できるようにする必要があります。 この手順を開始して、適切なプラグインをアップグレードします。

Gradleベースのワークスペース向けにマニュアルで実行するには、記事[Updating Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/updating-liferay-workspace)をご覧ください。 Mavenベースのワークスペースの場合は、最新の[バンドルサポートプラグイン](/docs/7-1/reference/-/knowledge_base/r/bundle-support-plugin)バージョンをルート`pom.xml`ファイルで設定してください。

## プロジェクトのビルドの依存関係バージョンを削除する

| **注: **この手順は、ターゲット | プラットフォーム機能が現時点ではGradleプロジェクトのみで利用可能であるため、Gradleベースのワークスペースのみに適用されます。

ワークスペースはターゲットプラットフォーム機能を活用しているため、`build.gradle`ファイルでプラグインの依存関係バージョンを設定する必要はありません。 これは、設定したターゲットプラットフォームバージョンが、プロジェクトで使用するアーティファクトバージョンを既に定義しているためです。 したがって、プロジェクトの`build.gradle`ファイルのいずれかに依存関係バージョンが存在する場合、それらを削除する必要があります。

この手順を開始して、プロジェクトの`build.gradle`ファイルから依存関係バージョンを削除します。

`build.gradle`の`dependencies`ブロックの例として、以下のスニペットを参照してください。

``` groovy
dependencies {
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
    compileOnly group: "javax.portlet", name: "portlet-api"
    compileOnly group: "javax.servlet", name: "javax.servlet-api"
    compileOnly group: "jstl", name: "jstl"
    compileOnly group: "org.osgi", name: "osgi.cmpn"
}
```

ワークスペースでターゲットプラットフォーム機能を設定していない場合は、記事[Managing the Target Platform](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)で詳細をご覧ください。

これで、ビルドの依存関係を正常にアップグレードできました。 プロジェクトでコンパイルエラーが発生することがありますが、それは依存関係が変更された可能性があるためです。 次に、それらをアップデートする方法などについてを学びます。
