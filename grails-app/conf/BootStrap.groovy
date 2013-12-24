import org.mypfm.core.Category
import org.mypfm.user.Role
import org.mypfm.user.User
import org.mypfm.user.UserRole

import static java.util.UUID.randomUUID

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        if(!User.count()) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            def userRole = new Role(authority: 'ROLE_USER').save()

            def admin = new User(username: 'admin', password: 'changeit')
            admin.realmId = randomUUID() as String
            admin.save()

            UserRole.create admin, adminRole, true
            UserRole.create admin, userRole, true
        }

        // create the category
        def medicalCategory = new Category(name: "santé").save()
        def doctorCategory = new Category(name: "medecin", parent:medicalCategory).save()
        def dentistCategory = new Category(name: "dentiste", parent:medicalCategory).save()
        def pharmacyCategory = new Category(name:  "pharmacie", parent: medicalCategory).save()
        def socialCategory = new Category(name:  "sécurité sociale", parent: medicalCategory).save()
        def mutuelCategory = new Category(name:  "mutuelle", parent: medicalCategory).save()
        medicalCategory
                .addToChilds(doctorCategory)
                .addToChilds(dentistCategory)
                .addToChilds(pharmacyCategory)
                .addToChilds(socialCategory)
                .addToChilds(mutuelCategory)

        def foodCategory = new Category(name: "Alimentation").save()
        def cantinaCategory = new Category(name: "Cantine", parent: foodCategory).save()
        def courseCategory = new Category(name: "Course", parent: foodCategory).save()
        def restaurantCategory = new Category(name: "Restaurant", parent: foodCategory).save()
        def snackCategory = new Category(name: "Snack", parent: foodCategory).save()
        foodCategory
            .addToChilds(cantinaCategory)
            .addToChilds(courseCategory)
            .addToChilds(restaurantCategory)
            .addToChilds(snackCategory)

        def autoCategory = new Category(name: "Automobile").save()
        def assuranceCategory = new Category(name: "Assurance", parent: autoCategory).save()
        def petrolCategory = new Category(name: "Carburant", parent: autoCategory).save()
        def autoLoanCategory = new Category(name: "Emprunt", parent: autoCategory).save()
        def fixupCategory = new Category(name: "Entretien", parent: autoCategory).save()
        def tollCategory = new Category(name: "Péage", parent: autoCategory).save()
        autoCategory
            .addToChilds(assuranceCategory)
            .addToChilds(petrolCategory)
            .addToChilds(autoLoanCategory)
            .addToChilds(fixupCategory)
            .addToChilds(tollCategory)

        def giftCategory = new Category(name: "Cadeaux").save()
        def christmasCategory = new Category(name: "Noël", parent: giftCategory).save()
        def birthdayCategory = new Category(name: "Anniversaires", parent: giftCategory).save()
        def friendsCategory = new Category(name: "friends", parent: giftCategory).save()
        giftCategory
            .addToChilds(christmasCategory)
            .addToChilds(birthdayCategory)
            .addToChilds(birthdayCategory)
            .addToChilds(friendsCategory)

        def dressCategory = new Category(name: "Habillement").save()

        def kidsCategory = new Category(name: "Enfants").save()
        def schoolCategory = new Category(name: "Ecole", parent: kidsCategory).save()
        def furnitureCategory = new Category(name: "Habillement", parent: kidsCategory).save()
        def leasureCategory = new Category(name: "Loisirs", parent: kidsCategory).save()
        def nannyCategory = new Category(name: "Nourrice", parent: kidsCategory).save()
        kidsCategory
            .addToChilds(schoolCategory)
            .addToChilds(furnitureCategory)
            .addToChilds(leasureCategory)
            .addToChilds(nannyCategory)

        def financeCategory = new Category(name: "Frais Financiers").save()
        def agiosCategory = new Category(name: "Agios", parent: financeCategory).save()
        def cardCategory = new Category(name: "Carte Bancaire", parent: financeCategory).save()
        financeCategory
            .addToChilds(agiosCategory)
            .addToChilds(cardCategory)

        def taxCategory = new Category(name: "Impôts/Taxe").save()
        def directtaxCategory = new Category(name: "Revenu", parent: taxCategory).save()
        def housetaxCategory = new Category(name: "Fonciers", parent: taxCategory).save()
        def lodgingtaxCategory = new Category(name: "Habitation", parent: taxCategory).save()
        taxCategory
            .addToChilds(directtaxCategory)
            .addToChilds(housetaxCategory)
            .addToChilds(lodgingtaxCategory)

        def lodgingCategory = new Category(name: "Logement").save()
        def chargeCategory = new Category(name: "Charges", parent: lodgingCategory).save()
        def heatCategory = new Category(name: "Chauffage", parent: lodgingCategory).save()
        def waterCategory = new Category(name: "Eau", parent: lodgingCategory).save()
        def electricityCategory = new Category(name: "Electricity", parent: lodgingCategory).save()
        def housewifeCategory = new Category(name: "Employé de maison", parent: lodgingCategory).save()
        def houseAssuranceCategory = new Category(name: "Multi-risques habitation", parent: lodgingCategory).save()
        def houseLoanCategory = new Category(name: "Remboursement Emprunt", parent: lodgingCategory).save()
        def phoneCategory = new Category(name: "Téléphone", parent: lodgingCategory).save()
        lodgingCategory
            .addToChilds(chargeCategory)
            .addToChilds(heatCategory)
            .addToChilds(waterCategory)
            .addToChilds(electricityCategory)
            .addToChilds(housewifeCategory)
            .addToChilds(houseAssuranceCategory)
            .addToChilds(houseLoanCategory)
            .addToChilds(phoneCategory)

        def mainLeasureCategory = new Category(name: "Leasure").save()
        def movieCategory = new Category(name: "Cinéma", parent: mainLeasureCategory).save()
        def sportCategory = new Category(name: "Sports", parent: mainLeasureCategory).save()
        def bookCategory = new Category(name: "Livres", parent: mainLeasureCategory).save()
        def theaterCategory = new Category(name : "Théatre", parent: mainLeasureCategory).save()
        mainLeasureCategory
            .addToChilds(movieCategory)
            .addToChilds(sportCategory)
            .addToChilds(bookCategory)
            .addToChilds(theaterCategory)

        def mainFurnitureCategory = new Category(name: "Mobiliers").save()
        def furnitureDecoCategory = new Category(name: "Décoration", parent: mainFurnitureCategory).save()
        def furnitureElectroCategory = new Category(name: "Electro-ménager", parent: mainFurnitureCategory).save()
        def furnitureGardenCategory = new Category(name: "Jardin", parent: mainFurnitureCategory).save()
        def furnitureBigCategory = new Category(name: "Meubles", parent: mainFurnitureCategory).save()
        def furnitureTechCategory = new Category(name: "Hi Tech", parent: mainFurnitureCategory).save()
        mainFurnitureCategory
            .addToChilds(furnitureDecoCategory)
            .addToChilds(furnitureElectroCategory)
            .addToChilds(furnitureGardenCategory)
            .addToChilds(furnitureBigCategory)
            .addToChilds(furnitureTechCategory)

        def mainCashCategory = new Category(name: "Retrait Cash").save()

        def mainPersonalCategory = new Category(name: "Personnel").save()
        def personalHairCategory = new Category(name: "Coiffeur", parent: mainPersonalCategory).save()
        mainPersonalCategory
            .addToChilds(personalHairCategory)

        def mainHolidayCategory = new Category(name: "Vacances").save()
        def holidayLodgingCategory = new Category(name: "Logement", parent: mainHolidayCategory).save()
        def holidayLifeCategory = new Category(name: "Vie sur Place", parent: mainHolidayCategory).save()
        def holidayTravelCategory = new Category(name: "Voyage", parent: mainHolidayCategory).save()
        mainHolidayCategory
            .addToChilds(holidayLodgingCategory)
            .addToChilds(holidayLifeCategory)
            .addToChilds(holidayTravelCategory)
    }

    def destroy = {
    }
}