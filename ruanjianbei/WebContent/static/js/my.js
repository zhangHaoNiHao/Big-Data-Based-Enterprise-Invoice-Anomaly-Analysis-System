
$(document).ready(function() {
	var menuParent = $('.menu > .ListTitlePanel > div');//获取menu下的父层的DIV
	var menuList = $('.menuList');
	
	//遍历每个列表的大标题为其设置点击事件
	$('.menu > .menuParent > .ListTitlePanel > .ListTitle').each(function(i) {//获取列表的大标题并遍历
		$(this).click(function(){
			//点击后，如果该列表的菜单列表没有显示，则将菜单列表滑出，否则收起
			if($(menuList[i]).css('display') == 'none'){
				//将菜单列表滑出
				$(menuList[i]).slideDown(300);
			}
			else{
				//将菜单列表收起
				$(menuList[i]).slideUp(300);
			}
		});
	});
});