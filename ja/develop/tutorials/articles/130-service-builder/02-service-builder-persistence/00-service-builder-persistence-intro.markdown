---
header-id: service-builder-persistence
---

# サービスビルダーの永続性

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

LiferayのService Builderは、インターフェイスとクラスの作成を自動化することにより、プロジェクトの永続化レイヤーを生成できます。 アプリケーションの永続化レイヤーは、構成されたエンティティによって表されるデータをデータベースに永続化します。 実際、ローカルサービス実装クラスは、アプリケーションのデータを取得および保存するために永続層を呼び出す役割を果たします。 したがって、時間のかかる独自の永続化レイヤーを作成する代わりに、Service Builderを使用してエンティティをすばやく定義し、レイヤーを即座に生成できます。

これらのチュートリアルの対象は次のとおりです。

  - [オブジェクトリレーショナルマップを定義し、そのマップから永続レイヤーを生成する](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder)
  - [Service Builderの実行](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)
  - [Service Builderが生成するローカルおよびリモートサービスの理解と使用](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-code-generated-by-service-builder)
  - [`ServiceContext` クラスを使用する](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)
  - [モデルヒントを使用したモデルエンティティのカスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing-model-entities-with-model-hints)
  - [SQLクエリ](/docs/7-1/tutorials/-/knowledge_base/t/custom-sql)
  - [HibernateのクライテリアAPIを使用する](/docs/7-1/tutorials/-/knowledge_base/t/dynamic-query)
  - [`service.properties`構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-service-properties)
  - [Service Builderを外部データソースに接続する](/docs/7-1/tutorials/-/knowledge_base/t/connecting-service-builder-to-external-databases)

オブジェクトリレーショナルマップの定義から始めます。
