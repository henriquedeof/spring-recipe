package guru.springframework.controllers;

import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class ImageController {

    private ImageService imageService;
    private RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{idRecipe}/image")
    public String showUploadForm(@PathVariable String idRecipe, Model model){
        log.debug("Loading Upload form");

        model.addAttribute("recipe", this.recipeService.findCommandById(Long.valueOf(idRecipe)));
        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{idRecipe}/image")
    public String handleImagePost(@PathVariable String idRecipe, @RequestParam("imagefile") MultipartFile file){
        log.debug("Handling image");

        this.imageService.saveImageFile(Long.valueOf(idRecipe), file);

        return "redirect:/recipe/" + idRecipe + "/show";
    }


}
