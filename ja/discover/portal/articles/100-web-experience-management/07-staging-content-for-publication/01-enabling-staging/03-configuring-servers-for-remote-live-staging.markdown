---
header-id: configuring-servers-for-remote-live-staging
---

# リモートライブステージング用のサーバーの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

サイトのリモートライブステージングを有効にする前に、いくつかの必要な要件を満たす必要があります。

  - リモートのLiferayサーバーを、現在のLiferayサーバーの許可されたサーバーのリストに追加します。逆も同様です。
  - 現在のサーバーとリモートサーバーで共有する認証キーを指定します。
  - 各Liferayサーバーのトンネリングサーブレット認証検証を有効にします。
  - リモートLiferayインスタンスのTunnel Auth Verifier構成を更新します。

以下の手順に従って、サーバーをリモートライブステージング用に構成します。

1.  現在のLiferayサーバーとリモートLiferayサーバーの `portal-ext.properties` ファイルに次の行を追加します。
   
        tunneling.servlet.shared.secret=[secret]
        tunneling.servlet.shared.secret.hex=true

    @product@がステージング環境と本番環境の間で事前共有キーを使用することで、リモート公開プロセスの安全が確保されます。 また、Webサービス認証のために公開サーバーのパスワードをリモートサーバーに送信する必要がなくなります。 事前共有キーを使用すると、提供した電子メールアドレス、スクリーン名、またはユーザーIDから認可コンテキスト（許可チェッカー）を作成し、 *することなく、* ユーザーのパスワード。

2.  サーバーの `tunneling.servlet.shared.secret` プロパティの値を指定します。

    異なる暗号化アルゴリズムが異なる長さのキーをサポートするため、これらのプロパティの値は、選択した構成済み暗号化アルゴリズムによって異なります。 詳細については、 [HTTPトンネリング](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#HTTP%20Tunneling) プロパティのドキュメントを参照してください。 使用可能な暗号化アルゴリズムでは、次のキーの長さがサポートされていることに注意してください。

      - AES：128、192、および256ビットキー
      - Blowfish：32〜448ビットのキー
      - DESede（トリプルDES）：56、112、または168ビットキー（ただし、Liferayは最小キー長に人為的な制限を設けており、56ビットキー長をサポートしていません）

    潜在的な文字エンコードの問題を回避するには、次の2つの戦略のいずれかを使用できます。

    2a。 16進エンコードを使用します（推奨）。 たとえば、パスワードが *abcdefghijklmnop*場合、 `portal-ext.properties` ファイルで次の設定を使用します。

        tunneling.servlet.shared.secret = 6162636465666768696a6b6c6d6e6f70 tunneling.servlet.shared.secret.hex = true

    2b。 印刷可能なASCII文字（安全性が低い）を使用します。 これにより、パスワードエントロピーが低下します。

    あなたは、16進数のエンコーディングを使用しない場合（つまり、あなたは、デフォルト設定を使用する場合 `tunneling.servlet.shared.secret.hex =偽`）、 `tunneling.servlet.shared.secret` プロパティの値が *必須* もASCII準拠を。

    キーを選択したら、現在のサーバーの値がリモートサーバーの値と一致していることを確認してください。

    **重要：** どのユーザーとも鍵を共有しないでください。 これは、ステージング環境と本番環境の間の通信にのみ使用されます。 キーを所持しているすべてのユーザーは、本番サーバーの管理、サーバー側のJavaコードの実行などを行うことができます。

3.  リモートのLiferayサーバーの `portal-ext.properties` ファイルに次の行を追加します。

        tunnel.servlet.hosts.allowed = 127.0.0.1、SERVER_IP、[STAGING_IP]

    `[STAGING_IP]` 値は、ステージングサーバーのIPアドレスに置き換える必要があります。 サーバーに複数のインターフェースがある場合は、各IPアドレスも追加する必要があります。これは、ステージングサーバーからのhttp（s）リクエストのソースアドレスとして表示されます。 このプロパティに `SERVER_IP` 定数を設定したままにすることができます。 LiferayサーバーのIPアドレスに自動的に置き換えられます。

    IPv6アドレスを検証する場合は、IPv4アドレスの使用を強制しないようにアプリサーバーのJVMを構成する必要があります。 たとえば、Tomcatを使用している場合は、 `-Djava.net.preferIPv4Stack = false` 属性を `$TOMCAT_HOME\ bin \ setenv。[bat | sh]` ファイルに追加します。

4.  リモートLiferayインスタンスの *TunnelAuthVerfierConfiguration* を更新します。 これを行うには、コントロールパネルに移動します→ *構成* → *システム設定* → *API認証* → *トンネル認証ベリファイア*。 [ */ api / liferay / do* をクリックし、使用している追加のIPアドレスを[ *Hosts allowed* フィールドに挿入します。 次に *Update*選択します。

    または、この構成を@product@のOSGiファイル（例： `osgi / configs / com.liferay.portal.security.auth.verifier.tunnel.module.configuration.TunnelAuthVerifierConfiguration-default.config`）に書き込むこともできます。インスタンス：

        enabled = true hostsAllowed = 127.0.0.1、SERVER_IP、[Local server IP address] serviceAccessPolicyName = SYSTEM_USER_PASSWORD urlsIncludes = / api / liferay / do

5.  これらの構成を更新した後、両方のLiferayサーバーを再起動します。 再起動したら、サイト管理者としてローカルのLiferayインスタンスに再度ログインします。

リモートライブステージングを構成するために必要なのはこれだけです！ これで [有効にできます](/docs/7-1/user/-/knowledge_base/u/enabling-remote-live-staging)\！

| **注：** @product@データベースを複製しないでください。これを行うと重複する可能性があります。ステージングで使用される重要なデータ（UUIDなど）により、リモートパブリケーションが発生します。失敗するプロセス。

リモートライブステージングの構成の詳細については、以下のトピックを参照してください。

## リモートステージングを使用する場合のパッチの適用

リモートステージング環境にパッチを適用する場合は、すべてのサーバーにパッチを適用する必要があります。 異なるパッチレベルのサーバーを持つことは良い習慣ではなく、インポートの失敗やデータの破損につながる可能性があります。 リモートステージングが正しく機能するようにするには、すべてのサーバーを同じパッチレベルに更新することが不可欠です。

## リモートステージングのバッファサイズの構成

ローカルライブステージングと同様に、優れたパフォーマンスを得るには、サイトの開発の最初にリモートステージングをオンにすることをお勧めします。 リモートライブステージングを使用していて、大量のコンテンツを公開している場合、公開が遅くなり、大量のネットワークトラフィックが発生する可能性があります。 @product@のシステムは、ネットワークを介して転送されるデータ量に対して非常に高速です。 これは、1つの大きなデータダンプではなく、データ転送が少しずつ完了するためです。 `portal-ext.properties` ファイルで次のポータルプロパティを設定することにより、データトランザクションのサイズを制御できます。

    staging.remote.transfer.buffer.size

このプロパティは、リモートステージングのファイルブロックサイズを設定します。 リモートステージングに使用されるLARファイルがこのサイズを超える場合、ファイルは送信前に複数のファイルに分割され、リモートサーバーで再構成されます。 デフォルトのバッファサイズは10メガバイトです。
