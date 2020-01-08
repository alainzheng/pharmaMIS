/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulb.lisa.infoh400.labs2020.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * ListModel for the jList in the main interface.
 * Simple wrapper around the AbstractListModel using templates to be able to put Patients, Doctors, Appointments or Images in the list depending on the needs.
 * T should be a class from the model package.
 * @author Adrien Foucart
 */
public class EntityListModel<T> extends AbstractListModel {
    
    private List<T> entities;
    
    /**
     * Create model with a list of entities of class T or create an empty list if entities is null.
     * @param entities 
     */
    public EntityListModel(List<T> entities){
        if( entities == null ){
            entities = new ArrayList();
        }
        this.entities = entities;
    }
    
    /**
     * Set the list of entities of class T
     * @param entities 
     */
    public void setList(List<T> entities){
        this.entities = entities;
    }
    
    public List<T> getList(){
        return entities;
    }
    
    @Override
    public int getSize() {
        return entities.size();
    }

    @Override
    public Object getElementAt(int index) {
        return entities.get(index);
    }
    
}
