---
header-id: adding-a-button-to-a-styles-toolbar
---

# スタイルツールバーへのボタンの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

AlloyEditorでコンテンツが選択または強調表示されると、スタイルツールバーが表示されます。 次から選択できる5つのスタイルツールバーがあります。

`embedurl`：埋め込みコンテンツが選択されたときに表示されます。

![図1：埋め込みURLスタイルツールバーを使用すると、エディターで埋め込みコンテンツをフォーマットできます。](../../../../images/alloyeditor-embedurl-toolbar.png)

`画像`：画像が選択されたときに表示されます。

![図2：画像スタイルツールバーを使用すると、エディターで画像をフォーマットできます。](../../../../images/alloyeditor-image-toolbar.png)

`リンク`：ハイパーリンクが選択されたときに表示されます。

![図3：リンクスタイルツールバーを使用すると、エディターでハイパーリンクを書式設定できます。](../../../../images/alloyeditor-link-toolbar.png)

`table`：テーブルが選択されたときに表示されます。

![図4：テーブルスタイルツールバーを使用すると、エディターでテーブルをフォーマットできます。](../../../../images/alloyeditor-table-toolbar.png)

`text`：テキストが強調表示されたときに表示されます。

![図5：テキストスタイルツールバーを使用すると、エディターで強調表示されたテキストを書式設定できます。](../../../../images/alloyeditor-text-toolbar.png)

次の手順に従って、スタイルツールバーの1つにボタンを追加します。

1.  `populateConfigJSONObject（）` メソッド内で、スタイルツールバーを取得します。
   
        JSONObject stylesToolbar = toolbarsJSONObject.getJSONObject("styles");
       
        if (stylesToolbar == null) {
                stylesToolbar = JSONFactoryUtil.createJSONObject();
        }

2.  利用可能な選択ツールバーを取得します。
   
        JSONArray selectionsJSONArray = stylesToolbar.getJSONArray(
        "selections");

3.  選択ツールバーを繰り返し処理では、あなたが（にあるボタン（複数可）を追加したいものを選択`embedurl`、 `、画像`、 `リンク`、 `表`、または `テキスト`）、既存のボタンを取得し、追加するにはボタン。 以下の例では、追加 `クリップボード` プラグインの `コピー`、 `カット`、及び `ペースト` にボタンを `テキスト` 選択ツールバー。 ボタンは大文字と小文字が区別され、エイリアスされるか、プラグインの名前と一致しない場合があります。 プラグインの [`plugin.js` ファイル](/docs/7-1/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide) for `editor.ui.addButton` を検索して、ボタンの名前を見つけます。
   
        for (int i = 0; i < selectionsJSONArray.length(); i++) {
                JSONObject selection = selectionsJSONArray.getJSONObject(i);
       
                if (Objects.equals(selection.get("name"), "text")) {
                        JSONArray buttons = selection.getJSONArray("buttons");
       
                        buttons.put("Copy");
                        buttons.put("Cut");
                        buttons.put("Paste");
                }
        }

    上記の例では、@product@のAlloyEditor</a>バンドルされている

CKEditorプラグインの1つを追加します。 AlloyEditorにはデフォルトでいくつかのボタンがありますが、すべて有効になっているわけではありません。 [スタイル]ツールバーに追加できる既存のボタンの完全なリストは、以下の表にツールバー順に表示されています。</p> 
   
   
    | テキスト            | 表            | 画像          | リンク      |
    | --------------- | ------------ | ----------- | -------- |
    | 大胆な             | tableHeading | imageCenter | linkEdit |
    | コード             | tableRow     | 画像左         |          |
    | h1              | tableColumn  | 画像右         |          |
    | h2              | tableCell    |             |          |
    | indentBlock     | テーブル削除       |             |          |
    | イタリック           |              |             |          |
    | リンク             |              |             |          |
    | ol              |              |             |          |
    | outdentBlock    |              |             |          |
    | パラグラフ左          |              |             |          |
    | パラグラフ右          |              |             |          |
    | paragraphCenter |              |             |          |
    | パラグラフ           |              |             |          |
    | 見積もり            |              |             |          |
    | removeFormat    |              |             |          |
    | ストライク           |              |             |          |
    | スタイル            |              |             |          |
    | 添字              |              |             |          |
    | 上付き             |              |             |          |
    | twitter         |              |             |          |
    | ul              |              |             |          |
    | 下線              |              |             |          |

    
    参照してください [ここで](https://alloyeditor.com/docs/features/) 各ボタンの機能の説明について。</li> 
    
    4  AlloyEditorの構成を、行った変更で更新します。
  
       stylesToolbar.put("selections", selectionsJSONArray);
      
       toolbarsJSONObject.put("styles", stylesToolbar);
      
       jsonObject.put("toolbars", toolbarsJSONObject);
      

5  [モジュール](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-blade-cli) をデプロイし、AlloyEditorを使用する新しいコンテンツを作成します（ブログエントリやWebコンテンツの記事など）。新しい構成を実際に確認します\！</ol> 

`com.liferay.docs.my.button` モジュールの更新されたテキストスタイルツールバーを次の図に示します。

![図6：更新されたテキストスタイルツールバーを使用すると、エディターでテキストをコピー、切り取り、貼り付けできます。](../../../../images/alloyeditor-updated-styles-toolbar.png)



## 関連トピック

[追加ツールバーへのボタンの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-a-button-to-the-add-toolbar)

[CKEditorプラグインリファレンスガイド](/docs/7-1/reference/-/knowledge_base/r/ckeditor-plugin-reference-guide)
