---
header-id: file-checkout
---

# ファイルのチェックアウト

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ファイルをチェックアウトすると、次のようになります。

  - ユーザーと管理者のみがアクセスできるファイルのプライベート作業コピーが作成されます。 ファイルを再度チェックインするか、変更をキャンセルするまで、行った編集はプライベートの作業コピーに保存されます。

  - 他のユーザーは、ファイルのどのバージョンも変更または編集できません。 この状態は、変更をキャンセルまたはチェックインするまで残ります。

ファイルをチェックアウトするためのメインの [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッドは、この `checkOutFileEntry` メソッドです。

    checkOutFileEntry(long fileEntryId, ServiceContext serviceContext)

このメソッドが例外をスローする場合、チェックアウトが失敗したと想定して、操作を繰り返す必要があります。 メソッドとそのパラメーターの詳細については、その [Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkOutFileEntry-long-com.liferay.portal.kernel.service.ServiceContext-)参照してください。

この方法を使用してファイルをチェックアウトするには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  `checkOutFileEntry` メソッドの引数を設定するために必要なデータを取得します。 エンドユーザーによるアクションに応じてファイルをチェックアウトするのが一般的であるため、リクエストからデータを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法でデータを取得できます。
  
       long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
      
       ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
      
  
  `ServiceContext`詳細については、チュートリアル [ServiceContextについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)参照してください。

3  前の手順のデータを使用して、サービス参照の `checkOutFileEntry` メソッドを呼び出します。
  
       _dlAppService.checkOutFileEntry(fileEntryId, serviceContext);
      </ol> 

この例の完全なコードは、@product@の [`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) クラスの `checkOutFileEntries` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `checkOutFileEntries` メソッドと、残りの `EditFileEntryMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## チェックアウトの微調整

`ServiceContext` パラメーターで以下の属性を設定することにより、チェックアウトの実行方法を制御できます。

  - `manualCheckInRequired`：デフォルトでは、ユーザーが編集すると、システムはファイルを自動的にチェックアウト/チェックインします。 この属性を `true` 設定すると、これを防ぐことができるため、手動でのチェックアウトとチェックインが必要になります。

  - `existingDLFileVersionId`：システムは通常、異なるチェックアウト/チェックインシーケンスでプライベート作業コピーを再利用します。 一度に1人のユーザーしかプライベート作業コピーにアクセスできないため、編集が競合する可能性はほとんどありません。 システムが毎回新しいプライベート作業コピーを作成するように強制するには、この属性を省略するか、 `0`設定します。

  - `fileVersionUuid`：これは [ステージング](/docs/7-1/user/-/knowledge_base/u/staging-content-for-publication)で使用されますが、通常の使用では無視できます。 この属性を設定すると、システムは指定されたUUIDで新しいプライベートワーキングコピーバージョンを作成します。

これらの属性を設定するには、 `ServiceContext` メソッド [`setAttribute（String name、Serializable value）`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html#setAttribute-java.lang.String-java.io.Serializable-)ます。 以下に、 `manualCheckInRequired` 属性を `true`に設定する例を示します。

    serviceContext.setAttribute("manualCheckInRequired", Boolean.TRUE)
    



## 関連トピック

[ファイルのチェックイン](/docs/7-1/tutorials/-/knowledge_base/t/file-checkin)

[チェックアウトのキャンセル](/docs/7-1/tutorials/-/knowledge_base/t/cancelling-a-checkout)

[ファイルを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-files)
