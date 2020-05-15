---
header-id: web-services
---

# Webサービス

[TOC levels=1-4]

異なるマシン上のアプリケーション間での通信は、重要です。これを可能にするために、アプリはAPIを公開して、リモートコンポーネント（他のアプリまたはデバイス）がアプリの機能にアクセスできるようにします。たとえば、一つのサービスには、ユーザーに対して情報を表示するクライアントアプリ、B2B設定でデータを処理するサーバーアプリ、そしてデータを処理するためにデータを要求するIoTデバイスが含まれていることがあります。Web APIを公開すると、外部のアプリケーションやデバイスが使用しているアプリケーションやデバイスと通信できるようになります。

@product@には非常に多くのアプリと機能が含まれているため、Liferayでは開発者がAPIを公開することで外部のアプリおよびデバイスからアクセスできるようになっています。さらに、Liferayの開発プラットフォームでは、それらのアプリや機能を簡単に拡張し、新しいプラットフォームを作成することができます。

クライアントが@product@のWeb APIに接続するには、以下の2つの異なるアプローチがあります。

**ヘッドレスREST APIs：**@product@のフロントエンドから独立したRESTful Webサービスを使用できます（そのため、*ヘッドレス*です）。これらのAPIは、[OpenAPI](https://swagger.io/docs/specification/about/)仕様に準拠しており、@product@でWebサービスを操作するために推奨される最新の方法です。

**プレーンWeb/RESTサービス：**これは、@product@でWebサービスを構築および使用するための古い方法ですが、引き続きサポートされています。たとえば、[JAX-RS](/docs/7-1/tutorials/-/knowledge_base/t/jax-rs)、[JAX-WS](/docs/7-1/tutorials/-/knowledge_base/t/jax-ws)、または[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services)を使用して、プレーンRESTまたはSOAP Webサービスを実装できます。

これ以降のチュートリアルでは、ヘッドレスREST APIを始めとする、@product@でのWebサービスの使用方法とその作成方法を説明します。
