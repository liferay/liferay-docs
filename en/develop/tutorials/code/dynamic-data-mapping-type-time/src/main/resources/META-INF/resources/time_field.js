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
					render: function() {
						var instance = this;

						TimeField.superclass.render.apply(instance, arguments);

						instance.timePicker = new A.TimePicker(
							{
								trigger: instance.getInputSelector(),
								mask: instance.get('mask'),
								popover: {
									zIndex: 1
								},
								after: {
									selectionChange: A.bind('afterSelectionChange', instance)
								}
							}
						);
					},

					afterSelectionChange: function(event) {
						var instance = this;

						var time = event.newSelection;

						instance.set('value', time);
					},

					getTemplateContext: function() {
						var instance = this;

						return A.merge(
							TimeField.superclass.getTemplateContext.apply(instance, arguments),
							{
								placeholder: instance.get('placeholder')
							}
						);
					},
				}
			}
		);

		Liferay.namespace('DDM.Field').Time = TimeField;
	},
	'',
	{
		requires: ['aui-timepicker','liferay-ddm-form-renderer-field']
	}
);