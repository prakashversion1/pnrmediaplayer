
import com.sun.media.MimeManager;

public class register {

    String s2 = "video/mpeg", s3 = "vob", s4 = "dat", s5 = "audio/mpeg";

    register() {
        MimeManager.addMimeType(s3, s2);// here s3 is the extension and s2 is the mime type
        MimeManager.addMimeType(s4, s5);
        MimeManager.commit();

    }

//    public static void main(String args[]) {
//        new test();
//
//    }
}
