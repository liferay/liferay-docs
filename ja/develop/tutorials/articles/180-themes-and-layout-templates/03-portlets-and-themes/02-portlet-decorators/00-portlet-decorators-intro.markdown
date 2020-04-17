---
header-id: creating-configurable-styles-for-portlet-wrappers
---

# ポートレットラッパーの構成可能なスタイルの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ポートレットデコレータは、アプリケーションのラッパーのスタイルをカスタマイズします。 ページ上にあるアプリケーションのマークアップを調べると、2つのレイヤーにラップされていることがわかります。 とりわけ、これらのレイヤーは、ドラッグアンドドロップやアプリケーションの境界線スタイルなどのいくつかの一般的な基本機能を提供します。 これらの機能を保護するために、テーマを使用してこれらのレイヤーのマークアップを直接変更することはできません。

Portlet Decoratorsを使用すると、ユーザーの設定を介してこれらのラッピングレイヤーの1つにCSSクラスを追加できます。 テーマでこのクラスのスタイルを定義することにより、ラッパーを含むPortlet Decoratorが適用されるアプリケーションインスタンスのルックアンドフィールを変更できます。

次の図は、 *Decorate* Portlet Decoratorが適用されたときに@product@アプリケーションをラップするレイヤーのマークアップを示しています。

![図1：ポートレットデコレータは、デコレータのCSSクラスをアプリケーションのラッパーに追加します](../../../../images/portlet-application-markup.png)

Portlet Decoratorが完成したら、 [Look and Feel Configurationメニュー](/docs/7-1/user/-/knowledge_base/u/look-and-feel-configuration)使用してアプリケーションに適用します。

![図2：ルックアンドフィールの構成メニューからポートレットデコレータを適用できます](../../../../images/app-decor-look-and-feel.png)

このセクションのチュートリアルでは、ポートレットデコレータをカスタマイズしてアプリケーションに適用する方法について詳しく説明します。
