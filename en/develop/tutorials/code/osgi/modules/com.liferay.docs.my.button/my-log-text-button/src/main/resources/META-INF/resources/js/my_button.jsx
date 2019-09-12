/* global React, ReactDOM AlloyEditor */

(function() {
        'use strict';

        var React = AlloyEditor.React;

        var LogSelectedTextButton = React.createClass(
                {
                        mixins: [AlloyEditor.ButtonStateClasses, AlloyEditor.ButtonKeystroke],

                        displayName: 'ButtonMyButton',

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

                        // Lifecycle. Provides static properties to the widget.
                        statics: {
                            /**
                             * The name which is used as an alias of the button in the configuration.
                             *
                             * @default logSelectedText
                             * @memberof LogSelectedTextButton
                             * @property {String} key
                             * @static
                             */
                            key: 'logSelectedText'
                        },
                        /**
                         * Lifecycle. Returns the default values of the properties used in the widget.
                         *
                         * @instance
                         * @memberof LogSelectedTextButton
                         * @method getDefaultProps
                         * @return {Object} The default properties.
                         */
                        getDefaultProps: function() {
                            return {
                                command: '_logText',
                                keystroke: {
                                    fn: '_logText',
                                    keys: CKEDITOR.CTRL + CKEDITOR.SHIFT + 89 /*Y*/
                                }
                            };
                        },
                        /**
                         * Lifecycle. Renders the UI of the button.
                         *
                         * @method render
                         * @return {Object} The content which should be rendered.
                         */
                        render: function() {
                                var cssClass = 'ae-button ' + 
                                this.getStateClasses();
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

                        /**
                         * @protected
                         * @method  _logText
                         */
                        _logText: function() {
                        
                            var editor = this.props.editor.get('nativeEditor');
                            var selectedText = editor.getSelection().getSelectedText();
                            
                            console.log("Your selected text is " + selectedText);
                        }
                }
        );

        AlloyEditor.Buttons[LogSelectedTextButton.key] = AlloyEditor.LogSelectedTextButton = LogSelectedTextButton;
}());