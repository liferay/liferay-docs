---
header-id: implementing-portlet-actions
---

# ポートレット操作の実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">初めてのポートレット開発</p><p>手順7/8</p>
</div>

ユーザーがフォームを送信すると、アプリケーションはゲストブックに表示するフォームデータを保存します。 この最初のアプリケーションをシンプルに保つために、ポートレットのルック&フィールと呼ばれる、ポートレットAPIの一部を使用してこれを実装します。 通常はもちろんデータベースを使用し、これを後でデータベースにリファクタリングします。 ただし、この初回の段階では、ポートレットのルック&フィールを使用して、ゲストブックアプリケーションを作成します。

ポートレットで再レンダリング以外の操作を行うには、ポートレット操作を実行してください。 通常はユーザー入力に基づいて、ポートレットがレンダリングする前に実行する処理を操作で定義します。 ゲストブックポートレットの場合、次に実装する操作は、ユーザーがフォームに入力したゲストブックエントリを保存します。 保存されたゲストブックエントリは、後で取得され、表示されます。

LiferayのMVCポートレットフレームワークを使用しているため、操作を簡単に実装できます。 ポートレット操作は、コントローラとして機能するポートレットクラスに実装されています。 作成したフォームで操作URLを作成し、それを`addEntry`と呼びました。 ポートレット操作を作成するには、同じ名前のポートレットのクラス名にメソッドを作成します。 `MVCPortlet`は、ユーザーが一致するURLを実行したときにそのメソッドを呼び出します。

1.  `GuestbookPortlet`を開きます。 ポートレットプロジェクトを作成すると、プロジェクトテンプレートがこのクラスを生成します。

2.  以下の署名でメソッドを作成します。
   
        public void addEntry(ActionRequest request, ActionResponse response) {
       
        }

3.  \[CTRL\]+\[SHIFT\]+Oを押してインポートを整理し、必要な`javax.portlet.ActionRequest`および`javax.portlet.ActionResponse`クラスをインポートします。

これで、ポートレット操作が作成されました。 それは何も行いませんが、少なくともフォームを送信してもエラーは発生しません。 次に、フォームデータを保存します。

ポートレットのルック&フィールAPIの制限のため、各ゲストブックエントリを、文字列配列の`String`として保存する必要があります。 フォームに２つのフィールドがあるので、区切り文字を使用して、ユーザー名の終わりと、ゲストブックエントリの開始場所を決定します。 キャレット記号（`^`）は、ユーザーがゲストブックエントリでその記号を使用する可能性が非常に低いため、適切な区切り文字になります。

| **注: **ここでは、ポートレットのルック&フィールAPIがプロトタイピングの目的 | のみで使用されています。 ほとんどの場合、データを保存するためのより頑強なソリューションが必要です。 | *本サービスビルダー*セクションの後半で、このようなソリューションを実装する方法を学びます。

次のメソッドでは、ゲストブックエントリを`guestbook-entries`というポートレットのルック&フィールに追加することを実装します。

    public void addEntry(ActionRequest request, ActionResponse response) {
        try {
            PortletPreferences prefs = request.getPreferences();
    
            String[] guestbookEntries = prefs.getValues("guestbook-entries",
                    new String[1]);
    
            ArrayList<String> entries = new ArrayList<String>();
    
            if (guestbookEntries[0] != null) {
                entries = new ArrayList<String>(Arrays.asList(prefs.getValues(
                        "guestbook-entries", new String[1])));
            }
    
            String userName = ParamUtil.getString(request, "name");
            String message = ParamUtil.getString(request, "message");
            String entry = userName + "^" + message;
    
            entries.add(entry);
    
            String[] array = entries.toArray(new String[entries.size()]);
    
            prefs.setValues("guestbook-entries", array);
    
            try {
                prefs.store();
            }
            catch (IOException ex) {
                Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
            catch (ValidatorException ex) {
                Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
    
        }
        catch (ReadOnlyException ex) {
            Logger.getLogger(GuestbookPortlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

1.  既存の`addEntry`メソッドを上記のメソッドに置き換えます。

2.  インポートを整理するため \[CTRL\]+\[SHIFT\]+O を押し、`javax.portlet.PortletPreferences`、 `java.util.logging.Logger`および`java.util.logging.Level`を、要求された時に（Liferayに相当するものではありません）選択してください。

まず、プリファレンスを取得します。 それから`guestbook-entries`プリファレンスが取得され、`ArrayList`へ変換されます。そのため、配列のサイズを超えることを心配せずにエントリを追加できます。 次に、フォームから名前とメッセージフィールドが取得されます。 Liferayの`ParamUtil`クラスを使用すると、URLパラメータを簡単に取得できることを覚えておいてください。

最後に、フィールドはキャレットで区切られた`String`へ結合され、新しいエントリが `ArrayList`へ追加されます。その後、配列に戻って変換され、プリファレンスとして保存できます。 try/catchブロックは、ポートレットのルック&フィールAPIに必要です。

これは、ポートレットのルック&フィールを使用する通常の方法ではありませんが、この最初のバージョンのアプリケーションに、ゲストブックエントリをすばやく簡単に保存する方法を提供します。 後の手順で、データベースにゲストブックエントリを保存するための頑強な方法を実装します。

次回および最後の実装する機能は、ゲストブックエントリを表示するためのメカニズムです。
