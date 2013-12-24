package org.mypfm.core

/**
 *
 * @author fhenri
 */
class TransactionController {

    def springSecurityService

    static allowedMethods = [upload: "POST"]
    static defaultAction = 'list'

    def list(Integer max) {

        params.max = Math.min(params.max ? params.int('max') : 15, 100)
        def realmId = springSecurityService.currentUser.realmId

        def query
        if (params.sort) {
            query = {
                like("realmId", realmId)
                order(params.sort, params.order)
            }
        } else {
            query = {
                like("realmId", realmId)
            }
        }
        def results = Transaction.createCriteria().list(query, max: params.max, offset: params.offset)
        [transactionList: results, transactionTotal: results.totalCount]

    }
    
    def transactionService
    def upload() {
        def sourceFile = request.getFile('bankFile')
        
        if (sourceFile.empty) {
            flash.message = 'File cannot be empty'
        } 
        // only support ofx file for now
        else if (sourceFile.originalFilename.endsWith("ofx")) {
            def realmId = springSecurityService.currentUser.realmId
            def countTransaction = transactionService.importOFXStatement(sourceFile, realmId)
            flash.message = 'ofx File imported successfully: ' + countTransaction + ' transaction(s) loaded'
        }  else {
            flash.message = "only support ofx file"
        }
        
        redirect(action: "list", params: params)
    }

    def show(Long id) {
        def transactionInstance = Transaction.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), id])
            redirect(action: "list")
            return
        }

        [transactionUpdate: transactionInstance]
    }

    def update(Long id, Long version) {
        def transactionInstance = Transaction.get(id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (transactionInstance.version > version) {
                transactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'transaction.label', default: 'Transaction')] as Object[],
                        "Another user has updated this Transaction while you were editing")
                redirect(action: "list")
                return
            }
        }

        transactionInstance.properties = params

        if (!transactionInstance.save()) {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
            redirect(action: "list")
            return
        }

        redirect(action: "list")
    }

}