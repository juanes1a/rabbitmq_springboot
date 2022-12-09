package co.com.bancolombia.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserRegistrationListener {

    @Autowired
    public final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {"q.user-registration"})
    public void onUserRegistration(UserRegistrationRequest event) {
        log.info("User Registration Event Received: {}", event);

        executeRegistration(event);

        rabbitTemplate.convertAndSend("x.post-registration", "", event);
    }

    private void executeRegistration(UserRegistrationRequest event) {
        log.info("Executing User Registration Event: {}", event);
        throw new RuntimeException("Registration Failed");
    }

    @RabbitListener(queues = {"q.fall-back-registration"})
    public void onRegistrationFailure(UserRegistrationRequest failedRegistration){
        log.info("Executing fallback for failed registration {}", failedRegistration);
    }
}