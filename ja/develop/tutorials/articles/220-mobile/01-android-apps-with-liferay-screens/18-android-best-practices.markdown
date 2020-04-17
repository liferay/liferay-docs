---
header-id: android-best-practices
---

# Androidのベストプラクティス

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensを使用してAndroidプロジェクトを開発する場合、コードを可能な限りクリーンでバグのないものにするために従うべきベストプラクティスがいくつかあります。 このチュートリアルにはこれらがリストされています。

## ツールを更新する

最初に、最新のツールがインストールされていることを確認する必要があります。 Android Studioの最新バージョンでは、最新のAndroid APIレベルを使用する必要があります。 画面が *あり* EclipseのADTまたは手動のGradleと仕事のビルド、Androidのメーカーは、好適なIDEです。


<!--
## See the Breaking Changes Document

When updating an app or Screenlet to a new version of Liferay Screens, make sure 
to see the 
[Android breaking changes reference article](/docs/7-0/reference/-/knowledge_base/r/android-breaking-changes). 
This article lists changes to Screens that break functionality in prior 
versions. In most cases, updating your code is relatively straightforward. 
-->

## 命名規則

ここで説明する命名規則を使用すると、Screensライブラリーの一貫性とより良い理解につながります。 これにより、スクリーンレットでの作業がはるかに簡単になります。

