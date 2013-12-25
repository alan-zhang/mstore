define(['backbone', 'underscore'], function(){
	var event = {
		pageTurning: 'pageTurning',
		refreshUserView: 'refreshUserView'
	};
	_.extend(event, Backbone.Events);	
	
	return CustomEvent = event;
});