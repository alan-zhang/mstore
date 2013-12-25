define(['ember', 'app'], function(Ember, RolesApp){
    RolesApp.RolesController = Ember.ArrayController.extend({
        newRoleName: '',
        newRoleDesc: '',
        
        
        actions: {
            createRole: function(){
                $('#add-panel').removeClass('ll-collapsing').fadeIn().addClass('ll-expand');                
                
            },
            
            hide: function(){
                var role = this.store.createRecord('role', {
                    roleName: this.newRoleName,
                    roleDesc: this.newRoleDesc,
                    createTime: new Date(),
                    isBackend: 1,
                    isValid: 1
                });
                
                console.log(role)
                role.save();
                this.set('newRoleName', '');
                this.set('newRoleDesc', '');
                $('#add-panel').removeClass('ll-expand').addClass('ll-collapsing');
            }
        }
    });
    
    RolesApp.RoleController = Ember.ObjectController.extend({
        isEditing: false,
        
        actions: {
            edit: function(id){
                console.log('RoleController: ' + id);
                var role = this.store.find('role', id);
                console.log(role);
                console.log(this.get('model').get('roleName'));
                this.set('isEditing', true);
                //alert('edit' + id);
            },
            
            save: function(id){
                this.set('isEditing', false);
				var s = this.store.find('role', id);
				console.log(s.get('roleName'));
            }
        }
    });
});
