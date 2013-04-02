// JavaScript Document

	nav_names=['news','categories','groups'];
	nav_item_sel=document.getElementById('food_drink');
	for(var i=0;i<nav_names.length;++i)
	{
		var lis=document.getElementById(nav_names[i]).getElementsByTagName('li');
		for(var j=0;j<lis.length;++j)
		{
			lis[j].onclick=nav_item_click;
		}		
	}
	
	function nav_item_click()
	{
		if(nav_item_sel==this)
		   return;
		nav_item_sel.className='unselected';
		this.className='selected';
		nav_item_sel=this;
		var spans=this.getElementsByTagName('span');
		if(spans.length>2)
		{
			spans[2].parentNode.removeChild(spans[2]);
		}
	}	

    