# Creating the AlloyEditor Button's OSGi Bundle [](id=creating-the-alloyeditor-buttons-osgi-bundle)

Follow these steps to create your OSGi bundle for your new button:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module) 

2.  Add a `resources\META-INF\resources\js` folder to your module's `src\main` 
    folder.

3.  Specify your bundle's `Web-ContextPath` in its `bnd.bnd` file. An example 
    BND file configuration is shown below with the `Web-ContextPath` pointing to 
    the bundle's root folder. This is required to properly locate and load the 
    module's JavaScript: 

        Bundle-Name: my-log-text-button
        Bundle-SymbolicName: com.liferay.docs.portlet
        Bundle-Version: 1.0.0
        Web-ContextPath: /my-button-portlet-project

4.  Since the button's configuration is defined in a JSX file, it must be 
    transpiled for the browser. You can do this by adding the `transpileJS` task 
    to your `build.gradle` file. An example configuration is shown below:

        configJSModules {
            enabled = false
        }

        dependencies {
          compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.6.2"
          compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib", version: "2.0.0"
          compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
          compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
          compileOnly group: "jstl", name: "jstl", version: "1.2"
          compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
          compileOnly group: "org.osgi", name: "org.osgi.core", version: "6.0.0"
        }

        transpileJS {
            bundleFileName = "js/buttons.js"
            globalName = "AlloyEditor.Buttons"
            modules = "globals"
            srcIncludes = "**/*.jsx"
        }

5.  Add the following `devDependencies` to your `package.json` file:

        {
          "devDependencies": {
            "babel-preset-react": "^6.11.1",
            "metal-cli": "^4.0.1"
          },
          "name": "my-bundle-name",
          "version": "1.0.0"
        }

6.  Add following preset to your module's `.babelc` file to transpile your JSX 
    file:

        {
          "presets": [
            "react"
          ]
        }

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-1/adding-new-behavior-to-an-editor)

[Creating the Button's JSX File](/develop/tutorials/-/knowledge_base/7-1/creating-the-alloyeditor-buttons-jsx-file)

[Contributing the Button to AlloyEditor](/develop/tutorials/-/knowledge_base/7-1/contributing-the-button-to-alloyeditor)
