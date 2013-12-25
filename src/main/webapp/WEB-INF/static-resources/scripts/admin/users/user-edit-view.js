define(['backbone', 'alert', 'roleView', 'events'], function (Backbone, al, RolePanel) {
  return Backbone.View.extend({
    user: null,
    rolePanel: null,

    //自定义事件，Backbone有自己的内部事件，一般View上的按钮事件需要在这里自定义
    events: {
      'keyup input': 'dataBinding',
      'change input.ll-edit-in': 'dataBinding',
      'change select': 'dataBinding',
      'click #save': 'save',
      'click #cancel': 'cancel'
    },

    initialize: function (user) {
      this.user = user;
      this.$el.html($.tmpl($('#userEdit'), user.toJSON()).html());
      this.rolePanel = new RolePanel(this.$el.find('div.ll-role'), this.user.get('id'));
    },

    render: function () {
      $('div.ll-edit-panel').html('').append(this.$el).show().fadeIn('slow');
      return this;
    },

    //双向绑定比较弱，需要自己手动写代码实现
    dataBinding: function (e) {
      var target = $(e.target);
      var name = target.attr('name');
      var val = target.val();
      this.user.set(name, val);
      console.log(name + ' = ' + this.user.get(name));
    },

    save: function () {
      console.log(this.user.url());

      var _this = this;
      //save方法必须加第一个参数，否则success回调方法触发不了
      this.user.save({}, {
        success: function (model, response) {
          //这里要自己写保存Role的方法
          if (response.success) {
            console.log(JSON.stringify(_this.rolePanel.userOwnRoles));
            var userId = _this.user.isNew() ? response.message : _this.user.get('id');
            $.post('./service/roles/user-role/' + userId, JSON.stringify(_this.rolePanel.userOwnRoles), function (data) {
              if (data) {
                _this.saveSuccess(userId);
              }
            });
          } else {
            al.showError(response.message);
          }
        }
      });
    },

    saveSuccess: function (userId) {
      al.showSuccess('保存成功');

      this.$el.remove();
      CustomEvent.trigger(CustomEvent.refreshUserView, this.user, userId);
    },

    cancel: function () {
      $('div.ll-edit-panel').html('');
    }
  });
});
