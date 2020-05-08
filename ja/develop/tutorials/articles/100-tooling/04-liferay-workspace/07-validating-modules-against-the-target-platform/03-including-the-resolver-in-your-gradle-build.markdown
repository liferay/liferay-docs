---
header-id: including-the-resolver-in-your-gradle-build
---

# Gradleビルドにリゾルバを含める

[TOC levels=1-4]

デフォルトでは、Liferay Workspaceは`resolve`タスクを独立した実行可能ファイルとして提供します。[Target Platform](/docs/7-1/reference/-/knowledge_base/r/target-platform-gradle-plugin)のGradleプラグインによって提供され、他のGradleプロセスには統合されていません。これにより、デフォルトのビルドプロセスに含めたくないものを課すことなく、Gradleビルドを制御できます。

しかし、デプロイメントの前にモジュールプロジェクトのエラーをチェックする場合は、ビルドプロセスに`resolve`タスクを含めると役立ちます。プロジェクトを標準ビルドとは別にした状態で解決する代わりに、すべてを一度にビルドして解決できるからです。

これを行うためにLiferay Workspaceで推奨しているパスは、このタスクをデフォルトの`check`Gradleタスクに追加する方法です。`check`タスクは、ワークスペースにデフォルトで提供された[Java](https://docs.gradle.org/current/userguide/java_plugin.html#_lifecycle_tasks)プラグインです。 `resolve`タスクを`check`ライフサイクルタスクに追加すると、検証のために`check`タスクを通常実行するCIおよびその他のテストツールで実行される`resolve`タスクも促進されます。もちろん、Gradleの`build`タスクも`check`タスクに依存しているため、`gradlew build`を実行してリゾルバも実行することができます。

`check`タスク中に`resolve`タスクを自動的に呼び出すには、ワークスペースのルート`build.gradle`ファイルを開き、以下のディレクティブを追加します。

    check.dependsOn resolve

すべてのモジュールをグローバルの`check`に含めたくない場合は、ワークスペース内の特定のプロジェクトに対してこれを設定することもできます。

`resolve`タスクがすべてのGradleのビルド時に実行される場合は、リゾルバによって報告されたエラーがある場合にビルドの失敗を防止する方法があります。これを行うには、ワークスペースのルート`build.gradle`ファイルを開き、以下のコードを追加します。

    targetPlatform {
    ignoreResolveFailures = true
    }

これにより、ビルドに失敗することなく、障害が報告されます。これは、ワークスペースのルート`build.gradle`ファイルでのみ設定できることに注意してください。

 お使いのGradleライフサイクルで`resolve`タスクを実行する方法は、以上です。
