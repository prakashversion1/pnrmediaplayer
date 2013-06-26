
package Capture;


//import AudioCapture;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.MediaLocator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Record extends javax.swing.JPanel {
    
    /** Creates new form Record */
    public Record() {
        initComponents();
          audiocapture=new AudioCapture();
    }
    
    
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(53, 104, 195));
        jLabel1.setText("Set Max Time(Sec):-");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(new java.awt.Color(53, 104, 195));
        jLabel2.setText("Set Max Size(MB):-");

        jTextField1.setForeground(new java.awt.Color(53, 104, 195));
        jTextField1.setText("15");

        jTextField2.setForeground(new java.awt.Color(53, 104, 195));
        jTextField2.setText("50");

        jTextField3.setForeground(new java.awt.Color(53, 104, 195));
        jTextField3.setText("select path");

        jButton1.setForeground(new java.awt.Color(53, 104, 195));
        jButton1.setText("Save as");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(53, 104, 195));
        jButton2.setText("Start");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addContainerGap(333, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   if(flags==1) {
            audiocapture.setLimit(seconds,mb);
            audiocapture.AudioCaptureStart();
        } else {
            JOptionPane.showMessageDialog(null,"First select the location to save the file","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    long second=Integer.parseInt(jTextField1.getText());//get the seconds from the user
     seconds=second*500;//change the seconds to the standared format of the jmf
       
       long mbs=Integer.parseInt(jTextField2.getText());
      mb=mbs*1000000;
        flags=0;
        if( seconds<=0||mbs<=0) {
            JOptionPane.showMessageDialog(null,"Invalid input ","Error",JOptionPane.ERROR_MESSAGE);
        } else {
            flags=1;
        }
  if(flags==1)
  {
      jfilechooser4=new JFileChooser();
      int result4=jfilechooser4.showSaveDialog(null);
       jfilechooser4.setMultiSelectionEnabled(false);
      fileobj4=jfilechooser4.getSelectedFile();
      if(result4==jfilechooser4.APPROVE_OPTION) {
          jTextField3.setText(fileobj4.getPath());
        } else if(result4==jfilechooser4.CANCEL_OPTION) {
          jTextField3.setText("No files ");
         flags=0;
       }
      s7=new String(fileobj4.getPath());
    s7="file:/"+s7+".wav";
//             //   url7=fileobj7.toURL();
         try {
         url7=fileobj4.toURL();
      } catch (MalformedURLException ex) {
         ex.printStackTrace();
       }
//        //  convert=new Converter(url1,new MediaLocator(s1),flag);
      audiocapture.setthePath(s7,new MediaLocator(s7));
        // TODO add your handling code here:
  }
    }//GEN-LAST:event_jButton1ActionPerformed
    
     private String s7;
   // private Converter convert;
    private JFileChooser jfilechooser4;
    private int flag=1;
    private URL url1,url2;
    private URL url7;
    private URL url4;
    private long seconds;
    private long  mb;
    private File fileobj1,fileobj2;
    private File fileobj4;
    private int flags=0;
    private String s1,s2,s3,s4;
    private String s[];
//    private Transmitting transmit2;
//    private Receiving  receive;
    private JFileChooser jfilechooser1,jfilechooser2,jfilechooser3;
   private AudioCapture audiocapture =null;
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    
}

