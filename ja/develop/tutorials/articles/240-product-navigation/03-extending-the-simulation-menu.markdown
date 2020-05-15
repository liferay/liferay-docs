---
header-id: extending-the-simulation-menu
---

# シミュレーションメニューの拡張

[TOC levels=1-4]

ページとアプリがユーザーにどのように表示されるかをテストするときは、できるだけ多くの方法でビューをシミュレートすることが重要です。メインページの右側にあるシミュレーションメニューからシミュレーションを行うことができ、提供されていないものをシミュレートする必要がある場合は、メニューを拡張できます。

まず、パネルのカテゴリ/アプリの使用に慣れる必要があります。これについては、[Customizing The Product Menu](/docs/7-1/tutorials/-/knowledge_base/t/customizing-the-product-menu)のチュートリアルで詳しく説明しています。パネルカテゴリとパネルアプリの作成方法を理解してから、このチュートリアルを進めてください。

シミュレーションメニューと製品メニューは大抵は同じ基本クラスを拡張するため、両者の違いはほとんどありません。デフォルトでは、シミュレーションメニューは1つのパネルカテゴリと1つのパネルアプリのみで構成されています。Liferayは、`DevicePreviewPanelApp`を保持するために必要な非表示のカテゴリである[`SimulationPanelCategory`](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/modules/apps/product-navigation/product-navigation-simulation-web/src/main/java/com/liferay/product/navigation/simulation/web/internal/application/list/SimulationPanelCategory.java)クラスを提供します。これは、デフォルトでシミュレーションメニューに表示されるアプリと機能です。

![図1：シミュレーションメニューは、デバイスプレビューアプリケーションを提供します。](../../images/simulation-menu-preview.png)

シミュレーションメニューに独自の機能を提供するには、`SimulationPanelCategory`でパネルアプリを作成する必要があります。広範な機能を追加する場合は、メニューに追加のパネルカテゴリを作成して、パネルアプリを分割することもできます。このチュートリアルでは、すでに存在する非表示カテゴリ用のパネルアプリを作成する、より単純なケースについて説明します。

1. カスタムパネルアプリの作成については、[カスタムパネルアプリの追加](/docs/7-0/tutorials/-/knowledge_base/t/customizing-the-product-menu#adding-custom-panel-apps)に記載されている手順に従ってください。パネルアプリの基盤を作成したら、それを調整してシミュレーションメニューをカスタマイズする方法について学習しましょう。

   Blade CLIの[シミュレーションパネルエントリテンプレート](/docs/7-1/reference/-/knowledge_base/r/simulation-panel-entry-template)を使用して、シミュレーションパネルアプリを生成できます。
動作例については、[シミュレーションパネルアプリのサンプル](/docs/7-1/reference/-/knowledge_base/r/simulation-panel-app)も参照してください。

2. このチュートリアルでは、既存のシミュレーションカテゴリに追加の機能を提供することを前提としているため、`@Component`アノテーションの`panel.category.key`でシミュレーションカテゴリを設定します。

       "panel.category.key=" + SimulationPanelCategory.SIMULATION
   
   この定数を使用するには、[`com.liferay.product.navigation.simulation`](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.product.navigation.simulation/)に依存関係を追加する必要があります。

   新しいパネルアプリを表示する順序も必ず指定してください。これについては、[カスタムパネルアプリの追加](/docs/7-0/tutorials/-/knowledge_base/t/customizing-the-product-menu#adding-custom-panel-apps)で説明しました。

3. このチュートリアルでは、JSPを使用していることを前提としています。
したがって、[`BaseJSPPanelApp`](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/BaseJSPPanelApp.html)抽象クラスを拡張する必要があります。このクラスは、[`PanelApp`](@app-ref@/web-experience/latest/javadocs/com/liferay/application/list/PanelApp.html)インターフェイスを実装し、JSPを指定してパネルアプリのUIをレンダリングするために必要な追加のメソッドも提供します。JSP以外のテクノロジー（FreeMarkerなど）を使用する場合は、独自の`include()`メソッドを実装して、希望のフロントエンドテクノロジーを使用することもできます。

4. シミュレーションビューを定義します。たとえば、`DevicePreviewPanelApp`の`getJspPath`メソッドは、デバイスシミュレーションインターフェイスが定義されている`resources/META-INF/resources`フォルダ内の`simulation-device.jsp`ファイルを指します。オプションで、独自の言語キー、CSS、またはJavaScriptリソースをシミュレーションモジュールに追加することもできます。

   このメソッドを実装することにより、適切なサーブレットコンテキストも提供されます。

       @Override
       @Reference(
       target = "(osgi.web.symbolicname=com.liferay.product.navigation.simulation.device)",
       unbind = "-"
       )
       public void setServletContext(ServletContext servletContext) {
       super.setServletContext(servletContext);
       }
   
   [Customizing The Product Menu](/docs/7-0/tutorials/-/knowledge_base/t/customizing-the-product-menu)で説明したように、パネルアプリはポートレットに関連付ける必要があります。これにより、ユーザーがポートレットを表示する権限を持っている場合にのみ、パネルアプリが表示されます。
このパネルアプリは、次のメソッドを使用してシミュレーションデバイスポートレットに関連付けられます。

       @Override
       public String getPortletId() {
       return ProductNavigationSimulationPortletKeys.
       PRODUCT_NAVIGATION_SIMULATION;
       }
       
       @Override
       @Reference(
       target = "(javax.portlet.name=" + ProductNavigationSimulationPortletKeys.PRODUCT_NAVIGATION_SIMULATION + ")",
       unbind = "-"
       )
       public void setPortlet(Portlet portlet) {
       super.setPortlet(portlet);
       }
   
   対象ユーザー設定は、シミュレーションメニューを拡張する方法の良い例でもあります。対象ユーザー設定アプリがデプロイされると、シミュレーションメニューが拡張され、対象ユーザー設定の[User Segments]と[Campaigns]が提供されます。シミュレーションメニューからキャンペーンおよびユーザーの特定のシナリオを直接シミュレートできます。パネルアプリクラスは`DevicePreviewPanelApp`に似ていますが、異なるポートレットとJSPを指す点が異なります。

   ![図2：対象ユーザー設定アプリは、シミュレーションメニューを拡張して、さまざまなユーザーとキャンペーンのビューをシミュレートできるようにします。](../../images/simulation-menu-at.png)

5. デバイスプレビューiFrameと相互作用して、シミュレーションオプションとデバイスシミュレーションオプションを組み合わせることができます。カスタムシミュレーションビューのJavaScriptの`aui:script`ブロックでデバイスプレビューフレームを取得するには、次のコードを使用できます。

       var iframe = A.one('#simulationDeviceIframe');
   
   次に、以下のようにデバイスプレビューフレームのURLを変更できます。

       iframe.setAttribute('src', newUrlWithCustomParameters);
   
これで、必要なパネルカテゴリとパネルアプリを拡張してシミュレーションメニューを変更し、 独自の[モジュールを作成](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)し、ニーズに最も有用になるようシミュレーションメニューをカスタマイズする方法がわかりました。
