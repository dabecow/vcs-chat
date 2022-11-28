package edu.oreluniver.controller;

import edu.oreluniver.dao.MessageDAO;
import edu.oreluniver.dao.MessageDAOImpl;
import edu.oreluniver.dto.MessageDto;
import edu.oreluniver.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MessageController {

    private final MessageDAO messageDAO = new MessageDAOImpl();

    @GetMapping
    public String loadMessages(Model model) {
        model.addAttribute("messages", messageDAO.getMessages());
        if (!model.containsAttribute("messageDto"))
            model.addAttribute("messageDto", new MessageDto());

        return "main";
    }

    @PostMapping
    public RedirectView addMessage(@ModelAttribute("messageDto") MessageDto messageDto, RedirectAttributes redirectAttributes) {
        Message message = new Message(messageDto.getText(), messageDto.getAuthor());
        messageDAO.addMessage(message);
        redirectAttributes.addFlashAttribute("messageDto", new MessageDto(messageDto.getAuthor()));
        return new RedirectView("/", true);
    }

}
