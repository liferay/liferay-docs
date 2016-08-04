# Securing Elasticsearch with Shield

Elasticsearch makes storing, searching, and analyzing your data easy. *Shield*, an
Elasticsearch plugin that's used to secure your Elasticsearch cluster.

-  Password-protection
-  Encrypted communication
-  Role-based access control
-  IP filtering
-  Auditing

More high level:

-  Prevent unauthorized access
-  Preserve data integrity
-  Maintain an audit trail

https://www.elastic.co/guide/en/shield/2.2/index.html

Assuming you've already installed Elasticsearch, there are two main steps to
install Shield:

-  Installing Shield on Elasticsearch
    -  Install the Elasticsearch License Plugin
    -  Install the Shield plugin
-  Installing and configuring Liferay's Elastic Shield Adapter

+$$$

**Note:** The Shield Plugin can only be used when you're running Elasticsearch
in *remote mode*. If you're not sure what that means refer to the [Configuring
Elasticsearch article](discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch)

$$$

## Installing Shield on Elasticsearch

For more detailed information on installing Shield, see the [Elasticsearch
documentation](https://www.elastic.co/guide/en/shield/2.2/installing-shield.html).

1. Navigate to Elasticsearch Home, and install the license plugin and the Shield
   plugin by executing

        ./bin/plugin install license

    and then

        ./bin/plugin install shield

2. Next, you need to prepare for Shield to authenticate requests. 

    Users making requests to an Elasticsearch installation protected by Shield
    need to be part of the *realm*, a user database configured for Shield. You
    can use the native authentication system built into Shield, called
    *esusers*, or you can use an external user management system, like LDAP.

    *Roles* for Shield are defined in `[Elasticsearch Home]/shield/roles.yml`,
    and include

    -  `admin`: Has permission to perform any cluster or index action.
    -  `power_user`: Has permission to monitor the cluster and perform any index
    action.
    -  `user`: Has permission to perform read actions on any index.

    So who is the user you need to configure for Liferay? It's Liferay itself,
    and it needs the `admin` role.  Liferay's Elasticsearch client will send its
    authentication token (in other words, it's username and password) to Shield.
    Since Shield also has the authentication token stored in its user
    database.Shield's user database so that 

    From Elasticsearch Home, add an `admin` user named *liferay* to the esusers
    database:

        ./bin/shield/esusers useradd liferay -r admin

    When prompted, enter the password *liferay*.

3. Start Elasticsearch.

        ./bin/elasticsearch

4. Install your license file:

        curl -XPUT -u liferay 'http://localhost:9200/license?acknowledge=true' -d @license.json

    Enter the password you configured, and you'll get a confirmation message
    that looks like this:

        {"acknowledged":true}

5. To test that you have access to Elasticsearch, enter

        curl -u liferay:liferay -XGET 'http://localhost:9200/'

    and you'll see the Elasticsearch node information printed in the console:

        {
          "name" : "Amphibius",
          "cluster_name" : "LiferayElasticsearchCluster",
          "version" : {
            "number" : "2.2.0",
            "build_hash" : "8ff36d139e16f8720f2947ef62c8167a888992fe",
            "build_timestamp" : "2016-01-27T13:32:39Z",
            "build_snapshot" : false,
            "lucene_version" : "5.4.1"
          },
          "tagline" : "You Know, for Search"

Now Shield is installed. Configure Liferay's Shield adapter next.

## Installing and Configuring Liferay's Shield Adapter

You need to install Liferay's Shield Adapter WHY??????????

First install the Shield adapter (called *Liferay Portal Search Elasticsearch
Shield*) from [Liferay Marketplace](https://web.liferay.com/marketplace/).

You can confirm it's working as expected by typing `lb "Shield"` into the Gogo
shell. It should be listed as `Active`

    ID |State      |Level|Name
    517|Active     |   10|Liferay Portal Search Elasticsearch Shield (1.0.0)

