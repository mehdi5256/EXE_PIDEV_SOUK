<?php

namespace GestionBoutiquesBundle\Controller;

use GestionBoutiquesBundle\Entity\Boutique;
use GestionBoutiquesBundle\Entity\Categorie;
use GestionBoutiquesBundle\Entity\Gerant;
use GestionBoutiquesBundle\Entity\ProduitBoutique;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\File\UploadedFile;

class GestionController extends Controller
{
    /*public function demandeBoutiqueAction(Request $request)
    {
        $usr = $this->get('security.token_storage')->getToken()->getUser();
        $usr = $this->getUser();
        $valide = 0;

        $em = $this->getDoctrine()->getManager();
        $gerant = $em->getRepository('GestionBoutiquesBundle:Gerant')->findOneBy(array('user' => $usr));

        if ($gerant) {
            if ($gerant->getEtat() == false) {
                //Demande déja envoyée
                $valide = 1;
            }

            if ($gerant->getEtat() == true) {
                //Déja gérant d'une boutique
                $valide = 2;
            }
        }

        if ($request->isMethod('POST')) {
            $dmdgerant = new Gerant();
            $dmdgerant->setEtat(false);
            $dmdgerant->setUser($usr);
            $date = "1-1-1990";
            $dmdgerant->setDateDebut(new \DateTime($date));
            $valide = 1;

            $em->persist($dmdgerant);
            $em->flush();
            return $this->render('GestionBoutiquesBundle:Gestion:demande_boutique.html.twig', array('usr'=>$usr,'valide'=>$valide));
        }

        if ($gerant==null) {
            return $this->render('GestionBoutiquesBundle:Gestion:demande_boutique.html.twig', array('usr'=>$usr,'valide'=>$valide));
        }

        return $this->render('GestionBoutiquesBundle:Gestion:demande_boutique.html.twig', array('usr' => $usr, 'valide' => $valide));
    }*/

    public function AfficherBoutiquesAction()
    {

        return $this->render('GestionBoutiquesBundle:Gestion:afficher_boutiques.html.twig', array());
    }

    public function SupprimerBoutiquesAction()
    {
        return $this->render('GestionBoutiquesBundle:Gestion:supprimer_boutiques.html.twig', array(// ...
        ));
    }


    //Envoi de demande ou attente traitement ou espace boutique
    public function espaceBoutiqueAction(Request $request)
    {

        $user = $this->get('security.token_storage')->getToken()->getUser();
        $user = $this->getUser();
        $em = $this->getDoctrine()->getManager();
        $gerant = $em->getRepository('GestionBoutiquesBundle:Gerant')->findOneBy(array('user' => $user));

        if (!$gerant) {
            return $this->render('@GestionBoutiques/Gestion/demande_boutique.html.twig', array('user_id' => $user->getId()));
        }

        $msg = '';
        if ($gerant->getEtat() == false) {
            $msg = 'Votre demande n\'a pas encore été traitée';
            return $this->render('@GestionBoutiques/Gestion/espace_boutique.html.twig', array('msg' => $msg));
        } elseif ($gerant->getEtat() == true) {
            //$boutique = new Boutique();
            //$boutique=$em->getRepository('GestionBoutiquesBundle:Boutique')->findOneBy('');
            $produits = new ProduitBoutique();
            $produits = $em->getRepository('GestionBoutiquesBundle:ProduitBoutique')->findBy(array('boutique' => $gerant->getBoutique()));

            $categories = new Categorie();
            $categories = $em->getRepository('GestionBoutiquesBundle:Categorie')->findAll();

            $boutique = new Boutique();
            $boutique = $gerant->getBoutique();
            $id_boutique = $boutique->getId();
            return $this->render('@GestionBoutiques/Gestion/espace_boutique.html.twig', array('msg' => $msg, 'produits' => $produits,
                'categories' => $categories, 'id_boutique' => $id_boutique,
                'boutique' => $boutique));
        }
        return $this->render('@GestionBoutiques/Gestion/espace_boutique.html.twig', array('msg' => $msg));
    }

