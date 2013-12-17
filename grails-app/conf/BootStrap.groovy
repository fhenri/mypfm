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
        def santeCategory = new Category()
        def medecinCategory = new Category()

        santeCategory.setName("santé", new Locale("fr", "FR"))
        medecinCategory.setName("médecin", new Locale("fr", "FR"))

        santeCategory.setName("medical", new Locale("en", "US"))
        medecinCategory.setName("doctor", new Locale("en", "US"))

/*
        withLocale(new Locale("fr", "FR")) {
            santeCategory.setName("santé")
            medecinCategory.setName("médecin")
        }

        withLocale(new Locale("en", "US")) {
            santeCategory.setName("medical")
            medecinCategory.setName("doctor")
        }
/*

        santeCategory.setName_en_US("medical");
        santeCategory.setName_fr_FR("santé");
        medecinCategory.setName_en_US("doctor");
        medecinCategory.setName_fr_FR("médecin");

        santeCategory.save()
        medecinCategory.save()
/*
        def pharmacieCategory = new Category(name:  "pharmacie", parent: santeCategory).save()
        def secuCategory = new Category(name:  "sécurité sociale", parent: santeCategory).save()
        def mutuelleCategory = new Category(name:  "mutuelle", parent: santeCategory).save()

        santeCategory
                .addToChilds(medecinCategory)
                .addToChilds(pharmacieCategory)
                .addToChilds(secuCategory)
                .addToChilds(mutuelleCategory)

*/
    }
    def destroy = {
    }
}
