---
header-id: upgrading-plugins-to-liferay-7
---

# コードを@product-ver@にアップグレードする

[TOC levels=1-4]

@product-ver@にアップグレードするには、インストールとコード（カスタムアプリケーション）を新しいバージョンに移行する必要があります。 このセクションでは、コードをアップグレードする方法を学習します。

アップグレードプロセスの最初のステップは、既存のプラグインのコードを@product-ver@のAPIに適合させることです。 LiferayのUpgrade Plannerは、このステップを今まで以上に簡単にします。 コードに影響するLiferay APIの変更を特定し、APIの変更について説明するほか、解決策のステップを示します。 また、ツールは可能な場合に自動修正を提供します。

@product@が提供する新しい機能を活用するために、既存のプラグインをすぐに最適化したいと思うかもしれませんが、そうすべきではありません。 これらのチュートリアルに従ってプラグインをアップグレードすることをお勧めします。 この方法で、@product@で可能な限り高速にプラグインを実行させると同時に、後で実装可能な最適化のためのプラグインを準備します。

これらのチュートリアルでは、[Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)の使用を前提としています。 このセクションを実行するには、プランナーをインストールし、アップグレード手順を実行してください。

便宜上、このチュートリアルのセクションでは、コードをマニュアルでアップグレードすることを選択した人向けのドキュメンテーションと手順の概要も参照します。

コードのアップグレード手順は次のとおりです。

