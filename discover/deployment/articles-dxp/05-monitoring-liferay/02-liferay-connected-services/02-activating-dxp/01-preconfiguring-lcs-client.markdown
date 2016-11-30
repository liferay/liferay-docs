# Preconfiguring the LCS Client to Connect Through a Proxy [](id=preconfiguring-the-lcs-client-to-connect-through-a-proxy)

If you connect to the Internet through a proxy, there are some properties you 
must set for the LCS client app to connect to LCS. You can do this two different 
ways:

1. As JVM app server arguments. Set these properties to the appropriate values 
   for your proxy: 

        -Dhttp.proxyHost=
        -Dhttp.proxyPort=
        -Dhttp.proxyUser=
        -Dhttp.proxyPassword=
        -Dhttps.proxyHost=
        -Dhttps.proxyPort=

    Note that the user, password, and https properties are only needed if your 
    proxy requires authentication. 

2. Inside the LCS client app's WAR file, before it deploys. If you downloaded a 
   Liferay DXP bundle, this means that you should set these properties prior to 
   starting your bundle for the first time. You can find the LCS client's WAR 
   file in the bundle's `[Liferay_Home]/deploy` folder. Starting the bundle 
   automatically deploys the LCS client app. If you've already started your 
   bundle, download the client app from 
   [here in the Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947), 
   make your changes in its WAR file, and redeploy it. You can find the WAR file 
   inside the app's LPKG file that downloads to your machine from Marketplace. 
   In either case, the client's WAR file is `lcs-portlet-[version].war`. 

    You must set the properties in the WAR file's `portlet-ext.properties` file. 
    Follow these steps to do so: 

    a. In the LCS client's WAR file, open the 
        `WEB-INF/classes/portlet-ext.properties` file. 

    b. Add the following properties at the end of `portlet-ext.properties` and 
        set them to the appropriate values for your proxy: 
   
            proxy.host.name=
            proxy.host.port=

       If your proxy requires authentication, you should also add the following 
       properties and set them to the appropriate values for your proxy. 

            proxy.host.login=
            proxy.host.password=

       If your proxy requires NTLM authentication, you must also add the 
       following properties: 

            proxy.auth.type=ntlm
            proxy.domain=
            proxy.workstation=

       Be sure to set `proxy.domain` and `proxy.workstation` to the appropriate 
       values for your proxy. Note that you can leave `proxy.workstation` blank 
       if you don't need it. 

    c. Repackage the LCS client WAR with the modified `portlet-ext.properties` 
        file. 

    d. Deploy the LCS client WAR, or redeploy it if it's already deployed. 

Great! Now you're ready to connect your Liferay DXP instance to LCS. 
