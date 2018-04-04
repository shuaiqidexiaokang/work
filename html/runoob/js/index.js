;
$(function(){
	$('.placeholder').focus(function(){
		if (this.value=='搜索') {
			this.value='';
		}		
	});
	$('.placeholder').blur(function(){
		if (this.value=='') {
			this.value='搜索';
		}else{
			this.css({
				"color":"#333",
				"opacity":".8"
			});
		}
	});
});