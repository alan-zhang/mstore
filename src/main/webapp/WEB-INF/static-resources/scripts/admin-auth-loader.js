/**
 * Created by azhang6 on 13-12-19.
 */
require.config({
  baseUrl: 'scripts/',
  paths: {
    alert: "common/my-alert",
    pagination: "common/pagination-amd",
    jquery: "libs/jquery-1.8.2",
    encrypt: "libs/jquery.md5",
    underscore: "libs/underscore-min",
    jqTmpl: "libs/jquery.tmpl.min",
    postal: "libs/postal",
    authAdd: "admin/auth/auth-add",
    authShow: "admin/auth/auth-show",
    initializer: "admin/auth/auth-initialize",
    auth: "admin/auth/auth-model",
    auths: "admin/auth/auth-model"
  },
  shim: {
    underscore: {
      exports: '_'
    }
  }
});

require(['jquery'], function ($) {

  $(document).ready(function () {
    require(['initializer']);
  });

});