define(['ember', 'app'], function(Ember, RolesApp){
		
	RolesApp.Router.map(function(){
		this.resource('roles', { path: '/'});
		this.route('role', {path:'new-role'});
	});
	
	RolesApp.RolesRoute = Ember.Route.extend({
		
		
		model: function() {
			var st = this.get('store');
			return this.store.find('role');
			
			/*return Ember.$.getJSON('./service/roles').then(function(data){
				return data;
			});*/
			//var store = this.get('store');
			
			//return this.store.find('role');
			//return RolesApp.Role.find();			
		},
		
		setupController: function(controller, model) {
			controller.set('model', model);	
		},
		
	});
});