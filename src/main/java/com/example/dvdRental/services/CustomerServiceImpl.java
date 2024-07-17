package com.example.dvdRental.services;

import com.example.dvdRental.api.mapper.CustomerMapper;
import com.example.dvdRental.api.model.*;
import com.example.dvdRental.api.model.post.PostCustomerDTO;
import com.example.dvdRental.converters.CustomerConverter;
import com.example.dvdRental.exceptions.DisposableEmailException;
import com.example.dvdRental.exceptions.DuplicateDataException;
import com.example.dvdRental.exceptions.InvalidDataException;
import com.example.dvdRental.exceptions.NotFoundException;
import com.example.dvdRental.model.*;
import com.example.dvdRental.util.responses.DisifyResponse;
import com.example.dvdRental.repositories.AddressRepository;
import com.example.dvdRental.repositories.CustomerRepository;
import com.example.dvdRental.repositories.StoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    private final RestTemplate restTemplate;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            StoreRepository storeRepository,
            AddressRepository addressRepository,
            CustomerMapper customerMapper,
            RestTemplate restTemplate
    ) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.addressRepository = addressRepository;
        this.customerMapper = customerMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(CustomerConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> findCustomerById(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) return optionalCustomer.map(CustomerConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public List<CustomerInfoDTO> findAllCustomersInfo(Pageable pageable) {
        return customerRepository
                .findAll(pageable)
                .stream()
                .map(this::mapToCustomerInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerInfoDTO findCustomerInfoById(Integer customerId) throws NotFoundException {
        Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerId(customerId);

        if (customerOptional.isEmpty()) {
            throw new NotFoundException("CustomerId", customerId);
        }

        return this.mapToCustomerInfoDto(customerOptional.get());
    }

    @Override
    public List<CustomerInfoDTO> findAllCustomerByActiveBoolAndSearch(
            Boolean activeBool,
            String searchTerm,
            Pageable pageable
    ) {

        Pageable modifiedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                mapSortOrders(pageable.getSort())
        );

        Page<Customer> customersPage = customerRepository
                .findCustomersBySearchTermAndActiveBool(
                        activeBool,
                        searchTerm,
                        modifiedPageable
                );

        List<Customer> customers = customersPage.getContent();

        return customers
                .stream()
                .map(this::mapToCustomerInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createNewCustomer(PostCustomerDTO postCustomerDTO) throws InvalidDataException, DuplicateDataException, NotFoundException, DisposableEmailException {
        String email = postCustomerDTO.getEmail();

        DisifyResponse disifyResponse = this.checkEmail(email);

        if (disifyResponse.isDisposable()) {
            throw new DisposableEmailException(email, "Customer");
        }

        Integer storeId = postCustomerDTO.getStoreId();
        Integer addressId = postCustomerDTO.getAddressId();

        Optional<Store> storeOptional = storeRepository.findById(storeId);
        Optional<Address> addressOptional = addressRepository.findById(addressId);

        String firstName = postCustomerDTO.getFirstName();
        String lastName = postCustomerDTO.getLastName();

        if (!(firstName.matches("^[a-zA-Z]*$"))) {
            throw new InvalidDataException("firstName");
        }

        if (!(lastName.matches("^[a-zA-Z]*$"))) {
            throw new InvalidDataException("lastName");
        }

        if (addressOptional.isEmpty()) {
            throw new NotFoundException("Address", addressId);
        }

        if (storeOptional.isEmpty()) {
            throw new NotFoundException("Store", storeId);
        }

        if (customerRepository.findCustomerByEmail(postCustomerDTO.getEmail()).isPresent()) {
            throw new DuplicateDataException(
                    "Customer",
                    "Email",
                    email,
                    postCustomerDTO.getAddressId()
            );
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(postCustomerDTO.getEmail());
        customer.setActiveBool(postCustomerDTO.getActivebool());
//        customer.setCreateDate(postCustomerDTO.getCreateDate());
        customer.setActive(postCustomerDTO.getActive());
        customer.setStore(storeOptional.get());
        customer.setAddress(addressOptional.get());

        customerRepository.save(customer);

        return CustomerConverter.toDTO(customer);

    }

    @Override
    public CustomerDTO updateCustomer(Integer customerId, PostCustomerDTO postCustomerDTO) throws NotFoundException, InvalidDataException {
        Integer storeId = postCustomerDTO.getStoreId();
        Integer addressId = postCustomerDTO.getAddressId();

        Optional<Store> storeOptional = storeRepository.findById(storeId);
        Optional<Address> addressOptional = addressRepository.findById(addressId);

        String firstName = postCustomerDTO.getFirstName();
        String lastName = postCustomerDTO.getLastName();

        if (!(firstName.matches("^[a-zA-Z]*$"))) {
            throw new InvalidDataException("firstName");
        }

        if (!(lastName.matches("^[a-zA-Z]*$"))) {
            throw new InvalidDataException("lastName");
        }

        if (addressOptional.isEmpty()) {
            throw new NotFoundException("Address", addressId);
        }

        if (storeOptional.isEmpty()) {
            throw new NotFoundException("Store", storeId);
        }
        System.out.println("ID: " + customerId);
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(postCustomerDTO.getEmail());
        customer.setActiveBool(postCustomerDTO.getActivebool());
//        customer.setCreateDate(postCustomerDTO.getCreateDate());
        customer.setActive(postCustomerDTO.getActive());
        customer.setStore(storeOptional.get());
        customer.setAddress(addressOptional.get());

        customerRepository.save(customer);

        return CustomerConverter.toDTO(customer);

    }

    @Override
    public void deleteCustomer(Integer customerId) throws NotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isEmpty()) {
            throw new NotFoundException("Customer", customerId);
        } else {
            customerRepository.deleteById(customerId);
        }
    }

    private CustomerInfoDTO mapToCustomerInfoDto(Customer customer) {

        Address address = customer.getAddress();
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();

        customerInfoDTO.setFirstName(customer.getFirstName());
        customerInfoDTO.setLastName(customer.getLastName());
        customerInfoDTO.setEmail(customer.getEmail());
        customerInfoDTO.setAddressInfoDTO(this.mapToAddressInfoDto(address));

        return customerInfoDTO;
    }

    private AddressInfoDTO mapToAddressInfoDto(Address address) {
        City city = address.getCity();
        AddressInfoDTO addressInfoDTO = new AddressInfoDTO();

        addressInfoDTO.setAddress(address.getAddress());
        addressInfoDTO.setAddress2(address.getAddress2());
        addressInfoDTO.setDistrict(address.getDistrict());
        addressInfoDTO.setPostalCode(address.getPostalCode());
        addressInfoDTO.setPhone(address.getPhone());
        addressInfoDTO.setCityInfo(this.mapToCityInfoDto(city));

        return addressInfoDTO;
    }

    private CityInfoDTO mapToCityInfoDto(City city) {
        Country country = city.getCountry();
        CityInfoDTO cityInfoDTO = new CityInfoDTO();

        cityInfoDTO.setCity(city.getCity());
        cityInfoDTO.setCountryInfo(this.mapToCountryDto(country));

        return cityInfoDTO;
    }

    private CountryInfoDTO mapToCountryDto(Country country) {
        CountryInfoDTO countryInfoDTO = new CountryInfoDTO();

        countryInfoDTO.setCountry(country.getCountry());

        return countryInfoDTO;
    }

    public Customer mapToCustomerEntity(CustomerInfoDTO customerInfoDTO) {
        Customer customer = new Customer();

        customer.setFirstName(customerInfoDTO.getFirstName());
        customer.setLastName(customerInfoDTO.getLastName());
        customer.setEmail(customerInfoDTO.getEmail());
        customer.setAddress(mapToAddressEntity(customerInfoDTO.getAddressInfoDTO()));

        return customer;
    }

    private Address mapToAddressEntity(AddressInfoDTO addressInfoDTO) {
        Address address = new Address();

        address.setAddress(addressInfoDTO.getAddress());
        address.setAddress2(addressInfoDTO.getAddress2());
        address.setDistrict(addressInfoDTO.getDistrict());
        address.setPostalCode(addressInfoDTO.getPostalCode());
        address.setPhone(addressInfoDTO.getPhone());
        address.setCity(mapToCityEntity(addressInfoDTO.getCityInfo()));

        return address;
    }

    private City mapToCityEntity(CityInfoDTO cityInfoDTO) {
        City city = new City();

        city.setCity(cityInfoDTO.getCity());
        city.setCountry(mapToCountryEntity(cityInfoDTO.getCountryInfo()));

        return city;
    }

    private Country mapToCountryEntity(CountryInfoDTO countryInfoDTO) {
        Country country = new Country();

        country.setCountry(countryInfoDTO.getCountry());

        return country;
    }


    private Sort mapSortOrders(Sort sort) {
        List<Sort.Order> orders = sort
                .stream()
                .map(order -> new Sort.Order(order.getDirection(), this.propertyMapper(order)))
                .toList();

        return Sort.by(orders);
    }

    private String propertyMapper(Sort.Order order) {
        String field = order.getProperty();
        return switch (field) {
            case "address" -> "address.address";
            case "address2" -> "address.address2";
            case "district" -> "address.district";
            case "postalCode" -> "address.postalCode";
            case "phone" -> "address.phone";
            case "city" -> "address.city.city";
            case "country" -> "address.city.country.country";
            default -> order.getProperty();
        };
    }

    public DisifyResponse checkEmail(String email) {
        String url = "https://www.disify.com/api/email/" + email;
        ResponseEntity<DisifyResponse> response = restTemplate.getForEntity(url, DisifyResponse.class);
        return response.getBody();
    }
}
