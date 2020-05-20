---
header-id: writing-controller-code
---

# コントローラーコードの記述

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

MVCでは、コントローラーはフロントエンドから要求を受信し、バックエンドからデータをプルします。 これはトラフィックディレクタです。ユーザーに表示するためにデータを右側のフロントエンドビューに提供し、ユーザーがフロントエンドに入力したデータを取得して右側のバックエンドサービスに渡します。 このため、コントローラーはフロントエンドからの要求を処理する必要があり、ユーザーにデータを戻すための適切なフロントエンドビューを決定する必要があります。

コントローラーロジックに重くない小さなアプリケーション（いくつかのアクションメソッドのみ）がある場合、すべてのコントローラーコードを `-Portlet` クラスに入れることができます。 あなたはより多くの複雑なニーズを持っている場合にコントローラを壊す検討し、（複雑なアクションの多くは、ロジックを実装するためのレンダリング、または多分いくつかのリソース提供のコード） [コマンドクラスレンダリングMVC](/docs/7-1/tutorials/-/knowledge_base/t/mvc-render-command)、 [MVCアクションコマンドクラス](/docs/7-1/tutorials/-/knowledge_base/t/mvc-action-command)、および [MVCリソースのコマンドをクラス](/docs/7-1/tutorials/-/knowledge_base/t/mvc-resource-command)。 このチュートリアルでは、小規模なアプリケーション用のコントローラーロジックの実装について説明します。

このチュートリアルでは、 `-Portlet` クラスのすべてのコントローラーコードでLiferay MVCポートレットを実装する方法を学びます。 次のことが含まれます。

  - アクションメソッド
  - レンダリングロジック
  - 要求パラメーターと属性の設定と取得

アクションメソッドの作成から始めます。

## アクションメソッド

小さなアプリケーションがある場合は、最後の手順で作成したポートレットクラスにすべてのコントローラーロジックを実装できます。 単独でコントローラーとして機能できます。 アクションメソッドを使用してリクエストを処理します。 サンプルアクションメソッドは次のとおりです。

    public void addGuestbook(ActionRequest request, ActionResponse response)
            throws PortalException, SystemException {
    
        ServiceContext serviceContext = ServiceContextFactory.getInstance(
            Guestbook.class.getName(), request);
    
        String name = ParamUtil.getString(request, "name");
    
        try {
            _guestbookService.addGuestbook(serviceContext.getUserId(),
                    name, serviceContext);
    
            SessionMessages.add(request, "guestbookAdded");
    
        } catch (Exception e) {
            SessionErrors.add(request, e.getClass().getName());
    
            response.setRenderParameter("mvcPath",
                "/html/guestbook/edit_guestbook.jsp");
        }
    
    }

このアクションには1つのジョブがあります。サービスを呼び出してゲストブックを追加します。 この呼び出しが成功すると、メッセージ `"guestbookAdded"` が要求に関連付けられ、 [`SessionMessages` オブジェクト](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionMessages.html)追加されます。 例外がスローされると、キャッチされ、クラス名がリクエストに関連付けられて [`SessionErrors` オブジェクト](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/SessionErrors.html) 追加され、応答がrender `edit_guestbook.jsp`設定されます。 `mvcPath` レンダリングパラメーターの設定は、ユーザーにレンダリングする次のビューを示すLiferay `MVCPortlet` フレームワークの規則です。

アクションメソッドはユーザーアクションに応答しますが、レンダリングロジックはユーザーに表示するビューを決定します。 レンダリングロジックは次です。

## レンダリングロジック

レンダリングするビューをMVCポートレットが決定する方法は次のとおりです。 コンポーネントで設定した `init-param` プロパティに注意してください。

    "javax.portlet.init-param.template-path=/",
    "javax.portlet.init-param.view-template=/view.jsp",

`template-path` プロパティは、JSPファイルが存在する場所をMVCフレームワークに伝えます。 上記の例では、 `/` はJSPファイルがプロジェクトのルート `リソース` フォルダーにあることを意味します。 そのため、Liferayの標準フォルダー構造に従うことが重要です。 `view-template` プロパティは、デフォルトのレンダリングを `view.jsp`ます。

仮想Webモジュールのリソースフォルダーのパスは次のとおりです。

    docs.liferaymvc.web/src/main/resources/META-INF/resources

そのリソースフォルダーに基づいて、 `view.jsp` ファイルは

    docs.liferaymvc.web/src/main/resources/META-INF/resources/view.jsp

