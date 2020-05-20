---
header-id: relating-assets
---

# 資産の関連付け

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アセットを関連付けると、サイトまたはポータル全体の個々のコンテンツが接続されます。 これは、特に利用可能な他のコンテンツが豊富にある場合に、ユーザーが関連コンテンツを発見するのに役立ちます。 たとえば、Webコンテンツ記事に関連するアセットは、Asset Publisherアプリケーションのそのエントリとともに表示されます。

![図1：あなたとユーザーは、このブログエントリなどのように、アセットをエンティティに関連付けると便利です。](../../images/asset-related-content-asset-publisher.png)

次に、作成者がコンテンツを関連付ける方法を提供する方法を学習します。 このチュートリアルでは、あなたがしたことを前提と [、追加、更新、および削除資産](/docs/7-1/tutorials/-/knowledge_base/t/adding-updating-and-deleting-assets) アプリケーション。 すでにこれを行っている場合は、先に進み、アセットの関連付けを開始してください！

## サービスレイヤーのアセットの関連付け

最初に、ポートレットのサービスレイヤーにいくつかの変更を加える必要があります。 エンティティの資産関係の永続化を実装する必要があります。

1.  ポートレットの `service.xml`、ファインダーメソッド要素の下に次のコード行を配置し、 [](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) Service Builderを実行します。
   
        <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

2.  変更 `アドイン`、 `delete-`、及び `update-` あなたのメソッド `-LocalServiceImpl` 資産関係を持続します。 `-LocalServiceImpl`の `assetLinkLocalService` インスタンス変数を使用して、永続化アクションを実行します。

    たとえば、Wikiアプリケーションを考えてみましょう。 Wikiアセットとステータスを更新する場合、両方のメソッドはインスタンス変数 `assetLinkLocalService`介して `updateLinks` を利用します。 ここにあります `updateLinks` のWikiアプリケーションの中に呼び出し `WikiPageLocalServiceImpl.updateStatus（...）` の方法は：
   
        assetLinkLocalService.updateLinks(
            userId, assetEntry.getEntryId(), assetLinkEntryIds,
            AssetLinkConstants.TYPE_RELATED);

    `updateLinks` メソッドを呼び出すには、現在のユーザーのID、アセットエントリのID、アセットリンクエントリのID、およびリンクタイプを渡す必要があります。 アセットエントリの作成後にこのメソッドを呼び出します。 `AssetEntryLocalService.updateEntry`呼び出しから返された値を `AssetEntry` 変数（たとえば、 `assetEntry`と呼ばれる変数）に割り当てると、アセットリンクを更新するためのアセットエントリのIDを取得できます。 最後に、リンクタイプパラメータを指定するために、必ず `com.liferay.portlet.asset.model.AssetLinkConstants`をインポートしてください。

3.  `-LocalServiceImpl` class ' `delete-` メソッドでは、アセットを削除する前にアセットの関係を削除する必要があります。 たとえば、次のコードを使用して、既存のアセットリンク関係を削除できます。
   
        AssetEntry assetEntry = assetEntryLocalService.fetchEntry(
            ENTITY.class.getName(), ENTITYId);
       
        assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

カスタム `-delete` メソッドの *ENTITY* プレースホルダーを必ず交換してください。

スーパー\！ これで、ポートレットのサービスレイヤーが関連アセットを処理できるようになりました。 それでも、ポートレットのUIには、ユーザーがアセットを関連付けることができるものはまだありません。 次のステップでそれを処理します。

## UIでのアセットの関連付け

アセットをリンクするためのUIは、ユーザーがエンティティを作成および編集するJSPにある必要があります。 この方法では、コンテンツ作成者のみが他のアセットをエンティティに関連付けることができます。 関連するアセットは、折りたたみパネル内でLiferay UIタグ `liferay-ui：input-asset-links` を使用してJSPに実装されます。 このコードは、JSPの `aui：fieldset` タグ内に配置されます。

1.  `liferay-ui：input-asset-links` タグをフォームに追加します。 Blogsアプリケーションに追加する方法は次のとおりです。
   
        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="related-assets">
            <liferay-ui:input-asset-links
                className="<%= BlogsEntry.class.getName() %>"
                classPK="<%= entryId %>"
            />

    次のスクリーンショットは、アプリケーションの[関連アセット]メニューを示しています。 「関連資産」というタイトルの折りたたみ可能なパネルに含まれていることに注意してください。

    ![図2：ポートレットのエンティティは、関連アセット*選択*メニューで利用可能になりました。](../../images/related-assets-select-menu.png)

2.  残念ながら、[関連する資産]メニューには、エンティティの完全修飾クラス名が表示されます。 エンティティの簡略化された名前に置き換えるには、キーの完全修飾クラス名と値に使用する名前を持つ言語キーを追加します。 ポートレットのファイル `docroot / WEB-INF / src / content / Language.properties` に言語キーを配置します。 言語プロパティの使用に関する詳細なドキュメントについては、 [オーバーライド言語キー](/docs/7-1/tutorials/-/knowledge_base/t/overriding-language-keys) チュートリアルを参照できます。

    ポートレットを再デプロイすると、 `Language.properties` ファイルで完全修飾クラス名に割り当てた値が[関連アセット]メニューに表示されます。

驚くばかり\！ これで、コンテンツの作成者と編集者がアプリケーションのアセットを関連付けることができます。 次に行う必要があるのは、そのような関連するアセットをアプリケーションの残りのユーザーに公開することです。 結局のところ、関連するアセットを表示できるように全員に編集アクセス権を付与するのは望ましくありません。

## 関連資産の表示

アプリケーションのそのエンティティのビューで関連アセットを表示できます。または、カスタムエンティティのアセットレンダリングを実装している場合、ユーザーがAsset Publisherポートレットで表示できるように、エンティティのフルコンテンツビューで関連アセットを表示できます。

1.  エンティティに関連付けられた `AssetEntry` オブジェクトを取得する必要があります。
   
        <%
        long insultId = ParamUtil.getLong(renderRequest, "insultId");
        Insult ins = InsultLocalServiceUtil.getInsult(insultId);
        AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(Insult.class.getName(), ins.getInsultId());
        %>

2.  `liferay-ui：asset-links` タグを使用して、エンティティの関連アセットを表示します。 このタグでは、エンティティのクラス名とインスタンスオブジェクトを保持する変数を取得して、そのIDを返すことができるようにする必要があります。 以下のコード例では、エンティティクラス `ult辱` と、 `ins`というインスタンスオブジェクト変数の例を使用しています。
   
        <liferay-ui:asset-links
            assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
            className="<%=Insult.class.getName()%>"
            classPK="<%=ins.getInsultId()%>" />

すばらしいです\！ これで、ユーザーが関連資産を表示できるJSPができました。 関連するアセットは、作成済みの場合、ページの下部近くに表示されます。

優れた\！ これで、アプリに関連するアセットを実装する方法がわかりました。

## 関連トピック

[アセットの追加、更新、削除](/docs/7-1/tutorials/-/knowledge_base/t/adding-updating-and-deleting-assets)

[サービスビルダーとは](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)

[コンテンツ関係の定義](/docs/7-1/user/-/knowledge_base/u/defining-content-relationships)
