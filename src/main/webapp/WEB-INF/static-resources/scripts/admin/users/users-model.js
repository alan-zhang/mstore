define(['backbone', 'user'], function(Backbone, User){
	return Backbone.Collection.extend({
        model: User,
        url: './service/users'
    });
});