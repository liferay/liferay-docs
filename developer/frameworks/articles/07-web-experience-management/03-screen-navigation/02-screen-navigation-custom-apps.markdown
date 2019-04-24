# Using Screen Navigation for Your Application

To use the Screen Navigation framework with your application you will create a
Screen Navigation Category and Entry, and then create the JSP to provide the
layout for the Screen Navigation Entry.

## Adding Screens to Your Application's Back-end 

To add screens to your application, first you must add at least one Navigation 
Category for the top level navigation. Then you can add additional Navigation 
Entries for each page that you need. To demonstrate this, follow the 
instructions for integrating Screen Navigation for a sample application.

First, create the Navigation Category:

1.  Inside of your existing application, create a component named 
    `SampleScreenNavigationCategory` that implements the  
    `ScreenNavigationCategory` interface. 

2.  Inside of the `@Component` declaration, set your priority property which determines what order items appear in in the side navigation:

	    property = "screen.navigation.category.order:Integer=10",

3.  Add the following methods to the class body:

    	@Override
    	public String getCategoryKey() {
    		return SampleScreenNavigationConstants.
    			CATEGORY_KEY_SAMPLE_CONFIGURATION;
    	}

    	@Override
    	public String getLabel(Locale locale) {
    		return LanguageUtil.get(locale, "general");
    	}

    	@Override
    	public String getScreenNavigationKey() {
    		return SampleScreenNavigationConstants.
    			SAMPLE_KEY_METHOD;
    	}
        
    
    When you're finished, your `ScreenNavigationCategory` class will look 
    like :
    
        @Component(
        	property = "screen.navigation.category.order:Integer=10",
        	service = ScreenNavigationCategory.class
        )
        public class SampleScreenNavigationCategory
        	implements ScreenNavigationCategory {

        	@Override
        	public String getCategoryKey() {
        		return SampleScreenNavigationConstants.
        			CATEGORY_KEY_SAMPLE_CONFIGURATION;
        	}

        	@Override
        	public String getLabel(Locale locale) {
        		return LanguageUtil.get(locale, "general");
        	}

        	@Override
        	public String getScreenNavigationKey() {
        		return SampleScreenNavigationConstants.
        			SAMPLE_KEY_METHOD;
        	}
        }
        
 
Next, add a Navigation Entry: 

1.  Create a component named `SampleScreenNavigationEntry` which implements 
    `ScreenNavigationEntry`.

2.  Create the the `@Reference` variables you need for the render logic:

    	@Reference
    	private ConfigurationProvider _configurationProvider;

    	@Reference
    	private JSPRenderer _jspRenderer;

    	@Reference
    	private Portal _portal;

    	@Reference(
    		target = "(osgi.web.symbolicname=com.liferay.commerce.payment.method.sample)"
    	)
    	private ServletContext _servletContext;

3.  Implement the following methods in your component:

    	@Override
    	public String getCategoryKey() {
    		return SampleScreenNavigationConstants.
    			CATEGORY_KEY_SAMPLE_CONFIGURATION;
    	}

    	@Override
    	public String getEntryKey() {
    		return SampleScreenNavigationConstants.
                ENTRY_KEY_SAMPLE_CONFIGURATION;
    	}

    	@Override
    	public String getLabel(Locale locale) {
    		return LanguageUtil.get(
    			locale,
    			SampleScreenNavigationConstants.
    				CATEGORY_KEY_SAMPLE_CONFIGURATION);
    	}

    	@Override
    	public String getScreenNavigationKey() {
    		return SpaceShipScreenNavigationConstants.
    			SAMPLE_KEY_METHOD;
    	}

    	@Override
    	public boolean isVisible(
    		User user, SamplePermissions spaceShipPermissions) {

    		if (samplePermissions.criteriaMethod()) 
            {

    			return true;
    		}

    		return false;
    	}

        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
            
            _jspRenderer.renderJSP(request, response, "/my-category/view-category.jsp");
        }
    
    
    Here is what the `SampleScreenNavigationEntry` class looks like:
    
        @Component(
        	property = "screen.navigation.entry.order:Integer=20",
        	service = ScreenNavigationEntry.class
        )
        public class
        	SampleScreenNavigationEntry
        		implements ScreenNavigationEntry<SampleApplication> {
    	public static final String
    		ENTRY_KEY_SAMPLE_CONFIGURATION =
    			"sample-configuration";

    	@Override
    	public String getCategoryKey() {
    		return SpaceShipScreenNavigationConstants.
    			CATEGORY_KEY_SAMPLE_CONFIGURATION;
    	}

    	@Override
    	public String getEntryKey() {
    		return ENTRY_KEY_SAMPLE_CONFIGURATION;
    	}

    	@Override
    	public String getLabel(Locale locale) {
    		return LanguageUtil.get(
    			locale,
    			SpaceShipScreenNavigationConstants.
    				CATEGORY_KEY_SAMPLE_CONFIGURATION);
    	}

    	@Override
    	public String getScreenNavigationKey() {
    		return SpaceShipScreenNavigationConstants.
    			SAMPLE_KEY_METHOD;
    	}

    	@Override
    	public boolean isVisible(
    		User user, SamplePermissions spaceShipPermissions) {

    		if (samplePermissions.criteriaMethod()) 
            {

    			return true;
    		}

    		return false;
    	}

        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
            
            _jspRenderer.renderJSP(request, response, "/my-category/view-category.jsp");
        }
                	
    	@Reference
    	private JSPRenderer _jspRenderer;

    	@Reference(
    		target = "(osgi.web.symbolicname=com.liferay.commerce.payment.method.sample);
    	
        }

You can implement your render method any way that you want as long as it
provides a way to render HTML. Liferay developers typically use JSPs, shown
below. 

## Adding Screens to Your Application's Front-end 

The `render` method that you created in your last step references 
`/my-category/view-category.jsp`. Create the JSP now:

1.  Inside of `/src/resources/META-INF/resources` create the `my-category` 
    folder.
    
2.  Inside of that folder, create `view-category.jsp`.

3.  Inside the JSP add the `liferay-frontend:screen-navigation` taglib with the 
    required parameters:

        <liferay-frontend:screen-navigation key=
        "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
        modelBean="<%= category %>"
        portletURL="<%= portletURL %>"
        />

After that tag, you would add the rest of the content of the JSP file to handle
user interactions and communication with the back-end for configuration.
