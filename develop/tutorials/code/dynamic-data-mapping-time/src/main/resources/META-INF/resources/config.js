;(function() {
	AUI().applyConfig(
		{
			groups: {
				'field-time': {
					base: MODULE_PATH + '/',
					combine: Liferay.AUI.getCombine(),
					modules: {
						'liferay-ddm-form-field-time': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'time_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-time-template': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'time.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: MODULE_PATH + '/'
				}
			}
		}
	);
})();