---
header-id: adding-a-sharepoint-documents-and-media-repository
---

# SharePointドキュメントとメディアリポジトリの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

以下の手順に従って、SharePointライブラリを@product@のドキュメントとメディアに追加します。

1.  *Documents and Media* アプリケーションをページに追加します（まだ追加していない場合）。

2.  Documents and Mediaアプリケーション内で、 *Add* アイコン（![Add](../../../../images-dxp/icon-portlet-add-control.png)）をクリックし、 *Repository*を選択します。 [新しいリポジトリ]画面が表示されます。

3.  作成したSharePoint OAuth2構成のリポジトリタイプを選択します。 たとえば、構成の名前が `Foo`場合、リポジトリタイプは `SharePoint（Foo）`と表示されます。

4.  これらのフィールドの値を指定します。

    **サイト絶対URL：** 相対URLを解決します。 SharePoint Onlineの場合、値は次のパターンに従います： `https：//[your-site-name].sharepoint.com`。

    **ライブラリパス：** ドキュメントとメディアにマウントするSharePointドキュメントライブラリを指す *サイトの絶対URL* からの相対パス（パスの例は `共有ドキュメント`）。

    ![図1：リポジトリ構成フォームは、使用するSharePointライブラリへのアクセスを指定する場所です。](../../../../images-dxp/sharepoint-repo-configuration-form.png)

5.  [ *保存*クリックします。

@product@のドキュメントとメディアライブラリにSharePointリポジトリが一覧表示されます。

| **注：** マウントされたSharePointリポジトリに初めてアクセスするときは、|ログイン資格情報を提供し、@product@にアクセスするための権限を付与します。リモートSharePointリポジトリ。

甘い\！ SharePointリポジトリを@product@のドキュメントとメディアに追加したので、@product@インスタンスのSharePointライブラリファイルにアクセスして変更できます。
