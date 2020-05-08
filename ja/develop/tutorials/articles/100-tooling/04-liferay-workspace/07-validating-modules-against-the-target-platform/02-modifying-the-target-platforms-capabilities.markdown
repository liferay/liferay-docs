---
header-id: modifying-the-target-platforms-capabilities
---

# ターゲットプラットフォームの機能の変更

[TOC levels=1-4]

完璧な世界では、`resolve`タスクが収集してチェックするものは全部、開発プロセス中に機能するはずですが、実際には例外が生じて、`resolve`タスクのデフォルト機能の変更を強いられる場合もあります。ワークスペースの`resolve`タスクに慣れていない場合は、[Resolving Your Modules](/docs/7-1/tutorials/-/knowledge_base/t/resolving-your-modules)で詳細を参照してください。

リゾルバのチェックに合格するためにプロジェクトの変更が必要となる場合が開発中に発生してしまう可能性があるシナリオは、二通りあります。

- 一つは、対象の@product@インスタンスまたは現在のワークスペースで利用できないサードパーティのライブラリに依存している場合です。
- もう一つは、@product@のカスタマイズされたディストリビューションに依存している場合です。

ここでは、これらのユースケースについて、説明していきます。

## @product@に含まれていないサードパーティのライブラリへの依存

デフォルトでは、`resolve`タスクは@product@の機能とワークスペースのモジュールの機能を全部収集します。ただし、モジュールがどちらのスペースにも含まれていないサードパーティのプロジェクト（例: [Google Guava](https://opensource.google.com/projects/guava)）に依存している場合は違います。プロジェクトがこのプロジェクトタイプに依存している場合、`resolve`タスクはデフォルトでは失敗します。このプロジェクトを実行時にデプロイして利用できるように計画していても、リゾルバにはそれが分かりません。したがって、これを回避するには、リゾルバをカスタマイズする必要があります。

これを行うには、以下の3つの方法があります。

- [モジュールにサードパーティのライブラリを埋め込む](#embed-the-third-party-library-in-your-module)
- [リゾルバ機能の現在の静的セットにサードパーティのライブラリの機能を追加する](#add-the-third-party-librarys-capabilities-to-the-current-static-set-of-resolver-capabilities)
- [モジュールの解決プロセスをスキップする](#skip-the-resolving-process-for-your-module)

サードパーティの依存関係エラーの解決方法については、[Resolving Third Party Library Package Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)のチュートリアルを参照してください。

### モジュールにサードパーティのライブラリを埋め込む

サードパーティのプロジェクトに依存するモジュールが1つしかない場合は、モジュールにJARを埋め込むことでリゾルバの失敗を回避できます。OSGiコンテナ内の複数のプロジェクトがそのモジュールに依存している場合、この方法はベストプラクティスではありません。詳細については、[Embedding Libraries in a Module](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module#embedding-libraries-in-a-module)のセクションを参照してください。

### サードパーティのライブラリの機能を現在の静的なリゾルバ機能セットに追加する

サードパーティの依存関係を提供されたモジュールとしてリストすることにより、ターゲットプラットフォームのデフォルトの機能リストに追加することができます。これを行うには、ワークスペースのルート`build.gradle`ファイルに、以下のGradleコードを追加します。

    dependencies {
    providedModules group: "GROUP_ID", name: "NAME", version: "VERSION"
    }

たとえば、提供モジュールとして[Google Guava](https://opensource.google.com/projects/guava)を追加する場合は以下のようになります。

    dependencies {
    providedModules group: "com.google.guava", name: "guava", version: "23.0"
    }

上記はリゾルバにサードパーティの依存関係を提供し、初期化（例: `blade server init`）をするとサードパーティの依存関係をダウンロードし、依存関係が@product@バンドルの`osgi/modules`フォルダに取り込まれます。

### モジュールの解決プロセスをスキップする

解決プロセス中に、特定のモジュールの検証をスキップするのが最も簡単な方法である場合があります。これを行うには、ワークスペースのルートにある`build.gradle`ファイルを開き、ファイルの下部に以下のGradleコードを挿入します。

    targetPlatform {
    resolveOnlyIf { project ->
    project.name != 'PROJECT_NAME'
    }
    }

`PROJECT_NAME`フィラーは、使用しているモジュールの名前（例: `test-api`）に置き換えてください。

Target Platformプラグインを完全に無効にする場合は、`build.gradle`ファイルにわずかに異なるディレクティブを追加します。

    targetPlatform {
    onlyIf { project ->
    project.name != 'PROJECT_NAME'
    }
    }

これにより、`resolve`タスクの実行がスキップされ、BOMの依存関係の管理が無効になります。

これで、`resolve`タスクがモジュールプロジェクトをスキップするようになります。

## @product@のカスタマイズされたディストリビューションへの依存

プロジェクトの依存しているJARのリストを手動で指定するだけでは不十分な場合があります。アプリを実行するにあたって、カスタマイズされた@product@インスタンスが必要な場合は、アップデートされたリストを使用して、ターゲットプラットフォームの機能のデフォルトリストを再作成する必要があります。カスタマイズされた@product@インスタンスの2つの例を以下で説明します。

**例1：外部機能の活用**

外部機能/フレームワークには、デフォルトではダウンロード可能なバンドルに含まれていないものの、利用可能なものが多数あります。機能/フレームワークをデプロイすることで、モジュールプロジェクトで利用できるようになります。ただし、アプリを検証する場合は、`resolve`タスクはデフォルトで含まれていない外部機能にアクセスすることができません。たとえば、このタイプの外部フレームワークにはAudience Targetingがあります。Audience Targetingフレームワークに依存するLiferay Audience Targetingルールを作成している場合、モジュールに多数のJARを提供することは簡単にはできません。この場合、コードが依存するプラットフォームをインストールし、@product@インスタンスが提供する機能のアップデートされたリストを再作成する必要があります。

**例2：カスタマイズされたコア機能の活用**

@product@のコア機能を拡張して、対象ユーザーにカスタマイズされたエクスペリエンスを提供することができます。デプロイしたら、これらのカスタマイズが存在するものと想定して、その上に他のものをビルドすることができます。ただし、カスタマイズに起因する新しい機能は、ターゲットプラットフォームのデフォルトの機能リストでは使用できません。したがって、アプリケーションがデフォルト以外の機能に依存している場合、`resolve`タスク中に失敗します。これを回避するには、カスタマイズした@product@インスタンスが提供する機能の新しいリストを再作成する必要があります。

現在のワークスペースのインスタンスに基づいてターゲットプラットフォームの機能（distro JAR）を再作成するには、以下の手順に従います。

<!-- The below process will likely be automated in Blade at some point. -Cody -->

1. ワークスペースに保存されている@product@インスタンスを起動します。依存予定のプラットフォームがインストールされていることを確認してください。

2. [BND Remote Agent JAR ファイル](https://search.maven.org/#search%7Cga%7C1%7Cbiz.aqute.remote.agent)をダウンロードして、`osgi/modules`フォルダにコピーします。

3. ワークスペースのルートフォルダから、以下のコマンドを実行します。

       bnd remote distro -o custom_distro.jar release.portal.distro 7.1.0
   
   Liferay DXPのユーザーは`release.portal.distro`のアーティファクト名を`release.dxp.distro`に置き換え、バージョン`7.1.10`のシンタックスを使う必要があります。

   これにより、@product@で実行されている新しくデプロイされたBNDエージェントに接続し、`custom_distro.jar`という名前の新しいdistro JARを作成します。その他の機能はすべて@product@インスタンスに基づいて機能を継承するため、ワークスペースバンドルが本番環境でリリースする予定のバージョンと一致していることを確認してください。

4. ワークスペースのルート`build.gradle`ファイルに移動し、以下の依存関係を追加します。

       dependencies {
       targetPlatformDistro files('custom_distro.jar')
       }
   
これで、ワークスペースは提供されているデフォルトのJARファイルではなく、カスタムのdistro JARファイルを指すようになりました。`resolve`タスクを実行して、新しい機能セットに対してモジュールを検証します。
