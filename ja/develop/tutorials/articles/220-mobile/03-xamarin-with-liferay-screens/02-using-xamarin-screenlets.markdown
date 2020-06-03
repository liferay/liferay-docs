---
header-id: using-screenlets-in-xamarin-apps
---

# Xamarinアプリでスクリーンレットを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたがしたらのscreenletsを使用して起動することができます [準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-xamarin-projects-for-liferay-screens) Liferayの画面を使用するようにXamarinプロジェクトを。 スクリーンレットのリファレンスドキュメントでは、利用可能なスクリーンレットについて説明しています。

  - [Liferay Screens for Androidのスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android)
  - [Liferay Screens for iOSのスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)

スクリーンレットの使用は非常に簡単です。 このチュートリアルでは、Xamarinアプリにスクリーンレットを挿入して構成する方法を示します。あなたはすぐにスクリーンレットマスターになります\！

## Xamarin.iOS

次の手順に従って、Xamarin.iOSアプリにスクリーンレットを挿入します。

1.  ストーリーボード（Visual StudioのiOSデザイナーまたはXcodeのInterface Builder）にビュー（`UIView`）を挿入します。 XIBファイルを編集している場合は、XIBの親ビュー内にビューを挿入する必要があることに注意してください。

2.  ビューのクラスを、使用するスクリーンレットのクラスに設定します。 たとえば、Login Screenletのクラスは `LoginScreenlet`です。 Visual StudioでXamarin Designer for iOSを使用している場合は、View Controllerのコードで参照できるように、ビューに名前を付ける必要もあります。

    たとえば、次のビデオは、Xamarin Designer for iOSストーリーボードにログインスクリーンレットを挿入するための最初の2つの手順を示しています。 <iframe width="560" height="315" src="https://www.youtube.com/embed/y95XwdpCZVQ" frameborder="0" allowfullscreen mark="crwd-mark"></iframe>

3.  ViewletにScreenletのデリゲートを実装して、アプリでScreenletの動作を構成します。 Screenletがトリガーするイベントをリッスンするようにアプリを構成するには、Screenletのデリゲートメソッドを実装し、View Controllerをデリゲートとして登録します。 各デリゲートメソッドに必ず `[Export（...）]`注釈を付けてください。 これにより、メソッドがScreensで機能するために必要なObjective-Cから呼び出せるようになります。 必要なScreenlet属性も設定する必要があります。 各Liferay Screenletの [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios) は、利用可能な属性とデリゲートメソッドがリストされています。

    | **注：** XamarinのLiferay Screensでは、スクリーンレットデリゲートはプレフィックス付きです| `I`。 たとえば、ネイティブコードでのログインスクリーンレットのデリゲートは| `LoginScreenletDelegate`、Xamarinでは `ILoginScreenletDelegate`です。

    たとえば、Login Screenletのデリゲート `ILoginScreenletDelegate`を実装するView Controllerは次のとおりです。 `ViewDidLoad（）` メソッドは、スクリーンレットの `ThemeName` 属性を設定し（`ThemeName` は、 `BaseScreenlet` 継承を介してすべてのスクリーンレットで使用可能）、View Controllerをデリゲートとして登録します。 このView Controllerは、 `OnLoginResponseUserAttributes` メソッドも実装します。このメソッドは、ログインが成功すると呼び出されます。 また、このメソッドの `[Export（...）]` 注釈にも注意してください。
   
        public partial class ViewController : UIViewController, ILoginScreenletDelegate
        {
            protected ViewController(IntPtr handle) : base(handle) {}
       
            public override void ViewDidLoad()
            {
                base.ViewDidLoad();
       
                // Set the Screenlet's attributes
                this.loginScreenlet.ThemeName = "demo";
       
                // Registers this view controller as the delegate 
                this.loginScreenlet.Delegate = this;
            }
       
            ...
       
            // Delegate methods
       
            [Export("screenlet:onLoginResponseUserAttributes:")]
            public virtual void OnLoginResponseUserAttributes(BaseScreenlet screenlet, 
                NSDictionary<NSString, NSObject> attributes)
            {
                ...
            }
        }

