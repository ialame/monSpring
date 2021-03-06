package com.ia.ebay9.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="Cmd" )
public class Cmd
{
    /*public final int STATUS_A_RECEP = 1; // Payée
    public final int STATUS_A_RECUP = 10; // Réceptionnée
    public final int STATUS_A_NOTER = 2; // Récupèrée
    public final int STATUS_A_NOTER2 = 20; // Note 1
    public final int STATUS_A_NOTER3 = 21; // Note 2
    public final int STATUS_A_CERTIFIER = 3; // Notée
    public final int STATUS_A_PREPARER = 4; // Certifiée
    public final int STATUS_A_DISTRIBUER = 41; // Préparée
    public final int STATUS_A_ENVOYER = 42; // Préparée
    public final int STATUS_ENVOYE = 5; // Envoyée
    public final int STATUS_A_VOIR = 6;
    public final int STATUS_A_DESCELLER = 7;
    public final int STATUS_DESCELLER = 71;
    public final int STATUS_RECU_CLIENT = 8;*/

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String reference;
    private String numCommande;
    private String numCommandeClient;

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Client", inversedBy="cmds" ,cascade={"remove", "persist"})
     * @ORM\JoinColumn(nullable=true)
     */
    //private String client;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private LocalDateTime date;

    /**
     * @ORM\OneToMany(targetEntity="App\Core\Domain\Entity\CartCert", mappedBy="cmd", cascade={"persist"})
     */
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinTable( name = "CartCert",
            joinColumns = @JoinColumn( name = "cmd_id" ),
            inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<CartCert> cartcerts= new ArrayList<>();
    //private String adresseLivraison;
    //private String adresseFacturation;
    private boolean valider;
    private boolean noter;
    private boolean certifier;
    private boolean prestashop = false;
    private boolean manuelle = false;
    //private String facture;
    //private String poste;
    //private String note;
    private String delai;
    //private String suivi;
    //private String langue;
    //private String noteMinimale;
    //private String erreurs;
    //private boolean gratuite = false;
    private boolean mere = false;
    //private String commentaires;
    //private String dates;
    private boolean revue = false;
    private boolean annulee = false;
    private boolean retard = true;
    //private String nbDescellements;
    private boolean descellee = false;
    //private boolean noteMinimaleCSN;
    //private int num;
    //private String modifications;
    private int status;

    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinTable( name = "DetailCmd", joinColumns = @JoinColumn(name = "cmd_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<DetailCmd> details= new ArrayList<>();

    //private String details;
    //private String otherProducts;
    private boolean notationUnique = false;
    //private String partenaire;
    //private String commandeMere;
    //private String commandesFilles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNumCommande() {
        return numCommande;
    }

    public void setNumCommande(String numCommande) {
        this.numCommande = numCommande;
    }

    public String getNumCommandeClient() {
        return numCommandeClient;
    }

    public void setNumCommandeClient(String numCommandeClient) {
        this.numCommandeClient = numCommandeClient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<CartCert> getCartcerts() {
        return cartcerts;
    }

    public void setCartcerts(List<CartCert> cartcerts) {
        this.cartcerts = cartcerts;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public boolean isNoter() {
        return noter;
    }

    public void setNoter(boolean noter) {
        this.noter = noter;
    }

    public boolean isCertifier() {
        return certifier;
    }

    public void setCertifier(boolean certifier) {
        this.certifier = certifier;
    }

    public boolean isPrestashop() {
        return prestashop;
    }

    public void setPrestashop(boolean prestashop) {
        this.prestashop = prestashop;
    }

    public boolean isManuelle() {
        return manuelle;
    }

    public void setManuelle(boolean manuelle) {
        this.manuelle = manuelle;
    }

    public String getDelai() {
        return delai;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    public boolean isMere() {
        return mere;
    }

    public void setMere(boolean mere) {
        this.mere = mere;
    }

    public boolean isRevue() {
        return revue;
    }

    public void setRevue(boolean revue) {
        this.revue = revue;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }

    public boolean isRetard() {
        return retard;
    }

    public void setRetard(boolean retard) {
        this.retard = retard;
    }

    public boolean isDescellee() {
        return descellee;
    }

    public void setDescellee(boolean descellee) {
        this.descellee = descellee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isNotationUnique() {
        return notationUnique;
    }

    public void setNotationUnique(boolean notationUnique) {
        this.notationUnique = notationUnique;
    }

    public List<DetailCmd> getDetails() {
        return details;
    }

    public void setDetails(List<DetailCmd> details) {
        this.details = details;
    }
}

