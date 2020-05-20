---
header-id: generating-results-summaries
---

# 結果サマリーの生成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">ゲストブックの検索とインデックス作成を有効にする</p><p>手順5/6</p>
</div>

検索アプリケーションとAsset Publisherアプリケーションは、検索エンジンから取得した結果を表示する必要があります。 `ModelSummaryContributor`実装して、要約されたコンテンツを制御します。

要約は、一般的に表示できるエンティティのドキュメントのテキストベースの要約版です。 エンティティのデータの主要部分を組み合わせて作成し、ユーザーが検索エンティティを参照して必要なエンティティを見つけられるようにします。

`GuestbookModelSummaryContributor`作成します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelSummaryContributor.class
    )
    public class GuestbookModelSummaryContributor
        implements ModelSummaryContributor {
    
        @Override
        public Summary getSummary(
            Document document, Locale locale, String snippet) {
    
            Summary summary = createSummary(document);
    
            summary.setMaxContentLength(200);
    
            return summary;
        }
    
        private Summary createSummary(Document document) {
            String prefix = Field.SNIPPET + StringPool.UNDERLINE;
    
            String title = document.get(prefix + Field.TITLE, Field.TITLE);
    
            return new Summary(title, StringPool.BLANK);
        }
    
    }

最初に `getSummary` をオーバーライドし、返されたサマリーの最大サマリー長を設定します。 値 `200` はLiferay標準です。 `createSummary`というユーティリティメソッドでサマリー作成を制御します。 2つの定数、 `Field.SNIPPET` および `Stringpool.UNDERLINE`を使用して、 `プレフィックス` 変数を作成します。 `snippet_title` フィールドは、 `document.get` 呼び出しから返され、サマリーに追加されます。 スニペットフィールドを使用すると、次の2つの利点があります。

1.  スニペットテキストを強調表示して、一致するキーワードを強調できます。

2.  スニペットテキストは、検索アプリケーションによって自動的に短縮されるため、フィールドのテキストの適切な部分が検索結果に表示されます。

ゲストブックのタイトルはおそらく短いため、強調表示の動作のみがゲストブックのタイトルフィールドに役立ちます。 長いフィールド（ `コンテンツ` フィールドなど）の場合、クリッピング動作がより便利です。 追加の強調表示動作は、 [portal.properties](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Lucene%20Search)の `index.search.highlight。*` プロパティーを介して構成できます。

ユーザーが検索結果を参照して必要なエンティティを見つけることができるように、エンティティのデータの主要部分を組み合わせて概要を作成します。

すべての検索および索引付けのロジックが配置されると、ので、サービス層を更新 `アドオン`、 `更新`、および `削除` サービスコールは、新しいロジックをトリガーします。
