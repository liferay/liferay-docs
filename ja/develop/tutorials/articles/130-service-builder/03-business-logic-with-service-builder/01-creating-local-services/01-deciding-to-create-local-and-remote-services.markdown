---
header-id: deciding-to-create-local-and-remote-services
---

# ローカルサービスとリモートサービスの作成を決定する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[オブジェクトモデル](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder) を定義するには、ローカルサービスインターフェイスとリモートサービスインターフェイスのどちらを生成するかを選択する必要があります。 ローカルサービスは、デプロイ先のLiferayサーバーからのみ呼び出すことができます。 Liferayサーバーの外部のクライアントは、リモートサービスにアクセスできます。 ローカルまたはリモートサービスを実装する前に、ここで説明するベストプラクティスを検討してください。

1.  リモートサービスを使用する場合は、ローカルサービスも有効にします。

2.  `* LocalServiceImpl`でビジネスロジックを実装します。

3.  [ `* ServiceImpl`対応するリモートサービスメソッド](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services) 作成します。

4.  リモートサービスメソッドを使用して、ローカルサービスを呼び出し、アクセス許可チェックで呼び出しをラップします。

5.  アプリケーションでは、リモートサービスのみを呼び出します。 これにより、サービスメソッドが保護され、権限コードを複製する必要がなくなります。

`service.xml` ファイルでローカルまたはリモートサービスをオンにしている場合は、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) 再度実行して、サービスインターフェイスを生成してください。
