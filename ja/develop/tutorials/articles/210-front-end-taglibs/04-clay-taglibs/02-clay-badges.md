---
header-id: clay-badges
---

# Clayバッジ

[TOC levels=1-4]

バッジは、通知や新しい未読メッセージなどの重要な情報を強調するのに役立ちます。バッジには円形の境界線があり、番号を指定するためにのみ使用されます。このチュートリアルでは、アプリに追加できるさまざまな種類のClayバッジについて説明します。

## バッジの種類

次のバッジスタイルを使用できます。

プライマリバッジ：

    <div class="col-md-1">
    <clay:badge label="8" />
    
    <div>Primary</div>
    </div>

![図1：プライマリバッジは鮮やかな青色で、フォームのプライマリボタンのように注意を引きます。](../../../images/clay-taglib-badge-primary.png)

セカンダリバッジ：

    <div class="col-md-1">
    <clay:badge label="87" style="secondary" />
    
    <div>Secondary</div>
    </div>

![図2：セカンダリバッジは薄い灰色で、プライマリボタンよりも注目度は低くなります。](../../../images/clay-taglib-badge-secondary.png)

情報バッジ：

    <div class="col-md-1">
    <clay:badge label="91" style="info" />
    
    <div>Info</div>
    </div>

![図3：情報バッジは濃い青色で、一般情報に関連する数字を意味します。](../../../images/clay-taglib-badge-info.png)

エラーバッジ：

    <div class="col-md-1">
    <clay:badge label="130" style="danger" />
    
    <div>Error</div>
    </div>

![図4：エラーバッジには、エラーに関連する数字が表示されます。](../../../images/clay-taglib-badge-error.png)

成功バッジ：

    <div class="col-md-1">
    <clay:badge label="1111" style="success" />
    
    <div>Success</div>
    </div>

![図5：成功バッジには、成功したアクションに関連する数字が表示されます。](../../../images/clay-taglib-badge-success.png)

警告バッジ：

    <div class="col-md-1">
    <clay:badge label="21" style="warning" />
    
    <div>Warning</div>
    </div>

![図6：警告バッジには、対処する必要がある警告に関連する番号が表示されます。](../../../images/clay-taglib-badge-warning.png)

これで、バッジを使用してアプリの値を追跡する方法がわかりました。

## 関連トピック

[Clay Labels and Links](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links)

[Clay Cards](/docs/7-1/tutorials/-/knowledge_base/t/clay-cards)

[Clay Stickers](/docs/7-1/tutorials/-/knowledge_base/t/clay-stickers)
