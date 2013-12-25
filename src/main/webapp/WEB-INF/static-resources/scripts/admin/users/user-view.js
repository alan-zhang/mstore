define(['backbone', 'users', 'user', 'userEditView', 'pagination', 'events', 'jqTmpl'],
  function (Backbone, Users, User, UserEditPanel) {

    return Backbone.View.extend({
      el: $('div.ll-data'),
      users: null,
      initializePageInfo: {current: 1, pageCount: 4},

      events: {
        'click .ll-edit': 'openEditPanel',
        'click .ll-del': 'deleteUser',
        'click #newAdd': 'openEditPanel'
      },

      initialize: function () {
        var _this = this;
        //自定义事件，有利于摆脱苦命的回调函数
        CustomEvent.on(CustomEvent.pageTurning, this.generateTemplateContent, this);
        CustomEvent.on(CustomEvent.refreshUserView, this.refresh, this);

        this.users = new Users();
        this.users.fetch({
          success: function () {
            _this.render();
          }
        });
      },

      render: function () {
        this.generateTemplateContent(this.initializePageInfo);

        var pagination = new Pagination({
          total: this.users.length
        });
        pagination.render();

        return this;
      },

      generateTemplateContent: function (pageInfo) {
        var data = this.getDataByPaging(pageInfo);
        var tmpl = $.tmpl($('#userTable'), {users: data}).html();
        this.$el.empty().html(tmpl);
      },

      getDataByPaging: function (pageInfo) {
        var begin = (pageInfo.current - 1) * pageInfo.pageCount;
        var end = begin + pageInfo.pageCount;

        var subUsers = this.users.slice(begin, end);
        var datas = [];
        for (var i = 0, len = subUsers.length; i < len; i++) {
          datas.push(subUsers[i].toJSON());
        }

        return datas;
      },

      openEditPanel: function (e) {
        var id = this.getId(e);
        var user = this.getUser(id);

        var editPanel = new UserEditPanel(user);
        editPanel.render();
      },

      getUser: function (id) {
        return id === undefined ? new User() : this.users.get(id);
      },

      deleteUser: function (e) {
        if (confirm("确定要删除吗？")) {
          var _this = this;
          var id = this.getId(e);
          var delUser = this.users.get(id);
          //使用destroy完成在数据库删除一个model
          delUser.destroy({success: function () {
            _this.users.remove(delUser);
            _this.render();
          }});
        }
      },

      getId: function (e) {
        return $(e.target).closest('li').attr('id');
      },

      refresh: function (model, userId) {
        if (model.isNew()) {
          model.set({id: userId});
          this.users.add(model);
        }

        this.render();
      }
    });
  });
