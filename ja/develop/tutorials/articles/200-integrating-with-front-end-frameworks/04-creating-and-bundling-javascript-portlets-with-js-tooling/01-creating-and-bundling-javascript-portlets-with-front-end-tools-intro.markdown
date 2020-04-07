---
header-id: creating-and-bundling-javascript-portlets-with-javascript-tooling
---

# JavaScript Toolingを使用したJavaScriptウィジェットの作成とバンドル

[TOC levels=1-4]

Liferay JS Generatorは、@ \<product@\>JavaScriptウィジェットを生成します。 Liferay JS Bundle Toolkitの [ツールの](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages)つにすぎません</a> 。

| **重要：** Liferay JS Generatorを使用するには、Liferay JSが必要です。 @product@インスタンスにインストールされたPortlet Extender。 JSポートレットエクステンダー| Liferay Marketplaceから入手できるLabsアプリケーションです| [Liferay Digital Enterprise 7.1](https://web.liferay.com/marketplace/-/mp/application/115543020) |と| [Liferay Portal CE 7.1](https://web.liferay.com/marketplace/-/mp/application/115542926)。 | Labsとして指定されたアプリは実験的なものであり、Liferayではサポートされていません。 彼らは便利で最先端の機能の利用を促進するためにリリースされました。 |このステータスは予告なく変更される場合があります。 Labsアプリをダウンロードして使用してください|自分の裁量。

ポートレットはJava標準であるため、ポートレットを記述するためのJavaの仕組みに関する知識と理解が必要です。 これは、ウィジェットでJavaScriptフレームワークを使用したいフロントエンド開発者にとって非常に大きな障害となります。 JS Portlet Extenderと [liferay-npm-bundler](/docs/7-1/reference/-/knowledge_base/r/liferay-npm-bundler)おかげで、開発者は純粋なJavaScriptツールを使用して@product@でJavaScriptウィジェットを簡単に作成および開発できます。

![図1：JS Portlet Extenderでは、純粋なJavaScriptツールを使用してウィジェットを作成できます。](../../../images/extender-lifecycle.png)

| **注：** JavaScript Server-Side-Renderingは、そのままではサポートされていません。 へ|サイトのレンダリングに使用JSのフレームワークは、あなたが **必見** サーバー側の設定| （または検索クローラー）レンダリング生成をサポートします。

このセクションでは、生成されたJSウィジェットに対してこれらのオプションを構成する方法について説明します。

  - Liferay JS Generatorのインストールとバンドルの生成
  - システムおよびインスタンス設定の構成
  - ローカリゼーション
  - ポートレットプロパティの設定
  - 翻訳機能を使用する
