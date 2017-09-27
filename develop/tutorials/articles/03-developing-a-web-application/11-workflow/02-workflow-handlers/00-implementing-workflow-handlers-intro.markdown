# Handling Workflow

After the last section, you're properly setting the status fields for guestbooks
and guestbook entries in the service layer of the guestbook project. If you
recall, you also have an unused method, `updateStatus`, for both entities. In
this section you'll learn to implement workflow handlers, classes that interact
with @product@'s workflow framework and your service layer (by calling
`updateStatus` on the appropriate entity).

There's a handy abstract class you can extend to make the job easier, called
`BaseWorkflowHandler`. Do this for both entities of the Guestbook Application,
starting with guestbooks.

