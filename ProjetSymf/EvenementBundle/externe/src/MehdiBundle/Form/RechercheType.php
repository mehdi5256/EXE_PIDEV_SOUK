<?php
/**
 * Created by PhpStorm.
 * User: Mehdi
 * Date: 18/02/2018
 * Time: 00:44
 */

namespace MehdiBundle\Form;


use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;

class RechercheType extends  AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('recherhce');
    }
    public function getName()
    {
        return 'mehdibundle_produit_recherche';

    }

}