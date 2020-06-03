---
header-id: making-urls-friendlier
---

# URLをより使いやすくする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

これは、これまでと変わらない2つのURLの物語です。 1つは自分でいっぱいで、常にすべてのパラメーター（ユーザーとSEOサービス）に、彼が持っているすべてのパラメーターを公然と表示することで、彼がどれだけ賢いかを見せたかったのです。 彼が出会った人々が彼に脅かされ、混乱していると言うことができたとき、彼は最も幸せでした。

    http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_blogs_web_portlet_BlogsAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_mvcRenderCommandName=%2Fblogs%2Fedit_entry&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_redirect=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F~%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_blogs_web_portlet_BlogsAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_mvcRenderCommandName%3D%252Fblogs%252Fview%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_orderBycol%3Dtitle%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_orderByType%3Dasc%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_entriesNavigation%3D%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_cur%3D1%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_delta%3D20&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_entryId=30836

もう1つは、まあ、フレンドリーです。 彼女はスマートに見えることにあまり関心がなく、やり取りした人にもっと関心があったので、彼女は彼女についての重要なことだけを共有しました。 彼女は派手で複雑に見える必要はありませんでした。 彼女は、出会ったすべてのユーザーとSEOサービスにシンプルで親切になることを目指しました。

    http://localhost:8080/web/guest/home/-/blogs/lunar-scavenger-hunt

アプリケーションをユーザーやSEOサービスに使いやすくしたい場合は、URLを使いやすくします。 結局のところ、数ステップしかかかりません。

## わかりやすいURLルートの作成

1.  まず、アプリケーションのWebモジュールに `routes.xml` ファイルを作成します。 Liferayのパターンは、 `src / main / resources / META-INF / friendly-url-routes /` フォルダーに配置します。

2.  次のように、フレンドリーURLが必要な数だけ `<route>` タグを使用して、フレンドリーURLルートを追加します。
   
        <?xml version="1.0"?>
        <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 7.1.0//EN" "http://www.liferay.com/dtd/liferay-friendly-url-routes_7_1_0.dtd">
       <routes> <route> <pattern></pattern> <implicit-parameter name="mvcRenderCommandName">/ blogs / view</implicit-parameter> <implicit-parameter name="p_p_lifecycle">0</implicit-parameter> <implicit-parameter name="p_p_state">普通の</implicit-parameter> </route> <route> <pattern>/ maximized</pattern> <implicit-parameter name="mvcRenderCommandName">/ blogs / view</implicit-parameter> <implicit-parameter name="p_p_lifecycle">0</implicit-parameter> <implicit-parameter name="p_p_state">最大化</implicit-parameter> </route> <route> <pattern>/ {entryId：\ d +}</pattern> <implicit-parameter name="categoryId"></implicit-parameter> <implicit-parameter name="mvcRenderCommandName">/ blogs / view_entry</implicit-parameter> <implicit-parameter name="p_p_lifecycle">0</implicit-parameter> <implicit-parameter name="p_p_state">普通の</implicit-parameter> <implicit-parameter name="tag"></implicit-parameter> </route> ... </routes>

`<pattern>` タグを使用して、生成されたURLに通常表示されるパラメーターのプレースホルダー値を定義します。 これは単なるマスクです。 恐ろしいURLはまだその下に潜んでいます。

`パターン` 値 `/ {エントリId：\ D +}` マッチ `/` に続く `エントリId` 一致変数 [Java正規表現](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html) `\ D +`---一つ以上の数字を。 例えば、URL `/エントリId`、 `ENTRYID` 値は `123` URL値の結果 `/123`パターンと一致します。

| **警告：** を確認してください `パターン` の値がスラッシュで終わっていない `/`。 A |末尾のスラッシュ文字は、リクエストが正しいことを識別できないようにします。ルート。

**重要：** ポートレットがインスタンス化可能である場合、 `パターン` 値で `インスタンスID` バリアントを使用する必要があります。 たとえば、開始値が `render-it`場合、次のパターンのいずれかを使用します。

    <pattern>/{userIdAndInstanceId}/render-it</pattern>

または

    <pattern>/{instanceId}/render-it</pattern>

または

    <pattern>/{p_p_id}/render-it</pattern>

