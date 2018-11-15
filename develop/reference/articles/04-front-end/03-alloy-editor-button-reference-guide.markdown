# AlloyEditor Button Reference Guide [](id=alloyeditor-button-reference-guide)

This reference guide provides additional information that you may find helpful 
while 
[creating new buttons for AlloyEditor](/develop/tutorials/-/knowledge_base/7-1/creating-new-buttons-for-alloyeditor). 
In this guide provides useful information on the following topics:

- Mixins

## Mixins [](id=mixins)

When creating a new button for the Alloy Editor, several mixins are available 
that make it easy to provide additional functionality. The available mixins, 
along with a brief description and a link to their API docs, are listed below:

- [ButtonActionStyle](https://github.com/liferay/alloy-editor/tree/master/src/ui/react/src/components/base/button-action-style): 
provides applying style implementation for a button based on the `applyStyle` and `removeStyle` API of CKEDITOR
- [ButtonCommandActive](https://alloyeditor.com/api/1.5.0/ButtonCommandActive.html): 
provides an `isActive` method to determine if a context-aware command is currently in an active state.
- [ButtonCommand](https://alloyeditor.com/api/1.5.0/ButtonCommand.html): 
executes a command via CKEDITOR's API
- [ButtonKeystroke](https://alloyeditor.com/api/1.5.0/ButtonKeystroke.html): 
provides a `keystroke` prop that allows configuring a function of the instance to be invoked upon the keystroke activation. 
https://docs-old.ckeditor.com/ckeditor_api/symbols/CKEDITOR.dom.event.html#getKeystroke
- [ButtonCfgProps](https://alloyeditor.com/api/1.5.0/ButtonCfgProps.html): 
provides a style prop and some methods to apply the resulting style and checking if it is present in a given path or selection.
- [ButtonStateClasses](https://alloyeditor.com/api/1.5.0/ButtonStateClasses.html): 
decorates the domElement of a component with different CSS classes based on the current state of the element.
- [ButtonStyle](https://alloyeditor.com/api/1.5.0/ButtonStyle.html): 
provides a style prop and some methods to apply the resulting style and checking if it is present in a given path or selection.
- [ToolbarButtons](https://alloyeditor.com/api/1.5.0/ToolbarButtons.html): 
provides a list of buttons which have to be displayed on the current toolbar depending on user preferences and given state.
