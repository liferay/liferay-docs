---
header-id: generating-results-summaries-0
---

# 結果サマリーの生成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">エントリの検索とインデックス作成を有効にする</p><p>手順3/5</p>
</div>

検索アプリケーションとAsset Publisherアプリケーションには、検索エンジンから取得した結果が表示されます。 `ModelSummaryContributor`実装することにより、表示を制御できます。

`EntryModelSummaryContributor`作成します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelSummaryContributor.class
    )
    public class EntryModelSummaryContributor implements ModelSummaryContributor {
    
        @Override
        public Summary getSummary(
            Document document, Locale locale, String snippet) {
    
            Summary summary = createSummary(document);
    
            summary.setMaxContentLength(128);
    
            return summary;
        }
    
        private Summary createSummary(Document document) {
            String prefix = Field.SNIPPET + StringPool.UNDERLINE;
    
            String title = document.get(prefix + Field.TITLE, Field.CONTENT);
            String content = document.get(prefix + Field.CONTENT, Field.CONTENT);
    
            return new Summary(title, content);
        }
    
    }

最初に `getSummary`オーバーライドし、返されるサマリーの最大サマリー長を設定します。 値 `200` はLiferay標準です。 `createSummary`というユーティリティメソッドでサマリー作成を制御します。 ゲストブックのタイトルは概要にのみ含まれていますが、エントリはタイトルとコンテンツ（エントリメッセージフィールド）を使用して概要を作成します。

エンティティのデータの主要部分を組み合わせて、要約を作成します。

だから今、検索とインデックス作成のロジックが所定の位置にあることを、あなたは、サービス層を更新することができます `アドオン`、 `更新`、および `削除` サービスコールは、新しいロジックをトリガーします。
