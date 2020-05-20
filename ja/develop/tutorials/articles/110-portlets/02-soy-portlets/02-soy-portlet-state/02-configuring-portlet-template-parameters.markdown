---
header-id: configuring-portlet-template-parameter-state-properties
---

# ポートレットテンプレートパラメータ状態プロパティの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[ポートレットテンプレートパラメータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) は、状態プロパティとして [ビューコンポーネント](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#configuring-the-view-layer)の [`STATE` オブジェクト](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-state-object-architecture)自動的に追加されます。 したがって、 `STATE` オブジェクトで追加の構成オプションを提供できます。 以下の例では、 [`* MVCRenderCommand` クラス](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#render-logic)のポートレットテンプレートパラメーター `色` のデフォルト値を設定します。

    Template template = (Template)renderRequest.getAttribute(
                WebKeys.TEMPLATE);
    
    String color = "red";
    
    template.put("color", color);

上記の構成には、ビューのコンポーネントファイルに以下に示す暗黙的な状態プロパティ構成があります（たとえば、`View.es.js`）。

``` 
   View.STATE {
     color: {
       value: 'red'
     }
   }
```

`ビュー` コンポーネントの状態プロパティ</a>

設定することにより、追加の設定を提供できます。 以下の例では、 `STATE` オブジェクトに追加する前に色の文字列を大文字に変換する `セッター` 関数を定義しています。</p> 

    function setColor(color) {
      return color.toUpperCase();
    }
    
    View.STATE = {
      color: {
        setter: 'setColor'      
      }
    }
    

これで、ポートレットテンプレートのパラメーター状態プロパティを構成する方法がわかりました。



## 関連トピック

[状態オブジェクトのアーキテクチャを理解する](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-state-object-architecture)

[クライアント側での大豆ポートレットテンプレートパラメータの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-soy-portlet-template-parameters-on-the-client-side)
