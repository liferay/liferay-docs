---
header-id: rendering-field-types
---

# レンダリングフィールドタイプ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

フィールドタイプのレンダリングに必要なフロントエンドコーディングに進む前に、定義する別のコンポーネントとコーディングするJavaクラスがあります。

## `DDMFormFieldRenderer`実装

コンポーネントには1つのプロパティ `ddm.form.field.type.name`のみがあり、 `DDMFormFieldRenderer` 実装をOSGiフレームワークに追加することを宣言します。

    @Component(
        immediate = true, 
        property = "ddm.form.field.type.name=time",
        service = DDMFormFieldRenderer.class
    )

拡張 `BaseDDMFormFieldRenderer`、APIの唯一の必要なメソッドを実装する抽象クラスを、 `レンダリング`。 フォームエンジンは、フォームに存在するすべてのフォームフィールドタイプに対してrenderメソッドを呼び出し、レンダリングされたフィールドタイプのプレーンHTMLを返します。 抽象実装には、いくつかのユーティリティメソッドも含まれます。 時間フィールドの `DDMFormFieldRenderer` は次のようになります。

    public class TimeDDMFormFieldRenderer extends BaseDDMFormFieldRenderer {
    
        @Override
        public String getTemplateLanguage() {
            return TemplateConstants.LANG_TYPE_SOY;
        }
    
        @Override
        public String getTemplateNamespace() {
            return "DDMTime.render";
        }
    
        @Override
        public TemplateResource getTemplateResource() {
            return _templateResource;
        }
    
        @Activate
        protected void activate(Map<String, Object> properties) {
            _templateResource = getTemplateResource("/META-INF/resources/time.soy");
        }
    
        private TemplateResource _templateResource;
    
    }

テンプレート言語（Soyクロージャーテンプレート）、テンプレート名前空間（`DDMTime`）および名前（`render`）を設定し、モジュール内のテンプレートの場所をポイントします（`/META-INF/resources/time.soy`）。

## 大豆テンプレートの作成

次に、レンダラークラスで参照したテンプレートを作成します。時間フィールドタイプの場合は `time.soy` です。

