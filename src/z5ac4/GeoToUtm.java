/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z5ac4;

import static java.lang.Math.*;
import java.util.ArrayList;
import static z5ac4.toolExt.*;

/**
 *
 * @author MDLORIAL
 */
public class GeoToUtm {
    private final double lat,lon;
    private double Este, Norte;
    private  int Huso;
    private String Hemisferio;
    private final double G3,H3;
    private ArrayList<String []> argos = new ArrayList<>();
    
    public GeoToUtm(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        G3 = 6378137.;  //a (semieje mayor) WGS84
        H3 = 6356752.314;  //b (semieje menor) WGS84
        CalcGtu();
    }
    public GeoToUtm(double Latitude, double Longitude, double aDatum, double bDatum){
        this.lat = Latitude;
        this.lon = Longitude;
        this.G3 = aDatum;
        this.H3 = bDatum;
        CalcGtu();
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
        return Huso;
    }
    private void CalcGtu(){
        double [] gmsL = degTOagms(lat);
        String F7 = "S";
        if (gmsL[0]>0) F7 = "N";
        double C7 = gmsL[1];
        double D7 = gmsL[2];
        double E7 = gmsL[3];
        double [] gmsLG = degTOagms(lon);
        String F6 = "W";
        if (gmsLG[0]>0) F6 = "E";
        double C6 = gmsLG[1];
        double D6 = gmsLG[2];
        double E6 = gmsLG[3];
        double I3 = sqrt(G3*G3-pow(H3,2))/G3;
        double J3 = sqrt(G3*G3-H3*H3)/H3;
        double K3 =J3*J3;
        double L3 =+(G3*G3)/H3;
        
        //datum
        ArrayList<String> pd = new ArrayList<>();
        pd.add("a (semieje mayor) \t"+G3);
        pd.add("b (semieje menor) \t"+H3);
        pd.add("Excentricidad     \t"+I3);
        pd.add("2ª Excentric. (e')\t"+J3);
        pd.add("e' ²              \t"+K3);
        pd.add("c (radio polar de curvatura)\t"+L3);
        argos.add((String[]) pd.toArray(new String[pd.size()]));
        
        double G6;
        if (F6.equals("W")){
            G6 = -1*(((E6/60)/60)+(D6/60)+C6);
        }else{
            G6 = ((E6/60)/60)+(D6/60)+C6;
        }
        double G7;
        if (F7.equals("S")){
            G7 = -1*(((E7/60)/60)+(D7/60)+C7);
        }else{
            G7 = ((E7/60)/60)+(D7/60)+C7;
        }
        double H6=G6*PI/180.;
        double H7=G7*PI/180.;
        int I6 = Integer.valueOf((""+((G6/6.)+31)).substring(0, (""+((G6/6.)+31)).indexOf(".")));
        double J6 =6*I6-183;
        double K6 =+H6-((J6*PI)/180);
        double L6 =cos(H7)*sin(K6);
        double M6 =(1./2.)*log((1+L6)/(1-L6));
        double N6=atan((tan(H7))/cos(K6))-H7;
        double O6=(L3/pow((1+K3*pow((cos(H7)),2)),(1./2.)))*0.9996;
        double P6 =(K3/2.)*M6*M6*pow((cos(H7)),2);
        double Q6 =sin(2*H7);
        double R6=+Q6*pow((cos(H7)),2);
        double S6=H7+(Q6/2.);
        double T6=((3*S6)+R6)/4.;
        double U6=(5*T6+R6*pow((cos(H7)),2))/3.;
        double V6=(3./4.)*K3;
        double W6=(5./3.)*V6*V6;
        double X6=(35./27.)*V6*V6*V6;
        double Y6=0.9996*L3*(H7-(V6*S6)+(W6*T6)-(X6*U6));
        double F10=M6*O6*(1+P6/3.)+500000.;
        double F11;
                if(F7.equals("S")){
                    F11= N6*O6*(1+P6)+Y6+10000000.;
                }else{
                    F11= N6*O6*(1+P6)+Y6;}
        int F12=I6;
        String I7 = null;
        if (G7 > 0){
            I7 = "N";
        }else{
            I7 = "S";
        }
        
        ArrayList<String> geoutm = new ArrayList<>();
        geoutm.add("Lon gg\t\t"+C6);
        geoutm.add("Lon mm\t\t"+D6);
        geoutm.add("Lon ss\t\t"+E6);
        geoutm.add("Lon EW\t\t"+F6);
        geoutm.add("Lat gg\t\t"+C7);
        geoutm.add("Lat mm\t\t"+D7);
        geoutm.add("Lat ss\t\t"+E7);
        geoutm.add("Lat NS\t\t"+F7);
        geoutm.add("LONG deg:\t\t"+G6);
        geoutm.add("LAT  deg:\t\t"+G7);
        geoutm.add("LONG rad:\t\t"+H6);
        geoutm.add("LAT  rad:\t\t"+H7);
        geoutm.add("Calculo Huso \t\t"+I6);
        geoutm.add("Meridiano Huso \t\t"+J6);
        geoutm.add("Delta Lambda \t\t"+K6);
        geoutm.add("A \t\t"+L6);
        geoutm.add("Xi\t\t"+M6);
        geoutm.add("Eta\t\t"+N6);
        geoutm.add("Ni\t\t"+O6);
        geoutm.add("Zeta\t\t"+P6);
        geoutm.add("A1\t\t"+Q6);
        geoutm.add("A2\t\t"+R6);
        geoutm.add("J2\t\t"+S6);
        geoutm.add("J4\t\t"+T6);
        geoutm.add("J6\t\t"+U6);
        geoutm.add("Alfa\t\t"+V6);
        geoutm.add("Beta\t\t"+W6);
        geoutm.add("Gamma\t\t"+X6);
        geoutm.add("B(fi)\t\t"+Y6);
        geoutm.add("UTM Este X  = \t"+F10);
        geoutm.add("UTM Norte Y = \t"+F11);
        geoutm.add("Huso        = \t"+F12);
        geoutm.add("Hemisferio  = \t"+I7);
        argos.add((String[]) geoutm.toArray(new String[geoutm.size()]));
        ArrayList<String> result = new ArrayList<>();
        result.add(""+F10);
        result.add(""+F11);
        result.add(""+F12);
        result.add(""+I7);
        Este = F10;
        Norte = F11;
        Huso = F12;
        Hemisferio = I7;
        argos.add((String[]) result.toArray(new String[result.size()]));
    }
}
