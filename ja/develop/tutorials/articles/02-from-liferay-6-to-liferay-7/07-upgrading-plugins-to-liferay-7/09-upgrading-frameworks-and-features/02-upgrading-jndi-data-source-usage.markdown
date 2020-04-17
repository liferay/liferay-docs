---
header-id: upgrading-jndi-data-source-usage
---

# JNDIデータソース使用量のアップグレード

[TOC levels=1-4]

@product@のOSGi環境では、ポータルのクラスローダーを使用して、アプリケーションサーバーのJNDIクラスをロードする必要があります。 OSGiバンドルが@product@のクラスローダーを使用せずにJNDIデータソースに接続しようとすると、 `java.lang.ClassNotFoundException`という結果になります。

これを行う方法の詳細については、記事[Connecting to JNDI Data Sources](/docs/7-1/tutorials/-/knowledge_base/t/connecting-to-data-sources-using-jndi)を参照してください。
