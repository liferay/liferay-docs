---
header-id: using-the-dbstore
---

# DBStoreの使用

DBStoreを使用すると、@product@データベースにドキュメントとメディアファイルを保存できます。DBStoreの最大ファイル（BLOBとして保存される）サイズは1ギガバイトです。それよりも大きいファイルを保存するには、Simple File System StoreまたはAdvanced File System Storeを使用します。

DBStoreの設定手順は次のとおりです。

1. [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)フォルダの`portal-ext.properties`ファイルに、以下のプロパティを設定します。

       dl.store.impl=com.liferay.portal.store.db.DBStore
   
2. @product@を再起動します。

これでドキュメントとメディアは、DBStoreを介して@product@のデータベースを使用するようになります。
