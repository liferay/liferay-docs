---
header-id: liferay-sample-projects
---

# Liferayのサンプルプロジェクト

[TOC levels=1-4]

Liferayでは、@product@のさまざまな統合ポイントを対象としたサンプルプロジェクトの実例を提供しています。これらの作業例は独自の独立したプロジェクトにコピー/貼り付けができるため、さまざまなLiferay拡張ポイントを活用できます。各サンプルはスタンドアロンプロジェクトであり、独自のビルドファイルが含まれています。Liferayのサンプルプロジェクトは 、GitHubの[liferay-blade-samples](https://github.com/liferay/liferay-blade-samples)リポジトリにあります。そして、Liferayのサンプルプロジェクトのドキュメンテーションは、 [Sample Projects](/docs/7-1/reference/-/knowledge_base/r/sample-projects)のリファレンスセクションにあります。

リポジトリをローカルで参照したり、サンプルプロジェクトを独自のプロジェクトにコピーしたい場合は、リポジトリを[フォーク](https://help.github.com/articles/fork-a-repo/)して、`liferay-blade-samples`リポジトリの[クローンを作成](https://help.github.com/articles/cloning-a-repository/)します。

また、以下のコマンドを実行することで[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)を使用してサンプルを作成することもできます。

    blade samples [SAMPLE_NAME]

たとえば、以下のコマンドは[ds-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/ds-portlet)サンプルを作成します。

    blade samples ds-portlet


リポジトリが以下の3つの主要なフォルダに分かれていることが一目でわかります。

- `gradle`
- `liferay-workspace`
- `maven`

提供されているサンプルプロジェクトは、さまざまな開発者に対応できるように開発ツールチェーンごとに編成されています。各フォルダには、サンプルのLiferayプロジェクトの同じセットが用意されています。唯一の違いは、ビルドファイルがツールチェーンに固有であることです。たとえば、`gradle`フォルダには、任意のGradleコンポジットビルドに追加できる、標準のOSS Gradleプラグインを使用したプロジェクトが含まれています。同じことが`liferay-workspace`と`maven`プロジェクトにも適用されています。

この`gradle`フォルダは、さまざまなタイプのLiferayプロジェクトの追加機能を含むLiferay Gradleプラグイン（例: `com.liferay.plugin`）も使用しています。Liferay Gradleプラグインは、@product@向けに開発しているGradleユーザーに推奨されています。

また、一部のサンプルにはロギングも設定されているため、サンプルがバックグラウンドで何を行っているのかを理解するのに役立ちます。たとえば、OSGiモジュールのロギングは、いくつかのサンプル（例: [action-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/action-command-portlet)、[document-action](/docs/7-1/reference/-/knowledge_base/r/document-action)、[service-builder/jdbc](/docs/7-1/reference/-/knowledge_base/r/service-builder-application-using-external-database-via-jdbc)など）に対して実装されます。これにより、OSGiモジュールは外部設定なしで独自のロギング設定デフォルトを提供できます。詳細については、[Adjusting Module Logging](/docs/7-1/tutorials/-/knowledge_base/t/adjusting-module-logging)のチュートリアルを参照してください。

利用可能なサンプルテンプレートプロジェクトのリストについて は、Liferay Blade Samplesリポジトリにある[Liferay extension points](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-and-template-projects)のサブセクションを確認してください。このリストは包括的なものではありません。不足している拡張ポイントサンプルのサブセットは、[Liferay extension points without template projects](https://github.com/liferay/liferay-blade-samples#liferay-extension-points-without-template-projects)のサブセクションにあります。このリポジトリへ貢献する方法の詳細については、リポジトリの[Contribution Guidelines](https://github.com/liferay/liferay-blade-samples#contribution-guidelines)のセクションを確認してください。
