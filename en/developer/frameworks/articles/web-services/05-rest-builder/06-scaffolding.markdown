---
header-id: rest-builder-scaffolding
---

# REST Builder Scaffolding

[TOC levels=1-4]

Apart from the JAX-RS annotations, basic structure and GraphQL support, there are some other things the REST Builder provides for us. Let's see them:

## Context fields

The ResourceImpl classes are a fully JAX-RS compliant resource, you can add @Context injections, mix JAX-RS endpoints or use all the full power of JAX-RS standard.

And, from the beginning, REST Builder injects several fields (as protected fields in the base class) to help you implement new APIs, like these:

* contextAcceptLanguage, with the current Locale.
* contextCompany, that contains the current _Company_.
* contextHttpServletRequest, with the _HttpServletRequest_.
* contextHttpServletResponse, with the _HttpServletResponse_.
* contextUser, that contains the current logged-in User.
* contextUriInfo, with the UriInfo information (paths, endpoints).

## Automatic transactions

One, usually unknown feature, of the REST Builder is that wraps our API calls in transactions if the HTTP verb used is POST, PUT, PATCH or DELETE (doesn't apply for GET operations). 

If you need to do several service builder calls in a sequence, you don't have to wrap them in a transaction, it will be done automatically. The transaction will commit if no exception is thrown and rollback if an exception bubbles up to the resource implementation method.

## Test generation

If you specify a _testDir_ property like this:

    testDir: "../headless-admin-taxonomy-test/src/testIntegration/java"
    
REST Builder will generate integration tests under the -test module. Those tests will check the API with a REST client and check the response, doing an end-to-end test involving all the steps: from parsing the request to returning JSON.

The generated tests are ignored by default and, depending on the path, will force you to implement a creation method (to be able to add content to either update/delete or retrieve it).

There are a lot of examples [here](https://github.com/liferay/liferay-portal/tree/master/modules/apps/headless/headless-delivery/headless-delivery-test).      

## Client generation

If you specify a _clientDir_ property like this:

    clientDir: "../headless-admin-taxonomy-client/src/main/java"
    
REST Builder will create a Java, typed, client to interact with the APIs with static methods. The client project will have all the methods to call your paths and will do the parsing of the requests and responses.

## Common utilities

The REST Builder provides us with several JAX-RS utilities, from exception mappers for the most common exceptions in the portal, XML and JSON Body Readers/Writers, Site validation (that works with siteId and siteKey) and support for Bean Validation.

And there are also some utility libraries that can be useful when developing new APIs:

* ContentLanguageUtil, to deal with the _ContentLanguage_ header.
* JaxRsLinkUtil, to create links between APIs.
* LocalDateTimeUtil, to transform between date formats.
* LocalizedMapUtil, to create maps with locales as keys.
* SearchUtil, to help using the search framework to return Pages of content.
* TransformUtil, to deal with lambdas and conversions.