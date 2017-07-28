AUI.add(
	'liferay-ddm-form-field-time',
	function(A) {
		var TimeField = A.Component.create(
			{
				ATTRS: {
					mask: {
						value: '%I:%M %p'
					},

					placeholder: {
						value: ''
					},

					type: {
						value: 'time'
					}
				},

				EXTENDS: Liferay.DDM.Renderer.Field,

				NAME: 'liferay-ddm-form-field-time',

				prototype: {

					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							TimeField.superclass.getTemplateContext.apply(instance, arguments),
							{
								placeholder: instance.get('placeholder')
							}
						);
					},

					render: function() {
						var instance = this;

						TimeField.superclass.render.apply(instance, arguments);

						instance._timePicker = new A.TimePicker(
							{
								trigger: instance.getInputSelector(),
								mask: instance.get('mask'),
								popover: {
									zIndex: 1
								}
							}
						);
					}

				}
			}
		);

		Liferay.namespace('DDM.Field').Time = TimeField;
	},
	'',
	{
		requires: ['aui-timepicker', 'liferay-ddm-form-renderer-field']
	}
);