それがアプリケーションのデフォルトビューです。 ポートレットの `init` メソッド（たとえば、ポートレットの `MVCPortlet.init（）`のオーバーライド）が呼び出されると、指定した初期化パラメーターが読み取られ、デフォルトのJSPにレンダリングを指示するために使用されます。 コントローラー全体で、次のようにレンダリングパラメーター `mvcPath` 設定することにより、さまざまなビュー（JSPファイル）をレンダリングできます`</p>

<pre><code>actionResponse.setRenderParameter("mvcPath", "/error.jsp");
`</pre>

初期化パラメーターとレンダリングパラメーターを使用してレンダリングロジックを回避することは可能ですが、ほとんどの場合、ポートレットの `レンダリング` メソッドをオーバーライドします。 以下に例を示します。

    @Override
    public void render(RenderRequest renderRequest,
            RenderResponse renderResponse) throws PortletException, IOException {
    
        try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Guestbook.class.getName(), renderRequest);
    
            long groupId = serviceContext.getScopeGroupId();
    
            long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
    
            List<Guestbook> guestbooks = _guestbookService
                    .getGuestbooks(groupId);
    
            if (guestbooks.size() == 0) {
                Guestbook guestbook = _guestbookService.addGuestbook(
                        serviceContext.getUserId(), "Main", serviceContext);
    
                guestbookId = guestbook.getGuestbookId();
    
            }
    
            if (!(guestbookId > 0)) {
                guestbookId = guestbooks.get(0).getGuestbookId();
            }
    
            renderRequest.setAttribute("guestbookId", guestbookId);
    
        } catch (Exception e) {
    
            throw new PortletException(e);
        }
    
        super.render(renderRequest, renderResponse);
    
    }

このレンダリングロジックは、ユーザーに表示するデータをビューレイヤーに提供します。 上記の `render` メソッドは、表示するゲストブックのIDでレンダリング要求属性 `guestbookId` を設定します。 ゲストブックが存在する場合は、最初のものを選択します。 それ以外の場合は、ゲストブックを作成し、表示するように設定します。 最後に、このメソッドは、 `render` メソッドを介して、レンダリング要求とレンダリング応答オブジェクトを基本クラスに渡します。

| **注：** Service Builderサービスを呼び出す方法を知りたい| @product-ver@？ | [Service Builder Code](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code) からのサービスの呼び出し|助けられる。 つまり、いずれかの注釈を付けて、サービスへの参照を取得します。 @Reference</code> Declarative Servicesが `サービスタイプのフィールド|注釈。 | | @リファレンス| private GuestbookService \ _guestbookService; | |完了したら、サービスのメソッドを呼び出すことができます。 | | \ _guestbookService.addGuestbook（serviceContext.getUserId（）、 "Main"、| serviceContext）;</p>

<p spaces-before="0">ビューレイヤーに進む前に、次のセクションでは、コントローラーレイヤーとビューレイヤーの間で情報を渡す方法を示します。</p>

<h2 spaces-before="0">リクエストとレスポンスのパラメータと属性の設定と取得</h2>

<p spaces-before="0"><a href="@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html"><code>ParamUtil`</a> という便利なユーティリティクラスを使用して、 `ActionRequest` または `RenderRequest`からパラメーターを取得できます。

たとえば、JSPはアクションURLで `guestbookId` という名前のパラメーターを渡すことができます。

    <portlet:actionURL name="doSomething" var="doSomethingURL">
        <portlet:param name="guestbookId" 
                value="<%= String.valueOf(entry.getGuestbookId()) %>" />
    </portlet:actionURL>

`<portlet:actionURL>` タグの `name` 属性は、アクションURLを `doSomething`という名前のコントローラーアクションメソッドにマップします。 アクションURLをトリガーすると、コントローラーの対応するメソッドが呼び出されます。

この例で参照されるコントローラーの `doSomething` メソッドは、 `ActionRequest`から `guestbookId` パラメーター値を取得できます。

    long guestbookId = ParamUtil.getLong(actionRequest, "guestbookId");

ビューレイヤーに情報を渡すために、コントローラーコードは応答オブジェクトにレンダリングパラメーターを設定できます。

    actionResponse.setRenderParameter("mvcPath", "/error.jsp");

上記のコードは、 `mvcPath` というパラメーターをJSPパス `/error.jsp`ます。 これにより、コントローラーのrenderメソッドはユーザーをJSP `/error.jsp`にリダイレクトします。

コントローラクラスは、 `setAttribute` メソッドを使用して、応答オブジェクトに属性を設定することもできます。

    renderRequest.setAttribute("guestbookId", guestbookId);

JSPは、スクリプトレットでJavaコードを使用してリクエストオブジェクトと対話できます。

    <%
        long guestbookId = Long.valueOf((Long) renderRequest
                .getAttribute("guestbookId"));
    %>

ビューとコントローラーの間で情報をやり取りすることは重要ですが、ビューレイヤーにはそれ以上のものがあります。 次にビューレイヤーが表示されます。
