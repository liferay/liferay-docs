---
header-id: creating-the-alloyeditor-buttons-jsx-file
---

# ボタンのJSXファイルの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

AlloyEditorのボタンを作成するには、次の手順に従います。

1.  OSGiバンドルの `resources \ META-INF\resources \ js` フォルダーに `.jsx` ファイルを作成します。 このファイルは、ボタンの構成を定義します。

2.  JSXファイル内で、ボタンに必要なReact変数（React、ReactDOM）を定義します。 ログテキストボタンはAlloyEditorのReactのみを使用します。
   
        (function() {
                'use strict';
       
                var React = AlloyEditor.React;

3.  ボタンのクラスを作成します。
   
        var LogSelectedTextButton = React.createClass(
            {
                //button configuration goes here
            }
        );

4.  `React.createClass（）` メソッドの構成オブジェクト内で、ボタンに必要な [ミックスイン](/docs/7-1/reference/-/knowledge_base/r/alloyeditor-button-reference-guide#mixins) 指定します。 これらは追加機能を提供し、ボタンにショートカットキーをバインドするなど、ボタンに機能を簡単に追加できます。 以下の例では、 `ButtonStateClasses` および `ButtonKeystroke` ミックスインを使用しています。
   
        mixins: [AlloyEditor.ButtonStateClasses, AlloyEditor.ButtonKeystroke],

5.  ボタンの検証小道具を渡します。 これらは、ボタンのインスタンスごとに定義されます。 少なくとも、 `エディター` 定義する必要があります。 以下の例では、エディター、ラベル、およびtabIndexのプロパティを設定します。
   
        propTypes: {
            /**
             * The editor instance where the component is being used.
             * 
             * @instance
             * @memberof LogSelectedTextButton
             * @property {Object} editor
             */
            editor: React.PropTypes.object.isRequired,
       
            /**
             * The label that should be used for accessibility purposes.
             * 
             * @instance
             * @memberof LogSelectedTextButton
             * @property {String} label
             */
            label: React.PropTypes.string,
       
            /**
             * The tabIndex of the button in its toolbar current state. A value other than -1
             * means that the button has focus and is the active element.
             * 
             * @instance
             * @memberof LogSelectedTextButton
             * @property {Number} tabIndex
             */
            tabIndex: React.PropTypes.number
        },

6.  ボタンの静的プロパティを定義します。 少なくとも `キー`する必要があります。 `キー` は、 [AlloyEditorの構成](/docs/7-1/tutorials/-/knowledge_base/t/adding-buttons-to-alloyeditor-toolbars)指定するボタンの名前を定義します。 `my-log-text-button` モジュールの静的プロパティは次のとおりです。
   
        statics: {
            /**
             * The name which is used as an alias of the button in the configuration.
             * 
             * @default myTestButton
             * @memberof LogSelectedTextButton
             * @property {String} key
             * @static
             */
            key: 'logSelectedText'
        },

7.  オプションで、 `getDefaultProps` プロパティを使用して、各インスタンスのボタンにあるデフォルトプロパティを定義します。 次の例では、 `ButtonKeystroke` mixinに必要な `コマンド` および `キーストローク` プロパティを使用して、ボタンの `logText（）` 関数のショートカットキーを設定します。
   
        getDefaultProps: function() {
            return {
                command: 'logText',
                keystroke: {
                    fn: 'logText',
                    keys: CKEDITOR.CTRL + CKEDITOR.SHIFT + 89 /*Y*/
                }
            };
        },

8.  ボタンにレンダリングするHTMLマークアップを定義します。 以下の例では、 `getStateClasses（）` メソッドを使用して、 `ButtonStateClasses` ミックスインによって提供される状態クラス情報を取得し、現在の `cssClass` 値に追加します。 また、Liferay Utilの `getLexiconIconTpl（）` メソッドを使用して、ボタンに使用するレキシコンアイコンを取得します。 利用可能なアイコンの完全なリストについては、 [レキシコンのデザインサイト](https://lexicondesign.io/docs/patterns/icons.html#liferay-icon-library) を参照してください。
   
        render: function() {
                var cssClass = 'ae-button ' + this.getStateClasses();
                var svg = Liferay.Util.getLexiconIconTpl('desktop');
       
                return (
                        <button
                            className={cssClass}
                            onClick={this._logText}
                            title="Log the selected text in the console"
                            dangerouslySetInnerHTML={{__html: svg}}
                        />
                );
        },

9.  ボタンのメインアクションを定義します。 次の例に示すように、 [`nativeEditor`](https://alloyeditor.com/api/1.5.0/Core.html#nativeEditor)取得すると、CKEditorの完全なAPIにアクセスできます。 そこから、利用可能な [`CKEditor.editor` メソッド](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_editor.html#methods) いずれかを使用して、エディターのコンテンツと対話できます。 以下の例では、エディターの [`getSelection（）`](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_editor.html#method-getSelection) および [`getSelectedText（）`](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_dom_selection.html#method-getSelectedText) メソッドをチェーンして、ユーザーの強調表示されたテキストを取得し、ブラウザーのコンソールにログを記録します。
   
        /**
         * @protected
         * @method  _logText
         */
        _logText: function() {
            var editor = this.props.editor.get('nativeEditor');
            var selectedText = editor.getSelection().getSelectedText();
       
            console.log("Your selected text is " + selectedText);
        }

10. 最後に、使用可能なボタンのリストにボタンを追加します。

    AlloyEditor.Buttons \ [LogSelectedTextButton.key \] = AlloyEditor.LogSelectedTextButton = LogSelectedTextButton;

これでAlloyEditor \のボタンの作成方法がわかりました！

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[AlloyEditorボタンのOSGiバンドルの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-alloyeditor-buttons-osgi-bundle)

[AlloyEditorへのボタンの貢献](/docs/7-1/tutorials/-/knowledge_base/t/contributing-the-button-to-alloyeditor)
