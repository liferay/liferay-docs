---
header-id: deciding-to-create-local-and-remote-services
---

# ローカルサービスとリモートサービスの作成を決定する

[TOC levels=1-4]

[オブジェクトモデル](/docs/7-1/tutorials/-/knowledge_base/t/defining-an-object-relational-map-with-service-builder) を定義するには、ローカルサービスインターフェイスとリモートサービスインターフェイスのどちらを生成するかを選択する必要があります。 ローカルサービスは、デプロイ先のLiferayサーバーからのみ呼び出すことができます。 Liferayサーバーの外部のクライアントは、リモートサービスにアクセスできます。 ローカルまたはリモートサービスを実装する前に、ここで説明するベストプラクティスを検討してください。

1.  リモートサービスを使用する場合は、ローカルサービスも有効にします。

2.  `* LocalServiceImpl`でビジネスロジックを実装します。

3.  [ `* ServiceImpl`対応するリモートサービスメソッド](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services) 作成します。

4.  リモートサービスメソッドを使用して、ローカルサービスを呼び出し、アクセス許可チェックで呼び出しをラップします。

5.  アプリケーションでは、リモートサービスのみを呼び出します。 これにより、サービスメソッドが保護され、権限コードを複製する必要がなくなります。

`service.xml` ファイルでローカルまたはリモートサービスをオンにしている場合は、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder) 再度実行して、サービスインターフェイスを生成してください。
