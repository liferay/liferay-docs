---
header-id: adding-custom-screens-to-liferay-applications
---

# Liferayアプリケーションへのカスタムスクリーンの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

特定のLiferayアプリケーションをカスタム画面で拡張できます。 カスタム画面では、開発した機能の構成を追加して、元のアプリケーションとシームレスに統合できます。

## カテゴリー管理

カテゴリ管理アプリケーションは、カスタム画面の追加をサポートして、カテゴリを編集するための追加オプションを提供します。 新しい画面ナビゲーションエントリとカテゴリの追加を示すために、カテゴリ管理に追加します。

1.  `ScreenNavigationCategory` および `ScreenNavigationEntry`を実装する `CategoryCustomScreenNavigationEntry` という名前の `asset-categories-admin-web` モジュールに新しいJavaクラスを作成します。

2.  クラス宣言の上に次のコンポーネントアノテーションを追加します。

    ``` 
     @Component(
         property = {
           "screen.navigation.category.order:Integer=1",
           "screen.navigation.entry.order:Integer=1"
         },
         service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
     )
    ```

    `screen.navigation.category.order` および `screen.navigation.entry.order` は、ナビゲーション内のアイテムの表示場所を決定します。 ナビゲーションの最初が高い。

    `サービス` 宣言、定義として宣言 `ScreenNavigationCategory`、 `ScreenNavigationEntry`、または両方。

3.  クラス本体には、次のコードを挿入します。
   
        @Override
        public String getCategoryKey() {
            return "custom-screen";
        }
       
        @Override
        public String getEntryKey() {
            return "custom-screen";
        }
       
        @Override
        public String getLabel(Locale locale) {
            return LanguageUtil.get(locale, "custom-screen");
        }
       
        @Override
        public String getScreenNavigationKey() {
            return AssetCategoriesConstants.CATEGORY_KEY_GENERAL;
        }
       
        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
       
           _jspRenderer.renderJSP(request, response, "/category/custom-screen.jsp");
        }
       
        @Reference
        private JSPRenderer _jspRenderer;

4.  `/ resources / META-INF / resources / category /` フォルダーに `custom-screen.jsp` を作成します。

5.  JSPクラスの上部で、画面ナビゲーションUIを使用するには、上部に次のスクリプレットを挿入します。
   
        <%
        String redirect = ParamUtil.getString(request, "redirect", assetCategoriesDisplayContext.getEditCategoryRedirect());
       
        long categoryId = ParamUtil.getLong(request, "categoryId");
       
        AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryId);
       
        long parentCategoryId = BeanParamUtil.getLong(category, request, "parentCategoryId");
       
        long vocabularyId = ParamUtil.getLong(request, "vocabularyId");
       
        portletDisplay.setShowBackIcon(true);
        portletDisplay.setURLBack(redirect);
       
        renderResponse.setTitle(((category == null) ? LanguageUtil.get(request, "add-new-category") : category.getTitle(locale)));
        %>

6.  その下に次のタグを挿入します。
   
        <liferay-frontend:screen-navigation key=
        "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
        modelBean="<%= category %>"
        portletURL="<%= portletURL %>"
        />

7.  JSPの残りの部分では、カスタム画面を作成します。

これで、そのパターンを使用して、必要なものに合わせて追加の画面を作成できます。
