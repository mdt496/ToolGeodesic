
package toolgeodesic;

import z5ac4.GeoToUtm;
import z5ac4.UtmToGeo;
/**
 *
 * @author MDT496
 */
public class ToolGeodesic {
    
    public static void main(String[] args) {
        
        System.out.println("Demostracion: Transformación coord. UTM a GEO");
        UtmToGeo utg = new UtmToGeo(20,"S",190676.7544318299,8928670.196644317);
        System.out.println(utg.getLatitude());
        System.out.println(utg.getLongitude());
        
        System.out.println("\nDemostracion: Transformación coord. GEO a UTM");
        GeoToUtm gtu = new GeoToUtm(-9.680155555555555,-65.81861388888889);
        System.out.println(gtu.getEste());
        System.out.println(gtu.getNorte());
        System.out.println(gtu.getHemisferio());
        System.out.println(gtu.getHuso());
    }
}
