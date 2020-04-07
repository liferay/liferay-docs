---
header-id: configuring-portlet-properties-for-your-js-portlet
---

# JSウィジェットのポートレットプロパティの構成

[TOC levels=1-4]

ポートレットのプロパティを構成するには、次の手順に従います。

1.  生成されたJavaScriptウィジェットの `package.json` ファイルを開きます。

2.  `ポートレット` エントリの下にプロパティを設定します。 これらは、 [liferay-portlet-app \ _7 \ _2 \ _0.dtd](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html)定義されているように、ポートレットのJava `@Component` アノテーションで定義するプロパティと同じであることに注意してください。 構成例を以下に示します。
   
        "portlet": {
            "com.liferay.portlet.display-category": "category.sample",
            "com.liferay.portlet.header-portlet-css": "/css/styles.css",
            "com.liferay.portlet.instanceable": true,
            "javax.portlet.name": "my_js_portlet_project",
            "javax.portlet.security-role-ref": "power-user,user",
            "javax.portlet.resource-bundle": "content.Language"
        },

3.  バンドルをデプロイして、変更を適用します。

すばらしいです\！ これで、JavaScriptポートレットのプロパティを設定する方法がわかりました。

## 関連トピック

  - [JavaScriptウィジェットのシステム設定とインスタンス設定の構成](/docs/7-2/frameworks/-/knowledge_base/f/configuring-system-settings-and-instance-settings-for-your-js-portlet)
  - [ウィジェットのローカライズ](/docs/7-2/frameworks/-/knowledge_base/f/localizing-your-portlet)
  - [JavaScriptウィジェットで翻訳機能を使用する](/docs/7-2/frameworks/-/knowledge_base/f/using-translation-features-in-your-javascript-portlet)
