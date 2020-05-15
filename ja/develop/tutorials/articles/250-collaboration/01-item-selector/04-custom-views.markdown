---
header-id: creating-custom-item-selector-views
---

# カスタムアイテムセレクタービューの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アイテムセレクタのデフォルトの選択ビューは、アプリに必要なすべてを提供する場合があります。ただし、特定の状況では、カスタム選択ビューが必要です。 たとえば、ユーザーが外部の画像プロバイダーから画像を選択できるようにする場合は、カスタム選択ビューを作成する必要があります。 このチュートリアルの手順に従って、カスタム選択ビューを作成できます。 始める前に、選択ビューについてもう少し学習します。

アイテムセレクタが表示するビューは、ユーザーが選択しているエンティティのタイプによって決定されることに注意してください。 アイテムセレクタは、同じエンティティタイプの複数のビューをレンダリングすることもできます。 たとえば、ユーザーが画像を選択すると、いくつかの選択ビューが利用できます。 各選択ビューは、画像の場所に対応するUIのタブです。

![図1：エンティティタイプには複数の選択ビューを含めることができます。](../../../images/item-selector-tabs.png)

各選択ビューは、 `* ItemSelectorCriterion` クラスによって表されます。 図1のタブは、次の `* ItemSelectorCriterion`表されます`</p>

<ul>
<li><a href="@app-ref@/collaboration/latest/javadocs/com/liferay/blogs/item/selector/criterion/BlogsItemSelectorCriterion.html"><code>BlogsItemSelectorCriterion` クラス</a>：ブログ画像ビュー</li>
  - [`ImageItemSelectorCriterion` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html)：ドキュメントとメディアビュー
  - [`URLItemSelectorCriterion` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/url/criterion/URLItemSelectorCriterion.html)：URLビュー
  - [`UploadItemSelectorCriterion` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/upload/criterion/UploadItemSelectorCriterion.html)：画像ビューのアップロード</ul>

次の手順に従って、カスタム選択ビューを作成します。

1.  選択ビューのOSGiモジュールを構成します。
2.  選択ビューのクラスを実装します。
3.  選択ビューのマークアップを記述します。

## 選択ビューのOSGiモジュールの構成

選択ビューのモジュールを構成するには、次の手順に従います。

1.  これらの依存関係をモジュールの `build.gradle`追加します。
   
        dependencies {
                compileOnly group: "com.liferay", name: "com.liferay.item.selector.api", version: "2.0.0"
                compileOnly group: "com.liferay", name: "com.liferay.item.selector.criteria.api", version: "2.0.0"
                compileOnly group: "com.liferay.portal", name: "com.liferay.portal.impl", version: "2.0.0"
                compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
                compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
                compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
                compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
                compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
        }

2.  モジュールの情報を `bnd.bnd` ファイルに追加します。 たとえば、この構成では、 `My Custom View`というモジュールの情報を追加します。
   
        Bundle-Name: My Custom View
        Bundle-SymbolicName: com.liferay.docs.my.custom.view
        Bundle-Version: 1.0.0

3.  `Web-ContextPath` を `bnd.bnd` に追加して、モジュールのリソースを指すようにします。
   
        Include-Resource:\
                META-INF/resources=src/main/resources/META-INF/resources
        Web-ContextPath: /my-custom-view

    `Web-ContextPath`がない場合、モジュールはリソースの場所を認識しません。 `Include-Resource` ヘッダーは、モジュールのリソースの相対パスを指します。

モジュールが構成されたので、選択ビューのクラスを作成できます。

## 選択ビューのクラスの実装

新しい選択ビューを作成するには、最初に表示するエンティティの種類（画像、ビデオ、ユーザーなど）を知る必要があります。 これにより、使用する必要がある特定の `ItemSelectorCriterion` が決まります。 たとえば、画像の選択ビューでは `ImageItemSelectorCriterion`使用する必要があります。

また、エンティティの戻り値の種類（ユーザーがエンティティを選択したときにエンティティに期待する情報の種類）を知っている必要があります。 たとえば、選択したエンティティがそのURLを返す場合、戻りタイプに `URLItemSelectorReturnType` を使用します。

