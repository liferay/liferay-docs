---
header-id: setting-up-marketplace-and-portal-security
---

# マーケットプレイスの設定

[TOC levels=1-4]

[Liferay Marketplace](https://www.liferay.com/marketplace)は、Liferayアプリケーションを保管するだけのものではありません。内部では、ストアと@product@の両方のアプリケーションのデプロイメント機能を提供しています。このため、Marketplace自体が実行および設定できるようにする必要があります。

Marketplaceが正常に機能するようにするための回避策は次のとおりです。

- [サーバーがファイアウォールで保護されていて、インターネットにアクセスできない](#server-is-firewalled-without-access-to-the-internet)
- [制限付きデータベースアクセス](#limited-database-access)

最初にファイアウォールのシナリオについて説明します。

## サーバーがファイアウォールで保護されていて、インターネットにアクセスできない

ご利用のサーバーがインターネットへのアクセスを妨ぐファイアウォールの内側にあるかもしれません。または、セキュリティポリシーによって、インターネットからの直接ダウンロードとインストールが許可されていない可能性があります。このような場合、2つの選択肢があります。

1. インターネット対応のコンピュータから[Marketplaceプラグイン](https://www.liferay.com/marketplace/download)をダウンロードし ます。
次に、ダウンロードした`.lpkg`ファイルを[Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)の`deploy`フォルダにドロップして、@product@が自動的にデプロイするようにします 。

2. あるいは、`.lpkg`ファイルをダウンロードしたら、[App Manager](/docs/7-1/user/-/knowledge_base/u/managing-and-configuring-apps)を使用してそれをデプロイします 。

次に、データベースアクセス制限を回避する方法を学びます。

## 制限付きデータベースアクセス

本番環境の中には、@product@、アプリケーション、モジュール、およびプラグインがテーブルを管理するために必要なデータベース権限を持っていないものがあります。これらの場合：

1. @product@ データベースユーザーに、データベースに対する一時的な完全権限を付与します。

2. @product@をインストールして起動し、データベースにデータを入力します。

3. データベースが作成されたら、@product@ データベースユーザーからテーブルの作成と削除をする権限を削除します。

詳細については、[データベースサーバーを選んで新規のデータベースを作成する](/docs/7-1/deploy/-/knowledge_base/d/preparing-for-install#step-1-choose-a-database-server-and-create-a-new-database)を参照してください。Marketplaceアプリケーションだけでなく、@product@アプリケーションの多くは、デプロイ時に新しいテーブルを必要とします。データベースアクセスが制限されている場合は、新しいアプリをデプロイするたびに上記の手順を繰り返す必要があります。

これで、Marketplaceと追加のアプリケーションをインストールするために@product@の用意ができました。

