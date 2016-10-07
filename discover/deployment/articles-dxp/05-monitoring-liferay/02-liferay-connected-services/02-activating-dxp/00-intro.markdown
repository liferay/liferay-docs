# Activating Your DXP Server [](id=licensing-your-dxp-server)

To activate your Liferay DXP server, you must register it with Liferay Connected 
Services (LCS). LCS gives you a convenient central location to activate and 
manage your DXP subscriptions, and more. This section covers the following steps 
to help you get your DXP instance up and running with LCS: 

1. **Downloading the LCS Client App:** The LCS client app is part of Liferay DXP 
   bundles and autodeploys when the bundle starts. The LCS client app in the DXP 
   bundle, however, may be outdated. You should therefore download and install 
   the latest version of the LCS client app. You can download the LCS client app 
   [here in the Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/71774947). 
   For instructions on using Marketplace to download and install apps, see 
   [this user guide article](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace). 
   Also note that as improvements are made to LCS, older versions of the LCS 
   client app may not work. You should therefore ensure that your DXP instance 
   is always running the latest version of the client. When upgrading the 
   client, you may also need to regenerate the 
   [environment token](/discover/deployment/-/knowledge_base/7-0/using-lcs#using-environment-tokens) 
   that you use to connect. 

2. [**Preconfiguring the LCS Client to Connect Through a Proxy:**](/discover/deployment/-/knowledge_base/7-0/preconfiguring-the-lcs-client-to-connect-through-a-proxy)
   If your server connects to the Internet through a proxy, you must 
   preconfigure the LCS client app before it deploys to your DXP instance. This 
   article shows you how to do this. If your server doesn't connect through a 
   proxy, then you can skip this and proceed to activating your DXP server in 
   the next article. 

3. [**Activating Your DXP Server with LCS:**](/discover/deployment/-/knowledge_base/7-0/registering-your-dxp-server-with-lcs)
   Once you've preconfigured (if connecting through a proxy) and installed the 
   LCS client app, you must activate your DXP instance with LCS. This section 
   shows you how to do this. 

+$$$

**Note:** These steps show how to activate DXP instances for use in production 
environments. To activate DXP on a local workstation for testing or development 
purposes, you don't need to use LCS. Instead, create a ticket in 
[LESA](https://web.liferay.com/group/customer/support/-/support/ticket) 
to request an activation key. When creating this ticket, select *Activation Key* 
in the *Select a component* field. When you receive your key, place it in your 
local DXP instance's `deploy` folder. 

$$$

Also note that to use LCS, you must have an account at 
[Liferay.com](http://www.liferay.com/). 
