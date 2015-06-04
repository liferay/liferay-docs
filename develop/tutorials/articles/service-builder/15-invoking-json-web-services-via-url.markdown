# Invoking JSON Web Services via URL

This tutorial explains how to invoke Liferay's JSON web services via URL and
provides examples. To learn how to invoke Liferay's JSON web services via
JavaScript or cURL, please see the
[Invoking JSON Web Services via JavaScript](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-javascript)
tutorial or the
[Invoking JSON Web Services via cURL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-curl)
tutorial.

## Simple JSON Web Service Invocation via URL

Let's examine a simple JSON web service invocation that's performed via URL:

    http://localhost:8080/api/jsonws/user/get-user-by-email-address/company-id/20154/email-address/test%40liferay.com

Note that the `@` symbol has a special meaning in a URL and needs to be
[escaped](http://www.w3schools.com/tags/ref_urlencode.asp). Of course, to make
this example work, use a valid company ID and email address.

## Related Topics

[Invoking JSON Web Services via JavaScript](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-javascript)

[Invoking JSON Web Services via cURL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-curl)
