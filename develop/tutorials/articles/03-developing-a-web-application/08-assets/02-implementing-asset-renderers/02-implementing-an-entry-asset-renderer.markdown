# Implementing an Entry Asset Renderer [](id=implementing-an-entry-asset-renderer)

Next, you need to implement an asset renderer and an asset renderer factory for
guestbook entries. In the `com.liferay.docs.guestbook.asset` package, create an
`EntryAssetRenderer` class that extends Liferay's `BaseJSPAssetRenderer` class.
Replace the contents of your `EntryAssetRenderer` class with the following code:

    package com.liferay.docs.guestbook.asset;

    import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.model.LayoutConstants;
    import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
    import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
    import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
    import com.liferay.portal.kernel.security.permission.ActionKeys;
    import com.liferay.portal.kernel.security.permission.PermissionChecker;
    import com.liferay.portal.kernel.util.HtmlUtil;
    import com.liferay.portal.kernel.util.PortalUtil;
    import com.liferay.portal.kernel.util.StringUtil;
    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.permission.EntryPermission;
    import java.util.Locale;
    import javax.portlet.PortletRequest;
    import javax.portlet.PortletResponse;
    import javax.portlet.PortletURL;
    import javax.portlet.WindowState;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    public class EntryAssetRenderer extends BaseJSPAssetRenderer<Entry> {
    	public EntryAssetRenderer(Entry entry) {

            _entry = entry;
    	
    	}

        @Override
        public boolean hasViewPermission(PermissionChecker permissionChecker)
        		throws PortalException { 

                long entryId = _entry.getEntryId();
                return EntryPermission.contains(permissionChecker,
                        entryId, ActionKeys.VIEW);
        }
        @Override
        public Entry getAssetObject() {
        		return _entry;
        }
        @Override
        public long getGroupId() {
                return _entry.getGroupId();
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
        public String getClassName() {
                return Entry.class.getName();
        }

        @Override
        public long getClassPK() {
                return _entry.getEntryId();
        }

    	@Override
    	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
    		return "Name: " + _entry.getName() + ". Message: "
                    + _entry.getMessage();
    	}
        @Override
        public String getTitle(Locale locale) {
                return _entry.getMessage();
        }

    	@Override
    	public boolean include(HttpServletRequest request, HttpServletResponse response, String template)
    			throws Exception {
    			request.setAttribute("ENTRY", _entry);
    			request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
    			request.setAttribute("StringUtil", new StringUtil());
    		return super.include(request, response, template);
    	}


    	@Override
    	public String getJspPath(HttpServletRequest request, String template) {
    		return "/html/asset/entry/" + template + ".jsp";
    	}
    	
    	@Override
    	public PortletURL getURLEdit(
    			LiferayPortletRequest liferayPortletRequest,
    			LiferayPortletResponse liferayPortletResponse)
    		throws Exception {
    		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
    			getControlPanelPlid(liferayPortletRequest),
    			"com_liferay_docs_guestbook_portlet_GuestbookPortlet", PortletRequest.RENDER_PHASE);
    		portletURL.setParameter("mvcRenderCommandName", "/html/guestbookmvcportlet/edit_entry");
    		portletURL.setParameter(
    			"entryId", String.valueOf(_entry.getEntryId()));
    		portletURL.setParameter("showback", Boolean.FALSE.toString());
    		
    		return portletURL;
    	}	
    	@Override
    	public String getURLViewInContext(
    			LiferayPortletRequest liferayPortletRequest,
    			LiferayPortletResponse liferayPortletResponse, 
    			String noSuchEntryRedirect)
    		throws Exception {
    		try {
    			long plid = PortalUtil.getPlidFromPortletId(
    				_entry.getGroupId(), 
    				"com_liferay_docs_guestbook_portlet_GuestbookPortlet");
    			
    			PortletURL portletURL;
    			if (plid == LayoutConstants.DEFAULT_PLID) {
    				portletURL = liferayPortletResponse.createLiferayPortletURL(
    					getControlPanelPlid(liferayPortletRequest),
    					"com_liferay_docs_guestbook_portlet_GuestbookPortlet", 
    					PortletRequest.RENDER_PHASE);
    			}
    			else {
    				portletURL = PortletURLFactoryUtil.create(
    					liferayPortletRequest, "com_liferay_docs_guestbook_portlet_GuestbookPortlet", 
    					plid, PortletRequest.RENDER_PHASE);
    			}
    			
    			portletURL.setParameter(
    					"mvcRenderCommandName", "/html/guestbookmvcportlet/view");
    			portletURL.setParameter(
    				"entryId", String.valueOf(_entry.getEntryId()));
    			
    			String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);
    			
    			portletURL.setParameter("redirect", currentUrl);
    			
    			return portletURL.toString();
    		
    		}
    		catch (PortalException e) {
    			
    		} 
    		catch (SystemException e) {
    		} 
    		
    		return noSuchEntryRedirect;
    	}
    	
    	@Override
    	public String getURLView(
    			LiferayPortletResponse liferayPortletResponse, 
    			WindowState windowState) 
    		throws Exception {
    		
    		return super.getURLView(liferayPortletResponse, windowState);
    	}
    		
    	private Entry _entry;
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

    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.model.EntryModel;
    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.EntryLocalService;
    import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
    import com.liferay.docs.guestbook.service.GuestbookLocalService;
    import com.liferay.docs.guestbook.service.permission.EntryPermission;
    import com.liferay.docs.guestbook.util.WebKeys;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
    import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
    import com.liferay.portal.kernel.security.permission.PermissionChecker;
    import com.liferay.portal.kernel.theme.ThemeDisplay;

    import javax.portlet.PortletRequest;
    import javax.portlet.PortletURL;
    import javax.servlet.ServletContext;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;


    @Component(
    	immediate = true,
    	property = {
    		"javax.portlet.name=" + "com_liferay_docs_guestbook_portlet_GuestbookPortlet"
    }, service = AssetRendererFactory.class)
    public class EntryAssetRendererFactory extends 
    BaseAssetRendererFactory<Entry> {
    public EntryAssetRendererFactory() {
    setClassName(CLASS_NAME);
    setLinkable(_LINKABLE);
    setPortletId("com_liferay_docs_guestbook_portlet_GuestbookPortlet");
    setSearchable(true);
    }
    @Override
    public AssetRenderer<Entry> getAssetRenderer(long classPK, int type)
            throws PortalException {
            Entry entry = _entryLocalService.getEntry(classPK);

    EntryAssetRenderer entryAssetRenderer =
    	new EntryAssetRenderer(entry);

    entryAssetRenderer.setAssetRendererType(type);
            return entryAssetRenderer;
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

            Entry entry = _entryLocalService.getEntry(classPK);
    		return EntryPermission.contains(permissionChecker, classPK, actionId);
    }

    @Override
    public PortletURL getURLAdd(
    LiferayPortletRequest liferayPortletRequest,
    LiferayPortletResponse liferayPortletResponse, long classTypeId) {
    PortletURL portletURL = null;

    try {
      ThemeDisplay themeDisplay =
    	  (ThemeDisplay)liferayPortletRequest.getAttribute(
    	  	WebKeys.THEME_DISPLAY);

    	  portletURL = liferayPortletResponse.createLiferayPortletURL(
    	  getControlPanelPlid(themeDisplay),
    	  "com_liferay_docs_guestbook_portlet_GuestbookPortlet", PortletRequest.RENDER_PHASE);
      portletURL.setParameter("mvcRenderCommandName", "/html/guestbookmvcportlet/edit_entry");
      portletURL.setParameter("showback", Boolean.FALSE.toString());
    }
    catch (PortalException e) {
    }

    return portletURL;
    }

    @Override
    public boolean isLinkable() {
            return _LINKABLE;
    }

    @Reference(target = "(osgi.web.symbolicname=guestbook.web)")
    private ServletContext _servletContext;
    @Reference
    private EntryLocalService _entryLocalService;
    private static final boolean _LINKABLE = true;
    public static final String CLASS_NAME = Entry.class.getName();
    public static final String TYPE = "entry";

    }

