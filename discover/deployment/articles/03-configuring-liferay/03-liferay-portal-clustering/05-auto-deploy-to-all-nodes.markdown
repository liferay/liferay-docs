# Auto Deploy to All Nodes 

If you want to deploy any module or WAR file onto the cluster, it must be 
deployed to all nodes of the cluster. Because @product@ now 
[installs applications as OSGi bundles](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator), 
this means you cannot rely on your application server's means of installing WAR 
files (even if you only intend to install WAR files) to deploy an application to 
the entire cluster. Instead, the application must be placed in @product@'s 
`deploy` folder on each node. 

This, as you might imagine, can be done with a script. Write a shell script that
uploads applications to each node using sftp or some other service. This way,
when you deploy an application, it is uploaded to each node's `deploy` folder
and installed by each running @product@ installation.
