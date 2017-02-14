var bar;
window.onload = function onLoad() {
	bar = new ProgressBar.Circle(progressbarGamma, {
		color: '#aaa',
		strokeWidth: 1,
		trailWidth: 1,
		easing: 'easeInOut',
		duration: 1400,
		text: {
			autoStyleContainer: true
		},
		from: { color: '#aaa', width: 1 },
		to: { color: '#333', width: 1 },
		// Set default step function for all animate calls
		step: function(state, circle) {
			circle.path.setAttribute('stroke', state.color);
			circle.path.setAttribute('stroke-width', state.width);
			
			var value = Math.round(circle.value() * 100);
			if (value === 0) {
				circle.setText('0%');
			} else {
				circle.setText(value + '%');
			}
			
		}
	});
	bar.text.style.fontFamily = '"Raleway", Helvetica, sans-serif';
	bar.text.style.fontSize = '2rem';
	
	bar.set(0);
	bar.animate(1);
	bar.animate(0);  // Number from 0.0 to 1.0	
}

function createGammaIndicator(percGamma) {
	bar.animate(percGamma);  // Number from 0.0 to 1.0	
}