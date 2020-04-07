---
header-id: creating-ios-apps-that-use-the-mobile-sdk
---

# Mobile SDKを使用するiOSアプリの作成

[TOC levels=1-4]

Liferay Mobile SDKは、@product@のコアWebサービスとユーティリティ、およびカスタムアプリの消費を合理化する方法を提供します。 @product@のJSON Webサービスをラップし、ネイティブモバイルアプリで簡単に呼び出せるようにします。 あなたがに集中できるように、それは、認証を処理するHTTPリクエスト（同期または非同期）を行う、JSONの結果を解析し、ハンドルサーバ側の例外 *使用して* あなたのアプリでのサービスを。

Liferay Mobile SDKには、Liferay iOS SDKが付属しています。 公式の [プロジェクトページ](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) では、SDKリリースへのアクセス、SDKの最新ニュース、およびモバイルアプリ開発のディスカッションに参加するためのフォーラムが用意されています。 アプリでMobile SDKを構成したら、その中で@product@サービスを呼び出すことができます。

![図1：LiferayのMobile SDKは、ネイティブアプリがLiferayと通信できるようにします。](../../../../images/mobile-sdk-diagram.png)

iOS Mobile SDKアプリ開発のチュートリアルでは、次のトピックを扱います。

  - LiferayおよびカスタムポートレットサービスをiOSアプリで利用可能にする
  - iOSアプリでLiferayサービスを呼び出す
  - iOSアプリからのサービスの非同期呼び出し
  - バッチ処理を使用してiOSアプリのリクエストを送信する

開始するのに最適な方法は、iOSプロジェクトにMobile SDKを設定することです。 これにより、@product@のサービスがアプリで利用可能になります。

## 関連トピック

[iOSアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)

[Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
