---
header-id: file-checkin
---

# ファイルのチェックイン

[TOC levels=1-4]

ファイルをチェックアウトして編集した後、他のユーザーが新しいバージョンを表示するには、ファイルをチェックインする必要があります。 そうすると、プライベートの作業コピーにアクセスできなくなります。 次回ファイルがチェックアウトされると、プライベート作業コピーの内容が上書きされます。

ファイルをチェックインするための [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッドは `checkInFileEntry`です。

    checkInFileEntry(long fileEntryId, boolean majorVersion, String changeLog, 
                    ServiceContext serviceContext)

メソッドとそのパラメーターの詳細については、その [Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkInFileEntry-long-boolean-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-)参照してください。 このメソッドは、プライベート作業コピーを使用して、ファイルの新しいバージョンを作成します。 [アップデートファイルチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/updating-files) 説明しているように、 `majorVersion` パラメーターの設定により、ファイルのバージョン番号の増分方法が決まります。

`checkInFileEntry` を使用してファイルをチェックインするには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `checkInFileEntry` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーによるアクションに応じてファイルをチェックインするのが一般的であるため、リクエストからデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法でデータを取得できます。
  
       long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
       boolean majorVersion = ParamUtil.getBoolean(actionRequest, "majorVersion");
       String changeLog = ParamUtil.getString(actionRequest, "changeLog");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
      
  
  `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `checkInFileEntry` メソッドを呼び出します。
  
       _dlAppService.checkInFileEntry(
               fileEntryId, majorVersion, changeLog, serviceContext);
      </ol> 

この例の完全なコードは、@product@の [`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) クラスの `checkInFileEntries` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `checkInFileEntries` メソッドと、残りの `EditFileEntryMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[ファイルのチェックアウト](/docs/7-1/tutorials/-/knowledge_base/t/file-checkout)

[チェックアウトのキャンセル](/docs/7-1/tutorials/-/knowledge_base/t/cancelling-a-checkout)

[ファイルを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-files)
