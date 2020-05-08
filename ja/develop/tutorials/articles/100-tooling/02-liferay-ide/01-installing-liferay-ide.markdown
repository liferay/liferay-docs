---
header-id: installing-liferay-ide
---

# Liferay @ide@のインストール

[TOC levels=1-4]

Liferay @ide@はEclipseのプラグインで、Liferay固有の機能を数多く提供します。これを既存のEclipse環境にインストールをする方法と、LiferayがProject SDKにバンドルされたバージョンを提供する方法があります。このチュートリアルでは、以下のようにDev Studioのインストールに使用できる複数の方法について説明します。

- [Dev Studioバンドルを最初からインストールする](#install-the-dev-studio-bundle)
- [更新URLを使用して、既存のEclipseインスタンスにDev Studioをインストールする](#install-dev-studio-into-eclipse)
- [ZIPファイルを使用して、Dev Studioを既存のEclipseインスタンスにインストールする](#install-dev-studio-into-eclipse-from-a-zip-file)

**重要：**Dev Studioを既存のEclipse環境にインストールする場合は、Eclipse Photon以降を使用する必要があります。Photonにアップグレードする手順については、Eclipseの[アップグレードドキュメンテーション](https://wiki.eclipse.org/FAQ_How_do_I_upgrade_Eclipse_IDE%3F#Upgrading_existing_Eclipse_IDE_and_Installed_Features_to_newer_release)を参照してください。
この特定のアップグレードでは、*[ウィンドウ]* &rarr; *[設定]* &rarr; *[インストール/アップデート]* &rarr; *[利用可能なソフトウェアサイト]*メニューで現在利用可能な更新サイトを非アクティブ化して、アップグレードを成功させる必要があります（Oxygenなど）。

## Dev Studioバンドルをインストールする

1. [Java](http://java.oracle.com)をダウンロードしてインストールします。LiferayはJava上で実行されるため、すべてを実行するためにはJavaが必要になるからです。Dev StudioでLiferay Portalのアプリを開発することになるので、Java Development Kit（JDK）が必要です。これは、新しいJavaテクノロジーの開発に使用される、Java環境の拡張バージョンです。Java SE JDKは、Javaの[ダウンロード](http://www.oracle.com/technetwork/java/javase/downloads/index.html)ページからダウンロードできます。

2. 使用しているオペレーティングシステムに対応する、最新の[Project SDK with @ide@](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/)の実行ファイルをダウンロードします。Project SDKの実行ファイルには、@ide@、 [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)、および[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)が含まれています。

3. Project SDKの実行ファイルを実行し、インストーラーを実行して、すべてをマシンにインストールします。プロキシ設定のセットアップに関するヘルプ（必要な場合）の詳細については、 [Liferay IDE Proxy Settings](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-ide)および[Liferay Workspace Proxy Settings](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-workspace)のチュートリアルを参照してください。

 これで、Liferay @ide@のインストールが完了したので、Project SDKフォルダの`liferay-developer-studio`で利用できるようになりました。Dev Studioを実行するには、`DeveloperStudio`の実行ファイルを実行し ます。Liferay Workspaceも同じフォルダで初期化されています。

 これで、Liferay Dev Studioがインストールされました。

## EclipseにDev Studioをインストールする

他の用途に使用しているEclipse環境が既にある場合は、既存のEclipseインストールにDev Studioを簡単に追加することができます。

1. ブラウザで、 [Liferay @ide@](https://community.liferay.com/en_GB/project/-/asset_publisher/TyF2HQPLV1b5/content/ide-installation-instructions)のページに移動します。最新の安定した更新サイトのURLをコピーします。

2. *[ヘルプ]*→*[Install New Software]*を選択します。

3. *[作業]*フィールドにURLをコピーします。

4. Liferay Dev Studioコンポーネントが以下のリストに表示されます。それらのチェックを外して、*[次へ]*をクリックします。

5. 契約条項に同意して*[次へ]*をクリックすると、Dev Studioがインストールされます。他のEclipseプラグインと同様に、Eclipseを再起動して有効にする必要があります。

## ZIPファイルからEclipseにDev Studioをインストールする

Liferay Dev StudioをZipファイルからEclipseにインストールするには、以下の手順に従います。

1. [Liferay @ide@](https://community.liferay.com/en_GB/project/-/asset_publisher/TyF2HQPLV1b5/content/ide-installation-instructions)のダウンロードページに行きます。下では*[Other Downloads]*の下にある、*[Liferay IDE[[version]]Archived Update-site]*というオプションを選択し、ダウンロードします。

2. Eclipseで、*[ヘルプ]*→*[Install New Software...]*に移動します。

3. *[追加]*ダイアログで、*[アーカイブ]*ボタンをクリックし、Dev StusioのZipファイルがダウンロードされた場所を参照します。

4. リストの下に、Dev Studioコンポーネントが表示されます。それらのチェックを外して、*[次へ]*をクリックします。

5. 契約条項に同意し、*[次へ]*をクリックすると、Liferay Dev Studioがインストールされます。他のEclipseプラグインと同様に、Eclipseを再起動して有効にする必要があります。

 これで、Liferay Dev Studioがインストールされたので、一般的にサポートされているIDEを使用して、Liferayの開発を行うことができます。
