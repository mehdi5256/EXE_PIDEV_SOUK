<?php

namespace SAVBundle\Controller;

use SAVBundle\Entity\FullAdress;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Fulladress controller.
 *
 * @Route("SAV/fullAdress")
 */
class FullAdressController extends Controller
{
    /**
     * Lists all fullAdress entities.
     *
     * @Route("/", name="SAV_fullAdress_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $fullAdresses = $em->getRepository('SAVBundle:FullAdress')->findAll();

        return $this->render('SAVBundle:fulladress:index.html.twig', array(
            'fullAdresses' => $fullAdresses,
        ));
    }

    public function indexAdminAction()
    {
        $em = $this->getDoctrine()->getManager();

        $fullAdresses = $em->getRepository('SAVBundle:FullAdress')->findAll();

        return $this->render('SAVBundle:fulladress:indexAdmin.html.twig', array(
            'fullAdresses' => $fullAdresses,
        ));
    }

    /**
     * Creates a new fullAdress entity.
     *
     * @Route("/new", name="SAV_fullAdress_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request,$id)
    {
        $fullAdress = new Fulladress();
        $form = $this->createForm('SAVBundle\Form\FullAdressType', $fullAdress);
        $form->handleRequest($request);
        $user=$this->get('security.token_storage')->getToken()->getUser();

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $fullAdress->setIdUser($user);
            $em->persist($fullAdress);
            $em->flush();

            return $this->redirectToRoute('sav_livraison_new', array('id' => $id));
        }

        return $this->render('SAVBundle:fulladress:new.html.twig', array(
            'fullAdress' => $fullAdress,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a fullAdress entity.
     *
     * @Route("/{id}", name="SAV_fullAdress_show")
     * @Method("GET")
     */
    public function showAction(FullAdress $fullAdress)
    {
        $deleteForm = $this->createDeleteForm($fullAdress);

        return $this->render('SAVBundle:fulladress/show.html.twig', array(
            'fullAdress' => $fullAdress,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing fullAdress entity.
     *
     * @Route("/{id}/edit", name="SAV_fullAdress_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, FullAdress $fullAdress)
    {
        $deleteForm = $this->createDeleteForm($fullAdress);
        $editForm = $this->createForm('SAVBundle\Form\FullAdressType', $fullAdress);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('SAV_fullAdress_edit', array('id' => $fullAdress->getId()));
        }

        return $this->render('SAVBundle:fulladress/edit.html.twig', array(
            'fullAdress' => $fullAdress,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a fullAdress entity.
     *
     * @Route("/{id}", name="SAV_fullAdress_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, FullAdress $fullAdress)
    {
        $form = $this->createDeleteForm($fullAdress);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($fullAdress);
            $em->flush();
        }

        return $this->redirectToRoute('SAV_fullAdress_index');
    }

    /**
     * Creates a form to delete a fullAdress entity.
     *
     * @param FullAdress $fullAdress The fullAdress entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(FullAdress $fullAdress)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('SAV_fullAdress_delete', array('id' => $fullAdress->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
