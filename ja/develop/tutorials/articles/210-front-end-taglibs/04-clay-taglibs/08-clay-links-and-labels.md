---
header-id: clay-labels-and-links
---

# Clayラベルとリンク

[TOC levels=1-4]

Liferay Clay taglibは、アプリでラベルとリンクを作成するためのタグを提供します。
このチュートリアルでは、これらの両方のUI要素をアプリに追加する方法を説明します。

## ラベル

Liferay Clay taglibは、アプリにいくつかの異なるラベルを提供します。`clay:label`タグを使用し て、アプリにラベルを追加します。色分けされたラベル、削除可能なラベル、およびリンクを含むラベルを作成できます。以下のセクションでは、これらすべてのオプションについて説明します。

### 色分けされたラベル

Liferay Clayラベルには、4つの異なる色があります：情報を示す濃い青色、ステータスを示す薄い灰色、保留中を示すオレンジ色、拒否済みを示す赤色、承認済みを示す緑色です。

情報ラベルは濃い青色で、ステータスラベルよりも目立つため、一般的な情報を伝えるのに最適です。情報ラベルを使用するには、`style`属性を`info`に設定します。

    <clay:label label="Label text" style="info" />

![図1：情報ラベルは一般的な情報を伝えます。](../../../images/clay-taglib-label-info.png)

ステータスラベルは薄い灰色で、中間色のため、基本的な情報を伝えるのに最適です。ステータスラベルはデフォルトのラベルであるため、`style`属性は必要ありません。

    <clay:label label="Status" />

![図2：ステータスラベルは最も目立たず、基本的な情報を表示するのに最適です。](../../../images/clay-taglib-label-status.png)

警告ラベルはオレンジ色であり、その色から、警告メッセージを伝えるのに最適です。警告ラベルを使用するには、`style`属性を`warning`に設定します 。

    <clay:label label="Pending" style="warning" />

![図3：警告ラベルはユーザーに問題を通知しますが、アプリが破損することはありません。](../../../images/clay-taglib-label-warning.png)

危険ラベルは赤色で、何か問題が発生しているか、または故障したことを示します。危険ラベルを使用するには、`style`属性を`danger`に設定します。

    <clay:label label="Rejected" style="danger" />

![図4：危険ラベルは対処しなければならない緊急事態を伝えます。](../../../images/clay-taglib-label-danger.png)

成功ラベルは緑色で、アクションが正常に完了したことを示します。
成功ラベルを使用するには、`style`属性を`success`に設定します。

    <clay:label label="Approved" style="success" />

![図5：成功ラベルは、成功したアクションを示します。](../../../images/clay-taglib-label-success.png)

ラベルは大きくすることもできます。大きなラベルを表示するには、`size`属性を`lg`に設定します。

    <clay:label label="Approved" size="lg" style="success" />

### 削除可能なラベル

ユーザーにラベルを閉じさせたい場合（一時的な通知など）、`closeable`属性を`true`に設定してラベルを削除可能にすることができます。

    <clay:label closeable="<%= true %>" label="Normal Label" />

![図6：ラベルは削除可能です。](../../../images/clay-taglib-label-removable.png)

### リンクを含むラベル

アンカータグと同じように`href`属性を追加して、ラベルをリンクにすることができます。

    <clay:label href="#" label="Label Text" />

![図7：ラベルはリンクにすることもできます。](../../../images/clay-taglib-label-link.png)

## リンク

`<clay:link>`タグを使用して、従来のハイパーリンクをアプリに追加できます。

    <clay:link href="#" label="link text" />

![図8：Clay taglibはリンク要素も提供します。](../../../images/clay-taglib-link.png)

これで、アプリにリンクとラベルを追加する方法がわかりました。

## 関連トピック

[Clay Badges](/docs/7-1/tutorials/-/knowledge_base/t/clay-badges)

[Clay Cards](/docs/7-1/tutorials/-/knowledge_base/t/clay-cards)

[Clay Form Elements](/docs/7-1/tutorials/-/knowledge_base/t/clay-form-elements)
