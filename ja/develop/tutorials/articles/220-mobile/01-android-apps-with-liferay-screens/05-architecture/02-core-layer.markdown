---
header-id: core-layer
---

# コア層

[TOC levels=1-4]

コアレイヤーは、開発者が構造化され分離された方法でスクリーンレットを作成できるようにするマイクロフレームワークです。 すべてのScreenletはコアクラスに基づいて共通の構造を共有しますが、各Screenletには独自の目的と通信APIを持たせることができます。

![図1：Android向けLiferay Screensのコアレイヤーです。](../../../../images/screens-android-architecture-02.png)

コアの主要コンポーネントは次のとおりです。

[**インタラクター：**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/Interactor.java) すべてのLiferayポータルインタラクションの基本クラスおよびスクリーンレットがサポートするユースケース。 インタラクターは、Liferay Mobile SDKを介してサービスを呼び出し、 [EventBus](https://github.com/greenrobot/EventBus)を介して非同期的に応答を受信し、最終的にビューの状態を変更します。 それらのアクションは、単純なアルゴリズムの実行からサーバーまたはデータベースへのデータの非同期要求まで、複雑さがさまざまです。 スクリーンレットには、特定の操作のサポート専用の複数のインタラクターを含めることができます。

[**BaseScreenlet：**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java) すべてのScreenletクラスの基本クラス。 Screenletのビューからユーザーイベントを受信し、インタラクターをインスタンス化して呼び出し、操作結果でビューを更新します。 それを拡張するクラスは、その [テンプレートメソッド](http://www.oodesign.com/template-method-pattern.html)オーバーライドできます。

  - *createScreenletView：* は通常、スクリーンレットのビューを拡大し、XML定義から属性値を取得します。
  - *createInteractor：* は、指定されたアクションのインターアクターをインスタンス化します。 スクリーンレットが1つのInteractorタイプのみをサポートする場合、そのタイプのInteractorは常にインスタンス化されます。
  - *onUserAction：* は、指定されたアクションに関連付けられたインターアクターを実行します。

**スクリーンレットビュー：** はスクリーンレットのUIを実装します。 Screenletの `createScreenletView` メソッドによってインスタンス化されます。 標準レイアウトファイルを使用して特定のUIをレンダリングし、データの変更に合わせてUIを更新します。 親ビューを拡張する独自のビューを開発する場合、親スクリーンレットのプロパティを読み取るか、このクラスからメソッドを呼び出すことができます。

[**EventBus：**](https://github.com/greenrobot/EventBus) は、非同期操作が完了するとInteractorに通知します。 通常、 `AsyncTask` インスタンスに関連する問題を回避するために、アクティビティライフサイクルから `AsyncTask` クラスインスタンスを分離します。

[**Liferay Mobile SDK：**](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) は、Liferayインスタンスのリモートサービスをタイプセーフで透過的な方法で呼び出します。

[**SessionContext：**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/SessionContext.java) ログインしたユーザーのセッションを保持するシングルトンクラス。 アプリは、ユーザーには見えない暗黙的なログイン、または明示的なユーザー入力に依存してセッションを作成するログインを使用できます。 ユーザーログインは、 [ログインスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android)で実装できます。 これは詳細に説明されて [ここ](/docs/7-1/tutorials/-/knowledge_base/t/accessing-the-liferay-session-in-android)。

[**LiferayServerContext：**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/LiferayServerContext.java) サーバー構成パラメーターを保持するシングルトンオブジェクト。 `server_context.xml` ファイル、または `server_context.xml`定義されたキーをオーバーライドする他のXMLファイルからロードされます。

[**server \ _context.xml：**](https://github.com/liferay/liferay-screens/blob/develop/android/library/core/src/main/res/values/server_context.xml) はデフォルトサーバー、 `companyId` （LiferayインスタンスID）および `groupId` （サイトID）を指定します。 現在のLiferayバージョン（属性 `liferay_portal_version`）または代替 `ServiceVersionFactory` など、このファイルの他のScreensパラメーターを構成して、カスタムバックエンドにアクセスすることもできます。

[**LiferayScreensContext：**](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/LiferayScreensContext.java) アプリケーションコンテキストへの参照を保持するシングルトンオブジェクト。 必要に応じて内部で使用されます。

[**ServiceVersionFactory：**](https://github.com/liferay/liferay-screens/blob/develop/android/library/core/src/main/java/com/liferay/mobile/screens/util/ServiceVersionFactory.java) Liferay Screensでサポートされるすべてのサーバー操作を定義するインターフェイス。 これは、特定のLiferayバージョンと対話するために必要なサーバーコネクタを作成する [`ServiceProvider`](https://github.com/liferay/liferay-screens/blob/develop/android/library/core/src/main/java/com/liferay/mobile/screens/util/ServiceProvider.java) を介して作成およびアクセスされます。 `ServiceVersionFactory` は、 [Abstract Factoryパターン](https://en.wikipedia.org/wiki/Abstract_factory_pattern)実装です。

コアレイヤーを構成する要素がわかったので、スクリーンレットレイヤーの詳細を学ぶ準備ができました。

## 関連トピック

[高レベルのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/high-level-architecture)

[スクリーンレットレイヤー](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-layer)

[レイヤーを表示](/docs/7-1/tutorials/-/knowledge_base/t/view-layer)

[スクリーンレットのライフサイクル](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-lifecycle)
