<?php

namespace CalendrierMedecinsBundle\Controller;

use CalendrierMedecinsBundle\Entity\Patient;
use CalendrierMedecinsBundle\Form\PatientType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class PatientController extends Controller
{
    public function AjoutAction(Request $a)
    {
        $modele = new Patient();
        $form = $this->createForm(PatientType::class, $modele);
        $form->handleRequest($a);
        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($modele);
            $em->flush();
        }
        return $this->render('CalendrierMedecinsBundle:Patient:ajout.html.twig', array(
            'form' => $form->createView()
        ));

    }

    public function ListAction()
    {
        $em = $this->getDoctrine()->getManager();
        $modeles = $this->getDoctrine()
            ->getRepository(Patient::class)
            ->findAll();
        return $this->render('CalendrierMedecinsBundle:Patient:list.html.twig', array('modeles' => $modeles));
            // ...

    }


        public function RechercheAction(Request $c)
        {
            $em = $this->getDoctrine()->getManager();
            $modeles = $em->getRepository("CalendrierMedecinsBundle:Patient")->findAll();
            if ($c->isMethod('POST')) {
                $libele1 = $c->get('nom');
                $libele2 = $c->get('prenom');

                $modeles = $em->getRepository("CalendrierMedecinsBundle:Patient")->findSerieParametre($libele1,$libele2);
            } else {
                $modeles = $em->getRepository("CalendrierMedecinsBundle:Patient")->findAll();
            }
            return $this->render('CalendrierMedecinsBundle:Patient:recherche.html.twig', array('modeles' => $modeles));
            // ...
        }
    }


