package pl.javastart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.javastart.common.Message;
import pl.javastart.item.Item;
import pl.javastart.item.ItemRepository;
import pl.javastart.order.ClientOrder;
import pl.javastart.order.Order;
import pl.javastart.order.OrderRepository;

import java.util.Optional;

@Controller
@RequestMapping("/zamowienie")
public class OrderController {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private ClientOrder clientOrder;

    @Autowired
    public OrderController(OrderRepository orderRepository, ItemRepository itemRepository, ClientOrder clientOrder) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.clientOrder = clientOrder;
    }

    @GetMapping
    public String getOrder(Model model) {
        model.addAttribute("order", clientOrder.getOrder());
        model.addAttribute("sum", clientOrder.getOrder().getItems().stream()
                .mapToDouble(Item::getPrice)
                .sum());
        return "order";
    }

    @GetMapping("/dodaj")
    public String addItemToOrder(@RequestParam Long itemId, Model model) {
        Optional<Item> item = itemRepository.findById(itemId);
        item.ifPresent(clientOrder::add);
        if(item.isPresent()) {
            model.addAttribute("message", new Message("Dodano", "Do zamówienia dodano: " + item.get().getName()));
        } else {
            model.addAttribute("message", new Message("Nie dodano", "Podano błędne id z menu: " + itemId));
        }
        return "message";
    }

    @PostMapping("/realizuj")
    public String proceedOrder(@RequestParam String address, @RequestParam String telephone, Model model) {
        Order order = clientOrder.getOrder();
        order.setAddress(address);
        order.setTelephone(telephone);
        orderRepository.save(order);
        clientOrder.clear();
        model.addAttribute("message", new Message("Dziękujemy", "Zamówienie przekazane do realizacji"));
        return "message";
    }
}
