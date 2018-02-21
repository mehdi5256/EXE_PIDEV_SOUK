<?php

namespace MehdiBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MehdiBundle:Default:index.html.twig');
    }
}
