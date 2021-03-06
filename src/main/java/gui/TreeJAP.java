package com.pca.gui;
//import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import com.pca.entity.CartePokemon;
import com.pca.entity.ExtensionJAP;
import com.pca.entity.SerieJAP;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JTree basic tutorial and example
 * @author wwww.codejava.net
 */
public class TreeJAP extends JPanel
{
    private JTree tree;
    private JLabel selectedLabel;
    //private JTableCarte jTableCarte = new JTableCarte(new ArrayList(),null);
    JTablePok jTablePok;
    //JTablePainterCarte jTablePainterCarte = new JTablePainterCarte();
    Thread t,t2;
    public TreeJAP(List<SerieJAP> series, ApplicationPM app)
    {
        //jTablePok = app.pokemon.jTablePok;
        setLayout(new BorderLayout());
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("BDD");
        //create the child nodes
        ArrayList<DefaultMutableTreeNode> LesSeries = new ArrayList<>();
        for(SerieJAP s : series){
            DefaultMutableTreeNode serie =new DefaultMutableTreeNode(s);
            //  MIGRATION
            /*for(ExtensionJAP0 e : s.getExtensionsJAP()) {
                DefaultMutableTreeNode extension =new DefaultMutableTreeNode(e);
                for(Object c :e.getCartes()){
                    DefaultMutableTreeNode carte =new DefaultMutableTreeNode(c);
                    extension.add(carte);
                }

                serie.add(extension);
            }*/
            root.add(serie);
        }

        tree = new JTree(root);
        ImageIcon imageIcon = new ImageIcon("leaf.jpg");
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(imageIcon);

        tree.setCellRenderer(renderer);
        tree.setShowsRootHandles(true);
        tree.setRootVisible(false);
        //add(new JScrollPane(tree));
        add(new JScrollPane(tree), BorderLayout.CENTER);
        selectedLabel = new JLabel();
        add(selectedLabel, BorderLayout.SOUTH);
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(selectedNode!=null) {
                    if(selectedNode.getUserObject() instanceof ExtensionJAP) {
                        ExtensionJAP extensionJAP= (ExtensionJAP) selectedNode.getUserObject();
                        String numjap = ((CartePokemon) extensionJAP.getCartes().get(0)).getJapNum();
                        extensionJAP.setPremierNumero(numjap);
                        //System.out.println(extensionJAP.getURL());
                        //////////////////////////////////////////////////////////////
                        app.setResulTable(jTablePok);
                        int width = getWidth();
                        jTablePok.setSize(width, (width * 3) / 4);
                        JPanel tab = (JPanel) ((JTabbedPane) app.getContentPane().getComponent(0)).getSelectedComponent();
                        tab.add(jTablePok, BorderLayout.SOUTH);
                        app.pack();



                        jTablePok.getModele().clearCartes();
                        //jTablePainterCarte.jt = jTablePok; //Fill in with the bar you want painted
                        t = new Thread(jTablePok);
                        t.start();
                        ParseExtensionPok parseExtensionPok = new ParseExtensionPok(extensionJAP, app);
                        parseExtensionPok.jTablePok = jTablePok;
                        t2 = new Thread(parseExtensionPok);
                        t2.start();
                        //////////////////////////////////////////////////////////////
                    }
                }
                //selectedLabel.setText(selectedNode.getUserObject().toString());
            }
        });

    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new TreeExample();
            }
        });
    }
}