package pt.isec.pd.as.pd.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListeningPortController
{
    @GetMapping("/getListeningPort")
    public int getListeningPort() {
        // Logic to retrieve the server's listening port
        // This can be retrieved from configuration or dynamically determined
        return 8080; // Replace with the actual listening port
    }
}
