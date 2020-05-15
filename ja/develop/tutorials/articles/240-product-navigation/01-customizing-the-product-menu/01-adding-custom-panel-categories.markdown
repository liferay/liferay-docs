---
header-id: adding-custom-panel-categories
---

# カスタムパネルカテゴリの追加

[TOC levels=1-4]

製品メニューを操作すると、*Webコンテンツ*や*サイト設定*などのパネルアプリが、*[Content]*や*[設定]*などのパネルカテゴリに整理されていることがわかります。このチュートリアルでは、新しいパネルカテゴリをメニューに追加する方法について説明します。新しいパネルアプリの追加については、次のセクションで説明します。

新しいカテゴリを作成するには、3つの手順があります。

1. OSGi構造とメタデータを作成します。

2. Liferayのフレームワークを実装します。

3. コントロールメニューカテゴリを定義します。

## OSGiモジュールの作成

最初にプロジェクトを作成する必要があります。

1. お気に入りのサードパーティツールを使用してOSGiモジュールを作成するか、[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)を使用します。Blade CLIは、パネルカテゴリとパネルアプリを作成するための[Panel App](/docs/7-1/reference/-/knowledge_base/r/panel-app-template)テンプレートを提供します 。

2. モジュールの`src`ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。命名規則に従うには、クラスに一意の名前を付け、その後に`PanelCategory`を付けます（例：`ControlPanelCategory`）。

## Liferayのフレームワークの実装

次に、OSGiモジュールをLiferayのフレームワークに接続し、それらを使用してエントリに関する情報を定義する必要があります。必要な手順は2つのみです。

1. パネルカテゴリキーを宣言する`@Component`アノテーションを挿入します。

2. `PanelCategory`インターフェイスを実装します。

これらの手順の両方を以下で説明します。

### @Componentアノテーションの挿入

クラスの宣言のすぐ上に、次のアノテーションを挿入します。

    @Component(
    immediate = true,
    property = {
    "panel.category.key=" + [Panel Category Key],
    "panel.category.order:Integer=[int]"
    },
    service = PanelCategory.class
    )

`property`要素は、カテゴリに割り当てる必要がある2つのプロパティを指定します。`panel.category.key`は、カスタムカテゴリの親カテゴリを指定します。割り当てる一般的な親カテゴリは、[`PanelCategoryKeys`](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/constants/PanelCategoryKeys.html)クラスにあります。たとえば、コントロールパネルで子カテゴリを作成する場合、`PanelCategoryKeys.CONTROL_PANEL`を割り当てることができます。同様に、コントロールパネルやサイト管理などのルートカテゴリを作成する場合は、`PanelCategoryKeys.ROOT`を割り当てることができます。

`panel.category.order:Integer`プロパティは、カテゴリが表示される順序を指定します。数値（整数）が大きいほど、親に割り当てられている他の兄弟カテゴリの中であなたのカテゴリのリスト順が低くなります。

最後に、`service`要素で`PanelCategory.class`サービスを指定する必要があります。`UserPanelCategory`クラスに対する同様の`@Component`アノテーションの例を以下に示します。

    @Component(
    immediate = true,
    property = {
    "panel.category.key=" + PanelCategoryKeys.ROOT,
    "panel.category.order:Integer=200"
    },
    service = PanelCategory.class
    )

**注：**デフォルトメニューの既存のカテゴリの間にパネルカテゴリを挿入するには、既存のカテゴリの`panel.category.order:Integer`プロパティを把握する必要があります。指定されたpanel.category.keyを持つデフォルトのカテゴリには、100から始まる100単位の番号が付けられています。
たとえば、製品メニューの3つのメインセクション（コントロールパネル、ユーザーメニュー、サイト管理）には、それぞれ100、200、300の`panel.category.order:Integer`プロパティがあります。コントロールパネルとユーザーメニューの間に挿入された新しいパネルには、ROOTの`panel.category.key`と150の`panel.category.order:Integer`が必要です。

### PanelCategoryインターフェイスの実装

`PanelCategory`インターフェイスでは、次のメソッドを実装する必要があります。

