<?php

namespace SAVBundle\Form;

use Symfony\Component\Form\Extension\Core\Type\DateType;
use SAVBundle\Entity\FullAdress;
use SAVBundle\Entity\Livraison;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;

class LivraisonType extends AbstractType
{

    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('dateLivraison' , DateType::class , array('widget' => 'single_text'))->add('adresse',  ChoiceType::class, array(
            'placeholder'       => 'Veuillez choisir',
            'choices'           => $options['adresses'],
            'choice_label'      => 'rue',
            'choice_value'      =>  'id'

        ));
    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'SAVBundle\Entity\Livraison',
            'adresses' =>array()
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'savbundle_livraison';
    }


}
