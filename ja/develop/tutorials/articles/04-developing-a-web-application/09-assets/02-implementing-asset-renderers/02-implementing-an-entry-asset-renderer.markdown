---
header-id: implementing-an-entry-asset-renderer
---

# エントリーアセットレンダラーの実装

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">アセットレンダラーの実装</p><p>手順2/2</p>
</div>

ここで作成するクラスは、前の手順でゲストブック用に作成した `GuestbookAssetRenderer` および `GuestbookAssetRendererFactory` クラスとほぼ同じです。 この手順は、ゲストブックエントリに必要なコードを提供します。 前のセクションを参照して、このコードの仕組みを確認してください。

## EntryAssetRendererクラスの作成

`com.liferay.docs.guestbook.web.internal.asset` パッケージで、@product@の `BaseJSPAssetRenderer` クラスを拡張する `EntryAssetRenderer` クラスを作成します。 `EntryAssetRenderer` クラスの内容を次のコードに置き換えます。

    package com.liferay.docs.guestbook.web.internal.asset;
    
    import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.model.LayoutConstants;
    import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
    import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
    import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
    import com.liferay.portal.kernel.security.permission.ActionKeys;
    import com.liferay.portal.kernel.security.permission.PermissionChecker;
    import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
    import com.liferay.portal.kernel.util.HtmlUtil;
    import com.liferay.portal.kernel.util.PortalUtil;
    import com.liferay.petra.string.StringUtil;
    import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.model.Entry;
    import java.util.Locale;
    import javax.portlet.PortletRequest;
    import javax.portlet.PortletResponse;
    import javax.portlet.PortletURL;
    import javax.portlet.WindowState;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    
    public class EntryAssetRenderer extends BaseJSPAssetRenderer<Entry> {
    
        public EntryAssetRenderer(Entry entry, ModelResourcePermission<Entry> modelResourcePermission) {
    
            _entry = entry;
            _entryModelResourcePermission = modelResourcePermission;
        }
    
        @Override
        public boolean hasViewPermission(PermissionChecker permissionChecker) 
        {
            try {
                return _entryModelResourcePermission.contains(
                        permissionChecker, _entry, ActionKeys.VIEW);
            }
            catch (Exception e) {
            }
    
            return true;
        }
    
        @Override
        public Entry getAssetObject() {
            return _entry;
        }
    
        @Override
        public long getGroupId() {
            return _entry.getGroupId();
        }
    
        @Override
        public long getUserId() {
    
            return _entry.getUserId();
        }
    
        @Override
        public String getUserName() {
            return _entry.getUserName();
        }
    
        @Override
        public String getUuid() {
            return _entry.getUuid();
        }
    
        @Override
        public String getClassName() {
            return Entry.class.getName();
        }
    
        @Override
        public long getClassPK() {
            return _entry.getEntryId();
        }
    
        @Override
        public String getSummary(PortletRequest portletRequest, 
                PortletResponse portletResponse) {
            return "Name: " + _entry.getName() + ". Message: " + _entry.getMessage();
        }
    
        @Override
        public String getTitle(Locale locale) {
            return _entry.getMessage();
        }
    
        @Override
        public boolean include(HttpServletRequest request, 
                HttpServletResponse response, String template) throws Exception {
            request.setAttribute("ENTRY", _entry);
            request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
            request.setAttribute("StringUtil", new StringUtil());
            return super.include(request, response, template);
        }
    
        @Override
        public String getJspPath(HttpServletRequest request, String template) {
    
            if (template.equals(TEMPLATE_FULL_CONTENT)) {
                request.setAttribute("gb_entry", _entry);
    
                return "/asset/entry/" + template + ".jsp";
            } else {
                return null;
            }
        }
    
        @Override
        public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse) throws Exception {
            PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
                    getControlPanelPlid(liferayPortletRequest), GuestbookPortletKeys.GUESTBOOK,
                    PortletRequest.RENDER_PHASE);
            portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_entry");
            portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));
            portletURL.setParameter("showback", Boolean.FALSE.toString());
    
            return portletURL;
        }
    
        @Override
        public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) 
                        throws Exception {
            try {
                long plid = PortalUtil.getPlidFromPortletId(_entry.getGroupId(),
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
                portletURL.setParameter("entryId", String.valueOf(_entry.getEntryId()));
    
                String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);
    
                portletURL.setParameter("redirect", currentUrl);
    
                return portletURL.toString();
    
            } catch (PortalException e) {
    
            } catch (SystemException e) {
            }
    
            return noSuchEntryRedirect;
        }
    
        @Override
        public String getURLView(LiferayPortletResponse liferayPortletResponse, 
                WindowState windowState) throws Exception {
    
            return super.getURLView(liferayPortletResponse, windowState);
        }
    
        @Override
        public boolean isPrintable() {
            return true;
        }
        private final ModelResourcePermission<Entry> _entryModelResourcePermission;
        private Entry _entry;
    }

このクラスは、 `GuestbookAssetRenderer` クラスに似てい` 。 <code>EntryAssetRenderer.getSummary` メソッドの場合、エントリ名（エントリを作成したユーザーの名前）とエントリメッセージを表示するサマリーを返します。

`GuestbookAssetRenderer.getSummary` は、ゲストブック名を表示するサマリーを返します。 `EntryAssetRenderer.getTitle` は、エントリメッセージを返します。 `GuestbookAssetRenderer.getTitle` はゲストブック名を返します。 `EntryAssetRenderer` の他のメソッドは、 `GuestbookAssetRenderer`のメソッドとほぼ同じです。

## EntryAssetRendererFactoryクラスの作成

