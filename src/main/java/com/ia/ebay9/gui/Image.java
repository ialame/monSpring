package com.ia.ebay9.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Date;

public class Image {
    public String src;
    public String imageLocal;
    public String imageVPS;
    public String tailleImg;
    public String type;
    Date downloaded_at = new Date();
    public int NS;

    Image(String src){
        this.src=src;
    }

    void save(){
        try {
            URL url = new URL(src);
            BufferedImage image = ImageIO.read(url);
            setTailleImg(image.getWidth() + "x" + image.getHeight());
            File outputfile = new File(getImageLocal());
            ImageIO.write(image, type, outputfile);
        }catch (Exception e){
            System.out.println("Problème de sauvegarde de l'image sur le disque");
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getImageLocal() {
        return imageLocal;
    }

    public void setImageLocal(String imageLocal) {
        this.imageLocal = imageLocal;
    }

    public String getImageVPS() {
        return imageVPS;
    }

    public void setImageVPS(String imageVPS) {
        this.imageVPS = imageVPS;
    }

    public String getTailleImg() {
        return tailleImg;
    }

    public void setTailleImg(String tailleImg) {
        this.tailleImg = tailleImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNS() {
        return NS;
    }

    public void setNS(int NS) {
        this.NS = NS;
    }

    public Date getDownloaded_at() {
        return downloaded_at;
    }

    public void setDownloaded_at(Date downloaded_at) {
        this.downloaded_at = downloaded_at;
    }
}
