---
header-id: adding-logic-to-mvc-commands
---

# MVCコマンドにロジックを追加する

[TOC levels=1-4]

MVCコマンドやOSGiサービスを完全にオーバーライドすることもできますが、コマンドに*ロジックを追加*する方がより良い選択です。必要なロジックを破棄するのは良くありません。逆に、オリジナルからコピーしたロジックは、新しいバージョンのポートレットでは機能しない場合があります。元のロジックの呼び出しを続けながらカスタムロジックを追加すると、元の実装からカスタムクラスが分離されます。
新しいロジックを元のロジックから分離しておくことで、コードがクリーンで維持しやすく、理解しやすくなります。

MVCコマンドにロジックを追加する手順は次のとおりです。

1. インターフェイスを実装する
2. コンポーネントとして公開する
3. 元の実装を参照する
4. ロジックを追加し、オリジナルを呼び出す

## ステップ1：インターフェイスを実装する

それぞれのMVCコマンドインターフェイスを直接実装するか、またはそれを実装している既存のベースクラスを拡張して実装します。インターフェイスのベースクラスを拡張すると、ほとんどのコマンド実装の一部であるロジックの実装が不要になります。たとえば、ブログポートレットの`EditEntryMVCActionCommand`にロジックを追加するには、ベースクラス`BaseMVCActionCommand`を拡張します。

    public class CustomBlogsMVCActionCommand extends BaseMVCActionCommand

既存のベースクラスのMVCコマンドインターフェイスを確認します。

- [`MVCActionCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
- [`MVCRenderCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
- [`MVCResourceCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)

次に、クラスをサービスコンポーネントにします。

## ステップ2：コンポーネントとして公開する

Declarative Servicesの`@Component` アノテーションを使用すると、MVCコマンドのカスタマイズが容易になります。すべてのカスタマイズオプションでは、MVCコマンドクラスをコンポーネントとして公開する必要があります。たとえば、この`@Component`アノテーションは`MVCActionCommand`サービスを宣言します。

    @Component(
    immediate = true,
    property = {
    "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
    "mvc.command.name=/blogs/edit_entry",
    "service.ranking:Integer=100"
    },
    service = MVCActionCommand.class
    )
    public class CustomBlogsMVCActionCommand extends BaseMVCActionCommand {
    ...
    }

`MVCActionCommand`クラスのサービスコンポーネントとして`CustomBlogsMVCActionCommand`を公開します。解決すると、`immediate =
true`であるため、すぐにアクティブになります。コンポーネントは、コマンドURL `/blogs/edit_entry`によってプログ管理ポートレットで呼び出されます。サービスランキングが`100`なので、ランキングが`0`の元のサービスコンポーネントよりも優先されます。

カスタムMVCコマンドに`@Component`アノテーションで指定する必要がある内容は次のとおりです。

- `javax.portlet.name`：カスタマイズを適用する各ポートレット。
これらのポートレットのJSPは、該当するコマンドURLタグを介してMVCコマンドを呼び出すことができます。元のMVCコマンドと同じポートレット、またはそれらのポートレットのサブセットを指定できます。

- `mvc.command.name`：このプロパティは、このカスタムMVCコマンドコンポーネントにマップするコマンドURLを宣言します。

- `service.ranking:Integer`：このプロパティを元のサービス実装のランキングよりも高い整数に設定します。このランキングは、複数のコンポーネントが同じプロパティを持つ同じサービスを登録している場合に、使用するサービスをOSGiランタイムに伝えます。ここで指定する整数の値が大きいほど、コンポーネントの重みが大きくなります。Liferayのサービス実装では通常、ランキングは`0`です。

- `service`：この属性は、オーバーライドするサービス（インターフェイス）を指定します。

- `immediate`：この属性を`true`に設定すると、解決後すぐにコンポーネントがアクティブになります。

カスタムMVCコマンドに`@Component`アノテーションを追加するときに、このリストに戻って参照できます。

次に、元の実装を参照します。

## ステップ3：元の実装を参照する

`@Reference`アノテーションが付けられたフィールドを使用して、元のMVCコマンドコンポーネントへの参照を取得します。 元のコンポーネントに追加のカスタマイズがない場合、この参照は元のMVCコマンドタイプになります。たとえば、次のフィールドは元のMVCコマンドコンポーネント`EditEntryMVCActionCommand`を参照します。

    @Reference(
    target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
    protected MVCActionCommand mvcActionCommand;

参照を追加する方法は次のとおりです。

1. フィールドをMVCコマンドインターフェイスのタイプとして宣言します。たとえば、`mvcActionCommand`フィールドのタイプは`MVCActionCommand`です。

2. `@Reference`アノテーションを追加します。

3. アノテーションで、デフォルトのサービス実装クラスの完全修飾名に等しい`component.name`でフィルタリングする`target`属性を定義します。

カスタムコンポーネントが解決されると、OSGiランタイムは対象のサービスをフィールドに割り当てます。次に、カスタムロジックを追加します。

## ステップ4：ロジックの追加

ロジックを追加するには、拡張するベースクラスまたは実装するインターフェイスのプライマリメソッドをオーバーライドします。メソッドのオーバーライドで、新しいロジックを追加し、元の実装を呼び出します。たとえば、次のメソッドは`BaseMVCActionCommand`のメソッド`doProcessAction`をオーバーライドします。

    @Override
    protected void doProcessAction(
    ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception {
    // Add custom logic here
    ...
    
    // Call the original service implementation
    mvcActionCommand.processAction(actionRequest, actionResponse);
    }

上記のメソッドはカスタムロジックを定義し、前のステップで参照した元のサービスを呼び出します。

この方法を使用すると、元のポートレットのロジックとカスタマイズとの間に結合が存在しないため、拡張機能は元のポートレットの新しいバージョンで引き続き動作します。コマンド実装クラスは変更できます。参照を最新の実装クラスの名前に更新してください。

既存のMVCコマンドにロジックを追加できました。
