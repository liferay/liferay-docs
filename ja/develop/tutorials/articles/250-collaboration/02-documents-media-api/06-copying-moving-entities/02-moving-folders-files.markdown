---
header-id: moving-folders-and-files
---

# フォルダーとファイルの移動

[TOC levels=1-4]

移動操作は、 [コピー操作](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)よりも柔軟です。 コピーはフォルダでのみ機能し、リポジトリ間でコピーすることはできません。 ただし、移動操作は、リポジトリ内またはリポジトリ間でファイルおよびフォルダを操作します。

| **注：** リポジトリーの実装によっては、予期しない結果が生じる場合があります|リポジトリ間でフォルダを移動するときの動作。 フォルダーを移動すると移動します|フォルダー内の各アイテムの個別の移動操作によるコンテンツ。 いくつかで|リポジトリの実装、移動サブ操作が失敗した場合、親の移動|操作も失敗します。 他のリポジトリ実装では、|の結果他のサブオペレーションが失敗しても、成功したサブオペレーションは残ります。フォルダ全体の完全な移動。

フォルダーを移動するには、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッド `moveFolder`使用します。

    moveFolder(long folderId, long parentFolderId, ServiceContext serviceContext)

この方法とそのパラメータの詳細については、その参照 [のJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFolder-long-long-com.liferay.portal.kernel.service.ServiceContext-)。 このメソッドは `copyFolder`と似ていますが、フォルダーの名前や説明を変更することはできず、リポジトリー間でフォルダーを移動できます。 フォルダの内容は、フォルダとともに移動します。

ファイルを移動する操作は、フォルダーを移動する操作とほぼ同じです。 ファイルを移動するには、 `DLAppService` メソッド `moveFileEntry`使用します。

    moveFileEntry(long fileEntryId, long newFolderId, ServiceContext serviceContext)

この方法とそのパラメータの詳細については、その参照 [のJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFileEntry-long-long-com.liferay.portal.kernel.service.ServiceContext-)。

以下の手順に従って、 `moveFolder` および `moveFileEntry` を使用して、それぞれフォルダーとファイルを移動します。 この例では、手順を示すためだけに両方を実行していますが、

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  メソッドの引数を設定するために必要なデータを取得します。 通常、フォルダーとファイルの移動はユーザーのアクションに応じて行われるため、リクエストからデータを取得できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法でデータを取得できます。
  
       // Get the folder IDs
       long folderId = ParamUtil.getLong(actionRequest, "folderId");
       long newFolderId = ParamUtil.getLong(actionRequest, "newFolderId");
      
       // Get the file ID
       long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(
               DLFileEntry.class.getName(), actionRequest);
      
  
  フォルダーIDの取得の詳細については、フォルダーの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  サービス参照のメソッドを呼び出します。 この例では、 `moveFolder` を呼び出して、フォルダー（`folderId`）を別のフォルダー（`newFolderId`）に移動します。 次に、 `moveFileEntry` を呼び出して、ファイル（`fileEntryId`）を同じ宛先フォルダーに移動します。
  
       _dlAppService.moveFolder(folderId, newFolderId, serviceContext);
      
       _dlAppService.moveFileEntry(fileEntryId, newFolderId, serviceContext);
      </ol> 



## 関連トピック

[フォルダーのコピー](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)
