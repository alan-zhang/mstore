/*require.config({
    baseUrl: 'scripts/',
    paths: {
    	md: 'resources/md5',
        jquery: 'resources/jquery-1.8.2',
        jqueryui: 'resources/jquery-ui-1.8.24',
        backbone: 'resources/backbone',
        underscore: 'resources/underscore-min',
        jqTmpl: 'resources/jquery.tmpl.min',
        jqjson: 'resources/jquery.json-2.2',        
        login: 'auth/auth'        
    }
});*/
require.config({
    baseUrl: 'scripts/',
    paths: {    	
        jquery: "libs/jquery-1.8.2",
        encrypt: "libs/jquery.md5",
        backbone: "libs/backbone-1.1.0",
        underscore: "libs/underscore-min",
        login: "auth/auth"
    },    
    shim: {
    	underscore: {
    		exports: '_'
    	},
    	backbone: {
    		deps: ['underscore'],
    		exports: 'Backbone'
    	}    	
    }
});

require(['jquery', 'login'], function ($, login) {

    $(document).ready(function () {
        login.render();
    });
    
});