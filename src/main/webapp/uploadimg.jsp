
<%@page import="cloudsharing.ManipulateImage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String imgEncodedStr = request.getParameter("image");
String fileName = request.getParameter("filename");
String userName = request.getParameter("username");
String md5offile = request.getParameter("md5offile");
System.out.println("Filename: "+ fileName);
System.out.println("Username: "+ userName);
System.out.println("Md5SUM: "+ md5offile);

if(imgEncodedStr != null){
	ManipulateImage.convertStringtoImage(imgEncodedStr, fileName,userName,md5offile);
	System.out.println("Inside if");
	out.print("Uploading complete");
} else{
	System.out.println("Inside else");
	out.print("Image is empty");	
}
%>
