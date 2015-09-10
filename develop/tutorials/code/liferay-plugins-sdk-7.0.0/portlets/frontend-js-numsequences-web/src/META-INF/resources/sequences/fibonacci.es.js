'use strict';

import Sequence from './sequence.es';

function* fibonacciGenerator(){
	let val1 = 0, val2 = 1, swap;

	yield val1;
	yield val2;

	while (true){
		swap = val1 + val2;
		val1 = val2;
		val2 = swap;
		yield swap;
	}
}

class FibonacciSequence extends Sequence {
	constructor() {
		super(fibonacciGenerator, 'Fibonacci');
	}
}

export default FibonacciSequence;