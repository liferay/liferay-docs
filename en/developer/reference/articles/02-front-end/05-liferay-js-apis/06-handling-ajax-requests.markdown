---
header-id: handling-ajax-requests-with-fetch
---

# Handling AJAX Requests with `Liferay.Util.fetch`

[TOC levels=1-4]

When you make Ajax requests (referred to as Service Resource actions/requests in 
@product@), they must protect against [CSRF](https://en.wikipedia.org/wiki/Cross-site_request_forgery)
and include the proper credentials. Since Liferay DXP 7.2 SP1 and Liferay 
CE Portal 7.2 GA2, @product@ provides a `Liferay.Util.fetch` utility 
based on the standard [`fetch`](https://fetch.spec.whatwg.org/) API that you 
can use to make AJAX requests. It includes these key features:

- A thin wrapper on ES6 [`fetch`](https://fetch.spec.whatwg.org/) that shares 
  the same API
- Sets `credentials:include` to each request
- Sets `x-csrf-token` header to each request
- Requires no dependencies

Below is an example configuration in ES6:

```JavaScript
import {fetch} from 'frontend-js-web';

fetch(url, {
  body: new FormData(form),
  method: 'POST'
})
  .then(response => response.json())
  .then(response => processData(response))
  .then(response => failureCallback(error));
```

Example use case in JSPs:

```JavaScript
Liferay.Util.fetch(url, {
  body: new FormData(form),
  method: 'POST'
}).then(function(response) {
  return response.json();
}).then(function(response) {
  message.innerHTML = response.message;
}).catch(function() {
  failureCallback();
});
```

| **NOTE:** global access through `Liferay.Util` is only meant for use in JSP 
| code. In ES6, you must use the `fetch` module, as shown in the JavaScript 
| example above.
