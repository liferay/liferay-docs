# Forms and Validation [](id=forms-and-validation)

Form, input, and validation tags are provided in Portal, to make creating forms easy and flexible.

The `<aui:form>` taglib: setups the necessary code (HTML, JS), utilizes Liferay JavaScript APIs, calls the validation framework, and submits the form data to the back-end.

The "input" taglibs (`<aui:input>`, `<liferay-ui:input-*>`, etc.) generate the necessary code (HTML, CSS, JS) to keep a consistent form UI. These taglibs also initialize the input's state, eg. `value`, `disabled`, `readonly`, etc.

The `<aui:validator>` taglib pairs with some input taglibs, to provide validation rules for those inputs. This way, you can ensure your users are entering proper data, formated the way you expect, before it gets sent to the back-end for final validation and processing.

The inputs that support validators natively are: `<aui:input>`, `<aui:select>`, `<liferay-ui:input-date>`, and `<liferay-ui:input-search>`. All other inputs can be validated using the method hightlighted in [Add Additional Validation Via JavaScript](#add-additional-validation-via-javascript), below.

## Creating Your First Form [](id=creating-your-first-form)

We'll start by adding a simple form.

```jsp
<aui:form action="<%= myActionURL %>" method="post" name="myForm">
	<aui:input label="My Text Input" name="myTextInput" type="text" value='<%= "My Text Value" %>' />

	<aui:button type="submit" />
</aui:form>
```

Nothing to complex about this. But, it ends up outputting all the "magic" that's needed, to pass the data to the back-end.

```html
<form action="http://localhost:8080/web/guest/home?p_p_id=my_portlet&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;p_p_col_id=column-1&amp;p_p_col_count=3&amp;_my_portlet_javax.portlet.action=%2Fmy%2Faction&amp;_my_portlet_mvcRenderCommandName=%2Fmy%2Faction&amp;p_auth=WAxorpsN" class="form" data-fm-namespace="_my_portlet_" id="_my_portlet_myForm" method="post" name="_my_portlet_myForm">
	<input class="field form-control" id="_my_portlet_formDate" name="_my_portlet_formDate" type="hidden" value="1472516415545">

	<div class="form-group input-text-wrapper">
		<label class="control-label" for="_my_portlet_myTextInput">My Text Input</label>

		<input class="field form-control" id="_my_portlet_myTextInput" name="_my_portlet_myTextInput" type="text" value="My Text Value">
	</div>

	<button class="btn  btn-primary btn-default" id="_my_portlet_kpyg" type="submit">
		<span class="lfr-btn-label">Save</span>
	</button>
</form>
```

Glad we didn't have to write all that; right?

## Adding Validation [](id=adding-validation)

Now, what if we want to ensure the user enters the required data? We'd then add validators.

```jsp
<aui:input label="My Text Input" name="myTextInput" type="text" value='<%= "My Text Value" %>'>
	<aui:validator name="required" />
</aui:input>
```

This will force the user to enter something into the input, before the form is submitted.

![Figure 1: An input with a failed required validator.](../../images/forms-input-required.png)

But lets say we wanted to restrict the value to a number between `0` and `10`. We can add additional validators. Each one will need to pass, in order for the form to submit.

```jsp
<aui:validator name="required" />
<aui:validator name="number" />
<aui:validator name="range">[0,10]</aui:validator>
```

We can even customize the error message.

```jsp
<aui:validator errorMessage="Please enter how many fingers you have." name="range">[0,10]</aui:validator>
```

![Figure 2: A required validator with a custom error message.](../../images/forms-input-required-message.png)

There's a wide variety of build-in validators available. Check the [full documentation](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/using-the-alloyui-validator-tag#available-validation-rules) for details.

## Conditionally Requiring an Input [](id=conditionally-requiring-an-input)

Sometimes you'll want to validate an input, based on the value of another input.

You can do this by checking for that condition in a JavaScript function, in the `required` validator's body.

```jsp
<aui:input label="My Checkbox" name="myCheckbox" type="checkbox" />

<aui:input label="My Text Input" name="myTextInput" type="text">
	<aui:validator name="required">
		function() {
			return AUI.$('#<portlet:namespace />myCheckbox').prop('checked');
		}
	</aui:validator>
</aui:input>
```

![Figure 3: Inputs can required based on other conditions.](../../images/forms-input-required-condition.png)

## Adding Custom Validators [](id=adding-custom-validators)

So far, we've only been using validator rules which come with AUI. But suppose you need something a little more custom or advanced.

You can write your own validator, and optionally supplement it with built-in validators.

```jsp
<aui:input label="Email" name="email" type="text">
	<aui:validator name="email" />

	<aui:validator errorMessage="Only emails from @example.com are allowed." name="custom">
		function(val, fieldNode, ruleValue) {
			var regex = new RegExp(/@example\.com$/i);

			return regex.test(val);
		}
	</aui:validator>
</aui:input>
```

This example runs the regular `email` validator, as well as your `custom` domain validator.

![Figure 4: You can write your own custom validations.](../../images/forms-input-custom.png)

## Adding Custom Validators in JavaScript [](id=adding-custom-validators-in-javascript)

Sometimes you need to dynamically add additional validation, after the page has rendered. Maybe some additional inputs were added to the DOM, via an AJAX request.

To do this, you'll need to access the `Liferay.Form` object.

```jsp
<aui:script use="liferay-form">
	var form = Liferay.Form.get('<portlet:namespace />myForm');
	// ...
```

With the `Liferay.Form` object, you can now `get()` and `set()` `fieldRules`.

`fieldRules` are the JavaScript equivalent of all the validators attached to the form.

This example uses a `custom` validator and the built-in `number` validator.

```jsp
<aui:script use="liferay-form">
	var form = Liferay.Form.get('<portlet:namespace />myForm');

	var oldFieldRules = form.get('fieldRules');

	var newFieldRules = [
		{
			body: function (val, fieldNode, ruleValue) {
				return (val !== '2');
			},
			custom: true,
			errorMessage: 'must-not-equal-2',
			fieldName: 'fooInput',
			validatorName: 'custom_fooInput'
		},
		{
			fieldName: 'fooInput',
			validatorName: 'number'
		}
	];

	var fieldRules = oldFieldRules.concat(newFieldRules);

	form.set('fieldRules', fieldRules);

</aui:script>
```

Notice that `newFieldRules` are combined with `oldFieldRules`, using `concat()`. We could leave this part out, and we'd have a whole new set of validators, for the whole form.

## Manual Validation

Lastly, you may need to execute validation on an input, based off some event, not typical of user input. Maybe validate a related input, at the same time.

You can do this by accessing the `formValidator` object and calling the `validateField()` method, passing the input's `name`.

```jsp
	<aui:input label="Old Title" name="oldTitle" type="text">
		<aui:validator errorMessage="The New Title cannot match the Old Title" name="custom">
			function(val, fieldNode, ruleValue) {
				<portlet:namespace />checkOtherTitle('<portlet:namespace />newTitle');

				// check if old and new titles are a match

				return !match;
			}
		</aui:validator>
	</aui:input>

	<aui:input label="New Title" name="newTitle" type="text">
		<!-- same custom validator -->
	</aui:input>

<aui:script use="liferay-form">
	function <portlet:namespace />checkOtherTitle(fieldName) {
		var formValidator = Liferay.Form.get('<portlet:namespace />myForm').formValidator;

		formValidator.validateField(fieldName);
	}
</aui:script>
```