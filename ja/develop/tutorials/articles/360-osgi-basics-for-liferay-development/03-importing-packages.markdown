---
header-id: importing-packages
---

# パッケージのインポート

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたのモジュールは、多くの場合、パッケージからJavaクラスを使用する必要があります [エクスポート](/docs/7-1/tutorials/-/knowledge_base/t/exporting-packages) 他のモジュールで。 モジュールがインポート用にセットアップされると、OSGiフレームワークは、必要なパッケージをエクスポートする他の登録済みモジュールを見つけ、それらをインポートモジュールに接続します。 実行時に、インポートモジュールは、クラスのパッケージをエクスポートする有線モジュールからクラスを取得します。

これを実現するには、モジュールは、必要なJavaパッケージのコンマ区切りリストで `Import-Package` OSGiマニフェストヘッダーを指定する必要があります。 たとえば、モジュールに `javax.portlet` および `com.liferay.portal.kernel.util` パッケージのクラスが必要な場合、次のように指定する必要があります。

    Import-Package: javax.portlet,com.liferay.portal.kernel.util

インポートパッケージは、手動で指定する必要がありますが、常に指定する必要はありません。 便利なことに、@product@プロジェクトのテンプレートとツールは、モジュールが使用するパッケージを自動的に検出し、モジュールJARのマニフェストのパッケージインポートに追加します。 さまざまなパッケージインポートシナリオを次に示します。

  - [パッケージの自動インポート生成](#automatic-package-import-generation)

  - [パッケージのインポートを手動で追加する](#manually-adding-package-imports)

これらのシナリオでパッケージのインポートがどのように指定されるかを見てみましょう。

## パッケージの自動インポート生成

[のGradleとMavenモジュールプロジェクト](/docs/7-1/reference/-/knowledge_base/r/project-templates) 使用して作成された [ブレードCLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)、 [LiferayのMavenの原型](/docs/7-1/tutorials/-/knowledge_base/t/maven)、又は [のLiferay @ IDE @](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) 用途 [BND](http://bnd.bndtools.org/)。 そのようなプロジェクトのモジュールJARをビルドすると、bndはモジュールが使用するパッケージを検出し、 `Import-Package` ヘッダーがパッケージを指定する `META-INF / MANIFEST.MF` ファイルを生成します。

| **注**：LiferayのMavenモジュールアーキタイプは `bnd-maven-plugin`ます。 | LiferayのGradleモジュールプロジェクトテンプレートの使用| [サードパーティのGradleプラグイン](https://github.com/TomDmitriev/gradle-bundle-plugin) | bndを呼び出します。

たとえば、MavenまたはGradleを使用してLiferayモジュールを開発しているとします。 ほとんどの場合、 `pom.xml` または `build.gradle` ファイルでモジュールの依存関係を指定します。 ビルド時に、MavenまたはGradleバンドルプラグインは `pom.xml` または `build.gradle` ファイルを読み取り、必要な `Import-Package` ヘッダーをモジュールJARの `META-INF / MANIFEST.MF`追加します。

次に、モジュールの `build.gradle` ファイルの依存関係セクションの例を示します。

    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

モジュールJARの `META-INF / MANIFEST.MF` ファイルで生成される `Import-Package` ヘッダーは次のとおりです。

    Import-Package: com.liferay.portal.kernel.portlet.bridges.mvc;version=
    "[1.0,2)",com.liferay.portal.kernel.util;version="[7.0,8)",javax.nami
    ng,javax.portlet;version="[2.0,3)",javax.servlet,javax.servlet.http,j
    avax.sql

ビルドファイルはJARファイルの依存関係のみを指定する必要があることに注意してください。 bndはモジュールのクラスパスを調べて、アプリケーションが使用するクラスをそれらのJARファイルのどのパッケージに含めるかを決定し、パッケージをインポートします。 でも、これらの組み込みから-検査では、クラスパスで見つかったすべてのクラスが含ま [サードパーティのライブラリJAR](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)。

従来のLiferayプラグインWARで使用されるクラスに関して、 [LiferayのWAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) は、WARのJSP、記述子ファイル、およびクラス（ `WEB-INF / classes` および組み込みJAR）での使用を検出します。 WABジェネレータ検索 `web.xmlの`、 `のLiferay-web.xmlの`、 `のportlet.xml`、 `のLiferay-のportlet.xml`、および `のLiferay-hook.xml` 記述子ファイル。 プラグインの `WEB-INF / classes` フォルダーにも埋め込みJARにも見つからないクラスのパッケージインポートを追加します。

| **注：** JavaポートレットなどのJava APIのパッケージは、意味論的ではありません。バージョン管理されていますが、ポータブルJavaコントラクトがあります。 各APIのコントラクトでは、 JSRが満たしています。 これらのAPIを使用するモジュールは、要件を指定する必要があります| API契約。 契約要件は、モジュールの関係を指定します|インポートされたAPIパッケージを使用します。 実行しているシステムが *はない場合、* 提供しません。正確な契約、あなたのモジュールは解決しません。 不足しているパッケージの解決|実行中の非互換性の障害を処理するよりも優れています。 | | **Blade CLIおよびLiferay @ide@モジュールプロジェクト** ポータブルJavaを指定|自動的に契約する\！ たとえば、Blade CLIまたはLiferay @ide@ |モジュールはJavaポートレットAPIを使用し、Javaポートレットに対してコンパイルします| 2.0アーティファクト、パッケージの契約要件が追加されます|モジュールのマニフェスト。 | | **bndを使用しているが、Blade CLIまたは|を使用して作成されていないモジュールプロジェクトLiferay @ide@** は、 `bnd.bnd` ファイルで契約を指定する必要があります。 たとえば、| JavaポートレットおよびJavaサーブレットAPIの契約手順は次のとおりです。 | -contract：JavaPortlet、JavaServlet | |ビルド時に、bndは契約の指示をモジュールのマニフェストに追加します。 | |にあるAPIの最初のバージョンの要件が追加されます。クラスパスと *は、 `Import-Package` から* バージョン範囲情報を削除します。対応するAPIパッケージのエントリ---パッケージバージョン情報|必要ありません。 | | **bnd** 使用しないプロジェクトは、モジュールで契約を指定する必要があります|マニフェスト。 たとえば、 `JavaPortlet` 2.0の指定されたコントラクトは次のとおりです。これは `META-INF / MANIFEST.MF` ファイルに含まれます。 |インポートパッケージ：javax.portlet |必須機能：osgi.contract; filter：=（&（osgi.contract = JavaPortlet）（version = 2.0））| |ポータブルJava契約の詳細については、を参照してください| [ポータブルJavaコントラクト定義](https://www.osgi.org/portable-java-contract-definitions/)。

| **注：** @product@ 7.1 GA1はJava Portlet 2.0 APIをエクスポートします。 Javaポートレットまで| 3.0 APIがサポートされています。必ず2.0バージョンを使用してください。

## パッケージのインポートを手動で追加する

WAB Generatorおよびbndは、これらの場所で参照されるクラスのパッケージインポートを追加しません。

  - 認識されない記述子ファイル
  - カスタムまたは認識されない記述子要素または属性
  - 反射コード
  - クラスローダーコード

このような場合、必要なパッケージを手動で決定し、プロジェクトタイプに適した場所の `Import-Package` OSGiヘッダーに追加する必要があります。

| プロジェクトの種類          | `Import-Package` ヘッダーの場所                    |
|:------------------ |:------------------------------------------- |
| モジュール（bndを使用）      | `[project]/bnd.bnd`                         |
| モジュール（bndを使用しません）  | `[module JAR]/META-INF/MANIFEST.MF`         |
| 従来のLiferayプラグインWAR | `WEB-INF/liferay-plugin-package.properties` |

| **注：** | [WABジェネレーター](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) |埋め込まれたWARプロジェクトの追加を控える|サードパーティのJARをWABに [@product@はすでにJARのパッケージをエクスポートしています](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#understanding-excluded-jars)。 | | WARに必要なサードパーティパッケージの異なるバージョンが必要な場合| @product@ exports、 `Import-Package：` リストでそのパッケージを指定します。 その後|パッケージプロバイダーがOSGiモジュールの場合、エクスポートされたパッケージを|で公開します。モジュールをデプロイします。 パッケージプロバイダーがOSGiモジュールでない場合は、 |の手順| [サードパーティライブラリの追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-third-party-libraries-to-a-module)

おめでとうございます！ これで、使用するモジュールとプラグインのすべての種類のパッケージをインポートできます。

## 関連トピック

[依存関係の構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[ツーリング](/docs/7-1/tutorials/-/knowledge_base/t/tooling)
