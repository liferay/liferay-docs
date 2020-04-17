---
header-id: business-logic-with-service-builder
---

# Service Builderを使用したビジネスロジック

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリケーションのエンティティ</a> と

を [定義したら、Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder) を実行してサービスレイヤーと永続レイヤーを生成したら、ビジネスロジックの追加を開始できます。 Service Builderによって生成される各エンティティには、モデル実装、ローカルサービス実装、およびオプションでリモートサービス実装クラスが含まれます。 アプリケーションのビジネスロジックは、これらのクラスに実装できます。 生成されたサービスレイヤーにはCRUD操作のデフォルトメソッドが含まれていますが、多くの場合、追加のメソッドを実装する必要があります。 ビジネスロジックを追加したら、Service Builderを再度実行すると、アプリケーションのインターフェイスが再生成され、新しいロジックを呼び出し可能にします。</p> 

チュートリアルのこのセクションでは、アプリケーションのローカルサービスの作成と呼び出し、Liferayのサービスの検索と呼び出し、Liferayサービスのカスタマイズについて学習します。
