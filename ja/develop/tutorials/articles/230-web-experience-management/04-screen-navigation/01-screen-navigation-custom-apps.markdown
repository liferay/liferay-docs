---
header-id: using-the-framework-for-your-application
---

# アプリケーションにフレームワークを使用する

[TOC levels=1-4]

画面ナビゲーションフレームワークは、画面のJavaクラスとフロントエンドのタグライブラリの2つの部分で構成されています。 最初に必要なJavaクラスを作成してから、JSPを介してフロントエンドサポートを追加します。

## アプリケーションのバックエンドに画面を追加する

少なくとも1つのナビゲーションカテゴリを作成する必要があります。 アプリケーションに画面を追加するには、まずトップレベルのナビゲーション用に少なくとも1つのナビゲーションカテゴリを追加する必要があります。 次に、必要なページごとにナビゲーションエントリを追加できます。

まず、ナビゲーションカテゴリを追加します

1.  `ScreenNavigationCategory` インターフェースを実装するコンポーネントを作成します。

2.  コンポーネントに次のメソッドを実装します。

    **`getCategoryKey（）`**：カテゴリーの主キーを返します。

    **`getLabel（Locale locale）`**：キーのラベルを返します。

    **`getScreenNavigationKey（）`**：アプリケーションで定義されているように、カテゴリが属するナビゲーションキーを返します。

次に、ナビゲーションエントリを追加します。

1.  `ScreenNavigationEntry`を実装するコンポーネントを作成します。

2.  コンポーネントに次のメソッドを実装します。

    **`getCategoryKey（）`**：カテゴリーの主キーを返します。

    **`getEntryKey（）`**：エントリの主キーを返します。

    **`getLabel（）`**：エントリラベルを返します。

    **`getScreenNavigationKey（）`**：現在のエントリのカテゴリのナビゲーションキーを返します。

    **`isVisible（User user、T screenModelBean）`**：現在のユーザーに対してエントリを表示するかどうかのブール値。

    **`render（HttpServletRequest request、HttpServletResponse response）`**：エントリをレンダリングします。

レンダリングメソッドは、HTMLをレンダリングする方法を提供する限り、任意の方法で実装できます。 Liferay開発者は通常、以下に示すJSPを使用します。

## アプリケーションのフロントエンドに画面を追加する

JSPを使用して画面をレンダリングするには、 `render` メソッドで `JSPRenderer` コンポーネントを呼び出し、HTMLをレンダリングするJSPを作成する必要があります。

1.  次のように `JSPRenderer` を使用する `render` メソッドを作成します。
   
        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
       
            _jspRenderer.renderJSP(request, response, "/my-category/view-category.jsp");
        }

2.  クラスの下部に次のコードを追加して、参照アノテーションを使用して `JSPRenderer`にアクセスします。
   
        @Reference
        private JSPRenderer _jspRenderer;

3.  `liferay-frontend：screen-navigation` taglibと次のような必要なパラメーターを含むJSPを作成します。
   
        <liferay-frontend:screen-navigation key=
        "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
        modelBean="<%= category %>"
        portletURL="<%= portletURL %>"
        />

パラメータが必要である `キー`、 `modelBean`、及び `のPortletURL`。

  - **キー**：このアプリケーションのナビゲーションの一意の名前。

  - **modelBean**：レンダリングされているモデル

  - **portletURL**：各リンクのタイトルを作成するために使用されるポートレットURL。

次のセクションでは、より多くの画面で既存のLiferayクラスを拡張する方法について説明します。
