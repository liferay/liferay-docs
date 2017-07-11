# Triggering Portlet Actions

As already stated, portlets run in a portion of a page. A page can contain
multiple portlets. Because of this, portlets have *phases* of operation. Here,
you'll learn about the most important two. The first phase is the one you've
already used: it's called the *render* phase. All this means is that the portlet
draws itself, using the JSPs you write for it.

The other phase is called the *action* phase. This phase runs once, when a user
triggers a portlet action. The portlet performs whatever action the user
triggered, such as performing a search or adding a record to a database. Then,
based on what happened in the action, the portlet goes back to the render phase
and re-renders itself according to its new state.

To save a guestbook entry, you must trigger a portlet action. For this, you'll
create an action URL.

1. Add the following tag after in `edit_entry.jsp` the closing `</portlet:renderURL>` tag:

        <portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

You now have the two required URLs for your form.
