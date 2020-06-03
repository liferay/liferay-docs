---
header-id: upgrade-processes-for-former-service-builder-plugins
---

# 以前のService Builderプラグインのアップグレードプロセス

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Service Builderサービスを実装する従来のLiferayプラグインアプリケーションをモジュール化した場合、新しいモジュール式アプリケーションは@product@の `Release_` テーブルに登録する必要があります。 これは、アプリの以前のバージョンのリリースレコードが既に存在するかどうかに関係なく必要です。 Bundle Activatorは、変換されたアプリケーションの最初のモジュラーバージョンのリリースレコードを追加するための推奨される方法です。 ここでは、Bundle Activatorの例を参照し、アプリケーションのBundle Activatorを作成してアクティブにする方法を学習します。

**重要**：このチュートリアルは、Service Builderを使用し、従来のLiferayプラグインアプリケーションからモジュール化されたモジュラーアプリケーションにのみ適用されます。 アプリケーションでService Builderを使用していない場合、または従来のLiferayプラグインアプリケーション（WARアプリケーション）であったことがない場合は、適用されません。

Bundle Activatorクラスコードは高密度ですが、簡単です。 Bundle Activatorの例を参照すると役立ちます。 LiferayナレッジベースアプリケーションのBundle Activatorは次のとおりです。

    public class KnowledgeBaseServiceBundleActivator implements BundleActivator {
    
        @Override
        public void start(BundleContext bundleContext) throws Exception {
            Filter filter = bundleContext.createFilter(
                StringBundler.concat(
                    "(&(objectClass=", ModuleServiceLifecycle.class.getName(), ")",
                    ModuleServiceLifecycle.DATABASE_INITIALIZED, ")"));
    
            _serviceTracker = new ServiceTracker<Object, Object>(
                bundleContext, filter, null) {
    
                @Override
                public Object addingService(
                    ServiceReference<Object> serviceReference) {
    
                    try {
                        BaseUpgradeServiceModuleRelease
                            upgradeServiceModuleRelease =
                                new BaseUpgradeServiceModuleRelease() {
    
                                    @Override
                                    protected String getNamespace() {
                                        return "KB";
                                    }
    
                                    @Override
                                    protected String getNewBundleSymbolicName() {
                                        return "com.liferay.knowledge.base.service";
                                    }
    
                                    @Override
                                    protected String getOldBundleSymbolicName() {
                                        return "knowledge-base-portlet";
                                    }
    
                                };
    
                        upgradeServiceModuleRelease.upgrade();
    
                        return null;
                    }
                    catch (UpgradeException ue) {
                        throw new RuntimeException(ue);
                    }
                }
    
            };
    
            _serviceTracker.open();
        }
    
        @Override
        public void stop(BundleContext bundleContext) throws Exception {
            _serviceTracker.close();
        }
    
        private ServiceTracker<Object, Object> _serviceTracker;
    
    }

次の手順では、上記の例のように、バンドルアクティベーターを作成する方法を説明します。

1.  インターフェース `org.osgi.framework.BundleActivator`を実装するクラスを作成します。

2.  サービストラッカーフィールドを追加します。

    `private ServiceTracker<Object, Object> _serviceTracker;`

3.  BundleActivatorの `stop` メソッドをオーバーライドして、サービストラッカーを閉じます。
   
        @Override
        public void stop(BundleContext bundleContext) throws Exception {
            _serviceTracker.close();
        }

4.  BundleActivatorの `start` メソッドをオーバーライドして、アプリのデータベース初期化イベントをリッスンするフィルターを作成し、そのフィルターを使用するようにサービストラッカーを初期化するサービストラッカーをインスタンス化します。 次の手順で、サービストラッカーの初期化コードを追加します。 `start` メソッドの最後に、サービストラッカーを開きます。
   
        @Override
        public void start(BundleContext bundleContext) throws Exception {
            Filter filter = bundleContext.createFilter(
                StringBundler.concat(
                    "(&(objectClass=", ModuleServiceLifecycle.class.getName(), ")",
                    ModuleServiceLifecycle.DATABASE_INITIALIZED, ")"));
       
            _serviceTracker = new ServiceTracker<Object, Object>(
                bundleContext, filter, null) {
                // See the next step for this code ...
            };
       
            _serviceTracker.open();
        }

5.  サービストラッカーの初期化ブロック `{//このコードの次のステップを参照してください... }` 前のステップから、追加 `addingService` インスタンスメソッド `BaseUpgradeServiceModuleRelease` アプリを記述するために。次の例 `BaseUpgradeServiceModuleRelease` インスタンスは、Liferayのナレッジベースアプリについて説明しています。
   
        @Override
        public Object addingService(
            ServiceReference<Object> serviceReference) {
       
            try {
                BaseUpgradeServiceModuleRelease
                        upgradeServiceModuleRelease =
                    new BaseUpgradeServiceModuleRelease() {
       
                        @Override
                        protected String getNamespace() {
                            return "KB";
                        }
       
                        @Override
                        protected String getNewBundleSymbolicName() {
                            return "com.liferay.knowledge.base.service";
                        }
       
                        @Override
                        protected String getOldBundleSymbolicName() {
                            return "knowledge-base-portlet";
                        }
       
                    };
       
                upgradeServiceModuleRelease.upgrade();
       
                return null;
            }
            catch (UpgradeException ue) {
                throw new RuntimeException(ue);
            }
        }

    `BaseUpgradeServiceModuleRelease` は、次のメソッドを実装します。

      - `getNamespace`：以前のプラグインの `service.xml` ファイルで指定された名前空間値を返します。 この値は、プラグインの `ServiceComponent` テーブルレコードの `buildNamespace` フィールドにもあります。
      - `getOldBundleSymbolicName`：以前のプラグインの名前を返します。
      - `getNewBundleSymbolicName`：モジュールのシンボル名を返します。 モジュールの `bnd.bnd` ファイルでは、 `Bundle-SymbolicName` 値です。
      - `upgrade`：アプリのアップグレードプロセスを呼び出します。

6.  モジュールの `bnd.bnd` ファイルで、作成したBundle Activatorクラスを参照します。 以下は、サンプルのBundle Activatorリファレンスです。

    `Bundle-Activator: com.liferay.knowledge.base.internal.activator.KnowledgeBaseServiceBundleActivator`

Bundle Activatorは、次の値のいずれかを使用して、アプリケーションの `Release_` テーブルレコードの `schemaVersion` フィールドを初期化します。

  - 現在の `buildNumber`：以前のプラグインの既存の `Release_` テーブルレコードがある場合。
  - `0.0.1`：既存の `Release_` テーブルレコードがない場合。

サービスモジュールのデータアップグレードプロセスを設定しました。

## 関連トピック

[モジュールのデータアップグレードプロセスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app)

[Liferay 7へのプラグインのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-plugins-to-liferay-7)
