---
header-id: embedding-portlets-in-themes
---

# テーマへのポートレットの埋め込み

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマにポートレットを埋め込んで、テーマが使用されているすべてのページでポートレットを表示したい場合があります。 特定のポートレットを所定の場所にハードコーディングすることには多くの欠点があるため、 *Portlet Providers* フレームワークは、特定のエンティティタイプとアクションに基づいて適切なポートレットを表示する代替手段を提供します。

このチュートリアルでは、カスタムテーマでエンティティタイプとアクションを宣言する方法を学習し、指定されたパラメーターに基づいて使用する正しいポートレットを見つけるモジュールを作成します。 最初に、ポートレットをテーマに埋め込む方法を学びます。

## カスタムテーマへのポートレットの追加

最初に行うべきことは、埋め込みポートレットを宣言するテンプレートファイルを開くことです。 たとえば、 `portal_normal.ftl` テンプレートファイルは、埋め込みポートレットを宣言する一般的な場所です。 問題を回避するには、通常、エンティティタイプとアクションを含むポートレットを埋め込むのが最善ですが、ポートレット名でハードコーディングする必要がある状況が発生する場合があります。 ここでは両方の方法について説明します。

### エンティティタイプとアクションによるポートレットの埋め込み[](id=embedding-a-portlet-by-class-name)

ポートレットを埋め込む場所に次の宣言を挿入することから始めます。

    <@liferay_portlet["runtime"]
        portletProviderAction=ACTION
        portletProviderClassName="CLASS_NAME"
    />

この宣言には、アクションのタイプと、ポートレットが処理するエンティティタイプのクラス名の2つのパラメーターが必要です。 クラス名を使用する埋め込みポートレット宣言の例を次に示します。

    <@liferay_portlet["runtime"]
        portletProviderAction=portletProviderAction.VIEW
        portletProviderClassName="com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry"
    />

これにより、テーマが言語エントリの表示を要求していることが宣言されます。 @product@は、ポートレットに最高のサービスランキングを提供することにより、この場合に使用するデプロイ済みポートレットを決定します。

| **注：** 場合によっては、デフォルトのポートレットが既に提供されています。特定のリクエスト。 カスタムでデフォルトのポートレットをオーバーライドできます。より高いサービスランクを指定してポートレットを作成します。 これを行うには、次を設定します|クラスのプロパティ ' `@Component` 宣言：| | property = {"service.ranking：Integer = 20"} | |サービスのランキングは、デフォルトのポートレットよりも高く設定してください。中古。

：ポートレット・プロバイダ・フレームワークによってサポートされるアクションの5種類がある `ADD`、 `BROWSE`、 `EDIT`、 `PREVIEW`、及び `VIEW`。 テーマのランタイム宣言でエンティティタイプとアクションを指定します。

すばらしいです\！ テーマの宣言が完了しました。 ただし、ポータルはまだこのリクエストを処理するように設定されていません。 テーマの要求に適合するポートレットを見つけることができるモジュールを作成する必要があります。

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。

2.  モジュールの `src` ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。 命名規則に従うには、エンティティタイプとアクションタイプに基づいてクラスに名前を付け、その後に *PortletProvider* （たとえば、 `SiteNavigationLanguageEntryViewPortletProvider`）を付けます。 クラスが拡張する必要があり [`BasePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html) クラスを、あなたのテーマ（例えば、で選択したアクションに基づいて、適切なポートレット・プロバイダ・インターフェース実装 [`ViewPortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/ViewPortletProvider.html)、 [`BrowsePortletProvider`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BrowsePortletProvider.html)、など）。

3.  クラスの宣言のすぐ上に、次の注釈を挿入します。
   
        @Component(
            immediate = true,
            property = {"model.class.name=CLASS_NAME"},
            service = INTERFACE.class
        )

    `プロパティ` 要素は、テーマ宣言で指定したエンティティタイプと一致する必要があります（例： `com.liferay.portal.kernel.servlet.taglib.ui.LanguageEntry`）。 また、 `サービス` 要素は、実装しているインターフェイスと一致する必要があります（例： `ViewPortletProvider.class`）。 [RolesSelectorEditPortletProvider](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/roles/roles-selector-web/src/main/java/com/liferay/roles/selector/web/internal/portlet/RolesSelectorEditPortletProvider.java) クラスの同様の `@Component` アノテーションの例を表示できます。