また、Liferay Screensは、Android用の [SquareのJava規則](https://github.com/square/java-code-styles) に従い、タブがセパレーターであることにも注意してください。 IDEA、findbugs、PMD、およびcheckstyleの構成は、プロジェクトのソースコードで利用できます。

### スクリーンレットフォルダ

Screenletフォルダーの名前は、Screenletの機能を示す必要があります。 たとえば、 [Login Screenletのフォルダーは `login`](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/auth/login)という名前です。

同じエンティティで動作する複数のスクリーンレットがある場合は、それらのスクリーンレットをそのエンティティに対応する名前のフォルダー内に配置できます。 たとえば、 [Asset Display Screenlet](/docs/7-1/reference/-/knowledge_base/r/asset-display-screenlet-for-android) および [Asset List Screenlet](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-android) どちらもLiferayアセットで機能します。 したがって、これらはScreensライブラリの [`アセット` フォルダー](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/asset)ます。

### スクリーンレット

スクリーンレットの適切な命名は非常に重要です。 Liferay Screensの主な焦点です。 最初にScreenletにプリンシパルアクションの名前を付け、その後に *Screenlet*付ける必要があります。 Screenletクラスもこのパターンに従う必要があります。 たとえば、 [Login Screenletの](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android) 主要なアクションは、ユーザーを@product@インストールにログインさせることです。 したがって、このScreenletのScreenletクラスは `LoginScreenlet`です。

### モデルを見る

スクリーンレットに名前を付けるのと同じ方法でビューモデルに名前を付けますが、 `スクリーンレット`代わりに `ViewModel` を使用します。 また、スクリーンレットのルートフォルダーの `ビュー` フォルダーにビューモデルを配置します。 たとえば、ログインスクリーンレットのビューモデルの名前は `LoginViewModel` あり、 [`login / view` フォルダー](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/auth/login/view)ます。

### インタラクター

Screenletのルートフォルダーにある `interactor` という名前のフォルダーにScreenletのInteractorsを配置します。 各インタラクターに最初に操作対象のオブジェクトの名前を付け、次にそのアクションと接尾辞 *インタラクター*ます。 必要に応じて、各インタラクターをそのアクションにちなんで命名された独自のフォルダーに入れることもできます。 たとえば、 [評価スクリーンレット](/docs/7-1/reference/-/knowledge_base/r/rating-screenlet-for-android) は3つのインタラクターがあります。 それぞれの内部に独自のフォルダにある [ `インタラクター` フォルダ](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/rating/interactor)：

  - `delete / RatingDeleteInteractor`：アセットの評価を削除します
  - `load / RatingLoadInteractor`：アセットの評価をロードします
  - `update / RatingUpdateInteractor`：アセットの評価を更新します

### 視聴回数

スクリーンレットのルートフォルダー内の `ビュー` フォルダーにビューを配置します。 ただし、ビューセットを作成する場合、そのビューをスクリーンレットのルートフォルダーの外側の個別の `ビューセット` フォルダーに配置できます。 これは、画面ライブラリのために何をするかである [その材質やWesteros表示を設定します](https://github.com/liferay/liferay-screens/tree/master/android/viewsets)。 `マテリアル` および `westeros` フォルダーには、それぞれこれらのビューセットが含まれています。 また、各ビューでは、各スクリーンレットのビュークラスが独自のフォルダーにあることに注意してください。 例えば、のためのViewクラス [パスワードScreenletの忘れた](/docs/7-1/reference/-/knowledge_base/r/forgotpasswordscreenlet-for-android) 材質表示がである [フォルダ `ビューセット/材料/ srcに/メイン/ javaの/ COM / Liferayの/モバイル/画面/ビューセット/ /認証/ forgotpassword材料`](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/java/com/liferay/mobile/screens/viewsets/material/auth/forgotpassword)。 このパスの `auth` フォルダーはScreenletのモジュールであることに注意してください。 モジュールでスクリーンレットとビューを作成する必要はありません。 また、 [ビューのレイアウトファイル `forgotpassword_material.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/src/main/res/layout/forgotpassword_material.xml) は `viewsets / material / src / main / res / layout`ことに注意してください。

スクリーンレットの後にビューのレイアウトXMLおよびビュークラスに名前を付け、必要に応じて *スクリーンレット* を *ビュー* に置き換えます。 レイアウトのファイル名には、 `_yourViewName`接尾辞も付ける必要があります。 たとえば、パスワードを忘れた場合のスクリーンレットのマテリアルビューのXIBファイルとビュークラスは、それぞれ `forgotpassword_material.xml` と `ForgotPasswordView.java`です。

## ハードコードされた要素を避ける

ハードコードされた要素の代わりに定数を使用することは、バグを回避する簡単な方法です。 定数は、共通の要素を参照するときにタイプミスをする可能性を減らします。 また、これらの要素を1つの場所に収集します。 たとえば、 [DDL Form ScreenletのScreenletクラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/ddl/form/DDLFormScreenlet.java) は、ユーザーアクション名に次の定数を定義します。

    public static final String LOAD_FORM_ACTION = "loadForm";
    public static final String LOAD_RECORD_ACTION = "loadRecord";
    public static final String ADD_RECORD_ACTION = "addRecord";
    public static final String UPDATE_RECORD_ACTION = "updateRecord";
    public static final String UPLOAD_DOCUMENT_ACTION = "uploadDocument";

## インタラクターの状態を回避する

Liferay Screensは、 [EventBus](http://greenrobot.org/eventbus/) を使用して、デバイスの向きが変わってもネットワークまたはバックグラウンド操作が失われないようにします。 ただし、これが機能するには、Interactorのリクエストがステートレスであることを確認する必要があります。

Interactorが何らかの情報を必要とする場合、 `start` 呼び出しを介してInteractorに情報を渡し、イベントに添付する必要があります。 あなたはからサンプル追加ブックマークScreenletにこの例を見ることができます [Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)。 Screenletクラスの `onUserAction` メソッド（`AddBookmarkScreenlet`）は、Interactorの `start` メソッドを介して、View ModelからInteractorにブックマークのURLとタイトルを渡します。

    @Override
    protected void onUserAction(String userActionName, AddBookmarkInteractor interactor, 
        Object... args) {
            AddBookmarkViewModel viewModel = getViewModel();
            String url = viewModel.getURL();
            String title = viewModel.getTitle();
    
            interactor.start(url, title, folderId);
    }

`start` メソッドは、バックグラウンドスレッドでInteractorの `execute` メソッドを呼び出します。 Add Bookmark ScreenletのInteractor（`AddBookmarkInteractor`）の `execute` メソッドは、 `start` メソッドの引数を含む `BasicEvent` オブジェクトを作成します。

    @Override
    public BasicEvent execute(Object[] args) throws Exception {
        String url = (String) args[0];
        String title = (String) args[1];
        long folderId = (long) args[2];
    
        validate(url, folderId);
    
        JSONObject jsonObject = getJSONObject(url, title, folderId);
        return new BasicEvent(jsonObject);
    }

## レイヤーにとどまる

他のScreenletコンポーネントに属する変数にアクセスするときは、現在のScreenletレイヤー外の変数を避ける必要があります。 これにより、レイヤー間のデカップリングが改善され、バグが減少し、メンテナンスが簡単になります。 Liferayの画面で層の説明については、 [アーキテクチャチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)。 たとえば、InteractorからView変数に直接アクセスしないでください。 代わりに、Interactorの `start` メソッドを介して、View ModelからInteractorにデータを渡します。 前のセクションの例 `onUserAction` メソッドはこれを示しています。

## 関連トピック

[Android向けLiferay画面のトラブルシューティングとFAQ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-screens-for-android-troubleshooting-and-faqs)

[Android向けLiferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)

[Androidスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-screenlets)


<!--
[Android Breaking Changes](/docs/7-0/reference/-/knowledge_base/r/android-breaking-changes)
-->
