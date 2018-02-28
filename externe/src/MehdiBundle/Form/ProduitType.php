<?php

namespace MehdiBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProduitType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomProduit')
            ->add('description',TextareaType::class)
            ->add('image', null ,array('required' => false, 'data_class' => null))
            ->add('prix_sans_solde')
            ->add('solde')
            ->add('prix')
            ->add('categorie',EntityType::class, array(
                'class'=>'MehdiBundle:Categorie',
                'choice_label'=>'nomCategorie',
                'multiple'=> false, ))
            ->add('rating',\blackknight467\StarRatingBundle\Form\RatingType::class,
                ['label' => 'Rating'])
            ->add('ajouter',SubmitType::class);

    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'MehdiBundle\Entity\Produit'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'mehdibundle_produit';
    }


}
