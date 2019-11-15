---
header-id: backing-up-elasticsearch
---

# Elasticsearchのバックアップ

[TOC levels=1-4]

[Elasticsearchレプリカ](https://www.elastic.co/guide/en/elasticsearch/guide/master/replica-shards.html)は、ノードが停止するのを防ぎますが、壊滅的な障害には役立ちません。壊滅的な障害の場合、バックアップをしっかり取っておく習慣でしか対処できません。

Elasticsearchクラスターを3つのステップでバックアップおよび復元します。

1. リポジトリを設定する

2. クラスターのスナップショットを作成する

3. スナップショットから復元する

詳細については、[Elasticsearch管理ガイド](https://www.elastic.co/guide/en/elasticsearch/guide/master/administration.html)、特に[Snapshot / Restoreモジュール](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-snapshots.html)のドキュメンテーションを参照してください 。

## リポジトリを作成する

まず、スナップショットを保存するための[リポジトリを作成](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-snapshots.html#_repositories)します。いくつかのリポジトリタイプがサポートされています。

- ネットワークファイルシステムやNASなどの共有ファイルシステム
- Amazon S3
- HDFS（Hadoop分散ファイルシステム）
- Azureクラウド

共有ファイルシステムのリポジトリタイプを使用している場合は、最初に[path.repo設定](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-snapshots.html#_shared_file_system_repository)を使用して、各ノードの`elasticsearch.yml`にある共有ファイルシステムへのパスを登録します。

    path.repo: ["path/to/shared/file/system/"]

リポジトリをホストしているフォルダへのパスが登録されたら（フォルダが存在することを確認してください）、PUTコマンドでリポジトリを作成します。

    curl -X PUT "localhost:9200/_snapshot/test_backup" -H 'Content-Type: application/json' -d'
    {
    "type": "fs",
    "settings": {
    "location": "/path/to/shared/file/system/"
    }
    }
    '
`localhost:9200`を使用しているシステムに適した`hostname:port`の組み合わせに置き換え、`test_backup`を作成するリポジトリの名前に置き換え、そして`ロケーション`内にある共有ファイルシステムへの絶対パスを使用します。

リポジトリが正常に設定されると、次のメッセージが表示されます。

    {"acknowledged":true}

リポジトリが完成したら、スナップショットの作成を始めることができます。

## クラスターのスナップショットの取得

最も簡単な方法は、クラスター内のすべてのインデックスのスナップショットを作成することです。
すべてをスナップショットするには、次のように入力します。

    curl -XPUT localhost:9200/_snapshot/test_backup/snapshot_1

`{"accepted":true}`端末に表示されていれば、スナップショットは成功しています。

スナップショットを取得する際、選択肢をさらに絞り込むこともできます。たとえば、[X-Packモニタリング](https://help.liferay.com/hc/en-us/articles/360018176011-Installing-X-Pack-Monitoring-)を使用している場合は、モニタリングインデックスを除外できます。スナップショットに含めるインデックスを明示的に宣言します。

    curl -XPUT localhost:9200/_snapshot/test_backup/snapshot_2
    { "indices": "liferay-0,liferay-20116" }

| **Note:** For a list of all the Elasticsearch indexes, use this command:
|
|     curl -X GET "localhost:9200/_cat/indices?v"
|
| This shows the index metrics:
|
|     health status index         uuid                   pri rep docs.count docs.deleted store.size pri.store.size
|     green  open   liferay-20099 obqiNE1_SDqfuz7rincrGQ   1   0        195            0    303.1kb        303.1kb
|     green  open   liferay-47206 3YEjtye1S9OVT0i0EZcXcw   1   0          7            0     69.7kb         69.7kb
|     green  open   liferay-0     shBWwpkXRxuAmGEaE475ug   1   0        147            1    390.9kb        390.9kb

Elasticsearchは*スマートスナップショット*アプローチを使用していることに注意することが重要です。
それが何を意味するのかを理解するために、単一のインデックスについて考えます。最初のスナップショットにはインデックス全体のコピーが含まれ、後続のスナップショットには最初の完全なインデックススナップショットとインデックスの現在の状態との差分のみが含まれます。

最終的にはリポジトリにたくさんのスナップショットが作成されることになり、スナップショットにどれほど賢く名前を付けても、いくつかのスナップショットに何が含まれているのか忘れてしまう可能性があります。
そのため、Elasticsearch APIはスナップショットに関する情報を取得し、提供します。例えば：

    curl -XGET localhost:9200/_snapshot/test_backup/snapshot_1

は以下を返します。

    {"snapshots":[
    {"snapshot":"snapshot_1",
    "uuid":"WlSjvJwHRh-xlAny7zeW3w",
    "version_id":6.50399,
    "version":"6.5.1",
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

どのインデックスがスナップショットに含まれていたかなどの有用な情報がたくさんあります。


スナップショットを削除したい場合は、`DELETE`コマンドを使用してください。

    curl -XDELETE localhost:9200/_snapshot/test_backup/snapshot_1

スナップショットの作成をトリガーして後悔する可能性があります（たとえば、スナップショットにすべてのインデックスを含めたくない場合）。スナップショットに大量のデータが含まれていると、時間とリソースがかかる可能性があります。進行中のスナップショットの作成をキャンセルするには、同じ`DELETE`コマンドを使用します。スナップショットのプロセスが終了し、部分スナップショットはリポジトリから削除されます。

## スナップショットからの復元

壊滅的な障害が発生した場合に、スナップショットを使用して[サーチインデックスを復元](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-snapshots.html#_restore)することはできませんが、以下の方法があります。`_restoreAPI`を使用して、すべてのスナップショットのインデックスを復元します。

    curl -XPOST localhost:9200/_snapshot/test_backup/snapshot_1/_restore

`indices`オプションを渡してスナップショットから特定のインデックスのみを復元し、`rename_pattern` and `rename_replacement`オプションを使用してインデックスの名前を変更します。

    curl -XPOST
    localhost:9200/_snapshot/test_backup/snapshot_1/_restore
    {
    "indices": "liferay-20116",
    "rename_pattern": "liferayindex_(.+)",
    "rename_replacement": "restored_liferayindex_$1"
    }

これは、スナップショットから`liferay-20116index_1`というインデックスのみを復元します。`rename...`設定は、先頭の`liferayindex_`が`restored_liferayindex_`に置き換えられるように指定します。したがって、`liferay-20116index_1`は`restored_liferay-20116index_1`になります。

スナップショットを作成するプロセスと同様に、誤って復元されたインデックスは`DELETE`コマンドでキャンセルできます。

    curl -XDELETE localhost:9200/restored_liferay-20116index_3

 詳細とオプションについては、[スナップショットと復元モジュール](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-snapshots.html#modules-snapshots)に関するElasticsearchのドキュメンテーションを参照してください。
