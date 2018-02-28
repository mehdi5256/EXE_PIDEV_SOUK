<?php

namespace Front2Bundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class sitewebController extends Controller
{
    public function page0Action()
    {
        return $this->render('Front2Bundle:siteweb:page0.html.twig', array(
            // ...
        ));
    }
    public function page1Action()
    {
        return $this->render('Front2Bundle:siteweb:page1.html.twig', array(
            // ...
        ));
    }

}
