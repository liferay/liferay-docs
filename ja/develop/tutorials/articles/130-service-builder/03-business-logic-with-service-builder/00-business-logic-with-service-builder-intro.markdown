---
header-id: business-logic-with-service-builder
---

# Service Builderを使用したビジネスロジック

[TOC levels=1-4]

アプリケーションのエンティティ</a> と

を [定義したら、Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder) を実行してサービスレイヤーと永続レイヤーを生成したら、ビジネスロジックの追加を開始できます。 Service Builderによって生成される各エンティティには、モデル実装、ローカルサービス実装、およびオプションでリモートサービス実装クラスが含まれます。 アプリケーションのビジネスロジックは、これらのクラスに実装できます。 生成されたサービスレイヤーにはCRUD操作のデフォルトメソッドが含まれていますが、多くの場合、追加のメソッドを実装する必要があります。 ビジネスロジックを追加したら、Service Builderを再度実行すると、アプリケーションのインターフェイスが再生成され、新しいロジックを呼び出し可能にします。</p> 

チュートリアルのこのセクションでは、アプリケーションのローカルサービスの作成と呼び出し、Liferayのサービスの検索と呼び出し、Liferayサービスのカスタマイズについて学習します。
