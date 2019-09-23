---
header-id: document-repository-configuration
---

# ドキュメントリポジトリの設定

ファイルストレージはいくつかの方法で設定できます。
各オプションは、[`dl.store.impl=`プロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Document%20Library%20Service)を設定することで`portal-ext.properties`ファイルを介して設定することができる*ストア*です。

デフォルトストアはSimple File Storeと呼ばれます。ドキュメントとメディアファイルをファイルシステム（ローカルまたはマウント済み）に保存します。ストアのデフォルトのルートフォルダは`[Liferay Home]/data/document_library`です。[システム設定](/discover/portal/-/knowledge_base/7-1/system-settings)内から別のルートディレクトリを指定できます。
システム設定にアクセスするには、*メニュー*（![メニュー](../../../images/icon-menu.png)）を開き、*[コントロールパネル] &rarr; [設定] &rarr; [システム設定]*に移動します。システム設定から*[プラットフォーム]*に移動し、*[ファイルストレ―ジ]*（![フォルダ](../../../images/icon-folder.png)）を検索してクリックします。
ストアの*ルートディレクトリ*値には、[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)への相対パスまたは絶対パスを指定します。次に*[保存]*ボタンをクリックします。ドキュメントライブラリストアはすぐに新しいフォルダに切り替わります。

![図1：システム設定のファイルストレージのページでは、ドキュメントリポジトリの保存場所を設定できます。](../../../images/file-storage.png)


| **注：**ストア設定をクラスターに伝播するには、システム設定内にあるストアの*ファイルストレージ*画面から、`.config`ファイルにストア設定をエクスポートします。そして、そのファイルを各ノードの`[Liferay-Home]/osgi/config`フォルダーにコピーします。設定をエクスポートするには、オプションアイコン![オプション](../../../images/icon-options.png)から*[エクスポート]*を選択します。

ドキュメントとメディアファイルを保存するために、全く異なる方法を使用することもできます。

**Simple File System Store**：ファイルを保存するために、ファイルシステム（ローカルまたはマウントされた共有）を使用します。

Advanced File System Store：ファイルシステム（ローカルまたはマウントされた共有）を使用してファイルを格納するだけでなく、Advanced File System Storeはファイルをバージョン別にフォルダーにネストし、パフォーマンスを向上させ、より多くのファイルを格納します。

**S3 Store（Amazon Simple Storage）**：Amazonのクラウドベースのストレージソリューションを使用しています。

**CMISストア（コンテンツ管理相互運用サービス）**：ファイルを格納するためにCMIS準拠のサーバーに接続します。

**DBStore（データベースストレージ）**：ファイルをデータベースに保存します。DBStoreのファイル（BLOBとして保存される）サイズは1ギガバイトです。1ギガバイトを超えるファイルを保存するには、Simple File System StoreまたはAdvanced File System Storeを使用します。

これらの記事はそれぞれの詳細を説明しています。
