function card_eidt_description_edittable(target)
{
	var card_description = target.parentNode;
	card_description.removeChild(target);
	var description_textarea = document.createElement("textarea");
	description_textarea.className="card_edit_description_textarea";
	card_description.appendChild(description_textarea);
}
function facebook_radio_change(target)
{
	if(target.src.indexOf("images/dot_bg.png")!=-1)
	{
		target.src="images/green_dot.png";
	}
	else
	{
		target.src="images/dot_bg.png";
	}
}
function twitter_radio_change(target)
{
	if(target.src.indexOf("images/dot_bg.png")!=-1)
	{
		target.src="images/green_dot.png";
	}
	else
	{
		target.src="images/dot_bg.png";
	}
}
function card_rating_change(target)
{
	var rating = target.id[target.id.length-1];
	for(var i=1;i<=5;i++)
	{
		if(i<=parseInt(rating))
		{
			document.getElementById("rating_star"+i).src="images/star_icon.png";
		}
		else
		{
			document.getElementById("rating_star"+i).src="images/grey_star.png";
		}
	}
}
function showCardDescription(the)
{
    var obj=the.getElementsByTagName('div')[0];
	obj.style.display = "block";
}
function hideCardDescription(the)
{
    var obj=the.getElementsByTagName('div')[0];
	obj.style.display = "none";
}
function card_footer_comment_mouse_enter()
{
   var cmnt = $(this).parents('.card').find('.card_comment');
   var aboutToHide = cmnt.attr('aboutToHide');
   if(aboutToHide != null)
    {
       clearTimeout(aboutToHide );
       cmnt.attr('aboutToHide',null);
    }
   cmnt.show();  
}

function card_footer_comment_mouse_leave()
{
   var cmnt= $(this).parents('.card').find('.card_comment');
   var focused = cmnt.attr('editing');
   focused = focused == null ? 'false' : focused;
   if(focused == 'false')
   {
      cmnt.attr('aboutToHide',setTimeout(function(){cmnt.hide()},100));
   }
   
}

function card_comment_mouse_enter()
{ 
    var aboutToHide = $(this).attr('aboutToHide');
    if(aboutToHide != null)
    {
       clearTimeout(aboutToHide);
       $(this).attr('aboutToHide',null);
    }    
}

function card_comment_mouse_leave()
{
   var the=$(this);
   var focused = the.attr('editing');
   focused = focused == null ? 'false' : focused;
   if(focused == 'false')
   {
      the.hide();
   }
}

function card_comment_text_focus(the)
{
   $(the).parents('.card_comment').attr('editing', 'true');
}

function card_comment_text_cancel_click(the)
{
    var cmnt=$(the).parents('.card_comment');
    cmnt.attr('editing','false');
    cmnt.hide();
}

function comment(target)
{
	target.src = "images/comment_red_icon.png";
	var card_rating = document.getElementById("card_rating");
	card_rating.parentNode.removeChild(card_rating);
	var card_recommend = document.getElementById("card_recommend");
	card_recommend.parentNode.removeChild(card_recommend);
	
	
}
function repost(target)
{
	target.src = "images/repost_red_icon.png";
}

function card_load(file){
    var $card = $('#card_template').clone(true);
    $card.css('display','block');
    $card.attr('id','');
    $card.find('.card_content_image').attr('src',file);
    var $cards = $('#cards');
    $cards.append($card);
}
files = ['images/example/pic-4.png','images/example/example.png','images/example/example.jpg','images/example/pic-2.png',
'images/example/example.png','images/example/example.jpg',
'images/example/example.png','images/example/example.jpg',
'images/example/example.png','images/example/example.jpg'
,'images/example/pic-1.png','images/example/pic-2.png'
,'images/example/pic-3.png','images/example/pic-1.png'
,'images/example/pic-4.png'];
function load_cards(){
 for(var index in files)
  {
    card_load(files[index]);
  }
}

// placeholder
$(function(){
      var $card = $('#card_template');
      var $textBack = $card.find('.card_recommend_text_back');
      if(typeof($textBack.get(0).placeholder) == 'undefined')
      {
         $textBack.keyup(placeholderOnkeyup);
         $textBack.show();
      } 
      var $cmnt = $card.find('.card_comment');
      $cmnt.mouseenter(card_comment_mouse_enter);
      $cmnt.mouseleave(card_comment_mouse_leave);
      $cmnt = $card.find('.card_footer_comment');
      $cmnt.mouseenter(card_footer_comment_mouse_enter);
      $cmnt.mouseleave(card_footer_comment_mouse_leave);
      load_cards();
  });




