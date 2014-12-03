# Installing Liferay on JOnAS 5.2 [](id=installing-liferay-on-jonas-5-2)

JOnAS is somewhat unique among the open-source application servers for two
reasons: it is built entirely on an OSGi core, allowing for dynamic deployment
of simultaneous webapps and containers and allows the clean separation of the
configuration directory from the server itself. In fact, the separation of the
configuration is recommended by the developers of JOnAS as a way to cleanly
deploy so you can revert to default settings later.

If you don't have an existing JOnAS installation, it is recommeded to use the
available Liferay-JOnAS bundle, which can be downloaded from
[http://www.liferay.com/ja/downloads/liferay-portal/available-releases](http://www.liferay.com/ja/downloads/liferay-portal/available-releases).

Given the unique nature of the server, there are a few steps to consider in the
configuration stage. Otherwise, installing on JOnAS follows much the same
pattern as other servers: configure, copy dependencies and deploy.

## Configuring JOnAS for Liferay [](id=configuring-jonas-for-liferay)

A pristine JOnAS installation comes with a number of samples, tutorials and
template configuration files. A lot of this is unnecessary and irrelevant for
installing Liferay. In addition, the server already contains an application
deployed to the root context, which you must remove prior to installing the
Liferay `.war` package (and which you'd want to remove anyway for a production
configuration). JOnAS allows you to decide where to place all the server
configuration and deployment settings, also called `$JONAS_BASE`. The folder
created by unzipping the JOnAS application (likely called `jonas-full-5.2.2` or
similar) is referred to as `$JONAS_ROOT`. This allows a unique, clean separation
between application and configuration. 

The structure of `$JONAS_BASE` is:
- /conf    -    configuration files
- /deploy    -    main deployment directory (Liferay is deployed here)
- /lib    -    used for extending the main server classloaders
- /lib/ext    -    extensions for unbundled applications
- /logs    -    logs for the running instance
- /work    -    the working directory, used by containers such as Tomcat
- /repositories    -    contains OSGi bundles for deployment; not used for Liferay installation
    
By default, the `$JONAS_BASE` directory is the same as `$JONAS_ROOT`. Creating a
new `$JONAS_BASE` is a simple process, outlined in the JOnAS Configuration
Guide, found at
[http://jonas.ow2.org/JONAS_5_2_2/doc/doc-en/html/configuration_guide.html](http://jonas.ow2.org/JONAS_5_2_2/doc/doc-en/html/configuration_guide.html).

To remove sample files and unneeded configuration:

1.  Navigate to the directory you unpackaged *JOnAS* into, `$JONAS_BASE`.

2.  Find the following sample directories and remove them:
    - /examples
    - /tutorial

3.  Navigate to `$JONAS_BASE/conf` and remove the following files:
    - db2.properties
    - FirebirdSQL.properties
    - HSQL1.properties
    - jetty\*.xml
    - InstantDB1.properties
    - InterBase1.properties
    - MailMimePartDS1.properties
    - MailSession1.properties
    - McKoi1.properties
    - MySQL.properties
    - Oracle1.properties
    - PostgreSQL1.properties
    - spy.properties
    - Sybase1.properties
		
    This disables the default settings for the databases available in JOnAS, as
    well as removing configuration for Jetty as a container to use for the
    webapp.
      
4.  To remove the default application installed on the root context:

    1. Go to the `$JONAS_BASE/deploy` directory and remove:
        - ctxroot.xml
        - doc.xml
        - jdbc-ds.xml
        - jonasAdmin.xml
    2. Go to the `$JONAS_ROOT/repositories` directory to remove the application
    by removing:
        - org/mortbay/
        - org/ow2/jonas/documentation/
        - org/ow2/jonas/jonas-admin/
        - org/ow2/jonas/jonas-ctxroot/
	     
    This will fully remove the Maven deployment plan and artifact for the
    JOnAS default application, as well as the administration console from
    loading on the root context.
      
Now that JOnAS is prepared for configuring Liferay to run on the server as its
root application, you can begin tuning the settings for Liferay. By default,
JOnAS has its own deployment of Hypersonic it uses internally. This internal use
of HSQL must be disabled, along with other JOnAS services, so they won't
conflict with Liferay's.

To turn of HSQL and other JOnAS-level services:

1. Open the file `jonas.properties` in the directory `$JONAS_BASE/conf`.

2. Find the configuration section for the JOnAS database manager, starting
   around line 340:

        #
        ###################### JOnAS DBM Database service configuration
        
        ...
	
        jonas.service.dbm.datasources    hsql
	    
3. Change the datasources definition around line 353 to read: 

            jonas.service.dbm.datasources    
	    
    Thereby preventing the HSQL database from being used internally.
       
4. Find the services configuration around line 82:

            jonas.services    jtm,db,security,resource,ejb3,jaxws,web,ear,depmonitor
	   
5. Modify the services being loaded to read:

            jonas.services    jtm,resource,ejb3,jaxws,web,ear,validation,depmonitor
	    
    This prevents the internal `db` and `security` services from interfering
    with Liferay.
      
6. To put JOnAS into production mode for proper deployment of Liferay, find the
   property around line 71:

            jonas.development    true      
	    
    And change it to `false`:

            jonas.development    false

This allows JOnAS to startup appropriately with Liferay installed.
            
## Configuring Containers in JOnAS [](id=configuring-containers-in-jonas)

Now that the application server has all extraneous services and applications
disabled, you can now tweak the configuration of the containers within JOnAS:
Tomcat and OSGi. By default, the Tomcat container is set to listen on a
different HTTP port and HTTPS port than Liferay uses by default.

To change the Tomcat ports for Liferay's use:

1. Open the file `tomcat6-server.xml` inside of `$JONAS_BASE/conf`.

2. Find the `Connector` definition around line 69:

            <Connector port="9000" protocol="HTTP/1.1"
                           connectionTimeout="20000"
                           redirectPort="9043" />
			   
    Change it to reflect the default ports:

            <Connector port="8080" protocol="HTTP/1.1"
                           connectionTimeout="20000"
                           redirectPort="8443" />

3. If you are using any other settings in Tomcat's server settings, you can
   adjust the ports if needed (such as changing the AJP port from `9009` to
   `8009`.

To modify the OSGI defaults to ensure required java packages are bootsrapped by
the loader:

1. Open the file `defaults.properties` inside of `$JONAS_BASE/conf/osgi`.

2. Find the declaration for `javase-packages` around line 93:

        javase-packages ${javase-${javase.version}}
	    
     And add the following packages to make it read:
      
        javase-packages ${javase-${javase.version}}, com.sun.jmx.mbeanserver, com.sun.crypto.provider, org.apache.felix.framework
	      
    To ensure the required packages are loaded.
      
## Starting JOnAS [](id=starting-jonas)

Once you have the required configuration in place, all that is left is to copy
the portal dependencies and the Liferay `.war` file and start the server. JOnAS
maintains libraries inside `$JONAS_BASE/lib/ext` and the application inside
`$JONAS_BASE/deploy`.

To install `liferay-portal-dependencies-6.1.x-<date>.zip`:

1. Unzip the archive `liferay-portal-dependencies-6.1.x-<date>.zip` on your
   local filesystem.

2. Navigate to `$JONAS_BASE/lib/ext`.

3. Copy the `.jar` files from `liferay-portal-dependencies-6.1.x-<date>/` to
   `$JONAS_BASE/lib/ext`.

4. Install any additional libraries needed, such as database connectors.

To deploy the `liferay-portal-6.1.x-<date>.war` file:

1. Copy the `liferay-portal-6.1.x-<date>.war` file from its current directory.

2. Navigate to `$JONAS_BASE/deploy`.

3. Paste the `liferay-portal-6.1.x-<date>.war` file into the `deploy` directory.

Once the necessary files have been installed, all that is needed is to start
JOnAS:

1. Navigate to `$JONAS_BASE/bin`.

2. Run the command `jonas.bat start` on Windows and `./jonas start` on UNIX-lixe
   systems.

JOnAS starts and Liferay opens a browser to `http://localhost:8080`.
