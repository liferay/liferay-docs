'use strict';

import {fibonacci as FibonacciSeq, prime as PrimeSeq, square as SquareSeq, triangle as TriangleSeq} from 'js-numsequences/collection.es';
import dragula from '../dragula.min';

class SequencePrinter {
	constructor(container, limit = 20) {
		this.container = container;
		this.limit = limit;

		this._sequences = {
			fibonacci: new FibonacciSeq(),
			prime: new PrimeSeq(),
			square: new SquareSeq(),
			triangle: new TriangleSeq()
		}
	}

	print() {
		let sequencesHeader = Object.keys(this._sequences).map(s => `<th>${this._sequences[s].label}</th>`);

		let tableHead = `<thead><tr><th><small>(Item)</small></th>${sequencesHeader.join('')}</tr></thead>`;
		let tableBody = `<tbody>${this._getSequencesContent()}</tbody>`;

		this.container.innerHTML = `<table class="table table-striped">${tableHead}${tableBody}</table>`;

		dragula([this.container.querySelector('tbody')]);
	}

	_getSequencesContent() {
		let sequencesContent = '';

		for (let i = 0; i < this.limit; i++) {
			let sequencesIterationData = Object.keys(this._sequences).map(s => `<td>${this._sequences[s].next().value}</td>`);

			sequencesContent += `<tr><th scope="row">${i + 1}</th>${sequencesIterationData.join('')}</tr>`
		}

		return sequencesContent;
	}
}

export default SequencePrinter;