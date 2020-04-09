---
header-id: creating-portlet-keys
---

# ポートレットキーの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">プロトタイプのリファクタリング</p><p>手順3/6</p>
</div>

`PortletKeys`は1つの場所で、ポートレット名や、その他の反復可能な一般的に使用される変数などの重要なものを管理します。 このようにして、ポートレットの名前を変更する必要がある場合、1か所でそれを実行し、それを必要とするすべてのクラスで参照できます。 キーは最初にコンポーネントプロパティとして、次にクラスとして参照する必要があります。

次の手順に従って、アプリケーションの`PortletKeys`を作成します。

1.  `guestbook-web`モジュールで、 `GuestbookPortlet`クラスを開き、1つの新しいプロパティを追加してコンポーネントクラスのメタデータプロパティを更新します。
   
        "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,

    リストの中央にプロパティを追加した場合、末尾のコンマが必要であることに注意してください。 最後の末尾に追加した場合は、そのままにしておきます（ただし、前のプロパティに末尾のコンマを追加してください\!）。

2.  `GuestbookPortlet`を保存します。 クラスにキーを追加していないため、エラーが表示されます。

3.  `com.liferay.docs.guestbook.constants`パッケージを開きます。

4.  `GuestbookPortletKeys`を開き、`com_liferay_docs_guestbook_portlet_GuestbookPortlet`の値で、`GUESTBOOK`と呼ばれるパブリックで静的な最終文字列を作成します。
   
        public static final String GUESTBOOK =
                 "com_liferay_docs_guestbook_portlet_GuestbookPortlet";

5.  ファイルを保存します。

これで`GuestbookPortlet`のエラーがなくなり、アプリケーションを再度デプロイできるようになりました。

次に、Service Builderで生成した新しいバックエンドとアプリケーションを統合します。
