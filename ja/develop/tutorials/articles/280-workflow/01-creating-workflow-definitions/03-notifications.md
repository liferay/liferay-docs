---
header-id: workflow-notifications
---

# ワークフロー通知

[TOC levels=1-4]

アセットがワークフローにある間、レビュータスクが完了したときなど、特定のイベントについて関連するユーザーに通知されます。`<actions>`要素を持つ任意のワークフローノードに通知を含めることができます。

    <actions>
    <action>
    <notification>
    <name>Creator Modification Notification</name>
    <template>Your submission was rejected by ${userName}, please modify and resubmit.</template>
    <template-language>freemarker</template-language>
    <notification-type>email</notification-type>
    <notification-type>user-notification</notification-type>
    <execution-type>onAssignment</execution-type>
    </notification>
    </actions>
    </actions>

上記の作成者変更通知は、電子メール経由とユーザー通知（ユーザーのサイトの通知ウィジェットに移動します）の2つの方法で通知メッセージを送信します。メッセージはFreeMarkerテンプレートで定義され、タスク割り当てが作成されると送信されます。しかし、通知を受け取るのは誰でしょうか。`recipients`タグで明示的に受信者が指定されていない場合、アセットの作成者が通知を受け取ります。

## 通知オプション

`<notification>`で指定できる要素はいくつかあります。

**名前**：`<name>`要素に通知の名前を設定します。この情報は、ユーザーの個人用サイトの_[My Workflow Tasks]_ウィジェットに通知を表示するために使用されます。

**テンプレート**：`<template>`要素には、通知のメッセージが含まれています。構文は、使用しているテンプレートの言語によって決まります。

**テンプレート言語**：`<template-language>`タグで`freemarker`、`velocity`、またはプレーン`text`から選択します。

**通知タイプ**：`<notification-type>`タグで`email`、`user-notification`（通知ウィジェット経由）、`im`（インスタントメッセージ）、または`private-message`を送信するかを選択します。

    <notification-type>email</notification-type>

**実行タイプ**：タスクの割り当て時（`onAssignment`）、またはワークフロー処理がノードを離れるとき（`onExit`）に、通知の送信をノードへのエントリ（`onEntry`）にリンクすることを選択します。割り当て時に送信される通知を指定すると、担当者に自動的に通知されます。


**受信者**：`<recipients>`タグで通知の受信者を決定します。

    <recipients>
    [SEE BELOW FOR THE AVAILABLE RECIPIENT TAGS]
    </recipients>

使用可能な受信者タグは以下のとおりです。

- `<user>`：ワークフローを通じてアセットを送信したユーザーに通知します。
`<user />`としてタグを指定します。特定のユーザーに通知するには、`userId`を入力します。

       <recipients>
       <user />
       </recipients>
       <recipients>
       <user>
       <user-id>20139</user-id>
       </user
       </recipients>
   
- `<roles>`：IDまたはそのタイプと名前のいずれかによって、特定のロールに通知します。

       <recipients>
       <roles>
       <role>
       <role-id>33621</role-id>
       </role>
       </roles>
       </recipients>
       <recipients>
       <roles>
       <role>
       <role-type>regular</role-type>
       <name>Power User</name>
       <auto-create>false</auto-create>
       </role>
       </roles>
       </recipients>
   
- `<assignees />`：タスクの担当者に通知します。

- `<scripted-recipient>`：スクリプトを使用して通知の受信者を識別します。

       <recipients>
       <scripted-recipient>
       <script>
       <![CDATA[Script logic goes here]]>
       </script>
       <script-language>groovy</script-language>
       </scripted-recipient>
       </recipients>
   

通知タイプが`email`の場合、`<recipients>`タグの`recipientType`属性を_To_、_CC_、または_BCC_として指定できます。

    <recipients receptionType="cc">
    <roles>
    <role>
    <role-type>regular</role-type>
    <name>Manager</name>
    </role>
    </roles>
    </recipients>

デフォルトでは、`recipientType`は`to`です。

また、[スキーマで全詳細について](https://www.liferay.com/dtd/liferay-workflow-definition_7_1_0.xsd)ご確認ください。

