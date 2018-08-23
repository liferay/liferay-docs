# Bottom JSP Dynamic Includes [](id=bottom-jsp-dynamic-includes)

The `bottom.jsp` dynamic includes load additional HTML or scripts in the bottom
of the theme's body. The following keys are available:

Load additional HTML or scripts in the bottom of the theme's body, before the 
existing ones:

    /html/common/themes/bottom.jsp#pre

Alternatively, load HTML or scripts in the bottom of the theme's body, after the 
existing ones:

    /html/common/themes/bottom.jsp#post 
 
The example below includes an additional script for the Simulation panel in the 
bottom of the theme's body, after the existing ones.

`SimulationDeviceDynamicInclude` Java class:

    @Component(immediate = true, service = DynamicInclude.class)
    public class SimulationDeviceDynamicInclude extends BaseDynamicInclude {

    	@Override
    	public void include(
    			HttpServletRequest request, HttpServletResponse response,
    			String key)
    		throws IOException {

    		PrintWriter printWriter = response.getWriter();

    		printWriter.print(_TMPL_CONTENT);
    	}

    	@Override
    	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
    		dynamicIncludeRegistry.register("/html/common/themes/bottom.jsp#post");
    	}

    	private static final String _TMPL_CONTENT = StringUtil.read(
    		SimulationDeviceDynamicInclude.class,
    		"/META-INF/resources/simulation_device_dynamic_include.tmpl");

    }

`simulation_device_dynamic_include.tmpl`:

    <script type="text/javascript">
    	// <![CDATA[
    		AUI().use(
    			'aui-base',
    			function(A) {
    				var frameElement = window.frameElement;

    				if (frameElement && frameElement.getAttribute('id') === 'simulationDeviceIframe') {
    					A.getBody().addClass('lfr-has-simulation-panel');
    				}
    			}
    		);
    	// ]]>
    </script>
 
When the Simulation panel is open, the script adds the 
`lfr-has-simulation-panel` class to the theme's body.

![Figure 1: You can use the bottom JSP dynamic include to inject scripts.](../../../images/dynamic-include-bottom-jsp-post-simulation-tmpl.png)

Now you know how to use the `bottom.jsp` dynamic includes.

## Related Topics [](id=related-topics)

[Top Head JSP Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/top-head-jsp-dynamic-includes)

[Top JS Dynamic Include](/develop/tutorials/-/knowledge_base/7-1/top-js-dynamic-include)

[WYSIWYG Editor Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/wysiwyg-editor-dynamic-includes)
