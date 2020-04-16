---
header-id: embedding-widgets-in-templates
---

# テンプレートへのウィジェットの埋め込み

[TOC levels=1-4]

ウィジェットをWebコンテンツテンプレートに埋め込むこともできます。 インスタンス化可能またはインスタンス化不可能なコアアプリとカスタムアプリは、Webコンテンツテンプレートに埋め込むことができます。 以下は、FreeMarkerとVelocityに言語ウィジェットを埋め込む例です。

**FreeMarker：**

    <@liferay_portlet_ext["runtime"] portletName="com_liferay_portal_kernel_servlet_taglib_ui_LanguageEntry" />

**速度：**

    $theme.runtime("com_liferay_portal_kernel_servlet_taglib_ui_LanguageEntry");

| **警告：** `テーマ` 変数は、FreeMarkerに注入されなくなりました。環境。 テーマ変数が削除された理由の詳細については、| @ product @ 7.0とコード更新の提案については、|をご覧ください。 [タグライブラリは、FreeMarker](/docs/7-0/reference/-/knowledge_base/r/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker) テーマ変数を介してアクセスできなくなりました|重大な変更エントリ。

テンプレートにウィジェットを埋め込むだけでなく、テンプレートを別のテンプレートに埋め込むこともできます。 これにより、再利用可能なコード、JavaScriptライブラリのインポート、スクリプト、またはマクロが可能になります。

以下は、FreeMarkerとVelocityにテンプレートを埋め込む例です。

**FreeMarker**

``` 
<#include "${templatesPath}/[template-key]" />    
```

**速度**

    #parse ("$templatesPath/[template-key]")

*テンプレートキー* は、以前に発行されたテンプレートを編集するときに見つかります。

![図1：テンプレートの編集ページを表示すると、テンプレートキーを見つけることができます。](../../../../../images/find-template-key.png)
