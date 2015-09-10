'use strict';

import Sequence from './sequence.es';

function* primesGenerator() {
	var seq = numbers(2);
	var prime;

	while (true) {
		prime = seq.next().value;
		yield prime;
		seq = filter(seq, prime);
	}
}

function* numbers(start) {
	while (true) {
		yield start++;
	}
}

function* filter(seq, prime) {
	for (var num of seq) {
		if (num % prime !== 0) {
			yield num;
		}
	}
}

class PrimeSequence extends Sequence {
	constructor() {
		super(primesGenerator, 'Primes');
	}
}

export default PrimeSequence;