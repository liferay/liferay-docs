# Installing @product@ 

The easiest way to start using @product@ for testing or development is to 
download a standalone bundle. You can download a @product@ bundle from 
[https://www.liferay.com/downloads](https://www.liferay.com/downloads). For the 
purpose of this Learning Path, download a @product@ Tomcat bundle. Then follow 
these steps to run @product@:

1.  Once the bundle finishes downloading, unzip it to a location of your 
    choosing on your local filesystem. 

2.  In the unzipped bundle, use the command line to navigate to 
    `.../liferay-[version]/tomcat-[version]/bin`. 

3.  In the `bin` folder, run the appropriate startup script for your operating 
    system:
    
    - Windows: `startup.bat`
    - Unix-based systems (including Mac OS): `./catalina.sh run`

In the console, watch for a message that reads: 

    Server startup in 77777 ms

Note that the actual time it takes to start the server will vary. @product@ then 
launches a new browser window with the *Basic Configuration* page. On this page, 
you'll provide your portal's initial configuration. The next article shows you 
how to do this.

![Figure X: @product@'s Basic Configuration page.](../../../images/001-basic-configuration.png)

For more information on installing @product@, see the tutorial 
[Installing @product@](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal). 
