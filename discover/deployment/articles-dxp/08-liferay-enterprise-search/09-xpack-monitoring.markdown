# Installing X-Pack Monitoring for Elasticsearch 6

Come up with a generalized instruction for X-Pack installation, for both
security and monitoring

1.  Download and install X-Pack, matching the ES version
2.  Install Kibana 
3.  Create a user for the monitoring UI (if using X-Pack security)
4.  If using an external monitoring cluster (as opposed to letting the
    monitoring indexes reside in the existing Elasticsearch cluster), set up a
    user for the Elasticsearch cluster's monitoring agent, and configure the
    user's credentials in the monitoring cluster.
    See more about monitoring users [here](https://www.elastic.co/guide/en/x-pack/6.1/secure-monitoring.html#monitoring-ui-users)

This document assumes you're enabling security *and* monitoring, though it's not
a requirement. Differences in the process are noted, as appropriate.

For the X-Pack installation procedure, refer to the [X-Pack security
article](/discover/deployment/-/knowledge_base-7-1/securing-elasticsearch-6-with-x-pack).
This guide starts with the installation of Kibana

## Install Kibana

1.  [Download Kibana](https://www.elastic.co/downloads/kibana) and extract it. The root folder wil be referred to as *Kibana Home*.

2.  Start Kibana by running this command from Kibana Home: 

        ./bin/kibana

3.  Install Kibana into X-Pack

        ./bin/kibana-plugin install x-pack

    If you're not using X-Pack Security on the Elasticsearch cluster, skip this
    step.

4. Tell Kibana where to send monitoring data by setting Elasticsearch's URL in
   `kibana.yml`:

        http://localhost:9200

    If SSL is enabled on Elasticsearch, this will be an `HTTPS` URL.

5. If not using X-Pack security, start Kibana by entering

        ./bin/kibana

    from Kibana Home.

If you're using X-Pack's security features, there's additional configuration
required before starting Kibana.

### Configure Kibana with Authentication

Follow these steps, or refer to [Elastic's
documentation](https://www.elastic.co/guide/en/kibana/6.1/monitoring-xpack-kibana.html)
for a more complete explanation. Follow these steps to configure Kibana if
X-Pack is requiring authentication to communicate with the elasticsearch
cluster.

1. Set the password for the built-in `kibana` user in `Kibana
   Home/config/kibana.yml`:

        elasticsearch.username: "kibana"
        elasticsearch.password: "kibanapassword"

2. If you're not encrypting communication with the Elasticsearch cluster, start
   Kibana with 

        ./bin/kibana

and navigate to `localhost:5601`. Log in with a
[user](https://www.elastic.co/guide/en/x-pack/6.1/native-realm.html#native-add) who has the `kibana_user`
[role](https://www.elastic.co/guide/en/x-pack/6.1/built-in-roles.html).

### Configuring Kibana with Encryption

Follow these steps to configure Kibana if X-Pack is encrypting communication
with the elasticsearch cluster.



Copied from Elastic docs, https://www.elastic.co/guide/en/x-pack/6.1/secure-monitoring.html
To use X-Pack monitoring with X-Pack security enabled, you need to set up Kibana
to work with X-Pack security and create at least one user for the Monitoring UI.
If you are using an external monitoring cluster, you also need to configure a
user for the monitoring agent and configure the agent to use the appropriate
credentials when communicating with the monitoring cluster.





