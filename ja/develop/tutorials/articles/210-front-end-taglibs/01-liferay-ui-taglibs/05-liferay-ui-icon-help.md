---
header-id: liferay-ui-icon-help
---

# Liferay UIアイコンヘルプ

[TOC levels=1-4]

アイコンヘルプタグを使用すると、邪魔にならない方法で追加情報をユーザーに伝えることができます。これは、アイコン疑問符として表示され、マウスでポイントしたときにポップアップツールチップを介して追加情報が表示されます。この例は、コントロールパネルで確認できます。

![図1：これは、アイコンヘルプタグの例です。](../../../images/liferay-ui-taglib-tooltip.png)

**注：**カスタムテーマをインストールしている場合は 、`liferay-ui:icon-help`タグを機能させるために次のインポートを`view.jsp`に追加する必要がある場合があります。
|
|     <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
|     <liferay-theme:defineObjects />

ツールチップ情報が必要なUIの横に`<liferay-ui:icon-help/>`タグを追加します。必要な`message`属性を使用して情報テキストを定義します。以下は、サーバー管理のクリーンアップアクションの1つに関するスニペットの例です。

    <h5>
    <liferay-ui:message key="clean-up-permissions" />
    <liferay-ui:icon-help message="clean-up-permissions-help" />
    </h5>

![図2：コントロールパネル全体でヘルプアイコンが使用されています。](../../../images/liferay-ui-taglib-tooltip-02.png)

メッセージは[言語キー](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application#where-do-i-put-language-files)を介して提供されることに注意してください。
テスト目的でツールチップのメッセージに文字列を使用できますが、言語キーはベストプラクティスと見なされ、本番環境で使用されます。

## 関連トピック

[Clay Badges](/docs/7-1/tutorials/-/knowledge_base/t/clay-badges)

[Clay Stickers](/docs/7-1/tutorials/-/knowledge_base/t/clay-stickers)

[Liferay UI Icon Menus](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-menus)
