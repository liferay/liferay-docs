---
header-id: defining-the-attributes
---

# 属性の定義

[TOC levels=1-4]

Screenletクラスを作成する前に、その属性を定義する必要があります。 これらは、アクティビティまたはフラグメントレイアウトにスクリーンレットのXMLを挿入するときにアプリ開発者が設定できる属性です。 たとえば、 [Login Screenlet](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android)を使用するには、アプリ開発者はアクティビティまたはフラグメントレイアウトに次のLogin Screenlet XMLを挿入できます。

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        />

アプリ開発者は、 `Liferay` 属性 `basicAuthMethod` および `layoutId` を設定して、それぞれログインスクリーンレットの認証方法と表示を設定できます。 Screenletクラスはこれらの設定を読み取り、適切な機能を有効にします。

スクリーンレットを作成するとき、アプリ開発者が利用できるようにする属性を定義できます。 これは、Androidプロジェクトの `res / values` ディレクトリ内のXMLファイルで行います。 たとえば、ブックマークの追加スクリーンレットの属性は、 [スクリーンレットの `bookmark_attrs.xml` ファイル](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/res/values/bookmark_attrs.xml)定義されています。

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="AddBookmarkScreenlet">
            <attr name="layoutId"/>
            <attr name="folderId"/>
            <attr name="defaultTitle" format="string"/>
        </declare-styleable>
    </resources>

この属性定義 `layoutId`、 `フォルダID`、および `defaultTitle`。 ブックマークの追加ScreenletのScreenletクラスは、これらの属性に機能を追加します。 各機能の概要は次のとおりです。

  - `layoutId`：スクリーンレットを表示するビューを設定します。 これは、 [Liferayの既存のスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android)の `layoutId` 属性と同じように機能します。

  - `folderId`：スクリーンレットがブックマークを追加するブックマークポートレットにフォルダーIDを設定します。

  - `defaultTitle`：各ブックマークのデフォルトのタイトルを設定します。

Screenletの属性を定義したので、Screenletクラスを作成する準備が整いました。

## 関連トピック

[UIを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui)

[インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor)

[スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class)

[スクリーンレットのパッケージ化](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-screenlets)
