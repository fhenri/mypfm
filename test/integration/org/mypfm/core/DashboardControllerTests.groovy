package org.mypfm.core

import grails.plugin.springsecurity.SpringSecurityUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mypfm.user.User
import org.springframework.security.core.context.SecurityContextHolder

import static java.util.UUID.randomUUID

class DashboardControllerTests extends GroovyTestCase {

    def testUser

    @Before
    void setUp() {
        super.setUp()
        testUser = new User(username: 'test', password: 'changeit')
        testUser.realmId = randomUUID() as String
        testUser.save()
    }

    @After
    void tearDown() {
        super.tearDown()
        SecurityContextHolder.clearContext()
    }

    @Test
    void testList() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {
            def model = controller.list()
            assert model.bankInstanceList.size() == 0
            assert model.bankInstanceTotal == 0
            assert model.accountInstanceList.size() == 0
            assert model.accountInstanceTotal == 0
        }
    }

    @Test
    void testCreateBank() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {
            def model = controller.createBank()
            assert model.bankInstance != null
        }
    }

    @Test
    void testSaveBank() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {
            controller.saveBank()
            def model = controller.modelAndView.model.bankInstance
            def view = controller.modelAndView.viewName
            assert model != null
            assert view == '/dashboard/createBank'
        }
    }

    @Test
    void testSaveNoIbanBank() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {

            controller.params.bankName = 'bank test'
            controller.params.iBanCode = 'FR12345678'
            controller.params.webPage = 'www.banktest.com'
            controller.saveBank()

            assert controller.flash.message == null
            assert controller.modelAndView.viewName == '/dashboard/createBank'
            assert Bank.count() == 0
        }
    }

    @Test
    void testSaveNewBank() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {

            controller.params.bankName = 'bank test'
            controller.params.iBanCode = 'FR123456789012345678901234567'
            controller.params.webPage = 'http://www.banktest.com'
            controller.saveBank()

            assert controller.flash.message == null
            assert Bank.count() == 1
        }
    }

    @Test
    void testShowBank() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {
            def bank = new Bank(bankName:'bank')
            bank.realmId = testUser.realmId
            assert bank.save() != null

            controller.params.id = bank.id
            def model = controller.showBank()
            //def model = controller.modelAndView.model.bankInstance
            assert model.bankInstance == bank
        }
    }

    @Test
    void testEdit() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {
            def bank = new Bank(bankName:'bank')
            bank.realmId = testUser.realmId
            assert bank.save() != null

            controller.params.id = bank.id

            def model = controller.edit()

            assert model.bankInstance == bank
        }
    }

    @Test
    void testDelete() {
        def controller = new DashboardController()
        SpringSecurityUtils.doWithAuth("test") {
            def bank = new Bank(bankName:'bank')
            bank.realmId = testUser.realmId
            assert bank.save() != null
            assert Bank.count() == 1

            controller.params.id = bank.id
            controller.delete()

            assert Bank.count() == 0
            assert Bank.get(bank.id) == null
            //assert controller.response.redirectedUrl == '/dashboard/list'
        }
    }
}
