---
header-id: modules-as-an-improvement-over-traditional-plugins
---

# 従来のプラグインを改良したモジュール

[TOC levels=1-4]

@product-ver@では、OSGiモジュールまたは従来のLiferayプラグイン（WARスタイルのポートレット、フック、EXT、およびWebアプリケーション）を使用してアプリケーションを開発できます。 Liferayのプラグイン互換性レイヤー（後述）を使用すると、OSGiランタイムフレームワークに従来のプラグインをデプロイできます。 ただし、すべての@product@とOSGiから得られる利点を利用するためには、OSGiモジュールを使用する必要があります。

モジュールには次の利点があります。

  - **カプセル化の改善** - モジュールが公開するクラスは、明示的に[エクスポートするパッケージ](/docs/7-1/tutorials/-/knowledge_base/t/exporting-packages)のクラスのみです。 これにより、外部クライアントに対して透過的な内部パブリッククラスを定義できます。

  - **パッケージによる依存関係** - 依存関係は、JARファイルではなくJavaパッケージによって指定されます。 従来のプラグインでは、*任意*のクラスを使用するために、JARファイルのクラスの*すべて*をクラスパスに追加しなければなりませんでした。 OSGiを使用すると、必要なクラスを含む[インポートパッケージ](/docs/7-1/tutorials/-/knowledge_base/t/importing-packages)のみが必要となります。 それらのパッケージ内のクラスのみがモジュールのクラスパスに追加されます。

  - **軽量** - モジュールは必要なだけ小さくすることができます。 複数の記述子ファイルを必要とする従来のプラグインとは対照的に、モジュールは単一の記述子ファイル（標準JARマニフェスト）のみを必要とします。 また、従来のプラグインは通常モジュールよりも大きく、アプリケーションサーバーの立ち上げ時にデプロイされるため、そのプロセスを大幅に遅くする可能性があります。 モジュールはより迅速にデプロイするほか、最小限のオーバーヘッドコストで済みます。

  - **簡単な再利用** - モジュールは小さくて凝集性の高いコードチャンクの開発に適しています。 これらを組み合わせて、テストと保守が簡単なアプリケーションを作成できます。 モジュールは、パブリックに（例: [Maven Central](https://search.maven.org/)）またはプライベートに分散できます。 また、モジュールはバージョン管理されているため、使用するモジュールを正確に指定できます。

  - **コンテキスト内記述子** - プラグインが記述子ファイル（例: `web.xml`、`portlet.xml`、など）を使用してクラスを記述する場合、モジュールのクラスはそれらを記述するためOSGiのアノテーションを使用しています。 たとえば、モジュールのポートレットのクラス名は[OSGiサービスアノテーションプロパティ](/docs/7-1/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map)を使用し、その名前、表示名、リソースバンドル、パブリックレンダリングパラメーターなどを指定できます。 その情報をコードとは別に記述子ファイルで指定する代わりに、コード内のコンテキストで指定します。

これらは、モジュールが従来のプラグインよりも優れていることを示すほんの数例です。 ただしLiferayプラグインを経験した開発者は、両方においてメリットがあることを覚えておいてください。 @product-ver@は、従来のプラグイン*および*モジュールをサポートします。 既存のLiferay開発者は、モジュールのシンプルさとプラグインとの類似性に快適さを見出すことができます。

モジュールがプラグインと共有するいくつかの基本的な特徴は次のとおりです。

  - 開発者はそれらを使用してアプリケーション（Liferay用のポートレット）を作成します。

  - クラスとリソースのパッケージが圧縮されています。

  - 標準のJava JARとしてパッケージ化されています。

ここまで、モジュールをプラグインと比較対照してきました。次に、モジュールの構造を見ていきます。

## モジュールの構造：マニフェスト付きのJARファイル

モジュールの構造は非常にシンプルです。 1つの必須ファイル: `META-INF/MANIFEST.MF`があり、 モジュールにコードとリソースを追加して、必要に応じて整理します。

モジュールJARファイルの必須構造は次のとおりです。

  - `[Project root]`
      - `[Module's files]`
      - `META-INF`
          - `MANIFEST.MF`

`MANIFEST.MF`ファイルは、システムにモジュールを記述します。 マニフェストの[OSGiヘッダー](https://www.osgi.org/bundle-headers-reference/)は、モジュールと他のモジュールとの関係を識別します。

最も一般的に使用されるヘッダーの一部を次に示します。

  - `Bundle-Name`：モジュールのユーザーフレンドリーな名前。

  - `Bundle-SymbolicName`：モジュールのグローバルな一意識別子。 Javaパッケージの規則（例: `com.liferay.journal.api`）が一般的に使用されます。

  - `Bundle-Version`：モジュールのバージョン。

  - `Export-Package`：このモジュールのパッケージは、他のモジュールへのアクセスを可能にします。

  - `Import-Package`：このモジュールは、他のモジュールが提供するパッケージを必要とします。

他のヘッダーを使用して、モジュールの構築方法、使用された開発ツールなど、より多くの特性を指定できます。

例として、Liferay Journal Webモジュールマニフェストのヘッダーの一部を次に示します。

    Manifest-Version: 1.0
    Bundle-ManifestVersion: 2
    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Vendor: Liferay, Inc.
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.dynamic.data.mapping.util,\
        com.liferay.journal.model,
        com.liferay.journal.service,com.liferay.journal.util, [..]
    Import-Package:\
        aQute.bnd.annotation.metatype,\
        com.liferay.announcements.kernel.model,
        com.liferay.application.list,\
        com.liferay.asset.kernel,\
        com.liferay.asset.kernel.exception, [..]

注: この例から不要な「ノイズ」を除去するために、一部のヘッダーは省略され（`[..]`）、一部は除去されています。

モジュールのJavaコードとリソースは好きなように組織化および構造化できます。 Mavenや開発チームで使用されているものなど、フォルダ構造の規則を自由に使用できます。 また、GradleやMavenなどの任意のビルドツールを使用して[依存関係の管理](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)ができます。

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)は、モジュールプロジェクト（およびテーマプロジェクト）を管理するための環境です。 デフォルトのWorkspaceはGradleビルドスクリプトを提供し、LiferayプロジェクトテンプレートWorkspaceアーキタイプから作成されたWorkspaceは、Liferayで開発するためのMavenビルドスクリプトを提供します。 Workspaceは[コマンドラインから](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)または[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)内から使用できます。 Liferay @ide@は、Gradle、Maven、およびBndTools用のプラグインを提供することも覚えておいてください。 ツールの詳細については、このシリーズの後半で説明します。

モジュールの構造とマニフェストについて理解を深めた後は、モジュールの構築方法を見ていきます。

## bndを使用したモジュールの構築

モジュールを構築する最も一般的な方法は、[bnd](http://bnd.bndtools.org/)と呼ばれる小さなツールを使用する方法です。 なかでも、マニフェストメタデータの生成を簡素化するエンジンが特に使用されます。 `MANIFEST.MF`ファイルをマニュアルで作成する代わりに、開発者はbndを使用して生成します。 bndは、単独で使用することも、GradleやMavenなどの他のビルドツールとともに使用することもできます。 Liferay Workspaceは、bndとGradleまたはMavenを併用します。

bndの最も優れた機能の1つは、モジュールのコードを自動的に横断して、モジュールが使用する外部クラスを識別し、インポートするパッケージのマニフェストのリストに追加するということです。 bndは、モジュール開発を簡素化するOSGi固有の運用もいくつか提供します。

bndはプロジェクトルートの`bnd.bnd`というファイルに基づいてマニフェストを生成します。 このファイルのヘッダーリストは、`MANIFEST.MF`のヘッダーリスト（より短い）と似ています。 以下のLiferay Journal Webモジュールの`bnd.bnd`ファイルコンテンツ（若干簡略化）を、前述でリストしたその`MANIFEST.MF`ファイルコンテンツと比較します。

    Bundle-Name: Liferay Journal Web
    Bundle-SymbolicName: com.liferay.journal.web
    Bundle-Version: 1.1.2
    Export-Package:\
        com.liferay.journal.web.asset,\
        com.liferay.journal.web.dynamic.data.mapping.util,\
        com.liferay.journal.web.social,\
        com.liferay.journal.web.util

主な違いは、`bnd.bnd`ファイルが`Import-Package`ヘッダーを指定していないということです。 bndはそれを`MANIFEST.MF`ファイルで自動的に生成するため、その指定が不要となります。 このことから、メタデータが安易になりました。\

bndプラグインは、GradleおよびMavenで利用できます。 Liferay Workspaceにはbndが含まれているため、コマンドラインおよびLiferay @ide@からbndを使用できます。

それでは、OSGiモジュールを作成してデプロイする実践に移りましょう。 次の記事へ進みます。
