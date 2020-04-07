---
header-id: the-state-object
---

# 状態オブジェクト

[TOC levels=1-4]

[ビューコンポーネント](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#configuring-the-view-layer) が拡張するMetalJSの `コンポーネント` クラスは、MetalJSの `状態` クラスを拡張します。 `state` クラスは、stateプロパティを含む `STATE` オブジェクトを提供し、これらのプロパティの変更を監視します。 ポートレットクラスで定義された [テンプレートパラメータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) は、ポートレットの `STATE` オブジェクトのプロパティとして自動的に追加されます。 `コンポーネント` クラスは、 `状態` クラスが状態プロパティの変更を検出したときにコンポーネントを自動的に再レンダリングするなど、追加のレンダリングロジックを提供します。 このあなたができることを意味 [の変化状態のクライアント側のプロパティ](/docs/7-1/tutorials/-/knowledge_base/t/configuring-soy-portlet-template-parameters-on-the-client-side) と、自動的にコンポーネントのUI \に反映その変更を参照してください！

チュートリアルのこのセクションでは、Soyポートレットの `STATE` オブジェクトを構成および使用する方法について説明します。
