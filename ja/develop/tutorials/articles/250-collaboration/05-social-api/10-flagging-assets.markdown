---
header-id: flagging-inappropriate-asset-content
---

# 不適切なアセットコンテンツにフラグを立てる

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

完璧な世界では、人々は素敵で親切でまともなコンテンツを投稿するでしょう。 彼らは建設的なフィードバックでコメントに返信し、お互いに非難しません。 残念なことに、人々は悪い日を過ごし、不適切な投稿で欲求不満を解消することがあります。 心配する必要はありませんが、アセットフレームワークはアプリのコンテンツにフラグを立てるシステムをサポートしています。 ユーザーに不適切なコンテンツにフラグを立てさせると、サイト管理者の手間がかかります。

このチュートリアルでは、ポートレットでコンテンツのフラグ設定を有効にする方法を示します。

![図1：ユーザーが不快なコンテンツをマークできるようにするためのフラグは、メッセージボードポートレットで有効になっています。](../../../images/social-flags.png)

アプリでコンテンツのフラグ設定を有効にするには、次の手順に従います。

1.  エンティティが [アセット有効](/docs/7-0/tutorials/-/knowledge_base/t/asset-framework)ことを確認してください。

2.  フラグを有効にするエンティティの読み取り専用ビューを選択します。 あなたは、アプリケーションのビューのいずれかのフラグを表示することができ、またはあなたがした場合 [資産のレンダリング実装](/docs/7-1/tutorials/-/knowledge_base/t/rendering-an-asset) 、あなたは資産パブリッシャーアプリで完全なコンテンツビューで表示することができます。

3.  JSPに、 `liferay-flags` taglib宣言を含めます。
   
        <%@ taglib prefix="liferay-flags" uri="http://liferay.com/tld/flags" %>

4.  レンダリング要求からエンティティのIDを取得するには、 `ParamUtil` を使用します。 次に、 `-LocalServiceUtil` クラスを使用してエンティティオブジェクトを作成します。
   
        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  [タグ `liferay-flags：flags`](@app-ref@/collaboration/latest/taglibdocs/liferay-flags/flags.html) を使用して、フラグコンポーネントを追加します。
   
        <liferay-flags:flags
            className="<%= Entry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            contentTitle="<%= title %>"
            message="flag-this-content"
            reportedUserId="<%= reportedUserId %>"
        />

    `reportsUserId` 属性は、アセットにフラグを立てたユーザーを指定します。

すばらしいです\！ これで、ユーザーがアセット対応アプリのコンテンツにフラグを立てることができるようになりました。

## 関連トピック

[資産フレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)

[資産の評価](/docs/7-1/tutorials/-/knowledge_base/t/rating-assets)
