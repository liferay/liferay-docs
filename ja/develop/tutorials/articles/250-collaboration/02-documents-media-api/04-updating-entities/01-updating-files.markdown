---
header-id: updating-files
---

# ファイルを更新する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ファイルの更新は、</a>作成する

よりも少し複雑です。 これは、更新操作がファイルのメタデータとコンテンツを処理する方法によるものです。 ファイルのコンテンツのみを更新するには、ファイルの既存のメタデータも提供する必要があります。 そうしないと、更新操作でメタデータが失われる可能性があります。 しかし、その反対は真実ではありません。 コンテンツを再提供せずにファイルのメタデータを変更できます。 このような更新では、ファイルのコンテンツがファイルの新しいバージョンに自動的にコピーされます。 これを覚えやすくするために、ファイルを更新するときは次の規則に従ってください。</p> 

  - 常にすべてのメタデータを提供します。
  - ファイルの内容は、変更する場合にのみ提供してください。

[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) は、ファイルの更新に使用できる3つの `updateFileEntry` メソッドがあります。 これらのメソッドは、ファイルコンテンツのタイプのみが異なります。 各メソッドをクリックして、そのパラメーターの詳細な説明を含むJavadocを表示します。

  - [`updateFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-byte:A-com.liferay.portal.kernel.service.ServiceContext-)

  - [`updateFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

  - [`updateFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

これらの方法を使用するときは、次のことに注意してください。

  - 元のファイルのタイトルと説明を保持するには、これらのパラメーターを `updateFileEntry`指定する必要があります。 それらを省略すると、既存のタイトルと説明が削除されます。

  - ユーザーが指定する場合は `ヌル` （例えば、ファイルの内容の代わりに、 `バイト`、 `ファイル`、または `ある`）、更新が自動的にファイルの既存のコンテンツを使用しています。 これは、ファイルのメタデータを更新する場合にのみ行います。

  - `majorVersion` パラメーターに `false` を使用すると、更新によりファイルのバージョンが `0.1` 増加します（たとえば、 `1.0` から `1.1`）。 あなたが使用している場合は `、真の` 更新インクリメント次のファイルのバージョン、このパラメータに `.0` の値（例えば、から `1.0` に `2.0`、 `1.1` に `2.0`、等）。

ファイルを更新するには、次の手順に従います。 これらの手順の例では、 `InputStream`を含む `updateFileEntry` メソッドを使用していますが、必要に応じて他のメソッドに例を適用できます。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;
       
   
   詳細については、入門チュートリアルの「サービスリファレンス</a> 取得 」のセクションを参照してください。</p></li> 
   
   2  `updateFileEntry` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーから送信されたデータでファイルを更新するのが一般的であるため、リクエストからデータを抽出できます。 この例では、 [`UploadPortletRequest`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upload/UploadPortletRequest.html) および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を使用してこれを行いますが、任意の方法でデータを取得できます。
  
       long repositoryId = ParamUtil.getLong(uploadPortletRequest, "repositoryId");
       long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
       String sourceFileName = uploadPortletRequest.getFileName("file");
       String title = ParamUtil.getString(uploadPortletRequest, "title");
       String description = ParamUtil.getString(uploadPortletRequest, "description");
       String changeLog = ParamUtil.getString(uploadPortletRequest, "changeLog");
       boolean majorVersion = ParamUtil.getBoolean(uploadPortletRequest, "majorVersion");
      
       try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {
      
           String contentType = uploadPortletRequest.getContentType("file");
           long size = uploadPortletRequest.getSize("file");
      
           ServiceContext serviceContext = ServiceContextFactory.getInstance(
                   DLFileEntry.class.getName(), uploadPortletRequest);
       }
      
  
  リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `updateFileEntry` メソッドを呼び出します。 この例は、前のステップのtry-with-resourcesステートメント内で実行することに注意してください。
  
       try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {
      
           ...
      
           FileEntry fileEntry = _dlAppService.updateFileEntry(
                                   fileEntryId, sourceFileName, contentType, title,
                                   description, changeLog, majorVersion, inputStream, size,
                                   serviceContext);
       }
      
  
  このメソッドは `FileEntry` オブジェクトを返します`この例では、後で使用するために変数に設定します。 ただし、これを行う必要はありません。</p></li>
</ol>

<p spaces-before="0">この例の完全なコードは、@product@の <a href="https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java"><code>EditFileEntryMVCActionCommand`</a> クラスの `updateFileEntry` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `updateFileEntry` メソッドと、残りの `EditFileEntryMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。
  
  

## 関連トピック

[ファイルを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-files)

[ファイルを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
