<?php

namespace ActualiteBundle\Controller;
use ActualiteBundle\Entity\Actualite;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


class ActualiteController extends Controller
{


    public function indexAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $currentDate = Date('Y-m-d H:i:s');
        $newdate = strtotime ( '-3 day' , strtotime ( $currentDate ) ) ;
        $newdate = date ( 'Y-m-j' , $newdate );

        $list = $em->getRepository('ActualiteBundle:Actualite')->createQueryBuilder('e')
            ->addORderBy('e.date', 'DESC')
            ->andWhere('e.date BETWEEN :from AND :to')
            ->setParameter('from', $newdate  )
            ->setParameter('to', $currentDate)
            ->getQuery()
            ->execute();
        $actualites  = $this->get('knp_paginator')->paginate($list,
            $request->query->get('page', 1)/*page number*/,
            3/*limit per page*/
        );
        return $this->render('ActualiteBundle:Actualite:index.html.twig',array('actualites'=>$actualites));

    }

    public function manipulationAction()
    {
        $em = $this->getDoctrine()->getManager();
        $actualites = $em->getRepository('ActualiteBundle:Actualite')
            ->createQueryBuilder('e')
            ->getQuery()
            ->execute();

        return $this->render('@Actualite/actualite/manipulation.html.twig', array(
            'actualites' => $actualites,
        ));
    }

    public function newAction(Request $request)
    {
        $actualite = new Actualite();
        $form = $this->createForm('ActualiteBundle\Form\ActualiteType', $actualite);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $file = $actualite->getPhoto();

            $fileName = md5(uniqid('', true)).'.'.$file->guessExtension();
            $path = "C:\wamp64\\www\\externe\\web" ;
            $file->move(
                $path,
                $fileName
            );
            $em = $this->getDoctrine()->getManager();
            $actualite->setPhoto($fileName);
            $time = new \DateTime();
            echo $time->format('H:i:s \O\n Y-m-d');
            $actualite->setDate($time);
            $em->persist($actualite);
            $em->flush();

            return $this->redirectToRoute('a_manipulation', array('id' => $actualite->getId()));
        }

        return $this->render('@Actualite/actualite/new.html.twig', array(
            'actualite' => $actualite,
            'form' => $form->createView(),
        ));

    }

    public function showAction(Actualite $actualite)
    {
        $deleteForm = $this->createDeleteForm($actualite);
        $em = $this->getDoctrine()->getManager();
        return $this->render('@Actualite/actualite/show.html.twig', array(
            'actualite' => $actualite,
            'delete_form' => $deleteForm->createView(),

        ));
    }

    public function editAction(Request $request, Actualite $actualite)
    {
        $photo=$actualite->getPhoto();
        $deleteForm = $this->createDeleteForm($actualite);
        $editForm = $this->createForm('ActualiteBundle\Form\ActualiteType', $actualite);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $file = $actualite->getPhoto();
            if ($file !=""){
            $fileName = md5(uniqid('', true)).'.'.$file->guessExtension();
            $path = "C:\wamp64\www\\externe\web" ;
            $file->move(
                $path,
                $fileName
            );
                $photo=$fileName;
            }
            $actualite->setPhoto($photo);
            $em = $this->getDoctrine()->getManager();
            $em->persist($actualite);
            $em->flush();

            return $this->redirectToRoute('a_manipulation', array('id' => $actualite->getId()));
        }

        return $this->render('@Actualite/actualite/edit.html.twig', array(
            'actualite' => $actualite,
            'form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }


    public function deleteAction(Request $request, Actualite $actualite, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $actualite = $em->getRepository('ActualiteBundle:Actualite')->find($id);
        $em->remove($actualite);
        $em->flush();



        return $this->redirectToRoute('a_manipulation');
    }

    private function createDeleteForm(Actualite $actualite)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('a_delete', array('id' => $actualite->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


    public function rechercheAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        if ($request->isXmlHttpRequest()) {
            $search = $request->get('search');
            dump($search);
            $actualite = new Actualite();
            $repo= $em->getRepository('ActualiteBundle:Actualite');
            $actualite = $repo->findActualite($search);
            return $this->render('ActualiteBundle:actualite:search.html.twig', array('actualites' => $actualite));
        }



    }

}
