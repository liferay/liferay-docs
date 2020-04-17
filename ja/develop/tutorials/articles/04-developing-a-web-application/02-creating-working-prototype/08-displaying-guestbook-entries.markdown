---
header-id: viewing-guestbook-entries
---

# ゲストブックのエントリを表示する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">初めてのポートレット開発</p><p>手順8/8</p>
</div>

ゲストブックのエントリを表示するには、それらを保存するために行ったのと逆の操作を行う必要があります。ポートレットのルック&フィールからそれらを取得し、ループし、ページに表示します。 MVCポートレットでこれを行う最良の方法は、[Model-View-Controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)パラダイムを使用することです。 すでにビュー（JSPファイル）と、コントローラ （ポートレットクラス）があるので、 次にモデルを作成します。

## モデルの作成

1.  `com.liferay.docs.guestbook.model`という新しいパッケージを作成します。 これを行うには、`src/main/java`フォルダを右クリックして*New* → *Package*を選択します。 次に、表示されるダイアログボックスに、パッケージ名を入力します。

2.  次に、モデルクラスを作成します。 このクラスは、ゲストブックエントリをモデル化します。 これを行うには、新しいパッケージを右クリックし、*New* → *Class*クラスを選択します。 クラスに`Entry`と名前を付け、*Finish*をクリックします。

    これで、ゲストブックエントリ用のJavaクラスが作成されました。 次に、エントリを保存するために必要なフィールドを指定します。

3.  2つのプライベートString変数を作成します：`name`および`message`。
   
        private String name;
        private String message;

4.  エディターの空白エリアを右クリックして、*Source* → *Generate Getters and Setters*を選択します。 ポップアップされたダイアログで*Select All*をクリックし、*Generate*をクリックします。

5.  次に、2つのコンストラクターを提供します。1つは2つのフィールドの値なしでクラスを初期化し、もう1つはパラメーターとして2つのフィールドを取り、それらの値を設定します。
   
        public Entry() {
           this.name = null;
           this.message = null;
        }
       
        public Entry(String name, String message) {
           setName(name);
           setMessage(message);
        }

これで、下記のようなモデルクラスが作成されました:

    package com.liferay.docs.guestbook.model;
    
    public class Entry {
    
        private String name;
        private String message;
    
        public Entry() {
            this.name = null;
            this.message = null;
        }
    
        public Entry(String name, String message) {
            setName(name);
            setMessage(message);
        }
    
        public String getName() {
            return name;
        }
    
        public String getMessage() {
            return message;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public void setMessage(String message) {
            this.message = message;
        }
    
    }

モデルを作成したので、ゲストブックエントリをカプセル化してコントローラレイヤーで処理し、ビューレイヤーで表示できるようにする簡単な方法を利用できます。 次のステップでは、ゲストブックのエントリが処理され、ユーザーがゲストブックアプリケーションにアクセスした時に閲覧できるように、コントローラ（ポートレットクラス）を拡張します。

## アプリケーションのレンダリング方法のカスタマイズ

前述のように、アプリケーションはレンダリングと操作の、2つのポートレットフェーズを使用します。 ユーザーがアプリケーションを表示したときにゲストブックに保存済みのゲストブックエントリを表示させるには、現在その親クラス`MVCPortlet`から継承しているポートレットのレンダリング機能をカスタマイズする必要があります。

1.  配列をモデルオブジェクトの`List`に変換する次のメソッドを追加します。
   
        private List<Entry> parseEntries(String[] guestbookEntries) {
            List<Entry> entries = new ArrayList<Entry>();
       
            for (String entry : guestbookEntries) {
                String[] parts = entry.split("\\^", 2);
                Entry gbEntry = new Entry(parts[0], parts[1]);
                entries.add(gbEntry);
            }
       
            return entries;
        }

    ご覧のとおり、このメソッドは`String`配列のエントリを、キャレット（`^`）文字に基づいて2つの部分に分割します。

2.  `GuestbookPortlet`を開き、`addEntry`メソッドの下に次のメソッドを追加します。
   
        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse)
            throws PortletException, IOException {
       
            PortletPreferences prefs = renderRequest.getPreferences();
            String[] guestbookEntries = prefs.getValues("guestbook-entries", new String[1]);
       
            if (guestbookEntries[0] != null) {
                List<Entry> entries = parseEntries(guestbookEntries);
                renderRequest.setAttribute("entries", entries);
            }
       
            super.render(renderRequest, renderResponse);
        }

    このメソッドは設定からゲストブックエントリを取得し、`parsEntries`を呼び出して それを`Entry`の`List`オブジェクトに変換し、`List`を要求オブジェクトに配置します。 次に、親クラスの`render`メソッドを呼び出します。