| **注：** [クロージャーテンプレート](https://developers.google.com/closure/templates/) | UI要素を構築するためのテンプレートシステムです。 @product@開発者が選択した|クロージャーテンプレートを使用してForms UIを構築します。ユーザーがデータを入力すると、UIがレスポンシブに再描画されます。 閉鎖テンプレートを使用して| UIの更新時にサーバーからページ全体をリロードする必要はありません|ユーザーによる：ページの関連部分のみがサーバーから更新されます。 |これにより、スムーズなユーザーエクスペリエンスが実現します。

作成する

    src/main/resources/META-INF/resources/time.soy

そして、これでそれを埋めます：

    {namespace DDMTime}
    
    /**
    * Defines the delegated template for the time field.
    */
    {deltemplate ddm.field variant="'time'"}
        {call .render data="all" /}
    {/deltemplate}
    
    /**
    * Prints the time field.
    */
    {template .render}
        {@param name: string}
        {@param pathThemeImages: string}
        {@param value: ?}
        {@param visible: bool}
        {@param? dir: string}
        {@param? label: string}
        {@param? predefinedValue: string}
        {@param? readOnly: bool}
        {@param? required: bool}
        {@param? showLabel: bool}
        {@param? tip: string}
    
        {let $displayValue: $value ? $value : $predefinedValue ? $predefinedValue : '' /}
    
        <div class="form-group {$visible ? '' : 'hide'} liferay-ddm-form-field-time"
            data-fieldname="{$name}">
            {if $showLabel or $required}
                <label for="{$name}">
                    {if $showLabel}
                        {$label}{sp}
                    {/if}
    
                    {if $required}
                        <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk reference-mark">
                            <use xlink:href="{$pathThemeImages}/lexicon/icons.svg#asterisk" />
                        </svg>
                    {/if}
                </label>
            {/if}
    
            {if $showLabel}
                {if $tip}
                    <span class="form-text">{$tip}</span>
                {/if}
            {/if}
    
            <div class="input-group">
                <div class="input-group-item">
                    <input class="field form-control"
                        {if $dir}dir="{$dir}"{/if}
                        {if $readOnly}disabled{/if}
                        id="{$name}"
                        name="{$name}"
                        type="text"
                        value="{$displayValue}">
                </div>
            </div>
        </div>
    {/template}

テンプレートでは、次の4つの重要なことを実行します。

1.  テンプレート名前空間を定義します。 テンプレート名前空間は、名前空間をプレフィックスとして追加することで、フィールドタイプに複数のテンプレートを定義できます。
   
        {namespace DDMTime}

2.  時間フィールドをレンダリングするために呼び出されるテンプレートを設定します。 `variant = "'time'"` は時間フィールドを識別し、 `.render` はそれをレンダリングするテンプレートに名前を付けます。 テンプレート自体が続き、ブロック `{template .render}... {/ template}`定義されます。
   
        /**
        * Defines the delegated template for the time field.
        */
        {deltemplate ddm.field variant="'time'"}
            {call .render data="all" /}
        {/deltemplate}

3.  テンプレートパラメータを説明します。 上記のテンプレートでは、一部のパラメーターをフラグとして使用して、HTMLの一部を表示または非表示にします（たとえば、 `$required` パラメーター）。 リストされたすべてのパラメーターはデフォルトで使用可能です。
   
        {@param name: string}
        {@param pathThemeImages: string}
        {@param value: ?}
        {@param visible: bool}
        {@param? dir: string}
        {@param? label: string}
        {@param? predefinedValue: string}
        {@param? readOnly: bool}
        {@param? required: bool}
        {@param? showLabel: bool}
        {@param? tip: string}

4.  テンプレートロジック（ `{template .render}... {/ template}` ブロックでカプセル化されたものすべて）を記述します。 上記の例では、テンプレートは次のことを行います。

      - フィールドのラベルを表示するかどうかを確認し、表示する場合は追加します。

      - フィールドが必須かどうかを確認し、必要であれば `アスタリスク` を追加します。

      - ヒントが提供されているかどうかを確認し、表示します。

      - `<input>` タグの時間フィールドにマークアップを提供します。 この場合、テキスト入力フィールドが定義されます。

テンプレートを定義したら、フィールドをモデル化するJavaScriptファイルを作成します。

## JavaScriptファイルの作成

`time_field.js` ファイルを作成し、次のコンテンツを提供します。

    AUI.add(
        'liferay-ddm-form-field-time',
        function(A) {
            var TimeField = A.Component.create(
                {
                    ATTRS: {
                        type: {
                            value: 'time'
                        }
                    },
    
                    EXTENDS: Liferay.DDM.Renderer.Field,
    
                    NAME: 'liferay-ddm-form-field-time',
    
                    prototype: {
                    }
                }
            );
    
            Liferay.namespace('DDM.Field').Time = TimeField;
        },
        '',
        {
            requires: ['liferay-ddm-form-renderer-field']
        }
    );

上記のJavaScriptは、 `TimeField`というコンポーネントを作成します。 コンポーネントは `Liferay.DDM.Renderer.Field`拡張します。これにより、デフォルトのフィールドパラメーターが自動的に挿入されます。

次に、 `* .es.js` ファイルを記述して、Soyテンプレートのレンダリングを構成します。 `time.es.js` という名前のファイルを作成して、データを入力します。

    import Component from 'metal-component';
    import Soy from 'metal-soy';
    import templates from './time.soy';
    /**
     * Time Component
     */
    class Time extends Component {}
    // Register component
    Soy.register(Time, templates, 'render');
    if (!window.DDMTime) {
        window.DDMTime = {
        };
    }
    window.DDMTime.render = Time;
    export default Time;

これにより、 `タイム` コンポーネントをレンダリングするためにSoyテンプレートが呼び出されます。 次に、 `config.js` ファイルを作成します。

    ;(function() {
        AUI().applyConfig(
            {
                groups: {
                    'field-time': {
                        base: MODULE_PATH + '/',
                        combine: Liferay.AUI.getCombine(),
                        filter: Liferay.AUI.getFilterConfig(),
                        modules: {
                            'liferay-ddm-form-field-time': {
                                condition: {
                                    trigger: 'liferay-ddm-form-renderer'
                                },
                                path: 'time_field.js',
                                requires: [
                                    'liferay-ddm-form-renderer-field'
                                ]
                            }
                        },
                        root: MODULE_PATH + '/'
                    }
                }
            }
        );
    })();

このファイルは完全に定型です。 実際、Blade CLIを使用してフィールドタイプモジュールを生成する場合、このファイルを変更する必要はありません。 機能的には、宣言されたJavaScriptコンポーネントの依存関係（`が必要...`）、およびファイルの場所（`パス...`）を定義するJavaScriptファイルです。 Alloyローダーは、各JavaScriptコンポーネントの依存関係を満たすときに `config.js` 使用します。 Alloyローダーの詳細については、その [チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/loading-amd-modules-in-liferay)参照してください。

![図1：フォームアプリケーションに独自のフォームフィールドタイプを追加します。](../../../images/forms-time-field-type.png)

新しいフィールドタイプモジュールをビルドしてデプロイすると、 `time.soy` ファイルで説明したとおりになります（単一のテキスト入力フィールド）。 もちろん、それはあなたが望むものではありません！ タイムピッカーが必要です。

## フィールドに動作を追加する

テキスト入力フィールドを提供する以上のことを行うには、 `time_field.js` ファイルで追加の動作を定義します。

AlloyUIのtimepickerを追加するには、最初のコンポーネントが必要であることを指定 `AUI-timepicker` に `必要...` のブロック `time_field.js` 及び `config.jsの`：

    {
        requires: ['aui-timepicker','liferay-ddm-form-renderer-field']
    }

ベース `レンダリング` ロジックを上書きし、タイムピッカーをインスタンス化し、選択した時間をフィールドに追加することにより、フィールドのデフォルトレンダリングを変更します。 これは、 `プロトタイプ` ブロックで発生します。

    prototype: {
        render: function() {
            var instance = this;
    
            TimeField.superclass.render.apply(instance, arguments);
    
            instance.timePicker = new A.TimePicker(
                {
                    trigger: instance.getInputSelector(),
                    popover: {
                        zIndex: 1
                    },
                    after: {
                        selectionChange: A.bind('afterSelectionChange', instance)
                    }
                }
            );
        },
    
        afterSelectionChange: function(event) {
            var instance = this;
    
            var time = event.newSelection;
    
            instance.set('value', time);
        }
    }

元のレンダリングメソッドを呼び出します。合金タイムピッカーに必要なマークアップを印刷します。 次に、フィールドタイプの入力を `トリガー`として渡して、タイムピッカーをインスタンス化します。 また、コールバックメソッド（追加`afterSelectionChange`実行する） `後` 時間、時間ピッカーで選択されます。 このメソッドは、フィールドの値を更新します。 詳細については、 [Alloyの資料を参照してください](http://alloyui.com/tutorials/timepicker/)。

フィールドがレンダリングされると、リアルタイムピッカーが表示されます。

![図2：動作中のAlloy UI Timepicker](../../../images/forms-timepicker.png)

これで、新しいフィールドタイプを作成し、その動作を定義する方法がわかりました。 現在、フィールドタイプには、スーパークラスから継承するデフォルト設定のみが含まれています。 それだけでは不十分な場合は、フィールドタイプの追加設定を作成します。 方法については、次のチュートリアルをご覧ください。
