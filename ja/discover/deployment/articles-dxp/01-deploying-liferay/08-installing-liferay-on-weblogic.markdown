---
header-id: installing-liferay-dxp-on-weblogic-12c-r2
---

# WebLogic 12c R2への@product@のインストール

@product@をWebLogic Adminサーバー上でインストールできますが、この方法は勧められていません。@product@を含むWebアプリケーションをWebLogic 管理サーバーにインストールすることをお勧めします。Web 管理サーバーにデプロイすると、@product@をより早く起動またはシャットダウンすることができ、クラスター設定への移行も容易になります。この記事では、@product@をWeb 管理サーバーにインストールする方法について説明します。



始める前に、Adminサーバーと管理サーバーを作成してください。 Adminサーバーと管理サーバーの設定方法については、[WebLogic's documentation](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html)を参照してください 。



以下の@product@の一般的なインストール手順について書かれている記事にも目を通しておいてください。



- [Installing @product@](/discover/deployment/-/knowledge_base/7-1/installing-liferay)
- [Preparing for Instal](/discover/deployment/-/knowledge_base/7-1/preparing-for-install)
- [マニュアルで@product@をインストールする](/discover/deployment/-/knowledge_base/7-1/installing-liferay-manually)

最後に、[カスタマーポータル](https://web.liferay.com/group/customer/dxp/downloads/7-1)から@product@のWARファイルと依存関係をダウンロードしてください 。
以下のファイルが必要です：

- `liferay-dxp-digital-enterprise-[version].war`: @product@ WAR file

- `liferay-dxp-digital-enterprise-dependencies-[version].zip`: @product@
の依存関係

- `liferay-dxp-digital-enterprise-osgi-[version].zip`: @product@ OSGi
の依存関係

**確認事項**

`liferay-dxp-digital-enterprise-dependencies-[version].zip`の中に以下のjarファイルがある：



1. `com.liferay.petra.concurrent.jar`
2. `com.liferay.petra.executor.jar`
3. `com.liferay.petra.function.jar`
4. `com.liferay.petra.io.jar`
5. `com.liferay.petra.lang.jar`
6. `com.liferay.petra.memory.jar`
7. `com.liferay.petra.nio.jar`
8. `com.liferay.petra.process.jar`
9. `com.liferay.petra.reflect.jar`
10. `com.liferay.petra.string.jar`
11. `com.liferay.registry.api.jar`
12. `hsql.jar`
13. `portal-kernel.jar`
14. `portlet.jar`


`/liferay/osgi`フォルダの中に以下のフォルダがなければいけません：

1. `Configs`
2. `Core`
3. `Marketplace`
4. `Modules`
5. `Portal`
6. `Static`
7. `Test`
8. `War`


では、WebLogicに@product@をインストールしましょう！



## WebLogicのNode Managerの設定方法

WebLogicでは、管理されているサーバーを起動およびシャットダウンするためにはNode Managerが必要です。@product@をインストールする前に、WebLogicインストールに含まれているNode Managerを設定する必要があります。`domains/your_domain_name/nodemanager/nodemanager.properties`ファイルを介してこれを行います。このファイルを開き、`SecureListener`プロパティを`false`に設定します：

    SecureListener=false

この設定により、Node Managerの暗号化（SSL）の条件がなくなり、暗号化されていない接続も受け入れられるようになります。プロパティを`true`と設定しても@product@を実行することができますが、問題が起きる可能性があります。また、`SecureListener`が`true`と設定されていると、Node Managerから送られてくる、暗号化されていない接続を受け入れられるようにAdminサーバーのコンソール上で設定しなければいけません。これを行うには、まずAdminサーバーにログインし、左側の*[Domain Structure]*ボックスの*[Environment]*>*[Machines]* を選択します。
表の中から自分のマシンをクリックして、*[Configuration]*> *[Node
Manager]* タブを選択します。*[Type]*のフィールドのセレクターメニューから*[Plain]*を選択し、*[Save]*をクリックします。この変更を有効にするには、Adminサーバーを再起動する必要があります。



MacまたはLinux上でWebLogicを実行している場合は、`NativeVersionEnabled`プロパティを`false`に設定する必要があります。

    NativeVersionEnabled=false

これにより、Node Managerはノンネイティブモードで起動するようになります。これは、WebLogicがネイティブのNode Managerライブラリーを提供しないプラットフォームで必要です。

## WebLogicの設定方法

次に、2つのWebLogic立ち上げ時スクリプト上でいくつかの変数を設定する必要があります。これらの変数とスクリプトは次のとおりです。 Windows を使用している場合は、`set`の代わりに`export`を使用してください。



1. `your-domain/startWebLogic.[cmd|sh]`：これはAdminサーバーの立ち上げ時のスクリプトです。



2. `your-domain/bin/startWebLogic.[cmd|sh]`：これは管理サーバーの立ち上げ時のスクリプトです。



   以下の変数を両方の`startWebLogic.[cmd|sh]` のスクリプトに追加してください：

       export DERBY_FLAG="false"
       export JAVA_OPTIONS="${JAVA_OPTIONS} -Dfile.encoding=UTF-8 -da:org.apache.lucene... -da:org.aspectj..."
       export MW_HOME="/your/weblogic/directory"
       export USER_MEM_ARGS="-Xmx2048m"
   
   `DERBY_FLAG`の設定はWebLogicに組み込まれているDerbyサーバーを無効にします。なぜなら、@product@がこのサーバーを必要としないからです。残りの設定は、@product@のメモリー要件、UTF-8要件、Luceneの使用量、およびAspectJを介したAspect Oriented Programmingをサポートします。またマシン上のWebLogicサーバーを含むディレクトリに`MW_HOME`を設定してください。例えば：

       export MW_HOME="/Users/ray/Oracle/wls12210"
   

3. いくつかの設定は`your-domain/bin/SetDomainEnv.[cmd|sh]`上で見つけることができます。Windowsを使用している場合は、以下の変数を追加してください：

       set WLS_MEM_ARGS_64BIT=-Xms512m -Xmx2048m
       set WLS_MEM_ARGS_32BIT=-Xms512m -Xmx2048m
   
   MacまたはLinuxを使用している場合は以下を追加してください：

       WLS_MEM_ARGS_64BIT="-Xms512m -Xmx2048m"
       export WLS_MEM_ARGS_64BIT
       
       WLS_MEM_ARGS_32BIT="-Xms512m -Xmx2048m"
       export WLS_MEM_ARGS_32BIT
   
4. 他のJavaプロパティの前に`-Dfile.encoding=utf8`を付け加え、`your-domain/bin/SetDomainEnv.[cmd|sh]`上でJavaファイルをUTF-8にエンコードしてください。

       JAVA_PROPERTIES="-Dfile.encoding=utf8 ${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"
   
5. また、管理サーバーを起動するときに、Node Managerが@product@のメモリ要件を設定するようにする必要があります。AdminサーバーのコンソールUIで、@product@をデプロイしたいManage Serverに移動して*[Server Start]*のタブを選択してください。*[Argument]*のフィールドに次のパラメータを入力します ：

       -Xmx2048m -XX:MaxMetaspaceSize=512m
   
   完了したら*[Save]*をクリックします。

次に、@product@インストールに@product@特有のプロパティを設定します。



## @product@プロパティを設定する


@product@をインストールする前に、`portal-ext.properties`ファイルの`liferay.home`プロパティを介して[*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)のフォルダのロケーションを設定する必要があります。このファイルを使用して、 必要になる可能性のある[他の@product@プロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)を上書きすることもできます 。

まず、どのフォルダをLiferay Homeとして使用するかを決めます。WebLogicでは、ドメインのフォルダは通常Liferay Homeですが、マシン上の好きなフォルダを選択することができます。次に`portal-ext.properties`ファイルを作成して`liferay.home`プロパティを追加します：

    liferay.home=/full/path/to/your/liferay/home/folder

このファイルパスをLiferay Homeとして使用したいマシン上のロケーションに必ず変更してください。



これで`portal-ext.properties`ファイルを作成したので、それを@product@ WARファイル内に入れる必要があります。@product@ WARファイルを展開し、`portal-ext.properties`を`WEB-INF/classes`フォルダに入れます。後で拡大したアーカイブをWebLogicにデプロイすることができます。あるいは、後でデプロイするために拡大したアーカイブを再びWARすることもできます。どちらの場合も、起動時に@product@はプロパティ設定を読み込みます。



@product@のデプロイ後に`portal-ext.properties`に変更を加える必要がある場合は、ドメインの`autodeploy/ROOT/WEB-INF/classes`フォルダに見つけることができます。`autodeploy/ROOT`フォルダには@product@デプロイが含まれています。


次に、@product@の依存関係をインストールします。

## @product@依存関係のインストール

次に@product@の依存関係をインストールする必要があります。以前に以下の依存関係を含む2つのZIPファイルをダウンロードしました。今度は、それらのコンテンツをインストールしてください：



1. `liferay-dxp-digital-enterprise-dependencies-[version].zip`：このファイルを解凍し、その内容をWebLogicドメインの`lib`フォルダに入れてください。



2. `liferay-dxp-digital-enterprise-osgi-[version].zip`：このファイルを解凍し、その内容をを`Liferay_Home/osgi`フォルダに入れてください（このフォルダがない場合は作成してください）。



データベースのドライバJARファイルをドメインの`lib`フォルダに追加する必要があります。
Hypersonicはテスト目的として使用するには問題ありませんが 、プロダクションの @product@インスタンスとしては使用**しないで**ください。

<segment 0468 ¶>

**確認事項**

ドメイン`lib`フォルダには以下のjarファイルがある：

* `com.liferay.petra.concurrent.jar`
* `com.liferay.petra.executor.jar`
* `com.liferay.petra.function.jar`
* `com.liferay.petra.io.jar`
* `com.liferay.petra.lang.jar`
* `com.liferay.petra.memory.jar`
* `com.liferay.petra.nio.jar`
* `com.liferay.petra.process.jar`
* `com.liferay.petra.reflect.jar`
* `com.liferay.petra.string.jar`
* `com.liferay.registry.api.jar`
* `hsql.jar`
* `portal-kernel.jar`
* `portlet.jar`

データベースのJDBCドライバがドメインの`lib`フォルダに追加されている。
一般的なJDBCドライバは以下のとおり：

* [`mariadb.jar`](https://downloads.mariadb.org/)
* [`mysql.jar`](http://dev.mysql.com/downloads/connector/j)
* [`postgres.jar`](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

`[Liferay Home]/osgi`フォルダに以下のサブフォルダが含まれている：

* `Configs`
* `Core`
* `Marketplace`
* `Modules`
* `Portal`
* `Static`
* `Test`
* `War`

次に、データベースを設定します。

## データベースの設定方法

WebLogicに@product@のデータベースを管理させたい場合は、次の手順に従ってください。@product@に組み込まれているHypersonicデータベースを使用したい場合は、このセクションを飛ばしてください。



1. AdminServerコンソールにログインします。



2.  *[Domain Structure]* ツリー上でドメインを見つけ、*[Services]*
>*[JDBC]*>*[Data Sources]*に移動してください。


3. 新しいデータソースを作成するには、*[New]*をクリックします。*[Name]*のフィールドに`Liferay Data Source`を入力し、*[JNDI Name]*のフィールドに`jdbc/LiferayPool`を入力してください。
データベースの種類とドライバを選択します。例えば、MySQLは*MySQL's Driver
(Type 4) Versions:using com.mysql.jdbc.Driver*です。
*[Next]*をクリックして続行します。

4. このページのデフォルト設定を受け入れて、*Next*をクリックして先に進みます。



5. MySQLデータベースにデータベース情報を入力してください。



6. MySQLを使用している場合は、`?useUnicode=true&characterEncoding=UTF-8&\useFastDateParsing=false`というテキストをURLの行に追加し、接続をテストしてください。正常に機能する場合は、*[Next]*をクリックしてください。



7. データソースのターゲットを選択して *[Finish]*をクリックします。



8. 次に、@product@にJDBCデータソースの情報を追加しなければいけません。Liferay Homeディレクトリに`portal-ext.propreties`ファイルを作成し、`jdbc.default.jndi.name=jdbc/LiferayPool`の行を追加します。

あるいは、`portal-ext.properties`ファイル内のプロパティを使用して上記の設定を厳密に行うこともできます。

これを行うには、ファイルに次のプロパティと値を追加します。必ず`your*`の値をデータベースの設定に適した値に変更してください（MySQLを使用している場合に限る）。

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://your.db.ip.address/yourdbname?useUnicode?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=yourdbuser
    jdbc.default.password=yourdbpassword

次に、メールセッションを設定します。

## メール設定方法

WebLogicにメールセッションを管理させたい場合は、以下の手順を使用してください。
Liferayの組み込みメールセッション（推奨）を使用したい場合は、このセクションを飛ばしてください。



1. WebLogicを起動して、Adminサーバーのコンソールにログインします。



2. AdminサーバーのコンソールUIの左側にある*[Domain Structure]*のボックスから*[Services]*>*[Mail Sessions]*を選択します。



3. *[New]*をクリックして新しいメールセッションを作成します。



4. セッションに*LiferayMail*という名前を付け、JNDI名`mail/MailSession`を付けます。
そしたら、メールサーバーの必要に応じて *[Session Username]*、*[Session Password]*、*[Confirm Session
Password]*、および*[JavaMail Properties]*のプロパティを記入してください。 これらのフィールドの詳細については、[WebLogic documentation](http://docs.oracle.com/middleware/1221/wls/FMWCH/pagehelp/Mailcreatemailsessiontitle.html)を参照してください 。完了したら、*[Next]* をクリックします。

5. @product@をインストールする管理サーバーを選択して*[Finish]*をクリックします 。そしたら、管理サーバーとAdminサーバーをシャットダウンします。



6. MangedサーバーとAdminサーバーをシャットダウンした状態で、Liferay Homeの`portal-ext.propertiesLiferay Home`のファイルに次のプロパティを追加します：

       mail.session.jndi.name=mail/MailSession
   
   @product@は、このプロパティ設定を介してWebLogicメールセッションを参照します。
@product@をすでにデプロイしてある場合は`portal-ext.properties`ファイルはドメインの`autodeploy/ROOT/WEB-INF/classes`フォルダにあります。



変更内容は、管理サーバーとAdminサーバーを再起動すると有効になります。

##  @product@をデプロイする

前述の通り、WebLogic Adminサーバーに@product@をデプロイすることができますが、WebLogic 管理サーバーにデプロイすることを勧めます。Adminサーバーを、アプリを実行する他のサーバーを管理するために使用するのがベストプラクティスです。


@product@をManagedサーバにデプロイするには、次の手順に従ってください：

1. @product@をデプロイする管理サーバーがシャットダウンされていることを確認してください。



2. AdminサーバーのコンソールUIで、左側の*[Domain
Structure]*のボックスから*[Deployments]* を選択します。次に、*[Install]*をクリックして新しいデプロイを開始します。



3. ファイルシステム上の@product@ WARファイルまたはその拡大されたコンテンツを選択します。
または、*[Upload your file(s)]*リンクをクリックしてWARファイルをアップロードすることもできます。*[Next]*をクリックしてください。

4. *Install this deployment as an application*を選択し *[Next]*をクリックします。



5. @product@をデプロイする管理サーバーを選択して*[Next]*をクリックします。



6. デフォルト名がご使用のシステムに適している場合は、そのまま使用してください。
適していない場合は、新しい名前を付けて*[Next]*をクリックします。



7. *Finish*をクリックします。デプロイが完了したら、設定を保存したい場合は*[Save]*をクリックします。



8. @product@をデプロイした管理サーバーを起動します。@product@はすべてのJSPを事前にコンパイルしてから起動します。

 これで、WebLogic上で@product@を実行できました。

+$$$

@product@をデプロイした後、`PhaseOptimizer`を含む以下のような警告とログメッセージが表示される場合があります。これらの警告は無視してください。
必要ないログメッセージが頻繁に表示されないように、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。

    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass gatherExternProperties
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass checkControlFlow
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
    current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]

$$$
