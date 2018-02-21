<?php

namespace MehdiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class PanierController extends Controller
{
    public function AjoutAction($id)
    {
       /* $session = $this->getRequest()->getSession();

        if (!$session->has('panier')) $session->set('panier', array());
        $panier = $session->get('panier');

        if (array_key_exists($id, $panier)) {
            if ($this->getRequest()->query->get('qte') != null) $panier[$id] = $this->getRequest()->query->get('qte');
            $this->get('session')->getFlashBag()->add('success', 'Quantité modifié avec succès');
        } else {
            if ($this->getRequest()->query->get('qte') != null)
                $panier[$id] = $this->getRequest()->query->get('qte');
            else
                $panier[$id] = 1;

            $this->get('session')->getFlashBag()->add('success', 'Article ajouté avec succès');
        }

        $session->set('panier', $panier);*/


        return $this->render('MehdiBundle:Panier:ajout.html.twig', array());
    }
    public function panierAction()
    {
        /*$session = $this->getRequest()->getSession();
        if (!$session->has('panier')) $session->set('panier', array());

        $em = $this->getDoctrine()->getManager();
        $produits = $em->getRepository('EcommerceBundle:Produits')->findArray(array_keys($session->get('panier')));

        return $this->render('MehdiBundle:Panier:panier.html.twig', array('produits' => $produits,
            'panier' => $session->get('panier')));*/
    }



}
