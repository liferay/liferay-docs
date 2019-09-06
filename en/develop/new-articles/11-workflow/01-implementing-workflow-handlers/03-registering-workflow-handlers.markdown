# Registering the Guestbook Portlet's Workflow Handlers 

Now that you have new `-WorkflowHandler`s, you need to register them with
Liferay. Add the following lines to `docroot/WEB-INF/liferay-portlet.xml`,
underneath the `<asset-renderer-factory>` declaration:

		<workflow-handler>com.liferay.docs.guestbook.workflow.EntryWorkflowHandler</workflow-handler>
	    <workflow-handler>com.liferay.docs.guestbook.workflow.GuestbookWorkflowHandler</workflow-handler>

Both the Guestbook App's entities are now prepared to be handled by the portal's
workflow. To make this work, the service layer still needs some updating to set
the initial workflow status, send the entity through the portal's workflow (if
any), and persist the status to the database after it's returned from the
workflow process.
