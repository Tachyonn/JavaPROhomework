package ua.kiev.prog;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MyController {

    private Map<String, byte[]> photos = new HashMap<>();

    @RequestMapping("/")
    public String onIndex(Model model) {
        Set<String> list = photos.keySet();
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("/upload")
    public String onMultipleUpload(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) {
        for (MultipartFile uploadedFile : uploadingFiles) {
            try {
                String id = uploadedFile.getOriginalFilename();
                photos.put(id, uploadedFile.getBytes());
            } catch (IOException e) {
                throw new PhotoErrorException();
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo.isEmpty())
            throw new PhotoErrorException();

        try {
            byte[] photoBytes = photo.getBytes();
            String id = photo.getOriginalFilename();
            photos.put(id, photoBytes);

            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    /*
        Spring обрезает @PathVariable после точки, это оф. баг,
        по этому использовался "костыль" в виде regex
        https://stackoverflow.com/a/16333149
     */
    @RequestMapping("/photo/{photo_id:.+}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") String id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id:") String id) {
        return photoById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String onDelete(@RequestParam("toDelete") String[] ids) {
        if (ids == null) {
            return "index";
        }
        for (String id : ids) {
            photos.remove(id);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String onList(Model model) {
        Set<String> list = photos.keySet();
        model.addAttribute("list", list);
        return "list";
    }

    private ResponseEntity<byte[]> photoById(String id) {
        byte[] bytes = photos.get(id);
        if (bytes == null)
            throw new PhotoNotFoundException();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
