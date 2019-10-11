---
header-id: implementing-asset-renderers
---

# Implementing Asset Renderers

[TOC levels=1-4]

Assets are display versions of entities, so they contain fields like `title`,
`description`, and `summary`. @product@ uses these fields to display assets. 
Asset Renderers translate an entity into an asset via these fields. You must
therefore create and register Asset Renderer classes for your guestbook and
guestbook entry entities. Without these classes, @product@ can't display your
entities in Asset Publisher, Notifications, Activities, or anywhere else that
displays assets. 

Your next task is to create these Asset Renderers. Ready to begin? 

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/implementing-a-guestbook-asset-renderer">Let's Go!<span class="icon-circle-arrow-right"></span></a>
