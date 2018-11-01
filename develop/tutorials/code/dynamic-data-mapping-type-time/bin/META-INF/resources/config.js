;(function() {
	AUI().applyConfig(
		{
			groups: {
				'field-time': {
					base: MODULE_PATH + '/',
					combine: Liferay.AUI.getCombine(),
					filter: Liferay.AUI.getFilterConfig(),
					modules: {
						'liferay-ddm-form-field-time': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'time_field.js',
							requires: [
								'aui-timepicker','liferay-ddm-form-renderer-field'
							]
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();