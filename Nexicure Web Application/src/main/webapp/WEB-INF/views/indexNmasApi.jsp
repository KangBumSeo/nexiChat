<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="javax.net.ssl.SSLSession"%>
<%@page import="javax.net.ssl.HostnameVerifier"%>
<%@page import="javax.net.ssl.SSLContext"%>
<%@page import="javax.net.ssl.X509TrustManager"%>
<%@page import="javax.net.ssl.TrustManager"%>
<%@page import="javax.net.ssl.HttpsURLConnection"%>
<%@page import="java.security.cert.X509Certificate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	String only_uid = "you";
	String password = "awefbw";
//	String apiURL = "https://192.168.0.199:8443/nmasapi/w/wa_wallet/update.jsp?WALLET_ID="+only_uid+"&ID="+only_uid+"&PROFILE_NAME=putty&WALLET_PASSWORD="+password;
String apiURL = "https://192.168.0.199:8443/nmasapi/common/log/grid.jsp?pagingNumber=1&pagingSize=10&searchFilter=&searchContent=&startDate=2022-04-27&endDate=2022-04-27";
	System.out.println(getStringAsWebAPIClient(apiURL));

%>
<%! 
	public static String getStringAsWebAPIClient(String apiURL) throws Exception {
		URL url = null;
		
		HttpsURLConnection conn = null;
		BufferedReader br = null;
		    
		String t_data = "";
		
		try {
			url = new URL(apiURL);
			String TIMEOUT = "3000";
	
	        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
	            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
	            public void checkServerTrusted(X509Certificate[] certs, String authType) { }
	        } };
	 
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	 
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	        	public boolean verify(String hostname, SSLSession session) { return true; }
	        };
	        
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	
			conn = (HttpsURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
	
		    conn.setConnectTimeout(Integer.parseInt(TIMEOUT)); 
		    conn.setReadTimeout(Integer.parseInt(TIMEOUT));
		    conn.setRequestProperty("Authorization", "Basic bm1hc19tYXN0ZXI6U2VjdXJlOTk=");
		        
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "EUC-KR"));
			
			StringBuffer data = new StringBuffer();
			String inputLine;
			while((inputLine = br.readLine()) != null) {
				data.append(inputLine);
			}
			
			t_data = data.toString().trim();
		} finally {
			if(br != null) {
				try { br.close(); } catch(Exception e) { }
			}
		}
		
		return t_data;
	}
%>
