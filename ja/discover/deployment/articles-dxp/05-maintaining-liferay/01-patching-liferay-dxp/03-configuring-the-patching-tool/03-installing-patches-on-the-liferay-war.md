# @product-ver@ WARへのパッチのインストール[](id=installing-patches-on-the-liferay-de-war)

サポートされているアプリケーションサーバーにWARファイルとして@ product @をマニュアルでインストールした場合は（参照：[installed @product@ manually](/discover/deployment/-/knowledge_base/7-1/installing-liferay-manually)） 、WARファイルとサポートファイルにパッチを適用し、それらを再びデプロイする必要があります。ここではそれをどう行うか説明します。



## 前提条件[](id=prerequisites)


[Customer Portal:](https://web.liferay.com/group/customer/dxp/downloads/7-1)から必要なアーティファクトをダウンロードします 。



- @product@ WARファイル（`liferay-dxp-[version].war`）

- 依存関係のZIPファイル（`liferay-dxp-dependencies-[version].zip`）

- OSGi JARのZIPファイル（`liferay-dxp-osgi-[version].zip`）

- [最新のパッチングツール](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise/patching-tool)

## @product@ WARとアーティファクトにパッチをインストールする[](id=how-to-install-a-fix-pack-on-the-liferay-war)


1. 任意のフォルダを作成してください。依存関係のアーティファクトとパッチングツールをそのフォルダに解凍します。フォルダの内容は次のようになるはずです：


   - `[patching-home]/`
      - `liferay-dxp-dependencies-[version]/` ←解凍された依存関係

      - `osgi/` ←解凍済みのOSGi JAR

      - `patching-tool/` ←解凍済みのパッチングツール

      - `liferay-dxp-[version].war/` ←@product@ WARファイル


2. パッチングツールフォルダにデフォルトのプロファイル設定ファイルを作成します： `patching-home/patching-tool/default.properties`内容は次のようになります。

       patching.mode=binary
       war.path=../../patching-home/liferay-dxp-[version].war
       global.lib.path=../../patching-home/liferay-dxp-dependencies-[version]
       liferay.home=../../patching-home
   
   別のOSGiフォルダー構造を使用している場合は、それを[Patching Tool Advanced Configuration](/discover/deployment/-/knowledge_base/7-1/patching-tool-advanced-configuration)の文書で説明されているとおりに指定できます。

       module.framework.core.path=/osgi-home/osgi/core
       module.framework.marketplace.path=/osgi-home/osgi/marketplace
       module.framework.modules.path=/osgi-home/osgi/modules
       module.framework.portal.path=/osgi-home/osgi/portal
       module.framework.static.path=/osgi-home/osgi/static
   
3. インストールするためにパッチ（フィックスパックまたはホットフィックス）をダウンロードして、パッチングツールフォルダ内の`patches`という名前のフォルダに入れてください。

       [patching-home]/patching-tool/patches
   
4. パッチングツールの`info`コマンドを実行します：

       /patching-home/patching-tool> patching-tool info
       Loading product and patch information...
       Product information:
       * installation type: binary
       * build number: 7110
       * service pack version:
       - available SP version: Not available
       - installable SP version: Not available
       * patching-tool version: 2.0.8
       * time: 2018-09-12 18:30Z
       * host: 91WRQ72 (8 cores)
       * plugins: no plugins detected
       
       Currently installed patches: -
       Available patches: dxp-2-7110
       
       Detailed patch list:
       [ I] dxp-2-7110 :: Currently not installed; Will be installed. :: Built for LIFERAY
   
5. パッチをインストールしてください。

       /patching-home/patching-tool> patching-tool.sh  install
       One patch is ready to be installed. Applying dxp-2...
       Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
       Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
       The installation was successful. One patch is installed on the system.
   
 これでアーティファクトは正常にパッチされたため、サポートされているアプリケーションサーバーにデプロイする準備が整いました。

## 関連トピック[](id=related-topics)

[Patching Tool Advanced Configuration](/discover/deployment/-/knowledge_base/7-1/patching-tool-advanced-configuration)

[Deploying @product@](/discover/deployment/-/knowledge_base/7-1/deploying-product)
