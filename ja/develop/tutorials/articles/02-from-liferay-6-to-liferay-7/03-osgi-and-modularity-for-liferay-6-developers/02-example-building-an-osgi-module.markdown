---
header-id: example-building-an-osgi-module
---

# 例：OSGiモジュールの構築

[TOC levels=1-4]

前回のセクションでは、Liferay Portal 6開発者がOSGiとモジュラリティについて理解するうえで、最も重要な概念のいくつかについてを説明しました。 ここでは、モジュールを作成してデプロイすることで、これらの知識を実践に移していきます。

このモジュールには、宣言型サービスを使用してOSGiサービスを実装するJavaクラスが含まれています。 プロジェクトはGradleとbndを使用し、[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)内から構築およびデプロイできます。

モジュールプロジェクトの構造は次のとおりです。

  - `bnd.bnd`

  - `build.gradle`

  - `src/main/java/com/liferay/docs/service/MyService.java`

モジュールJARの構築時に、bndはモジュールマニフェストを自動的に生成します。

Javaクラスは次のとおりです。

    package com.liferay.docs.service;
    
    import org.osgi.service.component.annotations.Activate;
    import org.osgi.service.component.annotations.Component;
    
    @Component(
        immediate = true,
        service = MyService.class
    )
    public class MyService {
    
        @Activate
        void activate() throws Exception {
    
            System.out.println("Activating " + this.getDescription());
        }
    
        public String getDescription() {
    
            return this.getClass().getSimpleName();
        }
    
    }

ここには次のメソッドが含まれています。

  - `getDescription` - クラス名を戻します。

  - `activate` - コンソールメッセージ*Activating MyService*を出力します。 `@Activate`アノテーションは、コンポーネントのアクティベーション時にこのメソッドを呼び出すようOSGiランタイム環境に通知します。

`@Component`アノテーションは、クラスをOSGiサービスコンポーネントとして定義します。 次のプロパティで詳細を指定します。

  - `service=MyService.class` - コンポーネントをタイプ`MyService`で登録するためのサービスコンポーネントとして指定します。 この例では、クラスはそれ自体のサービスを実装します。 ただしサービスコンポーネントは通常、インターフェイスクラス向けのサービスを実装するということを覚えておいてください。

  - `immediate=true` - コンポーネントの依存関係が解決された直後にコンポーネントをアクティベートするように、サービスコンポーネントのランタイムに通知します。

以下が`bnd.bnd`ファイルです。

    Bundle-SymbolicName: my.service.project
    Bundle-Version: 1.0.0

`Bundle-SymbolicName`は、モジュールの任意の名前です。 モジュールのバージョン値`1.0.0`が適切です。

bndは、モジュールのJAR内のファイル`META-INF/MANIFEST.MF`にモジュールのOSGiマニフェストを生成します。 このプロジェクトでは、JARは`build/libs`フォルダに作成されます。

最後に作成されるファイルは、Gradleビルドファイル`build.gradle`です。

    dependencies {
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

`MyService`クラスは`@Component`アノテーションを使用するため、プロジェクトはOSGiサービスコンポーネントアノテーションモジュールに依存します。 Liferay Workspaceモジュールプロジェクトは、WorkspaceのGradleビルドインフラストラクチャを活用するため、ビルドスクリプトは非常にシンプルです。

このモジュールプロジェクトは、[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/installing-liferay-workspace)での開発用に作成されましたが、他のビルド環境で使用するために簡単に変更できます。

Liferay Workspaceの`modules`フォルダのサブフォルダに、プロジェクトファイルを配置します（例: `[Liferay_Workspace]/modules/my.service.project`）。

モジュールJARを構築して@product@にデプロイするには、`deploy`Gradleタスクを実行します。

    ../../gradlew deploy

| **注**: Bladeがインストールされている場合（推奨）、タスク名に続けて`blade gw`を | 入力することでGradleを実行できます（ 例: `blade gw deploy`）。 Bladeコマンドの | 詳細については、[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)を参照してください。

モジュールをデプロイすると、次のメッセージがサーバーコンソールに出力されます。

`Activating MyService`

　 OSGiモジュールを正常に構築し、@product@にデプロイしました。

ここで解説したOSGiモジュールの操作を知ることで、モジュラリティとOSGiがLiferayの開発を改善する、より多くの方法について理解できるようになります。 それらについて、次で説明します。
