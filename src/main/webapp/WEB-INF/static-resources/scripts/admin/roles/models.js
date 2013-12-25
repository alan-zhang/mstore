define(function(){
    
    RolesApp.Role = DS.Model.extend({
        createTime: DS.attr('date'),
        isBackend: DS.attr('number'),
        isValid: DS.attr('number'),
        roleDesc: DS.attr('string'),
        roleName: DS.attr('string')
    });
	
	
});
