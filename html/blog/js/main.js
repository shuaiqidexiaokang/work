;
$(function(){
	var sidebar_trigger=$('#sidebar_trigger'),
	mask = $('.mask'),
	sidebar = $('#sidebar'),
	backButton = $('.back-to-top');

	sidebar_trigger.on('click',showSideBar); 
	mask.on('click',hideSideBar);
	backButton.on('click',function(){
		$("html,body").animate({scrollTop:0}, 500);
	})
	$(window).on('scroll',function () {
	    if($(window).scrollTop()>$(window).height()) {
	        backButton.fadeIn(400); /* 当滑动到不小于 100px 时，回到顶部图标显示 */
	    }else {
	        backButton.fadeOut(400); /* 当滑动到小于(页面被卷去的高度) 100px 时，回到顶部图标隐藏 */
	    }
	});

	function showSideBar(){
		mask.fadeIn();
		//sidebar.animate({'right':0});
		sidebar.css('right',0);//结合transition同样实现过度
	}
	function hideSideBar(){
		mask.fadeOut();
		//sidebar.animate({'right':0});
		sidebar.css('right',-sidebar.width());//结合transition同样实现过度
	}
})

