
package z5ac4;

import static java.lang.Math.*;
import java.util.ArrayList;
import static z5ac4.toolExt.*;
/**
 *
 * @author MDLORIAL
 */
public class UtmToGeo {
    private double Este, Norte,Huso,G3,H3,lat,lon;
    private String Hemisferio;
    private ArrayList<String []> argos = new ArrayList<>();
        
    //Hemisferio  "N" o "S"    //Nota: Norte:= {N,P,Q,R,S,T,U,V,W,X}  Sud:= {M,L,K,J,H,G,F,E,D,C}
    public UtmToGeo(double Huso, String Hemisferio,double Este, double Norte){
            this.Huso = Huso;
            this.Hemisferio = Hemisferio;
            this.Este = Este;
            this.Norte = Norte;
            G3 = 6378137.;  //a (semieje mayor) WGS84
            H3 = 6356752.314;  //b (semieje menor) WGS84
            CalcUtg();
    }
        public UtmToGeo(double Huso, String Hemisferio,double Este, double Norte, double aDatum, double bDatum){
            this.Huso = Huso;
            this.Hemisferio = Hemisferio;
            this.Este = Este;
            this.Norte = Norte;
            G3 = aDatum;
            H3 = bDatum;
            CalcUtg();
    }
    //muestra la informacion del Datum
    public String datumInfo(){
        return TXT(argos.get(0));
    }
    //Muestra información extra del calculo.
    public String resultInfo(){
        return TXT(argos.get(1));
    }
    public double getEste(){
        return Este;
    }
    public double getLatitude(){
        return lat;
    }
    public double getLongitude(){
        return lon;
    }
    public double getNorte(){
        return Norte;
    }
    public String getHemisferio(){
        return Hemisferio;
    }
    public int getHuso(){
        return (int) Huso;
    }
    private void CalcUtg(){
        double C17 = Huso;
        String F17 = Hemisferio;
        double C16 = Norte;
        double C15 = Este;
        
        double I3 = sqrt(G3*G3-pow(H3,2))/G3;
        double J3 = sqrt(G3*G3-H3*H3)/H3;
        double K3 =J3*J3;
        double L3 =+(G3*G3)/H3;
        ArrayList<String> pd = new ArrayList<>();
        pd.add("a (semieje mayor) \t"+G3);
        pd.add("b (semieje menor) \t"+H3);
        pd.add("Excentricidad     \t"+I3);
        pd.add("2ª Excentric. (e')\t"+J3);
        pd.add("e' ²              \t"+K3);
        pd.add("c (radio polar de curvatura)\t"+L3);
        argos.add((String[]) pd.toArray(new String[pd.size()]));

        double G17=6*C17-183;
        double H17;
        if (F17.equals("S")){H17=C16-10000000;}else{H17=C16;}
        double G15 = H17/(6366197.724*0.9996);
        double H15=(L3/sqrt(1+K3*(cos(G15))*(cos(G15))))*0.9996;
        double I15 =(C15-500000)/H15;
        double J15=sin(2*G15);
        double K15=J15*(cos(G15))*(cos(G15));
        double L15=G15+(J15/2.);
        double M15=(3*L15+K15)/4.;
        double N15=(5*M15+K15*(cos(G15))*(cos(G15)))/3.;
        double O15=(3./4.)*K3;
        double P15=(5./3.)*(O15)*(O15);
        double Q15=(35./27.)*(O15)*(O15)*(O15);
        double R15=0.9996*L3*(G15-(O15*L15)+(P15*M15)-(Q15*N15));
        double S15=(H17-R15)/H15;
        double T15=((K3*I15*I15)/2.)*(cos(G15))*(cos(G15));
        double U15=I15*(1-(T15/3.));
        double V15=S15*(1-T15)+G15;
        double W15=(exp(U15)-exp(-U15))/2.;
        double X15=atan(W15/cos(V15));
        double Y15=atan(cos(X15)*tan(V15));
        double H20=+(X15/PI)*180+G17;
        double J21=G15+(1+K3*(cos(G15))*(cos(G15))-(3./2.)*K3*sin(G15)*cos(G15)*(Y15-G15))*(Y15-G15);
        double H21=+(J21/PI)*180.;
        double C20=TRUNCAR(H20);
        double D20=TRUNCAR((H20-C20)*60);
        double E20=(((H20-C20)*60)-D20)*60;
        double C21=TRUNCAR(H21);
        double D21=TRUNCAR((H21-C21)*60);
        double E21=(((H21-C21)*60)-D21)*60;
        
        ArrayList<String> dats = new ArrayList<>();
        dats.add("UTM Este  X: \t"+C15);
        dats.add("UTM Norte Y: \t"+C16);
        dats.add("Huso :       \t"+C17);
        dats.add("Hemisferio : \t"+F17);
        dats.add("Meridiano Central: \t\t"+G17);
        dats.add("Y al sur del Ecuador: \t\t"+H17);
        dats.add("Fi'\t\t"+G15);
        dats.add("Ni\t\t"+H15);
        dats.add("a\t\t"+I15);
        dats.add("A1\t\t"+J15);
        dats.add("A2\t\t"+K15);
        dats.add("J2\t\t"+L15);
        dats.add("J4\t\t"+M15);
        dats.add("J6\t\t"+N15);
        dats.add("Alfa\t\t"+O15);
        dats.add("Beta\t\t"+P15);
        dats.add("Gamma\t\t"+Q15);
        dats.add("B(fi)\t\t"+R15);
        dats.add("b\t\t"+S15);
        dats.add("Zeta\t\t"+T15);
        dats.add("Xi\t\t"+U15);
        dats.add("Eta\t\t"+V15);
        dats.add("Sen h Xi\t\t"+W15);
        dats.add("Delta Lambda\t\t"+X15);
        dats.add("Tau\t\t"+Y15);
        dats.add("LON deg \t\t"+H20);
        dats.add("LAT rad\t\t"+J21);
        dats.add("LAT deg\t\t"+H21);
        dats.add("LON gg\t\t"+C20);
        dats.add("LON mm \t\t"+D20);
        dats.add("LON ss\t\t"+E20);
        dats.add("LAT gg \t\t"+C21);
        dats.add("LAT mm\t\t"+D21);
        dats.add("LAT ss\t\t"+E21);
        argos.add((String[]) dats.toArray(new String[dats.size()]));
        ArrayList<String> result = new ArrayList<>();
        result.add(""+H21);
        result.add(""+H20);
        lat = H21;
        lon = H20;
        argos.add((String[]) result.toArray(new String[result.size()]));
    }
}
