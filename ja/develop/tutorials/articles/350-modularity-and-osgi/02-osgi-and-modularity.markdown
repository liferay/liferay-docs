---
header-id: osgi-and-modularity
---

# OSGiとモジュール性

[TOC levels=1-4]

モジュール性により、特にチームとしてのソフトウェアの作成が楽しくなります！ DXPでのモジュール開発の利点は次のとおりです。

  - @product@のランタイムフレームワークは、軽量、高速、安全です。
  - フレームワークはOSGi標準を使用します。 他のプロジェクトでOSGiを使用した経験がある場合は、既存の知識をDXPでの開発に適用できます。
  - モジュールは、サービスレジストリにサービスを公開し、サービスレジストリからサービスを消費します。 サービスコントラクトはサービスプロバイダーとコンシューマーから疎結合されており、レジストリはコントラクトを自動的に管理します。
  - モジュールの依存関係は、コンテナによって自動的に動的に管理されます（再起動は不要です）。
  - コンテナはモジュールのライフサイクルを動的に管理します。 Liferayの実行中にモジュールをインストール、起動、更新、停止、およびアンインストールできるため、展開が簡単になります。
  - パッケージが明示的にエクスポートされているモジュールのクラスのみが公開されています。 OSGiはデフォルトで他のすべてのクラスを非表示にします。
  - モジュールとパッケージは意味的にバージョン管理され、他のパッケージの特定のバージョンへの依存関係を宣言します。 これにより、同じパッケージの異なるバージョンに依存する2つのアプリケーションが、それぞれ独自のバージョンのパッケージに依存することができます。
  - チームメンバーは、モジュールを並行して開発、テスト、および改善できます。
  - 既存の開発者ツールと環境を使用してモジュールを開発できます。

OSGiを使用したモジュール式ソフトウェア開発には多くの利点がありますが、ここではほんの一面しか説明できません。 モジュールの開発を開始すると、他の方法での開発に戻るのは難しいかもしれません。

## モジュール

モジュールプロジェクトがどのように見えるかを確認し、@product@のモジュール開発機能が実際に動作しているのを見てみましょう。 物事をシンプルに保つために、唯一のプロジェクトコードと構造が示されている：あなたがすることができます [、モジュールの作成](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development) これらのいつでもなどを。

これらのモジュールは、文字列を受け取り、グリーティングで使用するコマンドをまとめて提供します。 モジュールについては「Hello World」と考えてください。

### API

APIモジュールが最初です。 プロバイダーが実装し、コンシューマーが使用する契約を定義します。 その構造は次のとおりです。

  - `greeting-api`
      - `src`
          - `main`
              - `java`
                  - `com/liferay/docs/greeting/api`
                      - `Greeting.java`
      - `bnd.bnd`
      - `build.gradle`

とても簡単ですよね？ Javaソースファイル以外に、他の2つのファイルのみがあります。Gradleビルドスクリプト（任意のビルドシステムを使用できます）、および `bnd.bnd`と呼ばれる構成ファイルです。 `bnd.bnd` ファイルは、モジュールを説明および構成します。

    Bundle-Name: Greeting API
    Bundle-SymbolicName: com.liferay.docs.greeting.api
    Bundle-Version: 1.0.0
    Export-Package: com.liferay.docs.greeting.api

モジュールの名前は *Greeting API*です。 そのシンボリック名（一意性を保証する名前）は `com.liferay.docs.greeting.api`です。 次にセマンティックバージョンが宣言され、そのパッケージは *エクスポート*です*つまり、他のモジュールで使用できるようになります。 このモジュールのパッケージは、他のモジュールが実装できるAPIにすぎません。</p>

最後に、Javaクラスがあります。この場合はインターフェースです：

    package com.liferay.docs.greeting.api;
    
    import aQute.bnd.annotation.ProviderType;
    
    @ProviderType
    public interface Greeting {
    
            public void greet(String name);
    
    }

インターフェースの `@ProviderType` アノテーションは、インターフェースを実装するものはすべてプロバイダーであることをサービスレジストリに伝えます。 インターフェイスの1つのメソッドは、 `文字列` を要求し、何も返しません。

それでおしまい\！ ご覧のとおり、モジュールの作成は、他のJavaプロジェクトの作成とそれほど変わりません。

### プロバイダー

インターフェイスはAPIのみを定義します。何かをするには、実装する必要があります。 これがプロバイダーモジュールの目的です。 Greeting APIのプロバイダーモジュールは次のようになります。

  - `greeting-impl`
      - `src`
          - `main`
              - `java`
                  - `com/liferay/docs/greeting/impl`
                      - `GreetingImpl.java`
      - `bnd.bnd`
      - `build.gradle`

