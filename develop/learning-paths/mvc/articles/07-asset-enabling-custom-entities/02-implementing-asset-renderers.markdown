# Implementing Asset Renderers [](id=implementing-asset-renderers)

You need to create asset renderer classes for your guestbook and guestbook entry
entities and register them with Liferay. Without these classes, Liferay's Asset
Renderer portlet is unable to display your entities. Liferay assets contain such
fields as `title`, `description`, and `summary`. Liferay's Asset Publisher
portlet uses these fields to display assets, regardless of the actual type of
entity represented by the asset. These fields are not columns of the database
tables of the entities themselves (e.g., they are not columns in the
`JournalArticle` table in the case of web content articles or of the
`GB_Guestbook` table in the case of your guestbook entities). Rather, they are
are stored in the `AssetEntry` table.

## Implementing a Guestbook Asset Renderer

Create a new package called `com.liferay.docs.guestbook.asset` in your
guestbook-portlet project's `docroot/WEB-INF/src` folder. In this package,
create a `GuestbookAssetRenderer` class that extends Liferay's
`BaseAssetRenderer` class. `BaseAssetRenderer` is an abstract class that
implements the `AssetRenderer` interface. You gain a head-start on implementing
the `AssetRenderer` interface by having your `GuestbookAssetRenderer` extend
`BaseAssetRenderer`. Replace the contents of your `GuestbookAssetRenderer` class
with the following code:

    package com.liferay.docs.guestbook.asset;

    import java.util.Locale;

    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.security.permission.ActionKeys;
    import com.liferay.portal.security.permission.PermissionChecker;
    import com.liferay.portlet.asset.model.BaseAssetRenderer;

    public class GuestbookAssetRenderer extends BaseAssetRenderer {
            
            private Guestbook _guestbook;
            
            public GuestbookAssetRenderer (Guestbook guestbook) {
                    
                    _guestbook = guestbook;
            }
            
            @Override
            public boolean hasEditPermission(PermissionChecker permissionChecker) {
                    
                    long guestbookId = _guestbook.getGuestbookId();
                    
                    boolean contains = false;
                    
                    try {
                            contains = GuestbookPermission.contains(permissionChecker,
                                            guestbookId, ActionKeys.UPDATE);
                    } catch (PortalException pe) {
                            _log.error(pe.getLocalizedMessage());
                    } catch (SystemException se) {
                            _log.error(se.getLocalizedMessage());
                    }
                    
                    return contains;
            }

            @Override
            public boolean hasViewPermission(PermissionChecker permissionChecker) {
                    
                    long guestbookId = _guestbook.getGuestbookId();
                    
                    boolean contains = false;
                    
                    try {
                            contains = GuestbookPermission.contains(permissionChecker,
                                            guestbookId, ActionKeys.VIEW);
                    } catch (PortalException pe) {
                            _log.error(pe.getLocalizedMessage());
                    } catch (SystemException se) {
                            _log.error(se.getLocalizedMessage());
                    }
                    
                    return contains;
            }

            @Override
            public String getClassName() {
                    return Guestbook.class.getName();
            }

            @Override
            public long getClassPK() {
                    return _guestbook.getGuestbookId();
            }

            @Override
            public long getGroupId() {
                    return _guestbook.getGroupId();
            }

            @Override
            public String getSummary(Locale locale) {
                    return "Name: " + _guestbook.getName();
            }

            @Override
            public String getTitle(Locale locale) {
                    return _guestbook.getName();
            }

            @Override
            public long getUserId() {
                    
                    return _guestbook.getUserId();
            }

            @Override
            public String getUserName() {
                    return _guestbook.getUserName();
            }

            @Override
            public String getUuid() {
                    return _guestbook.getUuid();
            }

            @Override
            public String render(RenderRequest renderRequest,
                            RenderResponse renderResponse, String template) throws Exception {
                    
                    if (template.equals(TEMPLATE_FULL_CONTENT)) {
                            renderRequest.setAttribute("gb_guestbook", _guestbook);

                            return "/html/guestbookadmin/" + template + ".jsp";
                    }
                    else {
                            return null;
                    }
            }
            
            private Log _log;

    }

