/*********************************************************************
 * 클래스명 : CartController
 * 기능 :
 * 작성자 : 이지호
 * 작성일 : 2025-03-30
 * 수정 : 2025-04-01     이지호
 *********************************************************************/
package com.onetouch.delinight.Controller;

import com.onetouch.delinight.DTO.CartItemDTO;
import com.onetouch.delinight.DTO.MenuDTO;
import com.onetouch.delinight.Service.CartService;
import com.onetouch.delinight.Service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
@Log4j2
public class CartController {
    private final CartService cartService;

    @GetMapping("/list")
    public String list(){


        return "roomservice/cart/list";
    }


}
