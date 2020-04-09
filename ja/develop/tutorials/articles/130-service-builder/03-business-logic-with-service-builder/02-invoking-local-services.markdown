---
header-id: invoking-local-services
---

# ローカルサービスの呼び出し

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

サービスモジュールをデプロイすると、それらのサービスはコンテナで利用可能になります。 Service Builderは、ローカルおよびリモートサービスクラスをOSGi宣言サービス（DS）コンポーネントとして生成します。 これらのコンポーネントは他のDSコンポーネントからアクセスできるため、Webアプリケーションなどの他のコンポーネントから呼び出すことができます。 方法は次のとおりです。

1.  [ローカルサービスコンポーネントへの参照を追加します。](#step-1-reference-the-local-service-component)

2.  [コンポーネントのメソッドを呼び出します。](#step-2-call-the-components-methods)

[Basic Service Builder](/docs/7-1/reference/-/knowledge_base/r/service-builder-samples)という名前のBladeサンプルがあります。 `基本Web` モジュールには、ローカルサービスコンポーネントの参照を示す `ポートレット` サービスコンポーネントがあります。 このモジュールには、コンポーネントのメソッドを呼び出すJSPもあります。 最初のステップは、ローカルサービスコンポーネントオブジェクトへの参照を追加することです。

## 手順1：ローカルサービスコンポーネントを参照する

アプリケーションのService Builderで生成されたローカルサービスは、 `@Reference` アノテーション</a>を使用して、アプリケーションの他のDSコンポーネント（ `@Component`アノテーションが付けられたクラス）

注入できるDSコンポーネントです。 `basic-web` モジュールの `JSPPortlet` クラスは、DSコンポーネントとして `FooLocalService` ローカルサービスを参照する `Portlet` サービスコンポーネントです。</p> 

    @Reference
    private volatile FooLocalService _fooLocalService;
    

OSGiサービスレジストリは、サービス実装オブジェクトを、それを参照するクラスに接続します。 `JSPPortlet` サンプルクラスは、 `_fooLocalService` フィールドをvolatileとして宣言しますが、フィールドをvolatileにすることは完全にオプションです。

| **注**：サービスビルダが生成する `* LocalServiceImpl`、 `* ServiceImpl`、| `* PersistenceImpl`および `[ENTITY_NAME]サービスとしてのエンティティのImpl` クラス| Builder Spring Beans --- OSGi宣言サービスではありません。 | [Service Builder Spring Beansは、LiferayサービスおよびOSGiサービスを参照するために、 `@Reference` アノテーション以外の手段を使用する必要があります](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code)。

**重要：** `* LocalServiceImpl` オブジェクトを直接呼び出さないでください。 `* LocalService` サービスインターフェイスを介して間接的にのみ呼び出す必要があります。 OSGiサービスレジストリは、サービス実装オブジェクトをクラスに接続します。

`RenderRequest` 属性に関連付けることにより、サービスオブジェクトをJSPで使用可能にすることができます。 たとえば、 `JSPPortlet`の `render` メソッドは、 `FooLocalService` オブジェクトを `fooLocalService`という属性に関連付けます。

    @Override
    public void render(RenderRequest request, RenderResponse response)
        throws IOException, PortletException {
    
        //set service bean
        request.setAttribute("fooLocalService", getFooLocalService());
    
        super.render(request, response);
    }
    
    public FooLocalService getFooLocalService() {
        return _fooLocalService;
    }
    

JSPが `<portlet:defineObjects />` タグを宣言している場合、 `RenderRequest` 属性からサービスオブジェクトを取得できます。 たとえば、 `JSPPortlet`の `init.jsp` ファイルは、 `"fooLocalService"` 属性から `FooLocalService` オブジェクトを取得します。

    ...
    <%@
    page import="com.liferay.blade.samples.servicebuilder.service.FooLocalService" %>
    ...
    
    <liferay-theme:defineObjects />
    
    <portlet:defineObjects />
    
    <%
    ...
    
    //get service bean
    FooLocalService fooLocalService = (FooLocalService)request.getAttribute("fooLocalService");
    %>
    

上記の `init.jsp` を含むすべてのJSPは、 `fooLocalService` 変数を使用してローカルサービスコンポーネントのメソッドを呼び出すことができます。



## ステップ2：コンポーネントのメソッドを呼び出す

これでサービスコンポーネントオブジェクトができたので、Javaオブジェクトのメソッドと同じようにそのメソッドを呼び出すことができます。

`basic-web` サンプルモジュールの `view.jsp` および `edit_foo.jsp` ファイルには、前のセクションで示した `init.jsp` 含まれています。 したがって、サービスコンポーネントオブジェクトを参照する `fooLocalService` 変数にアクセスできます。 `view.jsp` ファイルは、 `Foo` インスタンスをリストするLiferay Search Containerでコンポーネントの `getFoosCount` メソッドと `getFoos` メソッドを使用します。

    <liferay-ui:search-container
        total="<%= fooLocalService.getFoosCount() %>"
    >
        <liferay-ui:search-container-results
            results="<%= fooLocalService.getFoos(searchContainer.getStart(), searchContainer.getEnd()) %>"
        />
        ...
    </liferay-ui:search-container>
    

`edit_foo.jsp` ファイルは、 `getFoo（long id）` を呼び出して、エンティティインスタンスのIDに基づいて `Foo` エンティティを取得します。

    long fooId = ParamUtil.getLong(request, "fooId");
    Foo foo = null;
    if (fooId > 0) {
        foo = fooLocalService.getFoo(fooId);
    }
    

| **重要：** MVCCが有効になっているサービスのサービスエンティティアップデート（例： `fooService.update（object）`）を呼び出すときは、必ず|トランザクションでそうします。 拒否されたトランザクションをUIに伝播して、ユーザーが|ハンドル。 詳細については、を参照してください| [マルチバージョン同時実行制御（MVCC）](/docs/7-1/tutorials/-/knowledge_base/t/defining-global-service-information#multiversion-concurrency-control-mvcc)

`@Reference` アノテーションを使用すると、アプリケーションのOSGi DSコンポーネント（ポートレットDSコンポーネントなど）に、アプリケーションのService Builderで生成されたローカルサービスコンポーネントのインスタンスを注入できます。 また、 `RenderRequest` 属性を介して、JSPにコンポーネントインスタンスへのアクセスを提供できます。



## 関連トピック

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)

[リモートサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)

[リモートサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)

[サービスセキュリティレイヤー](/docs/7-1/tutorials/-/knowledge_base/t/service-security-layers)

[Service Builderコードからのサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code)

[OSGiサービスと宣言型サービスによる依存性注入](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)
