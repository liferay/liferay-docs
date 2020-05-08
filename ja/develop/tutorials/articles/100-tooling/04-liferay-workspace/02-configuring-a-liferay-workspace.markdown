---
header-id: configuring-a-liferay-workspace
---

# Liferay Workspaceの設定方法

[TOC levels=1-4]

Liferay Workspaceは、開発ニーズに合わせて設定できる開発環境を提供します。ここでは、ワークスペースがデフォルトで提供するファイル/フォルダについて説明し、それから、ワークスペースを設定します。

Liferay (Gradle) Workspaceの最上位ファイル/フォルダの概要は、以下のとおりです。

- `bundles`（作成済み）: @product@バンドルのデフォルトフォルダ。
- `configs`: さまざまな環境の設定ファイルを保持します。これらのファイルは、ワークスペースにあるすべての@product@サーバーおよびプロジェクトのグローバル設定ファイルとして機能します。`configs`フォルダの使用の詳細については、[Testing Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)のセクションを参照してください。
- `ext`（作成済み）: Ext OSGiモジュールとExtプラグインを保持しています。
- `gradle`: ワークスペースで使用される、Gradleラッパーを保持しています。
- `modules`: カスタムモジュールを保持しています。これは、[Liferay JS Toolkit](/docs/7-1/reference/-/knowledge_base/r/js-generator)で作成されたフロントエンドのポートレットも保持でき ます。
- `plugins-sdk`（作成済み）: 以前のリリースから移行するプラグインを保持しています。
これらのプロジェクトタイプは、可能であれば最終的に`wars`フォルダに移行させる必要があります。これは@product@ 7.0を対象としており、Plugins SDKからLiferay Workspaceに移行する方法を提供します。詳細については、[Using a Plugins SDK from Your Workspace](/docs/7-0/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#using-a-plugins-sdk-from-your-workspace)のセクションを参照してください。Plugins SDKは、@product-ver@では使用できなくなりました。
- `themes`: Liferay Theme Generatorを使用して構築されたLiferay JS Theme Toolkitを使用する、Node.jsスタイルのテーマを保持しています。
- `wars`: 従来のWARスタイルのWebアプリケーションプロジェクトとテーマプロジェクト（つまり、[`theme`](/docs/7-1/reference/-/knowledge_base/r/theme-template)プロジェクトテンプレートによって作成されたものなど）を保持します。
- `build.gradle`: 一般的なGradleビルドファイル。
- `gradle.properties`: ワークスペースのプロジェクトの場所と@product@のサーバー設定をグローバルに指定します。
- `gradle-local.properties`: ワークスペースのユーザー固有のプロパティを設定します。
これにより、複数のユーザーが単一のワークスペースを使用して、自分のマシン上のワークスペースに特定のプロパティを設定することができます。
- `gradlew`: Gradleコマンドラッパーを実行します。
- `settings.gradle`: プラグインをワークスペースに適用し、その依存関係を設定します。

Liferayの[Maven Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)を代わりに使用している場合は、フォルダ階層は同じですが、Gradleビルドファイルが`pom.xml`ファイルにスワップアウトされています。このようなワークスペースプロジェクトの設定方法の詳細については、Maven Workspaceチュートリアルを参照してください。

ワークスペースのルートフォルダに含まれるビルド/プロパティファイルは、ワークスペースのGradleプロパティを設定し、モジュールのビルドプロセスを促進します。

ワークスペースの使用を開始する前に、`gradle.properties`ファイルにお使いのワークスペースのGradleプロパティを設定する必要があります。このファイルには、多くのコメント化されたプロパティがあります。これらは、ワークスペースで設定されるデフォルトのプロパティです。
変数を変更する場合は、変数のコメントを外して、カスタム値に設定します。たとえば、モジュールを`[ROOT]/modules`以外のフォルダに保存する場合、`liferay.workspace.modules.dir`変数のコメントを外して、別の値に設定します。

グローバルなGradleプロパティを維持しつつ、それらを自分用に（たとえば、ローカルテスト用に）変更したい場合は、`gradle.properties`ファイルを独自の`gradle-local.properties`ファイルで上書きすることができます。

`gradle.properties`ファイル内のすべてのプロパティは、ファイル内にドキュメント化されています。
各プロパティの機能と利用可能な機能の詳細については、ワークスペースで作成された`gradle.properties`ファイルにアクセスしてください。また、これらのプロパティのリストについては、[Gradle Workspace Properties](#gradle-workspace-properties)のセクションを参照してください。

| **注：**Liferay Workspaceは、バックグラウンドで多くのサブプロジェクトを提供しています。| これにより、Gradleの複雑な部分は隠れています。この点についての詳細は、| [Building Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#building-projects)| のセクションを参照してください。

これで、ワークスペースのデフォルトのフォルダ構造と、Gradleプロパティを変更する方法がわかったので、次にLiferayバンドルをワークスペースに追加する方法を説明します。

## Liferayバンドルをワークスペースに追加する

Liferay Workspaceは、Liferayサーバーを作成および保持することができます。これにより、実行中のLiferayインスタンスに対してプラグインをビルド/テストできます。Liferayインスタンスを作成する前に、ワークスペースのルートフォルダにある`gradle.properties`ファイルを開きます。ワークスペースのLiferayインスタンスには、設定可能なプロパティがいくつかあります。`liferay.workspace.bundle.url`プロパティのダウンロードURLを設定することにより、作成およびインストールを希望しているLiferayバンドルのバージョンを設定できます（例: `https://releases-cdn.liferay.com/portal/7.1.0-ga1/liferay-ce-portal-tomcat-7.1.0-ga1-20180703012531655.zip`）。
また、Liferayバンドルが作成されるフォルダは`liferay.workspace.home.dir`プロパティで設定することもできます。デフォルトでは、`bundles`に設定されています。

DXPサブスクライバーの場合は、ワークスペース用のLiferay DXPバンドルをダウンロードできます。これを行うには、`liferay.workspace.bundle.url`プロパティを*api.liferay.com*でホストされているZIPに設定します。例えば、

`liferay.workspace.bundle.url=https://api.liferay.com/downloads/portal/7.1.10/liferay-dxp-tomcat-7.1.10-ga1-20180703090613030.zip`

必要なDXPバンドルの完全に修飾されたZIP名/番号を見つけるのが難しい場合があります。LiferayのAPIサイトに直接アクセスして見つけることはできないため、DXPのダウンロードを手動で開始し、ファイル名をメモして、それを`https://api.liferay.com/downloads/portal/`に付ける必要があります。

また、`liferay.workspace.bundle.token.download`プロパティを`true`に設定して、ワークスペースがLiferayのAPIサイトにアクセスできるようにする必要があります。

Gradleプロパティを確定したら、ワークスペースのルートフォルダに移動して、以下を実行します。

    blade server init

これは、ワークスペースの事前にバンドルされた[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)ツールを使用して、Gradleプロパティで指定した@product@バージョンをダウンロードし、Liferayインスタンスを`bundles`フォルダにインストールします。

ダウンロードプロセスをスキップする場合は、ワークスペースのROOTフォルダに`bundles`フォルダを手動で作成し、@product@バンドルをそのフォルダに解凍します。

ワークスペース内から、ディストリビューションが可能なLiferayバンドル（ZipまたはTar）を作成することもできます。これを行うには、ワークスペースのルートフォルダに移動し、以下のコマンドを実行します。

    ./gradlew distBundle[Zip|Tar]

ディストリビューションファイルは、ワークスペースの`/build`フォルダから入手できます。

| **注：**Liferayバンドルのさまざまな環境は、| 簡単なテストで定義できます。これについての詳細は、| [Testing Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)| のセクションで説明しています。

Liferay Workspaceは、Liferayモジュール開発のための優れた開発環境です。Liferay WorkspaceでWARスタイルのアプリケーションの開発も行いたい場合、それも可能です。

## Gradle Workspaceのプロパティ

ワークスペースの`gradle.properties`ファイルでは、以下の設定可能なプロパティが使用できます。

- `liferay.workspace.bundle.cache.dir`: ダウンロードしたバンドルのZipファイルが保存されるディレクトリを設定します。デフォルト値は、ユーザーホームディレクトリ内の`.liferay/bundles`フォルダです。
- `liferay.workspace.bundle.token.download`: `liferay.workspace.bundle.url`プロパティがDXPバンドルZipに設定されている場合に、これを`true`に設定します。このプロパティを使用すると、`~/.liferay`フォルダにあるトークンを使用して、バンドルをダウンロードするときにユーザー資格情報を検証できます。デフォルト値は、`false`です。
- `liferay.workspace.bundle.token.email.address`: DXPバンドルのダウンロード時に使用するメールアドレスを設定します。これは、認証トークンを作成するために使用されます。メールアドレスは、DXPサブスクリプションに登録されているものと一致する必要があります。このファイルにメールアドレスとパスワードを入力せずに新しいトークンを作成したい場合は、Liferayプロファイルの[アカウント設定]ページに移動し、[認証トークン]メニューでトークンを作成することにより、手動で作成できます。トークンは、`~/.liferay`フォルダ内に存在する必要があります。
- `liferay.workspace.bundle.token.force`: これを`true`に設定して、既存のトークンを`createToken`タスクによって新しく作成されたトークンでオーバーライドします。デフォルト値は、`false`です。
- `liferay.workspace.bundle.token.password`: DXPバンドルをダウンロードするときに使用するパスワードを設定します。これは、認証トークンを作成するために使用されます。パスワードは、DXPサブスクリプションに登録されているものと一致する必要があります。詳細については、`liferay.workspace.bundle.token.email.address`プロパティを参照してください。
- `liferay.workspace.bundle.token.password.file`: Liferayバンドルの認証トークンのパスワードを保持するように、ファイルを設定します。デフォルトのファイル値は、`~/.liferay/token`です。
- `liferay.workspace.bundle.url`: ダウンロードするバンドルZipを指すURLを設定します。URLがDXPバンドルを指す場合（例: https://api.liferay.com/...）、 `liferay.workspace.bundle.token.download`プロパティを`true`に設定します。デフォルト値は、Liferay Portal CEの最新バージョンのURLです。
- `liferay.workspace.default.repository.enabled`: これを`true`に設定して、Liferay CDNをルートプロジェクトのデフォルトリポジトリとして設定します。デフォルト値は、`true`です。
- `liferay.workspace.environment`: 現在の開発に適した設定で環境を設定します。`configs`フォルダは、同じワークスペースで異なる環境を保持するために使用されます。環境設定を整理し、その設定で環境インストールを作成できます。`common`、`dev`、`local`、`prod`、および `uat`の五つの環境があります。デフォルト値は、`local`です。
- `liferay.workspace.home.dir`: `liferay.workspace.bundle.url`プロパティからダウンロードしたLiferayバンドルを含むフォルダを設定します。デフォルト値は、`bundles`です。
- `liferay.workspace.modules.default.repository.enabled`: これを`true`に設定して、Liferay CDNをモジュール/OSGiプロジェクトのデフォルトリポジトリとして設定します。デフォルト値は、`true`です。
- `liferay.workspace.ext.dir`: すべてのExt OSGiモジュールとExtプラグインを含むフォルダを設定します。デフォルト値は、`ext`です。
- `liferay.workspace.modules.dir`: すべてのモジュール/OSGiプロジェクトを含むフォルダを設定します。デフォルト値は、`modules`です。
- `liferay.workspace.modules.jsp.precompile.enabled`: これを`true`に設定して、OSGiモジュールのJSPファイルをコンパイルし、ディストリビューションが可能なZip/Tarに追加します。デフォルト値は、`false`です。
- `liferay.workspace.plugins.sdk.dir`: Plugins SDK環境を含むフォルダを設定します。デフォルト値は、`plugins-sdk`です。
- `liferay.workspace.target.platform.version`: Liferay PortalまたはDXPバンドルのバージョンを開発対象に設定します。このプロパティは、OSGiの`resolve`タスクや特殊な依存関係の管理などのターゲットプラットフォーム機能を有効にします。Liferay CEの最新リリースには`7.1.1`、DXPの最新リリースには`7.1.10`を使用してください。
- `liferay.workspace.themes.dir`: Liferay JS Theme Toolkitを使用している、Node.jsスタイルのテーマプロジェクトを含んだフォルダを設定します。デフォルト値は、`themes`です。
- `liferay.workspace.themes.java.build`: Liferay Portal Tools Theme Builderを使用してテーマプロジェクトをビルドするには、これを`true`に設定します。デフォルト値は、`false`です。
- `liferay.workspace.wars.dir`: 従来のWARスタイルのプロジェクトを含んでいるフォルダを設定します。デフォルト値は、`wars`です。

 これで、Liferay Workspaceを完全に活用するための準備が整いました。
