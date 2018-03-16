<?php

namespace MehdiBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class layoutControllerTest extends WebTestCase
{
    public function testLayout()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/layout');
    }

}
