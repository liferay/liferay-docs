---
header-id: custom-facet
---

# カスタムファセット

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

すべてのファセットは設定可能であり、共有された特性（たとえば、すべてのブログエントリ）に基づいて検索結果を絞り込むことができます。 カスタムファセットを使用すると、まったく新しいファセットを作成できます。 まず最初に、カスタムファセットの構成画面に入ります。

![図1：最初にカスタムファセットを構成する必要があります。](../../../images/search-custom-facet-ddmTemplateKey.png)

上のスクリーンショットは、結果の集計に使用されるフィールドとしてWeb Content Article DDMテンプレートキーを持つカスタムファセットを示しています。 次のスクリーンショットは、それがどのように構成されたかを示しています。

![図2：すぐにカスタムファセットを構成する](../../../images/search-custom-facet-config.png)

**集計フィールド** ：ファセット用語の作成に使用される値を持つ分析されていないキーワードフィールドを指定します。 検索結果の `ddmTemplateKey` フィールドの値がBASIC-WEB-CONTENTの場合、それがカスタムファセットに用語として表示されます。

**カスタム見出し** ：カスタムファセットの人間が読める見出しを入力します。

**カスタムパラメータ名** ：ファセットに使用するURLパラメータを設定します。 上記の構成で、 *lunar resort* を検索し、BASIC-WEB-CONTENTをクリックすると、URLが生成されます

``` 
    http://localhost:8080/web/guest/search?q=lunar&ddmtemplatekey=BASIC-WEB-CONTENT
```

**最大用語** ：ファセットに一致する用語がいくつ見つかったかに関係なく、表示するファセット用語の最大数を設定します。

**頻度しきい値** ：用語が結果リストに表示されるために必要な最小頻度を設定します。 たとえば、ファセットの頻度しきい値が `3`設定されている場合、2つの一致する結果を持つ用語は用語結果リストに表示されません。

**周波数の表示** ：用語の周波数を表示するかどうかを選択します。

## インデックス付きフィールドの検索

カスタムファセットを使用するには、分析対象外のキーワードフィールドを指定する必要があります。 ほとんどの場合、カスタムファセットが必要であることがわかっている場合は、ファセットを作成するために必要な情報がわかります。

1.  検索結果ウィジェット構成の[ドキュメントフォームに結果を表示]設定を有効にします。

2.  必要な情報を持つアセットを検索します。

3.  結果のヒットのドキュメントビューを調べます。

    カスタムファセットの *集計フィールド*キーを使用します。

使用可能なフィールドのリスト全体を閲覧するには、検索エンジンのAPIを使用してフィールドマッピングを調べます。

Elasticsearchの場合、CURLを使用して端末からフィールドマッピングにアクセスし、 [Get Mapping API](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/indices-get-mapping.html)を呼び出します。

    curl -X GET "localhost:9200/_mapping/LiferayDocumentType"?pretty

Solrは [ListFields API](https://lucene.apache.org/solr/guide/6_6/schema-api.html#SchemaAPI-ListFields)使用します。

    curl http://localhost:8983/solr/liferay/schema/

Elasticsearchの例からの出力のスニペットを次に示します。

    "ddmStructureKey": {
        "store": true,
        "type": "keyword"
    },
    "ddmTemplateKey": {
        "store": true,
        "type": "keyword"
    },
    "defaultLanguageId": {
        "store": true,
        "type": "keyword"
    },
    "description": {
        "store": true,
        "term_vector": "with_positions_offsets",
        "type": "text"
    },
    "discussion": {
        "store": true,
        "type": "keyword"
    },

カスタムフィールドを使用して、分析されていない共有キーワードフィールド値によってファセット用語を集計します。

<！-バンドルの検索エンジンアダプターから型マッピングファイルを抽出します。

1.  `[Liferay Home] / osgi / portal`から、アーカイブツールを使用して開きます
   
        com.liferay.portal.search.elasticsearch6.impl.jar

2. `META-INF / mappings / liferay-type-mappings / json` をローカルフォルダーに抽出し、 開きます。

Solrを使用している場合は、JARを `Liferay Home] / osgi / marketplace`から開き、 が `schema.xml` ファイルを抽出します。

以下は、いくつかのキーワードフィールドを含む `liferay-type-mappings.json` スニペットです`</p>

<pre><code>"ddmStructureKey": {
    "store": true,
    "type": "keyword"
},
"ddmTemplateKey": {
    "store": true,
    "type": "keyword"
},
"defaultLanguageId": {
    "store": true,
    "type": "keyword"
},
"description": {
    "store": true,
    "term_vector": "with_positions_offsets",
    "type": "text"
},
"discussion": {
    "store": true,
    "type": "keyword"
},
`</pre>
