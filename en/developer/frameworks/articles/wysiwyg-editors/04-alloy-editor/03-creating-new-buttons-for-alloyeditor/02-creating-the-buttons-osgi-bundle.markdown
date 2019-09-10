---
header-id: creating-the-alloyeditor-buttons-osgi-bundle
---

# Creating the AlloyEditor Button's OSGi Bundle

[TOC levels=1-4]

Follow these steps to create your OSGi bundle for your new button:

1.  [Create an OSGi module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project) 

2.  Add a `resources\META-INF\resources\js` folder to your module's `src\main` 
    folder.

3.  Specify your bundle's `Web-ContextPath` in its `bnd.bnd` file. An example 
    BND file configuration is shown below with the `Web-ContextPath` pointing to 
    the bundle's root folder. This is required to properly locate and load the 
    module's JavaScript: 

    ```properties
    Bundle-Name: my-log-text-button
    Bundle-SymbolicName: com.liferay.docs.portlet
    Bundle-Version: 1.0.0
    Web-ContextPath: /my-button-portlet-project
    ```

4.  Since the button's configuration is defined in a JSX file, it must be 
    transpiled for the browser. You can do this by adding the `transpileJS` task 
    to your `build.gradle` file. An example configuration is shown below:

    ```groovy
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
    ```

5.  Add the following `devDependencies` to your `package.json` file:

    ```json
    {
      "devDependencies": {
        "babel-preset-react": "^6.11.1",
        "metal-cli": "^4.0.1"
      },
      "name": "my-bundle-name",
      "version": "1.0.0"
    }
    ```

6.  Add following preset to your module's `.babelc` file to transpile your JSX 
    file:

    ```json
    {
      "presets": [
        "react"
      ]
    }
    ```

## Related Topics

- [Adding New Behavior to an Editor](/docs/7-2/frameworks/-/knowledge_base/f/adding-new-behavior-to-an-editor)
- [Creating the Button's JSX File](/docs/7-2/frameworks/-/knowledge_base/f/creating-the-alloyeditor-buttons-jsx-file)
- [Contributing the Button to AlloyEditor](/docs/7-2/frameworks/-/knowledge_base/f/contributing-the-button-to-alloyeditor)
