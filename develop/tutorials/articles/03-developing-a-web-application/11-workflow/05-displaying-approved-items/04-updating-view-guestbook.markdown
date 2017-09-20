# Updating the View Layer to Display Approved Guestbooks 

Open `view.jsp`, and find this method call:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId);

Replace it with this one:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

All you did here was add the parameter that marks a `Guestbook`
as approved in the workflow.

Now open the `view_search.jsp`, located in `docroot/html/guestbook`. Find the
scriptlet, denoted by `<%`,that comes right after the closing `</aui:form>` tag.
As in the `view.jsp`, replace the current line starting with `List<Guestbook>
guestbooks...` with this one:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

Now both entities are enabled for workflow, and the Guestbook portlet's view
layer is modified accordingly. But what about the Guestbook Admin Portlet that's
accessed through the portal's Site Administration section? What should the
strategy be there?

