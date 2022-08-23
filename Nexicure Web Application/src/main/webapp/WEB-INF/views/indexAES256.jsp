<%@page import="javax.crypto.Cipher"%>
<%@page import="javax.crypto.spec.SecretKeySpec"%>
<%@page import="org.jasypt.util.password.ConfigurablePasswordEncryptor"%>
<%@page import="smartsuite.security.core.authentication.encryption.ShaPasswordEncryptor"%>
<%@page import="com.nexicure.es.services.crypt.sha1.SHA1"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Base64.Encoder"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<!--script src="/nim/resources/contents/js/core.js" type="text/javascript"></script>
<script src="/nim/resources/contents/js/lopas-pwd.js" type="text/javascript"></script>
<script src="/nim/resources/contents/js/x64-core.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" type="text/javascript"></script>
<script src="/nim/resources/contents/js/jquery-3.0.0.min.js" type="text/javascript"></script-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" type="text/javascript"></script>
<script type="text/javascript">
function s(x) {return x.charCodeAt(0);}
//"test.message".split('').map(s);

function bin2String(array) {
	  var result = "";
	  for (var i = 0; i < array.length; i++) {
	    result += String.fromCharCode(parseInt(array[i], 2));
	  }
	  return result;
	}
var Iv;
var pwd = CryptoJS.AES.encrypt("Secure99", CryptoJS.enc.Utf8.parse("12345678901234567890123456789012"), {
	//var pwd = CryptoJS.AES.encrypt("Secure99", "12345678901234567890123456789012".split('').map(s), {
//	iv : CryptoJS.enc.Hex.parse("0000000000000000")
    mode : CryptoJS.mode.ECB,
    padding : CryptoJS.pad.ZeroPadding
});
function decryptByAESECB(ciphertext) {

    var decrypted = CryptoJS.AES.decrypt(ciphertext, CryptoJS.enc.Utf8.parse("12345678901234567890123456789012"), {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.ZeroPadding,
        format: CryptoJS.format.Hex
    });

    return decrypted.toString(CryptoJS.enc.Utf8);
}

alert(decryptByAESECB("16cbe40b525411e1149fa7168bb909f8"));
// [인코딩 된 데이터 확인 실시]
//var aes256DecodeData = cipher.toString(CryptoJS.enc.Utf8);
//alert(request.getParameter("aa"));
//	alert(pwd);
//	alert(pwd.ciphertext.toString());
//	alert(decryptByAESECB(pwd.toString()));
	//alert(c);
//	alert(CryptoJS.enc.Base64.stringify(c).toString());
//		alert(CryptoJS.enc.Base64.parse(c));
//alert(PasswordEncryptor.encryptpw("password1!"));
	//	fnInitOrgChart();
	//	$.post('<spring:url value="/commons/codedesc/orgchart"/>',{},function(data){fnOrgCallback(data);});

	//	fnMakeSelectBox();


</script>
</head>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
aaaaa<br>
<% String str = "Secure99";
		if(str == null || "".equals(str)) {
		} else {
			SecretKeySpec skeySpec = new SecretKeySpec("12345678901234567890123456789012".getBytes() , "AES");
			//SecretKeySpec skeySpec = new SecretKeySpec("2CDFA3E1A5F449cfBF6FC2421F1B91F6".getBytes() , "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			
			byte[] encb = str.getBytes("UTF-8");
			int len = encb.length / 16 + 1;
			byte[] encbf = new byte[16 * len];
			
			for(int k = 0; k < encb.length; k ++)
				encbf[k] = encb[k];
			
			byte[] buf = cipher.doFinal(encbf);
			
			
			StringBuffer strbuf = new StringBuffer(buf.length * 2);
			for(int i=0; i<buf.length; i++) {
				if(((int) buf[i] & 0xff) < 0x10)
					strbuf.append("0");
					strbuf.append(Long.toString((int)buf[i] & 0xff,16));
			}

			
			out.println("12345678901234567890123456789012".getBytes()[0]+"<br>");
			out.println(encb[0]+"<br>");
			out.println(encbf[0]+"<br>");
			out.println(buf[0]+"<br>");
			out.println(strbuf+"<br>");
		}
	%>
</body>
</html>