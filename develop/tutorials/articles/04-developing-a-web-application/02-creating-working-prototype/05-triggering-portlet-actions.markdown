# Triggering Portlet Actions [](id=triggering-portlet-actions)

<div class="learn-path-step">
    <p>Developing Your First Portlet<br>Step 5 of 8</p>
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

To save a guestbook entry, you must trigger a portlet action. For this, you'll
create an action URL.

Add the following tag in `edit_entry.jsp` after the closing 
`</portlet:renderURL>` tag: 

    <portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

You now have the two required URLs for your form. 
