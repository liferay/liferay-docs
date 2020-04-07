---
header-id: deleting-entities
---

# エンティティの削除

[TOC levels=1-4]

今、あなたはどのように知っている [ドキュメントとメディアエンティティの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-files-folders-and-shortcuts)、あなたはそれらを削除する方法を学ぶ必要があります。 *delete* 正確な意味は、選択したポータル構成と削除操作に依存することに注意してください。 これは、デフォルトで有効になっている [ごみ箱](/docs/7-1/user/-/knowledge_base/u/restoring-deleted-assets)使用して、削除されたアイテムを回復できるためです。 ただし、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)を介した削除は永続的です。 アイテムをごみ箱に送信するには、Capabilities APIを使用する必要があります。

チュートリアルのこのセクションでは、 `DLAppService` を使用して、ドキュメントおよびメディアライブラリからエンティティを削除する方法を示します。 このセクションの最後のチュートリアルでは、Capabilities APIを使用してエンティティをごみ箱に移動する方法を示します。
