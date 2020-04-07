---
header-id: implementing-a-guestbook-asset-renderer
---

# ゲストブックアセットレンダラーの実装

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">アセットレンダラーの実装</p><p>手順1/2</p>
</div>

@product@のアセットレンダラーはファクトリパターンに従うため、 `GuestbookAssetRenderer`のプライベートゲストブックオブジェクトをインスタンス化する `GuestbookAssetRendererFactory` を作成する必要があります。 ここでは、両方のクラスを作成します。

最初にAsset Rendererクラスを作成します。

## AssetRendererクラスの作成

`GuestbookAssetRenderer` クラスを作成するには、次の手順に従います。

1.  `guestbook-web` モジュールの `src / main / java` フォルダーに `com.liferay.docs.guestbook.web.internal.asset` という新しいパッケージを作成します。 このパッケージでは、@product@の `BaseJSPAssetRenderer` クラスを拡張する `GuestbookAssetRenderer` クラスを作成します。 このクラスを拡張すると、 `AssetRenderer` インターフェースを実装するためのスタート地点が得られます。
   
        public class GuestbookAssetRenderer extends BaseJSPAssetRenderer<Guestbook> {
       
        }

2.  コンストラクター、guestbookクラス変数、およびアクセス許可モデルリソースを追加します。 このクラスのメソッドのほとんどは、プライベート `_guestbook` オブジェクトからフィールドを返すゲッターです。 パーミッションチェックを必要とするメソッドは `_guestbookModelResourcePermission`使用します：
   
        public GuestbookAssetRenderer(Guestbook guestbook, ModelResourcePermission<Guestbook> modelResourcePermission) {
       
                    _guestbook = guestbook;
                    _guestbookModelResourcePermission = modelResourcePermission;
        }
       
        private Guestbook _guestbook;
        private final ModelResourcePermission<Guestbook> _guestbookModelResourcePermission;   
        private Logger logger = Logger.getLogger(this.getClass().getName());

3.  拡張する `BaseJSPAssetRenderer` 抽象クラスには、 `hasEditPermission` および `hasViewPermission` メソッドのダミー実装が含まれています。これらのメソッドは、以前に作成した権限リソースを使用して実際の権限チェックでオーバーライドする必要があります。
   
        @Override
        public boolean hasEditPermission(PermissionChecker permissionChecker) 
        {
            try {
                return _guestbookModelResourcePermission.contains(
                    permissionChecker, _guestbook, ActionKeys.UPDATE);
            }
            catch (Exception e) {
            }
       
            return false;
        }
       
        @Override
        public boolean hasViewPermission(PermissionChecker permissionChecker) 
        {
            try {
                return _guestbookModelResourcePermission.contains(
                    permissionChecker, _guestbook, ActionKeys.VIEW);
            }
            catch (Exception e) {
            }
       
            return true;
        }

4.  次のゲッターメソッドを追加して、ゲストブックアセットに関する情報を取得します。
   
        @Override
        public Guestbook getAssetObject() {
          return _guestbook;
        }
       
        @Override
        public long getGroupId() {
          return _guestbook.getGroupId();
        }
       
        @Override
        public long getUserId() {
       
          return _guestbook.getUserId();
        }
       
        @Override
        public String getUserName() {
          return _guestbook.getUserName();
        }
       
        @Override
        public String getUuid() {
          return _guestbook.getUuid();
        }
       
        @Override
        public String getClassName() {
          return Guestbook.class.getName();
        }
       
        @Override
        public long getClassPK() {
          return _guestbook.getGuestbookId();
        }
       
        @Override
        public String getSummary(PortletRequest portletRequest, PortletResponse 
          portletResponse) {
            return "Name: " + _guestbook.getName();
        }
       
        @Override
        public String getTitle(Locale locale) {
          return _guestbook.getName();
        }
       
        @Override
        public boolean include(HttpServletRequest request, HttpServletResponse 
          response, String template) throws Exception {
            request.setAttribute("GUESTBOOK", _guestbook);
            request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
            request.setAttribute("StringUtil", new StringUtil());
            return super.include(request, response, template);
        }

    最後のメソッドは、 `HttpServletRequest` オブジェクトでいくつかのユーティリティと `Guestbook` エンティティを利用可能にします。

