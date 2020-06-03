---
header-id: deleting-files
---

# ファイルを削除する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ファイルを削除するには、次の2つの方法があります。

  - [`deleteFileEntry(long fileEntryId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntry-long-)

  - [`deleteFileEntryByTitle(long repositoryId, long folderId, String title)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntryByTitle-long-long-java.lang.String-)

これらの方法は、削除するファイルを識別する方法のみが異なります。 `deleteFileEntryByTitle` の `folderId` および `title` パラメーターの組み合わせは、同じフォルダー内の2つのファイルが名前を共有することができないため、ファイルを一意に識別します。

ファイルを削除するには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  使用する `deleteFileEntry *` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーが指定したファイルを削除するのが一般的であるため、要求から必要なデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を使用してこれを行いますが、任意の方法でデータを取得できます。 また、この例では `deleteFileEntry`使用しているため、ファイルエントリIDのみを取得することに注意してください。
  
       long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
      
  
  代わりに `deleteFileEntryByTitle` を使用する場合は、リクエストからリポジトリID、フォルダーID、およびタイトルを取得することもできます。 リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。

3  前の手順のデータで使用するサービス参照の `deleteFileEntry *` メソッドを呼び出します。 この例では、ファイルエントリのIDで `deleteFileEntry` を呼び出します。
  
       _dlAppService.deleteFileEntry(fileEntryId);
      </ol> 

この例の完全なコードは、@product@の [`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) クラスの `deleteFileEntry` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `deleteFileEntry` メソッドと `EditFileEntryMVCActionCommand`残りの部分には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[エンティティをごみ箱に移動する](/docs/7-1/tutorials/-/knowledge_base/t/moving-entities-to-the-recycle-bin)

[ファイルを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-files)

[ファイルを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-files)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
