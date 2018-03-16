<?php

namespace SAVBundle\Form;

use SAVBundle\Entity\FullAdress;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class LivraisonType extends AbstractType
{

    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('dateLivraison')->add('adresses',EntityType::class, array(
            'class' =>'SAVBundle\Entity\FullAdress',
            'choices' =>$options['adresses'],
            'choice_label' => function (FullAdress $rue){
                return $rue->getRue();
            }));
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
