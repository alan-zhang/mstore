/**
 * Created by azhang6 on 13-12-19.
 */
define('authAdd', ['jquery', 'postal', 'auth'], function ($, postal, Auth) {
  var authAdd = (function(selector){
    var $addPanel = $(selector);

    $addPanel.on('click', 'button', function(){
      var auth = new Auth($('#name').val(), $('#desc').val());
      auth.save();
    });

    var _clear = function() {
      $('#name').val('');
      $('#desc').val('')
    };

    return {
      clear: _clear
    }

  }('div.ll-auth'));

  postal.channel(bus.channels.SHOW_TIME).subscribe(bus.topics.CLEAR_TEXT, authAdd.clear);

  return authAdd;
});