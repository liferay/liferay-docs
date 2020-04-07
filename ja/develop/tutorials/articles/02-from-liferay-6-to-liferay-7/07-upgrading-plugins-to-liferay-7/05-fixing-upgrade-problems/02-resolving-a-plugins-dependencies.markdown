---
header-id: resolving-a-plugins-dependencies
---

# プラグインの依存関係の解決

[TOC levels=1-4]

プラグインプロジェクトをLiferay @ide@にインポートしたので、おそらく使用するLiferayクラスの一部のコンパイルエラーが表示されます。 これらのクラスは、移動、名前変更、または削除されているため、未定義のクラスまたは未解決のシンボルとしてリストされます。 @product@のモジュール化の一環として、これらのクラスの多くは新しいモジュールに存在します。

プラグインのこれらすべてのLiferayクラスを解決する必要があります。 クラスの変更の一部は、迅速かつ簡単に修正できます。 新しいモジュールに関連する変更は、解決するためにより多くの努力を必要としますが、それでも実行するのは簡単です。

Liferayクラスの変更と必要な適応について、ここで説明します。

1.  **クラスをクラスパスにあるパッケージに移動**：一般的で簡単に修正できる変更です。 モジュールはすでにクラスパスにあるため、クラスのインポートをアップデートするだけで済みます。 これを行うには、Liferayコードアップグレードツールを使用するか、@ide@でインポートを整理します。 Upgrade Plannerは移動した各クラスを報告して、1つずつ対処します。 インポートを@ide@で整理すると、複数のクラスが一度で自動的に解決されます。

    通常、前述のEclipse機能を使用して、移動したクラスを解決する方が高速です。 Liferay @ide@はEclipseに基づいているため、*インポートを整理する*キーボード列*Ctrl-Shift-o*で、クラスパスにクラスのインポートを生成できます。 エラーとしてマークされたインポートはコメント化または削除してから、*Ctrl-Shift-o*を押してください。 インポートに一致するものが1つしかない場合、@ide@はそのインポートステートメントを自動的に生成します。 それ以外の場合は、正しいインポートを選択できるウィザードが表示されます。

2.  **クラスパス*ではなく*モジュールに移動したクラス**：プロジェクトの依存関係として新しいモジュールを解決する必要があります。 これを行うにはモジュールを識別し、プロジェクトの依存関係を指定する必要があります。

3.  **置換または削除されたクラス**：クラスは別のクラスに置き換えられたか、製品から削除されています。 The Upgrade Planner（後述）は、クラスに起きたこと、変更を処理する方法、および変更が行われた理由を説明します。

クラスパス内で移動したクラスの解決は簡単です。 まずはそのようなクラスを先に解決することを検討してください。 このチュートリアルの残りの部分では、最後の2つのケースを解決する方法について説明しますが、まずは必要なモジュールを宣言するためのプラグインプロジェクトの設定から始めます。

## モジュールの依存関係の特定

