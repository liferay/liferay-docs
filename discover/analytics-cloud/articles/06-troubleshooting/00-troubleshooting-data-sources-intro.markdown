# Troubleshooting Data Sources [](id=troubleshooting-data-sources)

Environment and data source configurations can prevent or disrupt access to data
sources. Here are troubleshooting steps for resolving data source issues.

## DXP Data Source [](id=dxp-data-source)

### Retry Authorization [](id=retry-authorization)

**Error Message:** `Unknown error. Please retry authorization.`

DXP data source access requires that your DXP instance be
[publicly available](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-make-sure-liferay-dxp-and-its-json-web-services-are-accessible)
and that your Analytics Cloud instance be registered with the DXP instance as an
[OAuth application](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-register-analytics-cloud-with-your-liferay-dxp-instance). 

**Resolution:** 

1. Follow steps for
[adding a Liferay DXP data source](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown). 

2. [Register Analytics Cloud with your DXP instance](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-register-analytics-cloud-with-your-liferay-dxp-instance).



### Unsupported Version [](id=unsupported-version)

**Error Message:** `Unsupported version. This method of connection does not 
support the data source Liferay version. Make sure you are connecting to Liferay
7.0/7.1 instance or try a different method of connection.`

Analytics Cloud invokes the DXP instance's JSON web services to access data.
JSON web services must be enabled on the DXP instance. 

**Resolution:** 

1. Follow steps for
[adding a Liferay DXP data source](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown). 

2. [Enable JSON web services](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-2-make-sure-liferay-dxp-and-its-json-web-services-are-accessible)
on your DXP instance. 

### The Application is *Under Maintenance* [](id=the-application-is-under-maintenance)

**Error Message:** `The Application is under maintenance. Please check again after some time.`

This can occur if DXP's default login portlet is disabled. 

**Resolution:**

1. In your browser, sign in directly to your DXP instance via your instance's 
default login portlet URL (e.g., `https://www.my_dxp_instance.com/login`). 

2. If the login portlet is unavailable, verify the default login portlet is 
enabled. 

3. If you customized the default login portlet, try restoring the original 
portlet. 

### Invalid Credentials; the Authorization Expired [](id=invalid-credentials-the-authorization-expired)

**Error Message:** `Invalid Credentials. The authorization for this data source has expired. Please reauthorize the token in the OAuth tab.`

This message appears in the log:

`WARN [http-nio-8080-exec-14][AbstractOAuthService:88] Unsecure HTTP, Transport Layer Security is recommended`

Connection to a DXP data source requires that the DXP instance's web server
protocol be forwarded. 

**Resolution:**

1. Follow steps for
[adding a Liferay DXP data source](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown).

2. [Configure DXP to forward the web server protocol](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/02-getting-started/02-adding-a-liferay-dxp-data-source.markdown#step-3-set-liferay-dxp-to-forward-the-web-server-protocol). 
