---
header-id: jax-ws
---

# JAX-WS

[TOC levels=1-4]

Liferayは[Apache CXF](https://en.wikipedia.org/wiki/Java_API_for_XML_Web_Services)の実装を介して、JAX-WSをサポートしています。アプリケーションは、Liferayインスタンスで定義されたCXFエンドポイントにJAX-WS Webサービスを公開することができます。CXFエンドポイントは、JAX-WS Webサービスがデプロイされてアクセス可能なコンテキストパスです。種類を問わず、JAX-WS Webサービスを公開するには、1つ以上のCXFエンドポイントを定義する必要があります。JAX-WS Webサービスにアクセスするには、Liferayインスタンスで*エクステンダー*も設定する必要があります。エクステンダーは、サービスがデプロイされる場所の指定と、ハンドラーやプロバイダーなどでサービスを拡張するかどうかの指定を行います。

**SOAPエクステンダー：**これは、JAX-WS Webサービスを公開するために必要です。各SOAPエクステンダーは、1つ以上のCXFエンドポイントにサービスをデプロイでき、[JAX-WSハンドラー](https://jax-ws.java.net/articles/handlers_introduction.html)のセットを使用してサービスを強化できます。

SOAPエクステンダーは、アプリ開発者がOSGiに登録するサービス（提供されている[OSGiフィルタ](https://osgi.org/javadoc/r6/core/org/osgi/framework/Filter.html)に一致するサービス）を追跡し、指定されたCXFエンドポイントの下にそれらのサービスをデプロイするサブシステムです 。たとえば、`/soap`というCXFエンドポイントを作成する場合、`/soap`のためにSOAPサービスを公開するSOAPエクステンダーを後で作成できます。もちろん、これは大まかな例に過ぎません。ご希望に合わせて、様々な調整ができます。

CXFエンドポイントとエクステンダーは、プログラムで作成するか、Liferaのコントロールパネルで作成することができます。このチュートリアルでは、その両方の作成方法を紹介し、それからJAX-WS Webサービスを公開する方法を示します。ここでは、以下のトピックをカバーします。

- [コントロールパネルでのエンドポイントおよびエクステンダーの設定方法](/docs/7-1/tutorials/-/knowledge_base/t/jax-ws#configuring-endpoints-and-extenders-with-the-control-panel)

- [プログラムによるエンドポイントおよびエクステンダーの設定方法](/docs/7-1/tutorials/-/knowledge_base/t/jax-ws#configuring-endpoints-and-extenders-programmatically)

- [JAX-WS Webサービスの公開](/docs/7-1/tutorials/-/knowledge_base/t/jax-ws#publishing-jax-ws-web-services)

## コントロールパネルでのエンドポイントおよびエクステンダーの設定方法

Liferayのコントロールパネルを使用すると、管理者はJAX-WS Webサービスのエンドポイントとエクステンダーを設定することができます。ここで設定にアクセスするには、Liferayインスタンスの管理者である必要があることに注意してください。まず最初に、CXFエンドポイントの作成方法について説明します。

コントロールパネルでCXFエンドポイントを設定するには、まず*[コントロールパネル]* → *[設定]* → *[システム設定]* → *[Web API]*に移動します。次に、リストから*[CXFエンドポイント]*を選択します。既存のCXFエンドポイントがある場合は、ここに表示されます。新しいものを追加するには、*[追加]*ボタンをクリックします。表示されるフォームにある以下のフィールドを入力すると、新しいCXFエンドポイントを設定することができます。

**Context Path:** JAX-WS WebサービスがLiferayサーバーにデプロイされるパス。たとえば、`/web-services`というコンテキストパスを定義する場合、そこにデプロイされているサービスは`http://your-server:your-port/o/web-services`で利用できます。

**`AuthVerifier`プロパティ:** ここで定義されたプロパティは、すべて`AuthVerifier`フィルタにそのまま渡されます。詳細については、[`AuthVerifier`のドキュメンテーション](/docs/7-1/deploy/-/knowledge_base/d/authentication-verifiers)を参照してください。

**Required Extensions:** CXFは通常、デフォルトの拡張機能クラスをロードしますが、場合によっては、それらをオーバーライドしてデフォルトの動作を置き換えることができます。ほとんどの場合、このフィールドは空白のままでかまいません。拡張機能を上書きすることは一般的ではないためです。ここで[OSGiフィルタ](https://osgi.org/javadoc/r6/core/org/osgi/framework/Filter.html)を介してカスタム拡張機能を指定することで、CXFサーブレットを作成して拡張機能をサーブレットに渡す前にそれらの拡張機能がOSGiフレームワークに登録されるまで、Liferayは待機します。

![図1：このフォームに入力して、CXFエンドポイントを作成します。](../../../images/cxf-endpoint-form.png)

アプリがJAX-WS Webサービスをデプロイするには、SOAPエクステンダーを設定する必要があります。
コントロールパネルでSOAPエクステンダーを設定するには、まず*[コントロールパネル]* → *[設定]* → *[システム設定]* → *[Web API]*に移動します。次に、リストから*[SOAPエクステンダー]*を選択します。既存のSOAPエクステンダーがある場合は、ここに表示されます。新しいものを追加するには、*[追加]*ボタンをクリックします。表示されるフォームにある以下のフィールドを入力すると、新しいSOAPエクステンダーを設定することができます。

**Context paths:** ここで少なくとも1つのCXFエンドポイントを指定します。このエクステンダーの影響を受けるサービスがデプロイされる場所です。前述のCXFエンドポイントの例では、`/web-services`がこの部分にあたります。ここでは、複数のCXFエンドポイントが指定できることに注意してください。

**jax.ws.handler.filters:** ここで、OSGiフレームワークに登録された特定のサービスを選択する[OSGiフィルター](https://osgi.org/javadoc/r6/core/org/osgi/framework/Filter.html)のセットを指定できます。選択したサービスは、JAX-WSハンドラーを実装し、*jax.ws.service.filters*プロパティで指定されたJAX-WSサービスを強化する必要があります。これらのJAX-WSハンドラーは、このエクステンダーで選択されたそれぞれのサービスに適用されます。

**jax.ws.service.filters:** ここで、CXFエンドポイントにデプロイされるOSGiフレームワークに登録されたサービスを選択する、OSGiフィルターのセットを指定できます。これらのOSGiサービスは、[適切なJAX-WSサービス](https://docs.oracle.com/javaee/7/tutorial/jaxws001.htm)でなければなりません。

**soap.descriptor.builder:** JAX-WSアノテーションを使用してSOAPサービスを記述するには、このオプションを空のままにします。別の方法でSOAPサービスを記述するために、ここで`com.liferay.portal.remote.soap.extender.SoapDescriptorBuilder`の実装を選択するOSGiフィルターを提供できます。

![図2：このフォームを入力して、SOAPエクステンダーを作成します。](../../../images/soap-extenders-form.png)

次に、エンドポイントとエクステンダーをプログラムで作成する方法について説明します。

## プログラムによるエンドポイントおよびエクステンダーの設定方法

エンドポイントやエクステンダーをプログラムで設定するには、Liferayのコンフィギュレーターエクステンダーを使用する必要があります。コンフィギュレーターエクステンダーは、OSGiモジュールがデフォルトの設定値をデプロイする方法を提供します。コンフィギュレーターエクステンダーを使用するモジュールは、モジュール内の設定ファイルの場所を指す`ConfigurationPath`ヘッダーを提供する必要があります。たとえば、以下の設定では、`ConfigurationPath`を`src/main/resources/configuration`に設定しています。


    Bundle-Name: Liferay Export Import Service JAX-WS
    Bundle-SymbolicName: com.liferay.exportimport.service.jaxws
    Bundle-Version: 1.0.0
    Liferay-Configuration-Path: /configuration
    Include-Resource: configuration=src/main/resources/configuration
    Liferay-Releng-Module-Group-Description:
    Liferay-Releng-Module-Group-Title: Data Management

Liferayに固有のBnd命令には、競合を避けるために接頭辞の`Liferay`が付いていることに注意してください。

[OSGiの`ConfigurationAdmin`](https://osgi.org/javadoc/r4v42/org/osgi/service/cm/ConfigurationAdmin.html)には、「シングル」と「ファクトリー」の2種類の設定タイプがあります。ファクトリー設定は、ファクトリー名ごとに複数の設定インスタンスを持つことができます。@product@は、工場出荷時の設定を使用しているため、工場出荷時の設定のデフォルト値を`*.properties`ファイルで提供する必要があります。
このプロパティファイルでは、PID（永続的な識別子）の末尾に接尾辞を使用してから設定を提供します。たとえば、以下のコードはPIDに接尾辞の`-stagingPID`を使用して、コンテキストパスの`/staging-ws`にCXFエンドポイントを作成します。

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-staging.properties:
    
    contextPath=/staging-ws

以下のコードはもう一つの例で、ここではPIDに接尾辞の`-stagingjaxwsPID`を使用して、コンテキストパスの`/staging-ws`にSOAPエクステンダーを作成します。このコードには、設定フィールドの`jaxWsHandlerFilterStrings`および`jaxWsServiceFilterStrings`の設定も含まれます。

    com.liferay.portal.remote.soap.extender.configuration.SoapExtenderConfiguration-stagingjaxws.properties:
    
    contextPaths=/staging-ws
    jaxWsHandlerFilterStrings=(staging.jax.ws.handler=true)
    jaxWsServiceFilterStrings=(staging.jax.ws.service=true)

次は、設定クラスでこれらの設定フィールドを使用する必要があります。例えば、以下の`SoapExtenderConfiguration`インターフェイスには、設定フィールドの`contextPaths`、`jaxWsHandlerFilterStrings`、および`jaxWsServiceFilterStrings`が含まれています。

    @ExtendedObjectClassDefinition(
    category = "foundation", factoryInstanceLabelAttribute = "contextPaths"
    )
    @Meta.OCD(
    factory = true,
    id = "com.liferay.portal.remote.soap.extender.configuration.SoapExtenderConfiguration",
    localization = "content/Language", name = "soap.extender.configuration.name"
    )
    public interface SoapExtenderConfiguration {
    
    @Meta.AD(required = false)
    public String[] contextPaths();
    
    @Meta.AD(name = "jax.ws.handler.filters", required = false)
    public String[] jaxWsHandlerFilterStrings();
    
    @Meta.AD(name = "jax.ws.service.filters", required = false)
    public String[] jaxWsServiceFilterStrings();
    
    @Meta.AD(name = "soap.descriptor.builder", required = false)
    public String soapDescriptorBuilderFilter();
    
    }

次に、JAX-WS Webサービスを公開する方法について説明します。

## JAX-WS Webサービスの公開

モジュールでSOAPを介してJAX-WS Webサービスを公開するには、クラスとそのメソッドに標準のJAX-WSアノテーションを付けて、OSGiフレームワークにサービスとして登録します。たとえば、以下のクラスでは、クラスに対して`@WebService`アノテーションを意使用しており、メソッドに対しては`@WebMethod`を使用しています。また、OSGiの`@Component`アノテーションで、`jaxws`プロパティを`true`に設定する必要もあります。

    import javax.jws.WebMethod;
    import javax.jws.WebService;
    
    import org.osgi.service.component.annotations.Component;
    
    @Component(
    immediate = true, property = "jaxws=true", service = Calculator.class
    )
    @WebService
    public class Calculator {
    
    @WebMethod
    public int divide(int a, int b) {
    return a / b;
    }
    
    @WebMethod
    public int multiply(int a, int b) {
    return a * b;
    }
    
    @WebMethod
    public int subtract(int a, int b) {
    return a - b;
    }
    
    @WebMethod
    public int sum(int a, int b) {
    return a + b;
    }
    
    }

プロジェクトへの依存関係として`org.osgi.core`および`org.osgi.service.component.annotations`も含めるようにしてください。

