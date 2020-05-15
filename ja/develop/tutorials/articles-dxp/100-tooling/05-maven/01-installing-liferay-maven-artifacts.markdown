---
header-id: installing-liferay-maven-artifacts
---

# Liferay Mavenアーティファクトのインストール

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Mavenを使用してLiferayモジュールを作成するには、Liferayで必要なアーカイブ（JARファイルやWARファイルなど）が必要です。 これは問題ではありません。LiferayはそれらをMavenアーティファクトとして提供します。 これらはリモートリポジトリから取得できます。

Liferayアーティファクトを含むリポジトリは、中央リポジトリとLiferayリポジトリの2つです。 セントラルリポジトリは、リモートリポジトリが設定されていない場合にアーティファクトをダウンロードするために使用されるデフォルトのリポジトリです。 中央リポジトリ *通常* 申し出最新のLiferay Mavenの成果物が、Liferayのリポジトリ使用して *保証* のLiferayがリリースした最新の成果物を。 2つのリポジトリ間のアーティファクトリリース間のわずかな遅延を除いて、それらは同じです。 次に、両方を参照する方法を学習します。

セントラルリポジトリを使用してLiferay Mavenアーティファクトをインストールするには、モジュールの依存関係をその `pom.xml` ファイルで指定するだけで済みます。 たとえば、次のスニペットは、Liferayの `com.liferay.portal.kernel` アーティファクトへの依存関係を設定します。

    <dependencies>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>com.liferay.portal.kernel</artifactId>
            <version>2.61.2</version>
            <scope>provided</scope>
        </dependency>
        ...
    </dependencies>

モジュールをパッケージ化するとき、自動Mavenアーティファクトインストールプロセスは、そのモジュールに必要なアーティファクトのみを中央リポジトリからダウンロードします。

リポジトリの検索バーで *ライフレイメイベン* 検索すると、中央リポジトリで公開されたライフレイメイベンアーティファクトを表示できます。 便宜上、使用可能なアーティファクトは [http://search.maven.org/\#search|ga|1|liferay maven](http://search.maven.org/#search|ga|1|liferay%20maven)で参照できます。 [最新バージョン]列をガイドとして使用して、開発中の@product@の目的のバージョンで利用できるものを確認してください。

Liferayの最新のMavenアーティファクトにアクセスする場合は、プロジェクトの親 `pom.xml`次のスニペットを挿入することにより、 [LiferayのNexusリポジトリ](https://repository.liferay.com) を使用するようにMavenを設定できます。

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

上記の設定は、Liferayのリリースリポジトリからアーティファクトを取得します。

| **注：** Liferayは、| [スナップショットリポジトリ](https://repository.liferay.com/nexus/content/repositories/liferay-public-snapshots/) |あなたが変更することでアクセスできる `<id>`、 `<name>`、および `<url>` にタグを|そのリポジトリをポイントします。 このリポジトリは、特別な場合にのみ使用してください。 あなたがします|また、スナップショットアーティファクトへのアクセスを有効にする必要があります。 | <snapshots> | <enabled>本当</enabled> | </snapshots>

Liferayリポジトリが `settings.xml` ファイルで設定されている場合、アーキタイプはそのリポジトリのコンテンツに基づいて生成されます。 Liferay開発にMavenアーキタイプを使用する方法の詳細については、 [アーキタイプ](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes) を使用した新しいプロジェクトの生成チュートリアルを参照してください。

Liferay MavenアーティファクトにアクセスするようにLiferay Nexusリポジトリを設定していて、すでに中央リポジトリから同期している場合は、ローカルリポジトリの一部を消去して、Mavenに新しいアーティファクトを再ダウンロードさせる必要がある場合があります。 また、アーティファクトをMaven Centralに公開するときに、Liferayリポジトリを構成したままにしないでください。 アーティファクトを公開するときは、Liferayリポジトリの資格情報をコメント化する必要があります。

Liferay Mavenリポジトリは、Liferayによって生成された最新のMavenアーティファクトを必要とするユーザーに適した代替手段を提供します。

おめでとう\！ Liferayアーティファクトをダウンロードして、選択したリポジトリにインストールしました。
