# What LCS Stores About Your Liferay DXP Servers [](id=what-lcs-stores-about-your-liferay-dxp-servers)

At this point, you might be wondering what information about your servers is 
stored on LCS's servers. Great question! To protect your users' privacy, LCS 
only stores system-specific data. LCS doesn't gather or store data on your 
users. To offer the best service possible, LCS stores the following information 
about your servers: 

- Patches installed on each server 
- `portal.properties` (except sensitive data) 
- JVM metrics 
- Portal and portlet metrics 
- Cache and server metrics 

Sensitive data is any key-value pair that contains usernames or passwords. For 
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
[the article on activating servers with LCS](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/activating-your-liferay-dxp-server-with-lcs). 
