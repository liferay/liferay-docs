---
header-id: clay-stickers
---

# Clayステッカー

[TOC levels=1-4]

バッジは数字を表示し、ラベルは短い情報を表示するのに対し、ステッカーはコンテンツ（通常はコンテンツタイプ）の小さな視覚的インジケーターです。ステッカーには小さなラベルまたはLiferayアイコンを含めることができ、円形と正方形の2つの形状があります。

ラベル付きの正方形ステッカー：

    <clay:sticker label="JPG" />

![図1：アプリにステッカーを含めることができます。](../../../images/clay-taglib-sticker-square-label.png)

アイコン付きの正方形ステッカー：

    <clay:sticker icon="picture" />

![図2：ステッカーにはアイコンを含めることができます。](../../../images/clay-taglib-sticker-square-icon.png)

円形ステッカー：

    <clay:sticker label="JPG" shape="circle" />

![図3：円形ステッカーを使用することもできます。](../../../images/clay-taglib-sticker-round.png)

ステッカーは、divの任意のコーナーに配置できます。`position`属性`top-left`、`bottom-left`、`top-right`、または`bottom-right`を使用して位置を示します。

    <div class="aspect-ratio">
    
    <img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />
    
    <clay:sticker label="PDF" position="top-left" style="danger" />
    </div>

![図4：コンテナ内のステッカーの位置を指定できます。](../../../images/clay-taglib-sticker-position.png)

これで、アプリでClayステッカーを使用する方法がわかりました。

## 関連トピック

[Clay Badges](/docs/7-1/tutorials/-/knowledge_base/t/clay-badges)

[Clay Cards](/docs/7-1/tutorials/-/knowledge_base/t/clay-cards)

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)
