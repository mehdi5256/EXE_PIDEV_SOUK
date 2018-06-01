<?php

namespace MehdiBundle\Controller;

use MehdiBundle\Entity\Categorie;
use MehdiBundle\Entity\Produit;
use MehdiBundle\Form\ProduitType;
use MehdiBundle\Form\RatingType;
use MehdiBundle\Form\RechercheType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ProduitController extends Controller
{
    public function AjoutProduitAction(Request $a)
    {
        $modele = new Produit();

        $form = $this->createForm(ProduitType::class, $modele);
        $form->handleRequest($a);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file = $form['image']->getData();
            $file->move('uploads/images', $file->getClientOriginalName());
            $modele->setImage("uploads/images/" . $file->getClientOriginalName());
            $em->persist($modele);
            $em->flush();
            return $this->redirectToRoute('accueil');
        }
        return $this->render('MehdiBundle:Produit:ajout_produit.html.twig', array('form' => $form->createView()
            // ...
        ));
    }

    public function readmoreAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $produit = $em->getRepository(Produit::class)->find($id);

        return $this->render('MehdiBundle:Produit:readmore.html.twig', array('produit' => $produit));
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

    public function AfficheAction(Request $a)
    {
        /* Controleur de affichage tableau de l'admin */
        $em = $this->getDoctrine()->getManager();
        $modeles = $em->getRepository("MehdiBundle:Produit")->findAll();
        $paginator = $this->get('knp_paginator');
        $modeles = $paginator->paginate($modeles, $a->query->get('page', 1)/*page number*/, 5/*limit per page*/
        );
        return $this->render('MehdiBundle:Produit:list.html.twig', array("modeles" => $modeles));

    }

    public function DeleteAction($id)
    {
        /* Supprimer un produit dans template admin*/
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Produit::class)->find($id);
        $em->remove($product);
        $em->flush();
        return $this->redirectToRoute('listp');
    }

    public function UpdatepAction(Request $request, Produit $produit)
    {
        $photo=$produit->getImage();
/*        $deleteForm = $this->createDeleteForm($produit);*/
        $editForm = $this->createForm('MehdiBundle\Form\ProduitType', $produit);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $file = $produit->getImage();
            if ($file !=""){
                $fileName = md5(uniqid('', true)).'.'.$file->guessExtension();
                $path = "C:/wamp64/www/externe/web" ;
                $file->move(
                    $path,
                    $fileName
                );
                $photo=$fileName;
            }
            $produit->setImage($photo);
            $em = $this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();

            return $this->redirectToRoute('listp', array('id' => $produit->getId()));
        }

        return $this->render('MehdiBundle:Produit:updateproduit.html.twig', array(
            'produit' => $produit,
            'form' => $editForm->createView(),
/*            'delete_form' => $deleteForm->createView(),*/
        ));
    }


    /*public function rechercheAjaxAction(Request $request)
    {
        if ($request->isXmlHttpRequest()) {
            $mot_cle=$request->get('requete');
            $em = $this->getDoctrine()->getManager();
            $produits = $em->getRepository('MehdiBundle:Produit')->findProduitDQL($mot_cle);

            $ser= new Serializer(array(new ObjectNormalizer()));
            $data=$ser->normalize($produits);
            return new JsonResponse($data);
        }
    }*/
    /* public function CompterAction($categorie)
     {
         $em = $this->getDoctrine()->getManager();
         $produit = $em->getRepository("MehdiBundle:Produit")->compter($categorie);

         return $this->render('MehdiBundle:tennis:accueil.html.twig', array('produit' => $produit));
    }
         // ...*/

    public function rechercheAjaxAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        if ($request->isXmlHttpRequest())
        {
            $search = $request->get('search');
            dump($search);
            $modeles = new Produit();
            $repo = $em->getRepository('MehdiBundle:Produit');
            $modeles = $repo->findProduitDQL($search);

            return $this->render('MehdiBundle:Produit:ajax.html.twig', array('modeles' => $modeles));
        }
    }

    /*public function ratingAjaxAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        if ($request->isXmlHttpRequest()) {
            $id_prod = $request->get('id_prod');
            $id_user = $request->get('id_user');
            $rate = $request->get('rate');

            //$test_rating = $em->getRepository('MehdiBundle\Entity\Rating')->

            $rating = new Rating();
            $user = $em->getRepository('AppBundle\Entity\User')->find($id_user);
            $produit = $em->getRepository('MehdiBundle\Entity\Produit')->find($id_prod);

            $rating->setRating($rate);
            $rating->setProduits($produit);
            $rating->setUsers($user);

            $tab = [];
            $ser = new Serializer(array(new ObjectNormalizer()));
            $data = $ser->normalize($tab);
            return new JsonResponse(array('data' => $data));

        }

    }*/
    public function ajaxAdminAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        if ($request->isXmlHttpRequest())
        {
            $search = $request->get('search');
            dump($search);
            $modeles = new Produit();
            $repo = $em->getRepository('MehdiBundle:Produit');
            $modeles = $repo->findProduitDQL($search);

            return $this->render('MehdiBundle:Produit:ajaxadmin.html.twig', array('modeles' => $modeles));
        }
    }

}
