---
header-id: making-applications-configurable
---

# アプリケーションを構成可能にする

[TOC levels=1-4]

構成可能なアプリケーションにより、適切な権限を持つユーザーは、開発者が設定した範囲内で、アプリケーションの特定の側面を変更できます。 Liferayの構成フレームワークは、Javaインターフェイスで構成オプションを定義する場合にUIを自動生成することにより、タスクを簡素化します。 このように、独自のアプリケーション構成フレームワークを作成する必要はありません。

次の3つの高レベルのタスクを完了して、アプリケーションを構成フレームワークと統合します。

1.  ユーザーインターフェイスで構成を設定する方法を提供します。

2.  アプリケーションを構成できる範囲を設定します。

3.  ビジネスロジックの構成値を読み取ります。

このチュートリアルでは、コントロールパネルのシステム設定アプリケーションにアプリケーションの構成フォームを追加することと、構成を分類することの両方を示します。 後続のチュートリアルでは、方法を示します

1.  構成の *スコープ* を設定します。 構成スコープ [こちら](/docs/7-1/user/-/knowledge_base/u/system-settings#configuration-scope)ご覧ください。

2.  さまざまなコンテキストから構成値を読み取ります。

| **注：** 稼働中のアプリケーション構成を表示するには、|をデプロイします。 `構成アクション` | [ブレードサンプル](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/configuration-action) |システム設定に移動します（*コントロールパネル* → *構成* →| *システム設定*）。 [その他]カテゴリで、[ *メッセージ]表示| [構成* エントリ。 | | *ブレードメッセージポートレット* をページに追加して、構成の選択をテストします。

構成APIを使用するのに事前の知識はあまり必要ありませんが、コードに飛び込む前にいくつかの重要な概念を理解しておくと役立ちます。

**型付き構成** ：ここで説明する方法では、 *型付き* 構成を使用します。 アプリケーション構成は、単にキーと値のペアのリストではありません。 値には、 `整数` `文字列のリスト`、URLなどのタイプがあります。 独自の型を使用することもできますが、それはこのチュートリアルの範囲外です。 型付き構成は、型なし構成よりも使いやすく、多くのプログラムエラーを防ぎます。 構成オプションはプログラムで明示的に指定する必要があるため、開発者は最新のIDEでオートコンプリートを使用して、特定のアプリケーションまたはそのコンポーネントのすべての構成オプションを見つけることができます。

**モジュール性** ：最新のアプリケーションは *モジュール* あり、軽量 [コンポーネントのコレクションとして構築されます](/docs/7-1/tutorials/-/knowledge_base/t/fundamentals#components)。

**構成スコープ** ：アプリケーションが異なるスコープで異なる構成をサポートする必要がある場合、以下に説明するAPIがほとんどの負担を処理します。 アプリケーションの構成をスコープする予定がない場合でも、用語 *構成スコープ* を引き続き理解する必要があります。 最も一般的な構成スコープは次のとおりです。

  - *システム* 構成は、アプリケーションの完全インストールに固有のものです。

  - *仮想インスタンス* 構成は、仮想インスタンスごとに異なる場合があります。

  - *サイト* 構成は、サイトごとに異なります。

  - *ポートレットインスタンス* 構成は、ページ（ポートレットなど）に配置された単一のアプリケーションに適用されます。 ページ上のアプリケーションの各配置（インスタンス）は、異なる構成を持つことができます。

概念的なもので十分です。 いくつかのコードを開始する準備が整いました。 Liferay Portal 6.2以前の従来のメカニズムを使用して設定可能なポートレットまたはサービスがすでにある場合は、「ポートレット設定からConfiguration API</a> への移行

チュートリアルを参照してください。</p> 



## 構成インターフェースの作成

最初に、システムスコープで構成を作成する方法を学習します。

1.  Javaインターフェイスを作成して、構成とそのデフォルト値を表します。 Javaインターフェースを使用すると、構成オプションごとに高度な型システムを使用できます。 Liferay Formsアプリケーションの構成インターフェースは次のとおりです。
   
        @Meta.OCD(
            id = "com.liferay.dynamic.data.mapping.form.web.configuration.DDMFormWebConfiguration",
            localization = "content/Language", name = "ddm-form-web-configuration-name"
        )
        public interface DDMFormWebConfiguration {
       
            @Meta.AD(
                deflt = "1", description = "autosave-interval-description",
                name = "autosave-interval-name", required = false
            )
            public int autosaveInterval();
       
            @Meta.AD(
                deflt = "descriptive", name = "default-display-view",
                optionLabels = {"Descriptive", "List"},
                optionValues = {"descriptive", "list"}, required = false
            )
            public String defaultDisplayView();
       
        }
       
   
   2つの構成オプション、自動保存間隔（デフォルトは1分）およびデフォルトの表示ビューを定義します。これは、説明またはリストにすることができますが、デフォルトは説明になります。 上記のスニペットの2つのJavaアノテーションが行うことは次のとおりです。
   
       1.  **Meta.OCD：** このクラスを特定のIDを持つ構成として登録します。 **IDは完全修飾構成クラス名でなければなりません。**

    2.  **Meta.AD：** 指定 [オプションのメタデータ](http://bnd.bndtools.org/chapters/210-metatype.html) 、このようなことが必要な分野だかどうか、それはデフォルト値を持っているかのようにフィールドについて、。 フィールドを必須として設定し、デフォルト値を指定しない場合、システム管理者は、アプリケーションが適切に機能するために値を指定する必要があることに注意してください。 デフォルト値を指定するには、 `deflt` プロパティを使用します。
       
       上記の `Meta` クラスの完全修飾名は `aQute.bnd.annotation.metatype.Meta`です。 このクラスと `Meta.OCD` および `Meta.AD` アノテーションの詳細については、 [bndtoolsのドキュメント](http://bnd.bndtools.org/chapters/210-metatype.html)を参照してください。
       
       構成インターフェースの素晴らしい点は、一度構成すると、自動生成されたUIも使えるということです！

2.  使用するには `Meta.OCD` 及び `Meta.AD` あなたのモジュールに注釈を、あなたがしなければならない [、依存関係を指定](/docs/7-0/tutorials/-/knowledge_base/t/configuring-dependencies) BNDライブラリ上を。 bndバージョン3の使用をお勧めします。 次に、この依存関係をGradleプロジェクトに含める方法の例を示します。
   
       dependencies {
           compile group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.1.0"
       }
       
   
   | **注：** アノテーション `@ Meta.OCD` および `@ Meta.AD` はbnd |ライブラリですが、OSGi標準バージョンR6では、OSGiコアに含まれています| `@ObjectClassDefinition` および `@AttributeDefinition`という名前で。 OSGi |アノテーションは、この記事で説明されているような単純なケースに使用できます。チュートリアル。 ただし、2つのライブラリの主な違いは、bnd | OSGi注釈は使用できませんが、注釈は実行時に使用できます。 | Liferay固有の機能の一部にはランタイムの可用性が必要なためです|以下で説明するように、デフォルトでbndアノテーションを使用することをお勧めします。

3.  プロジェクトの `bnd.bnd` ファイルに次の行を追加します。
   
       -metatype: *
       
   
   この行により、bndは構成インターフェースを使用してXML構成ファイルを生成できます。 これにより、アプリケーションの構成オプションに関する多くの情報が提供されます。 実際、 [システム設定](/docs/7-1/user/-/knowledge_base/u/system-settings) ユーザーインターフェイスを自動的に生成するのに十分です。

<!--[Figure 1: Navigate to the Control Panel and then click on *Configuration* &rarr; *System Settings*. Then click on *Platform* &rarr; *Third Party*, find the *Example configuration* link, and click on it.](../../images/example-configuration-system-settings.png)-->

構成インターフェイスを登録するだけで、システム設定アプリケーションで自動生成された完全に機能するUIフォームを取得できます。 デフォルトでは、構成はプラットフォーム→サードパーティに配置されます。 構成を論理的などこかに分類することで、アプリケーションのユーザーが見つけやすくなります。 次のチュートリアルでは、その方法を示します。
