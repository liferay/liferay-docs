---
header-id: creating-xamarin-views-and-themes
---

# Xamarinのビューとテーマを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Xamarin.AndroidのビューとXamarin.iOSのテーマは、スクリーンレットのルックアンドフィールをカスタマイズできる類似のコンポーネントであることを思い出してください。 Liferay Screensが提供するビューとテーマを使用することも、独自に作成することもできます。 独自に作成すると、スクリーンレットのUIを正確な仕様に合わせることができます。 このチュートリアルでは、これを行う方法を示します。

ビューとテーマを最初から作成するか、既存のものを基盤として使用できます。 ビューとテーマには、Screenlet UIの動作を実装するためのViewクラス、リスナー/デリゲートに通知し、Interactorを呼び出すためのScreenletクラス、およびUIを定義するためのAXMLまたはXIBファイルが含まれます。

ビューとテーマにはさまざまなタイプもあります。 これらは、ネイティブコードで [ビュー](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views) および [テーマ](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes) を作成するチュートリアルで説明されています。 Xamarin.AndroidでビューまたはXamarin.iOSでテーマを作成する前に、これらのチュートリアルを読む必要があります。

最初に、ビューまたはテーマを作成する場所を決定します。

## ビューまたはテーマの場所の決定

ビューまたはテーマを再利用または再配布する場合は、コード共有用の [マルチプラットフォームライブラリとして新しいXamarinプロジェクトで作成してください](https://developer.xamarin.com/guides/cross-platform/application_fundamentals/nuget-multiplatform-libraries/)。 それ以外の場合は、アプリのプロジェクトで作成します。

## Xamarin.Androidビューの作成

Xamarin.Androidのビューの作成は、ネイティブコードでの作成と非常に似ています。 次のビュータイプを作成できます。

  - **テーマビュー：** Xamarin.Android 7つのテーマビューを作成するには、同じである [ネイティブコードでそうする](/docs/7-1/tutorials/-/knowledge_base/t/themed-views)。 ただし、Xamarin.Androidでは、拡張できるのは既定のビューセットのみです。

  - **子供表示：** Xamarin.Androidに子ビューを作成するには、同じである [ネイティブコードでそうする](/docs/7-1/tutorials/-/knowledge_base/t/child-views)。

  - **拡張ビュー：** Xamarin.Androidでの拡張ビューの作成は、ネイティブコードでの作成とは異なります。 次のセクションでその方法を示します。

### 拡張ビュー

Xamarin.Androidで拡張ビューを作成するには、続く [ネイティブコードで拡張ビュー作成するための手順を](/docs/7-1/tutorials/-/knowledge_base/t/extended-views)、しかし必ず第二のステップで、カスタムビュークラスは、適切なC \＃クラスであることを確認してください。 たとえば、ネイティブコードチュートリアルのViewクラスをC \＃に変換します。

    using System;
    using Android.Content;
    using Android.Util;
    using Com.Liferay.Mobile.Screens.Viewsets.Defaultviews.Auth.Login;
    
    namespace ShowcaseAndroid.CustomViews
    {
        public class LoginCheckPasswordView : LoginView
        {
            public LoginCheckPasswordView(Context context) : base(context) { }
    
            public LoginCheckPasswordView(Context context, IAttributeSet attributes) : base(context, attributes) {}
    
            public LoginCheckPasswordView(Context context, IAttributeSet attributes, int defaultStyle) : base(context, attributes, defaultStyle) {}
    
            public override void OnClick(Android.Views.View view)
            {
                // compute password strength
                if (PasswordIsStrong) {
                    base.OnClick(view);
                }
                else {
                    // Present user message
                }
            }
        }
    }

驚くばかり\！ これで、Xamarin.Androidで拡張ビューを作成する方法がわかりました。

## Xamarin.iOSテーマの作成

Xamarin.iOSのテーマの作成は、ネイティブコード</a>作成と非常に似ています

。 Xamarin.iOSで次のテーマタイプを作成できます。</p> 

  - **子テーマ：** は親テーマと同じUIコンポーネントを表示しますが、外観と位置を変更できます。
  - **拡張テーマ：** は親テーマの機能と外観を継承しますが、両方を追加および変更できます。

最初に、Xamarin.iOSで子テーマを作成する方法を学びます。



### 子テーマ

子テーマは、親テーマの動作とUIコンポーネントを活用して、これらのコンポーネントの外観と位置を変更できるようにします。 コンポーネントを追加または削除することはできず、親テーマは完全なテーマでなければならないことに注意してください。 子テーマは、独自のXIBファイルで視覚的な変更を提示し、親のViewクラスを継承します。

Xamarin.iOSで子テーマを作成するには、次の手順に従います。

1.  Visual Studioで、ScreenletのViewクラスとテーマにちなんで命名された新しいXIBファイルを作成します。 慣例により、 `LoginView` という名前のViewクラスと `demo` という名前のテーマを持つスクリーンレットのXIBファイルは、 `LoginView_demo`という名前にする必要があります。 親テーマのXIBファイルのコンテンツを、新しいXIBファイルの基盤として使用できます。 新しいXIBでは、UIコンポーネントの視覚的プロパティ（位置やサイズなど）を変更できます。 ただし、XIBファイルのカスタムクラス、コンセント接続、または変更してはならない `restorationIdentifier`。 これらは親XIBファイルのものと一致する必要があります。

2.  View Controllerで、スクリーンレットの `ThemeName` プロパティをテーマの名前に設定します。 たとえば、これは、ログインスクリーンレットの `ThemeName` プロパティを、最初のステップからの `デモ` テーマに設定します。
   
        this.loginScreenlet.ThemeName = "demo";
       
   
   これにより、Liferay Screensはすべてのアプリのバンドルでファイル `LoginView_demo` を検索します。 そのファイルが存在しない場合、Screensは代わりにデフォルトテーマ（`LoginView_default`）を使用します。

あなたはの例を参照することができます `LoginView_demo` で [ショーケース- iOSのデモアプリ](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS/CustomViews)。 ファンタスティック\！ 次に、拡張テーマを作成する方法を学びます。



### 拡張テーマ

拡張テーマは別のテーマのUIコンポーネントと動作を継承しますが、両方を追加または変更できます。 たとえば、親テーマのViewクラスを拡張して、親テーマの動作を変更できます。 新しいまたは変更されたUIコンポーネントを含む新しいXIBファイルを作成することもできます。 拡張テーマの親は、完全なテーマ</a>なければなりません。</p> 

拡張テーマを作成するには、次の手順に従います。

1.  Visual Studioで、ScreenletのViewクラスとテーマにちなんで命名された新しいXIBファイルを作成します。 慣例により、 `LoginView` という名前のViewクラスと `demo` という名前のテーマを持つスクリーンレットのXIBファイルは、 `LoginView_demo`という名前にする必要があります。 親テーマのXIBファイルをテンプレートとして使用できます。 Visual StudioのiOSデザイナーまたはXcodeのInterface BuilderでXIBファイルを編集して、テーマのUIを変更します。

2.  親テーマのViewクラスを拡張する新しいViewクラスを作成します。 このクラスには、作成したXIBファイルにちなんで名前を付ける必要があります。 親テーマのViewクラスの機能を追加またはオーバーライドできます。 ログインスクリーンレットのデフォルトテーマ（`LoginView_default`）のViewクラスを拡張する例を次に示します。 ログインボタンの背景色を変更し、進行状況プレゼンターを無効にすることに注意してください。
   
        using LiferayScreens;
        using System;
       
        namespace ShowcaseiOS
        {
            public partial class LoginView_demo : LoginView_default
            {
                public LoginView_demo (IntPtr handle) : base (handle) { }
       
                public override void OnCreated()
                {
                    // You can change the login button color from code
                    this.LoginButton.BackgroundColor = UIKit.UIColor.DarkGray;
                }
       
                // If you don't want a progress presenter, create an empty one
                public override IProgressPresenter CreateProgressPresenter()
                {
                    return new NoneProgressPresenter();
                }
            }
        }
       

3.  新しいViewクラスをテーマのXIBファイルのカスタムクラスとして設定します。
   
   ![図1：XIBテーマファイルに新しいViewクラスを設定します。](../../../images/screens-ios-extended-theme.png)

よくやった\！ これで、拡張テーマの作成方法がわかりました。



## 関連トピック

[Androidビューの作成（ネイティブコード）](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)

[iOSテーマの作成（ネイティブコード）](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[Liferay画面用のXamarinプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-xamarin-projects-for-liferay-screens)

[Xamarinアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-xamarin-apps)

[Xamarin.Androidでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-xamarin-android)

[Xamarin.iOSでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-xamarin-ios)

[XamarinのLiferay画面のトラブルシューティングとFAQ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-screens-for-xamarin-troubleshooting-and-faqs)
