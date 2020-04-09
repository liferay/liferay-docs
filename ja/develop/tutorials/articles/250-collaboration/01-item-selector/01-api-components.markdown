---
header-id: understanding-the-item-selector-apis-components
---

# Item Selector APIのコンポーネントについて

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Item Selector APIを使用する前に、そのコンポーネントについて学習する必要があります。 アプリでAPIを活用しながら、これらのコンポーネントを使用します。

  - **選択ビュー：** これらはフレームワークの主要コンポーネントです。 それらは、異なるソースからの特定のタイプのエンティティを示します。 たとえば、画像を表示するように構成されたアイテムセレクターには、ドキュメントとメディア、サードパーティの画像プロバイダー、またはドラッグアンドドロップUIからの選択ビューが表示される場合があります。

  - **マークアップ：** 選択ビューをレンダリングするマークアップファイル。 選択したマークアップ言語に大きな柔軟性があります。 たとえば、JSP、FreeMarker、または純粋なHTMLとJavaScriptを使用できます。

  - **戻りタイプ：** ユーザーが選択したエンティティから返される情報のタイプを表すクラス。 たとえば、ユーザーが画像を選択し、選択した画像のURLを返す場合、URL戻り型クラスが必要です。 各戻り型クラスは、 [`ItemSelectorReturnType` インターフェイス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)実装する必要があります。 このようなクラスは、返されるデータに基づいて名前が付けられ、 `ItemSelectorReturnType`接尾辞が付き` 。 たとえば、URL戻り型クラスは <code>URLItemSelectorReturnType`です。

  - **基準：** ユーザーが選択したエンティティを表すクラス。 たとえば、ユーザーが画像を選択する場合、画像基準クラスが必要です。 各基準クラスは、 [`ItemSelectorCriterion` インターフェイス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html)実装する必要があります。 Criterionクラスは、それらが表すエンティティの名前が付けられ、接尾辞 `ItemSelectorCriterion`付けられ` 。 たとえば、画像の基準クラスは <code>ImageItemSelectorCriterion`です。

  - **基準ハンドラー：** 適切な選択ビューを取得するクラス。 各基準には基準ハンドラーが必要です。 Criterionハンドラクラスは、 [`BaseItemSelectorCriterionHandler` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/BaseItemSelectorCriterionHandler.html) を拡張し、基準のエンティティを型引数として使用します。 Criterionハンドラークラスは、基準のエンティティに基づいて名前が付けられ、接尾辞 `ItemSelectorCriterionHandler`付けられ` 。 たとえば、画像基準ハンドラークラスは <code>ImageItemSelectorCriterionHandler`です。 `BaseItemSelectorCriterionHandler<ImageItemSelectorCriterion>`拡張し 。</p></li>
</ul>

<p spaces-before="0">この図は、これらのコンポーネントがどのように相互作用して機能するAPIを形成するかを示しています。</p>

<p spaces-before="0"><img src="../../../images/item-selector-architecture.png" alt="図1：アイテムセレクタービュー（選択ビュー）は、戻り値のタイプと基準によって決定され、マークアップによってレンダリングされます。" /></p>

<h2 spaces-before="0">関連トピック</h2>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/selecting-entities-using-the-item-selector">アイテムセレクターを使用したエンティティの選択</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-entities">カスタムアイテムセレクタエンティティの作成</a></p>

<p spaces-before="0"><a href="/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-views">カスタムアイテムセレクタービューの作成</a></p>
