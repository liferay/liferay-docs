---
header-id: mvc-resource-command
---

# MVCリソースコマンド

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

LiferayのMVCフレームワークを使用する場合、JSPでリソースURLを作成して、Liferayインスタンスから画像、XML、またはその他の種類のリソースを取得できます。 次に、リソースURLは、リソースの要求と応答を処理する対応するMVCリソースコマンドクラス（`* MVCResourceCommand`）を呼び出します。

最初に、ビューレイヤー</a> を

設定し、 [`<portlet:resourceURL>` タグ](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/resourceURL.html) を使用してJSPにリソースURLを作成します。 たとえば、ログインポートレットの `/login-web/src/main/resources/META-INF/resources/navigation/create_account.jsp` ファイルは、アカウント作成中にCAPTCHAイメージを取得するための次のリソースURLを定義します。</p> 

    <portlet:resourceURL id="/login/captcha" var="captchaURL" />
    

`id` 属性は、リソースURLを宣言します。 `var` 属性は、ポートレットリソースURLオブジェクトを保持する変数を宣言します。 その変数を、ボタンやアイコンなどのUIコンポーネントに割り当てます。 ユーザーがUIコンポーネントをトリガーすると、リソースURLに一致する `* MVCResourceCommand` クラスがリソースの要求と応答を処理します。 このクラスを作成するには、 [`MVCResourceCommand` インターフェース](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html) 実装するか、 [`BaseMVCResourceCommand` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCResourceCommand.html)拡張します。 後者はすでに `MVCResourceCommand`実装しているため、時間を節約できます。

また、処理するリソースに基づいて `* MVCResourceCommand` クラスに名前を付け、 `MVCResourceCommand`接尾辞を付けることをお勧めします。 たとえば、ログインポートレットの前述のCAPTCHAリソースURLと一致するリソースコマンドクラスは [`CaptchaMVCResourceCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/login/login-web/src/main/java/com/liferay/login/web/internal/portlet/action/CaptchaMVCResourceCommand.java)です。 複数のMVCコマンドクラスを持つアプリケーションでは、これはそれらを区別するのに役立ちます。

`* MVCResourceCommand` クラスには、次の例のように `@Component` アノテーションも必要です。 プロパティ `javax.portlet.name` をポートレットの内部IDに設定し、プロパティ `mvc.command.name` をJSPの一致する `resourceURL`の `id` プロパティの値に設定します。 `MVCResourceCommand` クラスを使用してOSGiコンテナーにコンポーネントを登録するには、 `サービス` プロパティを `MVCResourceCommand.class`設定する必要があります。

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=your_portlet_name_YourPortlet",
            "mvc.command.name=/your/jsp/resource/url"
        },
        service = MVCResourceCommand.class
    )
    public class YourMVCResourceCommand implements MVCResourceCommand {
        // your resource handling code
    }
    

実際の例として、ログインポートレットの `CaptchaMVCResourceCommand` クラスを検討してください（このモジュールは、 `モジュール/ apps / login / login-web / src / main / java / com / liferay / login / web /にあるLiferayソースコードで見つけてください。 internal / portlet / action / CaptchaMVCResourceCommand.java`）：

    @Component(
        property = {
            "javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
            "javax.portlet.name=" + LoginPortletKeys.LOGIN,
            "mvc.command.name=/login/captcha"
        },
        service = MVCResourceCommand.class
    )
    public class CaptchaMVCResourceCommand implements MVCResourceCommand {
    
        @Override
        public boolean serveResource(
            ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
    
            try {
                CaptchaUtil.serveImage(resourceRequest, resourceResponse);
    
                return false;
            }
            catch (Exception e) {
                _log.error(e, e);
    
                return true;
            }
        }
    
        private static final Log _log = LogFactoryUtil.getLog(
            CaptchaMVCResourceCommand.class);
    }
    

`@Component` アノテーションでは、 `javax.portlet.name` は2つの異なる設定があります。 これにより、複数のポートレットが同じコンポーネントを使用できます。 この例では、ポートレットIDは [`LoginPortletKeys` クラス](@app-ref@/foundation/7.0.8/javadocs/com/liferay/login/web/constants/LoginPortletKeys.html)定数として定義されています。 また、 `mvc.command.name` プロパティ設定 `/ login / captcha` は、このチュートリアルで前述した `resourceURL`の `id` 設定と一致し、 `service` プロパティは `MVCResourceCommand.classに設定されます。`。

`CaptchaMVCResourceCommand` クラスは、単一のメソッド `serveResource`のみで `MVCResourceCommand` インターフェースを実装します。 このメソッドは、それぞれ `javax.portlet.ResourceRequest` および `javax.portlet.ResourceResponse` パラメーターを介してリソース要求と応答を処理します。 `try` ブロックはヘルパークラス [`CaptchaUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/captcha/CaptchaUtil.html) を使用してCAPTCHAイメージを提供することに注意してください。 このようなヘルパークラスを作成する必要はありませんが、そうすることでコードを単純化できます。

すばらしいです\！ これで、 `MVCResourceCommand` を使用してLiferay MVCポートレットのリソースを処理する方法がわかりました。



## 関連トピック

[MVCポートレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-mvc-portlet)

[MVCレンダリングコマンド](/docs/7-1/tutorials/-/knowledge_base/t/mvc-render-command)

[MVCアクションコマンド](/docs/7-1/tutorials/-/knowledge_base/t/mvc-action-command)

[MVCコマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvc-commands)

[Liferay開発のOSGiの基本](/docs/7-1/tutorials/-/knowledge_base/t/osgi-basics-for-liferay-development)
