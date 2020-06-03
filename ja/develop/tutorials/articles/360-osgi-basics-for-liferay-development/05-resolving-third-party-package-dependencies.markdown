---
header-id: adding-third-party-libraries-to-a-module
---

# サードパーティライブラリパッケージの依存関係の解決

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

OSGiフレームワークを使用すると、複数のOSGiバンドル（モジュール）で構成されるアプリケーションを構築できます。 フレームワークがモジュールを作業システムにアセンブルするには、モジュールがJavaパッケージの依存関係を解決する必要があります。 完璧な世界では、すべてのJavaライブラリはOSGiモジュールになりますが、多くのライブラリはそうではありません。 では、OSGi以外のサードパーティライブラリからモジュールに必要なパッケージをどのように解決しますか？

サードパーティのJavaライブラリパッケージを解決するための主なワークフローは次のとおりです。

**ステップ1-ライブラリのOSGiモジュールを見つける**： [Eclipse Orbit](https://www.eclipse.org/orbit/) および [ServiceMixバンドル](https://servicemix.apache.org/developers/source/bundles-source.html)などのプロジェクトは、数百の従来のJavaライブラリをOSGiモジュールに変換します。 それらのアーティファクトは、次の場所で入手できます。

  - [日食軌道](https://download.eclipse.org/tools/orbit/downloads/drops/R20170919201930/)
  - [ServiceMixバンドル](https://mvnrepository.com/artifact/org.apache.servicemix.bundles)

LiferayのOSGiフレームワークにモジュールをデプロイすると、システム上でモジュールを共有できます。 必要なライブラリのモジュールが見つかったら、 [デプロイ](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module) します。 次に、モジュールに `compileOnly` 依存関係を追加します。 モジュールをデプロイすると、OSGiフレームワークは依存モジュールをモジュールに接続します。 Javaライブラリに基づいたOSGiモジュールが見つからない場合は、手順2に進みます。

| **ヒント：** 同じものを提供するライブラリJARの埋め込みは控えてください。 @product@または既存のモジュールがすでに提供している [パッケージ](/docs/7-1/reference/-/knowledge_base/r/third-party-packages-portal-exports)。

| **注：** 異なるバージョンのaを必要とするWARを開発している場合|サードパーティのパッケージ [@product@または別のモジュールのエクスポート](/docs/7-1/reference/-/knowledge_base/r/third-party-packages-portal-exports)、|そのパッケージを指定します| [`Import-Package：` リスト](/docs/7-1/tutorials/-/knowledge_base/t/importing-packages) |パッケージプロバイダーがOSGiモジュールの場合、エクスポートされたパッケージを|で公開します。そのモジュールをデプロイします。 それ以外の場合、サードパーティライブラリ（OSGiモジュールではない）の名前を|とは異なる名前に変更します。 [WABジェネレーターが除外するJAR](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#understanding-excluded-jars) |プロジェクトにJARを埋め込みます。

**ステップ2-モジュール内のJavaパッケージをプライベートに解決します**：必要に応じて、必要なライブラリパッケージをモジュールにコピーするか、卸売りで埋め込むことができます。 チュートリアルの残りの部分では、これらのことを行う方法を示します。

| **注**：LiferayのGradleプラグイン `com.liferay.plugin` 数分の</code>自動化します|パーティライブラリの構成手順。 プラグインは自動的に適用されます| [Liferayワークスペース](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace) | [Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) を使用して作成されたGradleモジュールプロジェクト|または [Liferay Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)。 | | Liferayワークスペースの外部で `com.liferay.plugin` プラグインを活用するには、追加|以下のリストのようなコードをGradleプロジェクトに追加します。 | buildscript {|依存関係{|クラスパスグループ： "com.liferay"、名前： "com.liferay.gradle.plugins"、バージョン： "3.2.29" | } | |リポジトリ{| maven {| url "<https://repository-cdn.liferay.com/nexus/content/groups/public>" | } | } | } | |プラグインの適用： "com.liferay.plugin" | | `com.liferay.plugin` プラグインなしでGradleを使用する場合、以下を行う必要があります。 [サードパーティ製ライブラリの卸売を埋め込みます](#embedding-a-library-using-gradle)。

推奨されるパッケージ解決ワークフローは次です。

## ライブラリパッケージ解決ワークフロー

ライブラリJARに依存する場合、ほとんどの場合、必要なのはその一部だけです。 必要なJavaパッケージのみを明示的に指定すると、モジュールがよりモジュール化されます。 これにより、モジュールに依存する他のモジュールが不要なパッケージを組み込むこともなくなります。

依存関係とJavaパッケージのインポートを最小限に抑える構成ワークフローを次に示します。

1.  ライブラリをコンパイル専用の依存関係として追加します（たとえば、Gradleで `compileOnly`）。

2.  `bnd.bnd` ファイルの条件付きパッケージ命令（`条件付きパッケージ`）で指定して、必要なライブラリパッケージのみをコピーします。 ここではいくつかの例を示します。

    `条件-パッケージ：foo.common *` あなたのモジュールが使用するパッケージを追加するなど、 `foo.common`、 `foo.common -メッセージ`、 `foo.common-ウェブ` あなたのモジュールのクラスパスへ。

    `条件・パッケージ：foo.bar *` パッケージを追加し、あなたのモジュールは、以下のような使用しています `foo.bar` とそのすべてのサブパッケージ（例えば、 `foo.bar.baz`、 `foo.bar.biz`など）モジュールのクラスパスへ。

    モジュールをデプロイします。 モジュールに必要なクラスまたはその依存関係に必要なクラスが見つからない場合は、メインワークフロー **戻りますステップ1-ライブラリ** OSGiモジュールバージョンを見つけて解決します。

    **重要**：コンパイルのみの依存関係と条件付きパッケージ命令を使用してパッケージを解決すると、必要なパッケージのみを使用することが保証され、不必要な推移的な依存関係が回避されます。 必要なパッケージを解決するには、可能な限りこの時点までの手順を使用することをお勧めします。

3.  あなたが依存ライブラリパッケージは、ライブラリーから（例えば、DLLが、記述子）非クラスファイルを必要とする場合は、する必要があるかもしれません [モジュールで、ライブラリ卸売を埋め込む](#embedding-libraries-in-a-module)。 これにより、ライブラリ全体がモジュールのクラスパスに追加されます。

次に、モジュールにライブラリを埋め込む方法を学びます。

## モジュールへのライブラリの埋め込み

Gradle、Maven、またはIvyを使用して、モジュールにライブラリを埋め込むことができます。 以下は、3つのビルドユーティリティすべてを使用して [Apache Shiro](https://shiro.apache.org) を追加する例です。

### Gradleを使用してライブラリを埋め込む

モジュールの `build.gradle` ファイルを開き、 `compileInclude` 構成でライブラリを依存関係として追加します。

    dependencies {
        compileInclude group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
    }

`com.liferay.plugin` プラグインの `compileInclude` 構成は推移的です。 `compileInclude` 構成は、アーティファクトとそのすべての依存関係をモジュールのJARの `lib` フォルダーに埋め込みます。 また、モジュールの `Bundle-ClassPath` マニフェストヘッダーにアーティファクトJARを追加します。

**注**： `compileInclude` 構成は、推移的な [オプションの依存関係をダウンロードしません](https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html)。 モジュールにそのようなアーティファクトが必要な場合は、別のサードパーティライブラリと同じように追加してください。

**注：** 、あなたに依存関係として追加したライブラリ場合は `build.gradle` ファイルが推移依存関係を持っている、あなたはで名前によってそれらを参照することができます `-includeresource：` 命令を依存関係リストに明示的にそれらを追加することなく、 。 次のMavenセクションでどのように使用されるかを確認してください。

### MavenまたはIvyを使用したライブラリの埋め込み

次の手順を実行します：

1.  モジュールのビルドファイルを開き、ライブラリを依存関係として `提供された` スコープに追加します。

    **メイヴン:**<dependency> <groupId>org.apache.shiro</groupId> <artifactId>シロコア</artifactId> <version>1.1.0</version> <scope>提供された</scope> </dependency> 

    **Ant/Ivy:**
    
        <dependency conf="provided" name="shiro-core" org="org.apache.shiro" rev="1.1.0" />

2.  モジュールの `bnd.bnd` ファイルを開き、ライブラリを `-includeresource` 命令に追加します。
   
        -includeresource: META-INF/lib/shiro-core.jar=shiro-core-[0-9]*.jar;lib:=true

    この命令は、モジュールの `META-INF / lib` フォルダーに含まれるリソースとして `shiro-core-[version].jar` ファイルを追加します。 `META-INF / lib / shiro-core.jar` は、モジュールの組み込みライブラリです。 式 `[0-9]*` は、ビルドツールがライブラリバージョンと一致して、モジュールのクラスパスで使用できるようにするのに役立ちます。 `lib：= true` ディレクティブは、 `Bundle-Classpath` マニフェストヘッダーを介してモジュールのクラスパスに埋め込みJARを追加します。

最後に、ライブラリを埋め込んだ後、Liferayにデプロイしようとしたときに未解決のインポートが発生する場合、いくつかのインポートをブラックリストに登録する必要がある場合があります。

`Import-Package: !foo.bar.baz`

おめでとうございます！ モジュールのすべてのパッケージ依存関係、特に従来のJavaライブラリからの依存関係を解決することは、かなりの成果です。

## 関連トピック

[パッケージのインポート](/docs/7-1/tutorials/-/knowledge_base/t/importing-packages)

[パッケージのエクスポート](/docs/7-1/tutorials/-/knowledge_base/t/exporting-packages)

[Blade CLIを使用したプロジェクトの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)
