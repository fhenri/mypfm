package org.mypfm.core
import grails.plugin.springsecurity.SpringSecurityUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mypfm.user.User
import org.springframework.mock.web.MockMultipartFile
import org.springframework.mock.web.MockMultipartHttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder

import static java.util.UUID.randomUUID

class TransactionControllerTests extends GroovyTestCase {

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
    void testUploadNoOFXFile() {
        def controller = new TransactionController()
        SpringSecurityUtils.doWithAuth("test") {
            controller.metaClass.request = new MockMultipartHttpServletRequest()
            def fileContentBytes = '14004' as byte[]
            controller.request.addFile(new MockMultipartFile("test/integration/resources/files/comptes.cfx", fileContentBytes))
            controller.upload()
            assertEquals "only support ofx file", controller.flash.message
        }
    }

    @Test
    void testUploadOFXFile() {
        def controller = new TransactionController()
        SpringSecurityUtils.doWithAuth("test") {
            controller.metaClass.request = new MockMultipartHttpServletRequest()
            def fileContentBytes = '14004' as byte[]
            controller.request.addFile(new MockMultipartFile("test/integration/resources/files/comptes.ofx", fileContentBytes))
            controller.upload()
            assertEquals "ofx File imported successfully", controller.flash.message
            assert Transaction.count() == 55
            assert Account.count() == 5
        }
    }
}
