<?php

namespace SAVBundle\Controller;

use SAVBundle\Entity\demandeLivreur;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

/**
 * Demandelivreur controller.
 *
 * @Route("sav/demandeLivreur")
 */
class demandeLivreurController extends Controller
{
    /**
     * Lists all demandeLivreur entities.
     *
     * @Route("/", name="sav_demandeLivreur_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $demandeLivreurs = $em->getRepository('SAVBundle:demandeLivreur')->findAll();

        return $this->render('SAVBundle:demandelivreur:index.html.twig', array(
            'demandeLivreurs' => $demandeLivreurs,
        ));
    }

    public function indexAdminAction()
    {
        $em = $this->getDoctrine()->getManager();

        $demandeLivreurs = $em->getRepository('SAVBundle:demandeLivreur')->findBy(array('isActive' => 0));
        $demandeLivreursActive = $em->getRepository('SAVBundle:demandeLivreur')->findBy(array('isActive' => 1));
        return $this->render('SAVBundle:demandelivreur:indexAdmin.html.twig', array(
            'demandeLivreurs' => $demandeLivreurs,
            'demandeLivreursActive' => $demandeLivreursActive,
        ));
    }
    /**
     * Creates a new demandeLivreur entity.
     *
     * @Route("/new", name="sav_demandeLivreur_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $demandeLivreur = new Demandelivreur();
        $form = $this->createForm('SAVBundle\Form\demandeLivreurType', $demandeLivreur);
        $form->handleRequest($request);
        $user=$this->get('security.token_storage')->getToken()->getUser();
        $em = $this->getDoctrine()->getManager();
        $demandeLivreurs = $em->getRepository('SAVBundle:demandeLivreur')->findOneBy(array('clientRef' =>$user->getId()));
        if ($demandeLivreurs != null)
        {
            if($demandeLivreurs->isActive()==1)
            return $this->render('SAVBundle:demandelivreur:demandeActive.html.twig');
            else
                return $this->redirectToRoute('sav_demandeLivreur_show', array('id' => $demandeLivreurs->getId()));
        }
        if ($form->isSubmitted() && $form->isValid()) {
            $demandeLivreur->setClientRef($user);
            $demandeLivreur->setIsActive(0);
            $demandeLivreur->setDateInscription(new \DateTime('now'));
            $em->persist($demandeLivreur);
            $em->flush();

            return $this->redirectToRoute('sav_demandeLivreur_show', array('id' => $demandeLivreur->getId()));
        }

        return $this->render('SAVBundle:demandelivreur:new.html.twig', array(
            'demandeLivreur' => $demandeLivreur,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a demandeLivreur entity.
     *
     * @Route("/{id}", name="sav_demandeLivreur_show")
     * @Method("GET")
     */
    public function showAction(demandeLivreur $demandeLivreur)
    {
        $deleteForm = $this->createDeleteForm($demandeLivreur);

        return $this->render('SAVBundle:demandelivreur:show.html.twig', array(
            'demandeLivreur' => $demandeLivreur,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing demandeLivreur entity.
     *
     * @Route("/{id}/edit", name="sav_demandeLivreur_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, demandeLivreur $demandeLivreur)
    {
        $deleteForm = $this->createDeleteForm($demandeLivreur);
        $editForm = $this->createForm('SAVBundle\Form\demandeLivreurType', $demandeLivreur);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('sav_demandeLivreur_edit', array('id' => $demandeLivreur->getId()));
        }

        return $this->render('@SAV/demandelivreur/edit.html.twig', array(
            'demandeLivreur' => $demandeLivreur,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a demandeLivreur entity.
     *
     * @Route("/{id}", name="sav_demandeLivreur_delete")
     * @Method("DELETE")
     */
    public function deleteAdminAction(Request $request, demandeLivreur $demandeLivreur)
    {
        $form = $this->createDeleteForm($demandeLivreur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($demandeLivreur);
            $em->flush();
        }

        return $this->redirectToRoute('sav_admin_demandeLivreur_index');
    }
    public function addToListLivreurAction()
    {

    }


    /**
     * Creates a form to delete a demandeLivreur entity.
     *
     * @param demandeLivreur $demandeLivreur The demandeLivreur entity
     *
     * @return \Symfony\Component\Form\Form The form
     */


    private function createDeleteForm(demandeLivreur $demandeLivreur)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('sav_demandeLivreur_delete', array('id' => $demandeLivreur->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function activateLivreurAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $demandeLivrerur=$em->getRepository("SAVBundle:demandeLivreur")->find($id);
        $demandeLivrerur->setIsActive(1);
        $em->persist($demandeLivrerur);
        $em->flush();
        return $this->redirectToRoute('sav_admin_demandeLivreur_index');
    }
    public function disableLivreurAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $demandeLivrerur=$em->getRepository("SAVBundle:demandeLivreur")->find($id);
        $demandeLivrerur->setIsActive(0);
        $em->persist($demandeLivrerur);
        $em->flush();
        return $this->redirectToRoute('sav_admin_demandeLivreur_index');
    }
}
