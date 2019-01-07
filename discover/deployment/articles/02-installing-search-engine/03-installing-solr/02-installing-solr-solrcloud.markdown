# Installing Solr: High Availability with SolrCloud [](id=high-availability-with-solrcloud)

Use SolrCloud if you need a cluster of Solr servers. Note that to use SolrCloud
in production, you should set up an 
[external ZooKeeper ensemble](https://cwiki.apache.org/confluence/display/solr/Setting+Up+an+External+ZooKeeper+Ensemble).
[ZooKeeper](http://zookeeper.apache.org/) is a centralized coordination service
for managing distributed systems like your SolrCloud cluster.

The steps included here should be considered the bare minimum of what must be
done to configure SolrCloud with @product@. For example, these instructions cover
configuring SolrCloud on a single machine, whereas a production environment
would feature multiple physical or virtual machines. These instructions also
assume you've followed the earlier section on *Installing and Configuring Solr
7*. Refer to the [SolrCloud guide for more information](https://cwiki.apache.org/confluence/display/solr/SolrCloud).

1. Stop the Solr server if it's running.

2. Navigate to the `Solr_Home/configsets` folder and create a folder called 

        liferay_configs

3. Copy the `conf` folder from `Solr_Home/liferay` to the `liferay_configs`
   folder you just created.

    The `configset/liferay_configs` folder contains the SolrCloud
    @product@ collection configuration and is uploaded to ZooKeeper. By copying
    the `conf` folder from the `liferay` server configured earlier, you're using
    the `schema.xml` and `solrconfig.xml` files provided with the Liferay Solr
    Adapter.

4. Next launch an interactive SolrCloud session to configure your SolrCloud
   cluster. Use this command:

        ./bin/solr -e cloud

5. Complete the setup wizard. These steps demonstrate creating a two-node
   cluster:

    -  Enter `2` for the number of nodes.
    -  Specify ports `8983` and `7574` (the defaults). Both nodes are
       started with the start commands printed in the log:

                Starting up Solr on port 8983 using command:
                "bin/solr" start -cloud -p 8983 -s "example/cloud/node1/solr"

    -  Name the collection *liferay*.
    -  Split the collection into two shards.
    -  Specify two replicas per shard.
    -  When prompted to choose a configuration, enter *liferay_configs*. You
       should see a log message that concludes like this when the cluster has
       been started:

                SolrCloud example running, please visit http://localhost:8983/solr

Now you have a new collection called *liferay* in your local SolrCloud cluster.
Verify its status by running the *status* command:

    ./bin/solr status

You'll see log output like this:

    Found 2 Solr nodes: 

    Solr process 16989 running on port 8983
    INFO  - 2018-08-06 13:54:17.665; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
    {
      "solr_home":"/home/russell/liferay-bundles/solr-7-dxp/solr-7.4.0/example/cloud/node1/solr",
      "version":"7.4.0 9060ac689c270b02143f375de0348b7f626adebc - jpountz - 2018-06-18 16:55:13",
      "startTime":"2018-08-06T17:52:01.519Z",
      "uptime":"0 days, 0 hours, 2 minutes, 16 seconds",
      "memory":"68.5 MB (%14) of 490.7 MB",
      "cloud":{
        "ZooKeeper":"localhost:9983",
        "liveNodes":"2",
        "collections":"1"}}


    Solr process 17127 running on port 7574
    INFO  - 2018-08-06 13:54:18.507; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
    {
      "solr_home":"/home/russell/liferay-bundles/solr-7-dxp/solr-7.4.0/example/cloud/node2/solr",
      "version":"7.4.0 9060ac689c270b02143f375de0348b7f626adebc - jpountz - 2018-06-18 16:55:13",
      "startTime":"2018-08-06T17:52:11.987Z",
      "uptime":"0 days, 0 hours, 2 minutes, 6 seconds",
      "memory":"56.4 MB (%11.5) of 490.7 MB",
      "cloud":{
        "ZooKeeper":"localhost:9983",
        "liveNodes":"2",
        "collections":"1"}}

To stop Solr while running in SolrCloud mode, use the *stop* command, like this:

    bin/solr stop -all

## Configure the Solr Adapter for SolrCloud [](id=configure-the-solr-adapter-for-solrcloud)

There's only one thing left to do: specify the client type as *CLOUD* in
Liferay's Solr connector.

1. From System Settings or your OSGi configuration file, set the *Client Type*
   to *CLOUD*.

        clientType="CLOUD"

2. Start @product@ if it's not running already.

![Figure 1: From the Solr 7 System Settings entry, set the _Client Type_ to _Cloud_.](../../../images/solr-client-type.png)

<!-- +$$$
UNCOMMENT WHEN WRITTEN
**Note:** For a complete list of settings available in the Solr connector, see the
Solr Settings reference
article(/discover/reference/-/knowledge_base/7-1/solr-settings).

$$$ -->

Now you can configure @product@ for Solr and Solr for @product@. Remember that
Elasticsearch is the default search engine, so if you're not constrained to use
Solr or already a Solr expert, consider Elasticsearch for your search engine
requirements. If you do use Solr, tell all your colleagues that your @product@
installation's search capability is Solr powered (pun intended).
