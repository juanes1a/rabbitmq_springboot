package co.com.bancolombia.api;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    @Autowired
    public final RabbitTemplate rabbitTemplate;

    @PostMapping(path = "/user")
    public ResponseEntity createUser(@RequestBody UserRegistrationRequest request) {
        rabbitTemplate.convertAndSend("", "user-registration", request);
        return new ResponseEntity<String>("Message Send",HttpStatus.OK);
    }
}
