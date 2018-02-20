<?php

namespace CalendrierMedecinsBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class PatientControllerTest extends WebTestCase
{
    public function testAjout()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Ajout');
    }

    public function testList()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/List');
    }

    public function testRecherche()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/Recherche');
    }

}
