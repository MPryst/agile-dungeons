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
    }
    def destroy = {
    }
}
