---
header-id: export-import-and-staging
---

# エクスポート/インポートおよびステージング

[TOC levels=1-4]

エクスポート/インポートおよびステージング機能により、ユーザーはページの公開を計画し、コンテンツを管理できます。 エクスポート/インポート機能を使用すると、ユーザーはポータルからコンテンツをエクスポートし、外部コンテンツをポータルにインポートできます。 アプリケーションにエクスポート機能を提供すると、アプリケーションで作成したコンテンツを別のポータルインスタンスなどの他の場所にエクスポートしたり、後で使用するためにコンテンツを保存したりする柔軟性がユーザーに与えられます。 インポートは反対のことを行います。LARファイルのデータをポータルに取り込みます。

たとえば、オンライン教育コースを管理しているとします。 オンラインコースの性質上、学期ごとにサイトのデータ（成績、課題など）が消去され、新たに入学する学生に道が開かれます。 このようなシナリオでは、コース中に与えられたすべてのデータの完全な記録を頻繁に保存する必要があります。 コースを提供する教育機関は、最短年数の間、コースのデータの記録を保持する必要があります。 これらの要件を順守するには、エクスポート/インポート機能を備えた成績表アプリケーションを使用すると、新しい学期のアプリケーションのデータをクリアできますが、前のクラスの作業は保存できます。 生徒の成績をLARファイルとしてエクスポートし、コースのサイトの外部に保存できます。 成績に再度アクセスする必要がある場合は、LARをインポートして生徒の記録を表示できます。

エクスポート/インポート機能は、再利用可能なコンテンツを作成し、他の場所からコンテンツをインポートできるようにすることで、アプリケーションに別の次元を追加します。 エクスポート/インポート機能の使用の詳細については、「 [アプリデータのエクスポート/インポート](/docs/7-1/user/-/knowledge_base/u/exporting-importing-widget-data) ユーザーガイド」セクションをご覧ください。

ステージングを使用すると、ライブサイトに影響を与えることなく、舞台裏でサイトを変更でき、すべての変更を一気に公開できます。 ステージングはエクスポート/インポートフレームワークを活用することに注意してください。 ステージングされたコンテンツをライブサイトに公開する場合、基本的にステージングされたサイトからコンテンツをインポートし、ライブサイトにエクスポートします。 アプリケーションにステージングサポートを含めると、ユーザーは準備が整うまでコンテンツをステージングできます。

たとえば、特定の休日のみに情報を提供するアプリケーションがある場合、ステージング環境をサポートすると、ユーザーはその休日に固有のアプリケーションのアセットを保存できます。 公開の準備ができるまで、ステージング環境に常駐します。 ステージングの詳細については、パブリケーション</a>

ステージングコンテンツをご覧ください。</p> 

アプリケーションにこれらの機能を設定する以外に、カスタムコードを記述できるAPIにアクセスして、Liferayのデフォルト機能を拡張することもできます。

チュートリアルのこのセクションでは、ステージングとエクスポート/インポートフレームワークを実装する方法を学習します。 ステージングコードの主な重点分野は以下のとおりです。

1.  [StagedModel](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/StagedModel.html)： `StagedModel` はステージングの基礎です。 ステージングで処理する必要があるすべてのコンテンツは、このインターフェイスを実装する必要があります。ステージングプロセス中にステージングが使用するエンティティの動作コントラクトを提供します。

2.  [StagedModelDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/StagedModelDataHandler.html)：これらのデータハンドラーは、1つの特定のエンティティクラスを処理します。 たとえば、 `BookmarksEntryStagedModelDataHandler` は、ステージング中に `BookmarksEntry` 処理します。データのエクスポート、コンテンツのシリアル化、既存のエントリの検索など。

3.  [PortletDataHandler](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataHandler.html)：これらのデータハンドラーは、ステージング中にポートレットの構成と公開の側面を処理します。

4.  [ExportActionableDynamicQuery](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/ExportActionableDynamicQuery.html)：このフレームワークは、ステージングサポートを開発するときに役立ちます。 その目的は、データベースからデータを照会し、公開中に処理することです。 エンティティに適切なフィールドが含まれている場合は自動的に生成されるため、構成を心配する必要はありません。

5.  [ExportImportContentProcessor](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/content/processor/ExportImportContentProcessor.html) および [ExportImportPortletPreferencesProcessor](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/portlet/preferences/processor/ExportImportPortletPreferencesProcessor.html)：特別な場合にのみ必要な高度なフレームワーク。 `ExportImportContentProcessor` 使用すると、公開プロセス中にコンテンツを処理できます。 `ExportImportPortletPreferencesProcessor` 使用すると、公開プロセス中にポートレットプリファレンス（アプリケーションの構成）を処理できます。
