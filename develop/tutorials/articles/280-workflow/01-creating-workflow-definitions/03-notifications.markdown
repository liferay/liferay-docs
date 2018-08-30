# Workflow Notifications

While an asset is in a workflow, it's important that relevant Users are notified
about certain events, like when a review task is completed. Any workflow node
that can take an `<actions>` element can have notifications.

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

The above Creator Modification Notification sends a notification message in two
ways: via email and via user notification (this goes to the Notifications widget
in the User's site). The message is defined in a FreeMarker template and sent
once a task assignment is created. But who receives the notification? If no
recipients are explicitly specified via a `recipients` tag, the asset's creator
receives the notification.

## Notification Options

There are several elements that can be specified in a `<notification>`:

**Name**
: Set the name of the notification in the `<name>` element. This information is
used to display the notification in the _My Workflow Tasks_ widget of a User's
personal site.

**Template**
: The `<template>` element contains the actual message of the notification. The
syntax is determined by the template language you're using. 

**Template Language**
: Choose from `freemarker`, `velocity`, or plain `text` in the
`<template-language>` tag. 

**Notification Type**
: Choose whether to send an `email`, `user-notification` (via the Notification
widget), `im` (instant message), or `private-message` in the
`<notification-type>` tag.

        <notification-type>email</notification-type>

**Execution Type**
: Choose to link the sending of the notification to entry into the node
(`onEntry`), when a task is assigned (`onAssignment`), or when the workflow
processing is leaving a node (`onExit`). If you specify a notification to
be sent on assignment, the assignee is automatically notified.

**Recipients**
: Decide who should receive the notification in the `<recipients>` tag:

        <recipients>
            [SEE BELOW FOR THE AVAILABLE RECIPIENT TAGS]
        </recipients>

Available recipient tags are 

- `<user>`: notify the User that sent the asset through the workflow. Simply
    specify the tag as `<user />`. To notify a specific user, enter the
    `userId`:

        <recipients>
            <user />
            <user>
                <user-id>20139</user-id>
            </user
        </recipients>

- `<roles>`: notify specific roles, either by ID or by their type and name.

        <recipients>
            <roles>
                <role>
                    <role-id>33621</role-id>
                </role>
                <role>
                    <role-type>regular</role-type>
                    <name>Power User</name>
                    <auto-create>false</auto-create>
                </role>
            </roles>
        </recipients>

- `<assignees />`: notify the task assignees.

- `<scripted-recipient>`: use a script to identify notification recipients.

        <recipients>
            <scripted-recipient>
                <script>
                    <![CDATA[Script logic goes here]]>
                </script>
                <script-language>groovy</script-language>
            </scripted-recipient>
        </recipients>


If the notification type is `email`, you can specify the `recipientType`
attribute of the `<recipients>` tag as _To_, _CC_, or _BCC_.

    <recipients receptionType="cc">
        <roles>
            <role>
                <role-type>regular</role-type>
                <name>Manager</name>
            </role>
        </roles>
    </recipients>

By default, `recipientType` is `to`.

As always, read the 
[schema for all the details](https://www.liferay.com/dtd/liferay-workflow-definition_7_1_0.xsd).

