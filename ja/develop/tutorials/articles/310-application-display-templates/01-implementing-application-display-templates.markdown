---
header-id: implementing-application-display-templates
---

# アプリケーション表示テンプレートの実装

[TOC levels=1-4]

[アプリケーション表示テンプレート](/docs/7-1/user/-/knowledge_base/u/styling-widgets-with-application-display-templates)（ADT）を使用すると、ポータルからウィジェットにカスタム表示テンプレートを追加できます。 次の図は、ウィジェットの[設定]メニューの[Display Template]オプションがどのように見えるかを示しています。

![図1：カスタム表示テンプレートを使用して、ポートレットの表示をカスタマイズできます。](../../images/adt-dropdown.png)

このチュートリアルでは、アプリケーション表示テンプレートAPIを使用して、ADTをポートレットに追加する方法を学びます。

## アプリケーション表示テンプレートAPIの使用

ADT APIを活用するには、いくつかの手順を実行する必要があります。 これらの手順には、以下の内容が含まれます。

- ADTを使用するためのポートレットの登録
- 許可の定義
- ユーザーへのADT機能の公開

次にこれらの手順を順に説明します。

1. カスタム`*PortletDisplayTemplateHandler`コンポーネントを作成して登録します。 
Liferayは、拡張するための基本実装として[`BasePortletDisplayTemplateHandler`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portletdisplaytemplate/BasePortletDisplayTemplateHandler.html)を提供しています。[`TemplateHandler`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateHandler.html)インターフェイスのJavadocをチェックして、各テンプレートハンドラーメソッドについて学習できます。

   `@Component`アノテーションは、プロパティ`javax.portlet.name`をポートレットの名前に設定することにより、ハンドラーを特定のポートレットに関連付けます。同じプロパティがポートレットクラスにあります。 たとえば、

   ```java
   @Component(
    immediate = true,
    property = {
        "javax.portlet.name="+ AssetCategoriesNavigationPortletKeys.ASSET_CATEGORIES_NAVIGATION
    },
    service = TemplateHandler.class
)
   ```

   このクラスの各メソッドは、カスタムポートレットのADTを定義および実装する上で重要な役割を果たします。以下のリストは、ADT専用に定義されたメソッドの一部を強調表示しています。

   **getClassName()**：ポートレットがレンダリングするエントリのタイプを定義します。

   **getName()**：ADTタイプの名前（通常はポートレットの名前）を宣言します。

   **getResourceName()**：権限のチェックにADT（ポートレットなど）を使用しているリソースを指定します。このメソッドは、ポートレットの完全修飾ポートレットID（`com_liferay_wiki_web_portlet_WikiPortlet`など）を返す必要があります。

   **getTemplateVariableGroups()**：テンプレートエディターで公開される変数を定義します。

   `*PortletDisplayTemplateHandler`実装の例として、[`WikiPortletDisplayTemplateHandler`](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/template/WikiPortletDisplayTemplateHandler.java)クラスを検討できます。

2. ポートレットにはADTを追加する機能が新しく追加されたため、表示テンプレートの作成と管理を許可されるロールに管理ユーザーが権限を付与できるように、権限を構成する必要があります。アクションキー`ADD_PORTLET_DISPLAY_TEMPLATE`をポートレットの`/src/main/resources/resource-actions/default.xml`ファイルに追加します。

   ```xml
   <?xml version="1.0"?>
<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 7.1.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_1_0.dtd">
<resource-action-mapping>
    ...
    <portlet-resource>
        <portlet-name>yourportlet</portlet-name>
        <permissions>
            <supports>
                <action-key>ADD_PORTLET_DISPLAY_TEMPLATE</action-key>
                <action-key>ADD_TO_PAGE</action-key>
                <action-key>CONFIGURATION</action-key>
                <action-key>VIEW</action-key>
            </supports>
            ...
        </permissions>
    </portlet-resource>
    ...
</resource-action-mapping>
   ```

3. 次に、モジュールをデプロイするときに、@product@が新しいリソースアクションで更新された`default.xml`を検出できることを確認する必要があります。`/resources`フォルダに`portlet.properties`という名前のファイルを作成し、`default.xml`へのパスを提供する以下のコンテンツを追加します。

   ```properties
   include-and-override=portlet-ext.properties
resource.actions.configs=resource-actions/default.xml
   ```

