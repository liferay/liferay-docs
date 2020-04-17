---
header-id: screenlet-layer-of-liferay-screens-for-ios
---

# iOS用Liferayスクリーンのスクリーンレットレイヤー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screenletレイヤーには、iOSのLiferay Screensで使用可能なScreenletが含まれています。 次の図は、Core、Interactor、Theme、およびConnectorレイヤーに関連するScreenletレイヤーを示しています。 このセクションでは、図のScreenletクラスについて説明します。

![図1：この図は、iOS Screenlet Layerと他のScreensコンポーネントとの関係を示しています。](../../../../images/screens-ios-architecture-03.png)

スクリーンレットは、いくつかのSwiftクラスとXIBファイルで構成されています。

  - **MyScreenletViewModel：** UIに表示される属性を定義するプロトコル。 通常、ユーザーに提示されるすべての入力値と出力値を考慮します。 たとえば、 [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Auth/LoginScreenlet/LoginViewModel.swift) は、ユーザー名やパスワードなどの属性が含まれます。 これらの値を読み取って検証することにより、コネクターを構成できます。 また、スクリーンレットは、デフォルト値と操作結果に基づいてこれらの値を変更できます。

  - **MyScreenlet：** アプリ開発者が対話するScreenletコンポーネントを表すクラス。 次のものが含まれます。

      - スクリーンレットの動作を構成するための検査可能なパラメーター。 初期状態は、スクリーンレットのデータで設定できます。
      - 選択したテーマに基づく、スクリーンレットのビューへの参照。 スクリーンレットの要件を満たすには、すべてのテーマが `ViewModel` プロトコルを実装する必要があります。
      - コネクタを呼び出すための任意の数のメソッド。 オプションで、アプリ開発者が電話できるように公開することができます。
      - Screenletが特定のイベントを呼び出すことができるオプションの（ただし推奨） [デリゲートオブジェクト](https://developer.apple.com/library/ios/documentation/general/conceptual/DevPedia-CocoaCore/Delegation.html)。

  - **MyUserCaseInteractor：** 各Interactorは、ユースケースを実装する操作を実行します。 これらは、ローカル操作、リモート操作、またはそれらの組み合わせです。 操作は、順次または並行して実行できます。 最終結果は、結果 `オブジェクト` 保存され、通知されたときにスクリーンレットで読み取ることができます。 Screenletが必要とするInteractorクラスの数は、サポートするユースケースの数によって異なります。

  - **MyOperationConnector：** これはインタラクターに関連していますが、1つ以上のコネクターがあります。 サーバーコネクタがバックエンドコールである場合、通常、要求は1つだけです。 各サーバーコネクタは、関連する一連の値を取得します。 結果は `結果` オブジェクトに保存され、通知時にInteractorが読み取ることができます。 Interactorが必要とするサーバーコネクタクラスの数は、クエリを実行する必要があるエンドポイントの数、またはサポートする必要があるさまざまなサーバーの数によって異なります。 コネクタは常に [ファクトリクラス](https://en.wikipedia.org/wiki/Abstract_factory_pattern)を使用して作成されます。 したがって、コントロール</a>

反転を利用できます。 これにより、独自のファクトリクラスを実装して、独自のコネクタオブジェクトの作成に使用できます。 、あなたのファクトリクラスを使用するには、それを指定するには、画面に指示する `のLiferay-サーバcontext.plist` 説明されているようにファイル [画面のためのあなたのiOSプロジェクトの準備にチュートリアルで](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens#configuring-communication-with-liferay)。</p></li> 
    
      - **MyScreenletView \ _themeX：** 特定のテーマに属するクラス。 図では、このテーマは *ThemeX*です。 このクラスは、関連するXIBファイルを使用してスクリーンレットのUIをレンダリングします。 ViewオブジェクトとXIBファイルは、 `@IBOutlet` および `@IBAction`などの標準メカニズムを使用して通信します。 XIBファイルでユーザーアクションが発生すると、 `BaseScreenletView` 受信され、 `performAction` メソッドを介してScreenletクラスに渡されます。 さまざまなイベントを識別するために、コンポーネントの `restoreIdentifier` プロパティが `performAction` メソッドに渡されます。

  - **MyScreenletView \ _themeX.xib：** スクリーンレットのビューをレンダリングする方法を指定するXIBファイル。 その名前は非常に重要です。 慣例により、 *FooScreenletView* という名前のビュークラスと *BarTheme* という名前のテーマを持つスクリーンレットには、 `FooScreenletView_barTheme.xib`という名前のXIBファイルが必要です。</ul> 

詳細については、上のチュートリアルを参照してください [のiOSのscreenlets作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)。
