---
header-id: upgrading-6-2-themes-to-7-1
---

# Liferay Portal 6.2から7.1へのテーマのアップグレード

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

This section guides you through the process of upgrading your 6.2 theme to run on @product-ver@. その間、Sass、Bootstrap 4、Lexicon 2.0のサポートなど、テーマの改善を活用する必要があります。

テーマのアップグレードには次の手順が含まれます。

  - プロジェクトメタデータの更新
  - CSSの更新
  - テーマテンプレートの更新
  - リソースインポーターの構成とコンテンツの更新
  - 粘土デザインパターンの適用

例として、これらのチュートリアルでは、Liferay Portal 6.2 [Liferayテーマの開発](/docs/6-2/tutorials/-/knowledge_base/t/developing-a-liferay-theme) 学習パスで開発されたLunar Resortテーマと呼ばれるテーマに手順を適用します。 [`_styled` テーマ](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/html/themes/_styled)拡張し、構成可能な設定を追加し、Font AwesomeアイコンとBootstrapを活用するレスポンシブデザインを組み込むため、多くのLiferay Portal 6.2テーマに類似しています。 テーマ [ZIPファイル](/documents/10184/656312/lunar-resort-theme-migration-6.2.zip) は、元のソースコードが含まれています。

![図1：このチュートリアルでアップグレードされたLunar Resortサンプルテーマは、クリーンで最小限のデザインを使用しています。](../../../../images/finished-7-1-theme.png)

テーマをアップグレードする前に、考える [テーマに移行](/docs/7-1/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) 使用するために [LiferayのJSテーマツールキット](https://github.com/liferay/liferay-themes-sdk/tree/master/packages)。 @product-ver@はこの移行を必要としませんが、Liferay JS Theme ToolkitのGulp `upgrade` タスクは多くのアップグレード手順を自動化します。 Liferay JS Theme Toolkitを使用するテーマは、 [Themelets](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)などの専用機能も活用できます。

テーマがBootstrap 3とLexicon CSSを使用している場合、@product@の [互換性レイヤー](/docs/7-1/tutorials/-/knowledge_base/t/using-the-bootstrap-3-lexicon-css-compatibility-layer)のおかげで、Bootstrap 4とClay CSSマークアップとともにBootstrap 3とLexicon CSSを使用できます。

| **注：** 互換性レイヤーは、以下を確実にするための短期的なソリューションとして意図されています。 Bootstrap 3とLexicon CSSコンポーネントが壊れていないことを確認してください。 [Bootstrap 4](https://getbootstrap.com/docs/4.3/migration/) を使用するようにテーマを更新します|および [Clay CSS](https://clayui.com/docs/css-framework/scss.html)。 なります|将来のリリースでは無効になります。 Bootstrap 4とClayを使用するようにテーマを移行します|できるようになり次第、CSS。

手順に従ってください [6.2テーマアップグレードタスク実行](/docs/7-1/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) そのガルプ含め、LiferayのJSテーマToolkitを使用するようにテーマを移行する方法を学ぶためのチュートリアル `アップグレード` タスクを。 それ以外の場合は、このセクションの残りのチュートリアルの手順に従って、6.2テーマを7.1に手動でアップグレードする必要があります。
