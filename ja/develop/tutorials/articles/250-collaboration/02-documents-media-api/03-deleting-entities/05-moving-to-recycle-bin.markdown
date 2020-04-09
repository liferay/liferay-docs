---
header-id: moving-entities-to-the-recycle-bin
---

# エンティティをごみ箱に移動する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

エンティティを削除する代わりに、それらを [ごみ箱](/docs/7-1/user/-/knowledge_base/u/restoring-deleted-assets)移動できます。 ごみ箱はDocuments and Media APIの一部ではないことに注意してください。 ごみ箱APIは直接使用できますが、ドキュメントとメディアの場合は、Capabilities APIを使用することをお勧めします。 これは、一部のサードパーティのリポジトリ（SharePointなど）がごみ箱機能をサポートしていないためです。 Capabilities APIを使用すると、作業中のリポジトリがごみ箱をサポートしていることを確認できます。 したがって、エンティティをごみ箱に移動するときは、常に機能APIを使用することがベストプラクティスです。

Capabilities APIを使用してエンティティをごみ箱に移動するには、次の手順に従います。

1.  リポジトリがごみ箱をサポートしていることを確認します。 これを行うには、 [リポジトリオブジェクトの](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api#specifying-repositories) `isCapabilityProvided` メソッドを `TrashCapability.class` を引数として呼び出します。 この例では、</code> ステートメントの条件が `場合にそうします。</p>

<pre><code> if (repository.isCapabilityProvided(TrashCapability.class)) {
     // The code to move the entity to the Recycle Bin
     // You'll write this in the next step
 }
`</pre></li>

2

リポジトリがサポートしている場合は、エンティティをごみ箱に移動します。 これを行うには、最初に `TrashCapability.class` を引数としてリポジトリオブジェクトの `getCapability` メソッドを呼び出して、 `TrashCapability` 参照を取得します。 次に、エンティティをごみ箱に移動する `TrashCapability` メソッドを呼び出します。 たとえば、次のコードは `moveFileEntryToTrash` を呼び出して、ファイルをごみ箱に移動します。
  
       if (repository.isCapabilityProvided(TrashCapability.class)) {
      
           TrashCapability trashCapability = repository.getCapability(TrashCapability.class);
           trashCapability.moveFileEntryToTrash(user.getUserId(), fileEntry);
       }

    他の種類のエンティティをごみ箱に移動するために使用できる方法については、 [`TrashCapability` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/capabilities/TrashCapability.html) を参照してください。</ol>

## 関連トピック

[ファイルを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-files)

[フォルダーを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[ファイルのショートカットを削除する](/docs/7-1/tutorials/-/knowledge_base/t/deleting-file-shortcuts)

[フォルダーとファイルの移動](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
