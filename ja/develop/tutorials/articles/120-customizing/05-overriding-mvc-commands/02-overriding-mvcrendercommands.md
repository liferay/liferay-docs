---
header-id: overriding-mvcrendercommand
---

# MVCRenderCommandのオーバーライド

[TOC levels=1-4]

LiferayのMVCフレームワークを使用し、`MVCRenderCommand`コンポーネントを公開するポートレットの[`MVCRenderCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)をオーバーライドできます。

たとえば、Liferayのブログアプリケーションには、次のコンポーネントを含む`EditEntryMVCRenderCommand`というクラスがあります。


    @Component(
    immediate = true,
    property = {
    "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
    "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
    "javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
    "mvc.command.name=/blogs/edit_entry"
    },
    service = MVCRenderCommand.class
    )

このMVCレンダリングコマンドは、MVCコマンドに名前を付けるレンダーURLを呼び出すことにより、`javax.portlet.name`パラメーターで指定された任意のポートレットから呼び出すことができます。

    <portlet:renderURL var="addEntryURL">
    <portlet:param name="mvcRenderCommandName" value="/blogs/edit_entry" />
    <portlet:param name="redirect" value="<%= viewEntriesURL %>" />
    </portlet:renderURL>

コマンドをオーバーライドするが、元のコンポーネントにリストされているポートレットすべてに適用するわけではない場合はどうしますか。オーバーライドコンポーネントで、オーバーライドを有効にするポートレットの`javax.portlet.name`をリストするだけです。
たとえば、ブログ管理ポートレット（Liferayのサイト管理セクションからアクセスできるブログアプリケーション）に対してのみ`/blogs/edit_entry` MVC renderコマンドをオーバーライドする場合、コンポーネントは次のようになります。

    @Component(
    immediate = true,
    property = {
    "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
    "mvc.command.name=/blogs/edit_entry",
    "service.ranking:Integer=100"
    },
    service = MVCRenderCommand.class
    )

リストされている最後のプロパティ`service.ranking`に注目してください。これは、複数のコンポーネントが同じプロパティを持つ同じサービスを登録している場合に、使用するサービスをOSGiランタイムに伝えるためのものです。ここで指定する整数の値が大きいほど、コンポーネントの重みが大きくなります。この場合、このプロパティのデフォルト値は`0`であるため、元のコンポーネントの代わりにオーバーライドコンポーネントが使用されます。

この後はご自由にカスタマイズ可能です。MVC renderコマンドは、次の目的のためにカスタマイズできます。

- [既存のMVC Renderコマンドにロジックを追加する](#adding-logic-to-an-existing-mvc-render-command)
- [新しいJSPにリダイレクトする](#redirecting-to-a-new-jsp)

既存のMVC renderコマンドにロジックを追加する方法から見ていきましょう。

## 既存のMVC Renderコマンドにロジックを追加する

[MVCコマンドの一般的な手順](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)に従って、MVC renderコマンドにロジックを追加できます。 
特にMVC renderコマンドの場合は、[`MVCRenderCommand`インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)を直接実装し、その`render`メソッドをオーバーライドする必要があります。

たとえば、次のカスタムMVC renderコマンドには、`render`メソッドにロジックを追加するためのプレースホルダー（つまり、コメント`//Do something here`）があります。

    public CustomEditEntryRenderCommand implements MVCRenderCommand {
    @Override
    public String render(RenderRequest renderRequest,
    RenderResponse renderResponse)
    throws PortletException {
    
    //Do something here
    
    return mvcRenderCommand.render(renderRequest, renderResponse);
    }
    
    @Reference(target =
    "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCRenderCommand)")
    protected MVCRenderCommand mvcRenderCommand;
    }

この例では、`MVCRenderCommand`の`EditEntryMVCRenderCommand`実装を参照しています。`render`メソッドでは、プレースホルダーを新しいロジックに置き換え、その`render`メソッドを呼び出して元の実装のロジックを呼び出します。

場合によっては、リクエストをまったく新しいJSPにリダイレクトする必要があります。 これは、カスタムMVC renderコマンドモジュールからも実行できます。

## 新しいJSPにリダイレクトする

`MVCRenderCommand`の`render`メソッドは、JSPパスを文字列として返します。 デフォルトでは、JSPは元のモジュールに存在する必要があるため、単にオーバーライドモジュールでカスタムJSPへのパスを指定することはできません。新しいモジュールのJSPにリダイレクトするには、定数[`MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH`クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderConstants.html)を使用して、メソッドで元のJSPへのディスパッチを完全にスキップする必要があります。
次に、独自のディスパッチプロセスを開始して、JSPパスにリクエストを送信する必要があります。実際にどのように見えるかを次に示します。

    public class CustomEditEntryMVCRenderCommand implements MVCRenderCommand {
    
    @Override
    public String render(
    RenderRequest renderRequest, RenderResponse renderResponse) throws
    PortletException {
    
    System.out.println("Rendering custom_edit_entry.jsp");
    
    RequestDispatcher requestDispatcher =
    servletContext.getRequestDispatcher("/custom_edit_entry.jsp");
    
    try {
    HttpServletRequest httpServletRequest =
    PortalUtil.getHttpServletRequest(renderRequest);
    HttpServletResponse httpServletResponse =
    PortalUtil.getHttpServletResponse(renderResponse);
    
    requestDispatcher.include
    (httpServletRequest, httpServletResponse);
    } catch (Exception e) {
    throw new PortletException
    ("Unable to include custom_edit_entry.jsp", e);
    }
    
    return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
    }
    
    @Reference(target = "(osgi.web.symbolicname=com.custom.code.web)")
    protected ServletContext servletContext;
    }

サーブレットコンテキストは、リクエストディスパッチャへのアクセスを提供します。サーブレットコンテキストがポートレット用に自動的に作成されます。 `bnd.bnd`ファイルに次の行を含めることにより、他のモジュール用に作成できます。

    Web-ContextPath: /custom-code-web

次の手順に従って、カスタムMVC renderコマンドでポートレットのサーブレットコンテキストを取得します。

1. `ServletContext`フィールドを追加します。

       protected ServletContext servletContext;
   
2. フィールドに`@Reference`アノテーションを追加し、ポートレットのモジュールでフィルタリングするようにアノテーションを設定します。慣例により、Liferayは、シンボル名が`.web`で終わるモジュールにポートレットを配置しています。たとえば、次のサーブレットコンテキスト参照は、シンボル名が`com.custom.code.web`のモジュールでフィルタリングします。

       @Reference(target = "(osgi.web.symbolicname=com.custom.code.web)")
       protected ServletContext servletContext;
   
次のように`render`メソッドを実装します。

1. モジュールのカスタムJSPへのリクエストディスパッチャを取得します。

       RequestDispatcher requestDispatcher =
       servletContext.getRequestDispatcher("/custom_edit_entry.jsp");
   
2. HTTPサーブレットのリクエストとレスポンスをリクエストディスパッチャに含めます。

       try {
       HttpServletRequest httpServletRequest =
       PortalUtil.getHttpServletRequest(renderRequest);
       HttpServletResponse httpServletResponse =
       PortalUtil.getHttpServletResponse(renderResponse);
       
       requestDispatcher.include
       (httpServletRequest, httpServletResponse);
       } catch (Exception e) {
       throw new PortletException
       ("Unable to include custom_edit_entry.jsp", e);
       }
   
3. 定数`MVC_PATH_VALUE_SKIP_DISPATCH`を介してリクエストディスパッチャを返します

       return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
   
モジュールをデプロイすると、[カスタム`MVCRenderCommand`コンポーネントの対象となるポートレット](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands#step-2-publish-as-a-component)が新しいJSPをレンダリングします。

## 関連トピック

[MVC Render Command](/docs/7-1/tutorials/-/knowledge_base/t/mvc-render-command)

[Adding Logic to MVC Commands](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)

[Converting StrutsActionWrappers to MVCCommands](/docs/7-1/tutorials/-/knowledge_base/t/converting-strutsactionwrappers-to-mvccommands)
