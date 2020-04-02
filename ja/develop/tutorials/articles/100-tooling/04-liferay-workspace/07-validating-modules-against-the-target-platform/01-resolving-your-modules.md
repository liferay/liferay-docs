---
header-id: resolving-your-modules
---

# モジュールの解決

[TOC levels=1-4]

モジュールをデプロイしてコンソールエラーや不可解な問題が発生した場合、厄介な事態を引き起こす可能性があります。デプロイメントの前にモジュールを解決することにより、この苦痛なプロセスを回避できます。これは、Liferay Workspaceが提供する`resolve`Gradleタスクを呼び出すことで実現できます。

    ../gradlew resolve

このタスクは、以下によって提供されるすべての機能を収集します。

- 指定された@product@のバージョン（つまり、[ターゲットプラットフォーム](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)）
- 現在のワークスペースのモジュール

`resolve`タスクによって収集され、検証される機能/情報の一部には、以下のものが含まれます。

- 宣言された必須機能
- モジュールのバージョン
- パッケージのインポート/使用の制約
- サービスリファレンス

また、プロジェクトの実行要件のリストも算出します。次に、現在のプロジェクトの要件と収集された機能を比較します。収集された機能のリストにないものがプロジェクトで必要な場合は、このタスクは失敗します。

タスクはOSGiモジュールのみを検証できます。WARスタイルのプロジェクト、テーマ、またはnpmポートレットでは機能しません。

| ** 注意：**`resolve`タスクは、特定のプロジェクトフォルダから実行したり、| ワークスペースのルートフォルダから実行したりすることができます。ルートフォルダからタスクを実行すると、| ワークスペース内のすべてのモジュールを検証します。

`resolve`タスクは自動的にワークスペースから利用可能な機能を集めることができますが、ターゲットの@product@バージョン用にこれを指定する必要があります。これを行うには、ワークスペースの`gradle.properties`ファイルを開き、`liferay.workspace.target.platform.version`プロパティをターゲットのバージョンに設定します。例えば、

    liferay.workspace.target.platform.version=7.1.0

Liferay DXPを使用している場合は、以下のようにプロパティを設定できます。

    liferay.workspace.target.platform.version=7.1.10

DXPのGA1リリースに続くバージョンは、フィックスパックバージョン（例: `7.1.10.fp1`、`7.1.10.fp2`など）に従います。

ターゲットプラットフォームのプロパティを設定すると、指定した@product@バージョンの静的な*distro* JARが提供されます。これには、対象とするバージョンで実行されているすべてのメタデータ（つまり、機能やパッケージ、バージョンなど）が含まれています。distro JARは、OSGiランタイムで提供されるすべてを完全なスナップショットにしたものです。これは、モジュールを検証する対象プラットフォームの機能リストとして機能します。

これで、デプロイする前にモジュールプロジェクトを検証できるようになりました。場合によっては、`resolve`アプリの検証を成功させるために、タスクのデフォルトの動作を変更する必要がある場合があります。詳細については、[Modifying the Target Platform's Capabilities](/docs/7-1/tutorials/-/knowledge_base/t/modifying-the-target-platforms-capabilities)のチュートリアルを参照してください。`resolve`タスクによって出力される一般的な出力エラーの解決については、[Resolving Common Output Errors Reported by the resolve Task](/docs/7-1/reference/-/knowledge_base/r/resolving-common-output-errors-reported-by-the-resolve-task)の記事を参照してください。
