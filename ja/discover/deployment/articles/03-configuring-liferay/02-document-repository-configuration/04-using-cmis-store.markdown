---
header-id: using-the-cmis-store-ja
---

# CMISストアの使用

[TOC levels=1-4]

ドキュメントとメディアライブラリには、必要なだけのCMIS（Content Management Interoperability Services）リポジトリをマウントできますが、@product@リポジトリを定義してCMISリポジトリを指すこともできます。

CMISストアを使用するには、次の手順に従います。

1. `portal-ext.properties`をこのプロパティで設定します。

       dl.store.impl=com.liferay.portal.store.cmis.CMISStore
   
2. @product@を再起動します。

3. コントロールパネルから、*[設定]* → *[システム設定]* → *[ファイルストレージ]*へ移動します。

4. *CMISストア*画面で、ストアを設定します。

5. *CMISリポジトリ*画面で、リポジトリを設定します。

クラスタ内でCMISストアを使用するには、次の手順に従います。

1. `portal-ext.properties`を各ノードの[`[Liferay Home]`フォルダ](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)にコピーします。

2. *CMISストア*画面から[`.config`ファイル](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)に設定をエクスポートします。

3. *CMISリポジトリ*画面から[`.config`ファイル](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)に設定をエクスポートします。

4. `.config`ファイルを各ノードの`[Liferay Home]/osgi/configs`フォルダにコピーします。

5. ノードで@product@を再起動します。

@product@リポジトリは、CMISストアを介してCMISに接続されています。すべてのノードがCMISリポジトリを指している限り、CMISプロトコルによって複数の同時ファイルアクセスがデータ破損の原因となるのを防ぐため、クラスター内のすべてのもので問題ないはずです。
