---
header-id: creating-a-fragment
---

# フラグメントを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

最初の例は単純です。 フラグメントとコレクションの基本がよくわからない場合は、最初に [ページフラグメントの作成](/docs/7-1/user/-/knowledge_base/u/creating-fragments) 読んで、自分が何に夢中になっているかを理解する必要があります。

1.  *サイト管理* 移動し、選択したサイトで *ビルド* → *フラグメント*クリックします。

2.  名前の新しいコレクションを作成 *フラグメントの開発*。

3.  新しいコレクション内で、 *Basic Fragment*という名前の新しいFragmentを作成します。

これで、フラグメントの編集ページが表示されます。 この画面には4つのペインがあります。 左上のペインにHTML、右上にCSS、左下にJavaScriptを入力し、右下に結果をプレビューします。

3つの編集ペインは、それぞれが個別のファイルに書き込んでいるかのように見ることができます。 HTMLペイン内のすべてが `index.html`になり、CSSペインが `index.css`になり、JavaScript 1が `index.js`ます。 プレビューペインは、ページ上に表示されるすべてのものをレンダリングします。 これがどのように機能するかを次に示します。

1.  HTMLペインの `div` 内に次のコードを追加します。

    ``` 
      <h1>
          This text is styled, so it will look pretty!
      </h1>
    ```

2.  フラグメントブロック内のCSSペインに次のコードを追加します。

    ``` 
      width: 100%;
      background-color: white;
      text-align: center;
      padding: 2em 0;
      font-size: 28px;
    ```

3.  その下にこのコードを追加し、 `#####` をフラグメントに提供された自動生成番号に置き換えます
   
        .fragment_##### h1 {
          font-weight: 100;
          width: calc(100% - 1em);
          max-width: 60ch;
          margin: 0 auto;
          font-size: 28px;
         }

4.  [ *公開* をクリックして、作業内容を保存し、コンテンツページに追加できるようにします。

作業中、プレビューペインでプレビューを確認できます。

![図1：HTMLおよびCSSコードとライブプレビューを備えたフラグメントエディター](../../../images/fragment-editor-basic.png)

ここから、フラグメントを使用してコンテンツページを作成できます。 アクションでこのプロセスを確認するには、参照 [断片からビルのコンテンツページ](/docs/7-1/user/-/knowledge_base/u/building-content-pages-from-fragments) チュートリアルを。 次に、フラグメント固有のタグを調べます。
