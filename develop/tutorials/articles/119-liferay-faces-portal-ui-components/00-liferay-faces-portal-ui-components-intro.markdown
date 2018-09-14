# Liferay Faces Portal UI Components [](id=liferay-faces-portal-ui-components)

<!-- Eventually, this section (and module) needs to be expanded. First, the
reader needs some insight as to what Portal components are and why they'd want to use them.
Then, eventually, we need to demonstrate using Portal components in a JSF portlet. - Jim
--> 

Liferay Faces Portal is a `.jar` file that you add as a dependency in your JSF
portlet project to leverage [Liferay Portal's JSP components](https://docs.liferay.com/portal/6.2/taglibs/index.html)
in JSF. Liferay Faces Portal provides a way to use [`liferay-security`](https://docs.liferay.com/portal/6.2/taglibs/liferay-security/tld-summary.html)
and [`liferay-ui`](https://docs.liferay.com/portal/6.2/taglibs/liferay-ui/tld-summary.html)
JSP components in a typical JSF development fashion. It provides a set of
Facelet `UIComponent` tags as part of its component suite. 

For listings, demos, and code examples of Liferay Faces Portal components, check
out the [Liferay Faces Showcase](www.liferayfaces.org). 

The Liferay Faces Portal project home page can be found at
<http://www.liferay.com/community/liferay-projects/liferay-faces/portal>. 

Because Liferay Faces has several active 
[versions](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
(targeting different versions of JSF, Liferay Portal, etc.), there are several
versions of the project's View Declaration Language (VDL) documentation for
these tags: 

**Liferay Faces 4.2:** <http://www.liferayfaces.org/doc/faces/4.2/vdldoc/portal/tld-summary.html> 

**Liferay Faces 3.2:** <http://www.liferayfaces.org/doc/faces/3.2/vdldoc/portal/tld-summary.html> 

**Liferay Faces 3.1:** <http://docs.liferay.com/faces/3.1/vdldoc/> 

**Liferay Faces 3.0:** <http://docs.liferay.com/faces/3.0/vdldoc/> 

**Liferay Faces 3.0-legacy:** <http://docs.liferay.com/faces/3.0-legacy/vdldoc/> 

## Deprecated/Removed Tags [](id=deprecated-removed-tags)

The following tags have been deprecated in the Liferay Faces Portal 3.2.5-ga6
release and removed in the Liferay Faces Portal 4.2.5-ga6 release: 

| Deprecated/Removed Portal (`liferay-security`, `liferay-ui`, and `liferay-util`) Tag | Replacement Tag |
| :---: | :---: |
| [`liferay-security:permissionsURL`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-security/permissionsURL.html) | [`portal:permissionsURL`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/portal/permissionsURL.html) |
| [`liferay-ui:captcha`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/captcha.html) | [`portal:captcha`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/portal/captcha.html) |
| [`liferay-ui:ice-info-data-paginator`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/ice-info-data-paginator.html) | No replacement available |
| [`liferay-ui:ice-nav-data-paginator`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/ice-nav-data-paginator.html) | No replacement available |
| [`liferay-ui:ice-page-iterator`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/ice-page-iterator.html) | No replacement available |
| [`liferay-ui:icon-menu`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/icon-menu.html) | No replacement available |
| [`liferay-ui:icon`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/icon.html) | No replacement available |
| [`liferay-ui:info`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/info.html) | [`alloy:outputText`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/outputText.html) or [`h:outputText`](https://javaserverfaces.java.net/docs/2.2/vdldocs/facelets/h/outputText.html) |
| [`liferay-ui:input-editor`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/input-editor.html) | [`portal:inputRichText`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/portal/inputRichText.html) |
| [`liferay-ui:message`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/message.html) | [`alloy:outputText`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/outputText.html) or [`h:outputText`](https://javaserverfaces.java.net/docs/2.2/vdldocs/facelets/h/outputText.html) |
| [`liferay-ui:panel`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/panel.html) | [`alloy:panel`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/panel.html) |
| [`liferay-ui:panel-container`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/panel-container.html) | [`alloy:panelGroup`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/panelGroup.html) or [`h:panelGroup`](https://javaserverfaces.java.net/docs/2.2/vdldocs/facelets/h/panelGroup.html) |
| [`liferay-ui:portlet-toolbar`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/portlet-toolbar.html) | [`alloy:panelGroup`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/panelGroup.html) or [`h:panelGroup`](https://javaserverfaces.java.net/docs/2.2/vdldocs/facelets/h/panelGroup.html) |
| [`liferay-ui:separator`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/separator.html) | [`alloy:panelGroup`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/panelGroup.html) or [`h:panelGroup`](https://javaserverfaces.java.net/docs/2.2/vdldocs/facelets/h/panelGroup.html) |
| [`liferay-ui:toolbar-button`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-ui/toolbar-button.html) | [`alloy:panelGroup`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/alloy/panelGroup.html) or [`h:panelGroup`](https://javaserverfaces.java.net/docs/2.2/vdldocs/facelets/h/panelGroup.html) |
| [`liferay-util:validateCaptcha`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-util/validateCaptcha.html) | Validation is automatically built-in to [`portal:captcha`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/portal/captcha.html) |
| [`liferay-util:validateRichTextLength`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/liferay-util/validateRichTextLength.html) | Validation is automatically built-in to [`portal:inputRichText`](http://www.liferayfaces.org/doc/faces/3.2/vdldoc/portal/inputRichText.html) |

## Related Topics [](id=related-topics)

[Liferay Faces Alloy UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)
