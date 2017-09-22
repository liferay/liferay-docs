# Implementing Asset Renderers [](id=implementing-asset-renderers)

Assets are generic versions of entities, so they contain fields like `title`,
`description`, and `summary`. @product@ uses these fields to display assets. 
Asset Renderers translate an entity into an asset via these fields. For 
@product@ to display your entities as assets, you must therefore create and 
register Asset Renderer classes for your guestbook and guestbook entry entities. 
Without these classes, @product@ can't display your entities in Asset Publisher, 
Notifications, Activities, or anywhere else that displays assets. 

Your next task is to create these Asset Renderers. Ready to begin? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/implementing-a-guestbook-asset-renderer">Let's Go!<span class="icon-circle-arrow-right"></span></a>
