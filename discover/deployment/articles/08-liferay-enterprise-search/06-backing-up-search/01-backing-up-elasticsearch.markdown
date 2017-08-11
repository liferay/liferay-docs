# Backing Up Elasticsearch [](id=backing-up-elasticsearch)

[Elasticsearch replicas](https://www.elastic.co/guide/en/elasticsearch/guide/current/replica-shards.html)
protect you against a node going down here or there, but they won't help you in
the event of a catastrophic failure. Only good backup practices can help you
in that case.

The process for backing up and restoring your Elasticsearch cluster takes three
steps: 

- Configure a repository
- Make a snapshot of the cluster
- Restore from the snapshot

For more detailed information on the process, refer to the [Elasticsearch administration
guide](https://www.elastic.co/guide/en/elasticsearch/guide/current/administration.html),
and in particular to the documentation on the [Snapshot/Restore module](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/modules-snapshots.html)
and on [backing up your cluster.](https://www.elastic.co/guide/en/elasticsearch/guide/current/backing-up-your-cluster.html#_snapshotting_particular_indices)

## Configuring a Repository [](id=configuring-a-repository)

First [configure a repository](https://www.elastic.co/guide/en/elasticsearch/guide/current/backing-up-your-cluster.html#_creating_the_repository)
where your snapshots will be kept. Several repository types are supported:

- Shared file system, such as a Network File System or NAS
- Amazon S3
- HDFS (Hadoop Distributed File System)
- Azure Cloud

If using a shared file system repository type, first register the path to the
shared file system in each node's `elasticsearch.yml` using 
[the path.repo setting](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/modules-snapshots.html#_shared_file_system_repository).

    path.repo: ["path/to/shared/file/system/"]

When you create the repository using the Elasticsearch API, you can now refer to
this repository location in your PUT command:

    curl -XPUT localhost:9200/_snapshot/test_backup -d '{"type": "fs", "settings": { "location":"/path/to/shared/file/system/" } }'

In production replace `localhost:9200` with the proper `hostname:port`
combination for your system, and replace `test_backup` with the name of the
repository you want to create.  Additionally, use the real path to your shared
file system.

If the repository is successfully set up you'll see this message in your
terminal:

    {"acknowledged":true}

Once you have a repository, you can start creating snapshots.

## Snapshotting the Cluster [](id=snapshotting-the-cluster)

The easiest approach is to create is a [snapshot of all the indexes in your
cluster](https://www.elastic.co/guide/en/elasticsearch/guide/current/backing-up-your-cluster.html#_snapshotting_all_open_indices). Here's the basic command for snapshotting everything:

    curl -XPUT localhost:9200/_snapshot/test_backup/snapshot_1

If successful you see `{"accepted":true}` in the terminal.

You don't have to include all indexes in a snapshot. For example, if you're
[using Marvel](/deploy/-/official_documentation/deployment/monitoring-elasticsearch-with-marvel),
you might not want to include all the Marvel indexes. In this case just
explicitly declare the indexes you want to include in the snapshot (leaving out
the `.marvel` indexes):

    curl -XPUT localhost:9200/_snapshot/test_backup/snapshot_2
    { "indices": "liferay-0,liferay-20116" }

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
        "version_id":2020099,
        "version":"2.4.0",
        "indices":["liferay-0","liferay-20116"],
        "state":"SUCCESS",
        "start_time":"2016-11-29T19:50:12.375Z",
        "start_time_in_millis":1480449012375,
        "end_time":"2016-11-29T19:50:13.654Z",
        "end_time_in_millis":1480449013654,
        "duration_in_millis":1279,
        "failures":[],
        "shards":{
            "total":10,
            "failed":0,
            "successful":10

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
[restore your search indexes](https://www.elastic.co/guide/en/elasticsearch/guide/current/_restoring_from_a_snapshot.html) in case of catastrophic failure? Restoring your cluster from a snapshot is easy.
You'll leverage the `_restore` API:

    curl -XPOST localhost:9200/_snapshot/test_backup/snapshot_1/_restore

This command restores all the indexes in the snapshot. If you want to restore
only specific indexes from a snapshot, you can. For example, enter

    curl -XPOST
    localhost:9200/_snapshot/test_backup/snapshot_1/_restore
    {
        "indices": "liferay-20116",
        "rename_pattern": "liferayindex_(.+)",
        "rename_replacement": "restored_liferayindex_$1"
    }

This restores only the index named `liferay-20116index_1` from the snapshot. The
`rename...` settings specify that the beginning `liferayindex_` are replaced
with `restore_liferayindex_`, so `liferay-20116index_1` becomes
`restored_liferay-20116index_1`.

As with the snapshotting process, an errant restored index can be canceled with
the `DELETE` command:

    curl -XDELETE localhost:9200/restored_liferay-20116index_3

Nobody likes catastrophic failure on a production system, but Elasticsearch's
API for snapshotting and restoring indexes can help you rest easy knowing that
your search cluster can be restored if disaster strikes.
