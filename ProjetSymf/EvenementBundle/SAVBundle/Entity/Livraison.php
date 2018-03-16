<?php

namespace SAVBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livraison
 *
 * @ORM\Table(name="livraison")
 * @ORM\Entity(repositoryClass="SAVBundle\Repository\LivraisonRepository")
 */
class Livraison
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
     *
     * @ORM\OneToOne(targetEntity="SAVBundle\Entity\Commande")
     * @ORM\JoinColumn(name="commandeRef", referencedColumnName="id")
     */
    private $commandeRef;

    /**
     *
     * @ORM\OneToOne(targetEntity="TestBundle\Entity\User")
     * @ORM\JoinColumn(name="livreurRef", referencedColumnName="id")
     */
    private $livreurRef;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_livraison", type="date")
     */
    private $dateLivraison;

    /**
     * @var $adresse
     * @ORM\OneToOne(targetEntity="SAVBundle\Entity\FullAdress")
     * @ORM\JoinColumn(name="adresse", referencedColumnName="id")
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255)
     */
    private $etat;


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
     * Set commandeRef
     *
     * @param integer $commandeRef
     *
     * @return Livraison
     */
    public function setCommandeRef($commandeRef)
    {
        $this->commandeRef = $commandeRef;

        return $this;
    }

    /**
     * Get commandeRef
     *
     * @return int
     */
    public function getCommandeRef()
    {
        return $this->commandeRef;
    }

    /**
     * Set livreurRef
     *
     * @param integer $livreurRef
     *
     * @return Livraison
     */
    public function setLivreurRef($livreurRef)
    {
        $this->livreurRef = $livreurRef;

        return $this;
    }

    /**
     * Get livreurRef
     *
     * @return int
     */
    public function getLivreurRef()
    {
        return $this->livreurRef;
    }

    /**
     * Set dateLivraison
     *
     * @param \DateTime $dateLivraison
     *
     * @return Livraison
     */
    public function setDateLivraison($dateLivraison)
    {
        $this->dateLivraison = $dateLivraison;

        return $this;
    }

    /**
     * Get dateLivraison
     *
     * @return \DateTime
     */
    public function getDateLivraison()
    {
        return $this->dateLivraison;
    }

    /**
     * Set fullAdress
     *
     * @param string $fullAdress
     *
     * @return Livraison
     */
    public function setFullAdress($fullAdress)
    {
        $this->fullAdress = $fullAdress;

        return $this;
    }

    /**
     * Get fullAdress
     *
     * @return string
     */
    public function getFullAdress()
    {
        return $this->fullAdress;
    }

    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return Livraison
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * Set adresse
     *
     * @param \SAVBundle\Entity\FullAdress $adresse
     *
     * @return Livraison
     */
    public function setAdresse(\SAVBundle\Entity\FullAdress $adresse = null)
    {
        $this->adresse = $adresse;

        return $this;
    }

    /**
     * Get adresse
     *
     * @return \SAVBundle\Entity\FullAdress
     */
    public function getAdresse()
    {
        return $this->adresse;
    }
}
