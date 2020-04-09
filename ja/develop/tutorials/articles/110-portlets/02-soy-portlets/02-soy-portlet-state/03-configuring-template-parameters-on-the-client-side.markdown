---
header-id: configuring-soy-portlet-template-parameters-on-the-client-side
---

# クライアント側での大豆ポートレットテンプレートパラメータの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

[ポートレットテンプレートパラメータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-soy-portlet#using-portlet-template-parameters-in-the-soy-template) は、Soy Portletのサーバー側コードで設定されます。 MetalJSの `状態` クラスは、これらのパラメーターをプロパティとして公開する [`STATE` オブジェクト](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-state-object-architecture) を提供し、クライアント側でそれらにアクセスできるようにします。 このチュートリアルでは、UIを更新できるように、クライアント側でビューコンポーネントの `STATE` オブジェクトとそのプロパティを構成する方法について説明します。

このチュートリアルでは、以下の例を参照します。

## ヘッダー状態ポートレットの例

このチュートリアルでは、このセクションで説明するサンプルポートレットを参照します。 これには、デフォルトで *Hello Soy* を読み取るヘッダーを持つ1つのビューが含まれます。

![図1：サンプルのSoyポートレットには構成可能なヘッダーがあります。](../../../../images/soy-example-portlet-start.png)

*Hello* 続くヘッダー内のテキストは、 `* mvcRenderCommand` クラスで定義された `ヘッダー` 状態プロパティによって提供されます。

`* MVCRenderCommand` クラス：

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=MyStateSoyPortlet", "mvc.command.name=View",
            "mvc.command.name=/"
        },
        service = MVCRenderCommand.class
    )
    public class MyStateSoyPortletViewMVCRenderCommand
        implements MVCRenderCommand {
    
        @Override
        public String render(
            RenderRequest renderRequest, RenderResponse renderResponse) {
    
            Template template = (Template)renderRequest.getAttribute(
                WebKeys.TEMPLATE);
    
            String header = "Soy";
    
            template.put("header", header);
    
            return "View";
        }
    
    }

`View.soy`：

    {namespace View}
    
    /**
     * Prints the portlet main view.
     * 
     * @param id: string
     * @param header: string
     */
    {template .render}
    
        <div id="{$id}">
    
            <h1>Hello {$header}</h1>
    
            <p>You can update the header with the portlet's header State properties.</p>
        </div>
    {/template}

## 状態プロパティの構成

Soyポートレットは、 `Liferay.component` APIを使用して自動的に登録されるため、このAPIを使用して、ポートレットを取得し、その状態プロパティを更新できます。 ブラウザの開発者コンソールでこれをテストできます。

次の手順を実行します：

1.  Webブラウザーでコンソールを開きます。

2.  メソッド `Liferay.component（）`でSoyポートレットのIDを渡すことにより、ポートレットのコンポーネントを取得します。 構成例を次に示します。
   
        Liferay.component('_MyStateSoyPortlet_');

    これにより、プロトタイプから継承されたプロパティとともに状態プロパティを含むSoyポートレットのコンポーネントオブジェクトが返されます。 あるいは、 `getState（）` メソッドを呼び出すことにより、 `STATE` オブジェクトに直接アクセスできます。
   
        Liferay.component("_MyStateSoyPortlet_").getState();

    | **注：** `Liferay.component（）` メソッドは、 `STATE` オブジェクトのみを返します。現在ページにあるコンポーネントの情報。 これらは状態です|現在のビューに定義されているプロパティ。

3.  Soyポートレットのコンポーネントを取得したので、オブジェクトのプロパティにアクセスするのと同じ方法で、ドットプロパティまたはブラケット表記の状態プロパティにアクセスできます。 以下のコードは、サンプルポートレットの `ヘッダー` 状態プロパティを取得します。
   
        Liferay.component("_MyStateSoyPortlet_").header;

    または
   
        Liferay.component("_MyStateSoyPortlet_")["header"]

4.  状態プロパティの値を更新します。
   
        Liferay.component("portletID").stateProperty = "new value";

    または
   
        Liferay.component("portletID")["stateProperty"] = "new value";

    または、 `setState（）` メソッドで構成オブジェクトを渡すことができます。
   
        Liferay.component("portletID").setState({stateProperty: new value});

    たとえば、大豆が気に入らない場合は、代わりにサンプルポートレットのヘッダーを *Hello Hamburger* に変更できます。
   
        Liferay.component('_MyStateSoyPortlet_').setState({header: 'Hamburger'});

![図2：クライアント側でサンプルポートレットのヘッダー状態プロパティを変更できます。](../../../../images/soy-example-portlet-fin.png)

これで、クライアント側でSoyポートレット状態プロパティを構成する方法がわかりました！

## 関連トピック

[状態オブジェクトのアーキテクチャを理解する](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-state-object-architecture)

[ポートレットテンプレートパラメータ状態プロパティの構成](/docs/7-1/tutorials/-/knowledge_base/t/configuring-portlet-template-parameter-state-properties)
