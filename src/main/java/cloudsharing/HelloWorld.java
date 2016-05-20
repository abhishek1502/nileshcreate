package cloudsharing;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
 
@Path("/HelloWorld")
public class HelloWorld {

    @GET
    @Path("/names/{name}")
    // @Produces here defines the media type(s) that the methods
    // of a resource class can produce.
    @Produces(MediaType.TEXT_HTML)
    public String sayHelloWorld(@PathParam("name") String name) {
        System.out.println("hello");
        return "Hello world from " + name;
    }
    
//    
/*
       @GET
    @Consumes("text/plain")
    @Path("/Fileuploading")
    @Produces("text/html")
    public String Fileupload(@QueryParam("message") String message, @QueryParam("Uname") String Uname) {
        String output = "";
        System.out.println("filename=" + message);

        byte[] fildata = UtilityClass.fileRead(message.replace(",", File.separator));
        boolean b = UtilityClass.Upload(fildata, message.replace(",", File.separator), Uname);
       
        System.out.println("file upload==" + b);
        if (b) {
            output = message + " File Uploaded";
        } else {
            output = "File Not Found";
        }
        return output;
    }
*/

    @GET
    @Path("/UserFiles")
    public String ParticularUserFiles(@QueryParam("Uname") String Uname) {
        String output = ",";
//        String output = "Prameter1: " + parameter1 + "\nParameter2: " + parameter2.toString();
        ArrayList<String> FileList = UtilityClass.FilesOfUsers(Uname);

        System.out.println("File list==" + FileList);

        StringBuffer sb = new StringBuffer();

        for (String o : FileList) {
            sb.append(o + ",");
//				output = output+o+",";

        }

        sb.deleteCharAt(sb.toString().lastIndexOf(","));
        output = sb.toString();

        return output;
    }

    @GET
    @Path("/ListOfUser")
    public String ListOfUser() {
        ArrayList<String> UserList = UtilityClass.ListOfUser();
        System.out.println("" + UserList);
        String output = ",";
        StringBuffer sb = new StringBuffer();

        for (String o : UserList) {
            sb.append(o + ",");
//				output = output+o+",";

        }

        sb.deleteCharAt(sb.toString().lastIndexOf(","));
        output = sb.toString();
        return output;
    }

    @GET
    @Path("/login")
    public String Login(@QueryParam("parameter1") String parameter1, @QueryParam("parameter2") String parameter2) {
        String output = "";
//        String output = "Prameter1: " + parameter1 + "\nParameter2: " + parameter2.toString();
        Connection conn = UtilityClass.getconnect();
        int i = UtilityClass.Login(parameter1, parameter2);
        if (i == 1) {
            output = "Success";
        } else {
            output = "failed";
        }
        return output;
    }


    @GET
    @Path("/FileList")
    public String ListFilesShare() {
        String output = ",";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> FileList = UtilityClass.LisstFiles();
        ArrayList<String> FileList1 = UtilityClass.LisstFiles();
        for (String o : FileList) {
            sb.append(o + ",");
//				output = output+o+",";

        }

        sb.deleteCharAt(sb.toString().lastIndexOf(","));
        output = sb.toString();

        return output;
    }

//For md5sum of files


    @GET
    @Path("/Md5Sum")
    public String ListMd5SumFile() {
        String output = ",";
        StringBuilder sb = new StringBuilder();
        ArrayList<String> FileList = UtilityClass.ListOfMd5();
          for (String o : FileList) {
            sb.append(o + ",");
 

        }

        sb.deleteCharAt(sb.toString().lastIndexOf(","));
        output = sb.toString();

        return output;
    }

    //For register 
    @GET
    @Path("/register")
    public String Register(@QueryParam("parameter1") String parameter1, @QueryParam("parameter2") String parameter2, @QueryParam("parameter3") String parameter3, @QueryParam("parameter4") String parameter4) {
        String s = "";

        boolean b = false;
        b = UtilityClass.Register(parameter1, parameter2, parameter3, parameter4);
        System.out.println("b====" + b);
        if (b) {
            s = "Registered Success";
        } else {
            s = "Failed";
        }
        return s;
    }

//    
//    @GET
//    @Consumes("text/plain")
//    @Path("/dataupload")
//    @Produces("text/html")
//    public String dataupload(@QueryParam("bytarray") byte[] bytarray, @QueryParam("Fname") String Fname, @QueryParam("Uname") String Uname) {
//        boolean b = false;
//        String output = "";
//        System.out.println("filename=" + Fname);
//        String destpath=System.getenv("OPENSHIFT_DATA_DIR")+File.separator+Fname.substring(Fname.lastIndexOf(File.separator));
//        try {
//            FileOutputStream fout = new FileOutputStream(new File(destpath));
//            fout.write(bytarray);
//            fout.flush();
//            fout.close();
//            b = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (b) {
//            output = Fname + "FileUploaded";
//        } else {
//            output = "File Not Found";
//        }
//        return output;
//    }
    
/*
    @GET
    @Consumes("text/plain")
    @Path("/Download")
    @Produces("text/html")
    public String Filedownload(@QueryParam("Fname") String Fname) {
        String output = "";
        System.out.println("filename=" + Fname);

//      byte[] fildata = Classes.UtilityClass.fileRead(Fname.replace(",", File.separator));
        ArrayList<byte[]> b = Classes.UtilityClass.Down(Fname.replace(",", File.separator));
        for (int i = 0; i < b.size(); i++) {
            byte[] bs = b.get(i);
            boolean b1 = Classes.UtilityClass.writeFile(bs, Fname);
            if (b1) {
                output = "File Downloaded";
            } else {
                output = "File Not Found";
            }

        }
        return output;
    }
*/
    
    
    
    
    

       @GET
    @Path("/DownFile")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile1(@QueryParam("Fname") String Fname) {  

                    HashSet<String> multi = new HashSet<>();
                System.out.println("filename is  "+Fname);

            multi.add(".jpeg");
            multi.add(".jpg");
            multi.add(".png");
            multi.add(".mp3");
            String type  = Fname.substring(Fname.indexOf("."),Fname.length()) ;
            ResponseBuilder response = null;
if(multi.contains(type)) {

        File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + File.separator+"Multi"+File.separator + Fname);

          response = Response.ok(file, MediaType.APPLICATION_OCTET_STREAM);
        response.header("Content-Disposition",
                "attachment; filename="+Fname);
       
} else{



        File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + File.separator+"Normal"+File.separator + Fname);

          response = Response.ok(file, MediaType.APPLICATION_OCTET_STREAM);
        response.header("Content-Disposition",
                "attachment; filename="+Fname);

}
 return response.build();

    }
     
    

    @GET
    @Path("/FileShare")
        public String FileSharing(@QueryParam("Fnm") String Fnm, @QueryParam("Unm") String Unm) {
        String output = "";

        int  b = UtilityClass.FilShare(Fnm, Unm);
        if (b ==1 ) {
            output = "File Shared";
        } else {
            output = "File Not Shared";
        }
        return output;
    }
}
