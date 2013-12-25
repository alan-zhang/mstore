define(['backbone', 'role'], function(Backbone, Role){
	return Backbone.Collection.extend({
		model: Role,
		
		mixin: function(objs) {
			_.extend(this, objs);
		}
	});
});
