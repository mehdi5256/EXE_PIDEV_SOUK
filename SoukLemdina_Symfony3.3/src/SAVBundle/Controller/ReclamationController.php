<?php

namespace SAVBundle\Controller;

use SAVBundle\Entity\Reclamation;
use SAVBundle\SAVBundle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Validator\Constraints\Date;

/**
 * Reclamation controller.
 *
 * @Route("SAV/reclamation")
 */
class ReclamationController extends Controller
{
    /**
     * Lists all reclamation entities.
     *
     * @Route("/", name="SAV_reclamation_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $user=$this->get('security.token_storage')->getToken()->getUser();
        $reclamations = $em->getRepository('SAVBundle:Reclamation')->findBy(array('clientRef' => $user->getId()));

        return $this->render('SAVBundle:reclamation:index.html.twig', array(
            'reclamations' => $reclamations,
        ));
    }

    public function indexAdminAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reclamations = $em->getRepository('SAVBundle:Reclamation')->findAll();

        return $this->render('SAVBundle:reclamation:indexAdmin.html.twig', array(
            'reclamations' => $reclamations,
        ));
    }

    /**
     * Creates a new reclamation entity.
     *
     * @Route("/new", name="SAV_reclamation_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $reclamation = new Reclamation();
        $form = $this->createForm('SAVBundle\Form\ReclamationType', $reclamation);
        $form->handleRequest($request);
        $user=$this->get('security.token_storage')->getToken()->getUser();
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $reclamation->setClientRef($user);
            $reclamation->setDate(new \DateTime('now'));
            $reclamation->setEtat("non traitée");
            $reclamation->setAnswer("Pas Encore Répendu");
            $em->persist($reclamation);
            $em->flush();

            return $this->redirectToRoute('SAV_reclamation_show', array('id' => $reclamation->getId()));
        }

        return $this->render('SAVBundle:reclamation:new.html.twig', array(
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a reclamation entity.
     *
     * @Route("/{id}", name="SAV_reclamation_show")
     * @Method("GET")
     */
    public function showAction(Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);

        return $this->render('SAVBundle:reclamation:show.html.twig', array(
            'reclamation' => $reclamation,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing reclamation entity.
     *
     * @Route("/{id}/edit", name="SAV_reclamation_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Reclamation $reclamation)
{
    $deleteForm = $this->createDeleteForm($reclamation);
    $editForm = $this->createForm('SAVBundle\Form\ReclamationType', $reclamation);
    $editForm->handleRequest($request);

    if ($editForm->isSubmitted() && $editForm->isValid()) {
        $this->getDoctrine()->getManager()->flush();

        return $this->redirectToRoute('SAV_reclamation_edit', array('id' => $reclamation->getId()));
    }

    return $this->render('SAVBundle:reclamation:edit.html.twig', array(
        'reclamation' => $reclamation,
        'edit_form' => $editForm->createView(),
        'delete_form' => $deleteForm->createView(),
    ));
}

    public function editAdminAction(Request $request, Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);
        $editForm = $this->createForm('SAVBundle\Form\ReclamationType', $reclamation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('sav_admin_reclamation_edit', array('id' => $reclamation->getId()));
        }

        return $this->render('SAVBundle:reclamation:editAdmin.html.twig', array(
            'reclamation' => $reclamation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }


    /**
     * Deletes a reclamation entity.
     *
     * @Route("/{id}", name="SAV_reclamation_delete")
     * @Method("DELETE")
     */
    public function deleteAction()
    {
            $id=$_GET['id'];
            $em = $this->getDoctrine()->getManager();
            $reclamation=$em->getRepository(Reclamation::class)->findBy($id);
            $em->remove($reclamation);
            $em->flush();
            return $this->redirectToRoute('sav_admin_reclamation_index');

    }

    public function adminDeleteAction()
    {
        $id=$_GET['id'];
        $em = $this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(Reclamation::class)->find($id);
        $em->remove($reclamation);
        $em->flush();
        return $this->redirectToRoute('sav_admin_reclamation_index');

    }

    public function traiterReclamationAction()
    {
        $id=$_GET['id'];
        $em = $this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(Reclamation::class)->find($id);
        if ($reclamation->getEtat()=="non traitée") {
            $reclamation->setEtat("Traitée");
        }else $reclamation->setEtat("non traitée");
        $em->persist($reclamation);
        $em->flush();
        return $this->redirectToRoute('sav_admin_reclamation_index');

    }

    public function answerReclamationAction(Request $request, $id)
{

    $em = $this->getDoctrine()->getManager();
    $reclamation=$em->getRepository(Reclamation::class)->find($id);
    if ($request->isMethod('post'))
    {
        $reclamation->setAnswer($request->get("answer")) ;
        $em->persist($reclamation);
        $em->flush();
        return $this->redirectToRoute('sav_admin_reclamation_index');
    }
    return $this->render('SAVBundle:reclamation:answerAdmin.html.twig', array(
        'reclamation' => $reclamation ));
}



    public function sendbackAction(Request $request, $id)
    {
        $id=$_GET['id'];
        $em = $this->getDoctrine()->getManager();
        $reclamation=$em->getRepository(Reclamation::class)->find($id);
        $editForm = $this->createForm('SAVBundle\Form\ReclamationType', $reclamation);
        $editForm->handleRequest($request);
        $em->flush();
        return $this->redirectToRoute('sav_admin_reclamation_index');
    }




    /**
     * Creates a form to delete a reclamation entity.
     *
     * @param Reclamation $reclamation The reclamation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */


    private function createDeleteForm(Reclamation $reclamation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('SAV_reclamation_delete', array('id' => $reclamation->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

}
