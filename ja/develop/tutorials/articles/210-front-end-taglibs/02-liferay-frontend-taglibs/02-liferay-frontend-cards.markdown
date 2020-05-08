---
header-id: liferay-frontend-cards
---

# Liferayフロントエンドカード

[TOC levels=1-4]

比較したいデータがあり、画像の使用量が多い場合は、カードコンポーネントが役立ちます。カードは、最小限のコンパクトな形式でデータを視覚的に表します。カードは、画像、ドキュメントライブラリ、ユーザープロファイルなどに使用します。このチュートリアルで説明するカードには、主に4つのタイプがあります。

- 横型カード
- アイコンカード
- 縦型カード
- ユーザーカード

各カードの例を以下に示します。

## 横型カード

横型カードは、主にファイルやフォルダーなどのドキュメントを表示するために使用されます。構成例を以下に示します。

    <liferay-frontend:horizontal-card
    text="Documents"
    url="/docs/7-1/tutorials/-/knowledge_base/t/clay-icons"
    >
    <liferay-frontend:horizontal-card-col>
    <liferay-frontend:horizontal-card-icon
    icon="folder"
    />
    </liferay-frontend:horizontal-card-col>
    </liferay-frontend:horizontal-card>

![図1：横型カードは、ファイルやドキュメントの表示に最適です。](../../../images/liferay-frontend-taglib-cards-horizontal.png)

`<liferay-frontend:horizontal-card-icon>`タグは、その`icon`属性に[Clayアイコン](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)を使用します。

## アイコン縦型カード

名前が示すように、アイコン縦型カードは、アイコンを強調する縦型形式で情報を表示するカードです。これらのカードには、画像が関連付けられていないコンテンツが表示されます。代わりに、コンテンツのタイプを表すアイコンが表示されます。以下のサンプルスニペットは、Webコンテンツ記事の情報を表示しています。

    <liferay-frontend:icon-vertical-card
    cssClass="article-preview-content"
    icon="web-content"
    title="<%= title %>"
    >
    <liferay-frontend:vertical-card-sticker-bottom>
    <liferay-ui:user-portrait
    cssClass="sticker sticker-bottom"
    userId="<%= assetRenderer.getUserId() %>"
    />
    </liferay-frontend:vertical-card-sticker-bottom>
    
    <liferay-frontend:vertical-card-footer>
    <aui:workflow-status
    markupView="lexicon"
    showIcon="<%= false %>"
    showLabel="<%= false %>"
    status="<%= article.getStatus() %>"
    />
    </liferay-frontend:vertical-card-footer>
    </liferay-frontend:icon-vertical-card>

![図2：縦型アイコンカードは、Webコンテンツ記事などのエンティティ選択を表示するのに最適です。](../../../images/liferay-frontend-taglib-cards-icon-vertical.png)

## 縦型カード

縦型カードは、横型形式とは対照的に、縦型カード形式で情報を表示します。コンテンツに関連する画像（ブログヘッダー画像など）がある場合は、縦型カードを使用して画像を表示できます。関連付けられている画像がない場合は、代わりにアイコン縦型カードを使用して、コンテンツのタイプを表すことができます（PDFファイルなど）。以下の例は、画像プレビューが使用可能な場合のWebコンテンツ記事の縦型カードを表示しています。

    <liferay-frontend:vertical-card
    cssClass="article-preview-content"
    imageUrl="<%= articleImageURL %>"
    title="<%= title %>"
    >
    <liferay-frontend:vertical-card-sticker-bottom>
    <liferay-ui:user-portrait
    cssClass="sticker sticker-bottom"
    userId="<%= assetRenderer.getUserId() %>"
    />
    </liferay-frontend:vertical-card-sticker-bottom>
    
    <liferay-frontend:vertical-card-footer>
    <aui:workflow-status
    markupView="lexicon"
    showIcon="<%= false %>"
    showLabel="<%= false %>"
    status="<%= article.getStatus() %>"
    />
    </liferay-frontend:vertical-card-footer>
    </liferay-frontend:vertical-card>

![図3：縦型カードは、ファイルやドキュメントの表示に最適です。](../../../images/liferay-frontend-taglib-cards-vertical.png)

## HTML縦型カード

HTML縦型カードを使用すると、縦型カードのヘッダーにカスタムHTMLを表示できます。以下の例ではビデオを埋め込んでいます。

    <liferay-util:buffer var = "customThumbnailHtml">
    <div class="embed-responsive embed-responsive-16by9">
    <iframe class="embed-responsive-item"
    src="https://www.youtube.com/embed/8Bg9jPJpGOM?rel=0"
    allowfullscreen></iframe>
    </div>
    </liferay-util:buffer>
    
    <div class="container">
    <div class="row">
    <div class="col-md-4">
    <liferay-frontend:html-vertical-card
    html="<%= customThumbnailHtml %>"
    title="My Video"
    >
    </liferay-frontend:html-vertical-card>
    </div>
    </div>
    </div>

![図4：Html縦型カードを使用すると、カードのヘッダーにカスタムHTMLを表示できます。](../../../images/liferay-frontend-taglib-cards-html-vertical.png)

## ユーザー縦型カード

ユーザー縦型カードは、管理バーのアイコンビューにユーザープロファイルの選択を表示します。以下は、ユーザー管理ポートレットからのサンプルスニペットです。

    <liferay-frontend:user-vertical-card
    actionJsp="/membership_request_action.jsp"
    actionJspServletContext="<%= application %>"
    resultRow="<%= row %>"
    subtitle="<%= membershipRequestUser.getEmailAddress() %>"
    title="<%= HtmlUtil.escape(membershipRequestUser.getFullName()) %>"
    userId="<%= membershipRequest.getUserId() %>"
    >
    <liferay-frontend:vertical-card-header>
    <liferay-ui:message
    arguments="<%= LanguageUtil.getTimeDescription(
    request,
    System.currentTimeMillis() - membershipRequest.getCreateDate().getTime(),
    true) %>"
    key="x-ago"
    translateArguments="<%= false %>"
    />
    </liferay-frontend:vertical-card-header>
    </liferay-frontend:user-vertical-card>

![図5：ユーザー縦型カードは、ファイルやドキュメントの表示に最適です。](../../../images/liferay-frontend-taglib-cards-user-vertical.png)

## 関連トピック

[Liferay Front-end Add Menu](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-add-menu)

[Liferay Front-end Info Bar](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-info-bar)

[Liferay Front-end Management Bar](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-management-bar)
