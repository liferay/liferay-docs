---
header-id: applying-clay-patterns-to-navigation
---

# ナビゲーションへの粘土パターンの適用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このチュートリアルでは、アプリのナビゲーションでクレイパターンを活用して、ユーザーフレンドリにする方法について説明します。 `<clay:navigation-bar />` タグのおかげで、アプリのナビゲーションバーを更新してClayを使用するのは簡単です。 アプリを更新するには、次の手順に従います。

1.  必要なインポートをアプリの `init.jsp`追加します。
   
        // Import the clay tld file to be able to use the new tag
        <%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>
       
        // Import the NavigationItem utility class to create the items model
        <%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %>

2.  `frontend-taglib-clay` および `frontend.taglib.soy` モジュールの依存関係をアプリの `build.gradle` ファイルに追加します。
   
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", 
        version: "1.0.10"
       
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.clay", 
        version: "1.0.0"

3.  JSPビュー内に、ナビゲーション変数とポートレットURLを取得するJavaスクリプレットを追加します。 構成例を以下に示します。
   
        <%
        final String navigation = ParamUtil.getString(request, "navigation", 
        "entries");
       
        PortletURL portletURL = renderResponse.createRenderURL();
       
        portletURL.setParameter("mvcRenderCommandName", "/blogs/view");
        portletURL.setParameter("navigation", navigation);
        %>

4.  `<clay:navigation-bar />` タグをアプリに追加し、 `項目` 属性を使用してナビゲーション項目を指定します。 アプリが管理者向けの場合、ナビゲーションバーは暗いはずです。 これを行うには、 `反転` 属性を `true`ます。 アプリがライブサイトのインスタンスを対象としている場合、 `反転` 属性を `false`設定して、ナビゲーションバーを点灯したままにします。 管理アプリの構成例を以下に示します。
   
        <clay:navigation-bar
            inverted="<%= true %>"
            navigationItems="<%=
                new JSPNavigationItemList(pageContext) {
                    {
                        add(
                        navigationItem -> {
                            navigationItem.setActive(navigation.equals("entries"));
                            navigationItem.setHref(renderResponse.createRenderURL());
                            navigationItem.setLabel(LanguageUtil.get(request, "entries"));
                        });
       
                        add(
                        navigationItem -> {
                            navigationItem.setActive(navigation.equals("images"));
                            navigationItem.setHref(renderResponse.createRenderURL(), 
                  "navigation", "images");
                            navigationItem.setLabel(LanguageUtil.get(request, "images"));
                        });
                    }
                }
            %>"
        />

5.  選択したナビゲーション項目に適切なJSPを表示する条件ブロックを追加します。 ブログ管理ポートレットの構成例を以下に示します。
   
        <c:choose>
            <c:when test='<%= navigation.equals("entries") %>'>
                <liferay-util:include page="/blogs_admin/view_entries.jsp" 
            servletContext="<%= application %>" />
            </c:when>
            <c:otherwise>
                <liferay-util:include page="/blogs_admin/view_images.jsp" 
            servletContext="<%= application %>" />
            </c:otherwise>
        </c:choose>

ライブサイトナビゲーションバー：

![図1：ナビゲーションバーは、ライブサイトのアプリに対して明るいはずです。](../../../images/clay-patterns-navbar.png)

管理アプリのナビゲーションバー：

![図2：管理アプリではナビゲーションバーが暗い（反転している）はずです。](../../../images/clay-patterns-navbar-inverted.png)

甘い\！ これで、Clayを使用してナビゲーションバーのスタイルを設定する方法がわかりました。

## 関連トピック

[管理ツールバーの実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-management-toolbar)
