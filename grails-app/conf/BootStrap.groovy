import org.apache.shiro.crypto.hash.Sha512Hash
import org.mypfm.core.Category
import org.mypfm.user.ShiroUser
import org.mypfm.user.ShiroRole

import static java.util.UUID.randomUUID

class BootStrap {

    def shiroSecurityService

    def init = { servletContext ->
        //This code is only for demo purposes
        def adminRole = ShiroRole.findByName("Administrator")
        if(!adminRole){
            adminRole = new ShiroRole(name: 'Administrator')
            adminRole.save(failOnError:true)
        }
        def userRole = ShiroRole.findByName("User")
        if(!userRole){
            userRole = new ShiroRole(name: 'User')
            userRole.save(failOnError:true)
        }
        def admin = ShiroUser.findByUsername('admin')
        if(!admin){
            def hash = new Sha512Hash("changeit").toHex()
            admin = new ShiroUser(firstName:"Administator", lastName:"User",
                    username: 'admin', email:"me@the.internet")
            admin.realmId = randomUUID() as String
            admin.passwordHash = hash
            admin.save(failOnError:true)
            adminRole.addToUsers(admin)
            adminRole.save(failOnError:true)
            admin.addToPermissions("*:*")
        }

        // create the category
        def santeCategory = new Category(name: "santé").save()
        def medecinCategory = new Category(name: "medecin").save()
        def pharmacieCategory = new Category(name:  "pharmacie", parent: santeCategory).save()
        def secuCategory = new Category(name:  "sécurité sociale", parent: santeCategory).save()
        def mutuelleCategory = new Category(name:  "mutuelle", parent: santeCategory).save()

        santeCategory
                .addToChilds(medecinCategory)
                .addToChilds(pharmacieCategory)
                .addToChilds(secuCategory)
                .addToChilds(mutuelleCategory)

    }

    def destroy = {
    }
}