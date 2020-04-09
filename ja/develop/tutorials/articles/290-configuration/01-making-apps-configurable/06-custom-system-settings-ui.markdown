---
header-id: customizing-the-system-settings-user-interface
---

# システム設定ユーザーインターフェイスのカスタマイズ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@アプリケーションは、Apache Felix Configuration Admin Serviceを使用してアプリケーション構成を提供します。 単一の構成インターフェイスクラスを指定することにより、構成データが入力およびスコープされ、アプリケーションは構成が登録されると、コントロールパネル→構成→システム設定で利用可能な自動生成された構成ユーザーインターフェイスを取得します。 これが新しい情報である場合は、最初に [アプリケーションを構成可能にする](/docs/7-1/tutorials/-/knowledge_base/t/making-applications-configurable)に関するチュートリアルのセットを読むことを検討してください。

このチュートリアルでは、システム設定エントリのユーザーインターフェイスを次の方法でカスタマイズする方法について説明します。

  - 構成オブジェクトのカスタムフォームを提供します。

  - 完全にカスタム構成のUIを作成します。 これは、Configuration AdminサービスまたはLiferayのConfiguration APIを使用していない場合に特に便利です。

  - システム設定から構成オブジェクトを除外します。 完全にカスタムの構成UIを提供しているが、まだConfiguration Adminを使用している場合は、自動生成されたUIを非表示にする必要があります。 構成が管理ユーザーにアクセスできるように意図されていない場合（おそらくレベルが低すぎるため）、システム設定UIから除外することができます。

## カスタム構成フォームの提供

説明するように、この方法では、既存のコンフィグ管理者の設定クラスに依存している [ここ](/docs/7-1/tutorials/-/knowledge_base/t/making-applications-configurable)。 Liferay独自のCurrency Converterアプリケーションの構成クラスの例を次に示します。

    @ExtendedObjectClassDefinition(category = "localization")
    @Meta.OCD(
        id = "com.liferay.currency.converter.web.configuration.CurrencyConverterConfiguration",
        localization = "content/Language",
        name = "currency-converter-configuration-name"
    )
    public interface CurrencyConverterConfiguration {
    
        @Meta.AD(deflt = "GBP|CNY|EUR|JPY|USD", name = "symbols", required = false)
        public String[] symbols();
    }

値の配列をとる1つの構成オプション、シンボルがあります。

自動生成されたフォームをカスタマイズするために必要なのは、1つの追加クラス、 `ConfigurationFormRenderer` インターフェイスの実装だけです。

3つのメソッドを実装します。

1.  `getPid`：構成オブジェクトのIDを返します。 これは、 `* Configuration` クラスの `@ Meta.OCD` アノテーションの `id` プロパティで定義されてい` 。</p></li>
<li><p spaces-before="0"> <code>getRequestParameters`：カスタムフォームによって送信されたパラメーターを読み取り、キーを構成インターフェイスのフィールドの名前にするマップに配置します。

3.  `render`：目的のメソッド（たとえば、JSPや別のテンプレートメカニズム）を使用して、カスタムフォームのフィールドをレンダリングします。 `<form>` タグ自体は自動的に提供されるため、 `ConfigurationFormRenderer`含めるべきではありません。

完全な `ConfigurationFormRenderer` 実装は次のとおりです。

    @Component(immediate = true, service = ConfigurationFormRenderer.class)
    public class CurrencyConverterConfigurationFormRenderer
        implements ConfigurationFormRenderer {
    
        @Override
        public String getPid() {
            return "com.liferay.currency.converter.web.configuration.CurrencyConverterConfiguration";
        }
    
        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
    
            String formHtml = "<input name=\"mysymbols\" />";
    
            PrintWriter writer = response.getWriter();
    
            writer.print(formHtml);
    
        }
    
        @Override
        public Map<String, Object> getRequestParameters(
                HttpServletRequest request) {
    
            Map<String, Object> params = new HashMap<>();
    
            String[] mysymbols = ParamUtil.getParameterValues(request, "mysymbols");
    
            params.put("symbols", mysymbols);
    
            return params;
        }
    }