`<implicit-parameter>` タグを使用して、URLで常に同じパラメーターを定義します。 たとえば、レンダーURLの場合、 `p_p_lifecycle` パラメーターは常に `0`であると確信できます。 これらのタイプの暗黙パラメーターを定義する必要はありませんが、定義しないとURLに表示されるため、ベストプラクティスです。

名前が `mvcRenderCommandName` の暗黙パラメーターは非常に重要です。 あなたはしている場合は [使用して `MVCPortlet` と `MVCRenderCommand` クラス](/docs/7-1/tutorials/-/knowledge_base/t/mvc-render-command)、そのパラメータがから来ている `mvc.command.name` 内のプロパティ `@Component` あなたの `MVCRenderCommand` 実装。 これにより、レンダリングされるページが決まります（たとえば、 `view.jsp`）。

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS, "mvc.command.name=/",
            "mvc.command.name=/blogs/view"
        },
        service = MVCRenderCommand.class
    )

[DTDファイル](@platform-ref@/7.1-latest/definitions/liferay-friendly-url-routes_7_1_0.dtd.html) は、 `routes.xml` ファイルを完全に定義します。

## わかりやすいURLマッパーの実装

`routes.xml` ファイルにURLをマッピングしたら、 [`FriendlyURLMapper` サービス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/FriendlyURLMapper.html)実装を提供する必要があります。 2つのプロパティを持つ `FriendlyURLMapper` サービスを指定するコンポーネントを作成します。

1.  `com.liferay.portlet.friendly-url-routes` プロパティは、 `routes.xml` ファイルへのパスを設定します。

2.  `javax.portlet.name` プロパティは、おそらく既に持っているので、ポートレットの名前を指定します。
   
        @Component(
            property = {
                "com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
                "javax.portlet.name=" + BlogsPortletKeys.BLOGS
            },
            service = FriendlyURLMapper.class
        )

その後、 `FriendlyURLMapper` サービスを実装します。 便宜上、 [`DefaultFriendlyURLMapper` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/DefaultFriendlyURLMapper.html) はデフォルトの実装を提供します。 `DefaultFriendlyURLMapper` を拡張する場合は、1つのメソッド `getMapping（）`のみをオーバーライドする必要があります。 フレンドリーURLの最初の部分を定義する文字列を返します。 アプリケーションにちなんで名前を付けるのは賢明です。 Liferayのブログアプリケーションの外観は次のとおりです。

    public class BlogsFriendlyURLMapper extends DefaultFriendlyURLMapper {
    
        @Override
        public String getMapping() {
            return _MAPPING;
        }
    
        private static final String _MAPPING = "blogs";
    
    }

ブログのすべての分かりやすいURLは、ここで設定された文字列で始まります（`ブログ`）。

## 使いやすいURL

これらの分かりやすいURLの1つを実際に見てみましょう。 ブログエントリを追加し、エントリのタイトルをクリックします。 URLを見てください：

    http://localhost:8080/web/guest/home/-/blogs/lunar-scavenger-hunt

フレンドリーURLマッパークラスで指定されているように、 `ブログ` は、URLのLiferay部分の後に来るフレンドリーURLの最初の部分です。 次の部分は、 `routes.xml`特定のURLルートによって決定されます。

    <route>
        <pattern>/{urlTitle}</pattern>
        <implicit-parameter name="categoryId"></implicit-parameter>
        <implicit-parameter name="mvcRenderCommandName">/blogs/view_entry</implicit-parameter>
        <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
        <implicit-parameter name="p_p_state">normal</implicit-parameter>
        <implicit-parameter name="tag"></implicit-parameter>
    </route>

`urlTitle` は、ブログ投稿のタイトルから生成されます。 これはすでにURLのパラメーターであるため（以下を参照）、フレンドリURLで使用できます。

    <portlet:renderURL var="viewEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
        <portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
    </portlet:renderURL>

レンダーURLが呼び出されると、フレンドリURLマッパーで定義された文字列がフレンドリURLルートファイルの `パターン` タグと連携し、嫌な、うぬぼれた、フレンドリーでないURLの代わりに、非常にフレンドリーなURLを実際に取得します同様にユーザーとSEOサービスによって。
