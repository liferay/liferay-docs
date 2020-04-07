---
header-id: mobile-sdk
---

# モバイルSDK

[TOC levels=1-4]

Liferayのパワーをモバイルアプリで活用したいですか？ LiferayのMobile SDKのおかげで、まさにそれができます。 Liferay Mobile SDKは、消費するLiferayコアWebサービス、Liferayユーティリティ、およびカスタムアプリWebサービスを合理化する方法を提供します。 Liferay JSON Webサービスをラップする低レベルのレイヤーであり、ネイティブモバイルアプリで簡単に呼び出すことができます。 あなたがに集中できるように、それは、認証の面倒をHTTPリクエスト（同期または非同期）を行う、JSONの結果を解析し、ハンドルサーバ側の例外 *使用して* あなたのアプリでのサービスを。 Liferay Mobile SDKは、ネイティブアプリとLiferayサービスのギャップを埋めます。 公式プロジェクト [ページ](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) では、SDKリリースへのアクセス、SDKの最新ニュース、およびモバイルアプリ開発のディスカッションに参加するためのフォーラムが用意されています。 Liferay Mobile SDKは、AndroidおよびiOS用の個別のダウンロードとして入手できます。

![図1：LiferayのMobile SDKは、ネイティブアプリがLiferayと通信できるようにします。](../../../images/mobile-sdk-diagram.png)

呼び出す必要があるリモートサービスに応じて、アプリのプロジェクトに追加する必要があるMobile SDKには2種類あります。 Liferayのビルド済みMobile SDKには、一般的なリモートサービスコールの構築に必要なクラスが含まれています。 また、Liferayの *コア* ポートレットの特定のリモートサービスを呼び出すために必要なクラスも含まれています。 コアポートレットは、すべてのLiferayインストールに含まれています（これらは、すぐに使用可能なポートレットまたは組み込みポートレットとも呼ばれます）。 ただし、カスタムポートレットのリモートサービスを活用する場合は、追加のMobile SDKを構築する必要があります。 構築された後は、このモバイルSDKが含まれている *のみ* それらのサービスを呼び出すために必要なクラスを。 したがって、カスタムポートレットのリモートサービスを活用するには、Liferayのビルド済みMobile SDKとともにアプリにインストールする必要があります。

Liferayには、Liferayに接続するモバイルアプリを構築するための [Liferayスクリーン](https://www.liferay.com/products/liferay-screens) も用意されています。 Screensは、 *スクリーンレット* と呼ばれるコンポーネントを使用して、Mobile SDKの低レベルのサービス呼び出しを活用および抽象化します。 ただし、ユースケース用のスクリーンレットがない場合、またはサービス呼び出しをより詳細に制御する必要がある場合は、Mobile SDKを直接使用できます。 ここでのMobile SDKチュートリアルに加えて、Screensチュートリアルをお読みになり、どちらがニーズに合うかを判断してください。

このセクションのチュートリアルでは、AndroidおよびiOSアプリ開発でのMobile SDKの使用について説明します。 次のチュートリアルではこれらのトピックを紹介し、各トピックの詳細なチュートリアルが続きます。

  - [Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
  - [Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)

さらに、次のチュートリアルでは、カスタムポートレットサービスをサポートするMobile SDKの構築について説明します。

  - [モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)

シートベルトを締めてください。LiferayのMobile SDKでモバイルに移行しましょう！

**関連トピック:**

[Liferayスクリーンを備えたAndroidアプリ](/docs/7-1/tutorials/-/knowledge_base/t/android-apps-with-liferay-screens)

[Liferayスクリーンを備えたiOSアプリ](/docs/7-1/tutorials/-/knowledge_base/t/ios-apps-with-liferay-screens)

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)
