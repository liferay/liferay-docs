---
header-id: upgrading-7-0-themes-to-7-1
---

# Liferay Portal 7.0から7.1へのテーマのアップグレード

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

This section of tutorials guides you through the process of upgrading your 7.0 theme to run on @product-ver@. 作業中は、Bootstrap 4やLexicon 2.0のサポートなど、テーマの改善を活用する必要があります。

テーマのアップグレードには次の手順が含まれます。

  - プロジェクトメタデータの更新
  - CSSの更新
  - テーマテンプレートの更新

テーマを開発している環境に関係なく、これらのチュートリアルでは、テーマのアップグレードに必要なすべてを説明しています。 最も簡単なオプションは、gulp `upgrade` タスクを使用することです。 ただし、これは [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)作成されたテーマでのみ使用できます。 テーマジェネレーター以外の環境でアップグレードする場合は、このセクションの他のチュートリアルに従ってテーマを手動でアップグレードしてください。
