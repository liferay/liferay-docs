---
header-id: creating-control-menu-entries
---

# コントロールメニューエントリの作成

[TOC levels=1-4]

次に、コントロールメニューをカスタマイズするためのエントリを作成します。このチュートリアルを開始する前に、[Adding Custom Panel Categories](/docs/7-1/tutorials/-/knowledge_base/t/adding-custom-panel-categories)を必ずお読みください。このチュートリアルは、パネルカテゴリの作成方法を理解していることを前提としています。コントロールメニューエントリの作成は、カテゴリの作成と同じパターンに従います。

1. OSGi構造とメタデータを作成します。

2. Liferayのフレームワークを実装します。

3. コントロールメニューエントリを定義します。

## OSGiモジュールの作成

最初にプロジェクトを作成する必要があります。

1. 汎用OSGiモジュールを作成します。モジュールには、Javaクラス、`bnd.bnd`ファイル、ビルドファイル（`build.gradle`や`pom.xml`など）が含まれている必要があります。プロジェクトでまだ定義していない場合は、次にJavaクラスを作成します。

2. モジュールの`src`ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。クラスに一意の名前を付け、その後に*ProductNavigationControlMenuEntry*（例：`StagingProductNavigationControlMenuEntry`）を付けます。

## Liferayのフレームワークの実装

次に、OSGiモジュールをLiferayのフレームワークに接続し、それらを使用してエントリに関する情報を定義する必要があります。

1. クラスの宣言のすぐ上に、次のコードを挿入します。

       @Component(
       immediate = true,
       property = {
       "product.navigation.control.menu.category.key=" + [Control Menu Category],
       "product.navigation.control.menu.category.order:Integer=[int]"
       },
       service = ProductNavigationControlMenuEntry.class
       )
   
   `product.navigation.control.menu.category.key`プロパティは、エントリのカテゴリを指定します。デフォルトのコントロールメニューには、サイト（左側）、ルーツ（中央）、ユーザー（右側）の3つのカテゴリがあります。

   ![図1：この画像は、選択したカテゴリに応じてエントリが存在する場所を示しています。](../../../images/control-menu-areas.png)

   カテゴリを指定するには、[ProductNavigationControlMenuCategoryKeys](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/constants/ProductNavigationControlMenuCategoryKeys.html)クラスで適切なキーを参照します。たとえば、次のプロパティは、コントロールメニューの中央部分にエントリを配置します。

       "product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS
   
   パネルカテゴリと同様に、カテゴリにエントリを配置するには整数を指定する必要があります。エントリは左から右に並べられます。順序`1`のエントリは、順序`2`のエントリの左側に表示されます。順序が指定されていない場合、OSGiコンテナーに最初に登録されたサービスに基づいてランダムに選択されます。最後に、`service`要素で`ProductNavigationControlMenuEntry.class`サービスを指定する必要があります。

4. [`ProductNavigationControlMenuEntry`](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/ProductNavigationControlMenuEntry.html)インターフェイスを実装します。[`BaseProductNavigationControlMenuEntry`](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/BaseProductNavigationControlMenuEntry.html)または[`BaseJSPProductNavigationControlMenuEntry`](@app-ref@/web-experience/latest/javadocs/com/liferay/product/navigation/control/menu/BaseJSPProductNavigationControlMenuEntry.html)抽象クラスを拡張することもできます。通常、`BaseProductNavigationControlMenuEntry`はテキスト付きのリンクまたはシンプルなアイコンのみを表示する基本エントリ（例：`IndexingProductNavigationControlMenuEntry`）向けに拡張されます。ボタンまたはサブメニューを備えたより複雑なUIを提供する場合は、`include()`および`includeBody()`メソッドをオーバーライドできます。UIの生成にJSPを使用する場合は、`BaseJSPProductNavigationControlMenuEntry`を拡張して時間を節約できます。これについては、以下で詳しく説明します。

## コントロールメニューエントリの定義

次に、コントロールメニューエントリを定義する必要があります。以下に、エントリを定義する例をいくつか示します。

### コントロールメニューの例

[`IndexingProductNavigationControlMenuEntry`](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/foundation/portal-search/portal-search-web/src/main/java/com/liferay/portal/search/web/internal/product/navigation/control/menu/IndexingProductNavigationControlMenuEntry.java)は、テキストとアイコンを提供する簡単な例です。`BaseProductNavigationControlMenuEntry`クラスを拡張し、 Liferayがインデックスを作成するときに使用されます。インデックス作成エントリは、コントロールメニューの*[Tools]*（中央）領域に、*更新*アイコンと*ポータルが現在インデックスを作成している*ことを示すテキストとともに表示されます。

