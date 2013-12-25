define(['backbone'], function(Backbone){
	var Alert = Backbone.View.extend({
		el: $('#alert'),
		alertType: 'alert-info',
		
		events: {
			'click .close': 'close'
		},
		
		render: function(options) {
			this.alertType = options.alertType;
			this.$el.fadeIn().addClass(options.alertType);
			this.$el.find('div').text(options.text);
		},
		
		close: function() {
			this.$el.removeClass(this.alertType).fadeOut();
		}
	});
	
	var alert = new Alert();
	
	return {
		show: function(options) {
			alert.render(options);
		},
		
		showSuccess: function(text) {
			this.show({
				alertType: 'alert-success',
				text: text
			});
		},
		
		showError: function(text) {
			this.show({
				alertType: 'alert-danger',
				text: text
			});
		}
	}
});