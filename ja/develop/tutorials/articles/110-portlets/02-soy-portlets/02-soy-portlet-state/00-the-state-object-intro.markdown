---
header-id: the-state-object
---

# 状態オブジェクト

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[ビューコンポーネント](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#configuring-the-view-layer) が拡張するMetalJSの `コンポーネント` クラスは、MetalJSの `状態` クラスを拡張します。 `state` クラスは、stateプロパティを含む `STATE` オブジェクトを提供し、これらのプロパティの変更を監視します。 ポートレットクラスで定義された [テンプレートパラメータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) は、ポートレットの `STATE` オブジェクトのプロパティとして自動的に追加されます。 `コンポーネント` クラスは、 `状態` クラスが状態プロパティの変更を検出したときにコンポーネントを自動的に再レンダリングするなど、追加のレンダリングロジックを提供します。 このあなたができることを意味 [の変化状態のクライアント側のプロパティ](/docs/7-1/tutorials/-/knowledge_base/t/configuring-soy-portlet-template-parameters-on-the-client-side) と、自動的にコンポーネントのUI \に反映その変更を参照してください！

チュートリアルのこのセクションでは、Soyポートレットの `STATE` オブジェクトを構成および使用する方法について説明します。
