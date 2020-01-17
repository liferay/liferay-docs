---
header-id: rest-builder-scaffolding
---

# REST Builder Scaffolding

[TOC levels=1-4]

Apart from the JAX-RS annotations, basic structure and GraphQL support, there
are some other things the REST Builder provides:

- Context Fields
- Automatic Transactions
- Test Generation
- Client Generation
- Common Utilities

These are useful and time saving additions to your development cycle. 

## Context fields

The `ResourceImpl` classes are JAX-RS-compliant resources. You can add
`@Context` injections, mix JAX-RS endpoints, and use full power of the JAX-RS
standard.

REST Builder injects several fields (as protected fields in the base class) to
help you implement new APIs, like these:

* `contextAcceptLanguage`, containing the current `Locale`.
* `contextCompany`, containing the current `Company`.
* `contextHttpServletRequest`, containing the `HttpServletRequest`.
* `contextHttpServletResponse`, containing the `HttpServletResponse`.
* `contextUser`, containing the current logged-in `User`.
* `contextUriInfo`, containing the `UriInfo` information (paths, endpoints).

## Automatic transactions

One little-known feature of REST Builder is that it wraps your API calls
in transactions if the HTTP verb used is POST, PUT, PATCH or DELETE (doesn't
apply for GET operations). 

If you need to do several Service Builder calls in a sequence, you don't have
to wrap them in a transaction; it happens automatically. The transaction
commits if no exception is thrown and rolls back if an exception bubbles up
to the resource implementation method.

## Test generation

You can generate integration tests if you specify a `testDir` property: 

```yaml
testDir: "../headless-admin-taxonomy-test/src/testIntegration/java"
```

REST Builder generates integration tests under the -test module. Those tests
check the API using a REST client. They check the response, doing an
end-to-end test involving all steps from parsing the request to returning
JSON.

The generated tests are ignored by default and, depending on the path, may
force you to implement a creation method (to be able to add content to either
update/delete or retrieve it).

The [headless-delivery-test](https://github.com/liferay/liferay-portal/tree/master/modules/apps/headless/headless-delivery/headless-delivery-test)
project contains many examples. 

## Client generation

You can generate a Java client if you specify a `clientDir` property: 

```yaml
clientDir: "../headless-admin-taxonomy-client/src/main/java"
```

This is a Java, typed, client that interacts with the APIs using static methods.
The client project contains all the methods to call your paths and parses the
requests and responses.

## Common utilities

REST Builder provides several JAX-RS utilities, from exception mappers for the
most common exceptions in the portal, XML and JSON Body Readers/Writers, Site
validation (that works with `siteId` and `siteKey`), and support for Bean
Validation.

There are also utility libraries that can be useful when developing new APIs:

* `ContentLanguageUtil`, to deal with the `ContentLanguage` header.
* `JaxRsLinkUtil`, to create links between APIs.
* `LocalDateTimeUtil`, to transform between date formats.
* `LocalizedMapUtil`, to create maps with locales as keys.
* `SearchUtil`, to use the search framework to return Pages of content.
* `TransformUtil`, to deal with lambdas and conversions.
