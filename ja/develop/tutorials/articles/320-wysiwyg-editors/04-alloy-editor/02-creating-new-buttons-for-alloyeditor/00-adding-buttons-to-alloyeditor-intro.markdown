---
header-id: creating-new-buttons-for-alloyeditor
---

# AlloyEditorの新しいボタンの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

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
