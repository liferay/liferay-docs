'use strict';

import {fibonacci as FibonacciSeq, prime as PrimeSeq, square as SquareSeq, triangle as TriangleSeq} from 'js-numsequences/collection.es';
import d3 from '../d3.min';

const CHART_COLORS = ['blue', 'red', 'green', 'purple'];
const CHART_MARGINS = { top: 20, right: 20, bottom: 20, left: 50 };

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

	print(width = 600, height = 400, margins = CHART_MARGINS, colors = CHART_COLORS) {
		let data = this._getSequences();

		this.container.innerHTML = `<svg class="d3" height="400" width="600"></svg>`;

		let chart = d3.select(this.container.querySelector('.d3')),
			xScale = d3.scale.linear().range([margins.left, width - margins.right]).domain([0, 30]),
			yScale = d3.scale.linear().range([height - margins.top, margins.bottom]).domain([0, 2000]),
			xAxis = d3.svg.axis().scale(xScale),
			yAxis = d3.svg.axis().scale(yScale).orient('left');

		chart.append('svg:g')
			.attr('class', 'x axis')
			.attr('transform', 'translate(0,' + (height - margins.bottom) + ')')
			.call(xAxis);

		chart.append('svg:g')
			.attr('class', 'y axis')
			.attr('transform', 'translate(' + (margins.left) + ',0)')
			.call(yAxis);

		let lineGen = d3.svg.line()
			.x(function(d) { return xScale(d.iteration);})
			.y(function(d) { return yScale(d.value);})
			.interpolate('basis');

		Object.keys(this._sequences).forEach(function(sequenceName, sequenceIndex) {
			chart.append('svg:path')
				.attr('d', lineGen(data[sequenceName]))
				.attr('stroke', colors[sequenceIndex%4])
				.attr('stroke-width', 2)
				.attr('fill', 'none');
		});
	}

	_getSequences() {
		let sequences = {
			fibonacci: [],
			prime: [],
			square: [],
			triangle: []
		};

		for (let i = 0; i < this.limit; i++) {
			Object.keys(this._sequences).forEach(
				s => sequences[s].push({
					iteration: i,
					value: this._sequences[s].next().value
				})
			);
		}

		return sequences;
	}
}

export default SequencePrinter;

