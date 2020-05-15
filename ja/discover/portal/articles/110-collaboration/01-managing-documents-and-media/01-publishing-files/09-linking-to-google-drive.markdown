---
header-id: linking-to-google-drive
---

# Google Drive™へのリンク

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Google Drive™およびGoogle Photos™のファイルにリンクするドキュメントライブラリファイルを作成できます。 これにより、ドキュメントライブラリからGoogleファイルにアクセスできます。 この機能はデフォルトでは使用できないことに注意してください。 これを有効にするには、次の手順を完了する必要があります。

1.  Liferay MarketplaceからLiferay Plugin for Google Drive™をインストールします。

2.  @product@インスタンスと通信できるGoogleプロジェクトを作成または構成します。 このプロジェクトでは、 [Google Picker API](https://developers.google.com/picker/) 有効にする必要があります。 このAPIを使用すると、リンクするGoogleファイルを選択できます。 また、Googleプロジェクトが@product@インスタンスと通信するために必要な認証情報を作成する必要があります。

3.  Googleプロジェクトと通信するように@product@インスタンスを構成します。

この記事では、これらの手順を完了する方法を示し、ドキュメントライブラリからGoogleファイルにリンクする例を示して終わります。

| **重要：** Liferayプラグインfor Google Drive™はLabsアプリケーションです| Liferay CEポータルおよびLiferay DXPで利用できます。 Labsアプリは実験的なものです。 Liferayではサポートされていません。 |の可用性を加速するためにリリースされました。便利で最先端の機能。 このステータスは予告なく変更される場合があります。 使用|独自の裁量でLabsアプリ。

## アプリをインストール

まず、Liferay MarketplaceからGoogle Drive™用のLiferayプラグインをインストールする必要があります。 このアプリは、Liferay CEポータルおよびLiferay DXPの次のリンクから入手できます。

  - [Googleドライブ用Liferayプラグイン-CE](https://web.liferay.com/marketplace/-/mp/application/105847499)
  - [Googleドライブ用Liferayプラグイン-DXP](https://web.liferay.com/marketplace/-/mp/application/98011653)

Marketplaceからのアプリのインストールについてサポートが必要な場合は、Marketplace</a>を使用した

のドキュメントをご覧ください。</p> 



## Googleプロジェクトを構成する

@product@インスタンスと通信できるようにGoogleプロジェクトを作成または構成するには、次の手順に従います。

1.  [Google API Console](https://console.developers.google.com)移動します。 適切なプロジェクトがない場合は、 [で新しいプロジェクトを作成します](https://support.google.com/googleapi/answer/6251787?hl=en&ref_topic=7014522)。

2.  プロジェクトでGoogle Picker APIを有効にします。 手順については、Google API Consoleのドキュメントで [APIの有効化と無効化](https://support.google.com/googleapi/answer/6158841)ご覧ください。

3.  GoogleプロジェクトにOAuth 2クライアントIDを作成します。 手順については、上のGoogleのAPIコンソールのドキュメントを参照 [OAuth 2.0の設定](https://support.google.com/googleapi/answer/6158849)。 クライアントIDを作成するときに、次の値を入力します。
   
         - **アプリケーションタイプ：** Webアプリケーション
      - **名前：** Googleドキュメントフック
      - **承認されたJavaScriptオリジン**： `[liferay-instance-URL]` （たとえば、 `http：// localhost：8080` はローカル開発マシンのデフォルトです）
      - **承認済みリダイレクトURI**： `[liferay-instance-URL]/ oath2callback`
4.  Googleプロジェクトに新しいAPIキーを作成します。 手順については、Google API Consoleのドキュメントで [APIキーの作成](https://support.google.com/googleapi/answer/6158862?hl=en)ご覧ください。 キーを必ずHTTPリファラー（Webサイト）に制限し、@product@インスタンスのURLからのリクエストを受け入れるように設定してください。

新しいOAuthクライアントIDと公開APIアクセスキーがGoogleプロジェクトの[認証情報]画面に表示されます。 この画面を開いたままにして、@product@で指定するときにこれらの値を参照します。



## @product@のGoogle Apps設定を構成する

前の手順で作成したクライアントIDとAPIキーで@product@インスタンスを構成します。

1.  *コントロールパネル* → *構成* → *インスタンス設定*ます。

2.  [ *Miscellaneous* ]タブをクリックして、[ *Google Apps* セクションを展開します。

3.  *Google Apps API Key*場合は、前のセクションで作成したGoogle APIキーを入力します。

4.  *GoogleクライアントID*場合は、前のセクションで作成したGoogle OAuthクライアントIDを入力します。

5.  *変更を保存* ます。



## リンクファイルの作成

上記の構成手順が完了すると、Google Drive™のファイルまたはGoogle Photos™の画像にリンクするファイルをドキュメントライブラリに作成できます。 次の手順に従ってください。

1.  ドキュメントライブラリで、[ *追加* ]ボタン（![Add](../../../../images/icon-add.png)）をクリックし、[ *Googleドキュメント*]を選択します。 *New Google Docs* 画面が表示されます。

2.  [ *Select File* ]ボタンをクリックして、Googleのファイルピッカーを開きます。

3.  ファイルピッカーを使用して、Google Drive™またはGoogle Photos™からファイルを選択します。

4.  *公開*クリックし* 。</p></li> </ol> 
   
   ![図1：Google Drive™または写真からファイルを選択できます。](../../../../images/dm-google-select-a-file.png)
   
   リンクしたGoogleドキュメントの新しいファイルエントリが表示されます。 他のファイルエントリと同じように、ファイルエントリを表示できます。 Googleドキュメントのコンテンツがファイルエントリのプレビューペインに表示されます。 他のファイルエントリと同様に、[ *オプション* ]ボタン（![Options](../../../../images/icon-options.png)）を使用すると、ダウンロード、編集、移動、権限、ごみ箱に移動、およびチェックイン/チェックアウト/チェックアウトのキャンセルオプションにアクセスできます。
