---
header-id: understanding-staged-models
---

# 段階的モデルについて

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Stagingフレームワークを使用してアプリケーションのエンティティを追跡するには、アプリのモデルクラスに [StagedModel](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html) インターフェイスを実装する必要があります。 ステージングプロセス中にエンティティの動作コントラクトを提供します。 たとえば、Bookmarksアプリケーションは、 [BookmarksEntry](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntry.html)と [BookmarksFolder](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksFolder.html)管理し、どちらも `StagedModel` インターフェイスを実装します。 ステージングモデルを構成したら、ステージングモデル（エンティティ）とその参照コンテンツに関する情報をエクスポート/インポートおよびステージングフレームワークに提供するステージングモデルデータハンドラーを作成できます。 詳細については、「 [データハンドラーを理解する](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers) チュートリアルを参照してください。

アプリケーションのエンティティの段階的モデルを作成するには、2つの方法があります。

  - Service Builderを使用して、必要なステージング実装を生成します（[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/generating-staged-models-using-service-builder)）。
  - 必要なステージングインターフェイスを手動で実装する（[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-staged-models-manually)）。

エンティティの段階的なモデルを作成するための手順をステップごとに実行するには、それぞれのチュートリアルにアクセスしてください。

Service Builderを使用してステージングモデルを生成することが、アプリのステージングモデルを作成する最も簡単な方法です。 `service.xml` ファイルで必要な列を定義し、 `uuid` 属性を `true`ます。 次に、Service Builderを実行します。これにより、新しいステージングモデルに必要なコードが生成されます。

実装に必要なモデルロジック上演 *手動* あなたがた場合に行われるべきである **いない** のみ（すなわち、ビジネスロジックが必要としない）ステージングロジックを生成するために必要な特別な属性を使用してモデルを拡張したいが。 この場合、ステージングフレームワークのニーズに合わせてビジネスロジックを調整する必要があります。 これについては後で詳しく説明します。

次に、提供されている段階的なモデルのインターフェースを調べます。

## 段階的モデルのインターフェース

[StagedModel](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html) インターフェースはアプリのモデルクラスによって実装する必要がありますが、これは通常、ベースインターフェースを拡張するインターフェースの1つを実装することにより継承によって行われます。

  - [StagedAuditedModel](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedAuditedModel.html)
  - [StagedGroupedModel](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedGroupedModel.html)

自動グループマッピングやエンティティレベル *最終公開日* 処理など、ステージングフレームワークの特定の機能を使用する場合は、これらを実装する必要があります。 それで、あなたに合ったものをどのように選択しますか？

`StagedAuditedModel` インターフェースは、それを実装するモデルにすべての監査フィールドを提供します。 指定された特定の監査フィールドについては、 [AuditedModel](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/AuditedModel.html) インターフェイスを確認できます。 `StagedAuditedModel` インターフェイスは、グループコンセプトから独立して機能するモデル（会社のモデルと呼ばれることもあります）を対象としています。 モデルがグループモデルの場合、 `StagedAuditedModel` インターフェイスを実装しないでください。

`StagedGroupedModel` インターフェイスは、グループモデルに実装する必要があります。 たとえば、アプリケーションで `groupId` 列が必要な場合、モデルはグループモデルです。 モデルが `StagedGroupModel` と `StagedAuditedModel` 両方の要件を満たしている場合、 `StagedGroupedModel`を実装する必要があります。 モデルは、グループ化されたモデルのニーズを満たしていないが、監査されたモデルのニーズを満たしている場合にのみ、 `StagedAuditedModel` 実装する必要があります。 モデルが `StagedAuditedModel` または `StagedGroupedModel` 要件を満たさない場合、ベース `StagedModel` インターフェースを実装する必要があります。

