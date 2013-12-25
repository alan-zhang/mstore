define(['jquery', 'backbone', 'underscore', 'encrypt'], function($, Backbone, _){
	
	var LoginView = Backbone.View.extend({
		el: $('div.lg-panel'),
		
		events: {
            "click #login": "login",
            "keydown #password": "enter"
        },
		
		initialize: function () {
            $('#userName').focus();
        },
		
		enter: function(e) {
			if (e.keyCode === 13) {
				this.login();
			}
		},
		
		login: function() {		   
			var info = new LoginInfo();
			var data = info.toData();  
			$.post("./perform-login", data, this.success);		    
		},
		
		success: function(data) {
			if (data.error != '') {
    			$('div.lg-error').text(data.error);
    		} else {
    			window.location = data.direct;
    		}
		}
	});
	
	var LoginInfo = Backbone.Model.extend({		
		toData: function() {
			var username= $("input[name='j_username']").val();  
		    var password= $.md5($("input[name='j_password']").val());
			return "j_username="+username+"&j_password="+password;
		}
	});
	
	
	return {
		render: function() {
			return new LoginView();
		}
	}
});
