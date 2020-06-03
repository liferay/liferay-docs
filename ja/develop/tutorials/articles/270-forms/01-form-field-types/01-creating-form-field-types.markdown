---
header-id: creating-form-field-types
---

# フォームフィールドタイプの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

LiferayのFormsアプリケーションには、すぐに使用できる専用の時間フィールドは含まれていません。 使いやすく、適切な時間データを確実に収集するために、時間フィールドを作成し、@product@のフィールドタイプが同時にどのように機能するかを学習します。

フォームフィールドタイプの作成には、いくつかの手順が含まれます。

1.  フォームフィールドタイプのJavaクラスの作成。

2.  フォームフィールドタイプレンダラーJavaクラスの作成。

3.  JavaScriptおよびSoyテンプレートでフィールドの動作を定義します。

| **ブレードテンプレート：** プロジェクトをすぐに開始するには、|を使用します。 [ブレードCLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) |または| [Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide)。 |フォームフィールドを作成するためのBladeテンプレートがあります。 CLIを使用して、|を入力します。 | blade create -t form-field -v 7.1 -p com.liferay.docs.formfieldtype -c Time DDMTypeTime | |これにより、上記と同様の構造を持つ `DDMTypeTime` モジュールが得られます。 | Javaクラスは、パッケージ `com.liferay.docs.formfield` 下にあります| `src / main / java /` およびフロントエンドリソース（JavaScriptおよびSoyファイル）は| `sr / main / resources / META-INF / resources`。 | |フォームフィールドテンプレートの既知の制限により、|でキャメルケースを使用する必要があります。プロジェクト名（`DDMTypeTime`）。 代わりにケバブケースを使用しようとする| （`ddm-type-time`）は、機能していないモジュールを生成します。 これは|で修正されますBlade 3.3のリリース。コマンドラインから `ブレードバージョン` 実行して、実行しているBladeのバージョン。 | | Blade CLIまたはLiferay Dev Studioを使用すると、多くのプロジェクトスケルトンを取得できます。ボイラープレートが埋められているため、コーディングにすぐに集中できます。

プロジェクトのメタデータを設定することから始めます。

## OSGiメタデータの指定

最初に、 `bnd.bnd` ファイルで必要なOSGiメタデータを指定します（詳細については、ここ [参照](http://bnd.bndtools.org/chapters/800-headers.html)）。 `dynamic-data-mapping-type-time`というフォルダー内のモジュールの場合、次のようになります。

    Bundle-Name: Liferay Dynamic Data Mapping Type Time
    Bundle-SymbolicName: com.liferay.dynamic.data.mapping.type.time
    Bundle-Version: 1.0.0
    Liferay-JS-Config: /META-INF/resources/config.js
    Web-ContextPath: /dynamic-data-mapping-type-time

モジュールによって追加されたJavaScriptモジュールを定義するJavaScript構成ファイル（`config.js`）を指し（後で説明します）、モジュールのルートフォルダーにWebコンテキストパスを設定し、モジュールのリソースが利用できるようにしますモジュールのアクティベーション。

次に、クラスを `DDMFormFieldType`実装としてマークするOSGiコンポーネントを作成します。

## DDMFormFieldTypeコンポーネントの作成

*Time* フィールドタイプを作成する場合は、次のように `* DDMFormFieldType` クラスの上部でコンポーネントを定義します。

    @Component(
      immediate = true,
      property = {
        "ddm.form.field.type.description=time-field-type-description",
        "ddm.form.field.type.display.order:Integer=10",
        "ddm.form.field.type.icon=time",
        "ddm.form.field.type.js.class.name=Liferay.DDM.Field.Time",
        "ddm.form.field.type.js.module=liferay-ddm-form-field-time",
        "ddm.form.field.type.label=time-field-type-label",
        "ddm.form.field.type.name=time"
      },
      service = DDMFormFieldType.class
    )

フィールドタイプのプロパティ（`property = ...`）を定義し、 `DDMFormFieldType` サービス（`service = ...`）を実装していることを宣言します。

`DDMFormFieldType` コンポーネントにはいくつかのプロパティがあります。

`ddm.form.field.type.description` ：フィールドタイプを説明するオプションのプロパティ。 ローカライズされた値は、フォームビルダーのサイドバーのフィールドのラベルのすぐ下に表示されます。

`ddm.form.field.type.display.order` ：サイドバーでのフィールドタイプの位置を定義する整数。

`ddm.form.field.type.icon` ：フィールドタイプのアイコン。 [レキシコンアイコン](https://lexicondesign.io/docs/patterns/icons.html) いずれかを選択すると、フォームフィールドが既存のフォームフィールドタイプと調和します。

`ddm.form.field.type.js.class.name` ：フィールドタイプのJavaScriptクラス名--- JavaScriptファイルはフィールドタイプの動作を定義します。

`ddm.form.field.type.js.module` ：フォームエンジンに提供されるJavaScriptモジュールの名前。これにより、必要なときにモジュールをロードできます。

`ddm.form.field.type.label` ：フィールドタイプのラベル。 そのローカライズされた値は、フォームビルダーのサイドバーに表示されます。

`ddm.form.field.type.name` ：フィールドタイプの名前は一意である必要があります。 フィールドタイプモジュールの各コンポーネントはフィールドタイプ名を参照し、OSGiサービストラッカーがフィールドの機能（レンダリングや検証など）をフィルタリングするために使用します。

次に、 `* DDMFormFieldType` クラスをコーディングします。

## DDMFormFieldTypeの実装

Javaでのフィールドタイプの実装は、コードで活用できる抽象クラスである `BaseDDMFormFieldType`により簡単になります。

`BaseDDMFormFieldType`拡張した後、新しいフィールドタイプの名前を指定して `getName` メソッドをオーバーライドします。

    public class TimeDDMFormFieldType extends BaseDDMFormFieldType {
    
        @Override
        public String getName() {
            return "time";
        }
    
    }

フィールドタイプの定義はこれですべてです。 次に、フィールドタイプのレンダリング方法を決定します。
