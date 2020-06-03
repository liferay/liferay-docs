---
header-id: development-setup-overview
---

# 開発のセットアップ概要

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferayの開発ツールは、迅速に開発作業を始めるうえで役立ちます。 Liferay @ide@をインストールするための基本的な手順は次のとおりです。

  - Liferay @ide@バンドルをダウンロードします。

  - ダウンロードしたパッケージをシステム上のロケーションに解凍します。

  - @ide@を開始します。

これらの手順に従って、最初の@product@アプリケーションを開発するための環境を生成します。

## Liferay @ide@バンドルのインストール

次の手順を実行します：

1.  [ Java開発キット（JDK）](http://www.oracle.com/technetwork/java/javase/downloads/index.html)をダウンロードしてインストールします。 @product@はJavaで実行されます。 Liferay @<ide@>で@product@アプリケーションを開発するため、JDKが必要です。 JDKは、新しいJava技術を開発するために使用されるJava環境の拡張バージョンです。 JDK 8またはJDK 11を使用します。

2.  [Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/installing-liferay-ide)をダウンロードしてインストールしてください。システム上の便利なロケーションに解凍して簡単にインストールできます。

3.  Liferay @ide@を実行するには、`LiferayDeveloperStudio`実行ファイルを実行します。

Liferay @ide@を初めて起動すると、Eclipseワークスペースを選択するよう求められます。 空のフォルダを指定すると、Liferay @ide@はそのフォルダに新しいワークスペースを作成します。 新規にワークスペースを作成する場合は、次の手順で行います:

1.  プロンプトが表示されたら、ワークスペースのパスを指定します。 新しいワークスペースに`guestbook-workspace`と名前を付け、*OK*をクリックします。

2.  Liferay @ide@が最初に起動すると、ウェルカムページが表示されます。 *Workbench*アイコンをクリックして続行します。

開発環境がインストールされ、ワークスペースの設定ができました。

## Liferay Workspaceの作成

次に、別の種類のワークスペース[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)を作成します。 Liferay Workspaceは、@product@プロジェクトを保持および管理することにより、@product@アプリケーションを開発するための単純化された簡単な方法を提供します。 バックグラウンドでLiferay Workspaceは[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)および[Gradle](https://gradle.org/)を使用して依存関係を管理し、ビルド環境を整理します。 設定の問題を避けるため、各Eclipse Workspaceには1つのLiferay Workspaceしか作成できないことに注意してください。

Liferay @ide@でLiferay Workspaceを作成するには、次の手順に従います。

1.  *File* → *New* → *Liferay Workspace Project*と選択します。 注: *File* → *New* → *Other*、それから*Liferay*カテゴリで*Liferay Workspace Project*を選択する必要がある場合があります。

    ![図1：* Liferay Workspace*を選択すると、@product@プロジェクト用の新しいワークスペースを作成するプロセスが開始されます。](../../../images/selecting-liferay-workspace.png)

    *New Liferay Workspace*ダイアログが表示され、いくつかの設定オプションが表示されます。

2.  ワークスペースに`com-liferay-docs-guestbook`という名前を付けます。

3.  次に、ワークスペースのロケーションを選択します。 デフォルトの設定値はそのままにしておきます。 これにより、Ecipse Workspace内にLiferay Workspaceが配置されます。

4.  *Liferay Version*については、*7.1*を選択してください。

5.  ワークスペース内の@product@インスタンスを自動的にダウンロードして解凍するには、*Download Liferay bundle*チェックボックスをオンにします。 プロンプトが表示されたら、サーバーに`liferay-tomcat-bundle`と名前を付けます。

6.  Liferay Workspaceを作成するには、*Finish*をクリックします。 Liferay @ide@はバックグラウンドで@product@バンドルをダウンロードするため、これには時間がかかる場合があります。

    ![図2：Liferay @ide@は、Liferay Workspaceを作成するためのわかりやすいメニューを提供します。](../../../images/guestbook-workspace-menu.png)

ダイアログボックスが表示され、Liferay Workspaceのパースペクティブを開くように求められます。 *Yes*をクリックすると、パースペクティブがLiferay Workspaceに切り替わります。

これで開発環境の準備が整いました。\! 次に、最初の@product@アプリケーションの開発を開始します。
