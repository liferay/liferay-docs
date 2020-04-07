---
header-id: querying-for-entry-documents
---

# エントリー文書の照会

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">エントリの検索とインデックス作成を有効にする</p><p>手順3/5</p>
</div>

エントリを検索エンジンにインデックス付けするためのコードが用意されています。 次に、インデックス付きドキュメントのクエリに必要な動作をコーディングします。

2つのクラスを実装します。

1.  `EntryKeywordQueryContributor` は、進行中の検索クエリに句を提供します。

2.  `EntryModelPreFilterContributor` は、検索結果が検索エンジンから返される前にフィルターされる方法を制御します。

## `KeywordQueryContributor`実装

`EntryKeywordQueryContributor` を作成し、これを入力します。

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = KeywordQueryContributor.class
    )
    public class EntryKeywordQueryContributor implements KeywordQueryContributor {
    
        @Override
        public void contribute(
            String keywords, BooleanQuery booleanQuery,
            KeywordQueryContributorHelper keywordQueryContributorHelper) {
    
            SearchContext searchContext =
        keywordQueryContributorHelper.getSearchContext();
    
            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, Field.TITLE, false);
            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, Field.CONTENT, false);
            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, "entryEmail", false);
        }
    
        @Reference
        protected QueryHelper queryHelper;
    
    }

ローカライズされた検索語を追加することは重要です。 インデックス内のすべてのローカライズされたエントリフィールドについて、検索エンジンからローカライズされた値を取得します。

クエリコードが配置されたので、返されたエントリドキュメントの集計方法を定義できます。