Your `GuestbookAssetRenderer` class contains a private `_guestbook` variable.
Most of the methods in this class are simply getters that return fields from
this private guestbook object. Liferay's asset renderers follow the factory
pattern so you'll have to create a `GuestbookAssetRendererFactory` that will be
responsible for instantiating the `GuestbookAssetRenderer`'s private guestbook
object. The `BaseAssetRenderer` abstract class that you're extending contains
dummy implementations of the `hasEditPermission` and `hasViewPermission`
methods. You override these dummy implementations with actual permission checks
using the `GuestbookPermission` class that you created in an earlier learning
path. The `render` method just returns a string that represents the path to the
JSP that should be used to render the guestbook asset. When Liferay's Asset
Publisher portlet tries to display the full content of an asset, it invokes the
asset renderer class's render method and passes a string parameter called
`template` that equals `"full_content"`. Your `GuestbookAssetRenderer`'s
`render` method returns `"/html/guestbookadmin/full_content.jsp` when the
`"full_content"` template string is passed as a parameter. You'll create this
JSP later when you're updating your application's user interface.

Next, you need to create the factory class for your asset renderer. Create a
class called `GuestbookAssetRendererFactory` that extends Liferay's
`BaseAssetRendererFactory` class. Replace its contents with the following code:

    package com.liferay.docs.guestbook.asset;

    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
    import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.security.permission.PermissionChecker;
    import com.liferay.portlet.asset.model.AssetRenderer;
    import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

    public class GuestbookAssetRendererFactory extends BaseAssetRendererFactory {

            public static final String CLASS_NAME = Guestbook.class.getName();

            public static final String TYPE = "guestbook";

            @Override
            public AssetRenderer getAssetRenderer(long classPK, int type)
                    throws PortalException, SystemException {

                    Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbook(classPK);

                    return new GuestbookAssetRenderer(guestbook);
            }

            @Override
            public String getClassName() {
                    return CLASS_NAME;
            }

            @Override
            public String getType() {
                    return TYPE;
            }

            @Override
            public boolean hasPermission(
                            PermissionChecker permissionChecker, long classPK, String actionId)
                    throws Exception {

                    return GuestbookPermission.contains(permissionChecker, classPK, actionId);
            }

            @Override
            public boolean isLinkable() {
                    return _LINKABLE;
            }

            private static final boolean _LINKABLE = true;

    }

This is the class that you need to register with Liferay. You're extending
`BaseAssetRendererFactory` which is an abstract class that implements the
`AssetRendererFactory` interface. Each asset renderer factory class must
implement `getClassName` and `getType` methods (among others) which ensure that
your custom asset is associated with the correct entity.

Since `GuestbookAssetRendererFactory` is a factory class, it needs to be able to
create `GuestbookAssetRenderers` for specific guestbooks. `getAssetRenderer` is
the method that's responsible for constructing new `GuestbookAssetRenderer`s. It
uses the `classPK` (primary key) parameter to retrieve the guestbook from the
database. Then it calls the `GuestbookAssetRenderer`'s constructor, passing the
retrieved guestbook as an argument. `GuestbookAssetRendererFactory` also needs
to implement a `hasPermission` method. You can easily implement this by using
your `GuestbookPermission` class.

To actually register your `GuestbookAssetRendererFactory`, you need to add the
appropriate entry to `liferay-portlet.xml`. Open your project's
`docroot/WEB-INF/liferay-portlet.xml` file and add the following element inside
of the `<portlet>` element corresponding to the Guestbook Admin portlet:

    <asset-renderer-factory>com.liferay.docs.guestbook.asset.GuestbookAssetRendererFactory</asset-renderer-factory>

This entry should go just below the `<control-panel-entry-weight>` element and
just above the `<header-portlet-css>` element. Good! Now your asset renderer
factory will be registered the next time you re-deploy your plugin.

## Implementing an Entry Asset Renderer

