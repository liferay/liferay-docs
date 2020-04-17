---
header-id: localizing-your-portlet
---

# ウィジェットのローカライズ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

以下の手順に従って、ウィジェットをローカライズする方法を学習します。

1.  バンドルを生成したときにローカライズを使用することを選択しなかった場合は、このステップに従って今すぐバンドルで有効にしてください。それ以外の場合は、このステップをスキップできます。 プロジェクトに `/ features / localization` フォルダーを作成し、それに `Language.properties` ファイルを追加します。 `Language.properties` ファイルを指す `.npmbuildrc` ファイルに、 `create-jar.features.localization` キーを追加します。 構成例を以下に示します。

    {"create-jar"：{"output-dir"： "dist"、 "features"：{"js-extender"：true、 "web-context"： "/ my-test-js-widget"、 "ローカリゼーション"：" features / localization / Language "、" settings "：" features / settings.json "}}、... }

    | **注：** デフォルトのファイルパスは上記のとおりです。 この値を更新できます| `Language.properties` ファイルを別の場所に配置する場合|ロケーション。

2.  `Language.properties` ファイルを構成し、ローカライズされたプロパティファイル（例： `Language_[locale].properties`）に、使用可能な翻訳 [ごとに [言語キー](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application#what-are-language-keys) を提供します](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application#what-locales-are-available-by-default)。 *JavaScriptベースのウィジェット* 構成を以下に示します。

    javax.portlet.title.my \ _js \ _portlet \ _project = My JS widget Project porlet-namespace = Porlet Namespace context-path = Context Path portlet-element-id = Portlet Element Id configuration = Configuration fruit = Favourite fruit fruit-help =好きな果物を選んでくださいan-orange =オレンジオレンジa-pear =ナシan-apple =リンゴ

3.  `Liferay.Language.get（ 'key'）` メソッドを使用して、JavaScriptで言語キーのローカライズされた値を取得します。

すばらしいです\！ これで、ウィジェットをローカライズする方法がわかりました！

## 関連トピック

  - [JavaScriptウィジェットのシステム設定とインスタンス設定の構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-system-settings-and-instance-settings-for-your-js-portlet)
  - [JavaScriptウィジェットで翻訳機能を使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-translation-features-in-your-portlet)
  - [JavaScriptウィジェットのポートレットプロパティの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-portlet-properties-for-your-js-portlet)
