---
header-id: what-lcs-stores-about-your-liferay-dxp-servers
---

# What LCS Stores About Your Liferay DXP Servers

[TOC levels=1-4]

| **Note:** LCS is deprecated and will be shut down on December 31, 2021. 
| Customers who activate LCS are advised to replace it with our latest activation
| key type which is suitable for virtualized environments. 
|
| For further information, please see [Changes to Liferay Product Activation](https://help.liferay.com/hc/en-us/articles/4402347960845-Changes-to-Liferay-Product-Activation).

To protect your users' privacy, LCS only stores system-specific data. LCS
doesn't gather or store data on your users. 

By default, LCS stores the following information about your server:

-   Portal build number and edition
-   Patching Tool Version
-   LCS Client Build Number 
-   Application Server Name
-   Database Name
-   File Encoding
-   OS Name and Version
-   Timezone
-   IP Address
-   Java Version and Java Options
-   Number of Processor Cores
-   File System Usage
-   Memory Usage 

The other data LCS stores depends on the services you enabled in your environment 
token, and whether your server was connected before certain services were removed. For more information on this, see 
[Registering Servers with LCS](/docs/7-2/deploy/-/knowledge_base/d/activating-your-liferay-dxp-server-with-lcs).
If you enabled the following services, LCS gathered and stored the data listed
for each: 

-   **Portal analytics:** 

    -   Portal and portlet metrics
    -   JVM metrics
    -   Cache and server metrics 

-   **Fix pack management:**

    -   Patches installed on the server 

-   **Portal properties analysis:** 

    -   `portal.properties` (except sensitive data) 

Sensitive data is any key-value pair that contains user names or passwords. For
example, LCS did not store the following properties because they contain
sensitive data: 

    omniadmin.users
    ldap.security.credentials.0, ldap.security.credentials.1, ldap.security.credentials.2 ...
    facebook.connect.app.secret
    auth.token.shared.secret
    auth.mac.shared.key
    captcha.engine.recaptcha.key.private
    amazon.secret.access.key
    tunneling.servlet.shared.secret
    microsoft.translator.client.secret
    dl.store.s3.secret.key
    auto.deploy.glassfish.jee.dm.passwd

LCS also did not store properties that end in `.password`, besides the following 
non-sensitive properties: 

    portal.jaas.plain.password
    portal.jaas.strict.password
    login.create.account.allow.custom.password

LCS also allowed you to prevent it from analyzing specific properties of your 
choosing, by defining blacklisted properties.

LCS is no longer gathering or storing the data listed above, that was associated with enabled services.
