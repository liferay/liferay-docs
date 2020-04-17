---
header-id: leveraging-the-script-engine-in-workflow
---

# ワークフローでのスクリプトエンジンの活用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

LiferayのKaleoワークフローエンジンは、エンタープライズ環境でコンテンツを確認および承認するための堅牢なシステムを提供します。 スクリプトを利用しなくても、強力で堅牢なワークフローソリューションです。 スクリプトを追加すると、次のレベルに進みます。 これらのスクリプトはスクリプトコンソールからは実行されませんが、 [XMLワークフロー定義](/docs/7-1/tutorials/-/knowledge_base/t/crafting-xml-workflow-definitions) 埋め込まれ、ワークフローの実行中に実行されます。

ワークフローの最後のステップでは、コンテンツを使用できるようにするスクリプトを実行します。 以下のスニペットは、ワークフローに関連付けられたJavaクラスにアクセスして、コンテンツのステータスを *承認済み*に設定します。

    <script>
        <![CDATA[
            import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
            import com.liferay.portal.kernel.workflow.WorkflowConstants;
    
            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
        ]]>
    </script>
    <script-language>groovy</script-language>

ワークフローのほぼすべての時点で、Liferayのスクリプトエンジンを使用して、ワークフローAPIまたは他のLiferay APIにアクセスできます。 これを使用する方法はたくさんあります。 ここにいくつかの実用的な例があります：

  - 特定のワークフロー関連の役割を持つユーザーのリストを取得する
  - 指定されたコンテンツ承認者に、コンテンツをレビューできない場合に連絡する人のリストを記載したメールを送信する
  - コンテンツの承認に割り当てられたユーザーのアラートポートレットに表示されるアラートの作成

もちろん、これを試す前に、スクリプトをワークフローに挿入するための適切な構文を知っておく必要があります。 XMLワークフロー定義では、スクリプトが含まれていることができる任意のXML型に使用することができる `アクション` タグ：これらのタイプは、 `<state>`、 `<task>`、 `<fork>` と `<join>`。 これらのタイプの1つの内部で、スクリプトを次のようにフォーマットします。

    <actions>
        <action>
            <script>
                <![CDATA[*the contents of your script*]]>
            </script>
            <script-language>*your scripting language of choice*</script-language>
        </action>
        ...
    </actions>

以下は、Groovyで作成されたワークフロースクリプトの例です。 これは、カレオの `条件` ステートメントで使用されます。 Liferayのアセットフレームワークにアクセスして、アセットのカテゴリを決定します。 スクリプトはカテゴリを使用して、正しい承認プロセスを自動的に決定します。 カテゴリー `リーガル` がアセットに適用されている場合、アセットは送信時に `Legal Review` タスクに送信されます。 それ以外の場合、アセットは `デフォルトレビュー` タスクに送信されます。

    <script>
        <![CDATA[
            import com.liferay.portal.kernel.util.GetterUtil;
            import com.liferay.portal.kernel.workflow.WorkflowConstants;
            import com.liferay.portal.kernel.workflow.WorkflowHandler;
            import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
            import com.liferay.asset.kernel.model.AssetCategory;
            import com.liferay.asset.kernel.model.AssetEntry;
            import com.liferay.asset.kernel.model.AssetRenderer;
            import com.liferay.asset.kernel.model.AssetRendererFactory;
            import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
    
            import java.util.List;
    
            String className = (String)workflowContext.get(
                WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
    
            WorkflowHandler workflowHandler =
                WorkflowHandlerRegistryUtil.getWorkflowHandler(className);
    
            AssetRendererFactory assetRendererFactory =
                workflowHandler.getAssetRendererFactory();
    
            long classPK =
                GetterUtil.getLong((String)workflowContext.get
                (WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
    
            AssetRenderer assetRenderer =
                workflowHandler.getAssetRenderer(classPK);
    
            AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
                assetRendererFactory.getClassName(), assetRenderer.getClassPK());
    
            List<AssetCategory> assetCategories = assetEntry.getCategories();
    
            returnValue = "Default Review";
    
            for (AssetCategory assetCategory : assetCategories) {
                String categoryName = assetCategory.getName();
    
                if (categoryName.equals("legal")) {
                    returnValue = "Legal Review";
    
                    return;
                }
            }
           ]]>
    </script>
    <script-language>groovy</script-language>

ワークフロー内では、戻り値に基づいて次のタスクまたは状態が選択されます。 [埋め込みワークフロー](/docs/7-1/user/-/knowledge_base/u/workflow#embedded-workflows) アクセスしてXMLを検査することにより、ワークフロースクリプトの例をいくつか参照してください。

## OSGiサービスの呼び出し

OSGiランタイムの動的環境を考慮して、ワークフロースクリプトからOSGiサービスを呼び出すにはどうすればよいですか？ [サービストラッカーを使用します](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)。 そうすることで、コードが必要なサービスにアクセスできることを確認し、そうでない場合は、適切な対応を行うことができます。 以下は、Groovyでこれがどのように表示されるかを示すコードの例です。

    import com.liferay.journal.model.JournalArticle;
    import com.liferay.journal.service.JournalArticleLocalService;
    import com.liferay.portal.scripting.groovy.internal.GroovyExecutor;
    
    import org.osgi.framework.Bundle;
    import org.osgi.framework.FrameworkUtil;
    import org.osgi.util.tracker.ServiceTracker;
    
    ServiceTracker<JournalArticleLocalService, JournalArticleLocalService> st;
    
    try {
        Bundle bundle = FrameworkUtil.getBundle(GroovyExecutor.class);
    
        st = new ServiceTracker(bundle.getBundleContext(), JournalArticleLocalService.class, null);
        st.open();
    
        JournalArticleLocalService jaService = st.waitForService(500);
    
        if (jaService == null) {
            _log.warn("The required service 'JournalArticleLocalService' is not available.");
        }
        else {
            java.util.List<JournalArticle>articles = jaService.getArticles();
            if (articles != null) {
                _log.info("Article count: " + articles.size());
            } else {
                _log.info("no articles");
            }
        }
    }
    catch(Exception e) {
        //Handle error appropriately
    }
    finally {
        if (st != null) {
            st.close();
        }
    }

[サービストラッカー](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)に関する記事を読んだ場合、上記のコードの奇妙に見える部分は `getBundle` 呼び出しだけです。なぜ、 `GroovyExecutor.class` がパラメーターとして渡されるのですか？ `FrameworkUtil.getBundle` 呼び出しに渡されるパラメーターは、ワークフロースクリプトを実行するOSGiバンドルからのクラスでなければなりません。 これは、呼び出しを行っているクラスをホストしているバンドルを取得するプラグインプロジェクトのコンテキストとは異なります（たとえば、 `this.getClass（）`を使用）。 別のスクリプトエンジンの場合、スクリプトを実行する特定のバンドルから具象クラスを渡す必要があることに注意してください。

Liferayのスクリプトとワークフローエンジンの組み合わせは非常に強力です。 ただし、ユーザーがコードを実行できるため、危険な場合があります。 権限を構成するときは、ワークフロー定義内のスクリプトが不十分または悪意を持って記述されている場合の潜在的な結果に注意してください。 Kaleoワークフローを使用したワークフロー定義の作成の詳細については、 [ワークフローのドキュメント](/docs/7-1/user/-/knowledge_base/u/workflow)参照してください。

## 関連トピック

[スクリプトコンソールからのスクリプトの実行](/docs/7-1/user/-/knowledge_base/u/running-scripts-from-the-script-console)

[スクリプトの例](/docs/7-1/user/-/knowledge_base/u/script-examples)
