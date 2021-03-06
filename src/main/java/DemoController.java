package com.pca;


import com.pca.entity.CartePokemon;
import com.pca.repository.CartePokemon2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartePokemon2Repository cartePokemon2Repository;
    @PostMapping("/add")
    public String addCustomer(@RequestParam String first, @RequestParam String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        return "Added new customer to repo!";
    }

    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")

    public CartePokemon findCustomerById(@PathVariable Integer id) {
        CartePokemon cp2 = cartePokemon2Repository.findCartePokemonById(id);
        return cartePokemon2Repository.findCartePokemonById(id);
    }
}
