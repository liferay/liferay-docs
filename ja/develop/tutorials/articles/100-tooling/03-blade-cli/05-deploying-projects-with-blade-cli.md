---
header-id: deploying-projects-with-blade-cli
---

# Blade CLIを使用したプロジェクトのデプロイ

[TOC levels=1-4]

Blade CLIを使用して、Liferayサーバーにプロジェクトをデプロイするのは簡単です。Blade `deploy`コマンドを使用するには、最初にデプロイするプロジェクトをビルドしておく必要があります。 Liferayプロジェクトの作成方法の詳細については、[Creating Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)のチュートリアルを参照してください。プロジェクトをビルドしたら、CLIでプロジェクトに移動し、以下のコマンドを実行してデプロイします。

    blade deploy

これは、WARスタイルのプロジェクトとモジュール（JARs）に使用できます。また、親フォルダ（例: `[WORKSPACE_ROOT]/modules`）から`deploy`コマンドを実行して、フォルダ内のすべてのプロジェクトをデプロイすることもできます。

Liferay Workspaceを使用している場合、`deploy`コマンドはビルドツールのデプロイ設定に基づいて、プロジェクトをデプロイします。たとえば、デフォルトのGradle Liferay WorkspaceでBlade CLIを活用するには、基礎となるGradleデプロイ設定が使用されます。ビルドツールのデプロイ設定は、ワークスペースの`gradle.properties`または`pom.xml`ファイルに設定されているLiferay Homeフォルダを読み取ることで見つかります。ワークスペースの外で作業している場合、`deploy`コマンドは、同様に動作します。対照的に、Liferay Homeフォルダは、Liferay拡張オブジェクト（Gradle）または有効なPOM（Maven）をロードし、そこに保存されているLiferay Homeプロパティを検索することによって設定されます。保存されていない場合は使用できるように設定するように、Bladeはプロンプトを表示します。

| **注：**純粋なGradleまたはMavenを使用してプロジェクトをデプロイしたい場合は、| 適切なプラグインを適用し、Liferay Home| プロパティを設定して行ってください。GradleとMavenでこれを行う方法は以下のとおりです。| **Gradle：** | | まず、Liferay Gradleプラグインが`build.gradle`ファイルに適用されていることを確認します。| プラグインの適用： "com.liferay.plugin" | | 次に、Liferay拡張オブジェクトを拡張して、Liferay Homeと`deploy`フォルダを設定します：| | liferay {| liferayHome = "../../../../liferay-ce-portal-7.1.1-ga2" | deployDir = file（ "$ {liferayHome} / deploy"）| } | | **Maven**： | | Bundle Supportプラグインが適用されていることを確認し、`pom.xm`| 内にLiferay Homeを設定します。詳細については、| [Deploying a Project Built with Maven to Liferay Portal](/docs/7-1/tutorials/-/knowledge_base/t/deploying-a-project-built-with-maven-to-product)| を参照してください。

基礎となるビルドツール（GradleまたはMaven）のモジュールのデプロイメント設定を使用せずに、@product@のOSGiコンテナに直接デプロイする場合は、代わりに以下のコマンドを実行します。

```bash
blade deploy -l
```

また、Blade CLIは、デプロイされたプロジェクトを*監視する*方法も提供します。これにより、変更が検出されたときにプロジェクトをコンパイルおよび再デプロイします。これを行うには、2つの方法があります。

- `blade watch`
- `blade deploy -w`

`watch`コマンドは変更が検出されるたびにプロジェクトを再ビルドするわけではないため、モジュールの変更を開発およびテストするには`blade watch`コマンドが最も速くできます。`blade watch`を実行すると、プロジェクトはLiferay Portalにコピーされず、リファレンスとしてランタイムにインストールされます。つまり、Liferay Portalはプロジェクトのキャッシュされたコピーを作成しません。これにより、Liferay Portalはプロジェクトのファイルに追加された変更をすぐに確認することができます。`watch`タスクをキャンセルすると、モジュールは自動的にアンインストールされます。

この`watch`タスクは、JSFポートレットまたはフラグメントプロジェクトでは機能しません。

| **注：**`blade watch`コマンドは、Liferay Workspaceのバージョン| 1.10.9以降（つまり、`com.liferay.gradle.plugins.workspace`の依存関係）で使用可能です。Maven| プロジェクトでは、現時点では`watch`機能を活用することはできません。

`blade deploy -w`コマンドは、`blade watch`と同様に機能しますが、変更が検出されるたびに手動でプロジェクトを再コンパイルしてデプロイする点だけが異なります。
これにより、アップデート時間が遅くなりますが、Liferay Portalのシャットダウン時にデプロイされたプロジェクトは保持されます。

 これで、Blade CLIを使用したモジュールプロジェクトのデプロイが正常に完了しました。
