package com.example.dvdRental.services;

import com.example.dvdRental.DvdRentalApplication;
import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.CustomerConverter;
import com.example.dvdRental.converters.StoreConverter;
import com.example.dvdRental.exceptions.DuplicateDataException;
import com.example.dvdRental.exceptions.InvalidDataException;
import com.example.dvdRental.exceptions.NotFoundException;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Store;
import com.example.dvdRental.repositories.AddressRepository;
import com.example.dvdRental.repositories.CustomerRepository;
import com.example.dvdRental.repositories.StoreRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional
@SpringBootTest(classes = DvdRentalApplication.class)
public class CustomerServiceTest {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private static final String email = "janek12345678.kowalski@gmail.com";
    private static final String emailToChange = "sampleemail12@gmail.com";

    private static PostCustomerDTO postCustomerDTO;

    @Autowired
    public CustomerServiceTest(
            CustomerService customerService,
            CustomerRepository customerRepository,
            StoreRepository storeRepository,
            AddressRepository addressRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.addressRepository = addressRepository;
    }

//    @BeforeAll
//    public static void setUp() {
//        postCustomerDTO = new PostCustomerDTO();
//        postCustomerDTO.setStoreId(1);
//        postCustomerDTO.setFirstName("Jano");
//        postCustomerDTO.setLastName("Kowalski");
//        postCustomerDTO.setEmail(email);
//        postCustomerDTO.setAddressId(2);
//        postCustomerDTO.setActivebool(false);
//        postCustomerDTO.setActive(1);
//    }

