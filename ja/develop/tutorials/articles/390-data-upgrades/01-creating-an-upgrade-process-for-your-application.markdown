---
header-id: creating-an-upgrade-process-for-your-app
---

# モジュールのデータアップグレードプロセスの作成

[TOC levels=1-4]

モジュールに加えるいくつかの変更には、データベースの変更が含まれます。 これらの変更により、モジュールのデータベースを、次のバージョンに移動するためのアップグレードプロセスが必要になります。 Liferayには、これを簡単にするために使用できるアップグレードフレームワークがあります。 アップグレードを安全に行う機能豊富なフレームワークです。システムはスキーマの現在の状態を記録するため、アップグレードが失敗した場合、プロセスはモジュールを以前のバージョンに戻すことができます。

**注**：従来のLiferayプラグイン(WARファイル)のアップグレードプロセスは機能します 。これは[Liferay Portal 6.x](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes#understanding-liferay-portal-6-upgrade-processes)場合と同じ方法です。

@product@のアップグレードフレームワークは、新しいバージョンが初めて起動したときにモジュールのアップグレードを自動的に実行します。 具体的なデータスキーマの変更をアップグレードステップクラスに実装し、アップグレードステップ登録者を使用してアップグレードフレームワークに登録します。 このチュートリアルでは、これらすべてを実行してモジュールのアップグレードプロセスを作成する方法を学習します。

関連するものは次のとおりです。

  - **[スキーマバージョンの指定](#specifying-the-schema-version)**

  - **[依存関係の宣言](#declaring-dependencies)**

  - **[アップグレード手順を書く](#writing-upgrade-steps)**

  - **[登録者を書く](#writing-the-upgrade-step-registrator)**

  - **[アップグレードの完了を待っています](#waiting-for-upgrade-completion)**

それでは、始めましょう。

## スキーマバージョンの指定

モジュールの `bnd.bnd` ファイルで、新しいスキーマバージョン値で `Liferay-Require-SchemaVersion` ヘッダーを指定します。 次に、新しいスキーマがバージョン `1.1`であるモジュールのスキーマバージョンヘッダーの例を示します。

    Liferay-Require-SchemaVersion: 1.1

メジャースキーマバージョンとマイナースキーマバージョン（フォーマット `major.minor`）のみを指定すると、モジュールに任意のマイクロスキーマバージョンを使用する柔軟性が与えられます。 これにより、新しいマイクロスキーマバージョンを無視したり、必要に応じてアップグレードしたりできます。 マイクロスキーマバージョンを元に戻すこともできます。

| **重要**： `Liferay-Require-SchemaVersion` ヘッダーが指定されていない場合、 @product@は、 `Bundle-Version` ヘッダー値をデータベーススキーマバージョンと見なします。

次に、アップグレードの依存関係を指定します。

## 依存関係の宣言

あなたのモジュールの依存関係管理ファイル（例えば、MavenのPOM、Gradleのビルドファイル、またはアイビーファイルでは `ivy.xml` ファイル）で、 [依存関係の追加](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies) に [`com.liferay.portal.upgrade` モジュール](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.upgrade/)を追加します。

`build.gradle` ファイルでは、依存関係は次のようになります。

    compile group: "com.liferay", name: "com.liferay.portal.upgrade.api", version: "2.0.3"

アップグレードプロセスに必要な他のモジュールがある場合は、それらを依存関係として指定します。

モジュールプロジェクトをアップグレード用に構成しました。 データベースを現在のスキーマバージョンから新しいスキーマバージョンに更新するアップグレード手順を作成します。

## アップグレード手順の作成

アップグレード手順は、モジュールデータをモジュールのターゲットデータベーススキーマに適合させるクラスです。 SQLコマンドとDDLファイルを実行して、データをアップグレードできます。 アップグレードフレームワークを使用すると、アップグレードロジックをスキーマバージョンごとに複数のアップグレードステップクラスにカプセル化できます。

アップグレードクラスは、 [`UpgradeProcess` 基本クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html)拡張し、 [`UpgradeStep` インターフェイス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeStep.html)を実装します。 各アップグレード手順では、 `UpgradeProcess` クラスのメソッド `doUpgrade` を、データベースを変更するための指示でオーバーライドする必要があります。

`UpgradeProcess` は [`BaseDBProcess` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/db/BaseDBProcess.html)拡張するため、 `runSQL` および `runSQLTemplate *` メソッドを使用して、SQLコマンドとSQL DDLをそれぞれ実行できます。

SQLファイルからDDL文を実行して、テーブルまたはインデックスを作成、変更、または削除する場合は、必ずANSI SQLのみを使用してください。 これにより、コマンドが異なるデータベースで動作することが保証されます。

非ANSI SQLを使用する必要がある場合は、 [`UpgradeProcess` クラスの](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html) `runSQL` または `alter` メソッドと、文を異なるデータベースに移植できるトークンで記述するのが最適です。

たとえば、journal-serviceモジュールの [`UpgradeSchema` アップグレードステップクラス](https://github.com/liferay/liferay-portal/blob/master/modules/apps/journal/journal-service/src/main/java/com/liferay/journal/internal/upgrade/v0_0_4/UpgradeSchema.java)考えます。

    package com.liferay.journal.internal.upgrade.v0_0_4;
    
    import com.liferay.journal.internal.upgrade.v0_0_4.util.JournalArticleTable;
    import com.liferay.journal.internal.upgrade.v0_0_4.util.JournalFeedTable;
    import com.liferay.portal.kernel.upgrade.UpgradeMVCCVersion;
    import com.liferay.portal.kernel.upgrade.UpgradeProcess;
    import com.liferay.portal.kernel.util.StringUtil;
    
    /**
     * @author Eduardo Garcia
     */
    public class UpgradeSchema extends UpgradeProcess {
    
        @Override
        protected void doUpgrade() throws Exception {
            String template = StringUtil.read(
                UpgradeSchema.class.getResourceAsStream("dependencies/update.sql"));
    
            runSQLTemplateString(template, false, false);
    
            upgrade(UpgradeMVCCVersion.class);
    
            alter(
                JournalArticleTable.class,
                new AlterColumnName(
                    "structureId", "DDMStructureKey VARCHAR(75) null"),
                new AlterColumnName(
                    "templateId", "DDMTemplateKey VARCHAR(75) null"),
                new AlterColumnType("description", "TEXT null"));
    
            alter(
                JournalFeedTable.class,
                new AlterColumnName("structureId", "DDMStructureKey TEXT null"),
                new AlterColumnName("templateId", "DDMTemplateKey TEXT null"),
                new AlterColumnName(
                    "rendererTemplateId", "DDMRendererTemplateKey TEXT null"),
                new AlterColumnType("targetPortletId", "VARCHAR(200) null"));
        }
    
    }

上記の例のクラス `UpgradeSchema` は、 `runSQLTemplateString` メソッドを使用して、SQLファイルからANSI SQL DDLを実行します。 列名と列タイプを変更するには、 `alter` メソッドと [`UpgradeProcess`の](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upgrade/UpgradeProcess.html) `UpgradeProcess.AlterColumnName` および `UpgradeProcess.AlterColumnType` 内部クラスをトークンクラスとして使用します。

`com.liferay.calendar.service` モジュールからの簡単なアップグレード手順の例を次に示します。 `alter` メソッドを使用して、カレンダー予約テーブルの列タイプを変更します。

    public class UpgradeCalendarBooking extends UpgradeProcess {
    
            @Override
            protected void doUpgrade() throws Exception {
                    alter(
                            CalendarBookingTable.class,
                            new AlterColumnType("description", "TEXT null"));
            }
    
    }

モジュールスキーマに対して、これらのようなアップグレード手順を実装できます。

アップグレード手順の命名方法と編成方法をご自由に決めてください。 Liferayのアップグレードクラスは、次のようなパッケージ構造を使用して編成されています。

  - *some.package.structure*
      - `アップグレード`
          - `v1_1_0`
              - `UpgradeFoo.java` ←アップグレード手順
          - `v2_0_0`
              - `UpgradeFoo.java` ←アップグレード手順
              - `UpgradeBar.java` ←アップグレード手順
          - `MyCustomModuleUpgrade.java` ←登録者

上記のアップグレード構造の例は、2つのデータベーススキーマバージョン `1.1.0` および `2.0.0`を持つモジュール用です。 それらは、パッケージ `v1_1_0` および `v2_0_0`表されます。 各バージョンパッケージには、データベースを更新するアップグレードステップクラスが含まれています。 アップグレード手順の例は、架空のデータ要素 `Foo` および `Bar`焦点を当てています。 登録者クラス（この例では`MyCustomModuleUpgrade`）は、各スキーマバージョンに適用可能なアップグレード手順を登録する役割を果たします。

いくつかの組織的なヒントを次に示します。

  - すべてのアップグレードクラスを `upgrade`というサブパッケージに入れます。

  - 同様のデータベース更新（データ要素または関連データ要素で動作する更新）を同じアップグレードステップクラスにグループ化します。

  - 各データスキーマバージョンにちなんで名付けられたサブパッケージにアップグレード手順を作成します。

アプリケーションが以前のLiferayのプラグインアプリケーション（アプリケーションWAR）からモジュール化し、それがサービスビルダを使用した場合は、アップグレード手順のregistratorsを続行する前に、それは@product@の`Release_` テーブルを登録するBundle Activatorが必要になります。 このようなアプリケーションの場合、 [Bundle Activatorを作成および登録](/docs/7-1/tutorials/-/knowledge_base/t/upgrade-processes-for-former-service-builder-plugins)してから、ここに戻ってアップグレードステップ登録者を入力します。

## アップグレードステップ登録者の作成

モジュールのアップグレードステップ登録者は、Liferayのアップグレードフレームワークにすべてのアップグレードステップを通知し、各スキーマバージョンのモジュールデータを更新します。 モジュールのアップグレードプロセス全体を指定します。 アップグレードフレームワークは、アップグレード手順を実行して、現在のモジュールデータを最新のスキーマに更新します。

たとえば、アップグレードステップ登録者クラス `MyCustomModuleUpgrade` （下）は、各スキーマバージョン（過去と現在）のアップグレードステップを段階的に登録します。

    package com.liferay.mycustommodule.upgrade;
    
    import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
    
    import org.osgi.service.component.annotations.Component;
    
    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class MyCustomModuleUpgrade implements UpgradeStepRegistrator {
    
        @Override
        public void register(Registry registry) {
            registry.register(
                "com.liferay.mycustommodule", "0.0.0", "2.0.0",
                new DummyUpgradeStep());
    
            registry.register(
                "com.liferay.mycustommodule", "1.0.0", "1.1.0",
                new com.liferay.mycustommodule.upgrade.v1_1_0.UpgradeFoo());
    
            registry.register(
                "com.liferay.mycustommodule", "1.1.0", "2.0.0",
                new com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo(),
                new com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeBar());
        }
    
    }

登録者の `register` メソッドは、各新しいスキーマと関連するアップグレード手順についてアップグレードフレームワークに通知し、それにデータを適合させます。 各スキーマのアップグレードは、登録によって表示されます。 登録は、あるスキーマバージョンから次のスキーマバージョンにデータベースに適用する必要があるすべての変更の抽象化です。

次の図は、登録者とアップグレード手順の関係を示しています。

![図1：登録クラスでは、開発者は各スキーマバージョンのアップグレードの登録を指定します。 アップグレード手順は、データベースの更新を処理します。](../../images/data-upgrade-module-upgrade-architecture.png)

前の例 `MyCustomModuleUpgrade` 登録クラスリストは、これがどのように機能するかを示しています。

登録者クラスは、サービスタイプ `UpgradeStepRegistrator.class`OSGiコンポーネントであることを宣言します。 `@Component` アノテーションは、クラスをOSGiフレームワークにモジュールのアップグレードステップ登録者として登録します。 `immediate = true`属性は、OSGiフレームワークがインストールされた直後にこのモジュールをアクティブにするように指示します。

登録者は `com.liferay.portal.upgrade` モジュールにある [`UpgradeStepRegistrator` インターフェース](@app-ref@/foundation/latest/javadocs/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html)実装します。 インターフェイスは、登録者がオーバーライドする必要がある [`register` method](@app-ref@/foundation/latest/javadocs/com/liferay/portal/upgrade/registry/UpgradeStepRegistrator.html#register-com.liferay.portal.upgrade.registry.UpgradeStepRegistrator.Registry-) を宣言します。 その方法では、登録者はすべてのモジュールのアップグレード登録を実装します。

アップグレード登録は、次の値によって定義されます。

  - **モジュールのバンドルのシンボリック名**
  - **** からアップグレードするスキーマバージョン（ `文字列`として）
  - **** にアップグレードするスキーマバージョン（ `文字列`として）
  - **アップグレード手順のリスト**

サンプル登録者 `MyCustomModuleUpgrade` 3つのアップグレードを登録します。

  - `0.0.0` から `2.0.0`
  - `1.0.0` から `1.1.0`
  - `1.1.0` から `2.0.0`

モジュールが以前にインストールされていない場合、 `MyCustomModuleUpgrade` 登録者の最初の登録がアップグレードフレームワークによって適用されます。 ：アップグレード手順のリストは一つだけ含まれている `new DummyUpgradeStep()`。

    registry.register(
        "com.liferay.document.library.web", "0.0.0", "2.0.0",
        new DummyUpgradeStep());

[`DummyUpgradeStep` クラス](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/portal-kernel/src/com/liferay/portal/kernel/upgrade/DummyUpgradeStep.java) は、空のアップグレード手順を提供します。 `MyCustomModuleUpgrade` 登録者は、この登録を定義して、アップグレードフレームワークがモジュールの最新のスキーマバージョン（つまり、 `2.0.0`）を@product@の `Release_` テーブルに記録するようにします。

**重要**：サービスビルダ使用モジュール *ないはず* サービスビルダは、すでに製品@にそのスキーマのバージョンを記録して、彼らの初期のデータベーススキーマのバージョンの登録を定義し、@の `Release_` テーブルを。 サービスビルダを使用していないモジュールは、しかし、 最初のスキーマの登録を*定義する必要があります*。

`MyCustomUpgrade` 登録者の次の登録（スキーマバージョン `1.0.0` から `1.1.0`）には、1つのアップグレードステップが含まれます。

    registry.register(
        "com.liferay.mycustommodule", "1.0.0", "1.1.0",
              new com.liferay.mycustommodule.upgrade.v1_1_0.UpgradeFoo());

`UpgradeFoo` という名前のクラスはパッケージ `com.liferay.mycustommodule.upgrade.v1_1_0`および `com.liferay.mycustommodule.upgrade.v2_0_0`ため、アップグレードステップの完全修飾クラス名が必要です。

登録者の最終登録（スキーマバージョン `1.1.0` から `2.0.0`）には、2つのアップグレード手順が含まれます。

    registry.register(
        "com.liferay.mycustommodule", "1.1.0", "2.0.0",
        new com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo(),
        new UpgradeBar());

`UpgradeFoo` と `UpgradeBar`両方のアップグレード手順は、モジュールの `com.liferay.mycustommodule.upgrade.v2_0_0` パッケージにあります。 完全なクラス名 `com.liferay.mycustommodule.upgrade.v2_0_0.UpgradeFoo` は `UpgradeFoo` クラスに使用され、単純なクラス名 `UpgradeBar` は2番目のアップグレード手順に使用されます。

登録のアップグレード手順リストは、必要な数のアップグレード手順で構成できます。

**重要**：アップグレード手順でOSGiサービスを使用する場合、アップグレードはそのサービスの可用性を待機する必要があります。 そのサービスが利用可能になった後にのみアップグレードが実行されるように指定するには、そのサービスにOSGi参照を追加します。

たとえば、 [`WikiServiceUpgrade` 登録クラス](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/wiki/wiki-service/src/main/java/com/liferay/wiki/upgrade/WikiServiceUpgrade.java) は、 `SettingsFactory` クラスを参照します。 アップグレードステップクラス [`UpgradePortletSettings` アップグレードステップ](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/wiki/wiki-service/src/main/java/com/liferay/wiki/upgrade/v1_0_0/UpgradePortletSettings.java) はこれを使用します。 `WikiServiceUpgrade` クラスは次のとおりです。

    @Component(immediate = true, service = UpgradeStepRegistrator.class)
    public class WikiServiceUpgrade implements UpgradeStepRegistrator {
    
        @Override
        public void register(Registry registry) {
            registry.register(
                "com.liferay.wiki.service", "0.0.1", "0.0.2", new UpgradeSchema());
    
            registry.register(
                "com.liferay.wiki.service", "0.0.2", "0.0.3",
                new UpgradeKernelPackage(), new UpgradePortletId());
    
            registry.register(
                "com.liferay.wiki.service", "0.0.3", "1.0.0",
                new UpgradeCompanyId(), new UpgradeLastPublishDate(),
                new UpgradePortletPreferences(),
                new UpgradePortletSettings(_settingsFactory),
                new UpgradeWikiPageResource());
        }
    
        @Reference(unbind = "-")
        protected void setSettingsFactory(SettingsFactory settingsFactory) {
            _settingsFactory = settingsFactory;
        }
    
        private SettingsFactory _settingsFactory;
    
    }

上記のリストの3番目の登録では、 `UpgradePortletSettings` アップグレードステップは `SettingsFactory` サービスを使用します。 `setSettingsFactory` メソッドの `@Reference` アノテーションは、登録者クラスが依存しており、ランタイム環境で `SettingsFactory` サービスが利用可能になるのを待つ必要があることを宣言しています。 アノテーションの属性設定 `unbind = "-"` は、登録クラスにサービスをバインド解除するメソッドがないことを示します。

次に、サービスを利用可能にする前に、モジュールのアップグレードが実行されていることを確認する必要があります。

## アップグレード完了を待っています

データベースにアクセスするモジュールサービスを使用する前に、データベースを最新のデータベーススキーマにアップグレードする必要があります。

便宜上、Bndヘッダー `Liferay-Require-SchemaVersion` を最新のスキーマバージョンに構成するだけで、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder) サービス用にデータベースを確実にアップグレードできます。

他のすべてのサービスについては、開発者は、含まれるモジュールとその最新のスキーマバージョンを対象とする `@Reference` 注釈を指定することにより、データベースのアップグレードを保証できます。

ターゲットの必須属性は次のとおりです。

  - `release.bundle.symbolic.name`：モジュールのバンドルのシンボリック名
  - `release.schema.version`：モジュールの現在のスキーマバージョン

たとえば、 `com.liferay.comment.page.comments.web` モジュールの [`PageCommentsPortlet` クラス](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/comment/comment-page-comments-web/src/main/java/com/liferay/comment/page/comments/web/internal/portlet/PageCommentsPortlet.java) は、次の参照を定義することにより、スキーマバージョン `1.0.0` へのアップグレードを保証します。

    @Reference(
        target = "(&(release.bundle.symbolic.name=com.liferay.comment.page.comments.web)(release.schema.version=1.0.0))",
        unbind = "-"
    )
    protected void setRelease(Release release) {
    }

OSGiサービス間の依存関係により、アップグレード参照アノテーションが必要なサービスクラスの数を減らすことができます。 たとえば、依存関係が既にアップグレードを参照している場合、依存サービスにアップグレード参照を追加する必要はありません。

| **注**：クラス `VerifyProcess` を使用したデータ検証は非推奨です。 |検証は、スキーマバージョンに関連付けられる必要があります。 アップグレードプロセスが関連付けられています|スキーマバージョンはありますが、 `VerifyProcess` インスタンスはありません。

これで、すべてのモジュールのデータアップグレードを作成する方法がわかりました。 `bnd.bnd` ファイルで新しいデータスキーマバージョンを指定し、モジュールとスキーマバージョンへの参照を追加して、モジュールがService Builderを使用しない場合にアップグレードの実行を保証し、 `comliferay.portal.upgrade` モジュールへの依存関係を追加します。 プロセスの2番目の部分では、アップグレードステップクラスを作成して、データベーススキーマを更新し、アップグレードクラスを登録クラスに登録します。 これで完了です。

## 関連トピック

[以前のService Builderプラグインのアップグレードプロセス](/docs/7-1/tutorials/-/knowledge_base/t/upgrade-processes-for-former-service-builder-plugins)

[Liferay 7へのプラグインのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)

[構成可能なアプリケーション](/docs/7-1/tutorials/-/knowledge_base/t/configurable-applications)

[モジュールの新しいフレームワークへのデータアップグレードプロセスの移行](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
