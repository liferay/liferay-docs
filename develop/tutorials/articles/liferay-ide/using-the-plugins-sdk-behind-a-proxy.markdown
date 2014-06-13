# Using the Plugins SDK Behind a Proxy

You may run into errors when you try to create a Liferay project if your 
internet connection goes through a proxy first (you can skip this section if 
you're not behind a proxy). When you create a Liferay project, the Plugins SDK 
leverages Apache Ivy to take care of dependencies. Ivy is normally downloaded in 
the background by the SDK. However, if you're behind a proxy this download often 
fails. The error log shows connection errors, messages about failing to resolve 
dependencies, or both. Regardless, the project cannot be created and the dreaded 
`BUILD FAILED` message appears. 

There are two ways to resolve this problem:

1. Get out from behind the proxy. This isn't always possible or desirable.

2. Download the Ivy cache manually from
   [this link](http://files.liferay.org.es/staged/public-files/liferay-ide/dependencies/files/ivy-cache.zip)
   and unzip it to your Plugins SDK. If there is an existing `.ivy` folder in
   your Plugins SDK, make sure that it gets replaced with the one you extract
   from `ivy-cache.zip`. 

You're now be able to create Liferay projects from behind your proxy. 

