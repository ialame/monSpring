package com.pca;


import com.pca.form.PersonForm;
import com.pca.model.CartePokemon;
import com.pca.model.ExtensionUS;
import com.pca.model.SerieUS;
import com.pca.model.Person;
import com.pca.repository.CartePokemon2Repository;
import com.pca.repository.ExtensionUSRepository;
import com.pca.repository.SerieUSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartePokemon2Repository cartePokemon2Repository;
    @Autowired
    private ExtensionUSRepository extensionUSRepository;
    @Autowired
    private SerieUSRepository serieUSRepository;
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
    private static List<CartePokemon> cartes = new ArrayList<CartePokemon>();
    @GetMapping("/find/{id}")
    public String findCustomerById(@PathVariable Integer id,Model model) {
        ExtensionUS extensionUS = extensionUSRepository.findExtensionUSById(10);
        cartes = extensionUS.getCartes();
        //CartePokemon cp = cartePokemon2Repository.findCartePokemonById(2);
        //cartes.add(cp);
        //System.out.println("\n\n\n"+cp.Recherche+"\n\n\n");
        model.addAttribute("cartes", cartes);
        return "pages/personList";
    }


    @RequestMapping(value = { "/ebay" }, method = RequestMethod.GET)
    public String showJEbayPage(Model model) {

        Iterable<SerieUS> seriesUS = serieUSRepository.findAll();
        //cartes = serieUS.getCartes();
        //CartePokemon cp = cartePokemon2Repository.findCartePokemonById(2);
        //cartes.add(cp);
        //System.out.println("\n\n\n"+cp.Recherche+"\n\n\n");
        model.addAttribute("seriesUS", seriesUS);

        //return "pages/jebay";
        return "ebay";
    }
    @RequestMapping(value = { "/ebaydeux" })
    public String showJEbay2Page(@ModelAttribute SerieUS serieUS, Model model) {
        System.out.println("\n\n\n\n"+serieUS+"\n\n\n\n");
        Iterable<ExtensionUS> extensionsUS = extensionUSRepository.findExtensionUSBySerie(serieUS);
        //cartes = serieUS.getCartes();
        //CartePokemon cp = cartePokemon2Repository.findCartePokemonById(2);
        //cartes.add(cp);
        //System.out.println("\n\n\n"+cp.Recherche+"\n\n\n");
        model.addAttribute("extensionsUS", extensionsUS);

        //return "pages/jebay";
        return "redirect:ebay";
    }




    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    // Injectez (inject) via application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    private static List<Person> persons = new ArrayList<Person>();

    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }



    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {

        model.addAttribute("persons", persons);

        return "personList";
    }


    @RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
    //@ResponseBody
    public String index(Model model) {
        model.addAttribute("message", message);
        return "pages/index"; //"pages/index"
    }


    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName);
            persons.add(newPerson);

            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }
}
