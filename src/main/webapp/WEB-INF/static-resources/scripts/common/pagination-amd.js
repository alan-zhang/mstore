define(['backbone'], function(Backbone){

    return Pagination = Backbone.View.extend({
        el: $('div.ll-pg'),
        
        events: {
            'click a': 'pageTurningEvent'
        },
        
        initialize: function(options){
            this.options = $.extend({
                currentPage: 1,
                pageCount: 4,
                total: 0
            }, options);
        },
        
        render: function(){
            if (this.options.parentContainer === '') return;
			
            var pagePanel = this.generate();
            this.$el.empty().html(pagePanel);
            this.delegateEvents();
        },
        
        generate: function(){
            var pages = this.calculatePages();
            var currentPage = parseInt(this.options.currentPage);
            var pageLength = pages.length;
            var lis = [];
            
            lis.push('<div class=\"ll-page\">');
            
            if (currentPage === 1) {
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-nav prev-inactive\"></a>');
            }
            else {
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-nav prev\" data-page=\"' + (currentPage - 1) + '\"></a>');
            }
            
            var before = currentPage - 4;
            var after = currentPage + 4;
            
            if (before > 2) {
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-number\" data-page=\"1\">1</a>');
                lis.push('<span class=\"divider\"> / </span>');
                lis.push('<span class=\"ll-page-dots\"> ... </span>');
                lis.push('<span class=\"divider\"> / </span>');
            }
            var beforeLength = before <= 2 ? 1 : before;
            for (var i = beforeLength; i < currentPage; i++) {
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-number\" data-page=\"' + i + '\">' + i + '</a>');
                lis.push('<span class=\"divider\"> / </span>');
            }
            
            var loopCount = pageLength - after < 2 ? pageLength : (after > pageLength ? pageLength : after);
            for (var i = currentPage; i <= loopCount; i++) {
                if (i == currentPage) {
                    lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-number current\" data-page=\"' + i + '\">' + i + '</a>');
                }
                else {
                    lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-number\" data-page=\"' + i + '\">' + i + '</a>');
                }
                if (i != loopCount) {
                    lis.push('<span class=\"divider\"> / </span>');
                }
            }
            
            if (pageLength - after >= 2) {
                lis.push('<span class=\"divider\"> / </span>');
                lis.push('<span class=\"ll-page-dots\"> ... </span>');
                lis.push('<span class=\"divider\"> / </span>');
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-number\" data-page=\"' + pageLength + '\">' + pageLength + '</a>');
            }
            
            if (currentPage === pageLength) {
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-nav next-inactive\"></a>');
            }
            else {
                lis.push('<a href=\"javascript:void(0);\" class=\"ll-page-nav next\" data-page=\"' + (currentPage + 1) + '\"></a>');
            }
            
            lis.push('</div>');
            
            var paging = lis.join('');
            
            return paging;
        },
        
        calculatePages: function(){
            var total = this.options.total;
            var pageCount = this.options.pageCount;
            
            var p = parseInt(total / pageCount);
            var mod = total % pageCount;
            var length = mod === 0 ? p : p + 1;
            var pages = [];
            for (var i = 1; i <= length; i++) {
                pages.push(i);
            }
            
            return pages;
        },
        
        pageTurningEvent: function(e){
            var pageNumber = $(e.target).attr('data-page');
            if (pageNumber == null) return;
            
            this.options.currentPage = pageNumber;
            this.render();
            CustomEvent.trigger(CustomEvent.pageTurning, {current:pageNumber, pageCount:this.options.pageCount});
        },
    
    });
});
