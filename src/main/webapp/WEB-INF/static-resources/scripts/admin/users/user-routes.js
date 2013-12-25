define(['backbone'], function(Backbone){
	return Backbone.Router.extend({
		routes: {
			'add' : 'openAddPanel',
			'edit/:id' : 'openEditPanel'
		},
		
		openAddPanel: function() {
			this.openPanel(new User());
		},
		
		openEditPanel: function(id) {
			alert(id);
			//var user = users.get(id)            
            //this.openPanel(user);
		},
		
		openPanel: function(user) {
			var editPanel = new UserEditPanel(user);
            editPanel.render();
		}
	});
});