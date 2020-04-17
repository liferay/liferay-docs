---
header-id: getting-multiple-entity-types
---

# 複数のエンティティタイプの取得

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIには、いくつかのエンティティタイプを含むリストを取得するメソッドもあります。 これらのメソッドは、ファイルおよびフォルダーを取得するためにすでに説明したものと同じパラメーターの多くを使用します。 たとえば、 [このメソッド](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntriesAndFileShortcuts-long-long-int-int-int-) は、指定されたリポジトリとフォルダーからファイルとショートカットを取得します。 `ステータス` パラメーターは、 [ワークフロー](/docs/7-1/user/-/knowledge_base/u/workflow) ステータスを指定します。 前と同様に、 `開始` および `終了` パラメーターは、エンティティーのページ付けを制御します。

    getFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, int start, int end)

そのようなメソッドをすべて表示するには、 [`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)を参照してください。

上記の `getFileEntriesAndFileShortcuts` メソッドを使用するには、次の手順に従います。 これらの手順の例では、デフォルトのサイトリポジトリのルートフォルダにあるすべてのファイルとショートカットを取得します。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  任意の方法でメソッドの引数を設定するために必要なデータを取得します。 デフォルトのサイトリポジトリを指定するには、グループIDをリポジトリIDとして使用できます。 この例では、 [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)介してリクエスト（`javax.portlet.ActionRequest`）からグループIDを取得します。
  
       long groupId = ParamUtil.getLong(actionRequest, "groupId");
      
  
  @product@が定数を提供するため、親フォルダーID、ワークフローステータス、開始および終了パラメーターを取得する必要はありません。 次のステップでこれを詳細に示します。

3  前の手順のデータと提供する他の値を使用して、サービス参照メソッドを呼び出します。 この例では、前のステップのグループIDと残りの引数の定数を使用して `getFileEntriesAndFileShortcuts` を呼び出します。
  
       _dlAppService.getFileEntriesAndFileShortcuts(
               groupId, 
               DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
               WorkflowConstants.STATUS_APPROVED, 
               QueryUtil.ALL_POS, 
               QueryUtil.ALL_POS
       )
      
  
  この例で使用される引数の説明は次のとおりです。
  
        - `groupId`：リポジトリIDとしてグループIDを使用すると、デフォルトのサイトリポジトリで操作が実行されるように指定されます。
      - `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`： [`DLFolderConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 定数 `DEFAULT_PARENT_FOLDER_ID` を使用して、リポジトリのルートフォルダーを指定します。
      - `WorkflowConstants.STATUS_APPROVED`： [`WorkflowConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowConstants.html) 定数 `STATUS_APPROVED` を使用して、ワークフローを介して承認されたファイル/フォルダーのみを指定します。
      - `QueryUtil.ALL_POS`：結果の開始位置と終了位置に [`QueryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/QueryUtil.html) 定数 `ALL_POS` を使用します。 これは、ページネーションをバイパスして、すべての結果を指定します。</ol> 



## 関連トピック

[Documents and Media APIの使用開始](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api)

[ファイルを取得する](/docs/7-1/tutorials/-/knowledge_base/t/getting-files)

[フォルダーの取得](/docs/7-1/tutorials/-/knowledge_base/t/getting-folders)
