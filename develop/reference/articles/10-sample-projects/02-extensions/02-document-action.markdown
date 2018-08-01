# Document Action [](id=document-action)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Document Action sample shows how to add a context menu option to an entry in
the Documents and Media portlet. When deploying this sample with no
customizations, an additional menu option is available in the Documents and
Media Admin portlet and the Documents and Media portlet. This sample creates a
*Blade Basic Info* option that displays basic information about the entry (e.g.,
file name, type, version, etc.). For example, the Admin portlet provides the new
option as illustrated in the images below:

![Figure 1: The new *Blade Basic Info* option is available from the entry's Options menu.](../../../images/documents-and-media-admin-portlet.png)

![Figure 2: The new option is also available from the portlet's Document Details.](../../../images/documents-and-media-admin-portlet-detail.png)

Likewise, the Documents and Media portlet provides the same option after
selecting *Show Actions* from the portlet's Configuration menu.

![Figure 3: You can access the new *Blade Basic Info* option from the Documents and Media portlet added to a page.](../../../images/documents-and-media-portlet.png)

![Figure 4: The Documents And Media portlet provides the option from its Document Detail too.](../../../images/documents-and-media-portlet-detail.png)

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[PortletConfigurationIcon](@product-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/PortletConfigurationIcon.html)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

There are four Java classes used in this sample:

- `BladeActionConfigurationIcon`: Adds the new context menu option to the
   Document Detail screen options (![Options](../../../images/icon-options.png))
   (top right corner) of the Documents and Media Admin portlet. See the
   [Configuring Your Admin App's Actions Menu](/develop/tutorials/-/knowledge_base/7-0/configuring-your-admin-apps-actions-menu)
   tutorial for more details.
- `BladeActionDisplayContext`: Adds the Display Context for the document action.
   More about Display Contexts are described later.
- `BladeActionDisplayContextFactory`: Adds the Display Context factory for the
   document action.
- `BladeDocumentActionPortlet`: Provides the portlet class, which extends the
   [GenericPortlet](https://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/GenericPortlet.html).
   This class generates what is shown when the context menu option is selected.

<!-- Update 7.0 link above when 7.1 version is available (LRDOCS-4313). -Cody -->

A Display Context is a Java class that controls access to a portlet screen's UI
elements. For example, the Document Library would use Display Contexts to
provide its screens all their UI elements. It would use one Display Context for
its document edit screen, another for its document view screen, etc. A portlet
ideally uses a different Display Context for each of its screens.

A screen's JSP calls on the Display Context (DC) to get elements to render and
to decide whether to render certain types of elements. Some of the DC methods
return a collection of UI elements (e.g., a menu object of menu items), while
other DC methods return booleans that determine whether to show particular
element types. The DC decides which objects to display, while the JSP organizes
the rendered objects and implements the screen's look and feel. You don't have
to decide which elements to display in your JSP; simply call the DC methods to
populate UI components with objects to render.

To customize or extend a portlet screen that uses a DC, you can extend the DC
and override the methods that control access to the elements that interest you.
For example, you can turn off displaying certain types of elements (e.g.,
actions) by overriding the DC method that makes that decision. You can add new
custom elements (e.g., new actions) or remove existing elements (e.g., a delete
action) from a collection of elements a DC method returns. The beauty of
customizing via a DC is that you don't have to modify the JSP. You only modify
the particular methods that are related to the UI customization goals. And JSP
updates won't break the DC customizations. Replacing a JSP, on the other hand,
can lead to missing an important JSP modification that a new Liferay version
introduces.

As you create custom portlets, you may want to implement DCs. You can benefit
from the separation of concerns that DCs provide and customers can extend your
portlet DCs to specify which UI elements to display. And they don't need to
worry about missing out on the updates you make to the JSPs.

## Where Is This Sample? [](id=where-is-this-sample)
   
There are three different versions of this sample, each built with a different
build tool:
   
- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/document-action)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/extensions/document-action)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/extensions/document-action)
