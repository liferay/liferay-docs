---
header-id: ios-best-practices
---

# iOSのベストプラクティス

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensを使用してiOSプロジェクトを開発するときは、コードを可能な限りクリーンでバグのないものにするために従うべきベストプラクティスがいくつかあります。 このチュートリアルにはこれらがリストされています。 このチュートリアルでは、GitHubのLiferay Screensプロジェクトに貢献するためのSwiftのコーディング規約は取り上げていません。 [ここをクリックして](https://github.com/liferay/liferay-screens/blob/master/ios/swift-style-guide.md) をご覧ください。

## 命名規則

ここで説明する命名規則を使用すると、Screensライブラリーの一貫性とより良い理解につながります。 これにより、スクリーンレットでの作業がはるかに簡単になります。

### スクリーンレットフォルダ

Screenletフォルダーの名前は、Screenletの機能を示す必要があります。 たとえば、 [Login Screenletのフォルダーの名前は `LoginScreenlet`](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/LoginScreenlet)です。

同じエンティティで動作する複数のスクリーンレットがある場合は、それらのスクリーンレットをそのエンティティに対応する名前のフォルダー内に配置できます。 たとえば、 [Asset Display Screenlet](/docs/7-1/reference/-/knowledge_base/r/asset-display-screenlet-for-ios) および [Asset List Screenlet](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-ios) どちらもLiferayアセットで機能します。 したがって、それらはScreensライブラリの [`Asset` フォルダ](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Asset)ます。

### スクリーンレット

スクリーンレットの適切な命名は非常に重要です。 Liferay Screensの主な焦点です。 Screenletには、最初にその主要なアクションで名前を付け、その後に *Screenlet*付ける必要があります。 Screenletクラスもこのパターンに従う必要があります。 たとえば、 [Login Screenletの](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-ios) 主要なアクションは、Liferayインスタンスにユーザーをログインさせることです。 Screenletクラスは `LoginScreenlet`です。

### モデルを見る

スクリーンレットのルートフォルダにビューモデルを配置し、スクリーンレットの名前を付けてください。 たとえば、 [Forgot Password Screenletの](/docs/7-1/reference/-/knowledge_base/r/forgotpasswordscreenlet-for-ios) View Modelは [`ForgotPasswordScreenlet` フォルダー](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Auth/ForgotPasswordScreenlet) あり、 `ForgotPasswordViewModel`という名前です。

### インタラクター

Screenletのルートフォルダーにある `Interactors` という名前のフォルダーにScreenletのInteractorsを配置する必要があります。 最初に各インタラクターにアクションを指定し、その後に *インタラクター*ます。 たとえば、 [評価スクリーンレット](/docs/7-1/reference/-/knowledge_base/r/rating-screenlet-for-ios) は、 [つの3つのインタラクター</code> フォルダー](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Core/Rating/Interactors)`つのインタラクターがあります。</p>

<ul>
<li><code>DeleteRatingInteractor`：アセットの評価を削除します</li>
  - `LoadRatingsInteractor`：アセットの評価をロードします
  - `UpdateRatingInteractor`：資産の評価を更新します</ul>

### コネクター

Interactorsと同じ命名規則でコネクターに名前を付け、 *Interactor* を *Connector*置き換え* 。 コネクタがLiferayサービスを呼び出す場合、 *コネクタ* 前に *Liferay*ます。 例えば、 [コネクタ `CommentAddLiferayConnector`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Comment/Add/Connectors/CommentAddLiferayConnector.swift) Liferayのインスタンス内のアセットにコメントを追加します。 任意のURLからWebページのタイトルを取得するコネクターは、 `GetWebsiteTitleConnector`と呼ばれます。</p>

### テーマ

Screenletのルートフォルダー内の *Themes* という名前のフォルダーにScreenletのテーマを配置します。 ただし、複数のスクリーンレット用に同様のスタイルのテーマのグループを作成している場合は、それらをスクリーンレットのルートフォルダーの外側の個別の *テーマ* フォルダーに配置できます。 これは、画面ライブラリのために何をするかである [そのデフォルトとFlat7テーマ](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes)。 `Default` および `Flat7` フォルダーにはそれぞれ、いくつかのスクリーンレット用の同様のスタイルのテーマが含まれています。 また、各スクリーンレットのテーマは独自のフォルダーにあることに注意してください。 例えば、 [パスワードを忘れた場合Screenletの](/docs/7-1/reference/-/knowledge_base/r/forgotpasswordscreenlet-for-ios) デフォルトテーマである [フォルダ `テーマ/デフォルト/認証/ ForgotPasswordScreenlet`](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Default/Auth/ForgotPasswordScreenlet)。 `Auth` フォルダーがScreenletのモジュールであることに注意してください。 モジュールでスクリーンレットとテーマを作成する必要はありません。

テーマはXIBファイルとViewクラスで構成されていることを思い出してください。 Screenletにちなんでこれらに名前を付けますが、 *Screenlet*代わりに *View* 使用します。 ファイル名には、 `_yourThemeName`接尾辞も付ける必要があります。 たとえば、パスワードを忘れた場合のスクリーンレットのデフォルトテーマのXIBファイルとビュークラスは、それぞれ `ForgotPasswordView_default.xib` と `ForgotPasswordView_default.swift`です。

## ハードコードされた要素を避ける

ハードコードされた要素の代わりに定数を使用することは、バグを回避する簡単な方法です。 定数は、共通の要素を参照するときにタイプミスをする可能性を減らします。 また、これらの要素を1つの場所に収集します。 あなたときたとえば、 [あなたのScreenletにアクションを追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-screenlet-actions)、として使用される各Screenletアクション `restorationIdentifier` ViewクラスではScreenletクラスの定数として定義されています。 Screenletクラスの `createInteractor` メソッドは、定数を使用してアクションを区別します。 代わりに、両方の場所で各アクションを手動で入力した場合、入力ミスによりスクリーンレットが破損し、追跡するのが難しくなります。 定数を介して1か所でアクションを定義することで、この潜在的に厄介な問題を回避できます。

Screenletの属性は、各Screenletの [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)にリストされているようなもので、これのもう</a>つの良い例です。 これらはInterface Builderで直接設定できますが、 `plist` ファイルの定数を介して設定することをお勧めします。 これにより、スクリーンレットのすべての属性が、バージョン管理の対象となる単一の場所に配置されます。 で属性を設定する方法については `のplist` ファイル、 [Liferayのセクションでの通信の構成を参照してください](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens#configuring-communication-with-liferay) Liferayの画面用のiOSプロジェクトの準備のチュートリアルの。

コードでこれらの値を取得するには、次の `LiferayServerContext` メソッドを使用できます。

  - `propertyForKey`：プロパティを `AnyObject`として取得
  - `numberPropertyForKey`：プロパティを `NSNumber`として取得します。
  - `longPropertyForKey`：プロパティを `Int64として取得します`。
  - `intPropertyForKey`： `Int`としてプロパティを取得します。
  - `booleanPropertyForKey`：プロパティを `Boolとして取得`。
  - `datePropertyForKey`：プロパティを `NSDateとして取得します`。
  - `stringPropertyForKey`：プロパティを `文字列として取得します`。

たとえば、次のコードは `galleryFolderId` 値を取得し、Image Gallery Screenletの `folderId` 属性に設定します。

    @IBOutlet weak var imageGalleryScreenlet: ImageGalleryScreenlet? {
        didSet {
            imageGalleryScreenlet?.delegate = self
            imageGalleryScreenlet?.presentingViewController = self
    
            imageGalleryScreenlet?.repositoryId = LiferayServerContext.groupId
            imageGalleryScreenlet?.folderId = LiferayServerContext.longPropertyForKey("galleryFolderId")
        }
    }

## レイヤーにとどまる

他のScreenletコンポーネントに属する変数にアクセスするときは、現在のScreenletレイヤー外の変数を避ける必要があります。 これにより、レイヤー間のデカップリングが改善され、バグが減少し、メンテナンスが簡単になります。 Liferayの画面で層の説明については、 [アーキテクチャチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)。 たとえば、InteractorからView変数に直接アクセスしないでください。 次のInteractorの `start` メソッドは、Viewインスタンスを取得し、その `title` 変数にアクセスします。

    public class MyInteractor: Interactor {
        override func start() -> Bool {
            if let view = self.screenlet.screenletView as? MyView {
                let title = view.title
                ...
            }
        }
    }

代わりに、変数をInteractorの初期化子に渡す必要があります。 Interactorには、初期化子で設定された独自の `タイトル` 変数が含まれています。

    public class MyInteractor: Interactor {
    
        public let title: String
    
        //MARK: Initializer
    
        public init(screenlet: BaseScreenlet, title: String) {
            self.title = title
            super.init(screenlet: screenlet)
        }
    }

Screenletクラスの `createInteractor` メソッドは、Interactorのインスタンスを作成するときにこの初期化子を呼び出します。 また、スクリーンレットのビューモデルを使用して、ビューの `タイトル`を取得します。 チュートリアル [iOSスクリーンレットでの複数のテーマのサポート](/docs/7-1/tutorials/-/knowledge_base/t/supporting-multiple-themes-in-your-ios-screenlet)で説明したように、ビューモデルはビューの抽象化レイヤーとして機能し、スクリーンレットでさまざまなテーマを使用できます。

    public class MyScreenlet: BaseScreenlet {
        ...
        override public func createInteractor(name name: String, sender: AnyObject?) -> Interactor? {
            let interactor = MyInteractor(self, title: viewModel.title)
            ...
        }
        ...
    }

ただし、このルールを破ることができる場所がいくつかあります（そうしないと、レイヤーが相互作用できなくなります）。

  - Screenletクラスの `createInteractor` メソッド。 ユーザーの入力を取得するには、このメソッドはビューの計算されたプロパティにアクセスする必要があります。

  - スクリーンレットクラスのインタラクターの `onSuccess` クロージャー。 ここでは、Interactorの結果オブジェクトを取得する必要があります。

  - コネクターを使用する場合、Interactorの `completedConnector` メソッド。 このメソッドでは、コネクターの結果オブジェクトを取得する必要があります。

  - Screenletクラスのビューモデル参照。 これは、スクリーンレットがビューと通信するために必要です。

## 関連トピック

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[iOSリストスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-list-screenlets)

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[iOSスクリーンレットで複数のテーマをサポートする](/docs/7-1/tutorials/-/knowledge_base/t/supporting-multiple-themes-in-your-ios-screenlet)

[スクリーンレットアクションの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-screenlet-actions)

[スクリーンレットでコネクタを作成して使用する](/docs/7-1/tutorials/-/knowledge_base/t/create-and-use-a-connector-with-your-screenlet)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)
