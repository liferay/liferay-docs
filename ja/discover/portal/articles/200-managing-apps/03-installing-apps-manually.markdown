---
header-id: installing-apps-manually
---

# アプリを手動でインストールする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

デフォルトでは、Liferay Marketplaceを介してコントロールパネルからダウンロードしたアプリは自動的にインストールされます。 しかし、インストールするアプリがマーケットプレイスにない場合はどうなりますか？ あなたが持っているのがアプリのファイルだけの場合はどうでしょうか？ この場合、アプリを手動でインストールする必要があります。 この記事では、これを行う方法を示します。 ここの手順を使用して、任意のアプリをインストールできます。

| **警告：** すべてのアプリが「ホットデプロイ」されるように設計されているわけではありません。サーバーが実行中です。 この方法でデプロイすると、クラスなどの不安定性が発生する可能性があります。読み込みリークとメモリリーク。 本番システムでは、「ホットデプロイ」を回避する|可能な限りアプリ。 |を参照してください運用中のアプリを管理するための [ベストプラクティス](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps#managing-apps-in-production)。

| **重要**：アプリまたはモジュールをアンインストールするときは、必ず同じものを使用してください。アプリのインストールに使用したエージェント。たとえば、|でインストールした場合Marketplace、アンインストール| [マーケットプレイス](/docs/7-1/user/-/knowledge_base/u/using-the-liferay-marketplace)。 |ファイルシステムと共にインストールした場合は、ファイルシステムを使用してアンインストールします。 |ただし、App Managerでインストールした場合は、|を使用してください。 [ブラックリスト](/docs/7-1/user/-/knowledge_base/u/blacklisting-osgi-modules-and-components) |アンインストールします。

## コントロールパネルを使用してアプリをインストールする

コントロールパネルから手動でアプリをインストールするには、 *コントロールパネル* → *アプリ* → *アプリマネージャー*に移動し、オプションボタン（![Options](../../images/icon-options.png)）から *アップロード* を選択します。 [アップロード]ダイアログで、マシン上のアプリを選択し、[ *インストール*]をクリックします。 インストールが完了したら、ダイアログを閉じて、ロールする準備ができました\！

![図1：App Managerの[Upload]ダイアログを介したアプリのインストールは非常に簡単です。](../../images/app-manager-plugin-upload.png)

## ファイルシステムを使用してアプリをインストールする

@product@サーバーに手動でアプリをインストールするには、アプリを `[Liferay Home] / deploy` フォルダーに配置します（通常、Liferay Homeフォルダーはアプリサーバーの親フォルダーです）。 それでおしまい。 自動デプロイメカニズムが残りの処理を行います。

「おっとっ！」 「残り」とはどういう意味ですか？ここで正確に何が起こりますか？ そして、私のアプリケーションサーバーが自動展開をサポートしていない場合はどうなりますか？」これらは素晴らしい質問です\！ アプリを `[Liferay Home] / deploy` フォルダーに配置すると、OSGiコンテナーはアプリを `[Liferay Home] / osgi`の適切なサブフォルダーに展開します。 デフォルトでは、以下のサブフォルダーが、指定されたファイルタイプに一致するアプリに使用されます。

  - `マーケットプレイス`：マーケットプレイスLPKGパッケージ
  - `モジュール`：OSGiモジュール
  - `war`：WARファイル

ただし、 `portal-ext.properties` ファイルでプロパティ `module.framework.base.dir` および `module.framework.auto.deploy.dirs` を設定することにより、これらのサブフォルダーを変更できます。 これらのプロパティは、それぞれ `[Liferay Home] / osgi` フォルダーとそのホットデプロイサブフォルダーを定義します。 [`portal.properties`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html) ファイルのこれらのプロパティのデフォルト設定は次のとおりです。

    module.framework.base.dir=${liferay.home}/osgi
    
    module.framework.auto.deploy.dirs=\
        ${module.framework.base.dir}/configs,\
        ${module.framework.base.dir}/marketplace,\
        ${module.framework.base.dir}/modules,\
        ${module.framework.base.dir}/war

`configs` サブフォルダーはアプリ用ではありません。他の@product@インスタンスからインポートされた設定ファイル [用です](/docs/7-1/user/-/knowledge_base/u/system-settings#exporting-and-importing-configurations)。

しかし、アプリサーバーが自動デプロイをサポートしていない場合はどうなりますか？ 問題ない\！ @product@のモジュールフレームワーク（OSGi）により、自動デプロイが可能になります。 したがって、@product@を実行しているすべてのアプリサーバーも、この自動デプロイメカニズムをサポートしています。

## LPKGアプリを手動でデプロイする

LPKGアプリを手動でインストールすると、次のようなサーバーログメッセージが表示されてインストールがハングすることがあります。

    14:00:15,789 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing Liferay Push 2.1.0.lpkg

これは、LPKGアプリの `liferay-marketplace.properties` ファイル（LPKGファイル内）に `restart-required = true` プロパティがある場合に発生します。 このプロパティ設定は、インストールを完了するためにサーバーの再起動が必要であることを指定します。
