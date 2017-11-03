package hi.cord.com.common.controller.example;

import hi.cord.com.jpa.user.domain.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author HunSeol
 * @Date 2017. 11. 1.
 * @IDE IntelliJ IDEA
 */
//@RestController
//@RequestMapping("")
public class RestControllerExample {
    @GetMapping("")
    public ResponseEntity findAll(User user) {
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found required parameters");
        } else if(user.getPagination() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found required pagination parameters");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("/{nickname}")
    public ResponseEntity save(User user, @PathVariable String nickname) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"user\" parameter");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    @GetMapping("/{nickname}")
    public ResponseEntity findOne(User user, @PathVariable String nickname) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"user\" parameter");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/{nickname}")
    public ResponseEntity updated(User user, @PathVariable String nickname) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found \"user\" parameter");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("")
    public ResponseEntity delete() {

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
