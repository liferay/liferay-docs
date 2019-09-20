# 全てのノードが同じ@product@データベースを指すように設定する[](id=point-all-nodes-to-the-same-database)

全ノードにLiferay DXPデータベース（またはデータベースクラスター）を指すデータソースが必要です。よって、Liferay DXPはバンドルに付属されているHQSLデータベースは使用できません。またデータベースサーバーはLiferay DXPが起動しているサーバーとは別のシステム上に配置する必要があります。

## Read-Writer型データベースの構成[](id=read-writer-database-configuration)

よりパフォーマンスを重視する場合は、Read-Writer型データベース設定を利用できます。この方法では、読み取り、書き込み専用と2種類のデータソースを割り当てることができます。これにより、データベースインフラストラクチャー1つは読み込み用、もう1つは書き込み用としてを2つに分割して最適化できます。Liferayがサポートする全てのデータベースはレプリケーションをサポートしているので、現在ご使用のデータベースのレプリケーション構造を引き続き利用して、データベースノードを同期することができます。

レプリケーション用にデータベースを構成します。

そして、`portal-ext.properties`ファイル内でRead-Writer型のデータベースを有効にします。

1. JNDIを使用していない場合は、デフォルトのデータベース接続プールのプロバイダを`dbcp`、`tomcat`または`c3po`に設定します。プロバイダHikariCPは読み書きの分割をサポートしていません。 
以下が設定例です:

       jdbc.default.liferay.pool.provider=dbcp
   
   ポータルJDBCのすべての設定プロパティは[ここ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#JDBC)に記載されています。


   JNDIを使用するには、手順3に進みます。


2. 次に、読み取り用と、書き込み用に個別のデータソースを設定します。

       jdbc.read.driverClassName=com.mysql.jdbc.Driver
       jdbc.read.url=jdbc:mysql://dbread.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
       jdbc.read.username=**your user name**
       jdbc.read.password=**your password**
       
       jdbc.write.driverClassName=com.mysql.jdbc.Driver
       jdbc.write.url=jdbc:mysql://dbreadwrite.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
       jdbc.write.username=**your user name**
       jdbc.write.password=**your password**
   
3. JDBCデータソースの代わりにJNDIを使用するには、手順2の`*.username`、`*.password`プロパティを自分のJNDIユーザー名とパスワードに設定し、次の追加プロパティを設定します。

       jdbc.read.jndi.name=**your read JNDI name**
       jdbc.write.jndi.name=**your read-write JNDI name**
   
4. これを設定して、`default`データソースの使用を避けます。

       counter.jdbc.prefix=jdbc.write.
   
   `dbcp`または`tomcat`データベース接続プールプロバイダを使用している場合は、次のように設定します。

       jdbc.default.validationQuery=
       jdbc.read.validationQuery=SELECT releaseId FROM Release_
       jdbc.write.validationQuery=SELECT releaseId FROM Release_
   
   これらの設定は問題[LPS-64624](https://issues.liferay.com/browse/LPS-64624)に関連しています。

5. `spring.configs`および`spring.infrastructure.configs`プロパティから、次のSpring設定ファイルのコメントを外して、読み書き可能データベースの設定を有効にします。

       spring.configs=\
       [..]
       META-INF/dynamic-data-source-spring.xml,\
       [..]
       
       spring.infrastructure.configs=\
       [..]
       META-INF/dynamic-data-source-infrastructure-spring.xml,\
       [..]
   
   Spring構成ポータルのプロパティーは[ここ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Spring)に記載されています。


次回Liferay DXPを起動するときには、定義した2つのデータソースが使用されます。