上記の例は、 `render（）` メソッドでフォームのカスタムレンダリング（HTML）を生成し、 `getRequestParameters（）` メソッドでカスタムフォームに入力された情報を読み取ります。

JSPマークアップを含む完全なデモを見るには、 [構成フォームレンダラー](/docs/7-1/tutorials/-/knowledge_base/t/configuration-form-renderer)作成に関する専用のチュートリアルをお読みください。

## 完全にカスタマイズされた構成UIの作成

場合によっては、構成に完全にカスタムUIが必要です。 たとえば、

  - アプリケーションは、Config Adminを使用して構成を提供しません。 まったく異なる設定バックエンドがあり、完全に独立したフロントエンドを作成します。

  - アプリケーションには、複数の構成画面など、UIの柔軟性が必要です。

これを実現するには、 `ConfigurationScreen` 実装を作成します。

高レベルでする必要があります

1.  `ConfigurationScreen` インターフェースの実装を宣言するコンポーネントを作成します。

2.  `Configurationscreen`のメソッドを実装します。

3.  手動でUIを作成します。

以下に実装例を示します。

    @Component(immediate = true, service = ConfigurationScreen.class) 
    public class SampleConfigurationScreen implements ConfigurationScreen {

最初に、クラスに `ConfigurationScreen`実装を宣言します。

    @Override 
    public String getCategoryKey() { 
    
        return "third-party"; 
    
    }
    
    @Override 
    public String getKey() { 
    
        return "sample-configuration-screen"; 
    
    }
    
    @Override 
    public String getName(Locale locale) { 
    
        return "Sample Configuration Screen"; 
    
    }

次に、カテゴリキー、構成エントリのキー、およびローカライズされた名前を設定します。 この例プット構成エントリは、キー入力 `サンプルcofniguration画面`に、 `サードパーティ` システム設定セクション。 システム設定に表示される文字列は *です*サンプル構成画面*。</p> 

    @Override 
    public String getScope() { 
    
        return "system"; 
    
    }

3番目に、 [構成スコープ](/docs/7-1/tutorials/-/knowledge_base/t/scoping-configurations)設定します。

    @Override 
    public void render(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
    
        _jspRenderer.renderJSP( _servletContext, request, response,
        "/sample_configuration_screen.jsp"); 
    
    }
    
    @Reference private JSPRenderer _jspRenderer;
    
    @Reference(
        target ="(osgi.web.symbolicname=com.liferay.currency.converter.web)", 
        unbind = "-")
    private ServletContext _servletContext;

最も重要なステップは、 `render` メソッドを記述することです。この例では、 `JSPRenderer` サービスに依存して、JSPにレンダリングを委任します。

JSPマークアップを記述することは、このチュートリアルの範囲を超えています。 別のチュートリアルでは、 `ConfigurationScreen` 完全なデモンストレーションとその使用方法を示すための実装とJSPマークアップを提供します。

## システム設定から構成UIを除外する

システム設定でカスタムUIを提供することは適切ですが、システム設定UIから構成を除外する必要がある場合はどうでしょうか？ あなたはコンフィグ管理を使用しても提供している場合たとえば、 `ConfigurationScreen` の実装とカスタムJSPを、次の2つのシステム設定項目を取得します：カスタム1は、あなたが書いた *と* コンフィグ管理から自動生成されたUIを。また、バックエンド開発者向けに構成が存在する必要がありますが、UIで変更することは意図されていません。

UIエントリを除外するには、 `generateUI`という名前の `ExtendedObjectClassDefinition` アノテーションプロパティを使用し` 。 デフォルトは <code>true`であるため、自動生成されたUIを抑制するには `false` に設定します。 次に例を示します。

    @ExtendedObjectClassDefinition(generateUI=false)
    @Meta.OCD(
      id = "com.foo.bar.LowLevelConfiguration",
    )
    public interface LowLevelConfiguration {
    
      public String[] foo();
      public String bar();
    
    }

これで、プログラムまたは [.configファイル](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)を介して構成を管理できますが、システム設定UIを介しては構成できません。