5.  `getJspPath` メソッドをオーバーライドし` 。 ゲストブックアセットをレンダリングするJSPへのパスを表す文字列を返します。 Asset Publisherがアセットの完全なコンテンツを表示すると、アセットレンダラークラスの <code>getJspPath` メソッドを呼び出し、 `"full_content"`等しい `テンプレート` 文字列パラメーターを渡します。 `full_content` テンプレート文字列がパラメーターとして渡されると、これは `/asset/guestbook/full_content.jsp` 返します。 後でアプリケーションのユーザーインターフェイスを更新するときに、このJSPを作成します。

    ``` 
      @Override
      public String getJspPath(HttpServletRequest request, String template) {

        if (template.equals(TEMPLATE_FULL_CONTENT)) {
          request.setAttribute("gb_guestbook", _guestbook);

          return "/asset/guestbook/" + template + ".jsp";
        } else {
          return null;
        }
      }
    ```

6.  `getURLEdit` メソッドをオーバーライドし` 。 このメソッドは、アセットを編集するためのURLを返します。
<pre><code>      @Override
      public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
          LiferayPortletResponse liferayPortletResponse) throws Exception {
        PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
            getControlPanelPlid(liferayPortletRequest), GuestbookPortletKeys.GUESTBOOK,
            PortletRequest.RENDER_PHASE);
        portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
        portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));
        portletURL.setParameter("showback", Boolean.FALSE.toString());

        return portletURL;
      }
`</pre>

7.  `getURLViewInContext` メソッドをオーバーライドし` 。 このメソッドは、ネイティブアプリケーションでアセットを表示するURLを返します。
<pre><code>      @Override
      public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
          LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
        try {
          long plid = PortalUtil.getPlidFromPortletId(_guestbook.getGroupId(),
              GuestbookPortletKeys.GUESTBOOK);

          PortletURL portletURL;
          if (plid == LayoutConstants.DEFAULT_PLID) {
            portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(liferayPortletRequest),
                GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
          } else {
            portletURL = PortletURLFactoryUtil.create(liferayPortletRequest,
                GuestbookPortletKeys.GUESTBOOK, plid, PortletRequest.RENDER_PHASE);
          }

          portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/view");
          portletURL.setParameter("guestbookId", String.valueOf(_guestbook.getGuestbookId()));

          String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

          portletURL.setParameter("redirect", currentUrl);

          return portletURL.toString();

        } catch (PortalException e) {

            logger.log(Level.SEVERE, e.getMessage());

        } catch (SystemException e) {

            logger.log(Level.SEVERE, e.getMessage());

        }

        return noSuchEntryRedirect;
      }
`</pre>

8.  `getURLView` メソッドをオーバーライドし` 。 このメソッドは、Asset Publisher内からアセットを表示するURLを返します。
<pre><code>      @Override
      public String getURLView(LiferayPortletResponse liferayPortletResponse, 
      WindowState windowState) throws Exception {

        return super.getURLView(liferayPortletResponse, windowState);
      }
`</pre>

9.  インポートを整理し（Ctrl-Shift-O）、ファイルを保存します。 Liferayのカーネルで非推奨のものを避けるために、プロンプトが出されたら `com.liferay.petra。*` ライブラリを選択します。 ロギングの場合、 `java.util.logging.Logger` および `java.util.logging.Level`選択します。

次に、 `AssetRendererFactory` クラスを作成できます。

## GuestbookAssetRendererFactoryクラスの作成

`GuestbookAssetRendererFactory`を作成するには、次の手順に従います。

