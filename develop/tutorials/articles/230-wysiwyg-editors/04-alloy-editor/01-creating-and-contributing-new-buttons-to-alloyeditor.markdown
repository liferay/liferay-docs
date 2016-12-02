# Creating and Contributing new Buttons to AlloyEditor [](id=creating-and-contributing-new-buttons-to-alloyeditor)

It is possible to add additional AlloyEditor functionality through OSGi bundles. 
This tutorial demonstrates how to add a button to the editor.

In this tutorial, you will learn how to

- Create an OSGi bundle for your own button
- Create a custom button for `AlloyEditor`
- Contribute your button to the list of available buttons
- Use your custom button in a toolbar in `AlloyEditor`

Go ahead and get started by creating the OSGi bundle next.

## Creating the OSGi Bundle [](id=creating-the-osgi-bundle)

AlloyEditor is built on `React.js` and uses `jsx` to render each button in the 
editor. Below is the folder structure for a module that adds a new button:

- `frontend-editor-my-button-web`
	- `src`
	    - `main`
	        - `java`
                    - `com/liferay/frontend/editor/my/button/web/`
		        - `editor`
			    - `configuration`
			        - `AlloyEditorMyButtonConfigContributor.java`
			- `servlet`
			    - `taglib`
			        - `AlloyEditorMyButtonDynamicInclude.java`
		- `resources`
		    - `META-INF`
		        - `resources`
			    - `js`
			        - `my_button.jsx`

	- `.babelrc` - needed since `JSX` is being compiled

	- `bnd.bnd`(example configuration shown below)

		Bundle-Name: Liferay Frontend Editor AlloyEditor My Button Web
		Bundle-SymbolicName: com.liferay.frontend.editor.alloyeditor.my.button.web
		Bundle-Version: 1.0.0
		Liferay-Releng-Module-Group-Description:
		Liferay-Releng-Module-Group-Title: Rich Text Editors
		Web-ContextPath: /frontend-editor-alloyeditor-my-button-web


	- `build.gradle`(contents shown below)

		configJSModules {
			enabled = false
		}

		dependencies {
			provided group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
			provided group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
			provided group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
		}

		transpileJS {
			bundleFileName = "js/buttons.js"
			globalName = "AlloyEditor.Buttons"
			modules = "globals"
			srcIncludes = "**/*.jsx"
		}

	- `package.json`(contents shown below)

		{
			"devDependencies": {
				"babel-preset-react": "^6.11.1",
				"metal-cli": "^2.0.0"
			},
			"name": "frontend-editor-alloyeditor-my-button-web",
			"version": "1.0.0"
		}

The contents of some of the files have been added as well, since the 
`build gradle` file requires some customizing.

Now that your OSGi bundle is configured, you can learn how to create buttons for
the AlloyEditor next.

## Creating the Button [](id=creating-the-button)

Below is an example configuration for a JSX file that creates a new button:

    /* global React, ReactDOM AlloyEditor */
    
    (function() {
            'use strict';
    
            var React = AlloyEditor.React;
    
            var ButtonMyButton = React.createClass(
                    {
                            mixins: [AlloyEditor.ButtonStateClasses],
    
                            displayName: 'ButtonMyButton',
    
                            propTypes: {
                                    editor: React.PropTypes.object.isRequired
                            },
    
                            statics: {
                                    key: 'myButton'
                            },
    
                            /**
                             * Lifecycle. Renders the UI of the button.
                             *
                             * @method render
                             * @return {Object} The content which should be rendered.
                             */
                            render: function() {
                                    var cssClass = 'ae-button ' + 
                                    this.getStateClasses();
    
                                    return (
                                            <button className={cssClass} 
                                            onClick={this._requestExclusive} 
                                            tabIndex={this.props.tabIndex}>
                                                    <small className="ae-icon small">
                                                    Alt
                                                    </small>
                                            </button>
                                    );
                            },
    
                            /**
                             * @protected
                             * @method  _doSomething
                             * @param {MouseEvent} event
                             */
                            _doSomething: function(event) {
                                    console.log('do something!');
                            }
                    }
            );
    
            AlloyEditor.Buttons[ButtonMyButton.key] = AlloyEditor.ButtonMyButton 
            = ButtonMyButton;
    }());

The configuration above creates a new button called `ButtonMyButton`. The key
aspects to note here are the lines that reference the global `AlloyEditor`. You
can create your own JavaScript functions to interact with your button.

Now that you've seen how you can use a JSX file to create a new button, you can
learn how to use your button in the editor next.

## Contributing the Button [](id=contributing-the-button)