    @AfterEach
    public void removeCustomerRecord() {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
        customerOptional.ifPresent(customer -> {
            try {
                customerService.deleteCustomer(customer.getCustomerId());
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    void shouldConvertCustomerEntityToDTO() {
        Integer storeId = 1;
        Integer addressId = 2;

        Optional<Store> storeOptional = storeRepository.findById(storeId);
        Optional<Address> addressOptional = addressRepository.findById(addressId);

        Customer customer = new Customer();

        if (storeOptional.isPresent()) {
            Store store = storeOptional.get();
            customer.setStore(store);
        } else {
            assertNotNull(storeOptional, "Store does not exist!");
        }

        customer.setFirstName("Janek");
        customer.setLastName("Kowalski");
        customer.setEmail(email);

        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            customer.setAddress(address);
        } else {
            assertNotNull(addressOptional, "Address does not exist!");
        }

        customer.setActiveBool(true);
        customer.setActive(0);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO customerDTO = CustomerConverter.toDTO(customer);

        assertEquals(savedCustomer.getCustomerId(), customerDTO.getCustomerId());
        assertEquals("Janek", customerDTO.getFirstName());
        assertEquals("Kowalski", customerDTO.getLastName());
        assertEquals(email, customerDTO.getEmail());
        assertEquals(true, customerDTO.getActivebool());
        assertEquals(0, customerDTO.getActive());
        assertEquals(savedCustomer.getCreateDate(), customerDTO.getCreateDate());
        assertEquals(savedCustomer.getLastUpdate(), customerDTO.getLastUpdate());
    }

    @Test
    void shouldConvertCustomerDTOToEntity() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Janek");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            CustomerDTO createdCustomerDTO = customerService.createNewCustomer(postCustomerDTO);
            Customer customer = CustomerConverter.toEntity(createdCustomerDTO);
            assertEquals(createdCustomerDTO.getCustomerId(), customer.getCustomerId());
            assertEquals(1, customer.getStore().getStoreId());
            assertEquals("Janek", customer.getFirstName());
            assertEquals("Kowalski", customer.getLastName());
            assertEquals(email, customer.getEmail());
            assertEquals(2, customer.getAddress().getAddressId());
            assertEquals(false, customer.getActiveBool());
            assertEquals(1, customer.getActive());
            assertEquals(createdCustomerDTO.getCreateDate(), customer.getCreateDate());
            assertEquals(createdCustomerDTO.getLastUpdate(), customer.getLastUpdate());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldCreateCustomer_withValidName() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            CustomerDTO createdCustomer = customerService.createNewCustomer(postCustomerDTO);
            assertNotNull(createdCustomer.getCustomerId(), "Customer ID should not be null");

            Optional<Store> storeDTOOptional = storeRepository.findById(1);
            if (storeDTOOptional.isPresent()) {
                StoreDTO storeDTO = StoreConverter.toDTO(storeDTOOptional.get());
                assertEquals(storeDTO, createdCustomer.getStore());
            } else {
                assertNotNull(storeDTOOptional, "Store does not exist!");
            }

            assertEquals("Jano", createdCustomer.getFirstName());
            assertEquals("Kowalski", createdCustomer.getLastName());
            assertEquals(email, createdCustomer.getEmail());

            Optional<Address> addressDTOOptional = addressRepository.findById(2);
            if (addressDTOOptional.isPresent()) {
                AddressDTO addressDTO = AddressConverter.toDTO(addressDTOOptional.get());
                assertEquals(addressDTO, createdCustomer.getAddress());
            } else {
                assertNotNull(addressDTOOptional, "Address does not exist!");
            }

            assertEquals(false, createdCustomer.getActivebool());
            assertEquals(1, createdCustomer.getActive());
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void shouldThrowInvalidDataException_whenFirstNameContainsSpecialCharacters() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano@!");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        assertThrows(InvalidDataException.class, () -> customerService.createNewCustomer(postCustomerDTO));
    }

    @Test
    void shouldThrowInvalidDataException_whenLastNameContainsSpecialCharacters() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowals1ki");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        assertThrows(InvalidDataException.class, () -> customerService.createNewCustomer(postCustomerDTO));
    }

    @Test
    void shouldThrowNotFoundException_whenAddressDoesNotExist() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2432142);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        assertThrows(NotFoundException.class, () -> customerService.createNewCustomer(postCustomerDTO));
    }

    @Test
    void shouldThrowNotFoundException_whenStoreDoesNotExist() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1321421);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        assertThrows(NotFoundException.class, () -> customerService.createNewCustomer(postCustomerDTO));
    }

    @Test
    void shouldThrowDuplicateDataException_whenEmailIsDuplicated() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(email);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        PostCustomerDTO postCustomerDTO2 = new PostCustomerDTO();
        postCustomerDTO2.setStoreId(1);
        postCustomerDTO2.setFirstName("Janooo");
        postCustomerDTO2.setLastName("Kowalskiii");
        postCustomerDTO2.setEmail(email);
        postCustomerDTO2.setAddressId(5);
        postCustomerDTO2.setActivebool(false);
        postCustomerDTO2.setActive(1);

        assertThrows(DuplicateDataException.class, () -> customerService.createNewCustomer(postCustomerDTO2));
    }

    @Test
    void shouldUpdateCustomer_withValidData() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            PostCustomerDTO newPostCustomerDTO = new PostCustomerDTO();
            newPostCustomerDTO.setStoreId(2);
            newPostCustomerDTO.setFirstName("Janek");
            newPostCustomerDTO.setLastName("Kowalskiii");
            newPostCustomerDTO.setEmail(email);
            newPostCustomerDTO.setAddressId(10);
            newPostCustomerDTO.setActivebool(true);
            newPostCustomerDTO.setActive(0);

            try {
                CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, newPostCustomerDTO);

                assertNotNull(updatedCustomer.getCustomerId(), "Customer ID should not be null");
                assertEquals(customerId, updatedCustomer.getCustomerId());

                Optional<Store> storeDTOOptional = storeRepository.findById(2);
                if (storeDTOOptional.isPresent()) {
                    StoreDTO storeDTO = StoreConverter.toDTO(storeDTOOptional.get());
                    assertEquals(storeDTO, updatedCustomer.getStore());
                } else {
                    assertNotNull(storeDTOOptional, "Store does not exist!");
                }

                assertEquals("Janek", updatedCustomer.getFirstName());
                assertEquals("Kowalskiii", updatedCustomer.getLastName());
                assertEquals(email, updatedCustomer.getEmail());

                Optional<Address> addressDTOOptional = addressRepository.findById(10);
                if (addressDTOOptional.isPresent()) {
                    AddressDTO addressDTO = AddressConverter.toDTO(addressDTOOptional.get());
                    assertEquals(addressDTO, updatedCustomer.getAddress());
                } else {
                    assertNotNull(addressDTOOptional, "Address does not exist!");
                }

                assertEquals(true, updatedCustomer.getActivebool());
                assertEquals(0, updatedCustomer.getActive());
            } catch (Exception e) {
                fail();
            }
        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }

    }

    @Test
    void shouldThrowInvalidDataExceptionOnUpdateCustomer_whenFirstNameContainsSpecialCharacters() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            PostCustomerDTO newPostCustomerDTO = new PostCustomerDTO();
            newPostCustomerDTO.setStoreId(2);
            newPostCustomerDTO.setFirstName("Janek1");
            newPostCustomerDTO.setLastName("Kowalskiii");
            newPostCustomerDTO.setEmail(email);
            newPostCustomerDTO.setAddressId(10);
            newPostCustomerDTO.setActivebool(true);
            newPostCustomerDTO.setActive(0);

            assertThrows(InvalidDataException.class, () -> customerService.updateCustomer(customerId, newPostCustomerDTO));

            customerOptional.ifPresent(customer -> {
                try {
                    customerService.deleteCustomer(customer.getCustomerId());
                } catch (Exception e) {
                    fail();
                }
            });
        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }
    }

    @Test
    void shouldThrowInvalidDataExceptionOnUpdateCustomer_whenLastNameContainsSpecialCharacters() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            PostCustomerDTO newPostCustomerDTO = new PostCustomerDTO();
            newPostCustomerDTO.setStoreId(2);
            newPostCustomerDTO.setFirstName("Janek");
            newPostCustomerDTO.setLastName("Kowalsk!");
            newPostCustomerDTO.setEmail(email);
            newPostCustomerDTO.setAddressId(10);
            newPostCustomerDTO.setActivebool(true);
            newPostCustomerDTO.setActive(0);

            assertThrows(InvalidDataException.class, () -> customerService.updateCustomer(customerId, newPostCustomerDTO));

            customerOptional.ifPresent(customer -> {
                try {
                    customerService.deleteCustomer(customer.getCustomerId());
                } catch (Exception e) {
                    fail();
                }
            });
        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }
    }

    @Test
    void shouldThrowNotFoundExceptionOnUpdateCustomer_whenAddressDoesNotExist() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            PostCustomerDTO newPostCustomerDTO = new PostCustomerDTO();
            newPostCustomerDTO.setStoreId(2);
            newPostCustomerDTO.setFirstName("Janek");
            newPostCustomerDTO.setLastName("Kowalskiii");
            newPostCustomerDTO.setEmail(email);
            newPostCustomerDTO.setAddressId(0);
            newPostCustomerDTO.setActivebool(true);
            newPostCustomerDTO.setActive(0);

            assertThrows(NotFoundException.class, () -> customerService.updateCustomer(customerId, newPostCustomerDTO));

            customerOptional.ifPresent(customer -> {
                try {
                    customerService.deleteCustomer(customer.getCustomerId());
                } catch (Exception e) {
                    fail();
                }
            });
        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }
    }

    @Test
    void shouldThrowNotFoundExceptionOnUpdateCustomer_whenStoreDoesNotExist() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            PostCustomerDTO newPostCustomerDTO = new PostCustomerDTO();
            newPostCustomerDTO.setStoreId(0);
            newPostCustomerDTO.setFirstName("Janek");
            newPostCustomerDTO.setLastName("Kowalskiii");
            newPostCustomerDTO.setEmail(email);
            newPostCustomerDTO.setAddressId(10);
            newPostCustomerDTO.setActivebool(true);
            newPostCustomerDTO.setActive(0);

            assertThrows(NotFoundException.class, () -> customerService.updateCustomer(customerId, newPostCustomerDTO));

            customerOptional.ifPresent(customer -> {
                try {
                    customerService.deleteCustomer(customer.getCustomerId());
                } catch (Exception e) {
                    fail();
                }
            });
        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }
    }

    @Test
    void shouldRemoveCustomer() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            try {
                customerService.deleteCustomer(customerId);
            } catch (Exception e) {
                fail();
            }

            Optional<Customer> createdCustomerOptional = customerRepository.findCustomerByCustomerId(customerId);

            assertEquals(Optional.empty(), createdCustomerOptional);

        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }

    }

    @Test
    void shouldThrowNotFoundExceptionOnUpdateCustomer_whenCustomerDoesNotExist() {
        PostCustomerDTO postCustomerDTO = new PostCustomerDTO();
        postCustomerDTO.setStoreId(1);
        postCustomerDTO.setFirstName("Jano");
        postCustomerDTO.setLastName("Kowalski");
        postCustomerDTO.setEmail(emailToChange);
        postCustomerDTO.setAddressId(2);
        postCustomerDTO.setActivebool(false);
        postCustomerDTO.setActive(1);

        try {
            customerService.createNewCustomer(postCustomerDTO);
        } catch (Exception e) {
            fail();
        }

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(emailToChange);

        if (customerOptional.isPresent()) {
            Integer customerId = customerOptional.get().getCustomerId();
            System.out.println("ID: " + customerId);

            try {
                customerService.deleteCustomer(customerId);
                assertThrows(NotFoundException.class, () -> customerService.deleteCustomer(customerId));
            } catch (Exception e) {
                fail();
            }


//
//            Optional<Customer> createdCustomerOptional = customerRepository.findCustomerByCustomerId(customerId);
//
//            assertEquals(Optional.empty(), createdCustomerOptional);

        } else {
            assertNotNull(customerOptional, "Customer does not exist!");
        }



    }
}