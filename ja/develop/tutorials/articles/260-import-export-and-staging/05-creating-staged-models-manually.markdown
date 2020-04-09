---
header-id: creating-staged-models-manually
---

# ステージングモデルの手動作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[Service Builderを使用してステージングモデル](/docs/7-1/tutorials/-/knowledge_base/t/understanding-staged-models#important-attributes-in-staging) を生成することが実用的でない場合があります。 これらの場合、ステージングモデルを手動で作成する必要があります。 [ステージングモデルを構築するためのビジネスロジックの適応](/docs/7-1/tutorials/-/knowledge_base/t/understanding-staged-models#adapting-your-business-logic-to-build-staged-models) セクションを読んで、ステージングモデルを手動で作成することがユースケースにとって有益かどうかを判断してください。

このチュートリアルでは、アセットリンクフレームワーク（のために使用されるフレームワーク@製品どのように見ていきます [に関する資産](/docs/7-1/user/-/knowledge_base/u/defining-content-relationships)）手動で上演モデルを作成します。 このフレームワークはステージングとは別であり、モデルクラスをステージングされたモデルに適応させることができる [ModelAdapterBuilder](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html) フレームワークを活用する方法の例としてのみ参照されます。

アセットリンクはデフォルトではUUIDを提供しません。ただし、ステージングおよびエクスポート/インポートフレームワークで追跡する必要があります。 したがって、ステージングモデルが必要です。 UUIDを提供しないため、Service Builderはアセットリンクのステージングモデルを生成できません。 Asset Linkフレームワークは、Model Adapter Builderを使用して段階的にモデルを作成する必要があります。 このインターフェイスの命名規則は通常、 `Staged[Entity]` 構文に従います。 Asset Linkフレームワークは、 `AssetLink`という汎用エンティティを使用します。

アプリでモデルアダプタービルダーを活用するには、以下の手順に従ってください。

1.  [段階のモデルインターフェイス](/docs/7-1/tutorials/-/knowledge_base/t/understanding-staged-models#staged-model-interfaces) つとモデル固有のインターフェイスを拡張する新しいインターフェイスを作成します。 例えば、
   
        public interface StagedAssetLink extends AssetLink, StagedModel {
       
        }

    このインターフェイスは、モデルが段階的モデルとして適格であるために必要なメソッドを定義する必要があります。 アセットリンクの場合、（特に）エントリUUIDを取得する方法が定義されています。
   
        public String getEntry1Uuid();
       
        public String getEntry2Uuid();

    これらは、後で新しい実装クラスによって実装されます。

2.  新しい `Staged[Entity]`を実装する実装クラスを作成します。 たとえば、Asset Linkフレームワークはこれを行います：
   
        public class StagedAssetLinkImpl implements StagedAssetLink {
       
        }

    このクラスは、エンティティモデルがステージングモデルとして認識されるために必要なロジックを提供します。 以下は、アセットリンクエントリのUUIDを設定するために使用される `StagedAssetLinkImpl` クラスの例のロジックのサブセットです。
   
        public StagedAssetLinkImpl(AssetLink assetLink) {
             _assetLink = assetLink;
       
            ...
       
             populateUuid();
        }
       
        @Override
        public String getEntry1Uuid() {
            if (Validator.isNotNull(_entry1Uuid)) {
                return _entry1Uuid;
            }
       
            populateEntry1Attributes();
       
            return _entry1Uuid;
        }
       
        @Override
        public String getEntry2Uuid() {
            if (Validator.isNotNull(_entry2Uuid)) {
                return _entry2Uuid;
            }
       
            populateEntry2Attributes();
       
            return _entry2Uuid;
        }
       
        protected void populateEntry1Attributes() {
       
            ...
       
            AssetEntry entry1 = AssetEntryLocalServiceUtil.fetchAssetEntry(
                _assetLink.getEntryId1());
       
            ...
       
            _entry1Uuid = entry1.getClassUuid();
        }
       
        protected void populateEntry2Attributes() {
       
            ...
       
            AssetEntry entry2 = AssetEntryLocalServiceUtil.fetchAssetEntry(
                _assetLink.getEntryId2());
       
            ...
       
            _entry2Uuid = entry2.getClassUuid();
        }
       
        protected void populateUuid() {
       
            ...
       
            String entry1Uuid = getEntry1Uuid();
            String entry2Uuid = getEntry2Uuid();
       
            ...
       
            _uuid = entry1Uuid + StringPool.POUND + entry2Uuid;
                }
        }
       
        private AssetLink _assetLink;
        private String _entry1Uuid;
        private String _entry2Uuid;
        private String _uuid;

    このロジックは、アセットリンクエントリを取得し、ステージングフレームワークおよびエクスポート/インポートフレームワークで使用可能なUUIDを設定します。 新しく生成されたUUIDを使用して、アセットリンクモデルクラスをステージングモデルに変換できます。

3.  モデルアダプタービルダークラスを作成し、 [ModelAdapterBuilder](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html) インターフェイスを実装します。 インターフェイスを実装するときは、エンティティタイプとステージングモデルアダプタークラスを定義する必要があります。
   
        public class StagedAssetLinkModelAdapterBuilder
            implements ModelAdapterBuilder<AssetLink, StagedAssetLink> {
       
            @Override
            public StagedAssetLink build(AssetLink assetLink) {
                return new StagedAssetLinkImpl(assetLink);
            }
       
        }

    `StagedAssetLinkModelAdapterBuilder`場合、エンティティタイプは `AssetLink` で、Staged Model Adapterは `StagedAssetLink`です。 アプリも同様のデザインに従う必要があります。 Model Adapter Builderは、 `Staged[Entity]Impl` オブジェクトの新しいインスタンスを出力します。

4.  次に、提供されたAPIを呼び出すために、既存のビジネスロジックを調整する必要があります。 [ModelAdapterUtil](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/ModelAdapterUtil.html) クラスを呼び出して、ステージングモデルアダプターのインスタンスを作成できます。 Asset Linkフレームワークがこれをどのように行うかを以下に示します。
   
        StagedAssetLink stagedAssetLink = ModelAdapterUtil.adapt(
            assetLink, AssetLink.class, StagedAssetLink.class);

    [ステージングモデルデータハンドラー](/docs/7-1/tutorials/-/knowledge_base/t/developing-staged-model-data-handlers)を作成したら、現在のステージング互換エンティティのエクスポート/インポートを開始できます。
   
        StagedModelDataHandlerUtil.exportStagedModel(
            portletDataContext, stagedAssetLink);

    データハンドラーの動作に慣れていない場合は、 [データハンドラーについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-data-handlers) チュートリアルをご覧ください。

驚くばかり\！ ステージングモデルを構築するためにビジネスロジックをうまく適合させました\！
