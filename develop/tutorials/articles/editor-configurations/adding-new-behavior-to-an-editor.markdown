# Adding New Behavior to an Editor

With the support of several kinds of WYSIWYG editors, Liferay gives you many
options for your editing needs. However, sometimes the editor you prefer still
requires some customization that you can't do with provided configurations. To
help developers in these situations, Liferay provides a way to programatically
access the editor instance to add any kind of behavior needed.

This can be done by using the `liferay-util:dynamic-include` JavaScript
extension point. This allows anyone to inject JavaScript code after the tag
declaration to configure/change the editor.

In this tutorial, you'll learn how to use the JavaScript extension point in your
Liferay supported WYSIWYG editor.

## Injecting JavaScript into a WYSIWYG Editor

