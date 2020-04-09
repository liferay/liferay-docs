---
header-id: deleting-file-versions
---

# ファイルバージョンの削除

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ファイルが変更されると、Documents and Mediaは新しいファイルバージョンを作成し、以前のバージョンをそのまま残します。 時間の経過とともに、古いファイルバージョンが蓄積され、貴重なストレージスペースを消費する可能性があります。 幸いなことに、Documents and Media APIを使用してそれらを削除できます。 ただし、ファイルバージョンをごみ箱に送信する方法はないことに注意してください。一度削除すると、それらは永久に消えてしまいます。

[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) メソッド `deleteFileVersion`ファイルバージョンを削除できます。

    deleteFileVersion(long fileEntryId, String version)

参照してください [このメソッドのJavadocの](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileVersion-long-java.lang.String-) パラメータの説明について。

`deleteFileVersion` を使用してファイルバージョンを削除するには、次の手順に従います。

1.  `DLAppService`への参照を取得します。
   
        @Reference
        private DLAppService _dlAppService;

    詳細については、入門チュートリアルの「サービスリファレンス</a> 取得

」のセクションを参照してください。</p></li> 
   
   2  削除するファイルのファイルエントリIDとバージョンを取得します。 エンドユーザーが指定したファイルバージョンを削除するのが一般的であるため、リクエストからこれらのパラメーターを抽出できます。 この例では、 `javax.portlet.ActionRequest` および [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html)を介してこれを行いますが、これは任意の方法で実行できます。
  
       long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
       String version = ParamUtil.getString(actionRequest, "version");
      

3  サービス参照を使用して、前の手順のファイルエントリIDとバージョンで `deleteFileVersion` メソッドを呼び出します。
  
       _dlAppService.deleteFileVersion(fileEntryId, version);
      </ol> 

この例の完全なコードは、@product@の [`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) クラスの `deleteFileEntry` メソッドにあります。 このクラスは、Documents and Media APIを使用して、Documents and Mediaアプリがサポートするほぼすべての `FileEntry` アクションを実装します。 また、この `deleteFileEntry` メソッドと `EditFileEntryMVCActionCommand`残りの部分には、Documents and Mediaアプリの特定のニーズに合う追加のロジックが含まれていることに注意してください。



## ファイルバージョンの特定

ファイルには多くのバージョンが存在する可能性があるため、プログラムで削除する古いバージョンを特定すると便利です。 [`FileVersionVersionComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/FileVersionVersionComparator.html)これを行うことができます。

次の例では、このようなコンパレータを作成し、 `比較` メソッドを使用してファイルの古いバージョンを識別します。 コードは、 [承認された各バージョンの](/docs/7-1/user/-/knowledge_base/u/workflow) ファイル（`fileVersion`）を反復処理することにより、そうします。 各反復は、 `compare` メソッドを使用して、同じファイルの現在のバージョン（`fileEntry.getVersion（）`）に対してそのファイルバージョン（`fileVersion.getVersion（）`）をテストします。 この比較が `0`よりも大きい場合、反復のファイルバージョン（`fileVersion`）は古く、 `deleteFileVersion`によって削除されます。

    FileVersionVersionComparator comparator = new FileVersionVersionComparator();
    
    for (FileVersion fileVersion: fileEntry.getVersions(WorkflowConstants.STATUS_APPROVED)) {
    
        if (comparator.compare(fileEntry.getVersion(), fileVersion.getVersion()) > 0) {
    
            dlAppService.deleteFileVersion(fileVersion.getFileEntryId(), fileVersion.getVersion());
        }
    }
    



## 関連トピック

[ファイルを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)

[ファイルのショートカットを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-file-shortcuts)

[フォルダーを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[エンティティをごみ箱に移動する](/docs/7-1/tutorials/-/knowledge_base/t/moving-entities-to-the-recycle-bin)
