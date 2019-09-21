---
header-id: monitoring-garbage-collection-and-jvm
---

# ガベージコレクションとJVMの監視

[チューニングパラメータ](/discover/deployment/-/knowledge_base/7-1/tuning-guidelines)は、 JVM チューニングのいい出発点となりますが、ニーズに合わせ最適な設定であることを確認するためにGCのパフォーマンスを監視しなければなりません。Oracle JVMのパフォーマンスを監視するのに役立つツールがいくつかあります。

## VisualVM

VisualVMは、Oracle JVMのパフォーマンス情報を表示するための集中型コンソールを提供し、そのVisual GCプラグインはガーベージコレクターのアクティビティを表示します。

![図1: VisualVMのVisual GCプラグインではリアルタイムにガーベージコレクターを表示します。](../../images-dxp/visual-vm-gc.png)

+$$$

**注： **OracleのJDKにはVisualVMバンドルがあります。（`$JAVA_HOME/bin/jvisualvm`）
ただし、常に最新のVisualVMを[official website](https://visualvm.github.io/)からダウンロードして使用してください。

$$$

## JMXコンソール


このツールは、@product@の分散キャッシュパフォーマンス、アプリケーションサーバースレッドのパフォーマンス、JDBC接続プールの使用状況など、さまざまな統計情報を表示するのに役立ちます。

+$$$

**注：**JMXコンソールはTomcatを監視するために推奨されているツールです。

$$$

JMX接続を有効にするには、以下のJVM引数を追加します：

    -Dcom.sun.management.jmxremote=true
    -Dcom.sun.management.jmxremote.port=5000
    -Dcom.sun.management.jmxremote.authenticate=false
    -Dcom.sun.management.jmxremote.ssl=false

![図2: VisualVMはJava Management Extensionを使用しているJVMを監視します。](../../images-dxp/visual-vm-jmx.png)

## ガーベージコレクターの冗長ログ

これらのJVM引数を追加して、JVMガーベージコレクターの冗長ログを有効にします。

    -verbose:gc -Xloggc:/tmp/liferaygc1.log -XX:+PrintGCDetails
    -XX:+PrintGCCause -XX:+PrintGCApplicationConcurrentTime
    -XX:+PrintGCApplicationStoppedTime

これらのログを調べるとJVMを正しく調整するのに役立ちます。

**注：**これらのJVM引数を追加すると、`OutOfMemoryError`が発生した場合にヒープダンプが生成されます。

ダンプは指定されたヒープ・ダンプ・パスに書き込まれます。
使用するパスを指定してください。<segment 0032>


    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/heap/dump/path/

これでJVMでガベージコレクションを監視し、最高のパフォーマンスを発揮できるように調整できます。
