# Configuring Search [](id=configuring-search)

Liferay uses Elasticsearch to index its content. By default, Liferay uses
Elastic as an embedded service. It works, but this is not a supported
configuration for a production server. Feel free to use it while you're testing
or developing, but when you're ready to put your site in production, you'll need
to run Elasticsearch as a standalone process. This is better anyway, because it
frees you to design your infrastructure the way you want it. If you've got
hardware or a VM to spare, you can separate your search infrastructure from
Liferay and reap some performance gains by putting search on a separate box. If
you're more budget-conscious, you can still increase performance by running
Elastic in a separate, individually tunable JVM on the same box. 

Installing Elasticsearch for Liferay is pretty easy and takes only five steps: 

1. Find the version of Elasticsearch that's embedded in the version of Liferay
   you have, and then download that version from [Elastic's](https://www.elastic.co) 
   website. 

2. Install Elasticsearch by extracting its archive to the system where you want
   it to run. 

3. Name your Elastic cluster. 

4. Configure Liferay to connect to your Elastic cluster. 

5. Restart Liferay and reindex your search indexes. 

Now you'll actually perform these steps, and when you're done, you'll have a
production-ready instance of @product@ up and running. 

### Step One: Find the Right Version of Elasticsearch [](id=step-one-find-the-right-version-of-elasticsearch)

If Liferay isn't running, start it. Then, using a browser, visit port 9200 of
the machine on which Liferay's running. For example, if you're browsing from the same machine
Liferay's running on, visit this URL: 

    http://localhost:9200

A JSON document is returned that varies slightly, but should look similar to
this: 

    {
      "name" : "Wiz Kid",
      "cluster_name" : "LiferayElasticsearchCluster",
      "version" : {
        "number" : "2.2.0",
        "build_hash" : "8ff36d139e16f8720f2947ef62c8167a888992fe",
        "build_timestamp" : "2016-01-27T13:32:39Z",
        "build_snapshot" : false,
        "lucene_version" : "5.4.1"
      },
      "tagline" : "You Know, for Search"
    }

The version of Elasticsearch that you want is the value of the `"number"` field.
In this example, it's 2.2.0. 

Now that you know the version of Elasticsearch you need, go to
[Elastic's](https://www.elastic.co) website and download that version. 

### Step Two: Install Elasticsearch [](id=step-two-install-elasticsearch)

Most of this step entails deciding where you want to run Elasticsearch. Do you
want to run it on the same machine as Liferay, or do you want to run it on its
own hardware? The answer to this question comes down to a combination of the
resources you have available and the size of your installation. Regardless of
what you decide, either way you get the benefit of a separately tunable search
infrastructure. 

Once you have a copy of the right version of Elasticsearch, extract it to a
folder on the machine where you want it running. That's all there is to this
step. 

### Step Three: Name Your Elastic Cluster [](id=step-three-name-your-elastic-cluster)

A *cluster* in Elasticsearch is a collection of nodes (servers) identified as a
cluster by a shared cluster name. The nodes work together to share data and
workload. A one node cluster is discussed here; to create a multi-node cluster,
please refer to [Elastic's documentation](https://www.elastic.co/guide/index.html). 

Now that you've installed Elastic, it sits in a folder on your machine, which is
referred to here as `[Elasticsearch Home]`. To name your cluster, you'll define
the cluster name in both Elasticsearch and in Liferay. First, define it in
Elastic. Edit the following file: 

    [Elasticsearch Home]/config/elasticsearch.yml

Uncomment the line that begins with `cluster.name`. Set the cluster name to
whatever you want to name your cluster: 

    cluster.name: liferay_cluster

Of course, this isn't a very imaginative name; you may choose to name your
cluster `finders_keepers` or something else you can remember more easily. Save
the file. 

Now you can start Elasticsearch. Run the executable for your operating system
from the `[Elasticsearch Home]/bin` folder: 

    ./elasticsearch

Elastic starts, and one of its status messages includes a transport address: 

    2016-05-03 16:33:28,358][INFO ][transport] [Hobgoblin II] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}

Take note of this address; you'll need to give it to your Liferay server so it
can find Elastic on the network. 

### Step Four: Configure Liferay to Connect to your Elastic Cluster [](id=step-four-configure-liferay-to-connect-to-your-elastic-cluster)

Now you're ready to configure Liferay. Start Liferay if you haven't already, log
in, and then go to Control Panel &rarr; Configuration &rarr; System Settings
&rarr; Foundation. Find *Elasticsearch* in the list of settings and click on it.
Now you can configure it. Here are the options you need to change: 

**Cluster name:** Enter the name of the cluster as you defined it in Elastic. 

**Operation mode:** Defaults to EMBEDDED. Change it to REMOTE to connect to a
standalone Elasticsearch. 

**Transport addresses:** Enter a delimited list of transport addresses for
Elastic nodes. Here, you'll enter the transport address from the Elastic server
you started. 

When finished, click *Save*. You're almost done. 

### Step Five: Restart Liferay and Reindex [](id=step-five-restart-liferay-and-reindex)

Stop and restart Liferay. When it's back up, log in as an administrative user
and go to Control Panel &rarr; Configuration &rarr; Server Administration and
click the *Execute* button for *Reindex all search indexes*. When you do that,
you should see some messages scroll up in the Elasticsearch log. 

You're almost done! The only thing left is to make sure Marketplace is working
and optionally configure portal security. 
