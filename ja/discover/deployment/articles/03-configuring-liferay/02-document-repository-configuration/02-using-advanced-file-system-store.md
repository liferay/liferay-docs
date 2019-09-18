# Advanced File System Store の使用[](id=using-the-advanced-file-system-store)

Advanced File System Store は、Simple File System Store（デフォルトストア）と似ています。Simple File System Storeのように、ローカルファイルシステムにファイルを保存します。ローカルファイルシステムは、リモートファイルシステムマウントもあり得ます。下の図のように、ファイルを保存するためにわずかに異なるフォルダー構造を使用します。

![図1：Advanced File System Storeは、ファイルシステムストアよりもネストされたフォルダ構造を作成します。](../../../images/enterprise-adv-file-system-store.png)

 いくつかのオペレーティングシステムは、特定のフォルダに保存できるファイル数に制限があります。Advanced File System Storeは、ファイルをアルファベット順にフォルダにネストすることによって、プログラムから何百万というファイルに拡張できる構造を作成し、この制限を克服します。これにより、より多くのファイルを保存できるだけでなく、フォルダーごとに保存されるファイルの数が減るため、パフォーマンスも向上します。

Simple File System Storeに適用されるのと同じ規則がAdvanced File System Storeにも適用されます。これをクラスタ化するには、すべてのノードがアクセスできるネットワークマウントファイルシステムをストアに指定し、そのネットワークファイルシステムが同時要求とファイルロックをサポートする必要があります。そうでなければ、2人のユーザーが2つの異なるノードから同じファイルに同時に書き込もうとすると、データが破損する可能性があります。

Advanced File System Storeを使用するには、次の手順に従います。

1. `portal-ext.properties`をこのプロパティで設定します。

       dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
   
2. @product@を再起動します。

3. コントロールパネルから、*[設定]* → *[システム設定]* → *[ファイルストレージ]*へ移動します。

4. *Advanced File System Store* 画面で、ストアを設定します。

マルチノード環境でAdvanced File System Storeを使用するには、次の手順に従います。

1. `portal-ext.properties`を各ノードの[`[Liferay Home]`フォルダ](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)にコピーします。

2. *Advanced File System Store*画面から[`.config`ファイル](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)に設定をエクスポートします。

3. `.config`ファイルを各ノードの`[Liferay Home]/osgi/configs`フォルダにコピーします。

4. ノードで@product@を再起動します。

@product@はAdvanced File System Storeを使用しています。

+$$$

**警告：**データベーストランザクションのロールバックがドキュメントライブラリで発生した場合、トランザクションの開始以降に発生したファイルシステムの変更は元に戻されません。ドキュメントライブラリファイルとファイルシステムストア内のファイルとの間に矛盾が生じる可能性があり、手動での同期が必要になる場合があります。

$$$

いかなる理由であれ、ニーズを満たすことができない場合は、Advanced File System Storeを選択することができます。このような場合は、もちろんドキュメントやメディアライブラリに他のファイルシステムをマウントすることができます。これに加えて、他のサポートされているプロトコルの1つを使用するように@product@ストアを再定義することもできます。次はCMISストアです。
