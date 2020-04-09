---
header-id: configuring-the-view-layer
---

# ビューレイヤーの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このセクションでは、インポートを1つのJSPファイルに整理することから、処理をポートレットクラスのメソッドに指示するURLを作成することまで、ビューレイヤーを機能させる方法について簡単に説明します。

|注：JSPを作成すると、次のことができます。 [クレイスタイルをアプリに適用して、Liferayのアプリ](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app)に一致させます。

Liferayのベストプラクティスでは、すべてのJavaインポート、タグライブラリ宣言、および変数の初期化を `init.jsp`というJSPに入れます。 [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) または [Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) を使用して `mvc-portlet` プロジェクトテンプレートに基づいてモジュールを作成すると、これらのtaglib宣言と初期化が `init.jsp`自動的に追加されます。

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
    
    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
    
    <liferay-theme:defineObjects />
    
    <portlet:defineObjects />

他のJSPに `init.jsp` を含めるようにしてください。

    <%@include file="/html/init.jsp"%>

JSPはアクションURLを使用してコントローラーメソッドを呼び出すことができます。 別のページへのリンクを作成するには、 `mvcPath` パラメーターでレンダリングURLを使用します。 [`<portlet:renderURL>` タグ](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/renderURL.html) はURLを構築し、変数に割り当てます。

    <portlet:renderURL var="searchURL">
            <portlet:param name="mvcPath" value="/admin/view.jsp" />
    </portlet:renderURL>

レンダリングURLは、 `var` 属性で指定された `searchURL` 変数に割り当てられます。 上記の [`<portlet:param>` タグ](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/param.html) JSPパス `/admin/view.jsp` をレンダリングパラメーター `mvcPath`割り当てます。 コントローラーの `render` メソッドは、 `mvcPath` パラメーターからJSPパスを取得して、次のJSPをレンダリングします。

    docs.liferaymvc.web/src/main/resources/META-INF/resources/admin/view.jsp

レンダリングURLを呼び出すには、その変数（ `<portlet:renderURL>`の `var` 属性に設定された変数）を、ボタンやナビゲーションバー項目などのUIコンポーネントのアクションに割り当てます。

アクションメソッドは、単に別のページにリンクするのではなく、アクション（つまりコード）を呼び出すため、異なります。 JSPで、 [`<portlet:actionURL>` タグ](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/actionURL.html) を使用してアクションURLを作成し、そのURLをUIコンポーネントのアクションとして割り当てます。 `doSomething`という名前のコントローラーメソッドを呼び出すアクションURLを次に示します。

    <portlet:actionURL name="doSomething" var="doSomethingURL">
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:actionURL>

`リダイレクト` という名前のポートレットパラメータは、ポートレットアクションの呼び出し後にリダイレクトするポートレットのJSPパスに割り当てられます。 このアクションURLは、 `doSomethingURL`という名前の変数に割り当てられます。 レンダリングURLと同様に、アクションURLの変数（ `var`設定された変数）によって、UIコンポーネントアクションにアクションURLを割り当てることができます。

これらの簡単な例は、Liferay MVCフレームワークが小規模なアプリケーションのビューレイヤーとコントローラー間の通信を容易にする方法を示しています。
