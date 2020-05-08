---
header-id: declaring-optional-import-package-requirements
---

# オプションのインポートパッケージ要件の宣言

[TOC levels=1-4]

モジュールの開発時に、*オプション*の依存関係を宣言できます。オプションの依存関係は、使用可能な場合にモジュールが使用できるものですが、それを使用しなくても機能します。

**重要**：オプションの依存関係の使用は避けてください。最適なモジュール設計は、通常の依存関係に依存しています。オプションの依存関係が望ましいと思われる場合は、モジュールが2つ以上の異なるタイプの機能を提供しようとしている可能性があります。
このような状況では、より小さく、より焦点を絞った機能を提供する複数のモジュールに分割するのが最善です。

モジュールにオプションの依存関係が必要であると判断した場合は、次の手順に従って追加してください。

1. モジュールの`bnd.bnd`ファイルで、モジュールがオプションで依存するパッケージを宣言します。

       Import-Package: com.liferay.demo.foo;resolution:="optional"
   
   オプションのインポートまたは動的インポートのいずれかを使用できることに注意してください。違いは[こちら](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html#i2548181)で説明しています。

2. オプションのパッケージを使用するコンポーネントを作成します。

       import com.liferay.demo.foo.Foo; // A class from the optional package
       
       @Component(
       enabled = false // instruct declarative services to ignore this component by default
       )
       public class OptionalPackageConsumer implements Foo {...}
   
3. 最初のコンポーネントのコントローラーとして機能する2番目のコンポーネントを作成します。2番目のコンポーネントは、クラスパスでオプションクラスのクラスローダーをチェックします。存在しない場合、これは`ClassNotFoundException`をキャッチする必要があることを意味します。例：

       @Component
       public class OptionalPackageConsumerStarter {
       @Activate
       void activate(ComponentContext componentContext) {
       try {
       Class.forName(com.liferay.demo.foo.Foo.class.getName());
       
       componentContext.enableComponent(OptionalPackageConsumer.class.getName());
       }
       catch (Throwable t) {
       _log.warn("Could not find {}", t.getMessage()); // Could use _log.info instead
       }
       }
       }
   
コントローラコンポーネントでのクラスローダーのチェックが成功すると、クライアントコンポーネントが有効になります。このチェックは、これらのコンポーネントを含むモジュールへの配線変更があるたびに自動的に実行されます（配線変更があると、Declarative Servicesコンポーネントは常に再起動されます）。

オプションの依存関係が@product@のOSGiランタイムにないときにモジュールをインストールすると、コントローラーコンポーネントは`ClassNotFoundException`をキャッチし、警告または情報メッセージを記録します（またはこのケースを処理するために実装された他のアクションを実行します）。オプションの依存関係をインストールした場合、モジュールを更新すると、コントローラーの`activate`メソッドとオプションの依存関係のチェックをトリガーするOSGiバンドルライフサイクルイベントがトリガーされます。依存関係が存在するため、クライアントコンポーネントはそれを使用します。

次のコマンドを使用して、[Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)からバンドルを更新できることに留意してください。

    equinox:refresh [bundle ID]

## 関連トピック

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)
