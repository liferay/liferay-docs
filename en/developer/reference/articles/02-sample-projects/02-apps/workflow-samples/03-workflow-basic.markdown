---
header-id: workflow-application
---

# Workflow Application

[TOC levels=1-4]

The
[`basic`](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/apps/workflow/basic)
sample demonstrates workflow enabling an entity that is not an asset.

To see the Workflow sample in action, complete the following steps:

1.  Add the sample widget to a page by navigating to *Add*
    (![Add](../../../../images/icon-add.png)) &rarr; *Widgets* &rarr; *Sample*
    &rarr; *Workflow Basic* and dragging it to the page.

2.  Go to *Control Panel* &rarr; *Workflow* &rarr; *Process Builder* &rarr;
    *Configuration* and assign a workflow to the `Baz` entity.

3.  Select the app's *Add* button and add an entity. Do this several times to
    create multiple entities.

4.  Go to *User* &rarr; *My Workflow Tasks* &rarr; *Assigned to My Roles* and
    assigned the task to me and Approve the Task.

Now you've taken the entity and successfully run it through a workflow.

## What API(s) and/or code components does this sample highlight?

This sample demonstrates @product@'s Workflow Handler API.  Specifically, it
demonstrates how to create a `WorkflowHandler` for your custom entity.

## How does this sample leverage the API(s) and/or code component?

The basic implementation of `WorkflowHandler` is done via extension of the
`BaseWorkflowHandler` class. This is where the sample leverages the basic
methods required for the entity's `WorkflowHandler`.

```java
@Override
public String getClassName() {
  return Baz.class.getName();
}

@Override
public String getTitle(long classPK, Locale locale) {
  return String.valueOf(classPK);
}

@Override
public String getType(Locale locale) {
  return ResourceActionsUtil.getModelResource(locale, getClassName());
}

@Override
public Baz updateStatus(
    int status, Map<String, Serializable> workflowContext)
  throws PortalException {

  long userId = GetterUtil.getLong(
    (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

  long classPK = GetterUtil.getLong(
    (String)workflowContext.get(
      WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

  return _bazLocalService.updateStatus(userId, classPK, status);
}
```

For more information on the workflow framework, visit its dedicated
[documentation](/docs/7-2/frameworks/-/knowledge_base/f/the-workflow-framework).
