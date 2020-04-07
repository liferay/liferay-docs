---
header-id: migrating-traditional-plugins-to-workspace-web-applications
---

# 従来のプラグインをWorkspace Webアプリケーションに移行する

[TOC levels=1-4]

@product@のAPIに[従来のプラグインを適合](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)させた後、プラグインSDK 7.0でそれを維持し続けることができます。ただし、プラグインSDKは、@product-ver@では利用できなくなりました。 プラグインSDKの削除については、記事[Deprecated Apps in 7.1: What To Do](/docs/7-1/deploy/-/knowledge_base/d/deprecated-apps-in-7-1-what-to-do#foundation)で詳細をご覧ください。 [Liferay WorkspaceはプラグインSDKを置き換え](/docs/7-1/tutorials/-/knowledge_base/t/from-the-plugins-sdk-to-liferay-workspace)、包括的なGradle開発環境などを提供します。 簡単なコマンドで、従来のプラグイン（ポートレットなど）をGradleベースのWebアプリケーションプロジェクトに移行します。 そこから、Webアーカイブ（WAR）として@product@にそれらを構築およびデプロイできます。

## 移行コマンドの実行

[Blade CLIの](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)`convert`コマンドは、プラグインSDKプラグインをWorkspaceの`wars`フォルダのWebアプリケーションプロジェクトに移行します。 プラグインファイルは、標準のWebアプリケーションフォルダ構造に従うように再編成されます。

**標準のWebアプリケーションの構造：**

  - `[project root]`
      - `src`
          - `main`
              - `webapp`
                  - `WEB-INF`
                      - `classes`
                      - `lib` → Libraries
                      - descriptor files
              - `css` → CSS files
              - `js` → JavaScript files
              - `icons`
              - JSP files
          - `java` → Java source
      - build files

コマンドラインから、Liferay Workspace のルートフォルダに移動します。 次に、プラグインSDKプロジェクトの名前をBladeの`convert`コマンドに渡します。

    blade convert [PLUGIN_PROJECT_NAME]

Bladeは、プラグインSDKからプラグインを展開し、Workspaceの`wars`フォルダにある標準のWebアプリケーションプロジェクトで再編成します。

**注: **`blade convert -l`を実行すると、Workspace Webアプリケーションに移行できるプラグインSDKプロジェクトがリストされます。 `blade convert --all`を実行して、すべてのプラグインプロジェクトを移行します。

下の画像は、Workspace Webアプリケーションに移行する前後のプラグインファイルを示しています。

![図1：<code>convert</code>コマンドは、プラグインSDKプロジェクトをWorkspace Webアプリケーションプロジェクトに移行します。 Javaソースファイルを<code>src/main/java</code>へ、また他のすべてのファイル/フォルダを<code>src/main/webapp</code>へ移動させます。](../../../images/migrate-war-compare-folder-structure.png)

次の表は、従来のプラグインソースファイルをWorkspaceが使用する標準のWebアプリケーションフォルダ構造にマップしています。

**プラグインSDKフォルダからWebアプリケーションフォルダへ：**

| ファイル        | プラグインSDKフォルダ（旧）       | Webアプリケーションフォルダ（新規）       |
|:----------- |:--------------------- |:------------------------- |
| Java        | `docroot/WEB-INF/src` | `src/main/java`           |
| JSPs        | `docroot`             | `src/main/webapp`         |
| icons       | `docroot`             | `src/main/webapp`         |
| CSS         | `docroot/css`         | `src/main/webapp/css`     |
| JS          | `docroot/js`          | `src/main/webapp/js`      |
| descriptors | `docroot/WEB-INF`     | `src/main/webapp/WEB-INF` |
| libraries   | `docroot/WEB-INF/lib` | `src/main/webapp/lib`     |

プラグインの新しいロケーションから、[Workspace Gradleタスク](/docs/7-1/tutorials/-/knowledge_base/t/from-the-plugins-sdk-to-liferay-workspace#plugins-sdk-to-workspace-task-map)をそこで呼び出し、その`.war`ファイルを構築できます。

blade gw war

`.war`をデプロイするには、プラグインの`build/libs`フォルダから`[LIFERAY_HOME]/deploy`フォルダにコピーします。

これがWorkspaceのプラグインの新しいホームです。\

## 関連トピック

[Workspace開発のライフサイクル](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#building-projects)

[Workspace Gradleタスク](/docs/7-1/tutorials/-/knowledge_base/t/from-the-plugins-sdk-to-liferay-workspace#plugins-sdk-to-workspace-task-map)
