---
header-id: creating-new-buttons-for-alloyeditor
---

# AlloyEditorの新しいボタンの作成

[TOC levels=1-4]

AlloyEditorは `React.js` 上に構築され、 `jsx` を使用してエディターの各ボタンをレンダリングします。 AlloyEditorに新しいボタンを追加するには、3つのキーピースを含むOSGiバンドルを作成する必要があります。

  - ボタンの構成を含むJSXファイル
  - 利用可能なボタンのリストにボタンを提供するJavaクラス
  - AlloyEditorのツールバーにボタンを追加するJavaクラス

以下は、新しいボタンを追加するモジュールのフォルダー構造です。

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

このセクションのチュートリアルでは、次のトピックについて説明します。

  - ボタンのOSGiバンドルを作成する方法
  - ボタンのJSXファイルを作成する方法
  - 利用可能なボタンのリストにボタンを提供する方法

[EditorEditorのツールバーにボタンを追加するチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/adding-buttons-to-alloyeditor-toolbars)、エディターのツールバーにボタンを追加する方法を学ぶことができます。

[`my-log-text-button` bundle](https://github.com/liferay/liferay-docs/tree/7.1.x/en/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button/my-log-text-button) は、このチュートリアル全体を通して例として使用されます。 次のことができます [ダウンロードバンドルのzipファイル](https://github.com/liferay/liferay-docs/blob/7.1.x/en/develop/tutorials/code/osgi/modules/com.liferay.docs.my.button/my-log-text-button.zip) 参照のために、または必要に応じて、プロジェクトの開始点として使用します。