Now your guestbook project's entities have been fully asset-enabled. To test the
functionality that you added, add the Asset Publisher portlet to a page of your
locally running Liferay Portal instance. Then add a few guestbooks and guestbook
entries. Edit a few of them too. Then check the Asset Publisher portlet. By
default, the Asset Publisher is configured to dynamically display assets of any
kind from the current site.

![Figure 1: After you've implemented and registered your asset renderers for your custom entities, the Asset Publisher can display your entities.](../../../images/custom-entities-asset-publisher.png)

Confirm that the Asset Publisher is displaying the guestbooks and guestbook
entries that you added.

## Adding Custom Icons to Portlets and Asset Renderers [](id=adding-custom-icons-to-portlets-and-asset-renderers)

<!-- Proposed updates -->

You can use custom icons in your portlets and asset renderers. To do this just 
override the `getIconCssClass()` method and return the CSS class for the icon 
that you wish to use. Liferay uses Clay icons. Clay is the web implementation of 
Liferay's Lexicon Design Language. Clay is covered in a later Learning Path. You 
can find the list of Clay icons and their corresponding CSS classes on their 
site [here](http://liferay.github.io/clay/content/icons-lexicon/).

Open your `GuestbookAssetRendererFactory` class and add the following method 
override:

    @Override
    public String getIconCssClass() {
        return "bookmarks";
    }
    
Open your `EntryAssetRendererFactory` class and add the following method 
override:

    @Override
    public String getIconCssClass() {
        return "pencil";
    }

<!-- can't get this to work

![Figure 2: After you've implemented the `getIconCssClass` method in your `*AssetRendererFactory` classes, your custom icons are displayed with your custom assets in the Asset Publisher.](../../../images/custom-icons-custom-entities-asset-publisher.png)

-->

<!-- How do you do this now?
You can configure custom icons for your Guestbook and Guestbook Admin portlets.
To do so, just open your project's `liferay-portlet.xml`, and update the paths
inside of the `<icon>` elements inside of the `<portlet>` elements corresponding
to the Guestbook and Guestbook Admin portlets.

+$$$

**Note:** The recommended size for your portlet icons is 16 by 16 pixels. If
your portlet icons are a different size, they might not be displayed correctly.

$$$

<!--
Here is a sample icon for the Guestbook portlet that's intended to represent a
guestbook entry. Download this icon and add it to your guestbook-portlet
project's `docroot` folder.

![Figure 2: This is the icon for the Guestbook portlet that represents a guestbook entry.](../../../images/entry.png)

Here is a sample icon for the Guestbook Admin portlet that's intended to
represent a guestbook. Download this icon and add it to your guestbook-portlet
project's `docroot` folder.

![Figure 3: This is the icon for the Guestbook Admin portlet that represents a guestbook.](../../../images/guestbook.png)
-->
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
<!--
![Figure 4: After you've implemented the `getIconPath` method in your `*AssetRenderer` classes, your custom icons are displayed with your custom assets in the Asset Publisher.](../../../images/custom-icons-custom-entities-asset-publisher.png)
 -->
Great! In the next section, you'll update your portlets' user interfaces to use
several features of Liferay's asset framework: comments, ratings, tags,
categories, and related assets.