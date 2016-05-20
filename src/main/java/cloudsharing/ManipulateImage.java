package cloudsharing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashSet;

import org.apache.commons.codec.binary.Base64;

public class ManipulateImage {

    // Decode String into an Image
    public static void convertStringtoImage(String encodedImageStr, String fileName, String userName, String FileMd5) {

        try {
            // Decode String using Base64 Class
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);

            // Write file into File system - Make sure you update the path
            String type = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            File F = new File(System.getenv("OPENSHIFT_DATA_DIR") + "Normal" + File.separator);
            File F1 = new File(System.getenv("OPENSHIFT_DATA_DIR") + "Multi" + File.separator);

            HashSet<String> multi = new HashSet<>();
                System.out.println("filename is  "+fileName);

            multi.add(".jpeg");
            multi.add(".jpg");
            multi.add(".png");
            multi.add(".mp3");

            if (multi.contains(type)) {
                System.out.println("path inside "+F1.getAbsolutePath());

                FileOutputStream imageOutFile = new FileOutputStream(F1.getAbsolutePath() + fileName);
                                
                imageOutFile.write(imageByteArray);

                imageOutFile.close();

            } else {
                System.out.println("path "+F.getAbsolutePath());
                FileOutputStream imageOutFile = new FileOutputStream(F.getAbsolutePath() + fileName);
                imageOutFile.write(imageByteArray);

                imageOutFile.close();

            }

            Connection con = UtilityClass.getconnect();
            Statement st = con.createStatement();
            boolean rs = st.execute("INSERT INTO tbl_upload VALUES('" + fileName + "','" + userName + "','" + FileMd5 + "')");
            con.close();

            System.out.println("File Successfully Stored");
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Path not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Exception while converting the File " + ioe);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
