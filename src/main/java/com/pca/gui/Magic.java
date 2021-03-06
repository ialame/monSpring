package com.pca.gui;

import com.pca.model.CartePokemon;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ia on 12/04/2019.
 */
public class Magic {
    JTextField tfExtension = null;
    JRadioButton allExts, newExt;
    JCheckBox reclasser;
    JTextField tfNumber,tfSur;
    //JTableCarteJAP jtcj;
    //JTablePainterCarte jTablePainterCarte = new JTablePainterCarte();
    JTableMagic jTableMagic;
    Thread t,t2;
    JPanel tab= null;
    ApplicationPM app;
    public ExtensionMagic extensionMagic;
    public int id=0;
    /**
     * ici le volet Magic
     */
    Magic(ApplicationPM app){
        this.app=app;
        app.pays=100;
        app.typeCarte = K.MAGIC;
        extensionMagic = new ExtensionMagic();
        jTableMagic = new JTableMagic(extensionMagic.getCartes(),app);

    }
    public JPanel ihm() {

        JPanel pMagic = new JPanel(new BorderLayout());
        JButton bJson2sql, bImages, bFiltres;
        ExtensionMagic extensionMagic = null;
        /////////////////////   N O R D ////////////////////
        JPanel pNord = new JPanel();

        JPanel pChoix = new JPanel(new BorderLayout());
        ButtonGroup gb = new ButtonGroup();
        JPanel pNouvelle = new JPanel(new GridLayout(4,1));
        TitledBorder title;
        title = BorderFactory.createTitledBorder("");
        pNouvelle.setBorder(title);

        newExt = new JRadioButton("Nouvelle extension");
        newExt.setSelected(true);
        newExt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfExtension.setEnabled(true);
            }
        });



        pNouvelle.add(newExt);
        JPanel jpCode = new JPanel();
        jpCode.add(new JLabel("code:"));
        jpCode.add(tfExtension = new JTextField(10));
        pNouvelle.add(jpCode);
        reclasser =  new JCheckBox("Reclasser les cartes");
        reclasser.setSelected(false);
        pNouvelle.add(reclasser);
        JPanel pRecherche = new JPanel();
        pRecherche.add(new JLabel("Numéro de la première carte:"));
        tfNumber = new JTextField(5);
        pRecherche.add(tfNumber);
        pRecherche.add(new JLabel("/"));
        tfSur = new JTextField(5);
        pRecherche.add(tfSur);
        pNouvelle.add(pRecherche);


        gb.add(newExt);
        allExts = new JRadioButton("Toutes les extensions");
        allExts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfExtension.setEnabled(false);
            }
        });
        JPanel pToutes = new JPanel();
        pToutes.add(allExts);
        gb.add(allExts);
        pChoix.add(pNouvelle,BorderLayout.NORTH);
        pChoix.add(pToutes,BorderLayout.SOUTH);
        pNord.add(pChoix);

        pMagic.add(pNord, BorderLayout.NORTH);
        /////////////////////   C E N T R E  ////////////////////
        JPanel pCentre = new JPanel(new BorderLayout());
        JPanel pJson2SQL = new JPanel(new FlowLayout());

        pJson2SQL.add(bJson2sql = new JButton("Exécuter Json2SQL"));

        title = BorderFactory.createTitledBorder("Json2SQL");
        pJson2SQL.setBorder(title);
        pCentre.add(pJson2SQL, BorderLayout.NORTH);





        JPanel pCentreSud = new JPanel(new BorderLayout());



        JPanel pImages = new JPanel(new FlowLayout());
        pImages.add(bImages = new JButton("Sauvegarder les images"));

        //TitledBorder title;
        title = BorderFactory.createTitledBorder("Images");
        pImages.setBorder(title);
        pCentreSud.add(pImages, BorderLayout.WEST);

        JPanel pFiltres = new JPanel(new FlowLayout());
        pFiltres.add(bFiltres = new JButton("Appliquer les filtres"));

        //TitledBorder title;
        title = BorderFactory.createTitledBorder("Filtres");
        pFiltres.setBorder(title);
        pCentreSud.add(pFiltres, BorderLayout.EAST);

        //pCentre.add(pCentreSud,BorderLayout.SOUTH);
        pMagic.add(pCentre, BorderLayout.CENTER);
        /////////////////////   S U D  ////////////////////
        bJson2sql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(tab==null) {
                    app.setResulTableMagic(jTableMagic);
                    int width = app.getWidth();
                    jTableMagic.setSize(width, (width * 3) / 4);
                    tab = (JPanel) ((JTabbedPane) app.getContentPane().getComponent(0)).getSelectedComponent();
                    tab.add(jTableMagic, BorderLayout.SOUTH);
                    app.pack();
                }
                if(reclasser.isSelected() || tfNumber.getText().equals("") || tfSur.getText().equals(""))return;
                try {
                    if(allExts.isSelected()){
                        parseExtensions();
                    }else {
                        parseExtension(tfExtension.getText());
                    }

                } catch (Exception e1) {
                    System.out.println("Dans Magic :"+e1);
                }

            }
        });
        bImages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PrintWriter log = null;

                //récupère l'extension à partir de Textfield
                if (tfExtension.getText().equals("")) return;
                String ext = tfExtension.getText();

                ExtensionMagic em = new ExtensionMagic(ext);
                int annee = em.getReleaseDate().getYear();

                String dossier = "img/cards/magic/US";
                try {
                    log = new PrintWriter(dossier + "/magic.log");
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                File dir = new File(dossier);
                boolean isCreated = dir.mkdirs();

                //SerieMagic s = extensionMagic.getSerie();
                dir = new File(dossier + "/" + annee);
                isCreated = dir.mkdirs();
                dir = new File(dossier + "/" + annee + "/" + em.getCode());
                isCreated = dir.mkdirs();
                String transform1 = "";
                boolean recto = true;
                for (CartePokemon c : em.getCartes()) {
                    CarteMagic cm=(CarteMagic) c;
                    if (cm.getIsOversized()) continue;
                    String urlimage = null;

                    urlimage = "https://scryfall.com/card/" + em.getCode().toLowerCase() + "/" + cm.getNumber();
                    if (cm.getLayout() != null && cm.getLayout().equals("transform"))
                        if (recto) {
                            //transform1 = cm.getName();
                            recto = false;
                        } else {
                            //transform1 = (transform1 + "-" + cm.getName()).replace(" ", "-").replace("'", "");
                            urlimage += "/" + transform1.toLowerCase() + "?back";
                            transform1 = "";
                        }
                    //if(cm.isToken && em.id!=273 && em.id!=419 && em.id!=414   && em.id!=371)
                        urlimage = "https://scryfall.com/card/t"+em.getCode().toLowerCase() +"/"+cm.getNumber();

                    Document doc = null;
                    int k = 0;
                    while ((doc == null || doc.html().isEmpty()) && k++ < 100)
                        try {
                            String optionNavigateur = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
                            doc = Jsoup.connect(urlimage).userAgent(optionNavigateur).get();
                            doc = Jsoup.parseBodyFragment(doc.toString());
                        } catch (Exception ex) {
                            System.out.println("Erreur internet !");
                            log.println("carte: " + cm.getId());
                            log.println("Erreur internet !: " + ex);
                            log.print(" k= : " + k);
                            log.flush();
                        }
                    if (k == 100) continue;
                    String img = doc.select("div.card-image-front").first().select("img").attr("src");// c'est un BIG dans TABLE


                    try {

                        URL url2 = new URL(img);
                        BufferedImage image = ImageIO.read(url2);
                        //Thread.sleep(3000);
                        String fichier = dossier + "/" + annee + "/" + em.getCode() + "/" + cm.getId() + ".jpg";
                        File outputfile = new File(fichier);
                        ImageIO.write(image, "jpg", outputfile);

                        //log.println("carte: " + cm.getId());
                        //log.flush();
                        System.out.println("carte: " + cm.getId());
                    } catch (Exception excep) {
                        excep.printStackTrace();
                        log.println("carte: " + cm.getId());
                        log.println("erreur: " + excep);
                        log.flush();

                    }
                }

                System.out.println("  F I N   D E    L ' E X T E N S I O N ");


            }
        });

        bFiltres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                try {
                    //em.imagesDesCartesFiltrees();
/*
                    FiltterMagic.carteSplit();
                    FiltterMagic.carteMeld();
                    FiltterMagic.carteFlip();
                    FiltterMagic.cartePlanarSchemeVanguard();
                    FiltterMagic.carteTransform();
                    FiltterMagic.carteIsOversized();
                    FiltterMagic.carteCollectorsEdition();

*/
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });



        return pMagic;
    }
    public  String chargeJson(String extension){
        StringBuilder sb = new StringBuilder();
        try {

            URL url = new URL("https://mtgjson.com/json/"+extension+".json");
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            System.setProperty("http.agent", "Chrome");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));

            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return sb.toString();
    }

    /**
     *
     * @return int
     */
    public static int getMaxIdExtension(){
        int idExtension=0;
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            String sql2 = "select max(id) as max from  " +ApplicationPM.DB.getTable_ExtensionMagic();
            ResultSet resultMax = statement.executeQuery(sql2);
            if (resultMax.next()) {
                idExtension=resultMax.getInt("max") ;//https://scryfall.com/card/ktk/1/abzan_battle_priest
            }
            statement.close();
        } catch (Exception e1) {
            System.out.println(e1);

        }*/
        return idExtension;
    }

    ///////////////////////////////////////////////////
    //
    //              parseExtension (json)
    //
    //////////////////////////////////////////////////
    public void parseExtension(String ext) {
        int idCarte=0;
        int idToken=0;
        try {
            //CarteMagic.creerBDD();
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(this.chargeJson(ext));
            JSONObject extension  = (JSONObject) object;
            id=getMaxIdExtension();
            id++;
            ExtensionMagic extensionMagic = new ExtensionMagic();
            extensionMagic.setId(id);
            extensionMagic.init(extension);
            int annee = extensionMagic.getAnnee();
            String zeros="";
            if(id<10) zeros="00";
            else if(id<100) zeros="0";
            extensionMagic.setIdPCA(annee+zeros+id+"0");
            //extensionMagic.save(ApplicationPM.DB.getTable_ExtensionMagic()); // commenté provisoirement
            for(int j=0;j<extensionMagic.JsonCartes.size();j++) {
                //CarteMagicComplete cm = new CarteMagicComplete(com.pca.gui.ApplicationPM.DB.getTable_CarteMagic());


                //cm.setExtension(extensionMagic);
                //cm.getCard((JSONObject) extensionMagic.JsonCartes.get(j));
                if (tfNumber.getText().equals("001")) {
                    try {
                        String s = tfNumber.getText();
                        StringBuffer sb = new StringBuffer();
                        /*
                        for(int i=0;i<cm.number.length();i++){
                            char c= cm.number.charAt(i);
                            if('0'<= c && c <='9')
                                sb.append(c);
                        }

                         */
                        int nb =Integer.parseInt(sb.toString());
                        /*
                        if (nb<10)
                            cm.num="00"+nb;
                        else if(nb<100)
                            cm.num="0"+cm.num;

                         */

                    }catch(Exception ex){

                    }
                }
                /*
                cm.sur = tfSur.getText();
                cm.recherche = cm.num+"/"+cm.sur;

                 */
                //cm.saveRulings();   commenté provisoirement
                //cm.isToken=false;
                //System.out.println(cm);

                //jTableMagic.getModele().addCarte(cm);


              //  cm.save(); // commenté provisoirement



            }
            String Numtoken="",SurToken="";
            for(int j=0;j<extensionMagic.JsonTokens.size();j++) {
                CarteMagicComplete cm = new CarteMagicComplete();//com.pca.gui.ApplicationPM.DB.getTable_CarteMagic());
                if(j==0) {
                    Numtoken = JOptionPane.showInputDialog("Premier Num token?");
                    SurToken = JOptionPane.showInputDialog("Sur token?");
                }
                //idCarte++;
                //cm.setId(idCarte);
                //cm.setExtension_id(id);
                cm.setExtension(extensionMagic);
                cm.getCard((JSONObject) extensionMagic.JsonTokens.get(j));
                if (Numtoken.equals("001")) {
                    try {
                        StringBuffer sb = new StringBuffer();
                        for(int i=0;i<cm.number.length();i++){
                            char c= cm.number.charAt(i);
                            if('0'<= c && c <='9')
                                sb.append(c);
                        }
                        int nb =Integer.parseInt(sb.toString());
/*
                        if (nb<10)
                            cm.num="00"+nb;
                        else if(nb<100)
                            cm.num="0"+cm.num;

 */

                    }catch(Exception ex){

                    }
                }
                /*
                cm.sur = SurToken;
                cm.recherche = cm.num+"/"+cm.sur;

                 */


                //cm.saveRulings();   commenté provisoirement
                cm.isToken=true;
                cm.setRarity("token");
                System.out.println(cm);
                jTableMagic.getModele().addCarte(cm);
                //cm.save(); // commenté provisoirement

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////
    //
    //              parseExtensions (json)
    //
    //////////////////////////////////////////////////
    public void parseExtensions() {
        int idCarte=0;
        int idToken=0;
        try {
            //CarteMagic.creerBDD();
            JSONParser jsonParser = new JSONParser();

            File file = new File("json/AllSets.2019.01.15.json");
            //File file = new File("https://mtgjson.com/json/SetList.json");
            Object object = jsonParser.parse(new FileReader(file));

            JSONObject jsonObject = (JSONObject) object;
            Set<Object> set = jsonObject.keySet();
            Iterator<Object> iterator = set.iterator();

            while (iterator.hasNext()) {
                Object obj = iterator.next();
                System.out.println("\nExtension : " + obj+"\n");
                if(obj.toString().equals("PPRE"))
                    obj=obj;
                //CarteMagic cm = new CarteMagic(obj.toString());
                JSONObject extension = ((JSONObject) jsonObject.get(obj));
                id++;
                ExtensionMagic em = new ExtensionMagic();
                em.setId(id);
                em.init(extension);

                //cm.saveRulings();
                int annee = em.getAnnee();
                String zeros="";
                if(id<10) zeros="00";
                else if(id<100) zeros="0";
                em.setIdPCA(annee+zeros+id+"0");
                 //em.save(ApplicationPM.DB.getTable_ExtensionMagic());//  commenté provisoirement
                for(int j=0;j<em.JsonCartes.size();j++) {
                    CarteMagicComplete cm = new CarteMagicComplete();//com.pca.gui.ApplicationPM.DB.getTable_CarteMagic());
                    cm.setExtension(em);
                    cm.getCard((JSONObject) em.JsonCartes.get(j));
                    idCarte++;
                    //cm.setId(idCarte);
                    //cm.setExtension_id(id);

                    //cm.saveRulings();
                    cm.isToken=false;
                    cm.save();

                }
                for(int j=0;j<em.JsonTokens.size();j++) {
                    CarteMagicComplete cm = new CarteMagicComplete();//com.pca.gui.ApplicationPM.DB.getTable_CarteMagic());
                    cm.setExtension(em);
                    cm.getCard((JSONObject) em.JsonTokens.get(j));
                    idCarte++;
                    //cm.setId(idCarte);
                    //cm.setExtension_id(id);

                    //cm.saveRulings();
                    cm.isToken=true;
                    cm.setRarity("token");
                    cm.save();

                }

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    public static void saveImages() {
        String url = "https://scryfall.com/sets/bbd?as=grid&order=name";

        int iii = 0;
        Document docPage = null, doc = null;
        ArrayList<String>[] NameAngJap = new ArrayList[5];

        for (int i = 0; i < 5; i++) NameAngJap[i] = new ArrayList<String>();



        String[] s={"OARC","OE01","OHOP","OPC2","OPCA","PRED","PRNA","PRW2","RNA"};

        for (String x : s) {
            url = "https://scryfall.com/sets/"+x+"?as=grid&order=name";
            File dir = new File("images2/"+x);
            boolean isCreated = dir.mkdirs();
            try {
                docPage = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                doc = Jsoup.parseBodyFragment(docPage.toString());
            } catch (Exception e) {

            }

            Elements BIG =  doc.select(".card-grid-inner").first().children();// c'est un BIG dans TABLE
            Element big = null;
            for(int kk=0;kk<BIG.size();kk++){
                //while (BIG.hasNext()) {
                //kk++;
                big = BIG.get(kk);
                String uuid = big.attr("data-card-id");

                /*try{
                    Statement statement = ApplicationPM.DB.getConnect().createStatement();
                    String sqlSide = "select side from " + ApplicationPM.DB.getTable_CarteMagic() + " where id=" + uuid;
                    ResultSet resultSide = statement.executeQuery(sqlSide);
                    String side="";
                    if (resultSide.next()) {
                        side = resultSide.getString("side");
                        uuid+=side;
                    }
                    statement.close();

                }catch(Exception euuid){

                }*/
                String img = big.select("img").attr("src");
                if(img.equals(""))img = big.select("img").attr("data-src");

                BufferedImage image = null;
                System.out.println("kk="+kk);
                System.out.println("img="+img);
                if(img.equals("")){
                    continue;
                }

                try {

                    URL url2 = new URL(img);
                    image = ImageIO.read(url2);
                    //Thread.sleep(3000);
                    ImageIO.write(image, "jpg",new File("images2/"+x+"/"+uuid+".jpg"));
                    System.out.println("images2/"+x+"/"+uuid+".jpg");
                    if(kk%20==0){
                        System.out.println("coucou");
                        //Thread.sleep(5000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
            System.out.println("Done");

        }
    }

}
