---
header-id: upgrading-portlet-plugins
---

# ポートレットプラグインのアップグレード

[TOC levels=1-4]

Liferay Portal 6用に開発されたすべてのタイプのポートレットプラグインは、@product-ver@にアップグレードおよびデプロイできます。

ほとんどのポートレットのアップグレードには、次の手順が含まれます。

1.  @product-ver@のAPIにコードを適合させる
2.  依存関係を解決する

LiferayのUpgrade Plannerは、コードを@product-ver@のAPIに適合させるのに役立ちます。 また、ポートレットの依存関係の解決は簡単です。 ほとんどの場合、上記の手順を完了した後、ポートレットを@product@にデプロイできます。

ポートレットのアップグレードについてのチュートリアルでは、次の一般的なポートレットをアップグレードする方法を示します。

  - [GenericPortlet](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-genericportlet)
  - [Liferay MVCポートレット](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-liferay-mvc-portlet)
  - [Service Builderを使用するポートレット](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlets-that-use-service-builder)
  - [Liferay JSFポートレット](develop/tutorials/-/knowledge_base/7-1/upgrading-a-liferay-jsf-portlet)
  - [サーブレットベースのポートレット](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-servlet-based-portlet)
  - [Strutsポートレット](/docs/7-0/tutorials/-/knowledge_base/t/upgrading-a-struts-portlet)
  - [Spring MVCポートレット](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-spring-mvc-portlet)

チュートリアルではサンプルポートレットのアップグレード前後から、サンプルポートレットソースコードを提供します。 各チュートリアルの手順は、サンプルポートレットに適用されました。 ポートレットをアップグレードする時には、サンプルコードを参照できます。

それでは、@product-ver@\でポートレットを実行してみましょう。
