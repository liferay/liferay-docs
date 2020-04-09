---
header-id: deleting-folders
---

# フォルダーを削除する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

フォルダーの削除は、 [ファイルの削除](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)似ています。 フォルダーを削除するには、2つの方法があります。 各メソッドをクリックして、そのJavadocを表示します。

  - [`deleteFolder(long folderId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-)

  - [`deleteFolder(long repositoryId, long parentFolderId, String name)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-long-java.lang.String-)

どちらの方法を使用するかはユーザー次第です。どちらもフォルダーを削除します。 これらの方法のいずれかを使用してフォルダーを削除するには、以下の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  使用する `deleteFolder *` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーが指定したフォルダーを削除するのが一般的であるため、要求から必要なデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を使用してこれを行いますが、任意の方法でデータを取得できます。 また、次の手順では `deleteFolder（folderId）`フォルダーを削除するため、この例ではフォルダーIDのみを取得することに注意してください。
  
       long folderId = ParamUtil.getLong(actionRequest, "folderId");
      
  
  他の `deleteFolder` メソッドを使用する場合は、リクエストからリポジトリID、親フォルダーID、フォルダー名を取得することもできます。 リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。

3  前の手順のデータで使用するサービス参照の `deleteFolder *` メソッドを呼び出します。 この例では、フォルダーのIDで `deleteFolder` を呼び出します。
  
       _dlAppService.deleteFolder(folderId);
      </ol> 

この例の完全なコードは、@product@の [`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) クラスの `deleteFolders` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `Folder` アクションを実装します。 また、この `deleteFolders` メソッドと、残りの `EditFolderMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[エンティティをごみ箱に移動する](/docs/7-1/tutorials/-/knowledge_base/t/moving-entities-to-the-recycle-bin)

[フォルダーを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-folders)

[フォルダーの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-folders)

[フォルダーのコピー](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)

[ファイルを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)
