# パッチを使った作業[](id=working-with-patches)

以下のようなパッチ関連のことをする必要があるかもしれません：

- [
Liferayサポートへのパッチレベルの報告](#including-support-info-in-support-tickets)
- [パッチのアンインストール](#uninstalling-patches)
- [パッチレベルの比較](#comparing-patch-levels)
- [パッチとデプロイされたプラグインの衝突を表示](#list-collisions)
- [インストールからパッチを分ける](#separating-patches-from-the-installation)

Liferayサポートへのパッチレベルの報告から始めます。

## Support Ticketsにsupport-infoを足す[](id=including-support-info-in-support-tickets)

問題を再現するには、環境の情報（ハードウェアアーキテクチャなど）とパッチレベルをLiferayサポートに提供しなければいけません。以下のコマンドを実行して、（パッチレベルを含む）サポート情報をファイルに書き込みます。



    patching-tool support-info

サポート情報は`patching-tool`フォルダ内の`patching-tool-support-info-actual-timestamp.txt`ファイルに書き込まれます。このファイルをLESAチケットにアップロードしてください。

## パッチのアンインストール[](id=uninstalling-patches)

パッチングツールに`install`コマンドしかないことに気がつきましたか？
これは、パッチがコマンドではなく、`patches`フォルダに表示されるものによって管理されているためです。このフォルダからパッチを追加または削除して、インストールしたパッチを管理します。



パッチをアンインストール（削除）する方法は次のとおりです：



1. `patches`フォルダからパッチを削除してください。



2. `patching-tool install`コマンドを実行してください。



すべてのパッチを元に戻すには、次のコマンドを実行します：



    patching-tool revert

以上がパッチを削除、そして元に戻す方法です。

## パッチレベルの比較[](id=comparing-patch-levels)

パッチングツールは異なる@ product @パッチとバージョンの間で何が変わったかを示すことができるため、これは開発者にとっては便利な機能です。これらのコマンドは、さまざまなパッチレベルに関する情報を表示します：

`patching-tool diff`：2つのパッチレベルの違いをプリントします。少なくとも1つの保存済みパッチレベルが利用可能である必要があります。このコマンドは、出力をフィルタリングするためのオプションを受け入れます。

- `source`：2つのパッチレベルの違いを表示します。

- `files`：修正したファイルの一覧を表示します。

- `fixed-issues`：問題追跡システムからのLPS / LPE問題のリストを表示します。

- `html`：これを一つのフィルタオプションとともに指定し、（`source`、 `files`、または`fixed-issues`）パッチが同じレベルになった後、`diffs`フォルダ内に違いをHTMLファイルに書きます。（`<stored-name-1>-<stored-name-2>-[type]-diff.html`）
追加部分は緑色に、削除部分は赤色に着色されています。

- `collisions`：デプロイされたプラグインと衝突する修正されたファイルのリストを表示します。


詳しい使用法については、`patching-tool help diff`を実行してください。



`patching-tool store`：`diff`コマンドのパッチングレベルの情報を管理します。パッチには、パッチレベルを保存するためのもの、また`diff`コマンドで使用できる情報を準備するためのソースコードが含まれている必要があります。`store`コマンドオプションは次のとおりです：



- `info`：保存されているパッチレベルを構成するパッチの一覧をプリントします。

- `add`：パッチディレクトリにあるパッチレベルを保存します。

- `update`：パッチレベルの情報を追加または更新します。

- `rm`：以前に保存したパッチレベル情報を削除します。


詳しい使用法については、`patching-tool help store`を実行してください。

## パッチとデプロイされたプラグインの衝突を表示する[](id=list-collisions)

いくつかのパッチは、プラグインによってカスタマイズしたかもしれないファイルを更新します。この`patching-tool list-collisions`コマンドは、インストールされたパッチファイルとプラグインのバージョンの（衝突の）違いをリストします。これがコマンドです：


    patching-tool list-collisions

これは、以下のdiffコマンドの別名です。



    patching-tool diff collisions files _base

`_base`は文字通りのパッチレベル名です。衝突は、ソースコードファイルを含むインストール済みパッチに対してのみ一覧表示されます。



+$$$

**注： **パッチングツール2.0.9以降、`patching-tool list-collisions`はフラグメントバンドル内のJSPファイルの衝突のみリストします。

$$$

## インストールからのパッチの分離[](id=separating-patches-from-the-installation)

パッチングツールの`separate`コマンドは、パッチ適用済みの@ product @のインストールサイズを小さくするのに役立ちます。インストールにパッチが適用されている場合は、そこから復元ファイルを移動することで、インストールサイズを小さくすることができます。



なぜパッチを適用したインストールが大きいかというと、復元ファイルはWebアプリケーションの`WEB-INF`フォルダ内にデフォルトで保存されているからです。これらのファイルは、インストールに再度パッチを適用するために必要です。



これらのファイルが削除されると、その後のパッチ適用プロセスは失敗します。このため、Liferayは、新しいパッチが届いたときにファイルを安全に保存および復元しながら、インストールからパッチファイルを分離するオプションを追加しました。これを行うには、次のコマンドを使用します。

    patching-tool separate [separation_name]

このコマンドはパッチングツールの`patches`フォルダ内に`liferay-patching-files-[separation-name].zip`ファイルを作成します。ここにはパッチング、検証、そして確認するために必要なファイルとメタデータが含まれています。このファイルを作成すると、パッチファイルはデフォルトのロケーションから削除され、このファイルでのみ使用可能になります。このファイルを他の場所に保存して、インストールのサイズを小さくすることができます。



**警告**：この方法で製品がそのパッチから分離されていると、パッチが復元されるまで、ほとんどのパッチングツールコマンドを実行できません。



分離処理後は、以下のコマンドのみ使用できます：

- `auto-discovery`
- `info`
- `setup`

他のコマンドを使うと以下が表示されます：

    This installation does not include data for patching. Please copy the
    liferay-patching-files-[separation-name].zip file into the 'patches' directory
    and run patching-tool setup.

これが、パッチファイルをシステムに復元する方法です。詳細は下記。

## 分離したパッチファイルの復元[](id=restoring-the-separated-patch-files)

@ product @に再度パッチを適用する必要がある場合は、分離したパッチアーティファクトを復元する必要があります。これを行うためには、`liferay-patching-files-[separation-name].zip`をパッチングツールの`patches`フォルダに再度コピーして、`patching-tool setup`コマンドを実行してください。



このコマンドは、必要なパッチングアーティファクトを見つけて、パッチファイルをインストール済み環境に復元します。その後、パッチングツールはパッチを分離する前と同じように機能します。
