package exemple.eurekafeignclient.controller;


import exemple.eurekafeignclient.entities.Voiture;
import exemple.eurekafeignclient.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @GetMapping("/voitures")
    public List<Voiture> findAll() {
        return voitureService.findAll();
    }

    @GetMapping("/voiture/{id}")
    public Voiture findById(@PathVariable Long id) { // Ajouter @PathVariable ici
        try {
            return voitureService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/voiture/{ClientId}")
    public Voiture save(@RequestBody Voiture voiture, @PathVariable Long ClientId) { // Ajout de @PathVariable pour ClientId
        return voitureService.save(voiture);
    }

    @DeleteMapping("/voiture/{id}")
    public void delete(@PathVariable Long id) { // Ajouter @PathVariable ici
        voitureService.delete(id);
    }

    @PutMapping("/voiture/{id}")
    public Voiture update(@PathVariable Long id, @RequestBody Voiture voiture) { // Ajouter @PathVariable et @RequestBody
        return voitureService.update(id, voiture);
    }


}