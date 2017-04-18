# Document Action

The Document Action sample shows how to add a context menu option to an entry in
the Documents and Media portlet. When deploying this sample with no
customizations, an additional menu option is available in the Documents and
Media Admin portlet and the Documents and Media portlet. This sample creates a
*Blade Basic Info* option that displays basic information about the entry (e.g.,
file name, type, version, etc.). For example, the Admin portlet provides the new
option as illustrated in the images below:

![Documents And Media Admin Portlet](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/documents-and-media-admin-portlet.png)

![Documents And Media Admin Portlet Detail](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/documents-and-media-admin-portlet-detail.png)

Likewise, the Documents and Media portlet provides the same option after
selecting `Show Actions` from the portlet's `Configuration` menu.

![Documents And Media Portlet](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/documents-and-media-portlet.png)

![Documents And Media Portlet Detail](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/documents-and-media-portlet-detail.png)

There are four Java classes used in this sample:

- `BladeActionConfigurationIcon`: Adds the new context menu option to the
   Document Detail screen options (top right corner) of the Documents and Media
   Admin portlet. See the
   [Configuring Your App's Actions Menu](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app#configuring-your-apps-actions-menu)
   tutorial for more details.
- `BladeActionDisplayContext`: Adds the
   [Display Context](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Display+Context)
   for the document action.
- `BladeActionDisplayContextFactory`: Adds the
   [Display Context](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Display+Context)
   factory for the document action.
- `BladeDocumentActionPortlet`: Provides the portlet class, which extends the
   [GenericPortlet](https://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/GenericPortlet.html).
   This class generates what is shown when the context menu option is selected.