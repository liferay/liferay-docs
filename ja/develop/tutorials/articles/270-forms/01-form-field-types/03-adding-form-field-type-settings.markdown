---
header-id: adding-settings-to-form-field-types
---

# フォームフィールドタイプへの設定の追加

[TOC levels=1-4]

[Form Field Type](/docs/7-1/tutorials/-/knowledge_base/t/creating-form-field-types)を開発したら、設定を追加する必要があるかもしれません。 たとえば、Timeフィールドは異なる時間形式を受け入れる場合があります。 ここでは、前のチュートリアルで作成したTimeフィールドタイプに *マスク* と *プレースホルダー* を追加して、フォームフィールドタイプに設定を追加する方法を学習します。

| **注：** AUI Timepickerでマスクを使用する方法の詳細については、|を参照してください。 [ここに](http://alloyui.com/tutorials/timepicker/)。 |マスクは、時間の選択肢を表示するために使用される形式を設定するだけです。 |を使用します [strftime形式](http://pubs.opengroup.org/onlinepubs/007908799/xsh/strftime.html) |必要なマスクを選択します。

フォームフィールドタイプに設定を追加するには、次の手順を実行します。

  - デフォルトのフィールドタイプ設定 `DefaultDDMFormFieldTypeSettings`を拡張するインターフェイスを記述します。

  - `* FormFieldType` を更新して、前の手順で作成された新しいインターフェイスを参照します。

  - `* FormFieldRenderer` 更新して、新しい構成オプションをJavaScriptコンポーネントやレンダリング用のSoyテンプレートで利用できるようにします。

  - JavaScriptコンポーネント（この例では `time_field.js` で定義）を更新して、新しい設定とそのデフォルト値を構成します。

  - Soyテンプレートを更新して、フォーム（この例ではプレースホルダー）でレンダリングする必要がある設定を含めます。

まず、フィールドの設定を制御するインターフェイスを作成します。

## デフォルトのタイプ設定の拡張

タイプ設定を追加するには、 `DefaultDDMFormFieldTypeSettings`を拡張する `* TypeSettings` クラスが必要です。 この例はTimeフィールドタイプで機能するため、 `TimeDDMFormFieldTypeSettings`と呼びます。

このクラスは、 *フィールドタイプ* 構成フォームをセットアップします。

![図1：カスタムフィールドタイプと同様に、テキストフィールドタイプの設定はJavaインターフェースで構成されます。](../../../images/forms-text-settings.png)

これは次のようなものです。

    package com.liferay.dynamic.data.mapping.type.time;
    
    import com.liferay.dynamic.data.mapping.annotations.DDMForm;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayout;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutColumn;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutPage;
    import com.liferay.dynamic.data.mapping.annotations.DDMFormLayoutRow;
    import com.liferay.dynamic.data.mapping.form.field.type.DefaultDDMFormFieldTypeSettings;
    
    @DDMForm
    @DDMFormLayout(
        paginationMode = com.liferay.dynamic.data.mapping.model.DDMFormLayout.TABBED_MODE,
        value = {
            @DDMFormLayoutPage(
                title = "%basic",
                value = {
                    @DDMFormLayoutRow(
                        {
                            @DDMFormLayoutColumn(
                                size = 12,
                                value = {
                                    "label", "required", "tip", "mask",
                                    "placeholder"
                                }
                            )
                        }
                    )
                }
            ),
            @DDMFormLayoutPage(
                title = "%properties",
                value = {
                    @DDMFormLayoutRow(
                        {
                            @DDMFormLayoutColumn(
                                size = 12,
                                value = {
                                    "dataType", "name", "showLabel", "repeatable",
                                    "type", "validation", "visibilityExpression"
                                }
                            )
                        }
                    )
                }
            )
        }
    )
    public interface TimeDDMFormFieldTypeSettings
        extends DefaultDDMFormFieldTypeSettings {
    
        @DDMFormField(label = "%mask", predefinedValue="%I:%M %p")
        public String mask();
    
        @DDMFormField(label = "%placeholder-text")
        public String placeholder();
    
    }

実行する必要がある作業のほとんどは、クラスの注釈にあります。

このクラスは、フォームフィールドタイプに必要なすべての設定で動的フォームを設定します。 ここに示すフォームレイアウトは、フォームにネイティブフォームフィールドタイプのルックアンドフィールを与えます。 このフォームで使用されるDDMアノテーションの詳細については、以下のメモを参照してください。

注意すべきことは、すべてのデフォルト設定が設定フォームに存在する必要があるということです。 上記の各タブ（それぞれ `@DDMFormLayoutPage`）に存在する設定のリストに注意してください。 フィールドタイプの設定フォームでデフォルト設定の1つを使用不可にする必要がある場合は、フィールドに *非表示ルール* を設定します。 フォームフィールドルールは、 `@DDMFormRule` アノテーションを使用して構成されます。

インターフェイスは `DefaultDDMFormFieldTypeSettings`拡張します。 そのため、マスクとプレースホルダーに必要であったように、クラスで設定せずにクラス注釈でデフォルト設定を使用できます。

| **DDM注釈：** このクラスの `@DDMForm` 注釈により、フォームエンジンは次のことができます。インターフェイス定義を動的な形式に変換します。 これが本当に設定フォームを直感的にレイアウトできます。 | |今のところ、上記で使用されている注釈の簡単な説明を次に示します。例：| | `@DDMForm` | ：新しい `DDMForm`インスタンス化します。 注釈から動的フォームを作成します。 | | `@DDMFormLayout` | ：2つの変数： `paginationMode` および `value`取ります。 ページネーションモードはレイアウトページの表示方法を制御する文字列。 `ページネーションモード` |することができる `TABBED_MODE`、 `SINGLE_PAGE_MODE`、 `SETTINGS_MODE`、又は `WIZARD_MODE`。 | `値`下で、使用する `@DDMFormLayoutPage`指定します。 | | `@DDMFormLayoutPage` | ：タイプ設定フォームのセクション。 2つかかります|変数： `タイトル` および `値`ここで、タイトルは|フォームと値のセクションは1つ以上の `@DDMFormLayoutRow`です。 | |レイアウトページタイトル `%basic` および `%properties` は、すべてに共通です| @product@のフィールドタイプですが、好きなタイトルを使用できます。 を変更するには|レイアウトページのタイトル、注釈プロパティでタイトルを指定し（たとえば、`title | = "%advanced"`）、言語で新しいキーを作成します|リソースファイル。 たとえば、|で `advanced = Advanced` 使用します` <code>Language.properties`。 | | `@DDMFormLayoutRow` | ：行に必要な列の数をレイアウトします。 ほとんどの設定|フォームには1行と1列しかありません。 | | `@DDMFormLayoutColumn` | ：設定フォームに必要な列をレイアウトします。 ほとんどの設定フォーム| 1行と1列があります。 各列は、 `サイズ` と|の2つの引数を受け入れます。 `値` | | `@DDMFormField` | ：新しいフィールドを設定フォームに追加します。 この例では、 `マスク` と|この注釈では、 `プレースホルダー` 設定が構成されています。 追加することを忘れないでください|設定言語キー（`マスク` および `プレースホルダーテキスト`）から言語|リソースファイル。

`* TypeSettings` クラスが終了したら、フォームフィールドタイプの `* Type` クラスを更新します。

## 型クラスの更新

クラス `TimeDDMFormFieldType` 現在、1つのメソッド `getName`あり、現在のフォームフィールドの名前を返します。 Timeフィールドの特定の設定を保持する `TimeDDMFormFieldTypeSettings` を参照する新しいメソッドを追加します。 このメソッドはすでに基本クラス（`BaseDDMFormFieldType`）に存在するため、オーバーライドします：

    @Override
    public Class<? extends DDMFormFieldTypeSettings>
        getDDMFormFieldTypeSettings() {
    
        return TimeDDMFormFieldTypeSettings.class;
    }

次に、新しい時間フィールド設定をレンダリングします。
