---
header-id: using-screenlets-in-ios-apps
---

# iOSアプリでのスクリーンレットの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたがしたら [準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens) Liferayの画面を使用するようにiOSのプロジェクトを、アプリでのscreenletsを使用することができます。 Liferayスクリーンレットはたくさんありますが、スクリーンレット [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)記載されています。 このチュートリアルでは、SwiftおよびObjective-Cで記述されたiOSアプリにスクリーンレットを挿入および構成する方法を示します。 また、それらをローカライズする方法についても説明します。 あなたはすぐにスクリーンレットマスターになります\！

## iOSアプリでのスクリーンレットの挿入と構成

iOSプロジェクトでスクリーンレットを使用する最初のステップは、プロジェクトに新しいUIViewを追加することです。 Interface Builderで、ストーリーボードまたはXIBファイルに新しいUIViewを挿入します。 これを図1に示します。

![図1：新しいUIViewをプロジェクトに追加します。](../../../images/screens-ios-add-uiwindow.png)

次に、スクリーンレットの名前をカスタムクラスとして入力します。 たとえば、ログインスクリーンレットを使用している場合は、クラスとしてログインスクリーンレットを入力します。

![図2：スクリーンレットに合わせてカスタムクラスを変更します。](../../../images/screens-ios-custom-class.png)

ここで、 `ViewController` クラスでScreenletのデリゲートプロトコルに準拠する必要があります。 たとえば、ログインスクリーンレットのデリゲートクラスは `LoginScreenletDelegate`です。 これは、次のコードに示されています。 `onLoginResponse` および `onLoginError`の機能を実装する必要があることに注意してください。 これは、コード内のコメントで示されています。

    class ViewController: UIViewController, LoginScreenletDelegate {
    
        ...
    
       func screenlet(screenlet: BaseScreenlet,
               onLoginResponseUserAttributes attributes: [String:AnyObject]) {
           // handle succeeded login using passed user attributes
        }
    
        func screenlet(screenlet: BaseScreenlet,
                onLoginError error: NSError) {
            // handle failed login using passed error
        }
    
        ...

CocoaPodsを使用している場合、View ControllerにLiferay画面をインポートする必要があります。

    import LiferayScreens

Screenletのデリゲートプロトコルが `ViewController` クラスに準拠したので、Interface Builderに戻り、ScreenletのデリゲートをView Controllerに接続します。 使用しているスクリーンレットに複数のアウトレットがある場合、それらも割り当てることができます。

注現在のXcodeが有するいくつかの [の問題](http://stackoverflow.com/questions/26180268/interface-builder-iboutlet-and-protocols-for-delegate-and-datasource-in-swift/26180481#26180481) スイフトソースコードへの出口を接続します。 これを回避するには、デリゲートデータ型を変更するか、コードでアウトレットを割り当てます。 View Controllerで、次の手順を実行します。

1.  スクリーンレットへの参照を保持するアウトレットを宣言します。 Interface Builderで問題なく接続できます。

    ![図3：コンセントをScreenletリファレンスに接続します。](../../../images/screens-ios-xcode-add-screenlet-delegate.png)

2.  スクリーンレットのデリゲートに `viewDidLoad` メソッドを割り当てます。 これは通常、Interface Builderで行われる接続です。

これらの手順は、ログインスクリーンレットのView Controllerの次のコードに示されています。

    class ViewController: UIViewController, LoginScreenletDelegate {
    
        @IBOutlet var screenlet: LoginScreenlet?
    
        override func viewDidLoad() {
            super.viewDidLoad()
            self.screenlet?.delegate = self
        }
    
        ...

![図4：Screen BuilderのデリゲートをInterface Builderに接続します。](../../../images/screens-ios-xcode-delegate.png)

驚くばかり\！ これで、アプリでスクリーンレットを使用する方法がわかりました。 Objective-Cコードからスクリーンレットを使用する必要がある場合は、次のセクションの指示に従ってください。

## Objective-Cのスクリーンレットを使用する <iframe width="560" height="315" src="https://www.youtube.com/embed/YuniaiFzRbQ" frameborder="0" allowfullscreen mark="crwd-mark"></iframe>

Objective-CコードからScreenletクラスを呼び出す場合、追加のヘッダーファイルをインポートする必要があります。 すべてのObjective-Cファイルにヘッダーファイル `LiferayScreens-Swift.h` をインポートするか、プリコンパイラヘッダーファイルを構成できます。

最初のオプションでは、すべてのObjective-Cファイルに次のインポート行を追加します。

    #import "LiferayScreens-Swift.h"

または、次の手順に従ってプリコンパイラヘッダーファイルを構成できます。

1.  プリコンパイラヘッダーファイル（例： `PrefixHeader.pch`）を作成し、プロジェクトに追加します。

2.  インポート `LiferayScreens-Swift.h` あなただけのプリコンパイラのヘッダファイルで作成されました。

3.  ターゲットの次のビルド設定を編集します。 `path / to / your / file /` を `PrefixHeader.pch` ファイルへのパスに置き換えてください：

      - プリコンパイルプレフィックスヘッダー： `はい`
      - プレフィックスヘッダー： `path / to / your / file / PrefixHeader.pch`

    ![図5：Xcode設定の <code>PrefixHeader.pch</code> 構成。](../../../images/screens-ios-xcode-prefix.png)

プリコンパイラヘッダーファイル [`PrefixHeader.pch`](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Showcase-objc/LiferayScreens-Showcase-Objc/PrefixHeader.pch) をテンプレートとして使用できます。

スーパー\！ これで、アプリでObjective-Cコードのスクリーンレットを使用する方法がわかりました。

## スクリーンレットのローカライズ

Appleの [標準メカニズム](https://developer.apple.com/library/ios/documentation/MacOSX/Conceptual/BPInternational/Introduction/Introduction.html) に従って、スクリーンレットにローカライズを実装します。 注：Screenletは複数の言語をサポートする場合がありますが、アプリではそれらの言語もサポートする必要があります。つまり、スクリーンレットの言語のサポートは、アプリがその言語をサポートしている場合にのみ有効です。 言語をサポートするには、プロジェクトの設定でローカライズとして追加してください。

![図6：プロジェクトの設定でのXcodeのローカライズ。](../../../images/screens-ios-xcode-localizations.png)

行く方法\！ これで、iOSアプリでスクリーンレットを使用する方法がわかりました。

## 関連トピック

[Liferayスクリーン用のiOSプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens)

[iOSスクリーンレットでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)
