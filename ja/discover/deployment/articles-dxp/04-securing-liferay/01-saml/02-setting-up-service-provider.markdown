---
header-id: setting-up-liferay-as-a-saml-service-provider
---

# SAMLService Providerとして@product@を設定する

これらの手順の多くは、@product@をSAML Identity Providerとして設定する手順と似ています。単一の@product@インストールをSAML Identify Provider*または* SAML Service Providerとして設定できますが、両方を設定することはできないのでその点注意してください。SAML Identity Providerとして1つの@product@インストールをすでに設定している場合は、SAMLService Providerとは*別の* @product@インストールを使用して ください。


| **注：** SPを@product@としてサードパーティIdPを使用している場合は、IdPから来る全てのメッセージは署名されていなければなりません。そうでない場合は、エラーメッセージが表示され、IdPと@product@間の通信が失敗します。

1. Liferay SAML 2.0 Providerアプリをインストールします。
アプリが正常にデプロイされたことを確認するには、コントロールパネルのConfigurationセクションで*SAML Admin*エントリーを探します。



2. SAMLを使用するように@product@の設定を開始するには、@product@のSAMLロールを選択し、エンティティIDを選択する必要があります。*Service
Provider*SAMLロールを選択します。模範の@product@インストールを設定している場合は、*liferaysamlsp* と入力してください。あるいは、独自のエンティティIDを選択してください。次に *[Save]* をクリックすると、CertificateとPrivate Keyという新しいセクションが表示されます。


3. CertificateとPrivate Keyセクションは、SAML用のキーストアを作成するためのものです。
*Create Certificate*をクリックして、以下の情報を入力します。

   - ご自身のお名前（姓名）

   - 組織名

   - 組織内の部署名

   - 街や地区名

   - 都道府県名

   - 国名

   - キーストアが有効である日数（キーストアの有効期限が切れるまでの期間）

   - キーアルゴリズム（RSAがデフォルト）

   - キーの長さ（ビット単位）（2048がデフォルト）

   - キーパスワード

   必要な情報をすべて入力したら、*[Save]*をクリックしてください。



4.  *[Save]*をクリックした後、証明書に関する情報を表示できるか、証明書をダウンロードできるかを確認してください。

できた場合、キーストアが正常に作成されたということです。キーストアを作成した後、追加のオプションが表示されます。3つのタブがあります。



   - *General*：このタブはSAML IdPを有効または無効にし、必要なキーストアを管理します。



   - *Service Provider*：このタブは、SPの基本設定と詳細設定を管理します。



   - *Identity Provider Connection*：このタブはIdPへの接続を管理します。
IdP接続は1つだけです。


   これらのオプションは、@product@をアイデンティティプロバイダとして設定している場合とは異なります。

5. 次に、 Identity Provider接続を設定する必要があります。*Identity Provider Connection*タブをクリックし ます。 Identity Providerの名前を入力し、そのエンティティIDを入力し、そのメタデータURLを入力します。前の手順を既に実行し、別の@product@インストールをIdentifyプロバイダとして設定している場合は、次の情報を入力します。



   - 名前：*Liferay IdP*

   - エンティティID：*liferaysamlidp*

   - Clock Skew

   - Force Authn

   - メタデータURL：http：// localhost：8080 / c / portal / saml / metadata（最初にこのURLをテストしてください）

   - Name Identifier Format

   - Attribute Mapping

   - Keep Alive URL
   **重要**：LiferayのSAML 2.0 Providerアプリを使用してサポートしているのはSAML IdPがメタデータ・ファイルへのURL*または*実際の（アップロード）SAMLメタデータXMLファイルの*どちらか*のみです。*メタデータURL*フィールドに入力された値入力は、入力されたメタデータURLが1つあり、指定されたメタデータXMLファイルがない場合にのみ、データベースに永続化されます。それ以外の場合、@product@は元のメタデータURLをデータベースに保持します。これにより、一度メタデータURLが指定されると、常にメタデータURLがデータベースに保存されるようになります。こうすることで、ポータル管理者が以前に入力したメタデータURLまたはその形式を忘れた場合、表示されているメタデータURLを見て、表示されているメタデータURLを変更するか、メタデータXMLファイルを指定して以前保存したメタデータURLを上書きするかを選択できます。

   現在、SAMLプロバイダアプリは、コントロールパネルUIを使用してSAML IdPメタデータURLまたはメタデータXMLファイルフィールドを削除する方法を提供していません。これらのフィールドを削除する必要がある場合は@product@データベースの`SamlSpIdpConnection`テーブルのSAML IdPメタデータURLとメタデータXMLファイルの列の内容を削除することができます（ただしこれを行うことをお勧めできません）。