APIモジュールと同じ構造：ビルドスクリプト、 `bnd.bnd` 構成ファイル、および実装クラス。 唯一の違いはファイルの内容です。 `bnd.bnd` ファイルは少し異なります。

    Bundle-Name: Greeting Impl
    Bundle-SymbolicName: com.liferay.docs.greeting.impl
    Bundle-Version: 1.0.0

バンドル名、シンボル名、およびバージョンはすべて、APIと同様に設定されます。

最後に、 `Export-Package` 宣言はありません。 クライアント（これから作成する3番目のモジュール）は、APIを使用したいだけです。APIが返すものを返す限り、その実装がどのように動作するかは気にしません。 クライアントは、APIへの依存関係を宣言するだけです。サービスレジストリは、実行時に適切な実装を挿入します。

かなりクールですね

残っているのは、実装を提供するクラスのみです。

    package com.liferay.docs.greeting.impl;
    
    import com.liferay.docs.greeting.api.Greeting;
    
    import org.osgi.service.component.annotations.Component;
    
    @Component(
        immediate = true,
        property = {
        },
        service = Greeting.class
    )
    public class GreetingImpl implements Greeting {
    
        @Override
        public void greet(String name) {
            System.out.println("Hello " + name + "!");
        }
    
    }

実装は簡単です。 `文字列` を名前として使用し、helloメッセージを出力します。 より良い実装は、LiferayのAPIを使用して、システム内のすべてのユーザーのすべての名前を収集し、各ユーザーに挨拶通知を送信することですが、ここでのポイントは、物事をシンプルにすることです。 ただし、Greeting実装の `@Component` アノテーションがより高いサービスランキングプロパティ（ `"service.ranking：Integer = 100"`）を指定する別のモジュールをデプロイすることにより、この実装を置き換えることを妨げるものは何もないことを理解する必要があります。

この `@Component` アノテーションは3つのオプションを定義します： `immediate = true`、空のプロパティリスト、およびそれが実装するサービスクラス。 `immediate = true` 設定は、このモジュールを遅延ロードしないことを意味します。サービスレジストリは、最初に使用されるときではなく、展開されるとすぐにロードします。 `@Component` アノテーションを使用すると、クラスが宣言型サービスコンポーネントとして宣言されます。これは、OSGiモジュールのコンポーネントを作成する最も簡単な方法です。 コンポーネントは、モジュールの起動時にランタイムが自動的に作成するPOJOです。

このモジュールをコンパイルするには、実装するAPIがクラスパス上になければなりません。 Gradleを使用している場合、 `greetings-api` プロジェクトを `依存関係に追加します{... }` ブロック。 [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace) モジュールでは、依存関係は次のようになります。

    compileOnly project (':modules:greeting-api')

プロバイダーモジュールにはこれですべてです。

### 消費者

コンシューマまたはクライアントは、APIモジュールが定義し、プロバイダーモジュールが実装するAPIを使用します。 DXPには、さまざまな種類のコンシューマモジュールがあります。 [ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/portlets) は最も一般的なコンシューマモジュールタイプですが、それら自体がトピックであるため、Apache Felix Gogoシェル用のコマンドを作成することにより、この例は単純なままです。 もちろん、消費者は多くの異なるAPIを使用して機能を提供できることに注意してください。

コンシューマモジュールの構造は、他のモジュールタイプと同じです。

  - `greeting-command`
      - `src`
          - `main`
              - `java`
                  - `com/liferay/docs/greeting/command`
                      - `GreetingCommand.java`
      - `bnd.bnd`
      - `build.gradle`

繰り返しますが、ビルドスクリプト、 `bnd.bnd` ファイル、およびJavaクラスがあります。 このモジュールの `bnd.bnd` ファイルは、プロバイダーのものとほぼ同じです。

    Bundle-Name: Greeting Command
    Bundle-SymbolicName: com.liferay.docs.greeting.command
    Bundle-Version: 1.0.0

ここでは新しいことは何もありません。プロバイダーに対して宣言したものと同じものを宣言します。

Javaクラスはもう少し進んでいます：

    package com.liferay.docs.greeting.command;
    
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    
    import com.liferay.docs.greeting.api.Greeting;
    
    @Component(
        immediate = true,
        property = {
            "osgi.command.scope=greet",
            "osgi.command.function=greet"
        },
        service = Object.class
    )
    public class GreetingCommand {
    
        public void greet(String name) {
            Greeting greeting = _greeting;
    
            greeting.greet(name);
        }
    
        @Reference
        private Greeting _greeting;
    
    }

