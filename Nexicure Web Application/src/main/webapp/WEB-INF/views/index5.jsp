


<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.codec.binary.Hex"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.security.Key"%>
<%@page import="sun.misc.BASE64Encoder"%>
<%@page import="javax.crypto.spec.IvParameterSpec"%>
<%@page import="javax.crypto.spec.SecretKeySpec"%>
<%@page import="javax.crypto.Cipher"%>
<%
String aa = ":\\\\/";
System.out.println("C%3A%5C%5CProgram%20Files%5C%5CNWAgent");
System.out.println(URLEncoder.encode("한글입니다. NWAgent"));
System.out.println(URLDecoder.decode("C%3A%5C%5CPro/gram=%20Fil$$es%5C%5CNWAgent").replaceAll("[^\u3131-\u314e\u314f-\u3163\uac00-\ud7a3xfe0-9a-zA-Z-.:=\\\\/$@_\\s]", ""));
System.out.println(aa.replaceAll("\\\\", "\\\\\\\\"));
System.out.println(aa.replaceAll("/", "\\\\"));
System.out.println(aa.replaceAll("\\\\", "\\\\"));
System.out.println(hashSHA256("aabbcc!!@@##"));
System.out.println(hashSHA256("!@#$%^&*()_+"));
/*byte[] cipher = encrypt("A8O000A","TJDE92E82TKCIQPE2947DMCKESALPE4T");

System.out.print("After encryption: ");
for (int i=0; i<cipher.length; i++)
      System.out.print(new Integer(cipher[i])+" ");
System.out.println("");  */
	 %>
	 
<%!
//7520cb3b2d77c479d026953a8a40dc43
//7 J b g D + V Q A0 U q Z b vqjtLfPA==
public static String encrypt1AES256(String str) throws Exception {
		if(str == null || "".equals(str)) {
			return "";
		} else {
			SecretKeySpec skeySpec = new SecretKeySpec("TJDE92E82TKCIQPE2947DMCKESALPE4T".getBytes("UTF-8") , "AES");
			//SecretKeySpec skeySpec = new SecretKeySpec("2CDFA3E1A5F449cfBF6FC2421F1B91F6".getBytes() , "AES");

			//"AES/CBC/PKCS5Padding""AES/ECB/NoPadding"
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			
			byte[] encb = str.getBytes("UTF-8");
			int len = encb.length / 16 + 1;
			byte[] encbf = new byte[16 * len];
			
			for(int k = 0; k < encb.length; k ++)
				encbf[k] = encb[k];
			
			byte[] encrypted = cipher.doFinal(encbf);
			
			return asHex(encrypted);
		}
	}

public static String encryptAES256(String str) throws Exception  {
	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] keyBytes = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    Key key = new SecretKeySpec("TJDE92E82TKCIQPE2947DMCKESALPE4T".getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(keyBytes));

    byte[] src = str.getBytes(StandardCharsets.UTF_8);
    byte[] enc = cipher.doFinal(src);
    return Hex.encodeHexString(enc);
}
private String hashSHA256(String str){
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
public static String asHex(byte buf[]) {
	if(buf == null || buf.length == 0)
		return null;	
	
	StringBuffer strbuf = new StringBuffer(buf.length * 2);
	for(int i=0; i<buf.length; i++) {
		if(((int) buf[i] & 0xff) < 0x10)
			strbuf.append("0");
			strbuf.append(Long.toString((int)buf[i] & 0xff,16));
	}

	return strbuf.toString();
}
%>	 