[`ProductMenuProductNavigationControlMenuEntry`](https://github.com/liferay/liferay-portal/blob/7.0.3-ga4/modules/apps/web-experience/product-navigation/product-navigation-product-menu-web/src/main/java/com/liferay/product/navigation/product/menu/web/internal/product/navigation/control/menu/ProductMenuProductNavigationControlMenuEntry.java)はより高度です。このエントリは、コントロールメニューの*[Sites]*（左）領域に表示されますが、前述の例とは異なり、`BaseJSPProductNavigationControlMenuEntry`クラスを拡張します。これにより、JSPを使用してエントリのUIを定義するメソッドがさらにいくつか提供されます。次の2つのメソッドに注目してください。

    @Override
    public String getBodyJspPath() {
    return "/portlet/control_menu/product_menu_control_menu_entry_body.jsp";
    }
    
    @Override
    public String getIconJspPath() {
    return "/portlet/control_menu/product_menu_control_menu_entry_icon.jsp";
    }

`getIconJspPath()`メソッドは製品メニューアイコン（![[Menu Closed]](../../../images/icon-menu.png)
&rarr; ![[Menu Open]](../../../images/icon-menu-open.png)）を提供し、`getBodyJspPath()`メソッドはコントロールメニューの外側にエントリのUIボディを追加します。コントロールメニューの外側にUIを提供する場合は、後者の方法を使用する必要があります。これをテストするには、ホームページで製品メニューを開いたり閉じたりして確認します。

最後に、コントロールメニュー内にのみ存在する機能を提供する場合、`StagingProductNavigationControlMenuEntry`クラスは次のようにJSPを呼び出します。

    @Override
    public String getIconJspPath() {
    return "/control_menu/entry.jsp";
    }

`entry.jsp`が返され、コントロールメニューにステージングバーポートレットが埋め込まれます。

JSPファイルのサーブレットコンテキストも指定する必要があります。これがOSGiモジュール内にある場合は、`bnd.bnd`ファイルがWebコンテキストパスを定義していることを確認してください。

    Bundle-SymbolicName: com.sample.my.module.web
    Web-ContextPath: /my-module-web

次に、モジュールのシンボル名を使用してサーブレットコンテキストを参照します。

    @Override
    @Reference(
    target = "(osgi.web.symbolicname=com.sample.my.module.web)",
    unbind = "-"
    )
    public void setServletContext(ServletContext servletContext) {
    super.setServletContext(servletContext);
    }

### コントロールメニューエントリの表示

エントリの作成の一部として、表示されるタイミングを定義することがあります。コントロールメニューには、表示されているページに応じて異なるエントリが表示されます。`isShow(HttpServletRequest)`メソッドを使用してエントリが表示されるタイミングを指定できます。

たとえば、`IndexingProductNavigationControlMenuEntry`クラスは`isShow`を呼び出すときにインデックス作成ジョブの数を照会します。クエリ数が`0`場合、インデックス作成エントリはコントロールメニューに表示されません。

    @Override
    public boolean isShow(HttpServletRequest request) throws PortalException {
    int count = _indexWriterHelper.getReindexTaskCount(
    CompanyConstants.SYSTEM, false);
    
    if (count == 0) {
    return false;
    }
    
    return super.isShow(request);
    }

`StagingProductNavigationControlMenuEntry`クラスは、表示されるページ数を選択します。ページが管理ページ（*[サイト管理]*、*[自分のアカウント]*など）の場合、ステージングエントリは表示されません。

    @Override
    public boolean isShow(HttpServletRequest request) throws PortalException {
    ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
    WebKeys.THEME_DISPLAY);
    
    Layout layout = themeDisplay.getLayout();
    
    // This controls if the page is an Administration Page
    
    if (layout.isTypeControlPanel()) {
    return false;
    }
    
    // This controls if Staging is enabled
    
    if (!themeDisplay.isShowStagingIcon()) {
    return false;
    }
    
    return true;
    }

### 依存関係の定義

ビルドファイル（`build.grade`や`pom.xml`など）で、コントロールメニューエントリの依存関係を定義します。例として、いくつかの一般的な依存関係（Gradle形式）を以下に定義します。

    dependencies {
    compileOnly group: "com.liferay", name: "com.liferay.product.navigation.control.menu.api", version: "[VERSION]"
    compile group: "com.liferay", name: "com.liferay.product.navigation.taglib", version: "[VERSION]"
    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "[VERSION]"
    compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "[VERSION]"
    compile group: "javax.servlet.jsp", name: "javax.servlet.jsp-api", version: "[VERSION]"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "[VERSION]"
    }

モジュールの機能に応じて、プロジェクトにさらに依存関係が必要になる場合があります。

 コントロールメニューの3つのデフォルトのパネルカテゴリのいずれかにエントリを作成しました。そのエントリを提供する基本的な方法と高度な方法、およびその両方を適用する方法について学習しました。
