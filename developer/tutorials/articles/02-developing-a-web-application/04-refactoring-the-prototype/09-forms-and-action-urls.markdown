---
header-id: forms-and-action-urls
---

# Forms and Action URLs

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Developing Your First Portlet</p><p>Step 5 of 8</p>
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
<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>
```

You now have the two required URLs for your form. 

## Forms

The form for creating guestbook entries has two fields: one for the name of the
person submitting the entry and one for the entry itself. 

Add the following tags to the end of your `edit_entry.jsp` file: 

```markup
<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
        <aui:fieldset>
            <aui:input name="name"></aui:input>
            <aui:input name="message"></aui:input>
        </aui:fieldset>

        <aui:button-row>
            <aui:button type="submit"></aui:button>
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
        </aui:button-row>
</aui:form>
```

Save `edit_entry.jsp` and redeploy your application. If you refresh the page and
click the *Add Entry* button, your form appears. If you click the *Cancel*
button, you go back to `view.jsp`, but don't try the *Save* button yet. You
haven't yet created the action that saves a guestbook entry, so clicking *Save*
produces an error.

![Figure 1: This is the Guestbook application's form for adding entries.](../../../images/first-guestbook-portlet-edit-entry.png)

Implementing the portlet action (what happens when the user clicks *Save*) is
your next task. 
