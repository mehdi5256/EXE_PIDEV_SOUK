<?php

namespace SAVBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity(repositoryClass="SAVBundle\Repository\CommandeRepository")
 */
class Commande
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
     * @ORM\ManyToOne(targetEntity="MehdiBundle\Entity\Produit")
     * @ORM\JoinColumn(name="produit_id", referencedColumnName="id")
     */
    private $produit;

    /**
     *
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="clientRef", referencedColumnName="id")
     */
    private $clientRef;

    /**
     *
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\User")
     * @ORM\JoinColumn(name="vendeurRef", referencedColumnName="id")
     */
    private $vendeurRef;


    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_commande", type="date")
     */
    private $dateCommande;

    /**
     * @var bool
     *
     * @ORM\Column(name="plusLivraison", type="boolean")
     */
    private $plusLivraison;

    /**
     * @return mixed
     */
    public function getProduit()
    {
        return $this->produit;
    }

    /**
     * @param mixed $produit
     */
    public function setProduit($produit)
    {
        $this->produit = $produit;
    }


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
     * Set clientRef
     *
     * @param integer $clientRef
     *
     * @return Commande
     */
    public function setClientRef($clientRef)
    {
        $this->clientRef = $clientRef;

        return $this;
    }

    /**
     * Get clientRef
     *
     * @return int
     */
    public function getClientRef()
    {
        return $this->clientRef;
    }

    /**
     * Set vendeurRef
     *
     * @param integer $vendeurRef
     *
     * @return Commande
     */
    public function setVendeurRef($vendeurRef)
    {
        $this->vendeurRef = $vendeurRef;

        return $this;
    }

    /**
     * Get vendeurRef
     *
     * @return int
     */
    public function getVendeurRef()
    {
        return $this->vendeurRef;
    }


    /**
     * Set dateCommande
     *
     * @param \DateTime $dateCommande
     *
     * @return Commande
     */
    public function setDateCommande($dateCommande)
    {
        $this->dateCommande = $dateCommande;

        return $this;
    }

    /**
     * Get dateCommande
     *
     * @return \DateTime
     */
    public function getDateCommande()
    {
        return $this->dateCommande;
    }



    /**
     * Get plusLivraison
     *
     * @return bool
     */
    public function getPlusLivraison()
    {
        return $this->plusLivraison;
    }

    /**
     * Set plusLivraison
     *
     * @param boolean $plusLivraison
     *
     * @return Commande
     */
    public function setPlusLivraison($plusLivraison)
    {
        $this->plusLivraison = $plusLivraison;

        return $this;
    }


}
