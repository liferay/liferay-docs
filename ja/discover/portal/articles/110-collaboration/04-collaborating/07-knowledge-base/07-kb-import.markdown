---
header-id: importing-knowledge-base-articles
---

# ナレッジベース記事のインポート

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

前述のように、ナレッジベースアプリは記事を一括でインポートできます。 これにより、記事が公開される前に事前に準備されるオフラインプロセスを利用できます。 記事は、 [Markdown](http://commonmark.org) ファイルとしてナレッジベースにインポートされます。 Markdownは読みやすいテキストのみのファイル形式ですが、記事のフォーマットに必要なすべてのことをサポートしています。

| **注：** 記事をインポートするには、役割に *ナレッジベース* |を付与する必要があります。 → *リソース権限：記事のインポート* 権限。

ナレッジベースは、 [Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/)呼ばれるMarkdown方言をサポートしています。 この方言は、テーブルの書式設定、画像のキャプション、脚注などの機能で元のMarkdownを拡張します。

ナレッジベースがMarkdown記事をインポートするには、次の要件に準拠している必要があります。

  - すべてのソースファイルは、 `.markdown` または `.md` 拡張子を使用する必要があります。
  - 記事はトップレベルのヘッダーで始まる必要があります（例： `＃見出し...`）。
  - 各ヘッダーには、記事のわかりやすいURLタイトルと記事のサブヘッダーのアンカータグに関連付けられた一意のIDが必要です。 IDを正しく指定するトップレベルヘッダーの例を次に示します。

`# Some Heading [](id=some-heading)`

以下は、簡単なサンプル記事のMarkdownソーステキストです。

``` 
# The Moons of Mars [](id=the-moons-of-mars)

As you look up from your chaise lounge, you're sure to see our neighboring
planet Mars. Did you know that Mars has two moons? You might have to break 
out a pair of binoculars to see them.

Its two moons are aptly named after the two sons of mythical Roman god Mars.
Their names are Phobos and Deimos. 
```

上記の最初の行で、ヘッダーのID割り当て `id = the-moons-of-mars`に注意してください。 インポート時に、ID値はナレッジベース記事のURLタイトルになります。

値下げは、標準のものです：あります [Githubの風味Markdownを](https://help.github.com/articles/github-flavored-markdown)提案し、 [一般的な値下げ構文](http://www.commonmark.org)サポート値下げ（Redditに、StackExchange、その他）、マークダウンエディタ、というフォーラム、 [IETFドラフト](https://tools.ietf.org/html/rfc7763) それ公式インターネット作るためには、メディアタイプ（テキスト/マークダウン）。 Markdownにそれほど関心があるのはなぜですか？

1.  それは読みやすいです。 Markdownがわからなくても、構文を除外することなく読み取ることができます。

2.  それは作家のやり方から抜け出します。 テキストを見出しに変更したり、箇条書きを作成したりするために、さまざまなアイコンにマウスを合わせる必要はありません。 入力を開始するだけです。 構文は非常に直感的です。

3.  HTMLに変換するように設計されていますが、他の多くの形式に変換するツールがあります。 記事がMarkdownにある場合、それらをWeb、モバイル形式（Kindle、ePub）に発行し、印刷するのは簡単です。

4.  テキストだけなので、既存のツールを使用してそのテキストを共同編集できます。 GitHubのようなサービスを使用して、人々はあなたの記事に貢献でき、あなたはそれらに加えられたすべての変更を見ることができます。
