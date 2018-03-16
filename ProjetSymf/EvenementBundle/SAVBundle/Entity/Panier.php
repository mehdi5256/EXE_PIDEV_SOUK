<?php

namespace SAVBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Panier
 *
 * @ORM\Table(name="panier")
 * @ORM\Entity(repositoryClass="SAVBundle\Repository\PanierRepository")
 */
class Panier
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /** ONE-TO-MANY BIDIRECTIONAL, INVERSE SIDE
     * @var integer
     * @ORM\OneToMany(targetEntity="Panier", mappedBy="GestionBoutiquesBundle:ProduitBoutique")
     */
    private $produitRef;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer")
     */
    private $quantite;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;


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
     * Set produitRef
     *
     * @param integer $produitRef
     *
     * @return Panier
     */
    public function setProduitRef($produitRef)
    {
        $this->produitRef = $produitRef;

        return $this;
    }

    /**
     * Get produitRef
     *
     * @return int
     */
    public function getProduitRef()
    {
        return $this->produitRef;
    }

    /**
     * Set quantite
     *
     * @param integer $quantite
     *
     * @return Panier
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;

        return $this;
    }

    /**
     * Get quantite
     *
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Panier
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }
    /**
     * Constructor
     */
    public function __construct()
    {
        $this->produitRef = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * Add produitRef
     *
     * @param \SAVBundle\Entity\Panier $produitRef
     *
     * @return Panier
     */
    public function addProduitRef(\SAVBundle\Entity\Panier $produitRef)
    {
        $this->produitRef[] = $produitRef;

        return $this;
    }

    /**
     * Remove produitRef
     *
     * @param \SAVBundle\Entity\Panier $produitRef
     */
    public function removeProduitRef(\SAVBundle\Entity\Panier $produitRef)
    {
        $this->produitRef->removeElement($produitRef);
    }
}