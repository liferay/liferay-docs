---
header-id: deleting-file-shortcuts
---

# ファイルのショートカットを削除する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIでは、ファイルのショートカットを削除することもできます。 そのためには、削除するショートカットのIDで [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッド [`deleteFileShortcut`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileShortcut-long-) を使用します。

    deleteFileShortcut(long fileShortcutId)

この方法を使用してファイルショートカットを削除するには、次の手順を実行します。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  ファイルショートカットのIDを取得します。 エンドユーザーが指定したファイルショートカットを削除するのが一般的であるため、リクエストからそのIDを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、これは任意の方法で実行できます。
  
       long fileShortcutId = ParamUtil.getLong(actionRequest, "fileShortcutId");
      

3  サービス参照を使用して、前の手順のファイルショートカットIDで `deleteFileShortcut` メソッドを呼び出します。
  
       _dlAppService.deleteFileShortcut(fileShortcutId);
      </ol> 

この例の完全なコードは、@product@の [`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) クラスの `deleteFileShortcut` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileShortcut` アクションを実装します。 また、この `deleteFileShortcut` メソッドと、残りの `EditFileShortcutMVCActionCommand`には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## 関連トピック

[エンティティをごみ箱に移動する](/docs/7-1/tutorials/-/knowledge_base/t/moving-entities-to-the-recycle-bin)

[ファイルショートカットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-file-shortcuts)

[ファイルのショートカットを更新する](/docs/7-1/tutorials/-/knowledge_base/t/updating-file-shortcuts)
