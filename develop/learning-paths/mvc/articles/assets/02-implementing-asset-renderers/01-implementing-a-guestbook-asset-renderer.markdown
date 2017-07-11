# Implementing a Guestbook Asset Renderer [](id=implementing-a-guestbook-asset-renderer)

Create a new package called `com.liferay.docs.guestbook.asset` in your
guestbook-web module's `src` folder. In this package, create a 
`GuestbookAssetRenderer` class that extends Liferay's
`BaseJSPAssetRenderer` class. `BaseJSPAssetRenderer` is an abstract class that
extends the `BaseAssetRenderer` class and implements the `AssetRenderer` 
interface. You gain a head-start on implementing the `AssetRenderer` interface 
by having your `GuestbookAssetRenderer` extend `BaseJSPAssetRenderer`. Replace 
the contents of your `GuestbookAssetRenderer` class with the following code:

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
    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
    import java.util.Locale;
    import javax.portlet.PortletRequest;
    import javax.portlet.PortletResponse;
    import javax.portlet.PortletURL;
    import javax.portlet.WindowState;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    public class GuestbookAssetRenderer extends BaseJSPAssetRenderer<Guestbook> {
    		public GuestbookAssetRenderer(Guestbook guestbook) {

                    _guestbook = guestbook;
            }

            @Override
            public boolean hasEditPermission(PermissionChecker permissionChecker) 
            		throws PortalException {

            		long guestbookId = _guestbook.getGuestbookId();
            		return GuestbookPermission.contains(permissionChecker,
                            guestbookId, ActionKeys.UPDATE);
            }

            @Override
            public boolean hasViewPermission(PermissionChecker permissionChecker)
            		throws PortalException { 

                    long guestbookId = _guestbook.getGuestbookId();
                    return GuestbookPermission.contains(permissionChecker,
                            guestbookId, ActionKeys.VIEW);
            }
            @Override
            public Guestbook getAssetObject() {
            		return _guestbook;
            }
            @Override
            public long getGroupId() {
                    return _guestbook.getGroupId();
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
            public String getClassName() {
                    return Guestbook.class.getName();
            }

            @Override
            public long getClassPK() {
                    return _guestbook.getGuestbookId();
            }

    		@Override
    		public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
    			return "Name: " + _guestbook.getName();
    		}
            @Override
            public String getTitle(Locale locale) {
                    return _guestbook.getName();
            }

    		@Override
    		public boolean include(HttpServletRequest request, HttpServletResponse response, String template)
    				throws Exception {
    				request.setAttribute("GUESTBOOK", _guestbook);
    				request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
    				request.setAttribute("StringUtil", new StringUtil());
    			return super.include(request, response, template);
    		}


    		@Override
    		public String getJspPath(HttpServletRequest request, String template) {
    			return "/html/asset/guestbook/" + template + ".jsp";
    		}
    		
    		@Override
    		public PortletURL getURLEdit(
    				LiferayPortletRequest liferayPortletRequest,
    				LiferayPortletResponse liferayPortletResponse)
    			throws Exception {
    			PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
    				getControlPanelPlid(liferayPortletRequest),
    				"com_liferay_docs_guestbook_portlet_GuestbookPortlet", PortletRequest.RENDER_PHASE);
    			portletURL.setParameter("mvcRenderCommandName", "/html/guestbookmvcportlet/edit_guestbook");
    			portletURL.setParameter(
    				"guestbookId", String.valueOf(_guestbook.getGuestbookId()));
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
    					_guestbook.getGroupId(), 
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
    					"guestbookId", String.valueOf(_guestbook.getGuestbookId()));
    				
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
    			
    		private Guestbook _guestbook;
    	}

Your `GuestbookAssetRenderer` class contains a private `_guestbook` variable.
Most of the methods in this class are simply getters that return fields from
this private guestbook object. Liferay's asset renderers follow the factory
pattern so you'll have to create a `GuestbookAssetRendererFactory` that will be
responsible for instantiating the `GuestbookAssetRenderer`'s private guestbook
object. The `BaseJSPAssetRenderer` abstract class that you're extending contains
dummy implementations of the `hasEditPermission` and `hasViewPermission`
methods. You override these dummy implementations with actual permission checks
using the `GuestbookPermission` class that you created in an earlier Learning
Path. The `getJspPath` method just returns a string that represents the path to 
the JSP that should be used to render the guestbook asset. When Liferay's Asset
Publisher portlet tries to display the full content of an asset, it invokes the
asset renderer class's render method and passes a string parameter called
`template` that equals `"full_content"`. Your `GuestbookAssetRenderer`'s
`getJspPath` method returns `"/html/asset/guestbook/full_content.jsp` when the
`"full_content"` template string is passed as a parameter. You'll create this
JSP later when you're updating your application's user interface.

Next, you need to create the factory class for your asset renderer. Create a
class called `GuestbookAssetRendererFactory` that extends Liferay's
`BaseAssetRendererFactory` class. Replace its contents with the following code:

    package com.liferay.docs.guestbook.asset;

    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.model.GuestbookModel;
    import com.liferay.docs.guestbook.service.GuestbookLocalService;
    import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
    import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
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
    public class GuestbookAssetRendererFactory extends 
    		BaseAssetRendererFactory<Guestbook> {
    	public GuestbookAssetRendererFactory() {
    		setClassName(CLASS_NAME);
    		setLinkable(_LINKABLE);
    		setPortletId("com_liferay_docs_guestbook_portlet_GuestbookPortlet");
    		setSearchable(true);
    	}
            @Override
            public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type)
                    throws PortalException {
                    Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);

    		GuestbookAssetRenderer guestbookAssetRenderer =
    			new GuestbookAssetRenderer(guestbook);

    		guestbookAssetRenderer.setAssetRendererType(type);
                    return guestbookAssetRenderer;
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

                    Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
    				return GuestbookPermission.contains(permissionChecker, classPK, actionId);
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
    		  portletURL.setParameter("mvcRenderCommandName", "/html/guestbookmvcportlet/edit_guestbook");
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
    	private GuestbookLocalService _guestbookLocalService;
        private static final boolean _LINKABLE = true;
    	public static final String CLASS_NAME = Guestbook.class.getName();
        public static final String TYPE = "guestbook";

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