Next, you need to implement an asset renderer and an asset renderer factory for
guestbook entries. In the `com.liferay.docs.guestbook.asset` package, create an
`EntryAssetRenderer` class that extends Liferay's `BaseAssetRenderer` class.
Replace the contents of your `EntryAssetRenderer` class with the following code:

    package com.liferay.docs.guestbook.asset;

    import java.util.Locale;

    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.permission.EntryPermission;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.security.permission.ActionKeys;
    import com.liferay.portal.security.permission.PermissionChecker;
    import com.liferay.portlet.asset.model.BaseAssetRenderer;

    public class EntryAssetRenderer extends BaseAssetRenderer {

            private Entry _entry;
            
            public EntryAssetRenderer (Entry entry) {
                    
                    _entry = entry;
            }
            
            @Override
            public boolean hasEditPermission(PermissionChecker permissionChecker) {
                    
                    long entryId = _entry.getEntryId();
                    
                    boolean contains = false;
                    
                    try {
                            contains = EntryPermission.contains(permissionChecker,
                                            entryId, ActionKeys.UPDATE);
                    } catch (PortalException pe) {
                            _log.error(pe.getLocalizedMessage());
                    } catch (SystemException se) {
                            _log.error(se.getLocalizedMessage());
                    }
                    
                    return contains;
            }

            @Override
            public boolean hasViewPermission(PermissionChecker permissionChecker) {
                    
                    long entryId = _entry.getEntryId();
                    
                    boolean contains = false;
                    
                    try {
                            contains = EntryPermission.contains(permissionChecker,
                                            entryId, ActionKeys.VIEW);
                    } catch (PortalException pe) {
                            _log.error(pe.getLocalizedMessage());
                    } catch (SystemException se) {
                            _log.error(se.getLocalizedMessage());
                    }
                    
                    return contains;
            }

            @Override
            public String getClassName() {
                    return Entry.class.getName();
            }

            @Override
            public long getClassPK() {
                    return _entry.getEntryId();
            }

            @Override
            public long getGroupId() {
                    return _entry.getGroupId();
            }

            @Override
            public String getSummary(Locale locale) {
                    return "Name: " + _entry.getName() + ". Message: "
                                    + _entry.getMessage();
            }

            @Override
            public String getTitle(Locale locale) {
                    return _entry.getMessage();
            }

            @Override
            public long getUserId() {
                    return _entry.getUserId();
            }

            @Override
            public String getUserName() {
                    return _entry.getUserName();
            }

            @Override
            public String getUuid() {
                    return _entry.getUuid();
            }

            @Override
            public String render(RenderRequest renderRequest,
                            RenderResponse renderResponse, String template) throws Exception {
                    
                    if (template.equals(TEMPLATE_FULL_CONTENT)) {
                            renderRequest.setAttribute("gb_entry", _entry);

                            return "/html/guestbook/" + template + ".jsp";
                    }
                    else {
                            return null;
                    }
            }
            
            private Log _log;

    }

This class is very similar to the `GuestbookAssetRenderer` class. For the
`EntryAssetRenderer.getSummary` method, you're returning a summary that displays
both the entry name (the name of the user who created the entry) and the entry
message. `GuestbookAssetRenderer.getSummary` just returned a summary that
displayed the guestbook name. `EntryAssetRenderer.getTitle` returns the entry
message. `GuestbookAssetRenderer.getTitle` returned the guestbook name. The rest
of the methods of `EntryAssetRenderer` are nearly identical to those of
`GuestbookAssetRenderer`.

Next, you need to create the factory class for the guestbook entry asset
renderer. In the `com.liferay.docs.guestbook.asset` package, create a class
called `EntryAssetRendererFactory` that extends Liferay's
`BaseAssetRendererFactory` class. Replace its contents with the following code:

    package com.liferay.docs.guestbook.asset;

    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
    import com.liferay.docs.guestbook.service.permission.EntryPermission;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.security.permission.PermissionChecker;
    import com.liferay.portlet.asset.model.AssetRenderer;
    import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

    public class EntryAssetRendererFactory extends BaseAssetRendererFactory {

            public static final String CLASS_NAME = Entry.class.getName();

            public static final String TYPE = "entry";

            @Override
            public AssetRenderer getAssetRenderer(long classPK, int type)
                    throws PortalException, SystemException {

                    Entry entry = EntryLocalServiceUtil.getEntry(classPK);

                    return new EntryAssetRenderer(entry);
            }

            @Override
            public String getClassName() {
                    return CLASS_NAME;
            }

            @Override
            public String getType() {
                    return TYPE;
            }

            @Override
            public boolean hasPermission(
                            PermissionChecker permissionChecker, long classPK, String actionId)
                    throws Exception {

                    return EntryPermission.contains(permissionChecker, classPK, actionId);
            }

            @Override
            public boolean isLinkable() {
                    return _LINKABLE;
            }

            private static final boolean _LINKABLE = true;

    }