3.  \[CTRL\]+\[SHIFT\]+O を押してインポートを整理します。

| 注: インポートを選択するよう要求された時のために、いくつかのガイドラインがあります。| | \* `aQute.bnd...`の代わりに、常に`org.osgi...`パッケージを使用します。 | | \* 一般的には`java.util...`または`javax.portlet...`パッケージを使用します。 | | \ *このプロジェクトでは`java.awt...`を使用することはありません。 | | \* それがLiferay固有の実装用である場合、または | コンセプトのカスタム実装である場合、 `com.liferay...`のみを使用します。 | |例：| | \ * `javax.portlet.Portlet`および | `com.liferay.portlet.Portlet`との間で選択肢が与えられた場合は、`javax.portlet.Portlet`を選択します。 | | \* `org.osgi.component`および | `aQute.bnd.annotation.component`の間で選択肢が与えられた場合、`org.osgi.component`を選択します。 | | \* ただし、`java.util.Map.Entry`および | `com.liferay.docs.guestbook.model.Entry`（作成したカスタムクラス） | の間で選択肢が与えられた場合、`com.liferay.docs.guestbook.model.Entry`を選択します。 | | ある時点で誤ったインポートを選択したと思われるがそれが確かではないという場合は、 | ファイルからすべてのインポートを消去し、 | \[CTRL\]+\[SHIFT\]+O を再度押すことで、誤った場所を特定できるかどうかを確認できます。

データを表示するためにコントローラーを設定したら、次はユーザーがゲストブックエントリを閲覧できるようにビューを実装します。

## ゲストブックのエントリを表示する

Liferayの開発フレームワークにより、データを簡単にループし、エンドユーザーに適切に表示できます。 これを実現するには、*Search Container*というLiferay UIコンストラクトを使用します。

1.  `</portlet:renderURL>`タグと`<aui:button-row>`タグの間にある `view.jsp`に、これらのタグを追加します。
   
        <jsp:useBean id="entries" class="java.util.ArrayList" scope="request"/>
       
        <liferay-ui:search-container>
            <liferay-ui:search-container-results results="<%= entries %>" />
       
            <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Entry"
                modelVar="entry"
            >
                <liferay-ui:search-container-column-text property="message" />
       
                <liferay-ui:search-container-column-text property="name" />
            </liferay-ui:search-container-row>
       
            <liferay-ui:search-iterator />
        </liferay-ui:search-container>

作業を保存し、アプリケーションをデプロイしたら、ゲストブックエントリを追加して見ましょう。

![図1：情報を入力するフォームがあります。](../../../images/guestbook-prototype-form.png)

![図2：送信されたエントリがここに表示されます。](../../../images/guestbook-prototype-container.png)

これでプロトタイプが完成しました。 ゲストブックのエントリを追加して保存する、作業アプリケーションが完成しました。

エントリを保存する方法は、アプリケーションでデータを保持するための最良の方法ではありません。 次に、本サービスビルダーを使用して、データベースにアプリケーションデータを保存するために必要な永続クラスとメソッドを生成します。
