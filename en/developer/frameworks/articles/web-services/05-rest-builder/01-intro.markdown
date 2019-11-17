---
header-id: rest-builder
---

# REST Builder

[TOC levels=1-4]

REST Builder is @product@'s tool to build Headless and GraphQL APIs and is based on OpenAPI, following an [API first approach](../02-headless-apis/01-intro.markdown). First, we write the OpenAPI profile, then we use REST builder to generate the scaffolding and lastly we fill out the generated classes with our logic.

A good overview of the process is detailed [here](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/generating-apis-with-rest-builder).

We'll see each step in detail but first, let's talk about why we want to use REST Builder.

## Why we should use REST Builder

There are several reasons to prefer REST Builder over rolling our own [JAX-RS services](../03-jax/01-jaxrs-intro.markdown). Some of them are the following:

* Development speed: we avoid writing JAX-RS annotations, converters, add support for multipart or layers to organize our code. Everything is generated. 
* API scaffolding: pagination, filtering, searching, JSON writers, XML generation, even unit, and integration tests.
* GraphQL support out of the box: write your REST API and get a GraphQL endpoint for free.
* Integration with Liferay's authentication pipelines: Basic, OAuth, Cookie, CORS handling. You don't have to manually search for the user or the company, everything is already taken care of.
* JSON & XML support: APIs will return whichever format the consumer prefers.
* Consistency: all APIs follow the same rules and conventions, enforced by the REST builder.   