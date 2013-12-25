define(['backbone', 'alert'], function(Backbone, al){
	return Backbone.Model.extend({
        defaults: {
            userName: '',
            realName: '',
            age: 0,
            sex: 1,
            email: '',
            phone: '',
            address: '',
            createTime: new Date(),
            isBackend: 1,
            isValid: 1
        },
		
		toJSON: function() {
			return _.extend(this.attributes, {
                createTime: new Date(this.attributes.createTime).toISOString('yyyy-MM-dd hh:mm:ss')
            });
		},
        
        urlRoot: './service/users',
        
		//Backbone的验证函数，内部会自动调用，会在save之前做这个验证，setter的时候并不会调用这个方法
        validate: function(attrs, options){
            if (attrs.userName === '') {
                return "登录名不能为空！";
            }			
			if (attrs.realName === '') {
                return "用户名不能为空！";
            }
			if (attrs.email === '') {
                return "电子邮箱不能为空！";
            }
        },
        
        initialize: function(){
			//验证错误的时候触发的事件，可以自定义错误事件
            this.on("invalid", function(model, error){
                console.log(error);
                al.show({
                    alertType: 'alert-danger',
                    text: error
                });
            });
        }
    });
});
