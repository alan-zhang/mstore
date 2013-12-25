/**
 * Created by azhang6 on 13-12-20.
 */
define(['postal'], function (postal) {
  (function factory(root) {
    var _constants = {
      channels: {
        SHOW_TIME: 'showtime'
      },

      topics: {
        AUTH_SHOW: 'auth.show',
        CLEAR_TEXT: 'auth.clear',
        AUTH_REMOVE: 'auth.remove',
        AUTH_EDIT: 'auth.edit',
        AUTH_REFRESH: 'auth.refresh'
      }
    };

    if (root !== undefined) {
      root.bus = root.bus || _constants;
    }
  }(window));

  require(['authAdd']);
  require(['authShow']);
});