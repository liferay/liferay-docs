---
header-id: creating-a-sharepoint-application
---

# SharePointアプリケーションの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このセクションで説明する手順は、SharePoint Onlineに適用されます。 SharePointオンプレミスを使用している場合は、管理者に要件を問い合わせてください。

次の手順に従って、@product@をアプリケーションとしてSharePointインスタンスに登録します。

1.  SharePointインストールのURLに移動します： `https：//[your-site-name].sharepoint.com / _layouts / 15 / appregnew.aspx`。

2.  アプリに以下の情報を提供します。

      - **タイトル：** [ドキュメントとメディア]に表示される名前。
      - **ドメイン名：** アプリケーションのドメイン名とポート（例： `localhost：8228`）
      - **リダイレクトURL：** アプリケーションのURL。 URLはHTTPSを使用する必要があります。

3.  2つの *Generate* ボタンをクリックして、@product@のクライアントIDとクライアントシークレットを生成します。

    次に設定例を示します。

      - **クライアントID：** `1234a56b-7890-1234-5ccc-67d8ea9b0c1c`
      - **クライアントシークレット：** `1ABCDEfGh2IJKLmNoP3QrStuvwX41YzAB + CDEFg20G3 =`
      - **タイトル：** `アプリケーションのタイトル`
      - **アプリドメイン：** `localhost：8228`
      - **リダイレクトURL：** `https：// localhost：8228 / c / document_library / sharepoint / oauth2`

4.  次に、SharePointインスタンスに対する@product@書き込みおよび検索権限を付与する必要があります。 他の権限は無視されます。 `https：//[your-site-name].sharepoint.com / _layouts / 15 / appinv.aspx`ます。

    [ *APP ID* フィールドに、作成したアプリケーションのクライアントIDを入力し、[ *Search*]をクリックします。 アクセス許可要求XMLを構成する方法の詳細については、Microsoftの [ドキュメント](https://docs.microsoft.com/en-us/sharepoint/dev/sp-add-ins/add-in-permissions-in-sharepoint) を参照してください。 次に、SharePointインスタンスに対する書き込みと検索のアクセス許可をアプリケーションに付与する構成例を示します。<AppPermissionRequests> <AppPermissionRequest scope="http://sharepoint/content/sitecollection/web/list" Right="Write" /> <AppPermissionRequest scope="http://sharepoint/search" Right="QueryAsUserIgnoreAppPrincipal" /> </AppPermissionRequests>

5.  権限XMLを指定したら、[ *作成*]をクリックします。

6.  次に、SharePointインストールの *設定* → *サイトアプリの権限* します。 次のようなURLでこのページに直接アクセスすることもできます： `https：//[your-site-name].sharepoint.com / _layouts / 15 / appprincipals.aspx？Scope = Web`。 登録リストでアプリケーションを見つけ、その *アプリID*をメモし* 。 これを使用して、リポジトリー構成を作成します。 次にアプリ識別子の例を示します。</p> 
   
        i:0i.t|ms.sp.ext|6123d38d-2998-4972-9aaa-71a4da9f3a5a@b9c24ab3-ad34-4943-ab57-729d8c329053
       </li> </ol>

すごい\！ これで、@product@がアプリケーションとしてSharePointインスタンスに登録されました。 次に、新しいSharePointリポジトリタイプを作成する必要があります。