@product@のアプリで使用できる基準と返品タイプの完全なリストについては、リファレンスドキュメント [Item Selector Criterion and Return Types](/docs/7-1/reference/-/knowledge_base/r/item-selector-criterion-and-return-types)参照してください。

これらを決定したら、次の手順に従って選択ビューのクラスを作成します。

1.  [`ItemSelectorView` インターフェース](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html)を実装する `ItemSelectorView` コンポーネントクラスを作成します。 ビューがこのインターフェイスへの型引数として必要とする基準を使用します。 `@Component` アノテーションで、 `item.selector.view.order` プロパティを、同じ基準の他のセレクタービューと一緒に表示されるときに表示する順序に設定します。 この値が低いほど、ビューの優先度が高くなり、順序で表示が早くなります。

    たとえば、このセレクタビュークラスの例は画像用であるため、タイプ引数として [`ImageItemSelectorCriterion` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/image/criterion/ImageItemSelectorCriterion.html) を使用して `ItemSelectorView` を実装します。 `@Component` アノテーションは、 `item.selector.view.order` プロパティを `200` し、クラスを `ItemSelectorView` サービスとして登録します。
   
        @Component(
            property = {"item.selector.view.order:Integer=200"},
            service = ItemSelectorView.class
        )
        public class SampleItemSelectorView
            implements ItemSelectorView<ImageItemSelectorCriterion> {...

    基準の順序は、アプリの [`getItemSelectorURL` メソッド](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-)でも指定できることに注意してください。

2.  基準クラス、サーブレットコンテキスト、および戻り値の型の取得メソッドを作成します。 次の手順でこれらを使用します。
   
        @Override
        public Class<ImageItemSelectorCriterion> getItemSelectorCriterionClass() 
        {
            return ImageItemSelectorCriterion.class;
        }
       
        @Override            
        public ServletContext getServletContext() {
            return _servletContext;
        }
       
        @Override            
        public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
            return _supportedItemSelectorReturnTypes;
        }

    [`getSupportedItemSelectorReturnTypes` メソッド](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getSupportedItemSelectorReturnTypes--) は、 `ItemSelectorReturnType`のリストを返すことに注意してください。 後の手順でこのリストを作成して、選択ビューがサポートする戻り値の型を指定します。

