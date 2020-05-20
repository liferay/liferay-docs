---
header-id: rendering-form-field-settings
---

# レンダリングフォームフィールドの設定

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

フィールドの設定をサポートする設定がクラスに追加されたら、 `*レンダラー` が設定を取得してフロントエンドコードを更新できることを確認します。

## レンダラークラスに設定を渡す

新しい構成設定をSoyテンプレートに送信して、エンドユーザーに表示できるようにします。 インターフェイス `DDMFormFieldTemplateContextContributor` を実装する新しいJavaクラスを作成し、既存のクラス `* DDMFormFieldRenderer`を変更します。

`DDMFormFieldTemplateContextContributor` インターフェースには、 `getParameters`という名前のメソッドが</code> </code>ます。 フォームフィールドタイプに固有の新しい構成設定を取得し、それらを必要とするリソース（Soyテンプレートなど）に送信します。 これらの設定を取得するには、新しいクラス `TimeDDMFormFieldTemplateContextContributor`作成します。 まず、OSGIコンポーネントアノテーションとクラス宣言を作成します。

    @Component(
        immediate = true,
        property = "ddm.form.field.type.name=time",
        service = {
            DDMFormFieldTemplateContextContributor.class,
            TimeDDMFormFieldTemplateContextContributor.class
        }
    )
    public class TimeDDMFormFieldTemplateContextContributor
        implements DDMFormFieldTemplateContextContributor {

次に、 `getParameters` をオーバーライドして、新しい構成設定、 `プレースホルダー` および `マスク`を取得します。

``` 
    @Override
    public Map<String, Object> getParameters(
        DDMFormField ddmFormField,
        DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {

        Map<String, Object> parameters = new HashMap<>();

        parameters.put(
            "placeholder", (String)ddmFormField.getProperty("placeholder"));
        parameters.put("mask", (String)ddmFormField.getProperty("mask"));

        return parameters;
    }

}
```

次に、 `TimeDDMFormFieldRenderer`新しいメソッド `populateOptionalContext`使用して、構成設定をテンプレートに渡します。

    @Override
    protected void populateOptionalContext(
        Template template, DDMFormField ddmFormField,
        DDMFormFieldRenderingContext ddmFormFieldRenderingContext) {
    
        Map<String, Object> parameters =
            timeDDMFormFieldTemplateContextContributor.getParameters(
                ddmFormField, ddmFormFieldRenderingContext);
    
        template.putAll(parameters);
    }
    
    @Reference
    protected TimeDDMFormFieldTemplateContextContributor
        timeDDMFormFieldTemplateContextContributor;

`populateOptionalContext` メソッドは、テンプレートオブジェクト、 `DDMFormField`、および `DDMFormFieldRenderingContext`3つのパラメーターを取ります。 `DDMFormField` は、フィールドタイプインスタンスの定義を表します。このオブジェクトを使用して、フィールドタイプの設定にアクセスできます（この場合、マスクとプレースホルダーの設定）。 `DDMFormFieldRenderingContext` オブジェクトには、ユーザーのロケール、HTTP要求および応答オブジェクト、ポートレット名前空間などのフォームに関する追加情報が含まれます（含まれるすべてのプロパティは [](https://docs.liferay.com/ce/apps/forms-and-workflow/latest/javadocs/com/liferay/dynamic/data/mapping/render/DDMFormFieldRenderingContext.html)）。

OSGIリファレンス（`@Reference`）は、 `TimeDDMFormFieldTemplateContextContributor` サービスへのアクセスを提供します。

これで、JavaScriptコンポーネントとSoyテンプレートが新しい設定にアクセスできるようになりました。 次に、これらのプロパティを処理して使用できるようにJavaScriptコンポーネントを更新します。テンプレートコンテキストに渡すか（ `* Renderer`に似ていますが、今回はクライアント側のレンダリングのみ）、またはそれらを使用して動作を構成します。 JavaScriptコンポーネント自体。

| **注：** サーバー側とクライアントにSoyテンプレートが使用されていることに注意してください。サイドレンダリング。 追加する設定をJavaレンダラー|およびJavaScriptレンダラーを使用すると、可能な限り最高のユーザーを許可できます。経験。 たとえば、フォームビルダーがフォームビルダーにある場合、構成|フォームフィールドタイプの場合、入力した構成は直接|に渡すことができます。テンプレートを作成し、UIにほぼ瞬時に表示されます。 ただし、ユーザーが最初にフォームフィールドをクリックして編集を開始すると、レンダリングは|サーバー側。

次に、JavaScriptコンポーネントを構成して、新しい設定を含めます。

## JavaScriptコンポーネントへの設定の追加

JavaScriptコンポーネントは、新しい設定について知っている必要があります。 最初にそれらをコンポーネントの属性として構成します。

    ATTRS: {
        mask: {
            value: '%I:%M %p'
        },
        placeholder: {
            value: ''
        },
        type: {
            value: 'time'
        }
    },

マスク設定のデフォルト値は `%I:%M %p`で、プレースホルダーは空白です。 新しい設定がコンポーネントの属性として宣言されたので、JavaScriptコンポーネントがクライアント側のSoyテンプレートにプレースホルダー設定を渡すようにします。 Javaレンダラーと同様に、プレースホルダー構成をテンプレートコンテキストに渡します。 この場合、 `getTemplateContext（）` メソッドをオーバーライドして、プレースホルダー構成を渡します。 これをJavaScriptコンポーネント定義の `prototype` セクションに追加します。

    getTemplateContext: function() {
        var instance = this;
    
        return A.merge(
            TimeField.superclass.getTemplateContext.apply(instance, arguments),
            {
                placeholder: instance.get('placeholder')
            }
        );
    },

次に、コンポーネントのrenderメソッドで、 `mask：instance.get（ 'mask'）`を使用して、AUI Timepicker</a>

属性としてマスクを追加します。</p> 

    render: function() {
        var instance = this;
    
        TimeField.superclass.render.apply(instance, arguments);
    
        instance.timePicker = new A.TimePicker(
            {
                trigger: instance.getInputSelector(),
                mask: instance.get('mask'),
                popover: {
                    zIndex: 1
                },
                after: {
                    selectionChange: A.bind('afterSelectionChange', instance)
                }
            }
        );
    },
    

これで、フィールドタイプのJavaScriptコンポーネントが設定を含むように設定されました。 あとは、Soyテンプレートを更新して、プレースホルダーをフォームに時間フィールドでレンダリングできるようにするだけです。



## 大豆テンプレートの更新

Soyテンプレートのロジックにプレースホルダー設定を追加します。

全体のテンプレートは以下が含まれるが、唯一の追加は（---パラメータのリストにプレースホルダを追加し、パラメータのリストにある `？` プレースホルダが必要とされないことを示している）で、次いで、及び `<input>` タグ、パラメータ値を使用して、適切な値でプレースホルダーHTMLプロパティを設定します。

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
        {@param? placeholder: string}
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
                        placeholder="{$placeholder}"
                        type="text"
                        value="{$displayValue}">
                </div>
            </div>
        </div>
    {/template}
    

タイムマスクの動作を構成するJavaScriptでのみ使用されるため、マスクはSoyテンプレートでは必要ありません。 マスク設定を取得してフォームで構成するために、Soyテンプレートの動的レンダリングは必要ありません。 フォームビルダーによって設定されたマスクは、タイムピッカー自体のレンダリングでキャプチャされます。

これで、プロジェクトをビルドして時間フィールドをデプロイすると、完全に開発された *時間* フォームフィールドタイプがあり、適切なJavaScriptの動作と追加の設定が完了しています。
