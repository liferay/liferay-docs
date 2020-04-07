---
header-id: modularizing-an-existing-portlet
---

# 既存のポートレットのモジュール化

[TOC levels=1-4]

適切にモジュール化されたプラグインを持つアプリケーションには、いくつかの利点があります。 まず、アプリケーション全体をリリースすることなく、プラグインを個別にリリースできるということです。 また、アプリケーション全体に依存することなく、外部クライアントが特定のプラグインからサービスを利用できます。 そして、大量のコードを簡潔なモジュールに分割することにより、チームはアプリケーションの特定の領域により集中しやすくなります。 以上は、アプリケーションプラグインをモジュール化するためのいくつかの理由にすぎません。

これらのチュートリアルでは、従来のアプリケーションをモジュールに変換する方法を学びます。 始める前に、これらのチュートリアルに示されているモジュール構造は、アプリケーションのモジュールを構造化する多くの方法のうちの1つにすぎないことをまずは理解しておくことが重要です。 また、アプリケーションにはさまざまな形状とサイズがあります。 一部のアプリケーションに必要な、特別な操作がある場合もあります。 これらのチュートリアルは、Liferayのモジュール構造を使用してモジュールに変換するための一般的なプロセスを提供します。

関連するものは次のとおりです。

  - [ポートレットクラスとUIの変換](/docs/7-1/tutorials/-/knowledge_base/t/converting-your-applications-portlet-classes-and-ui)
  - [Service Builderインターフェースと実装の変換](/docs/7-1/tutorials/-/knowledge_base/t/converting-your-applications-service-builder-api-and-implementation)
  - [モジュールの構築とデプロイ](/docs/7-1/tutorials/-/knowledge_base/t/building-your-module-jars-for-deployment)

この手順は、Liferay DXP Digital EnterpriseとLiferay Portal CEの両方のポートレットに適用されます。

まず最初に行うことは、アプリケーションのWeb（クライアント）モジュールを作成することです。
