---
header-id: using-translation-features-in-your-portlet
---

# ウィジェットで翻訳機能を使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

デフォルトでは、Liferay JS Generatorは変換用の空の構成を作成します。 変換スクリプトは、サポートされている新しいロケールを追加する方法、または実行時に資格情報を構成する方法をユーザーに指示します。 翻訳ターゲットは、 `.npmbuildrc` ファイルの `supportedLocales` キーで定義したサポートされているロケールを読み取り、 `* language.properties` ファイルが一致することを確認します。

| **注：** 翻訳機能を使用するには、Microsoft |翻訳者キー。 次のいずれかで資格情報を提供します。 `translatorTextKey` あなたの中の変数 `.npmbuildrc` ファイル、またはそれらを提供| `TRANSLATOR_TEXT_KEY` 環境変数。

次の手順に従って、サポートされている新しいロケールを追加し、翻訳用の言語プロパティファイルを自動的に作成します。

1.  `.npmbuildrc` ファイルの `supportedLocales` 配列にロケールを追加します。

2.  以下のコマンドで翻訳ターゲットを実行します。
   
        npm run translate

3.  翻訳対象は、自動的にそれぞれの新しいの言語プロパティファイルを作成 **サポート** 言語キーの翻訳とロケールを。 また、サポートされていないが、 `* language.properties` ファイルがあるロケールについて警告します。

すばらしいです\！ これで、アプリでLiferay JS Generatorの翻訳機能を使用する方法がわかりました。

## 関連トピック

  - [JavaScriptウィジェットのシステム設定とインスタンス設定の構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-system-settings-and-instance-settings-for-your-js-portlet)
  - [ウィジェットのローカライズ](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-portlet)
  - [JavaScriptウィジェットのポートレットプロパティの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-portlet-properties-for-your-js-portlet)