1.  `com.liferay.docs.guestbook.web.internal.asset` パッケージで、@product@の `BaseAssetRendererFactory` クラスを拡張する `GuestbookAssetRendererFactory` というクラスを作成し、生成されたコンストラクターとクラス変数をこれで上書きします。
   
        @Component(immediate = true, 
          property = {"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK}, 
          service = AssetRendererFactory.class
          )
        public class GuestbookAssetRendererFactory extends 
          BaseAssetRendererFactory<Guestbook> {
       
          public GuestbookAssetRendererFactory() {
            setClassName(CLASS_NAME);
            setLinkable(_LINKABLE);
            setPortletId(GuestbookPortletKeys.GUESTBOOK); setSearchable(true);
            setSelectable(true); 
          }         
       
          private ServletContext _servletContext;
          private GuestbookLocalService _guestbookLocalService;
          private static final boolean _LINKABLE = true;
          public static final String CLASS_NAME = Guestbook.class.getName();
          public static final String TYPE = "guestbook";
          private Logger logger = Logger.getLogger(this.getClass().getName());
          private ModelResourcePermission<Guestbook>
          _guestbookModelResourcePermission;

    このコードには、クラス宣言、コンストラクター、およびクラス変数が含まれています。 `AssetRenderer` を作成するクラス名、ポートレットID、および `true` ブール値（`_LINKABLE`）を設定します。 ブール値は、生成された `AssetRenderer`URLを提供する実装メソッドを示します。

2.  `getAssetRenderer` メソッドを実装し`このメソッドは、特定のゲストブック用に新しい <code>GuestbookAssetRenderer` インスタンスを構築します。 `classPK` （プライマリキー）パラメーターを使用して、データベースからゲストブックを取得します。 次に、 `GuestbookAssetRenderer`のコンストラクターを呼び出し、取得したゲストブックと権限リソースモデルを引数として渡します。
   
        @Override
        public AssetRenderer<Guestbook> getAssetRenderer(long classPK, int type) 
        throws PortalException {
       
          Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
       
          GuestbookAssetRenderer guestbookAssetRenderer = 
          new GuestbookAssetRenderer(guestbook, _guestbookModelResourcePermission);
       
          guestbookAssetRenderer.setAssetRendererType(type);
          guestbookAssetRenderer.setServletContext(_servletContext);
       
          return guestbookAssetRenderer;
        }

3.  `AssetRendererFactory` インターフェースを実装する抽象クラスである `BaseAssetRendererFactory`拡張しています。 カスタムアセットが正しいエンティティに関連付けられていることを確認するには、各アセットレンダラーファクトリーが（特に） `getClassName` および `getType` メソッドを実装する必要があります。

    ``` 
      @Override
      public String getClassName() {
        return CLASS_NAME;
      }

      @Override
      public String getType() {
        return TYPE;
      }
    ```

4.  `GuestbookPermission` クラスを介して `hasPermission` メソッドを実装し` 。</p>

<pre><code> @Override
 public boolean hasPermission(PermissionChecker permissionChecker, 
 long classPK, String actionId) throws Exception {

   Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
   long groupId = guestbook.getGroupId();
   return GuestbookPermission.contains(permissionChecker, groupId, 
   actionId);
 }
`</pre></li>

5

残りのコードを追加して、アセットのポートレットURLを作成し、リンク可能かどうかを指定します。

    ``` 
          @Override
          public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
              LiferayPortletResponse liferayPortletResponse, long classTypeId) {
            PortletURL portletURL = null;

            try {
              ThemeDisplay themeDisplay = (ThemeDisplay) 
              liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

              portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
                  GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
              portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_guestbook");
              portletURL.setParameter("showback", Boolean.FALSE.toString());

              } catch (PortalException e) {

                    logger.log(Level.SEVERE, e.getMessage()); 

              }

            return portletURL;
          }

          @Override
          public boolean isLinkable() {
            return _LINKABLE;
          }

          @Override
          public String getIconCssClass() {
              return "bookmarks";
          }

        @Reference(target = "(osgi.web.symbolicname=com.liferay.docs.guestbook.portlet)",
                unbind = "-")
        public void setServletContext(ServletContext servletContext) {
                _servletContext = servletContext;
            }

        @Reference(unbind = "-")
            protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
                _guestbookLocalService = guestbookLocalService; 
        }
    }
    ```

6

インポートを整理します（Ctrl-Shift-O）。 プロンプトが表示されたら `org.osgi` パッケージ（ `a.Qute`はありません）を選択し、ファイルを保存します。</ol>

すばらしいです\！ ゲストブックアセットレンダラーが完成しました。 次に、エントリアセットレンダラーを作成します。