    //ajout gerant avec etat = 0
    public function envoiDemandeAction(Request $request)
    {
        if ($request->isXmlHttpRequest()) {
            $id = $request->get('user_id');
            $em = $this->getDoctrine()->getManager();
            $user = $em->getRepository('TestBundle:User')->find($id);
            $gerant = new Gerant();
            $gerant->setUser($user);
            $gerant->setEtat(0);
            $now = new \DateTime();
            //echo $now->format('Y-m-d');
            $gerant->setDateDebut($now);
            $em->persist($gerant);
            $em->flush();

            //$user=$em->getRepository('TestBundle:User')->find(4);
            $tab = [];
            $ser = new Serializer(array(new ObjectNormalizer()));
            $data = $ser->normalize($tab);
            dump($data);
            return new JsonResponse(array('data' => $data));
        }
    }

    public function ajoutProduitAction(Request $request)
    {
        if ($request->isXmlHttpRequest()) {
            $em = $this->getDoctrine()->getManager();
            //data: {'nom_p':nom_p,'desc_p':desc_p,'prix_p':prix_p,'id_categorie':id_categorie,'id_boutique':id_boutique},
            $nom_p = $request->get('nom_p');
            $desc_p = $request->get('desc_p');
            $prix_p = $request->get('prix_p');
            $id_categorie = $request->get('id_categorie');
            $id_boutique = $request->get('id_boutique');

            $produit = new ProduitBoutique();
            $produit->setNomProduit($nom_p);
            $produit->setDescription($desc_p);
            $produit->setPrix($prix_p);
            $now = new \DateTime();
            $produit->setDateDepot($now);

            $boutique = $em->getRepository('GestionBoutiquesBundle:Boutique')->find($id_boutique);
            $categorie = $em->getRepository('GestionBoutiquesBundle:Categorie')->find($id_categorie);

            $produit->setCategorie($categorie);
            $produit->setBoutique($boutique);

            $em->persist($produit);
            $em->flush();

            $tab = [];
            $ser = new Serializer(array(new ObjectNormalizer()));
            $data = $ser->normalize($tab);
            dump($data);
            return new JsonResponse(array('data' => $data));
        }
    }

    public function modifierBoutiqueAction(Request $request)
    {
        if ($request->isXmlHttpRequest()) {
            /*'nom_b': nom_b,
                        'desc_b': desc_b,
                        'lieu_b': lieu_b,
                        'id_boutique': id_boutique*/
            $em=$this->getDoctrine()->getManager();
            $id_boutique=$request->get('id_boutique');
            $boutique = new Boutique();
            $boutique=$em->getRepository('GestionBoutiquesBundle:Boutique')->find($id_boutique);
            $nom_b=$request->get('nom_b');
            $desc_b=$request->get('desc_b');
            $lieu_b=$request->get('lieu_b');
            $boutique->setNomBoutique($nom_b);
            $boutique->setDescription($desc_b);
            $boutique->setLieu($lieu_b);

            $em->persist($boutique);
            $em->flush();

            $tab = [];
            $ser = new Serializer(array(new ObjectNormalizer()));
            $data = $ser->normalize($tab);
            dump($data);
            return new JsonResponse(array('data' => $data));
        }
    }

    public function ajoutimgAction(Request $request)
    {
        $produit = new ProduitBoutique();

        if ($request->isMethod('POST')) {
            $em=$this->getDoctrine()->getManager();
            $file = $request->get('image')->getData();
            $file->move('uploads/images', $file->getClientOriginalName());
            //$modele->setImage("uploads/images/" . $file->getClientOriginalName());
            $produit->setImage("uploads/images/" . $file->getClientOriginalName());
            $produit->setDescription("testimg");
            $produit->setPrix('1000');
            $produit->setNomProduit('testimg');

            $em->persist($produit);
            $em->flush();

        }
        return $this->render('@GestionBoutiques/Admin/test.html.twig');
    }
}