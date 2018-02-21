<?php

namespace TestBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class RedirectionCController extends Controller
{
    public function redirectmeAction()
    {
        return $this->render('TestBundle:RedirectionC:admin.html.twig', array(
            // ...
        ));
    }

}
