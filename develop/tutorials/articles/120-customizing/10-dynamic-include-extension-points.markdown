# Dynamic Includes [](id=dynamic-includes)

Dynamic includes, let you inject additional content into apps at key extension 
points--to add resources, modify editors, etc. Several of these extension points 
are available for you to use. To use a dynamic include, you must provide the 
proper key. This tutorial list the available dynamic include keys, along with 
code examples and a screenshot of the resulting UI. 

## Editor Extension Points [](id=editor-extension-points)

AlloyEditor:
https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/foundation/frontend-editor/frontend-editor-alloyeditor-web/src/main/resources/META-INF/resources/alloyeditor.jsp

The first dynamic include lets you add resources to the editor. For example you 
can use this key to 
[include additional plugins for the editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor):

    com.liferay.frontend.editor.alloyeditor.web#alloyeditor#additionalResources

The second key gives you access to the editor instance, so you can listen to 
events, customize the configuration, etc:

    com.liferay.frontend.editor.alloyeditor.web#alloyeditor#onEditorCreate

CKEditor:
https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/foundation/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/ckeditor.jsp

Add resources to the CKEditor, such as plugins: 

    com.liferay.frontend.editor.ckeditor.web#ckeditor#additionalResources

Listen to CKEditor events, customize the editor's configuration, etc:

    com.liferay.frontend.editor.ckeditor.web#ckeditor#onEditorCreate

Listen to CKEditor events for the Creole format, customize the editor's 
configuration, etc:

    com.liferay.frontend.editor.ckeditor.web#ckeditor_creole#onEditorCreate

TinyMCE Simple:
https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/foundation/frontend-editor/frontend-editor-tinymce-web/src/main/resources/META-INF/resources/tinymce_simple.jsp
TinyMCE:
https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/foundation/frontend-editor/frontend-editor-tinymce-web/src/main/resources/META-INF/resources/tinymce.jsp

Add resources to the TinyMCE editor, such as plugins:

    com.liferay.frontend.editor.tinymce.web#tinymce#additionalResources

Listen to TinyMCE editor events, customize the editor's configuration, etc:

    com.liferay.frontend.editor.tinymce.web#tinymce#onEditorCreate

## Theme Extension Points [](id=theme-extension-points)

top_head:
https://github.com/liferay/liferay-portal/blob/7.1.x/portal-web/docroot/html/common/themes/top_head.jsp

Load additional resources in the theme's head before the theme's resources are 
loaded:

    /html/common/themes/top_head.jsp#pre

![Figure 1:](../../images/dynamic-include-top-head-pre.png)

Load additional resources in the theme's head after all theme resources are 
loaded. Including code here gives you access to the theme's CSS and JavaScript 
references:

    /html/common/themes/top_head.jsp#post

top_js:
https://github.com/liferay/liferay-portal/blob/7.1.x/portal-web/docroot/html/common/themes/top_js.jspf

Include additional JavaScript resources in the theme's head. This is loaded 
before portlet JavaScript references:

    /html/common/themes/top_js.jspf#resources

bottom:
https://github.com/liferay/liferay-portal/blob/7.1.x/portal-web/docroot/html/common/themes/bottom.jsp

Load additional resources in the bottom of the theme's body before the theme's 
resources are loaded in the bottom:

    /html/common/themes/bottom.jsp#pre

![Figure 2:](../../images/dynamic-include-bottom-jsp-pre.png)

Load additional resources in the bottom of the theme's body after the theme's 
resources are loaded in the bottom. This key gives you access to the theme's 
portlet CSS references, portlet JavaScript references, and theme JavaScript 
references. For example, the Simulation Panel is added with this dynamic include:

    /html/common/themes/bottom.jsp#post 

    

