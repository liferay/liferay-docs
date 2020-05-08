---
header-id: adding-custom-panel-apps
---

# カスタムパネルアプリの追加

[TOC levels=1-4]

カテゴリを作成したら、そこに追加するパネルアプリを作成します。

1. お気に入りのサードパーティツールを使用してOSGiモジュールを作成するか、[Blade CLI](/docs/7-0/tutorials/-/knowledge_base/t/blade-cli)を使用します。Blade CLIは、基本的なパネルカテゴリとパネルアプリの作成に役立つ[Panel App](/docs/7-0/reference/-/knowledge_base/r/panel-app-template)テンプレートを提供します。

2. モジュールの`src`ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。命名規則に従うには、クラスに一意の名前を付け、その後に*PanelApp* （例：`JournalPanelApp`）を付けます。

3. クラスの宣言のすぐ上に、次のアノテーションを挿入します。

       @Component(
       immediate = true,
       property = {
       "panel.app.order:Integer=INTEGER"
       "panel.category.key=" + PANEL_CATEGORY_KEY,
       },
       service = PanelApp.class
       )
   
   これらのプロパティと属性は、前の[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/adding-custom-panel-categories)で説明したものと同様です。
`panel.category.key`は、パネルアプリをパネルカテゴリに割り当てます。`panel.app.order:Integer`プロパティは、同じカテゴリの他のパネルアプリの中で、あなたのパネルアプリが表示される順序を指定します。たとえば、パネルアプリを[サイト管理] &rarr; *[Content]*に追加する場合は、次のプロパティを追加します。

       "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
   
   Liferayでデフォルトのパネルカテゴリを指定するために使用できるキーについては、[PanelCategoryKeys](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/constants/PanelCategoryKeys.html)クラスにアクセスしてください。

   `service`属性を`PanelApp.class`に設定します。`JournalPanelApp`クラスに対する同様の`@Component`アノテーションの例を以下に示します。

       @Component(
       immediate = true,
       property = {
       "panel.app.order:Integer=100",
       "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
       },
       service = PanelApp.class
       )
   
4. [BasePanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BasePanelApp.html)抽象クラスを拡張して、`PanelApp`インターフェイスを実装します。パネルカテゴリに関する前の[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/adding-custom-panel-categories)で学習したように、パネルでレンダリングするためにより複雑なUIを作成する必要がある場合は、作成できます。

   JSPを使用してそのUIをレンダリングする場合は、[BaseJSPPanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html)を拡張します。
これにより、JSP機能を製品メニューのアプリのリストに組み込むために使用できる追加のメソッドが提供されます。

   JSPは、パネルアプリにフロントエンド機能を提供する唯一の方法ではありません。`PanelCategory`を実装する独自のクラスを作成して、FreeMarkerなどの他のテクノロジーを使用できます。

5. 基本クラスを拡張せずに[PanelApp](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)インターフェイスを実装している場合は、そのメソッドを実装する必要があります。
`BlogsPanelApp`は、ポートレットをパネルアプリとして指定する方法の分かりやすい例です。このクラスは`BasePanelApp`を拡張し、`getPortletId`および`setPortlet`メソッドをオーバーライドします。これらのメソッドは、ブログポートレットをパネルアプリとして指定および設定します。

   各パネルアプリはポートレットに属している必要があり、各ポートレットには最大1つのパネルアプリを含めることができます。複数のパネルアプリが必要な場合は、別のポートレットを作成する必要があります。デフォルトでは、パネルアプリは、ユーザーが関連するポートレットを表示する権限を持っている場合にのみ表示されます。

   これらのメソッドは、ブログポートレットを次のように検索します。

       @Override
       public String getPortletId() {
       return BlogsPortletKeys.BLOGS_ADMIN;
       }
       
       @Override
       @Reference(
       target = "(javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN + ")",
       unbind = "-"
       )
       public void setPortlet(Portlet portlet) {
       super.setPortlet(portlet);
       }
   
   製品メニューでパネルアプリの外観をカスタマイズすることもできます。前に学んだように、`BaseJSPPanelApp`抽象クラスを拡張して、JSPにさらに機能を提供できます。

これで、製品メニューでパネルアプリを追加または変更する方法がわかりました。Liferayは、新しいパネルカテゴリとアプリを追加する簡単なソリューションを提供するだけでなく、任意のテクノロジーを使用して、より複雑なUIを製品メニューに追加する柔軟性も提供しています。
