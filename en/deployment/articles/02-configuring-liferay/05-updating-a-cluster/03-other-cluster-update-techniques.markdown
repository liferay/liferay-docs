---
header-id: other-cluster-update-techniques
---

# Blue-Green Deployment

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/maintaining-clustered-installations/blue-green-deployments.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

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

## Related Topics

[Rolling Restarts](/docs/7-2/deploy/-/knowledge_base/d/using-rolling-restarts)

[@product@ Clustering](/docs/7-2/deploy/-/knowledge_base/d/liferay-clustering)

[Maintaining @product@](/docs/7-2/deploy/-/knowledge_base/d/maintaining-liferay)
