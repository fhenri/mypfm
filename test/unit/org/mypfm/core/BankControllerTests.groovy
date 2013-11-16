package org.mypfm.core



import org.junit.*
import grails.test.mixin.*

@TestFor(BankController)
@Mock(Bank)
class BankControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bank/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bankInstanceList.size() == 0
        assert model.bankInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bankInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bankInstance != null
        assert view == '/bank/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bank/show/1'
        assert controller.flash.message != null
        assert Bank.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bank/list'

        populateValidParams(params)
        def bank = new Bank(params)

        assert bank.save() != null

        params.id = bank.id

        def model = controller.show()

        assert model.bankInstance == bank
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bank/list'

        populateValidParams(params)
        def bank = new Bank(params)

        assert bank.save() != null

        params.id = bank.id

        def model = controller.edit()

        assert model.bankInstance == bank
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bank/list'

        response.reset()

        populateValidParams(params)
        def bank = new Bank(params)

        assert bank.save() != null

        // test invalid parameters in update
        params.id = bank.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bank/edit"
        assert model.bankInstance != null

        bank.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bank/show/$bank.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bank.clearErrors()

        populateValidParams(params)
        params.id = bank.id
        params.version = -1
        controller.update()

        assert view == "/bank/edit"
        assert model.bankInstance != null
        assert model.bankInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bank/list'

        response.reset()

        populateValidParams(params)
        def bank = new Bank(params)

        assert bank.save() != null
        assert Bank.count() == 1

        params.id = bank.id

        controller.delete()

        assert Bank.count() == 0
        assert Bank.get(bank.id) == null
        assert response.redirectedUrl == '/bank/list'
    }
}
