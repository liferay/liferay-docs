---
header-id: creating-tasks-in-workflow-designer
---

# Kaleo Designerでタスクを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

タスクノードにはいくつかの部分があり、ワークフロー定義の最も複雑な部分です。 他のワークフローノードとは異なり、タスクノードには割り当てがあります。ワークフロープロセスがタスクノードに入ると、ユーザーは *実行して* （多くの場合、送信されたアセットを承認または拒否する）になることが期待されるためです。割り当ては、ユーザーが誰であるかを指定します。

通常、タスクノードには通知、割り当て、およびアクション（スクリプトで定義）が含まれます。 上の記事でより多くの通知とアクションについてを参照してください [ワークフローノード](/docs/7-1/user/-/knowledge_base/u/workflow-definition-nodes)。 タスクノードとその割り当てはより複雑で、独自の記事（これ）に値します。

はじめに、まだ行っていない場合は、ワークフローノードにタスクノードをドラッグアンドドロップします。 プロパティを開き、名前を付けます。 次に、タスクの[プロパティ]ペインで[ *アクション* ]をダブルクリックします。

通知を定義する（多くの場合、タスク担当者が適切です）か、タスクに対してトリガーされるアクションを定義するGroovyスクリプトを記述できます。

次に、タスクノードの割り当ての作成について説明します。


<!-- Task nodes are the most complex parts, and yet there's not much in this
section. Please describe an example here so users can understand what a task
node is for and how they are used. Include a script. -Rich -->

## 割り当て

ワークフロータスクはユーザーが完了する必要があります。 割り当てを構成する方法を選択できます。

![図1：タスクノードに割り当てを追加できます。](../../../images-dxp/workflow-designer-assignment.png)

特定のロール、ロールタイプの複数のロール（組織、サイト、または通常のロールタイプ）、アセット作成者、リソースアクション、または特定のユーザーへの割り当てを追加することを選択できます。 さらに、割り当てを定義するスクリプトを記述できます。

ロール、組織、またはアセット作成者へのタスクの割り当ては単純な概念ですが、ワークフロータスクをリソースアクションに割り当てるとはどういう意味ですか？ *UPDATE* リソースアクションを想像してください。 ワークフロー定義で割り当てにUPDATEアクションが指定されている場合、ワークフローで処理されているアセットのタイプを更新する権限を持つすべてのユーザーがタスクに割り当てられます。 タスクには複数の割り当てを設定できます。

### リソースアクションの割り当て

*リソースアクション* は、ユーザーがアプリケーションまたはエンティティに対して実行する操作です。 たとえば、ユーザーはメッセージボードメッセージを更新する権限を持っている場合があります。 ユーザーはリソースを更新できるため、これはUPDATEリソースアクションと呼ばれます。 それでもリソースアクションが不明な場合は、詳細な説明について、 [アクセス許可システム](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions) 開発者向けチュートリアルを参照してください。

構成されているすべてのリソースアクションを見つけるには、コントロールパネルのRoles Adminアプリケーションにアクセスする必要があります（つまり、Rolesリソースに対するVIEWアクションの権限が必要です）。

  - コントロールパネル→ユーザー→ロールに移動します。
  - 新しい通常の役割を追加します。 参照してください [役割管理についての記事](/docs/7-1/user/-/knowledge_base/u/roles-and-permissions) 詳細については、を。
  - ロールを追加したら、そのロールの[権限の定義]インターフェイスに移動します。
  - ワークフローの割り当てを定義するために使用するアクションを持つリソースを見つけます。

リソースアクションを見つけてからワークフローで使用する方法を教えてください。 リソースアクションの割り当てを設定するには、Kaleo Designerのインターフェースを使用します。

タスクノードの割り当てを構成するときに、割り当ての種類として[リソースアクション]を選択し、割り当てに使用するリソースアクション（たとえば、UPDATE）を指定します。

![図2：Kaleo Designerでリソースアクションの割り当てを構成します。](../../../images-dxp/workflow-designer-resource-action-assignment.png)

[ソース（ワークフローXML）]タブでの割り当ては次のようになります。

    <assignments>
        <resource-actions>
            <resource-action>UPDATE</resource-action>
        </resource-actions>
    </assignments>

通常どおり、ワークフローを適切なワークフロー対応アセットに割り当てます。

これで、ワークフローがリソースアクション割り当てを使用してタスクに進むと、リソース（たとえば、メッセージボードメッセージ）に対する `更新` 権限を持つユーザーにタスクが通知され、自分に割り当てることができます（通知が[タスク]に設定されている場合）譲受人）。 具体的には、ユーザーは、自分の *My Workflow Tasks* アプリケーションのタブ *Assigned to My Roles*タスクを表示します。

リソースアクション名にはすべて大文字を使用します。 一般的なリソースアクションは次のとおりです。

    UPDATE
    ADD
    DELETE
    VIEW
    PERMISSIONS
    SUBSCRIBE
    ADD_DISCUSSION

可能性のあるリソースアクション名は、そのリソースの権限画面から判断できます。 たとえば、メッセージボードでは、その画面に表示される権限の1つは *Add Discussion*です。 それをすべて大文字に変換し、スペースをアンダースコアに置き換えれば、アクション名がわかります。

### スクリプトによる割り当て

スクリプトを使用して割り当てを管理することもできます。 スクリプト化された単一の承認者ワークフロー定義（`single-approver-definition-scripted-assignment.xml`）のレビュータスク割り当てのスクリプトは次のとおりです。

    import com.liferay.portal.kernel.model.Group;
    import com.liferay.portal.kernel.model.Role;
    import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
    import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    
    long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
    
    long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));
    
    Group group = GroupLocalServiceUtil.getGroup(groupId);
    
    roles = new ArrayList<Role>();
    
    Role adminRole = RoleLocalServiceUtil.getRole(companyId, "Administrator");
    
    roles.add(adminRole);
    
    if (group.isOrganization()) {
        Role role = RoleLocalServiceUtil.getRole(companyId, "Organization Content Reviewer");
    
        roles.add(role);
    }
    else {
        Role role = RoleLocalServiceUtil.getRole(companyId, "Site Content Reviewer");
    
        roles.add(role);
    }
    
    user = null;

そのすべてのコードがあなたを威嚇させてはいけません。 それは、タスクを *管理者* 役割に割り当て、次にアセットの *グループ* が組織であるかどうかを確認し、それが *組織コンテンツレビューアー* 役割である場合はそれを割り当てるだけです。 そうでない場合は、タスクを *Site Content Reviewer* Roleに割り当てています。

`ロール=新しいArrayList<Role>（）;上記の` 行。 スクリプト化された割り当てでは、 `役割` 変数は、タスクが割り当てられる役割を指定する場所です。 たとえば、 `場合role.add（adminRole）;` が呼び出され、管理者ロールが割り当てに追加されます。


<!-- ## Related Topics [](id=related-topics)

[Workflow Forms](discover/portal/-/knowledge_base/7-1/workflow-forms)

[Using Workflow](discover/portal/-/knowledge_base/7-1/enabling-workflow)

[Liferay's Workflow Framework](/docs/7-1/tutorials/-/knowledge_base/t/liferays-workflow-framework)

[Creating Simple Applications](discover/portal/-/knowledge_base/7-1/creating-simple-applications) -->
