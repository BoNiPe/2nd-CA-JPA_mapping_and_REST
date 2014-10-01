
package restCRUD;

import facade.Facadelogic;

public class RESTcrud {
    
    private static final boolean DEVELOPMENT_MODE_YOLO_SWAG = true;
    Facadelogic facade;
    
    public static void main(String[] args) {
        new RESTcrud().restCRUD();
    }
    
    private void restCRUD(){
       facade = Facadelogic.getInstance();
        if(DEVELOPMENT_MODE_YOLO_SWAG){
            facade.testingCode();
        } 
    }
}
