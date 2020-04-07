---
header-id: reading-configuration-values-from-a-configuration-provider
---

# 構成プロバイダーからの構成値の読み取り

[TOC levels=1-4]

アプリケーションがデプロイされるとき、スコープ</a>応じて異なる構成

が必要になるのが一般的です。 つまり、仮想インスタンス（別名会社）、サイト（別名グループ）、またはポートレットインスタンスごとに、特定のアプリケーションに対して異なる構成を持つことを意味します。 標準のOSGi構成管理APIに基づいた構成プロバイダーAPIを使用して、これをわずかな労力で実現します。</p> 



## 構成プロバイダーの使用

構成プロバイダーを使用する場合、構成を直接受信する代わりに、構成プロバイダーにアクセスする必要があります

1.  `ConfigurationProvider` を受信して、構成を取得します

2.  `ConfigurationBeanDeclaration`登録します。

[スコープ構成](/docs/7-1/tutorials/-/knowledge_base/t/scoping-configurations) のチュートリアルでは、構成を `ConfigurationBeanDeclaration`に登録する方法を示します。

`ConfigurationBeanDeclaration`で登録した後、 `ConfigurationProvider` を使用してスコープ設定を取得する準備ができました。 参照を取得する方法は次のとおりです。

1.  コンポーネントのアプローチは次のとおりです。
   
        @Reference
        protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
            _configurationProvider = configurationProvider;
        }
       

2.  Service Builderサービスのアプローチは次のとおりです。
   
        @ServiceReference(type = ConfigurationProvider.class)
        protected ConfigurationProvider configurationProvider;
       

3.  Spring Beanの場合、Service Builderサービスと同じメカニズム（`@ServiceReference`）を使用できます。 上のドキュメントを参照してください [OSGiサービスでのSpring Beanを統合する方法](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-from-service-builder-code#referencing-osgi-services) 詳細については。

4.  それ以外の場合は、ユーティリティクラス `ConfigurationProviderUtil`から同じメソッドを呼び出します。 メソッド呼び出しの前にポータルが初期化されることが保証されているコンテキストでユーティリティメソッドを呼び出してください。 このクラスは、たとえば [スクリプトコンソール](/docs/7-0/user/-/knowledge_base/u/running-scripts-from-the-script-console)で役立ちます。 ユーティリティクラスを使用するメソッドの例を次に示します。 これは、実行中のポータルからのコンテンツのインポートおよびエクスポート中にのみ呼び出されるエクスポート/インポートサービスから取得されます。
   
   protected boolean isValidateLayoutReferences（）throws PortalException {long companyId = CompanyThreadLocal.getCompanyId（）;
   
        ExportImportServiceConfiguration exportImportServiceConfiguration =
            ConfigurationProviderUtil.getCompanyConfiguration(
                ExportImportServiceConfiguration.class, companyId);
       
        return exportImportServiceConfiguration.validateLayoutReferences();
       
   
   }

構成を取得するには、プロバイダーの次のいずれかの方法を使用します。

`getCompanyConfiguration（）` ：仮想インスタンスごとに異なる構成をサポートする場合に使用します。 この場合、構成は通常、管理者がコントロールパネル→構成→インスタンス設定から入力します。 このUIは（まだ）自動生成されないため、独自のフォームでUIを拡張する必要があります。

`getGroupConfiguration（）` ：サイトごとに（または必要に応じてページスコープごとに）異なる構成をサポートする場合に使用します。 通常、この構成は、管理者がサイト管理メニューからアクセスするアプリの[構成]メニューオプションを使用して指定します。 このUIは、ポートレット構成ビューとして開発されています。

`getPortletInstanceConfiguration（）` ：特定のポートレットインスタンスの構成を取得するために使用されます。 ほとんどの場合、これを直接使用するのではなく、以下に示すように、代わりに `PortletDisplay` 簡易メソッドを使用する必要があります。

`getSystemConfiguration` ：システムスコープの構成を取得するために使用されます。 これらの設定は、システム設定アプリケーションまたはOSGi構成ファイルを使用して管理者が指定します。

Liferayのソースコードの実際の例を次に示します。

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);
    
    MentionsGroupServiceConfiguration configuration =
      _configurationProvider.getCompanyConfiguration(
         MentionsGroupServiceConfiguration.class, entry.getCompanyId());
    

次に、JSPからポートレットインスタンス構成にアクセスするための気の利いた方法を学びます。



## PortletDisplayを介したポートレットインスタンス構成へのアクセス

多くの場合、JSPまたはOSGiコンポーネントではないJavaクラスからポートレットインスタンス設定にアクセスする必要があります。 これらの場合の設定を読み取るために、メソッドが `PortletDisplay`に追加されました`これは要求オブジェクトとして使用可能です。 使用方法の例を次に示します。</p>

<pre><code>RSSPortletInstanceConfiguration rssPortletInstanceConfiguration =
    portletDisplay.getPortletInstanceConfiguration(
        RSSPortletInstanceConfiguration.class);
`</pre> 

ご覧のとおり、値を見つける方法を知っており、構成クラスを渡すだけで、それらを含む型付きBeanを返します。
