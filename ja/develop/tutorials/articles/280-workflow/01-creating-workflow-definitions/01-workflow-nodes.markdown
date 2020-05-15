---
header-id: workflow-definition-nodes
---

# ワークフロー定義ノード

[TOC levels=1-4]

定義のスキーマとメタデータを配置したら、プロセスの定義を開始します。*ノード*要素とそのサブ要素は、ワークフロー定義を構成する基本的な構成要素です。

**状態ノード**はユーザー入力を必要としません。ワークフローは、状態ノードの`actions`タグで指定されたもの（通知やカスタムスクリプト）を実行し、指定された遷移に移動します。ワークフローは状態で始まり、状態で終わります。多くの場合、初期状態ノードには遷移のみが含まれます。

    <state>
    <name>created</name>
    <initial>true</initial>
    <transitions>
    <transition>
    <name>Determine Branch</name>
    <target>determine-branch</target>
    <default>true</default>
    </transition>
    </transitions>
    </state>

状態ノードで通知またはスクリプトが必要な場合は、`actions`タグを使用します。Groovyスクリプトを含む`action`要素を次に示します。これは多くの最終状態ノードにあり、ワークフローでアセットが承認済みとしてマークされます。

    <actions>
    <action>
    <name>Approve</name>
    <description>Approve</description>
    <script>
    <![CDATA[
    com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.
    updateStatus(com.liferay.portal.kernel.workflow.WorkflowConstants.
    getLabelStatus("approved"), workflowContext);]]>
    </script>
    <script-language>groovy</script-language>
    <execution-type>onEntry</execution-type>
    </action>
    </actions>

**条件**を使用すると、アセット（またはその実行コンテキスト）を検査し、特定の遷移に送信するなどの操作を行うことができます。

カテゴリ固有の承認ワークフロー定義の`determine-branch`条件は次のとおりです。

    <condition>
    <name>determine-branch</name>
    <script>
    <![CDATA[
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
    ]]>
    </script>
    <script-language>groovy</script-language>
    <transitions>
    <transition>
    <name>Legal Review</name>
    <target>legal-review</target>
    <default>false</default>
    </transition>
    <transition>
    <name>Content Review</name>
    <target>content-review</target>
    <default>false</default>
    </transition>
    </transitions>
    </condition>

この例では、アセットカテゴリをチェックして、*法務レビュー*タスクに移行するかまたは*コンテンツレビュー*タスクに移行するかの処理パスを選択します。

`returnValue`変数は条件から遷移を指し、その値は有効な遷移名と一致する必要があります。このスクリプトは、問題のアセットを検索し、その[アセットカテゴリ](/docs/7-0/user/-/knowledge_base/u/organizing-content-with-tags-and-categories#defining-categories-for-content)を取得して、初期の`returnValue`を設定します。次に、アセットが*法的*カテゴリでマークされているかどうかを確認します。マークされていない場合は、*コンテンツレビュー*（ワークフローのcontent-reviewタスク）を通過し、マークされている場合は、*法務レビュー*（ワークフローのlegal-reviewタスク）を通過します。

**フォークとジョイン：**フォークはワークフロープロセスを分割し、ジョインはプロセスを統合ブランチに戻します。0ジョイン（またはジョインXOR）を使用して常に処理を戻す必要があり、ワークフロー定義のフォークとジョインの数は等しくなければなりません。

    <fork>
    <name>fork-1</name>
    <transitions>
    <transition>
    <name>transition-1</name>
    <target>task-1</target>
    <default>true</default>
    </transition>
    <transition>
    <name>transition-2</name>
    <target>task-2</target>
    <default>false</default>
    </transition>
    </transitions>
    </fork>
    <join>
    <name>join-1</name>
    <transitions>
    <transition>
    <name>transition-4</name>
    <target>EndNode</target>
    <default>true</default>
    </transition>
    </transitions>
    </join>

ワークフローは、アセットが両方のフォークから移行するまでジョインを通過しません。ワークフロープロセスを分岐し、1つのフォークのみが完了したときに処理を続行できるようにするには、ジョインXORを使用します。

ジョインXORは、1つの重要な点でジョインと異なります。それは、処理を続行する前に両方のフォークを完了する必要があるという制約が取り除かれるという点です。処理を続行するには、アセットがフォークの一方を完了する必要があります。

    <join-xor>
    <name>join-xor</name>
    <transitions>
    <transition>
    <name>transition3</name>
    <target>EndNode</target>
    <default>true</default>
    </transition>
    </transitions>
    </join-xor>

[**タスクノード**](/docs/7-1/tutorials/-/knowledge_base/t/workflow-task-nodes)は、ワークフロー定義の中核です。それらは、ユーザーが何らかの方法でアセットと対話する部分です。タスクには、通知、割り当て、タスクタイマーなどのサブ要素を含めることもできます。

カテゴリ別承認ワークフローの`content-review`タスクを以下に示します。簡潔にするために`role`割り当てタグの一部を省略しています。

    <task>
    <name>content-review</name>
    <actions>
    <notification>
    <name>Review Notification</name>
    <template>You have a new submission waiting for your review in the workflow.</template>
    <template-language>text</template-language>
    <notification-type>email</notification-type>
    <notification-type>user-notification</notification-type>
    <execution-type>onAssignment</execution-type>
    </notification>
    </actions>
    <assignments>
    <roles>
    <role>
    <role-type>organization</role-type>
    <name>Organization Administrator</name>
    </role>
    ...
    </roles>
    </assignments>
    <task-timers>
    <task-timer>
    <name></name>
    <delay>
    <duration>1</duration>
    <scale>hour</scale>
    </delay>
    <blocking>false</blocking>
    <timer-actions>
    <timer-notification>
    <name></name>
    <template></template>
    <template-language>text</template-language>
    <notification-type>im</notification-type>
    </timer-notification>
    </timer-actions>
    </task-timer>
    </task-timers>
    <transitions>
    <transition>
    <name>approve</name>
    <target>approved</target>
    <default>true</default>
    </transition>
    <transition>
    <name>reject</name>
    <target>update</target>
    <default>false</default>
    </transition>
    </transitions>
    </task>

ワークフロータスクの詳細については、次の記事をご覧ください。
