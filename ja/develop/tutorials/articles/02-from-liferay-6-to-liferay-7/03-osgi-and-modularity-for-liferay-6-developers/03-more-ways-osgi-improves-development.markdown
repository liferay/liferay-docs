---
header-id: more-ways-osgi-improves-development-on-liferay
---

# OSGiがLiferayの開発を改善するその他の方法

[TOC levels=1-4]

ここでは、前述した[開発者エクスペリエンス目標](/docs/7-1/tutorials/-/knowledge_base/t/osgi-and-modularity-for-liferay-6-developers)をOSGiが達成する、より多くの方法を説明します。 これらのトピックは、新規およびベテランのLiferay開発者の両方に当てはまるので、より理解を深められるはずです。 それぞれを読んだ後、ここに戻って次のリストされたチュートリアルを続行するか、またはすべてを読み終えたらこのラーニングパスを続行してください。

1.  [依存関係の活用](/docs/7-1/tutorials/-/knowledge_base/t/leveraging-dependencies)：@product-ver@では、モジュール間の依存関係を宣言することも、モジュールを組み合わせてアプリケーションを作成することもできます。 依存関係を活用することには大きな利点があるので、そのための完全なチュートリアルが用意されています。

2.  [OSGiサービスと依存性の注入](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)：OSGiには、OSGiサービス（またはマイクロサービス）と呼ばれる強力な概念が含まれています。 OSGiの宣言サービス標準は、動的環境に依存関係を注入するための明確な方法を提供します。 システムの実行中に変更が行われることを除いては、Spring DIに似ています。 また、@product-ver@が広範囲に活用する簡潔な拡張モデルも提供します。

3.  [動的デプロイメント](/docs/7-1/tutorials/-/knowledge_base/t/dynamic-deployment)：モジュールのデプロイメントは、@product-ver@によって管理されます（アプリケーションサーバーではありません）。 このセクションでは、動的デプロイを使用して制御と効率を向上させる方法を示します。

Liferayの@product-ver@向けの開発者ツールは、モジュール開発を補完します。 従来のプラグイン開発をサポートし、アプリケーションのモジュールへの移動を容易にします。 開発テーマとMavenおよびGradleビルドシステムの使用には改善があるほか、既存のコードを@product-ver@のAPIに適合させ、プラグインのアップグレードプロセスの多くを自動化するUpgrade Plannerツールもあります。 開発者ツールの改善点は次のとおりです。
