# Understanding Liferay Faces Alloy [](id=understanding-liferay-faces-alloy)

Liferay Faces Alloy is distributed in a `.jar` file. You can add Liferay Faces
Alloy as a dependency to your portlet projects, in order to use AlloyUI in a
way that is consistent with JSF development. 

During the creation of a JSF portlet in Liferay IDE/Developer Studio, you have
the option of choosing the portlet's JSF Component Suite. The options include
*JSF standard*,
[*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf),
[*PrimeFaces*](http://primefaces.org/),
[*RichFaces*](http://richfaces.jboss.org/), and *Liferay Faces Alloy*. You can
visit the
[JSF learning path](/develop/learning-paths/jsf/-/knowledge_base/6-2/creating-a-jsf-portlet)
to walk through this process. 

If you selected the Liferay Faces Alloy JSF Component Suite during your
portlet's setup, the `.jar` file is included in your portlet project using Ivy. 

The Liferay Faces Alloy project provides a set of UI components that are JSF
equivalents of the `aui:` JSP tag library provided by Liferay Portal. For
example, a brief list of some of the supported `aui:` tags are listed below: 

- Layout: `aui:panel`, `aui:column`, `aui:button-row`, `aui:form`,
`aui:fieldset`
- UI Components: `aui:list`, `aui:list-item`, `aui:text-box-list`,
`aui:text-box-list-item`
- Composite Components: `aui-cc:button`, `aui-cc:input`, `aui-cc:message`,
`aui-cc:messages`, `aui-cc:select`

<!-- When new Liferay Faces is released, the `aui:` and `aui-cc:` components
will be deprecated and replaced by `alloy:` components. Update when this is
completed. -Cody -->

If you want to utilize Liferay's AlloyUI technology based on YUI3, you must
include the Liferay Faces Alloy `.jar` file in your JSF portlet project. If you
selected *Liferay Faces Alloy* during your JSF portlet's setup, you have Liferay
Faces Alloy preconfigured in your project, so you're automatically able to use
AUI tags. 

<!-- The first sentence of the above paragraph is describing the new Liferay
Faces version in Beta. -Cody -->

As you can see, it's extremely easy to configure your JSF application to use
Liferay's AlloyUI tags. 

## Related Topics [](id=related-topics)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Creating and Deploying JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/creating-and-deploying-jsf-portlets)

[Developing Liferay Faces Portlets with Maven](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/developing-liferay-faces-portlets-with-maven)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

[Understanding Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-portal)
