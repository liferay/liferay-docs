# トークンを使用したシングルサインオン認証[](id=token-based-single-sign-on-authentication)

トークン方式SSO認証は@product@7.0から導入され、Shibboleth、 SiteMinderや以下の方法でトークンを伝播させる形式のSSO製品に対して標準化されています:

- HTTP リクエストパラメータ

- HTTP リクエストヘッダー

- HTTP クッキー

- セッション属性

認証トークンには特定の会社(ポータルインスタンス)に使用できるように、@product@ユーザーのスクリーンネーム、Emailアドレスのいずれかが含まれています。@product@は3つの認証方法をサポートしてます:

- Emailアドレス
- スクリーンネーム

- ユーザーID


@product@のトークン認証は、Emailアドレスとスクリーン名のみをサポートしているのでご注意ください。トークンを利用した認証を試みた際に、ポータルがユーザーIDを使用するように設定されている場合は、`TokenAutoLogin`のクラスが次のような警告を出します:

    Incompatible setting for: company.security.auth.type

上記のソースは完全に信頼されています。

さらに、@product@の外部のセキュリティーメカニズム(Apacheなどフロントウェブサーバーなど）を利用してください。これらアプリケーションサーバの前段に配置されたソリューションを利用して、悪意のある@product@ユーザーの偽装を防止することが必要です。さもないとクライアントのウェブサーバーから直接、Liferay DXPにHTTPリクエストを送信できてしまいます。

デフォルトでは、トークン認証は無効になっています。トークンを使用したSSO認証を管理するには、@product@の[コントロールパネル] > *[システム設定]* > *[セキュリティ]* > *[SSO]*に行きます。
「トークン方式SSO」は3ページ目にありますが、*トークン*と検索を掛けて探すことも可能です。以下は、トークンベースのSSOモジュールの設定オプションです。

**Authentication cookies**: これをクッキーに書き込むことでログアウト後に削除されます。(例: `SMIDENTITY`, `SMSESSION`)

**Enabled**: トークン方式SSOを有効にするにはこれをチェックします。

**Import from LDAP**: これにチェックすることで、ポータルにユーザーが存在しない場合はLDAPからユーザーを自動的にインポートします。

**Logout redirect URL**: @product@からログアウトした場合、このURLにリダイレクトされます。

**Token location**:ユーザートークンの場所をここで指定します。上記で説明したとおりオプションは次のとおりです。

- HTTP リクエストパラメータ

- HTTP リクエストヘッダー

- HTTP クッキー

- セッション属性

トークン名と同様にします。****特定の場所から取得されます。(例: SM_USER)

*[保存]*をクリックしトークン方式SSOを有効にします。

## 必須のSiteMinder設定方法[](id=required-siteminder-configuration)

SiteMinderをご使用の場合は、@product@はそのURLにチルダ文字を使用することがあります。デフォルトでは、SiteMinderはチルダ文字（およびその他）を不正な文字として扱い、そのいずれかを含むURLを処理するとHTTP 500エラーを返します。この問題を回避するには、 SiteMinder設定を以下のように変更します:

    BadUrlChars       //,./,/.,/*,*.,\,%00-%1f,%7f-%ff,%25

上記の設定は`~`が不正URL文字リストから除外されている以外はデフォルトと同じです。SiteMinderを再起動して、設定の更新を有効にします。詳細については、SiteMinderの[キュメンテーション](https://support.ca.com/cadocs/0/CA%20SiteMinder%20r6%200%20SP6-ENU/Bookshelf_Files/HTML/index.htm?toc.htm?258201.html)をご覧ください。

# まとめ[](id=summary)

@product@のトークン方式のSSO認証メカニズムは、柔軟性が高く、有効な@product@ユーザーのスクリーン名や、Emailアドレスを提供できるSSOソリューションであれば、互換性があります。これにはShibboleth とSiteMinderが含まれます。
