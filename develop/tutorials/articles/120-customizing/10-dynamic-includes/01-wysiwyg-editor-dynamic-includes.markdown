# WYSIWYG Editor Dynamic Includes [](id=wysiwyg-editor-dynamic-includes)

All WYSIWYG editors share the same dynamic include extension points for these
things:

- Adding resources, plugins, etc. to the editor:
 
    com.liferay.frontend.editor.`editorType`.web#`editorName`#additionalResources
 
- Accessing the editor instance to listen to events, configure it, etc:
 
    com.liferay.frontend.editor.`editorType`.web#`editorName`#onEditorCreate 

The table below shows the `editorType`, variable, and `editorName`s for each editor:

  editorType |  variable | editorName  |
:---------: | :--------------: | :---------: |
  alloyeditor | alloyEditor   | alloyeditor       |
  &nbsp;            | &nbsp;              | alloyeditor_bbcode |
  &nbsp;            | &nbsp;              | alloyeditor_creole |
  ckeditor    | ckEditor      | ckeditor |
  &nbsp;            | &nbsp;              | ckeditor_bbcode |
  &nbsp;            | &nbsp;              | ckeditor_creole |
  tinymce     | tinyMCEEditor | tinymce |
  &nbsp;            | &nbsp;              | tinymce_simple |

The example below alerts the user when he/she pastes content into the CKEditor.

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
    			"/META-INF/resources/ckeditor/extension/ckeditor_alert.js");

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
 

Example JavaScript:

    // ckEditor variable is already available in the execution context
    ckEditor.on(
        'paste',
        function(event) {
            event.stop();

            alert('Please, do not paste code here!');
        }
    );

Now you know how to use the WYSIWYG editor dynamic includes.

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Bottom JSP Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/bottom-jsp-dynamic-includes)

[Top Head JSP Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/top-head-jsp-dynamic-includes)

[Top JS Dynamic Include](/develop/tutorials/-/knowledge_base/7-1/top-js-dynamic-include)
