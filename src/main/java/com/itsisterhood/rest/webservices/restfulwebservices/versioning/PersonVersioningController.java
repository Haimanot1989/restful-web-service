package com.itsisterhood.rest.webservices.restfulwebservices.versioning;
/*
* More resources on versioning
*   https://www.mnot.net/blog/2011/10/25/web_api_versioning_smackdown
*   http://urthen.github.io/2013/05/09/ways-to-version-your-api/
* Securing Resource
*   - Basic Authentication
*       - send user id and password as part of the request
*   - digest authentication
*       - create a password digest and send this digest across - the actual password is not sent to the server
*   - Oath || Oath2
*
* */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    //uri versioning --> used by twitter
    //execute by: http://localhost:8080/v1/person
    //con: URI polluting
    @GetMapping("v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Bob Charlie");
    }

    //execute by: http://localhost:8080/v2/person
    @GetMapping("v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //request parameter versioning --> used by Amazon
    //execute by: http://localhost:8080/person/param?version=1
    //con: URI polluting
    @GetMapping(value="person/param", params="version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Bob Charlie");
    }

    //execute by: http://localhost:8080/person/param?version=2
    @GetMapping(value="person/param", params="version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //custom || headers versioning --> used by microsoft
    //execute by: headers: key:X-API-VERSION value:1
    //con: misuse of HTTP headers
    //con: can not use caching, since I am not able to differentiate requests based on the header, as version is part of the header
    //con: can not execute request on browser
    //con: difficult to generate API documentation out of code
    @GetMapping(value="person/header", headers="X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Bob Charlie");
    }

    //execute by: headers: key:X-API-VERSION value:2
    @GetMapping(value="person/header", headers="X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //media type || produces || content negotiation || accept header versioning --> used by GitHub
    //execute by: headers: key:Accept value:application/vnd.company.app-v1+json
    //con: misuse of HTTP headers
    //con: can not use caching, since I am not able to differentiate requests based on the header, as version is part of the header
    //con: can not execute request on browser
    //con: difficult to generate API documentation out of code    @GetMapping(value="person/produces", produces="application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Bob Charlie");
    }

    //execute by: headers: key:Accept value:application/vnd.company.app-v2+json
    @GetMapping(value="person/produces", produces="application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
