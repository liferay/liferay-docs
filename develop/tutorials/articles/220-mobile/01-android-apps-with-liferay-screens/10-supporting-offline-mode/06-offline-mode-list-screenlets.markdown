---
header-id: supporting-offline-mode-in-list-screenlets
---

# Supporting Offline Mode in List Screenlets

[TOC levels=1-4]

A list Screenlet is a special type of read Screenlet that displays entities in a 
list. Recall from the 
[list Screenlet creation tutorial](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-list-screenlets) 
that list Screenlets have a model class that encapsulates entities retrieved
from the server. To support offline mode, a list Screenlet's event class must
extend `ListEvent` with the model class as a type argument. This event class
also needs three things: 

1. A default constructor 
2. A `getListKey` method that returns a unique ID to store the entity with 
3. A `getModel` method that returns the model instance 

The list Screenlet creation tutorial contains example model and event classes 
that support offline mode for Bookmark List Screenlet. Click the following links 
to see the sections in the tutorial that show you how to create these classes: 

- [Creating the Model Class](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-model-class) 
- [Creating the Event](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor-0#creating-the-screenlets-event)

## Related Topics

[Supporting Offline Mode](/docs/7-1/tutorials/-/knowledge_base/t/supporting-offline-mode)

[Creating Android List Screenlets](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-list-screenlets)