- `getNotificationCount`：パネルカテゴリに表示される通知の数を返します。
- `include`：パネルカテゴリのボディをレンダリングします。
- `includeHeader`：パネルカテゴリヘッダーをレンダリングします。
- `isActive`：パネルが選択されているかどうか。
- `isPersistState`：パネルカテゴリの状態をデータベースに保持するかどうか。これにより、メニューから移動するときにパネルカテゴリの状態が保存されます。

`PanelCategory`インターフェイスをすでに実装している基本クラスを拡張する場合、実装する必要のあるメソッドの数を減らすことができます。これを行うための推奨される方法は、[`BasePanelCategory`](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BasePanelCategory.html)または[`BaseJSPPanelCategory`](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelCategory.html)抽象クラスを拡張することです。通常、`BasePanelCategory`は、カテゴリ名のみを表示する基本的なカテゴリ（コントロールパネルのカテゴリなど）向けに拡張されます。さらに複雑な機能を追加するには、`PanelCategory`インターフェイスから`include()`または`includeHeader()`を実装することにより、任意のフロントエンドテクノロジーを使用してパネルにカスタムUIを提供できます。

フロントエンドテクノロジーとしてJSPを使用する場合は、メソッド`include()`および`includeHeader()`をすでに実装している`BaseJSPPanelCategory`という基本クラスを拡張します。これについては、以下で詳しく説明します。

**注：**このチュートリアルでは、JSPの例を使用して、パネルのカテゴリとアプリに機能を提供する方法を説明しています。ただし、JSPは、カテゴリ/アプリにフロントエンド機能を提供する唯一の方法ではありません。`PanelCategory`を実装する独自のクラスを作成して、FreeMarkerなどの他のテクノロジーを使用できます。

## コントロールメニューカテゴリの定義

カテゴリの作成に使用するフレームワークを確立したら、カスタムパネルカテゴリの作成に必要な他のメソッドを追加する必要があります。
前に学習したように、`BasePanelCategory`および`BaseJSPPanelCategory`抽象クラスを拡張して、`PanelCategory`を実装できます。

### BasePanelCategory

パネルカテゴリに名前のようなシンプルなものが必要な場合は、`BasePanelCategory`を拡張するだけで十分でしょう。たとえば、[`ControlPanelCategory`](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/web-experience/product-navigation/product-navigation-control-panel/src/main/java/com/liferay/product/navigation/control/panel/internal/application/list/ControlPanelCategory.java)は`BasePanelCategory`を拡張し、`getLabel`メソッドを指定してパネルカテゴリ名を設定および表示します。

    @Override
    public String getLabel(Locale locale) {
    return LanguageUtil.get(locale, "control-panel");
    }

### BaseJSPPanelCategory

より複雑な機能が必要な場合は、`BaseJSPPanelCategory`を拡張し、JSPを使用してパネルカテゴリをレンダリングします。たとえば、[`SiteAdministrationPanelCategory`](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/web-experience/product-navigation/product-navigation-site-administration/src/main/java/com/liferay/product/navigation/site/administration/internal/application/list/SiteAdministrationPanelCategory.java)は`getHeaderJspPath`および`getJspPath`メソッドを指定します。レンダリングするUIを使用してJSPを作成し、次のようなメソッドでそのパスを指定できます。

    @Override
    public String getHeaderJspPath() {
    return "/sites/site_administration_header.jsp";
    }
    
    @Override
    public String getJspPath() {
    return "/sites/site_administration_body.jsp";
    }

一方のJSPはパネルカテゴリのヘッダー（パネルが折りたたまれたときに表示される）をレンダリングし、もう一方のJSPはそのボディ（パネルを展開したときに表示される）をレンダリングします。

JSPファイルをロードするサーブレットコンテキストも指定する必要があります。これがOSGiモジュール内にある場合は、`bnd.bnd`ファイルがWebコンテキストパスを定義していることを確認してください。

    Bundle-SymbolicName: com.sample.my.module.web
    Web-ContextPath: /my-module-web

次に、以下のようにモジュールのシンボル名を使用してサーブレットコンテキストを参照します。

    @Override
    @Reference(
    target = "(osgi.web.symbolicname=com.sample.my.module.web)",
    unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
    super.setServletContext(servletContext);
    }

 製品メニューに表示するカスタムパネルカテゴリを作成できました。多くの場合、パネルカテゴリには、ユーザーがアクセスできるようにパネルアプリが保持されます。次に、パネルアプリをパネルカテゴリに追加する方法を学習します。
