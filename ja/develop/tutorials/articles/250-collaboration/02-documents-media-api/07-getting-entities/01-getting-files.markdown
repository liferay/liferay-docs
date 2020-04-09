---
header-id: getting-files
---

# ファイルを取得する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ファイルの取得は、Documents and Media APIを使用して実行する最も一般的なタスクの1つです。 ファイルを取得するには、主に2つのメソッドファミリがあります。

  - `getFileEntries`：特定のリポジトリからファイルを取得します。
  - `getGroupFileEntries`：リポジトリに関係なく、サイト（グループ）からファイルを取得します。

これらのメソッドファミリは一般的であるため、それらのメソッドは多くのパラメータを共有しています。

  - `repositoryId`：ファイルを取得するリポジトリのID。 デフォルトのサイトリポジトリを指定するには、 `groupId` （サイトID）を使用します。
  - `folderId`：ファイルを取得するフォルダーのID。 これらのメソッドはフォルダー構造を走査しないことに注意してください。指定されたフォルダーから直接ファイルを取得するだけです。 リポジトリのルートフォルダーを指定するには、定数 `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`使用します。
  - `開始` および `終了`：結果のページに含めるコレクションアイテムの下限と上限をそれぞれ指定する整数。 ページネーションを使用したくない場合は、これらのパラメーターに `QueryUtil.ALL_POS` を使用します。
  - `obc`：コレクションアイテムの注文に使用するコンパレータ。 コンパレータは、コレクションアイテムを並べ替える [`OrderByComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) 実装です。
  - `fileEntryTypeId`：取得するファイルタイプのID。 これを使用して、特定のタイプのファイルを取得します。
  - `mimeTypes`：取得するファイルのMIMEタイプ。 これを使用して、指定されたMIMEタイプのファイルを取得します。 [`ContentTypes`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html)定数を介してMIMEタイプを指定できます。

`obc` パラメーターは `OrderByComparator`実装でなければならないことに注意してください。 独自のコンパレーターを実装できますが、@product@には既にパッケージにいくつかの便利な実装が含まれています [`com.liferay.document.library.kernel.util.comparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/package-summary.html)：

  - `RepositoryModelCreateDateComparator`：作成日でソートします。
  - `RepositoryModelModifiedDateComparator`：変更日でソートします。
  - `RepositoryModelReadCountComparator`：ビューの数でソートします。
  - `RepositoryModelSizeComparator`：ファイルサイズでソートします。
  - `RepositoryModelTitleComparator`：タイトルでソートします。

例として、この [`getFileEntries`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntries-long-long-java.lang.String:A-int-int-com.liferay.portal.kernel.util.OrderByComparator-) メソッドには、 `fileEntryTypeId` （代わりに `mimeTypes` 含む）を除く上記のすべてのパラメーターが含まれます。

    List<FileEntry> getFileEntries(
            long repositoryId, 
            long folderId, 
            String[] mimeTypes, 
            int start, 
            int end, 
            OrderByComparator<FileEntry> obc
    )

このメソッドを使用してファイルのリストを取得するには、次の手順に従います。 これらの手順の例では、サイトのデフォルトリポジトリのルートフォルダーからすべてのPNGイメージを取得し、タイトルでソートしていることに注意してください。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  メソッドの引数を設定するために必要なデータを取得します。 これは任意の方法で実行できます。 次のステップで説明するように、@product@は、この例に必要なすべての引数に対して、グループID以外に定数とコンパレーターを提供します。 この例では、リクエスト（`javax.portlet.ActionRequest`）で [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) を使用してグループIDを取得します。
  
       long groupId = ParamUtil.getLong(actionRequest, "groupId");
      
  
  [`ThemeDisplay`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html)介してグループIDを取得することもできます。 `ThemeDisplay` メソッド `getScopeGroupId（）` 呼び出すと、アプリの現在のサイト（グループ）のIDが取得されます。
  
       ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
       long groupId = themeDisplay.getScopeGroupId();
      
  
  詳細については、 [データスコープチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/data-scopes)参照してください。

3  前の手順のデータを使用して、ファイルを取得するために使用するサービス参照メソッドを呼び出します。 この例では、上記の `getFileEntries` メソッドを、前のステップのグループID、および残りの引数の定数とコンパレーターで呼び出します。
  
       List<FileEntry> fileEntries = 
               _dlAppService.getFileEntries(
                       groupId, 
                       DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
                       new String[] {ContentTypes.IMAGE_PNG}, 
                       QueryUtil.ALL_POS, 
                       QueryUtil.ALL_POS, 
                       new RepositoryModelTitleComparator<>()
               );
      
  
  この例で使用される引数の説明は次のとおりです。
  
        - `groupId`：リポジトリIDとしてグループIDを使用すると、デフォルトのサイトリポジトリで操作が実行されるように指定されます。
      - `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`： [`DLFolderConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 定数 `DEFAULT_PARENT_FOLDER_ID` を使用して、リポジトリのルートフォルダーを指定します。
      - `new String [] {ContentTypes.IMAGE_PNG}`： [`ContentTypes`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html) 定数 `IMAGE_PNG` を使用してPNG画像を指定します。
      - `QueryUtil.ALL_POS`：結果の開始位置と終了位置に [`QueryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/QueryUtil.html) 定数 `ALL_POS` を使用します。 これは、ページネーションをバイパスして、すべての結果を指定します。
      - `new RepositoryModelTitleComparator<>（）`：新しい [`RepositoryModelTitleComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/RepositoryModelTitleComparator.html)作成し、タイトルで結果をソートします。</ol> 

これは、多数の `getFileEntries` および `getGroupFileEntries` メソッドの</code>つにすぎないことを忘れないでください。 そのようなメソッドをすべて表示するには、 [`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)を参照してください。



## 関連トピック

[Documents and Media APIの使用開始](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api)

[フォルダーの取得](/docs/7-1/tutorials/-/knowledge_base/t/getting-folders)

[複数のエンティティタイプの取得](/docs/7-1/tutorials/-/knowledge_base/t/getting-multiple-entity-types)
