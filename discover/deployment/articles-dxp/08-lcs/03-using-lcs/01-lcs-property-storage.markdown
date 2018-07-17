# What LCS Stores About Your Liferay DXP Servers [](id=what-lcs-stores-about-your-liferay-dxp-servers)

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

The other data LCS stores depends on the services you enable in your environment 
token. For more information on this, see 
[the article on activating servers with LCS](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs). When you enable the following services, LCS gathers and stores the 
data listed for each: 

-   **Portal analytics:** 

    -   Portal and portlet metrics
    -   JVM metrics
    -   Cache and server metrics 

-   **Fix pack management:**

    -   Patches installed on the server 

-   **Portal properties analysis:** 

    -   `portal.properties` (except sensitive data) 

Sensitive data is any key-value pair that contains user names or passwords. For
example, LCS doesn't store the following properties because they contain
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

LCS also doesn't store properties that end in `.password`, besides the following 
non-sensitive properties: 

    portal.jaas.plain.password
    portal.jaas.strict.password
    login.create.account.allow.custom.password

LCS also lets you prevent it from analyzing specific properties of your 
choosing. For more information on this, see 
[Activating Servers with LCS](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs). 
