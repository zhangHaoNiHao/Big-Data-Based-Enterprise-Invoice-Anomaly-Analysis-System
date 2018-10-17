/*$().ready(function() {
	
// 自定义验证规则
$.validator.addMethod("email", function(value, element) {   
			var emailReg = /.*@.*\.?.*?\.(com|cn)$/;
			return this.optional(element) || (emailReg.test(value));
		}, "请正确填写您的邮箱");
$.validator.addMethod("telephone", function(value, element) {   
	var numberReg = /^[1][3458][0-9]{9}$/;
	return this.optional(element) || (numberReg.test(value));
}, "请正确填写您的手机号码");
$.validator.addMethod("usernp", function(value, element) {   
	var usernameReg = /^[a-zA-Z0-9_][a-zA-Z0-9_]{3,9}$/;
	return this.optional(element) || (usernameReg.test(value));
}, "请正确填写您的用户名/密码");
//规则（把XXX改为相应的表单id）
  $("#checkForm").validate({
	  errorElement : 'span',  
      errorClass : 'help-block',  
      focusInvalid : false,   
	  rules: {
	    	codeCheck:{
				required : true,
				equalTo : "#checkCode"
			},
			password2:{
				required : true,
				equalTo:"#inputPassword"
			},
			username:{
				required : true,
				usernp:true
			},
			password:{
				required : true,
				usernp:true
			},				
			name: {
				required: true,
				rangelength:[2,15]
			},
			parentId: {
				digits:true,
				min:0,
				maxlength:6
			},
			sort: {
				digits:true,
				min:0,
				required: true,
				rangelength:[1,15]
			},
			account: {
				required: true,
				number: true
			},
			desc: {
				required: true,
				rangelength:[5,100]
			},
			account: {
				required: true,
				number: true
			}
	    },
	    messages: {
	    	codeCheck:{
				required : "验证码为空",
				equalTo :"验证码输入错误"
			},
			username:{
				required : "用户名为空",
				usernp:"用户名格式不正确，4-10位数字/字母/下划线"
			},
			password:{
				required : "密码为空",
				usernp:"密码格式不正确，4-10位数字/字母/下划线"
			},
			password2:{
				required : "确认密码为空",
				equalTo:"两次输入不一致"
			},
			name:{
				required: "请输入正确分类",
				rangelength: "分类名必须为2-15个字符"
			},
			parentId: {
				digits:"请输入大于0的数字或不填",
				min:"请输入大于0的数字或不填",
				maxlength: "所属分类必须为0-15个字符"
			},
			account: {
				required: "用户账号为空",
				number: "请输入正确手机号"
			},
			sort: {
				required: "请输入正确序号",
				digits:"请输入正确的数字",
				min:"请输入正确的数字",
				rangelength: "序号必须为1-15个字符"
			},
			desc: {
				required: "请输入正确简介",
				rangelength: "简介必须为5-100个字符"
			}
	    },  
        highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },  
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },  
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        }
	});
});*/
function checkPass1(){
	var reg=/^[a-zA-Z0-9\-]{4,16}$/;
  	var pwd1=document.getElementById("p1").value;
  	var pwd2=document.getElementById("p2").value;
  	if(!pwd1){
		document.getElementById("tishi8").innerHTML="<td><font color='red'>不能为空</font><td>";
		document.getElementById("p1").focus();
 	}else if(!reg.test(pwd1)){
		document.getElementById("tishi8").innerHTML="<td><font color='red'>4到16位字母数字下划线！</font><td>";
		document.getElementById("p1").value="";
		document.getElementById("p1").focus();
	}else{
	 	document.getElementById("tishi8").innerHTML="";
 	}

}
function checkPass2(){
	var pwd1=document.getElementById("p1").value;
  	var pwd2=document.getElementById("p2").value;
 	if(pwd1!=pwd2){
		document.getElementById("tishi2").innerHTML="<td><font color='red'>两次密码不同</font><td>";
	 	document.getElementById("p2").value="";
	 //document.getElementById("p2").focus();
 	}else{
	 	document.getElementById("tishi2").innerHTML="";
 	}
}
function checkkong(name,tishi){
  	var v=document.getElementById(name).value;
 	if(!v){
		document.getElementById(tishi).innerHTML="<td><font  color='red'>不能为空</font><td>";
 	}else{
	 	document.getElementById(tishi).innerHTML="";
 	}
}	
function checkusername(){
	var reg=/^[a-zA-Z0-9\-]{4,16}$/;
	var v=document.getElementById("username").value;
	if(!v){
		document.getElementById("tishi1").innerHTML="<td><font color='red'>不能为空</font><td>";
		document.getElementById("username").focus();
 	}else if(!reg.test(v)){
		document.getElementById("tishi1").innerHTML="<td><font color='red'>4到16位字母数字下划线！</font><td>";
		document.getElementById("username").value="";
		document.getElementById("username").focus();
 	}else{
	 	document.getElementById("tishi1").innerHTML="";
	}
}
function checkemail(){
	var reg=/.*@.*\.?.*?\.(com|cn)$/;
	var v=document.getElementById("youxiang").value;
	if(!reg.test(v)){
		document.getElementById("tishi7").innerHTML="<td><font color='red'>请输入正确邮箱！</font><td>";
		document.getElementById("youxiang").value="";
 	}else{
	 	document.getElementById("tishi7").innerHTML="";
	}
}
function checketel(){
	var reg= /^[1][3458][0-9]{9}$/;
	var v=document.getElementById("telephone").value;
	if(!reg.test(v)){
		document.getElementById("tishi6").innerHTML="<td><font color='red'>请输入正确手机号！</font><td>";
		document.getElementById("telephone").value="";
 	}else{
	 	document.getElementById("tishi6").innerHTML="";
	}
}