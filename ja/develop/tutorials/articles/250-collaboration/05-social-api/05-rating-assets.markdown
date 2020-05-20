---
header-id: rating-assets
---

# 資産の評価

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[アセットフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework) は、ユーザーがアプリ内のコンテンツを評価できるシステムをサポートしています。 この機能は、多くの@product@の組み込みアプリに表示されます。 たとえば、ユーザーはブログアプリで公開された記事を評価できます。 taglibを使用すると、数行のコードでアプリのコンテンツの評価を有効にできます。 このチュートリアルでは、その方法を示します。

![図1：評価により、ユーザーはコンテンツに関するフィードバックをすばやく提供できます。](../../../images/social-ratings-thumbs.png)

アプリで評価を有効にするには、次の手順に従います。これらの手順では、例としてGuestbookアプリを使用していることに注意してください。 その名前が示すように、このアプリはユーザーがゲストブックに簡単なメッセージを残すことを可能にします。

1.  エンティティが [アセット有効](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)ことを確認してください。

2.  評価を有効にするエンティティの読み取り専用ビューを選択します。 あなたは、ポートレットのビューのいずれかで評価を表示することができ、またはあなたがしている場合 [実装資産レンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-an-asset) 、あなたは資産パブリッシャーアプリで完全なコンテンツビューでそれらを表示することができます。

3.  JSPに、 `liferay-ui` taglib宣言を含めます。
   
        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

4.  レンダリング要求からエンティティのIDを取得するには、 `ParamUtil` を使用します。 次に、 `-LocalServiceUtil` クラスを使用してエンティティオブジェクトを作成します。 Guestbookアプリの例のゲストブックエントリに対してこれを行う例は次のとおりです。
   
        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  `liferay-ui：ratings` タグを使用して、エンティティの評価コンポーネントを追加します。
   
        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
            classPK="<%=entry.getEntryId()%>" type="stars" />

    `タイプ` 属性は、使用する評価システムを指定します。

      - `好き`：好き
      - `つ星`：つ星（デフォルトでは5つ）
      - `親指`：親指のアップ/ダウン（上のスクリーンショットに示すように）

    また、管理者が評価タイプを構成可能にすることもできます。 これを行うには、チュートリアルを参照 [の実装評価タイプ選択と値の型変換](/docs/7-1/tutorials/-/knowledge_base/t/implementing-ratings-type-selection-and-value-transformation)。

すばらしいです\！ これで、ユーザーがアセット対応アプリのコンテンツを評価できるようにする方法がわかりました。

## 関連トピック

[資産フレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)

[評価タイプの選択と値変換の実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-ratings-type-selection-and-value-transformation)