`@Component` アノテーションは同じ属性を宣言しますが、異なるプロパティと異なるサービスを指定します。 Javaのように、すべてのクラスが `java.lang.Object` サブクラスである場合（デフォルトで指定する必要はありませんが）、宣言型サービスでは、ランタイムは登録するクラスのタイプを知る必要があります。 特定の型を実装していないため、親クラスは `java.lang.Object`であるため、そのクラスをサービスとして指定する必要があります。 Javaでは、何も継承しないクラスを作成する場合、親として `Object` を指定する必要はありませんが、宣言型サービスでは必要です。

2つのプロパティは、コマンドスコープとコマンド関数を定義します。 複数のAPIが `copy` または `delete`などの類似の機能を持つことは一般的であるため、すべてのコマンドにはコンテキストを定義するスコープがあります。 これらのプロパティは、 `greet`というスコープで `greet` というコマンドを作成することを指定します。 想像力に欠ける点はありますが、これはコマンドを十分に定義しています。

`@Component` アノテーションで `osgi.command.function = greet` を指定したため、クラスには `greet`という名前のメソッドが必要です。 しかし、この `greet` メソッドはどのように機能しますか？ `Greeting` OSGiサービスのインスタンスを取得し、 `greet` メソッドを呼び出して、 `name` パラメーターを渡します。 `Greeting` OSGiサービスのインスタンスはどのように取得されますか？ `GreetingCommand` クラスは、プライベートサービスBean、 `_greeting` 、タイプ `Greeting`宣言します。 これは、プロバイダーモジュールが登録するOSGiサービスタイプです。 `@Reference` 注釈は、OSGiランタイムにサービスBeanをサービスレジストリのサービスでインスタンス化するように指示します。 ランタイムは、タイプ `GreetingImpl` の `Greeting` オブジェクトをプライベートフィールド `_greeting`バインドします。 `greet` メソッドは、 `_greeting` フィールド値を使用します。

プロバイダーと同じように、コンシューマーはコンパイルするためにクラスパスにAPIを持っている必要がありますが、実行時に、すべての依存関係を適切に宣言したため、コンテナーはこれらの依存関係を認識し、自動的に提供します。

あなたがいた場合は [DXPインスタンスにデプロイするこれらのモジュール](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)、あなたがに接続することができるだろう [ゴゴシェル](develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell) と、このようなコマンドを実行します。

    greet:greet "Captain\ Kirk"

その後、シェルは挨拶を返します。

    Hello Captain Kirk!

この最も基本的な例は、モジュールベースの開発が簡単で簡単であることを明確にするはずです。 API-Provider-Consumerコントラクトは疎結合を促進し、ソフトウェアの管理、強化、サポートを容易にします。

## 典型的なLiferayアプリケーション

Liferayのソースからの典型的なアプリケーションを見ると、一般的に少なくとも4つのモジュールが見つかります。

  - APIモジュール
  - サービス（プロバイダー）モジュール
  - テストモジュール
  - Web（消費者）モジュール

これは、ユーザーがコメント、ブログ、または他のアプリケーションで `@username` 命名法で他のユーザーに言及できるMentionsアプリケーションのような、いくつかの小規模なアプリケーションで見つけることができるものです。 Documents and Mediaライブラリなどのより大きなアプリケーションには、より多くのモジュールがあります。 ドキュメントおよびメディアライブラリの場合、異なるドキュメントストレージバックエンド用に個別のモジュールがあります。 Wikiの場合、Wikiエンジンごとに個別のモジュールがあります。

機能のバリエーションをモジュールとしてカプセル化すると、拡張性が向上します。 Liferayがまだサポートしていないドキュメントストレージバックエンドがある場合、LiferayのドキュメントストレージAPIを実装して、ソリューション用のモジュールを開発し、LiferayのDocuments and Mediaライブラリを拡張することができます。 Liferayのwikiが提供するものよりも好きなWiki方言がある場合は、そのモジュールを作成してLiferayのwikiを拡張できます。

まだ興奮していますか？ 開発を始める準備はできていますか？ 詳細については、次のリソースをご覧ください。

## 関連トピック

[Liferay IDE](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)

[Liferayワークスペース](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)

[ブレードCLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)

[メイヴン](/docs/7-1/tutorials/-/knowledge_base/t/maven)

[Liferay 7へのプラグインアップグレードの計画](/docs/7-1/tutorials/-/knowledge_base/t/migrating-existing-code-to-liferay-7)
