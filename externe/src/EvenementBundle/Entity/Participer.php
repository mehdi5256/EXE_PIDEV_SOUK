<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participer
 *
 * @ORM\Table(name="participer")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\ParticiperRepository")
 */
class Participer
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
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="idu", type="integer")
     */
    private $idu;

    /**
     * @var int
     *
     * @ORM\Column(name="participate", type="integer")
     */
    private $participate=0;

    /**
     * @var int
     *
     * @ORM\Column(name="ide", type="integer")
     */
    private $ide;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datedeb", type="date")
     */
    private $datedeb;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datefin", type="date")
     */
    private $datefin;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="heuredeb", type="time")
     */
    private $heuredeb;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="heurefin", type="time")
     */
    private $heurefin;

    /**
     * @var string
     *
     * @ORM\Column(name="nomorganisateur", type="string", length=255)
     */
    private $nomorganisateur;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu", type="string", length=255)
     */
    private $lieu;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     */
    private $image;


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
     * @return Participer
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
     * @return Participer
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
     * Set idu
     *
     * @param integer $idu
     *
     * @return Participer
     */
    public function setIdu($idu)
    {
        $this->idu = $idu;

        return $this;
    }

    /**
     * Get idu
     *
     * @return int
     */
    public function getIdu()
    {
        return $this->idu;
    }

    /**
     * Set participate
     *
     * @param integer $participate
     *
     * @return Participer
     */
    public function setParticipate($participate)
    {
        $this->participate = $participate;

        return $this;
    }

    /**
     * Get participate
     *
     * @return int
     */
    public function getParticipate()
    {
        return $this->participate;
    }

    /**
     * Set ide
     *
     * @param integer $ide
     *
     * @return Participer
     */
    public function setIde($ide)
    {
        $this->ide = $ide;

        return $this;
    }

    /**
     * Get ide
     *
     * @return int
     */
    public function getIde()
    {
        return $this->ide;
    }

    /**
     * Set datedeb
     *
     * @param \DateTime $datedeb
     *
     * @return Participer
     */
    public function setDatedeb($datedeb)
    {
        $this->datedeb = $datedeb;

        return $this;
    }

    /**
     * Get datedeb
     *
     * @return \DateTime
     */
    public function getDatedeb()
    {
        return $this->datedeb;
    }

    /**
     * Set datefin
     *
     * @param \DateTime $datefin
     *
     * @return Participer
     */
    public function setDatefin($datefin)
    {
        $this->datefin = $datefin;

        return $this;
    }

    /**
     * Get datefin
     *
     * @return \DateTime
     */
    public function getDatefin()
    {
        return $this->datefin;
    }

    /**
     * Set heuredeb
     *
     * @param \DateTime $heuredeb
     *
     * @return Participer
     */
    public function setHeuredeb($heuredeb)
    {
        $this->heuredeb = $heuredeb;

        return $this;
    }

    /**
     * Get heuredeb
     *
     * @return \DateTime
     */
    public function getHeuredeb()
    {
        return $this->heuredeb;
    }

    /**
     * Set heurefin
     *
     * @param \DateTime $heurefin
     *
     * @return Participer
     */
    public function setHeurefin($heurefin)
    {
        $this->heurefin = $heurefin;

        return $this;
    }

    /**
     * Get heurefin
     *
     * @return \DateTime
     */
    public function getHeurefin()
    {
        return $this->heurefin;
    }

    /**
     * Set nomorganisateur
     *
     * @param string $nomorganisateur
     *
     * @return Participer
     */
    public function setNomorganisateur($nomorganisateur)
    {
        $this->nomorganisateur = $nomorganisateur;

        return $this;
    }

    /**
     * Get nomorganisateur
     *
     * @return string
     */
    public function getNomorganisateur()
    {
        return $this->nomorganisateur;
    }

    /**
     * Set lieu
     *
     * @param string $lieu
     *
     * @return Participer
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
     * Set image
     *
     * @param string $image
     *
     * @return Participer
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
}
