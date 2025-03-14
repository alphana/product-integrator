package name.alp.productintegrator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
public class ProductController {

    private final MessageChannel requestChannel;

    public ProductController( @Qualifier("requestChannel") MessageChannel requestChannel) {
        this.requestChannel = requestChannel;
    }

    @PostMapping("/validate")
    public String startFlow(@RequestParam String arg1, @RequestParam String arg2) {
        Message<String> message = MessageBuilder.withPayload("")
                .setHeader("arg1", arg1)
                .setHeader("arg2", arg2)
                .build();
        requestChannel.send(message);
        return "Validation request sent!";
    }
}
