---
header-id: creating-a-new-sharepoint-repository-configuration
---

# 新しいSharePointリポジトリ構成の作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

リモートSharePointサーバーに接続するには、リポジトリー構成を作成する必要があります。

1.  @product@インスタンス内で、コントロールパネルを開き、 *設定* → *システム設定* → *コラボレーション* → *Sharepoint OAuth2*ます。

    ![図1：* Sharepoint OAuth2 *システム設定を使用して、新しいSharePointリポジトリ構成を作成します。](../../../../images-dxp/sharepoint-system-setting.png)

2.  *Add* アイコン（![Add](../../../../images-dxp/icon-portlet-add-control.png)）をクリックして、新しい構成の作成を開始します。

3.  新しいリポジトリフォームで、次のフィールドの値を指定します（SharePoint管理者がこの情報を提供できます）。

    **名前：** 構成の名前。

    **許可付与エンドポイント：** OAuth2許可付与の要求に使用されるURL。 SharePoint Onlineでは、URLは次のパターンに従います： `https：//[your-site-name]/sharepoint.com/_layouts/oauthauthorize.aspx`。

    **認証トークンエンドポイント：** ACS URL。 Azure ACSを使用したSharePoint Onlineでは、URLは次のパターンに従います： `https://accounts.accesscontrol.windows.net/[App ID] / tokens / OAuth / 2`。

    **クライアントID：** クライアントID。

    **クライアントシークレット：** クライアントシークレット。

    **スコープ：** トークンに必要な権限セット。 有効なスコープは、アプリのSharePoint登録時に構成されます。

    **テナントID：** テナントID。 SharePoint Onlineを使用している場合は、 *承認トークンエンドポイント*埋め込んだのと同じアプリIDを使用できます。

    **サイトドメイン：** アプリケーション用に登録されたサイトドメイン。

    **リソース：** この値は、使用するACSサービスによって異なります。 Azure ACSを使用したSharePoint Onlineでは、値は次のパターンに従います： `00000003-0000-0ff1-ce00-000000000000 /[your-site-name].sharepoint.com @ [tenant ID]`。

    ![図2：[新しいリポジトリ]フォームでは、リモートSharePointサーバーへのアクセスを指定します。](../../../../images-dxp/sharepoint-new-repo-configuration.png)

4.  [ *保存*クリックします。

驚くばかり\！ SharePointリポジトリが構成されたので、@product@のドキュメントとメディアライブラリにマウントできます。
