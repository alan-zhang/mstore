

define(['ember', 'emberData', 'handlebars'], 
		function(Ember, DS){
			
	window.RolesApp = Ember.Application.create();
	
	RolesApp.ApplicationAdapter = DS.RESTAdapter.extend({		
		host: 'http://localhost:8080/lily',
		namespace: 'service'
	});
	
	RolesApp.ApplicationSerializer = DS.RESTSerializer.extend({
		extractArray: function(store, primaryType, payload) {
			console.log(payload);
			var roles = [];
			payload.forEach(function(item){
				roles.push(item);
			});
			payload = {role: roles};
			
			return this._super(store, primaryType, payload);
		}
	});
	
	RolesApp.Role = DS.Model.extend({
		
        createTime: DS.attr('date'),
        isBackend: DS.attr('number'),
        isValid: DS.attr('number'),
        roleDesc: DS.attr('string'),
        roleName: DS.attr('string')
    });
	
	return RolesApp;	
});