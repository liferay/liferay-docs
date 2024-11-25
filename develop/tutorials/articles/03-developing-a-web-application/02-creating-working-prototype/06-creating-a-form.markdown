---
header-id: creating-a-form
---

# Creating a Form

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 6 of 8</p>
</div>

The form for creating guestbook entries is pretty simple. All you need are two 
fields: one for the name of the person submitting the entry, and one for the 
entry itself. 

Add the following tags to the end of your `edit_entry.jsp` file: 

    <c:set var="portletNamespace"><portlet:namespace/></c:set>
    <aui:form action="<%= addEntryURL %>" name="${portletNamespace}fm">
            <aui:fieldset>
                <aui:input name="name"></aui:input>
                <aui:input name="message"></aui:input>
            </aui:fieldset>

            <aui:button-row>
                <aui:button type="submit"></aui:button>
                <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
            </aui:button-row>
    </aui:form>

Save `edit_entry.jsp` and redeploy your application. If you refresh the page and
click the *Add Entry* button, your form appears. If you click the *Cancel* 
button, it works! However, don't try the *Save* button yet. You haven't yet
created the action that saves a guestbook entry, so clicking *Save* produces an
error.

![Figure 1: This is the Guestbook application's form for adding entries.](../../../images/first-guestbook-portlet-edit-entry.png)

Implementing the portlet action (what happens when the user clicks *Save*) is
your next task. 
