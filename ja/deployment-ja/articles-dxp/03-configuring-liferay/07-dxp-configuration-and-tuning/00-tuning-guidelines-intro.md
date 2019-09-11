# チューニングガイドライン[](id=tuning-guidelines)


設定名は異なる場合がありますが、これらの概念はほとんどのアプリケーションサーバーに適用されます。ここでは例としてTomcatを使用します。
他のアプリケーションサーバーの場合は、プロバイダのドキュメンテーションを参照しながら特定の設定を行なってください。

チューニングのトピックは次のとおりです：



- [データベース接続プール](#database-connection-pool)

- [JSPエンジンの開発設定の無効化](#deactivate-development-settings-in-the-jsp-engine)

- [スレッドプール](#thread-pool)

## データベース接続プール[](id=database-connection-pool)


データベース接続プールは、スレッドプールサイズの約30〜40％である必要があります。
そうすることにより、@product@がデータベースからデータを取得しなければいけない時、接続できるようになります。（例：ユーザーログイン）プールサイズが小さすぎる場合、データベース接続を待機しているサーバーにキューを要望してください。ただし、サイズが大きすぎると、アイドル状態のデータベース接続によってリソースが浪費されます。スレッドプールと同様に、これらの設定を監視しながらパフォーマンステストに基づいて調整します。

Tomcatでは、接続プールは以下のResource要素で設定されています： `$CATALINA_HOME/conf/Catalina/localhost/ROOT.xml`。Liferay エンジニアリングはこの設定方法でテストします：

    <Resource auth="Container"
    description="Digital Enterprise DB Connection"
    driverClass="com.mysql.jdbc.Driver"
    maxPoolSize="75"
    minPoolSize="10"
    acquireIncrement="5"
    name="jdbc/LiferayPool"
    user="XXXXXX"
    password="XXXXXXXXX"
    factory="org.apache.naming.factory.BeanFactory"
    type="com.mchange.v2.c3p0.ComboPooledDataSource"
    jdbcUrl="jdbc:mysql://someServer:3306/liferay_dxp?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false"/>

この設定方法は10スレッドから始まり、プール内で必要に応じて５ずつ、最大75接続まで増加します。


DBCP、C3P0、HikariCP、およびTomcatなど、さまざまなデータベース接続プールプロバイダーがあります。[`portal-ext.properties`ファイル](https://docs.liferay.com/ce/portal/7.1-latest/propertiesdoc/portal.properties.html)に Liferay JDBCを設定することもできます。

## JSPエンジンの開発設定の無効化[](id=deactivate-development-settings-in-the-jsp-engine)


多くのアプリケーションサーバーのJSPエンジンは、デフォルトで開発モードになっています。
作業を行う前にこれらの設定を無効にします。



**開発モード**：これをすることにより、JSPコンテナはJSPファイルへの変更に必要なファイルシステムをポーリングします。しかしJSPをその場で変更することはないので、このモードはオフにしてください。


**マップファイル：** JSPテキストの1行に1つのステートメントに対して1つのprintステートメントの静的なコンテンツを生成します。


たとえば、Tomcatでこれらを無効にするには、`$CATALINA_HOME/conf/web.xml`ファイルのJSPサーブレット設定を次のようにアップデートします：

    <servlet>
    <servlet-name>jsp</servlet-name>
    <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
    <init-param>
    <param-name>development</param-name>
    <param-value>false</param-value>
    </init-param>
    <init-param>
    <param-name>mappedFile</param-name>
    <param-value>false</param-value>
    </init-param>
    <load-on-startup>3</load-on-startup>
    </servlet>

開発モードとマップファイルは無効になります。

## スレッドプール[](id=thread-pool)


アプリケーションサーバーへの各リクエストは、そのリクエストの間のワーカースレッドを消費することになります。リクエストを処理するために使用できるスレッドがない場合、そのリクエストは次に使用可能なワーカースレッドを待つためのキューに入れられます。細かく調整されたシステムでは、スレッドプール内のスレッド数は同時にリクエストされたものの総数とバランスが取れるようになっています。サービスリクエストに対してアイドル状態のままになっているスレッドが大量にあってはいけません。


初期スレッドプール設定を50スレッドにしてから、アプリケーションサーバーの監視コンソールで監視します。平均ページ時間が2〜3秒の範囲にある場合は、もっと大きい数（たとえば250）を使用することをお勧めします。スレッドプール内のスレッドが少なすぎると、過剰なリクエストをキューする可能性があります；スレッドが多すぎると、過度のコンテキストの切り替えが発生する可能性があります

Tomcatでは、スレッドプールは`$CATALINA_HOME/conf/server.xml`ファイルの`Connector`要素で設定されています。 [Apache Tomcat documentation](https://tomcat.apache.org/tomcat-9.0-doc/config/http.html)では、 詳細がより細かく説明してあります。Liferay Engineeringはこの設定方法でテストします：

    <Connector maxThreads="75" minSpareThreads="50"
    maxConnections="16384" port="8080"
    connectionTimeout="600000" redirectPort="8443"
    URIEncoding="UTF-8"  socketBuffer="-1"
    maxKeepAliveRequests="-1" address="xxx.xxx.xxx.xxx"/>

コネクタタイプ、接続タイムアウト、TCPキューなど、コネクタに関する追加の調整パラメータもあります。詳細については、アプリケーションサーバーのマニュアルを参照してください。
