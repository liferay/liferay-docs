---
header-id: creating-file-shortcuts
---

# ファイルショートカットの作成

[TOC levels=1-4]

Documents and Mediaライブラリでファイルショートカット（`FileShortcut` エンティティ）を作成するには、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) インターフェイスの `addFileShortcut` メソッドを使用する必要があります。

    addFileShortcut(long repositoryId, 
                    long folderId, 
                    long toFileEntryId, 
                    ServiceContext serviceContext)

参照してください [このメソッドのJavadocの](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-) パラメータの説明について。 このメソッドのパラメーターはすべて必須です。

ショートカットを作成するときは、次のことに注意してください。

  - そのファイルとその結果のショートカットが同じポータルインスタンスにある場合、別のサイトのファイルへのショートカットを作成できます。
  - フォルダーのショートカットを作成することはできません。
  - ショートカットは、デフォルトのサイトリポジトリにのみ存在できます。 外部リポジトリのID（SharePointリポジトリなど）を使用して `addFileShortcut` を呼び出そうとすると、操作は失敗します。 すべてのリポジトリに同じ機能があるわけではないため、Documents and Media APIはすべてのリポジトリの共通の分母であるファイルとフォルダーのみをサポートします。

`DLAppService` メソッド `addFileShortcut`を使用してファイルショートカットを作成するには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `addFileShortcut` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーから送信されたデータを使用してファイルショートカットを作成するのが一般的であるため、リクエストからデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法でデータを取得できます。
  
       long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
       long folderId = ParamUtil.getLong(actionRequest, "folderId");
       long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(
                           DLFileShortcutConstants.getClassName(), actionRequest);
      
  
  リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `addFileShortcut` メソッドを呼び出します。
  
       FileShortcut fileShortcut = _dlAppService.addFileShortcut(
                                           repositoryId, folderId, toFileEntryId, 
                                           serviceContext);
      
  
  このメソッドは `FileShortcut` オブジェクトを返します`この例では、後で使用するために変数に設定します。 ただし、これを行う必要はありません。</p></li>
</ol>

<p spaces-before="0">この例の完全なコードは、@product@の <a href="https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java"><code>EditFileShortcutMVCActionCommand`</a> クラスの `updateFileShortcut` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileShortcut` アクションを実装します。 また、この `updateFileShortcut` メソッドと `つの残りのEditFileShortcutMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。
  
  

## 関連トピック

[ファイルのショートカットを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-file-shortcuts)

[ファイルのショートカットを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-file-shortcuts)