@product-ver@以前は、すべてのプラットフォームAPIは`portal-service.jar`にありました。 これらのAPIの多くは現在、独立したモジュールにあります。 モジュール化は[Benefits of @product-ver@ for Liferay Portal 6 Developers](/docs/7-1/tutorials/-/knowledge_base/t/benefits-of-liferay-7-for-liferay-6-developers#modular-development-paradigm)で説明しているように、多くのメリットをもたらしました 。 その利点の1つは、これらのAPIモジュールがプラットフォームカーネルとは別に進化できるということです。 さらに、今後のアップグレードも簡素化します。 たとえば、LiferayのAPIをすべてチェックする代わりに、各モジュールの[Semantic Versioning](http://semver.org)は、モジュールに後方互換性のない変更が含まれているかどうかを示します。 そのようなモジュール（ある場合）にコードを適合させるだけです。

モジュール化の一環として`portal-service.jar` は、ポータルカーネルのAPIを保持し続けるため、適切に`portal-kernel.jar`へ名前が変更されました。

![図1：Liferayは@product-ver@向けにポータルサービスJARをリファクタリングしました。 アプリケーションAPIは独自のモジュールに存在するようになり、ポータルサービスJARは*portal-kernel *になりました。](../../../../images/from-liferay-6-portal-apis-before-after.png)

各アプリケーションのモジュールは、アプリケーションのAPI、実装、またはUIの提供など、特定の目的を持つ、非常にまとまりのある一連のクラスで構成されています。 したがってアプリケーションモジュールの方が、はるかに理解しやすくなっています。 次に、プラグインが参照するクラスを保持するモジュールを追跡します。

[Classes Moved from`portal-service.jar`](/docs/7-1/reference/-/knowledge_base/r/classes-moved-from-portal-service-jar)では、`portal-service.jar`からその新しいモジュールに移動した各クラスをマップするテーブルについて説明しています。 テーブルには、各クラスの新しいパッケージとシンボル名（アーティファクトID）が含まれます。 この情報を使用して、これらのモジュールに対するプラグインの依存関係を構成します。

プラグインは、もともと`util-java`、`util-bridges`、`util-taglib`または `util-slf4j`として知られている、Liferayのユーティリティモジュールにあるクラスを参照することがあります。

次の表に、各Liferayユーティリティモジュールのシンボル名を示します。

| **Liferayユーティリティ** | **シンボル名（アーティファクトID）**      |
|:------------------ |:-------------------------- |
| util-bridges       | `com.liferay.util.bridges` |
| util-java          | `com.liferay.util.java`    |
| util-slf4j         | `com.liferay.util.slf4j`   |
| util-taglib        | `com.liferay.util.taglib`  |

@product@の[アプリケーションマネージャ](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies#finding-liferay-app-and-independent-artifacts)、[Felix Gogo Shel](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)、または [moduleJARファイルマニフェスト](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies#finding-core-artifacts)を使用して、@product@インスタンスにデプロイされたモジュールのバージョンを見つけることができます。

| **注:** 以前のバージョンのプラグインSDKは、プロジェクトで利用可能な`portal-service.jar` | を作成しました。 Liferay Portal 7.0プラグインSDKも同様に、`portal-kernel.jar`を利用可能にします。 @product@バンドル（アプリケーションサーバーに事前インストールされている | @product@）を使用している場合、Liferayユーティリティモジュールは既にお使いの | クラスパスにあります。 アプリケーションサーバーに@product@をマニュアルでインストールした場合、Liferay | ユーティリティモジュールがクラスパスにない可能性があります。 必要な | ユーティリティモジュールがクラスパスにない場合、そのシンボリック名（アーティファクトID）とバージョンに注意してください。

## 依存関係の解決

モジュールのアーティファクトIDとバージョンができたので、プラグインプロジェクトでモジュールを利用可能にできます。 プラグインが使用するモジュールは、コンパイル時および実行時に利用可能でなければなりません。 従来のプラグインプロジェクトでモジュールの依存関係を解決するための、2つのオプションを次に示します。

[**オプション1：依存関係管理ツールを使用する**](#using-a-dependency-management-tool)

[**オプション2：依存関係をマニュアルで管理する**](#managing-plugin-dependencies-manually)

次のセクションでは、これらのオプションについて説明し、実証します。

### 依存関係管理ツールを使用する

[Ant/Ivy](http://ant.apache.org/ivy/)、[Maven](/docs/7-1/tutorials/-/knowledge_base/t/maven)および [Gradle](https://gradle.org/)などの依存関係管理ツールでは、お使いのプラグインが必要なパッケージを提供するJavaアーティファクトの取得を促進します。 パブリックリポジトリまたはプロキシとして設定した内部リポジトリから、アーティファクトをダウンロードできます。 内部リポジトリから、依存関係を監査できます。

| プロキシの詳細についてのリンク：| | - [Ant/Ivy](http://ant.apache.org/ivy/) - プロキシ設定プロキシ構成についてのドキュメンテーション、`Setproxy`タスク、および [resolvers](http://ant.apache.org/ivy/history/latest-milestone/settings/resolvers.html) | - [Maven](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-maven-repository) | - [Liferay Workspace（Gradle）](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-workspace) | - [Liferay @ide@でプロキシを設定](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-ide)

LiferayプラグインSDKは、Ant/Ivyインフラストラクチャを提供します。 プラグインプロジェクトのルートフォルダにある`ivy.xml`ファイルで依存関係を宣言します。 プラグインSDKのAntタスクは、`ivy.xml`ファイルとプラグインSDKのIvyスクリプトを活用して、指定されたモジュールとその依存関係をダウンロードし、プラグインで利用可能にします。

| **注**: 依存関係管理には、Ivyの代わりにGradleまたはMavenを使用できます。| しかし、これはこのチュートリアルの対象範囲ではありません。 Liferayの | [Maven](/docs/7-1/tutorials/-/knowledge_base/t/maven)および | [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace) | チュートルアルでは、これらのツールの使用方法を示しています。 | |さらにLiferay Workspaceは、Ant/Ivy | プロジェクトをGradleベースのLiferay Workspaceプロジェクトに移行するためのコマンドを提供しています。 チュートリアル | [Migrating Traditional Plugins to Workspace Web Applications](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)をご覧ください。

Liferay Journal APIモジュール、バージョン2.0.1の依存要素の例を次に示します。

    <dependency name="com.liferay.journal.api" org="com.liferay" rev="2.0.1" />

各依存関係には、モジュールの名前（`name`）、組織（`org`）、およびリビジョン番号（`rev`）が含まれます。 チュートリアル[Configure Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)では、モジュールの組織（`org`）の設定方法について説明しています。

コンパイル時に、Ivyは依存関係JARファイルをキャッシュフォルダにダウンロードして、それらに対してコンパイルできるようにします。

<!-- TODO link to WAB generator-->

デプロイ時に、[@product@のWAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)は、プラグイン用のOSGi Webアプリケーションバンドル（WAB）を作成します。 WABジェネレーターは、プラグインが使用するJavaパッケージを検出し、それらの依存関係を宣言します。 プラグインは、登録済みのOSGiサービスが提供するパッケージを使用できます。

プロジェクトに`ivy.xml`ファイルがまだない場合は、Liferay @ide@で新しいプラグインプロジェクトを作成し、生成された`ivy.xml`ファイルをコピーすることで取得できます。

Liferay Portal 6.2 ナレッジベースポートレットから、`ivy.xml`ファイルの例を次に示します。

    <?xml version="1.0"?>
    
    <ivy-module
        version="2.0"
        xmlns:m2="http://ant.apache.org/ivy/maven"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://ant.apache.org/ivy/schemas/ivy.xsd"
    >
        <info module="knowledge-base-portlet" organisation="com.liferay">
            <extends extendType="configurations,description,info" location="${sdk.dir}/ivy.xml" module="com.liferay.sdk" organisation="com.liferay" revision="latest.integration" />
        </info>
    
        <dependencies defaultconf="default">
            <dependency org="com.liferay" name="com.liferay.markdown.converter" rev="1.0.2" />
        </dependencies>
    </ivy-module>

プラグインSDKは、プロジェクトIvyファイルと連携して、アーティファクトを保存し、プラグインプロジェクトにアクセスできるようにします。

Ivyやその他の依存関係管理フレームワークを使用したくない場合は、プラグインプロジェクト内にマニュアルで依存関係JARを保存できます。 このことについて次で学びます。

### プラグインの依存関係をマニュアルで管理する

プラグインは、コンパイル時および実行時の依存関係の可用性に依存しています。 プラグインをコンパイルするには、プラグインの`WEB-INF/lib`フォルダで依存関係が利用可能であることを確認する必要があります。 プラグインを実行するには、コンテナが以下2つのいずれかを見つけられる状態である必要があります。1つは依存性Javaパッケージが@product@のOSGiフレームワークで既にアクティブであること、もう1つは依存性JARがプラグイン用に生成されたWABに含まれていることです。 プラグインは、現在所有しているJARと@product@がエクスポートするパッケージの両方を使用できます。

#### パッケージポータルエクスポートの使用

Liferay Portal 6向けのプラグインSDKは、JARに対してコンパイルする方法を提供しました。 これらのJARは、[`liferay-plugin-package.properties`](@platform-ref@/7.1-latest/propertiesdoc/liferay-plugin-package_7_1_0.properties.html)ファイルの`portal-dependency-jars`プロパティで指定します。 プラグインの`portal-dependency-jars`リストを見ると、LiferayプラグインSDKはJARをプラグインの`WEB-INF / lib`へコピーしています。 プラグインSDKは、プラグインWARにJARを追加していません。 これにより、デプロイを高速化するためにWARが小さくなりました。 WARをリモートで、またはクラスターノードにデプロイする場合に、特にこれが役立ちました。

@product-ver@では、`portal-dependency-jars`プロパティは廃止され、以前のバージョンとは異なる動作をします。 JavaパッケージのインポートとエクスポートがJARの大規模な使用に取って代わったため、モジュールとWABはJARに関係なくパッケージをインポートできます。 つまり、@product@は過去に行ったのと同じJavaクラスを、プラグインで利用可能にできないということになります。

これらのファイルには、ポータルがエクスポートするパッケージがリストされています。

  - [GitHubリポジトリ](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/core/portal-bootstrap/system.packages.extra.bnd)にある`modules/core/portal-bootstrap/system.packages.extra.bnd`ファイル。 エクスポートされたパッケージを別々の行にリストしていて、読みやすくなっています。
  - `[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`にある`META-INF/system.packages.extra.mf`ファイル。 このファイルは、@product@バンドルで利用可能です。 エクスポートされたパッケージを、70カラムでラップされた段落にリストします。ここでは`system.packages.extra.bnd`ファイルよりも読みづらくなっています。

まだ`portal-dependency-jars`プロパティを使用している場合、以下のシナリオのいずれかに遭遇する可能性があります。 事象を解決するには、以下のシナリオの指示に従ってください。

1.  **JARを指定しましたが、@product-ver@では自分のプラグインで利用できるクラスがありません。**

    Liferay Portal 6.2で使用されていたいくつかのJARは、@product-ver@で削除されました。 `portal-dependency-jars`で指定した場合、@product@はそれらを提供できません。 それでもなお必要な場合、`portal-dependency-jars`プロパティからそれらを削除し、プラグインの`WEB-INF/lib`フォルダに必要なJARを追加します。

2.  **JARを指定しました。@product-ver@はプラグインがインポートする、すべてのJARパッケージもエクスポートします。**

    JARを`portal-dependency-jars`リストに保持してください。 プラグインSDKは、コンパイル時にJARをプラグインの`WEB-INF/lib`フォルダにコピーしますが、プラグインWABにJARを追加しません。 プラグイン用に生成されたWABは、実行時に登録済みプロバイダーからパッケージをインポートします。

3.  **@product-ver@はJARを提供しますが、プラグインがインポートするパッケージをエクスポートしません。**

    JARを`portal-dependency-jars`プロパティに保持してください。 プラグインSDKは、コンパイル時にJARをプラグインの`WEB-INF/lib`フォルダにコピーし、デプロイ時にJARをプラグインWABに追加します。

#### 除外されたJARについて

[ポータルプロパティ`module.framework.web.generator.excluded.paths`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)は、すべての@product@生成WABから削除されるJARを宣言します。 これらのJARは@product@によって既に提供されているため、WABから除外されます。 プラグインが`portal-dependency-jars`プロパティにJARをリストした場合でも、このプロパティに対してリストされたすべてのJARはWABから除外されます。

プラグインが@product@がエクスポートする異なるバージョンのパッケージを必要とする場合は、`module.framework.web.generator.excluded.paths`が除外するものとは異なる名前のJARにそれらを含める必要があります。

たとえば、@product@の[`system.packages.extra.bnd` ファイル](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/core/portal-bootstrap/system.packages.extra.bnd)は、Spring Frameworkバージョン4.1.9パッケージをエクスポートします。

    Export-Package:\
        ...
        org.springframework.*;version='4.1.9',\
        ...

@product@は、`module.framework.web.generator.excluded.paths`ポータルプロパティを使用して、JARを除外します。

    module.framework.web.generator.excluded.paths=\
        ...
        WEB-INF/lib/spring-aop.jar,\
        WEB-INF/lib/spring-aspects.jar,\
        WEB-INF/lib/spring-beans.jar,\
        WEB-INF/lib/spring-context.jar,\
        WEB-INF/lib/spring-context-support.jar,\
        WEB-INF/lib/spring-core.jar,\
        WEB-INF/lib/spring-expression.jar,\
        WEB-INF/lib/spring-jdbc.jar,\
        WEB-INF/lib/spring-jms.jar,\
        WEB-INF/lib/spring-orm.jar,\
        WEB-INF/lib/spring-oxm.jar,\
        WEB-INF/lib/spring-tx.jar,\
        WEB-INF/lib/spring-web.jar,\
        WEB-INF/lib/spring-webmvc.jar,\
        WEB-INF/lib/spring-webmvc-portlet.jar,\
        ...

WABで異なるSpring Frameworkバージョンを使用するには、対応するSpring Framework JARに、glob-patterned JARs`module.framework.web.generator.excluded.paths`リストとは異なる名前を付ける必要があります。

たとえば、Spring Frameworkバージョン3.0.7のSpring AOP JARを使用するには、プラグインの`WEB-INF/lib`にそれを含める必要がありますが、`spring-aop.jar`以外の名前を付けなければなりません。 JAR名にバージョンを追加すると（つまり`spring-aop-3.0.7.RELEASE.jar`）、除外されたJARと区別され、WABから削除されなくなります。

#### パッケージポータルを使用してもエクスポートされない

プラグインの`WEB-INF/lib`フォルダにJARをダウンロードしてインストールする必要があります。このJARは、プラグインが要求するものを@product@がエクスポートしないパッケージを提供します。

これを行うには、次の手順を実行します。

1.  <https://search.maven.org/>でMaven Centralにアクセスします。

2.  アーティファクトIDおよびグループIDでモジュールを検索します。

3.  検索結果をナビゲートして、目的のモジュールのバージョンを見つけます。

4.  *jar*リンクをクリックして、モジュールのJARファイルをダウンロードします。

5.  JARをプロジェクトの`WEB-INF/lib`フォルダに追加します。

![図2：Maven Centralを検索した後、* jar *リンクをクリックしてアーティファクトのJARファイルをダウンロードします。](../../../../images/resolving-plugin-dependencies-using-maven-central.png)

モジュールJARを管理するとき、OSGiフレームワークJARまたはLiferayモジュールJAR（例: `com.liferay.journal.api.jar`）をデプロイ</strong>しない**ようにしてください。 これらをデプロイすると、OSGiフレームワークにすでにインストールされているJARと競合します。 2つの異なるクラスローダーにある同一のJARによって、クラスキャストの例外が発生する場合があります。 プラグインのデプロイからそのようなJARを除外する最も簡単な方法は、プラグインの`liferay-plugin-package.properties`にある`deploy-excludes`プロパティに、それらをリストすることです。 それ以外の場合は、プラグインWARファイルからJARをマニュアルで削除する必要があります。 プラグインの`liferay-plugin-package.properties`ファイルでJARを除外するには、以下のようなエントリを追加し、角括弧で囲まれた項目を除外するJARファイルの名前に置き換えます。</p> 

    deploy-excludes=\
        **/WEB-INF/lib/[module-artifact.jar],\
        **/WEB-INF/lib/[another-module-artifact.jar]

例として、OSGiフレームワークJAR`osgi.core.jar`およびLiferayアプリケーションモジュールJAR`com.liferay.journal.api.jar`を除外するサンプルのプロパティを次に示します。

    deploy-excludes=\
        **/WEB-INF/lib/com.liferay.portal.journal.api.jar,\
        **/WEB-INF/lib/org.osgi.core.jar

次に、どのモジュールがすでに@product@にインストールされているかを知る方法について示します。 もし@product@インスタンスに特定のLiferayアプリ・スイートがインストールされている場合、そのアプリ・スイートにあることがわかっているモジュールJARをデプロイしないでください。 たとえば、Web Experience Managementアプリ・スイートがすでにインストールされている場合（@product@バンドルの場合）、`com.liferay.journal.api.jar`などのWebコンテンツモジュールJARをデプロイしないでください。 @product@の[アプリケーションマネージャ](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps)でモジュールを検索することは、既存のモジュールのインストールを確認する確実な方法です。
