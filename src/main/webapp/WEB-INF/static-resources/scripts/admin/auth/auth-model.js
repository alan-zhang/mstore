/**
 * Created by azhang6 on 13-12-23.
 */
define('auth', ['jquery', 'postal'], function ($, postal) {
  var Auth = function (authName, authDesc) {
    this.authName = authName;
    this.authDesc = authDesc;

    this.save = function () {
      if (this.authName === '') return;

      var data = {authorityName: this.authName, authorityDesc: this.authDesc};
      $.post('./service/auths', JSON.stringify(data), function (id) {
        if (id != -1) {
          data.id = id;
          postal.channel(bus.channels.SHOW_TIME).publish(bus.topics.AUTH_SHOW, data);
        }
      });
    };
  };

  return Auth;
});

define('auths', ['jquery', 'postal'], function ($, postal) {
  var Auths = function () {
    this.auths = null;

    var _fetch = function (options) {
      $.getJSON('./service/auths', function (data) {
        if (data.length > 0) {
          this.auths = data;
          options.success(data);
        }
      });
    };

    var _remove = function (id, options) {
      $.ajax({
        url: './service/auths/' + id,
        type: 'DELETE',
        success: function() {
          postal.channel(bus.channels.SHOW_TIME).publish(bus.topics.AUTH_REFRESH);
        }
      });
    };

    return {
      load: _fetch,
      remove: _remove
    }
  }

  return Auths;
});