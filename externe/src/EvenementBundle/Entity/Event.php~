<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints as Assert ;

/**
 * Event
 *
 * @ORM\Table(name="Event")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\EventRepository")
 */
class Event
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="string", length=255)
     */
    private $description;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateDeb", type="date")
     */
    private $dateDeb;

    /**
     * @ORM\Column(name="date_fin",type="datetime")
     * @Assert\Expression(
     *    "this.getDateFin() > this.getDateDeb()",
     *    message="La date fin de l'evenement  doit etre supérieure à la date début"
     * )
     */
    private $dateFin;

    /**
     * @var string
     *
     * @ORM\Column(name="Lieu", type="string", length=255)
     */
    private $lieu;

    /**
     * @var \String
     *
     * @ORM\Column(name="nom_organisateur", type="string")
     */
    private $nomOrganisateur;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="HeureDeb", type="time")
     */
    private $heureDeb;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="HeureFin", type="time")
     */
    private $heureFin;

    /**
     * @ORM\Column(type="string")
     *
     * @Assert\NotBlank(message="Please, upload the product brochure as a PDF file.")
     * @Assert\Image()
     */
    private $image;
    /**
     * @var integer
     *
     * @ORM\Column(name="participate", type="integer",options={"default" = 0})
     */
    private $participate='0';
    /**
     * @ORM\Column(type="integer" , nullable=true)
     */
    private $nbrparticipants = '0' ;

    /**
     * @var integer
     *
     * @ORM\Column(name="idu", type="integer",options={"default" = 0})
     */
    private $idu;
    /**
     * @var string
     *
     * @ORM\Column(name="nomuser", type="string", length=255)
     */

    private $nomuser;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Event
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Event
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set dateDeb
     *
     * @param \DateTime $dateDeb
     *
     * @return Event
     */
    public function setDateDeb($dateDeb)
    {
        $this->dateDeb = $dateDeb;

        return $this;
    }

    /**
     * Get dateDeb
     *
     * @return \DateTime
     */
    public function getDateDeb()
    {
        return $this->dateDeb;
    }

    /**
     * Set dateFin
     *
     * @param \DateTime $dateFin
     *
     * @return Event
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * Get dateFin
     *
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * Set lieu
     *
     * @param string $lieu
     *
     * @return Event
     */
    public function setLieu($lieu)
    {
        $this->lieu = $lieu;

        return $this;
    }

    /**
     * Get lieu
     *
     * @return string
     */
    public function getLieu()
    {
        return $this->lieu;
    }

    /**
     * Set nomOrganisateur
     *
     * @param string $nomOrganisateur
     *
     * @return Event
     */
    public function setNomOrganisateur($nomOrganisateur)
    {
        $this->nomOrganisateur = $nomOrganisateur;

        return $this;
    }

    /**
     * Get nomOrganisateur
     *
     * @return string
     */
    public function getNomOrganisateur()
    {
        return $this->nomOrganisateur;
    }

    /**
     * Set heureDeb
     *
     * @param \DateTime $heureDeb
     *
     * @return Event
     */
    public function setHeureDeb($heureDeb)
    {
        $this->heureDeb = $heureDeb;

        return $this;
    }

    /**
     * Get heureDeb
     *
     * @return \DateTime
     */
    public function getHeureDeb()
    {
        return $this->heureDeb;
    }

    /**
     * Set heureFin
     *
     * @param \DateTime $heureFin
     *
     * @return Event
     */
    public function setHeureFin($heureFin)
    {
        $this->heureFin = $heureFin;

        return $this;
    }

    /**
     * Get heureFin
     *
     * @return \DateTime
     */
    public function getHeureFin()
    {
        return $this->heureFin;
    }
    /**
     * Set image
     *
     * @param string $image
     *
     * @return Event
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }
    /**
     * Constructor
     */
    public function __construct()
    {
        $this->participants = new \Doctrine\Common\Collections\ArrayCollection();
        $this->dateNow = new \DateTime('now');


    }



    /**
     * @return mixed
     */
    public function getNbrparticipants()
    {
        return $this->nbrparticipants;
    }

    /**
     * @param mixed $nbrparticipants
     */
    public function setNbrparticipants($nbrparticipants)
    {
        $this->nbrparticipants = $nbrparticipants;
    }

    /**
     * Set idu
     *
     * @param integer $idu
     *
     * @return Event
     */
    public function setIdu($idu)
    {
        $this->idu = $idu;

        return $this;
    }

    /**
     * Get idu
     *
     * @return integer
     */
    public function getIdu()
    {
        return $this->idu;
    }

    /**
     * Set nomuser
     *
     * @param string $nomuser
     *
     * @return Event
     */
    public function setNomuser($nomuser)
    {
        $this->nomuser = $nomuser;

        return $this;
    }

    /**
     * Get nomuser
     *
     * @return string
     */
    public function getNomuser()
    {
        return $this->nomuser;
    }




    /**
     * Set participate
     *
     * @param integer $participate
     *
     * @return Event
     */
    public function setParticipate($participate)
    {
        $this->participate = $participate;

        return $this;
    }

    /**
     * Get participate
     *
     * @return integer
     */
    public function getParticipate()
    {
        return $this->participate;
    }
}
