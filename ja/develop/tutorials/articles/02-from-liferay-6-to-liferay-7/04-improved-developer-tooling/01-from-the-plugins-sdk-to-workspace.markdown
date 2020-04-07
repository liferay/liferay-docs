---
header-id: from-the-plugins-sdk-to-liferay-workspace
---

# プラグインSDKからLiferay Workspaceへ

[TOC levels=1-4]

LiferayプラグインSDKは、@product-ver@では利用できません。 プラグインSDKの削除については、記事[Deprecated Apps in 7.1: What To Do](/docs/7-1/deploy/-/knowledge_base/d/deprecated-apps-in-7-1-what-to-do#foundation)で詳細をご覧ください。 [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)は、Liferayの開発環境として定評のある、プラグインSDKの後継です。 GradleやMavenなどの代替ビルドシステムを使用していない場合は、これを使用する必要があります。

Workspaceの主な機能は次のとおりです。

  - [モジュールとコンポーネントのテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli#project-templates)
  - [サンプルプロジェクト](/docs/7-1/tutorials/-/knowledge_base/t/liferay-sample-projects)
  - ポータルサーバーの設定
  - [プロジェクト検証](/docs/7-1/tutorials/-/knowledge_base/t/validating-modules-against-the-target-platform)
  - 統合テスト
  - フォルダ構造の柔軟性
  - プラグインを移行して@product@バンドルをインストールし、ポータルインスタンスを起動/停止する[コマンド](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)

このシリーズの後半のチュートリアル[プラグインアップデート](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)では、[Liferay開発スタジオ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)で自動的に既存のプラグインを@product-ver@に適応させる方法を説明します。 また、オプションで[従来のプラグインをWorkspaceへ移行する方法](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)を説明するチュートリアルも用意しています。

![図1：Liferay@ide@のUpgrade Plannerは、プラグインのアップグレードプロセスにおける多くの面を自動化します。](../../../images/improved-tooling-code-upgrade-configure-proj.png)

Workspaceフォルダ構造の例を以下に示します。

![図2：Liferay Workspaceはプロジェクトを集約し、同じサーバー設定とGradleビルド環境を使用します。](../../../images/improved-tooling-workspace-structure.png)

Workspaceの構造は次のとおりです。

  - `bundles/`（生成）→ @product@バンドルのデフォルトフォルダ
  - `configs/` → ポータルサーバー設定を保持
  - `ext/` → ExtモジュールとExt WARファイルを保持
  - `gradle/` → Gradleラッパーファイルを保持
  - `modules/` → モジュールプロジェクトを保持
  - `plugins-sdk/`（生成）→ 以前のリリースからのプラグインを保持
  - `themes/` → Liferay JS Theme Toolkitを使用するLiferay Theme Generatorで作成されたテーマプロジェクトを保持
  - `wars/` → 従来のWebアプリケーションプロジェクトを保持
  - `build.gradle` → 一般的なGradleビルドファイル
  - `gradle-local.properties` → ワークスペースのユーザー固有のプロパティを設定
  - `gradle.properties` → ポータルサーバー設定とプロジェクトロケーションの指定
  - `gradlew / gradlew.bat` → Gradleコマンドラッパーの実行
  - `pom.xml`（Mavenによって生成されたWorkspaceのみ）→ 共通のMavenビルドファイル
  - `settings.gradle` → Workspaceにプラグインを適用し、その依存関係を設定

ワークスペースモジュール、テーマ、およびWARプロジェクトは、同じポータルサーバー設定を使用します。 開発者は、モジュール開発、ユーザー受け入れテスト、本番などの設定を作成できます。

`configs`以下の各サブフォルダは、その`portal-ext.properties`ファイルによって定義されたポータルサーバー設定を保持します。 Workspaceの`gradle.properties`ファイルのGradleプロパティ`liferay.workspace.environment`は、使用する設定を指定します。 詳細については[Testing Projects](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)セクションを参照してください。

他のGradleプロパティは、@product@バンドル、モジュール、テーマ、およびプラグインSDKのルートロケーションを設定します。 利用可能なすべてのWorkspaceプロパティのリストについては、[Gradle Workspace Properties](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#gradle-workspace-properties)セクションを参照してください。

### Workspaceフォルダ構造のプロパティ

| プロパティ                               | 説明                                    |
|:----------------------------------- |:------------------------------------- |
| `liferay.workspace.environment`     | 使用するポータルサーバー設定を保持する`configs`サブフォルダの名前 |
| `liferay.workspace.ext.dir`         | Extプロジェクトのルートフォルダ                     |
| `liferay.workspace.home.dir`        | @product@バンドルのルートフォルダ                 |
| `liferay.workspace.modules.dir`     | モジュールプロジェクトのルートフォルダ                   |
| `liferay.workspace.plugins.sdk.dir` | プラグインSDKのルートフォルダ                      |
| `liferay.workspace.themes.dir`      | テーマプロジェクトのルートフォルダ                     |
| `liferay.workspace.wars.dir`        | WARスタイルプロジェクトのルートフォルダ                 |

Workspaceには、プラグインSDK Antターゲットに相当するGradleタスクがあります。

### プラグインSDKからWorkspaceへのタスクマップ

| プラグインSDK Antのターゲット | Workspace Gradleのタスク          | タスクの説明                                    |
|:------------------ |:----------------------------- |:----------------------------------------- |
| `build-css`        | `buildCSS`                    | CSSファイルを構築します                             |
| `build-lang`       | `buildLang`                   | 言語ビルダーを使用して言語キーを翻訳します                     |
| `build-service`    | `buildService`                | Service Builderを実行します                     |
| `clean`            | `clean`                       | すべてのビルド出力を削除します                           |
| `compile`          | `classes`                     | クラスをコンパイルします                              |
| `deploy`           | `deploy` (または `blade deploy`) | 現在のプロジェクトを@product@のOSGiフレームワークにインストールします |
| `jar`              | `jar`                         | プロジェクトをコンパイルし、JARファイルとしてパッケージ化します         |
| `war`              | `assemble`                    | プロジェクトの出力を組み立てます                          |

その他のWorkspace Gradleのタスクは、追加機能を提供します。

| Workspace Gradleのタスク    | タスクの説明                                                |
|:----------------------- |:----------------------------------------------------- |
| `buildDB`               | 汎用SQLスクリプトからデータベースSQLスクリプトを構築します                      |
| `buildSoy`              | JavaScript関数でクロージャテンプレートをコンパイルします                     |
| `components`            | プロジェクトのコンポーネントをリストします                                 |
| `configJSModules`       | @product@のコンボローダーを介して、AMDファイルをロードするために必要な設定ファイルを生成します |
| `dependencies`          | プロジェクトの宣言された依存関係をリストします                               |
| `formatSource`          | Liferay Source Formatterを実行してプロジェクトファイルをフォーマット化します    |
| `initBundle`            | @product@バンドルをダウンロードしてインストールします                       |
| `model`                 | プロジェクトの設定モデルをリストします                                   |
| `projects`              | プロジェクトのサブプロジェクトをリストします                                |
| `properties`            | プロジェクトをリストします                                         |
| `replaceSoyTranslation` | `goog.getMsg`定義を置き換えます                                |
| `transpileJS`           | プロジェクトのJavaScriptファイルを変換します                           |

これはLiferay Workspaceで利用可能な、Gradleコマンドのサブセットです。 Gradleコマンドの完全なリストについては、ワークスペースのプロジェクトから`gradlew tasks`を実行します。


<!-- TODO: Mention Workspace Test Integration section here for info on test
commands once article is available. -Cody -->
