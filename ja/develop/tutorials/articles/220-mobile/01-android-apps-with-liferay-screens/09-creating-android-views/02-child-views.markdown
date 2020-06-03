---
header-id: child-views
---

# 子ビュー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

子ビューは、親と同じ動作とUIコンポーネントを表示しますが、UIコンポーネントの外観と位置を変更できます。 UIコンポーネントを追加または削除することはできません。 子ビューは、独自のレイアウトXMLファイルで視覚的な変更を指定します。親のViewクラスとScreenletクラスを継承します。 親はフルビューである必要があります。

ここで説明する子ビューは、 [ログインスクリーンレットの](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login) デフォルトビューと同じUIコンポーネントを表示しますが、よりコンパクトなレイアウトを使用します。

次の手順に従って、子ビューを作成できます。

1.  ビューのスクリーンレットとその使用目的にちなんだ名前の新しいレイアウトXMLファイルを作成します。 UIの構築を開始する良い方法は、親のレイアウトXMLファイルを複製し、テンプレートとして使用することです。 ただし、UIの構築を開始する場合は、親ビューの完全修飾クラス名に基づいてルート要素に名前を付け、同じIDで親のUIコンポーネントを指定します。

    この例では、子ビューのレイアウトファイル `login_compact.xml` は、親のレイアウトファイル [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/layout/login_default.xml)ログインスクリーンレットのデフォルトビューのレイアウトに似ています。 子ビューの名前 *コンパクト* は、そのユースケースを説明しています。スクリーンレットのコンポーネントをよりコンパクトなレイアウトで表示します。 `EditText` および `Button` コンポーネントのIDは、親ビューのIDと一致します。 そのルート要素は、親Viewクラスの完全修飾名を使用します。
   
        <?xml version="1.0" encoding="utf-8"?>
        <com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">
       
            <EditText
                android:id="@+id/liferay_login"
                style="@style/default_edit_text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:drawableLeft="@drawable/default_mail_icon"
                android:hint="@string/email_address"
                android:inputType="text" />
       
            <EditText
                android:id="@+id/liferay_password"
                style="@style/default_edit_text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:drawableLeft="@drawable/default_lock_icon"
                android:hint="@string/password"
                android:inputType="textPassword" />
       
            <Button
                android:id="@+id/liferay_login_button"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                style="@style/default_button"
                android:text="@string/sign_in" />
       
        </com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView>

    [GitHub](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/res/layout)、画面のデフォルトビューの他のレイアウトを参照できます。

2.  新しいレイアウトの名前を `liferay：layoutId` 属性の値として使用して、アクティビティまたはフラグメントのいずれかにビューのスクリーンレットを挿入します。 たとえば、新しい `login_compact` レイアウトを使用するには、アクティビティまたはフラグメントに `LoginScreenlet` を挿入し、 `liferay：layoutId = "@ layout / login_compact"`ます。

調べるべきもう1つの良い子ビューレイアウトファイルは [`sign_up_material.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/src/main/res/layout/sign_up_material.xml)です。 サインアップスクリーンレットのデフォルトビューと同じUIコンポーネントと機能を提供しますが、Androidのマテリアルデザイン</a>を使用し [](http://www.google.com/design/spec/material-design/introduction.html) 。

## 関連トピック

[テーマ別のビュー](/docs/7-1/tutorials/-/knowledge_base/t/themed-views)

[拡張ビュー](/docs/7-1/tutorials/-/knowledge_base/t/extended-views)

[全景](/docs/7-1/tutorials/-/knowledge_base/t/full-views)

[ビューをパッケージ化する](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-views)
