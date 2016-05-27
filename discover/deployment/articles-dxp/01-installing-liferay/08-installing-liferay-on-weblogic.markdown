# Installing Liferay DXP on WebLogic 12c R2

<!-- general intro goes here -->

Before installing Liferay DXP in your WebLogic server, you should read the 
following to familiarize yourself with Liferay's general installation steps: 

- [Liferay Installation Overview](/discover/deployment/-/knowledge_base/7-0/liferay-installation-overview)
- [Installing Liferay Portal](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal)
- [Installing Liferay Manually](/discover/deployment/-/knowledge_base/7-0/installing-liferay-manually)

Note that although it's possible to install Liferay DXP in a WebLogic admin 
server, this isn't recommended. It's best practice to install web apps, 
including Liferay DXP, in a WebLogic managed server. This guide shows you how to 
do this, and assumes that your admin and managed servers already exist. See 
[WebLogic's documentation](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html) 
for instructions on setting up and configuring admin and managed servers. 

In WebLogic, 
[Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home) 
is generally `$WL_HOME/user_projects/domains/your_domain_name`, where 
`your_domain_name` is the name of your WebLogic domain. 

Without any further ado, get ready to install Liferay DXP in WebLogic! 

## Installing Liferay Dependencies

First, you should download Liferay DXP's dependencies from 
[http://files.liferay.com/private/ee](http://files.liferay.com/private/ee) 
or the customer portal on 
[liferay.com](https://www.liferay.com/). 
Unzip the dependencies and place them in your WebLogic domain's `lib` folder. 
Generally, this is `Liferay_Home/lib`. 

<!-- List files. Also include tomcat-juli.jar, and database driver? -->

Next, you must download and install Liferay's OSGi dependencies. Download the 
OSGi zip file from the same place you downloaded DXP's dependencies. Extract the 
OSGi dependencies to the `osgi` folder (create this folder if it doesn't exist) 
in `Liferay_Home`. 

## Configuring WebLogic

Next, you must set some properties in your WebLogic startup scripts. First, set 
UTF-8 file encoding in the clustering section of your domain’s 
`bin/setDomainEnv.[cmd|sh]` file. To do this, open the 
`bin/setDomainEnv.[cmd|sh]` file and change this: 

    JAVA_PROPERTIES="${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"

To this:

    JAVA_PROPERTIES="-Dfile.encoding=utf8 ${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"

Next, you must set your Java memory arguments and other properties in your 
managed server's startup script. To do so, set the following properties in your 
domain's `bin/startManagedWebLogic.[cmd|sh]` file:

    export USER_MEM_ARGS="-Xmx2048m -XX:MaxMetaspaceSize=512m"

    export MW_HOME="/your/weblogic/server/parent/directory"

    export JAVA_OPTIONS="${JAVA_OPTIONS}  -da:org.apache.lucene... -da:org.aspectj..."

These settings support Liferay's memory requirements, Lucene usage, and Aspect 
Oriented Programming via AspectJ. Make sure to set `MW_HOME` to the directory 
containing your WebLogic server on your machine. 

## Database Configuration

1. Log in to your AdminServer console.

2. In the *Domain Structure* tree, find your domain and navigate to 
   *Services* &rarr; *JDBC* &rarr; *Data Sources*.

3. To create a new data source, click *New*. Fill in the *Name* field with 
   `Liferay Data Source` and the *JNDI Name* field with `jdbc/LiferayPool`. 
   Select *MySQL* as the database type, and 
   *MySQL's Driver (Type 4) Versions:using com.mysql.jdbc.Driver* as the 
   database driver. Click *Next* to continue.

4. Accept the default settings on this page and click *Next* to move on. 

5. Fill in the database information for your MySQL database. 

6. Add the text 
   `?useUnicode=true&characterEncoding=UTF-8&\useFastDateParsing=false` to the 
   URL line and test the connection. If it works, click *Next*. 

7. Select the target for the data source and click *Finish*. 

8. You must now tell Liferay about the JDBC data source. Create a 
   `portal-ext.propreties` file in the Liferay Home directory, and add the line 
   `jdbc.default.jndi.name=jdbc/LiferayPool`. 

Alternatively, you can make the above configuration strictly via properties in 
the `portal-ext.properties` file. To do so, place the following properties and 
values in the file. Be sure to change the `your*` values with the values 
appropriate for your database's configuration: 

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://your.db.ip.address/yourdbname?useUnicode?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=yourdbuser
    jdbc.default.password=yourdbpassword

## Mail Configuration

## Deploying Liferay DXP

- Download and unzip the WAR

- In `WEB-INF/classes`, create a `portal-ext.properties` file and in it, set 
  `liferay.home` to your Liferay Home folder. 

- In your admin server, select the expanded WAR folder and deploy it to your 
  managed server. 
