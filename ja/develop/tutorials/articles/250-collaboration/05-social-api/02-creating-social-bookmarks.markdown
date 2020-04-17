---
header-id: creating-social-bookmarks
---

# ソーシャルブックマークの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[ソーシャルブックマーク](/docs/7-1/tutorials/-/knowledge_base/t/applying-social-bookmarks) 適用すると、アプリのコンテンツを選択したソーシャルネットワークにリンクできます。 デフォルトでは、@product@はTwitter、Facebook、LinkedIn、およびGoogle Plusのソーシャルブックマークをサポートしています。 モジュール `com.liferay.social.bookmarks.api`から `SocialBookmark` インターフェースを実装するコンポーネントを登録することにより、独自のソーシャルブックマークを作成することもできます。 このチュートリアルでは、これを行う方法を示します。

## SocialBookmarkインターフェイスの実装

`SocialBookmark` インターフェイスを実装するには、次の手順に従います。

1.  `* SocialBookmark` クラスを作成し、 `social.bookmarks.type` プロパティを定義するコンポーネントを登録します。 このプロパティの値は、ソーシャルブックマークを使用するときに、 `liferay-social-bookmarks：bookmarks` タグの `type` 属性に入力した値です。

    たとえば、Twitterソーシャルブックマーククラスの定義は次のとおりです。
   
        @Component(immediate = true, property = "social.bookmarks.type=twitter")
        public class TwitterSocialBookmark implements SocialBookmark {...

2.  [`ResourceBundleLoader`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html) 参照を作成して、ソーシャルブックマークの名前のローカライズを支援します。
   
        @Reference(
                target = "(bundle.symbolic.name=com.liferay.social.bookmark.twitter)"
        )
        private ResourceBundleLoader _resourceBundleLoader;

3.  `getName` メソッドを実装して、ソーシャルブックマークの名前を `文字列`として返します。 このメソッドは、 [`LanguageUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/language/LanguageUtil.html) および [`ResourceBundle`](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html)介してローカライズに使用できる [`Locale`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) オブジェクトを受け取ります。
   
        @Override
        public String getName(Locale locale) {
            ResourceBundle resourceBundle = _resourceBundleLoader.loadResourceBundle(locale);
       
            return LanguageUtil.get(resourceBundle, "twitter");
        }

4.  `getPostURL` メソッドを実装して、共有URLを返します。 このメソッドは、タイトルとURLから共有URLを構築し、 [`URLCodec`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/URLCodec.html) を使用してURLのタイトルをエンコードします。
   
        @Override
        public String getPostURL(String title, String url) {
            return String.format(
                "https://twitter.com/intent/tweet?text=%s&tw_p=tweetbutton&url=%s", 
                URLCodec.encodeURL(title), url);
        }

5.  `ServletContext` 参照を作成します。
   
        @Reference(
                target = "(osgi.web.symbolicname=com.liferay.social.bookmark.twitter)"
        )
        private ServletContext _servletContext;

6.  インライン表示スタイルが選択されたときに呼び出される `render` メソッドを実装します。 通常、このメソッドは共有URLへのリンク（共有ボタンなど）をレンダリングしますが、必要なものに使用できます。 デフォルトのソーシャルブックマークで一貫したルックアンドフィールを維持するには、 [クレイアイコン](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)使用できます。

    この例では、Clayアイコン（`page.jsp`）を含むJSPの `RequestDispatcher` を取得し、そのJSPを応答に含めます。
   
        @Override
        public void render(
                        String target, String title, String url, HttpServletRequest request,
                        HttpServletResponse response)
                throws IOException, ServletException {
       
                RequestDispatcher requestDispatcher =
                        _servletContext.getRequestDispatcher("/page.jsp");
       
                requestDispatcher.include(request, response);
        }

次に、 `page.jsp` ファイルを作成する方法の例が表示されます。

## JSPを作成する

上記 `SocialBookmark` 実装で参照されている `page.jsp` ファイルは、 [クレイリンク](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links) （`clay：link`）を使用して、クレイに含まれるTwitterアイコンを指定およびスタイルします。 以下の手順に従って、独自のソーシャルブックマーク用のJSPを作成します。

1.  `クレイ` および `ライフライフテーマ` taglib宣言を追加します。
   
        <%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>
        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

2.  インポート [`GetterUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/GetterUtil.html) および `SocialBookmark`：
   
        <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
        <%@ page import="com.liferay.social.bookmarks.SocialBookmark" %>

3.  リクエストから、 `SocialBookmark` インスタンスとソーシャルブックマークのタイトルとURLを取得します。
   
        <%
        SocialBookmark socialBookmark = (SocialBookmark)request.getAttribute("liferay-social-bookmarks:bookmark:socialBookmark");
        String title = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:title"));
        String url = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:url"));
        %>

    タイトルとURLは、 [ソーシャルブックマーク](/docs/7-1/tutorials/-/knowledge_base/t/applying-social-bookmarks)適用するときに、 `liferay-social-bookmarks` タグライブラリを介して設定されます。

4.  Clayリンクを追加します。 この例では、次の `clay：link` 属性を設定します。

      - `buttonStyle`：この例では、 [ボタンのタイプ](/docs/7-1/tutorials/-/knowledge_base/t/clay-buttons#types) を2次ボタンとしてレンダリングします。

      - `elementClasses`：ボタンのスタイル設定に使用するカスタムCSS（オプション）。

      - `href`：ボタンのURL。 `SocialBookmark` インスタンスの `getPostURL` メソッドを呼び出して、これを指定する必要があります。

      - `icon`：ボタンのアイコン。 この例では、Clay（`twitter`）に含まれるTwitterアイコンを指定します。

      - `title`：ボタンのタイトル。 この例では、 `SocialBookmark` インスタンスの `getName` メソッドを使用します。
        
            <clay:link
                    buttonStyle="secondary"
                    elementClasses="btn-outline-borderless btn-sm lfr-portal-tooltip"
                    href="<%= socialBookmark.getPostURL(title, url) %>"
                    icon="twitter"
                    title="<%= socialBookmark.getName(locale) %>"
            />

ソーシャルブックマークの実装の実際の完全な例を確認するには、 [LiferayのTwitterソーシャルブックマークコード](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/social/social-bookmark-twitter)参照してください。

## 関連トピック

[ソーシャルブックマークの適用](/docs/7-1/tutorials/-/knowledge_base/t/applying-social-bookmarks)

[ポートレットでClay Taglibを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-the-clay-taglib-in-your-portlets)
