---
header-id: blade-cli
---

# Blade CLI

[TOC levels=1-4]

[Blade CLI](https://github.com/liferay/liferay-blade-cli/)ツールは、Liferayの開発者が新しいLiferayのモジュールを作成するためにあたって、最も簡単な方法です。Blade CLIにより、以下のことができます。

- IDEまたは開発環境で使用できるプロジェクト（GradleまたはMaven）の作成
- @product@インスタンスの作成/管理
- モジュールのデプロイ（GradleまたはMaven）

以下のサブコマンドは、Blade CLI環境で呼び出すことが可能です。

- *convert*：Plugins SDKのプラグインプロジェクトをGradle Workspaceプロジェクトに変換します。
- *create*：利用可能なテンプレートから新しいLiferay Module Projectを作成します。
- *deploy*：バンドルをビルドして、Liferayモジュールフレームワークにデプロイします。
- *gw*：検出された場合、Gradle Wrapperを使用してGradleコマンドを実行します。
- *help*：特定のコマンドに関するヘルプを提供します。
- *init*：新しいLiferay Workspaceを初期化します。
- *install*：Liferayのモジュールフレームワークにバンドルをインストールします。
- *open*：Liferay @ide@でファイルまたはプロジェクトを開いたり、インポートしたりします。
- *samples*：サンプルプロジェクトを作成します。
- *server*：Liferayプロジェクトで定義されたサーバーを起動または停止します。
- *sh*：@product@に接続し、後続のGogoコマンドを実行して、出力を返します。
- *update*：Blade CLIを最新バージョンにアップデートします。
- *version*：Blade CLIに関するバージョン情報を表示します。

<!-- TODO: New options `outputs` and `upgradeProps`. Should these be added to
list? - Cody -->

これらのサブコマンドの詳細については、`--help`フラグ（例: `blade samples --help`）を指定してサブコマンドを実行してください。

このチュートリアルセクションでは、これらのコマンドを使用して@product@のインスタンスとモジュールの作成、およびテスト方法を説明します。