This class is nearly identical to the `GuestbookAssetRenderer` class. Your last
step is to actually register your `EntryAssetRendererFactory`. Open your
project's `docroot/WEB-INF/liferay-portlet.xml` file and add the following
element inside of the `<portlet>` element corresponding to the Guestbook
portlet:

    <asset-renderer-factory>com.liferay.docs.guestbook.asset.EntryAssetRendererFactory</asset-renderer-factory>

This entry should go just below the `<indexer-class>` element and just above the
`<header-portlet-css>` element. Now your guestbook entry asset renderer factory
will be registered the next time you re-deploy your plugin. Excellent! Now your
guestbook project's entities have been fully asset-enabled. To test the
functionality that you added, add the Asset Publisher portlet to a page of your
locally running Liferay Portal instance. Then add a few guestbooks and guestbook
entries. Edit a few of them too. Then check the Asset Publisher portlet. By
default, the Asset Publisher is configured to dynamically display assets of any
kind from the current site.

![Figure x: After you've implemented and registered your asset renderers for your custom entities, the Asset Publisher can display your entities.](../../images/custom-entities-asset-publisher.png)

Confirm that the Asset Publisher is displaying the guestbooks and guestbook
entries that you added.

## Adding Custom Icons to Portlets and Asset Renderers

You can configure custom icons for your Guestbook and Guestbook Admin portlets.
To do so, just open your project's `liferay-portlet.xml`, and update the paths
inside of the `<icon>` elements inside of the `<portlet>` elements corresponding
to the Guestbook and Guestbook Admin portlets.

+$$$

**Note:** The recommended size for your portlet icons is 16 by 16 pixels. If
your portlet icons are a different size, they might not be displayed correctly.

$$$

Here is a sample icon for the Guestbook portlet that's intended to represent a
guestbook entry. Download this icon and add it to your guestbook-portlet
project's `docroot` folder.

![Figure x: This is the icon for the Guestbook portlet that represents a guestbook entry.](../../images/entry.png)

Here is a sample icon for the Guestbook Admin portlet that's intended to
represent a guestbook. Download this icon and add it to your guestbook-portlet
project's `docroot` folder.

![Figure x: This is the icon for the Guestbook Admin portlet that represents a guestbook.](../../images/guestbook.png)

The default contents of each `<icon>` element is `/icon.png`. Change this path
to `/entry.png` for the Guestbook portlet's `<icon>` element and to
`/guestbook.png` for the Guestbook portlet.

To make these new icons appear when guestbooks and guestbook entries are
displayed by the Asset Publisher, you need to take one more step. Open
`GuestbookAssetRenderer` and add the following method as the last method of the
class, just above the `_log` variable declaration:

    @Override
    protected String getIconPath(ThemeDisplay themeDisplay) {

            return themeDisplay.getURLPortal()
                            + "/guestbook-portlet/guestbook.png";

    }

Next, open `EntryAssetRenderer` and add the following method as the last method
of the class, just above the `_log` variable declaration:

    @Override
    protected String getIconPath(ThemeDisplay themeDisplay) {

            return themeDisplay.getURLPortal()
                            + "/guestbook-portlet/entry.png";

    }

Confirm that the Asset Publisher now uses the correct icons when displaying
guestbook and guestbook entry assets.

![Figure x: After you've implemented the `getIconPath` method in your `*AssetRenderer` classes, your custom icons are displayed with your custom assets in the Asset Publisher.](../../images/custom-icons-custom-entities-asset-publisher.png)

Great! In the next section, you'll update your portlets' user interfaces to use
several features of Liferay's asset framework: comments, ratings, tags,
categories, and related assets.
