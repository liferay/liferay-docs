---
header-id: workflow-task-nodes
---

# ワークフロータスクノード

[TOC levels=1-4]

タスクノードは、ワークフロー定義の基本的な部分です。組織のビジネスプロセスを定義し、対応するワークフローを設計するときは、まずタスクを想定すると思います。名名前が示すように、タスクは*作業*が行われるワークフローの一部です。ユーザーは役割を持ち、提出されたアセットと相互作用する必要があります。ユーザーは多くの場合、レビューアの役割を果たし、ワークフローのアセットが公開するのに適しているか、またはさらに作業が必要かを判断します。

他のワークフローノードとは異なり、タスクノードには割り当てがあります。これは、ワークフロープロセスがタスクノードに入ったときにユーザーが*何らかの操作を行う*（大抵 は、送信されたアセットの承認または拒否）ことが期待されるためです

一般に、タスクノードには、タスクタイマー、割り当て、アクション（通知とスクリプトが含まれる場合があります）、および遷移が含まれます。通知とアクションはタスクノードに限定されませんが、タスクノードとその割り当てはそれぞれ独自の記事（この記事）に値するものです。

単一承認者の定義のレビュータスクを確認し、簡潔にするためにいくつかの`<role>`タグが次のスニペットから除外されていることに注意してください。

    <task>
    <name>review</name>
    <actions>
    <notification>
    <name>Review Notification</name>
    <template>${userName} sent you a ${entryType} for review in the workflow.</template>
    <template-language>freemarker</template-language>
    <notification-type>email</notification-type>
    <notification-type>user-notification</notification-type>
    <execution-type>onAssignment</execution-type>
    </notification>
    <notification>
    <name>Review Completion Notification</name>
    <template><![CDATA[Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.]]></template>
    <template-language>freemarker</template-language>
    <notification-type>email</notification-type>
    <recipients>
    <user />
    </recipients>
    <execution-type>onExit</execution-type>
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
    <transitions>
    <transition>
    <name>approve</name>
    <target>approved</target>
    </transition>
    <transition>
    <name>reject</name>
    <target>update</target>
    <default>false</default>
    </transition>
    </transitions>
    </task>

レビュータスクには2つの`actions`があります。両方とも`<notification>`です。各通知には、名前、テンプレート、通知タイプ、実行タイプ、および受信者を含めることができます。通知に加えて、`<action>`タグも使用できます。
これらには名前と[スクリプト](/docs/7-1/user/-/knowledge_base/u/leveraging-the-script-engine-in-workflow)があり、タスクよりも状態ノードで頻繁に使用されます。

## 割り当て

ワークフロータスクはユーザーが完了します。割り当てにより、適切なユーザーがタスクにアクセスできるようになります。割り当ての構成方法を選択できます。

特定のロール、ロールタイプ（組織、サイト、または通常のロールタイプ）の複数のロール、アセット作成者、リソースアクション、または特定のユーザーに割り当てを追加することを選択できます。さらに、割り当てを定義するスクリプトを作成できます。例については、`single-approver-definition-scripted-assignment.xml`を参照してください

    <assignments>
    <roles>
    <role>
    <role-type>organization</role-type>
    <name>Organization Administrator</name>
    </role>
    </roles>
    </assignments>

上記の割り当ては、組織管理者がタスクを完了する必要があることを指定しています。

    <assignments>
    <user>
    <user-id>20156</user-id>
    </user>
    </assignments>

上記の割り当ては、ユーザーIDが20156のユーザーのみがタスクを完了できることを指定しています。または、ユーザーの`<screen-name>`または`<email-address>`を指定します。

    <assignments>
    <scripted-assignment>
    <script>
    <![CDATA[
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
    ]]>
    </script>
    <script-language>groovy</script-language>
    </scripted-assignment>
    </assignments>

上記の割り当てにより、タスクが*管理者*ロールに割り当てられ、アセットの*グループ*が組織であるかどうかが確認されます。そうである場合、*組織コンテンツレビューア*のロールがそれに割り当てられます。そうでない場合、タスクは*サイトコンテンツレビューア*のロールに割り当てられます。

上記の`roles = new ArrayList<Role>();`の行に注目してください。スクリプト化された割り当てでは、`roles`変数はタスクが割り当てられるロールを指定する場所です。たとえば、`roles.add(adminRole);`が呼び出されると、管理者ロールが割り当てに追加されます。

ロール、組織、またはアセット作成者へのタスクの割り当ては簡単な概念ですが、ワークフロータスクをリソースアクションに割り当てるとはどういう意味でしょうか。
*UPDATE*リソースアクションについて考えてみます。ワークフロー定義で割り当てにUPDATEアクションが指定されている場合、ワークフローで処理されているアセットのタイプを更新する権限を持つユーザーがタスクに割り当てられます。1つのタスクに複数の割り当てを構成できます。

