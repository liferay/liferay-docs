---
header-id: using-the-export-import-lifecycle-listener-framework
---

# エクスポート/インポートライフサイクルリスナーフレームワークの使用

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

`ExportImportLifecycleListener` フレームワークを使用すると、開発者は公開プロセス中に特定のステージングイベントまたはエクスポート/インポートイベントをリッスンするコードを作成できます。 ステージングプロセスとエクスポート/インポートプロセスには、デフォルトではリッスンできない多くの舞台裏のイベントがあります。 エクスポートの成功やインポートの失敗など、これらの一部は、何らかのアクションを実行するイベントです。 また、多くのイベントで構成されるプロセスをリッスンし、これらのプロセスが開始されたときにカスタムコードを実装することもできます。 リスニングできるイベントの短いリストは次のとおりです。

  - ステージングが開始されました
  - ポートレットのエクスポートが失敗しました
  - エンティティのエクスポートが成功しました

エクスポート/インポートおよびステージングイベントをリッスンするという概念はクールに聞こえますが、特定のイベントをリッスンすることが有用な理由について興味があるかもしれません。 イベントをリッスンすることで、アプリケーションの状態をより詳しく知ることができます。 インポートプロセス中に特定のイベントが発生したときの詳細なログが必要だとします。 関心のある特定のインポートイベントをリッスンするようにリスナーを設定し、それらのイベントに関する情報が発生したときにコンソールに出力できます。

@product@は、いくつかの場合にデフォルトでこのフレームワークを使用します。 たとえば、Webコンテンツのインポートプロセスが終了すると、キャッシュはクリアされます。 これを実現するために、ライフサイクルリスナーフレームワークは、Webコンテンツのインポートプロセスが完了したことを指定するイベントをリッスンします。 そのイベントが発生すると、キャッシュを自動的にクリアするイベントリスナーがあります。 どのようなイベントに対しても、この種の機能を自分で実装できます。 特定のイベントをリッスンし、そのイベントが発生したタイミングに基づいてアクションを完了することができます。 エクスポート/インポートおよびステージングプロセス中にリッスンできるイベントのリストについては、 [ExportImportLifecycleConstants](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/ExportImportLifecycleConstants.html)参照してください。

いくつかの定義は順番通りです：

**イベント** は、処理中に発生する特定のアクションです。

**プロセス** は、イベントのグループをより長く実行しています。

このチュートリアルでは、 `ExportImportLifecycleListener` フレームワークを使用して、ステージングおよびエクスポート/インポートのライフサイクル中にプロセス/イベントをリッスンする方法を学習します。

## ライフサイクルイベントを聴く

ライフサイクルリスナーの作成を開始するには、モジュールを作成する必要があります。 以下の手順に従ってください：

1.  [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)。

2.  モジュールの `src` ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。 命名規則に従うには、処理するエンティティ名またはアクション名でクラス名を開始し、その後に *ExportImportLifecycleListener* （たとえば `LoggerExportImportLifecycleListener`）を続けます。

3.  Export / Import Lifecycle Listenerフレームワークで提供される2つのBaseクラスのいずれかを拡張する必要があります： [BaseExportImportLifecycleListener](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseExportImportLifecycleListener.html) または [BaseProcessExportImportLifecycleListener](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lifecycle/BaseProcessExportImportLifecycleListener.html)。 選択するには、ライフサイクルのどの部分を聞きたいかを考慮する必要があります。

    ライフサイクル中に特定の *イベント* をリッスンする場合は、 `BaseExportImportLifecycleListener` クラスを拡張します。 たとえば、レイアウトのエクスポートが失敗した場合にカスタムコードを記述できます。

    ライフサイクル中に *プロセス* をリッスンする場合は、 `BaseProcessExportImportLifecycleListener` クラスを拡張します。 たとえば、サイトの公開が失敗した場合にカスタムコードを記述できます。 通常、プロセスは多くの個々のイベントで構成されていることに注意してください。 この基本クラスによって提供されるメソッドは、目的のプロセスアクションが発生したときに1回だけ実行されます。

4.  クラスの宣言のすぐ上に、次の注釈を挿入します。
   
        @Component(immediate = true, 
                    service = ExportImportLifecycleListener.class)

    このアノテーションは、コンポーネントの実装クラスを宣言し、ポータルがすぐにモジュールを開始することを指定します。

5.  クラスに実装するメソッドを指定します。

エクスポート/インポートライフサイクルリスナーモジュールを正常に作成したら、モジュールのJARファイルを生成し、それを@product@の `osgi / modules` フォルダーにコピーします。 インスタンスのサービスレジストリにモジュールをインストールしてアクティブ化すると、ポータルインスタンスでライフサイクルリスナーを使用する準備が整います。

このフレームワークの詳細についてまだ知りたい場合は、幸運です！ [LoggerExportImportLifecycleListener](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/LoggerExportImportLifecycleListener.html)を使用した例を次に示します。 このリスナーは `BaseExportImportLifecycleListener`拡張するため、ライフサイクルイベントを処理することがすぐにわかります。

最初のメソッド `getStagedModelLogFragment` は、ステージングされたモデルのログフラグメントを取得します。これは、イベントに関するライフサイクルリスナーのログ情報です。 次のメソッド `isParallel（）` は、リスナーをインポート/エクスポートプロセスと並行して実行するか、呼び出し元のメソッドを停止してリスナーを実行し、リスナーの終了後にイベントが発生した場所に戻るかを決定します。 次のメソッドは、 `onExportImportLifecycleEvent（...）` メソッドです。このメソッドは、ライフサイクルイベントを消費し、それを基本クラスのメソッドに渡します（デバッグモードが有効でない場合）。

残りの各メソッドは、ユーザーのログ情報を印刷するために呼び出されます。 たとえば、レイアウトのエクスポートが開始、成功、または失敗すると、そのイベントに直接関連するログ情報が出力されます。 要約すると、 `LoggerExportImportLifecycleListener` は、ライフサイクルリスナーフレームワークを使用して、エクスポート/インポートイベントが発生したときにメッセージをログに出力します。 イベントライフサイクルリスナーのもう</a>良い例は、 [CacheExportImportLifecycleListener ](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/CacheExportImportLifecycleListener.html)です。

`BaseProcessExportImportLifecycleListener` クラスを拡張するライフサイクルリスナーの例については、 [ExportImportProcessCallbackLifecycleListener](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/lifecycle/ExportImportProcessCallbackLifecycleListener.html) クラスを調べてください。 ライフサイクルイベントをリッスンする代わりに、このクラスはプロセスアクションのみをリッスンします。

すごい！ ライフサイクルリスナーのエクスポート/インポートフレームワークについて学び、ポータルのコンテンツのエクスポート/インポート中に発生するイベント/プロセスの独自のリスナーを作成する方法を学びました。
