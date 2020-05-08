---
header-id: factory-configurations
---

# 工場構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

複数のエントリをサポートする構成は、 *ファクトリ構成*と呼ばれます。
| **工場構成の例：** | JAX-WSおよびJAX-RS Web |サービス<!--(/docs/7-1/tutorials/-/knowledge_base/t/jax-ws-and-jax-rs)--> ある|サポートされています。 これらのサービスは、CXFエンドポイントを使用する必要があります<!--
| (/docs/7-1/tutorials/-/knowledge_base/t/jax-ws-and-jax-rs#cxf-endpoints)-->、|これは、Webサービスがデプロイおよびアクセスされるコンテキストパスです。 |エンドポイントは、CXFに移動することにより、出荷時の構成で作成できます。システム設定のエンドポイントエントリ（システム設定→プラットフォーム→Web | API→CXFエンドポイント）。 

*ADD*クリックし、必要な構成値を入力します。次に、プロセスを繰り返して、必要な数のCXFエンドポイント構成を追加します。 | CXFエンドポイント構成を作成すると、CXFエンドポイント自体も作成されます。 |これが工場出荷時の構成のしくみです。

サービスが出荷時の構成をサポートすることを意図している場合、そのシステム設定エントリには[追加]ボタンがあります。

![図1：システム設定エントリに[追加]ボタンがある場合、工場出荷時の構成に適しています。](../../../../images/factory-configuration-entry.png)

単一インスタンス構成と同様に、システム設定インターフェイス（上記の例で説明）で、または構成ファイルを使用して工場出荷時の構成を設定できます。 次のように、標準の単一インスタンス構成ファイルに名前を付けます。

    my.service.ServiceConfiguration.config

サービスが出荷時の構成をサポートしている場合は、構成の最初のインスタンス `-default.config`を呼び出す規則を使用します。

    my.service.ServiceConfiguration-default.config

次のインスタンスには、一意の *サブネーム* （ *以外のデフォルト*）が含まれています。 わかりやすい名前を使用することをお勧めします。

    my.service.ServiceConfiguration-port9080.config

上記のCXFエンドポイントの例に従うと、Liferayの開発者が初期のCXFエンドポイント `.config` ファイルを@product@とともに出荷していた場合、このファイルの名前は次のようになります。

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-default.config

この `-default.config` 構成で *REST* Webサービスのコンテキストパスを指定し、 *SOAP* Webサービスの異なるコンテキストパスで別のエンドポイントを作成する場合、2番目の構成ファイルに次の名前を付けることができます。

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-soap.config

| **注：** 一部のシステム設定エントリー（CXFエンドポイントエントリーなど）は出荷されません|構成ファイルを使用すると、作成したものはすべて最初に出現します。 |ただし、構成してエクスポートして `.config` ファイルを取得すると、| `-default.config` 命名規則を使用しません。 代わりに|が与えられます次のように、そのサブネームの一意の識別子が保証されています。 | com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-a6f67e48-6dca-49c6-bf6b-8fd5e6016b2d.config | |これにより、ファイルに一意の名前が付けられます。 |をエクスポートする場合別のシステムにデプロイするための構成ファイル。名前を変更できます。より記述的なサブネームを使用するためにエクスポートされたファイル。 ファイルの名前を変更し、|エクスポート元と同じシステムにデプロイします。新しいサブネームは|としてマークします。まったく新しい構成。 追加の設定が必要になります|この場合のインスタンスは、名前を変更したものだけではありません。

| **警告::** 工場出荷時の構成をサポートする構成エントリーの場合、| `.config` ファイルの名前からサブネームを省略すると、システム設定が|構成の新しいエントリの追加を禁止します（構成エントリのみ|この `.config` ファイルのターゲット）。 これは既知のバグが原因です。 見る| [LPS-76352](https://issues.liferay.com/browse/LPS-76352) |詳細については。 正しくない名前の構成ファイルがデプロイされると、|システムから問題の構成のエントリを追加することはできません。設定エントリ。 たとえば、CXFを構成するために次のファイルをデプロイする場合|エンドポイントは、CXFエンドポイントを追加しないだけでなく、|システム設定を介してCXFエンドポイントを追加する：| | com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration.config | |エラーのある（サブネームがない） `.config` ファイルをデプロイしても無効になりません|永久に何でも。 説明されている適切な規則を使用して、ファイルの名前を変更するだけです。上記または完全に削除してやり直します。

多くの場合、構成ファイルを使用して出荷時の構成シナリオを強制できますが、すべての構成をこの方法で使用できるわけではありません。 意図されたユースケースに固執するのが最善です。 上記のシステム設定を使用して、工場出荷時の構成を使用することが適切かどうかを判断します。 そうでない場合は、構成の単一発生モードに固執します（サービスの構成ファイルを1つだけ指定します）。
