---
header-id: adding-failure-and-success-messages
---

# 失敗および成功メッセージの追加

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">メッセージとエラーの表示</p><p>手順2/3</p>
</div>

ユーザーにフィードバックを適切に表示するには、ポートレットクラスを編集して、@product@の `SessionMessages` および `SessionErrors` クラスを使用する必要があります。 これらのクラスは、ビューレイヤーがタグを通じてユーザーに表示するメッセージを収集します。

これらのメッセージは、ユーザーがエントリまたはゲストブックの作成、編集、削除など、成功または失敗する可能性のあるシステム関数をトリガーしたときに実行されるコードに追加します。 これは通常、アクションメソッドで発生します。 `GuestbookPortlet.java` および `GuestbookAdminPortlet.java`失敗および成功状態を処理するには、これらのメソッドを更新する必要があります。 `GuestbookPortlet.java``addEntry` および `deleteEntry` を更新することから始めます。

1.  `GuestbookPortlet.java`で `addEntry` メソッドを見つけます。 最初の `try ... catch` ブロックの `try` セクションで、終了 `}`直前に成功メッセージを追加します。
   
        SessionMessages.add(request, "entryAdded");

    これは、Liferayの `SessionMessages` APIを使用して、ゲストブックが正常に追加されるたびに成功メッセージを追加します。 `Language.properties` ファイルに配置したメッセージを検索し、キー `エントリが追加された` のメッセージを挿入します（キーをラクダケースから自動的に変換します）。

2.  その下の `catch` ブロックで、次のコードを見つけます。
   
        System.out.println(e);

3.  その下に、次の行を貼り付けます。
   
        SessionErrors.add(request, e.getClass().getName());

    メッセージをコンソールに記録するだけでなく、 `SessionErrors` オブジェクトを使用してユーザーにメッセージを表示します。

次に、 `deleteEntry` メソッドについても同じことを行い` 。</p>

<ol start="1">
<li><p spaces-before="0"> エントリを削除するロジックの後に、成功メッセージを追加します。</p>

<pre><code> SessionMessages.add(request, "entryDeleted");
`</pre></li>

2

同じ `Loggerを見つけます... <code>deleteEntry` メソッドで</code> ブロックのコードを作成し、その後に次の行を貼り付けます。
  
       SessionErrors.add(request, e.getClass().getName());

3

\ [CTRL \] + \ [SHIFT \] + Oを押して、 `com.liferay.portal.kernel.servlet.SessionErrors` および `com.liferay.portal.kernel.servlet.SessionMessages`をインポートし` 。 ファイルを保存します。</p></li>
</ol>

<p spaces-before="0">よくやった\！ <code>GuestbookPortlet`メッセージを追加しました。 `GuestbookAdminPortlet.java`更新する必要があります：

1.  `GuestbookAdminPortlet.java` を開き、同じキューを探します。

2.  適切な成功メッセージを追加 `トライ` のセクション `のtry ...キャッチ` で `addGuestbook`、 `updateGuestbook`、および `deleteGuestbook`、それぞれ：

    ``` 
     SessionMessages.add(request, "guestbookAdded");

     SessionMessages.add(request, "guestbookUpdated");

     SessionMessages.add(request, "guestbookDeleted");
    ```

3.  これらの同じメソッドの `catch` セクションで、 `Logger.getlogger ...` を見つけ、その下に `SessionErrors` ブロックを貼り付けます。
   
        SessionErrors.add(request, pe.getClass().getName());

4.  \ [CTRL \] + \ [SHIFT \] + Oを押して `SessionErrors` および `SessionMessages`をインポートし 。 ファイルを保存します。</p></li>
</ol>

<p spaces-before="0">すばらしいです\！ コントローラーは、関連する詳細なフィードバックを利用できるようになりました。 あとは、このフィードバックをビューレイヤーで公開するだけです。</p>
