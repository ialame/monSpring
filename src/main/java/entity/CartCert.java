package com.pca.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
//import java.util.Date;

@Entity
@Table( name="CartCert" )
public class CartCert {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private LocalDateTime date;
    //private String note;
    //private String note1;
    //private String note2;
    private String codeBarre;


    @ManyToOne
    @JoinColumn(name="carte_id", nullable=true)
    private Carte carte;


    //@ManyToOne( cascade = {CascadeType.ALL} )
    //@JoinColumn(name="cmd_id", nullable=true)
    @Transient
    private Cmd cmd;

    public Cmd getCmd() {
        return cmd;
    }

    public void setCmd(Cmd cmd) {
        this.cmd = cmd;
    }

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Sav", inversedBy="cartcerts")
     * @ORM\JoinColumn(nullable=true)
     */
    // private String sav;
    private boolean displayed = true;

    /**
     * @var ArrayCollection
     *
     * @ORM\OneToMany(
     *      targetEntity="App\Core\Domain\Entity\CartCertCommentaire",
     *      mappedBy="cartCert",
     *      cascade="all"
     * )
     */
    //private String commentaires;
    //private boolean reverse = false;
    //private int edition = 2;
    //private String langue = "FR";
    //private boolean annotation = false;
    //private boolean bug = false;
    private boolean actif = true;
    private boolean manuelle = false;
    private boolean descellee = false;
    //private boolean csn = false;
    //private String type;
    //private boolean photo = false;
    private boolean deleted = false;
    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", nullable=true)
     * @ORM\JoinColumn(nullable=true)
     */
    //private String image;
    //private String comm1;
    //private String comm2;

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Client")
     * @ORM\JoinColumn(nullable=true)
     */
    // private String noteur;

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Client")
     * @ORM\JoinColumn(nullable=true)
     */
    // private String noteur1;

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Client")
     * @ORM\JoinColumn(nullable=true)
     */
    //private String noteur2;
    //private LocalDate date_note;
    //private LocalDate date_note1;
    //private LocalDate date_note2;
    private boolean echantillon = false;
    //private String langueMention = "FR";
    //private String langueMentionClient;

    /**
     * @ORM\OneToMany(targetEntity="App\Core\Domain\Entity\CartSearch", mappedBy="cartcert")
     * @ORM\JoinColumn(nullable=true)
     */
    // private String search;
    //private int vdCC;
    //private String note3;

    /**
     * @ORM\OneToMany(targetEntity="App\Core\Domain\Entity\SousNote", mappedBy="cartcert", cascade="all")
     */
    //private String sousNotes;
    private boolean quatreNotes = false;

    /**
     * @ORM\OneToMany(targetEntity="App\Core\Domain\Entity\Modification", mappedBy="cc", cascade={"remove", "persist"})
     */
    //private String modifications;
    //private boolean foil = false;

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\IncertifiableRaison")
     * @ORM\JoinColumn(nullable=true)
     */
    //private String incertifiableRaison;

    /*@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinTable( name = "DetailCmd",
            joinColumns = @JoinColumn( name = "cartCert_id" ),
            inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<DetailCmd> details= new ArrayList<>();*/


    public CartCert(){}
    public CartCert(LocalDateTime date, String codeBarre, Carte carte,Cmd cmd) {
        this.date = date;
        this.codeBarre = codeBarre;
        this.carte = carte;
        this.cmd=cmd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isManuelle() {
        return manuelle;
    }

    public void setManuelle(boolean manuelle) {
        this.manuelle = manuelle;
    }

    public boolean isDescellee() {
        return descellee;
    }

    public void setDescellee(boolean descellee) {
        this.descellee = descellee;
    }



    public boolean isEchantillon() {
        return echantillon;
    }

    public void setEchantillon(boolean echantillon) {
        this.echantillon = echantillon;
    }


    public boolean isQuatreNotes() {
        return quatreNotes;
    }

    public void setQuatreNotes(boolean quatreNotes) {
        this.quatreNotes = quatreNotes;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
