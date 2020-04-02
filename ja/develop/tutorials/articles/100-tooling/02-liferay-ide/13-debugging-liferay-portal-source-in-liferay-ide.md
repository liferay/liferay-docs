---
header-id: debugging-product-source-in-liferay-ide
---

# Liferay Dev Studioで@product@ソースをデバッグする

[TOC levels=1-4]

Liferay Dev Studioを使用して@product@のソースコードをデバッグし、エラーの解決に役立てることができます。
@product@コードのデバッグは、Eclipseでのデバッグに関連したテクニックとほとんど同じ方法で行います。一般的なデバッグに関するヘルプが必要な場合は、Eclipseのドキュメントを参照してください。ここでは、Eclipseの役立つリンクをいくつか紹介します。

- [Debugger](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fcdebugger.htm&cp=1_2_9)
- [Local Debugging](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fclocdbug.htm&cp=1_2_11)
- [Remote Debugging](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fcremdbug.htm&cp=1_2_12)

コードをデバッグする前に、以下のLiferay固有の設定について知っておく必要があります。

- [ターゲットプラットフォームを設定方法します。](#configure-your-target-platform)
- [Liferayサーバーを設定し、デバッグモードで起動します。](#configure-a-liferay-server-and-start-it-in-debug-mode)

これらのLiferay固有のデバッグ設定を詳しく見ていきます。

## ターゲットプラットフォームを設定する

ターゲットプラットフォームを設定するには、[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)で開発する必要があります。
Liferay Workspaceは、設定されたソースコードにインデックスを付ける特定のバージョンを対象とすることで、デバッグ機能を提供できます。
以下のプロパティをワークスペースの`gradle.properties`ファイルに追加して、この機能を有効にする必要があります。

```properties
target.platform.index.sources=true
```

| **注：**ポータルソースのインデックス作成は、Gradleワークスペースのバージョン| 2.0.3以降 (Target Platformプラグインのバージョン 2.0.0以降)では無効になっています。

ターゲットプラットフォームを指定しないと、Dev Studioは@product@のソースコードにアクセスできません。この仕組みの詳細については、[Managing the Target Platform in Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)のチュートリアルを参照してください。

**重要：**ターゲットプラットフォームは、次のセクションで設定するLiferayサーバーのバージョンと一致している必要があります。

ワークスペースでターゲットプラットフォームを設定すると、Eclipseはのすべてのソースコードにアクセスできます。次に、Liferayサーバーを設定し、デバッグモードで起動する方法について説明します。

## Liferayサーバーを設定し、デバッグモードで起動する

ターゲットプラットフォームを設定すると、Eclipseのソースコードが参照できるようになります。次に、デバッグするカスタムコードをデプロイできるように、ターゲットプラットフォームバージョンと一致するLiferayサーバーを設定する必要があります。

1. @product@サーバーをセットアップして、Dev Studioで実行します。詳細については、[Installing a Server in Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/installing-a-server-in-liferay-ide)を参照してください。

2. サーバーをデバッグモードで起動します。これを行うには、Liferay Dev Studioの[サーバー]ペインにある、[デバッグ]ボタンをクリックします。

   ![図1：このスクリーンショットの赤いボックスは、デバッグボタンをハイライトしています。このボタンをクリックして、デバッグモードでサーバーを起動します。](../../../images/ide-debug.png)

 これで、Liferay Dev Studioでデバッグを開始する準備が整いました。
