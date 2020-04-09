---
header-id: adding-metadata
---

# メタデータを追加する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Guestbook Adminアプリの作成</p><p>手順2/5</p>
</div>

クラスを生成したので、それらをOSGiコンポーネントに変換する必要があります。 コンポーネントはコンテナ管理のオブジェクトであるため、ライフサイクルの管理方法を@product@のOSGiコンテナに伝えるメタデータを提供する必要があることに注意してください。

次の手順を実行します：

1.  `GuestbookPortletKeys` クラスに次のポートレットキーを追加します。
   
        public static final String GUESTBOOK_ADMIN =
          "com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet";

2.  `GuestbookAdminPortlet` クラスを開き、クラス宣言のすぐ上に `@Component` アノテーションを追加します。
   
        @Component(
            immediate = true,
            property = {
                    "com.liferay.portlet.display-category=category.hidden",
                    "com.liferay.portlet.scopeable=true",
                    "javax.portlet.display-name=Guestbooks",
                    "javax.portlet.expiration-cache=0",
                    "javax.portlet.init-param.portlet-title-based-navigation=true",
                    "javax.portlet.init-param.template-path=/",
                    "javax.portlet.init-param.view-template=/guestbookadminportlet/view.jsp",
                    "javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN,
                    "javax.portlet.resource-bundle=content.Language",
                    "javax.portlet.security-role-ref=administrator",
                    "javax.portlet.supports.mime-type=text/html",
                    "com.liferay.portlet.add-default-resource=true"
            },
            service = Portlet.class
        )

3.  \ [CTRL \] + \ [SHIFT \] + Oを押して、 `javax.portlet.Portlet` およびその他のインポートを追加し` 。</p></li>
</ol>

<p spaces-before="0">ここには新しいものがいくつかあります。 <code>javax.portlet.display-name` プロパティの値に注意してください： `Guestbooks`。 これは、[サイト]メニューに表示される名前です。 `javax.portlet.name` プロパティの値にも注意してください： `+ GuestbookPortletKeys.GUESTBOOK_ADMIN`。 これは、作成したばかりの `GUESTBOOK_ADMIN` ポートレットキーを介して、ポートレットのタイトルを指定します。

次のメタデータプロパティに特に注意してください。
   
       com.liferay.portlet.display-category=category.hidden

これは、以前にGuestbookポートレットで使用したプロパティと同じです。 そのポートレットをソーシャルカテゴリに配置しました。 値 `category.hidden` は、どこにも表示されない特別なカテゴリを指定します。 Guestメニュー管理ポートレットは、サイトメニューの一部であり、ユーザーがページに追加することを望まないため、ここに配置しています。 これは、彼らがそれをするのを防ぎます。

次に、Panelアプリクラスを構成できます。 次の手順を実行します：

1.  `GuestbookAdminPanelApp` クラスを開き、クラス宣言のすぐ上に `@Component` アノテーションを追加します。
   
        @Component(
            immediate = true,
            property = {
                "panel.app.order:Integer=300",
                "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT
            },
            service = PanelApp.class
        )

    `panel.category.key` メタデータプロパティは、ゲストメニュー管理ポートレットを製品メニューのどこに配置するかを決定します。 製品メニューは、コントロールパネル、ユーザーメニュー、およびサイト管理エリアの3つのメインセクションに分かれていることに注意してください。 `panel.category.key` プロパティの値は `PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT`であり、これはGuestbook Adminが *サイト管理* → *コンテンツ*にあることを意味します。 キーは、 `PanelCategoryKeys` class</a>によって提供され

。 `panel.app.order` 値は、リスト内のGuestbook Adminポートレットのランクを決定します。</p></li> 
   
   2  最後に、適切な名前とポートレットキーを使用するようにクラスを更新します。
  
       public class GuestbookAdminPanelApp extends BasePanelApp {
      
           @Override
           public String getPortletId() {
               return GuestbookPortletKeys.GUESTBOOK_ADMIN;
           }
      
           @Override
           @Reference(
               target = "(javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK_ADMIN + ")",
               unbind = "-"
           )
           public void setPortlet(Portlet portlet) {
               super.setPortlet(portlet);
           }
      
       }
      

3  \ [CTRL \] + \ [SHIFT \] + Oを押してインポートを整理します。 今回は、 `javax.portlet.Portlet`代わりに `com.liferay.portal.kernel.model.Portlet` インポートし 。</p></li>
</ol>

<p spaces-before="0">これで設定が完了したので、ゲストブックの追加、編集、削除というアプリの機能を自由に実装できます。 それが次のステップです。</p>
