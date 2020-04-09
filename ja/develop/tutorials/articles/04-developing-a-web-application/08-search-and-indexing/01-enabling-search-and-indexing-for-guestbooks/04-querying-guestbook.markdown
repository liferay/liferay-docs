---
header-id: querying-for-guestbook-documents
---

# ゲストブックドキュメントのクエリ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">ゲストブックの検索とインデックス作成を有効にする</p><p>手順4/6</p>
</div>

ゲストブックを検索エンジンにインデックス付けするためのコードが用意されています。 次に、インデックス付きドキュメントのクエリに必要な動作をコーディングします。

2つのインターフェイスを実装します。

1.  `KeywordQueryContributor` は、進行中の検索クエリに句を提供します。

2.  `ModelPreFilterContributor` は、検索結果が検索エンジンから返される前にフィルターされる方法を制御します。

## `KeywordQueryContributor`実装

`GuestbookKeywordQueryContributor`作成します`</p>

<pre><code>@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
        service = KeywordQueryContributor.class
)
public class GuestbookKeywordQueryContributor
    implements KeywordQueryContributor {

    @Override
    public void contribute(
        String keywords, BooleanQuery booleanQuery,
        KeywordQueryContributorHelper keywordQueryContributorHelper) {

        SearchContext searchContext =
    keywordQueryContributorHelper.getSearchContext();

        queryHelper.addSearchLocalizedTerm(
    booleanQuery, searchContext, Field.TITLE, false);
    }

    @Reference
    protected QueryHelper queryHelper;

}
`</pre>

This class adds Guestbook fields to the search query constructed by the Search application in @<product@>. 後でゲストブックをアセット有効にすると、このコードにより、キーワードが検索バーに入力されたときにインデックス付きゲストブックを検索アプリケーションから検索できるようになります。 クエリヘルパーを使用して、ゲストブックを検索できる検索用語をクエリに追加します。 ここでは、ローカライズされた検索語を追加することが重要であることに注意することが重要です。 ローカライズされたゲストブックのタイトルにはインデックスが付けられているため、検索エンジンからローカライズされた値を取得する必要があります。

クエリコードを配置したら、返されたゲストブックドキュメントの要約方法を定義します。
