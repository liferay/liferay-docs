---
header-id: creating-new-buttons-for-alloyeditor
---

# Creating New Buttons for AlloyEditor

[TOC levels=1-4]

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

The section covers these topics:

- How to create your button's OSGi bundle
- How to create your button's JSX file
- How to contribute your button to the list of available buttons 

To learn how to add your button to the editor's toolbars, see 
[Adding Buttons to AlloyEditor's Toolbars](/docs/7-2/frameworks/-/knowledge_base/f/adding-buttons-to-alloyeditor-toolbars). 

The 
[`my-log-text-button` bundle](https://github.com/liferay/liferay-docs/tree/7.1.x/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button/my-log-text-button) 
is used as an example throughout this section. You can 
[download the bundle's zip file](https://github.com/liferay/liferay-docs/tree/7.1.x/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button/my-log-text-button.zip) 
for reference, or use it as a starting point for your project if you wish. 
