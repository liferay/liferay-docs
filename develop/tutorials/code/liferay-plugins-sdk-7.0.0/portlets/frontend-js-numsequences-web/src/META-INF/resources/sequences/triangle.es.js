'use strict';

import Sequence from './sequence.es';

function* triangleGenerator(){
	let currentVal = 0;
	let increase = 0;

	while (true){
		currentVal += (++increase);
		yield currentVal;
	}
}

class TriangleSequence extends Sequence {
	constructor() {
		super(triangleGenerator, 'Triangle');
	}
}

export default TriangleSequence;