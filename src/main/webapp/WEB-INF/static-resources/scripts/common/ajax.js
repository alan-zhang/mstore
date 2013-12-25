define(['jquery'], function(){
	return {
		get: function(url, success) {
			$.get(url, success);
		},
		
		post: function(url, data, success) {
			$.post(url, data, success);
		}
	};
});