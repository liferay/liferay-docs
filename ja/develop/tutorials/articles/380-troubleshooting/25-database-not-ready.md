---
header-id: portal-failed-to-initialize-because-the-database-wasnt-ready
---

# データベースの準備ができていないため、@product@の初期化に失敗しました

[TOC levels=1-4]

データベースサーバーとアプリケーションサーバーを同時に起動すると、データベースの準備が整う前に@product@ がデータソースへの接続を試みる場合があります。
デフォルトでは、@product@はデータベースへの接続を再試行しません。 失敗するだけです。
しかし、この状況を回避する方法として、データベース接続の再試行があります。

1. [Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)フォルダに`portal-ext.properties`ファイルを作成します。

2. プロパティ`retry.jdbc.on.startup.max.retries`を、データソースへの接続を再試行する回数に設定します。

3. プロパティ`retry.jdbc.on.startup.delay`を、接続を再試行するまで待機する秒数に設定します。

最初に接続が成功しなかった場合、@product@は再試行設定を使用して再試行します。

## 関連トピック

[Connecting to JNDI Data Sources](/docs/7-1/tutorials/-/knowledge_base/t/connecting-to-data-sources-using-jndi)
