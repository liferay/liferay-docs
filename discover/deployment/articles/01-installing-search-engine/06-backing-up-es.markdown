# Backing Up Elasticsearch

[Elasticsearch replicas](https://www.elastic.co/guide/en/elasticsearch/guide/master/replica-shards.html)
protect you against a node going down here or there, but they won't help you in
the event of a catastrophic failure. Only good backup practices can help you
in that case.

Back up and restore your Elasticsearch cluster in three steps: 

1.  Configure a repository
2.  Make a snapshot of the cluster
3.  Restore from the snapshot

For more detailed information, refer to the 
[Elasticsearch administration guide](https://www.elastic.co/guide/en/elasticsearch/guide/master/administration.html),
and in particular to the documentation on the 
[Snapshot/Restore module](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/modules-snapshots.html).

## Creating a Repository [](id=creating-a-repository)

First [create a repository](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/modules-snapshots.html#_repositories)
where your snapshots are to be kept. Several repository types are supported:

- Shared file system, such as a Network File System or NAS
- Amazon S3
- HDFS (Hadoop Distributed File System)
- Azure Cloud

If using a shared file system repository type, first register the path to the
shared file system in each node's `elasticsearch.yml` using 
[the path.repo setting](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/modules-snapshots.html#_shared_file_system_repository).

    path.repo: ["path/to/shared/file/system/"]

Once the path to the folder hosting the repository is registered (make sure the
folder exists), create the repository with a PUT command:

    curl -X PUT "localhost:9200/_snapshot/test_backup" -H 'Content-Type: application/json' -d'
    {
      "type": "fs",
      "settings": {
        "location": "/path/to/shared/file/system/"
      }
    }
    '
Replace `localhost:9200` with the proper `hostname:port` combination for your
system, replace `test_backup` with the name of the repository to create, and use
the absolute path to your shared file system in the `location`.

If the repository is successfully set up you'll see this message in your
terminal:

    {"acknowledged":true}

Once the repository exists, you can start creating snapshots.

## Snapshotting the Cluster [](id=snapshotting-the-cluster)

The easiest snapshotting approach is to create a 
[snapshot of all the indexes in your cluster](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/modules-snapshots.html#_snapshot). 
To snapshot everything, enter

    curl -XPUT localhost:9200/_snapshot/test_backup/snapshot_1

If `{"accepted":true}` appears in the terminal, the snapshot was a success.

It's possible to be more selective when snapshotting. For example, if you're
[using X-Pack Monitoring](https://customer.liferay.com/documentation/7.1/deploy/-/official_documentation/deployment/installing-x-pack-monitoring),
you can exclude the monitoring indexes. Explicitly declare the indexes to
include in the snapshot:

    curl -XPUT localhost:9200/_snapshot/test_backup/snapshot_2
    { "indices": "liferay-0,liferay-20116" }

+$$$

**Note:** For a list of all the Elasticsearch indexes, use this command:

    curl -X GET "localhost:9200/_cat/indices?v"

The indexes are listed with various metrics reported:

    health status index         uuid                   pri rep docs.count docs.deleted store.size pri.store.size
    green  open   liferay-20099 obqiNE1_SDqfuz7rincrGQ   1   0        195            0    303.1kb        303.1kb
    green  open   liferay-47206 3YEjtye1S9OVT0i0EZcXcw   1   0          7            0     69.7kb         69.7kb
    green  open   liferay-0     shBWwpkXRxuAmGEaE475ug   1   0        147            1    390.9kb        390.9kb

$$$

It's important to note that Elasticsearch uses a *smart snapshotting* approach.
To understand what that means, consider a single index. The first snapshot
includes a copy of the entire index, while subsequent snapshots only include the
delta between the first, complete index snapshot and the current state of the
index.

Eventually you'll end up with a lot of snapshots in your repository, and no
matter how cleverly you name the snapshots, you may forget what some snapshots
contain. For this purpose, the Elasticsearch API includes the ability to get
information about any snapshot. For example:

    curl -XGET localhost:9200/_snapshot/test_backup/snapshot_1

returns

    {"snapshots":[
        {"snapshot":"snapshot_1",
        "uuid":"WlSjvJwHRh-xlAny7zeW3w",
        "version_id":6010399,
        "version":"6.1.3",
        "indices":["liferay-20099","liferay-0","liferay-47206"],
        "state":"SUCCESS",
        "start_time":"2018-08-15T21:40:17.261Z",
        "start_time_in_millis":1534369217261,
        "end_time":"2018-08-15T21:40:17.482Z",
        "end_time_in_millis":1534369217482,
        "duration_in_millis":221,
        "failures":[],
        "shards":{
            "total":3,
            "failed":0,
            "successful":3
            
            }
        }
    ]}

There's lots of useful information here, including which indexes were
included in the snapshot.

If you want to get rid of a snapshot, use the `DELETE` command.

    curl -XDELETE localhost:9200/_snapshot/test_backup/snapshot_1

You might trigger creation of a snapshot and regret it (for example, you didn't
want to include all the indexes in the snapshot). If you're snapshotting a lot
of data, this can cost time and resources. To cancel the ongoing creation of a
snapshot, use the same `DELETE` command.  The snapshot process is terminated and
the partial snapshot is deleted from the repository.

## Restoring from a Snapshot [](id=restoring-from-a-snapshot)

What good is a snapshot if you can't use it to 
[restore your search indexes](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/modules-snapshots.html#_restore) 
in case of catastrophic failure? Use the `_restore` API to restore all the
snapshot's indexes:

    curl -XPOST localhost:9200/_snapshot/test_backup/snapshot_1/_restore

Restore only specific indexes from a snapshot by passing in the `indices`
option, and rename the indexes using the `rename_pattern` and
`rename_replacement` options:

    curl -XPOST
    localhost:9200/_snapshot/test_backup/snapshot_1/_restore
    {
        "indices": "liferay-20116",
        "rename_pattern": "liferayindex_(.+)",
        "rename_replacement": "restored_liferayindex_$1"
    }

This restores only the index named `liferay-20116index_1` from the snapshot. The
`rename...` settings specify that the beginning `liferayindex_` are replaced
with `restored_liferayindex_`, so `liferay-20116index_1` becomes
`restored_liferay-20116index_1`.

As with the snapshotting process, an errant restored index can be canceled with
the `DELETE` command:

    curl -XDELETE localhost:9200/restored_liferay-20116index_3

Nobody likes catastrophic failure on a production system, but Elasticsearch's
API for snapshotting and restoring indexes can help you rest easy knowing that
your search cluster can be restored if disaster strikes. For more details and
options, read Elastic's documentation on the 
[Snapshot and Restore Module](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/modules-snapshots.html#modules-snapshots).
