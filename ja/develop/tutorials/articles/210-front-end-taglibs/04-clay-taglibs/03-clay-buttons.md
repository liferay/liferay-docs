---
header-id: clay-buttons
---

# Clayボタン

[TOC levels=1-4]

ボタンにはいくつかの種類とバリエーションがあります。このチュートリアルでは、Clay taglibで作成できるボタンのさまざまなスタイルとバリエーションについて説明します。

## 種類

**プライマリボタン：**最も重要なアクションに使用されます。2つのプライマリボタンを一緒にしたり、互いに近い位置に配置することはできません。

ラベル付きのプライマリボタン：

    <clay:button label="Primary" />

![図1：プライマリボタンは鮮やかな青色で、ユーザーの注意を引きます。](../../../images/clay-taglib-button-primary.png)

**セカンダリボタン：**二次アクションに使用されます。複数のセカンダリボタンを一緒に、または互いに近くに配置できます。

    <div class="col">
    <clay:button label="Secondary" style="secondary" />
    </div>
    <div class="col">
    <clay:button ariaLabel="Wiki" icon="wiki" style="secondary" />
    </div>

![図2：セカンダリボタンは、プライマリボタンよりも注意を引くものではなく、二次アクション向けです。](../../../images/clay-taglib-button-secondary.png)

**縁なしボタン：** ツールバーなど、セカンダリボタンがデザインに対して重すぎる場合に使用します。これにより、デザインがすっきりします。

    <div class="col">
    <clay:button label="Borderless" style="borderless" />
    </div>
    <div class="col">
    <clay:button ariaLabel="Page Template" icon="page-template" style="borderless" />
    </div>

![図3：縁なしボタンでは、ボタンから黒い輪郭線が削除されます。](../../../images/clay-taglib-button-borderless.png)

**リンクボタン：** キャンセルアクションに使用されます。

    <div class="col">
    <clay:button label="Link" style="link" />
    </div>
    <div class="col">
    <clay:button ariaLabel="Add Role" icon="add-role" style="link" />
    </div>

![図4：ボタンをリンクに変えることもできます。](../../../images/clay-taglib-button-link.png)

ボタンにラベルまたはアイコンを使用できます。以下は、アイコン付きのプライマリボタンの例です。

    <clay:button ariaLabel="Workflow" icon="workflow" />

![図5：ボタンにはアイコンも表示できます。](../../../images/clay-taglib-button-primary-icon.png)

`disabled`属性を追加することで、ボタンを無効にできます。

    <div class="col">
    <clay:button disabled="<%= true %>" label="Primary" />
    </div>
    <div class="col">
    <clay:button ariaLabel="Workflow" disabled="<%= true %>" icon="workflow" />
    </div>

![図6：ユーザーにボタンを操作してほしくない場合は、ボタンを無効にできます。](../../../images/clay-taglib-button-primary-disabled.png)

## バリエーション

アイコンとテキストを含むボタン：

    <clay:button icon="share" label="Share" />

![図7：ボタンにはアイコンとテキストの両方を表示できます。](../../../images/clay-taglib-button-icon-text.png)

等幅テキストを含むボタン：

    <clay:button icon="indent-less" monospaced="<%= true %>" style="secondary" />

![図8：ボタンには等幅テキストを表示できます。](../../../images/clay-taglib-button-monospaced.png)

ブロックレベルボタン：

    <clay:button block="<%= true %>" label="Button" />

![図9：ブロックレベルボタンは、コンテナの幅全体に広がります。](../../../images/clay-taglib-button-block-level.png)

プラスボタン：

    <clay:button icon="plus" monospaced="<%= true %>" style="secondary" />

![図10：プラスボタンは、アプリにアクションを追加する際に使用します。](../../../images/clay-taglib-button-plus.png)

アクションボタン：

    <clay:button icon="ellipsis-v" monospaced="<%= true %>" style="borderless" />

![図11：アクションボタンは、アクションメニューを表示するために使用されます。](../../../images/clay-taglib-button-action.png)

## 関連トピック

[Clay Alerts](/docs/7-1/tutorials/-/knowledge_base/t/clay-alerts)

[Clay Buttons](/docs/7-1/tutorials/-/knowledge_base/t/clay-buttons)

[Clay Labels and Links](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links)
