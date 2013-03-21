function card_eidt_description_edittable(target)
{
	var card_description = target.parentNode;
	target.style.display = "none";
	$("#newCard_edit_description_textarea").css('display','inline');
}
function newCard_facebook_check(target)
{
	if(target.className=="newCard_checkbox_checked")
	{
		target.className = "newCard_checkbox_unchecked";
	}
	else
	{
		target.className = "newCard_checkbox_checked";
	}
}
function newCard_twitter_check(target)
{
	if(target.className=="newCard_checkbox_checked")
	{
		target.className = "newCard_checkbox_unchecked";
	}
	else
	{
		target.className = "newCard_checkbox_checked";
	}
}
function newCard_location_check(target)
{
	if(target.className=="newCard_checkbox_checked")
	{
		target.className = "newCard_checkbox_unchecked";
		$("#newCard_location_image").get(0).className = "newCard_location_image_disable";
		$("#newCard_location_text").attr('disabled','disabled');
	}
	else
	{
		target.className = "newCard_checkbox_checked";
		$("#newCard_location_image").get(0).className = "newCard_location_image_able";
		$("#newCard_location_text").removeAttr('disabled');
	}	
}
function newCard_rating_change(target)
{
	var rating = target.className[0];
	target.parentNode.getElementsByTagName("input")[0].value = rating;
	var stars = target.parentNode.getElementsByTagName("span");
	for(var i=0;i<=4;i++)
	{
		if(i<=parseInt(rating)-1)
		{
			$(stars[i]).removeClass("newCard_rating_star_gray");
			$(stars[i]).removeClass("newCard_rating_star_pink");
			$(stars[i]).addClass("newCard_rating_star_red");
		}
		else
		{
			$(stars[i]).removeClass("newCard_rating_star_red");
			$(stars[i]).removeClass("newCard_rating_star_pink");
			$(stars[i]).addClass("newCard_rating_star_gray");
		}
	}
}
function newCard_rating_hover(target)
{
	var rating = target.className[0];
	var stars = target.parentNode.getElementsByTagName("span");
	for(var i=0;i<=4;i++)
	{
		if(i<=parseInt(rating)-1)
		{
			$(stars[i]).removeClass("newCard_rating_star_gray");
			$(stars[i]).removeClass("newCard_rating_star_red");
			$(stars[i]).addClass("newCard_rating_star_pink");
		}
		else
		{
			$(stars[i]).removeClass("newCard_rating_star_red");
			$(stars[i]).removeClass("newCard_rating_star_pink");
			$(stars[i]).addClass("newCard_rating_star_gray");
		}
	}
}
function newCard_rating_out(target)
{
	var rating = target.parentNode.getElementsByTagName("input")[0].value;
	var stars = target.parentNode.getElementsByTagName("span");
	for(var i=0;i<=4;i++)
	{
		if(i<=parseInt(rating)-1)
		{
			$(stars[i]).removeClass("newCard_rating_star_gray");
			$(stars[i]).removeClass("newCard_rating_star_pink");
			$(stars[i]).addClass("newCard_rating_star_red");
		}
		else
		{
			$(stars[i]).removeClass("newCard_rating_star_red");
			$(stars[i]).removeClass("newCard_rating_star_pink");
			$(stars[i]).addClass("newCard_rating_star_gray");
		}
	}
}

function newCard_edit_image(target)
{
	
}