---
header-id: managing-liferay-dxp-with-liferay-connected-services
---

# Liferay Connected Servicesを使用したLiferay DXPの管理

Liferay Connected Services（LCS）は、@product@インスタンスを管理および監視するためのツールとサービスのセットです。LCSは、フィックスパックのインストール、インスタンスのパフォーマンスのモニター、インスタンスの有効化、およびサブスクリプションの管理をサポートします。@product@が大豪邸だとしたら、LCSはその豪邸の執事のよ​​うなものです。さらに良いことに、LCSの機能は、インスタンスが単一の個別のサーバー上にあるか、クラスタ全体に分散されているかに関係なく機能します。一度に複数の大豪邸に仕える執事がいるようなものです！LCSの詳細については、 こちらの公式の製品ページを参照してください：[official product page](http://www.liferay.com/products/liferay-connected-services)

+$$$

**注:**Elasticサブスクリプションを有効化するにはLCSを使用する必要があります。そうでなければ、アクティベーションにLCSを使う必要はありません。代わりにLiferayサポートからXMLアクティベーションキーをリクエストすることができます。

$$$

先に進む前に、このガイドで使用されているいくつかの重要な用語を紹介しておきます。

**プロジェクト:** 会社または組織に属するユーザーのグループのことです。たとえば、プロジェクトには、プロジェクトチームまたは事業単位のすべてのユーザーで構成することも、会社全体を含めることもできます。

**環境**: サーバーの物理的なクラスター、またはサーバーの仮想および論理的な集合体を表します。

**サーバー**: 具体的な@product@インスタンスについて説明します。サーバーはスタンドアロンサーバーまたはクラスタノードのどちらでも機能します。


このガイドでは、次のトピックについて説明します。



- [まず初めに](/discover/deployment/-/knowledge_base/7-1/getting-started-with-lcs)
- [LCSの事前設定](/discover/deployment/-/knowledge_base/7-1/lcs-preconfiguration)
- [@product@ ServerをLCSに登録する](/discover/deployment/-/knowledge_base/7-1/activating-your-liferay-dxp-server-with-lcs)
- [LCSを使う](/discover/deployment/-/knowledge_base/7-1/using-lcs)
- [LCS接続のトラブルシューティング](/discover/deployment/-/knowledge_base/7-1/troubleshooting-your-lcs-connection)

では、@product@でLCSを使用するために必要な設定手順から始めましょう。
