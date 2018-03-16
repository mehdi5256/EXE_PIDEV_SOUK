<?php

namespace MehdiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class ProduitControllerTest extends WebTestCase
{
    public function testAjoutproduit()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AjoutProduit');
    }

    public function testListproduit()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/listProduit');
    }

}
