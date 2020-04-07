---
header-id: creating-a-factory-for-the-asset-renderer
---

# アセットレンダラーのファクトリを作成する

[TOC levels=1-4]

アセットレンダラーは正常に作成されましたが、ファクトリクラスを作成して、各アセットインスタンスのアセットレンダラーを生成する必要があります。 たとえば、ブログアセットレンダラーファクトリは、アセットパブリッシャーに表示される各ブログアセットに対して `BlogsEntryAssetRenderer` をインスタンス化します。

ブログアセットレンダラーファクトリを作成して、ブログアセットレンダラーの例を続けます。

1.  アセットレンダラークラスと同じフォルダーに `-AssetRenderFactory` クラスを作成します。 ブログの場合、 [`BlogsEntryAssetRendererFactory`](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/asset/BlogsEntryAssetRendererFactory.java) クラスは、 `com.liferay.blogs.web` モジュールの `com.liferay.blogs.web.asset` パッケージにあります。 ファクトリクラスは、 `BaseAssetRendererFactory` クラスを拡張し、アセットタイプはそのパラメーターとして指定する必要があります。 以下の `BlogsEntryAssetRendererFactory` クラスでこれがどのように行われたかを見ることができます。
   
        public class BlogsEntryAssetRendererFactory
            extends BaseAssetRendererFactory<BlogsEntry> {

2.  クラス宣言の上に `@Component` 注釈セクションを作成します。 このアノテーションは、アセットのファクトリインスタンスを登録する役割を果たします。
   
        @Component(
            immediate = true,
            property = {"javax.portlet.name=" + BlogsPortletKeys.BLOGS},
            service = AssetRendererFactory.class
        )
        public class BlogsEntryAssetRendererFactory
            extends BaseAssetRendererFactory<BlogsEntry> {

    設定すべき注釈要素がいくつかあります。

      - `イミディエート` 要素は、モジュールの起動時にファクトリが@product@で起動するように指示します。
      - `property` 要素は、アセットに関連付けられているポートレットを設定します。 これはブログアセットレンダラーファクトリであるため、ブログポートレットが指定されています。
      - `サービス` 要素は、 `AssetRendererFactory.class` インターフェイスを指す必要があります。

    | **注：** @product@の以前のバージョンでは、アセットを登録する必要がありました|ポートレットの `liferay-portlet.xml` ファイルのレンダラーファクトリ。 登録|プロセスは、OSGiが `@Component` |を使用して自動的に完了するようになりました。注釈。

3.  ファクトリのプライベート属性を事前設定するファクトリクラスのコンストラクタを作成します。

        public BlogsEntryAssetRendererFactory（）{ setClassName（BlogsEntry.class.getName（））; setCategorizable（true）; setLinkable（true）; setPortletId（BlogsPortletKeys.BLOGS）; setSearchable（true）; setSelectable（true）; }

      - *リンク可能*：他のアセットは、ブログアセットを関連アセットとして選択できます。
      - *categorizable*：ブログを使用して、語彙の範囲をカテゴリ管理から区切ることができます。
      - *検索可能*：アセットを検索するとブログが見つかります。
      - *選択可能*：アセットパブリッシャーに表示するアセットを選択するときにブログを選択できます。

    クラス名とポートレットIDを設定すると、アセットレンダラーファクトリがエンティティにリンクされます。

4.  アセットのアセットレンダラーを作成します。 これは、コンストラクターを呼び出すことによって行われます。
   
        @Override
        public AssetRenderer<BlogsEntry> getAssetRenderer(long classPK, int type)
            throws PortalException {
       
            BlogsEntry entry = _blogsEntryLocalService.getEntry(classPK);
       
            BlogsEntryAssetRenderer blogsEntryAssetRenderer =
                new BlogsEntryAssetRenderer(entry, _resourceBundleLoader);
       
            blogsEntryAssetRenderer.setAssetRendererType(type);
            blogsEntryAssetRenderer.setServletContext(_servletContext);
       
            return blogsEntryAssetRenderer;
        }

    ブログの場合、アセットはブログアプリケーションのローカルサービスを呼び出すことで取得されます。 次に、ブログアセットとリソースバンドルローダーを使用してアセットレンダラーがインスタンス化されます。 次に、アセットレンダラーのタイプとサーブレットコンテキストが設定されます。 最後に、構成されたアセットレンダラーが返されます。

    `getAssetRenderer（...）` メソッドには、作成する必要のある変数がいくつかあります。 これらの変数を設定し、次に何をしているのかを学習します。

    a。 Blogsアプリケーションのローカルサービスを呼び出してエントリを取得する必要があります。 プライベートフィールドを作成し、setterメソッドを使用して設定することにより、このサービスをインスタンス化できます。
   
        @Reference(unbind = "-")
        protected void setBlogsEntryLocalService(
            BlogsEntryLocalService blogsEntryLocalService) {
       
            _blogsEntryLocalService = blogsEntryLocalService;
        }
       
        private BlogsEntryLocalService _blogsEntryLocalService;

    セッターメソッドには、 `@Reference` タグで注釈が付けられます。 詳細については、 [Invoking Local Services](/docs/7-0/tutorials/-/knowledge_base/t/invoking-local-services) チュートリアルをご覧ください。

    b。 `BlogsEntryAssetRenderer`のコンストラクターで指定されているため、リソースバンドルローダーを指定する必要があります。
   
        @Reference(
            target = "(bundle.symbolic.name=com.liferay.blogs.web)", unbind = "-"
        )
        public void setResourceBundleLoader(
            ResourceBundleLoader resourceBundleLoader) {
       
            _resourceBundleLoader = resourceBundleLoader;
        }
       
        private ResourceBundleLoader _resourceBundleLoader;

    `@Reference` アノテーションの `ターゲット` プロパティの `osgi.web.symbolicname` が、ファクトリモジュールの `bnd.bnd` ファイルで定義された `Bundle-SymbolicName` と同じ値に設定されていることを確認し`に住んでいます。</p>

<p spaces-before="4">c。 アセットレンダラー <code>タイプ` 整数がアセットレンダラーに設定されていますが、なぜ整数ですか？ それは、最新表示する必要がある時に差別化するニーズ@製品@ *承認された* 、資産のバージョン、または最新バージョンを、それは未承認だ場合でも（例えば、承認されていないバージョンがワークフローの資産の審査のために表示されます）。 これらの状況では、アセットレンダラーファクトリは次のいずれかを受け取る必要があります

      - 資産の最新バージョンの場合は`0`
      - 資産の最新の承認済みバージョンの場合は`1`

    d。 Blogsアプリケーションは独自のJSPを提供するため、サーブレットコンテキストの参照をアセットレンダラーに渡す必要があります。 アセットレンダラーでカスタムJSPを使用する場合、これは常に必要です。

        @Reference（ target = "（osgi.web.symbolicname = com.liferay.blogs.web）"、unbind = "-" ） public void setServletContext（ServletContext servletContext）{ _servletContext = servletContext; }

        private ServletContext _servletContext;

5.  アセットファクトリが関連付けるアセットのタイプを設定し、そのタイプを取得するゲッターメソッドを提供します。 また、ブログのエントリクラス名を取得する別のゲッターを提供します。これは必須です。

        public static final String TYPE = "blog";

        @Override public String getType（）{ return TYPE; }

        @Override public String getClassName（）{ return BlogsEntry.class.getName（）; }

6.  アセットのレキシコンアイコンを設定します。

        @Override public String getIconCssClass（）{ return "blogs"; }

    <https://liferay.github.io/clay/content/icons-lexicon/>で利用可能なすべてのレキシコンアイコンのリストを見つけることができます。

7.  アセットを追加および表示するURLを生成するメソッドを追加します。

        @Override public PortletURL getURLAdd（ LiferayPortletRequest liferayPortletRequest、 LiferayPortletResponse liferayPortletResponse、long classTypeId）{
   
          PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
              liferayPortletRequest, getGroup(liferayPortletRequest),
              BlogsPortletKeys.BLOGS, 0, 0, PortletRequest.RENDER_PHASE);
       
          portletURL.setParameter("mvcRenderCommandName", "/blogs/edit_entry");
       
          return portletURL;
        }

        @Override public PortletURL getURLView（ LiferayPortletResponse liferayPortletResponse、 WindowState windowState）{
   
          LiferayPortletURL liferayPortletURL =
              liferayPortletResponse.createLiferayPortletURL(
                  BlogsPortletKeys.BLOGS, PortletRequest.RENDER_PHASE);
       
          try {
              liferayPortletURL.setWindowState(windowState);
          }
          catch (WindowStateException wse) {
          }
       
          return liferayPortletURL;
        }

    細心の注意を払っている場合は、 `getURLView（...）` メソッドが `BlogsEntryAssetRenderer` クラスにも実装されていることに気付いたかもしれません。 アセットレンダラーの `getURLView（...）` メソッドは特定のアセットインスタンスのURLを作成しますが、ファクトリはメソッドを使用して、アセットを管理するアプリケーション（ブログアプリケーションなど）のみを指す汎用URLを作成します。

8.  すべてのブログアセットのグローバルアクセス許可を設定します。

        @Override public boolean hasAddPermission（ PermissionChecker permissionChecker、long groupId、long classTypeId） throws Exception {
   
          return BlogsPermission.contains(
              permissionChecker, groupId, ActionKeys.ADD_ENTRY);
        }

        @Override public boolean hasPermission（ PermissionChecker permissionChecker、long classPK、String actionId） throws Exception {
   
          return BlogsEntryPermission.contains(
              permissionChecker, classPK, actionId);
        }

すばらしいです\！ Blogsアプリケーションのアセットレンダラーファクトリの作成が完了しました！ これで、資産レンダラーを実装し、factory \！を使用して各資産インスタンスの資産レンダラーを生成する知識が得られました。
