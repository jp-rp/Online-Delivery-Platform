package com.sep.onlinedeliverysystem;

import com.sep.onlinedeliverysystem.domain.dto.*;
import com.sep.onlinedeliverysystem.domain.entities.*;

public final class TestUtil {
    private TestUtil(){

    }

    public static User userBuild1() {
        User userEntity = User.builder()
            .email("luke@trottmail.com")
            .firstName("Luke")
            .lastName("Trott")
            .password("password")
            .build();
        return userEntity;
    }

    public static UserDTO userDTOCreate1() {
        UserDTO userDTO = UserDTO.builder()
                .email("luke2@trottmail.com")
                .firstName("LukeyPookie")
                .lastName("Trottottottott")
                .password("password")
                .build();
        return userDTO;
    }

    public static User userBuild2() {
        User userEntity = User.builder()
                .email("hugh@kiggmail.com")
                .firstName("Hugh")
                .lastName("Kiggell")
                .password("drowssap")
                .build();
        return userEntity;
    }

    public static User userBuild3() {
        User userEntity = User.builder()
                .email("anthony@pricemmail.com")
                .firstName("Anthony")
                .lastName("Price")
                .password("wordpass")
                .build();
        return userEntity;
    }

    public static UserAddress userAddressBuild1(final User userEntity) {
        UserAddress userAddress = UserAddress.builder()
                .id(100L)
                .user(userEntity)
                .street("123 Kiggell Road")
                .city("Bristol")
                .postCode("A12 B34")
                .country("Wales")
                .build();
        return userAddress;
    }

    public static UserAddressDTO userAddressDTOCreate1(final UserDTO userDTO) {
        UserAddressDTO userAddressDTO = UserAddressDTO.builder()
                .id(101L)
                .userDTO(userDTO)
                .street("123 Kiggell Road")
                .city("Bristol")
                .postCode("A12 B34")
                .country("Wales")
                .build();
        return userAddressDTO;
    }

    public static UserAddress userAddressBuild2(final User userEntity) {
        UserAddress userAddress = UserAddress.builder()
                .id(200L)
                .user(userEntity)
                .street("123 Price Road")
                .city("Bristol")
                .postCode("C12 D34")
                .country("Wales")
                .build();
        return userAddress;
    }

    public static UserAddress userAddressBuild3(final User userEntity) {
        UserAddress userAddress = UserAddress.builder()
                .id(300L)
                .user(userEntity)
                .street("123 Li Road")
                .city("Bristol")
                .postCode("E12 F34")
                .country("Wales")
                .build();
        return userAddress;
    }

    public static Vendor vendorBuild1(){
        Vendor vendor = Vendor.builder()
                .email("restaurant@foodmail.com")
                .name("WeSellFood")
                .description("Thai")
                .rating(4.0F)
                .build();
        return vendor;
    }

    public static VendorDTO vendorDTOBuild1(){
        VendorDTO vendorDTO = VendorDTO.builder()
                .email("restaurant2@foodmail.com")
                .name("WeSellFoodDTO")
                .description("Thai")
                .rating(4.0F)
                .build();
        return vendorDTO;
    }

    public static Vendor vendorBuild2(){
        Vendor vendor = Vendor.builder()
                .email("food@foodmail.com")
                .name("Trotty's Kitchen")
                .description("Thai")
                .rating(3.5F)
                .build();
        return vendor;
    }

    public static Vendor vendorBuild3(){
        Vendor vendor = Vendor.builder()
                .email("foodie@foodmail.com")
                .name("ToastLovers")
                .description("Toast")
                .rating(5.0F)
                .build();
        return vendor;
    }

    public static VendorAddress vendorAddressBuild1(final Vendor vendor){
        VendorAddress address = VendorAddress.builder()
                .id(1L)
                .vendor(vendor)
                .street("Food Street")
                .city("Bristol")
                .postCode("W98 Z67")
                .country("United Kingdom")
                .build();
        return address;
    }

    public static VendorAddressDTO vendorAddressDTOCreate1(final Vendor vendor){
        VendorAddressDTO address = VendorAddressDTO.builder()
                .id(1L)
                .vendor(vendor)
                .street("Food Street")
                .city("Bristol")
                .postCode("W98 Z67")
                .country("United Kingdom")
                .build();
        return address;
    }

    public static VendorAddress vendorAddressBuild2(final Vendor vendor){
        VendorAddress address = VendorAddress.builder()
                .id(2L)
                .vendor(vendor)
                .street("Toast Av")
                .city("Bristol")
                .postCode("R98 K67")
                .country("United Kingdom")
                .build();
        return address;
    }

    public static VendorAddress vendorAddressBuild3(final Vendor vendor){
        VendorAddress address = VendorAddress.builder()
                .id(3L)
                .vendor(vendor)
                .street("Tea Street")
                .city("Bristol")
                .postCode("T98 U67")
                .country("United Kingdom")
                .build();
        return address;
    }

    public static MenuItem menuItemBuilder1(final Vendor vendor){
        MenuItem item = MenuItem.builder()
                .id(1000L)
                .name("Toast")
                .description("2 slices of plain toast")
                .price(1.99F)
                .vendor(vendor)
                .build();
        return item;
    }

    public static MenuItemDTO menuItemDTOCreate1(final Vendor vendor){
        MenuItemDTO item = MenuItemDTO.builder()
                .id(1000L)
                .name("Toast")
                .description("2 slices of plain toast")
                .price(1.99F)
                .vendor(vendor)
                .build();
        return item;
    }

    public static MenuItem menuItemBuilder2(final Vendor vendor){
        MenuItem item = MenuItem.builder()
                .id(1001L)
                .name("CS Nachos")
                .description("Chicken, cheese, mild salsa, nachos")
                .price(8.99F)
                .vendor(vendor)
                .build();
        return item;
    }

    public static MenuItem menuItemBuilder3(final Vendor vendor){
        MenuItem item = MenuItem.builder()
                .id(1003L)
                .name("Marmite pasta")
                .description("Marmite!")
                .price(99.99F)
                .vendor(vendor)
                .build();
        return item;
    }
}
