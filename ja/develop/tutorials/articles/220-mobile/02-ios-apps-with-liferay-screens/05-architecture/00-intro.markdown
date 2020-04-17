---
header-id: architecture-of-liferay-screens-for-ios
---

# iOS用Liferayスクリーンのアーキテクチャ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferayの画面は、そのプレゼンテーションとビジネスロジックからのアイデアを使用してコード分離 [プレゼンターモデルビュー](http://en.wikipedia.org/wiki/Model-view-presenter)、 [モデルビューViewModelに](http://en.wikipedia.org/wiki/Model_View_ViewModel)、および [VIPER](http://www.objc.io/issue-13/viper.html)。 ただし、Screensはアプリ向けに設計されているため、これらのアーキテクチャの標準的な実装ではありません。 Screensはアプリではありません。 *in* アプリの使用を目的としたビジュアルコンポーネントのスイートです。

このセクションのチュートリアルでは、iOS用Liferay Screensのアーキテクチャについて説明します。 最初は、システムを構成する高レベルのコンポーネントの概要です。 これには、コア、スクリーンレット、およびテーマが含まれます。 これらのコンポーネントについては、以降のチュートリアルで詳しく説明します。 これらのビルディングブロックの調査が完了したら、すばらしいスクリーンレットとテーマを作成する準備が整います。
