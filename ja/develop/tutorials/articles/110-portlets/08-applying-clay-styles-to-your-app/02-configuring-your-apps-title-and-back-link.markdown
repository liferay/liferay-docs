---
header-id: configuring-your-applications-title-and-back-link
---

# アプリケーションのタイトルとバックリンクの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

管理アプリケーションの場合、タイトルをアプリの内部ビューに移動し、関連する後方リンクをポートレットのタイトルに移動する必要があります。 コントロールパネルでBlogs Adminアプリケーションを開き、新しいブログエントリを追加すると、次の動作が実行されていることがわかります。

![図1：新しいブログエントリを追加すると、上部にポートレットのタイトルと戻るリンクが表示されます。](../../../images/new-blog-entry-title.png)

この記事では、ブログ管理アプリケーションを例として使用します。 次の手順に従って、アプリのタイトルとバックURLを構成します。

1.  URLのリダイレクトを取得するには、 `ParamUtil` を使用します。

    ``` java
    String redirect = ParamUtil.getString(request, "redirect");
    ```

2.  戻るアイコンを表示し、戻るURLを `リダイレクト`設定します。

    ``` java
    portletDisplay.setShowBackIcon(true);
    portletDisplay.setURLBack(redirect);
    ```

3.  最後に、 `renderResponse.setTitle（）` メソッドを使用してタイトルを設定します。 以下の例は、2つのシナリオのタイトルを示しています。

      - 既存のブログエントリが更新されている場合、ブログのタイトルが表示されます。
      - それ以外の場合は、新しいブログエントリが作成されるため、デフォルトで *New Blog Entry* なります。
    
    <!-- end list -->
    
    ``` java
    renderResponse.setTitle((entry != null) ? entry.getTitle() : 
    LanguageUtil.get(request, "new-blog-entry"));
    %>
    ```

4.  ビューの後方リンクを更新して、 `リダイレクト`を使用し` 。 例として、ブログ管理アプリの <code>edit_entry.jsp` フォームのキャンセルボタンを示します。

    ``` java
    <aui:button cssClass="btn-lg" href="<%= redirect %>" name="cancelButton" 
    type="cancel" />
    ```

すばらしいです\！ これで、アプリのタイトルとバックURLを構成する方法がわかりました。

## 関連トピック

  - [ナビゲーションバーへの粘土パターンの適用](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-patterns-to-navigation)
  - [空の結果メッセージの設定](/docs/7-1/tutorials/-/knowledge_base/t/setting-empty-results-messages)
