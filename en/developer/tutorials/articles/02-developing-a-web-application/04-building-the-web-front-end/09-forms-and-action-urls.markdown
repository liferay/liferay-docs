---
header-id: forms-and-action-urls
---

# Forms and Action URLs

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Building the Web Front-End/p><p>Step 8 of 11</p>
</div>

Recall that portlets run in a portion of a page, and a page can contain multiple 
portlets. Because of this, portlets have *phases* of operation. Here, you'll 
learn about the most important two. The first phase is the one you've already 
used: the *render* phase. All this means is that the portlet draws itself, using 
the JSPs you write for it. 

The other phase is called the *action* phase. This phase runs once, when a user
triggers a portlet action. The portlet performs whatever action the user
triggered, such as performing a search or adding a record to a database. Then
the portlet goes back to the render phase and re-renders itself according to its
new state.

## Action URLs

To save a guestbook entry, you must trigger a portlet action. For this, you'll
create an action URL.

Add the following tag in `edit_entry.jsp` after the closing 
`</portlet:renderURL>` tag: 

```markup
<portlet:actionURL name="addEntry" var="addEntryURL" />
```

You now have the two required URLs for your form. 

## Forms

The form for creating guestbook entries has three fields: one for the name of the
person submitting the entry, one for the person's email address, and one for the
entry itself. 

Add the following tags to the end of your `edit_entry.jsp` file: 

```markup
<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

<aui:model-context bean="<%= entry %>" model="<%= GuestbookEntry.class %>" />

	<aui:fieldset>

		<aui:input name="name" />
		<aui:input name="email" />
		<aui:input name="message" />
		<aui:input name="entryId" type="hidden" />
		<aui:input name="guestbookId" type="hidden" value='<%= entry == null ? guestbookId : entry.getGuestbookId() %>'/>

	</aui:fieldset>

	<aui:button-row>

		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>

	</aui:button-row>
</aui:form>
```

Liferay uses its Alloy UI tag library to create forms. 

Save `edit_entry.jsp` and redeploy your application. If you refresh the page and
click the *Add Entry* button, your form appears. If you click the *Cancel*
button, you go back to `view.jsp`, but don't try the *Save* button yet. You
haven't yet created the action that saves a guestbook entry, so clicking *Save*
produces an error.

![Figure 1: This is the Guestbook application's form for adding entries.](../../../images/first-guestbook-portlet-edit-entry.png)

Implementing portlet actions (what happens when the user clicks *Save* or
*Delete*) is your next task. 
