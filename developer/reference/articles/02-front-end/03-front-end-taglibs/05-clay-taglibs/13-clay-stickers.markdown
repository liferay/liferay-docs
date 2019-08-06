---
header-id: clay-stickers
---

# Clay Stickers

[TOC levels=1-4]

Whereas badges display numbers and labels display short information, stickers
are small visual indicators of the content (usually the content type). They
can include a small label or a Liferay icon, and they come in two shapes:
circle and square. 

Square sticker with label:

```html
<clay:sticker label="JPG" />
```

![Figure 1: You can include stickers in your apps.](../../../../images/clay-taglib-sticker-square-label.png)

Square sticker with icon:

```html
<clay:sticker icon="picture" />
```

![Figure 2: Stickers can include icons.](../../../../images/clay-taglib-sticker-square-icon.png)

Circle sticker:

```html
<clay:sticker label="JPG" shape="circle" />
```

![Figure 3: You can also have circle stickers.](../../../../images/clay-taglib-sticker-round.png)

Stickers can be positioned in any corner of a div. Indicate their position with 
the `position` attribute: `top-left`, `bottom-left`, `top-right`, or 
`bottom-right`:

```html
<div class="aspect-ratio">

	<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

	<clay:sticker label="PDF" position="top-left" style="danger" />
</div>
```

![Figure 4: You can specify the position of the sticker within a container.](../../../../images/clay-taglib-sticker-position.png)

Now you know how to use Clay stickers in your app!

## Related Topics

- [Clay Badges](/docs/7-2/reference/-/knowledge_base/r/clay-badges)
- [Clay Cards](/docs/7-2/reference/-/knowledge_base/r/clay-cards)
- [Clay Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons)
