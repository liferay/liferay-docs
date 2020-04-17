---
header-id: evaluating-a-rule
---

# ルールの評価

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

管理者がカスタムルールを正常に構成してユーザーセグメントに保存した後、ルールはその目的を果たす必要があります。つまり、事前設定された気象値を、サイトにアクセスするユーザーの気象値と比較して評価します。 ユーザーの値が（セグメントの他のルールとともに）事前設定値と一致する場合、そのユーザーはユーザーセグメントに追加されます。

1.  評価プロセスを開始するには、 `evaluate（...）` ルールを実装する必要があります。 このメソッドは、ユーザーセグメンテーションライフサイクルの一部です。 ページが読み込まれると、Liferayはルールの `evaluate` メソッドを呼び出して、現在のユーザーがユーザーセグメントに属しているかどうかを判断します。 気象ルールの場合、この `評価` メソッドを追加し` 。
<pre><code class="java">    @Override
    public boolean evaluate(
            HttpServletRequest request, RuleInstance ruleInstance,
            AnonymousUser anonymousUser)
        throws Exception {

        String userWeather = getUserWeather(anonymousUser);

        String weather = ruleInstance.getTypeSettings();

        if (Validator.equals(userWeather, weather)) {
                return true;
        }

        return false;
    }
`</pre>

    ユーザーの天気を取得するには、後で定義する `getUserWeather` メソッドを呼び出します。 次に、ルールインスタンスの `typeSettings` パラメータにアクセスして、事前設定の気象値を取得します。 最後に、2つの値を比較します。 一致する場合、 `true`返します。それ以外の場合は `false`返します。 ユーザーがユーザーセグメントに追加されるのは、ユーザーセグメントのすべてのルールがtrueを返す場合のみです。

2.  次に、ユーザーの天気を取得する必要があります。 前に学んだように、まずユーザーの場所にアクセスする必要があります。 これを行うには、以下のロジックを追加します。

    ``` java
    protected String getCityFromUserProfile(long contactId, long companyId)
        throws PortalException, SystemException {

        List<Address> addresses = AddressLocalServiceUtil.getAddresses(companyId, Contact.class.getName(), contactId);

        if (addresses.isEmpty()) {
            return null;
        }

        Address address = addresses.get(0);

        return address.getCity() + StringPool.COMMA + address.getCountry().getA2();
    }
    ```

    このメソッドは、ユーザーのプロファイル情報にアクセスして場所を取得します。 また、地理位置情報サービスを使用して、ユーザーのIPアドレスでこれを見つけることもできます。 ユーザーの場所がわかったら、その場所の現在の天気を見つけることができます。

3.  次のメソッドを追加して、ユーザーの天気予報を取得します。

    ``` java
    protected String getUserWeather(AnonymousUser anonymousUser)
        throws PortalException, SystemException {

        User user = anonymousUser.getUser();

        String city = getCityFromUserProfile(user.getContactId(), user.getCompanyId());

        Http.Options options = new Http.Options();

        String location = HttpUtil.addParameter(API_URL, "q", city);
        location = HttpUtil.addParameter(location, "format", "json");

        options.setLocation(location);

        int weatherCode = 0;

        try {
            String text = HttpUtil.URLtoString(options);

            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(text);

            weatherCode = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
        }
        catch (Exception e) {
            _log.error(e);
        }

        return getWeatherFromCode(weatherCode);
    }

    private static Log _log = LogFactoryUtil.getLog(WeatherRule.class);
    ```

    このメソッドは、 `getCityFromUserProfile` メソッドを呼び出して、ユーザーの場所を取得します。 次に、気象サービスからその場所の気象コードを取得します。

4.  `API_URL` フィールドをOpen Weather MapのAPI URLに設定します。

    ``` java
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    ```

    天気ルールについては、Open Weather MapのAPIにアクセスして、天気コードを取得できます。

5.  最後に、天気コードを評価可能な文字列に変換します（例： `sunny`）。 Open Weather Mapの天気コードを変換するには、次のメソッドを追加します。

    ``` java
    protected String getWeatherFromCode(int code) {
        if (code == 800 || code == 801) {
            return "sunny";
        }
        else if (code > 801 && code < 805) {
            return "clouds";
        }
        else if (code >= 600 && code < 622) {
            return "snow";
        }
        else if (code >= 500 && code < 532) {
            return "rain";
        }

        return null;
    }
    ```

    可能なすべての天気コードは、ここでは [です](http://openweathermap.org/weather-conditions)。

優秀な\！ `evaluate` メソッドを実装し、 `-Rule` クラスに必要なロジックを追加して、ユーザーのローカル天気を取得しました。 気象ルールの動作が定義され、完全です。 あなたがする必要がある最後のことは、JSPテンプレートを作成することです。
