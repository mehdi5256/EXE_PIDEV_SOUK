<?php

namespace MehdiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CategorieControllerTest extends WebTestCase
{
    public function testAjoutcategorie()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/AjoutCategorie');
    }

    public function testUpdatecategorie()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/UpdateCategorie');
    }

    public function testListcategorie()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/ListCategorie');
    }

}
