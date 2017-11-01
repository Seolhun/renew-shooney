package hi.cord.com.common.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author HunSeol
 * @Date 2017. 11. 1.
 * @IDE IntelliJ IDEA
 */
//@RestController
//@RequestMapping("")
public class RestControllerExample {
    @GetMapping("")
    public ResponseEntity findAll(){


        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("")
    public ResponseEntity save(){

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    @GetMapping("")
    public ResponseEntity findOne(){

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("")
    public ResponseEntity updated(){

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("")
    public ResponseEntity delete(){

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