次に、ゲストブックエントリアセットレンダラーのファクトリクラスを作成する必要があります。 `com.liferay.docs.guestbook.web.internal.asset` パッケージで、@product@の `BaseAssetRendererFactory` クラスを拡張する `EntryAssetRendererFactory` というクラスを作成します。 その内容を次のコードに置き換えます。

    package com.liferay.docs.guestbook.web.internal.asset;
    
    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
    import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.EntryLocalService;
    import com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookEntryPermission;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
    import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
    import com.liferay.portal.kernel.portlet.LiferayPortletURL;
    import com.liferay.portal.kernel.security.permission.PermissionChecker;
    import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
    import com.liferay.portal.kernel.theme.ThemeDisplay;
    import com.liferay.portal.kernel.util.WebKeys;
    
    import javax.portlet.PortletRequest;
    import javax.portlet.PortletURL;
    import javax.portlet.WindowState;
    import javax.portlet.WindowStateException;
    import javax.servlet.ServletContext;
    
    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    
    @Component(
            immediate = true,
            property = {"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK},
            service = AssetRendererFactory.class
            )
    public class EntryAssetRendererFactory extends BaseAssetRendererFactory<Entry> {
    
        public EntryAssetRendererFactory() {
            setClassName(CLASS_NAME);
            setLinkable(_LINKABLE);
            setPortletId(GuestbookPortletKeys.GUESTBOOK);
            setSearchable(true);
            setSelectable(true);
    
        }
    
        @Override
        public AssetRenderer<Entry> getAssetRenderer(long classPK, int type)
                throws PortalException {
    
            Entry entry = _entryLocalService.getEntry(classPK);
    
            EntryAssetRenderer entryAssetRenderer = new EntryAssetRenderer(entry, _entryModelResourcePermission);
    
            entryAssetRenderer.setAssetRendererType(type);
            entryAssetRenderer.setServletContext(_servletContext);
    
            return entryAssetRenderer;
        }
    
        @Override
        public String getClassName() {
            return CLASS_NAME;
        }
    
        @Override
        public String getType() {
            return TYPE;
        }
    
        @Override
        public boolean hasPermission(PermissionChecker permissionChecker,
                long classPK, String actionId) throws Exception {
    
            Entry entry = _entryLocalService.getEntry(classPK);
            return GuestbookEntryPermission.contains(permissionChecker, entry, actionId);
        }
    
        @Override
        public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,
                LiferayPortletResponse liferayPortletResponse, long classTypeId) {
    
            PortletURL portletURL = null;
    
            try {
                ThemeDisplay themeDisplay = (ThemeDisplay) liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);
    
                portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),
                        GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
                portletURL.setParameter("mvcRenderCommandName", "/guestbookwebportlet/edit_entry");
                portletURL.setParameter("showback", Boolean.FALSE.toString());
            } catch (PortalException e) {
            }
    
            return portletURL;
        }
    
        @Override
        public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) {
    
            LiferayPortletURL liferayPortletURL
            = liferayPortletResponse.createLiferayPortletURL(
                    GuestbookPortletKeys.GUESTBOOK, PortletRequest.RENDER_PHASE);
    
            try {
                liferayPortletURL.setWindowState(windowState);
            } catch (WindowStateException wse) {
    
            }
            return liferayPortletURL;
        }
    
        @Override
        public boolean isLinkable() {
            return _LINKABLE;
        }
    
        @Override
        public String getIconCssClass() {
            return "pencil";
        }
    
        @Reference(target = "(osgi.web.symbolicname=com.liferay.docs.guestbook.portlet)",
                unbind = "-")
        public void setServletContext (ServletContext servletContext) {
            _servletContext = servletContext;
        }
    
        @Reference(unbind = "-")
        protected void setEntryLocalService(EntryLocalService entryLocalService) {
            _entryLocalService = entryLocalService;
        }
    
    
        private EntryLocalService _entryLocalService;
        private ServletContext _servletContext;
        private static final boolean _LINKABLE = true;
        public static final String CLASS_NAME = Entry.class.getName();
        public static final String TYPE = "entry";
    
        private ModelResourcePermission<Entry>
        _entryModelResourcePermission;
    
    
    }

## 資産パッケージのエクスポート

コンテナは、必要に応じてアセットレンダラーとそのファクトリーを@product@で使用できるようにします。 パッケージをコンテナにエクスポートする必要があります。

`guestbook-service` モジュールの `bnd.bnd` ファイルを開き、資産パッケージを `Export-Package` 宣言に追加します。 終了すると、次のようになります。

    Export-Package: com.liferay.docs.guestbook.asset,\
                    com.liferay.docs.guestbook.service.permission,\
                    com.liferay.docs.guestbook.web.internal.security.permission.resource,\
                    com.liferay.docs.guestbook.search

これで、ゲストブックプロジェクトのエンティティは完全にアセット対応になりました。 機能をテストするには、Asset Publisherポートレットをページに追加します。 次に、ゲストブックとゲストブックエントリを追加および編集します。 次に、Asset Publisherポートレットを確認します。 Asset Publisherは、現在のサイトのあらゆる種類のアセットを動的に表示します。

![図1：カスタムエンティティのアセットレンダラーを実装して登録した後、Asset Publisherはエンティティを表示できます。](../../../../images/custom-entities-asset-publisher.png)

Asset Publisherに、追加したゲストブックとゲストブックエントリが表示されることを確認します。

すばらしいです\！ 次に、コメント、評価、タグ、カテゴリ、および関連するアセットなどのいくつかのアセットフレームワーク機能を使用するように、ポートレットのユーザーインターフェイスを更新します。
