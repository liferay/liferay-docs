---
header-id: leveraging-docker
---

# Dockerの活用

[TOC levels=1-4]

Dockerは、自動でソフトウェアとその依存関係をクロスプラットフォームで共有できる標準化されたユニットにパッケージ化でき、今日の開発ライフサイクルでますます人気が高まっています。詳細については、Dockerの多数ある[ドキュメンテーション](https://docs.docker.com/)を参照してください。

Liferayでは、Dockerイメージを以下のサービスで提供しています。

- [Liferay Portal](https://hub.docker.com/r/liferay/portal)
- [Liferay DXP](https://hub.docker.com/r/liferay/dxp)
- [Liferay Commerce](https://hub.docker.com/r/liferay/commerce)
- [Liferay Portal Snapshots](https://hub.docker.com/r/liferay/portal-snapshot)

LiferayのDockerイメージをこれらのリソースから引き出して、自分で管理することができます。ただし、Liferay Workspaceは、事前に設定されたGradleタスクを使用して、既存の開発ワークフローにDocker開発を統合する簡単な方法を提供します。

| **注：**現時点では、Liferay WorkspaceでのDockerの活用は、Gradle| プロジェクトでのみ利用できます。

このチュートリアルでは、ワークスペース内で以下のタスクを実行する方法を説明します。

- [提供された@product@イメージに基づいたDockerコンテナの作成](#creating-a-product-docker-container)
- [コンテナの設定方法](#configuring-the-container)
- [コンテナとの対話](#interacting-with-the-container)
- [@product@のカスタムイメージのビルド](#building-a-custom-product-image)

## @product@のDockerコンテナの作成

1. 必要なDockerイメージを選択します。これは、このプロパティをカスタマイズすることにより、ワークスペースの`gradle.properties`ファイルに設定されます。

       liferay.workspace.docker.image.liferay
   
   設定可能なプロパティ値を見つけるには、@product@の公式のDocker Hubのタグセクション（例: [Liferay Portal Dockerタグ](https://hub.docker.com/r/liferay/portal/tags)など）を参照してください。
たとえば、Liferay Portal 7.1 GA2イメージに基づいてコンテナを作成する場合は、以下のプロパティを設定します。

       liferay.workspace.docker.image.liferay=liferay/portal:7.1.1-ga2
   
2. ワークスペースのルートフォルダから次のコマンドを実行します。

       ./gradlew createDockerContainer
   
このコマンドは、`[projectName]-liferayapp`という名前の新しいコンテナを作成します。ワークスペースに新しい`build/docker`フォルダが作成されます。このフォルダは、コンテナのファイルシステムにマウントされます。つまり、ワークスペースの`build/docker`フォルダ内のファイルは、コンテナの`/etc/liferay`フォルダでも使用できます。

ワークスペース内のプロジェクトはすべてコンテナ作成時に`build/docker/deploy`に自動的にコンパイルされ、コピーされます。つまり、コンテナが開始されると、すべてのプロジェクトがコンテナにデプロイされることになります。すべての設定はコンテナにも適用されます。次に、コンテナの設定方法について詳しく説明します。

## コンテナの設定方法

コンテナを起動する前に、追加のポータル設定を追加できます。これには以下のようなものが含まれます。

- プロパティのオーバーライド（例: `portal-ext.properties`）
- Marketplaceアプリのオーバーライド
- アプリサーバーの設定
- ライセンスファイル

これを行うには、ワークスペースの`configs/docker`フォルダにファイル（と必要に応じて付随するフォルダ構造）を適用します。このフォルダは、Docker開発用のLiferayホームとして扱われます。ワークスペースの`configs/common`フォルダと@product@のコンテナのデフォルト設定をオーバーレイする、追加ファイルを追加します。

たとえば、コンテナのGogoシェルを有効にするには、`configs/docker/portal-ext.properties`をワークスペースに以下の設定で追加します。

    module.framework.properties.osgi.console=0.0.0.0:11311

これにより、[Gonetシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)を使用してtelnetセッション経由でコンテナにアクセスできます。

コンテナが開始されると、`configs/commmon`と`configs/docker`に保存されている設定が`build/docker/files`フォルダに転送されます。これにより、すべての設定がコンテナのファイルシステムに適用されます。ワークスペースの`configs`フォルダの詳細については、[こちらのセクション](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)を参照してください。

| **注：**ワークスペースの| ルートフォルダから`deployDocker` Gradleタスクを呼び出して、| `build/docker/files`フォルダへのDockerの設定の転送を手動で開始できます。コンテナの作成、| または開始時に自動的に実行されます。

次に、コンテナと対話するためのコマンドを説明します。

## コンテナとの対話

`startDockerContainer`: コンテナを開始します。

`logsDockerContainer`: ポータルのランタイムのログを出力します。実行中のコンテナ（例: [Ctrl|Command] +C）を維持しながら、ログ追跡モードを終了できます。

`dockerDeploy`: プロジェクトのアーカイブファイルをワークスペースの`build/docker/deploy`フォルダにコピーすることにより、プロジェクトをコンテナの`deploy`フォルダにデプロイします。
このコマンドは、ワークスペースのルートフォルダから実行して、すべてのプロジェクトをデプロイし、Docker設定（例: `configs/common`および`configs/docker`フォルダから）を全部コンテナにコピーすることもできます。

`stopDockerContainer`: コンテナを停止します。

`removeDockerContainer`: コンテナをDockerのシステムから削除します。

| **注：**コンテナの起動中に、次のエラーが発生する場合があります。|
|     /etc/liferay/entrypoint.sh: line 3:    11 Killed
|     ${LIFERAY_HOME}/tomcat/bin/catalina.sh run
|
| これは通常、コンテナを正常に実行するために| Dockerエンジンに十分なメモリを割り当てていないことを意味します。Dockerで使用できる| リソースを増やす方法については、| Dockerの[ドキュメンテーション](https://docs.docker.com)を参照してください。

次に、カスタムイメージを作成する方法を説明します。

## カスタムの@product@イメージのビルド

コンテナの設定をイメージとしてビルドすることにより、保存することができます。カスタムの@product@イメージを作成するには、以下を実行します。

    ./gradlew buildDockerImage

`Dockerfile`がイメージをビルドするときにコンテナ用に作成されます。これを手動で行うには、以下を実行します。

    ./gradlew createDockerfile

ワークスペースの`build/docker`フォルダ内に`Dockerfile`が作成されます。`Dockerfile`の設定方法の詳細については、Dockerの[Dockerfile reference documentation](https://docs.docker.com/engine/reference/builder/)を参照してください。

これで、カスタムイメージが利用可能になります。`docker image ls`を実行して、可用性を確認します。

これで、Liferay WorkspaceでLiferayのDockerイメージを管理できるようになりました。
