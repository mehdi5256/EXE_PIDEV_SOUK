<?php

namespace MehdiBundle\Controller;

use MehdiBundle\Entity\Categorie;
use MehdiBundle\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class CategorieController extends Controller
{
    public function AjoutCategorieAction(Request $a)
    {
        $modele = new Categorie();

            $form = $this->createForm(CategorieType::class, $modele);
        $form->handleRequest($a);
        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($modele);
            $em->flush();
            return $this->redirectToRoute('_list_categorie');
        }
        return $this->render('MehdiBundle:Categorie:ajout_categorie.html.twig', array('form' => $form->createView()
            // ...
        ));
    }


    public function UpdateCategorieAction(Request $b,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository(Categorie::class)->find($id);
        $form = $this->createForm(CategorieType::class, $modele);
        $form->handleRequest($b);
        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($modele);
            $em->flush();
            return $this->redirectToRoute('_list_categorie');
        }
        return $this->render('MehdiBundle:Categorie:update_categorie.html.twig', array('form' => $form->createView()
            // ...
        ));
    }

    public function ListCategorieAction()
    {
        $em = $this->getDoctrine()->getManager();
        $categorie = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findAll();
        return $this->render('MehdiBundle:Categorie:list_categorie.html.twig', array('categories' => $categorie));
            // ...

    }
    public function DeleteAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository( Categorie::class)->find($id);
        $em->remove($product);
        $em->flush();
        return $this->redirectToRoute('_list_categorie');
    }

    public function exempleAction()
    {
        $em = $this->getDoctrine()->getManager();
        $categorie = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findAll();
        return $this->render('MehdiBundle:Categorie:categorie.html.twig', array('categorie' => $categorie));
        // ...

    }

}
