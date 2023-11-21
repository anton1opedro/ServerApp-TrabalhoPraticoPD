package pt.isec.pd.as.pd.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listeningPort")
public class ListeningPortController
{
    @GetMapping("/get")
    public int getListeningPort() {
        return 8000;
    }
}
