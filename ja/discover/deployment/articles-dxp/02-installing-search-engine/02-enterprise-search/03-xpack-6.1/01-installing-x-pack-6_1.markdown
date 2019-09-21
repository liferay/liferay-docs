---
header-id:installing-x-pack-0
---

# X-Packをインストールする

X-Packのインストール手順は次のとおりです：

1. [Install X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)して自動的に必要な権限を付与するには（推奨）、各Elasticsearchノードで次のコマンドを実行します。



       bin/elasticsearch-plugin install x-pack --batch
   
   この`--batch`オプションは、X-Packに許可を与えるためのインストールプロンプトをバイパスします。

   ログ出力には、許可が与えられた詳細が示され` Installed x-pack`で完了します。

       -> Downloading x-pack from elastic
       [=================================================] 100%
       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       @     WARNING: plugin requires additional permissions     @
       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       * java.io.FilePermission \\.\pipe\* read,write
       * java.lang.RuntimePermission accessClassInPackage.com.sun.activation.registries
       * java.lang.RuntimePermission getClassLoader
       * java.lang.RuntimePermission setContextClassLoader
       * java.lang.RuntimePermission setFactory
       * java.net.SocketPermission * connect,accept,resolve
       * java.security.SecurityPermission createPolicy.JavaPolicy
       * java.security.SecurityPermission getPolicy
       * java.security.SecurityPermission putProviderProperty.BC
       * java.security.SecurityPermission setPolicy
       * java.util.PropertyPermission * read,write
       See http://docs.oracle.com/javase/8/docs/technotes/guides/security/permissions.html
       for descriptions of what these permissions allow and the associated risks.
       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       @        WARNING: plugin forks a native controller        @
       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       This plugin launches a native controller that is not subject to the Java
       security manager nor to system call filters.
       Elasticsearch keystore is required by plugin [x-pack], creating...
       -> Installed x-pack
   
   X-Packが必要とする許可についての詳細はこちらを参照してください：[here](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/installing-xpack-es.html)

2. Elasticsearchが自動インデックス作成を許可できるようになっていることを`elasticsearch.yml`ファイル内で確認してください。



       action.auto_create_index: false
   
   このプロパティはデフォルトで`true`となっています。 
[Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/docs-index_.html#index-creation)には、自動インデックス作成の詳細が記載されています。

3. @product@をシャットダウンしてElasticsearchを再起動してください。

必要に応じて、セキュリティや監視を設定してください。
