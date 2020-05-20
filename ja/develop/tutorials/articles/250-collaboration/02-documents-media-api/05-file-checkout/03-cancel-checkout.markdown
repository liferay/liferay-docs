---
header-id: cancelling-a-checkout
---

# チェックアウトのキャンセル

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIでは、チェックアウトをキャンセルすることもできます。 この操作には注意が必要です。チェックアウト以降に行われた編集はすべて破棄されます。 チェックアウトをキャンセルする場合は、 [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッド `cancelCheckOut`使用してキャンセルします。

    cancelCheckOut(long fileEntryId)

この方法とパラメータの詳細については、その参照 [のJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#cancelCheckOut-long-)。 このメソッドをエラーなしで呼び出した場合、プライベート作業コピーを破棄してファイルのロックを解除したと安全に想定できます。 これで、他のユーザーがファイルをチェックアウトして編集できるようになります。

チェックアウトをキャンセルするには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  チェックアウトをキャンセルするファイルのIDを取得します。 ユーザーのアクションに応じてチェックアウトをキャンセルするのが一般的であるため、リクエストからファイルIDを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、任意の方法で取得できます。
  
       long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
      

3  ファイルのIDを使用して、サービス参照の `cancelCheckOut` メソッドを呼び出します。
  
       _dlAppService.cancelCheckOut(fileEntryId);
      </ol> 

この例の完全なコードは、@product@の [`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) クラスの `cancelFileEntriesCheckOut` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `cancelFileEntriesCheckOut` メソッドと `EditFileEntryMVCActionCommand`残りの部分には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[ファイルのチェックアウト](/docs/7-1/tutorials/-/knowledge_base/t/file-checkout)

[ファイルのチェックイン](/docs/7-1/tutorials/-/knowledge_base/t/file-checkin)

[ファイルを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-files)
