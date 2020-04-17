---
header-id: core-layer-of-liferay-screens-for-ios
---

# iOS向けLiferay画面のコアレイヤー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

コアは、開発者が構造化され分離された方法でスクリーンレットを作成できるようにするマイクロフレームワークです。 すべてのスクリーンレットはコアクラスに基づいて共通の構造を共有しますが、各スクリーンレットには独自の目的と通信APIがあります。

![図1：iOS用Liferay Screensのコアレイヤー。](../../../../images/screens-ios-architecture-02.png)

右から左に、これらは主なコンポーネントです。

  - [**BaseScreenletView**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift)：すべてのスクリーンレットビュークラスの基本クラス。 その子クラスはテーマレイヤーに属します。 ビュークラスは標準XIBファイルを使用してUIをレンダリングし、データが変更されたときに更新します。 `BaseScreenletView` クラスには、子クラスが上書きできるテンプレートメソッドが含まれています。 親テーマから独自のテーマを開発する場合、Screenletのプロパティを読み取るか、このクラスからそのメソッドを呼び出すことができます。 UIのユーザーアクションはすべてこのクラスで受信され、Screenletクラスにリダイレクトされます。

  - [**BaseScreenlet**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift)：すべてのScreenletクラスの基本クラス。 Screenletクラスは、 `ScreenletView` クラスを介してUIイベントを受け取り、Interactorsをインスタンス化して、そのUIイベントを処理して応答します。 Interactorの結果が受信されると、それに応じて `ScreenletView` （UI）が更新されます。 `BaseScreenlet` クラスには、子クラスが上書きできる [テンプレートメソッド](http://www.oodesign.com/template-method-pattern.html) セットが含まれています。

  - [**Interactor**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/Interactor.swift)：ScreenletがサポートするすべてのInteractorの基本クラス。 Interactorクラスは、Screenletでサポートされる特定のユースケースを実装します。 Screenletが複数のユースケースをサポートする場合、異なるインタラクターが必要です。 Interactorがリモートデータを取得する必要がある場合は、サーバーコネクタを使用して取得します。 サーバーコネクタが操作の結果を返すと、インタラクターはその結果をスクリーンレットに返します。 その後、スクリーンレットは `ScreenletView` （UI）ステータスを変更します。

  - [**ServerConnector**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseConnectors/ServerConnector.swift)：スクリーンレットがサポートするすべてのLiferayコネクタの基本クラス。 コネクタは、ローカルまたはリモートのデータソースから非同期にデータを取得します。 Interactorクラスは、これらのConnectorクラスをインスタンス化して開始します。

  - [**SessionContext**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Context/SessionContext.swift)：ログインしたユーザーのセッションを保持するオブジェクト（通常はシングルトン）。 アプリは、ユーザーには見えない暗黙的なログイン、または明示的なユーザー入力に依存してセッションを作成するログインを使用できます。 ユーザーログインは、 [ログインスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-ios)実装できます。 詳細については、iOS</a>セッションにアクセスするための
チュートリアルを参照してください。</p></li> 
    
      - [**LiferayServerContext**](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Context/LiferayServerContext.swift)：サーバー構成パラメーターを保持するシングルトンオブジェクト。 `liferay-server-context.plist` ファイルからロードされます。 ほとんどのスクリーンレットは、これらのパラメーターをデフォルト値として使用します。</ul>
