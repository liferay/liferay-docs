---
header-id: patching-tool-advanced-configuration
---

# パッチングツールの高度な設定方法

デフォルトでは、パッチングツールの設定ファイル`default.properties`はツールのフォルダにあります。



パッチングツールの設定ファイルは通常、以下のように表示されます：

    patching.mode=binary
    war.path=../tomcat-9.0.6/webapps/ROOT/
    global.lib.path=../tomcat-9.0.6/lib/ext/
    liferay.home=../

上記のプロパティ（詳しい説明は[below](#using-profiles-with-the-patching-tool)）は、[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)、パッチングモード（バイナリまたはソース）、WARファイルがアプリケーションサーバにデプロイされている場所へのパス、およびグローバルライブラリーパスの場所を定義します。このツールの自動検出機能は、OSGiモジュールフレームワークのパスをLiferay Homeに基づいています。ただし、OSGiモジュールフレームワークのパスをデフォルトフォルダ内の`[Liferay Home]/osgi`とは異なるパスに変更した場合は、次のプロパティをマニュアルで指定する必要があります。

    module.framework.core.path=path_to_modules_core_dir
    module.framework.marketplace.path=path_to_modules_marketplace_dir
    module.framework.modules.path=path_to_modules_modules_dir
    module.framework.portal.path=path_to_modules_portal_dir
    module.framework.static.path=path_to_modules_static_dir

自動検出を使用してデフォルトプロファイル`default.properties`を使用するのがパッチングツールを使用する最も簡単な方法であり、小規模の単一サーバーインストールに最適です。しかし、多くの@ product @のインストールは1日に何百万ものページを扱っているため、パッチングツールも同様にこのために設計されました。そのため、@product@ machineの小規模、中規模、または大規模なクラスタリングを実行している場合、規模の大きさにかかわらず、パッチングツールプロファイルを使用してすべてのパッチを管理できます。

## パッチングツールを使ったプロファイルの使用

自動検出の実行、またはマニュアルでプロファイルを作成することで、複数のランタイムに対してプロファイルを作成できます。他のランタイムを自動検出するには、次のようなパラメータを使ってパッチングツールを実行してください。

    ./patching-tool.sh [name of profile] auto-discovery [path/to/Liferay Home]

これは同じ検出プロセスを実行しますが、指定したパス上で実行されます。プロファイル情報を`[name of profile].properties`というファイルに書き込みます。
または、`patching-tool`フォルダ内にプロファイルプロパティファイルをマニュアルで作成することもできます。

次はパッチングツール設定プロパティ（プロファイルプロパティ）です。

## プロパティの設定方法

パッチングツールの設定プロパティは次のとおりです：

**patching.mode**：ソースツリーにパッチを適用する場合、`binary`（デフォルト）または`source`に使用できます。パッチには、バイナリパッチとソースパッチの両方が含まれています。開発チームが@ product @を拡張する場合、彼らにソースツリーをパッチしてもらってください。

**patches.folder**：パッチを保存する場所を指定します。デフォルトのロケーションは `./patches`です。

**war.path：**アプリケーションサーバー内に@ product @インストールのロケーションを指定します。代わりに、ここに`.war`ファイルを指定し、アプリケーションサーバーにインストールするために@ product @`.war`にパッチを当てることができます。



**global.lib.path：**`.jar`ファイルをグローバルクラスパスに保存する場所を指定します。確かでない場合は、グローバルクラスパス上にある`portal-kernel.jar`を検索してください。このプロパティは`patching.mode`が`binary`の時のみ有効です。



**liferay.home：**`data`、`osgi`、および `tools`フォルダのデフォルトの場所を指定してください。



**source.path：**@ product @sourceツリーの場所を指定してください。このプロパティは`patching.mode`が`source`の場合にのみ有効です。


| ** 注：**@ product-ver @ソースコードにパッチを適用するには、パッチングツールの最新バージョン2.0にアップグレードしてください。

サービスパックの検出はプロキシサーバーの裏側で利用できます。プロキシを設定するには、次の設定を使用して、必ず`[PROXY_IP_ADDRESS]`を使用しているプロキシサーバーのIPアドレスに置き換え、ポート番号を自分のに置き換えます。

    ### Proxy settings
    
    # HTTP Proxy
    
    #proxy.http.host=[PROXY_IP_ADDRESS]
    #proxy.http.port=80
    #proxy.http.user=user
    #proxy.http.password=password
    
    # HTTPS Proxy
    
    proxy.https.host=[PROXY_IP_ADDRESS]
    proxy.https.port=808
    proxy.https.user=user
    proxy.https.password=password
    
    # SOCKS Proxy
    
    #proxy.socks.host=[PROXY_IP_ADDRESS]
    #proxy.socks.port=1080
    #proxy.socks.user=user
    #proxy.socks.password=password

これで好きなだけの数のプロファイルを持つことができ、同じパッチングツールを使ってそれらすべてにパッチを当てることができます。これにより、すべてのインストールを一致させておくことができます。
