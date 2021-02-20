package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.order.Order;
import pl.javastart.order.OrderRepository;
import pl.javastart.order.OrderStatus;

import java.util.List;

@Controller
public class PanelController {

    private OrderRepository orderRepository;

    @Autowired
    public PanelController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/panel/zamowienia")
    public String getOrders(@RequestParam(required = false) OrderStatus status,
                    Model model) {
        List<Order> orders;
        if(status == null) {
            orders = orderRepository.findAll();
        } else {
            orders = orderRepository.findAllByStatus(status);
        }
        model.addAttribute("orders", orders);
        return "panel/orders";
    }
}
