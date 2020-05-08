---
header-id: clay-alerts
---

# Clayアラート

[TOC levels=1-4]

Clayアラートは、埋め込み型とストライプ型の2種類があります。このチュートリアルでは、両方の種類について説明し、それぞれについて例をいくつか示します。

## 埋め込みアラート

通常、埋め込みアラートはフォーム内で使用されます。アラートを含む要素は、埋め込まれたアラートの幅を決定します。埋め込みアラートの場合、クローズアクションは必要ありません。Clay taglibsを使用して、次の埋め込みアラートを作成できます。

危険アラート（埋め込み）：

    <clay:alert
    message="This is an error message."
    style="danger"
    title="Error"
    />

![図1：危険アラートは、ユーザーにエラーまたは問題を通知します。](../../../images/clay-taglib-alert-danger.png)

成功アラート（埋め込み）：

    <clay:alert
    message="This is a success message."
    style="success"
    title="Success"
    />

![図2：アクションが成功すると、成功アラートがユーザーに通知されます。](../../../images/clay-taglib-alert-success.png)

情報アラート（埋め込み）：

    <clay:alert
    message="This is an info message."
    title="Info"
    />

![図3：情報アラートは、一般的な情報をユーザーに表示します。](../../../images/clay-taglib-alert-info.png)

警告アラート（埋め込み）：

    <clay:alert
    message="This is a warning message."
    style="warning"
    title="Warning"
    />

![図4：警告アラートは、ユーザーに警告メッセージを表示します。](../../../images/clay-taglib-alert-warning.png)

## ストライプアラート

ストライプアラートは、最後のナビゲーション要素（ヘッダーまたはナビゲーションバー）の下に配置され、通常はアクションの*保存*時に表示され、サーバーから受信したアクションのステータスを伝えます。埋め込みアラートとは異なり、ストライプアラートにはクローズアクションが必要です。ストライプアラートは常にコンテナの全幅であり、その下のすべてのコンテンツをプッシュします。Clay taglibsを使用して、次のストライプアラートを作成できます。

危険アラート（ストライプ）：

    <clay:stripe
    message="This is an error message."
    style="danger"
    title="Error"
    />

![図5：危険ストライプアラートは、アクションが失敗したことをユーザーに通知します。](../../../images/clay-taglib-alert-danger-stripe.png)

成功アラート（ストライプ）：

    <clay:stripe
    message="This is a success message."
    style="success"
    title="Success"
    />

![図6：成功ストライプアラートは、アクションが正常に完了したことをユーザーに通知します。](../../../images/clay-taglib-alert-success-stripe.png)

情報アラート（ストライプ）：

    <clay:stripe
    message="This is an info message."
    title="Info"
    />

![図7：情報ストライプアラートには、ユーザーに対するアクションに関する一般情報が表示されます。](../../../images/clay-taglib-alert-info-stripe.png)

警告アラート（ストライプ）

    <clay:stripe
    message="This is a warning message."
    style="warning"
    title="Warning"
    />

![図8：警告ストライプアラートは、アクションについてユーザーに警告します。](../../../images/clay-taglib-alert-warning-stripe.png)

これで、ユーザーに警告する方法がわかりました。

## 関連トピック

[Clay Buttons](/docs/7-1/tutorials/-/knowledge_base/t/clay-buttons)

[Clay Form Elements](/docs/7-1/tutorials/-/knowledge_base/t/clay-form-elements)

[Clay Labels and Links](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links)
