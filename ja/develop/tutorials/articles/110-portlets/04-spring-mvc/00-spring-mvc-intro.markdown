---
header-id: spring-mvc
---

# Spring MVC

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferayは、オープンプラットフォームのエコシステムにおけるオープンプラットフォームです。 Liferayが独自の [MVCフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet) を持っているからといって、それを使用する必要があるわけではありません。 他の開発プロジェクトのツールと経験をLiferayに持ち込むことは完全に有効です。 実際、私たちはあなたに期待しています。 Liferayの開発プラットフォームは標準ベースであるため、ほぼすべてのタイプのアプリケーションに最適です。

既にSpring MVCのウィザードを使用している場合は、Liferayの [`MVCPortlet` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html) の代わりに、何の制限もなくウィザードを使用できます。 Spring MVCはアプリケーションのWebアプリケーションレイヤーのみを置き換えるので、サービスレイヤーに [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder) を使用できます。

それでは、LiferayにSpring MVCアプリケーションを実装するには何が必要ですか？

1.  Spring MVCを使用して通常どおりに開発します。

2.  [Liferay用にアプリケーションを構成します。](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-spring-mvc-portlet)

3.  [Liferayに展開します。](/docs/7-1/tutorials/-/knowledge_base/t/deploying-a-spring-mvc-portlet)

すでにアプリを持っているので、設定から始めます。
