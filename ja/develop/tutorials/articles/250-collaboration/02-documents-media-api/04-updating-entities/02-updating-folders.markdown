---
header-id: updating-folders
---

# フォルダーの更新

[TOC levels=1-4]

Documents and Media APIを使用すると、</a> フォルダーを別の場所に

コピーまたは移動できます。 ただし、インプレースフォルダ更新のオプションは制限されています。 フォルダーの名前と説明のみを更新できます。 これを行うには、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) method `updateFolder`ます。</p> 

    updateFolder(long folderId, String name, String description, ServiceContext serviceContext)
    

説明を除くすべてのパラメーターは必須です。 この方法とそのパラメータの詳細については、その参照 [のJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFolder-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-)。

このメソッドを使用してフォルダーを更新するには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;
       
   
   詳細については、入門チュートリアルの「サービスリファレンス</a> 取得 」のセクションを参照してください。</p></li> 
   
   2  `updateFolder` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーから送信されたデータでフォルダーを更新するのが一般的であるため、リクエストからデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法でデータを取得できます。
  
       long folderId = ParamUtil.getLong(actionRequest, "folderId");
       String name = ParamUtil.getString(actionRequest, "name");
       String description = ParamUtil.getString(actionRequest, "description");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(
                   DLFolder.class.getName(), actionRequest);
      
  
  フォルダーIDの取得の詳細については、フォルダーの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `updateFolder` メソッドを呼び出します。
  
       _dlAppService.updateFolder(folderId, name, description, serviceContext);
      </ol> 

この例の完全なコードは、@product@の [`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) クラスの `updateFolder` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `Folder` アクションを実装します。 また、この `updateFolder` メソッドと、残りの `EditFolderMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[フォルダーを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-folders)

[フォルダーを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[フォルダーのコピー](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
