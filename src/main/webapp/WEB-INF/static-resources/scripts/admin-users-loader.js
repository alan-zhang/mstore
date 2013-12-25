require.config({
    baseUrl: 'scripts/',
    paths: {
        alert: "common/my-alert",
        pagination: "common/pagination-amd",
        events: "common/events",
        jquery: "libs/jquery-1.8.2",
        encrypt: "libs/jquery.md5",
        backbone: "libs/backbone-1.1.0",
        underscore: "libs/underscore-min",
        jqTmpl: "libs/jquery.tmpl.min",
        userAdmin: "admin/users/user-admin",
        roleModel: "admin/users/role-model",
        role: "admin/users/role-model",
        roles: "admin/users/roles-model",
        roleView: "admin/users/role-view",
        userView: "admin/users/user-view",
        user: "admin/users/user-model",
        users: "admin/users/users-model",
        userEditView: "admin/users/user-edit-view",
        userRoutes: "admin/users/user-routes",
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

require(['jquery'], function ($) {

    $(document).ready(function () {
        require(['userAdmin']);
    });

});