---
header-id: sharepoint-repositoryの使用
---

# SharePointリポジトリの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOCレベル= 1-4]

Liferay Marketplaceアプリ [*Liferay Connector for SharePoint*](https://web.liferay.com/marketplace/-/mp/application/105406871) 使用すると、@product@のドキュメントおよびメディアライブラリからSharePoint 2013およびSharePoint 2016ライブラリにアクセスできます。 このアプリをインストールしたら、@product@のドキュメントとメディアライブラリにSharePointリポジトリタイプを追加して、SharePointファイルにアクセスできます。

| **注：** Liferay Connector for SharePointは、Azure ACS with OAuth 2を使用します。 SharePointサーバーの承認。 したがって、|でHTTPSサポートを有効にする必要があります。アプリサーバー。 手順については、アプリサーバーのドキュメントをご覧ください。 |たとえば、Tomcatに必要な手順は|にあります。 [そのドキュメント](https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html)。

Liferay Connector for SharePointには、次の主要な利点があります。

  - ドキュメントとフォルダーの読み取り/書き込み
  - ドキュメントのチェックイン、チェックアウト、およびチェックアウトの取り消し
  - ドキュメントのダウンロード
  - リポジトリ内でのフォルダとドキュメントの移動
  - 変更履歴を取得する
  - リビジョンに戻す

アプリはSharePointのAPIを使用しますが、次の制限があります。

  - 最初にチェックアウトせずにファイルを移動または名前を変更すると、バージョン履歴が失われます。
  - ファイル拡張子を変更することはできません。変更できるのはファイル名のみです。
  - ファイルの現在の名前は、以前のすべてのバージョンに反映されます。
  - ファイルをチェックアウトしたユーザーのみが、そのファイルの作業コピーのバージョン番号を確認できます。
  - サフィックスまたは中間ワイルドカードのクエリは、包含のクエリに変換されます。
  - コメント、評価、SharePointフォルダーをドキュメントとメディアのルートフォルダーとして使用することはサポートされていません。

Documents and MediaでSharePointリポジトリを使用するには、まずSharePointでアプリケーションを作成し、リポジトリへのアクセスを承認する必要があります。 これらのチュートリアルでは、このプロセスについて説明します。
