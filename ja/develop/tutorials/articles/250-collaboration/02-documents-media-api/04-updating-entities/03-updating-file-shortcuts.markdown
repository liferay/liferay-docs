---
header-id: updating-file-shortcuts
---

# ファイルのショートカットを更新する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIを使用すると、ファイルのショートカット（`FileShortcut` エンティティ）を更新できます。 ショートカットを更新して、それが指すファイルまたはそれが存在するフォルダーを変更できます。 これは、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッド `updateFileShortcut`使用して実行できます。

    updateFileShortcut(long fileShortcutId, long folderId, long toFileEntryId, ServiceContext serviceContext)

このメソッドのパラメーターはすべて必須です。 ショートカットの元の値を保持するには、このメソッドにそれらを提供する必要があります。 パラメーターの詳細については、メソッドの [Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-)参照してください。

この方法を使用してファイルショートカットを更新するには、次の手順を実行します。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `updateFileShortcut` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーが送信したデータでファイルショートカットを更新するのが一般的であるため、リクエストからデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法でデータを取得できます。
  
       long fileShortcutId = ParamUtil.getLong(actionRequest, "fileShortcutId");
       long folderId = ParamUtil.getLong(actionRequest, "folderId");
       long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(
                   DLFileShortcutConstants.getClassName(), actionRequest);
      
  
  フォルダーIDの取得の詳細については、フォルダーの指定に関する [入門チュートリアルの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) セクションを参照してください。 `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `updateFileShortcut` メソッドを呼び出します。
  
       _dlAppService.updateFileShortcut(
               fileShortcutId, folderId, toFileEntryId, serviceContext);
      </ol> 

この例の完全なコードは、@product@の [`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) クラスの `updateFileShortcut` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileShortcut` アクションを実装します。 また、この `updateFileShortcut` メソッドと `つの残りのEditFileShortcutMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[ファイルショートカットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-file-shortcuts)

[ファイルのショートカットを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-file-shortcuts)
