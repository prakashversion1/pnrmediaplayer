package Capture;

import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import javax.media.CaptureDeviceInfo;
import javax.media.DataSink;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoDataSinkException;
import javax.media.NoProcessorException;
import javax.media.Processor;
import javax.media.cdm.CaptureDeviceManager;
import javax.media.control.StreamWriterControl;
import javax.media.format.AudioFormat;
import javax.media.protocol.FileTypeDescriptor;
import javax.media.protocol.DataSource;
//import javax.sql.DataSource;

public class AudioCapture {
    private Processor p;
    private MediaLocator  mediaurl;
    private URL murl;
    private String s7;
    private AudioCaptureHelp sh=null;
    private CaptureDeviceInfo di= null;
    DataSink filewriter = null;
    private long seconds;
    private long mbs;
    // DataSource source= null;
    public AudioCapture() {
        
    }
    public void AudioCaptureStart() {
        Vector deviceList=CaptureDeviceManager.getDeviceList(new AudioFormat(AudioFormat.LINEAR,44100,16,2));
        if (deviceList.size() > 0)
            di = (CaptureDeviceInfo)deviceList.firstElement();
        else
// Exit if we can't find a device that does linear,
// 44100Hz, 16 bit,
// stereo audio.
            System.exit(-1);
        try {
            p = Manager.createProcessor(di.getLocator());
            sh = new AudioCaptureHelp(p);
        } catch (IOException e) {
            System.exit(-1);
        } catch (NoProcessorException e) {
            System.exit(-1);
        }
// Configure the processor
        if (!sh.configure(10000))
            System.exit(-1);
// Set the output content type and realize the processor
        p.setContentDescriptor(new
                FileTypeDescriptor(FileTypeDescriptor.WAVE));
        if (!sh.realize(10000))
            System.exit(-1);
// get the output of the processor
        DataSource source = p.getDataOutput();
// create a File protocol MediaLocator with the location of the
// file to which the data is to be written
       // MediaLocator dest = new MediaLocator("file://bishu.WAV");// create a datasink to do the file writing & open the sink to
// make sure we can write to it.
        
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
            swc.setStreamSizeLimit(mbs);
// now start the filewriter and processor
        try {
            filewriter.start();
        } catch (IOException e)
        
        {
            System.exit(-1);
        }
// Capture for 5 seconds
        sh.playToEndOfMedia(seconds);
        sh.close();
// Wait for an EndOfStream from the DataSink and close it...
        filewriter.close();
    }
    public void stopcapturing() {
        // sh.close();
        //   filewriter.close();
        System.out.println("testing");
        sh.close();
        filewriter.close();
    }
    public void setLimit(long seconds,long mbs) {
        this.seconds=seconds;
        this.mbs=mbs;
    }
    public void setthePath(String s7,MediaLocator mediaurl) {
        this.s7=s7;
        this.mediaurl=mediaurl;
    }
    
}



