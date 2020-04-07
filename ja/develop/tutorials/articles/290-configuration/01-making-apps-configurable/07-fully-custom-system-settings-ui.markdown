---
header-id: configuration-form-renderer
---

# 構成フォームレンダラー

[TOC levels=1-4]

構成可能なアプリケーション用に自動生成されたシステム設定UIをカスタマイズするには、さまざまなアプローチがあります。 アプリケーションの自動生成された構成画面をゼロから作成されたフォームに置き換えるには、次の手順に従います。

1.  `DisplayContext` クラスを使用して、バックエンドコードと目的のJSPマークアップ間でデータを転送します。
2.  `ConfigurationFormRenderer` インターフェイスを実装します。
3.  構成フォームをレンダリングします。 このチュートリアルでは、JSPと以前に作成した `DisplayContext` クラスの使用方法を示します。

システム設定UIのカスタマイズに関する一般議論は、に見出される [別のチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/customizing-the-system-settings-user-interface)。

このチュートリアルでは、コントロールパネル→設定→システム設定→ローカリゼーション→言語テンプレートにある *言語テンプレート* システム設定エントリの設定UIを置き換える方法を説明します。 カスタムアプリケーションの自動生成UIを置き換える場合も、同じ手順が適用されます。

![図1：言語テンプレート構成画面用に自動生成されたUIは最適ではありません。 より人間が読めるオプションのある選択リストが望ましいです。](../../../images/sys-settings-lang-template-default.png)

具体的には、自動生成されたUIで *DDMテンプレートキー* というラベルが付いたテキスト入力フィールドは、 *言語選択スタイル*という選択リストフィールドタイプに置き換えられ、すべての可能なDDMテンプレートキーが入力されます。

## `DisplayContext`作成

`DisplayContext` クラスは、JSPでのJavaロジックの使用を簡素化および最小化するPOJOです。 ディスプレイコンテキストの使用は必須ではありませんが、従うべき良い規則です。 これは一種のデータ転送オブジェクトであり、 `DisplayContext`のセッターはレンダリングロジックを提供するJavaクラスから呼び出され（この場合 `ConfigurationFormRenderer`の `レンダー` メソッド）、ゲッターはJSPから呼び出されます、JSP自体の内部にJavaロジックを記述する必要がなくなります。

この例では、次のコンテンツで `LanguageTemplateConfigurationDisplayContext` クラスを作成します。

    public class LanguageTemplateConfigurationDisplayContext {
    
        public void addTemplateValue(
            String templateKey, String templateDisplayName) {
    
            _templateValues.add(new String[] {templateKey, templateDisplayName});
        }
    
        public String getCurrentTemplateName() {
            return _currentTemplateName;
        }
    
        public String getFieldLabel() {
            return _fieldLabel;
        }
    
        public List<String[]> getTemplateValues() {
            return _templateValues;
        }
    
        public void setCurrentTemplateName(String currentTemplateName) {
            _currentTemplateName = currentTemplateName;
        }
    
        public void setFieldLabel(String fieldLabel) {
            _fieldLabel = fieldLabel;
        }
    
        private String _currentTemplateName;
        private String _fieldLabel;
        private final List<String[]> _templateValues = new ArrayList<>();
    
    }

次に、 `ConfigurationFormRenderer`実装し` 。</p>

<h2 spaces-before="0"><code>ConfigurationFormRenderer`実装</h2>

