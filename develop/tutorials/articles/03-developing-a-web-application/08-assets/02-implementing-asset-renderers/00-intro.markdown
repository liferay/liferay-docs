# Implementing Asset Renderers [](id=implementing-asset-renderers)

You must create asset renderer classes for your guestbook and guestbook entry
entities and register them with @product@. Without these classes, @product@
can't display your entities in Asset Publisher, Notifications, Activities, or
anywhere else. 

Assets are generic versions of entities, so they contain such fields as `title`,
`description`, and `summary`. @product@ uses these fields to display assets,
and to do that, there must be a translation layer that translates the entity
into an asset. Asset Renderers do exactly that. 

Your next task is to create Asset Renderers for `Guestbook`s and `Entry`s. Ready
to begin? 
