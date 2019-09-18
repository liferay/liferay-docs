# Java仮想マシンのチューニング[](id=java-virtual-machine-tuning)

Java仮想マシン（JVM）のチューニングは、主にガーベージコレクターとJavaメモリヒープの調整に焦点を当てています。基準アーキテクチャーはOracleの1.8 JVMを使用しています。サポートされている他のJVMのバージョンとインプリメンテーションを選択することもできます。
その他の互換性のあるJVMについては[Liferay DXP 互換性マトリクス](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix)を参照してください 。

JVMチューニングに関するトピックは次のとおりです:

- [ガーベージコレクター](#garbage-collector)
- [コードキャッシュ](#code-cache)
- [Javaヒープ](#java-heap)
- [JVMの詳細オプション](#jvm-advanced-options)

まず、ガーベージコレクターから説明します。

## ガーベージコレクター[](id=garbage-collector)

適切なガーベージコレクター（GC）を選択することにより、@product@のデプロイの応答性が向上します。コンカレントポーズコレクターを使用します:

    -XX:+UseParNewGC -XX:ParallelGCThreads=16 -XX:+UseConcMarkSweepGC
    -XX:+CMSParallelRemarkEnabled -XX:+CMSCompactWhenClearAllSoftRefs
    -XX:CMSInitiatingOccupancyFraction=85 -XX:+CMSScavengeBeforeRemark

パラレルスループットコレクターやG1コレクターなど、他の利用可能なGCアルゴリズムからも選択できます。パラレルコレクターは新世代ので、コンカレントマークスイープコレクター（CMS）は旧世代のを使用してチューニングを開始します。

**注：**`ParallelGCThreads`の値（例：`ParallelGCThreads=16`）は利用可能なCPUのタイプに基づいて異なります。CPUの仕様に合わせて値を設定してください。Linuxマシンでは、`cat /proc/cpuinfo`を実行して利用可能なCPUの数を報告します。



**注：** G1のような追加の新しいアルゴリズムがありますが、Liferay EngineeringによるG1のテストでは、パフォーマンスの向上が見受けられませんでした。アプリケーションのパフォーマンスはそれぞれ異なる可能性があるので、テストおよびチューニング計画にG1を追加してください。

## コードキャッシュ[](id=code-cache)

Javaの実行時（JIT）コンパイラーは、パフォーマンスを向上させるためにネイティブコードを生成します。
デフォルトのサイズは`48m`です。しかしこのサイズは大きなアプリケーションには不十分な場合があります。
JITでは回数の多いメソッドを最適化できないため、コードキャッシュが小さすぎるとパフォーマンスが低下します。@product@の場合、最初は`64mの`コードキャッシュサイズから始めてください。

    -XX:InitialCodeCacheSize=64m -XX:ReservedCodeCacheSize=96m

以下のロギングパラメータを追加して、パラメータ変更の有効性を調べます：

    -XX:+PrintCodeCache -XX:+PrintCodeCacheOnCompilation

## Javaヒープ[](id=java-heap)

Javaメモリヒープをチューニングする時、多くの人はヒープの最大メモリと最小メモリを設定しようとします。しかし、ほとんどのデプロイでは最適なパフォーマンスを得るために、より若い世代のサイズ、Tenure期間、Survivor領域、その他多くのJVM内部のチューニングなど、はるかに洗練されたヒープチューニングが必要です。

ほとんどのシステムでは、少なくとも以下のメモリ設定から始めるのが最善です。

    -server -XX:NewSize=700m -XX:MaxNewSize=700m -Xms2048m -Xmx2048m -XX:MetaspaceSize=512m
    -XX:MaxMetaspaceSize=512m -XX:SurvivorRatio=6 -XX:TargetSurvivorRatio=9 -XX:MaxTenuringThreshold=15

大きなヒープサイズ（たとえば4GB以上）を必要とするシステムでは、大きなページサイズを使用することをお勧めします。このように大きなページサイズを有効化できます：

    -XX:+UseLargePages -XX:LargePageSizeInBytes=256m

アプリケーションプロファイルに基づいて異なるページサイズを指定することもできます。

**注：** JVMで大きなページを使用するには、基盤となるオペレーティングシステムを設定し直さないとアクティビーションできません。Linuxでは、`cat /proc/meminfo`を実行して"huge page"の項目を見てください。

+$$$

**注意：** JVMヒープに32GB以上を割り当てないようにしてください。ヒープサイズは、利用可能なCPUリソースの速度と量に見合ったものにする必要があります。


$$$

## JVMの詳細オプション [](id=jvm-advanced-options)

Liferayベンチマーク環境では、以下の高度なJVMオプションも適用されています。

    -XX:+UseLargePages -XX:LargePageSizeInBytes=256m
    -XX:+UseCompressedOops -XX:+DisableExplicitGC -XX:-UseBiasedLocking
    -XX:+BindGCTaskThreadsToCPUs -XX:UseFastAccessorMethods

高度なJVMオプションの詳細については、JVMのマニュアルを参照してください。

上記の推奨事項を組み合わせると、以下の構成になります：

    -server -XX:NewSize=1024m -XX:MaxNewSize=1024m -Xms4096m
    -Xmx4096m -XX:MetaspaceSize=512m -XX:MaxMetaspaceSize=512m
    -XX:SurvivorRatio=12 -XX:TargetSurvivorRatio=90
    -XX:MaxTenuringThreshold=15 -XX:+UseLargePages
    -XX:LargePageSizeInBytes=256m -XX:+UseParNewGC
    -XX:ParallelGCThreads=16 -XX:+UseConcMarkSweepGC
    -XX:+CMSParallelRemarkEnabled -XX:+CMSCompactWhenClearAllSoftRefs
    -XX:CMSInitiatingOccupancyFraction=85 -XX:+CMSScavengeBeforeRemark
    -XX:+UseLargePages -XX:LargePageSizeInBytes=256m
    -XX:+UseCompressedOops -XX:+DisableExplicitGC -XX:-UseBiasedLocking
    -XX:+BindGCTaskThreadsToCPUs -XX:+UseFastAccessorMethods
    -XX:InitialCodeCacheSize=32m -XX:ReservedCodeCacheSize=96m

+$$$

**注意：**上記のJVM設定は、パフォーマンスチューニングの出発点となるはずです。すべてのシステムの最終的なパラメータは、現在のユーザー数やトランザクション速度など、さまざまな要因によって異なります。

$$$

ガーベージコレクターの統計を監視して、ご使用の環境にメタスペースおよびSurvivor領域の十分な割り振りがあることを確認してください間違った環境で上記の設定を行うと、メモリ不足のような危険なランタイムシナリオが起こる可能性があります。不適切に調整されたSurvivor領域もヒープスペースの浪費につながります。
