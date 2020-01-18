---
header-id: rest-builder-conventions
---

# REST Builder Liferay Conventions

[TOC levels=1-4]

Liferay's headless APIs follow several patterns and conventions to provide
consistency and uniformity in the APIs. 

Below is a list the most important ones. It's a living list. Improvements are
being made all the time, so check back to stay up to date on the changes. 

## YAML & OpenAPI restrictions

* *Tags are required*. We can't assign a class to a DELETE operation (doesn't
  return anything, doesn't receive an entity) so we need the tag to assign the
  method to a Java class.
* Responses must return a status code (default is not supported).
* Paths must be quoted.
* Paths only contain the method path (application and version are inherited
  from the JAX-RS application).

## Conventions

* We use path parameters for information that is required (like the id in
  a DELETE operation) and query parameters for optional information (filtering,
  sorting...)

* We don't expose `className`. If you need to return information like the
  `className`, use `contentType` keyword.

