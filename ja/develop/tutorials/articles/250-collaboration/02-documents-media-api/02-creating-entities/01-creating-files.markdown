---
header-id: creating-files
---

# ファイルを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Mediaライブラリでファイル（`FileEntry` エンティティ）を作成するには、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) インターフェイスの `addFileEntry` メソッドを使用する必要があります。 そのような方法は3つあり、ファイルの作成に使用されるデータ型によって異なります。 各メソッドをクリックすると、メソッドとそのパラメーターの詳細な説明が表示されます。

  - [`addFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-byte:A-com.liferay.portal.kernel.service.ServiceContext-)

  - [`addFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

  - [`addFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

次の引数はオプションです。

  - `sourceFileName`：これは、アップロードされたファイルを追跡します。 そのファイルに拡張子がある場合、コンテンツタイプを推測します。
  - `mimeType`：デフォルトはバイナリストリームです。 省略すると、Documents and Mediaはファイル拡張子からタイプを推測しようとします。
  - `description`：ポータルに表示するファイルの説明。
  - `changeLog`：ファイルバージョンの説明。
  - `は` および `サイズ`： `InputStream`をとるメソッドでは、 `is` パラメーターに `null` を使用できます。 ただし、これを行う場合は、 `サイズ` パラメーターに `0` を使用する必要があります。

`DLAppService` メソッド `addFileEntry`を使用してファイルを作成するには、次の手順に従います。 これらの手順では、 `InputStream`を含むメソッドを使用することに注意してください。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `addFileEntry` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーから送信されたデータを使用してファイルを作成するのが一般的であるため、リクエストからデータを抽出できます。 この例では、 [`UploadPortletRequest`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upload/UploadPortletRequest.html) および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を使用してこれを行いますが、任意の方法でデータを取得できます。
  
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

3  前の手順のデータを使用して、サービス参照の `addFileEntry` メソッドを呼び出します。 この例は、前のステップのtry-with-resourcesステートメント内で実行することに注意してください。
  
       try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {
      
           ...
      
           FileEntry fileEntry = _dlAppService.addFileEntry(
                               repositoryId, folderId, sourceFileName, contentType, title, 
                               description, changeLog, inputStream, size, serviceContext);
       }
      
  
  このメソッドは `FileEntry` オブジェクトを返します`この例では、後で使用するために変数に設定します。 ただし、これを行う必要はありません。</p></li>
</ol>

<p spaces-before="0">この例の完全なコードは、@product@の <a href="https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java"><code>EditFileEntryMVCActionCommand`</a> クラスの `updateFileEntry` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `updateFileEntry` メソッドと、残りの `EditFileEntryMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。
  
  

## 関連トピック

[ファイルを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-files)

[ファイルを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)

[フォルダーを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-folders)

[ファイルショートカットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-file-shortcuts)
