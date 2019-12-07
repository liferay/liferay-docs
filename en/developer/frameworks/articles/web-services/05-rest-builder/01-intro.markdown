---
header-id: rest-builder
---

# REST Builder

[TOC levels=1-4]

REST Builder is @product@'s tool to build REST and GraphQL APIs. It's based
on OpenAPI, following an 
[API-first approach](/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis). 

Using REST Builder takes only three steps: 

1. Write the OpenAPI profile. 

2. Use REST builder to generate the scaffolding. 

3. Fill out the generated classes with your logic.

A good overview of the process is detailed [here](https://portal.liferay.dev/docs/7-2/appdev/-/knowledge_base/a/generating-apis-with-rest-builder).

We'll see each step in detail but first, let's talk about why we want to use
REST Builder.

## Why we should use REST Builder

There are several reasons to prefer REST Builder over rolling our own [JAX-RS
services](../03-jax/01-jaxrs-intro.markdown). Some of them are the following:

* Development speed: you avoid writing JAX-RS annotations, converters, adding
  support for multipart or layers to organize your code. Everything is generated. 
* API scaffolding: pagination, filtering, searching, JSON writers, XML
  generation, even unit, and integration tests are generated.
* GraphQL support out of the box: write your REST API and get a GraphQL endpoint
  for free.
* Integration with Liferay's authentication pipelines: Basic, OAuth, Cookie,
  CORS handling. You don't have to search manually for the user or the company;
  everything is already there.
* JSON & XML support: APIs return whichever format the consumer prefers.
* Consistency: all APIs follow the same rules and conventions, enforced by 
  REST builder.
