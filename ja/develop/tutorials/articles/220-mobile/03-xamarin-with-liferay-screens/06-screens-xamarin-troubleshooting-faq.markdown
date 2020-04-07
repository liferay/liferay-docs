---
header-id: liferay-screens-for-xamarin-troubleshooting-and-faqs
---

# XamarinのLiferay画面のトラブルシューティングとFAQ

[TOC levels=1-4]

Liferayは細心の注意を払ってXamarinのLiferay Screensを開発しましたが、いくつかの一般的な問題に直面する可能性があります。 このチュートリアルでは、これらの問題のヒントと解決策、およびScreens for Xamarinに関する一般的な質問への回答を示します。

## 一般的なトラブルシューティング

特定の問題を調査する前に、Visual StudioとMono .NETフレームワークの正しいバージョンがインストールされていることを最初に確認する必要があります。 各スクリーンレットのリファレンスドキュメント（ [Android](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) および [iOS](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)利用可能）には、これらのバージョンがリストされています。

Liferayが開発したサンプルのXamarin.AndroidおよびXamarin.iOSアプリの調査にも役立つ場合があります。 どちらもスクリーンレット、ビュー（Android）、およびテーマ（iOS）の使用方法の良い例です。

  - [ショーケース-Android](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android)
  - [ショーケース-iOS](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android)

