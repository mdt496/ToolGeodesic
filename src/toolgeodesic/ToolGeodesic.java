
package toolgeodesic;

import z5ac4.GeoToUtm;
import z5ac4.UtmToGeo;
/**
 *
 * @author MDT496
 */
public class ToolGeodesic {
    
    public static void main(String[] args) {
        
        if(args.length == 0){
            System.out.println("Modo de uso:\n");
            System.out.println("UtmToGeo Huso Hemisferio Este Norte");
            System.out.println("UtmToGeo 20 S 190676.7 8928670");
            System.out.println("UtmToGeo Huso Hemisferio Este Norte aDatum bDatum");
            System.out.println("UtmToGeo 20 S 190676.7 8928670 6378137. 6356752.314");
            
            System.out.println("\nGeoToUtm Lat Lon");
            System.out.println("GeoToUtm -9.680157 -65.818614");
            System.out.println("GeoToUtm Lat Lon aDatum bDatum");
            System.out.println("GeoToUtm -9.680157 -65.818614 6378137. 6356752.314");
        }else{
            if(args[0].equals("UtmToGeo")){
                int H = Integer.parseInt(args[1]);
                double E = Double.valueOf(args[3]);
                double N = Double.valueOf(args[4]);
                UtmToGeo utg;
                if(args.length >5) utg = new UtmToGeo(H,args[2],E,N,Double.valueOf(args[5]),Double.valueOf(args[6]));
                else utg = new UtmToGeo(H,args[2],E,N);
                System.out.println("Lat:=  "+utg.getLatitude());
                System.out.println("Lon:=  "+utg.getLongitude());
                System.out.println("\nDatumInfo:\n"+utg.datumInfo());
            }
            if(args[0].equals("GeoToUtm")){
                double Lat = Double.valueOf(args[1]);
                double Lon = Double.valueOf(args[2]);
                GeoToUtm gtu;
                if(args.length > 3) gtu = new GeoToUtm(Lat,Lon,Double.valueOf(args[3]),Double.valueOf(args[4]));
                else gtu = new GeoToUtm(Lat,Lon);
                System.out.println("E:=    "+gtu.getEste());
                System.out.println("N:=    "+gtu.getNorte());
                System.out.println("Hem:=  "+gtu.getHemisferio());
                System.out.println("Huso:= "+gtu.getHuso());
                System.out.println("\nDatumInfo:\n"+gtu.datumInfo());
            }
        }
    }
}
