# Blue-Green Deployment [](id=other-cluster-update-techniques)

Blue-green is a deployment technique in which you duplicate your production
environment (the *blue* environment) and modify the duplicate (the *green*
environment) with software and data changes. When you've successfully tested the
green environment, you cut users over from the blue environment to the green
environment. Blue-green eliminates system down time. 

Data schema and data changes require special attention. Custom plugin/module
data schema changes that break compatibility with existing code must be
introduced over several releases in which the data is transitioned and
maintained in old and new columns until the old columns are unnecessary. 

Data and schema changes require these steps:

1.  Create a new column.

2.  Copy the data to the new column.

3.  Maintain both columns until the old column is no longer used by any cluster 
    nodes. 

4.  Delete the column in the next release. 

For more information, refer to these blue-green deployment articles: 

-   [BlueGreenDeployment](http://martinfowler.com/bliki/BlueGreenDeployment.html)

-   [Implementing Blue-Green Deployments with AWS](https://www.thoughtworks.com/insights/blog/implementing-blue-green-deployments-aws)

## Related Topics [](id=related-topics)

[Rolling Restarts](/discover/deployment/-/knowledge_base/7-1/using-rolling-restarts)

[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)

[Maintaining @product@](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay)

[Implementing Data Upgrades](/develop/tutorials/-/knowledge_base/7-1/data-upgrades)
