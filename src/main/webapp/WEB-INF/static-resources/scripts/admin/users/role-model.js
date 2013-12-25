define(['backbone'], function(){
	return Backbone.Model.extend({
		defaults: {
			roleName: '',
			roleDesc: '',
			createTime: '',
            isBackend: 1,
            isValid: 1
		},
		urlRoot: './service/roles'

	});
});