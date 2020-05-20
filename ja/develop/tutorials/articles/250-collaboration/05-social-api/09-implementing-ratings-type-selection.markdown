---
header-id: implementing-ratings-type-selection-and-value-transformation
---

# 評価タイプの選択と値変換の実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@には、コンテンツを評価するための3つの異なるメカニズムがあります。

  - いいね
  - 星
  - 親指（上/下）

7.0より前は、ポータルまたはサイトの管理者が評価タイプを選択する方法はありませんでした。これは各アプリにハードコーディングされていました。 7.0以降では、管理者はコントロールパネルとサイト管理を介してアプリのエンティティの評価タイプを選択できます。

  - **ポータル管理者：** は、ポータルのデフォルトの評価タイプを設定できます
  - **サイト管理者：** は、サイトのデフォルトの評価タイプをオーバーライドできます

Liferayアプリはすべて、この機能を活用しています。 アプリもできます。このチュートリアルでは、その方法を示します。

## エンティティの評価タイプの指定

評価を使用するカスタムアプリは、 `PortletRatingsDefinition` インターフェイスを実装するOSGiコンポーネントで評価タイプを定義する必要があります。 このクラスは、評価の使用法（ポートレットとエンティティを指定）とデフォルトの評価タイプ（ポータルとサイトの管理者がオーバーライドできる）を宣言します。

`PortletRatingsDefinition` を実装してアプリの評価タイプを定義するには、次の手順に従います。