3.  選択ビューのタイトル、検索オプション、表示設定を構成します。 次の方法でこれを行います。

      - [`getTitle`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#getTitle-java.util.Locale-)：アイテムセレクターダイアログに表示するタブのローカライズされたタイトルを返します。

      - [`isShowSearch（）`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isShowSearch--)：アイテムセレクタービューに検索フィールドを表示するかどうかを返します。

    | **注：** 検索を実装するには、このメソッドに対して `true` を返します。 | `renderHTML` メソッドは、次のセクションで説明しますが、ユーザーが| `search` パラメーターの値に基づいて検索を実行しました。 それから|ユーザーが検索したキーワードは、次のようにして取得できます。 |文字列キーワード= ParamUtil.getString（request、 "keywords"）;

      - [`isVisible（）`](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#isVisible-com.liferay.portal.kernel.theme.ThemeDisplay-)：アイテムセレクタービューが表示されるかどうかを返します。 ほとんどの場合、これを `true`に設定します。 このメソッドを使用して、ビューを無効にする条件ロジックを追加できます。

    `サンプルセレクター` 選択ビューの構成例を次に示します。

        @Override public String getTitle（Locale locale）{ return "Sample Selector"; }

        @Override public boolean isShowSearch（）{ falseを返します; }

        @Override public boolean isVisible（ThemeDisplay themeDisplay）{ trueを返す; }

4.  [`renderHTML` method](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorView.html#renderHTML-javax.servlet.ServletRequest-javax.servlet.ServletResponse-T-javax.portlet.PortletURL-java.lang.String-boolean-) を使用して、ビューのレンダリング設定を設定します。 このメソッドは、サーブレットのリクエストとレスポンスに加えて、次の引数を取ります。

      - `itemSelectorCriterion`：選択ビューを表示するには、 `* ItemSelectorCriterion` 必要です。
      - `portletURL`：アイテムセレクタの呼び出しに使用されるポートレットURL。
      - `itemSelectedEventName`：呼び出し元がリッスンするイベント名。 要素が選択されると、ビューはこの名前でJavaScriptイベントを起動します。
      - `search`：選択ビューが検索結果をレンダリングするタイミングを指定する検索ブール値。 ユーザーが検索を実行するとき、このブール値は `true`設定する必要があります。

    次に、ビューをレンダリングするJSPファイル（`sample.jsp`）を指す `renderHTML` メソッドの実装例を示します。 `itemSelectedEventName` は要求属性として渡されるため、ビューマークアップで使用できることに注意してください。 ビューマークアップは、 `ServletContext` メソッド `getRequestDispatcher`介して指定されます。 この例ではJSPを使用していますが、FreeMarkerなどの別の言語を使用してマークアップをレンダリングできます。

        @Override public void renderHTML（ ServletRequest request、ServletResponse response、 ImageItemSelectorCriterion itemSelectorCriterion、 PortletURL portletURL、String itemSelectedEventName、 boolean search ） throws IOException、ServletException {

       request.setAttribute(_ITEM_SELECTED_EVENT_NAME,
           itemSelectedEventName);
    
       ServletContext servletContext = getServletContext();
    
       RequestDispatcher requestDispatcher =
           servletContext.getRequestDispatcher("/sample.jsp");
    
       requestDispatcher.include(request, response);
        }

5.  `@Reference` アノテーションを使用して、 `setServletContext` メソッドのモジュールのクラスを参照します。 アノテーションで、 `ターゲット` パラメーターを使用して、サーブレットコンテキストで利用可能なサービスを指定します。 この例では、 `osgi.web.symbolicname` プロパティを使用して、 `com.liferay.selector.sample.web` クラスをデフォルト値として指定します。 また、 `unbind = _` パラメーターを使用して、このモジュールにunbindメソッドがないことを指定する必要があります。 メソッド本体で、サーブレットコンテキスト変数を設定するだけです。
   
        @Reference(
            target =
            "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
            unbind = "-"
        )
        public void setServletContext(ServletContext servletContext) {
            _servletContext = servletContext;
        }

6.  このビューがサポートする戻りタイプを使用して、ステップ2で参照した `_supportedItemSelectorReturnTypes` リストを定義します。 この例では、 [`URLItemSelectorReturnType` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/URLItemSelectorReturnType.html) および [`FileEntryItemSelectorReturnType` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/criteria/FileEntryItemSelectorReturnType.html) をサポートされている戻り値タイプのリストに追加します（必要に応じて、さらに戻り値タイプを使用できます）。 より多くの戻り値の型は、ビューがより再利用可能であることを意味します。 また、この例では、ファイルの下部にサーブレットコンテキスト変数を定義していることに注意してください。
   
        private static final List<ItemSelectorReturnType>
            _supportedItemSelectorReturnTypes =
            Collections.unmodifiableList(
                ListUtil.fromArray(
                    new ItemSelectorReturnType[] {
                        new FileEntryItemSelectorReturnType(),
                        new URLItemSelectorReturnType()
                    }));
       
         private ServletContext _servletContext;

ビュークラスの実際の例については、 [`SiteNavigationMenuItemItemSelectorView` クラス](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/site-navigation/site-navigation-item-selector-web/src/main/java/com/liferay/site/navigation/item/selector/web/internal/SiteNavigationMenuItemItemSelectorView.java)参照してください。

## ビューマークアップの作成

選択ビューのクラスを実装したので、ビューをレンダリングするマークアップを記述する必要があります。 作成する正確なマークアップは、アプリのニーズによって異なります。 また、taglib、AUIコンポーネント、または純粋なHTMLやJavaScriptを使用して作成できるため、個人的な好みにも依存します。 したがって、単純なアプリケーションであっても、標準または典型的なビューマークアップはありません。 とにかく、マークアップは2つの重要なことを行う必要があります。

  - ユーザーが選択するエンティティをレンダリングします。
  - エンティティが選択されると、JavaScript経由でアイテムセレクターの戻り値の型で指定された情報を渡します。

たとえば、前のセクションのサンプルビュークラスは、 `renderHTML` メソッドのリクエスト属性としてJavaScriptイベント名を渡します。 したがって、マークアップで次のイベント名を使用できます。

    Liferay.fire(
            `<%= {ITEM_SELECTED_EVENT_NAME} %>',
    
            {
                data:{
                    the-data-your-client-needs-according-to-the-return-type
                }
            }
    );

完全な実際の例については、 [`com.liferay.layout.item.selector.web`](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web) モジュールの [`layouts.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/layout/layout-item-selector-web/src/main/resources/META-INF/resources/layouts.jsp) ビューマークアップを参照してください。 Even though this example is for the previous version of @product@, it still applies to @product-ver@. 以下に、この `layouts.jsp` ファイルのウォークスルーを示します。

1.  この `layouts.jsp` ファイルは、最初にいくつかの変数を定義します。 `LayoutItemSelectorViewDisplayContext` は、基準とビューに関する追加情報を含むオプションのクラスであることに注意してください。
   
        <%
        LayoutItemSelectorViewDisplayContext layoutItemSelectorViewDisplayContext = 
            (LayoutItemSelectorViewDisplayContext)request.getAttribute(
            BaseLayoutsItemSelectorView.LAYOUT_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
       
        LayoutItemSelectorCriterion layoutItemSelectorCriterion = 
            layoutItemSelectorViewDisplayContext.getLayoutItemSelectorCriterion();
       
        Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), 
            portletDisplay.getId());
        %>

2.  このスニペットは、スタイリング用のCSSファイルをインポートし、ページの `<head>` に配置します。
   
        <liferay-util:html-top>
                <link href="<%= PortalUtil.getStaticResourceURL(
                request, application.getContextPath() + "/css/main.css", 
                portlet.getTimestamp()) 
                %>" rel="stylesheet" type="text/css" />
        </liferay-util:html-top>

    チュートリアル [Liferay Util Taglibの使用](/docs/7-0/tutorials/-/knowledge_base/t/using-the-liferay-util-taglib)`liferay-util` taglibsの使用の詳細を学ぶことができます。

3.  このスニペットは、レイアウトエンティティを表示するUIを作成します。 [`liferay-layout：layouts-tree` taglib](@platform-ref@/7.0-latest/taglibs/modules/apps/web-experience/layout/com.liferay.layout.taglib/liferay-layout/layouts-tree.html) と [Lexicon](https://liferay.github.io/clay/) デザイン言語を使用して、 [枚のカード](https://liferay.github.io/clay/content/cards/)を作成します。 <div class="container-fluid-1280 layouts-selector">
            <div class="card-horizontal main-content-card">
                    <div class="card-row card-row-padded">
                            <liferay-layout:layouts-tree
                                    checkContentDisplayPage="<%= layoutItemSelectorCriterion.isCheckDisplayPage() %>"
                                    draggableTree="<%= false %>"
                                    expandFirstNode="<%= true %>"
                                    groupId="<%= scopeGroupId %>"
                                    portletURL="<%= layoutItemSelectorViewDisplayContext.getEditLayoutURL() %>"
                                    privateLayout="<%= layoutItemSelectorViewDisplayContext.isPrivateLayout() %>"
                                    rootNodeName="<%= layoutItemSelectorViewDisplayContext.getRootNodeName() %>"
                                    saveState="<%= false %>"
                                    selectedLayoutIds="<%= layoutItemSelectorViewDisplayContext.getSelectedLayoutIds() %>"
                                    selPlid="<%= layoutItemSelectorViewDisplayContext.getSelPlid() %>"
                                    treeId="treeContainer"
                            />
                    </div>
            </div>
        </div>

    This renders the following UI:
    
    ![Figure 1: The Layouts Item Selector view uses Lexicon and Liferay Layout taglibs to create the UI.](../../../images/layouts-item-selector-view.png)

4.  `aui：script` この部分は、ページのパスを返します。
   
        <aui:script use="aui-base">
            var LString = A.Lang.String;
       
            var getChosenPagePath = function(node) {
                    var buffer = [];
       
                    if (A.instanceOf(node, A.TreeNode)) {
                            var labelText = LString.escapeHTML(node.get('labelEl').text());
       
                            buffer.push(labelText);
       
                            node.eachParent(
                                    function(treeNode) {
                                            var labelEl = treeNode.get('labelEl');
       
                                            if (labelEl) {
                                                    labelText = LString.escapeHTML(labelEl.text());
       
                                                    buffer.unshift(labelText);
                                            }
                                    }
                            );
                    }
       
                    return buffer.join(' > ');
            };

5.  次のスニペットは、レイアウト（エンティティ）が選択されたときに戻り型のデータを渡します。 `url` および `uuid` 変数は、レイアウトのURLまたはUUIDを取得することに注意してください。

    ``` 
        var setSelectedPage = function(event) {
                var disabled = true;

                var messageText = '<%= UnicodeLanguageUtil.get(request, "there-is-no-selected-page") %>';

                var lastSelectedNode = event.newVal;

                var labelEl = lastSelectedNode.get('labelEl');

                var link = labelEl.one('a');

                var url = link.attr('data-url');
                var uuid = link.attr('data-uuid');

                var data = {};

                if (link && url) {
                        disabled = false;

                        data.layoutpath = getChosenPagePath(lastSelectedNode);
    ```

6.  これは、戻り値の型情報がURLかUUIDかを確認します。 次に、JSONオブジェクトの `データ` 属性の値を適宜設定します。 最後の行は、エディターの `CKEditorFuncNum` をJSONオブジェクトの `data` 属性に追加します。

    ``` 
                        <c:choose>
                                <c:when test="<%= Objects.equals(layoutItemSelectorViewDisplayContext.getItemSelectorReturnTypeName(), URLItemSelectorReturnType.class.getName()) %>">
                                        data.value = url;
                                </c:when>
                                <c:when test="<%= Objects.equals(layoutItemSelectorViewDisplayContext.getItemSelectorReturnTypeName(), UUIDItemSelectorReturnType.class.getName()) %>">
                                        data.value = uuid;
                                </c:when>
                        </c:choose>
                }

                <c:if test="<%= Validator.isNotNull(layoutItemSelectorViewDisplayContext.getCkEditorFuncNum()) %>">
                        data.ckeditorfuncnum: <%= layoutItemSelectorViewDisplayContext.getCkEditorFuncNum() %>;
                </c:if>
    ```

    `data-url` および `data-uuid` 属性は、レイアウトアイテムセレクターのHTMLマークアップにあります。 Layouts Item SelectorのインスタンスのHTMLマークアップは次のとおりです。

    ![図3：URLおよびUUIDは、レイアウトアイテムセレクターのHTMLマークアップの <code>data-url</code> および <code>data-uuid</code> 属性で確認できます。](../../../images/layouts-item-selector-html.png)

7.  アイテムセレクターの戻り値の型で指定されたJavaScriptトリガーイベントが発生し、必要な戻り値の情報を含むデータJSONオブジェクトを渡します。

    ``` 
                Liferay.Util.getOpener().Liferay.fire(
                        '<%= layoutItemSelectorViewDisplayContext.getItemSelectedEventName() %>',
                        {
                                data: data
                        }
                );
        };
    ```

8.  最後に、レイアウトは選択したページに設定されます。

    ``` 
        var container = A.one('#<portlet:namespace />treeContainerOutput');

        if (container) {
                container.swallowEvent('click', true);

                var tree = container.getData('tree-view');

                tree.after('lastSelectedChange', setSelectedPage);
        }
    </aui:script>
    ```

新しい選択ビューは、定義した基準と戻り値のタイプを使用するすべてのポートレットでアイテムセレクターによって自動的にレンダリングされます。これらのポートレットでは何も変更されません。

すばらしいです\！ これで、アイテムセレクタのカスタムビューを作成する方法がわかりました。

## 関連トピック

[Item Selector APIのコンポーネントについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-item-selector-apis-components)

[アイテムセレクターを使用したエンティティの選択](/docs/7-1/tutorials/-/knowledge_base/t/selecting-entities-using-the-item-selector)

[カスタムアイテムセレクタエンティティの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-entities)
