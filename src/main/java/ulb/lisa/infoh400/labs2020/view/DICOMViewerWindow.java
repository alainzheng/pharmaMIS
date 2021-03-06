/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulb.lisa.infoh400.labs2020.view;

import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.AttributeTag;
import com.pixelmed.dicom.DicomDictionary;
import com.pixelmed.dicom.DicomException;
import com.pixelmed.display.SourceImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Adrien Foucart
 */
public class DICOMViewerWindow extends javax.swing.JFrame {

    /**
     * Creates new form DICOMViewerWindow
     */
    public DICOMViewerWindow() {
        initComponents();
    }
    
    public void viewDICOM(File f){

        AttributeList al = new AttributeList();
        try {
            al.read(f);
        } catch (IOException | DicomException ex) {
            Logger.getLogger(DICOMViewerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        if( al == null ) return;
        
        String allAttributes = "";
        DicomDictionary dicomDic = new DicomDictionary();
        for( AttributeTag t : al.keySet() ){
            allAttributes +=  dicomDic.getFullNameFromTag(t) + " :: " + al.get(t).getDelimitedStringValuesOrEmptyString() + "\n";
        }
        dicomAttributesTextPane.setText(allAttributes);
        
        try {
            SourceImage sImg = new SourceImage(f.getAbsolutePath());
            ImageIcon icon = new ImageIcon(sImg.getBufferedImage()); // converts to Swing image
            imageViewerLabel.setIcon(icon); // Shows image in a jLabel
            imageViewerLabel.setText("");
        } catch (IOException | DicomException ex) {
            Logger.getLogger(OpenDICOMDIRWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageViewerLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        dicomAttributesTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        imageViewerLabel.setBackground(new java.awt.Color(0, 0, 0));
        imageViewerLabel.setForeground(new java.awt.Color(255, 255, 255));
        imageViewerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageViewerLabel.setText("Viewer");
        imageViewerLabel.setOpaque(true);

        jScrollPane3.setViewportView(dicomAttributesTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageViewerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageViewerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane dicomAttributesTextPane;
    private javax.swing.JLabel imageViewerLabel;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