1.  クラスをOSGiコンポーネントとして登録し、 `model.class.name` プロパティを、この評価定義を使用するクラスの完全修飾クラス名に設定します。 たとえば、この例の評価定義はブログエントリ用であるため、 `model.class.name` プロパティは `com.liferay.portlet.blogs.model.BlogsEntry`設定されます。
   
        @Component(
            property = {
                "model.class.name=com.liferay.portlet.blogs.model.BlogsEntry"
            }
        )
        public class BlogsPortletRatingsDefinition implements PortletRatingsDefinition {...

2.  `PortletRatingsDefinition` インターフェースには、実装する必要がある2つのメソッドがあります。

      - `getDefaultRatingsType`：エンティティのデフォルトの評価タイプを返します。これは、ポータルおよびサイト管理者がオーバーライドできます。 あなたは経由でこれを行うことができます [`RatingsType` 列挙型](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/RatingsType.html)あなたが使用することができます、 `LIKE`、 `STARS`、または `THUMBS` 評価タイプを設定します：
        
            @Override
            public RatingsType getDefaultRatingsType() {
                return RatingsType.THUMBS;
            }

      - `getPortletId`：エンティティを使用するメインポートレットのポートレットIDを返します。 これは、組み込みポートレットのポートレットIDに対応する多くの定数を定義する [`PortletKeys` enum](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortletKeys.html)を介して行うことができます。 この例では、ブログポートレットを指定します。
        
            @Override
            public String getPortletId() {
                return PortletKeys.BLOGS;
            }

次に、評価タイプ間で値を変換する方法を学習します。

## 評価タイプ間の評価値の変換

評価値は、正規化された値としてデータベースに保存されます。 これにより、基になるデータを変更することなく、さまざまな評価タイプを切り替えることができます。 管理者がエンティティの評価タイプを変更すると、最適な一致が計算されます。 評価タイプ間のデフォルトの変換のリストは次のとおりです。

1.  星から次へ変更する場合：

      - **いいね：** 値3、4、または5の星は、いいね！と見なされます。 1つまたは2つの星の値は省略されます。
      - **親指アップ/ダウン：** 3、4、または5つ星の値は、親指アップと見なされます。 1つまたは2つの星の値は不合格と見なされます。

2.  親指から上下に変更する場合：

      - **いいね：** いいね！はいいねと見なされます。
      - **星：** 親指を立てると1つ星と見なされます。いいね！は5つ星と見なされます。

3.  次のように変更する場合：

      - **星：** ね！は5つ星と見なされます。
      - **サムズアップ/ダウン：** ね！はサムズアップと見なされます。

ただし、新しい評価値を決定するために異なる基準を適用したい場合があります。 これを行うためのメカニズムが存在しますが、保存されている評価値を変更します。 このような変換を定義するには、 [`RatingsDataTransformer` インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/transformer/RatingsDataTransformer.html)を実装するOSGiコンポーネントを作成します。

| **注：** ポータルはデフォルトを提供しません `RatingsDataTransformer` |実装。 そのような実装を提供しない限り、保存された評価|ポータルが既存の値を解釈する間、値は常に同じままです。選択された評価タイプ。

`RatingsDataTransformer`を実装する場合、 `transformRatingsData` メソッドを実装してデータを変換します。 このメソッドの引数には、 `RatingsType` 変数 `fromRatingsType` および `toRatingsType`が含まれます。これらには、それぞれ変換元および変換先の評価タイプが含まれています。 これらの値により、カスタム変換のロジックを記述できます。 このロジックを作成するには、インターフェイス `ActionableDynamicQuery.PerformActionMethod` を匿名の内部クラスとして `transformRatingsData` メソッドに実装し、 `performAction` メソッドを変換のロジックに実装します。

たとえば、 `RatingsDataTransformer`を実装するには、次の手順に従います。

1.  `RatingsDataTransformer`を実装するOSGiコンポーネントクラスを作成します。
   
        @Component
        public class DummyRatingsDataTransformer implements RatingsDataTransformer {...

2.  このクラスでは、 `transformRatingsData` メソッドを実装し` 。 <code>RatingsType` 変数 `fromRatingsType` および `toRatingsType`が含まれていることに注意してください。
   
        @Override
        public ActionableDynamicQuery.PerformActionMethod transformRatingsData(
                final RatingsType fromRatingsType, final RatingsType toRatingsType)
            throws PortalException {
       
        }

3.  `transformRatingsData` メソッドで、インターフェイス `ActionableDynamicQuery.PerformActionMethod` を匿名内部クラスとして実装します。
   
        return new ActionableDynamicQuery.PerformActionMethod() {
       
        };

4.  匿名 `ActionableDynamicQuery.PerformActionMethod` 実装で、 `performAction` メソッドを実装して変換を実行します。 この例では、評価タイプを「いいね」から星に不可逆的に変換し、値を `0`リセットします。 `if` ステートメントは、 `fromRatingsType` と `toRatingsType` 値を使用して、いいね！からスターになったときにのみ変換が行われるように指定します。 変換は、 `RatingsEntry` およびその `LocalServiceUtil`介して実行されます。 [`RatingsEntry` オブジェクト](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/model/RatingsEntry.html)取得した後、その `setScore` メソッドは評価スコアを `0`ます。 [ `RatingsEntryLocalServiceUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/ratings/kernel/service/RatingsEntryLocalServiceUtil.html) メソッド `updateRatingsEntry` は、データベースの `RatingsEntry` 更新します。
   
        @Override
        public void performAction(Object object)
            throws PortalException {
       
            if (fromRatingsType.getValue().equals(RatingsType.LIKE) &&
                toRatingsType.getValue().equals(RatingsType.STARS)) {
       
                RatingsEntry ratingsEntry = (RatingsEntry) object;
       
                ratingsEntry.setScore(0);
       
                RatingsEntryLocalServiceUtil.updateRatingsEntry(
                    ratingsEntry);
            }
        }

この例の完全なクラスは次のとおりです。

    @Component
    public class DummyRatingsDataTransformer implements RatingsDataTransformer {
        @Override
        public ActionableDynamicQuery.PerformActionMethod transformRatingsData(
                final RatingsType fromRatingsType, final RatingsType toRatingsType)
            throws PortalException {
    
            return new ActionableDynamicQuery.PerformActionMethod() {
    
                @Override
                public void performAction(Object object)
                    throws PortalException {
    
                    if (fromRatingsType.getValue().equals(RatingsType.LIKE) &&
                        toRatingsType.getValue().equals(RatingsType.STARS)) {
    
                        RatingsEntry ratingsEntry = (RatingsEntry) object;
    
                        ratingsEntry.setScore(0);
    
                        RatingsEntryLocalServiceUtil.updateRatingsEntry(
                            ratingsEntry);
                    }
                }
            };
        }
    
    }

アプリのエンティティにレーティングタイプの選択と値タイプの変換を実装したら、コントロールパネルから *構成* → *インスタンス設定*に進み、 *ソーシャル* タブを選択して、デフォルトのレーティングタイプ値を構成できます。 サイトのデフォルト値を上書きするには、サイト管理→ *構成* → *サイト設定*に進み、 *ソーシャル* タブを選択します。

よくやった\！ これで、エンティティの評価タイプを設定する方法がわかりました。 また、評価データトランスフォーマーを実装する方法も知っています。 親指で敬礼します！

## 関連トピック

[資産フレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)

[資産の評価](/docs/7-1/tutorials/-/knowledge_base/t/rating-assets)
