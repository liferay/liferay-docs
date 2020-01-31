---
header-id: customizing-liferay-services-service-wrappers
---

# Liferayサービスのオーバーライド（サービスラッパー）

[TOC levels=1-4]

Liferayサービスをカスタマイズする必要があるのはなぜですか？Liferayの`User`オブジェクトに新しいフィールドを追加していて、LiferayのAPIの`addUser`または`updateUser` メソッドが呼び出されるたびにその値が保存されるようにしたいのでしょうか。あるいは、[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を使用して構築された一部のLiferay APIまたはその他のサービスに、追加のロギング機能を追加したいのでしょうか。
どちらの場合でも、Liferayのサービスラッパーでは、Liferayのサービスをカスタマイズするための使いやすい拡張ポイントが提供されます。

Liferayのサービスの1つをオーバーライドするモジュールを作成するには、[Service Wrapper Template](/docs/7-1/reference/-/knowledge_base/r/using-the-service-wrapper-template)の参照記事に従って`servicewrapper`プロジェクトタイプを作成します。

例として、サービスラッパーテンプレートのチュートリアルで生成された`UserLocalServiceOverride`クラスを以下に示します。

    package com.liferay.docs.serviceoverride;
    
    import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
    import com.liferay.portal.kernel.service.ServiceWrapper;
    import org.osgi.service.component.annotations.Component;
    
    @Component(
    immediate = true,
    property = {
    },
    service = ServiceWrapper.class
    )
    public class UserLocalServiceOverride extends UserLocalServiceWrapper {
    
    public UserLocalServiceOverride() {
    super(null);
    }
    
    }

拡張するサービスラッパークラスの完全修飾クラス名を指定する必要があることに注意してください。`service`引数は次のimportステートメントで完全に使用されました。

    import com.liferay.portal.service.UserLocalServiceWrapper

次に、このimportステートメントにより、コンポーネントクラスのクラス宣言で短い形式のサービスラッパークラス名を使用できるようになりました。

    public class UserLocalServiceOverride extends UserLocalServiceWrapper

一番下の行は、`blade create`を使用してサービスラッパープロジェクトを作成する場合に、`service`引数として完全修飾クラス名を指定する必要があることを示しています。（これは`blade create`を使用してサービスプロジェクトを作成する場合にも当てはまります。）サービスプロジェクトの作成については、[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)チュートリアルを参照してください。

生成された`UserLocalServiceOverride`クラスは、実際にはLiferayサービスをカスタマイズしていません。サービスラッパーモジュールが実際に機能するかテストする前に、少なくとも1つのサービスメソッドをオーバーライドする必要があります。

`UserLocalServiceOverride`クラスを開き、次のメソッドを追加します。

    @Override
    public int authenticateByEmailAddress(long companyId, String emailAddress,
    String password, Map<String, String[]> headerMap,
    Map<String, String[]> parameterMap, Map<String, Object> resultsMap)
    throws PortalException {
    
    System.out.println(
    "Authenticating user by email address " + emailAddress);
    return super.authenticateByEmailAddress(companyId, emailAddress, password,
    headerMap, parameterMap, resultsMap);
    }
    
    @Override
    public User getUser(long userId) throws PortalException {
    System.out.println("Getting user by id " + userId);
    return super.getUser(userId);
    }

これらの各メソッドは、Liferayサービスメソッドをオーバーライドします。これらの実装では、元のサービス実装を実行する前に、いくつかのprintステートメントのみが実行されます。

最後に、サービスラッパーの下部に次のメソッドを追加して、デプロイ時にオーバーライドする適切なサービスを見つけられるようにする必要があります。

    @Reference(unbind = "-")
    private void serviceSetter(UserLocalService userLocalService) {
    setWrappedService(userLocalService);
    }

[モジュールをビルドしてデプロイします](/docs/7-0/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)。
Liferayサービスラッパーを作成して展開する方法がわかりました。

## 関連トピック

[Upgrading Service Wrappers](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-wrappers)

[Installing Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/installing-blade-cli)

[Creating Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)
