# Creating and Contributing new Buttons to AlloyEditor [](id=creating-and-contributing-new-buttons-to-alloyeditor)

It is possible to add additional AlloyEditor functionality through OSGi bundles. This tutorial will demonstrate how to add a button to the editor.

In this tutorial, you will learn how to:

- Create an OSGi bundle for your own button
- Create a custom button for `AlloyEditor`
- Contribute your button to the list of available buttons
- Use your custom button in a toolbar in `AlloyEditor`

## Creating the OSGi Bundle [](id=creating-the-osgi-bundle)

AlloyEditor is built on `React.js` and uses `jsx` to render each button in the editor. Here is the folder structure of our module for adding a new button

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

	- `.babelrc` - needed since we are compiling `JSX`

	- `bnd.bnd`
		```
		Bundle-Name: Liferay Frontend Editor AlloyEditor My Button Web
		Bundle-SymbolicName: com.liferay.frontend.editor.alloyeditor.my.button.web
		Bundle-Version: 1.0.0
		Liferay-Releng-Module-Group-Description:
		Liferay-Releng-Module-Group-Title: Rich Text Editors
		Web-ContextPath: /frontend-editor-alloyeditor-my-button-web
		```

	- `build.gradle`
		```
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
		```

	- `package.json`
		```json
		{
			"devDependencies": {
				"babel-preset-react": "^6.11.1",
				"metal-cli": "^2.0.0"
			},
			"name": "frontend-editor-alloyeditor-my-button-web",
			"version": "1.0.0"
		}
		```

We've added the contents of some of the files since you'll need some customizations to the `build gradle` file.

## Creating the Button [](id=creating-the-button)

Here are the contents of our JSX file for the button
```javascript
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
				var cssClass = 'ae-button ' + this.getStateClasses();

				return (
					<button className={cssClass} onClick={this._requestExclusive} tabIndex={this.props.tabIndex}>
						<small className="ae-icon small">Alt</small>
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

	AlloyEditor.Buttons[ButtonMyButton.key] = AlloyEditor.ButtonMyButton = ButtonMyButton;
}());
```

The file is well documented so take a look at the code. The important lines are those that reference the global `AlloyEditor`. The developer can create their own javascript functions for interactions with their button.

## Contributing the Button [](id=contributing-the-button)

The next step is to add your button to the list of already available ones. This can be achieved thanks to some smartly placed `<liferay-util:dynamic-include />` tags in the editor's infrastructure. The following snippet adds our button so it becomes available to other AlloyEditor instances.

```java
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
```

## Using the Button in a Toolbar [](id=using-the-button-in-a-toolbar)

As explained in the [Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration) tutorial, you can add configure which buttons show in the `AlloyEditor toolbars adding your own `EditorConfigContributor`.

This file handles where in the toolbar our new button should be. A developer can access the `AlloyEditor toolbar and manipulate where there new button should be placed. Since there is no portlet name specified this will be something added for the global `AlloyEditor.

```java
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
```