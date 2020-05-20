---
header-id: iterative-development
---

# 反復開発

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリケーションを開発するときに、データベースにフィールドを追加する必要がある場合があります。 これは、反復開発の通常のプロセスです。新しい機能のアイデアを得るか、提案された場合、その機能にはデータベース内の追加データが必要です。 ** `service.xml` 追加された新しいフィールドは、データベースに自動的に追加されません。** フィールドを追加するには、次の2つのいずれかを実行する必要があります。

1.  [アップグレードプロセス](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app) を記述して、テーブルを変更し、データを保存します。または

2.  `cleanServiceBuilder` [Gradleタスク](/docs/7-1/reference/-/knowledge_base/r/db-support-gradle-plugin) （MavenおよびAntでもサポートされています）を実行します。これにより、テーブルが削除され、アプリが次回デプロイされるときにテーブルが再作成されます。 [Maven DB Support Plugin](/docs/7-1/reference/-/knowledge_base/r/db-support-plugin) リファレンス記事では、Mavenプロジェクトからこのコマンドを実行する方法について説明しています。

リリースされたアプリケーションがあり、ユーザーデータを保持する必要がある場合は、最初のオプションを使用します。 開発中に新しい列を追加する場合は、2番目のオプションを使用します。

## 関連トピック

[アップグレードプロセス](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)

[Gradle DBサポートプラグイン](/docs/7-1/reference/-/knowledge_base/r/db-support-gradle-plugin)

[Maven DBサポートプラグイン](/docs/7-1/reference/-/knowledge_base/r/db-support-plugin)
