---
header-id: patching-tool-basic-configuration-ja
---

# パッチングツール基本設定方法

[TOC levels=1-4]

パッチングツールの設定方法は2つあります：


1. `auto-discovery`コマンドを実行して自動的に設定する方法。



2. 設定ファイルを編集してマニュアルで設定する方法（詳細はこちら：[Patching Tool Advanced Configuration](/discover/deployment/-/knowledge_base/7-1/patching-tool-advanced-configuration)）。



自動設定は、ローカルファイルシステム内の@ product @ファイルを探しだして設定ファイルを生成します。デフォルトでは、パッチングツールはその親フォルダで探しだします。プロセスを開始するには、パッチングツールフォルダ（`patching-tool`）で以下のコマンドを実行します：

    patching-tool auto-discovery

@ product @が親フォルダにインストールされていない場合は、その場所を指定します：

    patching-tool auto-discovery /opt/liferay-dxp

@ product @の場所を間違えて指定した、または親フォルダにない場合は、パッチングツールは[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)を見つけることができず 、次のようなエラーを報告します：

    The .liferay-home has not been detected in the given directory tree.
    
    Configuration:
    patching.mode=binary
    war.path=../tomcat-9.0.6/webapps/ROOT/
    global.lib.path=../tomcat-9.0.6/lib/ext/
    liferay.home=**[please enter manually]**
    
    The configuration hasn't been saved. Please save this to the default.properties file.

Liferay Homeの問題を解決する方法は次のとおりです：


- `default.properties`ファイルにLiferay Homeのパスを指定します。

- Liferay Homeがパッチングツールのツリーにある場合、`.liferay-home`ファイルをLiferay Homeフォルダ内に作成して、自動検出プロセスを再実行してください。


パッチングツールが設定されると、`patching-tool info`を実行した際製品バージョン情報が報告されます。



これで以上です。パッチングツールをインストールして設定したので、これでパッチをダウンロードしてインストールする準備が整いました。
