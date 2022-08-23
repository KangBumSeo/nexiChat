<%@ page language="java" contentType="text/html; charset=UTF-7"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.NamingEnumeration"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.directory.*"%>
<%@page import="javax.naming.ldap.*"%>

<%
Properties prop = new Properties();

prop.load(new FileInputStream(
                "/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/sam901Node01Cell/DefaultApplication.ear/DefaultWebApplication.war/WEB-INF/conf/ldap.properties"));

String ldapHost = prop.getProperty("ldapHost");
String searchBase = prop.getProperty("searchBase");
String userId = prop.getProperty("userId");
String password = prop.getProperty("password");
String searchFilter = prop.getProperty("Fliter");

HashMap<String, Object> umap = null;

String returnedAtts[] = { "principalName", "secPwdValid" };


SearchControls searchCtls = new SearchControls();
searchCtls.setReturningAttributes(returnedAtts);

searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
Hashtable<String, String> env = new Hashtable<String, String>();
env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
env.put(Context.PROVIDER_URL, ldapHost);
env.put(Context.SECURITY_AUTHENTICATION, "simple");
env.put(Context.SECURITY_PRINCIPAL, userId);
env.put(Context.SECURITY_CREDENTIALS, password);
LdapContext ctxGC = null;

try {
        ctxGC = new InitialLdapContext(env, null);

        NamingEnumeration<SearchResult> answer = ctxGC.search(searchBase, searchFilter, searchCtls);
        out.println("------------------------------- ... .. .. -------------------------------< BR >");

        while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();

                if (attrs != null) {
        umap = new HashMap<String, Object>();
        NamingEnumeration<?> ne = attrs.getAll();
        while (ne.hasMore()) {
                Attribute attr = (Attribute) ne.next();
                umap.put(attr.getID(), attr.get());
                System.out.println(" > > " + attr.getID() + " : " + attr.get() + "< BR >");
                out.println(" > > " + attr.getID() + " : " + attr.get() + "< BR >");


        }
        ne.close();
                }
                out.println("------------------------------------------------------------------------------< BR >");
        }
} catch (NamingException ex) {
        System.out.println(ex.toString());
        System.out.println("< BR >");
}

if (umap == null) {
        System.out.println(".. .. ..!!! .... ..... ......!!");
} else {
        System.out.println("login success");
}
%>
