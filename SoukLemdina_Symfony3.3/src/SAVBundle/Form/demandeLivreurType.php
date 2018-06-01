<?php

namespace SAVBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class demandeLivreurType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('Vehicule',ChoiceType::class, array(
            'choices' => array(

                "Voiture"=>"Voiture",
                "Moto"=>"Moto",
                "Camion"=>"Camion",

            ),
            'placeholder'=> "Veuillez choisir un véhicule",
            'required'=>true
        ))
            ->add('disponibilite',ChoiceType::class, array(
            'choices'=> array(
                'Lundi' =>1,
                'Mardi' =>2,
                'Mercredi' =>3,
                'Jeudi' =>4,
                'Vendredi' =>5,
                'Samedi' =>6,
                'Dimanche' =>7),
                'choices_as_values' => true,'multiple'=>true,'expanded'=>true,'required'=>true
                                                            )
                 );
    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'SAVBundle\Entity\demandeLivreur'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'savbundle_demandelivreur';
    }


}
