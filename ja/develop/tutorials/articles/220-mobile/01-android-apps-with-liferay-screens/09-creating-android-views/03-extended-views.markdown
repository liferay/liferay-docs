---
header-id: extended-views
---

# 拡張ビュー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

拡張ビューは、親ビューの動作と外観を継承しますが、両方を変更および追加できます。 これを行うには、カスタムビュークラスと新しいレイアウトXMLファイルを記述します。 拡張ビューは、スクリーンレット、リスナー、インタラクターなど、親ビューの他のすべてのクラスを継承します。 拡張ビューの親はフルビューである必要があります。

ここで説明する拡張ビューの例は、ログインスクリーンレットのデフォルトビューと同じUIコンポーネントを示していますが、パスワード強度の計算という機能を追加しています。 もちろん、パスワードの強度の計算に制限されるわけではありません。必要なものを実装できます。

1.  ビューのスクリーンレットとその使用目的にちなんだ名前の新しいレイアウトXMLファイルを作成します。 UIの構築を開始する良い方法は、親のレイアウトXMLファイルを複製し、テンプレートとして使用することです。 ログインスクリーンレットの拡張ビューの新しいレイアウトファイルは、ログインスクリーンレットのデフォルトビューレイアウトファイル [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/layout/login_default.xml) 基づいており、パスワード強度計算を追加するため、 `login_password.xml`と呼ばれます。

2.  親Viewクラスを拡張する新しいカスタムViewクラスを作成します。 スクリーンレットと、追加またはオーバーライドする機能にちなんで名前を付けます。 サンプルのViewクラス `LoginCheckPasswordView` は、デフォルトビューの [`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java) クラスを拡張し、 `onClick` メソッドをオーバーライドしてパスワードの強度を計算します。
   
        public class LoginCheckPasswordView extends LoginView {
       
            // parent's constructors go here...
       
            @Override
            public void onClick(View view) {
                // compute password strength
       
                if (passwordIsStrong) {
                    super.onClick(view);
                }
                else {
                    // Present user message
                }
            }
       
        }

3.  カスタムビューの完全修飾クラス名の後に、レイアウトXMLファイルのルート要素の名前を変更します。 たとえば、 `login_password.xml` ルート要素は `com.your.package.LoginCheckPasswordView`です。
   
        <com.your.package.LoginCheckPasswordView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            ...

4.  新しいレイアウトの名前を `liferay：layoutId` 属性の値として使用して、アクティビティまたはフラグメントのいずれかにビューのスクリーンレットを挿入します。 たとえば、新しい `login_password` レイアウトを使用するには、アクティビティまたはフラグメントに `LoginScreenlet` を挿入し、 `liferay：layoutId = "@ layout / login_password"`ます。

[Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) サンプルアプリの [Westerosビューセット](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros) は、確認できる拡張ビューがいくつかあります。 ログインスクリーンレットのパスワードをクリアテキストで表示するための新しいボタンを追加する拡張ビューがあります。 ビューは、カスタムレイアウトファイル [`login_westeros.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/res/layout/login_westeros.xml) およびカスタムビュークラス [`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/login/LoginView.java)ます。 Westerosビューセットには、ユーザーポートレートスクリーンレットの拡張ビューも含まれています。ユーザーのポートレート写真の境界線の色と幅を変更し、カスタムレイアウトファイル [`userportrait_westeros.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/res/layout/userportrait_westeros.xml) とカスタムビュークラス [`UserPortraitView`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/userportrait/UserPortraitView.java)ます。

## 関連トピック

[テーマ別のビュー](/docs/7-1/tutorials/-/knowledge_base/t/themed-views)

[子ビュー](/docs/7-1/tutorials/-/knowledge_base/t/child-views)

[全景](/docs/7-1/tutorials/-/knowledge_base/t/full-views)

[ビューをパッケージ化する](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-views)