1.  [開発環境をアップグレードする](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment)

    使用可能なすべての機能を活用するには、以前のプロジェクト環境をLiferay Workspaceの最新バージョンにアップグレードする必要があります。

    1.  [Liferay Workspaceをセットアップする](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#setting-up-liferay-workspace)

        Liferay Workspaceは、Liferayプロジェクトを保持および管理するために構築された、生成された環境です。 ワークスペースを作成/インポートして開始します。

        1.  [新しいLiferay Workspaceを作成する](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#creating-new-liferay-workspace)

            既存の7.x Liferay Workspaceがない場合は、作成する必要があります。 既存のワークスペースがある場合は、次の手順にスキップします。

        2.  [既存のLiferay Workspaceをインポートする](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#importing-existing-liferay-workspace)

            既存のLiferay Workspaceをインポートします。 持っていない場合は、前の手順に戻ってください。

    2.  [Liferay Workspaceの設定方法](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-liferay-workspace-settings)

        アップグレードするワークスペースの設定で@product@バージョンを設定します。

        1.  [バンドルURLを構成する](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-bundle-url)

            @product@バンドルのダウンロード元のバンドルURLを設定します。

        2.  [ターゲットプラットフォームバージョンの設定](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-target-platform-version)

            ターゲットプラットフォームバージョンを設定します。これにより、@product@リリースに関連付けられた特定のアーティファクトが提供されます。

        3.  [サーバーバンドルの初期化](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#initializing-server-bundle)

            アップグレードする@product@バンドルをダウンロードします。

2.  [プラグインSDKプロジェクトの移行](/docs/7-1/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace)

    プラグインSDKプロジェクトをワークスペースにコピーし、Gradle/Mavenプロジェクトに変換します。

    1.  [既存のプラグインSDKプロジェクトをインポートする](/docs/7-1/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace#importing-existing-plugins-sdk-projects)

        既存のプラグインSDKプロジェクトをインポートします。

    2.  [既存のプラグインをWorkspaceに移行する](/docs/7-1/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace#migrating-existing-plugins-to-workspace)

        既存のプラグインをワークスペースに移行します。 これには、プラグインをワークスペースに移動し、ワークスペースのビルド環境に変換することが含まれます。

3.  [ビルドの依存関係をアップグレードする](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies)

    最も効率的なコードアップグレードエクスペリエンスのために、ワークスペースのビルド環境を最適化します。

    1.  [リポジトリURLのアップデート](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies#updating-the-repository-url)

        リポジトリURLを、Liferayの頻繁にアップデートされるCDNリポジトリにアップデートします。

    2.  [Workspaceプラグインバージョンのアップデート](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies#updating-the-workspace-plugin-version)

        Liferay Workspaceの最新機能を活用するには、Workspaceプラグインのバージョンをアップデートしてください。

    3.  [依存バージョンを削除する](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies#removing-your-projects-build-dependency-versions)

        ターゲットプラットフォームを活用しているため、プロジェクトの依存関係バージョンを削除します。

4.  [アップグレードの問題を修復](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems)

    プロジェクトの依存関係と重大な変更を処理する一般的なアップグレードの問題を修復します。

    1.  [自動修正アップグレードの問題](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#auto-correcting-upgrade-problems)

        簡単なアップグレードの問題を自動修正します。

    2.  [アップグレードの問題を見つける](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#finding-upgrade-problems)

        アップグレードの問題を見つけます。 これらは自動修正できない問題です。重大な変更に関するドキュメンテーションに従って、マニュアルでアップデートできます。

    3.  [アップグレードの問題を解決する](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#resolving-upgrade-problems)

        アップグレードの問題を解決した後、解決済みとしてマークします。

    4.  [問題マーカーを削除](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#removing-problem-markers)

        アップグレードの問題を修正したら、問題マーカーを削除します。

    5.  [プラグインの依存関係の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

    6.  [重大な変更の解決](/docs/7-1/tutorials/-/knowledge_base/t/resolving-breaking-changes)

5.  [カスタマイゼーションプラグインのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-hook-plugins)

    カスタマイゼーションプラグインをアップグレードして、@product-ver@にデプロイできるようにします。

    1.  [カスタマイゼーションモジュールのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-customization-modules)

    2.  [コアJSPフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-jsp-hooks)

    3.  [ポートレットJSPフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-app-jsp-hook-plugins)

    4.  [サービスラッパーフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-wrappers)

    5.  [コア言語キーフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)

    6.  [ポートレット言語キーフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlet-language-key-hooks)

    7.  [モデルリスナーフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-model-listener-hooks)

    8.  [イベント操作フックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portal-property-and-event-action-hooks)

    9.  [サーブレットフィルターフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-servlet-filter-hooks)

    10. [ポータルプロパティフックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portal-property-and-event-action-hooks)

    11. [Struts操作フックのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/converting-strutsactionwrappers-to-mvccommands)

6.  [テーマのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-themes-intro)

    テーマをアップグレードして、@product-ver@にデプロイできるようにします。

7.  [レイアウトテンプレートのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-layout-templates)

    レイアウトテンプレートをアップグレードして、@product-ver@にデプロイできるようにします。

8.  [Frameworks & 機能のアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-frameworks-and-features)

    1.  [JNDIデータソース使用量をアップグレードする](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-jndi-data-source-usage)

        @product@のクラスローダーを使用して、アプリケーションサーバーのJNDI APIにアクセスします。

    2.  [Service Builderサービス呼び出しのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-builder-service-invocation)

        WARに残っているService Builderロジックの場合、サービスを呼び出すサービストラッカーを実装する必要があります。 OSGiモジュールに分割されたロジックの場合、宣言型サービスを活用できます。

    3.  [Service Builderのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-builder)

        Service Builder固有の変更に対応するようにアプリケーションを調整します。

    4.  [速度テンプレートの移行](/docs/7-1/tutorials/-/knowledge_base/t/migrating-off-of-velocity-templates)

        Velocityテンプレート使用量は、@product-ver@で非推奨になりました。 テンプレートをFreeMarkerに変換する必要があります。

9.  [ポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlet-plugins)

    ポートレットをアップグレードして、@product-ver@にデプロイできるようにします。

    1.  [汎用ポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-genericportlet)

    2.  [Liferay MVCポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-liferay-mvc-portlet)

    3.  [JSFポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-liferay-jsf-portlet)

    4.  [サーブレットベースのポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-servlet-based-portlet)

    5.  [Spring MVCポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-spring-mvc-portlet)

    6.  [Struts 1ポートレットのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-struts-1-portlet)

10. [Webプラグインのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-web-plugins)

    以前のプラグインSDKの`webs`フォルダにすでに保存されたWebプラグインをアップグレードします。

11. [Extプラグインのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-ext-plugins)

    Extプラグインをアップグレードする代わりに、拡張ポイントを活用してみてください。 Extプラグインが必要な場合は、使用していた以前のLiferay Portalインスタンスと@product-ver@の間のすべての変更を確認し、Extプラグインをマニュアルで変更して、変更を@product@にマージする必要があります。

コードのアップグレード手順が完了すると、カスタムアプリケーションは@product-ver@\と互換性を持つようになります。
