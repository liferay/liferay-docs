---
header-id: configuring-dependencies
---

# 依存関係の設定方法

[TOC levels=1-4]

プロジェクトで外部のアーティファクトを使用するには、それらの依存関係を設定する必要があります。これを行うには、アーティファクトの属性を検索し、ビルドシステム（[Gradle](https://gradle.org/)、[Maven](https://maven.apache.org/)、または[Ant/Ivy](http://ant.apache.org/ivy/)のいずれか）の依存関係エントリにプラグインします。ビルドシステムは、プロジェクトを正常にコンパイルするために必要な依存関係のアーティファクトをダウンロードします。

アーティファクトを依存関係として指定する前に、まずその属性を見つける必要があります。アーティファクトには、以下の属性があります。

- *グループID*：オーサリング組織
- *アーティファクトID*：名前/識別子
- *バージョン*：リリース番号

| **注：**[アプリケーションマネージャ](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps)は、| 各モジュールのバージョン番号を示します。

このチュートリアルでは、プロジェクトに適切な依存関係があることを確認する方法を説明します。

- [コアの@product@アーティファクトの検索](#finding-core-artifacts)
- [@product@アプリと独立したアーティファクトの検索](#finding-liferay-app-and-independent-artifacts)
- [依存関係を設定する](#configuring-dependencies)

## コアのアーティファクトの検索

それぞれのLiferayアーティファクトはJARファイルであり、`META-INF/MANIFEST.MF`ファイルにアーティファクトのOSGiメタデータが含まれています。また、マニフェストはアーティファクトの属性も指定します。たとえば、以下の2つのOSGiヘッダーは、アーティファクトIDとバージョンを指定しています。

    Bundle-SymbolicName:  [artifact ID]
    Bundle-Version: [version]

| **重要：**@product@のフィックスパック内にあるアーティファクトは、@product@の| インストールアーティファクトをオーバーライドします。フィックスパックのZIPファイルにある`binaries`フォルダのサブフォルダは、| アーティファクトを保持しています。インストール済みのフィックスパックが依存するアーティファクトを提供する場合、| 依存関係で対象となるフィックスパックのアーティファクトバージョンを指定します。

以下の表では、それぞれのコア@product@アーティファクトのグループID、アーティファクトID、バージョン、およびオリジンがリストされています。

*コア@product@アーティファクト*：

| ファイル | グループID | アーティファクトID | バージョン | オリジン |
:------------ | :--------------- | :-------- | :--------- | :------ |
| `portal-kernel.jar` | `com.liferay.portal` | `com.liferay.portal.kernel` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、@product@インストール、または@product@依存関係ZIP |
| `portal-impl.jar` | `com.liferay.portal` | `com.liferay.portal.impl` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `portal-test.jar` | `com.liferay.portal` | `com.liferay.portal.test` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `portal-test-integration.jar` | `com.liferay.portal` | `com.liferay.portal.test.integration` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `util-bridges.jar` | `com.liferay.portal` | `com.liferay.util.bridges` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `util-java.jar` | `com.liferay.portal` | `com.liferay.util.java` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `util-slf4j.jar` | `com.liferay.portal` | `com.liferay.util.slf4j` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `util-taglibs.jar` | `com.liferay.portal` | `com.liferay.util.taglib` | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、または@product@の`.war` |
| `com.liferay.*` JARファイル | `com.liferay` | （JARの`MANIFEST.MF`を参照） | （JARの`MANIFEST.MF`を参照） | フィックスパックZIP、@product@インストール、@product@依存関係ZIP、またはOSGi ZIP |

次は、@product@アプリと独立したモジュールのアーティファクトを検索する方法について説明します。

## Liferayアプリと独立したアーティファクトの検索

独立したモジュールおよび@product@のアプリを構成するモジュールは、@product@コアの一部ではありません。ただし、依存関係を宣言する場合は、アーティファクト属性を見つける必要があります。以下のリソースは、@product@のアプリと独立したモジュールのアーティファクトの詳細を提供します。

| リソース | アーティファクトタイプ |
:-------- | :-------------- |
| [アプリケーションマネージャ](#app-manager) | デプロイされたモジュール |
| [参照ドキュメント](#reference-docs) | @product@モジュール（リリースごとに） |
| [Maven Central](#maven-central) | 全アーティファクトタイプ：@product@とサードパーティ、モジュールと非モジュール |

| **重要**：`com.liferay`は、Liferayのすべてのアプリおよび| 独立したモジュールのグループIDです。

アプリケーションマネージャは、デプロイされたモジュールに関する情報の最適なソースです。以下で、その詳細を説明します。

### アプリケーションマネージャ

[アプリケーションマネージャ](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps#using-the-app-manager)は、Liferayインスタンスに何がデプロイされているかを把握しています。したがって、アプリケーションマネージャを使用して、探しているモジュールを見つけることができます。

デプロイされたモジュールの情報を取得するには、以下の手順に従います。

1. @product@で、*[コントロールパネル]* → *[ アプリ]* → *[アプリケーションマネージャ]*に移動します。

2. 表示名、シンボル名、または関連キーワードを使って、モジュールを検索します。アプリでモジュールを参照することもできます。参照でも検索でも、アプリケーションマネージャはモジュールのアーティファクトIDとバージョン番号を表示します。

![図1：デプロイされたモジュールのアーティファクトIDとバージョン番号を検査できます。](../../images/intro-configuring-dependencies-search-app-manager-for-module.png)

![図2：アプリケーションマネージャは、Liferayと独立したモジュールを集約します。](../../images/intro-configuring-dependencies-indep-modules-in-app-manager.png)

デプロイされたモジュールのグループがわからない場合は、[Felix Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)を使用して見つけてください。

1. [コントロールパネル] → *[設定]* → *[Gogo Shell]*で、Gogoシェルのポートレットに移動します。コマンドは、提供されたFelix Gogoシェルのコマンドプロンプトで入力できます。

2. 表示名（例: `Liferay Bookmarks API`）またはキーワードで、モジュールを検索します。検索結果が出たら、モジュールの番号をメモします。この番号を次のステップで使用します。たとえば、以下の結果は、Liferay Bookmarks APIモジュールの番号が`52`であることを示しています。

       g!lb | grep "Liferay Bookmarks API"
       
       52|Active     |   10|Liferay Bookmarks API (2.0.1)
   
3. モジュールのマニフェストヘッダーを一覧表示するには、`headers`コマンドにモジュール番号を渡します。検索結果の`Bundle-Vendor`値をメモしておいてください。後の手順で、アーティファクトグループと照合するためです。

       g!headers 52
       
       Liferay Bookmarks API (52)
       --------------------------
       Manifest-Version = 1.0
       Bnd-LastModified = 1464725366614
       Bundle-ManifestVersion = 2
       Bundle-Name = Liferay Bookmarks API
       Bundle-SymbolicName = com.liferay.bookmarks.api
       Bundle-Vendor = Liferay, Inc.
       Bundle-Version = 2.0.1
       ...
   
4. Gogoシェルのセッションから切断します。

       g!disconnect
   
5. [Maven Central](https://search.maven.org/)または[MVNRepository](https://mvnrepository.com)上で、モジュールのアーティファクトIDを使って、モジュールを検索します。

6. 手順3の`Bundle-Vendor`値を一覧表示にあるアーティファクトを提供するグループと照合して、グループIDを決定します。

次は、@product@の参照ドキュメンテーションを使用して、@product@のアプリモジュールの属性を見つける方法を説明します。

### 参照ドキュメント

@product@のアプリであるJavadocでは、各アプリケーションモジュールのアーティファクトID、バージョン番号、表示名が一覧表示されます。ここは、まだ@product@インスタンスにデプロイされていない@product@アプリモジュールを検索するのに最適な場所です。

| **注：**コアの@product@アーティファクトのアーティファクト情報を見つけるには、| 前のセクションの| [Finding Core artifacts](#finding-core-artifacts)を参照してください。

Javadocで@product@のアプリモジュールの属性を見つけるには、以下の手順に従います。

1. アプリケーションモジュールクラスのJavadocに移動します。クラスのJavadocへのリンクがない場合は、[@app-ref@](@app-ref@)を参照して見つけてください。

2. クラスのパッケージ名をコピーします。

3. *[概要]*ページに移動します。

4. *[概要]*ページで、手順2でコピーしたパッケージ名を検索します。

パッケージ名の上にある見出しには、モジュールのアーティファクトID、バージョン番号、表示名が表示されます。すべてのアプリケーションモジュールのグループIDは`com.liferay`です。

![図3：@product@アプリケーションのJavadocの[概要]では、各アプリケーションモジュールの表示名がリストされており、その後ろにコロンで区切られた文字列のグループID、アーティファクトID、およびバージョン番号が続いています。これは、Gradleのアーティファクトのシンタックスです。](../../images/intro-configuring-dependencies-module-info-in-javadoc-overview.png)

| **注**: 現在、モジュールのバージョン番号はどのタグライブラリの| 参照ドキュメントにも含まれていません。

次は、MVNRepositoryおよびMaven Centralでアーティファクトを検索する方法について説明します。

### Maven Central

ほとんどのアーティファクトは、タイプやオリジンに関係なく、 [MVNRepository](https://mvnrepository.com/)および[Maven Central](https://search.maven.org/)にあります。
これらのサイトは、クラスパッケージに基づいてアーティファクトを見つけるのに役立ちます。一般的に、アーティファクトのパッケージ名の先頭にはアーティファクトのIDが含まれています。たとえば、`org.osgi.service.component.annotations.Component`クラスに依存している場合、Mavenサイトの1つで`org.osgi.service.component.annotations`というパッケージ名を検索します。

| **注：**必ず上記の指示に従って、必要なLiferayアーティファクトの| バージョンを決定してください。

これで、アーティファクトの属性値が取得できたので、アーティファクトの依存関係を設定する準備が整いました。

## 依存関係の設定方法

システムを構築するための依存関係の指定は複雑ではありません。グループID、アーティファクトID、およびバージョン番号を含む依存関係エントリを指定して、プロジェクトのビルドファイルを編集します。

| **注**: モジュールでサードパーティのライブラリを設定するには、| [Adding Third Party Libraries to a Module](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)のチュートリアルを参照してください。

以下に示すように、ビルドシステムごとに異なるアーティファクト属性名が使用されることに注意してください。

*アーティファクトの用語*

| フレームワーク | グループID | アーティファクトID | バージョン |
:------------ | :----------- | :----------- | :-------- |
| Gradle | `group` | `name` | `version` |
| Maven | `groupId` | `artifactId` | `version` |
| Ivy | `org` | `name` | `rev` |

以下の例は、Gradle、Maven、およびIvyに対するLiferayのJournal APIモジュールの依存関係の設定を示しています。

### Gradle

`build.gradle`ファイルで設定された依存関係は、以下のとおりです。

    dependencies {
    compileOnly group: "com.liferay", name: "com.liferay.journal.api", version: "1.0.1"
    ...
    }

### Maven

`pom.xml`ファイルで設定された依存関係は、以下のとおりです。

    <dependency>
    <groupId>com.liferay</groupId>
    <artifactId>com.liferay.journal.api</artifactId>
    <version>1.0.1</version>
    </dependency>

### Ivy

`ivy.xml`ファイルで設定された依存関係は、以下のとおりです。

    <dependency name="com.liferay.journal.api" org="com.liferay" rev="1.0.1" />

| **重要：**| [@product@はサードパーティのパッケージを多数エクスポートします](/docs/7-1/reference/-/knowledge_base/r/third-party-packages-portal-exports)。
| モジュールをデプロイして、@product@またはLiferay| インスタンスのOSGiランタイムフレームワーク内にある別のモジュールが必要なパッケージを提供しているかどうかを確認してください。既に提供| されている場合は、対応する依存関係を"provided"と指定します。
| 提供された依存関係を指定する方法は次のとおりです。| | Maven：`<scope>provided</scope>`| 
| Gradle：`providedCompile`| | 提供されたパッケージのJARを再デプロイしたり、JARをプロジェクトに組み込んだりしないでください。
| 同じパッケージを異なるJARからエクスポートすると、「分割パッケージ」の問題が発生します。| これによる影響は、ケースごとに異なります。パッケージが| サードパーティライブラリ（OSGiモジュールではない）にある場合は、| [Resolving Third
Party Library Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)を参照してください。
|
| [@product@または別のモジュールがエクスポートする](/docs/7-1/reference/-/knowledge_base/r/third-party-packages-portal-exports)| サードパーティのパッケージの| 異なるバージョンを必要とするWARを開発している場合は、| [`Import-Package:` list](/docs/7-1/tutorials/-/knowledge_base/t/importing-packages)で| そのパッケージを指定します。|
| パッケージプロバイダーがOSGiモジュールである場合は、そのモジュールをデプロイして、| エクスポートされたパッケージを公開します。そうでない場合は、| [adding a third-party library (not an OSGi module)](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)の手順に従ってください。

 アーティファクトを見つけて依存関係として設定する方法は、以上です。

## 関連トピック

[Using the App Manager](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps)

[Reference](/docs/7-1/reference/-/knowledge_base/r/development-reference)

[Resolving Third Party Library Package Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)

[Tooling](/docs/7-1/tutorials/-/knowledge_base/t/tooling)

[Portlets](/docs/7-1/tutorials/-/knowledge_base/t/portlets)
