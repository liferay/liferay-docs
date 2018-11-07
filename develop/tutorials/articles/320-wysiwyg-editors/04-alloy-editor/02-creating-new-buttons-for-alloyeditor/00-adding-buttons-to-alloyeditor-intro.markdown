# Creating New Buttons for AlloyEditor [](id=creating-new-buttons-for-alloyeditor)

AlloyEditor is built on `React.js` and uses `jsx` to render each button in the 
editor. To add a new button to the AlloyEditor, you must create an OSGi bundle 
that contains three key pieces:

- A JSX file containing the button's configuration 
- A Java class that contributes the button to the list of available buttons
- A Java class that adds the button to the AlloyEditor's toolbar

Below is the folder structure for a module that adds a new button:

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

  - `.babelrc`

  - `bnd.bnd`

  - `build.gradle`

  - `package.json`

The tutorials in this section cover the following topics:

- How to create your button's OSGi bundle
- How to create your button's JSX file
- How to contribute your button to the list of available buttons 

You can learn how to add your button to the editor's toolbars in the 
[Adding Buttons to AlloyEditor's Toolbars tutorials](/develop/tutorials/-/knowledge_base/7-1/adding-buttons-to-alloyeditor-toolbars). 

The 
[`my-log-text-button` bundle](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/my-log-text-button) 
is used as an example throughout this tutorial. You can 
[download the bundle's zip file](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules/my-log-text-button.zip) 
for reference, or use it as a starting point for your project if you wish. 
