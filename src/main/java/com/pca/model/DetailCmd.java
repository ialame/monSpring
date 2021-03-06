package com.pca.model;

import javax.persistence.*;
import java.time.LocalDateTime;
//import java.util.Date;

@Entity
@Table( name="DetailCmd" )
public class DetailCmd {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;


    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="cmd_id", nullable=true)
    private Cmd cmd;

    /**
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Sav", inversedBy="details")
     * @ORM\JoinColumn(nullable=true)
     */
    //private $sav;

    @ManyToOne( cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinColumn(name="cartCert_id", nullable=true)
    private CartCert cartCert;


    /**
     * @var Client|null
     *
     * @ORM\ManyToOne(targetEntity="App\Core\Domain\Entity\Client", inversedBy="details")
     * @ORM\JoinColumn(nullable=true)
     */
    //private $user;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private LocalDateTime date;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateMaj", type="datetime", nullable=true)
     */
    //private $dateMaj;

    /**
     * @var int
     *
     * @ORM\Column(name="status", type="integer")
     */
    private int status = 1;

    /**
     * @var int
     *
     * @ORM\Column(name="nbCartes", type="integer", nullable=true)
     */
    private Integer nbCartes;

    /**
     * @var bool
     *
     * @ORM\Column(type="boolean", options={"default": 0})
     */
    private  boolean needsDateUpdate = false;
    public DetailCmd(){}

    public DetailCmd(Cmd cmd,CartCert cartCert, LocalDateTime date, int nbCartes) {//CartCert cartCert,
        this.cmd = cmd;
        this.cartCert = cartCert;
        this.date = date;
        this.nbCartes = nbCartes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cmd getCmd() {
        return cmd;
    }

    public void setCmd(Cmd cmd) {
        this.cmd = cmd;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getNbCartes() {
        return nbCartes;
    }

    public void setNbCartes(Integer nbCartes) {
        this.nbCartes = nbCartes;
    }

    public boolean isNeedsDateUpdate() {
        return needsDateUpdate;
    }

    public void setNeedsDateUpdate(boolean needsDateUpdate) {
        this.needsDateUpdate = needsDateUpdate;
    }
}
