# WYSIWYG Editor Dynamic Includes

All WYSIWYG editors share the same dynamic include extension points that let you 
do the following things:

- Add resources, plugins, etc. to the editor:
    
    com.liferay.frontend.editor.editorType.web#editorName#additionalResources
    
- Gain access to the editor instance to listen to events, configure it, etc:
    
    com.liferay.frontend.editor.editorType.web#editorName#onEditorCreate 

The following `editorType`s are available:

- alloyeditor
- ckeditor
- tinymce

The following `editorName`s are available:

- alloyeditor
- alloyeditor_bbcode
- alloyeditor_creole
- ckeditor
- ckeditor_bbcode
- ckeditor_creole
- simple
- tinymce
- tinymce_simple

The example below configures the dialog definition for the CKEditor.

`*DynamicInclude` Java Class:

    @Component(immediate = true, service = DynamicInclude.class)
    public class CKEditorOnEditorCreateDynamicInclude implements DynamicInclude {

    	@Override
    	public void include(
    			HttpServletRequest request, HttpServletResponse response,
    			String key)
    		throws IOException {

    		Bundle bundle = _bundleContext.getBundle();

    		URL entryURL = bundle.getEntry(
    			"/META-INF/resources/ckeditor/extension/dialog_definition.js");

    		StreamUtil.transfer(
    			entryURL.openStream(), response.getOutputStream(), false);
    	}

    	@Override
    	public void register(
    		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

    		dynamicIncludeRegistry.register(
    			"com.liferay.frontend.editor.ckeditor.web#ckeditor#onEditorCreate");
    	}

    	@Activate
    	protected void activate(BundleContext bundleContext) {
    		_bundleContext = bundleContext;
    	}

    	private BundleContext _bundleContext;

    }
    

Injected JavaScript (`dialog_definition.js`):

    CKEDITOR.on(
    	'dialogDefinition',
    	function(event) {
    		if (event.editor === ckEditor) {
    			var dialogDefinition = event.data.definition;

    			var onShow = dialogDefinition.onShow;

    			dialogDefinition.onShow = function() {
    				if (typeof onShow === 'function') {
    					onShow.apply(this, arguments);
    				}

    				if (window.top != window.self) {
    					var editorElement = this.getParentEditor().container;

    					var documentPosition = editorElement.getDocumentPosition();

    					var dialogSize = this.getSize();

    					var x = documentPosition.x + ((editorElement.getSize('width', true) - dialogSize.width) / 2);
    					var y = documentPosition.y + ((editorElement.getSize('height', true) - dialogSize.height) / 2);

    					this.move(x, y, false);
    				}
    			};
    		}
    	}
    );

Now you know how to use the WYSIWYG editor dynamic includes.

## Related Topics

[Adding New Behavior to an Editor](develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Bottom JSP Dynamic Includes](develop/tutorials/-/knowledge_base/7-1/bottom-jsp-dynamic-includes)

[Top Head JSP Dynamic Includes](develop/tutorials/-/knowledge_base/7-1/top-head-jsp-dynamic-includes)
