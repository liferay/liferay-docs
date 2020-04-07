---
header-id: using-views-in-xamarin-android
---

# Xamarin.Androidでビューを使用する

[TOC levels=1-4]

Liferay Screens *View* を使用して、スクリーンレットのコア機能に依存しないスクリーンレットのルックアンドフィールを設定できます。 Liferayのスクリーンレットにはいくつかのビューがあり、Liferayとコミュニティによってさらに多くのビューが開発されています。 [Screenletリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) は、Screensに含まれる各Screenletで利用可能なビューがリストされています。 このチュートリアルでは、Xamarin.Androidでビューを使用する方法を示します。

## ビューとビューセット

XamarinのLiferay Screensのビューとビューセットを構成する概念とコンポーネントは、Liferay Screens for Androidのものと同じです。 これらのコンポーネントの簡単な説明については、 [ビューとビューセットのセクション](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-android-screenlets#views-and-view-sets) ビューの使用に関する一般的なチュートリアルを。 Liferay ScreensのViewレイヤーの詳細な説明については、チュートリアル [AndroidのLiferay Screensのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)参照してください。

## ビューを使用する

Xamarin.Androidでビューを使用するには、次の手順に従います。

1.  使用するビューのレイアウトを [Liferay Screensリポジトリ](https://github.com/liferay/liferay-screens) からアプリの `res / layout` フォルダーにコピーします。 または、新しいレイアウトを作成できます。 次のリンクは、各ビューセットで使用可能なビューレイアウトを示しています。

      - [デフォルト](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/res/layout)
      - [材料](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/res/layout)
      - [ウェステロス](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main/res/layout)

    たとえば、これはログインスクリーンレットのマテリアルビュー、 `login_material.xml`です。

        <com.liferay.mobile.screens.viewsets.material.auth.login.LoginView xmlns:android="http://schemas.android.com/apk/res/android" xmlns:liferay="http://schemas.android.com/apk/res-auto" android:paddingLeft="40dp" android:paddingRight="40dp" style="@style/default_screenlet"><LinearLayout android:id="@+id/basic_authentication_login" android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="vertical"> <LinearLayout style="@style/material_row"> <ImageView android:id="@+id/drawable_login" android:contentDescription="@string/user_login_icon" android:src="@drawable/material_email" style="@style/material_icon"/> <EditText android:id="@+id/liferay_login" android:layout_width="match_parent" android:layout_height="match_parent" android:layout_marginTop="8dp" android:inputType="text" android:labelFor="@+id/liferay_login"/> </LinearLayout> <LinearLayout style="@style/material_row"> <ImageView android:id="@+id/drawable_password" android:contentDescription="@string/password_icon" android:src="@drawable/material_https" style="@style/material_icon"/> <EditText android:id="@+id/liferay_password" android:layout_width="match_parent" android:layout_height="match_parent" android:layout_marginTop="8dp" android:hint="@string/password" android:inputType="textPassword"/> </LinearLayout> <FrameLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:layout_marginTop="32dp"> <Button android:id="@+id/liferay_login_button" android:layout_width="match_parent" android:layout_height="match_parent" android:layout_margin="10dp" android:text="@string/sign_in"/> <com.liferay.mobile.screens.base.ModalProgressBar android:id="@+id/liferay_progress" android:layout_width="wrap_content" android:layout_height="match_parent" android:layout_gravity="center_vertical|left" android:layout_margin="10dp" android:theme="@style/white_theme" android:visibility="invisible" liferay:actionViewId="@id/liferay_login_button"/> </FrameLayout> </LinearLayout> 

        <Button
            android:id="@+id/oauth_authentication_login"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/authorize_application"
            android:visibility="gone"/>
    
        </com.liferay.mobile.screens.viewsets.material.auth.login.LoginView>

2.  Screenletを表示するアクティビティまたはフラグメントのレイアウトにScreenletのXMLを挿入する場合、 `liferay：layoutId` 属性をビューのレイアウトに設定します。 たとえば、liferay：layoutId</code> を `設定したLogin ScreenletのXMLは <code>@ layout / login_material`に設定されています。これは、前のステップのLogin Screenletのマテリアルビューを指定します。
   
        <com.liferay.mobile.screens.auth.login.LoginScreenlet
            android:id="@+id/login_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            liferay:layoutId="@layout/login_material"
            />

3.  使用するビューがビューセットの一部である場合（たとえば、マテリアルビューがマテリアルビューセットの一部である場合）、アプリまたはアクティビティのテーマも、そのビューセットのスタイルを定義するテーマを継承する必要があります。 たとえば、アプリの `Resources / values / Styles.xml` の次のコードは、 `AppTheme.NoActionBar` に、マテリアルビューセットを親テーマとして使用するように指示します。<resources> <style name="AppTheme.NoActionBar" parent="material_theme">
                <item name="colorPrimary">@color/colorPrimary</item>
                <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
                <item name="colorAccent">@color/colorAccent</item>
        
                <item name="windowActionBar">false</item>
                <item name="windowNoTitle">true</item>
            </style>
            ... </resources> 

    To use the Default or Westeros View Set, inherit `default_theme` or `westeros_theme`, respectively.

驚くばかり\！ これで、ビューを使用してXamarin.Androidスクリーンレットをスプルースする方法がわかりました。

## 関連トピック

[Liferay画面用のXamarinプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-xamarin-projects-for-liferay-screens)

[Xamarinアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-xamarin-apps)

[Xamarin.iOSでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-xamarin-ios)

[Xamarinのビューとテーマを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-xamarin-views-and-themes)

[XamarinのLiferay画面のトラブルシューティングとFAQ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-screens-for-xamarin-troubleshooting-and-faqs)
