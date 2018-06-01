<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\Event;
use EvenementBundle\Entity\Participer;
use EvenementBundle\EvenementBundle;
use EvenementBundle\Form\EventType;
use EvenementBundle\Form\RechercheType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\TimeType;
use Symfony\Component\HttpFoundation\Request;

class EventController extends Controller
{
    public function createAction(Request $request)
    {
        $event = new Event();
        $form = $this->createFormBuilder($event)
            ->add('nom',TextType::class)
            ->add('description',TextareaType::class)
            ->add('datedeb',DateType::class, array('widget'=>'single_text'))
            ->add('datefin',DateType::class,array('widget'=>'single_text'))
            ->add('lieu',TextType::class)
            ->add('nomorganisateur',TextType::class)
            ->add('heuredeb',TimeType::class)
            ->add('heurefin',TimeType::class)
            ->add('image',FileType::class)
            ->add('save',SubmitType::class)
            ->getForm();

         $form->handleRequest($request);
         if($form->isValid())
        { $file = $form['image']->getData();
            $event = $form->getData();
            $file->move('uploads/images/',$file->getClientOriginalName());
            $event->setImage("uploads/images/".$file->getClientOriginalName());
            $event = $form->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();

            return $this->redirectToRoute("affiche");
        }

        return $this->render('EvenementBundle:Event:create.html.twig', array(
            'form'=>$form->createView()
        ));
    }

    public function afficheAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $listevents = $em->getRepository('EvenementBundle:Event')->findAll();
        $events  = $this->get('knp_paginator')->paginate($listevents,
            $request->query->get('page', 1)/*page number*/,
            3/*limit per page*/
        );
        return $this->render('EvenementBundle:Event:affiche.html.twig',array('events'=>$events));

    }

    public function detailAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $events= $em->getRepository(event::class)->find($id);
        return $this->render('EvenementBundle:Event:detail.html.twig', array(
            'events'=>$events
        ));
    }


    public function updateAction(Request $request,$id){

            $event = new Event();
            $em=$this->getDoctrine()->getManager();
            $event = $em->getRepository(Event::class)->find($id);

            $form=$this->createForm(EventType::class,$event);
            $form->handleRequest($request);

             if ($form->isValid()){


             $em->persist($event);
             $em->flush();
           return $this->redirectToRoute("affiche");
                    }
    return $this->render('EvenementBundle:Event:update.html.twig', array('form'=>$form->createView()));
             }




    public function deleteAction($id)
    {
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository(event::class)->find($id);
        $em->remove($event);
        $em->flush();

        return $this->redirectToRoute('affiche');

    }
    public function participeAction(Request $request,$id)
    { $user = $this->getUser();
        $idb = $user->getId();
        $name= $user->getNom();
        $Test=0;
        $em=$this->getDoctrine()->getManager();
        $voiture=$em->getRepository("EvenementBundle:Event")->find($id);
        $voiture->setNbrparticipants($voiture->getNbrparticipants()+1);
        $maparti = $this->getDoctrine()->getManager()->getRepository(Participer::class)
            ->findByIdu($idb);
        $eventsarray=array();

        foreach($maparti as $particip)
        {

            $ide=$particip->getIde();
            if($ide==$id)
            {
                $Test=1;
            }
        }

        if($Test==0)
        {
            //  $message=1;

            $events=new Event();
            $nbp=$events->getParticipate();
            $events->setParticipate($nbp+1);


            $participer=new Participer();
            $participer->setNom($voiture->getNom());
            $participer->setDescription($voiture->getDescription());
            $participer->setIdu($idb);
            $participer->setParticipate($voiture->getParticipate());
            $participer->setDatedeb($voiture->getDatedeb());
            $participer->setDatefin($voiture->getDatefin());
            $participer->setHeuredeb($voiture->getHeuredeb());
            $participer->setHeurefin($voiture->getHeurefin());
            $participer->setNomorganisateur($name);
            $participer->setLieu($voiture->getLieu());
            $participer->setImage($voiture->getImage());
            $participer->setide($id);

            $em->persist($participer);
            $em->flush();


            //  $data = array(
            //  'alert' => $message,);
            return $this->redirectToRoute('ListParticipants');//,$data);
        }
        else{
            //$message=2;
            //$data = array(
            //'alert' => $message,);
            return $this->redirectToRoute("ListParticipants");//$data);
        }


    }


    function  AfficheeventsavenirAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $listevents = $em->getRepository('EvenementBundle:Event')->findavenir();
        $events  = $this->get('knp_paginator')->paginate($listevents,
            $request->query->get('page', 1)/*page number*/,
            3/*limit per page*/
        );
        return $this->render('EvenementBundle:Event:evenetsvenir.html.twig',array('events'=>$events));

    }

    function  AfficheeventspasserAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $listevents = $em->getRepository('EvenementBundle:Event')->findfini();
        $events  = $this->get('knp_paginator')->paginate($listevents,
            $request->query->get('page', 1)/*page number*/,
            3/*limit per page*/
        );
        return $this->render('EvenementBundle:Event:eventspasser.html.twig',array('events'=>$events));

    }


    function  AfficheeventssemaineAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $listevents = $em->getRepository('EvenementBundle:Event')->findeventsemaine();
        $events  = $this->get('knp_paginator')->paginate($listevents,
            $request->query->get('page', 1)/*page number*/,
            3/*limit per page*/
        );
        return $this->render('EvenementBundle:Event:eventsemaine.html.twig',array('events'=>$events));

    }

    function ListeAction(Request $request)
    {
        {
            $em=$this->getDoctrine()->getManager();
            $listevents = $em->getRepository('EvenementBundle:Event')->findAll();
            $events  = $this->get('knp_paginator')->paginate($listevents,
                $request->query->get('page', 1)/*page number*/,
                5/*limit per page*/
            );
            return $this->render('EvenementBundle:Event:listEvent.html.twig',array('events'=>$events));

        }

    }


    function ListParticipantsAction()
    {

        $user = $this->getUser();
        $idb= $user->getId();

      $events = $this->getDoctrine()->getManager()->getRepository(Participer::class)
          ->findByIdu($idb);


        return $this->render('EvenementBundle:Event:listParticipant.html.twig', array('events' => $events));
        // return new Response("Bonjour");
    }

    public function SearchDQLAction()
    {
        $em=$this->getDoctrine();
        $events=$em->getRepository(Event::class)->finDqlPays();
        return $this->render('EvenementBundle:Event:affiche.html.twig', array(
            'events'=>$events
        ));
    }

    function BestEventAction()
    {
        $em = $this->getDoctrine()->getManager();
        $events = $em->getRepository('EvenementBundle:Event')->findbestEvent();
        return $this->render('EvenementBundle:Event:bestEvent.html.twig', array('events' => $events));
        // return new Response("Bonjour");
    }



    public function rechercheAjaxAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();

        if ($request->isXmlHttpRequest()) {
            $search  = $request->get('search');
            dump($search);
            $event = new Event();
            $repo  = $em->getRepository('EvenementBundle:Event');
            $event = $repo->findAjax($search);
            return $this->render('EvenementBundle:Event:component.html.twig', array('events' => $event));
        }


    }

}
