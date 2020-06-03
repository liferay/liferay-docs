---
header-id: deleting-entities
---

# エンティティの削除

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

今、あなたはどのように知っている [ドキュメントとメディアエンティティの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-files-folders-and-shortcuts)、あなたはそれらを削除する方法を学ぶ必要があります。 *delete* 正確な意味は、選択したポータル構成と削除操作に依存することに注意してください。 これは、デフォルトで有効になっている [ごみ箱](/docs/7-1/user/-/knowledge_base/u/restoring-deleted-assets)使用して、削除されたアイテムを回復できるためです。 ただし、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)を介した削除は永続的です。 アイテムをごみ箱に送信するには、Capabilities APIを使用する必要があります。

チュートリアルのこのセクションでは、 `DLAppService` を使用して、ドキュメントおよびメディアライブラリからエンティティを削除する方法を示します。 このセクションの最後のチュートリアルでは、Capabilities APIを使用してエンティティをごみ箱に移動する方法を示します。
