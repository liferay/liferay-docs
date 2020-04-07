---
header-id: creating-android-apps-that-use-the-mobile-sdk
---

# Mobile SDKを使用するAndroidアプリの作成

[TOC levels=1-4]

Liferay Mobile SDKは、@product@のコアWebサービスとユーティリティ、およびカスタムアプリの消費を合理化する方法を提供します。 @product@のJSON Webサービスをラップし、ネイティブモバイルアプリで簡単に呼び出せるようにします。 あなたがに集中できるように、それは、認証を処理するHTTPリクエスト（同期または非同期）を行う、JSONの結果を解析し、ハンドルサーバ側の例外 *使用して* あなたのアプリでのサービスを。

Liferay Mobile SDKには、Liferay Android SDKが付属しています。 公式の [プロジェクトページ](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) では、SDKリリースへのアクセス、SDKの最新ニュース、およびモバイルアプリ開発のディスカッションに参加するためのフォーラムが用意されています。 アプリでMobile SDKを構成したら、その中で@product@サービスを呼び出すことができます。

![図1：LiferayのMobile SDKは、ネイティブアプリが@product@と通信できるようにします。](../../../../images/mobile-sdk-diagram.png)

Android Mobile SDKアプリ開発のチュートリアルでは、次のトピックについて説明しています。

  - LiferayおよびカスタムポートレットサービスをAndroidアプリで利用可能にする
  - AndroidアプリでLiferayサービスを呼び出す
  - Androidアプリからのサービスの非同期呼び出し
  - バッチ処理を使用してAndroidアプリのリクエストを送信する

開始するのに最適な方法は、AndroidプロジェクトにMobile SDKを設定することです。 これにより、@product@のサービスがアプリで利用可能になります。

## 関連トピック

[AndroidアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app)

[Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)
