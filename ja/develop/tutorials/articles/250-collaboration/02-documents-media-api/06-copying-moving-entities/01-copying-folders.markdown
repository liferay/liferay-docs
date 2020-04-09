---
header-id: copying-folders
---

# フォルダーのコピー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIを使用すると、リポジトリ内のフォルダーをコピーできます。 ただし、異なるリポジトリ間でフォルダーをコピーすることはできません。 また、フォルダーをコピーすると、その内容もコピーされることに注意してください。

フォルダーをコピーするには、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) method `copyFolder`使用します。

    copyFolder(long repositoryId, long sourceFolderId, long parentFolderId, String name, 
            String description, ServiceContext serviceContext)

メソッドとそのパラメーターの詳細については、その [Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#copyFolder-long-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-)参照してください。

このメソッドを使用してフォルダーをコピーするには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `copyFolder` メソッドの引数を設定するために必要なデータを取得します。 これを行う方法は、ユースケースによって異なります。 この例のコピー操作は、デフォルトのサイトリポジトリで行われ、フォルダーの既存の名前と説明を保持します。 したがって、フォルダのグループID（デフォルトのサイトリポジトリを指定するため）、名前、および説明が必要です。 また、この例の宛先フォルダーはリポジトリのルートフォルダーであるため、親フォルダーIDは不要であることに注意してください。@product@は、リポジトリのルートフォルダーを指定するための定数を提供します。
  
  次のコードでは、 [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) はリクエスト（`javax.portlet.ActionRequest`）からフォルダーのIDを取得し、サービス参照の [`getFolder`](/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolder-long-) メソッドは対応するフォルダーオブジェクトを取得します。 フォルダの `getGroupId（）`、 `のgetName（）`、及び `のgetDescription（）` の方法、次いで、それぞれのフォルダのグループID、名前、説明を得ます。
  
       long folderId = ParamUtil.getLong(actionRequest, "folderId");
      
       Folder folder = _dlAppService.getFolder(folderId);
       long groupId = folder.getGroupId();
       String folderName = folder.getName();
       String folderDescription = folder.getDescription();
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(
                   DLFolder.class.getName(), actionRequest);
      
  
  リポジトリIDとフォルダIDの取得の詳細については、リポジトリとフォルダの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `copyFolder` メソッドを呼び出します。 この例では、 [`DLFolderConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 定数 `DEFAULT_PARENT_FOLDER_ID` を使用して、リポジトリのルートフォルダーを宛先フォルダーとして指定します。
  
       _dlAppService.copyFolder(
               groupId, folderId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
               folderName, folderDescription, serviceContext);
      </ol> 

これらの値は、コピー操作に合わせて変更できることに注意してください。 たとえば、コピーがデフォルトのサイトリポジトリ以外のリポジトリで行われる場合、グループIDの代わりにそのリポジトリのIDを指定します。 別の宛先フォルダーを指定したり、新しいフォルダーの名前や説明を変更したりすることもできます。



## 関連トピック

[Documents and Media APIの使用開始](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api)

[サービスコンテキストについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)

[フォルダーを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-folders)

[フォルダーの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-folders)

[フォルダーを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
