---
header-id: using-liferays-macros-in-your-theme
---

# テーマで@product@のマクロを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[マクロ](https://freemarker.apache.org/docs/ref_directive_macro.html) は、テーマテンプレートフラグメントを変数に割り当てることができます。 これにより、テーマテンプレートが乱雑にならず、読みやすくなります。 @product@は、FreeMarkerテーマテンプレートで使用してテーマリソースや標準ポートレットなどを含めることができる [`FTL_Liferay.ftl` テンプレート](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl) でいくつかのマクロを定義します。 @product@は、そのtaglibをFreeMarkerマクロとして公開します。 FreeMarkerテンプレートでtaglibを使用する方法の詳細については、対応する [taglibチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/front-end-taglibs) を参照してください。 このチュートリアルでは、FreeMarkerテーマテンプレートでマクロを使用する方法を示します。

次の手順を実行します：

1.  次の表に示すマクロのいずれかを選択します。

    | マクロ                         | パラメーター                                      | 説明                                                                                      | 例                                                                           |
    | --------------------------- | ------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
    | パン粉                         | default \ _preferences                     | オプションの設定でパンくずリストポートレットを追加します                                                            | `<@liferay.breadcrumbs />`                                            |
    | control \ _menu            | なし                                          | コントロールメニューポートレットを追加します                                                                  | `<@liferay.control_menu />`                                           |
    | CSS                         | ファイル名                                       | 指定したファイル名の場所に外部スタイルシートを追加します                                                            | `<@liferay.css file_name="${css_folder}/mycss.css"/>`                 |
    | date                        | フォーマット                                      | 指定された形式で現在のロケールの日付を出力します                                                                | `<@liferay.date format="/yyyy/MM/dd/HH/" />`                          |
    | js                          | ファイル名                                       | 指定されたファイル名ソースで外部JavaScriptファイルを追加します                                                    | `<@liferay.js file_name="${javascript_folder}/myJs.js"/>`             |
    | 言語                          | キー                                          | 現在のロケールで指定された言語キーを出力します                                                                 | `<@liferay.language key="last-modified" />`                           |
    | language \ _format         | 引数<br/>キー                                   | 指定された言語キーを指定された引数でフォーマットします。 たとえば、キーとして `go-to-x` を、引数として `Mars` を渡すと、 *Gos to Mars*ます。 | `<@liferay.language_format arguments="${site_name}" key="go-to-x" />` |
    | 言語                          | default \ _preferences                     | オプションの設定で言語ポートレットを追加します                                                                 | `<@liferay.languages />`                                              |
    | navigation \ _menu         | default \ _preferences<br/>instance \ _id | オプションの設定とインスタンスIDを含むナビゲーションメニューポートレットを追加します。                                            | `<@liferay.navigation_menu />`                                        |
    | サーチ                         | default \ _preferences                     | オプションの設定で検索ポートレットを追加します                                                                 | `<@liferay.search />`                                                 |
    | 検索バー                        | default \ _preferences                     | オプションの設定で検索バーポートレットを追加します                                                               | `<@liferay.search_bar />`                                             |
    | user \ _personal \ _bar | なし                                          | ユーザー個人バーポートレットを追加します                                                                    | `<@liferay.user_personal_bar />`                                      |


2.  テーマテンプレートでマクロを呼び出します。 製品@ @のデフォルトFreeMarkerのマクロ呼び出しをして名前空間さ `のLiferay`。 たとえば、検索バーポートレットを含めるには、以下に示すマクロ呼び出しを追加します。
   
        <@liferay.search_bar>

3.  [ポートレット設定](/docs/7-1/tutorials/-/knowledge_base/t/embedding-portlets-in-themes#setting-default-preferences-for-an-embedded-portlet)などの必須またはオプションの引数をマクロ呼び出しに含めます。 たとえば、@product@の `言語` マクロディレクティブには、言語キーパラメーターが含まれます。
   
        <#macro language
          key
        >
          ${languageUtil.get(locale, key)}
        </#macro>

    次のように、マクロ呼び出しで引数を渡すことができます。
   
        <@liferay.language key="powered-by" />

    以下の例では、検索ポートレットの [ポートレットデコレータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-configurable-styles-for-portlet-wrappers) をベアボーンに設定します。
   
        <@liferay.search default_preferences=
          freeMarkerPortletPreferences.getPreferences(
            "portletSetupPortletDecoratorId", "barebone"
          ) 
        />

    ナビゲーションメニューポートレットの以下の例に示すように、複数のポートレットプリファレンスをオブジェクトに渡すこともできます。
   
        <#assign secondaryNavigationPreferencesMap = 
          {
            "displayStyle": "ddmTemplate_NAVBAR-BLANK-JUSTIFIED-FTL", 
            "portletSetupPortletDecoratorId": "barebone", 
            "rootLayoutType": "relative", 
            "siteNavigationMenuId": "0", 
            "siteNavigationMenuType": "1"
          } 
        />
       
        <@liferay.navigation_menu
          default_preferences=
          freeMarkerPortletPreferences.getPreferences(secondaryNavigationPreferencesMap)
          instance_id="main_navigation_menu"
        />

| **注：** ポートレットプリファレンスは各ポートレットに固有であるため、最初に以下を行う必要があります。設定する設定を決定します。 次の2つの方法があります|ポートレットプリファレンスの適切なキー/値のペアを決定します。 最初はポートレットプリファレンスを手動で設定してから、|の値を確認します。 `portletPreferences.preferences何をするかのヒントとしてのデータベースの` 列|構成します。 | |別のアプローチは、バンドル内の各アプリでキーワードを検索することです| `設定`。 これにより、ポートレットプリファレンスを持つアプリJSPが返されます。ポートレットに定義されています。

これで、テーマテンプレートで@product@のマクロを使用する方法がわかりました！

## 関連トピック

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[テーマ用に再利用可能なコードを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-reusable-pieces-of-code-for-your-themes)

[テーマリファレンスガイド](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide)
