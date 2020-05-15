---
header-id: introduction-to-frontend-development
---

# フロントエンド開発入門

[TOC levels=1-4]

@product@は、完全に自由な開発者フロントエンドを提供します。手作業でのJavaScriptのコーディングに対する好みや、Liferayのフロントエンドフレームワークの使用経験の有無、jQuery、Lodash、またはモジュールに対する好みに関わらず、希望するフロントエンドフレームワークを使用することができます。

@product@を以前のバージョンから使用しているユーザーは、Liferayではなじみ深いAlloy UIを引き続き使用できますが、一番好きなフロントエンドテクノロジーを使用することもできます。

- EcmaScript ES2015+
- [Metal.js](https://metaljs.com/) （Liferayが開発）
- [AlloyUI](https://alloyui.com/)（Liferayが開発）
- jQuery（付属）
- Lodash（付属）

モジュールをロードするには、いつ必要なのか、ビルド時の場所はどこなのか、モジュールを一緒にバンドルするのか、個別にロードするのかについて把握しておき、実行時にアセンブルする必要があります。これらのタスクを追跡するのは大変ですが、
@product@のローダー（YUI/AUI、AMD、およびAMD形式のnpm）がロードを処理するため、すべての詳細を心配する必要はありません。モジュールに関するわずかな情報を提供するだけで、@product@のローダーが残りを処理します。

アプリケーションでnpmパッケージを使用する場合は、[`liferay-npm-bundler`](/docs/7-1/reference/-/knowledge_base/r/liferay-npm-bundler)を使用できます。
これはまさに上記の目的のために構築されており、時間を節約するために一般的なモジュールタイプ（AMD、React、Angular JSなど）のプリセットもいくつか提供しています。これによりOSGiバンドルが作成され、すべてのnpm依存関係が抽出され、Liferay AMD Loader用にコードがトランスパイルされます。

@product@用のJavaScriptアプリケーションを開発する際、@product@固有の情報、またはWebサービスにアクセスする必要がある場合があります。`Liferay`のグローバルJavaScript Objectでは 、[この情報を公開しており](/docs/7-1/tutorials/-/knowledge_base/t/liferay-javascript-apis)、JavaScriptアプリケーションで@product@を活用しながら、好きなフロントエンドフレームワークとテクノロジーを使用することができます。

## LexiconとClay

@product@では、[Lexicon](https://lexicondesign.io/docs/lexicon/)と呼ばれる独自の設計言語を使用して、Liferay製品のエコシステム全体で一貫したUIとユーザーエクスペリエンスを構築するための共通フレームワークを提供しています。LexiconのWeb実装（CSS、JS、およびHTML）は[Clay](https://claycss.com/docs/clay/)と呼ばれています。一連のCSSクラスまたは[タグライブラリ](/docs/7-1/tutorials/-/knowledge_base/t/using-the-clay-taglib-in-your-portlets)を介して、アプリケーション開発者は自動的に使用することができます。

## テンプレート

テンプレート化には、Java EEのJSP、FreeMarker、Googleの[Soy（別名Closure Templates）](/docs/7-1/tutorials/-/knowledge_base/t/liferay-soy-portlet)など、好きなものを使用できます。

## テーマ

@product@テーマは、サイトのルック&フィールを提供します。テーマは、CSS、JavaScript、HTML、およびFreeMarkerテンプレートを組み合わせたものです。デフォルトのテーマもありますが、独自のルック&フィールを作成することもできます。

[Theme Builder Gradleプラグイン](/docs/7-1/reference/-/knowledge_base/r/theme-builder-gradle-plugin)や[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)、[@ide@](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes-with-liferay-ide)や[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)の[テーマテンプレート](/docs/7-1/reference/-/knowledge_base/r/theme-template)を使って最適な開発ツールを選択できるため、洗練されたテーマの作成に専念することができます。

## フロントエンドの拡張

@product@のモジュール性は、開発のカスタマイズや拡張ポイントという形で、フロントエンド開発者に多くの利点をもたらします。これらの拡張機能は、アプリケーションの安定性、適合性、および将来的な進化を保証します。

以下は、利用可能なフロントエンド拡張の一部です。

- [Theme Contributors](/docs/7-1/tutorials/-/knowledge_base/t/packaging-independent-ui-resources-for-your-site)
- [Context Contributors](/docs/7-1/tutorials/-/knowledge_base/t/injecting-additional-context-variables-into-your-templates)
- [Creating Configurable Styles for Portlet Wrappers](/docs/7-1/tutorials/-/knowledge_base/t/creating-configurable-styles-for-portlet-wrappers)
- [Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

Read on to learn more.
