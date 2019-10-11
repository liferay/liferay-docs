---
header-id: handling-workflow
---

# Handling Workflow

[TOC levels=1-4]

The guestbook project's service layer is now updated to handle workflow. It now
properly sets the status fields for guestbooks and guestbook entries, gets
entities by their statuses, and sends entities to @product@'s workflow framework
whenever the `addGuestbook` or `addGuestbookEntry` methods are called. Recall
that you still have an uncalled service method, `updateStatus`, for both
entities. Now you'll implement workflow handlers, classes that interact with
@product@'s workflow framework and your service layer (by calling `updateStatus`
on the appropriate entity).

There's a handy abstract class you can extend to make the job easier, called
`BaseWorkflowHandler`. You'll do this next for both entities of the guestbook
project, starting with guestbooks.

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/creating-a-workflow-handler-for-guestbooks">Let's Go!<span class="icon-circle-arrow-right"></span></a>
