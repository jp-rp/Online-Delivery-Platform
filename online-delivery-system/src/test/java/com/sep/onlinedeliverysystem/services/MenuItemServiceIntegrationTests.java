package com.sep.onlinedeliverysystem.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sep.onlinedeliverysystem.TestUtil;
import com.sep.onlinedeliverysystem.domain.entities.MenuItem;
import com.sep.onlinedeliverysystem.domain.entities.Vendor;
import com.sep.onlinedeliverysystem.domain.entities.Order;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MenuItemServiceIntegrationTests {

    private MenuItemService menuItemService;
    private VendorService vendorService;
    private OrderService orderService;

    @Autowired
    public MenuItemServiceIntegrationTests(MenuItemService menuItemService, VendorService vendorService) {
        this.menuItemService = menuItemService;
        this.vendorService = vendorService;
    }

    @Test
    public void testFindAll() {
        Vendor vendor = TestUtil.vendorBuild1();
        vendorService.save(vendor);
        MenuItem menuItem1 = TestUtil.menuItemBuilder1(vendor);
        MenuItem menuItem2 = TestUtil.menuItemBuilder2(vendor);
        menuItemService.save(menuItem1);
        menuItemService.save(menuItem2);

        List<MenuItem> menuItems = menuItemService.findAll();

        assertThat(menuItems).containsExactly(menuItem1, menuItem2);
    }

    @Test
    public void testFindByVendorEmail() {
        Vendor vendor = TestUtil.vendorBuild1();
        vendorService.save(vendor);
        MenuItem menuItem1 = TestUtil.menuItemBuilder1(vendor);
        MenuItem menuItem2 = TestUtil.menuItemBuilder2(vendor);
        menuItemService.save(menuItem1);
        menuItemService.save(menuItem2);

        List<MenuItem> menuItems = menuItemService.findByVendorEmail(vendor.getEmail());

        assertThat(menuItems).containsExactly(menuItem1, menuItem2);
    }

    @Test
    public void testFindByDeleteIsFalse() {
        Vendor vendor = TestUtil.vendorBuild1();
        vendorService.save(vendor);
        MenuItem menuItem1 = TestUtil.menuItemBuilder1(vendor);
        MenuItem menuItem2 = TestUtil.menuItemBuilder2(vendor);
        menuItemService.save(menuItem1);
        menuItemService.save(menuItem2);

        List<MenuItem> menuItems = menuItemService.findByDeleteIsFalse();

        assertThat(menuItems).containsExactly(menuItem1, menuItem2);
    }

    @Test
    public void testFindByDeleteIsTrue() {
        Vendor vendor = TestUtil.vendorBuild1();
        vendorService.save(vendor);
        MenuItem menuItem1 = TestUtil.menuItemBuilder1(vendor);
        MenuItem menuItem2 = TestUtil.menuItemBuilder2(vendor);
        menuItem1.setDelete(true);
        menuItem2.setDelete(true);
        menuItemService.save(menuItem1);
        menuItemService.save(menuItem2);

        List<MenuItem> menuItems = menuItemService.findByDeleteIsTrue();

        assertThat(menuItems).containsExactly(menuItem1, menuItem2);
    }

    @Test
    public void testFindByDeleteIsFalseAndVendorEmail() {
        Vendor vendor = TestUtil.vendorBuild1();
        vendorService.save(vendor);
        MenuItem menuItem1 = TestUtil.menuItemBuilder1(vendor);
        MenuItem menuItem2 = TestUtil.menuItemBuilder2(vendor);
        menuItemService.save(menuItem1);
        menuItemService.save(menuItem2);

        List<MenuItem> menuItems = menuItemService.findByDeleteIsFalseAndVendorEmail(vendor.getEmail());

        assertThat(menuItems).containsExactly(menuItem1, menuItem2);
    }

}
