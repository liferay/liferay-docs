---
header-id: adding-a-wysiwyg-editor-to-a-portlet
---

# WYSIWYGエディターのポートレットへの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

`<liferay-editor:editor />` タグのおかげで、WYSIWYGエディターをポートレットに簡単に組み込むことができます。

| **注：** `<liferay-ui:input-editor />` タグは、|の時点で非推奨です。 `<liferay-editor:editor />` タグを支持する@product-ver@ |を使用します `<liferay-editor:editor />` タグは将来の問題を回避します。

以下に設定例を示します。

    <%@ taglib uri="http://liferay.com/tld/editor" prefix="liferay-editor" %>
    
    <div class="alloy-editor-container">
        <liferay-editor:editor
                contents="Default Content"
                cssClass="my-alloy-editor"
                editorName="alloyeditor"
                name="myAlloyEditor"
                placeholder="description"
                showSource="true" 
        /> 
    </div>

を通じてJavaScript関数を渡すことも可能である `onBlurMethod`、 `onChangeMethod`、 `onFocusMethod`、及び `onInitMethod` 属性。 `OnInitMethod` 属性を使用して `OnDescriptionEditorInit`というJavaScript関数を渡す構成の例を次に示します。

    <%@ taglib uri="http://liferay.com/tld/editor" prefix="liferay-editor" %>
    
    <div class="alloy-editor-container">
        <liferay-editor:editor
            contents="Default Content"
            cssClass="my-alloy-editor"
            editorName="alloyeditor"
            name="myAlloyEditor"
            onInitMethod="OnDescriptionEditorInit"
            placeholder="description"
            showSource="true" />
    </div>
    
    <aui:script>
        function <portlet:namespace />OnDescriptionEditorInit() {
            <c:if test="<%= !customAbstract %>">
                document.getElementById(
                  '<portlet:namespace />myAlloyEditor'
                ).setAttribute('contenteditable', false);
            </c:if>
        }
    </aui:script>

以下は、 `<liferay-editor:editor />` タグの主な属性の概要です。

| 属性                   | タイプ                | 説明                                               |
| -------------------- | ------------------ | ------------------------------------------------ |
| `autoCreate`         | `java.lang.String` | エディターのHTML編集ビューを最初に表示するかどうか                      |
| 内容                   | `java.lang.String` | エディターの初期コンテンツを設定します                              |
| `contentsLanguageId` | `java.lang.String` | 入力エディターのテキストの言語IDを設定します                          |
| `cssClass`           | `java.lang.String` | コンポーネントのスタイルを設定するためのCSSクラス。                      |
| データ                  | `java.util.Map`    | editorConfigとして使用できるデータ                          |
| `editorName`         | `java.lang.String` | 使用するエディター（alloyeditor、ckeditor、tinymce、simple）   |
| 名                    | `java.lang.String` | 入力エディターの名前。 デフォルト値は `editor`です。                  |
| `onBlurMethod`       | `java.lang.String` | 入力エディターがフォーカスを失ったときに呼び出される関数。                    |
| `onChangeMethod`     | `java.lang.String` | 入力エディターの変更時に呼び出される関数。                            |
| `onFocusMethod`      | `java.lang.String` | 入力エディターがフォーカスを取得したときに呼び出される関数。                   |
| `onInitMethod`       | `java.lang.String` | 入力エディターの初期化時に呼び出される関数。                           |
| `placeholder`        | `java.lang.String` | 入力エディターに表示するプレースホルダーテキスト。                        |
| `showSource`         | `java.lang.String` | コンテンツのHTMLソースコードの編集を有効にするかどうか。 デフォルト値は `true`です。 |

サポートされている属性の完全なリストについては、 [taglibdocs](@app-ref@/frontend-editor/latest/taglibdocs/liferay-editor/editor.html) を参照してください。

ご覧のとおり、ポートレットにWYSIWYGエディターを含めるのは簡単です。

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[エディターの構成の変更](/docs/7-1/tutorials/-/knowledge_base/t/modifying-an-editors-configuration)

[AlloyEditorの変更](/docs/7-1/tutorials/-/knowledge_base/t/alloyeditor)
