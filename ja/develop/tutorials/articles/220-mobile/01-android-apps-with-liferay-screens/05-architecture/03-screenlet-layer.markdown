---
header-id: screenlet-layer
---

# スクリーンレットレイヤー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

スクリーンレットレイヤーには、Liferay Screens for Androidで使用可能なスクリーンレットが含まれています。 次の図では、 *MyScreenlet* というプレフィックスが付けられたScreenletクラスを使用して、Screenletレイヤーとコア、ビュー、およびインタラクターコンポーネントとの関係を示しています。

![図1：この図は、Android Screenletレイヤーと他のScreensコンポーネントとの関係を示しています。](../../../../images/screens-android-architecture-03.png)

スクリーンレットは、いくつかのJavaクラスとXML記述子ファイルで構成されています。

**MyScreenletViewModel：** UIに表示される属性を定義するインターフェイス。 通常、ユーザーに提示されるすべての入力値と出力値を考慮します。 たとえば、 [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java) は、ユーザー名やパスワードなどの属性が含まれます。 スクリーンレットは、属性値を読み取り、Interactor操作を呼び出し、操作の結果に基づいてこれらの値を変更できます。

**MyScreenlet：** アプリ開発者が対話するScreenletコンポーネントを表すクラス。 次のものが含まれます。

  - スクリーンレットの動作を構成するための属性フィールド。 これらはScreenletの `createScreenletView` メソッドで読み取られ、デフォルト値もオプションで設定できます。
  - `liferay：layoutId` 属性の値で指定されたスクリーンレットのビューへの参照。 注：ビューは、スクリーンレットの `ViewModel` インターフェイスを実装する必要があります。
  - Interactor操作を呼び出すための任意の数のメソッド。 オプションで、アプリ開発者が電話できるように公開することができます。 また、通常のリスナー（Androidの `OnClickListener`）を介してビュークラスで受信したUIイベント、または `performUserAction` メソッドを介してスクリーンレットに転送されるイベントを処理できます。
  - Screenletが特定のイベントで呼び出すオプションの（ただし推奨）リスナーオブジェクト。

**MyScreenletInteractor：** は、サーバーと通信するかLiferayサービスを消費するエンドツーエンドのユースケースを実装します。 いくつかの中間ステップを実行する場合があります。 たとえば、サーバーに要求を送信し、応答に基づいてローカル値を計算し、この値を別のサーバーに送信します。 インタラクションが完了すると、Interactorはリスナーに通知する必要があります。リスナーの1つは通常、Screenletクラスインスタンスです。 Screenletが必要とするインターアクターの数は、サポートするサーバーユースケースの数によって異なります。 たとえば、 [Login Screenlet](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java) クラスは1つのユースケース（ユーザーのログイン）のみをサポートするため、Interactorは1つしかありません。 ただし、 [DDL Forms Screenlet](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/ddl/form/DDLFormScreenlet.java) クラスはいくつかのユースケース（フォームのロード、レコードのロード、フォームの送信など）をサポートしているため、ユースケースごとに異なるInteractorクラスを使用します。

**MyScreenletConnector62** および **MyScreenletConnector70**：特定のLiferayバージョンと通信するために必要なインタラクターを作成するクラス。 `ServiceProvider` は、適切なコネクタを返すシングルトン `ServiceVersionFactory` を作成します。

**MyScreenletDefaultView：** デフォルトのレイアウトでスクリーンレットのUIをレンダリングするクラス。 たとえば、図3のクラスは、デフォルトビューセットに属します。 Viewオブジェクトとレイアウトファイルは、 `findViewById` メソッドやリスナーオブジェクトなどの標準メカニズムを使用して通信します。 ユーザーアクションは、指定されたリスナー（たとえば、 `OnClickListener`）によって受信され、 `performUserAction` メソッドを介してScreenletオブジェクトに渡されます。

**myscreenlet \ _default.xml：** スクリーンレットのビューのレンダリング方法を指定するXMLファイル。 スクリーンレットのレイアウトXMLファイルのスケルトンは次のとおりです。

    <?xml version="1.0" encoding="utf-8"?>
    <com.your.package.MyScreenletView 
        xmlns:android="http://schemas.android.com/apk/res/android">
    
        <!-- Put your regular components here: EditText, Button, etc. -->
    
    </com.your.package.MyScreenletView>

スクリーンレットの詳細については、チュートリアル [Androidスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets) を参照してください。 次に、Viewレイヤーの詳細について説明します。

## 関連トピック

[高レベルのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/high-level-architecture)

[コア層](/docs/7-1/tutorials/-/knowledge_base/t/core-layer)

[レイヤーを表示](/docs/7-1/tutorials/-/knowledge_base/t/view-layer)

[スクリーンレットのライフサイクル](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-lifecycle)
