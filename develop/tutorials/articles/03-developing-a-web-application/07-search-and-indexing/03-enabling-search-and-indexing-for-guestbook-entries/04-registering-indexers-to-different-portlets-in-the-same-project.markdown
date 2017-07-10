# Registering Indexers To Different Portlets In The Same Project [](id=registering-indexers-to-different-portlets-in-the-same-project)

<!-- Steve, I don't think this section is needed any longer since the portlet 
IDs were not defined in the indexer classes-->

When you wrote the `EntryIndexer` and `GuestbookIndexer` classes, you specified
a portlet ID for each indexer. It's a Liferay convention to specify portlet IDs
just once in a class called `PortletKeys` and to reference the portlet IDs
defined in this class wherever else they are required in the project.

Create a new class called `PortletKeys` that extends
`com.liferay.portal.util.PortletKeys` in the `com.liferay.docs.guestbook.util`
package in the `docroot/WEB-INF/src` folder. Replace the contents of the newly
created file with the following code:

    package com.liferay.docs.guestbook.util;

    public class PortletKeys extends com.liferay.portal.util.PortletKeys {

            public static final String GUESTBOOK = "guestbook_WAR_guestbookportlet";

            public static final String GUESTBOOK_ADMIN = "guestbookadmin_WAR_guestbookportlet";

    }

Now you need to update your indexer classes to use these portlet IDs. Open
`EntryIndexer.java` and find the following line:

    public static final String PORTLET_ID = "guestbook-portlet";

Replace it with the following line:

    public static final String PORTLET_ID = PortletKeys.GUESTBOOK;

Next, open `GuestbookIndexer.java` and find the following line:

    public static final String PORTLET_ID = "guestbook-portlet";

Replace it with the following line:

    public static final String PORTLET_ID = PortletKeys.GUESTBOOK_ADMIN;

Now when the guestbook-portlet project is deployed, your indexers will be
registered to distinct portlets (the Guestbook portlet and the Guestbook Admin
portlet) in the guestbook-portlet project.