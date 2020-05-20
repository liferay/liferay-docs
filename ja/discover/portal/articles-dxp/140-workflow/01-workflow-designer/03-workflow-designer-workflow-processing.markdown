---
header-id: affecting-the-processing-of-workflow-definitions
---

# ワークフロー定義の処理に影響を与える

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ワークフロー定義にはすべて、開始ノードと終了ノード、および少なくとも1つのタスクノードのノードが含まれています。 ワークフローがあるノードから別のノードに進むには、遷移が必要であることを知っているかもしれません。 このチュートリアルでは、遷移を使用してワークフローを介してノード間でアセットを移動する方法について学習しますが、ワークフローの処理に影響を与える他のいくつかの機能についても学習します。

  - トランジション
  - フォーク
  - 参加
  - 条件

常に重要な移行について学ぶことから始めます。

## トランジション

トランジションとは何ですか？ ワークフローの遷移は、あるノードを別のノードに接続します。 最初のノードを終了すると、遷移が指すノードに処理が続きます。 あるノードから別のノードへの矢印を作成するたびに、ワークフローデザイナは遷移を作成します。

![図1：ノードを接続し、ワークフロー処理をトランジションで処理します。 単一の承認者ワークフローには、送信、再送信、拒否、および承認という名前の遷移があります。](../../../images-dxp/workflow-designer-single-approver.png)

追加する各ノードには、ノードを削除するためのポップアップメニューがあります。 ノードの端にマウスを合わせると、マウスポインタが十字に変わります。 十字は、現在のノードを別のノードに接続できることを示します。 マウスボタンを押したままマウスをドラッグして、別のノードへの遷移の描画を開始します。 次のノードの端に到達する前に停止すると、ポップアップが表示され、作成してすぐに接続できるノードタイプが表示されます。 既存のノードに接続するには、コネクタをそのノードにドラッグし続けます。

Kaleo Designerでワークフローを開発するときは、すべての遷移を確認し、適切な名前を付けてください。 既定では、これらの遷移はシステムで生成された名前を取得するため、ワークフローの次のステップにアイテムを送信するリンクとしてワークフローユーザーに表示されるため、それらをより人間が読める名前に変更します。

![図2：単一の承認者ワークフローでは、レビュータスクのユーザーはアセットを承認または却下することを選択でき、アセットをEndNodeまたはUpdateタスクのいずれかに送信します。](../../../images-dxp/workflow-designer-transition-link.png)

遷移の名前を変更するには、遷移を表す矢印をクリックし、[プロパティ]タブを使用して、ノードの場合と同じように名前を設定します。

## フォークと結合

別のタスクに進む前に、あるタスクが完了するのを待つ必要がない場合があります。 代わりに、同時に2つ以上のことを実行する必要があります。 これを行うには、forkノードに移行し、forkから並列タスクに2回移行してから、joinノードを使用して一緒に戻ります。

![図3：フォークと結合を使用して、ワークフローで並列処理を有効にします。](../../../images-dxp/workflow-designer-fork-join.png)

通常の結合ノードでは、ワークフローが結合を超えて進むには、両方の並列実行からの遷移を呼び出す必要があります。 ただし、代わりにJoin XORノードを使用する場合、並列実行の1つからの遷移が呼び出される限り、ワークフローは続行されます。

フォークと結合ノードのバランスを取る必要があることに注意してください。 つまり、すべてのフォークに対して、並列ワークフロースレッドを元に戻す結合が必要です。

## 条件

アセットまたはその実行コンテキストを検査し、結果に応じて適切な遷移に送信する必要がある場合があります。 トランジションの1つに値を設定して終了するスクリプトのノードが必要です。

![図4：カテゴリ固有の承認の定義は、条件ノードで始まります。](../../../images-dxp/workflow-designer-cat-specific-condition.png)

*カテゴリ固有の承認* （`category-specific-definition.xml`）から、これはワークフローを開始する条件ノード内のスクリプトです（開始ノードから直接来る）。

    import com.liferay.asset.kernel.model.AssetCategory;
    import com.liferay.asset.kernel.model.AssetEntry;
    import com.liferay.asset.kernel.model.AssetRenderer;
    import com.liferay.asset.kernel.model.AssetRendererFactory;
    import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandler;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
    
    import java.util.List;
    
    String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
    
    WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);
    
    AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();
    
    long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
    
    AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);
    
    AssetEntry assetEntry = assetRendererFactory.getAssetEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());
    
    List<AssetCategory> assetCategories = assetEntry.getCategories();
    
    returnValue = "Content Review";
    
    for (AssetCategory assetCategory : assetCategories) {
        String categoryName = assetCategory.getName();
    
        if (categoryName.equals("legal")) {
            returnValue = "Legal Review";
    
            return;
        }
    }

この例では、アセットカテゴリをチェックして、 *Legal Review* タスクまたは *Content Review* タスクに移行するかどうか、処理パスを選択します。

その `returnValue` 変数が何であるか疑問に思うかもしれません。 条件から遷移を指す変数であり、その値はワークフロー定義の有効な遷移と一致する必要があります。 問題の資産までこのスクリプトのルックスは、その取得 [資産カテゴリー](/docs/7-1/user/-/knowledge_base/u/defining-categories-for-content)、初期設定 `のreturnValue`。 次に、アセットが *正当な* カテゴリでマークされているかどうかを確認します。 そうでない場合は *コンテンツレビュー* （ワークフローのコンテンツレビュータスク）を通過し、通過した場合は *リーガルレビュー* （ワークフローの法的レビュータスク）を通過します。

これで、サイトが公開される前にアセットを適切にレビューできるように、美しく効果的なワークフローを設計するための基本的な知識が身に付きました。


<!-- ## Related Topics [](id=related-topics)

[Workflow Forms](discover/portal/-/knowledge_base/7-1/workflow-forms)

[Using Workflow](discover/portal/-/knowledge_base/7-1/enabling-workflow)

[Liferay's Workflow Framework](/docs/7-1/tutorials/-/knowledge_base/t/liferays-workflow-framework)

[Creating Simple Applications](discover/portal/-/knowledge_base/7-1/creating-simple-applications) -->
