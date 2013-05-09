$(document).ready(function(){	
	//newCard_image_rotate_angle = 0;
	var $croppedImage = $("#newCard_cropped_image").get(0);
	$croppedImage.addEventListener("dragenter", dragenter, false);
	$croppedImage.addEventListener("dragover", dragover, false);
	$croppedImage.addEventListener("drop", drop, false);
});

function newCard_cancel_click(the)
{
    $(the).parents('.newCard_container_background').hide();
}

function contentAddNewCard()
{
   $('.newCard_container_background').show();
}
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

function newCard_edit_image(){
	document.getElementById("newCard_image_input").click();
}
function createImage(files){
	var reader = new FileReader();
	reader.onload = function(e){
		$("#newCard_container").animate(
			{width:'720px'},
			500,
			function(){
				$("#newCard_cropArea").fadeIn(600).css('display','inline-block');
				$("#newCard_uploaded_image").attr('src',e.target.result);
				$("#newCard_uploaded_image").load(initializeCropImage);
		});
	};
	reader.readAsDataURL(files[0]);
	document.getElementById("newCard_image_input").files = files;
}
function initializeCropImage(){
	var width = $("#newCard_uploaded_image").width();
	var height = $("#newCard_uploaded_image").height();
	var init_crop_width = Math.round((width*1.1<height)?width:height/1.1);
	var init_crop_height = Math.round((width*1.1<height)?width*1.1:height);
	$("#newCard_uploaded_image").imgAreaSelect({x1:0, y1:0, x2:init_crop_width, y2:init_crop_height, aspectRatio:'1:1.1', minWidth:50, handles:true, onSelectChange:updateCroppedImage});
	
	cropImage(0,0,init_crop_width,init_crop_height);
	$("#newCard_cropped_image").attr('src',$("#newCard_uploaded_image").attr('src'));
}
function cropImage(x1,y1,width,height){
	var scaleX = 300 / width;
	var scaleY = 330 / height;

    $("#newCard_cropped_image").css({
        width: Math.round(scaleX * $("#newCard_uploaded_image").width()),
        height: Math.round(scaleY * $("#newCard_uploaded_image").height()),
        marginLeft: -Math.round(scaleX * x1),
        marginTop: -Math.round(scaleY * y1)
    });
}
function updateCroppedImage(img, selection)
{
	cropImage(selection.x1,selection.y1,selection.width,selection.height);
	var scaleX = $("#newCard_uploaded_image").get(0).naturalWidth/$("#newCard_uploaded_image").width();
	var scaleY = $("#newCard_uploaded_image").get(0).naturalHeight/$("#newCard_uploaded_image").height();
	$("#newCard_crop_startX").val(Math.round(selection.x1*scaleX));
	$("#newCard_crop_startY").val(Math.round(selection.y1*scaleY));
	$("#newCard_crop_width").val(Math.round(selection.width*scaleX));
	$("#newCard_crop_height").val(Math.round(selection.height*scaleY));
}
function dragenter(e) {
	e.stopPropagation();
	e.preventDefault();
} 
function dragover(e) {
	e.stopPropagation();
	e.preventDefault();
} 
function drop(e){
	e.stopPropagation();
	e.preventDefault();
	var dt = e.dataTransfer;
    var files = dt.files;
    createImage(files);
}
function rotateCrop(){
	//newCard_image_rotate_angle = (newCard_image_rotate_angle>=270)?0:(newCard_image_rotate_angle+90);
	//$("#newCard_uploaded_image").rotate(newCard_image_rotate_angle);
}
function completeCrop(){
	$("#newCard_uploaded_image").imgAreaSelect({remove:true});
	$("#newCard_cropArea").fadeOut(600,function(){
		$("#newCard_container").animate({width:'334px'},500);
	});
}