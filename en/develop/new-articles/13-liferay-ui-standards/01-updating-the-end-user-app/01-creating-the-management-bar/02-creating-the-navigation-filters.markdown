# Creating the Navigation Filters [](id=creating-the-navigation-filters)

Now it's time to create the Management Bar
Navigation Filters. The Navigation Filters let you filter the search container
results by scope. For the purposes of the Guestbook app, you'll just create one 
navigation filter for the default view. 

Go ahead and get started.

## Creating the Navigation Filter [](id=creating-the-navigation-filter)

The default filter displays all the results, you'll add this UI to the Guestbook.

Open the `guestbook-web` module project's `view.jsp`, add the  
`<liferay-frontend:management-bar-filters>` taglib just below the 
`</liferay-frontend:management-bar-buttons>` tags, and give it the following 
configuration:

    <liferay-frontend:management-bar-filters>
      <liferay-frontend:management-bar-navigation
        navigationKeys='<%= new String[] {"all"} %>'
        portletURL="<%= viewPageURL %>"
      />
    </liferay-frontend:management-bar-filters>

This adds the `all` option to the management bar navigation filters menu.
    
The Management Bar should now match the figure below:

![Figure 1: The Navigation Filters let you filter the guestbook entries by scope.](../../../../../images/navigation-filters-added.png)

In the next section, you'll add the Sort Filters for the Management Bar.
