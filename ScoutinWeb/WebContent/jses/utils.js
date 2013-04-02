function checkBoxClick(the)
{
    var arr = the.className.split(' ');
    var done = false;
    for(var index in arr)
    {
       if(arr[index].lastIndexOf('_unchecked') == arr[index].length-10)//unchecked
       {
           arr[index] = arr[index].substring(0,arr[index].length-10) + '_checked';
           done = true;
       }
       else if(arr[index].lastIndexOf('_checked') == arr[index].length-8)//checked
       {
           arr[index] = arr[index].substring(0,arr[index].length-8) + '_unchecked';
           done = true;
       }
       if(done)
       {
           the.className = arr.join(' ');
           break;
       }
    }
}

function placeholderOnkeyup()
{
    var $pre =  $(this.previousSibling);
    if(this.value.length !=0) $pre.hide();
    else $pre.show();
}
