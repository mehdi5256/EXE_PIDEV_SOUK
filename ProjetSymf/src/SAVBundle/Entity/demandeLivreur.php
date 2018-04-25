<?php

namespace SAVBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * demandeLivreur
 *
 * @ORM\Table(name="demande_livreur")
 * @ORM\Entity(repositoryClass="SAVBundle\Repository\demandeLivreurRepository")
 */
class demandeLivreur
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
     * @var \DateTime
     *
     * @ORM\Column(name="dateInscription", type="datetime")
     */
    private $dateInscription;

    /**
     * @var string
     *
     * @ORM\Column(name="vehicule", type="string", length=255)
     */
    private $vehicule;

    /**
     * @var array
     *
     * @ORM\Column(name="disponibilite", type="array", length=255)
     */
    private $disponibilite;

    /**
     *
     * @ORM\ManyToOne(targetEntity="TestBundle\Entity\User")
     * @ORM\JoinColumn(name="clientRef", referencedColumnName="id")
     */
    private $clientRef;

    /**
     * @var boolean
     *
     * @ORM\Column(name="isactive", type="boolean", length=255)
     */
    private $isActive;

    /**
     * @return bool
     */
    public function isActive()
    {
        return $this->isActive;
    }

    /**
     * @param bool $isActive
     */
    public function setIsActive($isActive)
    {
        $this->isActive = $isActive;
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
     * Set dateInscription
     *
     * @param \DateTime $dateInscription
     *
     * @return demandeLivreur
     */
    public function setDateInscription($dateInscription)
    {
        $this->dateInscription = $dateInscription;

        return $this;
    }

    /**
     * Get dateInscription
     *
     * @return \DateTime
     */
    public function getDateInscription()
    {
        return $this->dateInscription;
    }

    /**
     * Set vehicule
     *
     * @param string $vehicule
     *
     * @return demandeLivreur
     */
    public function setVehicule($vehicule)
    {
        $this->vehicule = $vehicule;

        return $this;
    }

    /**
     * Get vehicule
     *
     * @return string
     */
    public function getVehicule()
    {
        return $this->vehicule;
    }

    /**
     * Set disponibilite
     *
     * @param string $disponibilite
     *
     * @return demandeLivreur
     */
    public function setDisponibilite($disponibilite)
    {
        $this->disponibilite = $disponibilite;

        return $this;
    }

    /**
     * Get disponibilite
     *
     * @return string
     */
    public function getDisponibilite()
    {
        return $this->disponibilite;
    }

    /**
     * Set clientRef
     *
     * @param \TestBundle\Entity\User $clientRef
     *
     * @return demandeLivreur
     */
    public function setClientRef(\TestBundle\Entity\User $clientRef = null)
    {
        $this->clientRef = $clientRef;

        return $this;
    }

    /**
     * Get clientRef
     *
     * @return \TestBundle\Entity\User
     */
    public function getClientRef()
    {
        return $this->clientRef;
    }
}
