<?php

namespace CalendrierMedecinsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('CalendrierMedecinsBundle:Default:index.html.twig');
    }
}
