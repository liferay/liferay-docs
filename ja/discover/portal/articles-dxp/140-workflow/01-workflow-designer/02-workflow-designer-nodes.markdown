---
header-id: workflow-definition-nodes
---

# ワークフロー定義ノード

[TOC levels=1-4]

ワークフローデザイナでワークフロー定義</a> を作成する


の基本を理解したら、詳細に進むことができます。 このチュートリアルでは、ワークフローノードで使用できる2つの重要な機能であるアクションと通知について学びます。 また、遷移、フォーク、結合、および条件を使用してワークフローの処理に影響を与える方法についても学習します。</p> 

ワークフロー定義で使用できるいくつかのノードタイプがあります。

  - タスクノード
  - ノードの分岐と結合
  - 条件ノード
  - 開始ノード
  - 終了ノード
  - 状態ノード

これらは最も複雑なノードであり、多くの場合ワークフロー定義の中心であるため、タスクノードは個別に カバーされ[](/docs/7-1/user/-/knowledge_base/u/creating-tasks-in-workflow-designer)。</p> 

フォークは、参加、および条件ノードが上のチュートリアルで、トランジションと一緒に、議論されている [処理ワークフロー](/docs/7-1/user/-/knowledge_base/u/affecting-the-processing-of-workflow-definitions)彼らはワークフローの処理に影響を与えるために使用されているので、。

このチュートリアルでは、状態ノード、開始ノード、終了ノード、およびアクションと通知について説明します。



## ノードのアクションと通知

すべてのノードがアクションと通知を持つことができます。



### 行動

アクションは、ノードに入る前、ノードを出た後、またはタスクノードが割り当てられると、追加の処理を実行します。 ノードの[プロパティ]タブにアクセスし、[ *アクション*]をダブルクリックして構成します。

![図1：タスクノードにアクションを追加できます。](../../../images-dxp/workflow-designer-action.png)

単一の承認者ワークフローには、アセットのステータスを *拒否*に設定し、次に *保留*設定するGroovyで記述されたアクションを持つ更新タスクが含まれています。

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    
    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("denied"), workflowContext);
    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("pending"), workflowContext);
    

アクションスクリプトが最初にステータスを設定し、次に別のステータスに設定するのはなぜですか？ 一部のアセットでは、ステータスが *拒否* 場合、アイテムが拒否されたという通知がアセット作成者にメールで送信されます。

ワークフロー定義の終了ノードには、終了ノードへの入り口で、デフォルトでアクションが構成されています。

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    
    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
    

これは通常、ワークフロープロセスの目標であるため、ステータスを *承認済み*に更新するGroovyスクリプトです。

上記のアクションのような簡単なことをすることも、好きなだけクリエイティブにすることもできます。

<!-- Let's add an example of something creative. -Rich -->

タスクをユーザーに割り当てるのは良いことであり、ユーザーがワークフロータスクの通知を受け取ることができればさらに便利です。



### お知らせ

通知は、ワークフローに注意が必要であることをタスク担当者に知らせるため、またはプロセスのステータスについてアセット作成者を更新するために送信されます。 タスクまたはワークフローの他のタイプのノードに送信できます。 通知を設定するには、ノードの[プロパティ]タブで[ *通知* ]をダブルクリックして、通知を作成します。

![図2：タスクノードから通知を送信できます。](../../../images-dxp/workflow-designer-notification.png)

通知タイプを指定する必要があり、ユーザー通知、電子メール、インスタントメッセンジャー、またはプライベートメッセージを選択できます。 テンプレートが必要な場合は、FreemarkerまたはVelocityを使用するか、プレーンテキストメッセージを作成することを選択できます。

以下は、通知でアセット作成者の名前とアセットのタイプを報告する基本的なFreemarkerテンプレートです。

    ${userName} sent you a ${entryType} for review in the workflow.
    

また、タスクの割り当て時（割り当て時）、またはワークフロー処理がノードを離れるとき（終了時）に、通知の送信をノードへのエントリ（開始時）にリンクするように選択することもできます。 ノードで複数の通知を構成できます。

通常、割り当てと通知の設定はチーム化されているため、ワークフローでタスクが割り当てられると、ユーザーは通知を受け取ります。 これを行うには、通知を設定するときに、[Recipient Type]で[ *Task Assignees* 選択します。

| **注：** 電子メール通知の名前</em> の *とアドレス* の *は|です。ポータルプロパティで設定可能。 これらの設定を|に入れます。 `portal-ext.properties` あなたのLiferayホームフォルダに、ファイルを。 その後|サーバーを再起動します：| | workflow.email.from.name = | workflow.email.from.address = | |これらは、プログラムで `WorkflowContext`に設定することもできます。プログラムによる設定は常にシステムスコープのポータルよりも優先されます。プロパティ。</p> 



## 開始ノードと終了ノード

開始ノードと終了ノードはワークフロー処理を開始し、アセットを最終的な承認済み状態にします。 多くの場合、デフォルトの開始ノードと終了ノードを変更せずに使用できます。 さらに処理を開始する場合（開始ノードの場合）、上記のアクションのセクションで説明したように、[プロパティ]タブを使用してノードにアクションを追加します。

エンドノードには、Groovyスクリプト言語を使用してワークフローステータスをApprovedに設定するデフォルトアクションがあります。

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    
    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
    

追加の処理が必要な場合は、アクションスクリプトに追加してください。

デフォルトでは、開始ノードと終了ノードを接続する遷移がありますが、ほとんどのワークフローは初期状態から承認済みに直接移行しないため、おそらくそれを削除する必要があります。



## 状態ノード

状態ノードは、通知とアクションを持つことができます。 ワークフローデザイナによって追加されたデフォルトの終了ノードは、ワークフローのステータスを承認済みに設定する事前構成された状態ノードです。 ステータスを *Expired*設定するノードを作成するとします。 状態ノードを作成するには、ワークフローノードをワークフローデザイナキャンバスにドラッグし、状態ノードを期限切れに設定するアクションを構成します。 Groovyでは次のようになります。

    import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    
    WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("expired"), workflowContext);
    

次に、forkノードとjoinノードを使用して並列処理を行う方法を学びます。

<!-- ## Related Topics [](id=related-topics)

[Workflow Forms](discover/portal/-/knowledge_base/7-1/workflow-forms)

[Using Workflow](discover/portal/-/knowledge_base/7-1/enabling-workflow)

[Liferay's Workflow Framework](/docs/7-1/tutorials/-/knowledge_base/t/liferays-workflow-framework)

[Creating Simple Applications](discover/portal/-/knowledge_base/7-1/creating-simple-applications) -->

