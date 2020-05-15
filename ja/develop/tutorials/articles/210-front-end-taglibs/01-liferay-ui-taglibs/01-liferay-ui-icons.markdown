---
header-id: liferay-ui-icons
---

# Liferay UIアイコン

[TOC levels=1-4]

Liferay UI taglibでは、アプリに含めることができるいくつかのアイコンを提供しています。アプリにアイコンを追加するには、`liferay-ui:icon` タグを使用して、 `icon`、`iconCssClass`、または`image` 属性のいずれかでアイコンを指定します。各ユースケースの例を以下に示します。

`image`属性は、（スタイルなしテーマの`images/common`フォルダーで定義されている）使用する[Liferay UIアイコン](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/images)を指定します。JSPの構成例を次に示します。

    <div class="col-md-3">
    <liferay-ui:icon image="subscribe" />
    
    <span class="ml-2">Subscribe</span>
    </div>

![図1：Image属性を使用してテーマアイコンを使用します。](../../../images/liferay-ui-taglib-icon-subscribe.png)

Liferay UI taglibは、言語フラグアイコンも公開しています。言語フラグアイコンを使用するには、アイコン名の前に相対パス`../language/`を指定します。以下は、ローカライズされたメッセージとともに現在の言語のフラグを表示するWebコンテンツ検索ポートレットのサンプルスニペットです。

    <liferay-ui:icon
    image='<%= "../language/" + languageId %>'
    message='<%= LanguageUtil.format(
    request,
    "this-result-comes-from-the-x-version-of-this-content",
    snippetLocale.getDisplayLanguage(locale),
    false
    ) %>'
    />

使用可能な[`init.ftl` 変数](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/init.ftl)および[@product@マクロ](/docs/7-1/tutorials/-/knowledge_base/t/using-liferays-macros-in-your-theme)を使用する次のコードを使用して、FreeMarkerで同じ結果を得ることができ ます。

    <#assign flag_message>
    <@liferay.language_format
    arguments=language
    key="this-result-comes-from-the-x-version-of-this-content"
    />
    </#assign>
    
    <@liferay_ui["icon"]
    image="../language/${language_id}"
    message=flag_message
    />

使用可能なアイコンの全リストを以下の図に示します。

![図2：Liferay UI taglibは、アプリで使用できる複数のアイコンを提供しています。](../../../images/liferay-ui-taglib-icons.png)

![図3：言語に基づいてLiferay UIアイコンを構成できます。](../../../images/liferay-ui-taglib-icon-flags.png)

`icon` 属性は、使用する[Font Awesomeアイコン](https://fontawesome.com/v3.2.1/icons/)を指定します。

    <liferay-ui:icon icon="angle-down" />

![図4：Icon属性を使用して、アプリにFont Awesomeアイコンを含めることができます。](../../../images/liferay-ui-taglib-icon-angle-down.png)

`iconCssClass`属性は、使用する[glyphicon](http://marcoceppi.github.io/bootstrap-glyphicons/)を指定します。

    <liferay-ui:icon
    iconCssClass="icon-remove-sign"
    label="<%= true %>"
    message="unsubscribe"
    url="<%= unsubscribeURL %>"
    />

![図5：アプリでFont Awesomeアイコンを使用できます。](../../../images/liferay-ui-taglib-icon-css-class.png)

上記の例では、アイコンで使用可能な属性の一部を使用しています。全リストについては、[Icon taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon.html)を参照してください。

## 関連トピック

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)

[Liferay UI Icon Lists](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-lists)

[Liferay UI Icon Menus](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-menus)
