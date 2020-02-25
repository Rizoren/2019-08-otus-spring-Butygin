package todolist.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import todolist.dto.TaskDto;
import todolist.dto.UserDto;
import todolist.exception.NotFoundException;
import todolist.model.Tasks;
import todolist.service.ToDoTaskService;
import todolist.service.ToDoUserService;

import java.security.Principal;
import java.util.List;

@Controller
@EnableAutoConfiguration
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoUserService userService;
    private final ToDoTaskService taskService;
    private final Logger logger = LoggerFactory.getLogger(ToDoController.class);

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("usersSize", userService.getCountUsers());
        return "welcome";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/success")
    public RedirectView success(@RequestParam("username") String username) {
        String id = userService.findUserByName(username).getId();
        return new RedirectView("/list?id="+id);
    }

    @GetMapping("/list")
    public String getListPage(@RequestParam("id") String id, Model model) throws NotFoundException {
        List<TaskDto> tasks = taskService.findAllByUserId(id);
        model.addAttribute("userId", id);
        model.addAttribute("tasks", tasks);
        return "list";
    }

    @GetMapping("/edit")
    public String getListPage(@RequestParam("user_id") String userId, @RequestParam("task_id") String taskId, Model model) {
        TaskDto task = taskService.findById(taskId);
        model.addAttribute("userId", userId);
        model.addAttribute("task", task);
        return "edit";
    }

    @GetMapping("/errors/err403")
    public ModelAndView accessDenied(Principal user) {

        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("message", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("message",
                    "You do not have permission to access this page!");
        }
        model.setViewName("errors/err403");
        return model;
    }
}
