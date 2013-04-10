var Account = {
	emailSignup: function(email,password,repassword,callback){
	  $.post("account/emailSignup.action",
		{email:email, password:password, repassword:repassword},
		function(data,status){
			callback(data);
		},"json");
	},

	emailSignin: function(email,password,callback){
	  $.post("account/emailSignin.action",
		{email:email, password:password},
		function(data,status){
			callback(data);
		},'json');
	}
}