---
header-id: installing-liferay-maven-artifacts
---

# Liferay Mavenアーティファクトのインストール

[TOC levels=1-4]

Mavenを使用してLiferayモジュールを作成するには、Liferayで必要なアーカイブ（例: JARファイルおよびWARファイル）が必要です。Liferayでは、それらを「Mavenアーティファクト」として提供しています。Mavenアーティファクトは、リモートリポジトリから取得できます。

Liferayアーティファクトを含むリポジトリには、「セントラルリポジトリ」と「Liferayリポジトリ」の二つがあります。セントラルリポジトリは、リモートリポジトリが設定されていない場合にアーティファクトをダウンロードするために使用される、デフォルトのリポジトリです。セントラルリポジトリは*通常*、最新のLiferay Mavenアーティファクトを提供しますが、Liferayリポジトリを使用すると、Liferayによってリリースされた最新のアーティファクトが*保証されます*。2つのリポジトリ間でアーティファクトのリリースがわずかに遅れる以外は、どちらも同じです。次に、両方のリポジトリを参照する方法について説明します。

セントラルリポジトリを使用してLiferay Mavenアーティファクトをインストールするにあたって必要になることは、`pom.xml`ファイルで[モジュールの依存関係を指定する](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)ことだけです。たとえば、以下のスニペットは、Liferayの`com.liferay.portal.kernel`アーティファクトへの依存関係を設定します。

    <dependencies>
    <dependency>
    <groupId>com.liferay.portal</groupId>
    <artifactId>com.liferay.portal.kernel</artifactId>
    <version>2.61.2</version>
    <scope>provided</scope>
    </dependency>
    ...
    </dependencies>

モジュールをパッケージ化する際、Mavenアーティファクトの自動インストールプロセスは、セントラルリポジトリからそのモジュールに必要なアーティファクトのみをダウンロードします。

リポジトリの[検索バー]で*liferay maven*を検索すると、セントラルリポジトリで公開されているLiferay Mavenアーティファクトが表示されます。便宜上、[http://search.maven.org/#search|ga|1|liferay maven](http://search.maven.org/#search|ga|1|liferay%20maven)のリンクをクリックしてください。
[最新のバージョン]の列をガイドにして、開発対象の@product@のバージョンで利用可能なものを確認してください。

Liferayの最新のMavenアーティファクトにアクセスする場合は、プロジェクトの親`pom.xml`で以下のスニペットを使用して、[LiferayのNexusリポジトリ](https://repository.liferay.com)を使用するようにMavenを設定します。

    <repositories>
    <repository>
    <id>liferay-public-releases</id>
    <name>Liferay Public Releases</name>
    <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases</url>
    </repository>
    </repositories>
    
    <pluginRepositories>
    <pluginRepository>
    <id>liferay-public-releases</id>
    <url>https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/</url>
    </pluginRepository>
    </pluginRepositories>

上記の設定では、Liferayのリリースリポジトリからアーティファクトを取得します。

| **注：**Liferayでは、| [スナップショットリポジトリ](https://repository.liferay.com/nexus/content/repositories/liferay-public-snapshots/)も提供しています。| これは、`<id>`、`<name>`、および`<url>`タグを| リポジトリに指すように変更することでアクセスできます。このリポジトリは、特別な場合のみに使用してください。また、
| スナップショットアーティファクトへのアクセスも有効にする必要があります。
|
|     <snapshots>
|         <enabled>true</enabled>
|     </snapshots>

Liferayリポジトリが`settings.xml`ファイルで設定されると、そのリポジトリのコンテンツに基づいてアーキタイプが作成されます。Liferay開発にMavenアーキタイプを使用する方法の詳細については、[Generating New Projects Using Archetypes](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)のチュートリアルを参照してください。

Liferay NexusリポジトリをLiferay Mavenアーティファクトにアクセスできるように設定し、すでにセントラルリポジトリから同期している場合は、ローカルリポジトリの一部をクリアして、Mavenに新しいアーティファクトを強制的に再ダウンロードさせる必要があります。また、アーティファクトをMaven Centralに公開するときにLiferayリポジトリを有効にしたままにしないでください。アーティファクトを公開する際は、Liferayリポジトリの資格情報をコメントアウトする必要があります。

Liferay Mavenリポジトリでは、Liferayによって作成された最新のMavenアーティファクトが必要な場合に、適した代替手段を提供しています。

 これで、Liferayアーティファクトをダウンロードし、選択したリポジトリにインストールできました。
