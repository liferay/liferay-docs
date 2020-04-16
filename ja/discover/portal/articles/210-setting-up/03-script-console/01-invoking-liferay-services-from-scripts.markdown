---
header-id: invoking-liferay-services-from-scripts
---

# スクリプトからLiferayサービスを呼び出す

[TOC levels=1-4]

多くのスクリプトシナリオでは、Liferayサービスを呼び出す必要があります。 [Liferay `* ServiceUtil` クラス](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/) は、 [スクリプトコンソール](/docs/7-1/user/-/knowledge_base/u/running-scripts-from-the-script-console)でLiferayサービスを呼び出す最も速くて最も便利な方法です。 Groovyを使用して、Javaを使用するのと同じ方法でLiferayサービスを呼び出すことができます。 Groovyの構文は、簡潔でエレガントなスクリプトの作成を容易にします。

この最初の例は、Liferayサービスと対話するための正しい構文を示しています。 `UserLocalServiceUtil` を使用してユーザーのリストを取得し、その名前をLiferayのログに出力します。 これを行うには、次のようなJavaコードを使用してモジュールをデプロイします。

    import com.liferay.portal.kernel.model.User;
    import com.liferay.portal.kernel.service.UserLocalServiceUtil;
    import java.util.List;
    
    ...
    
    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    }
    
    ...

または、JavaベースのGroovyを使用して、同じコードで [スクリプトコンソール](/docs/7-1/user/-/knowledge_base/u/running-scripts-from-the-script-console) からすべてを実行することもできます。

``` 
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import java.util.List;

int userCount = UserLocalServiceUtil.getUsersCount();
List<User> users = UserLocalServiceUtil.getUsers(0, userCount);

for (User user:users) {
    System.out.println("User Name: " + user.getFullName());
} 
```

コードを多少Groovierにすることもできます。

    import com.liferay.portal.kernel.service.UserLocalServiceUtil
    
    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)
    for (user in users){
        System.out.println("User Name: " + user.getFullName())
    }

Liferayサービスを呼び出すGroovyスクリプトは、スクリプトコンソールで簡単に記述して実行できます。

スクリプトの実行方法は次のとおりです。

## 関連トピック

[スクリプトコンソールからのスクリプトの実行](/docs/7-1/user/-/knowledge_base/u/running-scripts-from-the-script-console)

[ワークフローでのスクリプトエンジンの活用](/docs/7-1/user/-/knowledge_base/u/leveraging-the-script-engine-in-workflow)

[スクリプトの例](/docs/7-1/user/-/knowledge_base/u/script-examples)
