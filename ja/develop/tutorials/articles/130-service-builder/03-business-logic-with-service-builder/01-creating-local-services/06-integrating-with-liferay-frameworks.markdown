---
header-id: integrating-with-liferays-frameworks
---

# Liferayのフレームワークとの統合

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

新車購入者は、パワーウィンドウ、クルーズコントロール、フロアマット（少なくとも安価なもの）など、特定の機能が標準になることを期待しています。 同様に、ユーザーはアプリケーションに特定の機能があることを期待し、それらの機能がアプリケーション全体で一貫して動作することを期待します。

たとえば、ユーザーは、アプリのコンテンツがTwitterやFacebookでソーシャルに共有できることを期待する場合があります。 ユーザーは、アプリのコンテンツにタグを付けて評価する方法、およびコメントする方法を期待する場合があります。 これらの期待に応え、さらにはそれを超えなければなりません。 Liferayのフレームワークは、ユーザーが知って愛しているこれらの機能を実装しています。 フレームワークとの統合は簡単で、フレームワークは直感的で一貫したユーザーエクスペリエンスを提供します。

Liferayで最も人気のあるフレームワークの一部を以下に示します。

  - [許可](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions)：エンティティのリソースと、リソースで実行するアクションを定義します。

  - [構成可能なアプリケーション](/docs/7-1/tutorials/-/knowledge_base/t/configurable-applications)：コントロールパネル内からアプリケーションを構成可能にします。

  - [ワークフロー](/docs/7-1/tutorials/-/knowledge_base/t/workflow)：公開前にワークフローでレビューするエンティティを装備します。

  - [アイテムセレクター](/docs/7-1/tutorials/-/knowledge_base/t/item-selector)：エンティティを参照および選択するための一貫した開発者エクスペリエンスを提供します。

  - [アセットフレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)：エンティティをよりわかりやすくし、ユーザーがタグ付け、分類、評価、優先順位付け、コメントを行えるようにします。 ユーザーはエンティティをアセットとして相互に関連付けることができ、エンティティをAsset Publisherで公開できます。

      - [タグとカテゴリ](/docs/7-1/tutorials/-/knowledge_base/t/implementing-asset-categorization-and-tagging)：ユーザーがエンティティにタグを付け、タグ付けされたエンティティを分類できるようにします。
      - [優先度](/docs/7-1/tutorials/-/knowledge_base/t/implementing-asset-priority)：ユーザーは数値の優先度をエンティティに割り当てることができます。
      - [関連資産](/docs/7-1/tutorials/-/knowledge_base/t/relating-assets)：ユーザーは1つのエンティティを別のエンティティに資産として関連付けることができます。
      - [Asset Renderer](/docs/7-1/tutorials/-/knowledge_base/t/rendering-an-asset)：Asset Publisherでのエンティティの表示を有効にします。
      - [コメント](/docs/7-1/tutorials/-/knowledge_base/t/adding-comments-to-your-app)：ユーザーがエンティティにコメントできます。
      - [評価](/docs/7-1/tutorials/-/knowledge_base/t/rating-assets)：星の評価システム（5つ星または親指のアップ/ダウンなど）を有効にします。
      - [フラグ](/docs/7-1/tutorials/-/knowledge_base/t/flagging-inappropriate-asset-content)：ユーザーはエンティティコンテンツを不適切としてフラグを立てることができます。
      - *ソーシャルブックマーク*：ユーザーは、Twitter、Facebookなどでエンティティコンテンツを共有できます。 （[チュートリアル](https://issues.liferay.com/browse/LRDOCS-4531) は近日公開予定です。）

  - [エクスポート/インポート](/docs/7-1/tutorials/-/knowledge_base/t/export-import-and-staging)：ユーザーがエンティティデータをファイルにエクスポートしたり、ファイルからエンティティデータをインポートしたりできるようにします（`.lpkg` ファイル）。 エクスポートされたデータは、別のポータルインスタンスにインポートしたり、後で使用するために保存したりできます。

  - [ステージング](/docs/7-1/tutorials/-/knowledge_base/t/export-import-and-staging)：ユーザーは、ライブサイトに影響を与えることなく、舞台裏でエンティティを変更できます。

  - *検索*：@product@検索結果でエンティティを見つけて表示できるようにします。 （[チュートリアル](https://issues.liferay.com/browse/LRDOCS-4372) は近日公開予定です。）

  - *ごみ箱*：エンティティをアプリケーションから移動して、ごみ箱に入れることができます。 エンティティはごみ箱から復元するか、完全に（手動またはスケジュールごとに）削除できます。 （[チュートリアル](https://issues.liferay.com/browse/LRDOCS-4505) は近日公開予定です。）

Liferayのフレームワークは、ユーザーがアプリケーションに期待する機能が豊富です。 フレームワークのチュートリアルリンクをクリックして、フレームワークの活用を開始します。 次に、ローカルサービスの呼び出しに関するチュートリアルがあります。

## 関連トピック:

[国際化](/docs/7-1/tutorials/-/knowledge_base/t/internationalization)

[JavaScriptモジュールローダー](/docs/7-1/tutorials/-/knowledge_base/t/javascript-module-loaders)

[フロントエンドTaglib](/docs/7-1/tutorials/-/knowledge_base/t/front-end-taglibs)

[データのアップグレード](/docs/7-1/tutorials/-/knowledge_base/t/data-upgrades)
