import org.apache.shiro.crypto.hash.Sha512Hash
import org.mypfm.user.*

class BootStrap {

    def shiroSecurityService

    def init = { servletContext ->
        //This code is only for demo purposes
        def adminRole = ShiroRole.findByName("Administrator")
        if(!adminRole){
            adminRole = new ShiroRole(name: 'Administrator')
            adminRole.save(failOnError:true)
        }
        def admin = ShiroUser.findByUsername('admin')
        if(!admin){
            def hash = new Sha512Hash("changeit").toHex()
            admin = new ShiroUser(firstName:"Administator", lastName:"User",
                    username: 'admin', email:"me@the.internet")
            admin.passwordHash = hash
            admin.save(failOnError:true)
            adminRole.addToUsers(admin)
            adminRole.save(failOnError:true)
            admin.addToPermissions("*:*")
        }
    }
    def destroy = {
    }
}