The next step is to add your button to the list of already available buttons.
This can be achieved thanks to some smartly placed
`<liferay-util:dynamic-include />` tags in the editor's infrastructure. To make
your button available in the AlloyEditor, you must extend the
[`BaseDynamicInclude` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/BaseDynamicInclude.html).
Below is an example configuration that extends this class:

    package com.liferay.frontend.editor.alloyeditor.my.button.web.servlet.taglib;
    
    import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
    import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    import com.liferay.portal.kernel.util.PortalUtil;
    import com.liferay.portal.kernel.util.StringBundler;
    import com.liferay.portal.kernel.util.WebKeys;
    
    import java.io.IOException;
    import java.io.PrintWriter;
    
    import javax.servlet.ServletContext;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    
    @Component(immediate = true, service = DynamicInclude.class)
    public class AlloyEditorMyButtonDynamicInclude extends BaseDynamicInclude {
    
            @Override
            public void include(
                            HttpServletRequest request, HttpServletResponse response,
                            String key)
                    throws IOException {
    
                    ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
                            WebKeys.THEME_DISPLAY);
    
                    PrintWriter printWriter = response.getWriter();
    
                    StringBundler sb = new StringBundler(7);
    
                    sb.append("<script src=\"");
                    sb.append(themeDisplay.getPortalURL());
                    sb.append(PortalUtil.getPathProxy());
                    sb.append(_servletContext.getContextPath());
                    sb.append("/js/buttons.js");
                    sb.append("\" ");
                    sb.append("type=\"text/javascript\"></script>");
    
                    printWriter.println(sb.toString());
            }
    
            @Override
            public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
                    dynamicIncludeRegistry.register(
                            "com.liferay.frontend.editor.alloyeditor.web#alloyeditor#" +
                                    "additionalResources");
            }
    
            @Reference(
                    target = "(osgi.web.symbolicname=com.liferay.frontend.editor.alloyeditor.my.button.web)"
            )
            private ServletContext _servletContext;
    }

Now that your button is included, you can learn how to make the button available
in the editor's toolbar next.
 
## Using the Button in a Toolbar [](id=using-the-button-in-a-toolbar)

As explained in the [Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration) 
tutorial, you can configure which buttons show in the AlloyEditor toolbars by
adding your own `EditorConfigContributor`. This file allows you to specify where 
in the toolbar your button should appear. The example configuration below
doesn't specify a portlet name, so the button is added to the global 
AlloyEditor.

    package com.liferay.frontend.editor.alloyeditor.my.button.web.editor.configuration;
    
    import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
    import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
    import com.liferay.portal.kernel.json.JSONArray;
    import com.liferay.portal.kernel.json.JSONFactoryUtil;
    import com.liferay.portal.kernel.json.JSONObject;
    import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    
    import java.util.Map;
    import java.util.Objects;
    
    import org.osgi.service.component.annotations.Component;
    
    @Component(
            property = {"editor.name=alloyeditor", "service.ranking:Integer=1000"},
            service = EditorConfigContributor.class
    )
    public class AlloyEditorMyButtonConfigContributor
            extends BaseEditorConfigContributor {
    
            @Override
            public void populateConfigJSONObject(
                    JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
                    ThemeDisplay themeDisplay,
                    RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
    
                    JSONObject toolbarsJSONObject = jsonObject.getJSONObject("toolbars");
    
                    if (toolbarsJSONObject == null) {
                            toolbarsJSONObject = JSONFactoryUtil.createJSONObject();
                    }
    
                    JSONObject stylesJSONObject = toolbarsJSONObject.getJSONObject(
                            "styles");
    
                    if (stylesJSONObject == null) {
                            stylesJSONObject = JSONFactoryUtil.createJSONObject();
                    }
    
                    JSONArray selectionsJSONArray = stylesJSONObject.getJSONArray(
                            "selections");
    
                    for (int i = 0; i < selectionsJSONArray.length(); i++) {
                            JSONObject selection = selectionsJSONArray.getJSONObject(i);
    
                            if (Objects.equals(selection.get("name"), "text")) {
                                    JSONArray buttons = selection.getJSONArray("buttons");
    
                                    buttons.put("myButton");
                            }
                    }
    
                    stylesJSONObject.put("selections", selectionsJSONArray);
    
                    toolbarsJSONObject.put("styles", stylesJSONObject);
    
                    jsonObject.put("toolbars", toolbarsJSONObject);
            }
    }

There you have it. Now you know how to create and use custom buttons in the
AlloyEditor!

## Related Topics [](id=related-topics)

[Adding a WYSIWYG Editor to a Custom Portlet](/develop/tutorials/-/knowledge_base/7-0/adding-a-wysiwyg-editor-to-a-custom-portlet)

[Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)
