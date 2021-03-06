package com.pca.gui;

import com.pca.model.Extension;
import com.pca.model.ExtensionJAP;
import com.pca.model.ExtensionUS;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PageGui extends JPanel implements ActionListener {
    public Formulaire formulaire;
    public ImagePanel logo = new ImagePanel();
    public JPanel grilleCouleurs;
    public JButton bouton;
    public JPanel tableau;
    public Extension extension;
    public ApplicationPM app;
    public JTablePok jTablePok;
    public String pays;
    JLabel jlTitre = null;
    PageGui(ApplicationPM app,String pays){
        this.app=app;
        this.pays=pays;
        setLayout(new BorderLayout());
        JButton[] jbs = new JButton[5];
        for(int i=0;i<5;i++)
            jbs[i]= new JButton(i+"");
        jbs[3]= new JButton("Actualiser");
        jbs[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTablePok.actualier();
            }
        });
        JPanel pLogo = new JPanel();
        if(pays.equals("jap")) {
            setLogo("flagJAP2.png");
            jlTitre = new JLabel("Cartes Pokemon JAP");
        }else if(pays.equals("us")) {
            setLogo("flagUSA2.png");
            jlTitre = new JLabel("Cartes Pokemon US");
        }
        logo.setPreferredSize(new Dimension(400,300));
        pLogo.add(logo);
        this.add(jbs[0],BorderLayout.CENTER);
        //this.add(jbs[2],BorderLayout.SOUTH);
        this.add(jbs[3],BorderLayout.EAST);
        this.add(jbs[4],BorderLayout.WEST);


        ////////////////////////////        P A N E L          D U          N O R D       /////////////

        JPanel pNord = new JPanel(new BorderLayout());
        this.add(pNord,BorderLayout.NORTH);
        JPanel pNordEst = new JPanel(), pNordWest = new JPanel(new BorderLayout());

        pNord.add(pNordEst,BorderLayout.EAST);
        pNord.add(pNordWest,BorderLayout.WEST);

        pNordEst.add(pLogo,BorderLayout.EAST);

        Font font1 = new Font("Comic Sans MS", Font.BOLD, 50);
        jlTitre.setFont(font1);
        pNordWest.add(jlTitre,BorderLayout.NORTH);
        String labels1[] = {"serie_id","extension_id","concat"};
        formulaire = new Formulaire(labels1,"Extension principale",pays,app);
        pNordWest.add(formulaire,BorderLayout.SOUTH);

        JPanel pBouton = new JPanel();
        String titreBouton = pays.equals("jap")? "Ajouter cartes japonaises":"Ajouter cartes US";
        pBouton.add(bouton=new JButton(titreBouton));
        this.add(pBouton,BorderLayout.CENTER);
        jTablePok =new JTablePok(new ArrayList<CarteUsJap>(),this);
        this.add(jTablePok,BorderLayout.SOUTH);
        //pNord.add(jbs[1],BorderLayout.EAST);
        bouton.addActionListener(this::actionPerformed);
    }

    public Formulaire getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(Formulaire formulaire) {
        this.formulaire = formulaire;
    }

    public JPanel getLogo() {
        return logo;
    }

    public void setLogo(String urlimage) {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(urlimage));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        logo.setImage(image);
    }

    public JPanel getGrilleCouleurs() {
        return grilleCouleurs;
    }

    public void setGrilleCouleurs(JPanel grilleCouleurs) {
        this.grilleCouleurs = grilleCouleurs;
    }

    public JButton getBouton() {
        return bouton;
    }

    public void setBouton(JButton bouton) {
        this.bouton = bouton;
    }

    public JPanel getTableau() {
        return tableau;
    }

    public void setTableau(JPanel tableau) {
        this.tableau = tableau;
    }
    public static void main(String[] str){
        /*
        JFrame f = new JFrame();
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File("pok.png"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        ImagePanel imgPanel = new ImagePanel();
        imgPanel.setImage(image);

        f.getContentPane().add(imgPanel);
        f.setSize(200,400);
        f.setVisible(true);
        */
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        app.pays=K.JAPON;
        //extensionJAP = (ExtensionJAP) formCartesJAP.saisir();
        extension=null;
        for (Component x : formulaire.getComponents()) {
            if (x.getClass() == JComboBox.class)
                if (x.getName().equals("extension_id")) {
                    if(pays.equals("jap"))
                        extension = (ExtensionJAP) ((JComboBox) x).getSelectedItem();
                    else if(pays.equals("us"))
                        extension = (ExtensionUS) ((JComboBox) x).getSelectedItem();
                    break;
                }
        }
        //parser(extensionJAP);
        new ParseExtensionPok(extension, this);
    }
}
