---
header-id: themed-views
---

# テーマ別のビュー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screensは、テーマビューを作成するためにアプリで再利用およびカスタマイズできる既存のビューセットをいくつか提供します。 あなたはAndroidのカラーパレットの値を使用したり、上書きした場合（例えば、 `のprimaryColor`、 `secondaryColor`、など...）、[表示設定の一般的な構造を再利用しますが、着色でも（新色を使用することができますリソース）。 アプリ内でテーマビューを作成する必要があることに注意してください。これは、テーマビューがアプリまたはアクティビティのテーマに依存するためです。

各ビューセットには独自のAndroidテーマがあります。 これらはここにリストされています：

  - **デフォルトビューセット：** `default_theme`
  - **レキシコンビューセット：** `lexicon_theme`
  - **マテリアルビューセット：** `material_theme`
  - **Westerosビューセット：** `westeros_theme`

ビューセットのAndroidテーマを継承するようにアプリまたはアクティビティテーマを設定することにより、すべてのスクリーンレットを簡単にスタイル設定できます。 たとえば、次のコードを使用して、独自のテーマで `material_theme` のスタイル（およびレイアウト）を再利用できます。

    <style name="AppTheme.NoActionBar" parent="material_theme">
        <item name="colorPrimary">#B91D6D</item>
        <item name="colorPrimaryDark">#670E3B</item>
        <item name="colorAccent">#BBBBBB</item>
    </style>
    
    <application android:theme="@style/AppTheme.NoActionBar"
        ...
    >

このコードは、上書きすることに注意してください `AppTheme.NoActionBar` のために、独自の色設定とテーマの色を `colorPrimary`、 `colorPrimaryDark`、及び `colorAccent`。 スクリーンレットもこれらの新しい色を使用し、それに応じて画像やその他のリソースを着色します。 Liferay Screensは、サポートライブラリのデフォルトのAndroidカラーパレット名を使用します。

親ビューセットのテーマカラーのみをオーバーライドすることもできます。 これにより、デフォルトのカラーパレットを設定し、必要なビューセットの色のみをオーバーライドできます。 各ビューセットの色名は、アンダースコアと表示設定の小文字の名前（続くデフォルトのAndroid名、ある`_default`、 `_material`、および `_westeros`）。 例えば、次のコードは、オーバーライド `colorPrimary`、 `colorPrimaryDark`、及び `colorAccent` のみ `material_theme`：

    <resources>
        <color name="colorPrimary_material">#B91D6D</color>
        <color name="colorPrimaryDark_material">#670E3B</color>
        <color name="colorAccent_material">#BBBBBB</color>
    </resources>

Liferay Screensでは、1つのビューセットのレイアウトをスクリーンレットで使用し、別のビューセットの一般的なスタイルと色を使用することもできます。 これを行うには、 `layoutId` 属性を、別のビューセットのテーマで既にスタイル設定されているスクリーンレットに渡します。 スクリーンレットは、 `layoutId`で指定されたレイアウト構造を使用しますが、ビューセットのテーマから一般的なスタイルと色を継承します。 たとえば、次のコードは、デフォルトビューセットのレイアウト構造を使用するようにログインスクリーンレットに指示しますが、 `AppTheme.NoActionBar`前に定義したスタイルと色を使用します。

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        app:credentialsStorage="shared_preferences" />
    
    <application android:theme="@style/AppTheme.NoActionBar"
        ...
    >

## 関連トピック

[子ビュー](/docs/7-1/tutorials/-/knowledge_base/t/child-views)

[拡張ビュー](/docs/7-1/tutorials/-/knowledge_base/t/extended-views)

[全景](/docs/7-1/tutorials/-/knowledge_base/t/full-views)

[ビューをパッケージ化する](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-views)
