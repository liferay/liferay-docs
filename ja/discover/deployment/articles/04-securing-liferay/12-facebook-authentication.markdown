---
header-id: facebook-connect-single-sign-on-authentication-ja
---

# Facebook Connect シングルサインオン認証

[TOC levels=1-4]

Facebook Connect SSO認証はFacebookのGraph APIとのインテグレーションを提供しており、
Facebookプロフィール情報と@product@ユーザー(FacebookのIDまたはEmailアドレス）と照合します。ユーザーのFacebookのEmailアドレスが見つかると、Facebook Connect SSO認証モジュールは@product@の`User_ `テーブルから同じEmailアドレスを検索します。一致するアドレスが見つかった場合、Facebook側で必要な権限があればユーザーはサインオンできます。

一致しない場合は、ユーザーをFacebookから@product@に追加するようにプロンプト表示が出ます。この方法でユーザーが作成される場合は、@product@は以下の項目を元にユーザーを作成します: 

- Emailアドレス
- 名
- 姓
- 性別

@product@とFacebookを統合するには、[Facebookのウェブサイト](https://developers.facebook.com)で「アプリケーション」を作成してください。@product@はOAuth 2.0プロトコルを使って認証するため、これが必須です。Facebookは@product@がOAuthで使用するアプリケーションIDとシークレットを提供します。これを使用する利点の１つは、Facebookユーザーがいつでも@product@のアクセスを取り消せることです。

## Facebook Connect SSO 認証を管理する

Facebook Connect SSO設定をシステムまたはインスタンスレベルで適用できます。
Facebook Connect SSOモジュールをシステムレベルで設定するには、 Liferay DXPから、[コントロールパネル] > [設定] > [システム設定] > [ セキュリティ] > [ SSO]へ行き、[Facebook Concect]モジュールへ行きます。ここで入力した値は、ポータルインスタンスに対するデフォルト値となります。

これらのデフォルト値を特定のポータル用に上書きするには、Liferay DXPの[コントロールパネル] > [設定] > [インスタンス設定] へ行き [認証]のセクションにある「Facebook」へ行きます。

**有効にする**: Facebook Connect SSO認証を有効にするにはここにチェックを入れます。有効にする: Facebook Connect SSO認証を有効にするにはここにチェックを入れます。

**有効なアカウントが必要です**: Facebook登録時に使用した際にEmail認証プロセスを済ませ、登録したEmailアドレスにアクセス可能なFacebookユーザーのログインを許可します。 

**アプリケーション ID**: ポータルインスタンスレベルに限り設定できます。Facebookアプリケーションに登録したIDを入力します。

**アプリケーションシークレット**: ポータルインスタンスレベルに限り設定できます。
登録してある Facebookアプリケーションのシークレットを入力します。 

**グラフURL**: これはFacebookグラフAPIのベースURLです。FacebookのグラフAPIが変更された時に限り、この設定を変更します。FacebookのグラフAPIが変更されない場合は、デフォルトのグラフURLを使用します。 

**OAuth認証URL**: FacebookのOAuth認証URLです。 FacebookがOAuth 認証エンドポイントを変更した場合のみ、これを変更します。このURLは動的パラメータが付与され、Liferay サインインポートレットにリンクされます。 

**OAuthトークンURL**: これは、FacebookのOAuth アクセストークンURLです。@product@はこのURLを使い、アクセストークンとリクエストトークンを交換します。

**OAuth リダイレクトURL**: OAuthリクエストトークンが生成されると、ユーザーはこのURLにダイレクトされます。このURLは、リクエストトークンをアクセストークンと交換する@product@サービスを指しています。これは@product@がFacebook Graph APIを正常に呼び出すために必要です。ご使用の@product@インスタンスへのリクエストが、Apacheなど、URLの上書きを行うフロントWebサーバーを経由する場合、このURLを変更します。

