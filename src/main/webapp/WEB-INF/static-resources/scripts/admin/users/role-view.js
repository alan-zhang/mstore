define(['backbone', 'roles', 'role'], function(Backbone, Roles, Role){
	return Backbone.View.extend({
		roles: null,
		parent: null,
		userOwnRoles: null,
		
		events: {
			'change input': 'checkboxChangeEvent'
		},
		
		initialize: function(parent, userId) {			
			this.parent = parent;
			this.userId = userId;
			this.createOwnRoles();			
			this.createAndLoadRoles();
		},
		
		createAndLoadRoles: function() {
			var _this = this;
			this.roles = new Roles();
			this.roles.mixin({url:'./service/roles'});
			this.roles.fetch({success: function(){
				_this.render();
			}});
		},
		
		createOwnRoles: function() {
			this.userOwnRoles = new Roles();
			this.userOwnRoles.mixin({url:'./service/roles/user-role'});
		},
		
		render: function() {
			var rolePanelTmpl = $.tmpl($('#rolePanel'), {roles: this.roles.toJSON()}).html();
			this.$el.html(rolePanelTmpl);
			this.parent.append(this.$el);
			
			this.fetchOwnRoles();
			return this;
		},
		
		fetchOwnRoles: function() {
			if (this.userId === undefined) return;
			
			var _this = this;
			this.userOwnRoles.fetch({data: {userId: this.userId}, success: function(){
				_this.userOwnRoles.each(function(item){
					var chk = _this.$el.find("input[name='"+item.get('roleName')+"']");
					if (chk != null && chk.length > 0) {
						chk.attr('checked', true);
						chk.attr('data-key', item.get('roleName'));
					}
				});
			}});
		},
		
		checkboxChangeEvent: function(e) {
			var $target = $(e.target);
			if ($target.attr('data-key') != '') {				
				$target.attr('data-key', '');
				this.removeRoleFromList($target);
			} else {
				$target.attr('data-key', $target.attr('name'));
				this.addRoleToList($target);
			}			
		},
		
		addRoleToList: function($item) {
			var role = new Role();
			role.set({id:$item.attr('id'), roleName: $item.attr('name'), roleDesc:$item.attr('data-desc')});
			this.userOwnRoles.add(role);
		},
		
		removeRoleFromList: function($item) {
			var role = this.userOwnRoles.get($item.attr('id'));
			this.userOwnRoles.remove(role);
		}
	});
});
