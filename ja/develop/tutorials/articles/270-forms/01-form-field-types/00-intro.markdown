---
header-id: form-field-types
---

# フォームフィールドタイプ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Formsアプリケーションには、高度に構成可能な多くのフィールド型がすぐに使用できます。 ほとんどのユースケースは、既存のフィールドタイプの1つで満たされます。

![図1：Formsアプリケーションには、すぐに使える便利なフィールドタイプがありますが、必要に応じて独自のフィールドタイプを追加できます。](../../../images/forms-field-types.png)

ただし、これを読んでいる場合は、おそらくユースケースがデフォルトのフィールドタイプに適合していません。 たとえば、おそらくカラーピッカーフィールドが必要です。 色の名前をリストする選択フィールドを作成できます。 ただし、一部のユーザーは、 *Gamboge* がスパイシーマスタードの色（少し暗いかもしれません）であることを知りません。

別の例は、専用の *タイム* フィールドです。 テキストフィールドと * `時間：分`*形式で入力するように指示するツールチップを使用しても、一部のユーザーは判読できないものを入力します。 これを修正するには、@product@のFormsアプリケーションに *time* フィールドを追加します。 独自のフィールドタイプを作成する独自のアイデアがある場合は、読み続けてその方法を確認してください。

これらのチュートリアルでは、次の方法を説明します

  - タイムピッカーで *Time* フォームフィールドタイプを追加するモジュールを作成します

  - カスタム設定オプションをフィールドタイプに追加する

| **プロジェクトの例：** サンプルのソースコード *時間* プロジェクトの開発|これらのチュートリアルは、便利にダウンロードできます。 クリック|ソースコードのzipファイルのダウンロードを開始するには、 [](https://portal.liferay.dev/documents/113763090/114000653/dynamic-data-mapping-type-time.zip) します。

始める前に、フォームフィールドタイプの構造を学びます。

## フィールドタイプモジュールの構造

すべてのフォームフィールドタイプモジュールの構造は似ています。 これらのチュートリアルで開発された `dynamic-data-mapping-type-time` モジュールのディレクトリ構造は次のとおりです。

    .babelrc
    .npmbundlerrc
    bnd.bnd
    build.gradle
    package-lock.json
    package.json
    src
    └── main
        ├── java
        │   └── com
        │       └── liferay
        │           └── dynamic
        │               └── data
        │                   └── mapping
        │                       └── type
        │                           └── time
        │                               ├── TimeDDMFormFieldRenderer.java
        │                               ├── TimeDDMFormFieldTemplateContextContributor.java
        │                               ├── TimeDDMFormFieldType.java
        │                               └── TimeDDMFormFieldTypeSettings.java
        └── resources
            ├── content
            │   └── Language.properties
            └── META-INF
                └── resources
                    ├── config.js
                    ├── time.es.js
                    ├── time_field.js
                    └── time.soy

初期モジュールでは `* TemplateContextContributor.java` または `* TypeSettings.java` は必要ありません（これらのクラスの詳細については、 [レンダリングフォームフィールド設定](/docs/7-1/tutorials/-/knowledge_base/t/rendering-form-field-settings) を参照してください）。 初期モジュールは、次のJavaクラスとリソースで構成されています。

`* DDMFormFieldRenderer.java`：テンプレートのレンダリングを制御します。 言語を設定し、名前空間を宣言し、コンポーネントのアクティブ化時にテンプレートリソースをロードします。 `DDMFormFieldRenderer` を実装する抽象クラスを拡張すると、ここでの作業が簡単になります。

`* DDMFormFieldType.java`：バックエンドでフォームフィールドタイプを定義します。 インターフェイスを実装する抽象クラスを拡張すると、フォームフィールドタイプのデフォルトのフォーム設定オプションが自動的に含まれます。 その場合は、インターフェイスの `getName` メソッドをオーバーライドすれば完了です。 フォームフィールドタイプが継承するデフォルトの構成オプションを確認するには、 `dynamic-data-mapping-api` モジュールの `DefaultDDMFormFieldTypeSettings` クラスを見てください。

`config.js`：Blade CLIを使用すると自動生成され、 `config.js` は宣言されたすべてのJavaScriptコンポーネントの依存関係を定義します。

`[name-of-field-type]_field.js`：フィールドをモデル化するJavaScriptファイル。

`[name-of-field-type].es.js`：テンプレートレンダリング（ `[name-of-field-type].soy` レンダリング）を構成するJavaScriptファイル。

`[name-of-field-type].soy`：フィールドの外観を定義するテンプレート。

`Language_xx_XX.properties`：なければならない任意の用語の定義 [の言語に翻訳](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application)。

Javaクラス、Soyテンプレート、およびJavaScriptファイルに加えて、フォームフィールドタイプには次のファイルが含まれます。

`.babelrc`： [Babel](https://babeljs.io/) 構成ファイル。

`.npmbundlerrc`： [liferay-npm-bundler](/docs/7-1/reference/-/knowledge_base/r/liferay-npm-bundler) 構成ファイル。

`bnd.bnd`：モジュールのメタデータ。

`build.gradle`：モジュールの依存関係とビルドプロパティ。

`package.json`：npmモジュールマネージャー。

`package-lock.json`：npmモジュールの依存関係を追跡するために自動的に生成されます。

次のチュートリアルで時間フィールドの作成を始めます。
