---
header-id: configuring-jsp-templates-for-an-asset-renderer
---

# アセットレンダラー用のJSPテンプレートの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アセットは、Asset Publisherでいくつかの異なる方法で表示できます。 `AssetRenderer` インターフェースによって提供される実装する3つのテンプレートがあります。

  - `abstract`
  - `full_content`
  - `preview`

これらのサポートされているテンプレートに加えて、アセットへの直接アクセスと操作を提供するボタンのJSPを作成することもできます。 例えば、

  - 編集
  - 表示する
  - コンテキストで表示

`BlogsEntryAssetRenderer` は、 `AssetRenderer`提供するJSPテンプレートをカスタマイズし、JSPを使用して他のいくつかの機能を追加します。 JSPテンプレート開発要件を満たすために、ブログ資産レンダラーがどのように組み立てられるかを調べます。

1.  `getJspPath（...）` メソッドをアセットレンダラーに追加します。 このメソッドは、Asset Publisher内でレンダリングされるJSPへのパスを返す必要があります。 これは、 `BlogsEntryAssetRenderer` がこのメソッドを使用する方法です。
   
        @Override
        public String getJspPath(HttpServletRequest request, String template) {
            if (template.equals(TEMPLATE_ABSTRACT) ||
                template.equals(TEMPLATE_FULL_CONTENT)) {
       
                return "/blogs/asset/" + template + ".jsp";
            }
            else {
                return null;
            }
        }

    ブログアセットは、 `abstract.jsp` および `個のfull_content.jsp` テンプレートを提供します。 これは、ブログ資産がブログの抽象的な説明またはブログの完全なコンテンツをAsset Publisherでレンダリングできることを意味します。 これらのテンプレートは、 `com.liferay.blogs.web` モジュールの `src / main / resources / META-INF / resources / blogs / asset` フォルダーにあります。 このメソッドに使用するJSPテンプレート用に同様のフォルダーを作成できます。 `AssetRenderer` インターフェイスによって提供されるもう</code>つのテンプレート、 `preview.jsp`は、ブログのアセットレンダラーによってカスタマイズされないため、デフォルトのテンプレートが実装されます。

    アセットの全コンテンツを表示するには、リンクを作成する必要があります。 これは後で行います。

2.  JSPにパスを追加したら、そのJSPを含める必要があります。 `BlogsEntryAssetRenderer` クラスは `BaseJSPAssetRenderer`拡張するため、特定のJSPをレンダリングする `include（...）` メソッドが既にあります。 ブログのビューで使用するリクエストの属性を設定するには、このメソッドをオーバーライドする必要があります。
   
        @Override
        public boolean include(
                HttpServletRequest request, HttpServletResponse response,
                String template)
            throws Exception {
       
            request.setAttribute(WebKeys.BLOGS_ENTRY, _entry);
       
            return super.include(request, response, template);
        }

    この属性には、ブログエントリオブジェクトが含まれます。 この方法でブログオブジェクトを追加することは必須ではありません。ビューからブログエントリを直接取得できます。 ただし、 `include（...）` メソッドを使用すると、MVCポートレットのベストプラクティスに従います。

    ![図1：抽象的コンテンツビューと完全なコンテンツビューは、ブログでは異なる方法でレンダリングされます。](../../../images/blogs-asset-views.png)

すごい！ Asset PublisherでサポートされているJSPをアセットに適用する方法を学習しました。 ただし、JSPテンプレートでできることはそれだけではありません。 アセットレンダラーフレームワークには、アセットに便利なボタンをレンダリングできる他のメソッドがいくつか用意されています。

1.  ブログアセットには、アセットを編集できる[編集]ボタンがあります。 `BlogsEntryAssetRenderer` クラスに次のメソッドを追加して、これを提供します。
   
        @Override
        public PortletURL getURLEdit(
                LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse)
            throws Exception {
       
            Group group = GroupLocalServiceUtil.fetchGroup(_entry.getGroupId());
       
            PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
                liferayPortletRequest, group, BlogsPortletKeys.BLOGS, 0, 0,
                PortletRequest.RENDER_PHASE);
       
            portletURL.setParameter("mvcRenderCommandName", "/blogs/edit_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));
       
            return portletURL;
        }

    アセットパブリッシャーは、ブログアプリケーションを使用してブログアセットをロードします。 次に、 `edit_entry.jsp` テンプレートは、編集UIのHTMLを生成します。 アセットに必要な編集を行ったら、Asset Publisherから保存できます。 かなりクールですよね？

2.  `BlogsEntryAssetRenderer` クラスで以下に概説するメソッドと同様のメソッドを提供することにより、アセットの表示方法を指定できます。
   
        @Override
        public String getURLView(
                LiferayPortletResponse liferayPortletResponse,
                WindowState windowState)
            throws Exception {
       
            AssetRendererFactory<BlogsEntry> assetRendererFactory =
                getAssetRendererFactory();
       
            PortletURL portletURL = assetRendererFactory.getURLView(
                liferayPortletResponse, windowState);
       
            portletURL.setParameter("mvcRenderCommandName", "/blogs/view_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));
            portletURL.setWindowState(windowState);
       
            return portletURL.toString();
        }
       
        @Override
        public String getURLViewInContext(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse,
            String noSuchEntryRedirect) {
       
            return getURLViewInContext(
                liferayPortletRequest, noSuchEntryRedirect, "/blogs/find_entry",
                "entryId", _entry.getEntryId());
        }

    `getURLView（...）` メソッドは、Asset Publisherのアセットの全コンテンツを表示するURLを生成します。 これは、クリック可能なアセット名に割り当てられます。 `getURLViewInContext（...）` メソッドは、アセット名に割り当てられた同様のURLを提供しますが、URLはアセットの元のコンテキストにリダイレクトします（ブログアプリケーションでブログアセットを表示するなど）。 Asset Publisherの *オプション* → *構成* → *セットアップ* → *表示設定* セクションに移動し、 *アセットコンテンツの動作ドロップのコンテキスト* でコンテキスト全体の表示</em> と *を選択して、レンダリングするビューを決定します-ダウンメニュー。</p></li> </ol>

Blogsアプリケーションは、 `AssetRenderer` インターフェースによって提供されるテンプレートをオーバーライドする、 `抽象` および `full_content` JSPテンプレートを提供します。 3番目のテンプレート `preview`もカスタマイズできます。 *Add* → *Content* メニューでレンダリングされたデフォルトの `preview.jsp` テンプレートを表示できます。

![図2： <code>プレビュー</code> テンプレートは、[追加]メニューの[コンテンツ]セクションにアセットのプレビューを表示します。](../../../images/preview-template-asset-renderer.png)

`AssetRenderer`提供するテンプレートを実装し、ニーズに合わせてカスタマイズすることについてすべて学習しました。 次に、ファクトリを作成して、アセットレンダラーを実行します。
