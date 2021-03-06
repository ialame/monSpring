package com.pca.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table( name = "carte" )
@Inheritance( strategy = InheritanceType.JOINED )
public class Carte
{
    String discriminator="pok";
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    //@Column( columnDefinition="uuid DEFAULT uuid_generate_v4()")

    //@Id
    //@GeneratedValue(generator = "uuid2",strategy=GenerationType.IDENTITY)
    //@GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Column(columnDefinition = "BINARY(16)")
    protected Integer id ;
    //private UUID id;

    /**
     * @var ArrayCollection
     *
     * @ORM\OneToMany(targetEntity="App\Core\Domain\Entity\CartCert", mappedBy="carte",cascade={"persist"})
     */
    //protected $cartcerts;

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "carte")
    private List<CartCert> cartcerts= new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "carte",orphanRemoval = true)
    private List<Image_Carte> imageCartes= new ArrayList<>();
/*
    @OneToMany( cascade = CascadeType.ALL)
    private List<MoyennePrix> moyennePrixs= new ArrayList<>();

 */


    /*
    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "image_carte", joinColumns = @JoinColumn(name = "carte_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Image_Carte> imageCartes= new ArrayList<>();

     */

    //protected String cartcerts;

    //protected String cartesvendues;

    //protected String images;


    /**
     * Constructor.
     */
    public Carte()
    {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CartCert> getCartcerts() {
        return cartcerts;
    }

    public void setCartcerts(List<CartCert> cartcerts) {
        this.cartcerts = cartcerts;
    }

    public List<Image_Carte> getImageCartes() {
        return imageCartes;
    }

    public void setImageCartes(List<Image_Carte> imageCartes) {
        this.imageCartes = imageCartes;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }


}
