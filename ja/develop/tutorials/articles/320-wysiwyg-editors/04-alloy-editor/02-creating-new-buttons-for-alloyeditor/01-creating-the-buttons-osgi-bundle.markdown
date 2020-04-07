---
header-id: creating-the-alloyeditor-buttons-osgi-bundle
---

# AlloyEditorボタンのOSGiバンドルの作成

[TOC levels=1-4]

次の手順に従って、新しいボタン用のOSGiバンドルを作成します。

1.  [OSGiモジュールを作成する](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)

2.  `resources \ META-INF\resources \ js` フォルダーをモジュールの `src \ main` フォルダーに追加します。

3.  `bnd.bnd` ファイルでバンドルの `Web-ContextPath` を指定します。 `Web-ContextPath` がバンドルのルートフォルダーを指しているBNDファイル構成の例を以下に示します。 これは、モジュールのJavaScriptを適切に見つけてロードするために必要です。
   
        Bundle-Name: my-log-text-button
        Bundle-SymbolicName: com.liferay.docs.portlet
        Bundle-Version: 1.0.0
        Web-ContextPath: /my-button-portlet-project

4.  ボタンの構成はJSXファイルで定義されているため、ブラウザー用に変換する必要があります。 これを行うには、 `transpileJS` タスクを `build.gradle` ファイルに追加します。 構成例を以下に示します。
   
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

5.  次の `devDependencies` を `package.json` ファイルに追加します。
   
        {
          "devDependencies": {
            "babel-preset-react": "^6.11.1",
            "metal-cli": "^4.0.1"
          },
          "name": "my-bundle-name",
          "version": "1.0.0"
        }

6.  次のプリセットをモジュールの `.babelrc` ファイルに追加して、JSXファイルをトランスパイルします。
   
        {
          "presets": [
            "react"
          ]
        }

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[ボタンのJSXファイルの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-alloyeditor-buttons-jsx-file)

[AlloyEditorへのボタンの貢献](/docs/7-1/tutorials/-/knowledge_base/t/contributing-the-button-to-alloyeditor)
