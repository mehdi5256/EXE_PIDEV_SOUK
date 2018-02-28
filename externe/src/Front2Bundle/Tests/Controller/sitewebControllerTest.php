<?php

namespace Front2Bundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class sitewebControllerTest extends WebTestCase
{
    public function testPage0()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/page0');
    }

}
