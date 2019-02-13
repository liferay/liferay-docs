# Troubleshooting Liferay DXP Data Sources [](id=troubleshooting-liferay-dxp-data-sources)

Environment and data source configurations can prevent or disrupt access to
Liferay DXP (DXP) data sources. Here are troubleshooting steps for resolving DXP
data source issues.

## Retry Authorization [](id=retry-authorization)

**Error Message:** `Unknown error. Please retry authorization.`

DXP data source access requires that your DXP instance be
[publicly available](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-make-sure-liferay-dxp-and-its-json-web-services-are-accessible)
and that your Analytics Cloud instance be registered with the DXP instance as an
[OAuth application](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-register-analytics-cloud-with-your-liferay-dxp-instance). 

**Resolution:** 

1.  Follow the steps for
    [adding a Liferay DXP data source](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown). 

2.  [Register Analytics Cloud with your DXP instance](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-register-analytics-cloud-with-your-liferay-dxp-instance).

## Unsupported Version [](id=unsupported-version)

**Error Message:** `Unsupported version. This method of connection does not 
support the data source Liferay version. Make sure you are connecting to Liferay
7.0/7.1 instance or try a different method of connection.`
 
**Resolution:** 

1.  Make sure to connect with a Liferay DXP 7.0 or 7.1 instance. 

2.  Follow the steps for
    [adding a Liferay DXP data source](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown). 

3.  If the error persists, make sure JSON web services are enabled on your 
    DXP instance. They're enabled by default. If you disabled them using a
    [portal property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)
    setting `json.web.service.enabled=false` (e.g., set in a
    `portal-ext.properties` file), delete the setting or set the property value
    to `true`. 

### Invalid Credentials; the Authorization Expired [](id=invalid-credentials-the-authorization-expired)

**Error Message:** `Invalid Credentials. The authorization for this data source 
has expired. Please reauthorize the token in the OAuth tab.`

This message appears in the log:

`WARN [http-nio-8080-exec-14][AbstractOAuthService:88] Unsecure HTTP, Transport Layer Security is recommended`

Connection to a DXP data source requires that the DXP instance's web server
protocol be forwarded. 

**Resolution:**

1.  Follow steps for
    [adding a DXP data source](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown),
    paying particular attention to
    [register Analytics Cloud with your DXP instance](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-register-analytics-cloud-with-your-liferay-dxp-instance).

2.  If the issue persists and the web server protocol is forwarded, set these
    [portal properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)
    in a `portal-ext.properties` file in your DXP instance. 
    
        web.server.forwarded.protocol.enabled=true
        redirect.url.security.mode=domain
        redirect.url.domains.allowed=
