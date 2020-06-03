---
header-id: tracking-custom-assets
---

# カスタムアセットの追跡

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[Liferay Analytics Cloud](https://help.liferay.com/hc/en-us/categories/360000608871-Liferay-Analytics-Cloud-Admin-Guide) は、フォーム、ブログ、ドキュメントとメディア、Webコンテンツなどの組み込みのLiferay DXPアセットを検出して分析できます。 ただし、カスタムアプリのアセットを分析するには、アプリのHTMLにタグを付けて、Analytics Cloud JavaScriptプラグインがアセットとのユーザーインタラクションを検出および追跡できるようにする必要があります。

## 資産イベント

Analytics Cloud JavaScriptプラグインには、追跡可能な次のイベントが含まれています。

`AssetClicked`：ユーザーがアセット領域をクリックします。 また、クリックされたタグに関する情報も含まれます。

`AssetDepthReached`：アセット領域のスクロールイベント。 また、ユーザーが到達したコンテンツの深さに関する情報も含まれます（たとえば、ユーザーがブログ投稿をどれだけ下にスクロールしたかなど）。

`AssetViewed`：ユーザーがアセットを表示します。

`AssetDownloaded`：ユーザーがアセットをダウンロードするリンクをクリックします。

`AssetSubmitted`：アセット領域でのフォーム送信。 これには、HTML `フォーム` 要素の下に配置される入力タイプ `送信` が必要です。

## 必要なメタデータ

カスタムエンティティの追跡を有効にするには、次の情報が必要です。 以下の属性を使用して、この情報をHTMLで指定する必要があります。

**資産タイプ（ストリング）：** 追跡する資産タイプ。 このためのHTML属性は `data-analytics-asset-type`です。 この属性 **値は、エンティティの正確なタイプ** はないことに注意してください。 カスタムエンティティの場合、この値は常に `custom`です。

**アセットID（文字列）：** アセットの一意の識別子。 このためのHTML属性は `data-analytics-asset-id`です。

**アセットカテゴリ（文字列、オプション）：** アセットを含むカスタムアプリのカテゴリ。 このためのHTML属性は `data-analytics-asset-category`です。 これを使用して、名前（例：「投票」）でカスタムアプリを識別できます。 カテゴリ内では、すべてのアセットIDは一意である必要があります。

**アセットのタイトル（文字列、オプション）：** アセットのタイトル。 このためのHTML属性は `data-analytics-asset-title`です。

## アセットイベントの追跡

たとえば、カスタムPollsアプリで投票を追跡する場合は、次のようなHTMLを使用できます。

    <div>
    <h1> What's your favorite food? </h1>
    <form action="/submit.php">
    <div>
      <input type="radio" id="sushi" name="food" value="Sushi"
         checked>
      <label for="sushi">Sushi</label>
    </div>
    <div>
      <input type="radio" id="pizza" name="food" value="Pizza">
      <label for="pizza">Pizza</label>
    </div>
    <input type="submit" value="Submit" />
    </form>
    </div>

この投票のイベントを追跡するには、上記の属性を `div`に追加します。

    <div data-analytics-asset-type="custom"
      data-analytics-asset-id="favorite-food-poll"
      data-analytics-asset-category="polls"
      data-analytics-asset-title="What is your favorite food Poll">
    
    <h1> What's your favorite food? </h1>
    ...
    </div>

Analytics Cloudで追跡する各アセットにこれらの属性を追加する必要があることに注意してください。 ただし、スクリプトを使用して属性の値を入力できるため、各アセットに対してこのプロセスを自動化できます。

### ダウンロードの追跡

ダウンロードを追跡するには、アクションをトリガーする要素に次の属性のタグを付ける必要があります。

    data-analytics-asset-action="download"

たとえば、上記の投票は、投票の指示が含まれているPDFファイルのダウンロードリンクがあります。

    <div data-analytics-asset-type="custom"
      data-analytics-asset-id="favorite-food-poll"
      data-analytics-asset-category="polls"
      data-analytics-asset-title="What is your favorite food Poll">
    
    <a href="/poll-instructions.pdf" data-analytics-asset-action="download">Download the Poll Instructions </a>
    
    <h1> What's your favorite food? </h1>
    ...
    </div>

## 関連トピック

[アセットフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)

[Liferay Analytics Cloud管理ガイド](https://help.liferay.com/hc/en-us/categories/360000608871-Liferay-Analytics-Cloud-Admin-Guide)
