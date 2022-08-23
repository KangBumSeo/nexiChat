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
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" type="text/javascript"></script-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" type="text/javascript"></script>
<script type="text/javascript">
function encryptByAESECB(message, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var encrypted = CryptoJS.AES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.NoPadding
    });
    return encrypted.toString();
}
function decryptByAESECB(ciphertext, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);

    // direct decrypt ciphertext
    var decrypted = CryptoJS.AES.decrypt({
        ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
    }, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.NoPadding
    });

    return decrypted.toString(CryptoJS.enc.Utf8);
}

var message = 'Secure99';
var key = '12345678901234567890123456789012';

function s(x) {return x.charCodeAt(0);}
var ciphertext = encryptByAESECB(message, key);
// ciphertext: 8dKft9vkZ4I=
alert(ciphertext);

alert(ciphertext.split('').map(s));
ciphertext = CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(ciphertext))
alert(ciphertext);
var plaintext = decryptByAESECB(ciphertext, key);
// plaintext : Message
alert(JSON.parse(plaintext));
//console.info('plaintext :', plaintext);
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