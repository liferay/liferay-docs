'use strict';

import Sequence from './sequence.es';

function* squareGenerator(){
	let currentVal = 0;
	let increase = -1;

	while (true){
		increase += 2;
		currentVal += increase;
		yield currentVal;
	}
}

class SquareSequence extends Sequence {
	constructor() {
		super(squareGenerator, 'Square');
	}
}

export default SquareSequence;