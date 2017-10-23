# Displaying Approved Workflow Items [](id=displaying-approved-workflow-items)

There's not much left to do. Both entities in the guestbook project's back-end
are workflow enabled, so it's time to update the UI. The Guestbook Admin portlet
and the Guestbook portlet each requires its own display strategy.

The Guestbook Admin application is accessed by administrators, so it can display
all guestbooks that have been submitted, even if they're not marked as approved.
However, adding a *Status* field to the search container makes sense. That way
admins can see which guestbooks are already approved, which are drafts, which
are pending, etc.

The Guestbook application is meant to be viewed by site members and even guests
(unauthenticated users of your site). Here it's smart to display only approved
guestbooks and approved entries.

Start by updating the Guestbook Admin UI.

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/displaying-guestbook-status">Let's Go!<span class="icon-circle-arrow-right"></span></a>