モデルクラスを拡張する例として、 [BookmarksEntryModel](@app-ref@/collaboration/latest/javadocs/com/liferay/bookmarks/model/BookmarksEntryModel.html) クラスにアクセスして、 `StagedGroupedModel` インターフェイスを拡張できます。これは、ブックマークエントリがグループモデルであるためです。

    public interface BookmarksEntryModel extends BaseModel<BookmarksEntry>,
        ShardedModel, StagedGroupedModel, TrashedModel, WorkflowedModel {

ステージングされたモデルインターフェイスについての理解が深まったので、ステージングで使用される属性と、それらが重要である理由について詳しく説明します。

## ステージングの重要な属性

[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を使用してステージングモデルを生成する場合は、プロジェクトの `service.xml` ファイルで適切な属性を定義する必要があります。 これを行う方法の詳細については、「Service Builder</a> を使用したステージングモデルの生成

チュートリアル」を参照してください。 次に、このプロセスに関する一般的な情報をいくつか学習します。</p> 

ステージングフレームワークで使用される最も重要な属性の1つは、UUID（Universally Unique Identifier）です。 モデルを適格なステージングモデルとして認識するには、Service Builderの `service.xml` ファイルでこの属性を `true` に設定する必要があります。 UUIDは、環境間でエンティティを区別するために使用されます。 UUIDは常に同じであるため、複数のシステムで一意です。 なぜこれがそんなに重要なのですか？

[リモートステージング](/docs/7-1/user/-/knowledge_base/u/enabling-remote-live-staging) を使用しており、ローカルステージングサイトで新しいエンティティを作成し、それをリモートのライブサイトに公開するとします。 ローカルサイトのエンティティを変更して、それらの変更を公開したい場合はどうなりますか？ UUIDがないと、ステージングフレームワークはローカルエンティティとリモートエンティティが同じであることを知る方法がありません。 エンティティを適切に公開するには、ステージングフレームワークで、システム全体で一意に識別されたエンティティがリモートサイト上の元のエンティティを認識して更新する必要があります。 UUIDはそれを提供します。

UUIDに加えて、Service Builderがモデルをステージングモデルとして定義するために、 `service.xml` ファイルで定義する必要があるいくつかの列があります。

  - `companyId`
  - `createDate`
  - `modifiedDate`

ステージングされたグループ化モデルが必要な場合は、 `groupId` および `lastPublishDate` 列も含めます。 段階的な監査モデルが必要な場合は、 `userId` 列と `userName` 列を含めます。

次に、段階的なモデルをゼロから構築する方法を学びます。



## 段階的モデルを構築するためのビジネスロジックの適応

ビジネスロジックでは必要ないかもしれない特別な属性でモデルを拡張したくない場合はどうしますか？ この場合、ステージングフレームワークのニーズに合わせてビジネスロジックを調整する必要があります。 Liferayは [ModelAdapterBuilder](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html) フレームワークを提供します。これにより、モデルクラスを段階的なモデルに適合させることができます。

例として、完全に開発されたアプリがあり、ステージングで動作するように設定したいとします。 ただし、アプリはエンティティのUUIDを必要としないため、提供しません。 ステージングモデルを生成するためだけにUUIDを処理するようにアプリを構成する代わりに、Model Adapter Builderを活用してステージングモデルを構築できます。

段階的なモデルをゼロから構築するもう1つの例は、データベースではなくRESTサービスを使用して属性にアクセスするアプリケーションの場合です。 この種のアプリは、リモートシステムから属性を取得するために開発されているため、データベース駆動型のService Builderに依存するのではなく、ステージングモデルを自分で構築する方が便利です。

モデルクラスを段階的モデルに適合させるには、以下に概説する手順に従います。

1.  `Staged[Entity]` インターフェイスを作成します。これにより、モデル固有のインターフェイス（ `[Entity]`）と適切なステージングモデルインターフェイス（ `StagedModel`）が拡張されます。 このクラスは、ステージングモデルアダプターとして機能します。

2.  `段階[Entity]` インターフェースを実装し、段階モデルとして認識されるエンティティモデルに必要なロジックを提供する `段階[Entity]Impl` クラスを作成します。

3.  `ModelAdapterBuilder<[Entity]、Staged[Entity]>`を実装する `Staged[Entity]ModelAdapterBuilder` クラスを作成します。 このクラスは、元のモデルを新しく作成されたステージモデルアダプターに適合させます。

4.  既存のモデルを調整し、提供されたAPIの1つを呼び出して、エンティティを自動的にエクスポートまたはインポートします。

![図1：ステージングモデルアダプタークラスは、エンティティとステージングモデルのインターフェイスを拡張します。](../../images/staged-model-adapter-diagram.png)

![図2：モデルアダプタービルダーは、モデルのインスタンスを取得し、段階的モデルを出力します。](../../images/model-adapter-builder-diagram.png)

既存のアプリのモデルアダプタービルダーを活用するプロセスをステップごとに実行するには、チュートリアル [ステージングモデルの手動作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-staged-models-manually) をご覧ください。
