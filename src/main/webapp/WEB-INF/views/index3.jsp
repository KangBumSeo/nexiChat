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
<script src="/nim/resources/contents/js/lopas-pwd.js" type="text/javascript"></script-->
<script src="/nim/resources/contents/js/crypto-js.js" type="text/javascript"></script>
<script src="/nim/resources/contents/js/x64-core.js" type="text/javascript"></script>
<script src="/nim/resources/contents/js/enc-base64.js" type="text/javascript"></script>
<script src="/nim/resources/contents/js/sha512.js" type="text/javascript"></script>
<script type="text/javascript">
function bin2String(array) {
	  var result = "";
	  for (var i = 0; i < array.length; i++) {
	    result += String.fromCharCode(parseInt(array[i], 2));
	  }
	  return result;
	}
	
var c = CryptoJS.SHA512("admin");
//alert(request.getParameter("aa"));
//	alert(c);
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
aaaaa
<%
out.println("<br>");
String sha512 = hashSHA512("password1!");
out.println(sha512);
out.println("<br>");
out.println("sss");
out.println("<br>");

Encoder encoder = Base64.getEncoder().withoutPadding();; 
byte[] encodedBytes = encoder.encode(sha512.getBytes());


		
byte[] base64 = org.apache.commons.codec.binary.Base64.decodeBase64(sha512.getBytes());
//String base64 = com.sun.org.apache.xml.internal.security.utils.Base64.encode(sha512.getBytes());
out.println(new String(base64));
out.println("<br>");


ShaPasswordEncryptor en = new ShaPasswordEncryptor();
ConfigurablePasswordEncryptor encryptor = new org.jasypt.util.password.ConfigurablePasswordEncryptor();
encryptor.setPlainDigest(true);
encryptor.setAlgorithm("SHA-512");


out.println(encryptor.encryptPassword("password1!"));
out.println("<br>");
out.println(en.encryptPw("password1!"));
out.println("<br>");

out.println(Base64.getEncoder().encodeToString(sha512.getBytes()));
out.println("<br>");


StringBuilder sb = new StringBuilder();
for(final byte b: encodedBytes)
    sb.append(String.format("%02x ", b&0xff));

out.println(Arrays.toString(encodedBytes));
//out.println(bytesToHex(encodedBytes));
out.println("<br>");
out.println(byteToHex(encodedBytes[0]));

SHA1 seed = new com.nexicure.es.services.crypt.sha1.SHA1();
out.println("<br>");
System.out.println(seed.crypt("password1!"));
		
%>
<%!
private String hashSHA512(String str){
	String SHA = ""; 
	try{
		java.security.MessageDigest sh = java.security.MessageDigest.getInstance("SHA-256"); 
		sh.update(str.getBytes()); 
		byte byteData[] = sh.digest();
		StringBuffer sb = new StringBuffer(); 
		for(int i = 0 ; i < byteData.length ; i++){
			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
		}
		SHA = sb.toString();
	}catch(Exception e){
		e.printStackTrace(); 
		SHA = null; 
	}
	return SHA;
}
private String hashSHA5121(String str){
	String SHA = ""; 
	try{
		java.security.MessageDigest sh = java.security.MessageDigest.getInstance("SHA-512"); 
		sh.update(str.getBytes()); 
		byte byteData[] = sh.digest();
		StringBuffer sb = new StringBuffer(); 
		for(int i = 0 ; i < byteData.length ; i++){
			sb.append(Integer.toHexString(byteData[i]&0xff));
		}
		SHA = sb.toString();
	}catch(Exception e){
		e.printStackTrace(); 
		SHA = null; 
	}
	return SHA;
}
private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
public static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
}
public String byteToHex(byte num) {
    char[] hexDigits = new char[2];
    hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
    hexDigits[1] = Character.forDigit((num & 0xF), 16);
    return new String(hexDigits);
}

%>
</body>
</html>
