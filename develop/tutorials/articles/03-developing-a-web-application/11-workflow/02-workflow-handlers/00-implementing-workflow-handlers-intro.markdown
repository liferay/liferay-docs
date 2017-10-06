# Handling Workflow

The guestbook project's service layer is now updated to handle workflow. You're
properly setting the status fields for guestbooks and guestbook entries in the
service layer of the guestbook project, getting entities by their status, and
sending the entity to @product@'s workflow framework whenever the `addGuestbook`
or `addEntry` methods are called. Recall that you still have an uncalled
service method, `updateStatus`, for both entities. In this section you'll
implement workflow handlers, classes that interact with @product@'s workflow
framework and your service layer (by calling `updateStatus` on the appropriate
entity).

There's a handy abstract class you can extend to make the job easier, called
`BaseWorkflowHandler`. Do this for both entities of the guestbook project,
starting with guestbooks.

