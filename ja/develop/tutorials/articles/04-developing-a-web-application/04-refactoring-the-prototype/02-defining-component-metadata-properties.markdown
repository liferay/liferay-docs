---
header-id: defining-the-component-metadata-properties
---

# コンポーネントメタデータプロパティの定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">プロトタイプのリファクタリング</p><p>手順2/6</p>
</div>

ユーザーがページにアプリケーションを追加すると、ユーザーは*display categories*のリストからそれらを選択します。

![図1：ユーザーは表示カテゴリのリストからアプリケーションを選択します。](../../../images/display-categories.png)

ポートレットの表示カテゴリは、コンポーネントクラスでメタデータプロパティとして定義されます。 ゲストブックポートレットを使用すると、ユーザーは相互に通信できるため、ソーシャルカテゴリに追加します。 1つのゲストブックポートレットのみをページに追加する必要があるため、*non-instanceable*ポートレットとしても定義します。 そのようなポートレットは、そのスコープに応じて、ページまたはサイトに一度しか表示できません。

1.  `GuestbookPortlet`クラスを開き、この設定に一致するようにコンポーネントクラスのメタデータプロパティをアップデートします。
   
        @Component(
            immediate = true,
            property = {
              "com.liferay.portlet.display-category=category.social",
              "com.liferay.portlet.instanceable=false",
              "com.liferay.portlet.scopeable=true",
              "javax.portlet.display-name=Guestbook",
              "javax.portlet.expiration-cache=0",
              "javax.portlet.init-param.template-path=/",
              "javax.portlet.init-param.view-template=/guestbookwebportlet/view.jsp",
              "javax.portlet.resource-bundle=content.Language",
              "javax.portlet.security-role-ref=power-user,user",
              "javax.portlet.supports.mime-type=text/html"
            },
            service = Portlet.class
        )

`com.liferay.portlet.display-category=category.social`プロパティは、ゲストブックポートレットの表示カテゴリを*Social*に設定します。 `com.liferay.portlet.instanceable=false`プロパティは、ゲストブックポートレットがインスタンス化できないことを指定しています。そのため、ページに追加できるポートレットのインスタンスは1つのみとなります。 プロパティ`javax.portlet.init-param.view-template`で、メイン`view.jsp`のロケーションを、`/ guestbookwebportlet`でその新しいロケーションにアップデートします。

ポートレットのメタデータを編集したため、続行する前にページからポートレットを削除して再追加する必要があります。

1.  Webブラウザーで`localhost:8080`へ進みます。

2.  管理アカウントにログインします。

3.  ゲストブックポートレットのページにエラーが表示されるようになりました。 ポートレットメニュー（ポートレットの右上）をクリックし*Remove*を選択したら、*OK*をクリックして確認します。

4.  *Add*メニューを開き、*Applications*を選択します。

5.  *Social*カテゴリを開き、*Guestbook*アプリケーションをページにドラッグアンドドロップします。

これで、ゲストブックポートレットが適切なカテゴリに表示されます。 以前はページに追加できましたが、ユーザーエクスペリエンスは向上しています。
