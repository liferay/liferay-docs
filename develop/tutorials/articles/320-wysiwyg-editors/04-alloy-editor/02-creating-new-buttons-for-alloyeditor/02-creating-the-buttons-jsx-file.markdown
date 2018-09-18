# Creating the Button's JSX File [](id=creating-the-alloyeditor-buttons-jsx-file)

Follow these steps to create your button for AlloyEditor:

1.  Create a `.jsx` file in your OSGi bundle's `resources\META-INF\resources\js` 
    folder. This file defines your button's configuration.

2.  Inside the JSX file, define the React variables your buttons require 
    (React, ReactDOM). The log text button only uses AlloyEditor's React:

        (function() {
                'use strict';

                var React = AlloyEditor.React;

3.  Create your button's class:

        var LogSelectedTextButton = React.createClass(
            {
                //button configuration goes here
            }
        );

4.  Inside the `React.createClass()` method's configuration object, specify the 
    [mixins](/develop/reference/-/knowledge_base/7-1/alloyeditor-button-reference-guide#mixins) 
    your button requires. These provide additional functionality, making it easy 
    to add features to your button, such as binding a shortcut key to your 
    button. The example below uses the `ButtonStateClasses` and 
    `ButtonKeystroke` mixins:

        mixins: [AlloyEditor.ButtonStateClasses, AlloyEditor.ButtonKeystroke],

5.  Pass validating props for your button. These are defined for each instance 
    of the button. At the very least, the `editor` must be defined. The example 
    below sets up properties for the editor, label, and tabIndex:

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

6.  Define the static properties for your button. You must at least provide the 
    `key`. The `key` defines the button's name to specify in the 
    [AlloyEditor's configuration](/develop/tutorials/-/knowledge_base/7-1/adding-buttons-to-alloyeditor-toolbars). 
    The `my-log-text-button` module's static properties are shown below:
    
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
    
7.  Optionally define any default properties your button has for each instance 
    using the `getDefaultProps` property. The example below uses the 
    `ButtonKeystroke` mixin's required `command` and `keystroke` properties to 
    set the shortcut keys for the button's `logText()` function:

        getDefaultProps: function() {
            return {
                command: 'logText',
                keystroke: {
                    fn: 'logText',
                    keys: CKEDITOR.CTRL + CKEDITOR.SHIFT + 89 /*Y*/
                }
            };
        },

8.  Define the HTML markup to render for your button. The example below uses the 
    `getStateClasses()` method to retrieve the state class information provided 
    by the `ButtonStateClasses` mixin and add it to the current `cssClass` 
    value. It also uses Liferay Util's `getLexiconIconTpl()` method to retrieve 
    a Lexicon icon to use for the button. See 
    [Lexicon's Design Site](https://lexicondesign.io/docs/patterns/icons.html#liferay-icon-library) 
    for a full list of the available icons. 

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

9.  Define your button's main action. Retrieving the 
    [`nativeEditor`](https://alloyeditor.com/api/1.5.0/Core.html#nativeEditor), 
    as shown in the example below, gives you access to the full API of 
    CKEditor. From there, you can use any of the available 
    [`CKEditor.editor` methods](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_editor.html#methods) 
    to interact with the editor's content. The example below chains the editor's 
    [`getSelection()`](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_editor.html#method-getSelection) 
    and 
    [`getSelectedText()`](https://ckeditor.com/docs/ckeditor4/latest/api/CKEDITOR_dom_selection.html#method-getSelectedText) 
    methods to retrieve the user's highlighted text, and then it logs it to the 
    browser's console:
    
        /**
         * @protected
         * @method  _logText
         */
        _logText: function() {
            var editor = this.props.editor.get('nativeEditor');
            var selectedText = editor.getSelection().getSelectedText();

            console.log("Your selected text is " + selectedText);
        }

10.  Finally, add the button to the list of available buttons:

        AlloyEditor.Buttons[LogSelectedTextButton.key] = AlloyEditor.LogSelectedTextButton = LogSelectedTextButton;

Now you know how to create a button for AlloyEditor! 

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Creating the AlloyEditor Button's OSGi Bundle](/develop/tutorials/-/knowledge_base/7-1/creating-the-alloyeditor-buttons-osgi-bundle)

[Contributing the Button to AlloyEditor](/develop/tutorials/-/knowledge_base/7-1/contributing-the-button-to-alloyeditor)