---
header-id: dynamic-query
---

# 動的クエリ

[TOC levels=1-4]

Service BuilderでカスタムSQLクエリを使用してデータベースからデータを取得できますが、実行時にクエリを動的に作成する方が便利な場合があります。 これは、HibernateのCriteria APIをラップするLiferayのDynamic Query APIを使用して実行できます。 Dynamic Query APIを使用すると、SQLを記述せずにクエリを作成できます。 テーブルと列ではなく、オブジェクトとメンバー変数の観点から考えるのに役立ちます。 複雑なクエリは、同等のカスタムSQL（またはHQL）クエリよりも理解および保守が非常に簡単です。 技術的には動的クエリを構築するためにSQLを知る必要はありませんが、効率的なクエリを構築するように注意する必要があります。 HibernateのCriteria APIについては、 [Hibernateのマニュアル](http://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/chapters/query/criteria/Criteria.html)を参照してください。 このチュートリアルでは、Service BuilderとDynamic Query APIを使用してLiferayアプリケーションのカスタムファインダーを作成する方法を示します。

LiferayのDynamic Query APIを使用するには、モデルエンティティのファインダー実装を作成する必要があります。 あなたはできる [でモデルのエンティティ定義 `service.xmlを`](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder) と [の実行サービスビルダ](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) アプリケーションのモデル、永続性、およびサービス層を生成します。 このチュートリアルでは、サービスモジュール、APIモジュール、およびWebモジュールで構成されるLiferayアプリケーションを作成することを想定しています。 Service Builderを使用してアプリケーションのモデル、永続性、およびサービスレイヤーを生成したら、次の手順に従ってDynamic Query APIを使用してカスタムファインダーを呼び出します。

1.  [カスタムfinderメソッドを定義します](/docs/7-1/tutorials/-/knowledge_base/t/defining-a-custom-finder-method)。

2.  [動的クエリAPI](/docs/7-1/tutorials/-/knowledge_base/t/implementing-a-custom-finder-method-using-dynamic-query)を使用してファインダーを実装します。

3.  [finderメソッド](/docs/7-1/tutorials/-/knowledge_base/t/accessing-your-custom-finder-method-from-the-service-layer)を呼び出す `* LocalServiceImpl` クラスにメソッドを追加します。

これらの手順を実行すると、サービスメソッドとしてカスタムファインダーにアクセスできます。 注： `* FinderImpl` クラスでは、複数のまたはオーバーロードされた `findBy *` finderメソッドを作成できます。 次に、これらの手順をさらに詳しく調べます。
