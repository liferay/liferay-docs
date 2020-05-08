---
header-id: overriding-mvcresourcecommand
---

# MVCResourceCommandのオーバーライド

[TOC levels=1-4]

Liferay MVC resourceコマンドに機能を追加する必要がある場合は、追加できます。Liferay MVCコマンドフレームワークは、MVC resourceコマンドのカスタマイズをサポートしています。[MVCコマンドにロジックを追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)プロセスに従い、`MVCRenderCommand`および`MVCActionCommand`で説明したものと同様です。留意すべきことがいくつかあります。

- コンポーネントで指定するサービスは`MVCResourceCommand.class`です

- `MVCRenderCommand`のオーバーライドと同様に、拡張する基本の実装クラスはありません。 [`MVCResourceCommand`インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)を自分で実装します。

- オリジナルへの参照を取得し、呼び出しを`serveResource`メソッドに返すことで、元の`MVCResourceCommand`のロジックにロジックを追加することにより、コードを元のコードから切り離したままにします。

       return mvcResourceCommand.serveResource(resourceRequest, resourceResponse);
   
次の例では、Liferayのログインポートレットの`login-web`モジュールから、`com.liferay.login.web.portlet.action.CaptchaMVCResourceCommand`の動作をオーバーライドします。コンソールに1行出力するだけで、元のロジック (アカウント作成画面用のCaptchaイメージを返す) を実行します。

    @Component(
    property = {
    "javax.portlet.name=" + LoginPortletKeys.LOGIN,
    "mvc.command.name=/login/captcha"
    },
    service = MVCResourceCommand.class
    )
    public class CustomCaptchaMVCResourceCommand implements MVCResourceCommand {
    
    @Override
    public boolean serveResource
    (ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
    
    System.out.println("Serving login captcha image");
    
    return mvcResourceCommand.serveResource(resourceRequest, resourceResponse);
    }
    
    @Reference(target =
    "(component.name=com.liferay.login.web.internal.portlet.action.CaptchaMVCResourceCommand)")
    protected MVCResourceCommand mvcResourceCommand;
    }

説明は以上です。アプリケーションのソースコードを所有していない場合でも、コンポーネントのクラス名がわかれば[MVCコマンドをオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvc-commands)できます。

## 関連トピック

[MVC Resource Command](/docs/7-1/tutorials/-/knowledge_base/t/mvc-resource-command)

[Adding Logic to MVC Commands](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)

[Overriding MVCRenderCommands](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand)
