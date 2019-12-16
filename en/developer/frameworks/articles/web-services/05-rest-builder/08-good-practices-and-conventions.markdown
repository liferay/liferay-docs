---
header-id: rest-builder-conventions
---

# REST Builder Liferay Conventions

[TOC levels=1-4]

We follow several patterns and conventions in Headless APIs. Those conventions give us consistency and uniformity in the APIs. 

Here we list the most important ones. It's a living list, we remove restrictions every day and we try to improve existing patterns.

## YAML & OpenAPI restrictions

* *Tags are required*. We can't assign a class to a DELETE operation (doesn't return anything, doesn't receive an entity) so we need the tag to assign the method to a Java class.
* Responses have to return a status code (default is not supported).
* Paths must be quoted.
* Paths only contain the method path (application and version are inherited from the JAX-RS application).

## Conventions

* We use path parameters for information that is required (like the id in a DELETE operation) and query parameters for optional information (filtering, sorting...)
* We don't expose _className_, if you need to return information like the _className_, use _contentType_ keyword.