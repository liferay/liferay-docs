---
header-id: bottom-jsp-dynamic-includes
---

# Bottom JSP Dynamic Includes

[TOC levels=1-4]

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

```java
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
```

`simulation_device_dynamic_include.tmpl`:

```javascript
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
```

When the Simulation panel is open, the script adds the 
`lfr-has-simulation-panel` class to the theme's body. 

Page Source:

```html
<body class="controls-visible has-control-menu closed  yui3-skin-sam guest-site signed-in public-page site lfr-has-simulation-panel" id="senna_surface1">
```

Now you know how to use the `bottom.jsp` dynamic includes. 

## Related Topics

- [Top Head JSP Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/top-head-jsp-dynamic-includes)
- [Top JS Dynamic Include](/docs/7-2/customization/-/knowledge_base/c/top-js-dynamic-include)
- [WYSIWYG Editor Dynamic Includes](/docs/7-2/customization/-/knowledge_base/c/wysiwyg-editor-dynamic-includes)
