# @product@ Deployment

@product@ is one of the most flexible applications on the market today with
respect to database and application server support. @product@ supports a wide
variety of databases and application servers, freeing you to use the ones you
think are best. @product@ also scales very well. You can install @product@ on a
shared hosting account, on a multi-node cluster running a commercial application
server, or on anything in between. In fact, @product@ is used successfully in all
of these scenarios every day.

## Supported Platforms and Limitations [](id=supported-platforms-and-limitations)

The list of supported OSes, application servers, databases, and other
environments is always available on the Customer Portal at
[https://www.liferay.com/group/customer](https://www.liferay.com/group/customer)
under the Support Matrix links.

Applications developed by Liferay, such as Audience Targeting, are
available through the Liferay
[Marketplace](https://www.liferay.com/marketplace/). It's possible that some of
these applications are supported only on certain environments. This is always
specified in the application's description. If you are planning to use these
additional products, make sure @product@ is running on a supported environment
before installing one of these applications. 

## Reference Architecture [](id=reference-architecture)

When considering deploying @product@, selecting the right architecture is one of
the first decisions you need to make. You need to consider these factors: 

- Information Security: Securing sensitive hardware and information from
  malicious attack and intrusion 
- Performance: Supporting the desired number of total users, concurrent 
  transactions, etc.
- Fault Tolerance: Maintaining uptime during unexpected failure or scheduled maintenance
- Flexibility and Scalability: Designing an expandable architecture to support
  additional features and users without significant redesign

The reference architecture depicted in Figure 1 provides high levels of fault
tolerance and flexibility.

![Figure 1: The @product-ver@ reference architecture is scalable.](../../images-dxp/de-reference-architecture.png)

The architecture contains the following tiers:

- **Firewall:** Intrusion detection and prevention
- **Load Balancer Tier**: Ensures smooth distribution of load between multiple
  web server resources 
- **Web Server Tier**: Delivers static content like images, rich media, CSS 
  files, etc. Also provides integration modules to single sign-on solutions like 
  CA SiteMinder, Oracle Identity, Ping, etc.
- **Application Tier:** Hosts @product@ supported application servers like Tomcat,
  JBoss, Oracle Weblogic, and IBM WebSphere (please see @product-ver@ Compatibility Matrix for additional platforms). Also hosts search
  engines like Solr and Elasticsearch.
- **Database Tier:** Hosts @product@ supported database servers like MySQL,
  Oracle, MS SQL Server, IBM DB2, PostgreSQL (please see @product-ver@
  Compatibility Matrix for additional platforms)

The hardware deployed within each tier varies depending on the type of transactions. 

### Virtualized and Cloud Deployments [](id=virtualized-and-cloud-deployments)

While the reference architecture describes a physical deployment, the same
concepts may be applied to a cloud based or virtualized deployment. Many @product@
customers choose to deploy on public clouds (e.g. Amazon EC2) or their own
private clouds (e.g. VMWare VSX based private cloud). Physical machines can
be replaced by appropriate quantities of virtual machines.

In virtualized deployments, it is critical to allocate sufficient CPU resources.
For instance, for systems deployed to Amazon AWS, allocated CPUs are calculated
using Amazon EC2 Compute Units. However, 1 Compute Unit does not equal to 1
physical CPU or even 1 core on a CPU. In Amazon's terms, each application server
equates to roughly a "Cluster Compute Quadruple Extra Large Instance," or 33.5
EC2 Compute Units. Thus, to plan the virtualized / cloud deployment properly,
customers must account not only for virtualization overhead, but also for
allocation of sufficient CPU resources.

### Fault Tolerance [](id=fault-tolerance)

The reference architecture is fault tolerant at every level. With clusters at
the web, application, and database tier, you may suffer a catastrophic hardware
failure of any node and continue to serve users with little performance
degradation.

The depicted reference architecture represents the minimum deployment units to
ensure proper fault tolerance within a single data center. You may increase the
number of servers within each tier according to your load patterns to achieve a
multiplier effect in the number of users the deployment can support while
maintaining sufficient fault tolerance.

Multi-data-center fault tolerant architectures are not provided as part of the
reference architecture.

### Performance [](id=performance)

Each deployment's performance characteristics vary depending on the type of
activity and the performance of custom application elements. Liferay Engineering
has created a series of scenarios to benchmark @product-ver@'s out
of the box performance characteristics for content management, collaboration and
social enterprise scenarios. Results from these reference architectures have
indicated @product-ver@ can support over 22,000 virtual
collaboration users and over 150,000 logins per minute with an average login
time of 300 milliseconds. @product-ver@ accomplished this load
within the reference architecture while using no more than 40% of CPU
resources in the Web Tier, 86% of CPU resources in the Application Tier, and 50%
of CPU resources in the Database Tier.

### Scalability [](id=scalability)

Liferay Engineering's testing has shown @product-ver@ to scale
linearly. Thus, if you know a single application server supports X virtual users
and assuming sufficient database and web server resources, you may calculate the
total number of application servers required.

### Security [](id=security)

The firewall preceding the Load Balancer Tier provides sufficient intrusion
detection and prevention. However, depending on your organization's information
security requirements, you may introduce additional firewall layers between each
tier to further secure the infrastructure.

### Environment Types [](id=environment-types)

Before starting to plan your @product@ deployment, it's important to include
different environments to be able to maintain the projects. Liferay uses the
following terminology for systems:

**Development:** The environment where the software is written. Most simply it
is developer workstations containing integrated development environments with
version and dependency handling along with a version control system with 
working copies on the individual developer workstations.

**SIT (System Integration Testing):** SIT is where the systems delivered are
validated. The is an environment for testing the automation of aggregated
components and the dependencies between them.

**UAT (User Acceptance Testing):** A QA or Test environment. The developers
deploy the software here when it's ready for users to verify its functionality. 

**Production Staging:** A pre-production environment that's as close as possible
to the production environment for final testing, such as installation,
configuration, deployment and load, and performance testing.

**Production:** The environment that's accessed directly by end users. Because
this is the live environment, updates are done using a secure process,
particularly if it is a complex clustered environment.
