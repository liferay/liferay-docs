---
header-id: clay-cards
---

# Clayカード

[TOC levels=1-4]

カードは視覚的にデータを表します。カードは、画像、ドキュメントライブラリ、ユーザープロファイルなどに使用します。カードには主に4つのタイプがあります。

- 画像カード
- ファイルカード
- ユーザーカード
- 横型カード

このチュートリアルでは、これらの各タイプについて説明します。

## 画像カード

画像カードは、画像/ドキュメントのギャラリーに使用されます。

画像カード：

    <clay:image-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    href="#1"
    imageAlt="thumbnail"
    imageSrc="https://images.unsplash.com/photo-1506976773555-b3da30a63b57"
    subtitle="Author Action"
    title="Madrid"
    />

![図1：画像カードには画像とドキュメントが表示されます。](../../../images/clay-taglib-image-card.png)

アイコン付きの画像カード：

    <clay:image-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    icon="camera"
    subtitle="Author Action"
    title="<%= SVG_FILE_TITLE %>"
    />

![図2：画像カードでは、画像の代わりにアイコンを表示することもできます。](../../../images/clay-taglib-image-card-icon.png)

空の画像カード：

    <clay:image-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    subtitle="Author Action"
    title="<%= SVG_FILE_TITLE %>"
    />

![図3：カードに何も表示されないようにすることもできます。](../../../images/clay-taglib-image-card-empty.png)

カードにはファイルの種類を含めることもできます。`filetype`属性を使用してファイルの種類を指定します。

    <clay:image-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    fileType="JPG"
    fileTypeStyle="danger"
    href="#1"
    imageAlt="thumbnail"
    imageSrc="https://images.unsplash.com/photo-1499310226026-b9d598980b90"
    subtitle="Author Action"
    title="California"
    />

![図4：カードにはファイルの種類を含めることもできます。](../../../images/clay-taglib-image-card-file-type.png)

ラベルをカードに追加するには、`labels`属性を含めます。

    <clay:image-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    fileType="JPG"
    fileTypeStyle="danger"
    href="#1"
    imageAlt="thumbnail"
    imageSrc="https://images.unsplash.com/photo-1503703294279-c83bdf7b4bf4"
    labels="<%= cardsDisplayContext.getLabels() %>"
    subtitle="Author Action"
    title="Beetle"
    />

![図5：カードにラベルを含めることができます。](../../../images/clay-taglib-image-card-icon-label.png)

カードを選択できるようにするには、`selectable`属性を含めます（チェックボックスを含める）。

    <clay:image-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    fileType="JPG"
    fileTypeStyle="danger"
    href="#1"
    imageAlt="thumbnail"
    imageSrc="https://images.unsplash.com/photo-1506020647804-b04ee956dc04"
    labels="<%= cardsDisplayContext.getLabels() %>"
    selectable="<%= true %>"
    selected="<%= true %>"
    subtitle="Author Action"
    title="Beetle"
    />

![図6：カードが選択可能になります。](../../../images/clay-taglib-image-card-icon-selectable.png)

## ファイルカード

ファイルカードには、ファイルの種類のアイコンが表示されます。これらは、画像ファイル以外のファイルの種類（PDF、MP3、DOCなど）を表します。

    <clay:file-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    fileType="MP3"
    fileTypeStyle="warning"
    labels="<%= cardsDisplayContext.getLabels() %>"
    labelStylesMap="<%= cardsDisplayContext.getLabelStylesMap() %>"
    selectable="<%= true %>"
    selected="<%= true %>"
    subtitle="Jimi Hendrix"
    title="<%= MP3_FILE_TITLE %>"
    />

![図7：ファイルカードにはファイルの種類を示すアイコンが表示されます。](../../../images/clay-taglib-file-card.png)

上記のように、オプションで`labelStylesMap`属性を使用して、複数のラベルの`HashMap`を渡すことができます。

次の例では、デフォルトのファイルアイコンの代わりにリスト`icon`を指定します。

    <clay:file-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    fileType="DOC"
    fileTypeStyle="info"
    icon="list"
    labels="<%= cardsDisplayContext.getLabels() %>"
    selectable="<%= true %>"
    selected="<%= true %>"
    subtitle="Paco de Lucia"
    title="<%= DOC_FILE_TITLE %>"
    />

| **注：**使用可能なLiferayアイコンの全リストは、[Clay CSS Webサイト](https://claycss.com/docs/components/icons-lexicon.html#clay-lexicon-icons)にあります。

## ユーザーカード

ユーザーカードには、ユーザープロフィール画像またはユーザーの名前または名前と姓のイニシャルが表示されます。

イニシャル付きのユーザーカード：

    <clay:user-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    initials="HS"
    name="User Name"
    subtitle="Latest Action"
    userColor="danger"
    />

![図8：ユーザーカードには、ユーザーのイニシャルを表示できます。](../../../images/clay-taglib-user-card-initial.png)

プロフィール画像付きのユーザーカード：

    <clay:user-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    disabled="<%= true %>"
    imageAlt="thumbnail"
    imageSrc="https://images.unsplash.com/photo-1502290822284-9538ef1f1291"
    name="User name"
    selectable="<%= true %>"
    selected="<%= true %>"
    subtitle="Latest Action"
    />

![図9：ユーザーカードにはプロフィール画像を表示することもできます。](../../../images/clay-taglib-user-card-profile-image.png)

## 横型カード

横型カードはフォルダーを表し、他のカードと同じ量の情報を持つことができます。主な違いとして、横型カードに必要なのはフォルダのアイコンだけなので、横型カードでは画像部分を削除できます。

    <clay:horizontal-card
    actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    selectable="<%= true %>"
    selected="<%= true %>"
    title="ReallySuperInsanelyJustIncrediblyLongAndTotallyNotPossibleWordButWeAreReallyTryingToCoverAllOurBasesHereJustInCaseSomeoneIsNutsAsPerUsual"
    />

![図10：横型カードはフォルダーの表示に適しています。](../../../images/clay-taglib-horizontal-card.png)

これで、UIにカードを使用してアプリに情報を表示する方法がわかりました。

## 関連トピック

[Clay Badges](/docs/7-1/tutorials/-/knowledge_base/t/clay-badges)

[Clay Labels and Links](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links)

[Clay Stickers](/docs/7-1/tutorials/-/knowledge_base/t/clay-stickers)
