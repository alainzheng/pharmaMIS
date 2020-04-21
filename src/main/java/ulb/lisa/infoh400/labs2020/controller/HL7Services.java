/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulb.lisa.infoh400.labs2020.controller;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v23.message.ACK;
import ca.uhn.hl7v2.model.v23.message.ADT_A01;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import ca.uhn.hl7v2.model.v23.segment.PID;
import ca.uhn.hl7v2.parser.Parser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ulb.lisa.infoh400.labs2020.GlobalConfig;
import ulb.lisa.infoh400.labs2020.model.Patient;

/**
 *
 * @author Adrien Foucart
 */
public class HL7Services {
    
    public static boolean sendADT_A01(Patient patient, String host, int port){
        try {
            ADT_A01 adt = new ADT_A01();
            adt.initQuickstart("ADT", "A01", "P");
            
            MSH msh = adt.getMSH();
            msh.getSendingApplication().getNamespaceID().setValue("HIS");
            msh.getMessageControlID().setValue(String.valueOf(GlobalConfig.getNextADTMessageID()));
            
            PID pid = adt.getPID();
            pid.getPatientName(0).getFamilyName().setValue(patient.getIdperson().getFamilyname());
            pid.getPatientName(0).getGivenName().setValue(patient.getIdperson().getFirstname());
            pid.getDateOfBirth().getTimeOfAnEvent().setValue(patient.getIdperson().getDateofbirth());
            pid.getPhoneNumberHome(0).getAnyText().setValue(patient.getPhonenumber());
            
            HapiContext ctxt = new DefaultHapiContext();
            Parser parser = ctxt.getXMLParser();
            String encoded = parser.encode(adt);
            
            Connection conn = ctxt.newClient(host, port, GlobalConfig.HL7_TLS);
            Initiator initiator = conn.getInitiator();
            ACK response = (ACK) initiator.sendAndReceive(adt);
            
            return response.getMSA().getAcknowledgementCode().getValue().equalsIgnoreCase("AA");
        } catch (HL7Exception | IOException | LLPException ex) {
            Logger.getLogger(HL7Services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
