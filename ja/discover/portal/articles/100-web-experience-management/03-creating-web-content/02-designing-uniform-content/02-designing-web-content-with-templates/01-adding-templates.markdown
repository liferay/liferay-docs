---
header-id: adding-templates
---

# 構造を持つテンプレートの追加

[TOC levels=1-4]

テンプレートをよりよく理解するために、構造と関連するテンプレートを作成します。 最初に構造を作成します。

1.  サイト管理ページから *コンテンツ* → *Webコンテンツ* 移動し、 *構造* タブを開きます。

2.  [ *追加* ]ボタン（![Add Structure](../../../../../images/icon-add.png)）をクリックします。

3.  構造に *News Article* という名前を付け、次のフィールドを追加します。

    | フィールドタイプ | フィールドラベル | 名前         |
    | -------- | -------- | ---------- |
    | テキスト     | 題名       | `title`    |
    | テキストボックス | 概要       | `abstract` |
    | 画像       | 画像       | `image`    |
    | HTML     | 体        | `body`     |


4.  [ *保存*クリックします。

次にテンプレートを作成し、構造に接続します。

1.  *Web Content* ページから、 *Templates* タブに移動します。

2.  [ *追加* ]ボタン（![Add Template](../../../../../images/icon-add.png)）をクリックします。

3.  名前 *ニュース記事*入力します。

4.  *Details* を開き、FreeMarkerがスクリプト言語として選択されていることを確認します（これがデフォルトです）。

5.  [ *構造*で[ *選択* クリックし* 。</p></li>

6

*News Article* 構造を選択し* 。</p></li>

7

で *スクリプト* エリア、見つける *フィールド* 左側のラベルをしてをクリックして *タイトル*、 *抽象*、 *画像* 及び *のボディ* エディタ領域へ。 次のようになります。
  
       ${title.getData()}
       ${abstract.getData()}
       <#if image.getData()?? && image.getData() != ""> <img alt="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" /> </#if>
       ${body.getData()}

8

次に、見出しと `<p>` タグを追加し、画像を中央に揃えて、次のように要素のスタイルを設定します。</ol><h1>$ {title.getData（）}</h1>
        <p>$ {abstract.getData（）}</p>
        <#if image.getData()?? && image.getData() != ""> <img alt="$ {image.getAttribute（ "alt"）}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" align="center" /> </#if>
        <p>$ {body.getData（）}</p>

9.  [ *保存*クリックします。

完成させるには、コンテンツを追加します。

1.  *Web Content* タブに移動します。

2.  [ *追加* ]ボタン（![Add](../../../../../images/icon-add.png)）をクリックし、[ *ニュース記事*]を選択します。

3.  コンテンツを挿入して公開します\！

![図1：ルナリゾートニュースの記事が形作られています\！](../../../../../images/web-content-structures-templates-completed.png)
