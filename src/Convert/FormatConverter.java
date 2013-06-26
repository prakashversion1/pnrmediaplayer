
package Convert;


import java.io.IOException;
import java.net.URL;
import javax.media.DataSink;
import javax.media.protocol.DataSource;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSinkException;
import javax.media.NoProcessorException;
import javax.media.Processor;
import javax.media.control.StreamWriterControl;
import javax.media.protocol.FileTypeDescriptor;
import jmapps.util.StateHelper;
//import jmapps.util.StateHelper;
public class FormatConverter{
    FormatConverter(URL murl,MediaLocator mediaurl,int flag) {
        this.murl=murl;
        this.mediaurl=mediaurl;
        this.flag=flag;
      
        
        create();
        
    }
    void create(){
        
        if(murl==null){
            System.exit(0);
        }
        try {
            p = Manager.createProcessor(murl);
            sh = new StateHelper(p);
        } catch (IOException e) {
            System.out.println("processor is not created"+e);System.exit(-1);
        } catch (NoProcessorException e) {
            System.out.println("processor is not created"+e); System.exit(-1);
        }
        if (!sh.configure(1000))
            System.exit(-1);
        p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.MPEG_AUDIO));
       if (flag==1) {
            p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.WAVE));
        } else if(flag==2)
            p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.MPEG_AUDIO));
        else if(flag==3)
            p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.AIFF));
        else if(flag==4)
            p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.GSM));
        else if(flag==5)
            p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.MPEG));
        else if(flag==6)
            p.setContentDescriptor(new FileTypeDescriptor(FileTypeDescriptor.QUICKTIME));
        
        if (!sh.realize(10000))
            System.exit(-1);
        
        DataSource source = p.getDataOutput();
       // MediaLocator dest = new MediaLocator(mediaurl);
        DataSink filewriter = null;
        try {
            filewriter = Manager.createDataSink(source, mediaurl);
            filewriter.open();
        } catch (NoDataSinkException e) {
            System.exit(-1);
        } catch (IOException e) {
            System.exit(-1);
        } catch (SecurityException e) {
            System.exit(-1);
        }
        
        //if the Processor implements StreamWriterControl, we can
// call setStreamSizeLimit
// to set a limit on the size of the file that is written.
        StreamWriterControl swc = (StreamWriterControl)
        p.getControl("javax.media.control.StreamWriterControl");
//set limit to 5MB
        if (swc != null)
            swc.setStreamSizeLimit(500000000);
// now start the filewriter and processor
        try {
            filewriter.start();
        } catch (IOException e)
        
        {
            System.exit(-1);
        }
// Capture for 5 seconds
        sh.playToEndOfMedia(50000);
        sh.close();
// Wait for an EndOfStream from the DataSink and close it...
        filewriter.close();
    }
    
    
    private int flag=1;
    private Processor p;
    private StateHelper sh=null;
    private URL murl;
    private MediaLocator mediaurl;
}
