---
header-id: using-rest-apis
---

# Using REST APIs

[TOC levels=1-4]

@product@'s headless REST APIs can be used with any REST client you prefer. The
only usual requirements are setting up the `Authentication` header (either
OAuth, Cookie, Basic...) and the `Content-Type` header if you are creating
content.

Our recommendation for JavaScript applications is to use `fetch` directly, like
this:

```javascript
fetch(`http://localhost:8080/o/headless-delivery/v1.0/sites/${SITE_ID}/structured-contents/'`, 
    {
        method: 'GET',
        headers: {
            'Authorization': `Basic ${BASIC_AUTH}`
        }
    }
);
```

Or for a `POST` request:

```javascript
fetch(`http://localhost:8080/o/headless-delivery/v1.0/sites/${SITE_ID}/structured-contents/`, 
    {
        method: 'POST', 
        headers: {
            'Authorization': `Basic ${BASIC_AUTH}`,
            'Content-Type': 'application/json'
        }, 
        body: JSON.stringify(
            {
                "title": "New appointment",
                "contentStructureId": STRUCTURE_ID,
                "contentFields": [
                    {
                        "name": "User",
                        "value": {
                            "data": USER,
                        }
                    },
                ]
            }
        )
    }
)
```

Here are two examples of JavaScript applications using the Headless REST APIs:

* [Alexa skill using Headless REST APIs with node-fetch](https://github.com/dgomezg/liferay-frontend-samples/tree/master/riuvo-alexa-skill).
* [Example API from scratch using REST Builder](https://liferay.dev/blogs/-/blogs/creating-headless-apis-part-1).
