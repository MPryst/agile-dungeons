package agile.dungeons

class BootStrap {    
    
    def init = { servletContext ->    
        Character tako = new Character(            
             "Takotsubo",            
             "Medium",
             "Darkvision",
             true,            
             16,
             38,
             20,)
        .save()

        Character cobalt = new Character(            
             "Cobalt",            
             "Medium",
             "Darkvision",
             true,
             12,
             49,
             49,)
        .save()

        Character logos = new Character(            
             "Logos",            
             "Medium",
             "Regularvision",
             false,
             11,
             44,
             0)
        .save()

        Character neisseria = new Character(
             "Neisseria",            
             "Small",
             "Regularvision",
             true,
             10,
             33,
             28,)
        .save()

        Player juli = new Player(username: "Juli", character: tako).save()                
        Player andres = new Player(username: "Andres", character: logos).save()   
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
