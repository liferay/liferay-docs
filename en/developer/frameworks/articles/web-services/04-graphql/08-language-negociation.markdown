---
header-id: language-negotiation
---

# Language Negotiation

[TOC levels=1-4]

The same mechanism for requesting content in another language in the
headless REST APIs is used in GraphQL. 

APIs available in different languages return the options in a block called
`availableLanguages`. For example, this block lists U.S. English (`en-US`) and
Spain/Castilian Spanish (`es-ES`): 

```json
{
  "availableLanguages": [
    "en-US",
    "es-ES"
  ],
  "contentFields": [
    {
      "dataType": "html",
      "name": "content",
      "repeatable": false,
      "value": {
        "data": "<p>The main reason is because Headless APIs have been designed with real use cases in mind...</p>"
      }
    }
  ],
  "contentStructureId": 36801,
  "creator": {
    "familyName": "Test",
    "givenName": "Test",
    "id": 20130,
    "name": "Test Test",
    "profileURL": "/web/test"
  },
  "dateCreated": "2019-04-22T10:29:40Z",
  "dateModified": "2019-04-22T10:30:31Z",
  "datePublished": "2019-04-22T10:28:00Z",
  "friendlyUrlPath": "why-headless-apis-are-better-than-json-ws-services-",
  "id": 59325,
  "key": "59323",
  "numberOfComments": 0,
  "renderedContents": [
    {
      "renderedContentURL": "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325/rendered-content/36804",
      "templateName": "Basic Web Content"
    }
  ],
  "siteId": 20124,
  "title": "Why Headless APIs are better than JSON-WS services?",
  "uuid": "e1c4c152-e47c-313f-2d16-2ee4eba5cd26"
}
```

To request the content in another language, specify your desired locale in the
request's `Accept-Language` header: 

```bash
curl "http://localhost:8080/o/graphql"  -H 'Accept-Language: es-ES'  -u 'test@liferay.com:test' ...
```

```json
    {
      "availableLanguages": [
        "en-US",
        "es-ES"
      ],
      "contentFields": [
        {
          "dataType": "html",
          "name": "content",
          "repeatable": false,
          "value": {
            "data": "<p>La principal razón es porque las APIs Headless se han diseñado pensando en casos de uso reales...</p>"
          }
        }
      ],
      "contentStructureId": 36801,
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T10:29:40Z",
      "dateModified": "2019-04-22T10:30:31Z",
      "datePublished": "2019-04-22T10:28:00Z",
      "friendlyUrlPath": "%C2%BFpor-qu%C3%A9-las-apis-headless-son-mejores-que-json-ws-",
      "id": 59325,
      "key": "59323",
      "numberOfComments": 0,
      "renderedContents": [
        {
          "renderedContentURL": "http://localhost:8080/o/headless-delivery/v1.0/structured-contents/59325/rendered-content/36804",
          "templateName": "Contenido web básico"
        }
      ],
      "siteId": 20124,
      "title": "¿Por qué las APIs Headless son mejores que JSON-WS?",
      "uuid": "e1c4c152-e47c-313f-2d16-2ee4eba5cd26"
    }
```

## Creating Content with Different Languages

By default, when sending a mutation request, the `Accept-Language` header is
used as the content's language. There is one exception, however. Some entities
require the first request to be in the Site's default language. In such cases,
the first request for a different language results in an error. 

After creating a new resource, a new request in a different language adds that
translation.

