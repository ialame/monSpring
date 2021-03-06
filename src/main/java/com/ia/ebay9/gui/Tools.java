package com.ia.ebay9.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by fxc on 12/10/2017.
 */
public class Tools {
    public static String removeAccent(String source) {
        return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
    }

    public static String removeCaracSpecial(String s) {
        StringBuffer sb= new StringBuffer();
        char c;
        for(int k=0;k<s.length();k++){
            c=s.charAt(k);
            if(( 0<=c-'a' && c-'a'<26)||( 0<=c-'A' && c-'A'<26)||( 0<=c-'0' && c-'0'<10) || c=='/') sb.append(c);
        }
        String ss=sb.toString();
        ss=ss.replace("//","/");
        return ss;
    }
    public static String removeEspace(String s) {
        StringBuffer sb= new StringBuffer();
        char c;
        for(int k=0;k<s.length();k++){
            c=s.charAt(k);
            if(c!=' ') sb.append(c);
        }
        String ss=sb.toString();
        ss=ss.replace("//","/");
        return ss;
    }
    public static String removeCaracSpecialSaufEspace(String s) {
        StringBuffer sb= new StringBuffer();
        char c;
        for(int k=0;k<s.length();k++){
            c=s.charAt(k);
            if(( 0<=c-'a' && c-'a'<26)||( 0<=c-'A' && c-'A'<26)||( 0<=c-'0' && c-'0'<10) || c=='/'  || c==' ') sb.append(c);
        }
        String ss=sb.toString();
        ss=ss.replace("//","/");
        return ss;
    }
    public static String remplaceCaracSpecialParBlanc(String s) {
        StringBuffer sb= new StringBuffer();
        char c;
        for(int k=0;k<s.length();k++){
            c=s.charAt(k);
            if(( 0<=c-'a' && c-'a'<26)||( 0<=c-'A' && c-'A'<26))
                sb.append(c);
            else
                sb.append(" ") ;
        }
        return sb.toString();
    }
    public static boolean anglais(String ss){// si la carte est en anglais on la supprime
        //System.out.print(s.indexOf("Anglais"));
        String s=ss.toLowerCase();
        //System.out.print(s+"\n");
        boolean b  = Pattern.matches(".*[-(~\\s]+\\s*[eE][nN]\\s*[-)~\\s]+\\s*.*", s) ;
        b = b || s.indexOf("anglais") != -1;
        b = b || s.indexOf(" engl") != -1;
        b = b || s.indexOf(" US ") != -1;
        return b;
    }
    public static boolean vo(String ss){// si la carte est en anglais on la supprime
        //System.out.print(s.indexOf("Anglais"));
        String s=ss.toLowerCase();
        //System.out.print(s+"\n");
        boolean b = Pattern.matches(".*[-(~\\s]+\\s*[vV][oO]\\s*[-)~\\s]+\\s*.*", s) ;
        return b;
    }
    public static boolean aRejeter(String ss,String tt){  // si le texte contient la sous chaine tt on supprime
        //System.out.print(s.indexOf("Anglais"));
        String s=ss.toLowerCase();
        //System.out.print(s+"\n");
        boolean b=s.indexOf(tt)==-1;
        return !b;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void saveComponentAsJPEG(Component myComponent, String filename) {
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graph = myImage.createGraphics();
        myComponent.paint(graph);
        /*   A rectifier
        try {
            OutputStream out = new FileOutputStream(filename);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(myImage);
            out.close();
            System.out.println("la courbe a ete sauvée dans le fichier "+filename);
        } catch (Exception e) {
            System.out.println("Impossible de sauver la courbe : "+e);
        }
        */
    }
    public void sauverImage(BufferedImage image,String nomImage) throws IOException
    {
        File nomfichier = new File("C:/Users/arezki/Desktop/images/" + nomImage + ".bmp");// ou jpg

        javax.imageio.ImageIO.write(image, "BMP", nomfichier);//ou JPG
    }



}
