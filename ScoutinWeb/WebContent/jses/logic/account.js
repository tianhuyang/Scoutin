var Account = {
	emailSignup: function(email,password,repassword,firstname,lastname,callback){
	  $.post("account/emailSignup.action",
		{email:email, password:password, repassword:repassword, firstname:firstname, lastname:lastname},
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