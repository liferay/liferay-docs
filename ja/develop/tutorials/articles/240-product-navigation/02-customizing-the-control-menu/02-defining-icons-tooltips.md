---
header-id: defining-icons-and-tooltips
---

# アイコンとツールチップの定義

[TOC levels=1-4]

コントロールメニューのエントリを作成するときは、テキストに加えて、またはテキストの代わりにアイコンを使用できます。ツールチップを使用して、より詳細な説明を提供することもできます。

## コントロールメニューエントリのアイコン

`*ControlMenuEntry`にLexiconまたはCSSアイコンを指定できます。Lexiconアイコンを使用するには、次のように`ProductMenuProductNavigationControlMenuEntry`でメソッドをオーバーライドする必要があります。

    public String getIconCssClass(HttpServletRequest request) {
    return "";
    }
    
    public String getIcon(HttpServletRequest request) {
    return "lexicon-icon";
    }
    
    public String getMarkupView(HttpServletRequest request) {
    return "lexicon";
    }

同様に、次のように`ProductMenuProductNavigationControlMenuEntry`メソッドをオーバーライドすることでCSSアイコンを使用できます。

    public String getIconCssClass(HttpServletRequest request) {
    return "icon-css";
    }
    
    public String getIcon(HttpServletRequest request) {
    return "";
    }
    
    public String getMarkupView(HttpServletRequest request) {
    return "";
    }

これらのアイコンは、それぞれ[icons-lexicon](https://liferay.github.io/clay/content/icons-lexicon/)および[icons-font-awesome](https://liferay.github.io/clay/content/icons-font-awesome/)コンポーネントにあります。

### コントロールメニューエントリのツールチップ

コントロールメニューエントリにツールチップを提供するには、次のような `getLabel`メソッドを作成します。

    @Override
    public String getLabel(Locale locale) {
    ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
    "content.Language", locale, getClass());
    
    return LanguageUtil.get(
    resourceBundle, "the-portal-is-currently-reindexing");
    }

ラベルを保存するには`Language.properties`を作成する必要があります。[Internationalization](/docs/7-1/tutorials/-/knowledge_base/t/internationalization)のチュートリアルでリソースバンドルの詳細を確認できます。
