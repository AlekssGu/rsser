package lv.gusevs.rsser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RssController {
    @GetMapping("/rss")
    public String hello() {
        return "Current time is " + new Date() + "\n";
    }
}