6. 最後に、証明書とプライベートキーの情報を保存して Identity Provider接続を設定したら、Generalタブの上部にある*Enabled* チェックボックスをオンにして*[Save]*をクリックします。これで@product@がSAMLService Providerになりました！

SAMLService Providerセッションは、アプリケーションサーバー上の通常のセッションに関連付けられています。アプリケーションサーバーでのセッションの有効期限が切れた場合、Service Providerーでのセッションは終了しますが、シングルログアウトは開始されません。

## チェックポイント

1. SAMLキーストアが生成されている。

2. IdPへの接続を確認する。

   a.  *Name*：IdPの総称名。

   b.  *Entity ID*：IdPの同じ名前。IdPが別の@product@インスタンスである場合、それは上記の例と同じ名前である。

   c. 
*Metadata URL*：URLまたはXMLファイルとしてのIdPのメタデータ。



   d. IdPが別の@productインスタンスである場合は、このSPに対応するService Provider Connectionが有効になっていることを確認する。



3. *General* タブで、*Enabled*チェックボックスがオンになっている。



4. *Enabled* チェックボックスをオンにすると、Service Providerのメタデータが利用可能になります：

       [host]:[port]/c/portal/saml/metadata
   
## SAMLService Provider設定の変更

@product@のSAMLService Provider設定を設定する場合は、SAML管理ポートレットのService Providerタブに移動します。




Service Providerタブには、次のオプションがあります:




**Require Assertion Signature?:**このボックスをオンにすると、SAMLメッセージ全体に加え、SAMLアサーションに個別に署名する必要があります。



| **注：** SAMLレスポンス自体が署名されていれば、個々のアサーションに署名する必要はありません。。トランスポートレベルで暗号化されるよう、SPとIdPは常に`https`上で通信する必要があります。
| 
| 中間者攻撃が可能であると思われる場合は、SAMLレスポンスは署名できます。アサーションに署名する唯一の理由は、SAMLレスポンスが署名されていない場合です。この場合、アサーションは署名されるだけでなく暗号化されるべきです。
| 
| 

**Clock Skew:** Clock skewは、メッセージの有効期限とアサーションを検証するためにService Providerが使用するミリ秒単位の許容誤差です。これは、Identity Provider とService Providerのクロック間の時間差を軽減するために使用できます。これは通常、アサーションが非常に早く期限切れになるようにされているときにだけ問題になります。 

**LDAP Import Enabled：**このボックスがチェックされると、解決されたNameIDに基づいて設定されたLDAP接続からユーザー情報がインポートされます。LDAP接続は Instance Settingsから設定できます。



**Sign Authn Requests:** このボックスがチェックされていると、dentity Provider のメタデータが署名が不要であることを示していてもAuthnRequestに署名されます。



**Sign Metadata:**このボックスをチェックすると、メタデータXMLファイルが署名されます。



**SSL Required:** このボックスをチェックすると、HTTPS経由で送信されていないSAMLメッセージはすべて拒否されます。これはURLの生成方法には影響しません。

## SAML Identity Provider接続設定の変更

@product@のSAML Identity Provider設定を設定する場合は、SAML管理ポートレットのIdentity Provider Connectionのタブに移動したください。



**Name:**接続先のIdentity Providerの名前。



**Entity ID:**  Identity ProviderのエンティティID。この値は、Identity Providerメタデータで宣言されているエンティティIDと一致する必要があります。



**Clock Skew:** Clock skewは、メッセージの有効期限とアサーションを検証するためにService Providerが使用するミリ秒単位の許容誤差です。これは、Identity Provider とService Providerのクロック間の時間差を軽減するために使用できます。これは通常、アサーションが非常に早く期限切れになるようにされているときにだけ問題になります。 

**Force Authn:**このボックスがチェックされていると、Service ProviderはIdentity Provider に、ユーザを確認する前にユーザの再認証をリクエストします。



**Metadata:** Identity Provider のメタデータXMLファイルへURLを提供することも、マニュアルでアップロードすることもできます。URLを指定した場合、XMLファイルは自動的に取得され、定期的に更新についてポーリングされます。[System Settings]で秒数を指定する`saml.metadata.refresh.interval`プロパティを変更することで、更新間隔を変更できます 。
URLによるメタデータXMLファイルの取得に失敗すると、アイデンティティプロバイダ接続を有効にできません。メタデータがURL経由でアクセスできない場合は、XMLファイルをマニュアルでアップロードできます。この場合、メタデータXMLファイルは自動的には更新されません。



