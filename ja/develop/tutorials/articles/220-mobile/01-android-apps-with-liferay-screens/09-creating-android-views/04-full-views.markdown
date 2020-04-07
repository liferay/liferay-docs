---
header-id: full-views
---

# 全景

[TOC levels=1-4]

フルビューには、一意のScreenletクラス、Viewクラス、およびレイアウトXMLファイルがあります。 スタンドアロンであり、どのビューからも継承しません。 ニーズを満たすために拡張できる他のビューがない場合、またはリスナーをカスタマイズするか、カスタムインタラクターを呼び出すことによってのみスクリーンレットの動作を拡張できる場合は、フルビューを作成する必要があります。 フルビューを作成するには、そのScreenletクラス、Viewクラス、およびレイアウトXMLファイルを作成する必要があります。 ここでのログインスクリーンレットの完全なビューの例は、ユーザー名に対して単一の `EditText` コンポーネントを示しています。 パスワードには、 [`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID)使用します。 [画面のテストのApp](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) 用途 [本フルビュー](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview)。

次の手順に従って、フルビューを作成できます。

1.  新しいレイアウトXMLファイルを作成し、その中にUIを構築します。 UIの構築を開始する良い方法は、別のビューのレイアウトXMLファイルを複製し、テンプレートとして使用することです。 レイアウトのXMLファイルには、ビューのスクリーンレットと使用目的の名前を付けます。 カスタムビューの完全修飾クラス名に基づいてルート要素に名前を付けます（次に作成します）。

    ログインスクリーンレット用のテストアプリのフルビューレイアウトXMLファイルは、 [`login_full.xml`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/login_full.xml)と呼ばれ

 。 LongScreenletのデフォルトビューファイル [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/layout/login_default.xml)からコピーされた `EditText` および `Button` 要素を指定します。</p> 
   
        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.LoginFullView
                xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">
       
        <EditText
                android:id="@+id/liferay_login"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="20dp"
                android:hint="Email Address"
                android:inputType="textEmailAddress"/>
       
        <Button
                android:id="@+id/liferay_login_button"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Sign In"/>
       
        </com.your.package.LoginFullView>
       </li> 
   
   2  レイアウトのルート要素にちなんだ名前の新しいカスタムビュークラスを作成します。 アンドロイドのscreenlets作成のチュートリアルでは、方法について説明します [Viewクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui#creating-the-screenlets-view-class)。 View Modelインターフェイスを実装するためにViewクラスを拡張する必要はありませんが、便利な場合があります。 たとえば、カスタムビュークラス [`LoginFullView`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullView.java)は、Default [`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java) クラスを拡張することにより、 [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java) インターフェースを実装します。 `ANDROID_ID`を返すために、 `LoginFullView` カスタムビュークラスは `getPassword（）` メソッドをオーバーライドします。

3  基本Screenletクラスを継承する新しいScreenletクラスを作成します。 この新しいクラスでは、リスナーにカスタム動作を追加したり、カスタムインターアクターを呼び出したりできます。 たとえば、Screenletクラス [`LoginFullScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullScreenlet.java)、 [`LoginScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java) を拡張し、 `onUserAction` メソッドをオーバーライドして、Interactor呼び出しをログに記録します。

4  新しいレイアウトの名前を `liferay：layoutId` 属性の値として使用して、アクティビティまたはフラグメントのいずれかにビューのスクリーンレットを挿入します。 たとえば、新しい `login_password` レイアウトを使用するには、アクティビティまたはフラグメントに `LoginScreenlet` を挿入し、 `liferay：layoutId = "@ layout / login_password"`ます。</ol> 

サインアップスクリーンレット</a> の Westerosビューセットのフルビューは、 [カスタムスクリーンレットクラス](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpScreenlet.java) を使用して [新しいリスナー](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpListener.java)を追加します。 カスタムScreenletクラスは、ベースInteractor `SignUpInteractor`を呼び出す新しいユーザーアクションも追加します。</p> 



## 関連トピック

[テーマ別のビュー](/docs/7-1/tutorials/-/knowledge_base/t/themed-views)

[子ビュー](/docs/7-1/tutorials/-/knowledge_base/t/child-views)

[拡張ビュー](/docs/7-1/tutorials/-/knowledge_base/t/extended-views)

[ビューをパッケージ化する](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-views)
