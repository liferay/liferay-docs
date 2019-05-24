---
header-id: data-access
---

# Data Access

@product@'s data can be retrieved using its APIs. It's important, however, to 
understand how data is used amid all of @product@'s constructs. At a basic 
level, all the data is represented by an object model. This is retrieved from 
the database and automatically mapped from SQL to the model by 
[Service Builder](/docs/7-0/tutorials/-/knowledge_base/t/service-builder). 
Using 
[Model Listeners](/docs/7-0/tutorials/-/knowledge_base/t/model-listeners), 
you can listen for events on these models and take action when they are stored 
or retrieved. 

All data in @product@ is 
[scoped](/docs/7-0/tutorials/-/knowledge_base/t/data-scopes) 
to a context: a site, a page, or global. When storing data in your application, 
you should therefore take advantage of scope so your application integrates 
well with the system and users can add your application in whatever scope they 
need it. 

Read on to learn how to do both of these things. 