最初にコンポーネントとクラスの宣言を作成します。 `サービス` プロパティを `ConfigurationFormRenderer.class`設定します。

    @Component(
        configurationPid = "com.liferay.site.navigation.language.web.configuration.SiteNavigationLanguageWebTemplateConfiguration",
        immediate = true, service = ConfigurationFormRenderer.class
    )
    public class LanguageTemplateConfigurationFormRenderer
        implements ConfigurationFormRenderer {

次に、 `activate` メソッド（ `@Activate` および `@Modified`装飾）を記述して、構成のプロパティのマップを型付きクラスに変換します。 設定はvolatileフィールドに保存されます。 スレッドセーフの問題を防ぐために、揮発性にすることを忘れないでください。 詳細については、コンポーネントクラス</a> から構成値を読み取る

に関する記事を参照してください。</p> 

    @Activate
    @Modified
    public void activate(Map<String, Object> properties) {
        _siteNavigationLanguageWebTemplateConfiguration =
            ConfigurableUtil.createConfigurable(
                SiteNavigationLanguageWebTemplateConfiguration.class,
                properties);
    }
    
    private volatile SiteNavigationLanguageWebTemplateConfiguration
        _siteNavigationLanguageWebTemplateConfiguration;
    

次に、 `getPid` および `getRequestParameters` メソッドをオーバーライドします。

    @Override
    public String getPid() {
        return "com.liferay.site.navigation.language.web.configuration." +
            "SiteNavigationLanguageWebTemplateConfiguration";
    }
    

`* Configuration` クラスの `@ Meta.OCD` アノテーションで指定されている完全な構成IDを返します。

    @Override
    public Map<String, Object> getRequestParameters(
        HttpServletRequest request) {
    
        Map<String, Object> params = new HashMap<>();
    
        String ddmTemplateKey = ParamUtil.getString(request, "ddmTemplateKey");
    
        params.put("ddmTemplateKey", ddmTemplateKey);
    
        return params;
    }
    

`getRequestParameters` メソッドで、カスタムフォーム（要求から取得）によって送信されたパラメーターを、構成インターフェイスのフィールドのキーにマップします。

オーバーライドされた `render` メソッドを介してレンダリングロジックを提供します。 ここで示すレンダリングアプローチでは、JSPを使用します。 要求オブジェクトに設定された `DisplayContext` クラスによってサポートされていることを思い出してください。 この `render` メソッドから設定された値は、 `DisplayContext` オブジェクトのgetterを介してJSPで使用できます。

指定された `groupId` のDDMテンプレートキーをループし、 `addTemplateKey` メソッドを使用して表示コンテキストに設定します。 次に、JSPが必要とする他の必要な値を設定します。 この場合、タイトル、フィールドラベル、リダイレクトURLを設定します。 最後に、 `renderJSP` を呼び出して、 `servletContext`、request、response、およびJSPへのパスを渡します。

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
    
        Locale locale = LocaleThreadLocal.getThemeDisplayLocale();
    
        LanguageTemplateConfigurationDisplayContext
            languageTemplateConfigurationDisplayContext =
                new LanguageTemplateConfigurationDisplayContext();
    
        languageTemplateConfigurationDisplayContext.setCurrentTemplateName(
            _siteNavigationLanguageWebTemplateConfiguration.ddmTemplateKey());
    
        long groupId = 0;
    
        Group group = _groupLocalService.fetchCompanyGroup(
            CompanyThreadLocal.getCompanyId());
    
        if (group != null) {
            groupId = group.getGroupId();
        }
    
        List<DDMTemplate> ddmTemplates = _ddmTemplateLocalService.getTemplates(
            groupId, _portal.getClassNameId(LanguageEntry.class));
    
        for (DDMTemplate ddmTemplate : ddmTemplates) {
            languageTemplateConfigurationDisplayContext.addTemplateValue(
                ddmTemplate.getTemplateKey(), ddmTemplate.getName(locale));
        }
    
        languageTemplateConfigurationDisplayContext.setFieldLabel(
            LanguageUtil.get(
                ResourceBundleUtil.getBundle(
                    locale, LanguageTemplateConfigurationFormRenderer.class),
                "language-selection-style"));
    
        request.setAttribute(
            LanguageTemplateConfigurationDisplayContext.class.getName(),
            languageTemplateConfigurationDisplayContext);
    
        _jspRenderer.renderJSP(
            _servletContext, request, response,
            "/configuration/site_navigation_language_web_template.jsp");
    }
    

クラスの下部で必要なサービス参照を指定します。 適切なサーブレットコンテキストをターゲットにして、モジュールの `bundle-SymbolicName` （その `bnd.bnd` ファイルにある）を参照ターゲットの `osgi.web.symbolicname` プロパティに渡すように注意してください。

    @Reference
    private DDMTemplateLocalService _ddmTemplateLocalService;
    
    @Reference
    private GroupLocalService _groupLocalService;
    
    @Reference
    private JSPRenderer _jspRenderer;
    
    @Reference
    private Portal _portal;
    
    @Reference(
        target = "(osgi.web.symbolicname=com.liferay.site.navigation.language.web)",
        unbind = "-"
    )
    private ServletContext _servletContext;
    

構成フォームレンダラーが実装されたら、フォームのJSPマークアップを記述できます。



## JSPマークアップの作成

次に、JSPを記述します。

    <%@ include file="/init.jsp" %>
    
    <%
    LanguageTemplateConfigurationDisplayContext
        languageTemplateConfigurationDisplayContext = (LanguageTemplateConfigurationDisplayContext)request.getAttribute(LanguageTemplateConfigurationDisplayContext.class.getName());
    
    String currentTemplateName = languageTemplateConfigurationDisplayContext.getCurrentTemplateName();
    %>
    
    <aui:select label="<%= HtmlUtil.escape(languageTemplateConfigurationDisplayContext.getFieldLabel()) %>" name="ddmTemplateKey" value="<%= currentTemplateName %>">
    
        <%
        for (String[] templateValue : languageTemplateConfigurationDisplayContext.getTemplateValues()) {
        %>
    
            <aui:option label="<%= templateValue[1] %>" selected="<%= currentTemplateName.equals(templateValue[0]) %>" value="<%= templateValue[0] %>" />
    
        <%
        }
        %>
    
    </aui:select>
    

オープニングスクリプトレットは、リクエストからディスプレイコンテキストオブジェクトを取得します。これにより、バックエンドからの情報が必要になると、そのすべてのゲッターが呼び出されます。 フォームがレンダリングされるとすぐに最初のオプションの `ddmTemplateKey` 表示値に現在のテンプレート名が必要になるため、すぐに `getCurrentTemplateName` メソッドが呼び出されます。 これは `<aui:select>` タグで発生します。 選択可能な使用可能なDDMテンプレートのそれぞれに対してオプションを作成するために使用されるロジックはほんのわずかです。

それで、この例は、すべてを言って完了したとき、どのように見えますか？

![図2：選択リストは、テキストフィールドよりもユーザーフレンドリーな構成エクスペリエンスを提供します。](../../../images/sys-settings-lang-template-custom.png)

一部の構成UIでは、 `ConfigurationFormRenderer`調整する必要があります。 このチュートリアルは、特に良い例を示しています。 システム設定で言語テンプレートのエントリに遭遇した管理者は、彼らがオフハンドで使用できるDDMテンプレートキーを知りません。 選択フィールドに使用可能な値を指定すると、ユーザーエクスペリエンスが大幅に向上します。
