<?php

namespace MehdiBundle\Controller;

use MehdiBundle\Entity\Categorie;
use MehdiBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class tennisController extends Controller
{
    public function accueilAction(Request $a)
    {
        $em = $this->getDoctrine()->getManager();
        $modeles = $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();
        $paginator = $this->get('knp_paginator');
        $modeles = $paginator->paginate($modeles, $a->query->get('page', 1)/*page number*/, 3/*limit per page*/
        );
        return $this->render('MehdiBundle:tennis:accueil.html.twig', array('modeles' => $modeles));
    }

    // ...


    public function ListCategorieAction($categorie)
    {
        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository("MehdiBundle:Produit")->byCategorie($categorie);

        return $this->render('MehdiBundle:tennis:categorie.html.twig', array('produit' => $produit));
        // ...

    }

    public function produitAction(Request $a)
    {
        $em = $this->getDoctrine()->getManager();
        $modeles = $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();
        $paginator = $this->get('knp_paginator');
        $modeles = $paginator->paginate($modeles, $a->query->get('page', 1)/*page number*/, 3/*limit per page*/
        );
        return $this->render('MehdiBundle:tennis:accueil.html.twig', array('modeles' => $modeles));
    }

    public function RechercheAction(Request $c)
    {
        $em = $this->getDoctrine()->getManager();
        $modeles = $em->getRepository("MehdiBundle:Produit")->findAll();
        if ($c->isMethod('POST')) {
            $libele = $c->get('nomProduit');
            $modeles = $em->getRepository("MehdiBundle:Produit")->findBy(array("nomProduit" => $libele));
        } else {
            $modeles = $em->getRepository("MehdiBundle:Produit")->findAll();
        }
        return $this->render('MehdiBundle:Produit:recherche.html.twig',
            array('modeles' => $modeles));
    }

    public function redirectionAction(Request $c)
    {
        $em = $this->getDoctrine()->getManager();
        $modeles = $em->getRepository("MehdiBundle:Produit")->findAll();
        if ($c->isMethod('POST')) {
            $libele = $c->get('nomProduit');
            $modeles = $em->getRepository("MehdiBundle:Produit")->findBy(array("nomProduit" => $libele));
        } else {
            $modeles = $em->getRepository("MehdiBundle:Produit")->findAll();
        }

        return $this->render('MehdiBundle:tennis:rechercheredirection.html.twig',
            array('modeles' => $modeles));
    }

    public function RatingCategorieAction()
    {
        /*$em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository("MehdiBundle:Produit")->findProduitRating(); */
        return $this->render('MehdiBundle:tennis:rating.html.twig', array());

    }

    public function xAction()
    {
        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository("MehdiBundle:Produit")->findProduitRating();
        return $this->render('MehdiBundle:tennis:x.html.twig', array( 'produit'=> $produit));
        // ...

    }
    public function suppAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository( Produit::class)->find($id);
        $em->remove($product);
        $em->flush();
        return $this->redirectToRoute('listp');
    }

    public function soldeAction()
    {
        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository("MehdiBundle:Produit")->findProduitSolde();
        return $this->render('MehdiBundle:tennis:solde.html.twig', array( 'solde'=> $produit));
        // ...

    }
}
