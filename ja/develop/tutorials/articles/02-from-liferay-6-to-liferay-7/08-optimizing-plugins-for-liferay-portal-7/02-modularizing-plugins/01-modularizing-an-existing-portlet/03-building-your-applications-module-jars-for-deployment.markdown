---
header-id: building-your-module-jars-for-deployment
---

# デプロイ向けアプリケーションのモジュールJARの構築

[TOC levels=1-4]

次に、モジュールを構築してデプロイします。 プロジェクトを構築するには、アプリケーションプロジェクトのルートフォルダから`gradlew build`を実行します。

ここで、すべてのモジュールの`/build/libs`フォルダを確認します。 それぞれに新しく生成されたJARファイルがありますが、これらがデプロイするファイルです。 各モジュールのルートフォルダから`blade deploy`を実行することにより、各JARをデプロイできます。

| **注: ** モジュールを順不同でデプロイすると、エラーメッセージ | が表示される場合があります。 例として、最初にWebクライアントモジュールをデプロイしようとすると、| それがサービスの実装とサービスの | APIモジュールに依存している場合にエラーが発生します。 各モジュールの依存関係が満たされると、Liferayにデプロイされます。 | [Felix Gogo Shellコマンド](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell) | を使うとモジュールの依存関係を確認できます。

モジュールを正常にデプロイしたら、Gogo shellから以下のようにモジュールをリストできます。

![図1：Gogo shell promptでLiferayインスタンスに接続したら、*lb*を実行して、変換された新しいモジュールを一覧表示します。](../../../../../images/deploy-converted-modules.png)

これらのチュートリアルでは、従来のアプリケーションを@product-ver@スタイルのアプリケーションのモジュール形式に変換する方法を説明しました。 具体的には、以下の方法についてです。

  - アプリケーションのポートレットクラスとUIを保持するWebクライアント(`*-web`) モジュールの作成
  - サービス実装モジュール (`*-service`) およびサービスAPIモジュール(`*-api`) の作成
  - Service Builderを実行した、アプリケーションのサービスおよびAPIモジュールのコードの生成
  - 相互の依存関係を宣言して、モジュールを相互に接続
  - モジュールを構築し、@product@インストールにデプロイ

**関連トピック**

[ポートレット](/docs/7-1/tutorials/-/knowledge_base/t/portlets)

[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)
