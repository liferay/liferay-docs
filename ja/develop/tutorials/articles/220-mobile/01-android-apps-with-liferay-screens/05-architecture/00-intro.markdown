---
header-id: architecture-of-liferay-screens-for-android
---

# Android向けLiferayスクリーンのアーキテクチャ

[TOC levels=1-4]

Liferayの画面から建築のアイデアを適用する [モデルビュープレゼンター](http://en.wikipedia.org/wiki/Model-view-presenter)、 [モデルビューViewModelに](http://en.wikipedia.org/wiki/Model_View_ViewModel)、および [VIPER](http://www.objc.io/issue-13/viper.html)。 Screensはアプリではないため、これらのアーキテクチャの標準的な実装とは見なされませんが、プレゼンテーションレイヤーをビジネスロジックから分離するためにスクリーンから借用しています。 このチュートリアルでは、Screenの高レベルアーキテクチャ、そのコンポーネントの低レベルアーキテクチャ、およびAndroid Screenletライフサイクルについて説明します。 さあ、スクリーンの構成要素の調査を始めましょう！
