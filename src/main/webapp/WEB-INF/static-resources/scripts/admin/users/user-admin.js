define(['userView', 'userRoutes'], function(UserView, UserRoutes){
	(function(){
		var router = new UserRoutes();
				
		Backbone.history.start({
			pushState: true
		});
		
		new UserView();
	})();
    
})
