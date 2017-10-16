# Installing @product@ 

The easiest way to start using @product@ for testing or development is to 
download a standalone bundle. You can download @product@ from 
[https://www.liferay.com/downloads](https://www.liferay.com/downloads).

1. Once it finishes downloading, unzip your bundle to your local 
    filesystem.
    
2. Using a command line application, navigate to 
    `.../liferay-[version]/tomcat-[version]/bin`

3. From the `bin` folder, run the appropriate startup script for your operating 
    script for your operating system.
    
    On Windows:
       
        startup.bat
    
    On Unix-based systems:
    
        ./catalina.sh run

After you launch it, watch the log for a message that reads `Server startup in 
77777 ms` (the actual number will vary), and Liferay will launch a new browser 
window with the *Basic Configuration* page. Next you need to take care of some 
basic configuration.

![Figure X: The basic configuration page.](../../../images/001-basic-configuration.png)

For more information on installing @product, see the 
[Installing Liferay Portal tutorial](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal)

