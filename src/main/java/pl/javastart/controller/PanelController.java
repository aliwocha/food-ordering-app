package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.item.Item;
import pl.javastart.order.Order;
import pl.javastart.order.OrderRepository;
import pl.javastart.order.OrderStatus;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/panel/zamowienia/{id}")
    public String getOrderDetails(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> addAttributes(o, model)).orElse("redirect:/");
    }

    @PostMapping("/panel/zamowienia/{id}")
    public String changeOrderStatus(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(o -> {
            o.setStatus(OrderStatus.nextStatus(o.getStatus()));
            orderRepository.save(o);
        });
        return order.map(o -> addAttributes(o, model)).orElse("redirect:/");
    }

    private String addAttributes(Order order, Model model) {
        model.addAttribute("order", order);
        model.addAttribute("sum", order.getItems().stream().mapToDouble(Item::getPrice).sum());
        return "panel/orderDetails";
    }
}
