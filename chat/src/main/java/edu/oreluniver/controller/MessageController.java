package edu.oreluniver.controller;

import edu.oreluniver.dto.BranchDto;
import edu.oreluniver.dto.MessageDto;
import edu.oreluniver.dto.NodeDto;
import edu.oreluniver.dto.StateDto;
import edu.oreluniver.mapper.MessageMapper;
import edu.oreluniver.model.Message;
import edu.oreluniver.vcs.model.VcsBranch;
import edu.oreluniver.vcs.model.VcsState;
import edu.oreluniver.vcs.service.VcsProcessor;
import edu.oreluniver.vcs.service.VcsProcessorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final VcsProcessor<Message> vcsProcessor = new VcsProcessorImpl<>();
    private final MessageMapper mapper;

    @PostConstruct
    void init() {
        vcsProcessor.init("master");
    }

    @GetMapping
    public String loadMessages(Model model) {
        List<MessageDto> messages = mapper.toDtoList(vcsProcessor.getEntries());
        model.addAttribute("messages", messages);
        if (!model.containsAttribute("messageDto"))
            model.addAttribute("messageDto", new MessageDto());

        model.addAttribute("branches", vcsProcessor.getBranchesNames().stream().map(BranchDto::new).toList());
        if (VcsState.ATTACHED.equals(vcsProcessor.getState())) {
            model.addAttribute("state",
                    new StateDto(
                            vcsProcessor.getState().toString(),
                            vcsProcessor.getHead().getUuid(),
                            ((VcsBranch) vcsProcessor.getHead()).getName())
            );
        } else {
            model.addAttribute("state",
                    new StateDto(
                            vcsProcessor.getState().toString(),
                            vcsProcessor.getHead().getUuid(),
                            "Not a branch")
            );
        }

        model.addAttribute("branchDto", new BranchDto());
        model.addAttribute("nodeDto", new NodeDto());

        return "main";
    }

    @PostMapping
    public RedirectView addMessage(@ModelAttribute("messageDto") MessageDto messageDto, RedirectAttributes redirectAttributes) {
        Message message = new Message(messageDto.getText(), messageDto.getAuthor());
        vcsProcessor.addEntry(message);
        redirectAttributes.addFlashAttribute("messageDto", new MessageDto(messageDto.getAuthor()));
        return new RedirectView("/", true);
    }

    @PostMapping("/branch/switch")
    public RedirectView switchBranch(@ModelAttribute("branchDto") BranchDto branch) {
        vcsProcessor.moveHead(branch.getName());
        return new RedirectView("/", true);
    }

    @PostMapping("/branch/add")
    public RedirectView addBranch(@ModelAttribute("branchDto") BranchDto branch) {
        vcsProcessor.addBranch(branch.getName());
        return new RedirectView("/", true);
    }

    @PostMapping("/node/move")
    public RedirectView moveToHead(@ModelAttribute("nodeDto") NodeDto node) {
        vcsProcessor.moveHead(node.getId());
        return new RedirectView("/", true);
    }
}
