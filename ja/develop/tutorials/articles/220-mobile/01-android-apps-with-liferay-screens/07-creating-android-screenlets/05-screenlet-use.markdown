---
header-id: using-your-screenlet
---

# スクリーンレットを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

スクリーンレットを使用するには、次の [一般的な手順](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)従う必要があります。

1.  スクリーンレットを表示するアクティビティまたはフラグメントレイアウトにスクリーンレットのXMLを挿入します。 Screenlet XMLの属性を設定することにより、Screenletの動作を微調整できます。

2.  アクティビティクラスまたはフラグメントクラスにScreenletのリスナーを実装します。

この例として、Liferay Screens [Test App](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) は、Add Bookmark Screenletを使用します。 [テストアプリの `add_bookmark.xml` レイアウト](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/add_bookmark.xml)次のブックマークスクリーンレットの追加XMLがあります。

    <com.liferay.mobile.screens.bookmark.AddBookmarkScreenlet
        android:id="@+id/bookmark_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:folderId="@string/bookmark_folder"
        app:layoutId="@layout/bookmark_default" />

`app：layoutId` （`bookmark_default`）で指定されたレイアウトは、スクリーンレットのビューのレイアウトファイル（`bookmark_default.xml`）と一致することに注意してください。 これは、スクリーンレットを表示するビューを指定する方法です。 たとえば、Add Bookmark Screenletに `bookmark_awesome.xml`という名前のレイアウトファイルで別のビューが定義されている場合、 `app：layoutId` 属性の値として `@ layout / bookmark_awesome` を指定することにより、そのレイアウトを使用できます。

また、 `app：folderId` 属性は、ブックマークフォルダーのIDとして `@ string / bookmark_folder` を指定します。 これは、属性の値を指定する別の方法です。 値を直接指定する代わりに、テストアプリはその [`server_context.xml` ファイル](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/values/server_context.xml#L23-L24)値を指定します。

    ...
    <string name="bookmark_folder">20622</string>
    ...

この `name` 属性の値、 `bookmark_folder` がScreenlet XMLで使用され、 `app：folderId` 属性を `20622`ます。

## 関連トピック

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)
