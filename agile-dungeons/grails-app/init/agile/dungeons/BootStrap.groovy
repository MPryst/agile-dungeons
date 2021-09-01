package agile.dungeons

class BootStrap {
    
    def init = { servletContext ->    
        Character tako = new Character(
            id: 1,
            name: "Takotsubo",            
            size: "Medium",
            vision: "Darkvision",
            awake: true,            
            perception: 16,)
        .save()

        Character cobalt = new Character(
            id: 2,
            name: "Cobalt",            
            size: "Medium",
            vision: "Darkvision",
            awake: true,
            perception: 12,)
        .save()

        Character logos = new Character(
            id: 3,
            name: "Logos",            
            size: "Medium",
            vision: "Regularvision",
            awake: false,
            perception: 11,)
        .save()

        Character neisseria = new Character(
            id: 4,
            name: "Neisseria",            
            size: "Small",
            vision: "Regularvision",
            awake: true,
            perception: 10,)
        .save()

        Player juli = new Player(username: "Juli", character: tako).save()                
        Player andres = new Player(username: "Andrés", character: logos).save()   
        Player matias = new Player(username: "Matias", character: cobalt).save()     
        Player caro = new Player(username: "Caro", character: neisseria).save()
        
        def before20mins = new Date()
        def before10mins = new Date()
        def before5mins = new Date()
        use (groovy.time.TimeCategory){
            before20mins = before20mins-20.minutes
            before10mins = before10mins-10.minutes
            before5mins = before5mins-5.minutes
        }

        
        new Message(date: before20mins, emisor: tako, receptor: cobalt, approved: false, content: "Saltá hacia la izquierda, Cobalt!").save()
        new Message(date: before10mins, emisor: neisseria, approved: true, content: "Si escucho un ruido busco total cover atrás de los árboles.").save()
        new Message(date: before20mins, receptor: neisseria, approved: true, content: "Te sentis cansada (exhaustion lvl 2)").save()
        new Message(date: before5mins, emisor: cobalt, receptor: logos, approved: null, content: "CORRÉ, LOGOS!!!!").save()
        new Message(date: new Date(), emisor: neisseria, approved: null, content: "Intento sigilosamente robar la bolsa de oro...").save()
    }
    def destroy = {
    }
}
