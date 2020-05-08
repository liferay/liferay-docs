---
header-id: installing-liferay-ide
---

# Liferay @ide@のインストール

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay @ide@は、多くのLiferay固有の機能と追加のエンタープライズ専用機能を提供するEclipseのプラグインです。 既存のEclipse環境にインストールするか、Liferayがバンドルバージョンを提供します。 インストールプロセスを開始する前に、Dev Studioの [互換性マトリックス](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/developer-tools) を参照して、サポートされているLiferayのバージョンとアプリケーションサーバーについて理解してください。

このチュートリアルでは、Liferay Dev Studioをインストールするためのさまざまな方法を学習します。

  - [Dev Studioバンドルを最初からインストールする](#install-the-liferay-dev-studio-bundle)
  - [更新URLを使用して、Dev Studioを既存のEclipseインスタンスにインストールする](#install-liferay-dev-studio-into-eclipse-environment)
  - [ZIPファイルを使用してDev Studioを既存のEclipseインスタンスにインストールする](#install-liferay-dev-studio-into-eclipse-from-a-zip-file)

**重要：** Dev Studioを既存のEclipse環境にインストールする場合は、Eclipse Oxygen以降が必要です。 Oxygenへのアップグレード手順については、Eclipseの [アップグレードドキュメント](https://wiki.eclipse.org/FAQ_How_do_I_upgrade_Eclipse_IDE%3F#Upgrading_existing_Eclipse_IDE_and_Installed_Features_to_newer_release)参照してください。 この特定のアップグレードでは、 *Window* → *Preferences* → *Install / Update* → *Available Software Sites* メニューで現在利用可能なアップデートサイトを非アクティブ化して、アップグレードを確実に成功させる必要があります（Neonなど）。

## Liferay Dev Studio Bundleをインストールする

1.  [Java](http://java.oracle.com)ダウンロードしてインストールします。 @product@はJavaで実行されるため、他のすべてを実行するにはJavaが必要です。 Liferay Dev Studioで@product@のアプリを開発するため、Java Development Kit（JDK）が必要です。 これは、新しいJavaテクノロジの開発に使用されるJava環境の拡張バージョンです。 Java SE JDKは、Java [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html) ページからダウンロードできます。

2.  オペレーティングシステムに関連する@ide@</a> 実行可能ファイルを含むLiferayの最新の3.2.x

プロジェクトSDKをダウンロードします。 プロジェクトSDKには、@ide@、 [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)、および [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)ます。</p> 
   
   @ide@インストーラをダウンロードする前に、liferay.comのユーザー名とパスワードの入力を求められる場合があります。 @ide@には@product@へのアクセスが含まれるため、それを使用する権限があることを確認する必要があります。
   
   資格情報はローカルに保存されません。それらは `/ .liferay` フォルダーにトークンとして保存されます。 @product@バンドルを再ダウンロードすることを決定した場合、このトークンはDev Studioの [Liferayワークスペース](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace) によって使用されます。 さらに、ワークスペースにダウンロードされた@product@バンドルも `/ .liferay / bundles` フォルダーにコピーされるため、同じバージョンの別の@product@インスタンスを初期化する場合、バンドルは再作成されませんダウンロードしました。 参照してください [ワークスペースへのLiferayバンドルを追加する](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace) このトピックの詳細については。
   
   **重要：** トークンジェネレーターは、プロキシの背後に構築されたワークスペースのトークンを生成するときに問題が発生することがあります。 ワークスペーストークンを自動的に生成できない場合は、 [手動で生成できます](#generating-a-workspace-token-manually)。</li> 
   
   3  インストーラーを実行します。 ご使用のオペレーティングシステムとインストール先によっては、インストーラーの実行を許可する必要がある場合があります。

4  インストールプロセスに使用するJavaランタイムを選択します。 次に *Next*クリックします。

5  *次* をクリックして、インストールプロセスを開始します。 Liferay Dev Studioインスタンスのインストールフォルダーを選択します。 次に *Next*クリックします。
  
  ![図1：Dev Studioインスタンスを配置するフォルダーを選択します。](../../../images-dxp/dev-studio-install.png)

6  @product@アクティベーションキーを入力して、@ide@とともにパッケージ化されたLiferay DXPバンドルをセットアップします。 次に *Next*クリックします。
  
  Dev Studioはデフォルトで [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace) インストールします。これは、@product@プロジェクトの構築と管理に使用される開発者環境です。 インストーラーは、Liferayワークスペースとその専用コマンドラインツール（[ブレードCLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)）を自動的にインストールします。

7  プロジェクトSDKのプロキシ設定を構成します。 ファイアウォールの内側でDev Studioを使用する必要がある場合は、プロキシ設定を構成できます。 詳細については、 [Liferay IDEプロキシ設定](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-ide) および [Liferayワークスペースプロキシ設定](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-workspace) チュートリアルを参照してください。 これが必要ない場合は、このステップをスキップしてください。
  
  ![図2：必要に応じて、プロジェクトSDKのプロキシ設定を構成します。](../../../images-dxp/dev-studio-proxy-settings.png)

8  *次* をクリックして、Dev Studioインスタンスのインストールプロセスを完了します。</ol> 

おめでとう\！ Liferay @ide@ \！をインストールしました。 指定したフォルダで利用できるようになりました。 Dev Studioを実行するには、 `DeveloperStudio` 実行可能ファイルを実行します。 Liferayワークスペースも同じフォルダーで初期化されています。



## Liferay Dev StudioをEclipse環境にインストールする

更新URLを使用してDev Studioをインストールするには、次の手順に従います。

1.  Eclipseで、 *ヘルプ* → *新しいソフトウェアのインストール...*ます。

2.  [ *Work with* フィールドで、URL <http://releases.liferay.com/tools/ide/latest/milestone/>コピーします。

3.  以下のリストにDev Studioコンポーネントが表示されます。 それらをチェックして、 *次へ*をクリックします。

4.  契約の条件に同意します。 *Next*クリックすると、Dev Studioがインストールされます。 他のEclipseプラグインと同様に、使用するにはEclipseを再起動する必要があります。

Liferay Dev Studioが既存のEclipse環境にインストールされました。



## Liferay Dev StudioをZIPファイルからEclipseにインストールする

Zipファイルを使用してDev Studioをインストールするには、次の手順に従います。

1.  [Liferay @ide@](https://web.liferay.com/group/customer/dxp/downloads/developer-tools) ダウンロードページに移動します。 ドロップダウンメニューから、[ *Developer Studio Updatesite Zip* ]を選択し、[ *ダウンロード*]をクリックします。

2.  Eclipseで、 *ヘルプ* → *新しいソフトウェアのインストール...*ます。

3.  [ *Add* ]ダイアログで、[ *Archive* ]ボタンをクリックし、ダウンロードしたLiferay Dev Studio Update Site `.zip` ファイルの場所を参照します。 次に *OK*押し* 。</p></li> 
   
   4  以下のリストにDev Studioコンポーネントが表示されます。 それらをチェックして、 *次へ*をクリックします。
  
  ![図3：インストールするすべてのDev Studioコンポーネントを確認してください。](../../../images-dxp/dev-studio-zip-install.png)

5  契約条件に同意し、[ *次へ*]をクリックすると、Developer Studioがインストールされます。 他のEclipseプラグインと同様に、使用するにはEclipseを再起動する必要があります。</ol> 

驚くばかり\！ Liferay Dev Studioを既存のEclipse環境にインストールしました。



## ワークスペーストークンを手動で生成する

トークンの自動生成で問題が発生した場合は、以下の手順に従って手動でトークンを作成できます。

1.  [www.liferay.com](https://www.liferay.com/) 移動して、アカウントにログインします。

2.  右上のプロフィール写真にカーソルを合わせ、[ *アカウントホーム*]を選択します。

3.  左側のメニューから *アカウント設定* 選択します。

4.  Miscellaneous見出しの下の右側のメニューから *Authorization Tokens* クリックします。
   
   ![図4：承認トークンメニューでワークスペーストークンを手動で作成できます。](../../../images-dxp/authorization-tokens-option.png)

5.  [ *Add Token*選択し、デバイス名を付けて、[ *Generate*]をクリックします。 デバイス名は任意の文字列に設定できます。それは簿記目的のためだけです。

6.  `/ .liferay / token` という名前のファイルを作成し、生成されたトークンをそのファイルにコピーします。
   
   ![図5：生成されたトークンはコピーに使用できます。](../../../images-dxp/generated-token.png)
   
   ファイルに改行や空白がないことを確認してください。 1行である必要があります。

トークンを手動で生成し、インストーラーがアクセスできるようになりました。 インストーラーを実行していない場合は、ここで実行できます。 すでにインストーラーを実行している場合は、ワークスペースの `gradle.properties` ファイルにダウンロードするようにDXPバンドルを設定できます。 詳細については、 [ワークスペース](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace) へのLiferayバンドルの追加チュートリアルを参照してください。
