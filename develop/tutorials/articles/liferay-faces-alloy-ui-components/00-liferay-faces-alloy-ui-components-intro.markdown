# Liferay Faces Alloy UI Components [](id=liferay-faces-alloy-ui-components)

<!-- Eventually, this section (and module) needs to be expanded. First, the
reader needs some insight as to what AlloyUI is and why they'd want to use it.
Then, eventually, we need to demonstrate using AlloyUI in a JSF portlet. - Jim
--> 

Liferay Faces Alloy is a `.jar` file that you add as a dependency in your JSF
portlet project to leverage [AlloyUI](http://alloyui.com/). Liferay Faces Alloy
provides a way to use AlloyUI in a typical JSF development fashion. It provides
a set of Facelet UIComponent and Facelet Composite Component tags as part of its
component suite. 

The Liferay Faces Alloy project provides a set of UI components that are JSF
equivalents of the `aui:` JSP tag library provided by Liferay Portal. Here are a
few of the components:

- Layout: `aui:panel`, `aui:button-row`, `aui:form`, `aui:fieldset`
- UI Components: `aui:list`, `aui:list-item`, `aui:text-box-list`,
  `aui:text-box-list-item`
- Composite Components: `aui-cc:button`, `aui-cc:input`, `aui-cc:message`,
  `aui-cc:messages`, `aui-cc:select`

The Liferay Faces Alloy project home page can be found at
<http://www.liferay.com/community/liferay-projects/liferay-faces/alloy>. 

Because Liferay Faces has several active
versions
<!--(/develop/learning-paths/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)-->
(targeting different versions of JSF, Liferay Portal, etc.), there are several
versions of the project's View Declaration Language (VDL) documentation for
these tags: 

**Liferay Faces 2.1:** <http://docs.liferay.com/faces/2.1/vdldoc/>

**Liferay Faces 3.0-legacy:** <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>

**Liferay Faces 3.0:** <http://docs.liferay.com/faces/3.0/vdldoc/>

**Liferay Faces 3.1:** <http://docs.liferay.com/faces/3.1/vdldoc/> 

**Liferay Faces 3.2:** <http://docs.liferay.com/faces/3.2/vdldoc/> 

<!-- Re-add links to VDLs for versions 4.1 and 4.2 when they are released. - Jim
- The VDL documentation for the Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

To see a sample JSF portlet that uses Liferay Faces Alloy, check out the [JSF2
Registration
Portlet](https://github.com/liferay/liferay-faces/tree/3.1.3-ga4/demos/portal/jsf2-registration-portlet). 

**Related Topics**

[Liferay Faces Bridge UI Components](/develop/tutorials/-/knowledge_base/6-2/tutorials/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)
