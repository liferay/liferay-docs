# @product@ deployment

@product@ is one of the most flexible applications on the market today with
respect to database and application server support. Liferay supports a wide
variety of databases and application servers, freeing you to use the ones you
think are best. @product@ also scales very well. You can install @product@ on a
shared hosting account, on a multi-node cluster running a commercial application
server, or on anything in between. In fact, Liferay is used successfully in all
of these scenarios every day.

## Platform components

## Supported Platforms and Limitations
@product@ supports several different environments. The detailed and updated list is always available on the Customer Portal at
[https://www.liferay.com/group/customer](https://www.liferay.com/group/customer) under the Support Matrix links.

Applications developed by Liferay, such as Audience Targeting, might be available through the Liferay [Marketplace](https://www.liferay.com/marketplace/). These products might have a different set of environments supported, in these cases it's specified in the application's description. If you are planning to use these additional products, please find the a common environment before proceeding with the installation.

## Reference Architecture

The selection of an appropriate architecture is one of the first decisions in your deployment path. To select an appropriate architecture, you must consider: 

* Information Security: Securing sensitive hardware and information from malicious attack  and intrusion
* Performance: Supporting the desired number of total users, concurrent transactions, etc.
* Fault Tolerance: Maintaining uptime during unexpected failure or scheduled maintenance
* Flexibility and Scalability: Designing an expandable architecture to support additional features and users without significant redesign

Although appearing somewhat complex, the reference architecture depicted in Figure 1 provides high levels of fault tolerance and flexibility.

![Liferay DE Reference Architecture](../../images-dxp/de-reference-architecture.png)

The architecture contains the following tiers:

* **Firewall:** Intrusion detection and prevention 
* **Load Balancer Tier**: Ensures smooth distribution of load between multiple  web server resources 
* **Web Server Tier**: Delivers static content elements like images, rich media, CSS files, etc.  Also provides integration modules to single sign-on solutions like CA Siteminder, Oracle Identity, Ping, etc.
* **Application Tier:** Hosts Liferay supported application servers like Tomcat, JBoss, Oracle Weblogic, 
 and IBM Websphere (please see Liferay Digital Enterprise Compatibility Matrix for additional platforms).
 Also hosts search engines like Solr and Elasticsearch.
* **Database Tier:** Hosts Liferay supported database servers like MySQL, Oracle, MS SQL, IBM DB2, Postgres (please see Liferay Digital Enterprise Compatibility Matrix for additional platforms)

The hardware deployed within each tier varies depending on the type of transactions. We will use Liferay Engineering's benchmarking environment as a hardware specification guide:

* **Load Balancer Tier:** Cisco Load Director or Cisco Content Services Switch (CSS) or F5 Big-IP
* **Web Tier:** Provides caching, compression, and other capabilities using Apache, Nginx, Varnish, etc. 1 – Intel Core 2 Duo E6405 2.13GHz CPU, 4GB memory, 1-146GB 10k RPM SCSI 
* **Application Tier:** Represents the workhorse of the architecture. 
2 – Intel Core 2 Quad X5677 3.46GHz CPU, 64GB memory, 
2-300GB 15K RPM SATA 6Gbps -  used for Liferay Portal 
2 – Intel Core 2 Quad X5677 3.46GHz CPU, 64GB memory, 2-300GB 15K RPM SATA 6Gbps -  used for Elasticsearch 
* **Database Tier 2** – Intel Core 2 Quad X5677 3.46GHz CPU, 64GB memory, 2-300GB 15K RPM SATA 6Gbp

Although the application servers have 64GB of physical memory, you may choose to deploy with less if your Java Virtual Machine (JVM) does not utilize large heap sizes. Modern operating systems will also use any available physical memory for file system caches.

###Virtualized and Cloud Deployments

While the reference architecture describes a physical deployment, the same concepts may be applied to a cloud based or virtualized deployment.Many Liferay customers choose to deploy on either public clouds (e.g. Amazon EC2) or their own private clouds (e.g. VMWare VSX based private cloud).Each physical machine may be replaced by appropriate quantities of virtual machines.

In the virtualized deployments, it is critical to allocate sufficient CPU resources. For instance,  for systems deployed to Amazon AWS, allocated CPUs are calculated using Amazon EC2 Compute Units. However, 1 Compute Unit does not equal to 1 physical CPU or even 1 core on a CPU. In Amazon's terms, each application server used in the reference architecture equates to roughly a "Cluster Compute Quadruple Extra Large Instance," or 33.5 EC2 Compute Units. Thus, to properly plan the virtualized/ cloud deployment, customers must account for not only virtualization overhead, but also ensure allocation of sufficient CPU resources.

###Fault Tolerance

The reference architecture is fault tolerant at every level. With clusters at the web, application, and database tier, you may suffer a catastrophic hardware failure of any node and continue to service users with little performance degradation.

The depicted reference architecture represents the minimum deployment units to ensure proper fault tolerance within a single data center. You may increase the number of servers within each tier according to your load patterns to achieve a multiplier effect in the number of users the deployment can support while maintaining sufficient fault tolerance.

Multi-data-center fault tolerant architectures are not provided as part of the reference architecture.

###Performance
Each deployment's performance characteristics will vary depending on the type of activity and the performance of custom application elements. Liferay Engineering has created a series of scenarios to benchmark Liferay Digital Enterprise's out of the box performance characteristics for content management, collaboration and social enterprise scenarios. Results from these reference architectures have indicated Liferay Digital Enterprise can support over 22,000 virtual collaboration users and over 150,000 logins per minute with an average login time of 300 milliseconds. Liferay Digital Enterprise accomplished this load within the reference architecture while utilizing no more than 40% of CPU resources in the Web Tier, 86% of CPU resources in the Application Tier, and 50% of CPU resources in the Database Tier.

###Scalability

Liferay Engineering's testing has shown Liferay Digital Enterprise to scale linearly. Thus, if you  know a single application server supports X virtual users and assuming sufficient database and web server resources, you may calculate the total number of application servers required.

###Security

The firewall preceding the Load Balancer Tier will provide sufficient intrusion detection and prevention. However, depending on your organization's information security requirements, you may introduce additional firewall layers between each tier to further secure the infrastructure.


//TODO - All below
* Development
* System Integration
* UAT
* Production Staging
* Production

## Preparation
Operating System

Storage

Networking