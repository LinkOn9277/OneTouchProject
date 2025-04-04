/*********************************************************************
 * 클래스명 : MembersController
 * 기능 :
 * 작성자 : 이동건
 * 작성일 : 2025-03-30
 * 수정 : 2025-03-30     이동건
 *********************************************************************/
package com.onetouch.delinight.Controller;

import com.onetouch.delinight.DTO.BranchDTO;
import com.onetouch.delinight.Service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/create")
    public String createView() {
        return "branch/create";
    }

    @PostMapping("/create")
    public String createProc(BranchDTO branchDTO) {
        branchService.create(branchDTO);


        return "branch/create";
    }

    @GetMapping("/list")
    public String listView(Model model) {
        List<BranchDTO> branchDTOList =
            branchService.list();
        model.addAttribute("branchDTOList", branchDTOList);
        return "branch/list";
    }
}
