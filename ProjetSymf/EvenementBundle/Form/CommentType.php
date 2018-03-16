<?php
/**
 * Created by PhpStorm.
 * User: Hp
 * Date: 2/28/2018
 * Time: 2:18 AM
 */

namespace EvenementBundle\Form;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;


class CommentType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('content',TextareaType::class,array(
                'attr' => array(
                    'class'=>'form-control border-color-1 '

                ),
                'label'=>'comment'
            ))
            ->add('valider',SubmitType::class,array(
                'attr' => array(

                    'class'=>'btn btn-xs btn-primary'
                )
            ));
    }
}