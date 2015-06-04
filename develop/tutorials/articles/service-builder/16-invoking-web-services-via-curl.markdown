# Invoking JSON Web Services via cURL

This tutorial explains how to invoke Liferay's JSON web services via
[cURL](http://curl.haxx.se/) and provides examples. To learn how to invoke
Liferay's JSON web services via JavaScript or URL, please see the
[Invoking JSON Web Services via JavaScript](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-javascript)
tutorial or the
[Invoking JSON Web Services via URL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-url)
tutorial.

## Simple JSON Web Service Invocation via cURL

Let's examine a simple JSON web service invocation that's performed via URL:

    curl http://localhost:8080/api/jsonws/user/get-user-by-email-address \
      -u test@liferay.com:test \
      -d companyId=20154 \
      -d emailAddress='test@liferay.com'

## Related Topics

[Invoking JSON Web Services via JavaScript](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-javascript)

[Invoking JSON Web Services via URL](develop/tutorials/-/knowledge_base/6-2/invoking-json-web-services-via-url)
