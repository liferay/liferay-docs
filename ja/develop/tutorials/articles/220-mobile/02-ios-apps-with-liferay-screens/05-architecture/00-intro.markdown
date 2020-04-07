---
header-id: architecture-of-liferay-screens-for-ios
---

# iOS用Liferayスクリーンのアーキテクチャ

[TOC levels=1-4]

Liferayの画面は、そのプレゼンテーションとビジネスロジックからのアイデアを使用してコード分離 [プレゼンターモデルビュー](http://en.wikipedia.org/wiki/Model-view-presenter)、 [モデルビューViewModelに](http://en.wikipedia.org/wiki/Model_View_ViewModel)、および [VIPER](http://www.objc.io/issue-13/viper.html)。 ただし、Screensはアプリ向けに設計されているため、これらのアーキテクチャの標準的な実装ではありません。 Screensはアプリではありません。 *in* アプリの使用を目的としたビジュアルコンポーネントのスイートです。

このセクションのチュートリアルでは、iOS用Liferay Screensのアーキテクチャについて説明します。 最初は、システムを構成する高レベルのコンポーネントの概要です。 これには、コア、スクリーンレット、およびテーマが含まれます。 これらのコンポーネントについては、以降のチュートリアルで詳しく説明します。 これらのビルディングブロックの調査が完了したら、すばらしいスクリーンレットとテーマを作成する準備が整います。
