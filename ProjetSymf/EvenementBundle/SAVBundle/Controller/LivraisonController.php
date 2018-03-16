<?php

namespace SAVBundle\Controller;

use SAVBundle\Entity\Commande;
use SAVBundle\Entity\FullAdress;
use SAVBundle\Entity\Livraison;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * Livraison controller.
 *
 * @Route("SAV/livraison")
 */
class LivraisonController extends Controller
{
    /**
     * Lists all livraison entities.
     *
     * @Route("/", name="SAV_livraison_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $livraisons = $em->getRepository('SAVBundle:Livraison')->findAll();

        return $this->render('SAVBundle:livraison/index.html.twig', array(
            'livraisons' => $livraisons,
        ));
    }

    /**
     * Creates a new livraison entity.
     *
     * @Route("/new", name="SAV_livraison_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request,$id)
    {
        $livraison = new Livraison();
        $em = $this->getDoctrine()->getManager();
        $user=$this->get('security.token_storage')->getToken()->getUser();
        $adresses= $em->getRepository('SAVBundle:FullAdress')->findBy(array('idUser' => $user->getId()));

        $form = $this->createForm('SAVBundle\Form\LivraisonType', $livraison, array('adresses'=>$adresses));
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $commande = $em->getRepository('SAVBundle:Commande')->find($id);
            $livraison->setCommandeRef($commande);
            $livraison->setAdresse(FullAdress::class);
            $livraison->setEtat("non envoyée");
            $em->persist($livraison);
            $em->flush();

            return $this->redirectToRoute('sav_livraison_show', array('id' => $livraison->getId()));
        }

        return $this->render('@SAV/livraison/new.html.twig', array(
            'livraison' => $livraison,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a livraison entity.
     *
     * @Route("/{id}", name="SAV_livraison_show")
     * @Method("GET")
     */
    public function showAction(Livraison $livraison)
    {
        $deleteForm = $this->createDeleteForm($livraison);

        return $this->render('SAVBundle:livraison/show.html.twig', array(
            'livraison' => $livraison,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing livraison entity.
     *
     * @Route("/{id}/edit", name="SAV_livraison_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Livraison $livraison)
    {
        $deleteForm = $this->createDeleteForm($livraison);
        $editForm = $this->createForm('SAVBundle\Form\LivraisonType', $livraison);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('SAV_livraison_edit', array('id' => $livraison->getId()));
        }

        return $this->render('SAVBundle:livraison:edit.html.twig', array(
            'livraison' => $livraison,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a livraison entity.
     *
     * @Route("/{id}", name="SAV_livraison_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Livraison $livraison)
    {
        $form = $this->createDeleteForm($livraison);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($livraison);
            $em->flush();
        }

        return $this->redirectToRoute('SAV_livraison_index');
    }

    /**
     * Creates a form to delete a livraison entity.
     *
     * @param Livraison $livraison The livraison entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Livraison $livraison)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('SAV_livraison_delete', array('id' => $livraison->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