参照してください [ショーケース- iOSアプリ](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS/ViewController) Liferayののscreenletsを使用してビューコントローラの例については。

## Xamarin.Android

次の手順に従って、Xamarin.Androidアプリにスクリーンレットを挿入します。

1.  アプリのレイアウトAXMLファイルを開き、アクティビティまたはフラグメントレイアウトにスクリーンレットのXMLを挿入します。 たとえば、アクティビティの `FrameLayout`にあるログインスクリーンレットのXMLは次のとおりです。
   
        <?xml version="1.0" encoding="utf-8"?>
       <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android" xmlns:app="http://schemas.android.com/apk/res-auto" android:orientation="vertical" android:layout_width="match_parent" android:layout_height="match_parent"> <com.liferay.mobile.screens.auth.login.LoginScreenlet android:id="@+id/login_screenlet" android:layout_width="match_parent" android:layout_height="match_parent" app:basicAuthMethod="email"/> </FrameLayout>

2.  スクリーンレットの属性を設定します。 Liferayスクリーンレットの場合は、 [スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) を参照して、スクリーンレットの必須およびサポートされる属性を確認してください。 このスクリーンショットは、設定されているログインスクリーンレットの属性を示しています。

    ![図1：アプリのレイアウトAXMLファイルを介してスクリーンレットの属性を設定できます。](../../../images/screens-xamarin-android-screenlet-attributes.png)

3.  Screenletがトリガーするイベントをリッスンするようにアプリを構成するには、アクティビティまたはフラグメントクラスにScreenletのリスナーインターフェイスを実装します。 リスナーインターフェイスについては、 [Screenletのリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) を参照してください。 次に、そのアクティビティまたはフラグメントをスクリーンレットのリスナーとして登録します。

    | **注：** XamarinのLiferay Screensでは、スクリーンレットリスナーにプレフィックスが付けられます| `I`。 たとえば、ネイティブコードでのログインスクリーンレットのリスナーは次のとおりです。 `LoginListener`、Xamarinでは `ILoginListener`です。

    たとえば、次のアクティビティクラスは、Login Screenletの `ILoginListener` インターフェイスを実装し、 `loginScreenlet.Listener = this`介して自身をScreenletのリスナーとして登録します。 リスナーメソッド `OnLoginSuccess` および `OnLoginFailure` は、それぞれログインが成功した場合と失敗した場合に呼び出されることに注意してください。 この場合、これらのメソッドは単純なトーストメッセージを出力します。
   
        [Activity]
        public class LoginActivity : Activity, ILoginListener
        {
            LoginScreenlet loginScreenlet;
       
            protected override void OnCreate(Bundle savedInstanceState)
            {
                base.OnCreate(savedInstanceState);
                SetContentView(Resource.Layout.LoginView);
       
                loginScreenlet = (LoginScreenlet) FindViewById(Resource.Id.login_screenlet);
                loginScreenlet.Listener = this;
            }
       
            // ILoginListener
       
            public void OnLoginSuccess(User p0)
            {
                Toast.MakeText(this, "Login success: " + p0.Id, ToastLength.Short).Show();
            }
       
            public void OnLoginFailure(Java.Lang.Exception p0)
            {
                Android.Util.Log.Debug("LoginScreenlet", $"Login failed: {p0.Message}");
            }
       
        }

参照してください [ショーケース-Androidアプリ](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android/Activities) Liferayののscreenletsを使用する活動の例については。

## 関連トピック

[Liferay画面用のXamarinプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-xamarin-projects-for-liferay-screens)

[Xamarin.Androidでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-xamarin-android)

[Xamarin.iOSでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-xamarin-ios)

[Xamarinのビューとテーマを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-xamarin-views-and-themes)

[XamarinのLiferay画面のトラブルシューティングとFAQ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-screens-for-xamarin-troubleshooting-and-faqs)
