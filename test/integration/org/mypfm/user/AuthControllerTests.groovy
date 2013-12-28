package org.mypfm.user

import grails.plugin.springsecurity.SpringSecurityUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.security.core.context.SecurityContextHolder

import static java.util.UUID.randomUUID

class AuthControllerTests extends GroovyTestCase {

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
    void testUpdatePasswordNoPassword() {
        def authController = new AuthController()
        authController.doUpdatePassword()
        assertEquals "error", authController.flash.status
    }

    @Test
    void testUpdatePasswordNoIdenticalPassword() {
        def authController = new AuthController()
        authController.params.password1 = 'NewPassword1'
        authController.params.password2 = 'NewPassword2'
        authController.doUpdatePassword()
        assertEquals "error", authController.flash.status
        assertEquals "Please enter same passwords.", authController.flash.message
    }

    @Test
    void testUpdatePasswordUnknownUser() {
        def authController = new AuthController()
        authController.params.password1 = 'NewPassword'
        authController.params.password2 = 'NewPassword'
        authController.doUpdatePassword()
        assertEquals "error", authController.flash.status
        assertEquals "Unknown user.", authController.flash.message
    }

    @Test
    void testUpdatePassword() {
        def authController = new AuthController()
        authController.params.password1 = 'NewPassword'
        authController.params.password2 = 'NewPassword'
        SpringSecurityUtils.doWithAuth("test") {
            authController.doUpdatePassword()
            assertEquals "Password successfully updated", authController.flash.message
        }
    }
}
