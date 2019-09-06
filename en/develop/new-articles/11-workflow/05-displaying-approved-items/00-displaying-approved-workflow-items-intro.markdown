# Displaying Approved Workflow Items 

To display only approved entities, you'll add a new *finder*, then a *getter*
that uses it in your service layer. The new methods will serve the purpose of
getting only entities with the proper workflow status. Then you can display only
those entities in the portlet's search container. Service Builder makes this
easy.

