# Approving Content with Workflow

The Guestbook application accepts submissions from guest users, so there's no
telling what what will end up on site pages with the Guestbook deployed. Illegal
data, objectionable content, the entire contents of Don Quixote, all of these
and more are possibilities. Ensure user created content is reviewed and approved
by enabling workflow in your application. 

This Learning Path instructs the reader in workflow-enabling the Guestbook
App's `Guestbook` and `Entry` entities to ensure that only approved content is
published after review.

![Figure 1: Enable workflow in your assets, just like @product@'s own assets.](../../../images/workflow-config.png)

There are five basic steps to enabling workflow:

1. Update the service layer to set each entity's status fields.
2. Send the entity to @product@'s workflow framework.
3. Add *getter* methods that account for an entity's workflow status.
4. Handle the entity as it returns from the workflow framework.
5. Update the user interface to account for workflow status.

The first three steps happen in the service layer, so that's a good place to
start.

