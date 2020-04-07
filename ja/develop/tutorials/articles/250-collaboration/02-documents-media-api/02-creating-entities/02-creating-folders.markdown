---
header-id: creating-folders
---

# フォルダーを作成する

[TOC levels=1-4]

Documents and Mediaライブラリにフォルダー（`フォルダー` エンティティ）を作成するには、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) インターフェースの `addFolder` メソッドを使用する必要があります。

    addFolder(long repositoryId, 
            long parentFolderId, 
            String name, 
            String description, 
            ServiceContext serviceContext)

参照してください [このメソッドのJavadocの](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFolder-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-) パラメータの説明について。 `description` パラメーターはオプションです。

`DLAppService` メソッド `addFolder`フォルダーを作成するには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `addFolder` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーから送信されたデータを使用してフォルダーを作成するのが一般的であるため、リクエストからデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)ます。
  
       long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
       long parentFolderId = ParamUtil.getLong(actionRequest, "parentFolderId");
       String name = ParamUtil.getString(actionRequest, "name");
       String description = ParamUtil.getString(actionRequest, "description");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(
                   DLFolder.class.getName(), actionRequest);
      
  
  リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `addFolder` メソッドを呼び出します。
  
       Folder folder = _dlAppService.addFolder(
                               repositoryId, parentFolderId, name, description, 
                               serviceContext);
      
  
  このメソッドは `Folder` オブジェクトを返します`この例では、後で使用するために変数に設定します。 ただし、これを行う必要はありません。</p></li>
</ol>

<p spaces-before="0">この例の完全なコードは、@product@の <a href="https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java"><code>EditFolderMVCActionCommand`</a> クラスの `updateFolder` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `Folder` アクションを実装します。 また、この `updateFolder` メソッドと、残りの `EditFolderMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。
  
  

## フォルダーと外部リポジトリ

外部リポジトリ（SharePointなど）のプロキシとして機能するフォルダを作成することにより、サイトのデフォルトリポジトリ内にそのリポジトリを効果的にマウントできます。 ユーザーがこの特別なフォルダーに入ると、外部リポジトリーが表示されます。 これらのフォルダーは *マウントポイント*と呼ばれます。 [サービスコンテキストの](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext) `mountPoint` 属性を `true`、そのサービスコンテキストを `addFolder` メソッドで使用することにより、API経由で作成できます。

    serviceContext.setAttribute("mountPoint", true);
    

このようなフォルダーの `repositoryId` は、フォルダーが存在するリポジトリではなく、フォルダーが指す外部リポジトリを示していることに注意してください。 また、マウントポイントフォルダーは、デフォルトのサイトリポジトリにのみ存在できます。



## 関連トピック

[フォルダーの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-folders)

[フォルダーを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[フォルダーのコピー](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
