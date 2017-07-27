# Document Action [](id=document-action)

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
selecting `Show Actions` from the portlet's `Configuration` menu.

![Figure 3: You can access the new *Blade Basic Info* option from the Documents and Media portlet added to a page.](../../../images/documents-and-media-portlet.png)

![Figure 4: The Documents And Media portlet provides the option from its Document Detail too.](../../../images/documents-and-media-portlet-detail.png)

There are four Java classes used in this sample:

- `BladeActionConfigurationIcon`: Adds the new context menu option to the
   Document Detail screen options (![Options](../../../images/icon-options.png))
   (top right corner) of the Documents and Media Admin portlet. See the
   [Configuring Your App's Actions Menu](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app#configuring-your-apps-actions-menu)
   tutorial for more details.
- `BladeActionDisplayContext`: Adds the
   [Display Context](/participate/liferaypedia/-/wiki/Main/Display+Context)
   for the document action.
- `BladeActionDisplayContextFactory`: Adds the
   [Display Context](/participate/liferaypedia/-/wiki/Main/Display+Context)
   factory for the document action.
- `BladeDocumentActionPortlet`: Provides the portlet class, which extends the
   [GenericPortlet](https://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/GenericPortlet.html).
   This class generates what is shown when the context menu option is selected.

## Where Is This Sample? [](id=where-is-this-sample)
   
There are three different versions of this sample, each built with a different
build tool:
   
- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/document-action)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/document-action)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/document-action)
