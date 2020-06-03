---
header-id: mvc-render-command
---

# MVCレンダリングコマンド

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたはここにいる場合は、あなたが知っているの手段ということを `MVCRenderCommand`秒をするために使用されている [URLをレンダリングするポートレットへの対応](/docs/7-1/tutorials/-/knowledge_base/t/writing-controller-code#render-logic)、あなたはコマンドをレンダリングMVCを作成して使用する方法を知りたいです。 LiferayのMVCポートレットフレームワーク全般について知りたいだけの場合、その情報は [別の記事](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)ます。

最初に、ビューレイヤー</a> を

設定し、 [`<portlet:renderURL>`](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/renderURL.html) を使用してJSPにレンダリングURLを作成します。 たとえば、次のレンダーURLは、 `/ hello / edit_entry`という名前のMVCレンダーコマンドを呼び出します。 これにより、ユーザーは編集用のフォームを含むページに誘導されます。</p> 

    <portlet:renderURL var="editEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" />
        <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
    </portlet:renderURL>
    

`mvcRenderCommandName` という名前の `<portlet:param>` は、レンダリングURLを宣言します。 `entryId` という名前の `<portlet:param>` は、ポートレットレンダリングURLオブジェクトを保持する変数を宣言します。 その変数を、ボタンやメニュー項目などのUIコンポーネントに割り当てます。 ユーザーがUIコンポーネントをトリガーすると、レンダリングURLに一致する `* MVCRenderCommand` クラスがレンダリング要求と応答を処理します。

特定のポートレット表示URLが呼び出されたときに何をしたいですか？ [`MVCRenderCommand` インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html) を実装し、その `render` メソッドをオーバーライドすることにより、独自のロジックを実行してJSPをレンダリングできます。 以下のようないくつかの `* MVCRenderCommand`、常に同じJSPをレンダリングします。

    public class BlogsViewMVCRenderCommand implements MVCRenderCommand {
    
        @Override
        public String render(
            RenderRequest renderRequest, RenderResponse renderResponse) {
    
            return "/blogs/view.jsp";
        }
    
    }
    

その他 `* MVCRenderCommand`、条件に基づいてJSPをレンダリングします。

    @Override
    public String render(
        RenderRequest renderRequest, RenderResponse renderResponse)
        throws PortletException {
    
        try {
            ActionUtil.getEntry(renderRequest);
        }
        catch (Exception e) {
            if (e instanceof NoSuchEntryException ||
                e instanceof PrincipalException) {
    
                SessionErrors.add(renderRequest, e.getClass());
    
                return "/hello/error.jsp";
            }
            else {
                throw new PortletException(e);
            }
        }
    
        return "/hello/edit_entry.jsp";
    }
    

上記のメソッドで、 `ActionUtil.getEntry`呼び出しで例外がスローされない場合、メソッドは `/hello/edit_entry.jsp`レンダリングします。 `NoSuchEntryException` がスローされると、 `/hello/error.jsp`レンダリングされます。 他の例外がスローされた場合、メソッドはそれを `PortletException`として再スローします。

特定のレンダーURLに応答するには、 `MVCRenderCommand` は、これらのプロパティを指定するOSGi宣言サービスコンポーネント（@Componentアノテーション付き）である必要があります。

  - `javax.portlet.name`
  - `mvc.command.name`

これらの2つのプロパティの例を次に示します。



``` 
 "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
 "mvc.command.name=/hello/edit_entry"
```


この場合のポートレット名は、定数 `HelloWorldPortletKeys.HELLO_WORLD`によって定義されます。 `mvc.command.name` は `/ hello / edit_entry`に設定されています。これは、MVCレンダーコマンドがエントリの編集に関連していることを示しているようです。

コンポーネントは、 `MVCRenderCommand.class` サービスとしてOSGiランタイムに公開する必要もあります。 サンプルプロパティを指定し、 `MVCRenderCommand.class` サービスとして自身を公開する基本的なコンポーネントを次に示します。

    @Component(
        immediate = true,
        property = {
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
           "mvc.command.name=/hello/edit_entry"
        },
        service = MVCRenderCommand.class
    )
    public class EditEntryMVCRenderCommand implements MVCRenderCommand {
        ...
    }
    

`mvc.command.name` value `/ hello / edit_entry` は、前述の `portlet：renderURL`の `mvcRenderCommand` パラメーターの値と一致します。 そのレンダリングURLは、この `* MVCRenderCommand` クラスを呼び出します。 実際、 `mvcRenderCommand` が `/ hello / edit_entry` であるこのポートレット（`HelloWorldPortletKeys.HELLO_WORLD`）のJSPのレンダリングURLは、この `* MVCRenderCommand`呼び出します。

`MVCRenderCommand` を複数のポートレットに応答させるには、それらをポートレット名に割り当てられた `javax.portlet.name` プロパティとして `@Component` に追加します。 同様に、複数のレンダリングURLに応答させるには、それらを `mvc.command.name` プロパティとして追加します。 本当にワイルドに感じている場合は、次のように、同じコマンドコンポーネントで複数のポートレットと複数のコマンドURLを指定できます。

    @Component(
        immediate = true,
        property = {
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_MY_WORLD,
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
           "mvc.command.name=/hello/edit_super_entry",
           "mvc.command.name=/hello/edit_entry"
        },
        service = MVCRenderCommand.class
    )
    

ご覧のとおり、MVCレンダリングコマンドは実装が簡単で、複数のポートレットの複数のコマンド名に応答できます。



## 関連トピック

[MVCポートレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-mvc-portlet)

[MVCリソースコマンド](/docs/7-1/tutorials/-/knowledge_base/t/mvc-resource-command)

[MVCアクションコマンド](/docs/7-1/tutorials/-/knowledge_base/t/mvc-action-command)

[MVCコマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvc-commands)
