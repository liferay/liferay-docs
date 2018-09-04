# Liferay Faces Alloy UI Components [](id=liferay-faces-alloy-ui-components)

<!-- Eventually, this section (and module) needs to be expanded. First, the
reader needs some insight as to what AlloyUI is and why they'd want to use it.
Then, eventually, we need to demonstrate using AlloyUI in a JSF portlet. - Jim
--> 

Liferay Faces Alloy is a `.jar` file that you add as a dependency in your JSF
portlet project to leverage [AlloyUI](http://alloyui.com/). Liferay Faces Alloy
provides a way to use AlloyUI in a typical JSF development fashion. It provides
a set of Facelet `UIComponent` tags as part of its component suite. 

For listings, demos, and code examples of Liferay Faces Alloy components, check
out the [Liferay Faces Showcase](www.liferayfaces.org). To view a working
example using Liferay Faces Alloy components, visit the
[Alloy Applicant Portlet](https://github.com/liferay/liferay-faces-bridge-impl/tree/3.0.0/demo/alloy-applicant-portlet). 

The Liferay Faces Alloy project home page can be found at
<http://www.liferay.com/community/liferay-projects/liferay-faces/alloy>. 

Because Liferay Faces has several active 
[versions](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
(targeting different versions of JSF, Liferay Portal, etc.), there are several
versions of the project's View Declaration Language (VDL) documentation for
these tags: 

**Liferay Faces 4.2:** <http://www.liferayfaces.org/doc/faces/4.2/vdldoc/alloy/tld-summary.html> 

**Liferay Faces 3.2:** <http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/tld-summary.html> 

**Liferay Faces 3.1:** <http://docs.liferay.com/faces/3.1/vdldoc/> 

**Liferay Faces 3.0:** <http://docs.liferay.com/faces/3.0/vdldoc/> 

**Liferay Faces 3.0-legacy:** <http://docs.liferay.com/faces/3.0-legacy/vdldoc/> 

## Deprecated/Removed Tags [](id=deprecated-removed-tags)

The following tags have been deprecated in the Liferay Faces Alloy 3.2.5-ga6
release and removed in the Liferay Faces Alloy 4.2.5-ga6 release: 

| Deprecated/Removed Alloy (`aui` or `aui-cc`) Tag | Replacement `alloy` Tag |
| :---: | :---: |
| [`aui:button-row`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/button-row.html) | [`alloy:buttonRow`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/buttonRow.html) |
| [`aui:col`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/col.html) | [`alloy:column`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/column.html) |
| [`aui:convertLongList`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/convertLongList.html) | No replacement available |
| [`aui:field`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/field.html) | [`alloy:field`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/field.html) |
| [`aui:fieldset`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/fieldset.html) | [`alloy:fieldset`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/fieldset.html) |
| [`aui:form`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/form.html) | [`alloy:form`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/form.html) |
| [`aui:importConstants`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/importConstants.html) | [`alloy:loadConstants`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/loadConstants.html) |
| [`aui:layout`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/layout.html) | [`alloy:panelGroup`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/panelGroup.html) |
| [`aui:list`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/list.html) | [`alloy:dataList`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/dataList.html) |
| [`aui:list-item`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/list-item.html) | [`alloy:dataItem`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/dataItem.html) |
| [`aui:row`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/row.html) | [`alloy:row`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/row.html) |
| [`aui:script`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/script.html) | [`alloy:outputScript`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/outputScript.html) |
| [`aui:text-box-list`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/text-box-list.html) | No replacement available |
| [`aui:text-box-list-item`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui/text-box-list-item.html) | No replacement available |
| [`aui-cc:appendToCssClasses()`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui-cc/appendToCssClasses.fn.html) | No replacement available |
| [`aui-cc:button`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui-cc/button.html) | [`alloy:commandButton`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/commandButton.html) |
| [`aui-cc:input`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui-cc/input.html) | [`alloy:inputSecret`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/inputSecret.html), [`alloy:inputText`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/inputText.html), [`alloy:inputTextarea`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/inputTextarea.html), or [`alloy:selectBooleanCheckbox`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/selectBooleanCheckbox.html) |
| [`aui-cc:message`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui-cc/message.html) | [`alloy:message`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/message.html) |
| [`aui-cc:messages`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/aui-cc/messages.html) | [`alloy:messages`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/messages.html) |

## Related Topics [](id=related-topics)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)

[Liferay Faces Portal UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-portal-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-alloy)
