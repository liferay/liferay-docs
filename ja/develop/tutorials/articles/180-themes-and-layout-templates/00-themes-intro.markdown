---
header-id: themes-and-layout-templates
---

# テーマとレイアウトテンプレート

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマは、サイトの全体的なルックアンドフィールを提供します。 [ページレイアウト](/docs/7-1/reference/-/knowledge_base/r/understanding-the-page-layout) 理解することは、スタイル設定、コンテンツの整理、およびサイトの作成のための正しいマークアップをターゲットにするために重要です。 ページの構成方法を理解したら、テーマを開発できます。

Webサイトを設計する場合、CSS、JavaScript、HTMLの3つの主要コンポーネントが必要です。 @product@は、 [CSS拡張とパターン](/docs/7-1/reference/-/knowledge_base/r/theme-components-and-workflow#css-frameworks-and-extensions) を提供し、SASSと複数のJavaScriptフレームワークをサポートします。 ただし、HTMLは [FreeMarker](https://freemarker.apache.org/) テーマテンプレートを介してレンダリングされます。

製品@ @いくつか提供して [デフォルトFreeMarkerのテンプレート](/docs/7-1/reference/-/knowledge_base/r/theme-components-and-workflow#theme-templates) 各ハンドルのページのための機能の重要な部分ということ。 ヘルプへの製品@、開発プロセスより簡単に@もいくつか提供して [テーマテンプレートユーティリティ](/docs/7-1/reference/-/knowledge_base/r/theme-components-and-workflow#theme-template-utilities) 使用すると、ポートレット、使用タグライブラリ、アクセスのテーマオブジェクト、およびそれ以上を含むためにあなたのテーマテンプレートで使用することができます。

以下のためのいくつかのメカニズムが存在する [のカスタマイズと延びるテーマ](/docs/7-1/reference/-/knowledge_base/r/theme-components-and-workflow#theme-customizations-and-extensions)カラースキームからのコードの再利用可能な部分には、。 同様に、のためのいくつかのメカニズムがある [のカスタマイズと拡張ポートレット](/docs/7-1/reference/-/knowledge_base/r/theme-components-and-workflow#portlet-customizations-and-extensions)。

チュートリアルのこのセクションでは、テーマとレイアウトテンプレートの開発方法、ポートレットのカスタマイズ方法などを学びます。