いつでも行き詰まったら、フォーラム [質問を投稿できます](https://www.liferay.com/community/forums/-/message_boards/category/42706063)。 喜んでお手伝いします！

## 一般的な問題

1.  ビルドの問題：

    Visual Studioで *Clean* を実行するだけでは不十分な場合があります。 Visual Studioを閉じ、クリーンアップによって削除されなかった `bin` および `obj` フォルダーをすべて削除してから、プロジェクトを再構築します。

2.  `NSUnknownKeyException` Xamarin.iOSでエラー：

    このエラーは、iOSのLiferay ScreensのXIBファイルに間違ったモジュール名がある場合に発生します。 Interface BuilderでXIBファイルの *カスタムクラス* 割り当てのモジュール名を削除し、Xcodeでこれを解決する必要があります。

3.  `セレクターは既に登録されています` Xamarin.iOSでエラー：

    このエラーは、1つ以上のメソッドが同じ名前を共有しているために発生します。 これを修正するには、バインディングファイルを更新する必要があります。 [Jira](https://issues.liferay.com/browse/LMW/) チケットを提出するか、 [フォーラム](https://www.liferay.com/community/forums/-/message_boards/category/42706063)問題を投稿してください。

4.  Xamarin.iOSは、コンソールにエラーメッセージなしで予期せずクラッシュします。

    ログファイルを確認してください。 Mac OSでは、コンソールを使用してこれを行います。 Windowsでは、イベントビューアーを使用します。 アプリでは、 *ユーザーレポート* をクリックしてから、アプリの名前を探す必要があります。 複数のログファイルが存在する場合があることに注意してください。

5.  アプリはXamarin.iOSのデリゲートメソッドを呼び出しません。

    View Controllerにデリゲートメソッドを実装するときは、必ず `[Export（...）]`アノテーションを付けてください。 また、View ControllerをScreenletインスタンスの `Delegate` プロパティに設定する必要があります。 ログインスクリーンレットのデリゲート、 `ILoginScreenletDelegate`を実装するこのようなビューコントローラーの例を次に示します。
   
        public partial class ViewController : UIViewController, ILoginScreenletDelegate
        {
            protected ViewController(IntPtr handle) : base(handle) {}
       
            public override void ViewDidLoad()
            {
                base.ViewDidLoad();
       
                this.loginScreenlet.Delegate = this;
            }
       
            [Export("screenlet:onLoginResponseUserAttributes:")]
            public virtual void OnLoginResponseUserAttributes(BaseScreenlet screenlet, 
                NSDictionary<NSString, NSObject> attributes)
            {
                ...
            }
       
            ...
        }

## データ型マッピング

Xamarinコードとサンプルアプリの理解を深めるには、このリストを参照してプラットフォーム間のタイプマッピングを比較してください。 XamarinアプリはC \＃で作成する必要がありますが、ネイティブコードとはいくつかの違いがあります。

  - デリゲート（iOS）またはリスナー（Android）クラス：

    これらのクラスは、スクリーンレットのイベントをリッスンするため重要です。 XamarinのLiferay Screensでは、スクリーンレットのデリゲートとリスナーの先頭に `I`が付きます。 たとえば、ネイティブコードでのログインスクリーンレットのデリゲートは `LoginScreenletDelegate`であり、Xamarinでは `ILoginScreenletDelegate`です。 同様に、ネイティブコードでのLogin Screenletのリスナーは `LoginListener`であり、Xamarinでは `ILoginListener`です。 クラスがインターフェースの標準実装であるクラス/インターフェースのペアを定義する場合、同様の命名スキームを使用します。

  - ゲッターおよびセッターメソッド：

    ネイティブコードで値を取得または設定するには、そのゲッターメソッドとセッターメソッドを使用します。 XamarinのLiferay Screensでは、このようなメソッドをプロパティに変換する必要があります。 これらのメソッドが1つしかない場合は、メソッド自体を呼び出すことができます。 たとえば、
    
        // If you implemented a setter and a getter, call the property
        loginScreenlet.Listener = this;
        
        // Otherwise, call the method
        loginScreenlet.getListener();

  - パスカルのケース規則：

    通常、C \＃コードはPascalの場合で記述されます。 ただし、保護されたインスタンスフィールドまたはパラメーターにはキャメルケースを使用する必要があります。

## SwiftとC \＃の同等の言語

  - Swiftのプロトコルは、C \＃のインターフェイスに類似しています。
    
        // Swift
        protocol DoThings {
            func MyMethod() -> String
        }
        
        
        // C#
        interface DoThings
        {
            string MyMethod();
        }

  - Swiftの初期化子は、C \＃のコンストラクターに類似しています。
    
        // Swift
        class MyClass {
            var myVar : String = ""
        
            init(myVar : String) {
                self.myVar = myVar
            }
        }
        
        var testing = MyClass(myVar: "Test")
        
        
        // C#
        class MyClass {
            protected string myVar = "";
        
            public MyClass() {}
        
            public MyClass(string myVar) {
                this.myVar = myVar;
            }
        }
        
        var testing = new MyClass(myVar: "Test");

スウィフトとCの間に言語の同等\＃の詳細については、参照 [このクイックリファレンス](https://download.microsoft.com/download/4/6/9/469501F4-5F6B-4E51-897C-9A216CFB30A3/SwiftCSharpPoster.pdf)。

## JavaとC \＃の同等の言語

クラスまたはインターフェースを拡張または実装するには、Javaは `extends` または `implements` キーワードを使用する必要があります。 C \＃はこれを必要としません：

    // Java
    class Bird extends Vertebrate implements Actions {
        ...
    }
    
    
    // C#
    class Bird : Vertebrate, Actions {
        ...
    }

JavaとC \＃間の言語の同等物の詳細については、を参照 [Java開発者チートシートのためのC \＃](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwjbr8bgz_XXAhWMMyYKHeUPA5wQFgg7MAA&url=http%3A%2F%2Fdownload.microsoft.com%2Fdownload%2FD%2FE%2FE%2FDEE91FC0-7AA9-4F6E-9FFA-8658AA0FA080%2FCSharp%2520for%2520Java%2520Developers%2520-%2520Cheat%2520Sheet.pdf&usg=AOvVaw1i0RzOcmyol7LhD59k9cUE)。

## よくある質問

1.  Visual Studioを使用する必要がありますか？

    いいえ。ただし、強くお勧めします。 ただし、必要に応じて、代わりにXamarin StudioまたはVisual Studio Codeを使用できます。

2.  デリゲートメソッド名の上の `[Export（...）]` の意味は何ですか？

    つまり、この属性により、Objective-Cでプロパティとメソッドが使用可能になります。 [Xamarinのドキュメント](https://developer.xamarin.com/api/type/MonoTouch.Foundation.ExportAttribute/) では、この属性について詳しく説明しています。

## 関連トピック

[Liferay画面用のXamarinプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-xamarin-projects-for-liferay-screens)

[Xamarinアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-xamarin-apps)

[Xamarin.Androidでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-xamarin-android)

[Xamarinのビューとテーマを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-xamarin-views-and-themes)

[Xamarin.iOSでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-xamarin-ios)
