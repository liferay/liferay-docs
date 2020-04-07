---
header-id: running-service-builder
---

# Service Builderの実行

[TOC levels=1-4]

このチュートリアルでは、Service Builderの実行方法を示します。 アプリケーション内のサービスビルダを使用していますが、まだ持っているしたい場合は [作成した `service.xmlに` あなたのアプリケーションのためのオブジェクト・リレーショナル・マップ定義ファイル](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder)、このチュートリアルを進める前にそうしてください。

`service.xml` ファイルからサービスを構築するには、2つの方法があります。

  - [Liferay Dev Studio](#using-liferay-dev-studio)
  - [コマンドライン](#using-the-command-line)

Liferay @ide@が最初にデモンストレーションされます。

## Liferay Dev Studioの使用

パッケージエクスプローラーから、サービスモジュールを右クリックし、 *Liferay* → *build-service*選択します。

![図1：Liferay @ide@は、サービスモジュールの* Liferay *サブメニューの* build-service *オプションを介してService Builderサービスの構築を容易にします。](../../../images/service-builder-ide-build-service.png)

Service Builderは多数のファイルを生成します。 コマンドラインからService Builderを実行することもできます。

## コマンドラインを使用する

コマンドラインを開き、アプリケーションフォルダー（ `* -api` および `* -service` モジュールを含むフォルダー）に移動します。

Gradleを使用してサービスを構築するには、次のコマンドを入力します。

``` 
blade gw buildService  
```

または

    gradlew buildService

Bladeの `gw` コマンドは、Gradle Wrapperを使用できるプロジェクトで機能します。Liferayプロジェクトテンプレートを使用して生成されたプロジェクトには、Gradle Wrapperがあります。

| **注：** Liferay WorkspaceのGradle Wrapperスクリプトはワークスペースのルートにあります|フォルダ。 アプリケーションプロジェクトフォルダーが|にある場合 `[workspace]/ modules /[application]`、たとえば、Gradle Wrapperは| `../../gradlew`入手できます。

Mavenを使用している場合は、次のコマンドを実行してサービスを構築します。

    mvn service-builder:build

**重要：** `mvn service-builder：build` コマンドは、 `com.liferay.portal.tools.service.builder` プラグインバージョン1.0.145+を使用している場合にのみ機能します。 Service Builderプラグインの以前のバージョンを使用するMavenプロジェクトは、それに応じてPOMを更新する必要があります。 詳細については、 [Mavenを使用してService Builder](/docs/7-1/tutorials/-/knowledge_base/t/using-service-builder-in-a-maven-project)を実行してください。

サービスを正常に構築すると、Service Builderはメッセージ `BUILD SUCCESSFUL`出力します。 生成された多くのファイルがプロジェクトに表示されます。 これらは、エンティティのモデル層、サービス層、および永続層を表します。 生成されるファイルの数については心配しないでください。次のチュートリアルで説明します。 Service Builderがエンティティに対して生成するコードを確認する時間です。
