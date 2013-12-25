/**
 * Created by azhang6 on 13-12-19.
 */
define(['jquery', 'postal', 'auths', 'jqTmpl'], function ($, postal, Auths) {
  var showUI = (function (selector) {
    var template = $('#authList');
    var auths = new Auths();
    var $shows = $(selector);
    var _inner = {
      render: function (data) {
        $shows.empty();
        for (var i = 0, len = data.length; i < len; i++) {
          var d = data[i];
          $shows.append($.tmpl(template, d).html());
        }
        _inner.bindEvents();
      },
      bindEvents: function () {
        $(selector).on('click', 'a.del', function (e) {
          var id = $(e.target).attr('name');
          postal.channel(bus.channels.SHOW_TIME).publish(bus.topics.AUTH_REMOVE, id);
        });
      }
    };
    var _init = function () {
      auths.load({success: _inner.render});
    };
    var _addToList = function (auth) {
      $shows.append($.tmpl(template, auth).html());
      postal.channel(bus.channels.SHOW_TIME).publish(bus.topics.CLEAR_TEXT);
    };
    var _remove = function (id) {
      auths.remove(id);
    };
    var _refresh = function () {
      _init();
    };
    return {
      addToList: _addToList,
      remove: _remove,
      refresh: _refresh,
      init: _init
    }
  }('#auths'));
  showUI.init();
  postal.channel(bus.channels.SHOW_TIME).subscribe(bus.topics.AUTH_REMOVE, showUI.remove);
  postal.channel(bus.channels.SHOW_TIME).subscribe(bus.topics.AUTH_REFRESH, showUI.refresh);
  postal.channel(bus.channels.SHOW_TIME).subscribe(bus.topics.AUTH_SHOW, showUI.addToList).withConstraint(function (auth) {
    //filter the name is empty, and not add to the list
    return auth.authorityName !== '';
  });
  return showUI;
});