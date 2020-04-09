---
header-id: planning-your-ios-screenlet
---

# iOSスクリーンレットの計画

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screenletを作成する前に、必要な処理と、開発者にScreenletを使用する方法を決定する必要があります。 これにより、スクリーンレットとその機能を作成する場所が決まります。

スクリーンレットを作成する場所は、スクリーンレットの使用方法によって異なります。 再利用または再配布する場合は、Xcodeで空のCocoa Touch Frameworkプロジェクトに作成する必要があります。 その後、CocoaPodsを使用して公開できます。 チュートリアル [iOSテーマのパッケージ化](/docs/7-1/tutorials/-/knowledge_base/t/packaging-ios-themes) では、iOSスクリーンレットを公開する方法について説明しています。 このチュートリアルではテーマについて言及していますが、スクリーンレットを公開する準備の手順は同じです。 Screenletを再利用または再配布する予定がない場合は、アプリのXcodeプロジェクトで作成します。

スクリーンレットの機能とスクリーンレットに必要なデータも決定する必要があります。 これにより、スクリーンレットがサポートする必要のあるアクションと、スクリーンレットが呼び出す必要があるLiferayリモートサービスが決まります。 たとえば、ブックマークスクリーンレットの追加は、Liferayのブックマークポートレットへのブックマークの追加という1つのアクションに応答するだけで済みます。 ブックマークを追加するには、このScreenletはLiferayインスタンスの `add-entry` サービスを `BookmarksEntry`に対して呼び出す必要があります。 ポート8080でLiferayインスタンスをローカルで実行している場合は、 [](http://localhost:8080/api/jsonws?contextName=bookmarks&signature=%2Fbookmarks.bookmarksentry%2Fadd-entry-6-groupId-folderId-name-url-description-serviceContext) をクリックしてこのサービスを表示します。 ブックマークを追加するには、このサービスに次のパラメーターが必要です。

  - `groupId`：ブックマークポートレットを含むLiferayインスタンスのサイトID。

  - `folderId`：新しいブックマークを受信するブックマークポートレットのフォルダーID。

  - `name`：新しいブックマークのタイトル。

  - `url`：新しいブックマークのURL。

  - `description`：新しいブックマークの説明。

  - `serviceContext`：Liferay [`ServiceContext`](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext) オブジェクト。

したがって、ブックマークの追加スクリーンレットでは、これらの各パラメーターを考慮する必要があります。 ブックマークを保存するとき、スクリーンレットはユーザーにブックマークのURLと名前を入力するように求めます。 ただし、ユーザーは他のパラメーターを入力する必要はありません。 これは、アプリの開発者がアプリのコードを介して `groupId` および `folderId` を設定するためです。 また、Screenletのコードは、 `説明` と `serviceContext`自動的に入力し 。</p>
