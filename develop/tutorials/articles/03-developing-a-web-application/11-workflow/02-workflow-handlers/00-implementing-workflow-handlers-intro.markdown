# Handling Workflow [](id=handling-workflow)

The guestbook project's service layer is now updated to handle workflow. It now
properly sets the status fields for guestbooks and guestbook entries, gets
entities by their statuses, and sends entities to @product@'s workflow framework
whenever the `addGuestbook` or `addEntry` methods are called. Recall that you
still have an uncalled service method, `updateStatus`, for both entities. In
this section you'll implement workflow handlers, classes that interact with
@product@'s workflow framework and your service layer (by calling `updateStatus`
on the appropriate entity).

There's a handy abstract class you can extend to make the job easier, called
`BaseWorkflowHandler`. You'll do this next for both entities of the guestbook
project, starting with guestbooks.

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/creating-a-workflow-handler-for-guestbooks">Let's Go!<span class="icon-circle-arrow-right"></span></a>
