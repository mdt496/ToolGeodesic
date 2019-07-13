/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z5ac4;

import static java.lang.Math.*;

/**
 *
 * @author MDLORIAL
 */
public class toolExt {
        public static double RND(double mat,int acuracy){
        acuracy = round((float) pow(10,acuracy));
        return (double) round(mat*acuracy)/acuracy;
    }
        //Metodo de Excel TRUNCAR
    public static int TRUNCAR(double x) {
        String uv = ""+x;
        return Double.valueOf(uv.substring(0,uv.indexOf("."))).intValue();
    }
            //imprime una matriz String con un tamaño optimo
    public static String TXT(String [] mat){
        String out = "";
        int j;
        for (j=0;j<=mat.length-2;j++){
            out+= mat[j];
            out+="\n";
        }
        out+= mat[mat.length-1];
        return out;
    }
        //Trasforma -18.18805277 a [-1,18,11,16.99] [signo,gg,mm,ss.ss]
    public static double[] degTOagms(double a) {
           //Nota: los seg se redondea a 2 decimales
        double sig = signum(a);
            a = abs(a);
            long hh,mm;
            double ss;
            long ssi = Double.valueOf(a*3600*1000).longValue();
            hh = ssi/(3600*1000);
            mm = ssi/(1000*60)-hh*60;
            ss = ssi/1000.-mm*60.-hh*3600.;
            ss=RND(ss,2);
            if(ss==60){
                mm=mm+1;
                ss=0;
            }
            if(mm==60){
                hh=hh+1;
                mm=0;
            }
            if(hh==0 && mm==0 && ss==0) sig=1;
            double [] asd = {sig,hh,mm,ss};
            return asd;
    }

}