**Name Identifier Format:** SAML Responseで使用されるName Identifier Format を選択します。これはService Providerが何を受け取るのかによって設定してください。LiferayService Providerの場合、メールアドレス以外の選択は、Name Identifierが画面名を指すことを示します。フォーマットは、Liferay Identity Providerにとって特別な意味はありません。NameID値は、Name Identifierの属性によって定義されます。

**Attribute Mapping:** Attribute mappingは、attribute名またはSAML Response内のフレンドリ名から@product@属性名で行われます。例えば、`mail`とい属性名を@product@属性の`emailAddress`にマッピングしたい場合は、次のマッピングを入力します。

    mail=emailAddress

利用可能な@product@ 属性は`emailAddress`、`screenName`、`firstName`、 `lastName`、`modifiedDate`、と`uuid`です。

**Keep Alive URL:** ユーザーが@product@ IdPを介して複数の@product@ SPインスタンスにログインしている場合、そのうちの１つのブラウザウィンドウを開いている限り、セッションをアクティブに保つことができます。

IdPが@product@の場合にのみ設定してください。URLは`https://[IdP host name]/c/portal/saml/keep_alive`です。@product@ IdPで、このURLを同じように設定しますが、SPを示すようにしてください。



@product@インスタンスをService Providerとして設定し終わったら、変更を保存してください。サーバーを再起動する必要はなく、変更はすぐに適用されます。



前の2つのセクションでは、SAML 2.0 ProviderアプリのControl Panelインターフェイスを使用して、@product@をIdentity ProviderまたはService Providerとして設定する方法について説明しました。そのような設定はSAML Control Panel インターフェースを介してのみ行われるべきで、プロパティを通して行わないでください。Liferay SAML 2.0 Providerアプリの一部の機能はプロパティとして利用できません。



| **制限事項：** Liferay SAMLアプリは単一の仮想環境ホストでのみ使用できます。@product@のSAMLメタデータでは、1つのバインディングだけ以下の形式で追加できるということです。
| 
|     <md:EntityDescriptor>
|     ...
|     <md:SPSSODescriptor>
|     ...
|     <md:AssertionConsumerService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="https://portal.domain.com/c/portal/saml/acs" index="1" isDefault="true" />
|     ...
|     </md:SPSSODescriptor>
|     </md:EntityDescriptor>


## クラスタ環境でのSAML Service Providerとしての@product@の設定

Liferay SAML 2.0 Providerアプリをクラスタ環境の @product@のSSOソリューションとして使用する場合は、このセクションの手順に従ってください。先に進む前に、以下の前提条件が当てはまることを確認してください。



ロードバランサーの背後でマルチノードクラスタを実行している場合は、次の手順に従ってすべてのノードをSPとして有効にします。



始める前に、クラスターで使用したいキーストアマネージャーのタイプを検討してください。



キーストアマネージャを選択するには、*[Control Panel]*>*[System Settings]*>*[SAML KeyStoreManager Implementation Configuration]*に行きます。そこでのオプションは*Filesystem Keystore Manager*と*Document Library Keystore Manager*です。



クラスタ内のすべてのノードは、同じキーストアマネージャを使用するように設定する必要があります。



Filesystem Keystore Managerを使用する場合（デフォルト）：



1. 前のセクションの指示に従って、[@product@クラスタ](/discover/deployment/-/knowledge_base/7-0/liferay-clustering)の各ノードをSAMLService Providerとして設定します。



2. キーストアファイルを（デフォルトの`[Liferay Home]/data/keystore.jks`）最初の@product@ノードから残りの@product@ノードにコピーします。このファイルは、SAML Providerアプリによって作成されたJavaキーストアです。キーストアには、SAML Provider アプリによって管理されている有効な証明書または自己署名証明書が含まれています。



3. Service ProviderのメタデータがURLまたはXMLファイルとして使用されるように生成されていることを確認してください。データベースのバックエンドが同じであるため、メタデータはすべてのノードで同じです。IdPのリクエストはロードバランサーを通過します。



4. この時点で、すべての@product@ノードは同じSAML SP設定を持ち、それぞれがWebリクエストにレスポンスしてSAMLプロトコルを処理できるはずです。
SSOソリューションをテストするには、ロードバランサーを介して@product@にサインインし、いくつかの異なるサイトのページに移動してログアウトします。



Document Library Keystore Managerを使用している場合は、キーストアファイルがすべてのノードで共有されているデータベースに格納されているため、手順3は省略して大丈夫です。



以上が@product@をSAML Identity ProviderまたはService Providerとして設定する方法です。これで、クラスタ環境でSAMLを設定することもできるはずです。
