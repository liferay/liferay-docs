---
header-id: adding-a-button-to-the-add-toolbar
---

# 追加ツールバーへのボタンの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

カーソルがエディター内にあり、追加ボタンをクリックすると、AlloyEditorに追加ツールバーが表示されます。

![図1：[追加]ツールバーを使用すると、エディターにコンテンツを追加できます。](../../../../images/alloyeditor-add-toolbar.png)

以下の手順に従って、ボタンをAlloyEditorの追加ツールバーに追加します。

1.  `populateConfigJSONObject（）` メソッド内で、ツールバーの追加を取得します。
   
        JSONObject addToolbar = toolbarsJSONObject.getJSONObject("add");

2.  既存のツールバーの追加ボタンを取得します。
   
        JSONArray addToolbarButtons = addToolbar.getJSONArray("buttons");

3.  既存のボタンにボタンを追加します。 ボタンの名前では大文字と小文字が区別されることに注意してください。 以下の例は、 `カメラ` ボタンをツールバーの追加に追加します。
   
        addToolbarButtons.put("camera");

    カメラボタンは、AlloyEditorでデフォルトで使用可能なボタンの1つにすぎませんが、すべて有効になっているわけではありません。 追加ツールバーに追加できる利用可能なボタンの完全なリストは次のとおりです。

      - カメラ
      - 埋め込む
      - hline
      - 画像
      - 表

    参照してください [ここで](https://alloyeditor.com/docs/features/) 各ボタンの機能の説明について。

4.  AlloyEditorの構成を、行った変更で更新します。

        addToolbar.put（ "buttons"、addToolbarButtons）;

        toolbarsJSONObject.put（ "add"、addToolbar）;

        jsonObject.put（ "toolbars"、toolbarsJSONObject）;

5.  [モジュール](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-blade-cli) をデプロイし、AlloyEditorを使用する新しいコンテンツ（ブログエントリやWebコンテンツ記事など）を作成して、新しい設定を実際に確認してください！

`com.liferay.docs.my.button` モジュールの更新されたツールバーの追加を以下の図に示します。

![図2：更新された追加ツールバーを使用すると、カメラから直接画像をエディターに追加できます。](../../../../images/alloyeditor-updated-add-toolbar.png)

## 関連トピック

[エディターに新しい動作を追加する](/docs/7-1/tutorials/-/knowledge_base/t/adding-new-behavior-to-an-editor)

[スタイルツールバーへのボタンの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-a-button-to-a-styles-toolbar)
