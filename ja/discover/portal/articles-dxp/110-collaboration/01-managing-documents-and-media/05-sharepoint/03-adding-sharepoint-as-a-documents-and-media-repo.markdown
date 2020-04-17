---
header-id: adding-a-sharepoint-documents-and-media-repository
---

# SharePointドキュメントとメディアリポジトリの追加

[TOC levels=1-4]

以下の手順に従って、SharePointライブラリを@ product @のドキュメントとメディアに追加します。

1.  *Documents and Media* アプリケーションをページに追加します（まだ追加していない場合）。

2.  Documents and Mediaアプリケーション内で、 *Add* アイコン（![Add](../../../../images-dxp/icon-portlet-add-control.png)）をクリックし、 *Repository*を選択します。 [新しいリポジトリ]画面が表示されます。

3.  作成したSharePoint OAuth2構成のリポジトリタイプを選択します。 たとえば、構成の名前が `Foo`場合、リポジトリタイプは `SharePoint（Foo）`と表示されます。

4.  これらのフィールドの値を指定します。

    **サイト絶対URL：** 相対URLを解決します。 SharePoint Onlineの場合、値は次のパターンに従います： `https：//[your-site-name].sharepoint.com`。

    **ライブラリパス：** ドキュメントとメディアにマウントするSharePointドキュメントライブラリを指す *サイトの絶対URL* からの相対パス（パスの例は `共有ドキュメント`）。

    ![図1：リポジトリ構成フォームは、使用するSharePointライブラリへのアクセスを指定する場所です。](../../../../images-dxp/sharepoint-repo-configuration-form.png)

5.  [ *保存*クリックします。

@ product @のドキュメントとメディアライブラリにSharePointリポジトリが一覧表示されます。

| **注：** マウントされたSharePointリポジトリに初めてアクセスするときは、|ログイン資格情報を提供し、@ product @にアクセスするための権限を付与します。リモートSharePointリポジトリ。

甘い\！ SharePointリポジトリを@ product @のドキュメントとメディアに追加したので、@ product @インスタンスのSharePointライブラリファイルにアクセスして変更できます。
