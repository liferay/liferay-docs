---
header-id: portlet-providers
---

# ポートレットプロバイダー

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

一部のアプリは、異なるエンティティタイプで同じ操作を実行します。 たとえば、Asset Publisherを使用すると、ユーザーはドキュメント、Webコンテンツ、ブログなどを含むアセットとしてさまざまなエンティティを参照、追加、プレビュー、表示できます。 エンティティはさまざまですが、運用と周囲のビジネスロジックは同じままです。 Asset Publisherなどのアプリは、Portlet Providersフレームワークに依存して、エンティティを操作するポートレットを取得します。 このようにして、フレームワークを使用すると、エンティティ操作に集中でき、それらの操作を実行するポートレットに関する心配から解放されます。 このチュートリアルでは、次の方法を説明します

  - [ポートレットプロバイダーの作成と登録](#creating-portletproviders)

  - [ポートレットプロバイダーからポートレットを取得する](#retrieving-portlets-for-desired-behaviors)

## PortletProvidersの作成

[`PortletProvider`s](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html) は、エンティティタイプに関連付けられたコンポーネントクラスです。 ポートレットIDとポートレットURLを返すメソッドがあります。 `PortletProvider`登録したら、 [`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) クラスを呼び出して、対応する `PortletProvider`からポートレットIDまたはポートレットURLを取得できます。

[`WikiPortletProvider`](@platform-ref@/7.1.0-ga1/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/portlet/WikiPortletProvider.java#L27-L44) クラスを調べます。

    @Component(
        immediate = true,
        property = {
            "model.class.name=com.liferay.wiki.model.WikiPage",
            "service.ranking:Integer=100"
        },
        service = {EditPortletProvider.class, ViewPortletProvider.class}
    )
    public class WikiPortletProvider
        extends BasePortletProvider
        implements EditPortletProvider, ViewPortletProvider {
    
        @Override
        public String getPortletName() {
            return WikiPortletKeys.WIKI;
        }
    
    }

`WikiPortletProvider` は `BasePortletProvider`拡張し、 `getPortletURL` メソッドを継承します。 ただし、ポートレットの名前 `WikiPortletKeys.WIKI`を返す `PortletProvider`の `getPortletName` メソッドを実装する必要があります。

| **注：** Liferayのポートレットの1つに対して `PortletProvider` を作成する場合、| `getPortletName` メソッドを作成し、そのポートレットの| |からポートレット名を返します。 `* PortletKeys` クラス（そのようなクラスがある場合）。

`WikiPortletProvider`の `@Component` アノテーションは、これらの要素とプロパティを指定します：

  - `immediate = true` インストール時にすぐにコンポーネントをアクティブにします。
  - `"model.class.name = com.liferay.wiki.model.WikiPage"` は、ポートレットが動作するエンティティタイプを指定します。
  - `"service.ranking：Integer = 100"` は、コンポーネントのランクを `100`に設定し、同じ `model.class.name` 値を指定しているがランクが低い `PortletProvider`すべて優先します。
  - `service = {EditPortletProvider.class, ViewPortletProvider.class}` は、このクラスが実装するサブインターフェース `PortletProvider` クラスを反映します。

独自の `PortletProvider`を作成する方法は次のとおりです。

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。

2.  モジュールに `PortletProvider` クラスを作成します。 推奨されるクラス命名規則を使用します。

    `[Entity] + [Action] + PortletProvider`

    例：

    `LanguageEntryViewPortletProvider`

3.  `getPortletURL` メソッド実装を使用する場合は、 [`BasePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html) 拡張します。

4.  アクションに一致する1つ以上の [`PortletProvider`](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html) サブインターフェースを実装します。

      - [`BrowsePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html)
      - [`EditPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/EditPortletProvider.html)
      - [`ManagePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ManagePortletProvider.html)
      - [`PreviewPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PreviewPortletProvider.html)
      - [`ViewPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html)

5.  次のような注釈を追加して、クラスをOSGiコンポーネントにします。

        @Component（ immediate = true、 property = {"model.class.name = CLASS_NAME"}、 service = {INTERFACE_1.class, ...} ）

    `immediate = true` 要素は、インストール直後にコンポーネントをアクティブ化することを指定します。

    `CLASS_NAME` をエンティティの完全修飾クラス名に置き換えることにより、ポートレットが操作するエンティティのプロパティ `model.class.name` クラス名を割り当てます。 次に、 `model.class.name` プロパティの例を示します。

        「model.class.name = com.liferay.wiki.model.WikiPage」

    割り当て `サービス` の要素 `portletproviderの` サブインターフェイス（複数可）あなたが実装しようとしているが（例えば、 `ViewPortletProvider.class`、 `BrowsePortletProvider`）。 `INTERFACE_1.class、...` を、実装しているサブインターフェイスのリストに置き換えます。

6.  既存の `PortletProvider`オーバーライドする場合、より高い整数ランキングを持つ `service.ranking：Integer` プロパティを指定することにより、独自のカスタム `PortletProvider` それをオーバーランクします。

        property = {"service.ranking：Integer = 10"}

7.  必要なプロバイダーメソッドを実装します。 [`PortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.html)の `getPortletName` メソッドを必ず実装してください。 [`BasePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)拡張しなかった場合、 `PortletProvider`の `getPortletURL` メソッドも実装します。

8.  [モジュールをデプロイします](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)。

これで、 `PortletProvider` を使用して、目的の動作を提供するポートレットのIDとURLを返すことができます。 使用 `PortletProviderUtil` ポートレットのIDとURLをフェッチすることが次です。

## 目的の動作のポートレットを取得する

[`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) クラスを使用すると、ポートレットを取得してエンティティでアクションを実行できます。 必要なエンティティアクションを実行するポートレットのIDまたはURLを要求できます。

Portlet Providerフレームワークの [`PortletProvider.Action`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProvider.Action.html) Enumは、次のアクションタイプを定義します。

  - `ADD`
  - `BROWSE`
  - `EDIT`
  - `MANAGE`
  - `PREVIEW`
  - `VIEW`

アクションタイプとエンティティタイプは、ポートレットのIDまたはURLを取得する際の重要なパラメーターです。

### ポートレットIDの取得

Portlet Providerフレームワークの [`PortletProviderUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) クラスは、エンティティ操作を処理するためのポートレットのIDの取得を容易にします。 たとえば、この呼び出しは、ごみ箱のエントリを表示するためのポートレットのIDを取得します。

    String portletId = PortletProviderUtil.getPortletId(
        "com.liferay.portlet.trash.model.TrashEntry", 
        PortletProvider.Action.VIEW);

`PortletProvider.Action.VIEW` は操作であり、 `com.liferay.portlet.trash.model.TrashEntry` はエンティティタイプです。

もう1つの例は、Asset PublisherがPortlet Providerフレームワークを使用してプレビューされたアセットをページに追加する方法です。アセットをポートレットに追加し、そのポートレットをページに追加します。 アセット・パブリッシャーは使用 `Liferayの資産を：asset_display` 、そのタグライブラリタグ `asset_display / preview.jsp` ショー *追加* ポートレットを追加するためのボタン。 たとえば、プレビューされたアセットがブログエントリの場合、フレームワークは、現在のページにポートレットを追加するためのブログポートレットIDまたはURLを返します。 [`asset_display / preview.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/asset/asset-taglib/src/main/resources/META-INF/resources/asset_display/preview.jsp#L75-L91)からの関連コードは次のとおりです。

    Map<String, Object> data = new HashMap<String, Object>();
    
    <!-- populate the data map -->
    
    String portletId = PortletProviderUtil.getPortletId(assetEntry.getClassName(), PortletProvider.Action.ADD);
    
    data.put("portlet-id", portletId);
    
    <!-- add more to the data map -->
    %>
    
    <c:if test="<%= PortletPermissionUtil.contains(permissionChecker, layout, portletId, ActionKeys.ADD_TO_PAGE) %>">
        <aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />
    </c:if>

上記のコードは、 `PortletProviderUtil.getPortletId（assetEntry.getClassName（）、PortletProvider.Action.ADD）` を呼び出して、基礎となるエンティティクラスのアセットを追加および表示するポートレットのIDを取得します。

JSPは、ポートレットIDを `データ` マップに入れます。

    data.put("portlet-id", portletId);

次に、 `データ` マップを、ポートレットをページに追加する新しい *追加* ボタンに渡します。

    <aui:button cssClass="add-button-preview" data="<%= data %>" value="add" />

ポートレットURLの取得も同様に簡単です。

### ポートレットURLの取得

`PortletProviderUtil`の [`getPortletURL` メソッド](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/PortletProviderUtil.html) は、 `HttpServletRequest` または `PortletRequest`基づいて `javax.portlet.PortletURL` を返します。 また、 `グループ`を指定することもできます。

たとえば、Asset Publisherが手動モードで構成されている場合、ユーザーはAsset Browserを使用してアセットエントリを選択できます。 `asset-publisher-web` モジュールの [`configuration / asset_entries.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/asset/asset-publisher-web/src/main/resources/META-INF/resources/configuration/asset_entries.jsp#L125) ファイルは、 `PortletProviderUtil`の `getPortletURL` メソッド（以下のコードの最後）を使用して、対応するAsset Browser URLを生成します。

    List<AssetRendererFactory<?>> assetRendererFactories = 
        ListUtil.sort(
            AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
                company.getCompanyId()),
                new AssetRendererFactoryTypeNameComparator(locale));
    
    for (AssetRendererFactory<?> curRendererFactory : assetRendererFactories) {
        long curGroupId = groupId;
    
        if (!curRendererFactory.isSelectable()) {
            continue;
        }
    
        PortletURL assetBrowserURL = PortletProviderUtil.getPortletURL(
            request, curRendererFactory.getClassName(),
            PortletProvider.Action.BROWSE);

これで、 `PortletProvider`の兵器庫を解いてアプリで使用できるようになりました！

## 関連トピック

[ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/portlets)

[テーマへのポートレットの埋め込み](/docs/7-1/tutorials/-/knowledge_base/t/embedding-portlets-in-themes)

[Liferayサービスのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-liferay-services-service-wrappers)
