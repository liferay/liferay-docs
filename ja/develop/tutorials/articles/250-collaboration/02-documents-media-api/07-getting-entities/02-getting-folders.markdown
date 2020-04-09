---
header-id: getting-folders
---

# フォルダーの取得

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIは、ファイルの取得と同様の方法でフォルダーを取得できます。 主な違いは、検索方法フォルダ含めるかどうかをシステムに伝えるために追加の引数有してもよいことである *フォルダをマウント*。 マウントフォルダは、サイトのデフォルトリポジトリに通常のフォルダとして表示される外部リポジトリ（AlfrescoやSharePointなど）のマウントポイントです。 これにより、ユーザーはリポジトリ間をシームレスに移動できます。 これに対応するため、一部のフォルダー取得メソッドにはブール値パラメーター `includeMountFolders`ます。 このパラメーターを `設定するとtrue` になり、結果にマウントフォルダーが含まれますが、省略するか `設定するとfalse` 除外されます。

たとえば、マウントフォルダーを含むリポジトリから親フォルダーのサブフォルダーのリストを取得するには、 [`getFolders`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolders-long-long-boolean-) メソッドを使用します。

    getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders)

このメソッドを使用して親フォルダーからフォルダーを取得するには、次の手順に従います。 これらの手順の例では、デフォルトのサイトリポジトリのルートフォルダー内のフォルダーを取得します。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  任意の方法でメソッドの引数を設定するために必要なデータを取得します。 この `getFolders` メソッドには、リポジトリID、親フォルダーID、および結果にマウントフォルダーを含めるかどうかを示すブール値が必要です。 デフォルトのサイトリポジトリを指定するには、グループIDをリポジトリIDとして使用できます。 この例では、 [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)介してリクエスト（`javax.portlet.ActionRequest`）からグループIDを取得します。
  
       long groupId = ParamUtil.getLong(actionRequest, "groupId");
      
  
  [`ThemeDisplay`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html)介してグループIDを取得することもできます。 `ThemeDisplay` メソッド `getScopeGroupId（）` 呼び出すと、アプリの現在のサイト（グループ）のIDが取得されます。 詳細については、 [データスコープチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/data-scopes)参照してください。
  
       ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
       long groupId = themeDisplay.getScopeGroupId();
      
  
  この例では、@product@が定数を提供するルートフォルダーを使用するため、親フォルダーIDを取得する必要はありません。 また、ブール値は直接提供することができます---どこかから取得する必要はありません。 リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。

3  前の手順のデータと提供する他の値を使用して、サービス参照の `getFolders` メソッドを呼び出します。 この例では、 `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` を使用して、リポジトリのルートフォルダーを親フォルダーとして指定しています。 また、結果にマウントフォルダーを含めるには、 `true` を使用します。
  
       _dlAppService.getFolders(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, true)
      </ol> 

これは、フォルダを取得するために使用できる多くの方法の1つであることに注意してください。 残りは [`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)リストされてい 。</p> 



## 関連トピック

[Documents and Media APIの使用開始](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api)

[ファイルを取得する](/docs/7-1/tutorials/-/knowledge_base/t/getting-files)

[複数のエンティティタイプの取得](/docs/7-1/tutorials/-/knowledge_base/t/getting-multiple-entity-types)