4. ポートレットが公式にADTをサポートしているので、ユーザーにADTオプションを公開する必要があります。 ポートレットの構成を制御するために使用しているJSPファイルに`<liferay-ui:ddm-template-selector>`タグを含めます。

   たとえば、次のように構成JSPファイルに`<aui:fieldset>`を挿入すると役立つ場合があります。

   ```
   <aui:fieldset>
    <div class="display-template">
        <liferay-ddm:template-selector
            classNameId="<%= YourEntity.class.getName() %>"
            displayStyle="<%= displayStyle %>"
            displayStyleGroupId="<%= displayStyleGroupId %>"
            refreshURL="<%= PortalUtil.getCurrentURL(request) %>"
            showEmptyOption="<%= true %>"
        />
    </div>
</aui:fieldset>
   ```

   このJSPでは、`<liferay-ddm:template-selector>`タグは、ウィジェットの[設定]メニューで使用される[Display Template]ドロップダウンメニューを指定します。変数`displayStyle`および`displayStyleGroupId`は、このtaglibを使用し、かつポートレットで[BaseJSPSettingsConfigurationAction](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BaseJSPSettingsConfigurationAction.html)または[DefaultConfigurationAction](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.html)が使用される場合に、ポートレットが保存する設定です。
それ以外の場合は、これらのパラメーターの値を取得し、構成クラスに手動で保存する必要があります。

   JSPの例として、Wikiウィジェットの[`configuration.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/modules/apps/wiki/wiki-web/src/main/resources/META-INF/resources/wiki/configuration.jsp)を参照してください。

5. ここで、ビューコードを拡張して、選択したADTを使用してポートレットをレンダリングする必要があります。これにより、ビューのどの部分をADTでレンダリングするか、およびテンプレートコンテキストで使用できるものを決定できます。

   まず、ADTに必要なJava変数を初期化します。

   ```
   <%
String displayStyle = GetterUtil.getString(portletPreferences.getValue("displayStyle", StringPool.BLANK));
long displayStyleGroupId = GetterUtil.getLong(portletPreferences.getValue("displayStyleGroupId", null), scopeGroupId);
%>
   ```

   次に、ADTが構成されているか、レンダリングするエンティティを取得するか、ADTを使用してレンダリングするかを確認できます。このプロセスには、タグ`<liferay-ddm:template-renderer>`が役立ちます。選択したテンプレートが自動的に使用されるか、テンプレートが選択されていない場合はそのボディがレンダリングされます。

   このタグを実装したサンプルコードを以下に示します。

   ```
   <liferay-ddm:template-renderer
    className="<%= YourEntity.class.getName() %>"
    contextObjects="<%= contextObjects %>"
    displayStyle="<%= displayStyle %>"
    displayStyleGroupId="<%= displayStyleGroupId %>"
    entries="<%= yourEntities %>"
>

    <%-- The code that will be rendered by default when there is no
    template available should be inserted here. --%>

</liferay-ddm:template-renderer>
   ```

   この手順では、表示設定（`displayStyle`および`displayStyleGroupId`）を処理する変数を初期化し、以下にリストされている他のパラメーターとともにそれらをタグに渡しました。

   - `className`：エンティティのクラス名。
   - `contextObjects`：テンプレートコンテキストに対する任意のオブジェクトを含む`Map<String, Object>`を受け入れます。
   - `entries`：エンティティのリストを受け入れます（`List<YourEntity>`など）。
   実装例については、[`configuration.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/modules/apps/site-navigation/site-navigation-site-map-web/src/main/resources/META-INF/resources/configuration.jsp)を参照してください。

 ポートレットがADTをサポートするようになりました。スクリプトをポータルにアップロードして保存すると、指定したロールを持つユーザーは、ページ上のポートレットの表示設定を構成するときにテンプレートを選択できます。ADTの使用の詳細については、[Styling Widgets with Application Display Templates](/docs/7-1/user/-/knowledge_base/u/styling-widgets-with-application-display-templates)セクションをご覧ください。
