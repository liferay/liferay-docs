---
header-id: converting-plugins-sdk-projects-with-blade-cli
---

# Blade CLIを使用したPlugins SDKプロジェクトの変換

[TOC levels=1-4]

Blade CLIは、Plugins SDKプロジェクトをに自動的に移行することができます。プロセス中に、AntベースのPlugins SDKプロジェクトは、プロジェクトタイプ（例: `wars`）に基づいて、適切なワークスペースフォルダにコピーされ、GradleベースのLiferay Workspaceプロジェクトに変換されます。これにより、レガシーPlugins SDKからLiferay Workspaceにアップグレードする際の移行プロセスが大幅にスピードアップします。

| **注：**移行プロセス用のMavenコマンドはまだありません。したがって、Mavenベースのワークスペースの場合は手動で完了する必要があります。

Plugins SDKプロジェクトをコピーしてGradleに変換するには、Bladeの`convert`コマンドを使用します。

1. コマンドラインツールでワークスペースのルートフォルダに移動し、

2. 以下のコマンドを実行します。

   ```bash
   blade convert -s [PLUGINS_SDK_PATH] [PLUGINS_SDK_PROJECT_NAME]
   ```

   プロジェクトが存在するPlugins SDKのパスと、変換するプロジェクト名を指定する必要があります。すべてのPlugins SDKプロジェクトを一度に変換する場合は、プロジェクト名の変数を`-a`で置き換えます。（つまり、すべてのプラグインを指定します。）

   | **注：**上記のように`convert`タスクが機能しない場合は、| Blade CLIバージョンをアップデートする必要がある可能性があります。 詳細については、| [Updating Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/updating-blade-cli)| の記事を参照してください。

   このGradle変換プロセスはテーマでも機能し、NodeJSを自動的に活用するように変換されます。Javaベースのテーマを変換する場合は、お使いのコマンドにも`-t`オプションを追加します。そうすることで、[Theme Builder](/docs/reference/7-1/-/knowledge_base/r/theme-builder-gradle-plugin) Gradleプラグインがテーマとして代わりに組み込まれます。6.2のテーマのアップグレードの詳細については 、[Upgrade a 6.2 Theme to 7.1](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-themes-to-7-1)の記事を参照してください。

| ** 注：**Service Builderプロジェクトを変換する場合、`convert`タスク| は自動的にプロジェクトのサービスインターフェイスと実装を| OSGiモジュール（つまり、* -implおよび* -api）に抽出し、ワークスペースの| `[modules]`フォルダに配置します。ポートレットおよびコントローラーロジックはWARのままであり、| `[wars]`フォルダに存在します。

これで、プロジェクトがGradleベースのワークスペースプロジェクトに正常に変換されました。