4.  実装するメソッドを指定します。 このサービスがテーマによって呼び出されるときに提供される必要があるポートレットIDとページIDを必ず取得してください。

    一般的な使用例は、 `getPortletId（）` および `getPlid（ThemeDisplay）` メソッドを実装することです。 あなたは見ることができます [SiteNavigationLanguageViewPortletProvider](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/site-navigation/site-navigation-language-web/src/main/java/com/liferay/site/navigation/language/web/internal/portlet/SiteNavigationLanguageViewPortletProvider.java) これらの方法は、テーマに埋め込むためのポートレットを提供するために実装する方法の例について。 このサンプルモジュールは、 [SiteNavigationLanguagePortletKeys](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/site-navigation/site-navigation-language-api/src/main/java/com/liferay/site/navigation/language/constants/SiteNavigationLanguagePortletKeys.java)指定された言語ポートレットのポートレットIDを返します。 PLIDも返します。PLIDは、テーマで使用されるページを一意に識別するIDです。 これらを取得することで、テーマはどのポートレットを使用するか、どのポートレットで使用するかを認識します。

あとはモジュールのJARファイルを生成し、Portalの `osgi / modules` ディレクトリにコピーするだけです。 モジュールがインストールされ、Portalのサービスレジストリにアクティブ化されると、テーマが使用されている場所であれば、埋め込みポートレットを使用できます。

必要なエンティティとアクションの種類に基づいてポートレットを正常に要求し、ポートレットを取得してテーマに埋め込むモジュールを作成してデプロイしました。

### ポートレット名によるポートレットの埋め込み

特定のポートレットをテーマに埋め込む場合は、インスタンスIDと名前を指定してハードコーディングできます。

    <@liferay_portlet["runtime"]
        instanceId="INSTANCE_ID"
        portletName="PORTLET_NAME"
    />

| **注：** ポートレットがインスタンス化可能である場合、インスタンスIDを提供する必要があります。 |それ以外の場合は、この行を削除できます。 ポートレットをインスタンス化不可に設定するには、|コンポーネントアノテーションでプロパティ `com.liferay.portlet.instanceable` 設定します|ポートレットの `false`

ポートレット名は `javax.portlet.name`の値と同じでなければなりません。

以下は、ポートレット名を使用してWebコンテンツポートレットを埋め込む埋め込みポートレット宣言の例です。

    <@liferay_portlet["runtime"]
        portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
    />

アプリケーションのデフォルト設定を設定することもできます。 次に、このプロセスについて説明します。

### 埋め込みポートレットのデフォルト設定の設定

埋め込みポートレットのデフォルトのポートレットプリファレンスを設定するには、次の手順に従います。

1.  `freeMarkerPortletPreferences` オブジェクトを使用して、ポートレットプリファレンスを取得します。 次の例では、 `barebone` [ポートレットデコレータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-configurable-styles-for-portlet-wrappers)取得します。
   
        <#assign preferences = freeMarkerPortletPreferences.getPreferences(
          "portletSetupPortletDecoratorId", "barebone"
        ) />

2.  組み込みポートレットの `defaultPreferences` 属性を、構成したばかりの `freeMarkerPortletPreferences` オブジェクトに設定します。
   
        <@liferay_portlet["runtime"]
            defaultPreferences="${preferences}"
            portletName="com_liferay_login_web_portlet_LoginPortlet"
        />

これで、埋め込みポートレットのデフォルト設定を設定する方法がわかりました！ 次に、埋め込みポートレットに使用できる追加属性を確認できます。

### ポートレットの追加属性

以下は、組み込みポートレットに定義できる追加の属性です。

**defaultPreferences**：アプリケーションのポートレットプリファレンスの文字列。 これには、ルックアンドフィールの構成が含まれます。

**instanceId**：アプリケーションがインスタンス化可能な場合、アプリのインスタンスID。

**persistSettings**：アプリケーションでデフォルト設定を使用するかどうか。このデフォルト設定はレイアウト間で保持されます。 デフォルト値は *true*です。

**settingsScope**：アプリケーションに使用する設定を指定します。 デフォルト値は `portletInstance`ですが、 `グループ` または `会社`設定できます。

これで、クラス名とポートレット名でテーマにポートレットを埋め込む方法と、埋め込みポートレットを構成する方法がわかりました！

## 関連トピック

[テーマへのポートレットの埋め込み](/docs/7-1/tutorials/-/knowledge_base/t/embedding-portlets-in-themes)

[ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/portlets)

[サービスビルダー](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)