## リソースアクションの割り当て

*リソースアクション*は、ユーザーがアプリケーションまたはエンティティで実行する操作です。たとえば、ユーザーにはメッセージボードのメッセージを更新する権限があります。ユーザーはリソースを更新できるため、これはUPDATEリソースアクションと呼ばれます。リソースアクションが不明な場合は、[権限システム](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions)の開発者向けチュートリアルで詳細な説明を参照してください。

作成されているすべてのリソースアクションを検索するには、コントロールパネルの[Roles Admin]アプリケーションにアクセスする必要があります（つまり、ロールリソースのVIEWアクションの許可が必要です）。

- [コントロールパネル] &rarr; [Users] &rarr; [Roles]に移動します。
- 新しい通常のロールを追加します。詳細については、[ロールの管理に関する記事](/docs/7-1/user/-/knowledge_base/u/roles-and-permissions)を参照して ください。
- ロールを追加したら、ロールの[Define Permissions]インターフェイスに移動します。
- アクションがワークフローの割り当てを定義するリソースを見つけます。

割り当てのXMLは次のようになります。

    <assignments>
    <resource-actions>
    <resource-action>UPDATE</resource-action>
    </resource-actions>
    </assignments>

これで、ワークフローがリソースアクションが割り当てられたタスクに進むと、リソース（メッセージボードのメッセージなど）に対する`UPDATE`権限を持つユーザーにタスクが通知され、ユーザー自身にタスクを割り当てることができます（通知がタスクの[Task Assignees]に設定されている場合）。具体的には、ユーザーは、*[Assigned to My Roles]*タブの下にある*[My Workflow Tasks]*アプリケーションでタスクを確認できます。

リソースアクション名にはすべて大文字を使用します。一般的なリソースアクションを次に示します。

    UPDATE
    ADD
    DELETE
    VIEW
    PERMISSIONS
    SUBSCRIBE
    ADD_DISCUSSION

リソースの許可画面から、考えられるリソースアクション名を判断します。たとえば、メッセージボードでは、その画面に表示される権限の1つが*ディスカッションの追加*です。それをすべて大文字に変換し、スペースをアンダースコアに置き換えれば、アクション名が得られます。

## タスクタイマー

タスクタイマーは、指定された時間が経過するとアクションをトリガーします。タイマーは、タスクが長時間放置されないようにする場合に便利です。利用可能なタイマーアクションには、追加の通知の送信、アセットの再割り当て、タイマーアクションの作成などがあります。

    <task-timers>
    <task-timer>
    <name></name>
    <delay>
    <duration>1</duration>
    <scale>hour</scale>
    </delay>
    <blocking>false</blocking>
    <recurrence>
    <duration>10</duration>
    <scale>minute</scale>
    </recurrence>
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

上記のタスクタイマーは通知を作成します。`<delay>`タグで期間を指定し、`<timer-actions>`ブロックで時間が経過したときに実行するアクションを指定します。`<blocking>`要素は、タイマーアクションを繰り返すかどうかを指定します。ブロッキングが`false`に設定されている場合、タイマーアクションを繰り返すことができます。`recurrence`要素では、上記のように、`duration`と`scale`を使用して繰り返し間隔を指定します。上記の繰り返し要素は、タイマーアクションが初回の発生後10分ごとに再度実行されるよう指定しています。
ブロッキングをtrueに設定すると、タイマーアクションが繰り返されなくなります。

    <timer-actions>
    <reassignments>
    <assignments>
    <roles>
    <role>
    <role-type></role-type>
    <name></name>
    </role>
    ...
    </roles>
    </assignments>
    </reassignments>
    </timer-actions>

上記のスニペットは、再割り当てアクションを設定する方法を示しています。

`<action>`要素と同様に、`<timer-action>`要素にはスクリプトを含めることができます。

    <timer-actions>
    <timer-action>
    <name>doSomething</name>
    <description>Do something cool when time runs out.</description>
    <script>
    ...
    </script>
    <script-language>groovy</script-language>
    </timer-action>
    </timer-actions>

上記の例は機能しませんが、タスクタイマーに`<script>`をセットアップする方法を示しています。
詳細については、[ワークフローのスクリプト作成に関する記事をご覧ください](/docs/7-1/user/-/knowledge_base/u/leveraging-the-script-engine-in-workflow)。

**注：**`timer-action`には、`execution-type`を除き、`action`と同じタグをすべて含めることができます。タイマーアクションは、時間が経過すると常にトリガーされるため、たとえば、実行タイプ`onEntry`を指定しても、タイマー内では意味がありません。

タスクはワークフロー定義の中核です。タスクと他の[ワークフローノード](/docs/7-1/tutorials/-/knowledge_base/t/workflow-definition-nodes)の作成方法とノード間の移行の追加方法を理解したら、次にワークフローウィザードの機能について見ていきましょう。
