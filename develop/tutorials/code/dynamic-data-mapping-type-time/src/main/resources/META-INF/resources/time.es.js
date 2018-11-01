import Component from 'metal-component';
import Soy from 'metal-soy';

import templates from './time.soy';

/**
 * Time Component
 */
class Time extends Component {}

// Register component
Soy.register(Time, templates, 'render');

if (!window.DDMTime) {
	window.DDMTime = {

	};
}

window.DDMTime.render = Time;

export default Time;