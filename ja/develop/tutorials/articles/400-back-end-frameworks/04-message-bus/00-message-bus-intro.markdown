---
header-id: message-bus
---

# メッセージバス

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Webの要求/応答の範囲外でデータ処理を行う必要がある場合は、Message Bus以外に注意してください。 概念的にはJava Messaging Service（JMS）トピックに似ていますが、トランザクションの信頼できる配信機能を犠牲にして、はるかに軽量にしています。 @product@は、至る所でメッセージバスを使用します。

  - 監査
  - 検索エンジンの統合
  - メール購読
  - モニタリング
  - ドキュメントライブラリの処理
  - バックグラウンドタスク
  - クラスタ全体のリクエスト実行
  - クラスター化されたキャッシュレプリケーション

あなたもそれを使用することができます\！ メッセージバスの最も重要な機能の一部を以下に示します。

  - パブリッシュ/サブスクライブメッセージング
  - キューイングとスロットリングを要求する
  - フロー制御
  - マルチスレッドメッセージ処理

Java SEのJConsoleなど、メッセージバスのアクティビティを監視できるツールもあります。

![図1：JConsoleは、送信されたメッセージバスメッセージ、保留中のメッセージなどの統計を示します。](../../../images/message-bus-jconsole.png)

メッセージバスのトピックは、次のチュートリアルで説明されています。

  - [メッセージング宛先](/docs/7-1/tutorials/-/knowledge_base/t/messaging-destinations)
  - [メッセージリスナー](/docs/7-1/tutorials/-/knowledge_base/t/message-listeners)
  - [メッセージを送信する](/docs/7-1/tutorials/-/knowledge_base/t/sending-messages)

すべてのメッセージは宛先で送受信されるため、メッセージング宛先は最初に調べる価値があります。
