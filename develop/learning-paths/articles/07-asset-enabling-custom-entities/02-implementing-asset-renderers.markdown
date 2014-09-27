# Implementing Asset Renderers

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

Next, we need to implement an asset renderer and an asset renderer factory for
guestbook entries.
