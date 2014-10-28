# Understanding Liferay Faces Alloy

If you rewind back to selecting the JSF guestbook portlet's JSF Component Suite
in Liferay IDE/Developer Studio, you may recall the option of choosing between
the *JSF standard*,
[*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf),
[*PrimeFaces*](http://primefaces.org/),
[*RichFaces*](http://richfaces.jboss.org/), and *Liferay Faces Alloy* (which was
selected for the JSF guestbook portlet). 

Liferay Faces Alloy is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to utilize AlloyUI in a way that is
consistent with JSF development. Since you selected the Liferay Faces Alloy JSF
Component Suite during your portlet's setup, the JAR is included in your
portlet project using Ivy, the same way that was described in the previous
section. 

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
will be replaced by `alloy:` components. Update when this is completed. -Cody
-->

If you want to utilize Liferay's AlloyUI technology based on YUI3, you'll need
to include the Liferay Faces Alloy JAR in your JSF portlet project. For your JSF
guestbook, you have Liferay Faces Alloy preconfigured in your project, so you're
automatically able to use AUI tags. Next, you'll explore what Liferay Faces
Portal accomplishes for your JSF portlet. 

<!-- The first sentence of the above paragraph will only be true once the new
version of Liferay Faces is released (very soon). -Cody -->
