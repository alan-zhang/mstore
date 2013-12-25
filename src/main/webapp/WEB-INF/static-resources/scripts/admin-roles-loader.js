require.config({
  baseUrl: 'scripts/',
  paths: {
    app: "admin/roles/app",
    model: "admin/roles/models",
    router: "admin/roles/router",
    controller: "admin/roles/controller",
    alert: "common/my-alert",
    jquery: "libs/jquery-1.10.2",
    handlebars: "libs/handlebars-1.1.2",
    ember: "libs/ember-1.2.0",
    emberData: "libs/ember-data",
    emberLocalstorageAdapter: "libs/ember-localstorge-adapter",
  },
  shim: {
    ember: {
      deps: ['handlebars'],
      exports: 'Ember'
    },
    emberData: {
      deps: ['ember'],
      exports: 'DS'
    },
  }
});

require(['jquery'], function ($) {

  $(document).ready(function () {
    require(['app']);
    //require(['model']);
    require(['router']);
    require(['controller']);

  });

});