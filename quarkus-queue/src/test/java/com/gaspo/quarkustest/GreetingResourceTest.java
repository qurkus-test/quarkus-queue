package com.gaspo.quarkustest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 *Questo è un classico Test di Integrazione scritto per Quarkus utilizzando la libreria RestAssured. In pratica, sta simulando una vera chiamata HTTP al tuo servizio per verificare che risponda correttamente.
 * Ecco la scomposizione dei componenti:
 * 1. Le Annotazioni
 * @QuarkusTest: Questa è l'annotazione "magica". Dice a Quarkus di avviare l'intero framework (il server, i database in memoria, le dipendenze) prima di eseguire i test. Senza questa, il test non saprebbe dove andare a fare la chiamata GET.
 * @Test: È l'annotazione standard di JUnit 5 che identifica il metodo come un caso di test.
 *
 * 2. La sintassi "Given / When / Then"
 * Il test usa il pattern Behavior Driven Development (BDD) di RestAssured per rendere il codice leggibile quasi come una frase in inglese:
 * given(): Qui imposteresti le condizioni iniziali (es. header, parametri, autenticazione). In questo caso è vuoto perché è una GET semplice.
 * .when().get("/hello"): Questa è l'azione. Il test effettua una chiamata HTTP di tipo GET all'endpoint /hello del tuo server locale (che Quarkus avvia automaticamente su una porta casuale, solitamente la 8081 durante i test).
 * .then(): Qui iniziano le asserzioni (le verifiche).
 * .statusCode(200): Verifica che il server risponda con 200 OK. Se risponde 404 o 500, il test fallisce.
 * .body(is("Hello RESTEasy")): Verifica che il contenuto (il body) della risposta sia esattamente la stringa "Hello RESTEasy". is() è un "matcher" della libreria Hamcrest.
 * Cosa sta succedendo "dietro le quinte"?
 * Quando lanci questo test:
 * Quarkus si avvia in modalità test.
 * RestAssured intercetta l'URL del server appena avviato.
 * Viene inviata una richiesta HTTP reale.
 * La risposta viene analizzata e confrontata con i tuoi parametri.
 */
@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}